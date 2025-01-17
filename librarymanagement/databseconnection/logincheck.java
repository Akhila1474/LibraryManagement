package databseconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class logincheck {
    public String doLogin(Connection con,String name,String password) throws Exception{
        String query="select * from login where name=? and passward=?";
        try(PreparedStatement ps=con.prepareStatement(query)){
            ps.setString(1, name);
            ps.setString(2, password);
        try(ResultSet rs=ps.executeQuery()){
            if(rs.next()){
                return rs.getString("type");
            }
              
        }
    }
        return null;
    }
}
