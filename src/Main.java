
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.*;
import javafx.collections.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.*;
import java.util.List;
import java.util.Scanner;



//Create a Class for FileIO

public class Main extends Application{

    TaskList testListGUI = new TaskList();
    static int[] APP_SIZE = { 600 , 400};

    public static void main(String[] args){

        TaskList testList = new TaskList();
        char choice = 'q';
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
                Dropdown List (1/3, std)
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
        Scene scene = new Scene(root, APP_SIZE[0], APP_SIZE[1]);
        root.setMaxSize(APP_SIZE[0], APP_SIZE[1]);
        root.setMinSize(APP_SIZE[0], APP_SIZE[1]);
        stage.setResizable(false);


        HBox application = new HBox();
            VBox leftPane = new VBox();
            leftPane.setPrefWidth(APP_SIZE[0] /3);
            leftPane.setTranslateX(2);
                ComboBox collectionList = new ComboBox();
                collectionList.setPrefWidth(APP_SIZE[0] / 3);
                HBox collectionButtons = new HBox();
                    Button addCollection = new Button("ADD LIST");
                    addCollection.setPrefWidth(APP_SIZE[0] / 6);
                    Button deleteCollection = new Button("DELETE LIST");
                    deleteCollection.setPrefWidth(APP_SIZE[0] / 6);
                collectionButtons.getChildren().addAll(addCollection, deleteCollection);
                ListView<String> taskList = new ListView<String>();
                taskList.setPrefHeight(APP_SIZE[1] - collectionList.getHeight() - addCollection.getHeight() - 51);
            leftPane.getChildren().addAll(collectionList, collectionButtons, taskList);




            VBox rightPane = new VBox();
            rightPane.setTranslateX(5);
                TextField taskDescription = new TextField();
                taskDescription.setPrefSize(2 * APP_SIZE[0] /3, 5 * APP_SIZE[1] / 6);
                taskDescription.setEditable(false);
                HBox taskButtons = new HBox();
                    Button addTaskButton = new Button("ADD TASK");
                    addTaskButton.setPrefSize(APP_SIZE[0] / 3, APP_SIZE[1] / 6);
                    Button deleteTaskButton = new Button("DELETE TASK");
                    deleteTaskButton.setPrefSize(APP_SIZE[0] / 3, APP_SIZE[1] / 6);
                taskButtons.getChildren().addAll(addTaskButton, deleteTaskButton);
            rightPane.getChildren().addAll(taskDescription, taskButtons);
        application.getChildren().addAll(leftPane, rightPane);
        root.getChildren().addAll(application);

        //Load Tasks into List
        for(int i = 0; i < testListGUI.GetTaskNumber(); i++){
            taskList.getItems().add(testListGUI.GetTaskByIndex(i).GetTask()[0].toString());
        }

        //Create Button Event Handlers
        EventHandler<ActionEvent> addTask = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                Stage newTaskStage = new Stage();
                newTaskStage.initModality(Modality.APPLICATION_MODAL);
                VBox box = new VBox();
                HBox buttons = new HBox();

                TextField taskNameField, taskDescriptionField, taskDateYearField, taskDateMonthField,
                        taskDateDayField, taskCompletionField;

                Label taskNameLabel, taskDescriptionLabel, taskDateYearLabel, taskDateMonthLabel,
                        taskDateDayLabel, taskCompletionLabel;

                taskNameLabel = new Label("Task Name");
                taskNameField = new TextField();

                taskDescriptionLabel = new Label("Task Description");
                taskDescriptionField = new TextField();

                taskDateYearLabel = new Label("Task Year");
                taskDateYearField = new TextField();

                taskDateMonthLabel = new Label("Task Month");
                taskDateMonthField = new TextField();

                taskDateDayLabel = new Label("Task Day");
                taskDateDayField = new TextField();

                taskCompletionLabel = new Label("Current Task Completion");
                taskCompletionField = new TextField();



                Button addTaskConfirm = new Button("Add");
                Button cancelTaskAdd = new Button("Cancel");
                buttons.getChildren().addAll(addTaskConfirm, cancelTaskAdd);

                box.getChildren().addAll(taskNameLabel,taskNameField,taskDescriptionLabel,taskDescriptionField,taskDateYearLabel,taskDateYearField,taskDateMonthLabel,taskDateMonthField,taskDateDayLabel,taskDateDayField,taskCompletionLabel,taskCompletionField,buttons);

                newTaskStage.setScene(new Scene(box,350,350));
                newTaskStage.show();

                EventHandler<ActionEvent> addTaskInternalButton = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        testListGUI.AddTask(new Task(taskNameField.getText().toString(),
                                taskDescriptionField.getText().toString(),
                                Integer.parseInt(taskDateYearField.getText()),
                                Integer.parseInt(taskDateMonthField.getText()),
                                Integer.parseInt(taskDateDayField.getText()),
                                Integer.parseInt(taskCompletionField.getText())));
                        taskList.getItems().add(testListGUI.GetTaskByIndex(testListGUI.GetTaskNumber() - 1).GetTask()[0].toString());
                        newTaskStage.close();
                    }
                };

                addTaskConfirm.setOnAction(addTaskInternalButton);


            }
        };
        addTaskButton.setOnAction(addTask);

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
