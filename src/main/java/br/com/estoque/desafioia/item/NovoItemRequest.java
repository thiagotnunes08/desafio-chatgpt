package br.com.estoque.desafioia.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class NovoItemRequest {

    @NotBlank
    private final String nome;
    @NotBlank
    private final String descricao;
    @NotBlank
    private final String categoria;
    @Positive
    @NotNull
    private final Integer quantidade;
    @Positive
    @NotNull
    private final BigDecimal preco;

    public NovoItemRequest(String nome, String descricao, String categoria, Integer quantidade, BigDecimal preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Item toModel() {
        return new Item(nome,descricao,categoria,quantidade,preco);
    }
}
