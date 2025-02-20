package com.gabriel.OEEManual.domain.produto;

public record ProdutoListarItemDTO(
        Long id,
        String descricao,
        String codigo
) {
    public ProdutoListarItemDTO(Produto dados) {
        this(dados.getId(), dados.getDescricao(), dados.getCodigo());
    }
}
