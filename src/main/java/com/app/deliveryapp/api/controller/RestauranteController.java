package com.app.deliveryapp.api.controller;

import com.app.deliveryapp.domain.repository.RestauranteRepository;
import com.app.deliveryapp.domain.model.Restaurante;
import com.app.deliveryapp.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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

        if (!restauranteRepository.existsById(idRestaurante)) {
            return ResponseEntity.notFound().build();
        }
        Optional<Restaurante> restauranteAntigo = restauranteRepository.findById(idRestaurante);
        BeanUtils.copyProperties(restaurante, restauranteAntigo, "formaPagamento",
                "id", "endereco", "dataCadastro");
        restaurante.setId(idRestaurante);
        Restaurante restauranteUpdate = restauranteRepository.save(restaurante);
        return ResponseEntity.ok(restauranteUpdate);
    }


    @DeleteMapping("/{idRestaurante}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long idRestaurante) {

        restauranteService.deletaRestaurante(idRestaurante);

    }


    @PatchMapping("/{idRestaurante}/atualizar")
    public ResponseEntity<Restaurante> atualizarCampo(@PathVariable Long idRestaurante,
                                                      @RequestBody Map<String, Object> campos) {

        if (!restauranteRepository.existsById(idRestaurante)) {
            return ResponseEntity.notFound().build();
        } else {
            Restaurante restauranteAtualizar = restauranteRepository.getById(idRestaurante);
            restauranteAtualizar.setId(idRestaurante);

            merge(restauranteAtualizar, campos);

            Restaurante restauranteUpdate = restauranteRepository.save(restauranteAtualizar);
            return ResponseEntity.ok(restauranteUpdate);
        }
    }

    private void merge(Restaurante restauranteDestino, Map<String, Object> dadosOrigem) {

        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomePropiedade, valorPropiedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropiedade);
            field.setAccessible(true);

            // Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
            //System.out.println(novoValor);

            ReflectionUtils.setField(field, restauranteDestino, valorPropiedade);


        });


    }

    @GetMapping("/frete")
    public List<Restaurante> fretes(BigDecimal taxaInicial, BigDecimal taxaFinal) {

        //aplicando esses valores nos params chaves e valor

        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }



}