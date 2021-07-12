/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Benjamin Giang
 */

package ucf.assignments;

public class Item
{
    private String description;
    private String dueDate;
    private Boolean isCompleted;

    // Constructors
    public Item(String description, String dueDate, Boolean isCompleted) {
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }

    // Getters & Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public boolean itemEquals(Item other)
    {
        if(this.isCompleted != other.isCompleted){
            return false;
        }
        if(this.dueDate != other.dueDate){
            return false;
        }
        if(this.description != other.description){
            return false;
        }
        return true;
    }

}
