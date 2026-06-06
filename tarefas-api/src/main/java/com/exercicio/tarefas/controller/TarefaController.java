package com.exercicio.tarefas.controller;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.exercicio.tarefas.dto.TarefaRequest;
import com.exercicio.tarefas.dto.TarefaResponse;
import com.exercicio.tarefas.model.Tarefa;
import com.exercicio.tarefas.service.TarefaService;

@Validated
@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public ResponseEntity<List<TarefaResponse>> listarTodas() {
        List<TarefaResponse> tarefas = tarefaService.listarTodas().stream()
                .map(TarefaResponse::from)
                .toList();

        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponse> buscarPorId(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(TarefaResponse.from(tarefaService.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<TarefaResponse> criar(@Valid @RequestBody TarefaRequest request) {
        Tarefa tarefaCriada = tarefaService.criar(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tarefaCriada.getId())
                .toUri();

        return ResponseEntity.created(location).body(TarefaResponse.from(tarefaCriada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponse> atualizar(@PathVariable @Positive Long id,
            @Valid @RequestBody TarefaRequest request) {
        return ResponseEntity.ok(TarefaResponse.from(tarefaService.atualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @Positive Long id) {
        tarefaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
