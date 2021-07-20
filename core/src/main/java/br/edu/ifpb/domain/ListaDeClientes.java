package br.edu.ifpb.domain;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/07/2021, 08:57:52
 */
@Stateless
public class ListaDeClientes {

    @EJB
    private Clientes clientes;
    
    public List<Cliente> todosOsClientes(){
        //cache
        return this.clientes.todos();
    }
}
