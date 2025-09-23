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

  public String solve() {
    if (a == 0) {
      if (b == 0) {
        // Vô số nghiệm
        return "VO_SO_NGHIEM";
      } else {
        // Vô nghiệm
        return "VO_NGHIEM";
      }
    } else {
      Double result = -b / a;
      return result.toString();
    }
  }
}
