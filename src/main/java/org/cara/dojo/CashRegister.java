package org.cara.dojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 */
public class CashRegister {
    private List<Denomination> denoms;

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

        if (dueChange > 0f) {
            for (Denomination offeredPaymentDenom : offeredPaymentDenoms) {
                if (dueChange - offeredPaymentDenom.amount() < 0.10f) {
                    change.add(offeredPaymentDenom);
                    break;
                }
            }
        }
        return change;
    }

    public List<Denomination> getChangeAsDenoms(Float dueChange) {

  	  ArrayList<Denomination> change = new ArrayList<Denomination>();

        if (dueChange > 0f) {
            for (Denomination cashDenom : this.denoms) {
                if (dueChange - cashDenom.amount() >= 0f) {
                    change.add(cashDenom);
                    dueChange -= cashDenom.amount();
                }
            }
        }
          return change;
    }

	public void sortCash() {
	 	Collections.sort(this.denoms, new Comparator<Denomination>() {
	        @Override
	        public int compare(Denomination  den1, Denomination  den2)
	        {

	            return Math.round(den2.amount()*100-den1.amount()*100);
	            
	        }
	    });
	}

}
