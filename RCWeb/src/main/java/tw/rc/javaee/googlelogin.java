package tw.rc.javaee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/googlelogin")
public class googlelogin extends HttpServlet {
    private static final String CLIENT_ID = "開發者ID";
    private static final String REDIRECT_URI = "http://localhost:8080/RCWeb/callback";
    private static final String AUTH_URI = "https://accounts.google.com/o/oauth2/auth";
    private static final String RESPONSE_TYPE = "code";
    private static final String SCOPE = "openid email profile";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oauthUrl = AUTH_URI + "?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=" + RESPONSE_TYPE + "&scope=" + SCOPE;

        resp.sendRedirect(oauthUrl);
    }
}

