package co.edu.unisabana.parcialarquitectura.service;

import org.junit.jupiter.api.Test;

class BuyTest {

  private final Buy service = new Buy();

  @Test
  public void test() {
    service.makePurchase(2, 3, "Books");
  }

}