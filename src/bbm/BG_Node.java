/*
 * BG_Node class
 * A class specially made for the special links of the records based on the blood group of that particular record
 * which eneables us to skip a set of records if it doesnot match the required bloodgroup.
 */

package bbm;

import java.io.*;

/**
 *
 * @author Dell
 */
  
public class BG_Node implements Serializable { 

    private static final long serialVersionUID = 1L;
    protected String BG_name;
    protected Node nxt;
    protected int count_RC;
    protected int count_Plasma;
    protected int count_Platelets;

    BG_Node(String s) {
        this.BG_name = s;
        this.nxt = null;
        this.count_RC = 0;
        this.count_Platelets = 0;
        this.count_Plasma = 0;
    }
    
    /** boolean = true --> for cases of priority Queue  */
    /* function to set the special BG_Link to each record inserted*/
    public void SetBG_Node_Link(Node m, boolean p){  
        if(p == true){
            this.nxt = m;
            m.SetLink_BG(null);
        }
        else if(p == false){
            if (this.nxt == null) {
            this.nxt = m;
            m.SetLink_BG(null);
            } 
            else {
            Node temp = this.nxt;
            while (temp.Link_BG != null) {
                temp = temp.Link_BG;
            }
            temp.SetLink_BG(m);
            m.SetLink_BG(null);
            }
                                
            count_RC += m.getRC_No();           // For RC Component
            count_Platelets += m.getPlat_No();  // For Plasma Component
            count_Plasma += m.getPlas_No();         // For Platelets Component        
       }
    }
/*getters and setters for the required fields */
    public Node GetBG_Node_Link() {
        return nxt;
    }

    public int count_RCsize() {
        return count_RC;
    }
    
    public int count_Platsize(){
        return count_Platelets;
    }
    
    public int count_Plassize(){
        return count_Plasma;
    }
    
    public void set_RCcount(int n){
        this.count_RC = n;
    }
    
    public void set_Platcount(int n){
        this.count_Platelets = n;
    }
    
    public void set_Plascount(int n){
        this.count_Plasma = n;
    }
}