package com.gabriel.OEEManual.domain.maquina;

import jakarta.validation.constraints.NotNull;

public record MaquinaEditarDTO(
        @NotNull
        Long id,
        String descricao,
        String tag,
        boolean batelada,
        boolean ativo
) {
}
