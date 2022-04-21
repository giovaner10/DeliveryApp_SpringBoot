package com.app.deliveryapp.api.controller;

import com.app.deliveryapp.domain.repository.CozinhaRepository;
import com.app.deliveryapp.domain.model.Cozinha;
import com.app.deliveryapp.domain.service.CozinhaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cozinha")
@AllArgsConstructor
public class CozinhaController {

    private CozinhaRepository cozinhaRepository;
    private CozinhaService cozinhaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{idCozinha}")
    public Cozinha buscar(@PathVariable Long idCozinha) {

        return cozinhaService.buscarId(idCozinha);
        /*if (cozinhaRepository.existsById(idCozinha)) {
            Optional<Cozinha> cozinha = cozinhaRepository.findById(idCozinha);
            return ResponseEntity.ok(cozinha);
        }
        return ResponseEntity.notFound().build();*/
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha salvar(@RequestBody @Valid Cozinha cozinha) {

        return cozinhaService.salvar(cozinha);
    }

    @PutMapping("/{idCozinha}/atualizar")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long idCozinha, @RequestBody Cozinha cozinha) {

         cozinhaService.buscarId(idCozinha);

        cozinha.setId(idCozinha);
        Cozinha cozinhaUpdate = cozinhaRepository.save(cozinha);
        return  ResponseEntity.ok(cozinhaUpdate);
    }

    @DeleteMapping("/{idCozinha}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long idCozinha) {

         cozinhaService.deletarCozinha(idCozinha);

    }


}
