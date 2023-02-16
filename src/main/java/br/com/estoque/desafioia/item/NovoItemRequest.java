package br.com.estoque.desafioia.item;

import br.com.estoque.desafioia.compartilhado.CampoUnico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record NovoItemRequest(

        @NotBlank
        @CampoUnico(message = "Já existe um item cadastrado com esse nome", fieldName = "nome", domainClass = Item.class)
        String nome,
        @NotBlank
        String descricao,
        @NotBlank
        String categoria,
        @NotNull
        @Positive
        Integer quantidade,
        @NotNull
        @Positive
        BigDecimal preco
) {
    public Item toModel() {
        return new Item(nome, descricao, categoria, quantidade, preco);
    }

}
