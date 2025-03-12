package dataaccess;

import model.Model;
import java.util.ArrayList;

public class MemoryAuthDAO implements AuthDAO{

    ArrayList<Model.AuthData> auths = new ArrayList<>();

    @Override
    public void createAuth() {

    }

    @Override
    public void readAuth() {

    }

    @Override
    public void updateAuth() {

    }

    @Override
    public void deleteAuth() {

    }

    @Override
    public void clearAuth(){
        auths.clear();
    }

}
