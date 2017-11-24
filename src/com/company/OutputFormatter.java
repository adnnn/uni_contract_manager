package com.company;

class OutputFormatter {

  static String discount(Integer discount) {
    switch (discount) {
      case 0:
        return "None";
      default:
        return discount.toString() + "%";
    }
  }

  static String international(char s) {
    if (s == 'Y') {
      return "Yes";
    }

    return "No";
  }

  static String period(Integer contractDuration) {
    if (contractDuration == 1) {
      return contractDuration.toString() + " Month";
    }
    return contractDuration.toString() + " Months";
  }

  static String type(Contract contract) {
    if (contract instanceof BusinessContract) {
      return "Business";
    }

    return "Non-Business";
  }

  static String dataBundle(Integer dataBundle) {
    switch (dataBundle) {
      case 1:
        return "Low (1 GB)";
      case 2:
        return "Medium (4GB)";
      case 3:
        return "High (8GB)";
      default:
        return "Unlimited";
    }
  }

  static String packageType(Integer packageType) {
    switch (packageType) {
      case 1:
        return "Small (300)";
      case 2:
        return "Medium (600)";
      default:
        return "Large (1200)";
    }
  }

  static String date(String date) {
    return date.replace("-", " ");
  }

  static String monthlyFeeMessage(Contract contract) {

    if (contract.hasDiscount()) {
      return "Discounted Monthly Charge";
    }

    return "Monthly Charge";
  }

  static float monthlyFee(Contract contract) {
    return contract.getFinalPrice() / 100;
  }
}
