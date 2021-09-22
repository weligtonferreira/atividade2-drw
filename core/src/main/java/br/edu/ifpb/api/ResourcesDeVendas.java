package br.edu.ifpb.api;

import br.edu.ifpb.domain.AtualizaVenda;
import br.edu.ifpb.domain.Clientes;
import br.edu.ifpb.domain.ListaDeVendas;
import br.edu.ifpb.domain.NovaVenda;
import br.edu.ifpb.domain.Produtos;
import br.edu.ifpb.domain.Venda;
import br.edu.ifpb.domain.VendaRequisicao;
import br.edu.ifpb.domain.Vendas;
import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

//@Stateless
@Path("vendas") //localhost:8080/core/api/clientes
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
public class ResourcesDeVendas {
    @Inject
    private ListaDeVendas todasAsVendas;
    @Inject
    private NovaVenda novaVenda;
    @Inject
    private AtualizaVenda atualizaVenda;
    
    @Inject
    private Vendas vendas;
    
    @Inject
    private Clientes clientes;
    
    @Inject
    private Produtos produtos;

    @GET
    public List<Venda> todos(){
        return this.todasAsVendas.todasAsVendas();
    }
    
    @POST //criar novo recurso (cliente)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public Response criarVenda(VendaRequisicao vendaRequisicao){ //xml, json
        Venda venda = vendaRequisicao.toVenda(clientes.todos(), produtos.todos());
        Venda resposta = novaVenda.nova(venda);
//      return Response.ok() //200
        URI uri = URI.create("localhost:8080/core/api/vendas/"+resposta.getId());
        return Response.created(uri) //201
          .entity(resposta) 
          .build();
    }
    
    // /clientes/1 -> recuperar o cliente com id igual 1
    @GET
    @Path("{id}") //     .../clientes/{id}
    public Response localizar(@PathParam("id") int id){
        Venda venda = vendas.localizar(id);
        return Response.ok()
            .entity(venda)
            .build();
    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public Response atualizarVenda(VendaRequisicao vendaRequisicao) {
        Venda venda = vendaRequisicao.toVenda(vendaRequisicao.getId(), clientes.todos(), produtos.todos());
        Venda resposta = atualizaVenda.atualiza(venda);
        return Response.ok()
                .entity(resposta)
                .build();
    }
    
    @DELETE
    @Path("{id}")
    public Response excluiVenda(@PathParam("id") int id) {
        vendas.exclui(id);
        return Response.ok()
                .build();
    }
    
}
