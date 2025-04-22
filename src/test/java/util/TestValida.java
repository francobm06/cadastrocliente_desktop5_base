package util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestValida {

    private Valida valida = null;

    /**
     * Instância uma classe de validação.
     */
    @BeforeAll
    void inicializa() {
        valida = new Valida();
    }

    /**
     * Testa CPF válido.
     */
    @Test
    void testValidaCPFValido1() {
        assertTrue(valida.validaCPF("11111111111"));
    }

    /**
     * Testa CPF válido.
     */
    @Test
    void testValidaCPFValido2() {
        assertTrue(valida.validaCPF("84807125206"));
    }

    /**
     * Testa CPF inválido final 1.
     */
    @Test
    void testValidaCPFInvalido1() {
        assertFalse(valida.validaCPF("94622036011"));
    }
    
    /**
     * Testa CPF inválido final 2.
     */
    @Test
    void testValidaCPFInvalido2() {
        assertFalse(valida.validaCPF("94622036012"));
    }

    /**
     * Finaliza a classe de validação.
     */
    @AfterAll
    void finaliza() {
        valida = null;
    }
}
