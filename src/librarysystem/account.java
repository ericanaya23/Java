package librarysystem;
import java.util.ArrayList;

public class account {
    String fName, lName;
    int accountNumber, checkOut;
    ArrayList<Book> books = new ArrayList<>();
    
    public account(){
        fName = " ";
        lName = " ";
        accountNumber = 0;
        checkOut = 0;
    }
    
    public account(String fName, String lName, int accountNumber){
        this.fName = fName;
        this.lName = lName;
        this.accountNumber = accountNumber;   
    }
    
    //*******************
    //METHOD TO WITHDRAW*
    //*******************
    public void withdraw(Book[] books, String title){
        boolean found = false;
        
        if(checkOut < 5){
            //SEARCH IF BOOK IS IN INVENTORY
            for (Book book : books) {
                if (book.title.toUpperCase().equals(title.toUpperCase()) && book.amount > 0) {
                    System.out.println(book.title + " has been withdrawn");
                
                    this.books.add(book);
                    book.amount -= 1;
                    checkOut += 1;
                    found = true;
                    break;
                }
                else if(book.title.toUpperCase().equals(title.toUpperCase()) && book.amount == 0){
                    System.out.println(book.title + " is out of stock");
                    found = true;
                    break;
                }
            }
            
            //BOOK IS NOT IN INVENTORY
            if (found ==false){
                System.out.println(title + " is not in inventory.");
            }
        }
        else{
            System.out.println("You have max amount of books checked out");
        }
        
        System.out.println();
    } 
    
    //******************
    //METHOD TO DEPOSIT*
    //******************
    public void deposit(Book[] books, String title){
        boolean found = false;
        
        if(checkOut > 0){
            //Search for book in YOUR inventory
            for(int i = 0; i < this.books.size(); i++){
                if(this.books.get(i).title.toUpperCase().equals(title.toUpperCase())){
                    
                    //Search for book in LIBRARY inventoy
                    for(int j = 0; j < books.length; j++){
                        if(books[j].title.toUpperCase().equals(title.toUpperCase()) && books[j].amount < 3){
                            System.out.println(books[j].title + " has been deposited.");
                            
                            this.books.remove(i);
                            books[j].amount += 1;
                            checkOut -= 1;
                            found = true;
                            break;
                        }
                    }
                    
                    if(found == false){
                        System.out.println(title + " is not in inventory.");
                    }
                }
                else{
                    System.out.println(title + " is not in your inventory.");
                }
            }
            
        }
        else{
            System.out.println("You have 0 books checked out.");
        }
        
        System.out.println();
    }
    
    //*********************************************
    //METHOD TO DISPLAY NUMBER OF BOOKS IN ACCOUNT*
    //*********************************************
    public void checkOutNum(){
        System.out.println("You have " + checkOut + " book(s) in you account.");
        
        for(int i =0; i < books.size(); i++){
            System.out.println(books.get(i).title + "\t" + books.get(i).author + "\t" + books.get(i).year);
        }

        System.out.println();
    }
}
