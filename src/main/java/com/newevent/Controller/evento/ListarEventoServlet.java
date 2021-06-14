package com.newevent.Controller.evento;

import com.google.gson.Gson;
import com.newevent.DAO.EventoDAO;
import com.newevent.Model.Evento;
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
public class ListarEventoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        EventoDAO dao = new EventoDAO();
        
        String json;
        if(id == 0) {
            List<Evento> listaEvento = dao.ListarTodos();
            json = new Gson().toJson(listaEvento);
        } else {
            Evento evento = dao.ListarPorId(id);
            json = new Gson().toJson(evento);
        }
        
        response.setContentType("aplication/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        
    }
    
}
