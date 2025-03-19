package com.gabriel.OEEManual.domain.estrutura;

import jakarta.validation.constraints.NotNull;

public record EstruturaEditarDTO(
    @NotNull
    Long id,
    @NotNull
    double velocidade
) {
}
