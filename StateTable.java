
/**
 * This class implements the stateTable map BackEnd and provides the necessary functionality
 * for FrontEnd
 * @author Zach Austin
 *
 */
public class StateTable {
	
	public int capacity;
	public HashTableMap stateTable = new HashTableMap(10);
	
	public StateTable(int capacity) {
		this.capacity = capacity;
		this.stateTable = new HashTableMap(capacity);
	}
	
	public StateTable() {
		this.capacity = 10;
		this.stateTable = new HashTableMap();
	}
	
	/* 
	 * keyToIndex takes a key as an argument and returns the corresponding index
	 * for that key in the Hash Table
	 * @param key - key to find index for
	 * @return - int of corresponding index
	 * */
	public int keyToIndex(String key) {
		return Math.abs(key.hashCode() % capacity);
	}
	
	/*
	 * insertInfo allows the functionality of adding info
	 * to a state in the Hash Table
	 * @param key - key of state being added to
	 * @param info - calue of information being added to state
	 * @return - true if added successfully
	*/
	public boolean insertInfo(String key, String info) {
		return stateTable.put(key, info);
	}
	
	/*
	 * remove allows the functionality of removing value info
	 * from a state in the Hash Table
	 * @param key - key of info being removed
	 * @return - String of value info removed
	 * */
	public String remove(String key) {
		return stateTable.remove(key).toString();
	}
	
	/*
	 * clears allows the functionality of clearing the stateTable to start with a new,
	 * empty stateTable
	 * 
	 * */
	public void clear() {
		stateTable.clear();
		return;
	}
}
