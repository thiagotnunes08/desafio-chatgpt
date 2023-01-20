package br.com.estoque.desafioia.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record NovoItemRequest(

        @NotBlank
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
