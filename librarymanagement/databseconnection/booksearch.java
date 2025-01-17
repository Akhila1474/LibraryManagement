package databseconnection;
import books.book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class booksearch {
    public book sidsearch(Connection con,int id) throws Exception{
      String query="select * from books where id=?";
       try(PreparedStatement ps=con.prepareStatement(query)){
       ps.setInt(1,id );
       try(ResultSet rs=ps.executeQuery()){
        if(rs.next()){
            book b =new book();
            b.setId(rs.getInt("id"));
            b.setS_no(rs.getString("s_no"));
            b.setName(rs.getString("name"));
            b.setAut_name(rs.getString("aut_name"));
            b.setQuantity(rs.getInt("quantity"));
            return b;
        }
        
 
       }
      
    }
    return null;
    }
    public book namesearch(Connection con,String name) throws Exception{
        String query="select * from books where name=?";
         try(PreparedStatement ps=con.prepareStatement(query)){
         ps.setString(1,name);
         try(ResultSet rs=ps.executeQuery()){
          if(rs.next()){
              book b =new book();
              b.setId(rs.getInt("id"));
              b.setS_no(rs.getString("s_no"));
              b.setName(rs.getString("name"));
              b.setAut_name(rs.getString("aut_name"));
              b.setQuantity(rs.getInt("quantity"));
              return b;
          }
          
        
         }
        return null;
      }
}
public void AddBook(Connection con,book b) throws Exception{
String query="insert into books values(?,?,?,?,?)";
try(PreparedStatement ps=con.prepareStatement(query)){
    ps.setInt(1,b.getId());
    ps.setString(2,b.getS_no());
    ps.setString(3,b.getName());
    ps.setString(4,b.getAut_name());
    ps.setInt(5,b.getQuantity());
    int rowsAffected = ps.executeUpdate(); // Use executeUpdate for DML queries
    if (rowsAffected > 0) {
        System.out.println("Succesfully Added Book");
        return;
    }
    else{
        System.out.println("Adding book is failed");
        return;
    }
}
}
public void quantityincrease(Connection con,int id) throws SQLException{
    String  query="UPDATE books SET quantity = quantity + 1  WHERE id = ?" ;
 try(PreparedStatement ps=con.prepareStatement(query)){
ps.setInt(1,id);
int rows=ps.executeUpdate();
if(rows>0){
    System.out.println("Quantity incresed for Book");
}
else{
    System.out.println("Failed to update quantity");
}
}
}
public List<book> displaybooks(Connection con) throws Exception{
    String query="select * from books where quantity>0 ";
    List<book> bb=new ArrayList<>();
     try(PreparedStatement ps=con.prepareStatement(query)){
     try(ResultSet rs=ps.executeQuery()){  
      while(rs.next()){
          book b =new book();
          b.setId(rs.getInt("id"));
          b.setS_no(rs.getString("s_no"));
          b.setName(rs.getString("name"));
          b.setAut_name(rs.getString("aut_name"));
          b.setQuantity(rs.getInt("quantity"));
          bb.add(b);
      }
     }  
}
return bb;
}
public int getquantity(Connection con, int bid) throws SQLException{
    String query="select quantity from books where id=?";
    try(PreparedStatement ps=con.prepareStatement(query)){
        ps.setInt(1, bid);
        try(ResultSet rs=ps.executeQuery()){
            if(rs.next()){
                return rs.getInt("quantity");
            }
        }
    }
        return 0;
}
}




