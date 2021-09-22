package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.ProdutoVenda;
import br.edu.ifpb.domain.Venda;
import br.edu.ifpb.domain.Vendas;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Named
@SessionScoped
public class ControladorDeVendas implements Serializable {
    @Inject
    private Vendas vendas;
    
    private Venda venda = new Venda();
    
    public List<Venda> listar() {
        return this.vendas.todos();
    }
    
    public Venda limparVenda() {
        return new Venda();
    }
    
    public String mostrarVendas() {
        this.venda = this.limparVenda();
        return "/faces/venda/index.xhtml?faces-redirect=true";
    }
    
    public String mostrarVenda(Venda venda) {
        this.venda = venda;
        return "/faces/venda/show.xhtml?faces-redirect=true";
    }
    
    public String removerVenda(int id) {
        this.vendas.exclui(id);
        return null;
    }
    
    public BigDecimal mostrarValorVenda(Venda venda) {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ProdutoVenda produtoVenda : venda.getProdutos()) {
            valorTotal = valorTotal.add(produtoVenda.getProduto().getValor().multiply(new BigDecimal(produtoVenda.getQuantidade())));
        }
        return valorTotal;
    }

    public Vendas getVendas() {
        return vendas;
    }

    public void setVendas(Vendas vendas) {
        this.vendas = vendas;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    
}
