package umm3601.todo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;

import static umm3601.Util.*;

/**
 * Controller that manages requests for info about users.
 */
public class TodoController {

  private final Gson gson;
  private ToDoDatabase toDoDatabase;

  /**
   * Construct a controller for users.
   *
   * This loads the "toDoDatabase" of user info from a JSON file and
   * stores that internally so that (subsets of) users can be returned
   * in response to requests.
   *
   * @param toDoDatabase the toDoDatabase containing user data
   */
  public TodoController(ToDoDatabase toDoDatabase) {
    gson = new Gson();
    this.toDoDatabase = toDoDatabase;
  }

  /**
   * Get the single to do user specified by the `id` parameter in the request.
   *
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object if the to do user with that ID is found, a fail
   * JSON object if no user with that ID is found
   */
  public JsonObject getToDo(Request req, Response res) {
    res.type("application/json");
    String id = req.params("id");
    Todo user = toDoDatabase.getToDo(id);
    if (user != null) {
      return buildSuccessJsonResponse("todo", gson.toJsonTree(user));
    } else {
      String message = "Todo with ID " + id + " wasn't found.";
      return buildFailJsonResponse("id", message);
    }
  }

  /**
   * Get a JSON response with a list of all the to do users in the "toDoDatabase".
   *
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object containing all the to do users
   */
  public JsonObject getToDos(Request req, Response res) {
    res.type("application/json");
    Todo[] users = toDoDatabase.listTodos(req.queryMap().toMap());
    return buildSuccessJsonResponse("todos", gson.toJsonTree(users));
  }

}
