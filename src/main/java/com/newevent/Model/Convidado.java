package com.newevent.Model;

/**
 *
 * @author gabriel
 */
public class Convidado {
    
    private int idConvidado;
    private String nome;
    private String telefone;
    private int qtdPessoas;
    private int idEvento;
    
    public Convidado(int idConvidado, String nome, String telefone, int qtd, int idEvento) {
        this.idConvidado = idConvidado;
        this.nome = nome;
        this.telefone = telefone;
        this.qtdPessoas = qtd;
        this.idEvento = idEvento;
    }

    public int getIdConvidado() {
        return idConvidado;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getQtdPessoas() {
        return qtdPessoas;
    }

    public int getIdEvento() {
        return idEvento;
    }
   
}
