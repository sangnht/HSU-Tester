package Week3.QuadraticEquation;

import java.util.ArrayList;

import Week3.LinearEquation.LinearEquation;

public class QuadraticEquation {
  private double a;
  private double b;
  private double c;
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
  public double getC() {
    return c;
  }
  public void setC(double c) {
    this.c = c;
  }
  public QuadraticEquation(double a, double b, double c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }
  public ArrayList<String> solve() {
    ArrayList<String> result = new ArrayList<>();
    double delta = 0;
    if (this.a != 0) {
      delta = Math.pow(this.b, 2) - 4 * this.a * this.c;
      if (delta >= 0) {
        Double x1 = (-this.b + Math.sqrt(delta)) / (2 * this.a);
        Double x2 = (-this.b - Math.sqrt(delta)) / (2 * this.a);
        result.add(x1.toString());
        result.add(x2.toString());
      } else {
        // Vô nghiệm
        result.add("VO_NGHIEM");
      }
    } else {
      LinearEquation ptb1 = new LinearEquation(this.b, this.c);
      Object x = ptb1.solve();
      result.add(x.toString());
    }
    return result;
  }
}
