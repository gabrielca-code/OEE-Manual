package com.gabriel.OEEManual.domain.maquina;

public record MaquinaListarItemDTO(
        Long id,
        String descricao,
        String tag,
        boolean batelada,
        boolean ativo
) {

    public MaquinaListarItemDTO(Maquina maquina) {
        this(maquina.getId(), maquina.getDescricao(), maquina.getTag(), maquina.getBatelada(), maquina.getAtivo());
    }

}