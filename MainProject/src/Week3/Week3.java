package Week3;

import java.util.ArrayList;
import java.util.stream.Collectors;

import Week3.QuadraticEquation.QuadraticEquation;
import common.MyFile;

public class Week3 {
  public void run() {
    MyFile myFile = new MyFile();
    ArrayList<String> inputValues = myFile.read("src/Week3/TestCases/EquationInput.txt");
    myFile.clear("src/Week3/TestCases/EquationOutput.txt");
    for (String item : inputValues) {
      String[] coefficients = item.split(":");
      if (coefficients.length == 3) {
        try {
          double a = Double.parseDouble(coefficients[0]);
          double b = Double.parseDouble(coefficients[1]);
          double c = Double.parseDouble(coefficients[2]);
          QuadraticEquation equation = new QuadraticEquation(a, b, c);
          ArrayList<String> result = equation.solve();
          
          String output = result.size() > 2 
            ? String.join(":", result)
            : result.get(0).toString();

          myFile.write("src/Week3/TestCases/EquationOutput.txt", result.size() > 2 
            ? output
            : result.get(0).toString());
          
        } catch (NumberFormatException e) {
          System.out.println("Invalid number format in line: " + item);
        }
      } else {
        System.out.println("Invalid data format in line: " + item);
      }
    }
  }
}
