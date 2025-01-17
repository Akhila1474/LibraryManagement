package bookservices;
import books.book;
import databseconnection.booksearch;
import java.sql.Connection;
import java.util.Scanner;
public class servicesofbooks {
    booksearch b=new booksearch() ;
    public void searchbyid(Connection con) throws Exception{
    Scanner sc=new Scanner(System.in); 
    System.out.println("Enter the book id ");
    int id=sc.nextInt();
   book bb= b.sidsearch(con,id);
   if(bb!=null){
    System.out.println("=========Yes Book found,Details=======");
    System.out.println("book name "+bb.getName()+" "+"book Id"+bb.getId()+" "+"book_serail_num"+bb.getS_no()+" "+"book_author"+bb.getAut_name()+" "+"book Quantity"+bb.getQuantity());
   }
   else{
    System.out.println("book not found by sid");
   }
    }
    public void searchbyname(Connection con) throws Exception{
        Scanner sc=new Scanner(System.in); 
        System.out.println("Enter the book name");
        String n=sc.nextLine();
       book bb= b.namesearch(con, n);
       if(bb!=null){
        System.out.println("=========Yes Book found,Details=======");
        System.out.println("book name "+bb.getName()+" "+"book Id"+bb.getId()+" "+"book serail num"+bb.getS_no()+" "+"book author"+bb.getAut_name()+" "+"book Quantity"+bb.getQuantity());
       }
       else{
        System.out.println("book not found by name");
       }
    }
}
