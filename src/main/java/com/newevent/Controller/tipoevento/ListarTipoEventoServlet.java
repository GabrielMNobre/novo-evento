package com.newevent.Controller.tipoevento;

import com.newevent.DAO.TipoEventoDAO;
import com.newevent.Model.TipoEvento;
import java.io.IOException;
import com.google.gson.Gson;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabriel
 */
public class ListarTipoEventoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<TipoEvento> listaTipoEvento = TipoEventoDAO.ListarTodos();
        
        String json = new Gson().toJson(listaTipoEvento);
        
        response.setContentType("aplication/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        
    }

}
