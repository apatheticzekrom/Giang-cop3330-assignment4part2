package ControllerTests;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import ucf.assignments.Controller;
import ucf.assignments.Item;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class itemMarkIncompleteTest {
    @Test
    public void itemMarkIncompleteTest()
    {
        ObservableList<Item> ToDoList = FXCollections.observableArrayList();
        Controller controller = new Controller();

        Item test = new Item("abc", "2000-10-10", false);
        ToDoList.add(test);

        ObservableList<Item> actual = controller.itemAdd("abc", "2000-10-10", true);
        controller.itemMarkIncomplete(0);

        for(int i = 0; i < ToDoList.size();i++)
        {
            assertTrue(ToDoList.get(i).equals(actual.get(i)));
        }
    }
}
