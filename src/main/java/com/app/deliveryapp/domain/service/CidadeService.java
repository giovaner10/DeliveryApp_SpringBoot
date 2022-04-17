package com.app.deliveryapp.domain.service;

import com.app.deliveryapp.domain.exception.EntidadeEmUsoException;
import com.app.deliveryapp.domain.exception.EntidadeNaoEcontradaException;
import com.app.deliveryapp.domain.model.Cidade;
import com.app.deliveryapp.domain.repository.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CidadeService {

    private CidadeRepository cidadeRepository;

    public Cidade salvar(Cidade Cidade){
        return cidadeRepository.save(Cidade);
    }

    public void deletaCidade(Long id){

        if(!cidadeRepository.existsById(id)){
            throw new EntidadeNaoEcontradaException("Esse Cidade nao foi localizada no seu banco de dados");
        }

        try {
            cidadeRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException E){
            throw new   EntidadeEmUsoException("Não foi possivel a deleção, pois esse item se encontra associado " +
                    "a outra tabela do seu banco de dados");
        }
    }
}
