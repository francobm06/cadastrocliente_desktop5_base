package dao.cliente;

import java.util.*;

import entidade.cliente;
import java.util.ArrayList;

/**
 * Interface que define as operações para a persistência de cliente.
 *
 * @author osmarbraz
 */
public interface ClienteDAO {

    public boolean inserir(Object obj);

    public int alterar(Object obj);

    public int excluir(Object obj);

    public ArrayList<cliente> aplicarFiltro(Object obj);

    public ArrayList<cliente> getLista();
}
