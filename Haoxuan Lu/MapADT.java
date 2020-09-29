// --== CS400 File Header Information ==--
// Name: <Haoxuan Lu>
// Email: <hlu224@wisc.edu>
// Team: <EB>
// TA: <Keren Chen>
// Lecturer: <Florian Heimerl>
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;

public interface MapADT<KeyType, ValueType> {

  public boolean put(KeyType key, ValueType value);
  public ValueType get(KeyType key) throws NoSuchElementException;
  public int size();
  public boolean containsKey(KeyType key);
  public ValueType remove(KeyType key);
  public void clear();
  
  
  
}