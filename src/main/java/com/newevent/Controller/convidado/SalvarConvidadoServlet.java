package com.newevent.Controller.convidado;

import com.google.gson.Gson;
import com.newevent.DAO.ConvidadoDAO;
import com.newevent.Model.Convidado;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SalvarConvidadoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int idEvento = Integer.parseInt(request.getParameter("idEvento"));
        int idConvidado = Integer.parseInt(request.getParameter("idConvidado"));
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        int qtdPessoas = Integer.parseInt(request.getParameter("qtdPessoa"));
        
        Convidado convidado = new Convidado(idConvidado, nome, telefone, qtdPessoas, idEvento);
        
        ConvidadoDAO dao = new ConvidadoDAO();
        boolean ok;
        
        if(idConvidado == 0)
            ok = dao.Cadastrar(convidado);
        else
            ok = dao.Atualizar(convidado);
        
        String json = new Gson().toJson(ok);

        response.setContentType("aplication/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        
    }
    
}
