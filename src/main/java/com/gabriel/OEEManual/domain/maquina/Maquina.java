package com.gabriel.OEEManual.domain.maquina;

import jakarta.persistence.*;

@Entity(name = "Maquina")
@Table(name = "maquina")
public class Maquina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String tag;
    private boolean batelada;
    private boolean ativo;

    public Maquina() {}

    public Maquina(String descricao, String tag, boolean batelada) {
        this.descricao = descricao;
        this.tag = tag;
        this.batelada = batelada;
        this.ativo = true;
    }

    public Maquina(MaquinaCadastroDTO dados) {
        this.descricao = dados.descricao();
        this.tag = dados.tag();
        this.batelada = dados.batelada();
        this.ativo = dados.ativo();
    }

    public Long getId() {
        return this.id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getTag() {
        return this.tag;
    }

    public boolean getBatelada() {
        return this.batelada;
    }

    public boolean getAtivo() {
        return this.ativo;
    }

    public void editarMaquina(MaquinaEditarDTO dados) {
        this.descricao = dados.descricao();
        this.tag = dados.tag();
        this.batelada = dados.batelada();
        this.ativo = dados.ativo();
    }
}