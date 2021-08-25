package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Clientes;
import br.edu.ifpb.domain.Cliente;
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

@Stateless
public class ClientesEmJDBC implements Clientes {

    @Resource(lookup = "java:app/jdbc/pgadmin")
    private DataSource dataSource;

    @Override
    public Cliente novo(Cliente cliente) {
        try {
            PreparedStatement statement = this.dataSource
                .getConnection() //2
                .prepareStatement(
                    "INSERT INTO clientes (cpf, nome) VALUES(?,?) RETURNING *; "
                );
            statement.setString(1,cliente.getCpf());
            statement.setString(2,cliente.getNome());
            ResultSet result = statement.executeQuery();
             if(result.next()) 
            return criarCliente(result);
        } catch (SQLException ex) {
            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
        return new Cliente();
    }

    @Override
    public List<Cliente> todos() {
        try {
            List<Cliente> lista = new ArrayList<>();
            ResultSet result = this.dataSource
                .getConnection()
                .prepareStatement(
                    "SELECT * FROM clientes"
                ).executeQuery();
            while (result.next()) {
                lista.add(
                    criarCliente(result)
                );
            }
            return lista;
        } catch (SQLException ex) {
//            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
            return Collections.EMPTY_LIST;
        }
    }

    private Cliente criarCliente(ResultSet result) throws SQLException {
        String nome = result.getString("nome");
        String cpf = result.getString("cpf");
        int id = result.getInt("id");
        return new Cliente(id,cpf,nome);
    }

    @Override
    public Cliente localizar(int id) {
        try {
            PreparedStatement statement = this.dataSource
                .getConnection()
                .prepareStatement(
                    "SELECT * FROM clientes WHERE id= ?"
                );
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            if(result.next()) 
            return criarCliente(result);
            
        } catch (SQLException ex) {}
        return new Cliente();
    }
}
