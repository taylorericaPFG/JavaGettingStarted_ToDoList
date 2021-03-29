package ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public String getGreeting() {
        return "ToDo List Manager";
    }

    public void displayFirstMenu() {
        System.out.println("\tMain Menu");
        System.out.println("\n1. Open To Do List"); //How is this different than the next one? Should we change #2 to Add To Do Items?
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
        String task1 = "Iron pants";
        String task2 = "Rake leaves";
        String task3 = "Till garden";
        ArrayList<String> listOfTasks = new ArrayList<String>();
        listOfTasks.add(task1);
        listOfTasks.add(task2);
        listOfTasks.add(task3);

//        Task task1x = new Task("Iron pants");
//        Task task2x = new Task("Rake Leaves");
//        Task task3x = new Task("Till garden");
//        ArrayList<Task> listOfTasksX = new ArrayList<>();
//        listOfTasksX.add(task1x);
//        listOfTasksX.add(task2x);
//        listOfTasksX.add(task3x);

        String prompt= "\nPlease input your option:";

        String responseReturned = app.callScanner(prompt);
        //do code based on what response we get back
        System.out.println("\nYour response was " + responseReturned);

        switch (responseReturned){
            case "1":
                System.out.println("You chose to open the To Do List");
                break;
            case "2":
                System.out.println("\n\tList of Tasks");
                for (String i : listOfTasks) {
                    System.out.println(i);
                }
                break;
            case "3":
                System.out.println("You chose to edit a To Do Item");
                break;
            case "4":
                System.out.println("You chose to delete a To Do Item");
                break;
            case "5":
                System.out.println("You chose to save the To Do List");
                break;
            case "6":
                System.out.println("You chose to exit the To Do List");
                break;
            //Add add new To Do List item?
            default:
                System.out.println("Please choose a number from 1 to 6");
                break;
        }

    }





}
