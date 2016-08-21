/*
 * Node class 
 * Defines a basic entities that are to be saved in a record.
 */

package bbm;

import java.io.*;

/**
 *
 * @author Dell
 */
/* Node class defined for making linked list*/
public class Node implements Serializable  
{
    /* fields declared here*/
    private static final long serialVersionUID = 1L;  
    protected int ID;
    protected String Name;
    protected String BloodGroup;
    protected String BirthDate;
    protected String DonateDate;
    protected String password;
    protected Node Link_ID;
    protected Node Link_BG;
    protected int Plasma;
    protected int Platelets;
    protected int RedBlood;
    String key;

    Node(int a, String s1, String s3, String s4,String s2, String s5, Node n1, Node n2) { // default constructor defined here
        this.ID = a;
        this.Name = s1;
        this.BirthDate = s3;
        this.DonateDate = s4;
        this.BloodGroup = s2;
        this.password = s5;
        this.Link_ID = n1;
        this.Link_BG = n2;
        this.Plasma  = 0;
        this.Platelets = 0;
        this.RedBlood = 0;
    }

    /*getters and setters of the required fields*/
    public void SetLink_ID(Node m) {
        Link_ID = m;
    }

    public void SetLink_BG(Node m) {
        Link_BG = m;
    }

    public int GetID() {
        return ID;
    }
    
    public int getRC_No(){
        return RedBlood;
    }
    
    public int getPlat_No(){
        return Platelets;
    }
    
    public int getPlas_No(){
        return Plasma;
    }

    public Node GetLink_ID() {
        return Link_ID;
    }

    public Node GetLink_BG() {
        return Link_BG;
    }
    
    public void setRC_No(int val){
        this.RedBlood = val;
    }
    
    public void setPlas_No(int val){
        this.Plasma = val;
    }
    
    public void setPlat_No(int val){
        this.Platelets = val;
    }
    /* display function to display the records */
    public void display(){   
        System.out.println();
        System.out.printf("%-20s%-5d%-10s%4d%9d%6d%16s%13s", 
                           this.Name, this.ID, this.BloodGroup, this.RedBlood, this.Platelets, this.Plasma, this.DonateDate, this.BirthDate);
        System.out.println();
    }
}
