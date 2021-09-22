package br.edu.ifpb.domain;

import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class NovoCliente {

    @EJB
    private Clientes clientes;
    
    public Cliente novo(Cliente cliente){
        // validações...
        Objects.requireNonNull(cliente,"O cliente não pode ser nulo");
        return clientes.novo(new Cliente(cliente.getCpf(), cliente.getNome()));
    }
        
}
