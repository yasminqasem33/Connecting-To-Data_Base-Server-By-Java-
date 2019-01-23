/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_bae;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nada
 */
class Person {

    int id;
    String fname;
    String mname;
    String lname;
    String email;
    int tel;
}

public class Data_bae {

    /**
     * @param args the command line arguments
     */
    static int counter=0;

    static Connection conn;


    public static boolean isConnected() {
        try {

            Class.forName("com.derby.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            // handle the error
        }

        try {
            conn
                    = DriverManager.getConnection("jdbc:derby://localhost:1527/dataBase22  ", "nada", "nada");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Data_bae.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public static void deletee(int id) {

        try {
            
            PreparedStatement p = conn.prepareStatement("DEleTE FROM PERSON WHERE id=" + id);
            p.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Data_bae.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void update(Person p) {
        String queryString;
        try {
            queryString = "UPDATE  person SET FIRSTNAME='" + p.fname + "',MIDDLENAME='" + p.mname + "',LASTNAME='" + p.lname + "',EMAIL='" + p.email + "',PHONE=" + p.tel + "where id=" + p.id + "";
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(queryString);
        } catch (SQLException ex) {
            Logger.getLogger(Data_bae.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Person firstt() {
         ArrayList<Person> data= new ArrayList<>();
       data= getAll();
        counter = 0;
        Person p = data.get(counter);

        return p;
    }

    public static Person lastt() {
        Person p;
         ArrayList<Person> data= new ArrayList<>();
        data=getAll();
        counter =data.size()-1;
      p=  data.get(counter);

        return p;

    }

    public static Person prev() {
      Person p;
         ArrayList<Person> data= new ArrayList<>();
        data=getAll();
        counter --;
        if(counter<0)
        {
            counter=data.size()-1;
        }
      p=  data.get(counter);


        return p;

    }

    public static Person nextt() {
        Person p;
         ArrayList<Person> data= new ArrayList<>();
        data=getAll();
        counter ++;
        if(counter>=data.size())
        {
            counter=0;
        }
          p=  data.get(counter);

        return p;

    }
   static int  id =0;
    public static void inseert(Person p) {

        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO person VALUES (?, ?,?,?,?,?)");
            pstmt.setInt(1, p.id);
            pstmt.setString(2, p.fname);
            pstmt.setString(3, p.mname);
            pstmt.setString(4, p.lname);
            pstmt.setString(5, p.email);
            pstmt.setInt(6, p.tel);

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Data_bae.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   static  int i = 0;

    public static ArrayList<Person> getAll() {
        PreparedStatement pst;
              ArrayList<Person> dataP= new ArrayList<>();

        try {
            pst = conn.prepareStatement("select * from person ");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                Person p = new Person();
                p.id=rs.getInt(1);
                p.fname = rs.getString(2);
                p.mname = rs.getString(3);
                p.lname = rs.getString(4);
                  p.tel = rs.getInt(6);
                p.email = rs.getString(5);
                dataP.add(p);
              
            }

        } catch (SQLException ex) {
            Logger.getLogger(Data_bae.class.getName()).log(Level.SEVERE, null, ex);

        }
        return dataP;
      

    }

    public static void main(String[] args) {


    }

}
