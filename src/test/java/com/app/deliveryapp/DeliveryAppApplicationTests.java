package com.app.deliveryapp;

import com.app.deliveryapp.domain.exceptionhandler.EntidadeNaoEncontradaException;
import com.app.deliveryapp.domain.model.Cozinha;
import com.app.deliveryapp.domain.service.CozinhaService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DeliveryAppApplicationTests {

    @Autowired
    private CozinhaService cozinhaService;

    @Test
    void contextLoads() {
    }

    @Test
    public  void testarCadastroCozinhaSucesso(){
        //cenario
        Cozinha cozinhaTestar = new Cozinha();
        cozinhaTestar.setNome("NovoNome");

        //acao
        cozinhaTestar = cozinhaService.salvar(cozinhaTestar);

        //validacao
         Assertions.assertThat(cozinhaTestar.getId()).isNotNull();

    }

    @Test
    public  void testarCadastroCozinhaFracasso(){
        //cenario
        Cozinha cozinhaTestar = new Cozinha();

        //acao

         assertThrows(ConstraintViolationException.class, () -> cozinhaService.salvar(cozinhaTestar));
        //validacao

    }

    @Test
    public  void testarDelecaoDeCozinhaInexistente(){
        //cenario
        Cozinha cozinhaTestar = new Cozinha();
        cozinhaTestar.setNome("NovoNome");
        cozinhaTestar = cozinhaService.salvar(cozinhaTestar);


        //acao
        Long id = cozinhaTestar.getId();
        cozinhaService.deletarCozinha(id);

        //validacao
        assertThrows(EntidadeNaoEncontradaException.class, () -> cozinhaService.deletarCozinha(id));

    }

    @Test
    public  void testarDelecaoDeCozinhaEmUso(){
        //cenario
        Cozinha cozinhaTestar = new Cozinha();
        cozinhaTestar.setNome("NovoNome");
        cozinhaTestar = cozinhaService.salvar(cozinhaTestar);



        //acao
        Long id = cozinhaTestar.getId();
        cozinhaService.deletarCozinha(id);

        //validacao
        assertThrows(EntidadeNaoEncontradaException.class, () -> cozinhaService.deletarCozinha(id));

    }
}
