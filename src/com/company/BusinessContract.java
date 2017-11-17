package com.company;

public class BusinessContract extends Contract implements ContractInterface {

  @Override
  public void setDiscount() {
    this.discount = 10;
  }
}
