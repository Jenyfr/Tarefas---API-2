package com.exercicio.tarefas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.exercicio.tarefas.dto.TarefaRequest;
import com.exercicio.tarefas.exception.TarefaNaoEncontradaException;
import com.exercicio.tarefas.model.Prioridade;
import com.exercicio.tarefas.model.Tarefa;
import com.exercicio.tarefas.repository.TarefaRepository;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new TarefaNaoEncontradaException(id));
    }

    public Tarefa criar(TarefaRequest request) {
        LocalDateTime agora = LocalDateTime.now();

        Tarefa tarefa = new Tarefa(
                null,
                limparTexto(request.getTitulo()),
                limparTexto(request.getDescricao()),
                normalizarConcluida(request.getConcluida()),
                normalizarPrioridade(request.getPrioridade()),
                agora,
                agora);

        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizar(Long id, TarefaRequest request) {
        Tarefa tarefaExistente = buscarPorId(id);

        Tarefa tarefaAtualizada = new Tarefa(
                id,
                limparTexto(request.getTitulo()),
                limparTexto(request.getDescricao()),
                normalizarConcluida(request.getConcluida()),
                normalizarPrioridade(request.getPrioridade()),
                tarefaExistente.getCriadaEm(),
                LocalDateTime.now());

        return tarefaRepository.save(tarefaAtualizada);
    }

    public void excluir(Long id) {
        buscarPorId(id);
        tarefaRepository.deleteById(id);
    }

    private String limparTexto(String texto) {
        return texto == null ? null : texto.trim();
    }

    private Boolean normalizarConcluida(Boolean concluida) {
        return concluida != null ? concluida : false;
    }

    private Prioridade normalizarPrioridade(Prioridade prioridade) {
        return prioridade != null ? prioridade : Prioridade.MEDIA;
    }
}
