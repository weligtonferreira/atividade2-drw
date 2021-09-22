package br.edu.ifpb.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Venda {

    private int id;
    private Cliente cliente;
    
    private List<ProdutoVenda> produtos = new ArrayList<>();

    public Venda() {
    }
    
    public Venda(int id) {
        this.id = id;
    }
    
    public Venda(Cliente cliente, List<ProdutoVenda> produtos) {
        this(-1, cliente, produtos);
    }        

    public Venda(int id,Cliente cliente, List<ProdutoVenda> produtos) {
        Random randomizador = new Random();
        this.id = randomizador.nextInt(1000);
        this.cliente = cliente;
        this.produtos = produtos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ProdutoVenda> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoVenda> produtos) {
        this.produtos = produtos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.cliente);
        hash = 79 * hash + Objects.hashCode(this.produtos);
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
        final Venda other = (Venda) obj;
        return this.id == other.id;
    }

}
