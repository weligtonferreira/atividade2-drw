package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.Produtos;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

//@Stateless
public class ProdutosEmJDBC implements Produtos {

    @Resource(lookup = "java:app/jdbc/pgadmin")
    private DataSource dataSource;

    @Override
    public Produto novo(Produto produto) {
        try {
            PreparedStatement statement = this.dataSource
                .getConnection() //2
                .prepareStatement(
                    "INSERT INTO produtos (descricao, valor) VALUES(?,?) RETURNING *; "
                );
            statement.setString(1,produto.getDescricao());
            statement.setBigDecimal(2,produto.getValor());
            ResultSet result = statement.executeQuery();
            if(result.next()) 
            return criarProduto(result);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
        return new Produto();
    }

    @Override
    public List<Produto> todos() {
        try {
            List<Produto> lista = new ArrayList<>();
            ResultSet result = this.dataSource
                .getConnection()
                .prepareStatement(
                    "SELECT * FROM produtos"
                ).executeQuery();
            while (result.next()) {
                lista.add(
                    criarProduto(result)
                );
            }
            return lista;
        } catch (SQLException ex) {
//            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
            return Collections.EMPTY_LIST;
        }
    }

    private Produto criarProduto(ResultSet result) throws SQLException {
        String descricao = result.getString("descricao");
        BigDecimal valor = result.getBigDecimal("valor");
        int id = result.getInt("id");
        return new Produto(id,descricao,valor);
    }

    @Override
    public Produto localizar(int id) {
        try {
            PreparedStatement statement = this.dataSource
                .getConnection()
                .prepareStatement(
                    "SELECT * FROM produtos WHERE id= ?"
                );
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            if(result.next()) 
            return criarProduto(result);
            
        } catch (SQLException ex) {}
        return new Produto();
    }

    @Override
    public Produto atualiza(Produto produto) {
        Produto produtoEncontrado = this.localizar(produto.getId());
        if (produtoEncontrado.equals(new Produto())) {
            return produtoEncontrado;
        }
        try {
            PreparedStatement statement = this.dataSource
                .getConnection()
                .prepareStatement(
                    "UPDATE produtos SET descricao = ?, valor = ? WHERE id= ?"
                );
            statement.setString(1, produto.getDescricao());
            statement.setBigDecimal(2, produto.getValor());
            statement.setInt(3, produto.getId());
            ResultSet result = statement.executeQuery();
            if(result.next()) 
            return criarProduto(result);
            
        } catch (SQLException ex) {}
        return new Produto();
    }

    @Override
    public Produto exclui(int id) {
        Produto produtoEncontrado = this.localizar(id);
        if (produtoEncontrado.equals(new Produto())) {
            return produtoEncontrado;
        }
        try {
            PreparedStatement statement = this.dataSource
                .getConnection()
                .prepareStatement(
                    "DELETE FROM produtos WHERE id= ?"
                );
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next()) 
            return criarProduto(result);
            
        } catch (SQLException ex) {}
        return new Produto();
    }
}
