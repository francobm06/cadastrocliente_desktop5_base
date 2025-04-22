package dao;

import dao.cliente.ClienteDAO;
import dao.cliente.RAFClienteDAO;

/**
 * Implementa a fonte de dado para persistência em memória utilizando Arquivo de
 * Acesso Aleatório(RandonAcessFile).
 *
 * @author osmarbraz
 */
public class RAFDAOFactory extends DAOFactory {

    /**
     * Retorna uma Cliente DAO
     *
     * @return ClienteDAO Um DAO para cliente
     */
    public ClienteDAO getClienteDAO() {
        return new RAFClienteDAO();
    }
}