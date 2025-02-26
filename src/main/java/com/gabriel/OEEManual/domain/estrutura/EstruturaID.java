package com.gabriel.OEEManual.domain.estrutura;

import com.gabriel.OEEManual.domain.maquina.Maquina;
import com.gabriel.OEEManual.domain.produto.Produto;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Embeddable
public class EstruturaID {

    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Maquina maquina;

    public EstruturaID() {}

    public EstruturaID(Produto produto, Maquina maquina) {
        this.produto = produto;
        this.maquina = maquina;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public Maquina getMaquina() {
        return this.maquina;
    }

}