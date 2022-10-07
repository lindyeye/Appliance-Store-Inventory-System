
import java.util.Scanner;

// test commit

/**
 * Assignment 1
 * For COMP  249 Section D - Fall 2022
 * 
 * 
 * 
 * @author Linden Wheeler 40195748 and Matej Pederson 40209550
 * @version 1.19
 */
class Appliance {
    private String type;
    private String brand;
    private long serialNumber;
    static long SNCtr = 1000000;
    static  int numOfAppliances = 0;
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
    public int findNumberOfCreatedAppliances(){
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
        int maxAppliances = integerCheck(userInput);	// go check out integerCheck()
        Appliance[] inventory = new Appliance[maxAppliances];
        
        final String password = "c249";
        int totalAttempts = 0;    // for the total password attempts (max is 12)
        int triedAttempts = 0; // for the password attempts in a row (max is 3)
        int inventoryCount = 0;	// keeping track of how many appliances have actually been added

        int code;
        do 
        {
            code = menuOptions(userInput);

            if (code == 1)
            {
                while (triedAttempts < 3){	// loops three times to give the user three chances to enter correct password
                    System.out.println("Please enter password to add a new appliance: ");
                    String enteredPassword = userInput.nextLine();	//changed to nextLine
                    if (enteredPassword.equals(password)){
                        System.out.println("How many appliances do you want?");
                        
                        int appliancesToAdd = integerCheck(userInput);	// makes sure that user passes a valid integer value
                        if (inventoryCount + appliancesToAdd <= maxAppliances)
                        {	// making sure that the user doesn't add more appliances than limit
                            for (int i = 1; i <= appliancesToAdd; i++)
                            {	// loops and adds the requested number of appliances
                                System.out.println();
                                System.out.println("Adding appliance " + i);
                                while (true) {	// Loops until user enters a valid type
                                	System.out.print("Please enter appliance type: ");
                                    String enteredType = userInput.nextLine();
	                                if (typeCheck(enteredType) == true) {
		                                System.out.print("Please enter appliance brand: ");
		                                String enteredBrand = userInput.nextLine();
		                                System.out.print("Please enter appliance price: ");
		                                double enteredPrice = doubleCheck(userInput);	// makes sure that user passes a valid double value
		
		                                inventory[numOfAppliances] = new Appliance(enteredType, enteredBrand, enteredPrice);
		                                // creates appliance object according to given info by user and places in the inventory array
		                                System.out.println();
		                                break;
	                                }
	                                
                                }
                            }
                        	inventoryCount += appliancesToAdd;	// adds number of appliances to inventory count
                        }
                        else
                        {
                            System.out.println("There are only " + (maxAppliances - inventoryCount) + " spaces left");
                        }
                        
                        triedAttempts = 0;	// password attempts are reset if the user correctly enters the pass-code
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
                
                triedAttempts = 0;	// reset the tried attempts but leave the total attempts since after 12 wrong attempts total the system is suspended
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
                    if (enteredPassword.equals(password)){
                        boolean updatingAppliance = true;
                        while (updatingAppliance)
                        {
                            System.out.println("Please enter the serial number of the appliance you would like to edit:");
                            
                            enteredSN = longCheck(userInput);
                            if (findAppliancesBySerialNumber(enteredSN, inventory) != null)
                            { 
                            	Appliance chosenAppliance = findAppliancesBySerialNumber(enteredSN, inventory);
                            	System.out.println("Appliance Serial # " + chosenAppliance.getSerialNumber()
                                        + "\nBrand: " + chosenAppliance.getBrand()
                                        + "\nType: " + chosenAppliance.getType()
                                        + "\nPrice: " + chosenAppliance.getPrice());
                                int editCode = editMenuOptions(userInput);
                                if(editCode == 4)
                                {
                                    break;
                                }
                                findAppliancesBySerialNumber(enteredSN, inventory).editAppliance(editCode, userInput);
                            }
                            else
                            {
                                System.out.println("Serial number does not match any appliance.");
                                System.out.println("If you would like to try another serial number, enter y below. " +
                                "Otherwise you will be taken back to the main menu");
                                String response = userInput.nextLine();	// changed to nextLine
                                if (!(response.equals("y")))
                                {
                                    break;
                                }
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
            	int price = integerCheck(userInput); // to verify integer is entered
            	findCheaperThan(price, inventory);	// all the work is in the static method
            }
            
        }
        while (code != 5);
        System.out.println("Thanks for using the Appliance manager software!");
        userInput.close();
    }   // MAIN
    
    
    /**
     * Loops until the user enters a valid code, redisplaying the menu each time.
     * 
     * @param input Scanner object used in driver
     * @return	valid integer code from 1 to 5
     */
    public static int menuOptions(Scanner input){
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("\t1.\tEnter new appliances");
            System.out.println("\t2.\tChange information of an appliance (password required)");
            System.out.println("\t3.\tDisplay all appliances of by a specific brand");
            System.out.println("\t4.\tDisplay all appliances under a certain price");
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
     * Verifies that the type of entered appliance is a valid type.
     * 
     * @param enteredType String type of appliance
     * @return	boolean value (true if entered appliance type is a valid type)
     */
    public static Boolean typeCheck(String enteredType)
    {
        String[] types = { "Fridge", "Air Conditioner", "Washer", "Dryer",
            "Freezer", "Stove", "Dishwasher", "Water Heaters", "Microwave"};
            for(int n = 0; n<8; n++)
            {
                if(types[n].equals(enteredType))
                {
                    return true;
                }
            }
            System.out.println("Please enter a valid type (Fridge, Air Conditioner, Washer, Dryer, "
            		+ "Freezer, Stove, Dishwasher, Water Heaters, Microwave): ");
            return false; 
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
     * 
     * 
     * @param code
     * @param input
     */
    public void editAppliance(int code, Scanner input)
    {
                switch(code)
                {
                    case 1:

                        System.out.println("Please enter the new brand: ");
                        String newBrand = input.nextLine();
                        this.setBrand(newBrand);
                        System.out.println("Updated info for this applaince:");
                        System.out.println(this);
                        break;

                    case 2:
                        System.out.println("Please enter the new type: ");
                        String newType = input.nextLine();
                        typeCheck(newType);
                        if(typeCheck(newType) == true)
                        {
                            this.setType(newType);
                            System.out.println("Updated info for this applaince:");
                            System.out.println(this);
                        }

                    case 3:
                        System.out.println("Please enter the new price: ");
                        
                        double newPrice = doubleCheck(input);
                        this.setPrice(newPrice);
                        System.out.println(this);
                    break;

                    case 4:
                        break;       
                }
                System.out.println();
    }
    

}   // CLASS

