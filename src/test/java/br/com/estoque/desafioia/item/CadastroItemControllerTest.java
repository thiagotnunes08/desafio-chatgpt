package br.com.estoque.desafioia.item;


import br.com.estoque.desafioia.compartilhado.CampoUnicoValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CadastroItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ItemRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("deve cadastrar um item valido")
    void test1() throws Exception {

        var itemRequest = new NovoItemRequest(
                "arroz",
                "graos",
                "alimento",
                10,
                BigDecimal.TEN);

        var payload = mapper.writeValueAsString(itemRequest);

        var request = MockMvcRequestBuilders
                .post("/itens")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
                .header("Accept-Language","pt-br");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andExpect(MockMvcResultMatchers
                        .redirectedUrlPattern("http://localhost/itens/*"));


       var list = repository.findAll();

        Assertions.assertEquals(1,list.size());

    }

    @Test
    @DisplayName("nao cadastra um item com nome,descricao e categoria em branco")
    void test2() throws Exception{
        var itemRequest = new NovoItemRequest(
                "",
                "",
                "",
                10,
                BigDecimal.TEN);

        var payload = mapper.writeValueAsString(itemRequest);

        var request = MockMvcRequestBuilders.post("/itens")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
                .header("Accept-Language","pt-br");

        var resolvedException = mockMvc
                .perform(request)
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest())
                .andReturn()
                .getResolvedException();

        Assertions.assertNotNull(resolvedException);
        Assertions.assertEquals(MethodArgumentNotValidException.class,resolvedException.getClass());
        Assertions.assertEquals(3,((MethodArgumentNotValidException)resolvedException).getErrorCount());

    }


    @Test
    @DisplayName("nao cadastra um item com nome preco e quantidade nulo")
    void test3() throws Exception{
        var itemRequest = new NovoItemRequest(
                "arroz",
                "grao",
                "alimentos",
                null,
                null);

        var payload = mapper.writeValueAsString(itemRequest);

        var request = MockMvcRequestBuilders.post("/itens")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
                .header("Accept-Language","pt-br");

        var resolvedException = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn().getResolvedException();

        Assertions.assertNotNull(resolvedException);
        Assertions.assertEquals(MethodArgumentNotValidException.class,resolvedException.getClass());
        Assertions.assertEquals(2,((MethodArgumentNotValidException)resolvedException).getErrorCount());

    }

    @Test
    @DisplayName("nao cadastra um item com preco e quantidade negativos")
    void test4() throws Exception{
        var itemRequest = new NovoItemRequest(
                "arroz",
                "grao",
                "alimentos",
                -1,
                new BigDecimal("-1"));

        var payload = mapper.writeValueAsString(itemRequest);

        var request = MockMvcRequestBuilders.post("/itens")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
                .header("Accept-Language","pt-br");

        var resolvedException = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn().getResolvedException();

        Assertions.assertNotNull(resolvedException);
        Assertions.assertEquals(MethodArgumentNotValidException.class,resolvedException.getClass());
        Assertions.assertEquals(2,((MethodArgumentNotValidException)resolvedException).getErrorCount());

    }

    @Test
    @DisplayName("nao cadastra item 2x, pois nome já eh existente")
    void test5() throws Exception {

        var itemRequest = new NovoItemRequest(
                "arroz",
                "graos",
                "alimento",
                10,
                BigDecimal.TEN);

        var payload = mapper.writeValueAsString(itemRequest);

        var request = MockMvcRequestBuilders
                .post("/itens")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
                .header("Accept-Language","pt-br");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andExpect(MockMvcResultMatchers
                        .redirectedUrlPattern("http://localhost/itens/*"));

        var resolvedException = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn().getResolvedException();

        var list = repository.findAll();

        Assertions.assertEquals(1,list.size());

        Assertions.assertNotNull(resolvedException);

        Assertions.assertEquals(MethodArgumentNotValidException.class,resolvedException.getClass());

        Assertions.assertEquals(1,((MethodArgumentNotValidException)resolvedException).getErrorCount());


    }
}