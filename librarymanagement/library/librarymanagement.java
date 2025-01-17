package library;
import loginservices.services;
public class librarymanagement{
public static void main(String args[]) throws Exception{
    System.out.println("Welcome to Library");
    System.out.println("Please do Login");
   
    services sc=new services();
    sc.doLogin();
    
}
}