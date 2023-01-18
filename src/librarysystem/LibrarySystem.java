package librarysystem;

import java.util.Scanner;
import java.util.ArrayList;

public class LibrarySystem {
       
    //LOG-IN/REGISTER MENU
    static final int newAcc = 1; //CREATE AN ACCOUNT
    static final int logIn = 2; //LOG-IN TO ACCOUNT
    static final int exitMain = 0; //EXIT PROGRAM
    
    //LOGGED-IN MENU
    static final int inventory = 1; //DISPLAY INVENTORY
    static final int withdraw = 2; //WITHDRAW BOOK
    static final int deposit = 3; //DEPOSIT BOOK
    static final int checkOut = 4; //VIEW AMOUNT OF BOOKS IN ACCOUNT
    static final int exit = 0; //EXIT ACCOUNT
    
    static Scanner in = new Scanner(System.in);
    
    //************
    //MAIN METHOD*
    //************
    public static void main(String[] args) {
        Book[] books = new Book[10];
        ArrayList<account> acc = new ArrayList<>();   
        int choice = 0;

        //TEST ACCOUNTS
        account admin = new account("Admin", "ad", 1);
        acc.add(admin);
        account test = new account("abc", "def", 456);
        acc.add(test);

        //TEST BOOKS
        books[0] = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 3);
        books[1] = new Book("Moby Dick", "Herman Melville", 1851, 3);
        books[2] = new Book("The Odyssey", "Homer", 2004, 3);
        books[3] = new Book("The Entrant", "Joseph Martin", 2013, 3);
        books[4] = new Book("Harry Potter", "J. K. Rowling", 1997, 3);
        books[5] = new Book("The Giver", "Lois Lowry", 1993, 3);
        books[6] = new Book("Catching Fire", "Suzanne Collins", 2009, 3);
        books[7] = new Book("Hunger Games", "Suzanne Collins", 2008, 3);
        books[8] = new Book("Mocking Jay", "Suzanne Collins", 2010, 3);
        books[9] = new Book("Romeo and Juliet", "William Shakespeare", 1597, 3);
 
        do{
            choice = logMenu(choice);
        
            switch(choice){
                case(newAcc):{
                    createAcc(acc);
                    logIn(acc, books);
                    break;
                }
                case(logIn):{
                    logIn(acc, books);
                    break;
                }
                case(exitMain):{
                    System.out.println("Ending Program...");
                    break;
                }
            }
        }while(choice != exitMain);
}
    
    //*****************************
    //Display Main Menu*
    //*****************************
    public static int logMenu(int choice){
        System.out.println("\tWelcome to my Library");
        System.out.println("---------------------------------------");
        System.out.println(newAcc + ") Register");
        System.out.println(logIn + ") Log-In");
        System.out.println(exitMain + ") Exit");
          
        do{
            System.out.print("Enter choice: ");
            choice = in.nextInt();
            in.nextLine();
        }while(choice != logIn && choice != newAcc && choice != exitMain);
        
        System.out.println();
        return choice;
    }
    
    //*******************
    //Display Login Menu*
    //*******************
    public static int accountMenu(int choice){
        System.out.println("What would you like to do?");
        System.out.println(inventory + ") View Inventory");
        System.out.println(withdraw + ") Withdraw");
        System.out.println(deposit + ") Deposit");
        System.out.println(checkOut + ") Books Checked-Out");
        System.out.println(exit + ") Exit");
        
        do{
            System.out.print("Enter choice: ");
            choice = in.nextInt();
            in.nextLine();
        }while(choice != inventory && choice != withdraw && choice != deposit && choice != checkOut && choice != exit);
        
        System.out.println();    
        return choice;
    }
    
    //***************
    //Create Account*
    //***************
    public static void createAcc(ArrayList<account> acc){
        account obj;
        String first, last;
        int accNum;
        boolean found = false;
        
        do{
            System.out.print("Enter first and last name: ");
            first = in.next();
            last = in.next();
        
            System.out.print("Create an account number: ");
            accNum = in.nextInt();
                
            //found = checkAcc(first, last, accNum, acc);
        }while(checkAcc(first, last, accNum, acc));
        
        obj = new account(first, last, accNum);
        acc.add(obj);      
    }
    
    //******
    //Login*
    //******
    public static void logIn(ArrayList<account>acc, Book[] books){
        String fName, lName, title;
        int accNum, choice = 0;
        boolean found = false;        
        
        System.out.println("Log-In");
        System.out.print("Enter Name: ");
        fName = in.next();
        lName = in.next();
        
        System.out.print("Enter Account Number: ");
        accNum = in.nextInt();
        
        System.out.println(); 
        for(int i = 0; i < acc.size(); i++){
            if(acc.get(i).fName.equals(fName) && acc.get(i).lName.equals(lName) && acc.get(i).accountNumber == accNum){
                found = true;
                System.out.println("Hello, " + fName + " " + lName);
                
                
                do{
                    choice = accountMenu(choice);
        
                    switch(choice){
                        case(inventory):{
                            inventory(books);
                            break;
                        }
                        case(withdraw):{ 
                            System.out.print("Enter title of book: ");
                            title = in.nextLine();
                    
                            acc.get(i).withdraw(books, title);
                            break;
                        }
                        case(deposit):{
                            System.out.print("Enter title of book: ");
                            title = in.nextLine();
                            
                            acc.get(i).deposit(books, title);
                            break;
                        }
                        case(checkOut):{
                            acc.get(i).checkOutNum();
                            break;
                        }
                        case(exit):{
                            System.out.println("Logged out...");
                            System.out.println();
                            break;
                        }            
                    }
                }while(choice != exit);                
                break;//EXIT FOR LOOP
            }
        }
        
        if(found == false){
            System.out.println("Invalid Name or Account Number...\n");
        }    
        System.out.println("------------------------------------------------------------------\n");
    }
   
    //******************
    //Display Inventory*
    //******************
    public static void inventory(Book[] books){

        System.out.printf("%-25s %-25s %s %n", "Title" , "Author", "Available");
        System.out.println("------------------------------------------------------------------");
        for (Book book : books) {
            System.out.printf("%-25s %-25s \t%d", book.title, book.author + ", " + book.year, book.amount);
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------\n");
    }
    
    //****************
    //Confirm Account
    //****************
    public static boolean checkAcc(String first, String last, int accNum, ArrayList<account> acc){
        
        System.out.println();
        for(int i = 0; i < acc.size(); i++){
            if(acc.get(i).accountNumber == accNum){
                System.out.println("Account Number already exists...");
                System.out.println("------------------------------------------------------------------\n");
                return true;
            }
        }
        System.out.println("------------------------------------------------------------------\n");
        return false;
    }
}
