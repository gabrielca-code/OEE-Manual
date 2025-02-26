
package com.gabriel.OEEManual.domain.estrutura;

import com.gabriel.OEEManual.domain.maquina.Maquina;
import com.gabriel.OEEManual.domain.produto.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Estrutura")
@Table(name = "estrutura")
public class Estrutura {

    @EmbeddedId
    private EstruturaID estruturaID;
    private double velocidade;

    public Estrutura() {}

    public Estrutura(Produto produto, Maquina maquina, double velocidade) {
        this.estruturaID = new EstruturaID(produto, maquina);
        this.velocidade = velocidade;
    }

    public Produto getProduto() {
        return this.estruturaID.getProduto();
    }

    public Maquina getMaquina() {
        return this.estruturaID.getMaquina();
    }

    public double getVelocidade() {
        return this.velocidade;
    }

}