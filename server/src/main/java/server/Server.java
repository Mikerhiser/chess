package server;

import spark.*;

public class Server {

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        Handler handler = new Handler();

        // Register your endpoints and handle exceptions here.
        Spark.delete("/db",(req,res)->{
            System.out.println("we deleting");
            res = handler.deleteHandler(req,res);
            System.out.println(res);
            return res;
        });

        Spark.post("/user",(req,res)->{
            return "Add user";
        });

        Spark.post("/session",(req,res)->{
            return "{\"username\": \"Blergleborf\", \"password\": \"Blerglebarf\"}";
        });

        Spark.delete("/session",(req,res)->{
            return "Logout";
        });

        Spark.get("/game",(req,res)->{
            return "List all games";
        });

        Spark.post("/game",(req,res)->{
            return "Create game";
        });

        Spark.put("/game",(req,res)->{
            return "Join Game";
        });

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
