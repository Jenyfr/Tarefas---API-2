package com.exercicio.tarefas;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCriarListarAtualizarEExcluirTarefa() throws Exception {
        String tarefa = """
                {
                  "titulo": "Estudar Spring Boot",
                  "descricao": "Implementar metodos HTTP",
                  "concluida": false,
                  "prioridade": "ALTA"
                }
                """;

        mockMvc.perform(post("/api/tarefas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tarefa))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Estudar Spring Boot"))
                .andExpect(jsonPath("$.prioridade").value("ALTA"))
                .andExpect(jsonPath("$.criadaEm").exists());

        mockMvc.perform(get("/api/tarefas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        String tarefaAtualizada = """
                {
                  "titulo": "Estudar API REST",
                  "descricao": "Testar o metodo PUT",
                  "concluida": true,
                  "prioridade": "MEDIA"
                }
                """;

        mockMvc.perform(put("/api/tarefas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tarefaAtualizada))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Estudar API REST"))
                .andExpect(jsonPath("$.concluida").value(true))
                .andExpect(jsonPath("$.prioridade").value("MEDIA"));

        mockMvc.perform(delete("/api/tarefas/1"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/tarefas/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRetornarErroQuandoTituloEstiverVazio() throws Exception {
        String tarefaInvalida = """
                {
                  "titulo": "",
                  "descricao": "Sem titulo",
                  "concluida": false
                }
                """;

        mockMvc.perform(post("/api/tarefas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tarefaInvalida))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.path").value("/api/tarefas"))
                .andExpect(jsonPath("$.campos.titulo").exists());
    }

    @Test
    void deveRetornarErroQuandoIdForInvalido() throws Exception {
        mockMvc.perform(get("/api/tarefas/0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.path").value("/api/tarefas/0"));
    }

    @Test
    void deveRetornarErroQuandoPrioridadeForInvalida() throws Exception {
        String tarefaComPrioridadeInvalida = """
                {
                  "titulo": "Estudar validacao",
                  "descricao": "Prioridade invalida",
                  "concluida": false,
                  "prioridade": "URGENTE"
                }
                """;

        mockMvc.perform(post("/api/tarefas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tarefaComPrioridadeInvalida))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem").value("O corpo da requisicao esta invalido ou possui valores nao aceitos."));
    }
}
