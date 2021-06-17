import java.util.Date;

public class Task implements java.io.Serializable{

    private String taskName;
    private String taskDescription;
    private Date taskDate; //Constructor : Date(int year, int month, int day)
    private double taskCompletion;

    public Task(String name, String description, int year, int month, int day, double completion){

        this.taskName = name;
        this.taskDescription = description;
        this.taskDate = new Date(year, month, day);
        this.taskCompletion = completion;

    }


    public Object[] GetTask(){
        Object[] objects = {this.taskName,this.taskDescription,this.taskDate,this.taskCompletion};
        return objects;
    }

    public String GetDateString(){
        return(String.valueOf(this.taskDate.getDay()) + " " + String.valueOf(this.taskDate.getMonth()) + " " + String.valueOf(this.taskDate.getYear()));
    }

    //Need a load task function that will load from disk
    //Need a save task function that will save to disk

}
