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
  protected Integer discount;

  private int[][] prices = {
      {500, 700, 900, 0},
      {650, 850, 1050, 0},
      {850, 1050, 1250, 2000}
  };

  protected Contract(Character contractType) {
    reference = generateReference(contractType);
    setDate(getCurrentDate());
  }

  private String generateReference(Character contractType) {
    String ref = "JB123";
    if (contractType == 'B') {
      return ref + "B";
    }

    return ref + "N";
  }

  private String getCurrentDate() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    return sdf.format(cal.getTime());
  }

  public abstract void setDiscount(Integer duration);

  public String getName() {
    return this.name;
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

  public void setContractDuration(Integer contractDuration) {
    this.contractDuration = contractDuration;

    this.setDiscount(contractDuration);
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

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
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

  public Integer getFinalPrice() {
    return finalPrice;
  }

  public void setFinalPrice(Integer finalPrice) {
    this.finalPrice = finalPrice;
  }

  /**
   * @TODO Save to TSV
   */
  public void save() {
    Store store = new Store();
    store.save(this);
  }

  public boolean hasDiscount() {
    return discount > 0;
  }
}
