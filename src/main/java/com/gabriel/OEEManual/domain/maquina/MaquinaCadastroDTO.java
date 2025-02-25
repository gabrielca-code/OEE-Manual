package com.gabriel.OEEManual.domain.maquina;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MaquinaCadastroDTO(
        @NotBlank
        String descricao,
        @NotBlank
        String tag,
        @NotNull
        boolean batelada,
        @NotNull
        boolean ativo
) {
}
