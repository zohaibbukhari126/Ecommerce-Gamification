import java.util.*;
import java.lang.*;

public class AppTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Person> personArrayList = new ArrayList<Person>();
        
        System.out.println("Enter First Name, Last Name, Email & Phone Number");
        personArrayList.add(new Person(sc.next(), sc.next(), sc.next(), sc.next()));

    }

}

