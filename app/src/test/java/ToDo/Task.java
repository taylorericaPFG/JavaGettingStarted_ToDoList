package ToDo;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Task {
    private String name;
    private LocalDate dueDate;
    private boolean inProgress;
    private boolean completed;

    public Task() {
    }

    public Task(String name) {
        this.name = name;
    }

    public Task(String ironing, boolean b, boolean b1) {
    }
    public Task(String name, LocalDate dueDate, boolean inProgress, boolean completed) {
        this.name = name;
        this.dueDate = dueDate;
        this.inProgress = inProgress;
        this.completed = completed;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public LocalDate getDueDate() {

        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {

        this.dueDate = dueDate;
    }

    public boolean isInProgress() {

        return inProgress;
    }

    public void setInProgress(boolean inProgress) {

        this.inProgress = inProgress;
    }

    public boolean isCompleted() {

        return completed;
    }

    public void setCompleted(boolean completed) {

        this.completed = completed;
    }

}

