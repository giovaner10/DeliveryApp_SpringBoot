package com.app.deliveryapp.api.controller;

import com.app.deliveryapp.domain.model.Estado;
import com.app.deliveryapp.domain.repository.EstadoRepository;
import com.app.deliveryapp.domain.service.EstadoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
@AllArgsConstructor
public class EstadoController {

    private EstadoRepository estadoRepository;
    private EstadoService estadoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @GetMapping("/{idEstado}")
    public ResponseEntity<Optional<Estado>> buscar(@PathVariable Long idEstado) {

        if (estadoRepository.existsById(idEstado)) {
            Optional<Estado> estado = estadoRepository.findById(idEstado);
            return ResponseEntity.ok(estado);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado salvar(@RequestBody Estado estado) {

        return estadoService.salvar(estado);
    }

    @PutMapping("/{idEstado}/atualizar")
    public ResponseEntity<Estado> atualizar(@PathVariable Long idEstado, @RequestBody Estado estado) {

        if(!estadoRepository.existsById(idEstado)){
            return ResponseEntity.notFound().build();
        }
        estado.setId(idEstado);
        Estado EstadoUpdate = estadoRepository.save(estado);
        return  ResponseEntity.ok(EstadoUpdate);
    }

    @DeleteMapping("/{idEstado}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long idEstado) {

         estadoService.deletaEstado(idEstado);

    }


}
