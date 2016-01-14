package User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private static final UserList userList = new UserList();

    public final List<User> list = new ArrayList<User>();

    public static UserList getInstance() {
        return userList;
    }

    private UserList() {
    }

    public synchronized User getUser(String login) {
        for (User tmp : list) {
            if (tmp.getLogin().equals(login)) {
                return tmp;
            }
        }
        return null;
    }

    public synchronized String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(list.toArray());
    }
}
