package tw.rc.javaee;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@WebServlet("/callback")
public class googlecallback extends HttpServlet {
    private static final String CLIENT_ID = "開法者ID";
    private static final String CLIENT_SECRET = "金鑰匙";
    private static final String REDIRECT_URI = "http://localhost:8080/RCWeb/callback";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String error = req.getParameter("error");
        if (error != null) {
            // 如果出现错误，可能是用户取消了授权
            // 重定向回登录页面
            resp.sendRedirect("http://localhost:8080/RCWeb/googlelogin.html");
            return;
        }

        if (code == null || code.isEmpty()) {
            // 授权码缺失，重定向回登录页面
            resp.sendRedirect("http://localhost:8080/RCWeb/googlelogin.html");
            return;
        }

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, Collections.singletonList("openid email profile"))
                .build();

        GoogleTokenResponse tokenResponse = flow.newTokenRequest(code)
                .setRedirectUri(REDIRECT_URI)
                .execute();

        String idToken = tokenResponse.getIdToken();

        // 解析 ID Token 以获取用户信息
        GoogleIdToken.Payload payload = GoogleIdToken.parse(JSON_FACTORY, idToken).getPayload();
        String name = (String) payload.get("name");
        String email = payload.getEmail();
        
     // 对用户信息进行 URL 编码
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
        String encodedEmail = URLEncoder.encode(email, StandardCharsets.UTF_8.toString());

        // 在重定向 URL 附加用户信息
        String redirectUrl = "http://localhost:8080/RCWeb/rc08.html?name=" + encodedName + "&email=" + encodedEmail;

        // 确保没有写入其他内容到响应中
        resp.setStatus(HttpServletResponse.SC_FOUND); // 设置状态码为302（重定向）
        resp.setHeader("Location", redirectUrl); // 设置重定向的URL
    }
}