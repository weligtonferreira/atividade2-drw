package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Venda;
import br.edu.ifpb.domain.Vendas;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class VendasEmMemoria implements Vendas {

    private List<Venda> vendas = new ArrayList<>();

    @Override
    public Venda nova(Venda venda) {
        this.vendas.add(venda);
        return venda;
    }

    @Override
    public List<Venda> todos() {
        return Collections
            .unmodifiableList(
                this.vendas
            );
    }

    @Override
    public Venda localizar(int id) {
        return this.vendas
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(new Venda());
    }

    @Override
    public Venda atualiza(Venda venda) {
        this.vendas.removeIf(v -> v.getId() == venda.getId());
        this.vendas.add(venda);
        return venda;
    }

    @Override
    public Venda exclui(int id) {
        this.vendas.removeIf(v -> v.getId() == id);
        return new Venda();
    }
}
