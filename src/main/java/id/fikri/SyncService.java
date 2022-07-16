package id.fikri;

import id.fikri.model.Todo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class SyncService {

    private final RestTemplate restTemplate;

    public List<Todo> getTodos() throws InterruptedException {
        log.info("getTodos starts");

        Todo[] todos = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/", Todo[].class);

        log.info("todos, {}", todos);
        Thread.sleep(2000L);  //Intentional delay
        log.info("todos completed");

        return List.of(todos);
    }


    public Todo getSingleTodo() throws InterruptedException {
        log.info("getSingleTodo starts");

        Todo todo = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/1", Todo.class);

        log.info("todo, {}", todo);
        Thread.sleep(2000L);  //Intentional delay
        log.info("todo completed");

        return todo;
    }

}
