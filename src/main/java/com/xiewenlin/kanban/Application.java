package com.xiewenlin.kanban;

import com.blade.Blade;
import com.xiewenlin.kanban.model.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName Application
 * @Description
 * @Date 2019/6/25 18:04
 * @Version V1.0.0
 */
public class Application {
    private static final List<Todo> todoList = new ArrayList<>();
    public static void main(String[] args) {
        Blade.of()
                .get("/", ctx -> ctx.render("index.html"))
                .post("/add", (request, response) -> {
                    request.query("todo").ifPresent(todo -> todoList.add(new Todo(todo, Todo.ACTIVE)));
                    System.out.println("TodoList: " + todoList);
                    response.text("success");
                })
                .post("/edit", (request, response) -> {
                    request.query("oldTodo").ifPresent(todo ->
                            todoList.stream().filter(t -> t.getValue().equals(todo))
                                    .findFirst()
                                    .ifPresent(todoModel -> todoModel.setValue(todo))
                    );
                    System.out.println("TodoList: " + todoList);
                    response.text("success");
                })
                .post("/remove", (request, response) -> {
                    request.query("todo").ifPresent(todo ->
                            todoList.stream().filter(t -> t.getValue().equals(todo))
                                    .findFirst()
                                    .ifPresent(todoModel -> todoList.remove(todoModel)));
                    System.out.println("TodoList: " + todoList);
                    response.text("success");
                })
                .post("/status/:status", (request, response) -> {
                    String status = request.pathString("status");
                    request.query("todo").ifPresent(todo ->
                            todoList.stream().filter(t -> t.getValue().equals(todo))
                                    .findFirst()
                                    .ifPresent(todoModel -> todoModel.setStatus(status)));
                    System.out.println("TodoList: " + todoList);
                    response.text("success");
                })
                .post("/clean", (request, response) -> {
                    todoList.clear();
                    response.text("success");
                })
                .start(Application.class, args);
    }
}
