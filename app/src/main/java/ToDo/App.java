


package ToDo;


import java.time.LocalDate;
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

    private void displayTaskList(ArrayList<Task> listOfTasks) { //add to display everything or even to show just completed ones, etc.
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

    private void showTaskList(ArrayList<Task> listOfTasks) { //add code to display the due date, etc. too
        System.out.println("\n\tList of Tasks");
        int count = 1;
        for (Task t : listOfTasks) {
            System.out.println(count + " - " + t.getName() + " due " + t.getDueDate());
            count++;
        }
    }

    private void editTask(ArrayList<Task> listOfTasks) { //add code to edit due date or status, etc.
        showTaskList(listOfTasks);
        String prompt = "\nType the number of the item you wish to edit and press Enter or simply press Enter to return to Main Menu without editing a To Do Item";
        String responseReturned = callScanner(prompt);
        if (responseReturned.equals(""))
            askIfFinished();
        else {
            System.out.println("You chose to edit task: " + responseReturned);
            int responseReturnedInt = Integer.parseInt(responseReturned);
            int sizeOfTaskList = listOfTasks.size();
            if (sizeOfTaskList < responseReturnedInt) {
                System.out.println("This is an invalid task number");
                askIfFinished();
            } else {
                prompt = "\nType the wording with which you would like to edit the existing To Do item and press Enter";
                String editedTaskName = callScanner(prompt);
                String editedDatePrompt = "\nType the Due Date for " + editedTaskName + " (YYYY-MM-DD)";
                LocalDate editedDueDate = LocalDate.parse(callScanner(editedDatePrompt));
                Task task = listOfTasks.get(responseReturnedInt -1); //this tells what item to grab
                task.setName(editedTaskName); //this sets the edited task name
                task.setDueDate(editedDueDate); //this sets the edited due date
                showTaskList(listOfTasks);
                askIfFinished();
            }
        }
    }

    private void addTask(ArrayList<Task> listOfTasks) {
        showTaskList(listOfTasks);
        String prompt = "\nType the wording for the item you wish to add and press Enter or simply press Enter to return to Main Menu without adding a To Do Item";
        String taskName = callScanner(prompt);
        if (taskName.equals(""))
            askIfFinished();
        else {
            String dueDateprompt = "\nType the Due Date for " + taskName + " (YYYY-MM-DD)";
            LocalDate taskDueDate = LocalDate.parse(callScanner(dueDateprompt));
            Task newTask = new Task(taskName, taskDueDate, false, false);
            System.out.println("You chose to add task: " + taskName + " with a due date of " + taskDueDate); //add some code to add the due date too so we ask for new name then ask for due date, etc.
            listOfTasks.add(newTask);
            showTaskList(listOfTasks);
            askIfFinished();
        }
    }

    private void deleteTask(ArrayList<Task> listOfTasks) {
        showTaskList(listOfTasks);
        String prompt = "\nType the number of the item you wish to delete and press Enter or simply press Enter to return to Main Menu without deleting a To Do Item";
        String responseReturned = callScanner(prompt);
        if (responseReturned.equals(""))
            askIfFinished();
        else {
            System.out.println("You chose to delete task: " + responseReturned);
            int responseReturnedInt = Integer.parseInt(responseReturned);
            int sizeOfTaskList = listOfTasks.size();
            if (sizeOfTaskList < responseReturnedInt) {
                System.out.println("This is an invalid task number");
                askIfFinished();
            } else {
                listOfTasks.remove(responseReturnedInt - 1);
                showTaskList(listOfTasks);
                askIfFinished();
            }
        }
    }

    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        App app = new App();

        Task task1 = new Task("De-thatch lawn", LocalDate.of(2021,05, 01), true, false);
        Task task2 = new Task("Rake Leaves", LocalDate.of(2021,05, 02), false, false);
        Task task3 = new Task("Mow Lawn", LocalDate.of(2021,05, 03), false, false);
        ArrayList<Task> listOfTasks = new ArrayList<>();
        listOfTasks.add(task1);
        listOfTasks.add(task2);
        listOfTasks.add(task3);

        System.out.println(listOfTasks.size());
        System.out.println(listOfTasks.get(0).getName());

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
                    System.out.println("You chose to display the To Do List");
                    app.displayTaskList(listOfTasks);
                    break;
                case "3":
                    System.out.println("You chose to edit a To Do Item");
                    app.editTask(listOfTasks);
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


//At end look for duplicate code to be able to call a function to refactor it



}
