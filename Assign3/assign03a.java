class Shapes {
    double length, breadth;
    double radius;
    double base, height;

    // Constructor for Rectangle
    Shapes(double l, double b) {
        length = l;
        breadth = b;
    }

    // Constructor for Circle
    Shapes(double r) {
        radius = r;
    }

    // Constructor for Triangle
    Shapes(double b, double h, boolean isTriangle) {
        base = b;
        height = h;
    }

    // Method Overloading

    // Rectangle
    double area(double l, double b) {
        return l * b;
    }

    // Circle
    double area(double r) {
        return 3.14 * r * r;
    }

    // Triangle (different signature using 3 parameters)
    double area(double b, double h, int triangleFlag) {
        return 0.5 * b * h;
    }

    public static void main(String[] args) {

        Shapes rect = new Shapes(5, 4);
        System.out.println("Area of Rectangle = " + rect.area(rect.length, rect.breadth));

        Shapes circle = new Shapes(3);
        System.out.println("Area of Circle = " + circle.area(circle.radius));

        Shapes triangle = new Shapes(6, 2, true);
        System.out.println("Area of Triangle = " + triangle.area(triangle.base, triangle.height, 1));
    }
}