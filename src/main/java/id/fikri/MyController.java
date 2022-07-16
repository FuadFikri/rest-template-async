package id.fikri;


import id.fikri.model.Todo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MyController {

    private final AsyncService asyncService;
    private final SyncService syncService;

    @GetMapping("/test-async")
    public void getData() throws InterruptedException {
        log.info("start");

        CompletableFuture<List<Todo>> todos = asyncService.getTodos();
        CompletableFuture<Todo> todo = asyncService.getSingleTodo();


        CompletableFuture.allOf(todos,todo).join();
        log.info("finsih");
    }


    @GetMapping("/test-sync")
    public void getDataSync() throws InterruptedException {
        log.info("start");

        List<Todo> todos = syncService.getTodos();
        Todo todo = syncService.getSingleTodo();


        log.info("finsih");
    }


}
