


package ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public String getGreeting() {

        return "ToDo List Manager";
    }

    public void displayFirstMenu() {
        System.out.println("\tMain Menu");
        System.out.println("\n1. Open To Do List"); //eventually open the to do list from a file
        System.out.println("2. Display To Do Items");
        System.out.println("3. Edit To Do Item");
        System.out.println("4. Add To Do Item");
        System.out.println("5. Delete To Do Item");
        System.out.println("6. Save To Do List"); //eventually save the to do list back to the file
        System.out.println("7. Exit");

        return;
    }

    private String callScanner(String prompt){

        System.out.println(prompt);
        String response = scanner.nextLine();
        return response;
    }

    private void displayTaskList(ArrayList<String> listOfTasks) {
        showTaskList(listOfTasks);
        askIfFinished();
    }

    private void askIfFinished() {
        String prompt = "\nDo you want to return to the Main Menu? (Y/N)";
        String responseReturned = callScanner(prompt);

        if(responseReturned.equalsIgnoreCase("Y"))
            return;

        prompt = "\nDo you want to exit? (Y/N)";
        responseReturned = callScanner(prompt);
        if(responseReturned.equalsIgnoreCase("Y"))
            System.exit(0);
        else
            return;
    }

    private void showTaskList(ArrayList<String> listOfTasks) {
        System.out.println("\n\tList of Tasks");
        int count = 1;
        for (String i : listOfTasks) {
            System.out.println(count + " - " + i);
            count++;
        }
    }

    private void addTask(ArrayList<String> listOfTasks) {
        showTaskList(listOfTasks);
        String prompt = "\nType the item you wish to add and press Enter or simply press Enter to return to Main Menu without adding a To Do Item";
        String responseReturned = callScanner(prompt);
        if (responseReturned.equals(""))
            askIfFinished();
        else {
            System.out.println("You chose to add task: " + responseReturned);
            listOfTasks.add(responseReturned);
            showTaskList(listOfTasks);
            askIfFinished();
        }
    }

    private void deleteTask(ArrayList<String> listOfTasks) {
        showTaskList(listOfTasks);
        String prompt = "\nType the number of the item you wish to delete and press Enter";
        String responseReturned = callScanner(prompt);
        System.out.println("You chose to delete task: " + responseReturned);
        int responseReturnedInt = Integer.parseInt(responseReturned);
        int sizeOfTaskList = listOfTasks.size();
        if (sizeOfTaskList < responseReturnedInt) {
            System.out.println("This is an invalid task number");
            askIfFinished();
        }
        else {
            listOfTasks.remove(responseReturnedInt - 1);
            showTaskList(listOfTasks);
            askIfFinished();
        }
    }

    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        App app = new App();

        //app.buildList();
        String task1 = "De-thatch lawn";
        String task2 = "Rake leaves";
        String task3 = "Mow lawn";
        ArrayList<String> listOfTasks = new ArrayList<String>();
        listOfTasks.add(task1);
        listOfTasks.add(task2);
        listOfTasks.add(task3);

//        Task task1x = new Task("De-thatch lawn");
//        Task task2x = new Task("Rake Leaves");
//        Task task3x = new Task("Mow Lawn");
//        ArrayList<Task> listOfTasksX = new ArrayList<>();
//        listOfTasksX.add(task1x);
//        listOfTasksX.add(task2x);
//        listOfTasksX.add(task3x);
//
//        System.out.println(listOfTasksX.size());

        String responseReturned;
        do {
            app.displayFirstMenu();

            String prompt = "\nPlease input an option 1 to 7:";
            responseReturned = app.callScanner(prompt);

            System.out.println("\nYour response was: " + responseReturned);
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
                    System.out.println("You chose to add a To Do Item");
                    app.addTask(listOfTasks);
                    break;
                case "5":
                    System.out.println("You chose to delete a To Do Item");
                    app.deleteTask(listOfTasks);
                    break;
                case "6":
                    System.out.println("You chose to save the To Do List");
                    break;
                case "7":
                    System.out.println("You chose to exit the To Do List");
                    break;
                default:
                    System.out.println("You chose an invalid option. Please choose a number from 1 to 7");
                    break;
            }
        } while (!responseReturned.equals("7"));
        app.scanner.close();
    }






}
