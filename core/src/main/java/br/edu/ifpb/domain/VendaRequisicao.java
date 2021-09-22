/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ricardo
 */
@XmlRootElement
public class VendaRequisicao {
    private int id;
    private int idCliente;
    private List<VendaProdutoRequisicao> produtos;
    
    public VendaRequisicao() {
        
    }
    
    public VendaRequisicao(int idCliente, List<VendaProdutoRequisicao> produtos) {
        Random randomizador = new Random();
        this.id = randomizador.nextInt(100);
        this.idCliente = idCliente;
        this.produtos = produtos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public List<VendaProdutoRequisicao> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<VendaProdutoRequisicao> produtos) {
        this.produtos = produtos;
    }
    
    public Venda toVenda(int id, List<Cliente> clientes, List<Produto> produtos) {
        Venda venda = new Venda();
        Cliente cliente = clientes
                .stream()
                .filter(c -> c.getId() == this.getIdCliente())
                .findFirst()
                .orElse(new Cliente());
        if (!cliente.equals(new Cliente())) {
            List<ProdutoVenda> produtosVenda = new ArrayList<>();
            this.produtos.stream().forEachOrdered(vendaProdutoRequisicao -> {
                        produtosVenda.add(vendaProdutoRequisicao.toProdutoVenda(vendaProdutoRequisicao.getIdProduto(), produtos));
            });
            if (produtosVenda.size() > 0) {
                venda = new Venda(id, cliente, produtosVenda);
            }
        }
        return venda;
    }
    
    public Venda toVenda(List<Cliente> clientes, List<Produto> produtos) {
        Venda venda = new Venda();
        Cliente cliente = clientes
                .stream()
                .filter(c -> c.getId() == this.getIdCliente())
                .findFirst()
                .orElse(new Cliente());
        if (!cliente.equals(new Cliente())) {
            List<ProdutoVenda> produtosVenda = new ArrayList<>();
            this.produtos.stream().forEachOrdered(vendaProdutoRequisicao -> {
                        produtosVenda.add(vendaProdutoRequisicao.toProdutoVenda(vendaProdutoRequisicao.getIdProduto(), produtos));
            });
            if (produtosVenda.size() > 0) {
                venda = new Venda(cliente, produtosVenda);
            }
        }
        return venda;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VendaRequisicao other = (VendaRequisicao) obj;
        return this.id == other.id;
    }
}
