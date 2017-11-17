package com.company;

public class ContractFactory {

  private Contract contract;

  public Contract make(Character contractType) {

    switch (contractType) {
      case 'P':
        contract = new PersonalContract();
        break;
      case 'B':
        contract = new BusinessContract();
        break;
    }

    return contract;
  }
}
