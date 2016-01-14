package User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserList userList = UserList.getInstance();
        String get = request.getParameter("get");

        if (get.equals("list")) {
            String json = userList.toJSON();
            response.getOutputStream().write(json.getBytes());
        } else {
            for (User tmp : userList.list) {
                if (tmp.getLogin().equals(get)) {
                    Gson gson = new GsonBuilder().create();
                    String json = gson.toJson(tmp);
                    response.getOutputStream().write(json.getBytes());
                    break;
                }
            }
        }
    }
}
