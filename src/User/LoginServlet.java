package User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    UserList users = UserList.getInstance();

    @Override
    public void init() throws ServletException {
        users.list.add(new User("user", "user"));
        users.list.add(new User("admin", "admin"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("user");
        String pass = request.getParameter("pass");
        String result = "no";

        for (User tmp : users.list) {
            if (tmp.getLogin().equals(login)) {
                if (pass.equals("logoff")) {
                    tmp.setStatus("offline");
                    return;
                }
                if ( tmp.getPassword().equals(pass)) {
                    result ="ok";
                    tmp.setStatus("online");
                }
            }
        }
        response.getWriter().print(result);
    }
}
