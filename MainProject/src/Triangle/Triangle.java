package Triangle;

public class Triangle {
    private double sideA;
    private double sideB;
    private double sideC;

    public double getSideA() {
        return sideA;
    }

    public void setSideA(double sideA) {
        this.sideA = sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public void setSideB(double sideB) {
        this.sideB = sideB;
    }

    public double getSideC() {
        return sideC;
    }

    public void setSideC(double sideC) {
        this.sideC = sideC;
    }

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public int checkTriangle() {
        if (this.sideA <= 0 || this.sideB <= 0 || this.sideC <= 0) {
            return 0; // Not a triangle
        }
        if (this.sideA + this.sideB > this.sideC && this.sideA + this.sideC > this.sideB && this.sideB + this.sideC > this.sideA) {
            boolean isEquilateral = (this.sideA == this.sideB) && (this.sideB == this.sideC);
            boolean isIsosceles = (this.sideA == this.sideB) || (this.sideB == this.sideC) || (this.sideA == this.sideC);
            boolean isRightAngled = (Math.pow(this.sideA, 2) + Math.pow(this.sideB, 2) == Math.pow(this.sideC, 2)) ||
                                   (Math.pow(this.sideA, 2) + Math.pow(this.sideC, 2) == Math.pow(this.sideB, 2)) ||
                                   (Math.pow(this.sideB, 2) + Math.pow(this.sideC, 2) == Math.pow(this.sideA, 2));

            if (isEquilateral) {
                return 3; // Equilateral triangle
            } else if (isIsosceles && isRightAngled) {
                return 5; // Right-angled isosceles triangle
            } else if (isIsosceles) {
                return 2; // Isosceles triangle
            } else if (isRightAngled) {
                return 4; // Right-angled triangle
            } else {
                return 1; // Scalene triangle
            }
        } else {
            return 0; // Not a triangle
        }
    }
}