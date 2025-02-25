package com.gabriel.OEEManual.controller.maquina;

import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity listarMaquina() {
        return null;
    }

    @PostMapping
    @Transactional
    public ResponseEntity criarMaquina() {
        return null;
    }

    @PutMapping
    @Transactional
    public ResponseEntity editarMaquina() {
        return null;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativarMaquina() {
        return null;
    }

}
