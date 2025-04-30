package controle;

import java.awt.event.ActionEvent;

import formulario.FrmCliente;
import entidade.*;
import Util.Valida;

/**
 * Classe que realiza a interação entre a interface e a entidade do sistema.
 *
 * @author osmarbraz
 */
public class CtrCliente {

    private FrmCliente frmCliente;

    /**
     * Construtor sem argumentos.
     */
    public CtrCliente() {

        // Instancia a janela
        setFrmCliente(new FrmCliente());

        // Associa os eventos aos componentes
        getFrmCliente().jBIncluir.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jBIncluir_actionPerformed(e);
                    }
                });

        getFrmCliente().jBAlterar
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jBAlterar_actionPerformed(e);
                    }
                });

        getFrmCliente().jBConsultar
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jBConsultar_actionPerformed(e);
                    }
                });

        getFrmCliente().jBExcluir
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jBExcluir_actionPerformed(e);
                    }
                });

        getFrmCliente().jBLimpar
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jBLimpar_actionPerformed(e);
                    }
                });

        getFrmCliente().jBFechar
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jBFechar_actionPerformed(e);
                    }
                });
    }

    /**
     * Operação que inicia a sequência de interações da manutenção de cliente.
     */
    public void executar() {
        getFrmCliente().setVisible(true);
    }

    /**
     * Inclui um cliente.
     *
     * @param e Objeto do evento.
     */
    public void jBIncluir_actionPerformed(ActionEvent e) {
        cliente cliente = new cliente();
        cliente.setCliente_id(getFrmCliente().jTClienteId.getText());
        cliente.setNome(getFrmCliente().jTNome.getText());
        cliente.setCPF(getFrmCliente().jTCpf.getText());
        Valida valida = new Valida();
        boolean cpfValido = valida.ValidaCPF(cliente.getCPF());
        if (cpfValido == true) {
            boolean resultado = cliente.inserir();
            if (resultado == true) {
                getFrmCliente().mostrarMensagem("Inclusão realizada com sucesso!");
            } else {
                getFrmCliente().mostrarMensagem("Inclusão não realizada!");
            }
        } else {
            getFrmCliente().mostrarMensagem("CPF Inválido!");
        }
    }

    /**
     * Altera um cliente.
     *
     * @param e Objeto do evento.
     */
    public void jBAlterar_actionPerformed(ActionEvent e) {
        cliente cliente = new cliente();
        cliente.setCliente_id(getFrmCliente().jTClienteId.getText());
        cliente.setNome(getFrmCliente().jTNome.getText());
        cliente.setCPF(getFrmCliente().jTCpf.getText());
        Valida valida = new Valida();
        boolean cpfValido = valida.ValidaCPF(cliente.getCPF());
        if (cpfValido == true) {
            int resultado = cliente.alterar();
            if (resultado != 0) {
                getFrmCliente().mostrarMensagem("Alteração realizada com sucesso!");
            } else {
                getFrmCliente().mostrarMensagem("Alteração nao realizada!");
            }
        } else {
            getFrmCliente().mostrarMensagem("CPF Inválido!");
        }
    }

    /**
     * Consulta um cliente.
     *
     * @param e Objeto do evento.
     */
    public void jBConsultar_actionPerformed(ActionEvent e) {
        cliente cliente = new cliente();
        cliente.setCliente_id(getFrmCliente().jTClienteId.getText());
        boolean resultado = cliente.abrir();
        if (resultado == true) {
            getFrmCliente().jTNome.setText(cliente.getNome());
            getFrmCliente().jTCpf.setText(cliente.getCPF());
            getFrmCliente().mostrarMensagem("Cliente encontrado!");
        } else {
            getFrmCliente().mostrarMensagem("Cliente não encontrado!");
        }
    }

    /**
     * Excluí o cliente.
     *
     * @param e Objeto do evento.
     */
    public void jBExcluir_actionPerformed(ActionEvent e) {
        cliente cliente = new cliente();
        cliente.setCliente_id(getFrmCliente().jTClienteId.getText());
        int resultado = cliente.excluir();
        if (resultado != 0) {
            getFrmCliente().mostrarMensagem("Exclusão realizada com sucesso!");
        } else {
            getFrmCliente().mostrarMensagem("Exclusão nao realizada!");
        }
    }

    /**
     * Limpa as caixas de texto.
     *
     * @param e Objeto do evento.
     */
    public void jBLimpar_actionPerformed(ActionEvent e) {
        getFrmCliente().jTClienteId.setText("");
        getFrmCliente().jTNome.setText("");
        getFrmCliente().jTCpf.setText("");
    }

    /**
     * Fecha o sistema.
     *
     * @param e Objeto do evento.
     */
    public void jBFechar_actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    /**
     * Retorna o formulário utilizado por este controle.
     */
    public FrmCliente getFrmCliente() {
        return frmCliente;
    }

    /**
     * Modifica o formulário utilizando por este controle.
     *
     */
    public void setFrmCliente(FrmCliente frmCliente) {
        this.frmCliente = frmCliente;
    }
}
