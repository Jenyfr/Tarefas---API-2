package com.exercicio.tarefas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.exercicio.tarefas.model.Prioridade;

public class TarefaRequest {

    @NotBlank(message = "O titulo e obrigatorio.")
    @Size(min = 3, max = 100, message = "O titulo deve ter entre 3 e 100 caracteres.")
    @Pattern(regexp = "^[^<>]*$", message = "O titulo nao pode conter os caracteres < ou >.")
    private String titulo;

    @Size(max = 255, message = "A descricao deve ter no maximo 255 caracteres.")
    @Pattern(regexp = "^[^<>]*$", message = "A descricao nao pode conter os caracteres < ou >.")
    private String descricao;

    private Boolean concluida;
    private Prioridade prioridade;

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
}
