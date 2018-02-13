package umm3601.todo;


import org.junit.Test;

import java.util.Map;
import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class FilterByKeyword {

  @Test
  public void filterTodosByKeyword() throws IOException{
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] filteredTodos = db.filteredKeywords(allTodos, "Anim");
    assertEquals("Incorrect number of todos returned by keyword", 300, filteredTodos.length);
  }

  @Test
  public void listTodosByKeyword() throws IOException{
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();

    queryParams.put("keyword", new String[] {"Anim"});
    Todo[] listFilteredTodos = db.listTodos(queryParams);
    assertEquals("Incorrect number of todos returned by keyword", 300, listFilteredTodos.length);
  }
}


