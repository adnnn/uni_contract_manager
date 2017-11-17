package com.company;

public class PersonalContract extends Contract implements ContractInterface {

  /**
   * Set the correct percentage of discount.
   */
  @Override
  public void setDiscount() {
    switch (this.getContractDuration()) {
      case 12:
      case 18:
        this.discount = 5;
        break;
      case 24:
        this.discount = 10;
        break;
      default:
        this.discount = 0;
    }
  }
}
