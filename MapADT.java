// --== CS400 File Header Information ==--
// Name: Zachary Austin
// Email: zaustin@wisc.edu
// Team: EB
// TA: Keren
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import java.util.NoSuchElementException;


public interface MapADT<KeyType, ValueType> {

	public boolean put(KeyType key, ValueType value);
	public ValueType get(KeyType key) throws NoSuchElementException;
	public int size();
	public boolean containsKey(KeyType key);
	public ValueType remove(KeyType key);
	public void clear();
	
}