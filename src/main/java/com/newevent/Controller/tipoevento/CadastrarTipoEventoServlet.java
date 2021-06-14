package com.newevent.Controller.tipoevento;

import com.google.gson.Gson;
import com.newevent.DAO.TipoEventoDAO;
import com.newevent.Model.TipoEvento;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabriel
 */
public class CadastrarTipoEventoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String tipoEvento = request.getParameter("tipoEvento");
        String ok = "";
        TipoEvento evento = new TipoEvento(1, tipoEvento);
        TipoEvento cadastrado = TipoEventoDAO.BuscarPorNome(evento);
        
        if(cadastrado == null) {
            boolean add = TipoEventoDAO.Adicionar(evento);
            ok = String.valueOf(add);
        } else
            ok = "invalid";
        
        String json = new Gson().toJson(ok);

        response.setContentType("aplication/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        
    }

}
