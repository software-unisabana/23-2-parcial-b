package co.edu.unisabana.parcialarquitectura.repository.entity;

import co.edu.unisabana.parcialarquitectura.service.model.Checkin;
import co.edu.unisabana.parcialarquitectura.service.model.Checkout;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "CHECKPOINT")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CheckpointEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String type;

  private String facility;

  private String driver;

  private int dayOfMonth;

  private LocalDateTime creationDate;

  private boolean finalized;

  public Checkin toCheckin() {
    Checkin checkin = new Checkin(this.facility, this.driver, this.dayOfMonth);
    checkin.setId(this.id);
    return checkin;
  }

  public static CheckpointEntity fromCheckin(Checkout checkin) {
    CheckpointEntity checkpoint = new CheckpointEntity();
    checkpoint.setCreationDate(LocalDateTime.now());
    checkpoint.setDriver(checkin.getDriver());
    checkpoint.setFacility(checkin.getFacility());
    checkpoint.setDayOfMonth(checkin.getDayOfMonth());
    checkpoint.setType("CHECKIN");
    checkpoint.setFinalized(false);
    return checkpoint;
  }

  public static CheckpointEntity fromCheckout(Checkout checkout) {
    CheckpointEntity checkpoint = new CheckpointEntity();
    checkpoint.setDriver(checkout.getDriver());
    checkpoint.setFacility(checkout.getFacility());
    checkpoint.setDayOfMonth(checkout.getDayOfMonth());
    checkpoint.setType("CHECKOUT");
    return checkpoint;
  }

}