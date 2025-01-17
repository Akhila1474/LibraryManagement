package databseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
public class connectionclass {
  public static  Connection con;
  public static Connection createconnection() throws Exception{
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","akhila123");
     System.out.println("Connection successfull");
     return con; 
    } catch (Exception e) {
      System.out.println("error while connection the db in connection class");
      e.printStackTrace(); 
    }
   return null;
  }
  
  public static Connection getconnection() throws Exception{
    if(con==null) return createconnection();
    return con;
  }   

    public connectionclass() {
    }

}
