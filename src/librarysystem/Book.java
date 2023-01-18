package librarysystem;

public class Book {
    String title, author;
    int year, amount;
    
    public Book(){
        title = " ";
        author = " ";
        year = 0;
        amount = 0;
    }
    
    public Book(String title, String author, int year, int amount){
        this.title = title;
        this.author = author;
        this.year = year;
        this.amount = amount;
    }  
}
