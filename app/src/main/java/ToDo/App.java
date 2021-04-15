


package ToDo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class App {
    public String getGreeting() {

        return "ToDo List Manager";
    }

    public void displayFirstMenu() {
        System.out.println("\tMain Menu");
        System.out.println("\n1. Open To Do List"); //eventually open the to do list from a file -- Look at this site to read the file: https://crunchify.com/how-to-read-json-object-from-file-in-java/
        System.out.println("2. Display To Do Items");
        System.out.println("3. Edit To Do Item");
        System.out.println("4. Add To Do Item");
        System.out.println("5. Delete To Do Item");
        System.out.println("6. Save To Do List"); //eventually save the to do list back to the file -- Look at this site to write the file: https://crunchify.com/how-to-write-json-object-to-file-in-java/
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

    private void showTaskList(ArrayList<Task> listOfTasks) {
        System.out.println("\n\tList of Tasks");
        int count = 1;
        for (Task t : listOfTasks) {
            System.out.println(count + " - " + t.getName() + "\n\t\tDue " + t.getDueDate() + "\n\t\tTask in progress? " + t.isInProgress() + "\n\t\tTask completed? " + t.isCompleted());
            count++;
        }
    }

    //create method to accept true or false answer

    private void editTask(ArrayList<Task> listOfTasks) {
        showTaskList(listOfTasks);
        String prompt = "\nType the number of the item you want to edit and press Enter or simply press Enter to return to Main Menu without editing a To Do Item";
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
                String editPiece;
                do {
                    displayEditMenu();
                    prompt = "\nUsing the options on the Edit Menu please type the number 1 to 4 of the piece of the task you want to edit or press Enter to return to the Main Menu";
                    editPiece = callScanner(prompt);
                    Task task = listOfTasks.get(responseReturnedInt - 1); //this tells what item to grab
                    switch (editPiece) {
                        case "1":
                            prompt = "\nType new Task Name to display";
                            System.out.println("Current Task Name: " + task.getName());
                            String editedTaskName = callScanner(prompt);
                            task.setName(editedTaskName); //this sets the edited task name
                            showTaskList(listOfTasks);
                            break;
                        case "2":
                            String editedDatePrompt = "\nType the new Due Date (YYYY-MM-DD)";
                            System.out.println("Current Due Date: " + task.getDueDate());
                            LocalDate editedDueDate = LocalDate.parse(callScanner(editedDatePrompt));
                            task.setDueDate(editedDueDate.toString()); //this sets the edited due date
                            showTaskList(listOfTasks);
                            break;
                        case "3":
                            String editedProgressPrompt = "\nType the In Progress status (true/false)";
                            Boolean editedProgress = Boolean.valueOf(callScanner(editedProgressPrompt).toLowerCase(Locale.ROOT));
                            task.setInProgress(editedProgress); //this sets the in progress status
                            showTaskList(listOfTasks);
                            break;
                        case "4":
                            String editedCompletePrompt = "\nType the Complete status (true/false)";
                            Boolean editedComplete = Boolean.valueOf(callScanner(editedCompletePrompt).toLowerCase(Locale.ROOT));
                            task.setCompleted(editedComplete); //this sets the completed status
                            showTaskList(listOfTasks);
                            break;
                        case "":
                            askIfFinished();
                            break;
                        default:
                            System.out.println("\nYou chose an invalid option");
                            break;
                    }
                } while (!editPiece.equals(""));
            }
        }
    }

    private void displayEditMenu(){
        System.out.println("\n\tEdit Menu");
        System.out.println("\n1. Edit Task Name");
        System.out.println("2. Edit Task Due Date");
        System.out.println("3. Edit In Progress Status");
        System.out.println("4. Edit Completed Status");

        return;
    }


    private void addTask(ArrayList<Task> listOfTasks) {
        showTaskList(listOfTasks);
        String prompt = "\nType the wording for the item you wish to add and press Enter or simply press Enter to return to Main Menu without adding a To Do Item";
        String taskName = callScanner(prompt);
        if (taskName.equals(""))
            askIfFinished();
        else {
            String dueDatePrompt = "\nType the Due Date for " + taskName + " (YYYY-MM-DD)";
            LocalDate taskDueDate = LocalDate.parse(callScanner(dueDatePrompt));
            Task newTask = new Task(taskName, taskDueDate, false, false);
            System.out.println("You chose to add task: " + taskName + " with a due date of " + taskDueDate);
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


    private static String convertListOfObjectsToJson(ArrayList <Task> taskList) {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writeValueAsString(taskList);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return result;
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
        String jsonList = convertListOfObjectsToJson(listOfTasks);

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

//how do I convert a java object array list to JSON.  Example can be found here: https://www.geeksforgeeks.org/convert-java-object-to-json-string-using-jackson-api/

}
