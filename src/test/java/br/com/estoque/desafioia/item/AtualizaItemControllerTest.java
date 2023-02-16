package br.com.estoque.desafioia.item;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AtualizaItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ItemRepository repository;


    @Test
    @DisplayName("nao atualiza o item, pois o mesmo é inválido ou nao foi encontrado")
    void test1() throws Exception {

        var itemRequest =

                new AtualizaItemRequest("arroz",
                        "alimenticio",
                        "graos",
                        100,
                        BigDecimal.TEN);

        var payload = mapper.writeValueAsString(itemRequest);


        var request = MockMvcRequestBuilders
                .put("/item/{id}",Integer.MAX_VALUE)
                .contentType(APPLICATION_JSON)
                .content(payload)
                .header("Accept-Language", "pt-br");


        var resolvedException = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNotFound())
                .andReturn()
                .getResolvedException();

        Assertions.assertNotNull(resolvedException);
        Assertions.assertEquals(resolvedException.getClass(), ResponseStatusException.class);
        Assertions.assertEquals("Item %s inválido ou não encontrado no sistema!"
                        .formatted(Integer.MAX_VALUE),
                ((ResponseStatusException) resolvedException).getReason());


    }

    @Test
    @DisplayName("deve atualizar um item existente")
    void test2() throws Exception{

        var itemRequest =

                new AtualizaItemRequest("arroz",
                        "alimenticio",
                        "graos",
                        100,
                       new BigDecimal("10").setScale(2, RoundingMode.DOWN));

        var item = new Item("arroz",
                "alimentos",
                "graos",1,
                BigDecimal.ONE);

        repository.save(item);


        var payload = mapper.writeValueAsString(itemRequest);

        var request = MockMvcRequestBuilders
                .put("/item/{id}",item.getId()).contentType(APPLICATION_JSON)
                .content(payload)
                .header("Accept-Language","pt-br");


        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());


        var possivelItem = repository.findById(item.getId());

        Assertions.assertTrue(possivelItem.isPresent());

        var itemAtualizado = possivelItem.get();

        Assertions.assertEquals(itemRequest.getNome(),itemAtualizado.getNome());
        Assertions.assertEquals(itemRequest.getCategoria(),itemAtualizado.getCategoria());
        Assertions.assertEquals(itemRequest.getDescricao(),itemAtualizado.getDescricao());
        Assertions.assertEquals(itemRequest.getQuantidade(),itemAtualizado.getQuantidade());
        Assertions.assertEquals(itemRequest.getPreco(),itemAtualizado.getPreco());

    }
}