public class LibraryCard
{
    private int userLimit, numBorrowed, numLeft;
    private String reference;
    public LibraryCard(int userLtInput,int referenceInput){
        userLimit = userLtInput;
        reference = "cardID" + referenceInput;
        numBorrowed = 0;
    }

    public void swipe(){
        if(numBorrowed == userLimit){
            System.out.println("Error: No more books can be borrowed from this card due to the limit being reached");
        }
        else{
            numBorrowed++;
        }
    }
    
    public boolean expired(){
        if(numBorrowed >= userLimit){
            return true;
        }
        else{
            return false;
        }
    }
    
    public String getCardRef(){
        return reference;
    }
    
    public void describe(){
       if(numBorrowed < userLimit){
           numLeft = userLimit - numBorrowed;
        }
       else{
           numLeft = 0;
        }
       System.out.println("Library Card Reference :" +" " +reference +" " +"has the ability to lend" +" " +numLeft +" " +"more book");
    }
}
       
        
