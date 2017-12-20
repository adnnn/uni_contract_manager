package com.company;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Contract {

  private String name;
  private Integer packageType;
  private Integer dataBundle;
  private Integer contractDuration;
  private Character international;
  private String reference;
  private Integer basePrice;
  private String date;
  private Integer finalPrice;
  Integer discount;

  private int[][] prices = {
      {500, 700, 900, 0},
      {650, 850, 1050, 0},
      {850, 1050, 1250, 2000}
  };

  Contract() {
    setDate(getCurrentDate());
  }

  private String getCurrentDate() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    return sdf.format(cal.getTime());
  }

  public abstract void setDiscount(Integer duration);

  String getName() {
    return this.name;
  }

  void setName(String name) {
    this.name = name;
  }

  Integer getPackageType() {
    return packageType;
  }

  void setPackageType(Integer packageType) {
    this.packageType = packageType;
  }

  Integer getDataBundle() {
    return dataBundle;
  }

  void setDataBundle(Integer dataBundle) {
    this.dataBundle = dataBundle;
  }

  Integer getContractDuration() {
    return contractDuration;
  }

  void setContractDuration(Integer contractDuration) {
    this.contractDuration = contractDuration;

    this.setDiscount(contractDuration);
  }

  Character getInternational() {
    return international;
  }

  void setInternational(Character international) {
    this.international = international;
  }

  String getReference() {
    return reference;
  }

  Integer getBasePrice() {
    return basePrice;
  }

  private void setBasePrice(Integer basePrice) {
    this.basePrice = basePrice;
  }

  String getDate() {
    return date;
  }

  private void setDate(String date) {
    this.date = date;
  }

  Integer getDiscount() {
    return discount;
  }

  void calculatePrice() {
    setBasePrice(determineBasePrice());

    Integer discount = (this.getBasePrice() / 100) * this.getDiscount();
    this.setFinalPrice(this.getBasePrice() - discount);
  }

  private Integer determineBasePrice() {
    int base = this.prices[this.getPackageType() - 1][this.getDataBundle() - 1];

    if (hasInternational()) {
      // Add 15% to existing base price
      base += (base / 100) * 15;
    }

    return base;
  }

  private boolean hasInternational() {
    return getInternational() == 'Y';
  }

  Integer getFinalPrice() {
    return finalPrice;
  }

  private void setFinalPrice(Integer finalPrice) {
    this.finalPrice = finalPrice;
  }

  /**
   * @TODO Save to TSV
   */
  void save() {
    Store store = new Store();
    store.save(this);
  }

  boolean hasDiscount() {
    return discount > 0;
  }

  public void setReference(String refernce) {
    this.reference = refernce;
  }
}
