package ToDo;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public String getGreeting() {
        return "ToDo List Manager";
    }

    public void displayFirstMenu() {
        System.out.println("\tMain Menu");
        System.out.println("\n1. Open To Do List"); //open up from file
        System.out.println("2. Display To Do Items");
        System.out.println("3. Edit To Do Item");
        System.out.println("4. Delete To Do Item");
        System.out.println("5. Save To Do List");
        System.out.println("6. Exit");

        return;
    }

    private String callScanner(String prompt){

        System.out.println(prompt);
        //Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        //scanner.close();
        return response;
    }

    private void displayTaskList(ArrayList<String> listOfTasks) {
        System.out.println("\n\tList of Tasks");
        for (String i : listOfTasks) {
            System.out.println(i);
        }
        String prompt = "\nDo you want to return to the Main Menu? (Y/N)";
        String responseReturned = callScanner(prompt);
        if(responseReturned.equals("Y"))
            return;
        responseReturned = callScanner("\nDo you want to exit? (Y/N)");
        if(responseReturned.equals("Y"))
            System.exit(0);
        else
            return;


    }

    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        App app = new App();

        //app.buildList();
        String task1 = "De-thatch lawn";
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
//
//        System.out.println(listOfTasksX.size());

        String responseReturned;
        do {
            app.displayFirstMenu();

            String prompt = "\nPlease input an option 1 to 6:";
            responseReturned = app.callScanner(prompt);

            System.out.println("\nYour response was " + responseReturned);
            switch (responseReturned) {
                case "1":
                    System.out.println("You chose to open the To Do List");
                    break;
                case "2":
                    app.displayTaskList(listOfTasks);
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
                default:
                    System.out.println("You chose an invalid option - Please choose a number from 1 to 6");
                    break;
            }
        } while (!responseReturned.equals("6"));
        app.scanner.close();
    }






}
