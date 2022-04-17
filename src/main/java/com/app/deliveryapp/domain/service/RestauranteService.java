package com.app.deliveryapp.domain.service;

import com.app.deliveryapp.domain.repository.RestauranteRepository;
import com.app.deliveryapp.domain.exception.EntidadeEmUsoException;
import com.app.deliveryapp.domain.exception.EntidadeNaoEcontradaException;
import com.app.deliveryapp.domain.model.Restaurante;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestauranteService {

    private RestauranteRepository restauranteRepository;

    public Restaurante salvar(Restaurante cozinha){
        return restauranteRepository.save(cozinha);
    }

    public void deletaRestaurante(Long id){

        if(!restauranteRepository.existsById(id)){
            throw new EntidadeNaoEcontradaException("Esse restaurante nao foi localizada no seu banco de dados");
        }

        try {
            restauranteRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException E){
            throw new   EntidadeEmUsoException("Não foi possivel pois esse item se encontra associado " +
                    "a outra tabela do seu banco de dados");
        }
    }
}
