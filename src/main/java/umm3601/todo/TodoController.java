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
    Todo todo = toDoDatabase.getToDo(id);
    if (todo != null) {
      return buildSuccessJsonResponse("todo", gson.toJsonTree(todo));
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
    Todo[] todo = toDoDatabase.listTodos(req.queryMap().toMap());
    return buildSuccessJsonResponse("todos", gson.toJsonTree(todo));
  }

  /**
   * Get a JSON response with a list of all the to dos with limit number in the "todoDatabase"
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object containing all the to do users with the required number
   */
  public JsonObject getLimit(Request req, Response res){
    res.type("application/json");
    String limit = req.params("limit");
    Todo[] todo = toDoDatabase.listTodos(req.queryMap().toMap());
    if (todo != null) {
      return buildSuccessJsonResponse("limit", gson.toJsonTree(todo));
    } else {
      String message = "Todo with limit " + limit + " wasn't found.";
      return buildFailJsonResponse("limit", message);
    }
  }


  /**
   * Get a JSON response with a list of all the to dos with complete status in the "todoDatabase"
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object containing all the to do users with the complete status
   */
  public JsonObject getComplete(Request req, Response res){
    res.type("application/json");
    String status = req.params("status");
    Todo[] todo = toDoDatabase.listTodos(req.queryMap().toMap());
    if (todo != null) {
      return buildSuccessJsonResponse("status", gson.toJsonTree(todo));
    } else {
      String message = "Todo with status " + status + " wasn't found.";
      return buildFailJsonResponse("status", message);
    }
  }

  /**
   * Get a JSON response with a list of all the to dos with their body contains some key words
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object containing all the to do users with their body contains some key words
   */
  public JsonObject getContains(Request req, Response res){
    res.type("application/json");
    String contains = req.params("contains");
    Todo[] todo = toDoDatabase.listTodos(req.queryMap().toMap());
    if (todo != null) {
      return buildSuccessJsonResponse("contains", gson.toJsonTree(todo));
    } else {
      String message = "Todo with contains " + contains + " wasn't found.";
      return buildFailJsonResponse("contains", message);
    }
  }

  /**
   * Get a JSON response with a list of all the to dos by owner info
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object containing all the to do users by owner info
   */
  public JsonObject getOwner(Request req, Response res){
    res.type("application/json");
    String owner = req.params("owner");
    Todo[] todo = toDoDatabase.listTodos(req.queryMap().toMap());
    if (todo != null) {
      return buildSuccessJsonResponse("owner", gson.toJsonTree(todo));
    } else {
      String message = "Todo with owner " + owner + " wasn't found.";
      return buildFailJsonResponse("owner", message);
    }
  }

  /**
   * Get a JSON response with a list of all the to dos with category
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object containing all the to do users with category
   */
  public JsonObject getCategory(Request req, Response res){
    res.type("application/json");
    String category = req.params("category");
    Todo[] todo = toDoDatabase.listTodos(req.queryMap().toMap());
    if (todo != null) {
      return buildSuccessJsonResponse("category", gson.toJsonTree(todo));
    } else {
      String message = "Todo with category " + category + " wasn't found.";
      return buildFailJsonResponse("category", message);
    }
  }

  /**
   * Get a JSON response with a list of all the to dos with specific order
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object containing all the to do users with specific order
   */
  public JsonObject getOrderby(Request req, Response res){
    res.type("application/json");
    String orderBy = req.params("contains");
    Todo[] todo = toDoDatabase.listTodos(req.queryMap().toMap());
    if (todo != null) {
      return buildSuccessJsonResponse("orderBy", gson.toJsonTree(todo));
    } else {
      String message = "Todo with orderBy " + orderBy + " wasn't found.";
      return buildFailJsonResponse("orderby", message);
    }
  }

}
