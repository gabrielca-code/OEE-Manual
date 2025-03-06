package com.gabriel.OEEManual.domain.estrutura;

import com.gabriel.OEEManual.domain.maquina.Maquina;
import com.gabriel.OEEManual.domain.produto.Produto;

public record EstruturaListarItemDTO(
        Long id,
        Produto produto,
        Maquina maquina,
        double velocidade
) {

    public EstruturaListarItemDTO(Estrutura estrutura) {
        this(estrutura.getId(), estrutura.getProduto(), estrutura.getMaquina(), estrutura.getVelocidade());
    }

}
