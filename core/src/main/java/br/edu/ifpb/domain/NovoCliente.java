package br.edu.ifpb.domain;

import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class NovoCliente {

    @EJB
    private Clientes clientes;
    
    public void novo(Cliente cliente){
        // validações...
        Objects.requireNonNull(cliente,"O cliente não pode ser nulo");
        clientes.novo(cliente);
    }
        
}
