
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Caleb
 */
public class InsertParent {
    
    
    
    private static void insertIntoTable(String insertString) {
         //To change body of generated methods, choose Tools | Templates.
         try{
                 Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                      String host = "jdbc:derby://localhost:1527/sample";
                      String uname="app";
                      String passwd="app";
                     
                         Connection con = DriverManager.getConnection(host, uname,passwd);
                       Statement st = con.createStatement();   
                       con.setAutoCommit(false);
                       BufferedReader br = new BufferedReader(new FileReader(insertString));
                        String line=null;
                        line=br.readLine();
                        int count=1;
                        while(line !=null)
                        {
                            if(line!=null && line.startsWith("insert"))
                            {System.out.println(line+" "+count++);
                              st.executeUpdate(line);}
                            line=br.readLine();
                            
                         
                         
//                        if(count++==500){con.commit(); 
//                        count=0;
//                        }
                        }
                       
                       
                       
                                                 st.close();
         
         }
                      catch (Exception ex) {
                         Logger.getLogger(InsertParent.class.getName()).log(Level.SEVERE, null, ex);
                     }}
    
    
    public static void main(String args[]){
        System.out.println("Insert parent");
    
    insertIntoTable("/home/Caleb/Desktop/Raj_course/prolog/8710/hw5/edb1.sql");
    }
    
    
}
