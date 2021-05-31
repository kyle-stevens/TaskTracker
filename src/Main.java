import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;


//Create a Class for FileIO

public class Main {

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
        RunGUI(testList);

        //Application.launch(args);
        return;
    }

    static void RunGUI(TaskList testList){
        
    }

    /*
    @Override
    public void start(Stage stage){

        //LoadData(testListGUI);

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

        //Currently Using a Static Size with No Dynamic Resizing
        //Basic Layout Architecture:
        //--Stage stage
        //----HBox panels
        //------Vbox
        //--------HBox
        //----------JFRAME -> Collections Select Option/Dropdown
        //----------AddButton - Later Implementation for collection
        //----------Delete Button - Later Implementation for collection
        //--------JFRAME -> Tasks
        //------Vbox
        //--------Description of Selected Task
        //--------VBox
        //----------Add Button
        //----------Delete Button



        //Using Textfield/box as prototyping blank
        HBox mainPanels = new HBox();
        System.out.println(stage.getHeight());
        VBox leftPane = new VBox();
        //Becomes JComboBox under JFrame
        ComboBox fillerTaskDropDown = new ComboBox();
            //fillerTaskDropDown.setMinHeight(APP_SIZE[1] / 6);
            fillerTaskDropDown.setMinWidth(APP_SIZE[0] / 3);
            for(int i = 0; i < testListGUI.GetTaskNumber(); i++){
                fillerTaskDropDown.getItems().add(testListGUI.GetTaskByIndex(i).GetTask()[0]);
            }

            //Still Requires Listener to handle changes in choice

//Needs to Be under a Frame
        JList fillerTasksList = new JList();
            //fillerTasksList.setMinHeight(5 * APP_SIZE[1] / 6);
            //fillerTasksList.setMinWidth(APP_SIZE[0] / 3);

        leftPane.getChildren().add(fillerTaskDropDown);
        //leftPane.getChildren().add(fillerTasksList);





        VBox rightPane = new VBox();
        TextField fillerDescriptionBox = new TextField();
            fillerDescriptionBox.setPrefHeight(2 * APP_SIZE[1] / 3);
            fillerDescriptionBox.setPrefWidth(2 * APP_SIZE[0] / 3);
        Button addButton = new Button("Add");
            addButton.setPrefSize(2 * APP_SIZE[0] / 3, APP_SIZE[1] / 6);
        Button deleteButton = new Button("Delete");
            deleteButton.setPrefSize(2 * APP_SIZE[0] / 3, APP_SIZE[1] / 6);

        rightPane.getChildren().addAll(fillerDescriptionBox, addButton, deleteButton);

        // Add the Text to the VBox
        mainPanels.getChildren().addAll(leftPane,rightPane);

        // Set the Size of the VBox
        mainPanels.setMinSize(350, 250);

        // Create the Scene
        Scene scene = new Scene(mainPanels);

        // Set the Properties of the Stage
        stage.setX(100);
        stage.setY(200);
        stage.setMinHeight(APP_SIZE[1]);
        stage.setMinWidth(APP_SIZE[0]);
        stage.setResizable(false); //Setting to Static Size Until Further Development

        // Add the scene to the Stage
        stage.setScene(scene);
        // Set the title of the Stage
        stage.setTitle("Your first JavaFX Example");
        // Display the Stage
        stage.show();
        /*
        // Create the Text
        //Text text = new Text("Hello JavaFX");
        // Create the HBox
        HBox root = new HBox();
        Pane pane1 = new Pane();
        pane1.setMinWidth(200);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxWidth(200);

        Text text = new Text("Lorem Ipsum and other such weird stuff to try and figure out how to create a scroll pane object. Will eventually become a scroll list of selectable objects and tasks to further inspect." +
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA  AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" +
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        text.setWrappingWidth(180);
        scrollPane.setContent(text);

        // Add the Text to the VBox
        root.getChildren().add(pane1);
        root.getChildren().add(scrollPane);
        // Set the Size of the VBox
        root.setMinSize(350, 250);

        // Create the Scene
        Scene scene = new Scene(root);

        // Set the Properties of the Stage
        stage.setX(100);
        stage.setY(200);
        stage.setMinHeight(300);
        stage.setMinWidth(400);

        // Add the scene to the Stage
        stage.setScene(scene);
        // Set the title of the Stage
        stage.setTitle("Your first JavaFX Example");
        // Display the Stage
        stage.show();
    }*/

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
