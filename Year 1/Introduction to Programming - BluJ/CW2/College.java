import java.util.ArrayList;
import java.util.Random;
/**
 * Class College contains methods that are unique to creating a college instance
 * College represents the academic part of the system. Methods such as runCollege
 * and nextStep which are built to ensure that Student objects in the program have studied.
 * @author Alex Reddington 
 * @version Version 1 (11/2015)
 */
public class College
{
    // Instance variables
    ArrayList <Student> list;
    Library library;
    Random random;
    /**
     * College Class Constructor
     * Initialize instance variables library and lists
     * @param enrolled Value specifying the amount of students enrolled
     * @param books Specifying the Methodamount of books in the library
     */
    public College(int enrolled, int books)
    {
        library = new Library(books);
        list = new ArrayList<>(enrolled);
        for(int i = 0; i < enrolled; i++){
            list.add(i,new Student("student"+i,library));
        }
        random = new Random();
    }

    /**
     * Method Prints to terminal the number of students in the list with appropriate text.
     */
    public void describe(){
        System.out.println("The college currently has"+" "+list.size()+" "+"hard working students");
        library.describe();
    }

    private void nextStep(){
        if(list.size() ==0){
            System.out.println("Everything has gone very quiet!");
        }
        else{
            int rand = random.nextInt(list.size());
            Student selectedStudent = list.get(rand);
            if(selectedStudent.finishedStudies()){
                System.out.println("The student has graduated and left the college!");
                list.remove(rand);
            }
            else{
                selectedStudent.study();
            }
        }
    }

    /**
     * Method used to run simulation of college running.
     * @param nSteps value set for limit on For Loop
     */
    public void runCollege(int nSteps){
        System.out.println("steps"+nSteps);
        for(int i = 0; i < nSteps; i++){
            System.out.println("Step"+" "+(i+1));
            describe();
            nextStep();
        }
    }

    /**
     * Main Method used to start program outside of blueJ enviroment.
     * @param args Arguements that are stored in a String array 
     */
    public static void main(String[] args){
        College storedCollege = new College(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        storedCollege.runCollege(Integer.parseInt(args[2]));
    }
}