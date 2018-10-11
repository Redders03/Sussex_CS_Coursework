import java.util.Arrays;
public class Library
{
    private TextBook[] bookShelf;
    private int userTotal, bookShelfLimit, counter, slotMarker = 0;
    public Library(int bookSLInput){
        bookShelfLimit = bookSLInput;
        bookShelf = new TextBook[bookShelfLimit];
        TextBook book;
        userTotal = 0;
        for(counter = 0; counter < bookShelfLimit;counter++){
             book = new TextBook("Textbook " + counter, 5); 
             bookShelf[counter] = book;
        }
    }
    
    public LibraryCard issueCard(){
        return new LibraryCard(5,userTotal++);
    }
    
    public TextBook borrowBook(LibraryCard card){
        if(card.expired() || counter < bookShelfLimit || card == null){
            System.out.println("Error: Cannot continue due to expired card or empty book shelf.");
            return null;
        }
        else{
            TextBook borrowedBook = bookShelf[slotMarker];
            bookShelf[slotMarker] = null;
            slotMarker++;
            card.swipe();
            return borrowedBook;
        }
    }
    
    public void returnBook(TextBook book){
        if(slotMarker == 0){
           System.out.println("Error: Operation is not possible due to bookshelf being full."); 
        }
        else{
            slotMarker--;
            bookShelf[slotMarker] = book;
        }
    }
    
    public void describe(){
        int left;
        left = bookShelfLimit - slotMarker;
        System.out.println("The Library has :"+" "+left+" "+"book left on the shelf"+" "+"and has issued"+" "+userTotal+" "+"library cards.");   
    }
}
