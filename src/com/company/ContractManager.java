package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.jetbrains.annotations.NotNull;

class ContractManager {

  private Integer menuChoice = null;

  /**
   * Main - Starts here.
   */
  public static void main(String[] args) {
    ContractManager instance = createInstance();
    instance.execute();
  }

  /**
   * Creates a dynamic instance of ContractManager.
   *
   * @return ContractManager
   */
  private static ContractManager createInstance() {
    return new ContractManager();
  }

  /**
   * Executes the main menu and responds to the users choice.
   */
  private void execute() {
    do {
      this.showMenu();
      this.getMenuChoice(new Scanner(System.in));
    } while (this.menuChoice == null);

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

  /**
   * Kills the program should the user select it from the menu.
   */
  private void killContractManger() {
    System.out.println("Thanks for stopping by!");
    System.exit(1);
  }

  /**
   * @todo Find a specific contract and display its overview.
   */
  private void findContract() {
    System.out.println("Please enter a contract identifier: ");
  }

  /**
   * @todo Summary of contracts for a given month.
   */
  private void monthlySummaryOfContracts() {
    System.out.println("Please enter the month (1-12) you would like to view all contracts for: ");
  }

  /**
   * Starts the process for creating a new contract.
   */
  private void createNewContract() {
    System.out.println("Okay, let's create a new contract.");

    Contract contract = this.createContractClass(this.getContractType(new Scanner(System.in)));

    contract.setName(this.getValidName(new Scanner(System.in)));
    contract.setPackageType(this.getPackage(new Scanner(System.in)));
    contract.setDataBundle(this.getDataBundle(new Scanner(System.in)));
    contract.setContractDuration(this.getContractPeriod(new Scanner(System.in)));
    contract.setInternational(this.getInternational(new Scanner(System.in)));
    contract.calculatePrice();

    this.confirmContract(contract);

    contract.save();
  }

  /**
   * Displays overview of contract and confirms whether the details are correct prior to saving to a
   * file.
   */
  private void confirmContract(Contract contract) {

    this.displayContractOverview(contract);

    System.out.println("Please confirm that the information above is accurate:");
    System.out.println("1: Yes, everything is accurate.");
    System.out.println("2: No, something is wrong. Start again.");

    Scanner scanner = new Scanner(System.in);

    int choice = 0;

    try {
      choice = scanner.nextInt();
    } catch (InputMismatchException e) {
      this.invalidInputMessage();
      this.confirmContract(contract);
    }

    switch (choice) {
      case 1:
        contract.save();
        break;
      case 2:
        this.createNewContract();
        break;
      default:
        this.invalidInputMessage();
        this.confirmContract(contract);
    }

    this.resetContractManager();
  }

  /**
   * Displays the contract overview.
   *
   * @param contract A contract object
   * @see com.company.Contract A Contract object
   */
  private void displayContractOverview(Contract contract) {
    printBorderTop();
    System.out.printf("| Customer: %-25s         |", contract.getName());
    System.out.println();
    System.out.printf("|      Ref: %-6s", contract.getReference());
    System.out.printf("%s |", "Date: " + contract.getDate());
    System.out.println();
  }

  /**
   * Prints top top and bottom borders for the console output.
   */
  private void printBorderTop() {
    System.out.print("+");
    for (int i = 0; i < 45; i++) {
      System.out.print("-");
    }
    System.out.print("+");
    System.out.println();
  }

  /**
   * Reset the ContractManager application.
   */
  private void resetContractManager() {
    this.menuChoice = null;
    this.execute();
  }

  /**
   * Ask user whether they want international calls, retrieves and validates input from user.
   *
   * @param scanner Scanner Object
   * @return Character - Y for Yes and N for No.
   * @see Scanner Scanner Object
   * @see Character Character
   */
  private Character getInternational(Scanner scanner) {
    System.out.println("Would you like to allow international calls?");
    System.out.println("1: Yes");
    System.out.println("2: No");

    int choice = 0;
    Character decision = null;

    try {
      choice = scanner.nextInt();
    } catch (InputMismatchException e) {
      this.invalidInputMessage();
      this.getInternational(new Scanner(System.in));
    }

    switch (choice) {
      case 1:
        decision = 'Y';
        break;
      case 2:
        decision = 'N';
        break;
      default:
        this.invalidInputMessage();
        this.getInternational(new Scanner(System.in));
    }

    return decision;
  }

  /**
   * Instantiate a contract based upon the type selected by the user.
   *
   * @param contractType Char as returned from {@link #getContractType(Scanner)}
   * ContractManager.getContractType();}
   * @return Contract A subclass of Contract based upon the type selected.
   * @see com.company.Contract Contract Class [Abstract]
   * @see com.company.BusinessContract BusinessContract Class [Concrete]
   * @see com.company.PersonalContract PersonalContract Class [Concrete]
   */
  private Contract createContractClass(Character contractType) {
    if (contractType == 'P') {
      return new PersonalContract();
    }

    return new BusinessContract();
  }

  /**
   * Ask user to choose a contract type, retrieves and validates input.
   *
   * @param scanner Scanner
   * @return Character P for Personal Contract or B for Business Contract
   * @see Character Character
   * @see Scanner Scanner Class
   */
  private Character getContractType(Scanner scanner) {
    System.out.println("Please state the type of contract you are looking to create:");
    System.out.println("1: Personal Contract");
    System.out.println("2: Business Contract");

    int choice = 0;
    Character type = null;

    try {
      choice = scanner.nextInt();
    } catch (InputMismatchException e) {
      this.invalidInputMessage();
      this.getContractType(new Scanner(System.in));
    }

    switch (choice) {
      case 1:
        type = 'P';
        break;
      case 2:
        type = 'B';
        break;
      default:
        this.invalidInputMessage();
        this.getContractType(new Scanner(System.in));
    }

    return type;
  }

  /**
   * Ask user to specify their contract period, retrieves and validates input.
   *
   * @param scanner Scanner Class
   * @return Integer Denotes duration of contract in months [1, 12, 18 24]
   * @see Scanner Scanner Class
   * @see Integer Integer Class (note: not int the primitive data type)
   */
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

  /**
   * Asks user to choose a data bundle, retrieves input and validates it.
   *
   * @param scanner Scanner object
   * @return Integer Denotes data bundle [1 => low, 2 => medium, 3 => High, 4 => Unlimited]
   * @see Scanner Scanner Class
   * @see Integer Integer Class (note: not int the primitive data type)
   */
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

  /**
   * Generic error message: Invalid Input.
   */
  private void invalidInputMessage() {
    System.out.println("Sorry, the choice you made was not valid: Please try again.");
  }

  /**
   * Ask user to choose a package, retrieves and validates input.
   *
   * @param scanner Scanner object
   * @return Integer Denotes Package [1 => small, 2 => medium, 3 => large]
   * @see Scanner Scanner Class
   * @see Integer Integer Class (note: not int the primitive data type)
   */
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

  /**
   * Asks user for name, retrieves and validates input.
   *
   * @param scanner Scanner Object
   * @return String Correctly formatted to "A Clark" format.
   * @see Scanner Scanner Class
   * @see String String Class
   */
  private String getValidName(Scanner scanner) {
    System.out.println("Please enter the full name of your customer: ");
    String name = this.formatName(scanner.nextLine());

    if (name.length() > 25) {
      this.invalidInputMessage();
      this.getValidName(new Scanner(System.in));
    }

    return name;
  }

  /**
   * Formats the name provided by the user during {@link #getValidName(Scanner) getValidName()
   * method}.
   *
   * @param enteredName String representation of the name provided by the user.
   * @return name A correctly formatted String [Andrew Clark => A Clark]
   */
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

  /**
   * @Todo Return an overview of contracts in archive.txt
   */
  private void summaryOfContracts() {
    System.out.println("A summary of all contracts");
  }

  /**
   * Retrieves menu choice from the user and validates that it is a legitimate option.
   *
   * @param scanner Scanner Object
   * @see Scanner Scanner Class
   */
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

  /**
   * Display the main menu.
   */
  private void showMenu() {
    System.out.println("1. Enter A New Contract");
    System.out.println("2. Display Summary of Contracts");
    System.out.println("3. Display Summary of Contracts for a Specific Month");
    System.out.println("4. Find and Display Contract");
    System.out.println("0. Exit");
  }
}
