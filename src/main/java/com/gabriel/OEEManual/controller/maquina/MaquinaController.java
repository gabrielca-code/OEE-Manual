package com.gabriel.OEEManual.controller.maquina;

import com.gabriel.OEEManual.domain.maquina.Maquina;
import com.gabriel.OEEManual.domain.maquina.MaquinaCadastroDTO;
import com.gabriel.OEEManual.domain.maquina.MaquinaEditarDTO;
import com.gabriel.OEEManual.domain.maquina.MaquinaListarItemDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/maquinas")
public class MaquinaController {

    @Autowired
    private MaquinaRepository maquinaRepository;

    @GetMapping
    public ResponseEntity listarMaquinas() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity listarMaquina(@PathVariable Long id) {
        var maquina = maquinaRepository.getReferenceById(id);

        return ResponseEntity.ok().body(new MaquinaListarItemDTO(maquina));
    }

    @PostMapping
    @Transactional
    public ResponseEntity criarMaquina(@RequestBody @Valid MaquinaCadastroDTO dados, UriComponentsBuilder uriBuilder) {
        var maquina = new Maquina(dados);
        maquinaRepository.save(maquina);

        var uri = uriBuilder.path("maquinas/{id}").buildAndExpand(maquina.getId()).toUri();

        return ResponseEntity.created(uri).body(new MaquinaListarItemDTO(maquina));
    }

    @PutMapping
    @Transactional
    public ResponseEntity editarMaquina(@RequestBody @Valid MaquinaEditarDTO dados) {
        var maquina = maquinaRepository.getReferenceById(dados.id());
        maquina.editarMaquina(dados);

        return ResponseEntity.ok(new MaquinaListarItemDTO(maquina));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativarMaquina() {
        return null;
    }

}
