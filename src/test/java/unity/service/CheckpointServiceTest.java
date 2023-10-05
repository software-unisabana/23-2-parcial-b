package unity.service;

import co.edu.unisabana.parcialarquitectura.controller.dto.CheckpointDTO;
import co.edu.unisabana.parcialarquitectura.repository.CheckpointPort;
import co.edu.unisabana.parcialarquitectura.service.CheckpointService;
import co.edu.unisabana.parcialarquitectura.service.model.Checkin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CheckpointServiceTest {

    @Mock
    private CheckpointPort checkpointPort;

    @InjectMocks
    private CheckpointService checkpointService;

    @Test
    private void Dado_un_chekpointDTO_nulo_Cuando_se_realiza_checkout_Entonces_arroja_excepcion() {
        CheckpointDTO checkpoint = new CheckpointDTO();
        Mockito.when(checkpointPort.findLastCheckin(checkpoint.driver, checkpoint.facility)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> {
            this.checkpointService.checkout(checkpoint);
        });
    }

    @Test
    private void Dado_un_chekpointDTO_con_dia_mayor_a_30_Cuando_se_realiza_checkout_Entonces_arroja_excepcion() {
        CheckpointDTO checkpoint = new CheckpointDTO("Hola", "pureba", 38);
        Checkin lastCheckin = new Checkin("Hola", "prueba", 38);
        Mockito.when(checkpointPort.findLastCheckin(checkpoint.driver, checkpoint.facility)).thenReturn(lastCheckin);
        assertThrows(IllegalArgumentException.class, () -> {
            this.checkpointService.checkout(checkpoint);
        });
    }

    @Test
    private void Dado_un_chekpointDTO_valido_Cuando_se_realiza_checkout_Entonces_se_crea_y_guarda_checkout() {
        CheckpointDTO checkpoint = new CheckpointDTO("Hola", "pureba", 26);
        Checkin lastCheckin = new Checkin("Hola", "prueba", 26);
        Mockito.when(checkpointPort.findLastCheckin(checkpoint.driver, checkpoint.facility)).thenReturn(lastCheckin);
        this.checkpointService.checkout(checkpoint);
        Mockito.verify(this.checkpointPort).saveCheckout(Mockito.any());
    }

}