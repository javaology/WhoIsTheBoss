
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Homework5B
 * @author Dilobar Ahmedova
 */

public class HomeWork5B {
    private static Employee[] employees;
    private static int multipleSupervisorsError = 0, noSupervisorError = 0;
    public HomeWork5B() throws FileNotFoundException {
        
        
        
        
        Scanner readFile;
        Scanner scan = new Scanner(System.in);
        String fileName;
        
        employees = new Employee[1000];  // Create an array of 1000 new default employees
                                                        // All employees are under the cap
        for(int i = 0; i < employees.length; i++) {
            employees[i] = new Employee();
            employees[i].id = i;
            employees[i].supervisor = -1;
        }
        employees[0].id = 0;
        employees[0].supervisor = 0;
        
        System.out.print("Enter name of the input file: ");
        fileName = scan.next();
        
        File file = new File(fileName);
        readFile = new Scanner(file);
        
        while(readFile.hasNext()) {
        new Employee(readFile.nextInt(), readFile.nextInt());
        }
        
   }
    
    
    public static void main(String[] args) throws FileNotFoundException {
        HomeWork5B hw = new HomeWork5B();
      
        System.out.println("\nThe number of crew members with no supervisor is " + noSupervisorError );
        System.out.println("The number of crew members with multiple supervisors is " + multipleSupervisorsError);
    }
    
    private class Employee {
        int id;
        int supervisor;
        public Employee() {
            id = 0;
            supervisor = 0;
        }
        
        public Employee (int id, int supervisor) {
            // default first encounter with the employee
            if(employees[id].supervisor == -1) {
                employees[id].supervisor = supervisor;
                if(employees[supervisor].supervisor == -1) {
                    noSupervisorError++;
                    employees[supervisor].supervisor = -2;
                }
            } else if(employees[id].supervisor == -2) {
                employees[id].supervisor = supervisor;
                noSupervisorError--;
                if(employees[supervisor].supervisor == -1) {
                    noSupervisorError++;
                    employees[supervisor].supervisor = -2;
                }
            } else if(employees[id].supervisor != supervisor) {
                multipleSupervisorsError++;
                if(employees[supervisor].supervisor == -1) {
                    noSupervisorError++;
                    employees[supervisor].supervisor = -2;
                }
            }
        }
    }
}
