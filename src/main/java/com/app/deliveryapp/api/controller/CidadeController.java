package com.app.deliveryapp.api.controller;

import com.app.deliveryapp.domain.model.Cidade;
import com.app.deliveryapp.domain.repository.CidadeRepository;
import com.app.deliveryapp.domain.service.CidadeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
@AllArgsConstructor
public class CidadeController {

    private CidadeRepository cidadeRepository;
    private CidadeService cidadeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{idCidade}")
    public Cidade buscar(@PathVariable Long idCidade) {
        return cidadeService.buscarId(idCidade);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade salvar(@RequestBody Cidade Cidade) {

        return cidadeService.salvar(Cidade);
    }

    @PutMapping("/{idCidade}/atualizar")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long idCidade, @RequestBody Cidade cidade) {

        cidadeService.buscarId(idCidade);

        cidade.setId(idCidade);
        Cidade CidadeUpdate = cidadeRepository.save(cidade);
        return  ResponseEntity.ok(CidadeUpdate);
    }

    @DeleteMapping("/{idCidade}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long idCidade) {

        cidadeService.deletaCidade(idCidade);

    }





}
