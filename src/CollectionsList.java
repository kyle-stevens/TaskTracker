import java.util.ArrayList;


public class CollectionsList implements java.io.Serializable{
    public String collectionName;
    private TaskList collectionsList;

    public CollectionsList(String collection_name){
        this.collectionsList = new TaskList();
        this.collectionName =     collection_name;
    }

    public boolean AddTask(Task task){
        if(this.collectionsList.AddTask(task)){
            return true;
        }
        return false;
    }

    public boolean RemoveTask(Task task){
        if(this.collectionsList.RemoveTask(task)){
            return true;
        }
        return false;
    }

    public int GetTaskNumber(){
        return this.collectionsList.GetTaskNumber();
    }

    public TaskList GetTaskList(){
        return this.collectionsList;
    }

}
