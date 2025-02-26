package com.gabriel.OEEManual.domain.estrutura;

import jakarta.validation.constraints.NotNull;

public record EstruturaPesquisarDTO(
        @NotNull
        Long idProduto,
        @NotNull
        Long idMaquina
) {

}