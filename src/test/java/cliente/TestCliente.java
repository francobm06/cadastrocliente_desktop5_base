package cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.List;

import entidade.cliente;

public class TestCliente {

    /**
     * Testa o construtor sem argumentos do cliente.
     */
    @Test
    void testCliente() {
        cliente instancia = new cliente();
        assertTrue("".equals(instancia.getCliente_id()) && "".equals(instancia.getNome()) && "".equals(instancia.getCPF()));
    }

    @Test
    void testClienteIdInt() {
        cliente instancia = new cliente();
        instancia.setCliente_id("1");
        assertTrue("1".equals(instancia.getCliente_id()) && "".equals(instancia.getNome()) && "".equals(instancia.getCPF()));
    }

    @Test
    void testParaString() {
        cliente instancia = new cliente();
        String esperado = "clienteId: - Nome : - CPF :";
        assertEquals(esperado, instancia.toString());
    }    
}
