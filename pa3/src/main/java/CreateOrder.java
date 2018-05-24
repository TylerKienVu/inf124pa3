import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.*;  

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/order")
public class CreateOrder extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
        doPost(req,res);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        PrintWriter out = response.getWriter();
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://matt-smith-v4.ics.uci.edu/inf124db061","inf124db061","TMcVwhIMAmW^");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inf124?autoReconnect=true&useSSL=false","root","mTigerl8855!");
            stmt = conn.createStatement();
            
            //insert person address
            int person_address_id = stmt.executeUpdate("INSERT INTO Address (address_1, address_2, city, state, zip)" + 
                "VALUES ("+ request.getParameter("shipAddress") + ',' +
                    request.getParameter("shipAddress2") +',' +
                    request.getParameter("city") + ',' +
                    request.getParameter("state") + ',' +
                    request.getParameter("zip") + ')', Statement.RETURN_GENERATED_KEYS);   
            
            //insert person
            int person_id = stmt.executeUpdate("INSERT INTO Person (address_id, email, first_name, last_name, phone)" +
                "VALUES (" +
                    person_address_id + ',' + 
                    request.getParameter("email") + ',' + 
                    request.getParameter("fname") + ',' +
                    request.getParameter("lname") + ',' +
                    request.getParameter("phone") + ')', Statement.RETURN_GENERATED_KEYS);
            
            //insert pay address
            int pay_address_id = stmt.executeUpdate("INSERT INTO Address (address_1, address_2, city, state, zip)" +
                "VALUES (" +
                    request.getParameter("billAddress") + ',' +
                    request.getParameter("billAddress2") + ',' +
                    request.getParameter("billCity") + ',' +
                    request.getParameter("billState") + ',' +
                    request.getParameter("billZip") + ')', Statement.RETURN_GENERATED_KEYS);
            
            //insert payment
            int payment_id = stmt.executeUpdate("INSERT INTO Payment (address_id,card_number,csc,expiration,name)" +
                "VALUES (" +
                pay_address_id + ',' +
                request.getParameter("cardNumber") + ',' +
                request.getParameter("csc") + ',' +
                request.getParameter("expiration") + ',' +
                request.getParameter("cardName") + ')', Statement.RETURN_GENERATED_KEYS);
            
            //insert order
            int order_id = stmt.executeUpdate("INSERT INTO Orders (payment_id,person_id,quantity,rock_id)" +
                "VALUES (" +
                payment_id + ',' +
                person_id + ',' +
                request.getParameter("quantity") + ',' +
                request.getParameter("rockNum]") + ')', Statement.RETURN_GENERATED_KEYS);
            
            out.flush();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(stmt != null){
                try{
                    stmt.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
