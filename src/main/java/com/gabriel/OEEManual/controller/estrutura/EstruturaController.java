package com.gabriel.OEEManual.controller.estrutura;

import com.gabriel.OEEManual.controller.maquina.MaquinaController;
import com.gabriel.OEEManual.controller.maquina.MaquinaRepository;
import com.gabriel.OEEManual.controller.produto.ProdutoRepository;
import com.gabriel.OEEManual.domain.estrutura.Estrutura;
import com.gabriel.OEEManual.domain.estrutura.EstruturaListarItemDTO;
import com.gabriel.OEEManual.domain.estrutura.EstruturaPesquisarDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estruturas")
public class EstruturaController {

    @Autowired
    private EstruturaRepository estruturaRepository;

    @Autowired
    private MaquinaRepository maquinaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/item")
    public ResponseEntity listarEstrutura(@RequestBody @Valid EstruturaPesquisarDTO dados) {
        //var estrutura = estruturaRepository.buscarEstrutura(dados.idProduto(), dados.idMaquina());

        //return ResponseEntity.ok().body(new EstruturaListarItemDTO(estrutura));
        return null;
    }

}
