package com.app.deliveryapp.domain.service;

import com.app.deliveryapp.domain.exceptionhandler.EntidadeEmUsoException;
import com.app.deliveryapp.domain.exceptionhandler.EntidadeNaoEncontradaException;
import com.app.deliveryapp.domain.model.Cidade;
import com.app.deliveryapp.domain.repository.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CidadeService {

    private CidadeRepository cidadeRepository;
    private EstadoService estadoService;

    public static final String NÃO_ECONTRADO = "NÃO ECONTRADO";

    public Cidade buscarId(Long id){
        return cidadeRepository.findById(id)
               .orElseThrow(()-> new EntidadeNaoEncontradaException(NÃO_ECONTRADO, id));
    }
    public Cidade salvar(Cidade cidade){
        estadoService.buscarId(cidade.getEstado().getId());
        return cidadeRepository.save(cidade);
    }

    public void deletaCidade(Long id){

       buscarId(id);
        cidadeRepository.deleteById(id);


        /*try {
            cidadeRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException E){
            //throw new EntidadeEmUsoException("Não foi possivel a deleção, pois esse item se encontra associado " +
                //    "a outra tabela do seu banco de dados");
        }*/
    }
}
