package br.edu.ifpb.api;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.ListaDeClientes;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("clientes") //localhost:8080/core/api/clientes
public class ResourcesDeClientes {

    @Inject
    private ListaDeClientes clientes;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> todos(){
        return this.clientes.todosOsClientes();
    }
}
