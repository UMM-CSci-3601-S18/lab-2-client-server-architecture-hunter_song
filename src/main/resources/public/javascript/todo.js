/**
 * Function to get all the to-dos!
 */

function getAllTodos() {
  console.log("Getting all the todos.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos", function(returned_json){
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosByID() {
  console.log("Getting all the users.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos/:id" +  document.getElementById("id").value, function(returned_json){
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosByLimit() {
  console.log("Getting all the todos.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?limit=" +  document.getElementById("limit").value, function(returned_json){
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosByStatus() {
  console.log("Getting all the todos.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?status=" +  document.getElementById("status").value, function(returned_json){
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosByContains() {
  console.log("Getting all the todos.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?contains=" +  document.getElementById("contains").value, function(returned_json){
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosByOwner() {
  console.log("Getting all the todos.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?owner=" +  document.getElementById("owner").value, function(returned_json){
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosByCategory() {
  console.log("Getting all the todos.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?category=" +  document.getElementById("category").value, function(returned_json){
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosByOrder() {
  console.log("Getting all the todos.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?orderBy=" +  document.getElementById("orderBy").value, function(returned_json){
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}


function getAllTodosByFilter() {
  console.log("Getting all the todos.");

  var todoURL = "/api/todos"
  var beFiltered = false;

  if (document.getElementById("id").value != ""){
    if(beFiltered){
      todoURL = todoURL + "&:id" + document.getElementById("id").value;
    }else{
      todoURL = todoURL + ":id" + document.getElementById("id").value;
      beFiltered = true;
    }
  }

  if (document.getElementById("limit").value != ""){
    if(beFiltered){
      todoURL = todoURL + "&limit=" + document.getElementById("limit").value;
    }else{
      todoURL = todoURL + "?limit=" + document.getElementById("limit").value;
      beFiltered = true;
    }
  }

  if (document.getElementById("status").value != ""){
    if(beFiltered){
      todoURL = todoURL + "&status=" + document.getElementById("status").value;
    }else{
      todoURL = todoURL + "?status=" + document.getElementById("status").value;
      beFiltered = true;
    }
  }

  if (document.getElementById("contains").value != ""){
    if(beFiltered){
      todoURL = todoURL + "&contains=" + document.getElementById("contains").value;
    }else{
      todoURL = todoURL + "?contains=" + document.getElementById("contains").value;
      beFiltered = true;
    }
  }

  if (document.getElementById("owner").value != ""){
    if(beFiltered){
      todoURL = todoURL + "&owner=" + document.getElementById("owner").value;
    }else{
      todoURL = todoURL + "?owner=" + document.getElementById("owner").value;
      beFiltered = true;
    }
  }

  if (document.getElementById("category").value != ""){
    if(beFiltered){
      todoURL = todoURL + "&category=" + document.getElementById("category").value;
    }else{
      todoURL = todoURL + "?category=" + document.getElementById("category").value;
      beFiltered = true;
    }
  }

  if (document.getElementById("orderBy").value != ""){
    if(beFiltered){
      todoURL = todoURL + "&orderBy=" + document.getElementById("orderBy").value;
    }else{
      todoURL = todoURL + "?owner=" + document.getElementById("orderBy").value;
      beFiltered = true;
    }
  }

  else{
    todoURL = todoURL;
    beFiltered = true;

  }


}


/**
 * Wrapper to make generating http requests easier. Should maybe be moved
 * somewhere else in the future!.
 *
 * Based on: http://stackoverflow.com/a/22076667
 * Now with more comments!
 */
function HttpClient() {
  // We'll take a URL string, and a callback function.
  this.get = function(aUrl, aCallback){
    var anHttpRequest = new XMLHttpRequest();

    // Set a callback to be called when the ready state of our request changes.
    anHttpRequest.onreadystatechange = function(){

      /**
       * Only call our 'aCallback' function if the ready state is 'DONE' and
       * the request status is 200 ('OK')
       *
       * See https://httpstatuses.com/ for HTTP status codes
       * See https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/readyState
       *  for XMLHttpRequest ready state documentation.
       *
       */
      if (anHttpRequest.readyState === 4 && anHttpRequest.status === 200)
        aCallback(anHttpRequest.responseText);
    };

    anHttpRequest.open("GET", aUrl, true);
    anHttpRequest.send(null);
  }
}
