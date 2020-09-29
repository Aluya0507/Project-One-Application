import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class implements method that reads user command to edit the working HashTableMap.
 * @author Yuliang Peng
 */
public class MapInteract {
  
  /**
   * Command method for users consisting: Load default data defined in defaultMap method,
   *                                      View current HashTableMap, Insert new country-info pairs,
   *                                      Remove country-info pairs, Get specific country's information,
   *                                      and exit the APP.
   * Loading default data sets may sometimes cause rehash of the working HashTable depending on its size.
   * The APP will only accept country keys with capitalized initials, however, it will recognize country names 
   * entered in lower-case or any case in general. It will not recognize country names with special characters.
   * @param map - the HashTableMap user is or will be working on.
   */
  public static void command(HashTableMap map) {
    System.out.println("Welcome to the World Geological Map.");
    Scanner sc = new Scanner(System.in);
    System.out.println("What you want to do next?\n1. (L)oad default map \n2. (V)iew the current map\n3. (I)nsert new country"
        + "\n4. (R)emove a country\n5. (G)et the information of a country\n6. (E)xit");
    String input = sc.nextLine().substring(0, 1).toUpperCase(); // allowing for both upper- and lower-case.
    while (true) {
      if (input.equals("V")) { // view map
        System.out.println(map.toString());
        System.out.println("What you want to do next?\n1. (L)oad default map \n2. (V)iew the current map\n3. (I)nsert new country"
            + "\n4. (R)emove a country\n5. (G)et the information of a country\n6. (E)xit");
        input = sc.nextLine().substring(0, 1).toUpperCase();
      } else if (input.equals("L")) { //load default
        MapData data=new MapData();
        for (int i=0; i<data.size;i++) {
          map.put(data.getCountry(i), data.getInfo(i));
        }
        System.out.println(map.toString());
        System.out.println("Default map loaded successfully.");
        System.out.println("What you want to do next?\n1. (L)oad default map \n2. (V)iew the current map\n3. (I)nsert new country"
            + "\n4. (R)emove a country\n5. (G)et the information of a country\n6. (E)xit");
        input = sc.nextLine().substring(0, 1).toUpperCase();
      } else if (input.equals("I")) { // insert new country-info pair
        System.out.println("What is the name for the country?");
        String country = sc.nextLine();
        String supposed="";
        String[] split=country.toLowerCase().split(" ");
        for (int i=0; i<split.length;i++) { // making input's initials capitalized to avoid duplication
          split[i] = split[i].substring(0, 1).toUpperCase() + split[i].substring(1);
          supposed+=split[i];
          if (i!=split.length-1) {
            supposed+=" ";
          }
        }
        if (!country.equals(supposed)) { // have users insert the initial-capitalized country name
          System.out.println("Insert failed. Please make sure the initial(s) is capitalized.");
        } else if (map.containsKey(country)) { // duplicated
          System.out.println("Sorry, the country's already stored in the map, you can choose to view the country by typing \"G\".");
          input = sc.nextLine().substring(0, 1).toUpperCase();
        } else { // insert info
          System.out.println("Any description about the country?");
          String info = sc.nextLine();
          map.put(country, info);
          System.out.println("You have successfully added the country to map.");
          System.out.println("What you want to do next?\n1. (L)oad default map \n2. (V)iew the current map\n3. (I)nsert new country"
              + "\n4. (R)emove a country\n5. (G)et the information of a country\n6. (E)xit");
          input = sc.nextLine().substring(0, 1).toUpperCase();
        }
      } else if (input.equals("R")) { // Remove country-info pair
        System.out.println("Which country you want to remove?");
        String country = sc.nextLine();
        String supposed="";
        String[] split=country.toLowerCase().split(" ");
        for (int i=0; i<split.length;i++) { // making input's initials capitalized so the APP's more flexible
          split[i] = split[i].substring(0, 1).toUpperCase() + split[i].substring(1);
          supposed+=split[i];
          if (i!=split.length-1) {
            supposed+=" ";
          }
        }
        country=supposed;
        String removeInfo=(String) map.remove(country);
        if (removeInfo==null) { // no country key as specified
          System.out.println("Sorry, there's no such country in the map, please make sure the initial(s) is capitalized."
              + " \nMaybe you want to try \"I(nsert)\".");
        } else { // remove country-info pair
          System.out.println("You have removed "+country+" successfully. \n"+removeInfo);
          System.out.println("What you want to do next?\n1. (L)oad default map \n2. (V)iew the current map\n3. (I)nsert new country"
              + "\n4. (R)emove a country\n5. (G)et the information of a country\n6. (E)xit");
          input = sc.nextLine().substring(0, 1).toUpperCase();
        }
      } else if (input.equals("G")) { // get specified country-info pair
        System.out.println("Which country's information do you want to get?");
        String country = sc.nextLine();
        String supposed="";
        String[] split=country.toLowerCase().split(" ");
        for (int i=0; i<split.length;i++) { // making input's initials capitalized so the APP's more flexible
          split[i] = split[i].substring(0, 1).toUpperCase() + split[i].substring(1);
          supposed+=split[i];
          if (i!=split.length-1) {
            supposed+=" ";
          }
        }
        country=supposed;
        try {
          String info=(String) map.get(country); // get info according to country key
          System.out.println(country+": \n"+info);
          System.out.println("What you want to do next?\n1. (L)oad default map \n2. (V)iew the current map\n3. (I)nsert new country"
              + "\n4. (R)emove a country\n5. (G)et the information of a country\n6. (E)xit");
          input = sc.nextLine().substring(0, 1).toUpperCase();
        } catch (NoSuchElementException e) { // no specified country key
          System.out.println(e.getMessage()+" Please make sure the initial(s) is capitalized."
              +" \nMaybe you want to try \"I(nsert)\".");
        }
      } else if (input.equals("E")) { // exit 
        System.out.println("Thanks for using the World Geological Map. Bye-bye!");
        break;
      } else { // command is not entered right
        System.out.println("Sorry there is no such option.");
        System.out.println("What you want to do next?\n1. (L)oad default map \n2. (V)iew the current map\n3. (I)nsert new country"
            + "\n4. (R)emove a country\n5. (G)et the information of a country\n6. (E)xit");
        input = sc.nextLine().substring(0, 1).toUpperCase();
      }
    }
    sc.close();
  }

  public static void main(String[] args) {
    HashTableMap map=new HashTableMap(20); // creating new map for user to edit.
    command(map); // editing user map

  }

}
