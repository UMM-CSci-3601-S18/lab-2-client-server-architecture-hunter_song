package umm3601.todo;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class GetToDoByOwner {
  @Test
  public void filterTodosByOwner() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] ownerBlanche = db.filterTodosByOwner(allTodos, "Blanche");
    assertEquals("Incorrect number of entries with owner Blanche", 43, ownerBlanche.length);


  }
}
