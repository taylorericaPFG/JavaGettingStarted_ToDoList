package ToDo;

import java.util.Scanner;

public class App {
    public String getGreeting() {
        return "ToDo List Manager";
    }

    public void displayFirstMenu() {
        System.out.println("\tMain Menu");
        System.out.println("\n1. Open To Do List");
        System.out.println("2. Display To Do Items");
        System.out.println("3. Edit To Do Item");
        System.out.println("4. Delete To Do Item");
        System.out.println("5. Save To Do List");
        System.out.println("6. Exit");

        return;
    }

    private String callScanner(String prompt){

        //print out question:
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);

        // print the next line
        String response = scanner.nextLine();

        scanner.close();

        return response;
    }

    public static void main(String[] args) {
        App app = new App();
        app.displayFirstMenu();

        String prompt= "\nPlease input your option:";

        String responseReturned = app.callScanner(prompt);
        //do code based on what response we get back
        System.out.println("\nYour response was " + responseReturned);


    }





}
