package com.firstapp.springboot.myfirstwebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();

    private static int todosount = 0;

    static {
        todos.add(new Todo(++todosount, "in28minutes", "Learn AWS",
                LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosount, "in28minutes", "Learn DevOps",
                LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todosount, "in28minutes", "Learn Full Stack Development",
                LocalDate.now().plusYears(3), false));
    }

    public static List<Todo> findByUsername(String username) {
        return todos;
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
        ++todosount;
        Todo todo = new Todo(++todosount, username, description, targetDate, done);
        todos.add(todo);
    }
}
