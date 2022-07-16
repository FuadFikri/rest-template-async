package id.fikri;

import id.fikri.model.Todo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncService {


    private final RestTemplate restTemplate;

    @Async("asyncExecutor")
    public CompletableFuture<List<Todo>> getTodos() throws InterruptedException
    {
        log.info("getTodos starts");

        Todo[] todos = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/", Todo[].class);

        log.info("todos, {}", todos);
        Thread.sleep(2000L);  //Intentional delay
        log.info("todos completed");

        return CompletableFuture.completedFuture(List.of(todos));
    }

    @Async("asyncExecutor")
    public CompletableFuture<Todo> getSingleTodo() throws InterruptedException
    {
        log.info("getSingleTodo starts");

        Todo todo = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/1", Todo.class);

        log.info("todo, {}", todo);
        Thread.sleep(2000L);  //Intentional delay
        log.info("todo completed");

        return CompletableFuture.completedFuture(todo);
    }


}
