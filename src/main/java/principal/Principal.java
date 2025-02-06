package principal;


import controle.CtrCliente;

/**
 * Classe que possui a operação main da aplicação.
 *
 * Serve para dar início ao sistema.
 *
 * @author osmarbraz
 */
public class Principal {

    /**
     * Inicia a aplicação.
     *
     * @param args args
     */
    public static void main(String[] args) {

        CtrCliente controle = new CtrCliente();
        controle.executar();
    }
}
