package com.newevent.Controller.convidado;

import com.google.gson.Gson;
import com.newevent.DAO.ConvidadoDAO;
import com.newevent.Model.Convidado;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabriel
 */
public class ListarConvidadoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int idEvento = Integer.parseInt(request.getParameter("idEvento"));
        int idConvidado = Integer.parseInt(request.getParameter("idConvidado"));
        ConvidadoDAO dao = new ConvidadoDAO();
        
        String json;
        if(idEvento != 0) {
            List<Convidado> listaConvidados = dao.ListarTodosPorEvento(idEvento);
            json = new Gson().toJson(listaConvidados);
        } else {
            Convidado convidado = dao.ListarPorId(idConvidado);
            json = new Gson().toJson(convidado);
        }
        
        response.setContentType("aplication/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        
    }
    
}
