import java.util.Scanner;

// Parent class
class Employee {
    protected double salary;

    Employee(double salary) {
        this.salary = salary;
    }

    void displaySalary(String msg) {
        System.out.println(msg + salary);
    }
}

// Child class - Full Time Employee
class FullTimeEmployee extends Employee {

    FullTimeEmployee(double salary) {
        super(salary);
    }

    void calculateSalary() {
        displaySalary("Full Time Salary before hike: ");
        salary = salary + (salary * 0.50);   // 50% hike
        displaySalary("Full Time Salary after hike: ");
        System.out.println();
    }
}

// Child class - Intern Employee
class InternEmployee extends Employee {

    InternEmployee(double salary) {
        super(salary);
    }

    void calculateSalary() {
        displaySalary("Intern Salary before hike: ");
        salary = salary + (salary * 0.25);   // 25% hike
        displaySalary("Intern Salary after hike: ");
        System.out.println();
    }
}

// Main class
public class EmployeeDemo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Full Time Employee salary: ");
        double ftSalary = sc.nextDouble();
        FullTimeEmployee fte = new FullTimeEmployee(ftSalary);
        fte.calculateSalary();

        System.out.print("Enter Intern Employee salary: ");
        double intSalary = sc.nextDouble();
        InternEmployee intern = new InternEmployee(intSalary);
        intern.calculateSalary();

        sc.close();
    }
}
