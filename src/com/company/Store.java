package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Store {

  /**
   * @TODO Save to TSV
   */
  public void save(Contract contract) {
    try {
      FileWriter fw = new FileWriter(new File("./data/contracts.txt"));
      PrintWriter pw = new PrintWriter(fw);

      pw.print(contract.getDate() + "\t");
      pw.print(contract.getPackageType() + "\t");
      pw.print(contract.getDataBundle() + "\t");
      pw.print(contract.getContractDuration() + "\t");
      pw.print(contract.getInternational() + "\t");
      pw.print(contract.getReference() + "\t");
      pw.print(contract.getFinalPrice() + "\t");
      pw.print(contract.getName() + "\t");
      pw.println();

    } catch (IOException e) {
      System.out.println("Error Printing Tab Delimited File");
    }
  }
}