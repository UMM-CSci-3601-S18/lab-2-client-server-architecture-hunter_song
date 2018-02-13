package umm3601.todo;

import org.junit.Before;
import org.junit.Test;
import umm3601.todo.ToDoDatabase;
import umm3601.todo.Todo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;



public class GetToDoWithLimit {

  @Test
  public void listTodosByLimit() throws IOException{
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    Map<String, String[]> queryParams = new HashMap<>();

    queryParams.put("limit", new String[] {"7"});
    Todo[] listTodos7 = db.listTodos(queryParams);
    assertEquals("Server did not properly limit todos", 7, listTodos7.length);
  }
}
