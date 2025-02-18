package com.gabriel.OEEManual.domain.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ProdutoCadastroDTO(
        @NotBlank
        String descricao,
        @NotBlank
        String codigo
) {

}
