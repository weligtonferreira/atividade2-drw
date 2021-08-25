package br.edu.ifpb.domain;

import br.edu.ifpb.domain.Cliente;
import java.util.List;

public interface Clientes {

    Cliente novo(Cliente cliente);
    Cliente localizar(int id);
    List<Cliente> todos();

}
