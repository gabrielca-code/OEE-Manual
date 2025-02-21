package com.gabriel.OEEManual.domain.produto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity(name = "Produto")
@Table(name = "produto")
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String codigo;
    private boolean ativo;

    public Produto() {}

    public Produto(Long id, String descricao, String codigo) {
        this.id = id;
        this.descricao = descricao;
        this.codigo = codigo;
    }

    public Produto(ProdutoCadastroDTO dados) {
        this.descricao = dados.descricao();
        this.codigo = dados.codigo();
        this.ativo = true;
    }

    public Long getId() {
        return this.id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public boolean getAtivo() {
        return this.ativo;
    }

    public void editarProduto(ProdutoEditarDTO dados) {
        this.descricao = dados.descricao();
        this.codigo = dados.codigo();
        this.ativo = dados.ativo();
    }

    public void desativarProduto(Long id) {
        this.ativo = false;
    }
}
