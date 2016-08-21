package bbm;
/*
 * BloodBankManagement Class
 * The main class containing the user interface.
 */

import java.util.Scanner;
import java.io.*;
import java.util.Hashtable;

/*Main class containing main methods */
public class BloodBankManagement implements Serializable {  

    private static final long sezrialVersionUID = 1L;
    /*Function containing menu for AdminFunctions*/
    public static void AdminchoiceDisplay() {
        System.out.println("\n CHOICES :-");
        System.out.println(" 1.> Add Record");
        System.out.println(" 2.> Modify Record");
        System.out.println(" 3.> Search(by Name+D.O.B.)");
        System.out.println(" 4.> Sort(by Name)");
        System.out.println(" 5.> Display(by ID)");
        System.out.println(" 6.> Display(by bloodgroup)");
        System.out.println(" 0.> EXIT Program");
        System.out.print("\n\n Enter your Choice : ");
    }
     /*Function containing menu for Donor Functions*/
    public static void DonorchoiceDisplay(){
        System.out.println("\n CHOICES :-");
        System.out.println(" 1.> Display Info");
        System.out.println(" 2.> Modify Record");
        System.out.println(" 0.> EXIT");
        System.out.print("\n\n Enter your Choice : ");
    }
   /*Function containg cases based on the choices made by the donor from the menu provided */
    public static void DonorFunctions(LinkedList List, Node n){
        int donor_choice = 1;
        Scanner d_scan = new Scanner(System.in);
        while(donor_choice  != 0){
           DonorchoiceDisplay();        // Displays Donor choices
           donor_choice = d_scan.nextInt();
           
           switch(donor_choice){
               case 1 :{
                   System.out.printf("%-20s%-5s%-10s%20s%15s%8s", "Name","ID","Blood-Group","Blood Avail   ","Donate Date","D.O.B");
                   System.out.println();
                   System.out.printf("%20s%5s%10s%3s%-9s%-6s","","","","  RC  ","Platelets","  Plasma");
                   List.get_info(n);
                   break;
               }
               case 2:{
                   List.donor_modify(n);
                   break;
               }
               case 0:{
                   donor_choice = 0;
                   saveDB(List);
                   System.out.println("You have successfully Signed Off!");
                   break;
               }
           }
        }
    } 
    /*Function containing cases for the choices made by the admin from the menu provided*/
    public static void AdminFunctions(LinkedList List){  
        int admin_choice = 1;
        Scanner m_scan = new Scanner(System.in);
        while (admin_choice != 0) // Infinite Loop
        {
            AdminchoiceDisplay();	// Displays the Choices
            admin_choice = m_scan.nextInt();

            switch (admin_choice) {
                case 1: {
                    List.insert_record();
                    break;
                }
                
                case 2: {
                    List.modify();
                    break;
                }
                
                case 3: {
                    Scanner tempp = new Scanner(System.in);
                    System.out.print("type the key of the record you wanna search : ");
                    String key=tempp.nextLine();
                    List.get(key);                                        
                    break;
                }
                
                 case 4 : {
                    List.sort_name();
                 break;
                 }		 
                 
                case 5: {
                    List.display_all();
                    break;
                }

                case 6: {
                    List.display_BG();
                    break;
                }
                
                case 0: { /** Start of Serializing  */
                    saveDB(List);    
                    break;
                }
                default:
                    System.out.println("\t\t :P");	//In case of Invalid input		 		 
                }
        }
    }
    
    /** Function that saves the List by File Management*/
    public static void saveDB(LinkedList List){
        
        try {
                try (FileOutputStream fileOut = new FileOutputStream("DB1.txt");
                     ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                     out.writeObject(List);
                }
                System.out.printf("Serialized data is saved in DB1.txt File !!");
        } catch (IOException i) {         }
        System.out.println("\n\n\t Thank you for using the Program !!\n\t   Have a Nice Day :)");
    }
        /*Main Method starting*/
    public static void main(String[] args){
        int choice;
        String s1,s2;
        Scanner input = new Scanner(System.in);
        boolean data_exist = true;
        LinkedList List = new LinkedList();
                                                                            
        try {//...............................................................// Reads the DataBase and load it to the LinkedList if the file exists !!!
             try (FileInputStream fileIn = new FileInputStream("DB1.txt");
                  ObjectInputStream in = new ObjectInputStream(fileIn)) {
                  List = (LinkedList) in.readObject();
                 }
             } catch (FileNotFoundException f) {
                 System.out.println("DataBase Don't Exists!!");
                 System.out.println("Creating new Database...");
                 data_exist = false;
        } catch (ClassNotFoundException | IOException c) {     }
        
        
        List.days_updater(); //..................................................// Function that checks blood expiry condition
       
        
        Scanner last_one = new Scanner(System.in);
        while(true){
            System.out.println("\t**** Blood Bank Management Program (v1.2) ****");
            System.out.print("Enter your Username : ");
            s1 = last_one.nextLine();
            System.out.print("Enter Password : ");
                                                            // Following will be used for displaying ' ' in password field however doesn't work with IDE
            Console console = System.console();             // For password use
            s2 = new String(console.readPassword());
            
            if(s1.equals("ADMIN") && s2.equals("admin")){
                System.out.println("Logged in as ADMIN!!");
                AdminFunctions(List);
            }
            else if(s1.equals("R") && s2.equals((""))){
                System.out.print("Enter needed Bloodgroup :");
                String r_bg = input.next();
                Receiver r = new Receiver(r_bg);
                r.makeRecord(List);
                try{
                    r.fileinfo();
                }
                catch(Exception ex)
                {
                    System.out.println("\n Error Occured !! Try again :(\n" + ex);
                }
                System.out.println("\n DONE !!!\n File has been saved as "+r.getfilename()+".txt");
            }
            else{
                Node p = List.search(s1, s2);
                if(p == null){
                     System.out.println("UserID OR Password incorrect!!\n Try again..");
                }
                else{
                    DonorFunctions(List, p);
                }
            }
        }   
    }
}    