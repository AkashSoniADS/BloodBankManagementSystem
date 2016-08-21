/*
 * Receiver Class is defined for the RECEIVER application process.
 * Makes the Receipt and the notification txt files.
 */
package bbm;

import java.util.Scanner;
import java.io.*;
import java.util.Date;

/**
 *
 * @author Nand Parikh
 */
public class Receiver implements Serializable {    // class specially made for receiever functions 

    private String r_name;
    private String r_bd;
    private String r_bg;
    private int r_type;
    private int r_need;
    private String[] donors;
    private String[] r_donaters;

    transient Scanner input = new Scanner(System.in);

    Receiver(String s) {   //default constructor 
        r_bg = s;
        r_name = null;
        r_bd = null;
        r_need = 0;
    }

    /*
     * Function for searching whether the required blood with required characteristics is there or not, if there then how much quantity is needed and then 
     * asking for the details of the person who gives blood in exchange of the blood taken 
     */
    
    public void makeRecord(LinkedList List) {   
        System.out.println("** Your Request has been received... **\n Enter your details :-");
        System.out.print("Enter your Name : ");
        String s1 = input.next();
        System.out.print("Enter your Birthdate(DD/MM/YYYY) :");
        input.nextLine();
        String s2 = input.next();
        System.out.println("\n 1.) Red Component 2.) Platelets 3.) Plasma");
        System.out.print("What kind of type you need? :  ");
        r_type = input.nextInt();
        System.out.print("Quantity needed : ");
        r_need = input.nextInt();

        setData(s1, s2);
        int arr_pointer = 0;
        donors = new String[r_need];
        BG_Node t = List.BG_Node_getter(r_bg);
        if (r_type == 1) {
            System.out.println("Bank has" + t.count_RC + "bottles");
        }
        if (r_type == 2) {
            System.out.println("Bank has" + t.count_Platelets + "bottles");
        }
        if (r_type == 3) {
            System.out.println("Bank has" + t.count_Plasma + "bottles");
        }
        System.out.print("Do you still want to continue?<Y - yes> :");
        input.nextLine();
        String s5 = input.nextLine();
        if (s5.equals("Y") || s5.equals("y")) {
            System.out.println("\n\n\t According to Blood bank POLICY, you need to provide same amount of donation!!");
            System.out.println("Enter the Donor(s) details :-");
            int cnt2 = r_need;
            while (cnt2 != 0) {
                List.insert_record();
                cnt2--;
            }

            switch (r_type) {
                case 1: {
                    if (t.count_RCsize() >= r_need) {
                        Node r = t.GetBG_Node_Link();
                        int cnt = r_need;
                        while (cnt != 0) {
                            if (r.getRC_No() == 1) {
                                r.setRC_No(0);
                                donors[arr_pointer] = r.Name;
                                r.GetLink_BG();
                                arr_pointer++;
                                cnt--;
                            } else {
                                r = r.GetLink_BG();
                            }
                        }
                        t.set_RCcount(t.count_RCsize() - r_need);
                        System.out.println("Requested no. of bottles of type " + r_bg + " has been dispatched.!!");
                    } else {
                        System.out.println("We only have " + t.count_RCsize() + " of bottles!!");
                        System.out.print("Do you still want that much : ");
                        String ch = input.next();
                        if ("Y".equals(ch) || "y".equals(ch)) {
                            Node r = t.GetBG_Node_Link();
                            int cnt = t.count_RCsize();
                            while (cnt != 0) {
                                if (r.getRC_No() == 1) {
                                    r.setRC_No(0);
                                    donors[arr_pointer] = r.Name;
                                    arr_pointer++;
                                    r.GetLink_BG();
                                    cnt--;
                                } else {
                                    r = r.GetLink_BG();
                                }
                            }
                            System.out.println("Requested no. of bottles of type " + r_bg + " has been dispatched.!!");
                            t.set_RCcount(t.count_RCsize() - r_need);
                        } else {
                            System.out.println("Sorry :( we weren't helpful!\n Please try again after some days !!");
                        }
                    }
                    break;
                }
                case 2: {
                    if (t.count_Platsize() >= r_need) {
                        Node r = t.GetBG_Node_Link();
                        int cnt = r_need;
                        while (cnt != 0) {
                            if (r.getPlat_No() == 1) {
                                r.setPlat_No(0);
                                donors[arr_pointer] = r.Name;
                                r.GetLink_BG();
                                arr_pointer++;
                                cnt--;
                            } else {
                                r = r.GetLink_BG();
                            }
                        }
                        t.set_Platcount(t.count_Platsize() - r_need);
                        System.out.println("Requested no. of bottles of type " + r_bg + " has been dispatched.!!");
                    } else {
                        System.out.println("We only have " + t.count_Platsize() + " of bottles!!");
                        System.out.print("Do you still want that much : ");
                        String ch = input.next();
                        if ("Y".equals(ch) || "y".equals(ch)) {
                            Node r = t.GetBG_Node_Link();
                            int cnt = t.count_Platsize();
                            while (cnt != 0) {
                                if (r.getPlat_No() == 1) {
                                    r.setPlat_No(0);
                                    donors[arr_pointer] = r.Name;
                                    arr_pointer++;
                                    r.GetLink_BG();
                                    cnt--;
                                } else {
                                    r = r.GetLink_BG();
                                }
                            }
                            System.out.println("Requested no. of bottles of type " + r_bg + " has been dispatched.!!");
                            t.set_Platcount(t.count_Platsize() - r_need);
                        } else {
                            System.out.println("Sorry :( we weren't helpful!\n Please try again after some days !!");
                        }
                    }

                    break;
                }
                case 3: {
                    if (t.count_Plassize() >= r_need) {
                        Node r = t.GetBG_Node_Link();
                        int cnt = r_need;
                        while (cnt != 0) {
                            if (r.getPlas_No() == 1) {
                                r.setPlas_No(0);
                                donors[arr_pointer] = r.Name;
                                r.GetLink_BG();
                                arr_pointer++;
                                cnt--;
                            } else {
                                r = r.GetLink_BG();
                            }
                        }
                        t.set_Plascount(t.count_Plassize() - r_need);
                        System.out.println("Requested no. of bottles of type " + r_bg + " has been dispatched.!!");
                    } else {
                        System.out.println("We only have " + t.count_Plassize() + " of bottles!!");
                        System.out.print("Do you still want that much : ");
                        String ch = input.next();
                        if ("Y".equals(ch) || "y".equals(ch)) {
                            Node r = t.GetBG_Node_Link();
                            int cnt = t.count_Plassize();
                            while (cnt != 0) {
                                if (r.getPlas_No() == 1) {
                                    r.setPlas_No(0);
                                    donors[arr_pointer] = r.Name;
                                    arr_pointer++;
                                    r.GetLink_BG();
                                    cnt--;
                                } else {
                                    r = r.GetLink_BG();
                                }
                            }
                            System.out.println("Requested no. of bottles of type " + r_bg + " has been dispatched.!!");
                            t.set_Plascount(t.count_Plassize() - r_need);
                        } else {
                            System.out.println("Sorry :( we weren't helpful!\n Please try again after some days !!");
                        }
                    }

                    break;
                }
                default: {
                    System.out.println("Made a mistake in Input!! Try Again :)");
                }
            }
            filemaker();
        } else {
            System.out.println("In-valid Input!!");
        }

    }

    public void setData(String s1, String s2) {
        r_name = s1;
        r_bd = s2;
    }

    public String getfilename() {
        return r_name;
    }

    /*
     * Function for making a file which contains notifications regarding changes in the record
     */
    public void filemaker() {
        try {
            File file = new File("notification.txt");
            try (PrintWriter output = new PrintWriter(new FileWriter(file, false))) {
                output.write("Notification :-(Changes in Record)");
                output.println();
                for (int i = 0; i < donors.length; i++) {
                    output.write(donors[i]);
                    output.println();
                }
                output.println();
            }
        } catch (IOException i) {
        }
    }
    
    /*
     * Creates file which will have the receipt of the receiver who will receive the blood from the bloodbank  
     */
    public void fileinfo() {
        File file = new File(this.r_name + ".txt");
        Date dt = new Date();
        System.out.print(dt.toString());
        try {
            try (PrintWriter output = new PrintWriter(new FileWriter(file, false))) {
                output.write("\t\tBlood Receiver Receipt\n");
                output.println();
                output.write("Quantity of Blood bottles taken by Receiver            : " + this.r_need);
                output.println();
                output.write("Receiver INFO :-");
                output.println();
                output.write("Patient Name            : " + this.r_name);
                output.println();
                output.write("BirthDate            : " + this.r_bd);
                output.println();
                output.write("Blood-Group of Patient     : " + this.r_bg);
                output.println();
                output.write("Blood received date  : " + dt.toString());
                output.println();

            }
        } catch (Exception e) {
        }
    }
}
