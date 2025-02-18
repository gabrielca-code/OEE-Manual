package com.gabriel.OEEManual.domain.produto;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Produto")
@Table(name = "produto")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String codigo;

    public Produto(ProdutoCadastroDTO dados) {
        this.descricao = dados.descricao();
        this.codigo = dados.codigo();
    }

}
