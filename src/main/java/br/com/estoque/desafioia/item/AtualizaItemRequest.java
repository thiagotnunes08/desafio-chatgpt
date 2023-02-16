package br.com.estoque.desafioia.item;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Objects;

public class AtualizaItemRequest {


    private final String nome;
    private final String descricao;
    private final String categoria;
    @Positive
    private final Integer quantidade;
    @Positive
    private final BigDecimal preco;

    public AtualizaItemRequest(String nome, String descricao, String categoria, Integer quantidade, BigDecimal preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public boolean temNomeValido() {
        return Objects.nonNull(nome);
    }

    public boolean temCategoriaValida() {
        return Objects.nonNull(categoria);
    }

    public boolean temDescricaoValida() {
        return Objects.nonNull(descricao);
    }

    public boolean temQuantidadeValida() {
        return Objects.nonNull(quantidade);
    }

    public boolean temPrecoValido() {
        return Objects.nonNull(preco);
    }
}
