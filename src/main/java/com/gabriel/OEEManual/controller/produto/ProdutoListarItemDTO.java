package com.gabriel.OEEManual.controller.produto;

import com.gabriel.OEEManual.domain.produto.Produto;
import jakarta.validation.constraints.NotBlank;

public record ProdutoListarItemDTO(
        Long id,
        String descricao,
        String codigo
) {
    public ProdutoListarItemDTO(Produto dados) {
        this(dados.getId(), dados.getDescricao(), dados.getCodigo());
    }
}
