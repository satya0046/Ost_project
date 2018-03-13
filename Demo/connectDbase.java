/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Abhinav
 */
public class connectDbase {
    
    
    
    Connection connect;
    Statement statement;
    connectDbase(){
        
        
        try{
        Class.forName("com.mysql.jdbc.Driver");
        
        
        connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?zeroDateTimeBehavior=convertToNull","root","root");
        
        statement=(Statement) connect.createStatement();
        
        
    }
        
        catch (Exception e){
            
            e.printStackTrace();
        }
}
    
    
    
    
    
    
    public void adminLoginDetails(String unameInput,String passwordInput)
    {
        ResultSet rs;
        
        String query="select *from adminlogin where uname='"+unameInput+"'";
        String temp="";
        try{
        rs=statement.executeQuery(query);
        while(rs.next()){   
            temp=rs.getString("password");
        }
        
        if(temp.equals(passwordInput))
        {
            adminDashBoard ad=new adminDashBoard();
            ad.setVisible(true);
        }
        else{
            JOptionPane jp=new JOptionPane("");
           jp.showMessageDialog(null,"incorrect password");   
            System.out.print("incorrect");
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    
    }
    public boolean isValid(String name,String username,String Password,String company,String contact)
    {
         
        if (name.equals(""))
        {
            JOptionPane jp=new JOptionPane("");
           jp.showMessageDialog(null,"Enter name");
           return false;
        }
        if (username.equals(""))
        {
            JOptionPane jp=new JOptionPane("");
           jp.showMessageDialog(null,"Enter username");
           return false;
        }
        
        if (Password.equals(""))
        {
            JOptionPane jp=new JOptionPane("");
           jp.showMessageDialog(null,"Enter Password");
           return false;
        }
        
        if (company.equals(""))
        {
            JOptionPane jp=new JOptionPane("");
           jp.showMessageDialog(null,"Enter Company ");
           return false;
        }
        
        if (contact.equals(""))
        {
            JOptionPane jp=new JOptionPane("");
           jp.showMessageDialog(null,"Enter Contact");
           return false;
        }
        int len=name.length();
        for(int i=0;i<len;i++)
        {
            char c=name.charAt(i);  
            if(c<'A'||(c>'Z'&&c<'a')||c>'z')
            {
                        JOptionPane jp=new JOptionPane("");
                        jp.showMessageDialog(null,"Name Invalid");
                        return false;
            }
        }
        ResultSet rs;
        
        String query="select * from userregisterdetails where userName='"+username+"';";
        String temp="";
        try{
        rs=statement.executeQuery(query);
        if(rs.next())
        {
            JOptionPane jp=new JOptionPane("");
                        jp.showMessageDialog(null,"Username already Exists");
                        return false;
        }
        }
        catch(Exception e)
        {
             e.printStackTrace();
        }
         len=contact.length();
        if(len!=10)
        {
                        JOptionPane jp=new JOptionPane("");
                        jp.showMessageDialog(null,"Contact Invalid");
                        return false;
        }
        for(int i=0;i<len;i++)
        {
            char c=contact.charAt(i);  
            if(Character.isLetter(c))
            {
                        JOptionPane jp=new JOptionPane("");
                        jp.showMessageDialog(null,"Contact Invalid");
                        return false;
            }
        }
        return true;
    }
    
    public void insertUserData(String name,String username,String Password,String company,String contact){
        
       boolean c= isValid(name,username,Password,company,contact);
       if(c)
       {
        try{
            
            
            
        String query="insert into userRegisterDetails values('"+name+"','"+username+"','"+Password+"','"+company+"','"+contact+"');";
        PreparedStatement ps=connect.prepareStatement(query);
        
        
        ps.execute();
 
            JOptionPane jp=new JOptionPane("");
           jp.showMessageDialog(null,"Registration successful!!");
           
        
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
       }
}
    
}