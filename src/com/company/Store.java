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
      FileWriter fw = new FileWriter(new File("contracts.txt"), true);
      PrintWriter pw = new PrintWriter(fw);

      pw.append(contract.getDate() + "\t");
      pw.append(contract.getPackageType() + "\t");
      pw.append(contract.getDataBundle() + "\t");
      pw.append(contract.getContractDuration() + "\t");
      pw.append(contract.getInternational() + "\t");
      pw.append(contract.getReference() + "\t");
      pw.append(contract.getFinalPrice() + "\t");
      pw.append(contract.getName() + "\t");
      pw.println();
      pw.close();

    } catch (IOException e) {
      System.out.println("Error Printing Tab Delimited File");
    }
  }
}