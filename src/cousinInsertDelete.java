
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
public class cousinInsertDelete {
     public static void populateFirstCousins() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                      String host = "jdbc:derby://localhost:1527/sample";
                      String uname="app";
                      String passwd="app";
                     
                         Connection con = DriverManager.getConnection(host, uname,passwd);
                       Statement st = con.createStatement();   
                       con.setAutoCommit(true);
                       String insertSql="insert into test_cousin(\n" +
"  select par1.CHILD,par2.CHILD from test_par par1,test_par par2,test_sib sib\n" +
"    where par1.parent=sib.sibling1 and par2.parent=sib.sibling2  group by par1.CHILD,par2.CHILD"
                               + ")\n";
                       st.execute(insertSql);
             st.close();
    
}
    
    public static void deleteCousins() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
    
      Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                      String host = "jdbc:derby://localhost:1527/sample";
                      String uname="app";
                      String passwd="app";
                     
                         Connection con = DriverManager.getConnection(host, uname,passwd);
                       Statement st = con.createStatement();   
                       con.setAutoCommit(true);
                       String deleteSql=" delete from test_cousin";
                       st.execute(deleteSql);
             st.close();
}
 
    
    
     public static boolean doesCousinExist(String cousin1,String cousin2) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                      String host = "jdbc:derby://localhost:1527/sample";
                      String uname="app";
                      String passwd="app";
                     
                         Connection con = DriverManager.getConnection(host, uname,passwd);
                       Statement st = con.createStatement();   
                       con.setAutoCommit(true);
                       String selectSql="select  * from test_cousin where cousin1= '"+cousin1+"' and cousin2='"
                               +cousin2+"'";
                       ResultSet rs=st.executeQuery(selectSql);
                       
                      if(rs.next()){
                          System.out.println(rs.getString(1)+":"+rs.getString(2)+": exist ");
                      }else{
                          System.out.println("No such cousins exist");
                          return false;
                      }
                               
    return true;
     }
    
     public static boolean insertCousin(String cousin1,String cousin2) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                      String host = "jdbc:derby://localhost:1527/sample";
                      String uname="app";
                      String passwd="app";
                     
                         Connection con = DriverManager.getConnection(host, uname,passwd);
                       Statement st = con.createStatement();   
                       con.setAutoCommit(true);
                       String selectSql="insert into test_cousin values ('"+cousin1+"','"+cousin2+"')";
                       boolean b=st.execute(selectSql);
                       
                      
                               
    return b;
     }


public static void populateSecondaryCousins() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
    
      Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                      String host = "jdbc:derby://localhost:1527/sample";
                      String uname="app";
                      String passwd="app";
                     
                         Connection con = DriverManager.getConnection(host, uname,passwd);
                       Statement st = con.createStatement();   
                       con.setAutoCommit(true);
                       String selectSql="select par1.CHILD,par2.CHILD from test_par par1,test_par par2,test_cousin cou\n" +
"           where par1.parent=cou.cousin1 and par2.parent=cou.cousin2 ";
                       ResultSet resultSet=st.executeQuery(selectSql);
                       int i=1;
                      while( resultSet.next()){
                          String cousin1=resultSet.getString(1);
                          String cousin2=resultSet.getString(2);
                          
                          if(cousinInsertDelete.doesCousinExist(cousin1, cousin2)){
                              //do nothing if cousin exists
                          }else{
                              //insert cousins
                              cousinInsertDelete.insertCousin(cousin1, cousin2);
                          }
                      }
                       
                       
             st.close();
}


public static boolean areThereNewCousins() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
    Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                      String host = "jdbc:derby://localhost:1527/sample";
                      String uname="app";
                      String passwd="app";
                     
                         Connection con = DriverManager.getConnection(host, uname,passwd);
                       Statement st = con.createStatement();   
                       con.setAutoCommit(true);
      String selectSql="select par1.CHILD as cousin1,par2.CHILD as cousin2 from test_par par1,test_par par2,test_cousin cou\n" +
"             where par1.parent=cou.cousin1 and par2.parent=cou.cousin2 \n" +
" except\n" +
"select * from TEST_COUSIN";
    ResultSet resultSet=st.executeQuery(selectSql);
    return resultSet.next();
    
}


}
