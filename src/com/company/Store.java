package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Store {

  private String file;

  public Store() {
    this.file = "contracts.txt";
  }

  public Store(String file) {
    if (file.equals("archive") || file.equals("contracts")) {
      this.file = file + ".txt";
    }
  }

  /**
   * @TODO Save to TSV
   */
  public void save(Contract contract) {
    try {
      FileWriter fw = new FileWriter(new File(file), true);
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

  public List<Contract> get() {
    try {
      List<Contract> contracts = Files.readAllLines(Paths.get("./" + this.file))
          .stream()
          .map(line -> Contract.hydrate(line))
          .collect(Collectors.toList());

      return contracts;
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
}