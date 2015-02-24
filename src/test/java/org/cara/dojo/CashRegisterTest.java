package org.cara.dojo;

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
  public void returns_no_change_when_exact_payment_offered() {
    // Given
    Float totalProducts = 20.0f;
    List<Denomination> offeredPaymentDenoms = Arrays.asList(Denomination.TWENTY);

    //when
    List<Denomination> resultingChange = cashRegister.getChangeForPayment(totalProducts, offeredPaymentDenoms);

    Assertions.assertThat(resultingChange).isEqualTo(Arrays.asList());
  }

  @Test
  public void compute_balance_with_just_Enough_Money() {
    // Given
    Float totalProducts = 50.0f;
    Float payment = 50.0f;

    // When
    Float balance = cashRegister.getPaymentBalance(totalProducts, payment);

    // Then
    Assertions.assertThat(balance).isEqualTo(0.0f);
  }

  @Test
  public void compute_balance_when_Not_Enough_Money_Offered() {
    // Given
    Float totalProductsCost = 50.0f;
    Float offeredPayment = 30.0f;

    // When
    Float balance = cashRegister.getPaymentBalance(totalProductsCost, offeredPayment);

    // Then
    Assertions.assertThat(balance).isEqualTo(-20.0f);
  }

  @Test
  public void compute_balance_when_too_Much_Money() {
    // Given
    Float totalProducts = 50.0f;
    Float payment = 80.0f;

    // When
    Float balance = cashRegister.getPaymentBalance(totalProducts, payment);

    // Then
    Assertions.assertThat(balance).isEqualTo(30.0f);
  }

  @Test (expected=InsuffisantMoneyException.class)
  public void rejects_Payment_If_Not_EnoughMoney() throws InsuffisantMoneyException
  {
    // Given
    Float balance = -50.0f;

    // When
    cashRegister.checkBalanceToProceedWithPayment(balance);
  }

  @Test
  public void accepts_Payment_If_Enough_Money() throws InsuffisantMoneyException
  {
    // Given
    Float balance = 40.0f;

    // When
    cashRegister.checkBalanceToProceedWithPayment(balance);

    // Then
  }

  @Test
  public void accepts_Payment_If_Just_Enough_Money() throws InsuffisantMoneyException
  {
    // Given
    Float balance = 0.0f;

    // When
    cashRegister.checkBalanceToProceedWithPayment(balance);

    // Then
  }

  @Test
  public void acceptedPaymentReturnsChangeAmount() throws InsuffisantMoneyException
  {
    // Given
    Float totalProducts = 50.0f;
    Float payment = 60.0f;

    // When
    Float change = cashRegister.makePayment(totalProducts, payment);
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
    Float amount = cashRegister.account(denoms);

    // Then
    Assertions.assertThat(amount).isEqualTo(20.02f);
  }



  @Test
  public void count_currency_from_list_of_denominations_20_26()
  {
    // Given
    List<Denomination> denoms = Arrays.asList(Denomination.PENNY, Denomination.QUARTER, Denomination.TWENTY);

    // When
    Float amount = cashRegister.account(denoms);

    // Then
    Assertions.assertThat(amount).isEqualTo(20.26f);
  }

  @Test
  public void init_cashregister_with_denoms() {
    // Given
    List<Denomination> denoms = Arrays.asList(Denomination.PENNY, Denomination.QUARTER, Denomination.TWENTY);

    // When
    cashRegister.init(denoms);

    // Then
    Assertions.assertThat(cashRegister.getDenoms()).isEqualTo(denoms);
  }


}
