package com.app.deliveryapp.domain.service;

import com.app.deliveryapp.domain.exceptionhandler.EntidadeNaoEncontradaException;
import com.app.deliveryapp.domain.repository.RestauranteRepository;
import com.app.deliveryapp.domain.model.Restaurante;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestauranteService {

    private RestauranteRepository restauranteRepository;

    public static final String NÃO_ECONTRADO = "NÃO ECONTRADO";

    public Restaurante buscarId(Long id){
        return restauranteRepository.findById(id)
                .orElseThrow(()-> new EntidadeNaoEncontradaException(NÃO_ECONTRADO));
    }

    public Restaurante salvar(Restaurante cozinha){
        return restauranteRepository.save(cozinha);
    }

    public void deletaRestaurante(Long id){

        buscarId(id);

        try {
            restauranteRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException E){
            throw new EntidadeNaoEncontradaException("Não foi possivel pois esse item se encontra associado " +
                    "a outra tabela do seu banco de dados");
        }
    }
}
