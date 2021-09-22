/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ricardo
 */
@XmlRootElement
public class ProdutoVenda {
    private Produto produto;
    private int quantidade;
    
    public ProdutoVenda() {
    }
    
    public ProdutoVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.quantidade;
        hash = 79 * hash + Objects.hashCode(this.produto);
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
        final ProdutoVenda other = (ProdutoVenda) obj;
        if (this.quantidade != other.quantidade) {
            return false;
        }
        return Objects.equals(this.produto,other.produto);
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    
}
