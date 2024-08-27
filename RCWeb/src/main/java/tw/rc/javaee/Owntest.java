package tw.rc.javaee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.BCrypt;

@WebServlet("/Owntest")
public class Owntest extends HttpServlet {
	private Connection conn;

	public Owntest() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/owntest", "root", "root");
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println("Ooooooop!!");
		}
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(500, "Get Out Here");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String account = request.getParameter("account");
		String passwd = request.getParameter("passwd");
		
		if (account == null) {
			response.sendRedirect("owntest.jsp");
			return;
		}
		System.out.println("debug1");
		try {
			String sql = "SELECT * FROM membertest WHERE account = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,account);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("debug2");
			if (rs.next()) {
				System.out.println("debug3");
				String hasPasswd = rs.getString("passwd");
				if (BCrypt.checkpw(passwd, hasPasswd)) {
					System.out.println("debug4");
					response.sendRedirect("RC19");
				}else {
					response.sendRedirect("owntest.jsp?errType=1");
				}
			}else {
				response.sendRedirect("owntest.jsp?errType=2");
			}
			
		} catch (Exception e) {
			response.sendRedirect("owntest.jsp");
			System.out.println("debug5");
			e.printStackTrace();
		}
		
	}


}
