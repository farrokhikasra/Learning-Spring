package com.firstapp.springboot.myfirstwebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();

    private static int todosount = 0;

    static {
        todos.add(new Todo(++todosount, "kasra", "Learn AWS",
                LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosount, "kasra", "Learn DevOps",
                LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todosount, "sarah", "Learn Full Stack Development",
                LocalDate.now().plusYears(3), false));
    }

    public static List<Todo> findByUsername(String username) {
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
        ++todosount;
        Todo todo = new Todo(++todosount, username, description, targetDate, done);
        todos.add(todo);
    }

    public void deleteById(int id){
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id){
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
