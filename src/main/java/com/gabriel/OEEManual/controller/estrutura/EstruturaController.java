package com.gabriel.OEEManual.controller.estrutura;

import com.gabriel.OEEManual.controller.maquina.MaquinaRepository;
import com.gabriel.OEEManual.controller.produto.ProdutoRepository;
import com.gabriel.OEEManual.domain.estrutura.Estrutura;
import com.gabriel.OEEManual.domain.estrutura.EstruturaListarItemDTO;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
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

    @Autowired
    private MaquinaRepository maquinaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/{id}")
    public ResponseEntity listarEstrutura(@PathVariable Long id) {
        var estrutura = estruturaRepository.getReferenceById(id);

        return ResponseEntity.ok().body(new EstruturaListarItemDTO(estrutura));
    }

    @GetMapping
    public ResponseEntity<Page<EstruturaListarItemDTO>> listarTodasEstruturas(@PageableDefault(size = 20, sort = {"id"}) Pageable pagina) {
        var paginaRetorno = estruturaRepository.findAll(pagina).map(EstruturaListarItemDTO::new);
        return ResponseEntity.ok(paginaRetorno);
    }

}
