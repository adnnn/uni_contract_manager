package com.company;

public class BusinessContract extends Contract implements ContractInterface {

  /**
   * Define discount that is available for this type of contract. Contract duration is available for
   * dynamically calculating discounts.
   *
   * @param duration integer
   */
  @Override
  public void setDiscount(Integer duration) {
    this.discount = 10;
  }
}
