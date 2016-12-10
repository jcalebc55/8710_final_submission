
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Caleb
 */
public class siblingInsertDelete {
    public static void populateSibling() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                      String host = "jdbc:derby://localhost:1527/sample";
                      String uname="app";
                      String passwd="app";
                     
                         Connection con = DriverManager.getConnection(host, uname,passwd);
                       Statement st = con.createStatement();   
                       con.setAutoCommit(true);
                       String insertSql=" insert into test_sib (  \n" +
" select par1.CHILD,par2.CHILD from test_par par1,test_par par2\n" +
"   where par1.child<par2.CHILD and par1.PARENT=par2.PARENT group by par1.CHILD,par2.CHILD)";
                       st.execute(insertSql);
             st.close();
    
}
    
    public static void deleteSibling() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
    
      Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                      String host = "jdbc:derby://localhost:1527/sample";
                      String uname="app";
                      String passwd="app";
                     
                         Connection con = DriverManager.getConnection(host, uname,passwd);
                       Statement st = con.createStatement();   
                       con.setAutoCommit(true);
                       String deleteSql=" delete from test_sib";
                       st.execute(deleteSql);
             st.close();
}
    
   
    
}
