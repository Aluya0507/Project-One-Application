// --== CS400 File Header Information ==--
// Name: Yuliang Peng
// Email: peng68@wisc.edu
// Team: eb
// Role: Data Wrangler
// TA: Keren Chen
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
// Outside Source: https://www.usnews.com/news/best-countries/best-international-influence
//                 https://stackabuse.com/reading-and-writing-csvs-in-java/
//                 https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class sets up default data that are supposed to be stored in the HashTable APP.
 * Data gathered from https://www.usnews.com/news/best-countries/best-international-influence, which
 * includes the top 73 most influential countries in the world. There's also an "Other info" empty 
 * section included in the data, which can be edited by users later.
 * The key for HashTable is designed to be the String name of each country, and value includes the 
 * country's their geographic region, area, capital, GDP, population, and GDP per capita as a single 
 * String.
 * @author Yuliang Peng
 */
public class MapData {
  
  private String[] countryData=new String[73]; // Array storing country names
  private String[] countryInfo=new String[73]; // Array storing other country infomation
  
  /**
   * Get country name by index.
   * @param index of the country name
   * @return country name according to index
   */
  public String getCountry(int i) {
    return countryData[i];
  }
  
  /**
   * Get country info by index.
   * @param index of the country info
   * @return country info according to index
   */
  public String getInfo(int i) {
    return countryInfo[i];
  }
  
  /**
   * Implementing default HashTableAPP.
   * @param args input arguments if any
   */
  public MapData()  {
    try {
      BufferedReader countries=new BufferedReader(new FileReader("countries.csv")); // importing csv data
      String[] title=countries.readLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // header of the imported csv form,
                                                                                          // separated by comma, ignore commas
                                                                                          // within quotes.
      for (int i=0;i<title.length;i++) {
        title[i]=title[i].replaceAll("\"", ""); // removing quotes
      }
      String temp=countries.readLine();
      int index=0;
      while (temp != null) {
        String[] country=temp.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        for (int i=0;i<country.length;i++) {
          country[i]=country[i].replaceAll("\"", "");
        }
        String info=title[1]+": "+country[1]+"\n"+
                    title[2]+": "+country[2]+"\n"+
                    title[3]+": "+country[3]+"\n"+
                    title[4]+": "+country[4]+"\n"+
                    title[5]+": "+country[5]+"\n"+
                    title[6]+": "+country[6]+"\n"+
                    title[7]+": "+country[7]+"\n"; // storing HashTable value as a single String
        temp=countries.readLine();
        countryData[index]=country[0]; // store to local Array
        countryInfo[index]=info;
        index+=1;
      }    
      // Check: System.out.println(data.toString());
      countries.close();
    } catch (FileNotFoundException e) { // catch Exceptions if any.
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
  
  /**
   * Visualize to see the MapData Class works as expected
   * @param args - args input arguments if any.
   */
  public static void main(String args[]) {
    MapData data=new MapData();
    System.out.println(data.getInfo(2));
    
  }
  
}
