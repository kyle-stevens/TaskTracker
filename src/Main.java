
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.*;
import javafx.collections.*;

import java.io.*;
import java.util.List;
import java.util.Scanner;



//Create a Class for FileIO

public class Main extends Application{

    static int[] APP_SIZE = { 600 , 400};

    public static void main(String[] args){

        TaskList testList = new TaskList();
        char choice = 'z';
        Scanner scan = new Scanner(System.in);
        //LoadData(testList);
        //Loading the Data

        try {
            FileInputStream fileIn = new FileInputStream("./test.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            testList = (TaskList) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();

        }

        do{

            System.out.println("TaskTracker Test Menu" +
                    "\n\tPlease Choose an Option for Testing..." +
                    "\n\t\ta\t:\tAdd a Task" +
                    "\n\t\td\t:\tDelete a Task" +
                    "\n\t\tv\t:\tView Tasks" +
                    "\n\t\tq\t:\tQuit Debug Menu");
            choice = scan.next().charAt(0);
            Option(choice, testList, scan);

        }while(choice != 'q');
        scan.close();
        //SaveData(testList);

        //Saving the Data
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("./test.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(testList);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /test.ser");

        } catch (IOException i) {
            i.printStackTrace();

        }
        //RunGUI(testList);

        Application.launch(args);
        return;
    }

    static void RunGUI(TaskList testList){
        Application.launch();
    }


    @Override
    public void start(Stage stage){
        //Loading the Data
        TaskList testListGUI = new TaskList();
        try {
            FileInputStream fileIn = new FileInputStream("./test.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            testListGUI = (TaskList) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();

        }

        /*
        Hbox
            Vbox
                Dropdown List
                Hbox
                    Add Collection button
                    Delete Collection button
                List
            Vbox
                Textfield/Label
                Hbox
                    Add task button
                    Delete task button
         */
        Pane root = new Pane();
        Scene scene = new Scene(root, 400, 400);
        HBox application = new HBox();
            VBox leftPane = new VBox();
                ComboBox collectionList = new ComboBox();
                HBox collectionButtons = new HBox();
                    Button addCollection = new Button("NEW COLLECTION");
                    Button deleteCollection = new Button("DELETE COLLECTION");
                collectionButtons.getChildren().addAll(addCollection, deleteCollection);
                ListView<Task> taskList = new ListView<Task>();
            leftPane.getChildren().addAll(collectionList, collectionButtons, taskList);




            VBox rightPane = new VBox();
                Label taskDescription = new Label();
                HBox taskButtons = new HBox();
                    Button addTaskButton = new Button("ADD TASK");
                    Button deleteTaskButton = new Button("DELETE TASK");
                taskButtons.getChildren().addAll(addTaskButton, deleteTaskButton);
            rightPane.getChildren().addAll(taskDescription, taskButtons);
        application.getChildren().addAll(leftPane, rightPane);
        root.getChildren().addAll(application);




        stage.setScene(scene);
        stage.show();
    }

    public static void Option(char choice, TaskList list, Scanner scan){

        if(choice == 'a'){
            System.out.println("Enter Name, Description, Year, Month, Day, and Completion on different lines");
            String name = scan.next();
            String desc = scan.next();
            int year = scan.nextInt();
            int month = scan.nextInt();
            int day = scan.nextInt();
            int completion = scan.nextInt();

            list.AddTask(new Task(name, desc, year, month, day, completion));
        }
        else if(choice == 'd'){
            if(list.GetTaskNumber() > 0) {
                System.out.println("Please Select a Task to delete");

                for (int i = 0; i < list.GetTaskNumber(); i++) {
                    System.out.println(i + " " + list.GetTaskByIndex(i).GetTask()[0]);
                }
                System.out.print("...");
                //scan.nextInt();
                list.RemoveTask(list.GetTaskByIndex(scan.nextInt()));
            }
            else{
                System.out.println("No Tasks in List");
            }

        }
        else if(choice == 'v'){
            for (int i = 0; i < list.GetTaskNumber(); i++) {
                System.out.println(i + " " + list.GetTaskByIndex(i).GetTask()[0]);
            }
        }
        else if(choice == 'q'){

            return;
        }
        else{
            System.out.println("Not a valid option.");
        }

    }


    public static boolean SaveData(TaskList list){

        //Saving the Data
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("./test.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /test.ser");
            return true;
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        }

    }

    public static boolean LoadData(TaskList list){

        //Loading the Data
        try {
            FileInputStream fileIn = new FileInputStream("./test.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            list = (TaskList) in.readObject();
            in.close();
            fileIn.close();
            return true;
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return false;
        }

    }
}
