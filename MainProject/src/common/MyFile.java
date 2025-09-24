package common;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class MyFile {
  public MyFile() {

  }

  /**
   * Read value in pathSourceFile
   * @param pathSourceFile ex: src/TestCase/source.txt
   * @return ArrayList<String>
   */
  public ArrayList<String> read(String pathSourceFile) {
    try {
      ArrayList<String> arrLineValues = new ArrayList<>();
      File directory = new File(pathSourceFile);
      if (!directory.exists()) {
        directory.mkdirs();
        System.out.println("Cannot find " + pathSourceFile + ". Create folder: " + pathSourceFile);
      }
      Scanner scanner = new Scanner(directory);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        arrLineValues.add(line);
      }
      scanner.close();
      return arrLineValues;
    } catch(Exception e) {
      System.out.println("Error: " + e.getMessage());
      return new ArrayList<>();
    }
  }

  /**
   * Clear file path
   * @param path ex: src/TestCase/source.txt
   * @return an empty file
   */
  public void clear(String path) {
    try {
      File directory = new File(path);
      if (!directory.exists()) {
        directory.createNewFile();
        System.out.println("Canot find " + path + ". Created file: " + path);
      }

      FileWriter fileWriter = new FileWriter(path);
      BufferedWriter buffer = new BufferedWriter(fileWriter);
      buffer.write("");
      buffer.close();

      System.out.println("The empty has been written to " + path);
    } catch (Exception e) {
      System.out.println("Invalid data format in file: " + path);
    }
  }

  /**
   * Wrtie a value to pathResultFile
   * @param pathResultFile ex: src/TestCase/source.txt
   * @param value
   */
  public void write (String pathResultFile, String value) {
    try {
      File directory = new File(pathResultFile);
      if (!directory.exists()) {
        directory.createNewFile();
        System.out.println("Canot find " + pathResultFile + ". Created file: " + pathResultFile);
      }

      FileWriter fileWriter = new FileWriter(pathResultFile, true);
      BufferedWriter buffer = new BufferedWriter(fileWriter);
      buffer.write(value);
      buffer.newLine();
      buffer.close();

      System.out.println("The " + value + " has been written to " + pathResultFile);
    } catch (Exception e) {
      System.out.println("Invalid data format in file: " + pathResultFile);
    }
  }

  /**
   * Compare string in pathResultFile with pathExpectedFile and write to pathFinalFile
   * @param pathResultFile ex: src/TestCase/source.txt
   * @param pathExpectedFile ex: src/TestCase/source.txt
   * @param pathFinalFile ex: src/TestCase/source.txt
   */
  public void compare(String pathResultFile, String pathExpectedFile, String pathFinalFile) {
    try {
      int casePassed = 0;
      int caseFailed = 0;
      ArrayList<String> resultValues = new ArrayList<>();
      ArrayList<String> expectedValues = new ArrayList<>();

      ArrayList<String> resultData = this.read(pathResultFile);
      ArrayList<String> expectedData = this.read(pathExpectedFile);

      this.clear(pathFinalFile);

      if (resultData.size() != expectedData.size()) {
        System.out.println("The arrays are unequal.");
      } else {
        for (int i = 0; i < resultData.size(); i++) {
          if (resultData.get(i).equals(expectedData.get(i))) {
            casePassed++;
          } else {
            caseFailed++;
            resultValues.add(resultData.get(i));
            expectedValues.add(expectedData.get(i));
          }
        }
        this.write(pathFinalFile, "Case Passed: " + casePassed);
        this.write(pathFinalFile, "Case Failed: " + caseFailed);
        this.write(pathFinalFile, "Total Case: " + (caseFailed + casePassed));
        this.write(pathFinalFile, "Case Failed. Detail: ");
        for (int i = 0; i < resultValues.size(); i++) {
          this.write(pathFinalFile, "Result Value: " + resultValues.get(i) + " - Expected Value: " + expectedValues.get(i));
        }

        System.out.println("Finished Task !");
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
