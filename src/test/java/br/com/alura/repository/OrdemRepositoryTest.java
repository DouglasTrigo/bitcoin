package br.com.alura.repository;

import br.com.alura.model.Ordem;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

@QuarkusTest
public class OrdemRepositoryTest {

    @InjectMock
    OrdemRepository ordemRepository;

    @Test
    public void testarSeListAllRetornaOrdensCorretas(){
        var primeiraOrdem = new Ordem();
        var segundaOrdem = new Ordem();

        List<Ordem> ordens = Arrays.asList(primeiraOrdem, segundaOrdem);

        Mockito.when(ordemRepository.listAll()).thenReturn(ordens);

        Assertions.assertSame(segundaOrdem, ordemRepository.listAll().get(1));
    }
}
