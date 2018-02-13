package umm3601.todo;


import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class FilterByStatus {

  @Test
  public void filterTodosByStatusComplete() throws IOException{
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] filteredTodos = db.filterToDosByStatus(allTodos, "true");
    assertEquals("Incorrect number of todos returned by status complete", 143, filteredTodos.length);
  }

  @Test
  public void listTodosByStatusComplete() throws IOException{
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();

    queryParams.put("status", new String[] {"true"});
    Todo[] listFilteredTodos = db.listTodos(queryParams);
    assertEquals("Incorrect number of todos returned by status complete", 143, listFilteredTodos.length);
  }
}
