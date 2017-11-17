package com.company;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import org.jetbrains.annotations.NotNull;

public class ContractManager {

  private Integer menuChoice = null;

  public void execute() {
    do {
      this.showMenu();
      this.getMenuChoice(new Scanner(System.in));
    } while (menuChoice == null);

    switch (this.menuChoice) {
      case 1:
        this.createNewContract();
        break;
      case 2:
        this.summaryOfContracts();
        break;
      case 3:
        this.monthlySummaryOfContracts();
        break;
      case 4:
        this.findContract();
        break;
      case 0:
        this.killContractManger();
    }


  }

  private void killContractManger() {
    System.out.println("Thanks for stopping by!");
    System.exit(1);
  }

  private void findContract() {
    System.out.println("Please enter a contract identifier: ");
  }

  private void monthlySummaryOfContracts() {
    System.out.println("Please enter the month (1-12) you would like to view all contracts for: ");
  }

  /**
   * Not advisable from what I've read? Does Map<String, Either<String, Integer>> work?! Maybe not
   * cos there's a character field?!
   */
  private void createNewContract() {
    System.out.println("Okay, let's create a new contract.");
    Map<String, Object> contract = new HashMap<>();

    contract.put("name", this.getValidName(new Scanner(System.in)));
    contract.put("package", this.getPackage(new Scanner(System.in)));
    contract.put("data", this.getDataBundle(new Scanner(System.in)));
    contract.put("period", this.getContractPeriod(new Scanner(System.in)));
  }

  private Integer getContractPeriod(Scanner scanner) {
    System.out.println("Please enter the desired contract period");
    System.out.println("1: One Month");
    System.out.println("2: Twelve Months");
    System.out.println("3: Eighteen Months");
    System.out.println("4: Twenty-Four Months");

    Integer choice = 0;

    try {
      choice = scanner.nextInt();
    } catch (InputMismatchException e) {
      this.invalidInputMessage();
      this.getContractPeriod(new Scanner(System.in));
    }

    int months = 0;

    switch (choice) {
      case 1:
        months = 1;
        break;
      case 2:
        months = 12;
        break;
      case 3:
        months = 18;
        break;
      case 4:
        months = 24;
        break;
      default:
        this.invalidInputMessage();
        this.getContractPeriod(new Scanner(System.in));
        break;
    }

    return months;
  }

  private Integer getDataBundle(Scanner scanner) {
    System.out.println("Please enter the data bundle you desire:");
    System.out.println("1: Low");
    System.out.println("2: Medium");
    System.out.println("3: High");
    System.out.println("4: Unlimited");

    Integer choice = 0;

    try {
      choice = scanner.nextInt();

    } catch (InputMismatchException e) {
      this.invalidInputMessage();
      this.getDataBundle(new Scanner(System.in));
    }

    if (choice < 1 || choice > 4) {
      this.invalidInputMessage();
      this.getDataBundle(new Scanner(System.in));
    }

    return choice;
  }

  private void invalidInputMessage() {
    System.out.println("Sorry, the choice you made was not valid: Please try again.");
  }

  private Integer getPackage(Scanner scanner) {
    System.out.println("Please Enter Your Package Choice:");
    System.out.println("1: Small");
    System.out.println("2: Medium");
    System.out.println("3: Large");

    Integer choice = 0;

    try {
      choice = scanner.nextInt();

      if (choice < 1 || choice > 3) {
        this.invalidInputMessage();
        this.getPackage(new Scanner(System.in));
      }
    } catch (InputMismatchException e) {
      this.invalidInputMessage();
      this.getPackage(new Scanner(System.in));
    }
    return choice;
  }

  private String getValidName(Scanner scanner) {
    System.out.println("Please enter the full name of your customer: ");
    String name = this.formatName(scanner.nextLine());

    if (name.length() > 25) {
      this.invalidInputMessage();
      this.getValidName(new Scanner(System.in));
    }

    return name;
  }

  @NotNull
  private String formatName(String enteredName) {
    String name = "";

    try {
      String[] parts = enteredName.split(" ");
      name = parts[0].charAt(0) + " " + parts[1];
    } catch (ArrayIndexOutOfBoundsException e) {
      this.invalidInputMessage();
      this.getValidName(new Scanner(System.in));
    }

    return name;
  }

  private void summaryOfContracts() {
    System.out.println("A summary of all contracts");
  }

  private void getMenuChoice(Scanner scanner) {
    this.menuChoice = scanner.nextInt();

    if (this.menuChoice < 0 || this.menuChoice > 4) {
      System.out
          .println("Sorry, that was an invalid choice. Please review the menu and choose again.");
      System.out.println();
      this.showMenu();
      this.getMenuChoice(scanner);
    }
  }

  private void showMenu() {
    System.out.println("1. Enter A New Contract");
    System.out.println("2. Display Summary of Contracts");
    System.out.println("3. Display Summary of Contracts for a Specific Month");
    System.out.println("4. Find and Display Contract");
    System.out.println("0. Exit");
  }
}
