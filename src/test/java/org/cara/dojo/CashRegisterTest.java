package org.cara.dojo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

public class CashRegisterTest {

  private CashRegister cashRegister;

  @Before
  public void setup() {
    cashRegister = new CashRegister();
  }

  @Test
  public void compute_balance_with_just_Enough_Money() {
    // Given
    Float totalProducts = 50.0f;
    Float payment = 50.0f;

    // When
    Float balance = getBalance(totalProducts, payment);

    // Then
    Assertions.assertThat(balance).isEqualTo(0.0f);
  }

  @Test
  public void compute_balance_when_Not_Enough_Money_Offered() {
    // Given
    Float totalProductsCost = 50.0f;
    Float offeredPayment = 30.0f;

    // When
    Float balance = getBalance(totalProductsCost, offeredPayment);

    // Then
    Assertions.assertThat(balance).isEqualTo(-20.0f);
  }

  @Test
  public void compute_balance_when_too_Much_Money() {
    // Given
    Float totalProducts = 50.0f;
    Float payment = 80.0f;

    // When
    Float balance = getBalance(totalProducts, payment);

    // Then
    Assertions.assertThat(balance).isEqualTo(30.0f);
  }

  @Test (expected=InsuffisantMoneyException.class)
  public void rejects_Payment_If_Not_EnoughMoney() throws InsuffisantMoneyException
  {
    // Given
    Float balance = -50.0f;

    // When
    checkBalance(balance);
  }

  @Test
  public void accepts_Payment_If_Enough_Money() throws InsuffisantMoneyException
  {
    // Given
    Float balance = 40.0f;

    // When
    checkBalance(balance);

    // Then
  }

  @Test
  public void accepts_Payment_If_Just_Enough_Money() throws InsuffisantMoneyException
  {
    // Given
    Float balance = 0.0f;

    // When
    checkBalance(balance);

    // Then
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

  @Test
  public void express_denomination_and_currency_value() throws Exception {
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
  public void count_currency_from_list_of_denominations_20_02()
  {
    // Given
    List<Denomination> denoms = Arrays.asList(Denomination.PENNY, Denomination.PENNY, Denomination.TWENTY);

    // When
    Float amount = account(denoms);

    // Then
    Assertions.assertThat(amount).isEqualTo(20.02f);
  }



  @Test
  public void count_currency_from_list_of_denominations_20_26()
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



  private Float getBalance(Float totalProducts, Float payment) {
    return payment - totalProducts;
  }
}
