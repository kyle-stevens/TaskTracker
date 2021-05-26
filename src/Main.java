import java.io.*;

public class Main {

    public static void main(String[] args){

        TaskList testList = new TaskList();

        for(int i = 0; i < 100; i++){
            testList.AddTask(new Task("S" + String.valueOf(i),String.valueOf(i),i,i,i,i));
            System.out.println(testList.GetTaskByIndex(i).GetTask()[0]);
        }

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("./test.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(testList);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /employee.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }



        try {
            FileInputStream fileIn = new FileInputStream("./test.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            testList = (TaskList) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }

        for(int i = 0; i < 100; i++){

            System.out.println(testList.GetTaskByIndex(i).GetTask()[0]);
        }

    }
}
