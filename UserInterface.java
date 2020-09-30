// --== CS400 File Header Information ==--
// Name: Kairas Mistry
// Email: kbmistry@wisc.edu
// Team: EB
// TA: Keren Chen
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;
import java.util.Scanner;

//This class is the front end portion of the project
public class UserInterface {
  
  private static void options(){
    System.out.println("Welcome to the Worldwide Directory.");
    System.out.println("What would you like to do?");
//    System.out.println("(V)iew current directory");
    System.out.println("(C)lear the current directory");
    System.out.println("(E)nter a new country");
    System.out.println("(A)dd info to an existing country");
    System.out.println("(G)et info about a country");
    System.out.println("(R)emove a country");
    System.out.println("(L)oad default directory");
    System.out.println("(Q)uit");
  }
  
  public static void main(String[] args) {
    StateTable<String, String> map = new StateTable<String, String>();
    Scanner sc = new Scanner(System.in);
    String input;
    String info;
    int index = 0;
    
    do {
      options();
      input = sc.nextLine().substring(0, 1).toLowerCase();
      switch(input) {
//        case "v": //TODO
//          for(int i = 0; i < map.stateTable.size(); i++) {
//            System.out.println(map.stateTable);
//          }
//          break;
        case "c":
          map.stateTable.clear();
          break;
          
        case "e":
          System.out.println("Which country would you like add?");
          input = sc.nextLine();
          map.keyToIndex(input);
          break;
          
        case "a":
            System.out.println("Which country would you like to add information to?");
            input = sc.nextLine();
            System.out.println("What information would you like to add?");
            info = sc.nextLine();
            map.insertInfo(input, info);
          
          break;
          
        case "g":
          try {
            System.out.println("Which existing country would you like to know about?");
            input = sc.nextLine();
            System.out.println(map.stateTable.get(input));
          }catch(NoSuchElementException e) {
              System.out.println(e.getMessage());
           }
          break;
          
        case "r":
          System.out.println("Which existing country would you like to remove?");
          input = sc.nextLine();
          map.stateTable.remove(input);
          break;
        case "l":
          MapData data=new MapData();
          for (int i=0; i<data.size;i++) {
            map.stateTable.put(data.getCountry(i), data.getInfo(i));
          }
          System.out.println("The default directory has been successfully loaded.");
          break;
          
        case "q":
          break;
          
        default:
          System.out.println("The command does not exist");
          break;
        
      }
    }while(!input.equals("q"));
    System.out.println("Thank you for using the Worldwide Directory!");
    
  }
}
