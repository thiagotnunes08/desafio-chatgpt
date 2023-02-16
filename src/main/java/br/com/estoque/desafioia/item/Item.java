package br.com.estoque.desafioia.item;

import jakarta.persistence.*;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private String categoria;
    @Column(nullable = false)
    private Integer quantidade;
    @Column(nullable = false)
    private BigDecimal preco;

    public Item(String nome, String descricao, String categoria, Integer quantidade, BigDecimal preco) {

        Assert.isTrue(StringUtils.hasText(nome), "nome é obrigatório");
        Assert.isTrue(StringUtils.hasText(descricao), "descricao é obrigatória");
        Assert.isTrue(quantidade >= 0, "quantidade  deve ser positiva");
        Assert.isTrue(preco.compareTo(BigDecimal.ZERO) >= 0, "preco  deve ser positivo");

        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    /**
     * @deprecated hibernate only
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


    public void atualizaItemCompleto(AtualizaItemRequest request) {

       if (request.temNomeValido()) this.nome = request.getNome();
       if (request.temCategoriaValida()) this.categoria = request.getCategoria();
       if (request.temDescricaoValida()) this.descricao = request.getDescricao();
       if (request.temPrecoValido()) this.preco = request.getPreco();
       if (request.temQuantidadeValida()) this.quantidade = request.getQuantidade();
    }

}
