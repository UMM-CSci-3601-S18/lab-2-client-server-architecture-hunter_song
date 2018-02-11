package umm3601.todo;

import org.junit.Test;
import umm3601.todo.ToDoDatabase;
import umm3601.todo.Todo;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;


public class GetToDoByIDFromDB {
  @Test
  public void getStokesClayton() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    Todo todo = db.getToDo("58895985a22c04e761776d54");
    assertEquals("Incorrect name", "Blanche", todo.owner);
  }

  @Test
  public void getBoltonMonroe() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    Todo todo = db.getToDo("58895985c1849992336c219b");
    assertEquals("Incorrect name", "Fry", todo.owner);
  }

}
