package example.micronaut.service

import example.micronaut.TaskDTO
import example.micronaut.domain.Task
import java.util.*
import javax.annotation.PostConstruct
import javax.inject.Singleton


@Singleton
class TaskService {

    companion object{
        lateinit var tasksList: MutableList<Task>
    }


    @PostConstruct
    fun init(){
        tasksList = mutableListOf(
            Task(UUID.randomUUID().toString(), "task 1", false),
            Task(UUID.randomUUID().toString(), "task 2", true),
            Task(UUID.randomUUID().toString(),"task 3", true)
        )
    }


    fun findAllTasks(): List<Task>{
        return tasksList;
    }

    fun findTaskById(id : String): Optional<Task>{
        return tasksList.stream().filter{ it.id.equals(id) }.findFirst();
    }

    /**
     * If task exists then delete it and return true, else return false;
     */
    fun deleteTask(task: Task): Boolean{
        return tasksList.remove(task);
    }

    fun createTask(taskDto : TaskDTO): Task{
        val task = Task(UUID.randomUUID().toString(), taskDto.name, taskDto.isDone)
        tasksList.add(task);
        return task
    }




}