package umm3601.todo;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/**
 * A fake "database" of user info
 *
 * Since we don't want to complicate this lab with a real database,
 * we're going to instead just read a bunch of user data from a
 * specified JSON file, and then provide various database-like
 * methods that allow the `UserController` to "query" the "database".
 */
public class ToDoDatabase {

  private Todo[] allTodos;

  public ToDoDatabase(String userDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(userDataFile);
    allTodos = gson.fromJson(reader, Todo[].class);
  }

  /**
   * Get the single user specified by the given ID. Return
   * `null` if there is no user with that ID.
   *
   * @param id the ID of the desired user
   * @return the user with the given ID, or null if there is no user
   * with that ID
   */
  public Todo getToDo(String id) {
    return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }



  /**
   * Get an array of all the users satisfying the queries in the params.
   *
   * @param queryParams map of required key-value pairs for the query
   * @return an array of all the users matching the given criteria
   */

  public Todo[] listTodos(Map<String, String[]> queryParams) {
    Todo[] filteredTodos = allTodos;

    // Filter owners if defined
    if(queryParams.containsKey("owner")) {
      String targetowner = queryParams.get("owner")[0];
      filteredTodos = filterTodosByOwner(filteredTodos, targetowner);
    }

    // Filter categories if defined
    if(queryParams.containsKey("category")) {
      String targetcategory = queryParams.get("category")[0];
      filteredTodos = filterTodosByCategory(filteredTodos, targetcategory);
    }

    //Filter owners with number limit
    if(queryParams.containsKey("limit")){
      int limitNum = Integer.parseInt(queryParams.get("limit")[0]);
      Todo[] temp = new Todo[limitNum];
      for (int i=0; i < limitNum; i++){
        temp[i] = filteredTodos[i];
      }
      filteredTodos = temp;
    }

    // Filter status if defined
    if(queryParams.containsKey("status")) {
      Boolean targetstatus;
      if(queryParams.get("status")[0] == "complete"){
        targetstatus = true;
      }else{
        targetstatus = false;
      }
      filteredTodos = filterTodosByStatus(filteredTodos, targetstatus);
    }

    // Filter bodies if have key words
    if(queryParams.containsKey("contains")){
      String keywords = queryParams.get("contains")[0];
      filteredTodos = filteredKeywords(filteredTodos,keywords);
    }

    // Sorts the returned to-dos alphabetically
    if(queryParams.containsKey("orderBy")){
      if(queryParams.get("orderBy")[0] == "owner"){ //sorted by owner
        Arrays.sort(filteredTodos, new TodoByOwner());
      }
      if(queryParams.get("orderBy")[0] == "body"){ //sorted by body
        Arrays.sort(filteredTodos, new TodoByBody());
      }
      if(queryParams.get("orderBy")[0] == "status"){ //sorted by status
        Arrays.sort(filteredTodos, new TodoByStatus());
      }
      if(queryParams.get("orderBy")[0] == "category"){ //sorted by category
        Arrays.sort(filteredTodos, new TodoByCategory());
      }
    }

    return filteredTodos;
  }

  /**
   * create classes for sorting
   */
  class TodoByOwner implements Comparator<Todo>{
    public int compare(Todo todo1, Todo todo2){
      return todo1.owner.compareTo(todo2.owner);
    }
  }

  class TodoByBody implements Comparator<Todo>{
    public int compare(Todo todo1, Todo todo2){
      return todo1.body.compareTo(todo2.body);
    }
  }
  class TodoByStatus implements Comparator<Todo>{
    public int compare(Todo todo1, Todo todo2){
      return Boolean.valueOf(todo1.status).compareTo(Boolean.valueOf(todo2.status));
    }
  }
  class TodoByCategory implements Comparator<Todo>{
    public int compare(Todo todo1, Todo todo2){
      return todo1.category.compareTo(todo2.category);
    }
  }


  /**
   * Get an array of all the to do users having the target owner.
   *
   * @param todos the list of users to filter by owner
   * @param targetowner the target owner to look for
   * @return an array of all the to do users from the given list that have
   * the target owner
   */

  public Todo[] filterTodosByOwner(Todo[] todos, String targetowner) {
    return Arrays.stream(todos).filter(x -> x.owner == targetowner).toArray(Todo[]::new);
  }

  /**
   * Get an array of all the to do users having the target owner.
   *
   * @param todos the list of users to filter by owner
   * @param targetcategory the target owner to look for
   * @return an array of all the to do users from the given list that have
   * the target owner
   */

  public Todo[] filterTodosByCategory(Todo[] todos, String targetcategory) {
    return Arrays.stream(todos).filter(x -> x.owner == targetcategory).toArray(Todo[]::new);
  }

  /***
   * Get an array of all the to do users having the target status.
   * @param todos
   * @param targetstatus
   * @return an array of all the to do users from the given list that have
   * the target status
   */
  public Todo[] filterTodosByStatus(Todo[] todos, Boolean targetstatus){
    return Arrays.stream(todos).filter(x -> x.status == targetstatus).toArray(Todo[]::new);
  }

  /**
   * Get an array of all the to do users having the keywords.
   * @param todos
   * @param keywords
   * @return an array of all the to do users from the given list that have
   * the keywords
   */
  public Todo[] filteredKeywords(Todo[] todos, String keywords){
    return Arrays.stream(todos).filter(x -> x.body.contains(keywords)).toArray(Todo[]::new);
  }





}
