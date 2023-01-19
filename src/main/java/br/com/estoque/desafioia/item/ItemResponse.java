package br.com.estoque.desafioia.item;

import java.math.BigDecimal;

public class ItemResponse {

    private Long id;
    private String nome;
    private String descricao;
    private String categoria;
    private Integer quantidade;
    private BigDecimal preco;

    public ItemResponse(Item novoItem) {
        this.id = novoItem.getId();
        this.nome = novoItem.getNome();
        this.categoria = novoItem.getCategoria();
        this.descricao = novoItem.getDescricao();
        this.quantidade = novoItem.getQuantidade();
        this.preco = novoItem.getPreco();
    }

    public Long getId() {
        return id;
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
}
