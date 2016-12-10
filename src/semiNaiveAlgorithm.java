
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
public class semiNaiveAlgorithm {
    
     public static void main(String args[]){
        
        try{
            siblingInsertDelete.deleteSibling();
            siblingInsertDelete.populateSibling();
            cousinInsertDelete.deleteCousins();
            cousinInsertDelete.populateFirstCousins();
            while(cousinInsertDelete.areThereNewCousins()){
                refreshDeltaCousins();
                popuateSecondaryCousinsUsingDelta();
            }
            
            
        }catch(Exception e){
        System.out.println("Exception caught"+e);
    }
    }
     
     //Used for populating the cousin table using semi-naive
     
     public static boolean refreshDeltaCousins() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
    Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                      String host = "jdbc:derby://localhost:1527/sample";
                      String uname="app";
                      String passwd="app";
                     
                         Connection con = DriverManager.getConnection(host, uname,passwd);
                       Statement insertStatement = con.createStatement();   
                       Statement deleteStatement = con.createStatement();   
                       con.setAutoCommit(true);
                       String deleteSql="delete from DELTA_COUSIN\n";
                       deleteStatement.execute(deleteSql);
                       
      String selectSql="insert into DELTA_COUSIN\n" +
"(select par1.CHILD as cousin1,par2.CHILD as cousin2 from test_par par1,test_par par2,test_cousin cou\n" +
"          where par1.parent=cou.cousin1 and par2.parent=cou.cousin2 \n" +
"   except \n" +
"   select * from test_cousin)";
    boolean b=insertStatement.execute(selectSql);
    return b;
    
}

     public static boolean popuateSecondaryCousinsUsingDelta() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
    Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                      String host = "jdbc:derby://localhost:1527/sample";
                      String uname="app";
                      String passwd="app";
                     
                         Connection con = DriverManager.getConnection(host, uname,passwd);
                       Statement st = con.createStatement();   
                       con.setAutoCommit(true);
      String selectSql="insert into test_cousin ( select * from delta_cousin)";
    boolean b=st.execute(selectSql);
    return b;
    
}

     
     
    
}
