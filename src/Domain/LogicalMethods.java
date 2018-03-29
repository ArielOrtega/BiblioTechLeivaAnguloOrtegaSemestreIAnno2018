/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Maria
 */
public class LogicalMethods {
    
    public String getStudentId(String career, String year, int number){
    String id= String.valueOf(career.charAt(0)
            +String.valueOf(year.charAt(3))
            +String.format("%03d", number));
    
    return id;
    }
    
}
