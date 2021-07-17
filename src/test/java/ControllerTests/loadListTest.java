package ControllerTests;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import ucf.assignments.Controller;
import ucf.assignments.Item;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class loadListTest {
    @Test
    public void loadListTest() throws FileNotFoundException {

        ObservableList<Item> ToDoList = FXCollections.observableArrayList();
        Item test = new Item("abc", "2000-10-20", false);
        ToDoList.add(test);

        Controller controller = new Controller();
        File file = new File("src/test/java/ControllerTests/testfile/testoutput.txt");
        ObservableList<Item> actual = controller.loadList(file);


        contentEquals(ToDoList, actual);

    }

    public boolean contentEquals(ObservableList<Item> ToDoList, ObservableList<Item> actual) {
        boolean is = true;

        for (int i = 0; i < ToDoList.size(); i++) {
            System.out.printf("%s %s", ToDoList.get(i).getDescription(), actual.get(i).getDescription());
            System.out.printf("%s %s", ToDoList.get(i).getDueDate(), actual.get(i).getDueDate());
            System.out.printf("%s %s", ToDoList.get(i).getCompleted(), actual.get(i).getCompleted());

            if (ToDoList.get(i).getDescription() != actual.get(i).getDescription())
            {
                is = false;
            }

            if (ToDoList.get(i).getDueDate() != actual.get(i).getDueDate())
            {
                is = false;
            }

            if (ToDoList.get(i).getCompleted() != actual.get(i).getCompleted())
            {
                is = false;
            }
        }

        System.out.print(is);
        return is;
    }
}
