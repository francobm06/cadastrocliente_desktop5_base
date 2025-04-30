package dao.cliente;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Collections;

import dao.HashMapDAOFactory;
import entidade.cliente;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementa a persitência para cliente utilizando HashMap.
 *
 * @author osmarbraz
 */
public class HashMapClienteDAO extends HashMapDAOFactory implements ClienteDAO {
        
    private static final Map<String, cliente> mapa = new HashMap<>();
    
    @Override
    public boolean inserir(Object obj) {
        if (obj != null) {
            cliente cliente = (cliente) obj;
            boolean tem = mapa.containsKey(cliente.getCliente_id());
            if (tem == false) {
                mapa.put(cliente.getCliente_id(), cliente);
                return true;
            } else {
                System.out.println("Problema em inserir o registro!");                
            }
        }
        return false;
    }

    public int alterar(Object obj) {
        if (obj != null) {
            cliente cliente = (cliente) obj;
            boolean tem = mapa.containsKey(cliente.getCliente_id());
            if (tem == true) {
                cliente c = mapa.get(cliente.getCliente_id());
                c.setNome(cliente.getNome());
                c.setCPF(cliente.getCPF());
                return 1;
            } else {
                System.out.println("Problema em altear o registro!");
            }
        }
        return 0;
    }

    public int excluir(Object obj) {
        if (obj != null) {
            cliente cliente = (cliente) obj;
            boolean tem = mapa.containsKey(cliente.getCliente_id());
            if (tem == true) {
                mapa.remove(cliente.getCliente_id());
                return 1;
            } else {
                System.out.println("Problema em excluir o registro!");
            }
        }
        return 0;
    }

    public ArrayList<cliente> getLista() {
        ArrayList<cliente> lista = new ArrayList<>();
        Iterator<cliente> it = mapa.values().iterator();
        while (it.hasNext() == true) { //Avança enquanto tiver objetos
            cliente c = it.next();
            lista.add(c);
        }
        return lista;
    }
    
    @Override
    public ArrayList<cliente> aplicarFiltro(Object obj) {
        if (obj != null) {
            cliente cliente = (cliente) obj;
            ArrayList<cliente> lista = new ArrayList<>();
            Iterator<cliente> it = mapa.values().iterator();

            while (it.hasNext() == true) { //Avança enquanto tiver objetos
                cliente c = it.next();

                //Filtro para clienteId
                if (c.getCliente_id().equalsIgnoreCase(cliente.getCliente_id()))  {
                        lista.add(c);                    
                }

                //Filtro para nome
                if (c.getCliente_id().equalsIgnoreCase(cliente.getCliente_id())) {
                     lista.add(c);                    
                }

                //Filtro para CPF
                if (c.getCPF().equalsIgnoreCase(cliente.getCPF())) {
                    lista.add(c);                    
                }
            }
            return lista;
        } else {
            return new ArrayList();
        }
    }
}