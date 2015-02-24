package org.cara.dojo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class CashRegister {

  @Test
  public void notEnoughMoney() {
    // Given
    Float totalProducts = 50.0f;
    Float payment = 30.0f;

    // When
    Float balance = getBalance(totalProducts, payment);

    // Then
    Assertions.assertThat(balance).isEqualTo(-20.0f);
  }

  @Test (expected=InsuffisantMoneyException.class)
  public void rejectPaymentIfNotEnoughMoney() throws InsuffisantMoneyException
  {
    // Given
    Float balance = -50.0f;

    // When
    checkBalance(balance);
  }

  @Test
  public void acceptPaymentIfEnoughMoney() throws InsuffisantMoneyException
  {
    // Given
    Float balance = 40.0f;

    // When
    checkBalance(balance);

    // Then
  }

  @Test
  public void acceptPaymentIfJustEnoughMoney() throws InsuffisantMoneyException
  {
    // Given
    Float balance = 0.0f;

    // When
    checkBalance(balance);

    // Then
  }

  @Test
  public void expressDenomination() throws Exception {
      //Given
      Denomination penny = Denomination.PENNY;
      Denomination quarter = Denomination.QUARTER;
      Denomination twenty = Denomination.TWENTY;

      // When Then
      Assertions.assertThat(penny.amount()).isEqualTo(0.01f);
      Assertions.assertThat(quarter.amount()).isEqualTo(0.25f);
      Assertions.assertThat(twenty.amount()).isEqualTo(20f);
  }

  @Test
  public void countDenomination()
  {
    // Given
    List<Denomination> denoms = Arrays.asList(Denomination.PENNY, Denomination.PENNY, Denomination.TWENTY);

    // When
    Float amount = account(denoms);

    // Then
    Assertions.assertThat(amount).isEqualTo(20.02f);
  }

  @Test
  public void countDenomination01()
  {
    // Given
    List<Denomination> denoms = Arrays.asList(Denomination.PENNY, Denomination.QUARTER, Denomination.TWENTY);

    // When
    Float amount = account(denoms);

    // Then
    Assertions.assertThat(amount).isEqualTo(20.26f);
  }

  private Float account(List<Denomination> denoms) {
    float totalAmount = 0.0f;
    for (Denomination denomination : denoms) {
      totalAmount += denomination.amount();
    }

    return totalAmount;
  }

  @Test
  public void acceptedPaymentReturnsChangeAmount() throws InsuffisantMoneyException
  {
    // Given
    Float totalProducts = 50.0f;
    Float payment = 60.0f;

    // When
    Float change = makePayment(totalProducts, payment);
    // Then
    Assertions.assertThat(change).isEqualTo(10.0f);
  }



  private Float makePayment(Float totalProducts, Float payment) throws InsuffisantMoneyException {
      Float balance = getBalance(totalProducts, payment);
      checkBalance(balance);
      return balance;
  }

  private void checkBalance(Float balance) throws InsuffisantMoneyException {
    if(balance <0.0f) {
      throw new InsuffisantMoneyException();
    }
  }

  @Test
  public void justEnoughMoney() {
    // Given
    Float totalProducts = 50.0f;
    Float payment = 50.0f;

    // When
    Float balance = getBalance(totalProducts, payment);

    // Then
    Assertions.assertThat(balance).isEqualTo(0.0f);
  }

  @Test
  public void tooMuchMoney() {
    // Given
    Float totalProducts = 50.0f;
    Float payment = 80.0f;

    // When
    Float balance = getBalance(totalProducts, payment);

    // Then
    Assertions.assertThat(balance).isEqualTo(30.0f);
  }



  private Float getBalance(Float totalProducts, Float payment) {
    return payment - totalProducts;
  }
}
