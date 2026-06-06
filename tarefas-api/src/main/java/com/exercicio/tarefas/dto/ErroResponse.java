package com.exercicio.tarefas.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ErroResponse {

    private LocalDateTime timestamp;
    private int status;
    private String erro;
    private String mensagem;
    private String path;
    private Map<String, String> campos;

    public ErroResponse(int status, String erro, String mensagem, String path, Map<String, String> campos) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.path = path;
        this.campos = campos;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getErro() {
        return erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getCampos() {
        return campos;
    }
}
