
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author dilorus
 */

public class HomeWork_5 {
    private static int employeeCount = 0;
    private static Employee[] employees;
    private static int multipleSupervisorsError = 0, noSupervisorError = 0;
    public HomeWork_5() throws FileNotFoundException {
        
        
        
        
        Scanner readFile;
        Scanner scan = new Scanner(System.in);
        String fileName;
        String employee;
        
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
        HomeWork_5 hw = new HomeWork_5();
        System.out.println("\nThere were " + multipleSupervisorsError + " counts of multiple supervisors");
        System.out.println("There were " + noSupervisorError + " counts of not supervised employees");
    }
    
    private class Employee {
        int id;
        int supervisor;
        public Employee() {
            id = 0;
            supervisor = 0;
        }
        
        public Employee (int id, int supervisor) {
            // the captain
            if(employees[id].id == 0) {
                employeeCount++;
            }
            
            // default first encounter with the employee
            if(employees[id].supervisor == -1) {
                employees[id].supervisor = supervisor;
                employeeCount++;
                if(employees[supervisor].supervisor == -1) {
                    noSupervisorError++;
                    employees[supervisor].supervisor = -2;
                }
            } else if(employees[id].supervisor == -2) {
                employees[id].supervisor = supervisor;
                noSupervisorError--;
                employeeCount++;
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
//        
//        public int getID() {
//            return id;
//        }
//        
//        public int getSupervisor() {
//            return supervisor;
//        }
//        
//        public void setID(int id) {
//            this.id = id;
//        }
//        
//        public void setSupervisor(int supervisor) {
//            this.supervisor = supervisor;
//        }
    }
}
