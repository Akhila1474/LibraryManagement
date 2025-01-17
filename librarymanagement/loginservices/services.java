package loginservices;
import books.book;
import bookservices.servicesofbooks;
import databseconnection.bookingdetails;
import databseconnection.booksearch;
import databseconnection.connectionclass;
import databseconnection.logincheck;
import databseconnection.studentsearch;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import students.student;
public class services{
    Scanner sc=new Scanner(System.in);
    public void doLogin() throws SQLException, Exception{
System.out.println("enter your details");
String name;
System.out.println("enter username");
name=sc.next();
String pass;
System.out.println("enter password");
pass=sc.next();
try(Connection con=connectionclass.getconnection()){
    logincheck lc=new logincheck();
    String type= lc.doLogin(con, name, pass);
    if(type==null){
        System.out.println("Inalid User");
    }
    else if(type.equals("admin")){
        System.out.println("You are Admin");
        displayadminmethos(con);
    }
    else{
        System.out.println("You are User");
        displayusermethods(con);
    }
}
catch(Exception e){
    System.out.println("error while logingin in servives class");
    e.printStackTrace(); 

}
}
public void displayadminmethos(Connection con) throws Exception{
    int ch;
    
do { 
 System.out.println("choose the below options");
 System.out.println("1.Search Book");
 System.out.println("2.Add new Book"); 
//  System.out.println("3.Upgrade Qunatiy of Book");
 System.out.println("3.Show All Books");
 System.out.println("4.register Students");
 System.out.println("5.Show All Registered Students");
 System.out.println("6.Exit from the Admin");
  ch=sc.nextInt();
  switch(ch){
    case 1: SerachBook(con);
           break;
    case 2:AddnewBook(con);
           break;
    case 3:DisplayBooks(con);
           break;
    case 4:RegisterStudent(con);
             break;
    case 5:DisplayStudents(con);
            break;
    case 6: System.out.println("Thank You For LibraryManagement");
           break;
    default:System.out.println("Enter Correct Option");
  }
} while (ch!=6);
}
public void SerachBook(Connection con) throws Exception{
servicesofbooks s=new servicesofbooks();
    System.out.println("Searching Book");
    System.out.println("1.Do You Want search By Serial NUmber");
    System.out.println("2.Do You Want  search By BookName ");
    int ch=sc.nextInt();
    switch(ch){
        case 1: s.searchbyid(con);
             break;
        case 2:s.searchbyname(con);
            break;
    }
}
public void AddnewBook( Connection con) throws Exception{
    System.out.println("enter the book details");
    int id;
    String s_no;
    String name;
    String aut_name; 
    int quantity;
    System.out.println("enter book_id");
    id=sc.nextInt();
    sc.nextLine();
    booksearch bb=new booksearch();
    if(bb.sidsearch(con, id)!=null){
        System.out.println("The book is already existing in the library so go for increment of quantity");
         bb.quantityincrease(con,id);
    }
    else{
        System.out.println("enter the s_id of book");
        s_no=sc.nextLine();
        System.out.println("enter book name");
        name=sc.nextLine();
        System.out.println("enter Author name");
        aut_name=sc.nextLine();
       book b=new book();
       b.setId(id);
       b.setAut_name(aut_name);
       b.setName(name);
       b.setS_no(s_no);
       b.setQuantity(1);
       bb.AddBook(con, b);
    }
}
public void DisplayBooks(Connection con) throws Exception{
    booksearch  b=new booksearch();
    List<book> bb =b.displaybooks(con);
    System.out.println("ID"+" "+"S_no"+" "+"name"+" "+"Author"+" "+"Quantity");
    for(book x:bb){
        System.out.println(x.getId()+" "+x.getS_no()+" "+x.getName()+" "+x.getAut_name()+" "+x.getQuantity());
    }
}
public void RegisterStudent(Connection con) throws Exception{
    System.out.println("enter the student details");
    int id;
    String name; 
    int reg_id;
    String pass;
    String type;
    System.out.println("enter student id");
    id=sc.nextInt();
    sc.nextLine();
    System.out.println("enter student name");
    name=sc.nextLine();
    System.out.println("enter reg_id");
    reg_id=sc.nextInt();
    sc.nextLine();
    System.out.println("enter  password");
     pass=sc.nextLine();
     System.out.println("enter type of services admin or student");
     type=sc.nextLine();
    studentsearch s=new studentsearch(); 
    student ss=new student();
    ss.setId(id);
    ss.setName(name);
    ss.setReg_id(reg_id);
    s.AddStudent(con, ss,pass,type);
    }


    public void DisplayStudents(Connection con) throws Exception{
        studentsearch  s=new studentsearch();
        List<student> ss=s.displaystudents(con);
        System.out.println("Students Table");
        System.out.println("ID"+" "+"name"+" "+"Reg_num");
        for(student x:ss){
            System.out.println(x.getId()+" "+x.getName()+" "+x.getId());
        }
    }




    public void displayusermethods(Connection con) throws Exception{
        int ch;
        
    do { 
     System.out.println("choose the below options");
     System.out.println("1.Search Book");
     System.out.println("2.Check Out Book"); 
     System.out.println("3.Check In Book");
     System.out.println("4.Exit from user");
      ch=sc.nextInt();
      switch(ch){
        case 1: SerachBook(con);
               break;
        case 2:CheckOutBook(con);
               break;
        case 3:CheckInBook(con);
               break;
        case 4: System.out.println("Thank You For connectiong Library");
               break;
        default:System.out.println("Enter Correct Option");
      }
    } while (ch!=4);
    }

 public void CheckOutBook(Connection con) throws Exception{
    System.out.println("here the list of books in library");
    DisplayBooks(con);
    System.out.println("Enter Book Id which You want barrow from library");
    int bid=sc.nextInt();
    booksearch b=new booksearch();
   book bb= b.sidsearch(con, bid);
   if(bb==null){
    System.out.println("Book you searched for not available in java");
    return;
   }
   else{
    System.out.println("Enter your Id");
    int id=sc.nextInt();
    bookingdetails bd=new bookingdetails();
    bd.AddBooking_checkout(con,id,bid,"checkout");
   }

 }
 public void CheckInBook(Connection con) throws Exception{
    System.out.println("Enter the book Id");
    int bid=sc.nextInt();
    System.out.println("enter your Id");
    int id=sc.nextInt();
    bookingdetails bd=new bookingdetails();
    bd.AddBooking_checkin(con,id,bid,"checkin");
 }



















}
