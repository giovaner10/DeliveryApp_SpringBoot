package com.app.deliveryapp.api.controller;

import com.app.deliveryapp.domain.repository.RestauranteRepository;
import com.app.deliveryapp.domain.model.Restaurante;
import com.app.deliveryapp.domain.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurante")
@AllArgsConstructor
public class RestauranteController {

    private RestauranteRepository restauranteRepository;
    private RestauranteService restauranteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/{idRestaurante}")
    public ResponseEntity<Optional<Restaurante>> buscar(@PathVariable Long idRestaurante) {

        if (restauranteRepository.existsById(idRestaurante)) {
            Optional<Restaurante> restaurante = restauranteRepository.findById(idRestaurante);
            return ResponseEntity.ok(restaurante);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante salvar(@RequestBody Restaurante restaurante) {

        return restauranteService.salvar(restaurante);
    }

    @PutMapping("/{idRestaurante}/atualizar")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long idRestaurante, @RequestBody Restaurante restaurante) {

        if(!restauranteRepository.existsById(idRestaurante)){
            return ResponseEntity.notFound().build();
        }
        restaurante.setId(idRestaurante);
        Restaurante restauranteUpdate = restauranteRepository.save(restaurante);
        return  ResponseEntity.ok(restauranteUpdate);
    }


    @DeleteMapping("/{idRestaurante}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long idRestaurante) {

        restauranteService.deletaRestaurante(idRestaurante);

    }


}
