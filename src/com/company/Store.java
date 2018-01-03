package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
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
      return Files.readAllLines(Paths.get("./" + this.file))
          .stream()
          .map(Contract::hydrate)
          .collect(Collectors.toList());

    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  public List<Contract> getFromMonth(String month) {
    try {
      Pattern pattern = Pattern.compile("^[0-9]{2}-" + month + "-[0-9]{4}");

      return Files.lines(Paths.get("./" + this.file))
          .filter(pattern.asPredicate())
          .map(Contract::hydrate)
          .collect(Collectors.toList());

    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
  
  /**
   * Search the name and reference fields on all contracts.
   * 
   * @param query
   * @return List of contracts that partially or entirely match the search query.
   */
  public List<Contract> search(String query) {
      try {          
          return Files.lines(Paths.get("./" + this.file))
                  .map(Contract::hydrate)
                  .filter(
                          contract -> contract.getName().contains(query)
                          || contract.getReference().contains(query)
                  )
                  .collect(Collectors.toList());
      } catch (IOException e) {
          e.printStackTrace();
      }
      
      return null;
  }
}