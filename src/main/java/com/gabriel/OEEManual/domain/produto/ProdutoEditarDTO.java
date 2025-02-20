package com.gabriel.OEEManual.domain.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoEditarDTO(
        @NotNull
        Long id,
        @NotBlank
        String descricao,
        @NotBlank
        String codigo
) {

}