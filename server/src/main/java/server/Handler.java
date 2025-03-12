package server;

import com.google.gson.Gson;
import service.Service;
import spark.Request;
import spark.Response;

public class Handler {
    Gson serializer = new Gson();
    Service service = new Service();

    public Response deleteHandler(Request req, Response res){
        service.deleteService();
        res.status(200);
        res.body("{}");
        return res;
    }

    public Response addHandler(Request req, Response res){
        return res;
    }

    public Response loginHandler(Request req, Response res){
        return res;
    }

    public Response logoutHandler(Request req, Response res){
        return res;
    }

    public Response listHandler(Request req, Response res){
        return res;
    }

    public Response createHandler(Request req, Response res){
        return res;
    }

    public Response joinHandler(Request req, Response res){
        return res;
    }


}
