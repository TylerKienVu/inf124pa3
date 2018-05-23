import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ConnectDBServlet.html")
public class Test extends HttpServlet{
        // DID NOT USE
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            doProcess(request,response);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    // DID NOT USE
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            doProcess(request,response);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException , SQLException{
        Connection conn = null;
        Statement  stmt = null;
        ResultSet   rs = null;
        PrintWriter out = response.getWriter();

        try{            
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            
            // conn = DriverManager.getConnection("jdbc:mysql://[hostname]/[database name]", "[username]", "[password]");
            conn = DriverManager.getConnection("jdbc:mysql://matt-smith-v4.ics.uci.edu/inf124db061", "inf124db061", "TMcVwhIMAmW^");
            
            // allow statement creation
            stmt = conn.createStatement();

            // write sql query here
            rs = stmt.executeQuery("SELECT * FROM Rocks");
            
            // for each result returned, get the user_id, name, email, and password
            // out.println("a") will print a
            while(rs.next()){
                out.println("rock_id : " + rs.getString("rock_id"));
            }
            
            // flush to remove any excess data
            out.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(stmt != null){
                stmt.close();
            }
            if(conn != null){
                conn.close();
            }
        }
    }
}
