public class TextBook
{
    private String title;
    private int chapterTotal, chapterCount;
    public TextBook(String titleInput,int chapterTotalInput){
        title = titleInput;
        chapterTotal = chapterTotalInput;
        chapterCount = 0;
    }

    public String getTitle(){
          return title;
    }
    
    public void readNextChapter(){
            if(chapterTotal > chapterCount){
                chapterCount ++;
            }
            else{
                System.out.println( "Error: You have reached the final chapter of this book");
            }
    }
  
    public boolean finished(){
        if(chapterCount == chapterTotal){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void closeBook(){
        chapterCount = 0;
    }
    
    public void describe(){
        int chapterLeft = 0;
        if(chapterCount < chapterTotal){
            chapterLeft = chapterTotal - chapterCount;
        }
        else{
            chapterLeft = 0;
        }
        System.out.println(title +" " +"With" +" " + chapterLeft +" " +"chapters left to read"); 
    }
}
    
    
