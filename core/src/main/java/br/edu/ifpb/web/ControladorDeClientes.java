package br.edu.ifpb.web;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.ListaDeClientes;
import br.edu.ifpb.domain.NovoCliente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ricardo Job
 */
@WebServlet(name = "ControladorDeClientes",urlPatterns = {"/clientes"})
public class ControladorDeClientes extends HttpServlet {
   
    @Inject
    private NovoCliente criarCliente;
    @Inject
    private ListaDeClientes listarClientes;

    // Listar todos os clientes
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorDeClientes</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> </h1>");
            listarClientes(out);
            out.println("</body>");
            out.println("</html>");
        }
    }

// Criar um novo Cliente
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException {
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        Cliente cliente = new Cliente(cpf,nome);
        this.criarCliente.novo(cliente);
        response.sendRedirect("clientes");
    }

    private void listarClientes(final PrintWriter out) {
        this.listarClientes
            .todosOsClientes()
            .forEach(c-> 
                out.println("<p>" + c.getNome() + "</p>")
            );
    }
    
    // servidor web -> atender as requisições http (tomcat, jetty..)
    // servidor de aplicação -> gerenciar lógica de negócios (payara, glassfish, wildfly..)
}
