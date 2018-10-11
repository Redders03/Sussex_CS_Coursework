public class TextBook
{
    private String title;
    private int nChapters;
    private int chaptersRead;
    public TextBook(String t, int n)
    {
        title = t;
        nChapters = n;
        chaptersRead = 0;
    }

    public String getTitle() 
    {
        return title;
    }

    public void readNextChapter() 
    {
        if ( isFinished() ) {
            System.out.println("No more chapters to read! The book is finished!");
        } 
        else {
            chaptersRead++;
        }
    }   

    public boolean isFinished()
    {
        return chaptersRead >= nChapters;
    }

    public void closeBook()
    {
        chaptersRead = 0;
    }     

    
    public void describe()
    {
        System.out.println(title + " with " + (nChapters - chaptersRead) + " chapters left.");
    }

}