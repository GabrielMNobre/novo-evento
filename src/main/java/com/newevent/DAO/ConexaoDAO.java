package com.newevent.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gabriel
 */
public abstract class ConexaoDAO {

    private static Connection conexao;
    private static final String DRIVER  = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String URL = "jdbc:derby://localhost:1527/Eventos";
    private static final String USER = "gabriel";
    private static final String PASS   = "root";

    public static Connection getConexao() throws SQLException {
        try{
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(URL,USER,PASS);
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Deu zebra\n"+"\nERRO\n"+ex);
        }
        return conexao;
    }
    
}
