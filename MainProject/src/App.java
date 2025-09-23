import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Scanner;
import Triangle.Triangle;

public class App {
    /**
     * @param pathSourceFile => ex: "source_file.txt"
     * @return ArrayList<String>
     */
    public static ArrayList<String> __readFiles(String pathSourceFile) {
        ArrayList<String> arrList = new ArrayList<>();
        try {
            String absolutePathString = "src";
            File directory = new File(absolutePathString + pathSourceFile);
            if (!directory.exists()) {
                directory.mkdirs(); // Tạo các thư mục cha nếu cần
                System.out.println("Created folder: " + pathSourceFile);
            }


            Scanner scanner = new Scanner(directory);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                arrList.add(line);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Invalid URI syntax: " + e.getMessage());
        }
        return arrList;
    }

    /**
     * @param pathResultFile => ex: "source_file.txt"
     * @param value => ex: "Hello World"
     */
    public static void __writeFiles(String pathResultFile, String value) {
        try {
            String absolutePathString = "src";
            File directory = new File(absolutePathString + pathResultFile);
            if (!directory.exists()) {
                directory.createNewFile();
                System.out.println("Created file: " + pathResultFile);
            }
            FileWriter writer = new FileWriter(absolutePathString + pathResultFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(value);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch(Exception e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public static void clearFile(String pathResultFile) {
        try {
            String absolutePathString = "src";
            File directory = new File(absolutePathString + pathResultFile);
            if (directory.exists()) {
                FileWriter writer = new FileWriter(absolutePathString + pathResultFile, false);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write("");
                bufferedWriter.close();
            }
        } catch(Exception e) {
            System.out.println("An error occurred while clearing the file: " + e.getMessage());
        }
    }

    // Cau 6
    public static void compareFiles(String pathResultFile, String pathExpectedFile) {
        try {
            int casePassed = 0;
            int caseFailed = 0;
            ArrayList<String> resultValue = new ArrayList<>();
            ArrayList<String> expectedValue = new ArrayList<>();

            ArrayList<String> arrResultData = __readFiles(pathResultFile);
            ArrayList<String> arrExpectedData = __readFiles(pathExpectedFile);
            if (arrResultData.size() != arrExpectedData.size()) {
                System.out.println("Files have different number of lines.");
                return;
            }
            for (int i = 0; i < arrResultData.size(); i++) {
                if (!arrResultData.get(i).equals(arrExpectedData.get(i))) {
                    caseFailed++;
                    resultValue.add(arrResultData.get(i));
                    expectedValue.add(arrExpectedData.get(i));
                } else {
                    casePassed++;
                }
            }
            System.out.println("Total cases: " + (casePassed + caseFailed));
            System.out.println("Passed cases: " + casePassed);
            System.out.println("Failed cases: " + caseFailed);
            if (caseFailed > 0) {
                System.out.println("Failed case details:");
                for (int i = 0; i < resultValue.size(); i++) {
                    System.out.println("Expected: " + expectedValue.get(i) + " | Got: " + resultValue.get(i));
                }
            }
        } catch(Exception e) {
            System.out.println("An error occurred while comparing the files: " + e.getMessage());
        }
    }

    // Cau 5
    public static void readAndWriteFile(String pathSourceFile) {
        try {
            ArrayList<String> arrData = __readFiles(pathSourceFile);
            for (String item : arrData) {
                String[] sides = item.split(":");
                if (sides.length == 3) {
                    double sideA = Double.parseDouble(sides[0]);
                    double sideB = Double.parseDouble(sides[1]);
                    double sideC = Double.parseDouble(sides[2]);
                    Triangle triangles = new Triangle(sideA, sideB, sideC);
                    String result = processTriangle(triangles);
                    __writeFiles("/TestCase-v2/testCaseResult.txt", result);
                } else {
                    System.out.println("Invalid data format in file: " + pathSourceFile);
                }
            }
            System.out.println("Successfully!");
        } catch(Exception e) {
            System.out.println("An error occurred while accessing the folder.");
        }
    }

    // Cau 4
    public static void readFile(String pathSourceFile) {
        try {
            ArrayList<String> arrData = __readFiles(pathSourceFile);
            for (String item : arrData) {
                String[] sides = item.split(":");
                if (sides.length == 3) {
                    double sideA = Double.parseDouble(sides[0]);
                    double sideB = Double.parseDouble(sides[1]);
                    double sideC = Double.parseDouble(sides[2]);
                    Triangle triangles = new Triangle(sideA, sideB, sideC);
                    String result = processTriangle(triangles);
                    System.out.println(result);
                } else {
                    System.out.println("Invalid data format in file: " + pathSourceFile);
                }
            }
            
        } catch(Exception e) {
            System.out.println("An error occurred while accessing the folder.");
        }
    }

    // Cau 3
    public static void readFiles(String folderName) {
        try {
            String s1 = new File(App.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getAbsolutePath();
            File folder = new File(s1 + folderName);
            if (!folder.exists() || !folder.isDirectory()) {
                System.out.println("Folder not found or is not a directory.");
            }
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
            if (files == null || files.length == 0) {
                System.out.println("No .txt files found in the folder.");
            } else {
                for (int i = 0; i < files.length; i++) {
                    try (Scanner fileScanner = new Scanner(files[i])) {
                        if (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            String[] sides = line.split(":");
                            if (sides.length == 3) {
                                double sideA = Double.parseDouble(sides[0]);
                                double sideB = Double.parseDouble(sides[1]);
                                double sideC = Double.parseDouble(sides[2]);
                                Triangle triangle = new Triangle(sideA, sideB, sideC);
                                String result = processTriangle(triangle);
                                System.out.println("File: " + files[i].getName() + " => " + result);
                            } else {
                                System.out.println("Invalid data format in file: " + files[i].getName());
                            }
                        } else {
                            System.out.println("Empty file: " + files[i].getName());
                        }
                    } catch (Exception e) {
                        System.out.println("Error reading file: " + files[i].getName());
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("An error occurred while accessing the folder.");
        }
    }

    // Cau 1 & 2
    public static void inputSides() {
        try {
            double sideA = 0, sideB = 0, sideC = 0;
            String sides = "";
            Scanner input = new Scanner(System.in);
            System.out.print("Enter sides of triangle (Ex: 3 4 5): ");
            sides = input.nextLine();
            String[] sideArray = sides.split(" ");
            sideA = Double.parseDouble(sideArray[0]);
            sideB = Double.parseDouble(sideArray[1]);
            sideC = Double.parseDouble(sideArray[2]);
            Triangle triangle = new Triangle(sideA, sideB, sideC);
            String result = processTriangle(triangle);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter three numeric values separated by spaces.");
        }
    }

    public static String processTriangle(Triangle triangle) {
        int result = triangle.checkTriangle();
        switch (result) {
            case 0:
                return "Khong tao thanh tam giac";
            case 1:
                return ("Tam giac thuong");
            case 2:
                return ("Tam giac can");
            case 3:
                return ("Tam giac deu");
            case 4:
                return ("Tam giac vuong");
            case 5:
                return ("Tam giac vuong can");
            default:
                return ("Error");
        }
    }

    public static void main(String[] args) {
        int choice;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("==========================================");
            System.out.println("Xac dinh loai tam giac tu 3 canh");
            System.out.println("[1] Cau 1 & 2: Nhap 3 canh cua tam giac tu ban phim");
            System.out.println("[2] Cau 3: Doc du lieu tu nhieu file *.txt");
            System.out.println("[3] Cau 4: Doc du lieu tu file testCase.txt");
            System.out.println("[4] Cau 5: Doc du lieu tu file testCase.txt va tra ve du lieu trong file testCaseResult.txt");
            System.out.println("[5] Cau 6: Doc du lieu tu file testCase.txt, tra ve du lieu trong file testCaseResult.txt va so sanh voi file testCaseExpected.txt");
            System.out.println("[0] Thoat!");
            System.out.println("==========================================");
            System.out.print("Nhap lua chon cua ban: ");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    inputSides();
                    break;
                case 2:
                    readFiles("/TestCase-v1");
                    break;
                case 3:
                    readFile("/TestCase-v2/testCases.txt");
                    break;
                case 4:
                    clearFile("/TestCase-v2/testCaseResult.txt");
                    readAndWriteFile("/TestCase-v2/testCases.txt");
                    break;
                case 5:
                    clearFile("/TestCase-v2/testCaseResult.txt");
                    readAndWriteFile("/TestCase-v2/testCases.txt");
                    compareFiles("/TestCase-v2/testCaseResult.txt", "/TestCase-v2/testCaseExpected.txt");
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai!");
                    break;
            }
        } while (choice != 0);
    }
}
