package com.exercicio.tarefas.repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.exercicio.tarefas.model.Tarefa;

@Repository
public class InMemoryTarefaRepository implements TarefaRepository {

    private final Map<Long, Tarefa> tarefas = new ConcurrentHashMap<>();
    private final AtomicLong proximoId = new AtomicLong(1);

    @Override
    public List<Tarefa> findAll() {
        return tarefas.values().stream()
                .sorted(Comparator.comparing(Tarefa::getId))
                .toList();
    }

    @Override
    public Optional<Tarefa> findById(Long id) {
        return Optional.ofNullable(tarefas.get(id));
    }

    @Override
    public boolean existsById(Long id) {
        return tarefas.containsKey(id);
    }

    @Override
    public Tarefa save(Tarefa tarefa) {
        if (tarefa.getId() == null) {
            tarefa.setId(proximoId.getAndIncrement());
        }

        tarefas.put(tarefa.getId(), tarefa);
        return tarefa;
    }

    @Override
    public void deleteById(Long id) {
        tarefas.remove(id);
    }
}
