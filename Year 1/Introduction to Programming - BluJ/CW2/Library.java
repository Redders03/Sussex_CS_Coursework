public class Library
{
    private TextBook[] bookShelf;
    private int nextBook;
    private int borrowers;

    public Library(int numOfBooks)
    {
        bookShelf = new TextBook[numOfBooks];
        for (int i = 0; i < bookShelf.length; i++ ) {
            String title = "text_" + i;
            bookShelf[i] = new TextBook(title,5);
        }
        nextBook = 0;
        borrowers = 0;
    }

    public LibraryCard issueCard()
    {
        LibraryCard aCard = new LibraryCard(borrowers, 5);
        borrowers++;
        return aCard;
    }

    public TextBook borrowBook(LibraryCard card)
    {
        TextBook book = null;
        if ( (nextBook < bookShelf.length)  && !card.expired() ) {
            book = bookShelf[ nextBook ];
            bookShelf[ nextBook ] = null;
            nextBook++;
            card.swipe();
        }
        return book;
    }  

    public void returnBook(TextBook book)
    {
        bookShelf[ nextBook - 1 ] = book;
        nextBook--;
    }

    public void describe()
    {
        System.out.print("The library has ");
        System.out.print((bookShelf.length-nextBook) + " books on the shelf, and ");
        System.out.println("has issued " + borrowers + " cards.");
    }

}