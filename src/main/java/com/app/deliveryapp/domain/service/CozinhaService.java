package com.app.deliveryapp.domain.service;

import com.app.deliveryapp.domain.repository.CozinhaRepository;
import com.app.deliveryapp.domain.exception.EntidadeEmUsoException;
import com.app.deliveryapp.domain.exception.EntidadeNaoEcontradaException;
import com.app.deliveryapp.domain.model.Cozinha;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CozinhaService {

    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    public void deletarCozinha(Long id){

        if(!cozinhaRepository.existsById(id)){
            throw new EntidadeNaoEcontradaException("Esse cozinha nao foi localizada no seu banco");
        }

        try {
            cozinhaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException E){
            throw new   EntidadeEmUsoException("Não foi possivel pois esse item se encontra associado " +
                    "a outra tabela do seu banco");
        }
    }
}
