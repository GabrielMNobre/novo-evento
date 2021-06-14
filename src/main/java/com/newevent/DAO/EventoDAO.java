package com.newevent.DAO;

import com.newevent.Model.Evento;
import java.sql.Connection;
import java.sql.Date;
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
public class EventoDAO extends ConexaoDAO implements BaseDAO<Evento>  {

    @Override
    public boolean Cadastrar(Evento evento) {
        Connection con;
        boolean ok = true;
        String query = "INSERT INTO Evento(Nome, DataEvento, QtdParticipantes, IdTipoEvento) VALUES (?,?,?,?)";
        try {
            con = getConexao();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, evento.getNome());
            ps.setDate(2, Date.valueOf(evento.getData()));
            ps.setInt(3, evento.getQtdParticipantes());
            ps.setInt(4, evento.getIdTipo());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
            ok = false;
        }
        return ok;
    }

    public List<Evento> ListarTodos() {
        List<Evento> listaEventos = new ArrayList();
        String query = "SELECT e.IdEvento, e.Nome, e.DataEvento, t.TipoEvento, e.QtdParticipantes FROM Evento e " +
                       "INNER JOIN TipoEvento t ON t.IdTipoEvento = e.IdTipoEvento";
        Connection con;
        try {
            con = getConexao();
            
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idEvento= rs.getInt("IdEvento");
                String nome= rs.getString("Nome");
                String data= String.valueOf(rs.getDate("DataEvento"));
                String tipoEvento= rs.getString("TipoEvento");
                int qtdParticipantes = rs.getInt("QtdParticipantes");
                
                Evento evento = new Evento(idEvento, nome, tipoEvento, data, qtdParticipantes);
                
                listaEventos.add(evento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaEventos;
    }

    @Override
    public Evento ListarPorId(int id) {
        Evento evento = null;
        String query = "SELECT * FROM Evento WHERE IdEvento = ?";
        Connection con;
        try {
            con = getConexao();
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idEvento= rs.getInt("IdEvento");
                String nome= rs.getString("Nome");
                String data= String.valueOf(rs.getDate("DataEvento"));
                int tipoEvento= rs.getInt("IdTipoEvento");
                int qtdParticipantes = rs.getInt("QtdParticipantes");
                
                evento = new Evento(idEvento, nome, tipoEvento, data, qtdParticipantes);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return evento;
    }

    @Override
    public boolean Deletar(int id) {
        boolean ok = true;
        String query = "DELETE FROM Evento WHERE IdEvento = ?";
        String query2 = "DELETE FROM Convidado WHERE IdEvento = ?";
        Connection con;
        try {
            con = getConexao();
            PreparedStatement p = con.prepareStatement(query2);
            p.setInt(1, id);
            p.executeUpdate();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex );
            ok = false;
        }
        return ok;
    }

    @Override
    public boolean Atualizar(Evento evento) {
        Connection con;
        boolean ok = true;
        String query = "UPDATE Evento SET Nome = ?, DataEvento = ?, QtdParticipantes = ?, IdTipoEvento = ? WHERE IdEvento = ?";
        try {
            con = getConexao();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, evento.getNome());
            ps.setDate(2, Date.valueOf(evento.getData()));
            ps.setInt(3, evento.getQtdParticipantes());
            ps.setInt(4, evento.getIdTipo());
            ps.setInt(5, evento.getIdEvento());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
            ok = false;
        }
        return ok;
    }
    
}
