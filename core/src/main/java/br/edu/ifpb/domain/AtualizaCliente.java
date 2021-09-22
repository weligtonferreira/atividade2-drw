package br.edu.ifpb.domain;

import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AtualizaCliente {

    @EJB
    private Clientes clientes;
    
    public Cliente atualiza(Cliente cliente){
        // validações...
        Objects.requireNonNull(cliente,"O cliente não pode ser nulo");
        return clientes.atualiza(cliente);
    }
        
}
