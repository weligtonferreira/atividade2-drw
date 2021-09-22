/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private List<Integer> idProdutos;
    
    public VendaRequisicao() {
        
    }
    
    public VendaRequisicao(int idCliente, List<Integer> idProdutos) {
        Random randomizador = new Random();
        this.id = randomizador.nextInt(100);
        this.idCliente = idCliente;
        this.idProdutos = idProdutos;
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

    public List<Integer> getIdProdutos() {
        return idProdutos;
    }

    public void setIdProdutos(List<Integer> idProdutos) {
        this.idProdutos = idProdutos;
    }
    
    public Venda toVenda(int id, List<Cliente> clientes, List<Produto> produtos) {
        Venda venda = new Venda();
        Cliente cliente = clientes
                .stream()
                .filter(c -> c.getId() == this.getIdCliente())
                .findFirst()
                .orElse(new Cliente());
        if (!cliente.equals(new Cliente())) {
            List<Produto> produtosEncontrados = new ArrayList<>();
            this.idProdutos.stream().map(idProduto -> produtos
                    .stream()
                    .filter(p -> p.getId() == idProduto)
                    .findFirst()
                    .orElse(new Produto())).filter(produto -> (!produto.equals(new Produto()))).forEachOrdered(produto -> {
                        produtosEncontrados.add(produto);
            });
            if (produtosEncontrados.size() > 0) {
                venda = new Venda(id, cliente, produtosEncontrados);
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
            List<Produto> produtosEncontrados = new ArrayList<>();
            this.idProdutos.stream().map(idProduto -> produtos
                    .stream()
                    .filter(p -> p.getId() == idProduto)
                    .findFirst()
                    .orElse(new Produto())).filter(produto -> (!produto.equals(new Produto()))).forEachOrdered(produto -> {
                        produtosEncontrados.add(produto);
            });
            if (produtosEncontrados.size() > 0) {
                venda = new Venda(cliente, produtosEncontrados);
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
