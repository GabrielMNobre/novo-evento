package com.newevent.Controller.convidado;

import com.google.gson.Gson;
import com.newevent.DAO.ConvidadoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabriel
 */
public class ExcluirConvidadoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        ConvidadoDAO dao = new ConvidadoDAO();
        
        boolean ok = dao.Deletar(id);
        
        String json = new Gson().toJson(ok);
        
        response.setContentType("aplication/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        
    }
    
}
