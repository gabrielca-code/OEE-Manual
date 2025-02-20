package com.gabriel.OEEManual.controller.produto;

import com.gabriel.OEEManual.domain.produto.Produto;
import com.gabriel.OEEManual.domain.produto.ProdutoCadastroDTO;
import com.gabriel.OEEManual.domain.produto.ProdutoEditarDTO;
import com.gabriel.OEEManual.domain.produto.ProdutoListarItemDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<Page<ProdutoListarItemDTO>> listarTodosProdutos(@PageableDefault(size = 10, sort = {"codigo"}) Pageable paginacao) {
        var page = produtoRepository.findAll(paginacao).map(ProdutoListarItemDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listaProdutoEspecifico(@PathVariable Long id) {
        var produto = produtoRepository.getReferenceById(id);
        return ResponseEntity.ok(new ProdutoListarItemDTO(produto));
    }

    @PostMapping
    @Transactional
    public ResponseEntity criarProduto(@RequestBody @Valid ProdutoCadastroDTO dados, UriComponentsBuilder uriBuilder) {
        var p = new Produto(dados);
        produtoRepository.save(p);

        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(p.getId()).toUri(); //created and return object
        return ResponseEntity.created(uri).body(new ProdutoListarItemDTO(p));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity editarProduto(@RequestBody @Valid ProdutoEditarDTO dados) {
        var produto = produtoRepository.getReferenceById(dados.id());
        produto.editarProduto(dados);

        return ResponseEntity.ok(new ProdutoListarItemDTO(produto));
    }

    

}