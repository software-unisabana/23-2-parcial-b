package integration.controller;

import co.edu.unisabana.parcialarquitectura.controller.dto.CheckpointDTO;
import co.edu.unisabana.parcialarquitectura.controller.dto.ResponseGate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class GateControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    private void Dado_un_checkpointdto_valido_Cuando_se_accede_al_end_point_checkout_Entonces_retorna_true() {
        CheckpointDTO checkpoint = new CheckpointDTO("soyprueba", "ayuda", 15);
        ResponseEntity<ResponseGate> respuesta = restTemplate.postForEntity("/checkpoint/checkout", checkpoint, ResponseGate.class);
        assertEquals(true, respuesta.getBody());
    }

    @Test
    private void Dado_un_checkpointdto_no_valido_Cuando_se_accede_al_end_point_checkout_Entonces_retorna_false() {
        CheckpointDTO checkpoint = new CheckpointDTO("soyprueba", "ayuda", 32);
        assertThrows(Exception.class, () -> {
            ResponseEntity<ResponseGate> respuesta = restTemplate.postForEntity("/checkpoint/checkout", checkpoint, ResponseGate.class);
        });
    }
}