/***
 * Class to model a catalog for a library
 * @author Marco Clark
 * Date Created: 2/17/2022
 * Date last modified: 2/27/2022
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Catalog{
    Title[] titleList;
    int numItems = 0;

    /***
     * Default constructor that sets numItems to an empty array of size 100
     */
    public Catalog(){
        titleList = new Title[100];
    }

    /***
     * Method to search for a call number in the catalog and print the title if found
     * @param cN call number being searched for
     * If a title is not found, then that result is printed
     */
    public void searchCallNum(String cN){
        int c = 0;
        for (int i = 0; i < numItems; i++){
            if (titleList[i].getCallNum().equals(cN)){
                System.out.println("\nTitle found.");
                System.out.println(titleList[i]);
                c++;
            }
        }
        if (c == 0){
            System.out.println("Title not found.");
        }
    }

    /***
     * Method to search for an item in the catalog
     * @param target value being searched for
     * @param type type of value being searched for
     * This method can search for titles with the given title or year, passed in by the parameter target
     * This method can also search for titles that are restoreable
     * The method first finds the items that match the conditions being searched for, adds the indexes of matching items into an array, and then prints the array.
     * Although not efficient, it seemed the only way to get the desired output
     */
    public void searchItem(String target, String type){
        int[] l = new int[20];
        int lN = 0;
        for (int i = 0; i < numItems; i++){
            switch (type){
                case "title":
                    if (titleList[i].getTitle().equals(target)){
                        l[lN++] = i; 
                        //return i;   
                    }
                    break;
                case "year":
                    if (titleList[i].getPubYear() == Integer.parseInt(target)){
                        l[lN++] = i; 
                        //return i;   
                    }
                    break;
                case "restoreable":
                    if (titleList[i].isRestoreable()){
                        l[lN++] = i; 
                    }
                    break;
            }
            
        }
        if (lN !=0){
            System.out.println("Number of titles found: " + lN);
            System.out.printf("%s\t%-40s\t%-25s\t%s\n", "Call Number", "Title", "Publisher", "Year");
            for (int j = 0; j < lN; j++){
                printItem(l[j]);
            }
        }
        else{
            System.out.println("No titles found.");
        }
    }

    /***
     * Method to add a book to the catalog
     * @param cN - call number
     * @param t - title
     * @param p - publisher
     * @param pY - year of publication
     * @param nC - number of copies
     * @param a - author
     * @param is - isbn
     */
    public void addBook(String cN, String t, String p, int pY, int nC, String a, Long is){
        titleList[numItems] = new Book(cN, t, p, pY, nC, a, is);
        numItems++;
    }

    /***
     * Method to add a periodical to the catalog
     * @param cN - call number
     * @param t - title
     * @param p - publisher
     * @param pY - year of publication
     * @param nC - number of copies
     * @param m - month of publication
     * @param iN - issue number
     */
    public void addPeriodical(String cN, String t, String p, int pY, int nC, int m, int iN){
        titleList[numItems] = new Periodical(cN, t, p, pY, nC, m, iN);
        numItems++;
    }

    /***
     * Method to remove a title from the vatalog
     * @param t call number being removed from the catalog
     * @return boolean value if the title was found and successfully removed
     */
    public boolean removeTitle(String t){
        boolean shift = false;
        for (int i = 0; i < numItems; i++){
            if (t.equals(titleList[i].getCallNum())){
                shift = true;
                numItems --;
            }
            if(shift){
                titleList[i] = titleList[i+1];
            }
        }
        return shift;
    }

    /***
     * Method to sort the catalog by publication year
     * Uses Java.util.arrays.sort, which checks the compareable method in the Title class
     * so it properly sorts by year
     */
    public void sortByYear(){
        java.util.Arrays.sort(titleList, 0, numItems);
    }

    /***
     * Mehtod to read titles from the given file name
     * @param fN file being read from
     * Reads and creates titles based on type. Type is determined by the first character in the call number that is read from file
     */
    public void readFromFile(String fN){
        File f = new File(fN);
        try{
            Scanner scn = new Scanner(f);
            while (scn.hasNextLine()){
                String cN = scn.nextLine();
                String t = scn.nextLine();
                String p = scn.nextLine();
                int y = Integer.parseInt(scn.nextLine());
                int nC = Integer.parseInt(scn.nextLine());
                char tType = cN.charAt(0);

                switch (tType){
                    case 'B':
                        String a = scn.nextLine();
                        Long is = Long.parseLong(scn.nextLine());
                        titleList[numItems] = new Book(cN, t, p, y, nC, a, is);
                        numItems++;
                        break;
                    case 'P':
                        int m = Integer.parseInt(scn.nextLine());
                        int iss = Integer.parseInt(scn.nextLine());
                        titleList[numItems] = new Periodical(cN, t, p, y, nC, m, iss);
                        numItems++;
                        break;
                }
            }
            scn.close();
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    /***
     * Method to write to file
     * @param fN file being written to
     * Checks the first character of the call number to ensure that the correct properties are printed
     */
    public void writeToFile(String fN){
        File f = new File(fN);
        try{
            PrintWriter pW = new PrintWriter(f);
            for(int i = 0; i < numItems; i++){
                pW.println(titleList[i].getCallNum());
                pW.println(titleList[i].getTitle());
                pW.println(titleList[i].getPublisher());
                pW.println(titleList[i].getPubYear());
                pW.println(titleList[i].getNumCopies());

                char c = titleList[i].getCallNum().charAt(0);

                switch (c){
                    case 'B':
                        pW.println(((Book) titleList[i]).getAuthor());
                        pW.println(((Book) titleList[i]).getISBN());
                        break;
                    case 'P':
                        pW.println(((Periodical) titleList[i]).getMonth());
                        pW.println(((Periodical) titleList[i]).getIssue());
                }
            }
            pW.close();
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    /***
     * Method to print the entire catalog 
     * Prints the call number, title, publisher, and year for all titles
     */
    public void printArray(){
        System.out.printf("%s\t%-40s\t%-25s\t%s\n", "Call Number", "Title", "Publisher", "Year");
        for (int i = 0; i < numItems; i++){
            printItem(i);
        }
    }

    /***
     * Method to print a single items call number, title, publisher, and year
     * @param index item in catalog being printed
     * This method was developed to help reduce some redundancy/duplicate code in the program
     */
    public void printItem(int index){
        System.out.printf("%s\t%-40s\t%-25s\t%d\n", titleList[index].getCallNum(), titleList[index].getTitle(), titleList[index].getPublisher(), titleList[index].getPubYear());
    }
}