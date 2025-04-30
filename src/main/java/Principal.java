

import controle.Ctr_Cliente;

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

        Ctr_Cliente controle = new Ctr_Cliente();
        controle.ExecutarCtrl_Cliente();
    }
}