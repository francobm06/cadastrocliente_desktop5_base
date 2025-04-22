package dao.cliente;

import java.util.LinkedList;
import java.util.List;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;

import dao.RAFDAOFactory;
import entidade.Cliente;

/**
 * Implementa a persitência para cliente utilizando Arquivo de Acesso
 * Aleatório(RandomAcessFile).
 *
 * @author osmarbraz
 */
public class RAFClienteDAO extends RAFDAOFactory implements ClienteDAO {

    private RandomAccessFile arquivo;

    public RAFClienteDAO() {
        abrirArquivo();
    }

    private void abrirArquivo() {
        try {
            File nomeArquivo = new File("cliente.dat");
            arquivo = new RandomAccessFile(nomeArquivo, "rw");
        } catch (IOException e) {
            System.out.println("Problema em abrir o arquivo!" + e);
        }
    }

    public void fecharArquivo() {
        try {
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Problema em fechar o arquivo!" + e);
        }
    }

    @Override
    public boolean inserir(Object obj) {
        if (obj != null) {
            Cliente cliente = (Cliente) obj;
            if (procurarCodigo(cliente.getCliente_id() + "") == -1) {
                try {
                    RAFRegistroCliente registro = new RAFRegistroCliente();
                    registro.setCliente_id(cliente.getCliente_id());
                    registro.setNome(cliente.getNome());
                    registro.setCpf(cliente.getCpf());
                    arquivo.seek(arquivo.length());
                    registro.escrita(arquivo);
                    return true;
                } catch (IOException e) {
                   System.out.println("Problema em inserir o registro!" + e);
                }
            }
        }
        return false;
    }

    @Override
    public List<Cliente> getLista() {
        List<Cliente> lista = new LinkedList();
        RAFRegistroCliente registro = new RAFRegistroCliente();
        try {
            arquivo.seek(0);

            while (arquivo.getFilePointer() < arquivo.length()) {
                registro.leitura(arquivo);
                Cliente cli = new Cliente();
                cli.setCliente_id(registro.getCliente_id());
                cli.setNome(registro.getNome());
                cli.setCpf(registro.getCpf());
                lista.add(cli);
            }
        } catch (EOFException eof) {
            System.out.println("Problema no fim do arquivo em geLista:" + eof);
        } catch (IOException io) {
            System.out.println("Problema de io no arquivo em getLista:" + io);
        }
        return lista;
    }
    
    @Override
    public List<Cliente> aplicarFiltro(Object obj) {
        if (obj != null) {
            Cliente cliente = (Cliente) obj;
            List<Cliente> lista = new LinkedList<>();
            //Filtro para clienteId            
            if (!"".equals(cliente.getCliente_id())) {
                lista = aplicarFiltroId(cliente);
            }
            //Filtro para nome            
            if (!"".equals(cliente.getNome())) {
                lista = aplicarFiltroNome(cliente);
            }
            //Filtro para CPF
            if (!"".equals(cliente.getCpf())) {
                lista = aplicarFiltroCpf(cliente);
            }
            return lista;
        } else {
            return Collections.emptyList();
        }
    }
    
    public Cliente gerarRegistro(RAFRegistroCliente registro) {
        Cliente cliente = new Cliente();
        cliente.setCliente_id(registro.getCliente_id());
        cliente.setNome(registro.getNome());
        cliente.setCpf(registro.getCpf());        
        return cliente;
    }

    public List<Cliente> aplicarFiltroId(Cliente cliente) {
        List<Cliente> lista = new LinkedList<>();
        //Filtro para clienteId
        try {
            arquivo.seek(0);
            RAFRegistroCliente registro = new RAFRegistroCliente();
            while (arquivo.getFilePointer() < arquivo.length()) { //Avança enquanto tiver objetos
                registro.leitura(arquivo);
                if (registro.getCliente_id().equalsIgnoreCase(cliente.getCliente_id())) {                    
                    lista.add(gerarRegistro(registro));
                }
            }
        } catch (EOFException eof) {
            System.out.println("Problema no fim do arquivo no aplicar filtro no id:" + eof);
        } catch (IOException io) {
            System.out.println("Problema de io no arquivo em aplicar filtro no id:" + io);
        }
        return lista;
    }

    public List<Cliente> aplicarFiltroNome(Cliente cliente) {
        List<Cliente> lista = new LinkedList<>();
        //Filtro para nome
        try {
            arquivo.seek(0);
            RAFRegistroCliente registro = new RAFRegistroCliente();
            while (arquivo.getFilePointer() < arquivo.length()) { //Avança enquanto tiver objetos
                registro.leitura(arquivo);
                if (registro.getNome().equalsIgnoreCase(cliente.getNome())) {
                    lista.add(gerarRegistro(registro));
                }
            }
        } catch (EOFException eof) {
            System.out.println("Problema no fim do arquivo no aplicar filtro no nome:" + eof);
        } catch (IOException io) {
            System.out.println("Problema de io no arquivo em aplicar filtro no nome:" + io);
        }
        return lista;
    }

    public List<Cliente> aplicarFiltroCpf(Cliente cliente) {
        List<Cliente> lista = new LinkedList<>();
        //Filtro para Cpf
        try {
            arquivo.seek(0);
            RAFRegistroCliente registro = new RAFRegistroCliente();
            while (arquivo.getFilePointer() < arquivo.length()) { //Avança enquanto tiver objetos
                registro.leitura(arquivo);
                if (registro.getCpf().equalsIgnoreCase(cliente.getCpf())) {
                    lista.add(gerarRegistro(registro));
                }
            }
        } catch (EOFException eof) {
            System.out.println("Problema no fim do arquivo no aplicar filtro no cpf:" + eof);
        } catch (IOException io) {
            System.out.println("Problema de io no arquivo em aplicar filtro no cpf:" + io);
        }
        return lista;
    }

    @Override
    public int alterar(Object obj) {
        if (obj != null) {
            Cliente cliente = (Cliente) obj;
            String chave = cliente.getCliente_id() + "";
            long pos = -1;
            RAFRegistroCliente registro = new RAFRegistroCliente();
            try {
                pos = procurarCodigo(chave);
                if (pos != -1) {
                    arquivo.seek(pos * registro.getTamanho());
                    registro.setCliente_id(cliente.getCliente_id());
                    registro.setNome(cliente.getNome());
                    registro.setCpf(cliente.getCpf());
                    registro.escrita(arquivo);
                    return 1;
                }
            } catch (EOFException eof) {
                System.out.println("Problema no fim do arquivo em alterar:" + eof);
            } catch (IOException io) {
                System.out.println("Problema de io no arquivo em alterar:" + io);
            }
        }
        return 0;
    }

    private int procurarCodigo(String cod) {
        int pos = -1;
        int cont = 0;
        RAFRegistroCliente registro = new RAFRegistroCliente();
        try {
            arquivo.seek(0);
            while (arquivo.getFilePointer() < arquivo.length()) {
                registro.leitura(arquivo);
                if (registro.getCliente_id().equalsIgnoreCase(cod)) {
                    pos = cont;
                }
                cont = cont + 1;
            }
        } catch (EOFException eof) {
           System.out.println("Problema no fim do arquivo em procurar por c\u00f3digo:" + eof);
        } catch (IOException io) {
           System.out.println("Problema de io no arquivo em procurar por c\u00f3digo:" + io);
        }
        return pos;
    }

    @Override
    public int excluir(Object obj) {
        if (obj != null) {
            Cliente cliente = (Cliente) obj;
            String chave = cliente.getCliente_id() + "";
            long pos = -1;
            RAFRegistroCliente registro = new RAFRegistroCliente();
            try {
                pos = procurarCodigo(chave);
                if (pos != -1) {
                    arquivo.seek(pos * registro.getTamanho());
                    registro.setCliente_id("-1");
                    registro.setNome("");
                    registro.setCpf("");
                    registro.escrita(arquivo);
                    return 1;
                }
            } catch (EOFException eof) {
                System.out.println("Problema no fim do arquivo em excluir:" +  eof);
            } catch (IOException io) {
                System.out.println("Problema de io no arquivo em excluir:" + io);
            }
        }
        return 0;
    }
}