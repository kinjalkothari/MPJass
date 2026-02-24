import java.util.Scanner;

class Student {
    private int rollNo;
    private String name;
    private int[] marks = new int[5];

    // Constructor
    public Student(int rollNo, String name, int[] marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    // Method to calculate average
    public double calculateAverage() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total / 5.0;
    }

    // Method to calculate grade
    public char calculateGrade() {
        double avg = calculateAverage();

        if (avg >= 75)
            return 'A';
        else if (avg >= 50)
            return 'B';
        else
            return 'C';
    }

    // Display method
    public void displayInfo() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);

        System.out.print("Marks: ");
        for (int mark : marks) {
            System.out.print(mark + " ");
        }

        System.out.println("\nAverage: " + calculateAverage());
        System.out.println("Grade: " + calculateGrade());
        System.out.println();
    }
}

public class StudentDemo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\nEnter details of Student " + (i + 1));

            System.out.print("Roll No: ");
            int roll = sc.nextInt();
            sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            int[] marks = new int[5];
            System.out.println("Enter marks for 5 subjects:");
            for (int j = 0; j < 5; j++) {
                marks[j] = sc.nextInt();
            }

            students[i] = new Student(roll, name, marks);
        }

        System.out.println("\nStudent Details:");
        for (Student s : students) {
            s.displayInfo();
        }

        sc.close();
    }
}