package service;

import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryGameDAO;
import dataaccess.MemoryUserDAO;
import spark.Request;
import spark.Response;

public class Service {

    MemoryAuthDAO authManip = new MemoryAuthDAO();
    MemoryGameDAO gameManip = new MemoryGameDAO();
    MemoryUserDAO userManip = new MemoryUserDAO();

    public void deleteService(){
        authManip.clearAuth();
        gameManip.clearGames();
        userManip.clearUser();
    }

    public Response addService(Request req, Response res){

        return res;
    }

    public Response loginService(Request req, Response res){

        return res;
    }

    public Response logoutService(Request req, Response res){

        return res;
    }

    public Response listService(Request req, Response res){

        return res;
    }

    public Response createService(Request req, Response res){

        return res;
    }

    public Response joinService(Request req, Response res){

        return res;
    }



}
