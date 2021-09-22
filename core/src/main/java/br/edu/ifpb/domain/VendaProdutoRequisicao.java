/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ricardo
 */
@XmlRootElement
public class VendaProdutoRequisicao {
    private int idProduto;
    private int quantidadeProduto;
    
    public VendaProdutoRequisicao() {
        
    }
    
    public VendaProdutoRequisicao(int idProduto, int quantidade) {
        this.idProduto = idProduto;
        this.quantidadeProduto = quantidade;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }
    
    public ProdutoVenda toProdutoVenda(int id, List<Produto> produtos) {
        ProdutoVenda produtoVenda = new ProdutoVenda();
        for (Produto produto : produtos) {
            if (produto.getId() == this.getIdProduto()) {
                produtoVenda = new ProdutoVenda(produto, this.quantidadeProduto);
                break;
            }
        }
        return produtoVenda;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.idProduto;
        hash = 79 * hash + this.quantidadeProduto;
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
        final VendaProdutoRequisicao other = (VendaProdutoRequisicao) obj;
        if (this.quantidadeProduto != other.quantidadeProduto) {
            return false;
        }
        return this.idProduto == other.idProduto;
    }
}
