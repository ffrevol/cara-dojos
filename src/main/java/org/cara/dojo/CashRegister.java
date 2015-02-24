package org.cara.dojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CashRegister {
    private List<?> denoms;

    Float account(List<Denomination> denoms) {
      float totalAmount = 0.0f;
      for (Denomination denomination : denoms) {
        totalAmount += denomination.amount();
      }

      return totalAmount;
    }

    Float getPaymentBalance(Float totalProducts, Float payment) {
      return payment - totalProducts;
    }

    void checkBalanceToProceedWithPayment(Float balance) throws InsuffisantMoneyException {
      if(balance <0.0f) {
        throw new InsuffisantMoneyException();
      }
    }

    Float makePayment(Float totalProducts, Float payment) throws InsuffisantMoneyException {
        Float balance = getPaymentBalance(totalProducts, payment);
        checkBalanceToProceedWithPayment(balance);
        return balance;
    }

    public void init(List<Denomination> denoms) {
        this.denoms = denoms;
    }

    public List<?> getDenoms() {
        return denoms;
    }

    public List<Denomination> getChangeForPayment(Float totalRequestedAmountForProducts, List<Denomination> offeredPaymentDenoms) {
        Float dueChange = getPaymentBalance(totalRequestedAmountForProducts, account(offeredPaymentDenoms));
        ArrayList<Denomination> change = new ArrayList<Denomination>();
        for (Denomination offeredPaymentDenom : offeredPaymentDenoms) {
            if (dueChange - offeredPaymentDenom.amount() < 0.10f) {
                change.add(offeredPaymentDenom);
                break;
            }
        }
        return change;
    }
}
