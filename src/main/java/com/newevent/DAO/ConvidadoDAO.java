package com.newevent.DAO;

import com.newevent.Model.Convidado;
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
public class ConvidadoDAO extends ConexaoDAO implements BaseDAO<Convidado> {

    @Override
    public boolean Cadastrar(Convidado convidado) {
        Connection con;
        boolean ok = true;
        String query = "INSERT INTO Convidado(Nome, Telefone, QtdPessoas, IdEvento) VALUES (?,?,?,?)";
        try {
            con = getConexao();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, convidado.getNome());
            ps.setString(2, convidado.getTelefone());
            ps.setInt(3, convidado.getQtdPessoas());
            ps.setInt(4, convidado.getIdEvento());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            ok = false;
        }
        return ok;
    }

    public List<Convidado> ListarTodosPorEvento(int idEvento) {
        List<Convidado> listaConvidados = new ArrayList();
        String query = "SELECT * FROM Convidado WHERE IdEvento = ?";
        Connection con;
        try {
            con = getConexao();
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idEvento);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int idConvidado = rs.getInt("IdConvidado");
                String nome = rs.getString("Nome");
                String telefone = rs.getString("Telefone");
                int qtdPessoas = rs.getInt("QtdPessoas");
                int evento = rs.getInt("IdEvento");
                
                Convidado convidado = new Convidado(idConvidado, nome, telefone, qtdPessoas, evento);
                
                listaConvidados.add(convidado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaConvidados;
    }

    @Override
    public Convidado ListarPorId(int id) {
        String query = "SELECT * FROM Convidado WHERE IdConvidado = ?";
        Connection con;
        Convidado convidado = null;
        try {
            con = getConexao();
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                int idConvidado = rs.getInt("IdConvidado");
                String nome = rs.getString("Nome");
                String telefone = rs.getString("Telefone");
                int qtdPessoas = rs.getInt("QtdPessoas");
                int evento = rs.getInt("IdEvento");
                
                convidado = new Convidado(idConvidado, nome, telefone, qtdPessoas, evento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return convidado;
    }

    @Override
    public boolean Deletar(int id) {
        boolean ok = true;
        String query = "DELETE FROM Convidado WHERE IdConvidado = ?";
        Connection con;
        try {
            con = getConexao();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex );
            ok = false;
        }
        return ok;
    }

    @Override
    public boolean Atualizar(Convidado convidado) {
        boolean ok = true;
        String query = "UPDATE Convidado SET Nome = ?, Telefone = ?, QtdPessoas = ? WHERE IdConvidado = ?";
        Connection con;
        try {
            con = getConexao();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, convidado.getNome());
            ps.setString(2, convidado.getTelefone());
            ps.setInt(3, convidado.getQtdPessoas());
            ps.setInt(4, convidado.getIdConvidado());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConvidadoDAO.class.getName()).log(Level.SEVERE, null, ex );
            ok = false;
        }
        return ok;
    }
    
}
