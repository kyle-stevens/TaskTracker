import java.util.ArrayList;


public class CollectionsList {

    private ArrayList<TaskList> collectionsList;

    public CollectionsList(){
        this.collectionsList = new ArrayList<TaskList>();
    }

    public boolean AddTask(TaskList taskList){
        if(this.collectionsList.add(taskList)){
            return true;
        }
        return false;
    }

    public boolean RemoveTask(TaskList taskList){
        if(this.collectionsList.remove(taskList)){
            return true;
        }
        return false;
    }

    public int GetTaskNumber(){
        return this.collectionsList.size();
    }

    public TaskList GetTaskByIndex(int index){
        return this.collectionsList.get(index);
    }

}
