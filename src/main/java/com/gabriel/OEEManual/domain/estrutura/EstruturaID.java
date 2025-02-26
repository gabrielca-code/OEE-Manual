package com.gabriel.OEEManual.domain.estrutura;

import com.gabriel.OEEManual.domain.maquina.Maquina;
import com.gabriel.OEEManual.domain.produto.Produto;
import jakarta.persistence.Embeddable;

@Embeddable
public class EstruturaID {

    private Produto produto;
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