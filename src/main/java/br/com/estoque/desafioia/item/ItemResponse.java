package br.com.estoque.desafioia.item;

import java.math.BigDecimal;

public class ItemResponse {

    public final Long id;
    public final String nome;
    public final String descricao;
    public final String categoria;
    public final Integer quantidade;
    public final BigDecimal preco;

    public ItemResponse(Item item) {
        this.id = item.getId();
        this.nome = item.getNome();
        this.descricao = item.getDescricao();
        this.categoria = item.getCategoria();
        this.quantidade = item.getQuantidade();
        this.preco = item.getPreco();
    }
}


