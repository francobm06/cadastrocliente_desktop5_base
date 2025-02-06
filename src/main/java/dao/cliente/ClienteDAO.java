package dao.cliente;

import java.util.List;

import entidade.Cliente;

/**
 * Interface que define as operações para a persistência de cliente.
 *
 * @author osmarbraz
 */
public interface ClienteDAO {

    public boolean inserir(Object obj);

    public int alterar(Object obj);

    public int excluir(Object obj);

    @SuppressWarnings("rawtypes")
    public List<Cliente> aplicarFiltro(Object obj);

    @SuppressWarnings("rawtypes")
    public List<Cliente> getLista();
}
