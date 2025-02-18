package com.gabriel.OEEManual.controller.produto;

import com.gabriel.OEEManual.domain.produto.Produto;
import com.gabriel.OEEManual.domain.produto.ProdutoCadastroDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<String> listar() {
        System.out.println("OK");
        return ResponseEntity.ok("teste");
    }

    @PostMapping
    @Transactional
    public ResponseEntity listar2(@RequestBody @Valid ProdutoCadastroDTO dados, UriComponentsBuilder uriBuilder) {
        Produto p = new Produto(dados);
        produtoRepository.save(p);

        //var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(p.getId()).toUri(); //created and return object
        return ResponseEntity.ok(p);//created().body(p);
    }

}