package com.exercicio.tarefas.model;

import java.time.LocalDateTime;

public class Tarefa {

    private Long id;
    private String titulo;
    private String descricao;
    private Boolean concluida;
    private Prioridade prioridade;
    private LocalDateTime criadaEm;
    private LocalDateTime atualizadaEm;

    public Tarefa() {
    }

    public Tarefa(Long id, String titulo, String descricao, Boolean concluida, Prioridade prioridade,
            LocalDateTime criadaEm, LocalDateTime atualizadaEm) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = concluida;
        this.prioridade = prioridade;
        this.criadaEm = criadaEm;
        this.atualizadaEm = atualizadaEm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public LocalDateTime getCriadaEm() {
        return criadaEm;
    }

    public void setCriadaEm(LocalDateTime criadaEm) {
        this.criadaEm = criadaEm;
    }

    public LocalDateTime getAtualizadaEm() {
        return atualizadaEm;
    }

    public void setAtualizadaEm(LocalDateTime atualizadaEm) {
        this.atualizadaEm = atualizadaEm;
    }
}
