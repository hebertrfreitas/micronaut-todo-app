package example.micronaut.controller

import example.micronaut.TaskDTO
import example.micronaut.domain.Task
import example.micronaut.service.TaskService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*


@Controller("tasks")
class TasksController(private val taskService : TaskService ) {

        @Get(produces = [MediaType.APPLICATION_JSON])
        fun getTasks() : HttpResponse<List<Task>>{
            return HttpResponse.ok(taskService.findAllTasks())
        }

        @Get("/{id}")
        fun getTaskById(@PathVariable id: String): HttpResponse<Any> {
                val optionalTask = taskService.findTaskById(id);

                if(optionalTask.isPresent){
                        return HttpResponse.ok(optionalTask.get())
                }else{
                        return HttpResponse.notFound();
                }
        }


        @Post(consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
        fun createTask(@Body taskDTO: TaskDTO): MutableHttpResponse<Task> {
                return HttpResponse.created(taskService.createTask(taskDTO))
        }


        @Delete(value = "{id}", consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
        fun delete(@PathVariable id: String): HttpResponse<Any> {
                val optionalTask = taskService.findTaskById(id);

                if (optionalTask.isPresent) {
                        taskService.deleteTask(optionalTask.get())
                        return HttpResponse.noContent();
                }else{
                        return HttpResponse.notFound()
                }

        }




}