package br.edu.ifpb.api;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.Clientes;
import br.edu.ifpb.domain.ListaDeClientes;
import br.edu.ifpb.domain.NovoCliente;
import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

//@Stateless
@Path("clientes") //localhost:8080/core/api/clientes
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class ResourcesDeClientes {
    @Inject
    private ListaDeClientes todosOsClientes;
    @Inject
    private NovoCliente novocliente;
    
    @Inject
    private Clientes clientes;

    @GET
    public List<Cliente> todos(){
        return this.todosOsClientes.todosOsClientes();
    }
    
    @POST //criar novo recurso (cliente)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response criarCliente(Cliente cliente){ //xml, json
        Cliente resposta = novocliente.novo(cliente);
//      return Response.ok() //200
        URI uri = URI.create("localhost:8080/core/api/clientes/"+resposta.getId());
        return Response.created(uri) //201
          .entity(resposta) 
          .build();
    }
    
    // /clientes/1 -> recuperar o cliente com id igual 1
    @GET
    @Path("{id}") //     .../clientes/{id}
    public Response localizar(@PathParam("id") int id){
        Cliente cliente = clientes.localizar(id);
        return Response.ok()
            .entity(cliente)
            .build();
    }
        
        
//    @GET
    //xml, json, text/html,... MIME
//    @Produces(MediaType.TEXT_PLAIN)
//    public String ola(){
//     return "ola";  
//    }
    
}
