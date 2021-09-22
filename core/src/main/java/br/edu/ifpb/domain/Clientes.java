package br.edu.ifpb.domain;

import java.util.List;

public interface Clientes {

    Cliente novo(Cliente cliente);
    Cliente atualiza(Cliente cliente);
    Cliente exclui(int id);
    Cliente localizar(int id);
    List<Cliente> todos();

}
