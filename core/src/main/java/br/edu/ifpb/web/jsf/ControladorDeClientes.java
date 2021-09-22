package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.Clientes;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ControladorDeClientes implements Serializable {
    @Inject
    private Clientes clientes;
    
    private Cliente cliente = new Cliente("", "");
    
    public List<Cliente> listar() {
        return this.clientes.todos();
    }
    
    public Cliente limparCliente() {
        return new Cliente("", "");
    }
    
    public String adicionarCliente() {
        Cliente clienteEncontrado = this.clientes
                .todos()
                .stream()
                .filter(c -> c.getCpf().equals(this.cliente.getCpf()))
                .findFirst()
                .orElse(new Cliente());
        if (clienteEncontrado.equals(new Cliente())) {
            this.clientes.novo(this.cliente);
        }
        this.cliente = this.limparCliente();
        return "/faces/cliente/index.xhtml?faces-redirect=true";
    }
    
    
    public String mostrarClientes() {
        this.cliente = this.limparCliente();
        return "/faces/cliente/index.xhtml?faces-redirect=true";
    }
    
    public String mostrarCliente(Cliente cliente) {
        this.cliente = cliente;
        return "/faces/cliente/show.xhtml?faces-redirect=true";
    }
    
    public String redirecionaAtualizarCliente(Cliente cliente) {
        this.cliente = cliente;
        return "/faces/cliente/edit.xhtml?faces-redirect=true";
    }
    
    public String atualizarCliente() {
        this.clientes.atualiza(this.cliente);
        this.limparCliente();
        return this.mostrarClientes();
    }
    
    public String removerCliente(int id) {
        this.clientes.exclui(id);
        this.limparCliente();
        return null;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
