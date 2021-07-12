/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Benjamin Giang
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    // FXML Stuff
    ObservableList<Item> ToDoList = FXCollections.observableArrayList();

    @FXML TextField descriptionTextField;
    @FXML DatePicker dateTextField;
    @FXML TableView<Item> displayTable;
    @FXML TableColumn<Item, String> descriptionColumn;
    @FXML TableColumn<Item, String> dateColumn;
    @FXML TableColumn<Item, String> completedColumn;

    @FXML
    public void AddItemClicked(ActionEvent actionEvent) {
        String desc = descriptionTextField.getText();
        String date = dateTextField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Boolean comp = false;
        if(desc.length() < 257)
        {
            itemAdd(desc, date, comp);
        }

    }

    @FXML
    public void DeleteItemClicked(ActionEvent actionEvent) {
        itemDelete();
    }

    @FXML
    public void ClearListClicked(ActionEvent actionEvent) {

        clearList();
    }

    @FXML
    public void updateDescClicked(ActionEvent actionEvent) {
        String desc = descriptionTextField.getText();
        if(desc.length() < 257)
        {
            itemUpdateDesc(desc);
        }
    }

    @FXML
    public void UpdateDateClicked(ActionEvent actionEvent) {
        String date = dateTextField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        itemUpdateDate(date);
    }

    @FXML
    public void MarkCompleteClicked(ActionEvent actionEvent) {

        itemMarkComplete();
    }

    @FXML
    public void MarkIncompleteClicked(ActionEvent actionEvent) {

        itemMarkIncomplete();
    }


    @FXML
    public void DisplayAllClicked(ActionEvent actionEvent) {

        displayAll();
    }

    @FXML
    public void DisplayCompleteClicked(ActionEvent actionEvent) {
        displayCompleted();
    }

    @FXML
    public void DisplayIncompleteClicked(ActionEvent actionEvent) {
        displayIncomplete();
    }

    @FXML public void SaveListClicked(ActionEvent actionEvent) {
        saveList();
    }

    @FXML
    public void LoadListClicked(ActionEvent actionEvent) throws FileNotFoundException {
        loadList();
    }

    // Actual functions
    public ObservableList<Item> itemAdd(String desc, String date, Boolean comp)
    {
        // Takes the inputs desc, date, and comp
        // adds the item to the list
        ToDoList.add(new Item(desc, date, comp));
        displayAll();
        return ToDoList;
    }

    public ObservableList<Item> itemDelete()
    {
        // Removes the selected item from the list
        ToDoList.remove(displayTable.getSelectionModel().getSelectedItem());
        displayAll();
        return ToDoList;
    }

    public ObservableList<Item> clearList()
    {
        // Removes all items from the list
        ToDoList.clear();
        displayAll();
        return ToDoList;
    }
    public ObservableList<Item> itemUpdateDesc(String desc)
    {
        // Takes input from the description text box
        // Sets description of selected item
        ToDoList.get(displayTable.getSelectionModel().getSelectedIndex()).setDescription(desc);
        displayAll();
        return ToDoList;
    }

    public ObservableList<Item> itemUpdateDate(String date)
    {
        // Takes input from the date picker box
        // Sets date of selected item
        ToDoList.get(displayTable.getSelectionModel().getSelectedIndex()).setDueDate(date);
        displayAll();
        return ToDoList;
    }

    public ObservableList<Item> itemMarkComplete()
    {
        // Sets the selected item as completed
        ToDoList.get(displayTable.getSelectionModel().getSelectedIndex()).setCompleted(true);
        displayAll();
        return ToDoList;
    }

    public ObservableList<Item> itemMarkIncomplete()
    {
        // Sets the selected item as incomplete
        ToDoList.get(displayTable.getSelectionModel().getSelectedIndex()).setCompleted(false);
        displayAll();
        return ToDoList;
    }

    public void displayAll()
    {
        // Clears display table
        // Adds everything currently in the list to the display table
        displayTable.getItems().clear();
        for(int i = 0; i < ToDoList.size();i++)
        {
            displayTable.getItems().add(ToDoList.get(i));
        }
    }

    public void displayIncomplete()
    {
        // Clears display table
        // Checks if the item is incomplete
        // If yes then adds it to the display table
        displayTable.getItems().clear();
        for(int i = 0; i < ToDoList.size();i++)
        {
            if(!ToDoList.get(i).getCompleted())
            {
                displayTable.getItems().add(ToDoList.get(i));
            }
        }
    }

    public void displayCompleted()
    {
        // Clears display table
        // Checks if the item is complete
        // If yes then adds it to the display table
        displayTable.getItems().clear();
        for(int i = 0; i < ToDoList.size();i++)
        {
            if(ToDoList.get(i).getCompleted())
            {
                displayTable.getItems().add(ToDoList.get(i));
            }
        }
    }

    FileChooser fileChooser = new FileChooser();

    public void saveList()
    {
        // If file isn't null, check item of list
        // Add description, due date, completed to String "content"
        // Sends content to the saveText() function
        File file = fileChooser.showSaveDialog(new Stage());
        if(file != null)
        {
            String content = "";
            for(int i = 0; i < ToDoList.size();i++)
            {
                content += ToDoList.get(i).getDescription();
                content += "\n" + ToDoList.get(i).getDueDate();
                content += "\n" + ToDoList.get(i).getCompleted() + "\n";
            }
            saveText(file, content);
        }

    }

    public void saveText(File file, String content){
        // Saves the inputted string into a text file
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write(content);
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadList() throws FileNotFoundException {
        // Prompts user to select a file to be loaded
        // Inputs the file contents into an item
        // Runs the addItem() function to add each item to the list
        // displays the list once completed
        File file = fileChooser.showOpenDialog(new Stage());
        try {
            Scanner scanner = new Scanner(file);
            clearList();
            while (scanner.hasNextLine()) {
                String desc = scanner.nextLine();
                String date = scanner.nextLine();
                String temp = scanner.nextLine();
                Boolean comp = false;
                if (temp == "true") {
                    comp = true;
                } else {
                    comp = false;
                }
                itemAdd(desc, date, comp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("dueDate"));
        completedColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("Completed"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

    }

}
