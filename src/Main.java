import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String jsonFilePath = "D://ideajProject//Banking App/test.json";
        Path reqFilePath = Paths.get(jsonFilePath);
        StringBuilder sb = new StringBuilder();
        System.out.println(sb);
        try (BufferedReader reader = Files.newBufferedReader(reqFilePath, Charset.forName("UTF-8"))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                sb.append(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Parse JSON file to Users object
        ObjectMapper objectMapper = new ObjectMapper();
        Users users = null;
        try {
            users = objectMapper.readValue(sb.toString(), Users.class);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Registered users from the JSON file
        ArrayList<UserList> userList = users != null ? new ArrayList<>(users.getUserList()) : new ArrayList<>();
        System.out.println(userList);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        String inputName = scanner.nextLine();
        System.out.println("Enter your account number:");
        String inputAccNum = scanner.nextLine();

        boolean verified = false;
        for (UserList user : userList) {
            if (user.getName().equals(inputName) && user.getAccNum().equals(inputAccNum)) {
                verified = true;
                break;
            }
        }

        if (verified) {
            System.out.println("Hi " + inputName + ", Welcome to Banking App");
            BankingApp userObj = new BankingApp();
            boolean flag = true;
            while (flag) {
                itinary();
                System.out.print("Please choose an option: ");
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (option) {
                    case 1:
                        System.out.println("Your Balance is " + userObj.viewBalance());
                        break;
                    case 2:
                        System.out.println("Enter amount to deposit:");
                        double amountDeposited = scanner.nextDouble();
                        double depositedAmt = userObj.deposit(amountDeposited);
                        System.out.println("Your Current Balance is " + depositedAmt);
                        System.out.println("Deposited amount " + amountDeposited);
                        break;
                    case 3:
                        System.out.println("Enter amount to withdraw:");
                        double amountWithdraw = scanner.nextDouble();
                        System.out.println("Your Current Balance is " + userObj.withDraw(amountWithdraw));
                        System.out.println("Withdrawn amount " + amountWithdraw);
                        break;
                    case 4:
                        System.out.println("Your Transaction History is " + userObj.viewTransaction());
                        break;
                    case 5:
                        System.out.println("Enter Your Name To Register");
                        String userName = scanner.nextLine();
                        userObj.userRegister(userName, userList);
                        System.out.println("Updated user list: " + userList);
                        break;
                    case 6:
                        flag = false;
                        System.out.println("Thank You For Banking With Us");
                        break;
                    default:
                        System.out.println("Choose the correct Option");
                }
            }
        } else {
            System.out.println("Verification failed. Please check your name and account number or register first.");
        }

        scanner.close();
    }

    public static void itinary() {
        System.out.println("""
                            1. Check Balance
                            2. Deposit Money
                            3. Withdraw Money
                            4. View Transaction History
                            5. Register a New User
                            6. Exit
                        """);
    }
}