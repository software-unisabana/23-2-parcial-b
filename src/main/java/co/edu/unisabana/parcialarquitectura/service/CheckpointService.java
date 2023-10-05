package co.edu.unisabana.parcialarquitectura.service;

import co.edu.unisabana.parcialarquitectura.controller.dto.CheckpointDTO;
import co.edu.unisabana.parcialarquitectura.service.model.Checkin;
import co.edu.unisabana.parcialarquitectura.service.model.Checkout;
import co.edu.unisabana.parcialarquitectura.repository.CheckpointPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CheckpointService {

  private CheckpointPort checkpointPort;

  public void checkout(CheckpointDTO checkpoint) {
    Checkin lastCheckin = checkpointPort.findLastCheckin(checkpoint.driver, checkpoint.facility);
    if (lastCheckin == null) {
      throw new IllegalArgumentException("don't exist previously check in");
    }
    if (checkpoint.dayOfMonth > 30 || checkpoint.dayOfMonth < 1) {
      throw new IllegalArgumentException("Invalid date");
    }
    Checkout checkout = new Checkout(checkpoint.facility, checkpoint.driver, checkpoint.dayOfMonth);
    checkpointPort.saveCheckout(checkout);
  }
}
