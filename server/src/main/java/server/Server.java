package server;

import spark.*;

public class Server {

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        Spark.delete("/db",(req,res)->"Delete all stuff");
        Spark.post("/user",(req,res)->"Add user");
        Spark.post("/session",(req,res)->"Login");
        Spark.delete("/session",(req,res)->"Logout");
        Spark.get("/game",(req,res)->"List all games");
        Spark.post("/game",(req,res)->"Create game");
        Spark.put("/game",(req,res)->"Join Game");

        //This line initializes the server and can be removed once you have a functioning endpoint 
       // Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
