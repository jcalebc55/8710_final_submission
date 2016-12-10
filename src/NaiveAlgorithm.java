
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
public class NaiveAlgorithm {
    
    
    
    
    
    public static void main(String args[]){
        
        try{
            siblingInsertDelete.deleteSibling();
            siblingInsertDelete.populateSibling();
            cousinInsertDelete.deleteCousins();
            cousinInsertDelete.populateFirstCousins();
            while(cousinInsertDelete.areThereNewCousins()){
                cousinInsertDelete.populateSecondaryCousins();
            }
            
            
        }catch(Exception e){
        System.out.println("Exception caught"+e);
    }
    }
    
    
    
    
    
    
    
}