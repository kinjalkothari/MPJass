import java.util.Scanner;

class Shapes {

    // Method Overloading

    // Rectangle
    double area(double length, double breadth) {
        return length * breadth;
    }

    // Circle
    double area(double radius) {
        return Math.PI * radius * radius;
    }

    // Triangle (different type to avoid conflict)
    double area(float base, float height) {
        return 0.5 * base * height;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Shapes s = new Shapes();

        int choice;

        do {
            System.out.println("\n--- Shape Area Calculator ---");
            System.out.println("1. Rectangle");
            System.out.println("2. Circle");
            System.out.println("3. Triangle");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter length: ");
                    double l = sc.nextDouble();

                    System.out.print("Enter breadth: ");
                    double b = sc.nextDouble();

                    System.out.println("Area of Rectangle = " + s.area(l, b));
                    break;

                case 2:
                    System.out.print("Enter radius: ");
                    double r = sc.nextDouble();

                    System.out.println("Area of Circle = " + s.area(r));
                    break;

                case 3:
                    System.out.print("Enter base: ");
                    float base = sc.nextFloat();

                    System.out.print("Enter height: ");
                    float height = sc.nextFloat();

                    System.out.println("Area of Triangle = " + s.area(base, height));
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}