package br.com.estoque.desafioia.item;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private  String nome;
    @Column(nullable = false)
    private  String descricao;
    @Column(nullable = false)
    private  String categoria;
    @Column(nullable = false)
    private  Integer quantidade;
    @Column(nullable = false)
    private  BigDecimal preco;

    public Item(String nome, String descricao, String categoria, Integer quantidade, BigDecimal preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    /**
     * @deprecated
     * hibernate only
     */
    @Deprecated
    public Item() {
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