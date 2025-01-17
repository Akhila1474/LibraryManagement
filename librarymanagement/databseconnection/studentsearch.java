package databseconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import students.student;

public class studentsearch {
    public void AddStudent(Connection con,student s,String pass,String type) throws Exception{
String query="insert into students values(?,?,?)";
String queryy="insert into login values(?,?,?,?)";
try(PreparedStatement ps=con.prepareStatement(query)){
    ps.setInt(1,s.getId());
    ps.setString(2,s.getName());
    ps.setInt(3,s.getReg_id());
    try{
    int rowsAffected = ps.executeUpdate(); // Use executeUpdate for DML queries
    if (rowsAffected > 0) {
        System.out.println("Succesfully Added Student");
    }
    else{
        System.out.println("Adding Student is failed ");
    }
}
catch(Exception e){
    System.out.println("Adding Student is failed Student already existing with id");
}
}
try(PreparedStatement ps=con.prepareStatement(queryy)) {
    ps.setInt(1,s.getId());
    ps.setString(2,s.getName());
    ps.setString(3,pass);
    ps.setString(4,type);
    try{
        int rowsAffected = ps.executeUpdate(); // Use executeUpdate for DML queries
        if (rowsAffected > 0) {
            System.out.println("Succesfully Added Student into login table");
        }
        else{
            System.out.println("Adding Student in login table is failed ");
        }
    }
    catch(Exception e){
        System.out.println("Adding Student is failed Student already existing with id");
    } 

} 
}

public List<student> displaystudents(Connection con) throws Exception{
    String query="select * from students";
    List<student> ss=new ArrayList<>();
     try(PreparedStatement ps=con.prepareStatement(query)){
     try(ResultSet rs=ps.executeQuery()){  
      while(rs.next()){
          student s =new student();
          s.setId(rs.getInt("id"));
          s.setName(rs.getString("name"));
          s.setReg_id(rs.getInt("reg_num"));
          ss.add(s);
      }
     }  
}
return ss;
}



}
