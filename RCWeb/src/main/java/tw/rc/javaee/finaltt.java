package tw.rc.javaee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.jar.Attributes.Name;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/finaltt")
public class finaltt extends HttpServlet {
    private Connection conn;

    public finaltt() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iii", "root", "root");
        } catch (Exception e) {
            System.out.println("Ooooooop!!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String key = request.getParameter("key");
        String key2 = (key != null && !key.isEmpty()) ? "%" + key + "%" : null;
        int page = 1;
        int recordsPerPage = 10;

        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1; // 默认页码为 1
            }
        }

        // SQL 查询和计数语句
        String sql = "SELECT * FROM finalmember LIMIT ?, ?";
        String countSql = "SELECT COUNT(*) FROM finalmember";

        if (key2 != null) {
            sql = "SELECT * FROM finalmember WHERE account LIKE ? OR passwd LIKE ? OR phone LIKE ? OR addr LIKE ? LIMIT ?, ?";
            countSql = "SELECT COUNT(*) FROM finalmember WHERE account LIKE ? OR passwd LIKE ? OR phone LIKE ? OR addr LIKE ?";
        }

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            PreparedStatement countPstmt = conn.prepareStatement(countSql);

            if (key2 != null) {
                pstmt.setString(1, key2);
                pstmt.setString(2, key2);
                pstmt.setString(3, key2);
                pstmt.setString(4, key2);
                pstmt.setInt(5, (page - 1) * recordsPerPage);
                pstmt.setInt(6, recordsPerPage);

                countPstmt.setString(1, key2);
                countPstmt.setString(2, key2);
                countPstmt.setString(3, key2);
                countPstmt.setString(4, key2);
            } else {
                pstmt.setInt(1, (page - 1) * recordsPerPage);
                pstmt.setInt(2, recordsPerPage);
            }

            ResultSet rs = pstmt.executeQuery();
            ResultSet countRs = countPstmt.executeQuery();

            int totalRecords = 0;
            if (countRs.next()) {
                totalRecords = countRs.getInt(1);
            }

            int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.print("{\"results\": [");
            boolean first = true;
            while (rs.next()) {
                if (!first) out.print(",");
                out.print("{");
                out.printf("\"id\": \"%s\", \"account\": \"%s\", \"passwd\": \"%s\", \"phone\": \"%s\", \"addr\": \"%s\"",
                        rs.getString("id"), rs.getString("account"), rs.getString("passwd"), rs.getString("phone"), rs.getString("addr"));
                out.print("}");
                first = false;
            }
            out.print("],");

            out.printf("\"totalPages\": %d, \"currentPage\": %d}", totalPages, page);

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}