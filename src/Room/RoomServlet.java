package Room;

import User.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoomServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String roomName = request.getParameter("name");
        String action = request.getParameter("action");
        UserList userList = UserList.getInstance();

        for (User tmp : userList.list) {
            if (tmp.getLogin().equals(login)) {
                if (action.equals("enter")) {
                    tmp.setRoom(roomName);
                    break;
                } else {
                    tmp.setRoom(null);
                    break;
                }
            }
        }
    }
}