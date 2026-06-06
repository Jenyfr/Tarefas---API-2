package com.exercicio.tarefas.dto;

import java.time.LocalDateTime;

import com.exercicio.tarefas.model.Prioridade;
import com.exercicio.tarefas.model.Tarefa;

public class TarefaResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private Boolean concluida;
    private Prioridade prioridade;
    private LocalDateTime criadaEm;
    private LocalDateTime atualizadaEm;

    public TarefaResponse(Long id, String titulo, String descricao, Boolean concluida, Prioridade prioridade,
            LocalDateTime criadaEm, LocalDateTime atualizadaEm) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = concluida;
        this.prioridade = prioridade;
        this.criadaEm = criadaEm;
        this.atualizadaEm = atualizadaEm;
    }

    public static TarefaResponse from(Tarefa tarefa) {
        return new TarefaResponse(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getConcluida(),
                tarefa.getPrioridade(),
                tarefa.getCriadaEm(),
                tarefa.getAtualizadaEm());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public LocalDateTime getCriadaEm() {
        return criadaEm;
    }

    public LocalDateTime getAtualizadaEm() {
        return atualizadaEm;
    }
}
