
import java.util.Scanner;

/**
 * Assignment 1
 * For COMP  249 Section D - Fall 2022
 * 
 * Software for managing appliance store inventory. The user is able to add, view, and edit appliances in order to keep track
 * of inventory in their store.
 * 
 * Static methods (integerCheck, longCheck, doubleCheck, typeCheck) are used in order to check that the user has entered valid data types.
 * For these methods a scanner object is passed in order to only use one scanner object throughout the program, as using multiple can
 * easily create errors. These methods loop until the user enters a valid data type, allowing for incorrect data type entry errors
 * to be handled.
 * 
 * Two menu functions also exist (menuOptions and editMenuOptions) which are controlled using static methods that take
 * a scanner object as a parameter, which makes the driver much more readable.
 * 
 * The menu codes are within a do while loop and use the various static methods in order to control their flow. In line comments
 * are included to aid with following the logic as code 1 and code 2 still have complex structure even with the help of the
 * static methods.
 * 
 * @author Linden Wheeler 40195748 and Matej Pederson 40209550
 * @version 1.35
 */
class Appliance {
    private String type;
    private String brand;
    private long serialNumber;
    static long SNCtr = 1000000;
    static int numOfAppliances = 0;
    private double price;

    /**
     * Default constructor.
     */
    public Appliance(){
        type = "unknown";
        brand = "unknown";
        serialNumber = SNCtr;
        SNCtr++;
        numOfAppliances++;
        price = 1.0;    // minimum price is $1
    }

    /**
     * Constructor that takes parameters.
     * 
     * @param type type of appliance
     * @param brand brand of appliance
     * @param price price of appliance
     */
    public Appliance(String type, String brand, double price){     
        this.type = type;
        this.brand = brand;
        this.price = (price >= 1) ? price : 1; // if price is less than $1, price is equal to $1
        serialNumber = SNCtr;
        SNCtr++;
        numOfAppliances++;
    } 

    /**
     * Returns type of appliance.
     * 
     * @return string type of appliance
     */
    public String getType(){
        return type;
    }

    /**
     * Sets type of appliance.
     * 
     * @param type of appliance
     */
    public void setType(String type){
        this.type = type;
    }
    
    /**
     * Returns brand of appliance.
     * 
     * @return string brand of appliance
     */
    public String getBrand(){
        return brand;
    }

    /**
     * Sets brand of appliance.
     * 
     * @param brand of appliance
     */
    public void setBrand(String brand){
        this.brand = brand;
    }

    /**
     * Returns serial number of appliance.
     * 
     * @return long serial number of appliance
     */
    public long getSerialNumber(){
        return serialNumber;
    }

    /**
     * Sets serial number of appliance.
     * 
     * @param serialNumber serial number of appliance
     */
    public void setSerialNumber(long serialNumber){
        this.serialNumber = serialNumber;
    }

    /**
     * Returns price of appliance.
     * 
     * @return double price of appliance
     */
    public double getPrice(){
        return price;
    }

    /**
     * Sets price of appliance.
     * 
     * @param price of appliance
     */
    public void setPrice(double price){ 
        this.price = (price >= 1) ? price : 1;	//checking if price is greater than one
    }

    /**
     * Allows object info to be printed out
     * 
     * @return string of information about appliance
     */
    public String toString()
    {
        return "Type: " + type + "\nBrand: " + brand + "\nSerial Number: " + serialNumber + "\nPrice: " + price;
    }

    /**
     * Returns number of appliances created.
     * 
     * @return integer number of appliances
     */
    public static int findNumberOfCreatedAppliances(){
        return numOfAppliances;
    }

    /**
     * Checks if two appliance objects have the same type, brand, and price.
     * 
     * @param other appliance to be compared
     * @return boolean true if appliances are equal, false if appliances have different attributes
     */
    public boolean equals(Appliance other)
    {
        return (this.type.equals(other.type) && this.brand.equals(other.brand) && this.price == other.price && this.serialNumber == other.serialNumber);
    }
    
    
    public static void main(String[] args) { 
        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome to the fantastic appliance tracking software!");
        System.out.println("What is the maximum amount of appliances that your store can contain, or the max amount that you would like to aquire?");
        int maxAppliances = integerCheckPos(userInput);	// go check out integerCheck()
        Appliance[] inventory = new Appliance[maxAppliances];
        
        final String password = "c249";
        int totalAttempts = 0;    // for the total password attempts (max is 12)
        int triedAttempts = 0; // for the password attempts in a row (max is 3)

        int code;
        do 
        {   // Loops showing menu options until user enters a valid menu code
            code = menuOptions(userInput);

            if (code == 1)
            {
                while (triedAttempts < 3){	// loops three times to give the user three chances to enter correct password
                    System.out.println("Please enter password to add a new appliance: ");
                    String enteredPassword = userInput.nextLine();
                    if (enteredPassword.equals(password)){
                        System.out.println("How many appliances do you want?");
                        int appliancesToAdd = integerCheck(userInput);	// valid integer check
                        if (findNumberOfCreatedAppliances() + appliancesToAdd <= maxAppliances){    // user cannot add more appliances than the space left
                            for (int i = 1; i <= appliancesToAdd; i++){	// loops and adds the requested number of appliances
                                System.out.println();
                                System.out.println("Adding appliance " + i);
                                while (true) {	// Loops until user enters a valid type
                                    
                                	System.out.print("Please enter appliance type: ");
                                    String enteredType = typeCheck(userInput);
                                    
                                    //String newType = typeCheck(enteredType, userInput);
                                    //System.out.println(newType);
                                    System.out.print("Please enter appliance brand: ");
                                    String enteredBrand = userInput.nextLine();
                                    System.out.print("Please enter appliance price: ");
                                    double enteredPrice = doubleCheck(userInput);	// valid double check
                                    inventory[findNumberOfCreatedAppliances()] = new Appliance(enteredType, enteredBrand, enteredPrice);
                                    System.out.println();
                                    break;
	                                
	                                
                                }
                            }
                        }
                        else
                        {
                            System.out.println("There are " + (maxAppliances - findNumberOfCreatedAppliances()) + " spaces left, cannot add " + appliancesToAdd + " appliances");
                        }
                        
                        triedAttempts = 0;	// both password attempts are reset if the user correctly enters the pass-code
                        totalAttempts = 0;
                        break; // to break out of password check loop
                    } 
                    else
                    {
                        System.out.println("Wrong password");
                        triedAttempts++;	// increment both attempts
                        totalAttempts++; 
                    } 
                }	// PASSWORD LOOP
                
                triedAttempts = 0;	// reset the tried attempts but leave the total attempts since after 12 wrong total attempts the system is suspended
                if (totalAttempts == 12)
                {

                    System.out.println("\nProgram detected suspicious activities and will terminate immediately!");

                    System.exit(0);	// suspends program
                }
                System.out.println();
            }	// CODE 1

            
            else if(code == 2)
            {
                long enteredSN;
                while(triedAttempts < 3)
                {
                    System.out.println("Please enter password to edit an appliance: ");
                    String enteredPassword = userInput.nextLine();
                    if (enteredPassword.equals(password)){ //password checking
                        boolean updatingAppliance = true;
                        while (updatingAppliance)//loops through this until user enters 4 to quit
                        {
                            System.out.println("Please enter the serial number of the appliance you would like to edit:");
                            
                            enteredSN = longCheck(userInput);
                            if (findAppliancesBySerialNumber(enteredSN, inventory) != null)//if the serial number exists
                            {
                                System.out.println();
                            	Appliance chosenAppliance = findAppliancesBySerialNumber(enteredSN, inventory);
                            	System.out.println("Appliance Serial # " + chosenAppliance.getSerialNumber()
                                        + "\nBrand: " + chosenAppliance.getBrand()
                                        + "\nType: " + chosenAppliance.getType()
                                        + "\nPrice: " + chosenAppliance.getPrice());
                                int editCode = editMenuOptions(userInput);
                                if(editCode == 4)//quits back to main menu
                                {
                                    break;
                                }
                                findAppliancesBySerialNumber(enteredSN, inventory).editAppliance(editCode, userInput);//calls the editing method
                            }
                            else//if SN does not exist, prompts to try again or go to main menu
                            {
                                System.out.println("Serial number does not match any appliance.");
                                System.out.println("If you would like to try another serial number, enter y below. " +
                                "Otherwise you will be taken back to the main menu");
                                String response = userInput.nextLine();
                                if (!(response.equals("y")))
                                {
                                    System.out.println();
                                    break;
                                }
                                System.out.println();
                            }
                        }
                        triedAttempts = 0;
                        break;
                    }
                    else
                    {
                        System.out.println("Wrong password.");
                        triedAttempts++;
                    }
                    
                }

                triedAttempts = 0;
            }	// CODE 2
            
            
            else if(code == 3)
            {
                System.out.print("Please enter a brand name: ");
                
                String brand = userInput.nextLine();
            	findAppliancesBy(brand, inventory);	// all the work is in the static method
            }
            
            else if(code == 4)
            {
                System.out.print("Please enter a price: ");
            	int price = integerCheck(userInput); // valid integer check
            	findCheaperThan(price, inventory);	// all the work is in the static method
            }
            
        }
        while (code != 5);  // if user enters code 5, the program stops
        System.out.println("Thanks for using the Appliance manager software!");
        userInput.close();
    }   // MAIN
    
    
    /**
     * Loops until the user enters a valid code, redisplaying the main menu each time.
     * 
     * @param input Scanner object used in driver
     * @return	valid integer code from 1 to 5
     */
    public static int menuOptions(Scanner input){
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("\t1.\tEnter new appliances");
            System.out.println("\t2.\tChange information of an appliance (password required)");
            System.out.println("\t3.\tDisplay all appliances by a specific brand");
            System.out.println("\t4.\tDisplay all appliances under a certain price.");
            System.out.println("\t5.\tQuit");
            System.out.println("Please enter your choice>"); 
            
	        int inputNum = integerCheck(input);	// to prevent errors if the user enters a non integer value
            if (inputNum > 0 && inputNum <= 5){
                return inputNum;
            }
            else{
                System.out.println("Please enter an valid code\n");
            }
            System.out.println();
        }
    }
    
    /**
     * Loop through given inventory array and prints appliance info if appliance brand matches input brand.
     * 
     * @param brand String of brand that is being compared to inventory
     * @param inventory	Appliance array
     */
    public static void findAppliancesBy(String brand, Appliance[] inventory) {
    	int totalAppliances = 0;	// checks if any appliances match passed brand
        System.out.println("Here are all the Appliances of the brand '" + brand + "':");
    	for (Appliance appliance : inventory) {	// 
    		if (appliance != null) {	// checks that an appliance object actually exists at current index
                if (appliance.getBrand().equals(brand)) {
        			System.out.println(appliance);
                    totalAppliances++;
                }
            }
        }
        
        if (totalAppliances == 0) {
            System.out.println("No appliances found with brand '" + brand + "'.");
        }
        System.out.println();
    }
    
    /**
     * Checks if passed serial number matches a serial number in passed Appliance array.
     * 
     * @param enteredNum
     * @param inventory
     * @return	Appliance matching passed serial number or null
     */
    public static Appliance findAppliancesBySerialNumber(long enteredNum, Appliance[] inventory)
    {
        for(Appliance appliance : inventory)
        {
            if (appliance != null){
                if(appliance.getSerialNumber() == enteredNum)
                {
                    return appliance;
                }
            }
        }
        return null;	// No appliance match found
    }
    
    /**
     * Prints out appliance info from appliance in passed Appliance array if it's price is lower than passed price.
     * 
     * @param price	integer value
     * @param inventory	Appliance array
     */
    public static void findCheaperThan(int price, Appliance[] inventory) {
    	int totalAppliances = 0;	// checks if any appliances are cheaper than passed price
        System.out.println("Here are all the appliances cheaper than $" + price + ":");
    	for (Appliance appliance : inventory) {
    		if(appliance != null) {	// verify appliance object actually exists at current index
	    		if (appliance.getPrice() < price) {
                    System.out.println(appliance);
                    totalAppliances++;
                }
            }
        }
        if(totalAppliances == 0) {
            System.out.println("No appliances found cheaper than $" + price + ".");
        }
        System.out.println();
    }
    
    /**
     * Loops until user enters a valid integer value.
     * 
     * @param input Scanner object from driver
     * @return	valid integer value
     */
    public static int integerCheck(Scanner input) {
    	while (!(input.hasNextInt())) {
    		input.nextLine();	// stores invalid value in garbage variable
            System.out.print("Please enter an integer value: ");
        }	
        int intOut = input.nextInt();
        input.nextLine();	// clears buffer
    	return intOut;
    }

    /**
     * Loops until user enters a valid integer value that is positive.
     * 
     * @param input Scanner object from driver
     * @return	valid integer value
     */
    public static int integerCheckPos(Scanner input) {
    	while (!(input.hasNextInt())) {
    		input.nextLine();	// stores invalid value in garbage variable
            System.out.print("Please enter an integer value: ");
        }	
        int intOut = input.nextInt();
        input.nextLine();	// clears buffer
        if (intOut < 0){
            intOut = 0;
        }
    	return intOut;
    }
    
    /**
     * Loops until user enters a valid double value.
     * 
     * @param input Scanner object from driver
     * @return	valid double value
     */
    public static double doubleCheck(Scanner input) {	
    	while (!(input.hasNextDouble())) {
            input.nextLine();	// stores invalid value in garbage variable
            System.out.print("Please enter an double value: ");
        }
        double doubleOut = input.nextDouble();
        input.nextLine();	// clears buffer
    	return doubleOut;
    }
    
    /**
     * Loops until user enters a valid long value.
     * 
     * @param input Scanner object from driver
     * @return	valid long value
     */
    public static long longCheck(Scanner input)
    {
        while(!(input.hasNextLong()))
        {
            input.nextLine();	// stores invalid value in garbage variable
            System.out.println("Please enter a long value: ");
        }
        long longOut = input.nextLong();
        input.nextLine();	// clears buffer
        return longOut;
    }
    
    /**
     * loops until a valid type is entered
     * 
     * @param enteredType String type of appliance
     * @param input Scanner object from driver
     * @return	boolean value (true if entered appliance type is a valid type)
     */
    public static String typeCheck(Scanner input)
    {
        boolean running = true;
        while(running){ //loops until a valid type is entered
            String enteredType = input.nextLine();
            String[] types = {"Fridge", "Air Conditioner", "Washer", "Dryer",
            "Freezer", "Stove", "Dishwasher", "Water Heaters", "Microwave"}; //acceptable appliances
            for(int n = 0; n<9; n++)
            {
                if(types[n].equals(enteredType))
                {
                    return enteredType;
                }
            }
            System.out.println("Please enter a valid type (Fridge, Air Conditioner, Washer, Dryer, "
            		+ "Freezer, Stove, Dishwasher, Water Heaters, Microwave): ");
        }
        return "";
            
    }
    
    /**
     * Loops until the user enters a valid code, redisplaying the edit menu each time.
     * 
     * @param input Scanner from driver
     * @return valid integer edit menu code
     * @see integerCheck()
     */
    public static int editMenuOptions(Scanner input)
    {
        while (true) 
        {
            System.out.println("What information would you like to change?");
            System.out.println("\t1.\tBrand");
            System.out.println("\t2.\tType");
            System.out.println("\t3.\tPrice");
            System.out.println("\t4.\tQuit");
            System.out.println("Please enter your choice>"); 
            
            int inputNum = integerCheck(input);	// to prevent errors if the user enters a non integer value
            if (inputNum > 0 && inputNum <= 4)
            {
                return inputNum;
            }
            else
            {
                System.out.println("Please enter an valid code\n");
            }
            System.out.println();
        }
        
    }

    /**
     * Handles each option from editMenuOptions(), lets user edit their appliance
     * 
     * 
     * @param code int to determine which attribute they will edit
     * @param input Scanner from driver
     * @see typeCheck
     * @see doubleCheck
     * 
     */
    public void editAppliance(int code, Scanner input)
    {
                switch(code)
                {
                    case 1: //brand

                        System.out.println("Please enter the new brand: ");
                        String newBrand = input.nextLine();
                        this.setBrand(newBrand);
                        System.out.println();
                        System.out.println("Updated info for this applaince:");
                        System.out.println(this);
                        break;

                    case 2: //type
                        System.out.println("Please enter the new type: ");
                        String newType = typeCheck(input);
                        this.setType(newType);
                        System.out.println();
                        System.out.println("Updated info for this appliance:");
                        System.out.println(this);
                        break;   
                        

                    case 3: //price
                        System.out.println("Please enter the new price: ");
                        
                        double newPrice = doubleCheck(input);
                        this.setPrice(newPrice);
                        System.out.println();
                        System.out.println("Updated info for this appliance:");
                        System.out.println(this);
                        break;

                    case 4: //quit
                        break;       
                }
                System.out.println();
    }
    

}   // CLASS

