// --== CS400 File Header Information ==--
// Name: <Haoxuan Lu>
// Email: <hlu224@wisc.edu>
// Team: <EB>
// TA: <Keren Chen>
// Lecturer: <Florian Heimerl>
// Notes to Grader: <optional extra notes>

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * This is the HashTableMap class implements the MapADT
 * 
 * @author Haoxuan Lu
 *
 * @param <KeyType>   genetic type for the key of key-value pairs
 * @param <ValueType> genetic type for the value for the key-value pairs
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  /**
   * A private class used for creating HashMapNode
   *
   * @param <KeyType>   generic type for key
   * @param <ValueType> generic type for value
   */
  private class HashMapNode<KeyType, ValueType> {
    KeyType key;
    ValueType value;
    // Reference to next node
    HashMapNode<KeyType, ValueType> next = null;

    /**
     * The constructor for the HashMapNode
     * 
     * @param key   an object represent the key of the key-value pairs
     * @param value an object represent the value of the key-value pairs
     */
    public HashMapNode(KeyType key, ValueType value, HashMapNode next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }

  private int capacity; // represent the capacity of the created HashTableMap
  private int size; // represent the number of object put into the map
  private double maxSize;// represent the max number of object can be store in the collection
  private HashMapNode[] MapArray; // a private array instance field to store
  // key-value pairs.


  /**
   * This is the constructor of the HashTableMap class with one parameter capacity represent the
   * capacity of the created HashTableMap
   * 
   * @param capacity an int represent the capacity of the created HashTableMap
   */
  public HashTableMap(int capacity) {
    this.capacity = capacity;
    this.size = 0;
    this.maxSize = (double) (this.capacity) * 0.8;
    this.MapArray = new HashMapNode[this.capacity];
    for (int i = 0; i < this.capacity; i++)
      this.MapArray[i] = null;
  }

  /**
   * This is the default constructor of the HashTableMap class creating an object with capacity 10
   */
  public HashTableMap() {
    // with default capacity = 10
    this(10);
  }

  /**
   * This is a private helper methods to double and rehash the previous MapArray storing the
   * key-value pairs
   */
  private void resize() {
    HashMapNode[] oldArray = this.MapArray;
    this.capacity = this.capacity * 2;
    this.MapArray = new HashMapNode[this.capacity];
    this.maxSize = this.capacity * 0.8;
    this.size = 0;
    for (int i = 0; i < oldArray.length; i++) {
      if (oldArray[i] != null) {
        HashMapNode<KeyType, ValueType> temp = oldArray[i];
        while (temp != null) {
          put(temp.key, temp.value);
          temp = temp.next;
        }
      }
    }
  }

  /**
   * This is the put method which store the key-value pairs to the private array of HashTableMap.
   * When the put method is passed a key that is already in your hash table, the put method should
   * return false without making any changes to the hash table. The put method should only return
   * true after successfully storing a new key-value pair
   * 
   * @param key   an object represent the key of the key-value pairs
   * @param value an object represent the value of the key-value pairs
   * @return a boolean represents whether this key-value pair successful add to the array. Return
   *         true if success, false otherwise
   */
  @Override
  public boolean put(KeyType key, ValueType value) {

    if (key == null)
      return false;
    if (containsKey(key))
      return false;

    int hash = Math.abs(key.hashCode()) % this.capacity;
    HashMapNode<KeyType, ValueType> temp = this.MapArray[hash];
    if (temp == null) {
      this.MapArray[hash] = new HashMapNode<KeyType, ValueType>(key, value, null);
      size++;
      // System.out.println(Arrays.toString(this.MapArray));
    } else {
      this.MapArray[hash] = new HashMapNode<KeyType, ValueType>(key, value, temp);
      size++;
    }

    // resize if the load factor is greater than 0.8
    if (size >= maxSize)
      this.resize();

    return true;
  }

  /**
   * This is the get method which get the certain key-value pairs from the collection or return
   * NoSuchElementException if there is no such element in the collection.
   * 
   * @param key an object represent the key of the key-value pairs
   * @throws NoSuchElementException when this specific key does not find in the collection
   * @return an object represent the value for this key-value pair.
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {

    if (!containsKey(key))
      throw new NoSuchElementException("This key is not found in the colloction");

    int hash = Math.abs(key.hashCode()) % this.capacity;
    HashMapNode<KeyType, ValueType> temp = this.MapArray[hash];

    while (temp != null) {
      if (temp.key.equals(key))
        return temp.value;
      temp = temp.next;
    }

    return null;
  }

  /**
   * This is a size method that returns the number of key-value pairs stored in this collection.
   * 
   * @return an int represent the size of this collections
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * This is the containKey method used to check whether this cell for the collection contain
   * specific key
   * 
   * @param key an object represent the key of the key-value pairs
   * @return a boolean represent whether this collection contain this key. Return true if contain,
   *         false otherwise.
   */
  @Override
  public boolean containsKey(KeyType key) {
    int hash = Math.abs(key.hashCode()) % this.capacity;
    HashMapNode<KeyType, ValueType> temp = this.MapArray[hash];
    if (temp == null)
      return false;

    while (temp != null) {
      if (temp.key.equals(key))
        return true;
      temp = temp.next;
    }
    return false;
  }

  /**
   * This is a remove method that returns a reference to the value associated with the key that is
   * being removed. When the key being removed does not exist, this method should instead return
   * null.
   * 
   * @param key an object represent the key
   * @return an object represent the value for this key-value pair which has been removed
   */
  @Override
  public ValueType remove(KeyType key) {
    if (!containsKey(key))
      return null;

    int hash = Math.abs(key.hashCode()) % this.capacity;
    HashMapNode<KeyType, ValueType> temp = this.MapArray[hash];

    // if the first pair is the result
    if (temp.key.equals(key)) {
      ValueType result = temp.value;
      this.MapArray[hash] = null;
      this.size--;
      return result;
    }

    // if the result is not first pair
    while (temp.next != null) {
      if (temp.next.key.equals(key))
        break;
      temp = temp.next;
    }
    ValueType result = temp.next.value;
    temp.next = null;
    this.size--;
    return result;
  }

  /**
   * This is the clear method for HashTableMap object used to remove all the pairings in the
   * collection
   */
  @Override
  public void clear() {
    this.MapArray = new HashMapNode[this.capacity];
    this.size = 0;
  }

  /**
   * This is a getter method for the private parameter capacity
   * 
   * @return an int represent the capacity of the collection.
   */
  public int getCapacity() {
    return this.capacity;
  }

  /**
   * This method help to print the data inside the HashTableMap
   */
  public void print() {
    for (int i = 0; i < MapArray.length; i++) {
      HashMapNode<KeyType, ValueType> temp = this.MapArray[i];
      while (temp != null) {
        System.out.println(temp.key + "\n" + temp.value);
        temp = temp.next;
      }
    }
  }
}
