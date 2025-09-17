package com.example.demo;

import java.util.List;

public class ResultadoOrdenacao {
    private long comparacoes;
    private long movimentacoes;
    private long tempoExecucaoMs;
    private List<Integer> vetorOriginal; 
    private List<Integer> vetorOrdenado;

    public ResultadoOrdenacao(long comparacoes, long movimentacoes, long tempoExecucaoMs, List<Integer> vetorOriginal, List<Integer> vetorOrdenado) {
        this.comparacoes = comparacoes;
        this.movimentacoes = movimentacoes;
        this.tempoExecucaoMs = tempoExecucaoMs;
        this.vetorOriginal = vetorOriginal;
        this.vetorOrdenado = vetorOrdenado;
    }

    
    public List<Integer> getVetorOriginal() {
        return vetorOriginal;
    }

    public List<Integer> getVetorOrdenado() {
        return vetorOrdenado;
    }

    public long getComparacoes() {
        return comparacoes;
    }

    public long getMovimentacoes() {
        return movimentacoes;
    }

    public long getTempoExecucaoMs() {
        return tempoExecucaoMs;
    }

    @Override
    public String toString() {
        return "Comparações: " + comparacoes +
               ", Movimentações: " + movimentacoes +
               ", Tempo: " + tempoExecucaoMs + "ms";
    }
}