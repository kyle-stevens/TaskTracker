import java.util.ArrayList;
import java.util.Collections;

public class TaskList implements java.io.Serializable{

    private ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<Task>();
    }

    public boolean AddTask(Task task){
        if(this.taskList.add(task)){
            return true;
        }
        return false;
    }

    public boolean RemoveTask(Task task){
        if(this.taskList.remove(task)){
            return true;
        }
        return false;
    }

    public int GetTaskNumber(){
        return this.taskList.size();
    }

    public Task GetTaskByIndex(int index){
        return this.taskList.get(index);
    }
}
