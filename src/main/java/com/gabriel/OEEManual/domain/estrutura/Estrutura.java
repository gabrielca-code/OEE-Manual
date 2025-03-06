
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idMaquina")
    private Maquina maquina;

    @ManyToOne
    @JoinColumn(name = "idProduto")
    private Produto produto;
    private double velocidade;

    public Estrutura() {}

    public Estrutura(Produto produto, Maquina maquina, double velocidade) {
        this.maquina = maquina;
        this.produto = produto;
        this.velocidade = velocidade;
    }

    public Long getId() {
        return this.id;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public Maquina getMaquina() {
        return this.maquina;
    }

    public double getVelocidade() {
        return this.velocidade;
    }

}