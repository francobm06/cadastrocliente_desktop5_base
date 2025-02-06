package dao.cliente;

import java.io.IOException;
import java.io.RandomAccessFile;

import entidade.Cliente;

/**
 * Implementa a classe do Registro do arquivo binário.
 *
 * @author osmarbraz
 */
public class RAFRegistroCliente extends Cliente {

    public RAFRegistroCliente() {
        super("0", "", "");
    }

    public RAFRegistroCliente(String codigo, String nome, String cpf) {
        super(codigo, nome, cpf);
    }

    public void escrita(RandomAccessFile arquivo) throws IOException {
        arquivo.writeInt(Integer.parseInt(getCliente_id()));
        escreveString(arquivo, getNome(), 100);
        escreveString(arquivo, getCpf(), 11);
    }

    public void leitura(RandomAccessFile arquivo) throws IOException {
        setCliente_id(arquivo.readInt() + "");
        setNome(lerString(arquivo, 100).trim());
        setCpf(lerString(arquivo, 11).trim());
    }

    private void escreveString(RandomAccessFile arquivo, String nome, int tamanho) throws IOException {
        StringBuilder buf = new StringBuilder(tamanho);
        if (nome != null) {
            buf = new StringBuilder(nome);
        }
        buf.setLength(tamanho);
        arquivo.writeChars(buf.toString());
    }

    private String lerString(RandomAccessFile f, int tamanho) throws IOException {
        char[] name= new char[tamanho];
        char temp;
        for (int i = 0; i < name.length; i++) {
            temp = f.readChar();
            name[i] = temp;
        }
        return new String(name).replace('\0', ' ');
    }

    /**
     * Retorna o tamanho do registro em bytes.
     *
     * @return Um inteiro longo com o tamanho do registro.
     */
    public long getTamanho() {
        return 200 + 22 + 4L;
    }
}