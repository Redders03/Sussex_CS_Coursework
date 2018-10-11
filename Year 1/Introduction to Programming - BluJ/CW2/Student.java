/**
 * Student Class contains methods that are unique methods that affect a student.
 * Creates a student object with multiple attributes associated with it.
 * @author Alex Reddington 
 * @version V1.0 (11/2015)
 */
public class Student
{
    // instance variables
    String setName;
    Library setLibrary;
    LibraryCard setCard;
    TextBook setTextbook;
    /**
     * Student Class Constructor
     * Initalize instance variables setName, setLibrary, setCard and setTextbook
     * @param name Name of student 
     * @param library Instance of library object that is required
     */
    public Student(String name, Library library){
        // initialise instance variables
        setName = name;
        setLibrary = library;
        setCard = setLibrary.issueCard();
        setTextbook = null;
    }

    /**
     * Indicates if studies have been finished by returning boolean value
     * @return     True/False
     */
    public boolean finishedStudies(){
        return setTextbook == null && setCard.expired();
    }

    /**
     * Method used to run appropriate behaviour of students studies depending 
     * on book status
     */
    public void study(){
        if(setTextbook == null){
            setTextbook = setLibrary.borrowBook(setCard);
        }
        else if(!setTextbook.isFinished()){
            setTextbook.readNextChapter();
        }
        else{
            setTextbook.closeBook();
            setLibrary.returnBook(setTextbook);
            setTextbook = null;
        }
    }

    /**
     * Method used to print to console appropriate information on the status of students 
     * studies.
     */
    public void describe(){
        if(setTextbook == null){
            System.out.printf("Student %s does not have a book and ",setName);
        }
        else{
            System.out.printf("Student %s has a book ",setName);
            setTextbook.describe();
            if(setTextbook.isFinished()){
                System.out.println("The book is finished");
            }
            else{
                System.out.println("The student is reading the book!");
            }
        }
        setCard.describe();
    }
}