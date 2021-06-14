package com.newevent.DAO;

import com.newevent.Model.TipoEvento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class TipoEventoDAO extends ConexaoDAO {
    
    public static List<TipoEvento> ListarTodos() {
        List<TipoEvento> listaTipos = new ArrayList();
        String query = "SELECT * FROM TipoEvento";
        Connection con;
        try {
            con = getConexao();
            System.out.println("Aqui -> " + con);
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idTipoEvento= rs.getInt("IdTipoEvento");
                String tipoEvento= rs.getString("TipoEvento");
                TipoEvento tipo = new TipoEvento(idTipoEvento, tipoEvento);
                listaTipos.add(tipo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaTipos;
    }
    
    public static TipoEvento BuscarPorNome(TipoEvento tipo) {
        TipoEvento result = null;
        String query = "SELECT * FROM TipoEvento WHERE TipoEvento = ?";
        Connection con;
        try {
            con = getConexao();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, tipo.getTipoEvento());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idTipoEvento= rs.getInt("IdTipoEvento");
                String tipoEvento= rs.getString("TipoEvento");
                result = new TipoEvento(idTipoEvento, tipoEvento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    public static boolean Adicionar(TipoEvento tipo) {
        Connection con;
        boolean ok = true;
        String query = "INSERT INTO TipoEvento(TipoEvento) VALUES (?)";
        try {
            con = getConexao();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, tipo.getTipoEvento());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
            ok = false;
        }
        return ok;
    }
    
    public static boolean Deletar(int id) {
        boolean ok = true;
        String query = "DELETE FROM TipoEvento WHERE IdTipoEvento = ?";
        Connection con;
        try {
            con = getConexao();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex );
            ok = false;
        }
        return ok;
    }
    
}
