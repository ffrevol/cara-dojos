package org.cara.dojo;


public enum Denomination {
  PENNY(0.01f), QUARTER(0.25f), TWENTY(20f);

  private Float amount;

  private Denomination(Float amount){
    this.amount = amount;

  }

  public Float amount() {
    return amount;
  }

}
