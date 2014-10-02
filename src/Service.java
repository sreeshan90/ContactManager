/*
 This class holds the methods that service the controller
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 *
 * @author sreesha.n
 */
public class Service {
    
    // Checks if the name already exits in file
    public static boolean checkNames(String fName,String initial, String lName) throws FileNotFoundException{
       
        File file = new File("C:\\Users\\sreesha.n\\Documents\\NetBeansProjects\\ContactManager\\src\\dataSample.txt");
            final Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                final String lineFromFile = scanner.nextLine();
                 if(lineFromFile.contains(fName+"!"+lName+"!"+initial)) {
                    return true;
                 }
          
            }
            return false;
    }
    
        // SAves a record to the file
    public static int persist(Person person) throws IOException{
        Writer output;
        
        try{
             output = new BufferedWriter(new FileWriter("C:\\Users\\sreesha.n\\Documents\\NetBeansProjects\\ContactManager\\src\\dataSample.txt",true));
        output.append(addRecord(person));
        output.append('\n');
        output.close();
        return 1;
        }
        catch(Exception e){
            System.out.println("Eror");
            return 0;
        }
       
        
    }
      // Prepares a String to be saved in a particular format
        public static String addRecord(Person p){
        
    
        String storeLine = p.getFirstName()+"!"+p.getLastName()+"!"+p.getMiddleInitial()+"!"+p.getAddr1()+"!"+p.getAddr2()+"!"+p.getCity()+
                            "!"+p.getState()+"!"+p.getZipCode()+"!"+p.getPhoneNumber()+"!"+p.getGender()+"!"+p.getId();
        
        return storeLine;
        
    }
        
        // fetches a record from the file for the given ID
       public static String fetchRecord(String id) throws FileNotFoundException{
           
           File file = new File("C:\\Users\\sreesha.n\\Documents\\NetBeansProjects\\ContactManager\\src\\dataSample.txt");
            final Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                final String lineFromFile = scanner.nextLine();
                 if(lineFromFile.contains(id)) {
                    return lineFromFile;
                 }
          
            }
            return "";
           
          
       }  
    
       //deletes a line in the file
    public static int deleteLine(String line) {

   File file = new File("C:\\Users\\sreesha.n\\Documents\\NetBeansProjects\\ContactManager\\src\\dataSample.txt");

    File file2 = new File(file.getParent() + "\\temp" + file.getName());
    PrintWriter pw = null;
    Scanner read = null;

    FileInputStream fis = null;
    FileOutputStream fos = null;
    FileChannel src = null;
    FileChannel dest = null;

    try {


        pw = new PrintWriter(file2);
        read = new Scanner(file);

        while (read.hasNextLine()) {

            String currline = read.nextLine();

            if (line.equalsIgnoreCase(currline)) {
                continue;
            } else {
                pw.println(currline);
            }
        }

        pw.flush();

        fis = new FileInputStream(file2);
        src = fis.getChannel();
        fos = new FileOutputStream(file);
        dest = fos.getChannel();

        dest.transferFrom(src, 0, src.size());
        return 0;


    } catch (IOException e) {
        e.printStackTrace();
        return 1;
    } finally {     
        pw.close();
        read.close();

        try {
            fis.close();
            fos.close();
            src.close();
            dest.close();
        } catch (IOException e) {
            e.printStackTrace();
                    }

        if (file2.delete()) {
            System.out.println("File is deleted");
        } else {
            System.out.println("Error occured! File: " + file2.getName() + " is not deleted!");
        }
    }

}
}
