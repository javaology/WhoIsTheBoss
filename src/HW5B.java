/**
 * This was not the final product
 * @author dilorus
 */

// supervisor is        0 by default
//                      -1 if listed as supervisor without own supervisor
//                      -2 if has multiple supervisors
public class HW5B {
    private static int employeeCount = 0;
    private static Employee[] employees;
    private static int multipleSupervisorsError = 0, notListedError = 0;
    public HW5B() {

        employees = new Employee[1000];  // Create an array of 1000 new default employees
                                                        // All employees are under the cap
        System.out.println(employees.length);
        for(int i = 0; i < employees.length; i++) {
            employees[i] = new Employee();
            employees[i].id = i;
            employees[i].supervisor = i;
            employees[i].listed = false;
            employees[i].supervising = false;
            employees[i].mismatched = false;
        }
        employees[0].listed = true;
        employees[0].supervising = true;
        employees[0].mismatched = false;

        Employee e1 = new Employee(1, 0);
        Employee e2 = new Employee(2, 0);
        Employee e3 = new Employee(200, 100);
        Employee e4 = new Employee(300, 100);
        Employee e5 = new Employee(200, 100);
        Employee e6 = new Employee(300, 200);
    }


    public static void main(String[] args) {
        HW5B hw = new HW5B();
        System.out.println("There were " + multipleSupervisorsError + " counts of multiple supervisors");
        System.out.println("There were " + notListedError + " counts of unlisted as employees");
    }

    private class Employee {
        int id;
        int supervisor;
        boolean listed;
        boolean supervising;
        boolean mismatched;

        public Employee() {
            id = 0;
            supervisor = 0;
            listed = false;
            supervising = false;
            mismatched = false;
            employeeCount++;
        }

        public Employee (int id, int supervisor) {
            if(!employees[id].listed) {
                employees[id].listed = true;
            }
            if(employees[id].supervisor != id && employees[id].supervisor != supervisor && !employees[id].mismatched) {
                employees[id].mismatched = true;
                multipleSupervisorsError++;
            }
            if(!employees[supervisor].supervising) {
                employees[supervisor].supervising = true;
            }
            if(!employees[supervisor].listed && !employees[supervisor].supervising) {
                notListedError++;
            }
            if(employees[id].supervising) {
                notListedError--;
            }

//            if (!employees[id].listed) {
//                if(employees[id].id == -1) {
//                    notListedError--;
//                }
//                employees[id].id = id;
//                employees[id].supervisor = this.supervisor;
//                employees[id].listed = true;
//                employeeCount++;
//                if(!employees[supervisor].listed && employees[supervisor].id == 0) {
//                    employees[supervisor].id = -1;
//                    notListedError++;
//                    System.out.println("Adding employee: " + id
//                            + " and supervisor: " + supervisor + " caused NotlistedError");
//                }
//            } else {
//                if ((employees[id].supervisor != supervisor && employees[id].supervisor != 0) && !mismatched) {
//                    multipleSupervisorsError++;
//                    mismatched = true;
//                    System.out.println("Adding employee: " + id
//                            + " and supervisor: " + supervisor + " caused multipleSupervisorsError");
//                } else if(employees[id].supervisor != supervisor && mismatched) {
//                    System.out.println("Adding employee: " + id
//                            + " and supervisor: " + supervisor + " couldn't cause multipleSupervisorsError");
//                }
//            }
        }

        public int getID() {
            return id;
        }

        public int getSupervisor() {
            return supervisor;
        }

        public boolean getListed() {
            return listed;
        }

        public void setID(int id) {
            this.id = id;
        }

        public void setSupervisor(int supervisor) {
            this.supervisor = supervisor;
        }

        public void setListed(boolean isListed) {
            listed = isListed;
        }

        public int getEmployeeCount() {
            return employeeCount;
        }
    }
}
