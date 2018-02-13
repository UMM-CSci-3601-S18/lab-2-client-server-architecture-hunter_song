package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class GetToDoByCategory {

  @Test
  public void filterTodosByCategory() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] cateHomework = db.filterTodosByCategory(allTodos, "homework");
    assertEquals("Incorrect number of entries with category homework", 79, cateHomework.length);


  }}
