package umm3601.todo;

import org.junit.Assert;
import org.junit.Test;
import umm3601.todo.ToDoDatabase;
import umm3601.todo.Todo;

import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;



public class GetToDoWithLimit {

  @Test
  public void toDoWithLimit() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    Todo[] listTodos = db.listTodos(new HashMap<>());
    Assert.assertEquals("Incorrect total number of todos", 300, listTodos.length);
  }
}
