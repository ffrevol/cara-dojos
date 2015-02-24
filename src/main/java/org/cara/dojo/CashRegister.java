package org.cara.dojo;

import java.util.List;

/**
 *
 */
public class CashRegister {
    Float account(List<Denomination> denoms) {
      float totalAmount = 0.0f;
      for (Denomination denomination : denoms) {
        totalAmount += denomination.amount();
      }

      return totalAmount;
    }

    Float getBalance(Float totalProducts, Float payment) {
      return payment - totalProducts;
    }

    void checkBalance(Float balance) throws InsuffisantMoneyException {
      if(balance <0.0f) {
        throw new InsuffisantMoneyException();
      }
    }

    Float makePayment(Float totalProducts, Float payment) throws InsuffisantMoneyException {
        Float balance = getBalance(totalProducts, payment);
        checkBalance(balance);
        return balance;
    }
}
