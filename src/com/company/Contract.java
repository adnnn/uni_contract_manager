package com.company;

import java.util.Date;
import java.util.List;

public abstract class Contract {

  private String name;
  private Integer packageType;
  private Integer dataBundle;
  private Integer contractDuration;
  private Character international;
  private String reference;
  private Integer basePrice;
  private Date date;
  private Integer finalPrice;
  protected Integer discount;

  private int[][] prices = {
      {500, 700, 900, 0},
      {650, 850, 1050, 0},
      {850, 1050, 1250, 2000}
  };

  public Contract() {
    this.setDiscount();
  }

  public abstract void setDiscount();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPackageType() {
    return packageType;
  }

  public void setPackageType(Integer packageType) {
    this.packageType = packageType;
  }

  public Integer getDataBundle() {
    return dataBundle;
  }

  public void setDataBundle(Integer dataBundle) {
    this.dataBundle = dataBundle;
  }

  public Integer getContractDuration() {
    return contractDuration;
  }

  public void setContractPeriod(Integer contractPeriod) {
    this.contractDuration = contractPeriod;
  }

  public Character getInternational() {
    return international;
  }

  public void setInternational(Character international) {
    this.international = international;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public Integer getBasePrice() {
    return basePrice;
  }

  public void setBasePrice(Integer basePrice) {
    this.basePrice = basePrice;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Integer getDiscount() {
    return discount;
  }

  public Integer finalPrice() {
    Integer discount = (this.getBasePrice() / 100) * this.getDiscount();
    return this.getBasePrice() - discount;
  }

  public void calculatePrice() {
    setBasePrice(determineBasePrice());

    Integer discount = (this.getBasePrice() / 100) * this.getDiscount();
    this.setFinalPrice(this.getBasePrice() - discount);
  }

  private Integer determineBasePrice() {
    return this.prices[this.getPackageType() - 1][this.getDataBundle() - 1];
  }

  public Integer getFinalPrice() {
    return finalPrice;
  }

  public void setFinalPrice(Integer finalPrice) {
    this.finalPrice = finalPrice;
  }
}
