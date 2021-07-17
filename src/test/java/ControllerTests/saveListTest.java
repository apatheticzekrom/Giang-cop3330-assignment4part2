package ControllerTests;

import org.junit.jupiter.api.Test;
import ucf.assignments.Controller;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class saveListTest {
    @Test
    public void saveListTest() throws IOException {
        File file = new File("src/test/java/ControllerTests/testfile/testoutput.txt");
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write("abc\n" + "2020-10-20\n" + "false\n");
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Controller controller = new Controller();
        File file2 = new File("src/test/java/ControllerTests/testfile/newtestoutput.txt");
        controller.saveText(file2 , "abc\n" + "2020-10-20\n" + "false\n");

        assertTrue(check());

    }

    private boolean check() throws IOException {

        BufferedReader file1 = new BufferedReader(new FileReader("src/test/java/ControllerTests/testfile/testoutput.txt"));
        BufferedReader file2 = new BufferedReader(new FileReader("src/test/java/ControllerTests/testfile/newtestoutput.txt"));

        String line1 = file1.readLine();
        String line2 = file2.readLine();

        boolean areEqual = true;

        while (line1 != null || line2 != null)
        {
            if(line1 == null || line2 == null)
            {
                areEqual = false;
                break;
            }
            else if(!line1.equalsIgnoreCase(line2))
            {
                areEqual = false;
                break;
            }
            line1 = file1.readLine();
            line2 = file2.readLine();
        }
        
        file1.close();
        file2.close();

        return areEqual;
    }
}
