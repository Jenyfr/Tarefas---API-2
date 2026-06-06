package com.exercicio.tarefas.repository;

import java.util.List;
import java.util.Optional;

import com.exercicio.tarefas.model.Tarefa;

public interface TarefaRepository {

    List<Tarefa> findAll();

    Optional<Tarefa> findById(Long id);

    boolean existsById(Long id);

    Tarefa save(Tarefa tarefa);

    void deleteById(Long id);
}
