package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.Produtos;
import br.edu.ifpb.domain.compra.Carrinho;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.CDI;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Named
@SessionScoped
public class ControladorDeProdutos implements Serializable {
    @Inject
    private Produtos produtos;
    
    private Produto produto = new Produto("", new BigDecimal(0));
    
    public List<Produto> listar() {
        return this.produtos.todos();
    }
    
    public Produto limparProduto() {
        return new Produto("", new BigDecimal(0));
    }
    
    public String adicionarProduto() {
        Produto produtoEncontrado = this.produtos
                .todos()
                .stream()
                .filter(p -> p.getDescricao().equals(this.produto.getDescricao()))
                .findFirst()
                .orElse(new Produto());
        if (produtoEncontrado.equals(new Produto())) {
            this.produtos.novo(this.produto);
        }
        this.produto = this.limparProduto();
        return "/faces/index.xhtml?faces-redirect=true";
    }
    
    
    public String mostrarProdutos() {
        this.produto = this.limparProduto();
        return "/faces/index.xhtml?faces-redirect=true";
    }
    
    public String mostrarProduto(Produto produto) {
        this.produto = produto;
        return "/faces/produto/show.xhtml?faces-redirect=true";
    }
    
    public String redirecionaAtualizarProduto(Produto produto) {
        this.produto = produto;
        return "/faces/produto/edit.xhtml?faces-redirect=true";
    }
    
    public String atualizarProduto() {
        this.produtos.atualiza(this.produto);
        this.produto = this.limparProduto();
        return "/faces/index.xhtml?faces-redirect=true";
    }
    
    public String removerProduto(int id) {
        this.produtos.exclui(id);
        return null;
    }
    
    private void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSession(true);
        session.invalidate();
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
