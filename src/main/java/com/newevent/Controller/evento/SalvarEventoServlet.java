package com.newevent.Controller.evento;

import com.google.gson.Gson;
import com.newevent.DAO.EventoDAO;
import com.newevent.Model.Evento;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabriel
 */
public class SalvarEventoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        int id = Integer.parseInt(request.getParameter("idEvento"));
        String nome = request.getParameter("nome");
        int tipo = Integer.parseInt(request.getParameter("tipo"));
        String data = request.getParameter("dataEvento");
        int qtdParticipantes = Integer.parseInt(request.getParameter("qtdParticipantes"));
        EventoDAO dao = new EventoDAO();
        
        boolean ok;
        
        Evento evento = new Evento(id, nome, tipo, data, qtdParticipantes);
        
        if(id == 0) 
            ok = dao.Cadastrar(evento);
        else 
            ok = dao.Atualizar(evento);
        
        
        String json = new Gson().toJson(ok);

        response.setContentType("aplication/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        
    }
}
