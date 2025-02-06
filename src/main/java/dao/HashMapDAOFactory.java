package dao;

import dao.cliente.ClienteDAO;
import dao.cliente.HashMapClienteDAO;

/**
 * Implementa a fonte de dado para persistência em memória utilizando HashMap.
 *
 * @author osmarbraz
 */
public class HashMapDAOFactory extends DAOFactory {

    /**
     * Retorna uma Cliente DAO
     *
     * @return ClienteDAO Um DAO para cliente
     */
    @Override
    public ClienteDAO getClienteDAO() {
        return new HashMapClienteDAO();
    }
}
