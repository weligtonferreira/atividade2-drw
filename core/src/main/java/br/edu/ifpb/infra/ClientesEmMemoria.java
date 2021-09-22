package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Clientes;
import br.edu.ifpb.domain.Cliente;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class ClientesEmMemoria implements Clientes {

    private List<Cliente> clientes = new ArrayList<>();

    @Override
    public Cliente novo(Cliente cliente) {
        this.clientes.add(cliente);
        return cliente;
    }

    @Override
    public List<Cliente> todos() {
        return Collections
            .unmodifiableList(
                this.clientes
            );
    }

    @Override
    public Cliente localizar(int id) {
        return this.clientes
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(new Cliente());
    }

    @Override
    public Cliente atualiza(Cliente cliente) {
        this.clientes.removeIf(c -> c.getId() == cliente.getId());
        this.clientes.add(cliente);
        return cliente;
    }

    @Override
    public Cliente exclui(int id) {
        this.clientes.removeIf(c -> c.getId() == id);
        return new Cliente();
    }
}
