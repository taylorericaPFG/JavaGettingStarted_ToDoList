package ToDo;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.databind.type.CollectionType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class App {

    private static FileWriter file;

    public String getGreeting() {

        return "ToDo List Manager";
    }

    public void displayFirstMenu() {
        System.out.println("\tMain Menu");
        System.out.println("\n1. Display To Do Items");
        System.out.println("2. Edit To Do Item");
        System.out.println("3. Add To Do Item");
        System.out.println("4. Delete To Do Item");
        System.out.println("5. Save To Do List To File");
        System.out.println("6. Exit");
        return;
    }

    private String callScanner(String prompt) {
        System.out.println(prompt);
        String response = scanner.nextLine();
        return response;
    }

    private void displayTaskList(List<Task> listOfTasks) {
        showTaskList(listOfTasks);
        askIfFinished();
    }

    private void askIfFinished() {
        String prompt = "\nDo you want to return to the Main Menu? (Y/N)";
        String responseReturned = callScanner(prompt);

        if (responseReturned.equalsIgnoreCase("Y"))
            return;

        prompt = "\nDo you want to exit? (Y/N)";
        responseReturned = callScanner(prompt);
        if (responseReturned.equalsIgnoreCase("Y"))
            System.exit(0);
        else
            return;
    }

    private void showTaskList(List<Task> listOfTasks) {
        System.out.println("\n\tList of Tasks");
        int count = 1;
        for (Task t : listOfTasks) {
            System.out.println(count + " - " + t.getName() + "\n\t\tDue " + t.getDueDate() + "\n\t\tTask in progress? " + t.getInProgress() + "\n\t\tTask completed? " + t.getCompleted());
            count++;
        }
    }

    private void editTask(List<Task> listOfTasks) {
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

    private void displayEditMenu() {
        System.out.println("\n\tEdit Menu");
        System.out.println("\n1. Edit Task Name");
        System.out.println("2. Edit Task Due Date");
        System.out.println("3. Edit In Progress Status");
        System.out.println("4. Edit Completed Status");

        return;
    }

    private void addTask(List<Task> listOfTasks) {
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

    private void deleteTask(List<Task> listOfTasks) {
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

    private static String saveListToJsonFile(List<Task> taskList) {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writeValueAsString(taskList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            file = new FileWriter("C:/Users/s306717/Java/JavaGettingStarted_ToDoList/Tasklist.json");
            file.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(result);
        return result;
    }

        Scanner scanner = new Scanner(System.in);
        static List<Task> listOfTasks = new ArrayList<>();
        public static void main(String[] args) throws IOException {
            App app = new App();

                //opening the saved file
                JSONParser parser = new JSONParser();
                ArrayList<JSONObject> jsonObject = null;
                try {
                    Object obj = parser.parse(new FileReader("C:/Users/s306717/Java/JavaGettingStarted_ToDoList/Tasklist.json"));
                    jsonObject = (ArrayList<JSONObject>) obj;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //mapping json file to the task list
                String response = String.valueOf(jsonObject);
                ObjectMapper mapper = new ObjectMapper();
                List<Task> taskArray = null;
                try {
                    CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Task.class);
                    taskArray = mapper.readValue(response,listType);
                }catch (IOException e) {
                    e.printStackTrace();
                }
                ArrayList<Task> listOfTasks = new ArrayList<>(taskArray);

            String responseReturned;
            do {
                app.displayFirstMenu();

                String prompt = "\nPlease input an option 1 to 6:";
                responseReturned = app.callScanner(prompt);

                System.out.println("\nYour response was: " + responseReturned);
                switch (responseReturned) {
                    case "1":
                        System.out.println("You chose to display the To Do List");
                        app.displayTaskList(listOfTasks);
                        break;
                    case "2":
                        System.out.println("You chose to edit a To Do Item");
                        app.editTask(listOfTasks);
                        break;
                    case "3":
                        System.out.println("You chose to add a To Do Item");
                        app.addTask(listOfTasks);
                        break;
                    case "4":
                        System.out.println("You chose to delete a To Do Item");
                        app.deleteTask(listOfTasks);
                        break;
                    case "5":
                        System.out.println("You chose to save the To Do List to C:Users>s306717>Java>JavaGettingStarted_ToDo_list>Tasklist.json");
                        saveListToJsonFile(listOfTasks);
                        app.askIfFinished();
                        break;
                    case "6":
                        System.out.println("You chose to exit the To Do List");
                        break;
                    default:
                        System.out.println("You chose an invalid option. Please choose a number from 1 to 6");
                        break;
                }
            } while (!responseReturned.equals("6"));
            app.scanner.close();
        }


//At end look for duplicate code to be able to call a function to refactor it

}
