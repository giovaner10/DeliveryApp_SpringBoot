package com.app.deliveryapp.domain.service;

import com.app.deliveryapp.domain.exception.EntidadeEmUsoException;
import com.app.deliveryapp.domain.exception.EntidadeNaoEcontradaException;
import com.app.deliveryapp.domain.model.Estado;
import com.app.deliveryapp.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EstadoService {

    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado){
        return estadoRepository.save(estado);
    }

    public void deletaEstado(Long id){

        if(!estadoRepository.existsById(id)){
            throw new EntidadeNaoEcontradaException("Esse Estado nao foi localizada no seu banco de dados");
        }

        try {
            estadoRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException E){
            throw new   EntidadeEmUsoException("Não foi possivel a deleção, pois esse item se encontra associado " +
                    "a outra tabela do seu banco de dados");
        }
    }
}
