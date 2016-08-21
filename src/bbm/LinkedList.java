/*
 * LinkedList class for our project "Blood Bank Management"
 * Contains the methods that can be applied using Linked list data strucure.
 * Inside package bbm.
 */

package bbm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
/**
 *
 * @author Dell
 */ 
public class LinkedList implements Serializable{  //Class linkedlist containing  every methods required to perform the required functions
    public Node start;
    protected Node end;
    protected BG_Node Ap = new BG_Node("Ap");
    protected BG_Node An = new BG_Node("An");
    protected BG_Node Bp = new BG_Node("Bp");
    protected BG_Node Bn = new BG_Node("Bn");
    protected BG_Node ABp = new BG_Node("ABp");
    protected BG_Node ABn = new BG_Node("ABn");
    protected BG_Node Op = new BG_Node("Op");
    protected BG_Node On = new BG_Node("On");
    public int size;
    public int val = 1;
    private static final long serialVersionUID = 1L;
    // Making the Scanner object transient as to avoid the errors when using the Serialization concept
    transient Scanner input = new Scanner(System.in);   // Scanner object created
    public int TABLE_SIZE = 5000;
    
    public Node[] table = new Node[TABLE_SIZE];  // defining the array table of Node type for hashtable purpose
   
    
    LinkedList(){  // defining default constructor
        start = null;
        end = null;
        size = 0;
    }

    public boolean isEmpty() {		// Returns whether List is empty or not as true/false
        return start == null;
    }
    public int getsize()                //to get the size of the list
    {
        return size;
    }

    /** Function that calculates the difference of days from the current System date  */
    public long calculateDays(String s){    
        long days = 1;
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        String t_date = myFormat.format(new Date());
        try {
            Date d1 = myFormat.parse(s);
            Date d2 = myFormat.parse(t_date);
            
            long diff = d2.getTime() - d1.getTime();
            
            long diffDays = diff / (24 * 60 * 60 * 1000);
            days = diffDays;
        } catch (ParseException e) {        }
        return days;
    }
    
    public int myhash(String s){  // function to calculate hashvalue from the hashcode of the particular string and based on that hashval the location 
        int hashVal = s.hashCode(); //of the record in the hashtable is decided  
        hashVal %= TABLE_SIZE;
        if(hashVal < 0){
            hashVal += TABLE_SIZE;
        }
        return hashVal;
    }
    
    public void printHashTable() // function to print the particular record of the hashtable
    {
        for (int i = 0; i < TABLE_SIZE; i++)
        {
            System.out.println("Bucket "+ (i +1) +" : ");
            Node entry = table[i];
            while (entry != null)
            {
                System.out.printf("%-20s%-5s%-10s%20s%15s%8s", "Name","ID","Blood-Group","Blood Avail   ","Donate Date","D.O.B");
                System.out.println();
                System.out.printf("%20s%5s%10s%3s%-9s%-6s","","","","  RC  ","Platelets","  Plasma"); 
                entry.display();
                entry = entry.Link_ID;
            }            
        }
    }
    
    public int get(String key) //Function to find the record uniquely using the key generated at the time of insertion of generated after modification 
    {                          // of the record          
        int hash = (myhash( key ) % TABLE_SIZE);
        if (table[hash] == null)
        { System.out.println("No record found !!");
            return -1;}
        else 
        {
            Node entry = table[hash];
             while (entry.Link_ID != null && !entry.key.equals(key))
                entry = entry.Link_ID;
            if (entry == null)
            {System.out.println("Record Not Found!");
                return -1;}
            else
            {
                System.out.printf("%-20s%-5s%-10s%20s%15s%8s", "Name","ID","Blood-Group","Blood Avail   ","Donate Date","D.O.B");
                System.out.println();
                System.out.printf("%20s%5s%10s%3s%-9s%-6s","","","","  RC  ","Platelets","  Plasma");
                entry.display();
            }
            
                return entry.ID; 
        }
    }
    
     
     
    /** Calculates the Expiry date from the given date  */
    public void bloodAdd(long n, Node temp){
        if(n <= 5){
            temp.setPlat_No(1);
        }
        if(n <= 90){
            temp.setRC_No(1);
        }
        if(n <= 365){
            temp.setPlas_No(1);
        }
    }
    
    public void hashkeygen(String key, String s1,String  s3,String  s4,String  s2,String  s5){ //function to modify the key once Name or D.O.B. of a 
        int hash = (myhash(key) % TABLE_SIZE);                                                 //record are modified
             if (table[hash] == null) {
            table[hash] = new Node(val, s1, s3, s4, s2, s5,null,null);
        } else {
            Node entry = table[hash];
            while (entry.Link_ID != null && !entry.key.equals(key)) {
                entry = entry.Link_ID;
            }
            if (entry.key.equals(key)) 
            {
                entry.ID = val;
            } else {
                entry.Link_ID = new Node(val, s1, s3, s4, s2, s5,null,null);
            }
       }
    }
    
    /*  Function to insert an element at begining  */
    public void insert_record() {         
        Scanner scan = new Scanner(System.in);
        
        System.out.println("\n Entering a new Record....");
        System.out.print("\n Name: ");
        String s1 = scan.nextLine();
        
        System.out.print(" Date Of Birth (Format DD/MM/YYYY) :");
        String s3 = scan.nextLine();

        long age = calculateDays(s3);
        System.out.println("\tYour age is : "+(age/365)+" Years.");
        if((age/365) >= 18 && (age/365) <= 50){
            System.out.println("\tELIGIBLE!!");
        }
        else{
            System.out.println("\tNOT ELIGIBLE!!");
            return;
        }
        
        System.out.print(" Donation Date (Format DD/MM/YYYY): ");
        String s4 = scan.nextLine();
       
        long days = calculateDays(s4);
        if(days >= 90){
                System.out.println("\tBlood has Expired!!");
                return;
            }
        else{
             System.out.println("\tBlood is Fresh.\n\tDays passed are : " + days);
        }
        
        System.out.print(" BloodGroup: ");
        String s2 = scan.nextLine();
        
        System.out.print(" Password:");
        String s5=scan.nextLine();
        
        String key=s1+s3;
        System.out.println("your key id is "+key);

        Node temp = new Node(val, s1, s3, s4, s2, s5, null, null);
        bloodAdd(days, temp);
        size++;
        val++;
        link_setter(s2, temp);

        if (isEmpty()) {
            start = temp;
            end = start;
        } else {
            end.SetLink_ID(temp);
            end = temp;
        }
        
        hashkeygen(key, s1, s3, s4, s2, s5);
        
        priorityQueue(temp);    // Make the appropriate Linkes
    }

    /**     Function that sets the Sp_Link of a node    */
    public void link_setter(String s, Node temp) {
        if ("A+".equals(s)) {
            Ap.SetBG_Node_Link(temp, false);
        } else if ("A-".equals(s)) {
            An.SetBG_Node_Link(temp, false);
        } else if ("B+".equals(s)) {
            Bp.SetBG_Node_Link(temp, false);
        } else if ("B-".equals(s)) {
            Bn.SetBG_Node_Link(temp, false);
        } else if ("AB+".equals(s)) {
            ABp.SetBG_Node_Link(temp, false);
        } else if ("AB-".equals(s)) {
            ABn.SetBG_Node_Link(temp, false);
        } else if ("O+".equals(s)) {
            Op.SetBG_Node_Link(temp, false);
        } else if ("O-".equals(s)) {
            On.SetBG_Node_Link(temp, false);
        }
    }

    /**     Function that takes a string and returns the BG_Node accordingly    */
    public BG_Node BG_Node_getter(String s){
        if ("A+".equals(s)) {
            return Ap;
        } else if ("A-".equals(s)) {
            return An;
        } else if ("B+".equals(s)) {
            return Bp;
        } else if ("B-".equals(s)) {
            return Bn;
        } else if ("AB+".equals(s)) {
            return ABp;
        } else if ("AB-".equals(s)) {
            return ABn;
        } else if ("O+".equals(s)) {
            return Op;
        } else if ("O-".equals(s)) {
            return On;
        } else{
            return null;
        }
            
    }
    
    /** Donor Modification function that is accessed by Donor on login  */
    public void donor_modify(Node n){
        boolean run = true;
        Scanner scans = new Scanner(System.in);
        do {
            System.out.println("Choose what you want to change");
            System.out.println("1) Name\t 2.) D.O.B.\t 3.) Blood-Group\t 0.) Exit");
            int opt1 = scans.nextInt();

            switch (opt1) {
                case 1: {
                    System.out.println("Enter your name");
                    scans.nextLine();
                    String modified_name = scans.nextLine();
                    
                    n.Name = modified_name;
                    String key = n.Name + n.BirthDate;
                    hashkeygen(key, n.Name, n.BirthDate, n.DonateDate, n.BloodGroup, n.password);
                    System.out.println("Change Successful");
                    run = false;
                    break;
                    }                    
                    
                case 2: {
                    System.out.println("Enter your Date of Birth");
                    String modified_DOB = scans.next();
                    n.BirthDate = modified_DOB;
                    String key = n.Name + n.BirthDate;
                    hashkeygen(key, n.Name, n.BirthDate, n.DonateDate, n.BloodGroup, n.password);
                    System.out.println("Change Successful");
                    run = false;
                    break;
                    }
                    
                case 3: {
                    System.out.println("Enter your Blood Group");
                    String modified_BG = scans.next();
                           
                    change_BG(n, modified_BG);
                    n.BloodGroup = modified_BG;
                    link_setter(modified_BG, n);
                    System.out.println("Change Successful");
                    run = false;
                    break;
                    }
                case 0:{ run = false;   }
            }
        } while (run != false);
    }
    
    /**     Modification function accessed by ADMIN     */
    public void modify() {
        Node i;
        int option;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter Name : ");
        String temp_name = s.nextLine();
        System.out.print("Enter Blood-Broup: ");
        String temp_bg = s.next();

        for (i = start; i != null; i = i.GetLink_ID()) {
            if (i.Name.equals(temp_name) && i.BloodGroup.equals(temp_bg)) {
                System.out.println("Record exists...");
                break;
            }
        }
        if(i == null) {
            System.out.println("Record not found !");
            return;
        }
        Scanner p = new Scanner(System.in);
        do {
            System.out.println("Choose what you want to change");
            System.out.println("1) Name");
            System.out.println("2) DOB");
            System.out.println("3) Blood Group");
            System.out.println("0) Exit");
            option = p.nextInt();

            switch (option) {
                case 1: {
                    System.out.println("Enter your name");
                    p.nextLine();
                    String modified_name = p.nextLine();
                    for (i = start; i != null; i = i.GetLink_ID()) {
                        if (i.Name.equals(temp_name) && i.BloodGroup.equals(temp_bg)) {
                            i.Name = modified_name;
                            temp_name = i.Name;
                            String key = i.Name + i.BirthDate;
                            hashkeygen(key, i.Name, i.BirthDate, i.DonateDate, i.BloodGroup, i.password);
                            System.out.println("Modification Successful");
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.println("Enter your Date of Birth");
                    String modified_DOB = p.next();
                    for (i = start; i != null; i = i.GetLink_ID()) {
                        if (i.Name.equals(temp_name) && i.BloodGroup.equals(temp_bg)) {
                            i.BirthDate = modified_DOB;
                            String key = i.Name + i.BirthDate;
                            hashkeygen(key, i.Name, i.BirthDate, i.DonateDate, i.BloodGroup, i.password);
                            System.out.println("Modification Successful");
                            break;
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.println("Enter your Blood Group");
                    String modified_BG = p.next();
                    for (i = start; i != null; i = i.GetLink_ID()) {
                        if (i.Name.equals(temp_name) && i.BloodGroup.equals(temp_bg)) {
                            
                            change_BG(i, temp_bg);
                            i.BloodGroup = modified_BG;
                            temp_bg = i.BloodGroup;
                            link_setter(modified_BG, i);
                            System.out.println("Modification Successful");
                            break;
                        }
                    }
                    break;
                }
            }
        } while (option != 0);
    }
    
    /** Function that is called in Modify function 
     *  Changes the BG_Node link of a Node
     */
    public void change_BG(Node m, String s){
        BG_Node t = BG_Node_getter(s);
        if(t.nxt == m){
            t.nxt = m.Link_BG;
        }
        else{
            Node temp = t.nxt;
                while(temp.Link_BG != m)
                    temp = temp.Link_BG;
                temp.SetLink_BG(m.GetLink_BG());
        }
        t.count_RC = t.count_RC - m.getRC_No();
        t.count_Plasma = t.count_Plasma - m.getPlas_No();
        t.count_Platelets = t.count_Platelets - m.getPlat_No();
    }
    
    /** Search function that searches for a user on login   */
    public Node search(String s1, String s2){
        Node finder;    
        for (finder = start; finder != null; finder = finder.GetLink_ID()){
            if(finder.password.equals(s2) && finder.ID == (Integer.parseInt(s1))){
              return finder;
            }
        }
        return finder;  // returns null is no user found!
    }

    /**     Function to display a Node  */
    public void get_info(Node n) {
        n.display();
    }

    /** Function that displays the Whole database   */
    public void display_all() {
        System.out.println("Database :-");
        System.out.printf("%-20s%-5s%-10s%20s%15s%8s", "Name","ID","Blood-Group","Blood Avail   ","Donate Date","D.O.B");
        System.out.println();
        System.out.printf("%20s%5s%10s%3s%-9s%-6s","","","","  RC  ","Platelets","  Plasma");
        
        if (size == 0) {
            System.out.println("\n Database is Empty!!");
        } else if (size == 1) {
            get_info(start);
        } else {
            Node ptr = start;
            get_info(start);
            ptr = ptr.GetLink_ID();
            while (ptr != null) {
                get_info(ptr);
                ptr = ptr.GetLink_ID();
            }
        }
        System.out.println("\n\tTotal Entries in DB are " + size);
    }

    /** Function to display records of a specific Blood-group   */
    public void display_BG() {
        Scanner s = new Scanner(System.in);
        System.out.println("Which blood group's info you want");
        System.out.println("1) A+\t2) A-\t3) B+\t4) B-\n5) AB+\t6) AB-\t7) O+\t8) O-");
        System.out.println("Write the No. For eg: Write 'A+' for 1st option: ");
        String select = s.next();
        if(select == "A+" || select == "A-" || select == "B+" || select == "B-" || select == "AB+" ||
                select == "AB-" || select == "O+" || select == "O-"){
            BG_Node t = BG_Node_getter(select);
        
        System.out.printf("%-20s%-5s%-10s%20s%15s%8s", "Name","ID","Blood-Group","Blood Avail   ","Donate Date","D.O.B");
        System.out.println();
        System.out.printf("%20s%5s%10s%3s%-9s%-6s","","","","  RC  ","Platelets","  Plasma");
        
        Node temp = t.GetBG_Node_Link();
        while(temp != null){
            get_info(temp);
            temp = temp.GetLink_BG();
        }
        System.out.println("\nTotal " + t.count_RCsize() + " bottles Found!!");
        }
        else
        {
            System.out.println("Invalid Input!! Try Again");
        }
        
    }
    
    
    /** 
     * Priority Queue Algorithm based on highest priority as Top
     * Parameter taken in consideration is Donation Date
     */
    public void priorityQueue(Node n){
        BG_Node Sp = BG_Node_getter(n.BloodGroup);
        Node d1 = Sp.GetBG_Node_Link();
        Node d2 = d1;
        if(Sp.GetBG_Node_Link() == n ){         }       // Do nothing
        
        else if(d1.GetLink_BG() == n && calculateDays(d1.DonateDate) <= calculateDays(n.DonateDate)){
            Sp.SetBG_Node_Link(n, true);
            n.SetLink_BG(d1);
            d1.SetLink_BG(null);
        }
        else if(d1.GetLink_BG() == n && calculateDays(d1.DonateDate) > calculateDays(n.DonateDate)){
            // Do nothing
        }
        else if(calculateDays(d1.DonateDate) <= calculateDays(n.DonateDate)){
            while(d2.GetLink_BG() != n)
                d2 = d2.GetLink_BG();
            Sp.SetBG_Node_Link(n, true);
            n.SetLink_BG(d1);
            d2.SetLink_BG(null);
        }
        else {
            while(calculateDays(d1.DonateDate) > calculateDays(n.DonateDate)){
                d2 = d1;
                d1 = d1.GetLink_BG();
            }
            Node dummy = d1;
            if(dummy != n){
                while(dummy.GetLink_BG() != n)
                dummy = dummy.GetLink_BG();
                dummy.SetLink_BG(null);
                d2.SetLink_BG(n);
                n.SetLink_BG(d1);
            }
        } 
    }
    
    /**     Sort by names Function - Insertion Sort  */
    public void sort_name() {
        Node i;
        int j = 0;
        int z;
        String names[] = new String[size];
        for (i = start; i != null; i = i.GetLink_ID()) {
            names[j] = i.Name;
            j++;
        }
        Arrays.sort(names);
        System.out.println("Database :-");

        System.out.printf("%-20s%-5s%-10s%20s%15s%8s", "Name","ID","Blood-Group","Blood Avail   ","Donate Date","D.O.B");
        System.out.println();
        System.out.printf("%20s%5s%10s%3s%-9s%-6s","","","","  RC  ","Platelets","  Plasma");

        if (size == 0) {
            System.out.println("Database is Empty!!");
        } else if (size == 1) {
            get_info(start);
        } else {
            for (j = 0; j <= size - 1; j++) {
                for (i = start; i != null; i = i.GetLink_ID()) {
                    if (names[j].equals(i.Name)) {
                        get_info(i);
                    }
                }
            }
        }
        System.out.println("\n\tTotal Entries in DB are " + size);
    }
        
    /** This function will be called after loading the database from the file
        Checks for any expiry blood and deletes it if expiry one is found       */
    public void ValidityChecker(String s){
        BG_Node t = BG_Node_getter(s);
        Node r1 = t.GetBG_Node_Link();
        
        if(r1 == null){}                                        // Do  nothing
        else if(calculateDays(r1.DonateDate) > 5)               // For Plateletes condition
        {
            if(r1.GetLink_BG() == null){
            if(calculateDays(r1.DonateDate) > 5){
                r1.setPlat_No(0);
                if(calculateDays(r1.DonateDate) > 90){          // For Blood component condition
                    r1.setRC_No(0);
                    t.set_RCcount(t.count_RCsize() - 1);
                }
                if(calculateDays(r1.DonateDate) > 365){         // For Plasma component condition
                    r1.setPlas_No(0);
                    t.set_Plascount(t.count_Plassize() - 1);
                }
                
            }
                t.set_Platcount(t.count_Platsize() - 1);
            }
            else{
                 Node follower = r1;
                 while(r1.GetLink_BG() != null){
                    if(calculateDays(r1.DonateDate) > 5){
                        r1.setPlat_No(0);
                        if(calculateDays(r1.DonateDate) > 90){
                            r1.setRC_No(0);
                            t.set_RCcount(t.count_RCsize() - 1);
                        }
                        if(calculateDays(r1.DonateDate) > 365){
                            r1.setPlas_No(0);
                            t.set_Plascount(t.count_Plassize() - 1);
                        }
                            
                        follower = r1;
                        t.set_Platcount(t.count_Platsize() - 1);
                    }
                }
                 if(follower.GetLink_BG() == r1){
                    r1.SetLink_BG(t.GetBG_Node_Link());
                    t.SetBG_Node_Link(r1, false);
                    follower.SetLink_BG(null);
                 }
                 else{
                    r1.SetLink_BG(t.GetBG_Node_Link());
                    t.SetBG_Node_Link(follower.GetLink_BG(), false);
                    follower.SetLink_BG(null);
                 }
            }        
        }
        else{    }      // Do nothing
    }
    
    /**  This function checks whether blood is expired or not  */
    public void days_updater() 
        {
            ValidityChecker("A+");
            ValidityChecker("A-");
            ValidityChecker("B+");
            ValidityChecker("B-");
            ValidityChecker("AB+");
            ValidityChecker("AB-");
            ValidityChecker("O+");
            ValidityChecker("O-");
    }
    
    /** Search by Name Function     */
    public void search_by_Name(String name) {
        Node it;
        for (it = start; it != null; it = it.GetLink_ID()) {
            if (it.Name.equals(name)) {
                System.out.printf("%-20s%-5s%-10s%20s%15s%8s", "Name","ID","Blood-Group","Blood Avail   ","Donate Date","D.O.B");
                System.out.println();
                System.out.printf("%20s%5s%10s%3s%-9s%-6s","","","","  RC  ","Platelets","  Plasma");
                System.out.println();
                it.display();
                System.out.println();
                return;
            }
        }
        if (it == null) {
            System.out.println("Not Found!!");
        }
    }
}


    
    