package com.company;

public class PersonalContract extends Contract implements ContractInterface {

  /**
   * Define discount that is available for this type of contract.
   * Contract duration is available for dynamically calculating discounts.
   *
   * @param duration integer
   */
  @Override
  public void setDiscount(Integer duration) {
    switch (duration) {
      case 12:
      case 18:
        discount = 5;
        break;
      case 24:
        discount = 10;
        break;
      default:
        discount = 0;
    }
  }
}
