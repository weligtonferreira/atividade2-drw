package br.edu.ifpb.api;

import br.edu.ifpb.domain.AtualizaProduto;
import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.ListaDeProdutos;
import br.edu.ifpb.domain.NovoProduto;
import br.edu.ifpb.domain.ProdutoRequisicao;
import br.edu.ifpb.domain.Produtos;
import java.math.BigDecimal;
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
@Path("produtos") //localhost:8080/core/api/clientes
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class ResourcesDeProdutos {
    @Inject
    private ListaDeProdutos todosOsProdutos;
    @Inject
    private NovoProduto novoProduto;
    @Inject
    private AtualizaProduto atualizaProduto;
    
    @Inject
    private Produtos produtos;

    @GET
    public List<Produto> todos(){
        return this.todosOsProdutos.todosOsProdutos();
    }
    
    @POST //criar novo recurso (cliente)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response criarProduto(ProdutoRequisicao produto){ //xml, json
        Produto produtoConvertido = new Produto(produto.getDescricao(), new BigDecimal(produto.getValor()));
        Produto resposta = novoProduto.novo(produtoConvertido);
//      return Response.ok() //200
        URI uri = URI.create("localhost:8080/core/api/produtos/"+resposta.getId());
        return Response.created(uri) //201
          .entity(resposta) 
          .build();
    }
    
    // /clientes/1 -> recuperar o cliente com id igual 1
    @GET
    @Path("{id}") //     .../clientes/{id}
    public Response localizar(@PathParam("id") int id){
        Produto produto = produtos.localizar(id);
        return Response.ok()
            .entity(produto)
            .build();
    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response atualizarProduto(Produto produto) {
        Produto resposta = atualizaProduto.atualiza(produto);
        return Response.ok()
                .entity(resposta)
                .build();
    }
    
    @DELETE
    @Path("{id}")
    public Response excluiProduto(@PathParam("id") int id) {
        produtos.exclui(id);
        return Response.ok()
                .build();
    }
        
        
//    @GET
    //xml, json, text/html,... MIME
//    @Produces(MediaType.TEXT_PLAIN)
//    public String ola(){
//     return "ola";  
//    }
    
}
