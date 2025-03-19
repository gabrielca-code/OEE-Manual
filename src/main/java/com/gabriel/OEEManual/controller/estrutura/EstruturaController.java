package com.gabriel.OEEManual.controller.estrutura;

import com.gabriel.OEEManual.controller.maquina.MaquinaRepository;
import com.gabriel.OEEManual.controller.produto.ProdutoRepository;
import com.gabriel.OEEManual.domain.estrutura.EstruturaEditarDTO;
import com.gabriel.OEEManual.domain.estrutura.EstruturaListarItemDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("estruturas")
public class EstruturaController {

    @Autowired
    private EstruturaRepository estruturaRepository;

    @GetMapping("/{id}")
    public ResponseEntity listarEstrutura(@PathVariable Long id) {
        var estrutura = estruturaRepository.getReferenceById(id);
        //System.out.println(estrutura.getProduto().getId());
        //System.out.println(estrutura.getMaquina().getId());
        return ResponseEntity.ok().body(new EstruturaListarItemDTO(estrutura));
    }

    @GetMapping
    public ResponseEntity<Page<EstruturaListarItemDTO>> listarTodasEstruturas(@PageableDefault(size = 20, sort = {"id"}) Pageable pagina) {
        var paginaRetorno = estruturaRepository.findAll(pagina).map(EstruturaListarItemDTO::new);
        return ResponseEntity.ok(paginaRetorno);
    }

    @PutMapping
    @Transactional
    public ResponseEntity editarEstrutura(@RequestBody @Valid EstruturaEditarDTO dados) {
        var estrutura = estruturaRepository.getReferenceById(dados.id());
        estrutura.editar(dados);

        return ResponseEntity.ok(new EstruturaListarItemDTO(estrutura));
    }

}
