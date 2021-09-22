package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.Clientes;
import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.ProdutoVenda;
import br.edu.ifpb.domain.Venda;
import br.edu.ifpb.domain.Vendas;
import br.edu.ifpb.domain.compra.Carrinho;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.CDI;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ControladorDeCarrinho implements Serializable {

    @Inject
    private Carrinho carrinho; //session-key: 90ac0300141f-ffffffffe7de3f3d-0
    
    @Inject
    private Vendas vendas;
    
    @Inject
    private Clientes clientes;
    
    public String novo(Produto produto){
        this.carrinho.adicionar(produto);
        return null;
    }
    
    public String excluir(ProdutoVenda produto){
        this.carrinho.excluir(produto);
        return null;
    }
    
    public String finalizarCompra (int id) {
        if (this.carrinho.produtos().isEmpty()) {
            return null;
        }
        Cliente cliente = this.clientes
                .todos()
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(new Cliente());
        if (cliente.equals(new Cliente())) {
            return null;
        }
        Venda venda = new Venda(cliente, this.carrinho.produtos());
        this.vendas.nova(venda);
        this.limparCarrinho();
        return "/faces/venda/index.xhtml?faces-redirect=true";
    }
    
    public void limparCarrinho(){
        this.carrinho.finalizar();
        novaInstanciaDoCarrinho();
//        logout();
//        return "index.xhtml?faces-redirect=true";
    }

    private void novaInstanciaDoCarrinho() {
        this.carrinho = CDI.current()
                .select(Carrinho.class)
                .get();
    }

    private void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSession(true);
        session.invalidate();
    }

    public List<ProdutoVenda> listar(){
        return this.carrinho.produtos();
    }
}
