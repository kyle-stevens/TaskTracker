import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        TaskList testList = new TaskList();
        char choice = 'z';
        Scanner scan = new Scanner(System.in);
        do{

            System.out.println("TaskTracker Test Menu" +
                    "\n\tPlease Choose an Option for Testing..." +
                    "\n\t\ta\t:\tAdd a Task" +
                    "\n\t\td\t:\tDelete a Task" +
                    "\n\t\tq\t:\tQuit Debug Menu");
            choice = scan.next().charAt(0);
            Option(choice, testList, scan);

        }while(choice != 'q');
        scan.close();
        return;
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
        else if(choice == 'q'){
            return;
        }
        else{
            System.out.println("Not a valid option.");
        }

    }


    public boolean SaveData(TaskList list){

        //Saving the Data
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("./data.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /data.ser");
            return true;
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        }

    }

    public boolean LoadData(TaskList list){

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
