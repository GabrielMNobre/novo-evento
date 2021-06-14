package com.newevent.Model;

/**
 *
 * @author gabriel
 */
public class Evento {
    
    private int idEvento;
    private String nome;
    private int idTipo;
    private String tipo;
    private String data;
    private int qtdParticipantes;

    public Evento(int id, String nome, int idTipo, String data, int qtdParticipantes) {
        this.idEvento = id;
        this.nome = nome;
        this.idTipo = idTipo;
        this.data = data;
        this.qtdParticipantes = qtdParticipantes;
    }
    
    public Evento(int id, String nome, String tipo, String data, int qtdParticipantes) {
        this.idEvento = id;
        this.nome = nome;
        this.tipo = tipo;
        this.data = data;
        this.qtdParticipantes = qtdParticipantes;
    }
    
    public int getIdEvento() {
        return idEvento;
    }

    public String getNome() {
        return nome;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public String getData() {
        return data;
    }

    public int getQtdParticipantes() {
        return qtdParticipantes;
    }
    
}
