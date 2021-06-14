package com.newevent.Model;

/**
 *
 * @author gabriel
 */
public class TipoEvento {
    
    private int idTipoEvento;
    private String tipoEvento;
    
    public TipoEvento(int id, String tipo) {
        this.idTipoEvento = id;
        this.tipoEvento = tipo;
    }

    public int getIdTipoEvento() {
        return idTipoEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }
    
}
