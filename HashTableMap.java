// --== CS400 File Header Information ==--
// Name: Zachary Austin
// Email: zaustin@wisc.edu
// Team: EB
// TA: Keren
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import java.util.LinkedList;

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
	private int capacity;
	private LinkedList<Node>[] hashTable = new LinkedList[capacity];
	private LinkedList pairList = new LinkedList();
	private int size;
	private KeyType key;
	private ValueType value;
	
	public HashTableMap(int capacity) {
		this.capacity = capacity;
		this.hashTable = new LinkedList[capacity];
		for(int i = 0; i < capacity; i++) {
			hashTable[i] = pairList;
		}
		this.size = 0;
	}
	
	public HashTableMap() {
		this.capacity = 10; // with default capacity = 10
		this.hashTable = new LinkedList[capacity];
		for(int i = 0; i < capacity; i++) {
			hashTable[i] = pairList;
		}
		this.size = 0;
	}
	
	public boolean put(KeyType key, ValueType value) {
		for(int i = 0; i < hashTable.length; i++) {
			if(hashTable[i] != null) {
				for(int j = 0; j < hashTable[i].size(); j++) {
					if(key.equals(hashTable[i].get(j).key)) {
						return false;
					}
				}
			}
		}
		Node newNode = new Node(key, value);
		hashTable[Math.abs(key.hashCode() % capacity)].add(newNode);
		size++;
		if(((float)size / (float)capacity) >= 0.8) {
			doubleAndRehash();
		}
		return true;
	}
	
	private void doubleAndRehash() {
		LinkedList<Node>[] newHashTable = new LinkedList[2*capacity];
		for(int i = 0; i < (2*capacity); i++) {
			newHashTable[i] = pairList;
		}
		for(int i = 0; i < capacity; i++) {
			newHashTable[i] = hashTable[i];
		}
		hashTable = newHashTable;
		capacity *= 2;
	}
	
	public ValueType remove(KeyType key) {
		for(int i = 0; i < hashTable[Math.abs(key.hashCode() % capacity)].size(); i++) {
			if(key.equals(hashTable[Math.abs(key.hashCode() % capacity)].get(i).key)) {
				Node removeNode = new Node(null, null);
				removeNode = hashTable[Math.abs(key.hashCode() % capacity)].get(i);
				hashTable[Math.abs(key.hashCode() % capacity)].remove(i);
				return (ValueType) removeNode.value;
			}
		}
		return null;
	}
	
	public int size() {
		return size;
	}
	
	public void clear() {
		for(int i = 0; i < capacity; i++) {
			for(int j = 0; j < hashTable[i].size(); j++) {
				hashTable[i].remove(j);
			}
		}
		size = 0;
	}
	
	public ValueType get(KeyType key) throws java.util.NoSuchElementException {
		for(int i = 0; i < hashTable.length; i++) {
			if(hashTable[i] != null) {
				for(int j = 0; j < hashTable[i].size(); j++) {
					if(key.equals(hashTable[i].get(j).key)) {
						return (ValueType)hashTable[i].get(j).value;
					}
				}
			}
		}
		throw new java.util.NoSuchElementException("No key-value pair with that key " +
													"exists in the hash table.");
	}
	
	public boolean containsKey(KeyType key) {
		for(int i = 0; i < hashTable.length; i++) {
			for(int j = 0; j < hashTable[i].size(); j++) {
				if(key.equals(hashTable[i].get(j).key)) {
					return true;
				}
			}
		}
		return false;
	}
	
}
