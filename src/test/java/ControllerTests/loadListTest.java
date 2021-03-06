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
        Item test = new Item("abc", "2020-10-20", false);
        ToDoList.add(test);

        Controller controller = new Controller();
        File file = new File("src/test/java/ControllerTests/testfile/testoutput.txt");
        ObservableList<Item> actual = controller.loadList(file);

//        System.out.printf("%s\n", ToDoList);
//        System.out.printf("%s", actual);

        for(int i = 0; i < ToDoList.size();i++)
        {
            assertTrue(ToDoList.get(i).equals(actual.get(i)));

        }
    }
}
