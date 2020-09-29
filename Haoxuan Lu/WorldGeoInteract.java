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
    StateTable<String, String> table = new StateTable<String, String>();
    System.out.println("Welcome to the World Geological Map.");
    Scanner sc = new Scanner(System.in);
    System.out.println("What you want to do?\n1. (L)oad the default map\n2. (V)iew the current "
        + "map\n3. (I)nsert new country\n4. (R)emove a country\n5. (G)et the information of a "
        + "country\n6. (E)xit");
    String input = sc.nextLine().substring(0, 1).toUpperCase();
    while (true) {
      if (input.equals("L")) {
        loadMap(table);
        input = sc.nextLine().substring(0, 1).toUpperCase();
      }

      else if (input.equals("V")) {
        printMap(table);
        input = sc.nextLine().substring(0, 1).toUpperCase();
      }

      else if (input.equals("I")) {
        System.out.println("What is the name for the country");
        String name = sc.nextLine().strip().toLowerCase();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        System.out.println("Any description to the country?");
        String desc = sc.nextLine().strip().toLowerCase();
        insertCountry(table, name, desc);
        input = sc.nextLine().substring(0, 1).toUpperCase();
      }

      else if (input.equals("R")) {
        System.out.println("Which country you want to remove?");
        String country = sc.nextLine().strip().toLowerCase();
        country = country.substring(0, 1).toUpperCase() + country.substring(1);
        Remove(table, country);
        input = sc.nextLine().substring(0, 1).toUpperCase();
      }

      else if (input.equals("G")) {
        System.out.println("Which country do you want to get information?");
        String country = sc.nextLine();
        country = country.substring(0, 1).toUpperCase() + country.substring(1);
        getInfo(table, country);
        input = sc.nextLine().substring(0, 1).toUpperCase();
      }

      else if (input.equals("E")) {
        System.out.println("Thanks for using the World Geological Map. Bye-bye!");
        break;
      }

      else {
        System.out.println("Sorry there is no such option.");
        input = sc.nextLine().substring(0, 1).toUpperCase();
      }
    }
  }

  /**
   * This is the method used to insert country from users
   * 
   * @param table The World Geological Map
   * @param name  The name of the country users want to insert
   * @param desc  The description for this country by users
   */
  private static void insertCountry(StateTable<String, String> table, String name, String desc) {
    try {
      table.insertInfo(name, desc);
      System.out
          .println("The information for the country " + name + " has been successfully insert");
    } catch (Exception e) {
      System.out.println(
          "Sorry, the country " + name + " is already inside the The World Geological Map");
    } finally {
      System.out.println("What you want to do next?\n1. (L)oad the default map\n2. (V)iew the curre"
          + "nt map\n3. (I)nsert new country\n4. (R)emove a country\n5. (G)et the information of a "
          + "country\n6. (E)xit");
    }
  }

  /**
   * This is the private method used to get the information of certain country from The World
   * Geological Map
   * 
   * @param table   The World Geological Map
   * @param country The country users want to get the information about
   */
  private static void getInfo(StateTable<String, String> table, String country) {
    try {
      System.out.println(table.getInfo(country));
    } catch (Exception e) {
      System.out
          .println("Sorry, the country " + country + " is not inside the The World Geological Map");
    } finally {
      System.out.println("What you want to do next?\n1. (L)oad the default map\n2. (V)iew the curre"
          + "nt map\n3. (I)nsert new country\n4. (R)emove a country\n5. (G)et the information of a "
          + "country\n6. (E)xit");
    }
  }

  /**
   * This is the private method used to load the default World Geological Map with 73 countries
   * 
   * @param table The World Geological Map
   */
  private static void loadMap(StateTable<String, String> table) {
    MapData data = new MapData();
    for (int i = 0; i < data.size; i++) {
      table.insertInfo(data.getCountry(i), data.getInfo(i));
    }
    System.out.println("Default map loaded successfully.");
    System.out.println("What you want to do next?\n1. (L)oad the default map\n2. (V)iew the current"
        + " map\n3. (I)nsert new country\n4. (R)emove a country\n5. (G)et the information of a "
        + "country\n6. (E)xit");
  }

  /**
   * This is a private remove method used to remove country from the World Geological Map
   * 
   * @param table   The World Geological Map
   * @param country The country users want to remove
   */
  private static void Remove(StateTable<String, String> table, String country) {
    try {
      table.remove(country);
      System.out.println("The country " + country
          + " has been successfully removed from The World Geological Map");
    } catch (Exception e) {
      System.out
          .println("Sorry, the country " + country + " is not inside the The World Geological Map");
    } finally {
      System.out.println("What you want to do next?\n1. (L)oad the default map\n2. (V)iew the curre"
          + "nt map\n3. (I)nsert new country\n4. (R)emove a country\n5. (G)et the information of a "
          + "country\n6. (E)xit");
    }
  }

  /**
   * This is a private method used to print all the city from the World Geological Map
   * 
   * @param table
   */
  private static void printMap(StateTable<String, String> table) {
    System.out.println("Here are the current World Geological Map");
    table.printTable();
    System.out.println("What you want to do next?\n1. (L)oad the default map\n2. (V)iew the current"
        + " map\n3. (I)nsert new country\n4. (R)emove a country\n5. (G)et the information of a "
        + "country\n6. (E)xit");
  }
}
