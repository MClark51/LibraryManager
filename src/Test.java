/***
 * Test class for a Catalog type
 * @author Marco Clark
 * Date Created: 2/17/2022
 * Date last modified: 2/27/2022
 */
import java.util.Scanner;

public class Test {
    
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Catalog l = new Catalog();
        l.readFromFile("titles.txt");
        
        int cont = 0;
        while (cont !=9){
            printMenu();
            try {
                cont = Integer.parseInt(kb.nextLine());
                switch (cont){
                    case 1:
                        l.printArray();
                        break;
                    case 2:
                        try{
                            System.out.println("Enter a call Number: ");
                            String cN = kb.nextLine();
                            if (!(cN.matches("[B|P]-\\d{3}-\\d{3}-\\d{3}"))){
                                throw new UserInputException("Invalid Call Number. Must be B-ddd-ddd-ddd or P-ddd-ddd-ddd");
                            }
                            l.searchCallNum(cN);
                        }
                        catch (UserInputException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Enter a title: ");
                        String t = kb.nextLine();
                        l.searchItem(t, "title");
                        break;
                    case 4:
                        try{
                            System.out.println("Enter a year: ");
                            String y = kb.nextLine();
                            if (checkYear(Integer.parseInt(y))){
                                l.searchItem(y, "year");
                            }
                        }
                        catch (UserInputException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.println("Enter a title:");
                        String title = kb.nextLine();
                        System.out.println("Enter a publisher:");
                        String pub = kb.nextLine();
                        try{
                            System.out.println("Enter the year of publication:");
                            int pubY = Integer.parseInt(kb.nextLine());
                            checkYear(pubY);
                            System.out.println("Enter the number of copies:");
                            int numCopy = Integer.parseInt(kb.nextLine());
                            System.out.println("Enter the type of title (book/periodical):");
                            String tType = kb.nextLine();
                            if (tType.equals("book") || tType.equals("periodical")){
                                System.out.println("Enter the call number (" + Character.toUpperCase(tType.charAt(0)) + "-ddd-ddd-ddd):");
                                String cNumber = kb.nextLine();
                                if (tType.equals("book")){
                                    if (! (Character.toUpperCase(tType.charAt(0)) == cNumber.charAt(0))){
                                        throw new UserInputException("Invalid Call Number for type book. Must be B-ddd-ddd-ddd");
                                    }
                                    checkCallNum(cNumber);
                                    System.out.println("Enter the author:");
                                    String auth = kb.nextLine();
                                    System.out.println("Enter ISBN (10 digits):");
                                    Long isbn = Long.parseLong(kb.nextLine());
                                    checkISBN(isbn);
                                    l.addBook(cNumber, title, pub, pubY, numCopy, auth, isbn);
                                    
                                }
                                else {
                                    if (! (Character.toUpperCase(tType.charAt(0)) == cNumber.charAt(0))){
                                        throw new UserInputException("Invalid Call Number for type periodical. Must be P-ddd-ddd-ddd");
                                    }
                                    checkCallNum(cNumber);
                                    System.out.println("Enter the month:");
                                    int month = Integer.parseInt(kb.nextLine());
                                    checkMonth(month);
                                    System.out.println("Enter the issue number:");
                                    int iN = Integer.parseInt(kb.nextLine());
                                    l.addPeriodical(cNumber, title, pub, pubY, numCopy, month, iN);
                                    
                                }
                                System.out.println("Title added Successfully");
                            }else {
                                throw new UserInputException("Invalid type of title. Must be a book or periodical.");
                            }  
                        }
                        catch (UserInputException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.println("Enter the call number (B-ddd-ddd-ddd or P-ddd-ddd-ddd):");
                        try{
                            String cN = kb.nextLine();
                            checkCallNum(cN);
                            if (l.removeTitle(cN)){
                                System.out.println("Title removed successfully.");
                            }
                            else{
                                System.out.println("Title not found/");
                            }
                        }
                        catch (UserInputException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 7:
                        l.sortByYear();
                        l.printArray();
                        break;
                    case 8:
                        l.searchItem("", "restoreable");
                        break;
                    case 9:
                        l.writeToFile("titles.txt");
                        break;
                }
            }
            //This will catch when the user puts Strings in for menu options as well as other fields that requrie integers
            catch (NumberFormatException e){
                System.out.println("You must input an integer.");
            }   
        }
        kb.close();
    }
    
    /***
     * Method to print the menu options
     */
    public static void printMenu(){
        System.out.println("\nSelect an operation\n1: View all titles\n2: Search by call number\n3: Search by title\n"
         + "4: Search by year\n5: Add new title\n6: Remove title\n7: Sort titles by year\n8: View Titles due for restoration\n9: Exit");
    }
    
    /***
     * Method that checks if a call number is valid
     * @param cN call number being checked
     * @return whether or not the call number is valid
     * @throws UserInputException thrown if the call number does not match the appropriate format
     */
    public static boolean checkCallNum(String cN) throws UserInputException{
        if (cN.matches("[B|P]-\\d{3}-\\d{3}-\\d{3}")){
            return true;
        }
        throw new UserInputException("Invalid Call Number. Must be B-ddd-ddd-ddd or P-ddd-ddd-ddd");
    }

    /***
     * Method to check if a year is valid
     * @param y year being checked
     * @return whether or not a year is valid
     * @throws UserInputException thrown if the year is invalid
     * The year must be between 1900 and 2022 to be considered valid
     */
    public static boolean checkYear(int y) throws UserInputException{
        if (y >= 1900 && y<= 2022){
            return true;
        }
        throw new UserInputException("Invalid Year. Must be between 1900 and 2022.");
        
    }

    /***
     * Method to check if a month is valid
     * @param m month being checked
     * @return whether or not the month is valid
     * @throws UserInputException thrown if the month is not between 1 and 12 inclusive
     */
    public static boolean checkMonth(int m) throws UserInputException{
        if (m >= 1 && m <= 12){
            return true;
        }
        throw new UserInputException("Invalid Month. Must be between 1 and 12.");
    }

    /***
     * Method to check if an ISBN is valid
     * @param i ISBN being checked
     * @return whether or not the ISBN is valid
     * @throws UserInputException thrown if the ISBN is not exactly 10 digits
     */
    public static boolean checkISBN(Long i) throws UserInputException{
        if (String.valueOf(i).length() == 10){
            return true;
        }
        throw new UserInputException("ISBN must be 10 digits long.");
    }
}
