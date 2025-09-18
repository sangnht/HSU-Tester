package Week3.LinearEquation;

public class LinearEquation {
  private double a;
  private double b;
  public double getA() {
    return a;
  }
  public void setA(double a) {
    this.a = a;
  }
  public double getB() {
    return b;
  }
  public void setB(double b) {
    this.b = b;
  }
  public LinearEquation(double a, double b) {
    this.a = a;
    this.b = b;
  }

  public Double solve() {
    // Solves ax + b = 0 for x, returns -b/a if a != 0, otherwise throws exception
    if (a == 0) {
      System.out.println("Coefficient 'a' cannot be zero in a linear equation.");
      return null;
    }
    return -b / a;
  }
}
