package com.app.deliveryapp.domain.service;

import com.app.deliveryapp.domain.exceptionhandler.EntidadeNaoEncontradaException;
import com.app.deliveryapp.domain.repository.CozinhaRepository;
import com.app.deliveryapp.domain.model.Cozinha;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class CozinhaService {

    private CozinhaRepository cozinhaRepository;

    public static final String NÃO_ECONTRADO = "NÃO ECONTRADO";


    public Cozinha buscarId(Long id){
        return cozinhaRepository.findById(id)
                .orElseThrow(()-> new EntidadeNaoEncontradaException(NÃO_ECONTRADO, id));
    }

    public Cozinha salvar(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    public void deletarCozinha(Long id){

        buscarId(id);

        try {
            cozinhaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException E){

            throw  new ResponseStatusException(HttpStatus.CONFLICT, "ID associado a outra tabela.");

            //throw new   EntidadeEmUsoException("Não foi possivel pois esse item se encontra associado " +
                  //  "a outra tabela do seu banco");
        }
    }
}
