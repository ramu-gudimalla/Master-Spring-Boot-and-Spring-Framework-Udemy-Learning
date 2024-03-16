package com.springtutorial.todowebapplication.todo;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Controller
@SessionAttributes("name")
public class todoController {
    @Autowired
    private TodoService todoService;
    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap modelMap){
        List<Todo> todos = todoService.findByUserName("Ram");
        modelMap.addAttribute("todos",todos);
        return "listTodos";
    }
    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodo(ModelMap modelMap){
        String userName = (String)modelMap.get("name");
        Todo todo = new Todo(0,userName,"",LocalDate.now().plusYears(1),false);
        modelMap.put("todo",todo);
        return "todo";
    }
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap modelMap, @Valid @ModelAttribute("todo") Todo todo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "todo";
        }
        String userName = (String)modelMap.get("name");
        todoService.addTodo(userName,todo.getDescription(), LocalDate.now().plusYears(1),false);
        return "redirect:list-todos";
    }
    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id){
        todoService.deleteById(id);
        return "redirect:list-todos";
    }
    @RequestMapping(value = "update-todo",method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap modelMap){
        Todo todo = todoService.findById(id);
        modelMap.addAttribute(todo);
        return "todo";
    }
    @RequestMapping(value = "update-todo",method = RequestMethod.POST)
    public String UpdateTodo(@RequestParam int id, ModelMap modelMap, @Valid Todo todo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "todo";
        }
        String userName = (String)modelMap.get("name");
        todo.setUserName(userName);
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }

}
