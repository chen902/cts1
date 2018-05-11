/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foundation;

/**
 *
 * @author Chen
 */
public class DynamicStringArray {
    private String[] data;
    
    public DynamicStringArray(){
        data = new String[0];
    }
    
    public void add(String newLine){
        String[] temp = new String[data.length+1];
        for(int i=0; i<data.length;i++){
            temp[i] = data[i];
        }
        temp[temp.length-1] = newLine;
        
        data = temp;
    }
    
    public String[] getArray(){
        return data;
    }
    
    public String getString(int index){
        return data[index];
    }
    
    public int length(){
        return data.length;
    }
    

}
