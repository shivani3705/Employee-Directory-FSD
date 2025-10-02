import java.util.ArrayList;
import java.util.List;

class Employee {
    int id;
    String name;
    String designation;

    public Employee(int id, String name, String designation) {
        this.id = id;
        this.name = name;
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Designation: " + designation;
    }
}

class DuplicateEmployeeException extends Exception {
    public DuplicateEmployeeException(String message) {
        super(message);
    }
}

class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}

public class EmployeeDirectory {
    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        try {
            addEmployee(new Employee(101, "Alice", "Developer"));
            addEmployee(new Employee(102, "Bob", "Manager"));
            addEmployee(new Employee(101, "Charlie", "Analyst")); // This will cause an exception
        } catch (DuplicateEmployeeException e) {
            System.err.println("Error: " + e.getMessage());
        }

        try {
            System.out.println("\nFound: " + findEmployeeById(102));
            findEmployeeById(103); // This will cause an exception
        } catch (EmployeeNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

        printAllEmployees();
    }

    public static void addEmployee(Employee newEmployee) throws DuplicateEmployeeException {
        for (Employee emp : employees) {
            if (emp.id == newEmployee.id) {
                throw new DuplicateEmployeeException("Employee with ID " + newEmployee.id + " already exists.");
            }
        }
        employees.add(newEmployee);
        System.out.println("Added: " + newEmployee.name);
    }

    public static Employee findEmployeeById(int id) throws EmployeeNotFoundException {
        for (Employee emp : employees) {
            if (emp.id == id) {
                return emp;
            }
        }
        throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
    }

    public static void printAllEmployees() {
        System.out.println("\n--- All Employees ---");
        employees.forEach(System.out::println);
    }
}