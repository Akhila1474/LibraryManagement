package databseconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class bookingdetails {
    public void AddBooking_checkout(Connection con,int id,int bid,String type) throws SQLException{
        booksearch b=new booksearch();
        int c=b.getquantity(con, bid);
        if(c==0){
            System.out.println("book not available");

        }
        else{
   
            String s="insert into booking_details (s_id,b_id,type) values(?,?,?)";
            String s1="update books set quantity = quantity -1 where id = ?";
            try(PreparedStatement ps=con.prepareStatement(s)){
                ps.setInt(1,id);
                ps.setInt(2,bid);
                ps.setString(3,type);
                int rows=ps.executeUpdate();
                if(rows>0){
                    System.out.println("Booking had done collect your book from library");
                }
                else{
                    System.out.println("Booking failed");
                }
            }
            try(PreparedStatement ps=con.prepareStatement(s1)){
                // ps.setInt(1,bid);
                // ps.setInt(1, cnt);  // Decrement quantity by cnt
                ps.setInt(1, bid);  // Specify the book ID
                int r=ps.executeUpdate();
                if(r>0){
                    System.out.println("Books table Updation also done");
                }
                else{
                    System.out.println("Books table Updation denied");
                }

        }
    }
        
        }

    public void AddBooking_checkin(Connection con,int id,int bid,String type) throws Exception{
        String s="insert into booking_details (s_id,b_id,type) values(?,?,?)";
        String s1="update books set quantity = quantity +1 where id = ?";
        try(PreparedStatement ps=con.prepareStatement(s)){
            ps.setInt(1,id);
            ps.setInt(2,bid);
            ps.setString(3,type);
            int rows=ps.executeUpdate();
            if(rows>0){
                System.out.println("Booking had done collect your book from library");
            }
            else{
                System.out.println("Booking failed");
            }
        }
        try(PreparedStatement ps=con.prepareStatement(s1)){
            // ps.setInt(1,bid);
            // ps.setInt(1, cnt);  // Decrement quantity by cnt
            ps.setInt(1, bid);  // Specify the book ID
            int r=ps.executeUpdate();
            if(r>0){
                System.out.println("Books table Updation also done");
            }
            else{
                System.out.println("Books table Updation denied");
            }
    }
    
    }
}

