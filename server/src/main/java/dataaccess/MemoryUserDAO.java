package dataaccess;

import model.Model;

import java.util.ArrayList;

public class MemoryUserDAO implements UserDAO {

    ArrayList<Model.UserData> users = new ArrayList<>();

    @Override
    public void createUser() {

    }

    @Override
    public void readUser() {

    }

    @Override
    public void updateUser() {

    }

    @Override
    public void deleteUser() {

    }

    @Override
    public void clearUser() {
        users.clear();
    }
}



