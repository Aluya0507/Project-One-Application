// --== CS400 File Header Information ==--
// Name: <Haoxuan Lu>
// Email: <hlu224@wisc.edu email address>
// Team: <EB>
// Role: <Front End Developer>
// TA: <Keren Chen>
// Lecturer: <Florian Heimerl>
// Notes to Grader: <optional extra notes>

import java.util.Scanner;

/**
 * This is the WorldGeoInteract class used to achieve the human-computer interaction
 * 
 * @author Haoxuan Lu
 *
 */
public class WorldGeoInteract {
  public static void main(String args[]) {
    System.out.println("Welcome to the World Geological Map.");
    Scanner sc = new Scanner(System.in);
    System.out.println("What you want to do?\n1. (V)iew the current map\n2. (I)nsert new country"
        + "\n3. (R)emove a country\n4. (G)et the information of a country\n5. (E)xit");
    String input = sc.nextLine().substring(0, 1).toUpperCase();
    while (true) {
      if (input.equals("V")) {
        printMap();
      } else if (input.equals("I")) {
        System.out.println("What is the name for the country");
        String name = sc.nextLine().strip().toLowerCase();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        System.out.println("Any description to the country?");
        String desc = sc.nextLine().strip().toLowerCase();
      } else if (input.equals("R")) {
        System.out.println("Which country you want to remove?");
        String country = sc.nextLine().strip().toLowerCase();
        country = country.substring(0, 1).toUpperCase() + country.substring(1);
        Remove(country);
      } else if (input.equals("G")) {
        System.out.println("What the information you want to get?");
        String inf = sc.nextLine().substring(0, 1).toUpperCase();

      } else if (input.equals("E")) {
        System.out.println("Thanks for using the World Geological Map. Bye-bye!");
        break;
      } else {
        System.out.println("Sorry there is no such option.");
        input = sc.nextLine().substring(0, 1).toUpperCase();
      }
    }
  }

  /**
   * This is a private remove method used to remove country from the World Geological Map
   * 
   * @param country
   */
  private static void Remove(String country) {
    // TODO Auto-generated method stub

  }

  /**
   * This is a private method used to print all the city from the World Geological Map
   */
  private static void printMap() {
    // TODO Auto-generated method stub

  }
}
