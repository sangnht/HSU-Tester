import java.io.File;
import java.util.Scanner;
import Triangle.Triangle;

public class App {

    public static Triangle[] readFiles(String folderName) {
        try {
            File folder = new File(folderName);
            if (!folder.exists() || !folder.isDirectory()) {
                System.out.println("Folder not found or is not a directory.");
                return new Triangle[0];
            }
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
            if (files == null || files.length == 0) {
                System.out.println("No .txt files found in the folder.");
                return new Triangle[0];
            } else {
                Triangle[] triangles = new Triangle[files.length];
                for (int i = 0; i < files.length; i++) {
                    try (Scanner fileScanner = new Scanner(files[i])) {
                        if (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            String[] sides = line.split(":");
                            if (sides.length == 3) {
                                double sideA = Double.parseDouble(sides[0]);
                                double sideB = Double.parseDouble(sides[1]);
                                double sideC = Double.parseDouble(sides[2]);
                                triangles[i] = new Triangle(sideA, sideB, sideC);
                            } else {
                                System.out.println("Invalid data format in file: " + files[i].getName());
                                triangles[i] = new Triangle(0, 0, 0);
                            }
                        } else {
                            System.out.println("Empty file: " + files[i].getName());
                            triangles[i] = new Triangle(0, 0, 0);
                        }
                    } catch (Exception e) {
                        System.out.println("Error reading file: " + files[i].getName());
                        triangles[i] = new Triangle(0, 0, 0);
                    }
                }
                return triangles;
            }
        }
        catch (Exception e) {
            System.out.println("An error occurred while accessing the folder.");
            return new Triangle[0];
        }
    }

    public static Triangle inputSides() {
        double sideA = 0, sideB = 0, sideC = 0;
        try {
            String sides = "";
            Scanner input = new Scanner(System.in);
            System.out.print("Enter sides of triangle (Ex: 3 4 5): ");
            sides = input.nextLine();
            String[] sideArray = sides.split(" ");
            sideA = Double.parseDouble(sideArray[0]);
            sideB = Double.parseDouble(sideArray[1]);
            sideC = Double.parseDouble(sideArray[2]);
            input.close();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter three numeric values separated by spaces.");
        }
        return new Triangle(sideA, sideB, sideC);
    }

    public static void processTriangle(Triangle triangle) {
        int result = triangle.checkTriangle();
        switch (result) {
            case 0:
                System.out.println("Khong tao thanh tam giac");
                break;
            case 1:
                System.out.println("Tam giac thuong");
                break;
            case 2:
                System.out.println("Tam giac can");
                break;
            case 3:
                System.out.println("Tam giac deu");
                break;
            case 4:
                System.out.println("Tam giac vuong");
                break;
            case 5:
                System.out.println("Tam giac vuong can");
                break;
            default:
                System.out.println("Error");
                break;
        }
    }

    public static void main(String[] args) {
        int choice;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("===============================");
            System.out.println("Xac dinh loai tam giac tu 3 canh");
            System.out.println("[1] Nhap 3 canh cua tam giac tu ban phim");
            System.out.println("[2] Doc du lieu tu file input.txt");
            System.out.println("[0] Thoat!");
            System.out.println("===============================");
            System.out.print("Nhap lua chon cua ban: ");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    Triangle triangleFromInput = inputSides();
                    processTriangle(triangleFromInput);
                    break;
                case 2:
                    Triangle[] trianglesFromFile = readFiles("bin/TestCases");
                    for (Triangle triangle : trianglesFromFile) {
                        processTriangle(triangle);
                    }
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
