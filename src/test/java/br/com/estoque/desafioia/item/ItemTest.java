package br.com.estoque.desafioia.item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ItemTest {

    @Test
    @DisplayName("deve instanciar um item valido")
    void test1() {
        var item = new Item("nome","descricao","categoria",1, BigDecimal.TEN);

        Assertions.assertNotNull(item,"item deve ser válido");
        Assertions.assertEquals(item.getNome(),"nome");
        Assertions.assertEquals(item.getDescricao(),"descricao");
        Assertions.assertEquals(item.getCategoria(),"categoria");
        Assertions.assertEquals(item.getQuantidade(),1);
        Assertions.assertEquals(item.getPreco(),BigDecimal.TEN);

    }

    @Test
    @DisplayName("nao instacia item com parametro vazio")
    void test2() {
        Assertions.assertThrows(IllegalArgumentException.class,()-> new Item("",
                "",
                "",
                1, BigDecimal.TEN));
    }


    @Test
    @DisplayName("nao instacia item com preco negativo")
    void test3() {
        Assertions.assertThrows(IllegalArgumentException.class,()-> new Item("nome",
                "descricao",
                "categoria",
                1, new BigDecimal(-1)));
    }


    @Test
    @DisplayName("nao instacia item com quantidade negativa")
    void test4() {
        Assertions.assertThrows(IllegalArgumentException.class,()-> new Item("nome",
                "descricao",
                "categoria",
                -1, new BigDecimal(1)));
    }
}