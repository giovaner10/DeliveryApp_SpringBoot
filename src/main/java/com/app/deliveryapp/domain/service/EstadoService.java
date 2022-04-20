package com.app.deliveryapp.domain.service;

import com.app.deliveryapp.domain.exceptionhandler.EntidadeNaoEncontradaException;
import com.app.deliveryapp.domain.model.Estado;
import com.app.deliveryapp.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EstadoService {

    private EstadoRepository estadoRepository;


    public static final String NÃO_ECONTRADO = "NÃO ECONTRADO";

    public Estado buscarId(Long id){
        return estadoRepository.findById(id)
                .orElseThrow(()-> new EntidadeNaoEncontradaException(NÃO_ECONTRADO));
    }

    public Estado salvar(Estado estado){
        return estadoRepository.save(estado);
    }

    public void deletaEstado(Long id){

        buscarId(id);
        try {
            estadoRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException E){
            throw new EntidadeNaoEncontradaException("Não foi possivel a deleção, pois esse item se encontra associado " +
                    "a outra tabela do seu banco de dados");
        }
    }
}
