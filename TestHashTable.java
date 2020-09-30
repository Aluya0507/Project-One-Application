// --== CS400 File Header Information ==--
// Name: Taylor Powers
// Email: tmpowers@wisc.edu
// Team: EB
// Role: Test Engineer 
// TA: Keren Chen
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.util.NoSuchElementException;

/**
 * This class implements unit test methods to check the correctness of the
 * HashTableMap class
 */
public class TestHashTable {
	/*
	 * Tests that the put method accurately adds key-value pairs to the hash
	 * table
	 * 
	 * @return True when the size of the HashTableMap is equal to the number of
	 * key-value pairs added to the hash table, and False when it is now
	 */
	public static boolean testPut() {
		HashTableMap<String, String> myMap = new HashTableMap<>(10);
		myMap.put("United States",
				"Geographic region: North America, Area: 9.83 million km^2, Capital: Washington D.C., "
						+ "GDP: $20.5 trillion, Population: 327.2 million, GDP per capita: $62,869");
		myMap.put("China",
				"Geographic region: Asia, Area: 9.6 million km^2, Capital: Beijing, GDP: $13.6 trillion, "
						+ "Population: 1.4 billion, GDP per capita: $18,116");
		myMap.put("United Kingdom",
				"Geographic region: Europe, Area: 243,610 km^2, Capital: London, GDP: $2.8 trillion, "
						+ "Population: 66.5 million, GDP per capita: $45,741");
		myMap.put("Russia",
				"Geographic region: Eurasia, Area: 17.1 million km^2, Capital: Moscow, GDP: $1.7 trillion, "
						+ "Population: 114.5 million, GDP per capita: $28,797");
		myMap.put("Germany",
				"Geographic region: Europe, Area: 357,022 km^2, Capital: Berlin, GDP: $4.0 trillion, "
						+ "Population: 82.9 million, GDP per capita: $52,386");
		if (myMap.size() != 5) {
			return false;
		}
		return true;

	}

	/*
	 * Tests that the get method returns the correct value when passed a key. If
	 * passed a key that does not exist in the hash table, then it tests whether
	 * a NoSuchElementException is caught or not.
	 * 
	 * @return True when the correct value is returned or an exception is
	 * caught, and False if an incorrect value is returned or an exception is
	 * not caught.
	 */
	public static boolean testGet() {
		HashTableMap<String, String> myMap = new HashTableMap<>(50);
		myMap.put("United States",
				"Geographic region: North America, Area: 9.83 million km^2, Capital: Washington D.C., "
						+ "GDP: $20.5 trillion, Population: 327.2 million, GDP per capita: $62,869");
		myMap.put("China",
				"Geographic region: Asia, Area: 9.6 million km^2, Capital: Beijing, GDP: $13.6 trillion, "
						+ "Population: 1.4 billion, GDP per capita: $18,116");
		myMap.put("United Kingdom",
				"Geographic region: Europe, Area: 243,610 km^2, Capital: London, GDP: $2.8 trillion, "
						+ "Population: 66.5 million, GDP per capita: $45,741");
		myMap.put("Russia",
				"Geographic region: Eurasia, Area: 17.1 million km^2, Capital: Moscow, GDP: $1.7 trillion, "
						+ "Population: 114.5 million, GDP per capita: $28,797");
		myMap.put("Germany",
				"Geographic region: Europe, Area: 357,022 km^2, Capital: Berlin, GDP: $4.0 trillion, "
						+ "Population: 82.9 million, GDP per capita: $52,386");
		if (myMap.get(
				"Russia") != "Geographic region: Eurasia, Area: 17.1 million km^2, Capital: Moscow, "
						+ "GDP: $1.7 trillion, Population: 114.5 million, GDP per capita: $28,797") {
			return false;
		}
		try {
			myMap.get("Japan");
			System.out.println(
					"Problem detected. Calling the get() method with an invalid key does"
							+ "not throw a NoSuchElementException.");
			return false;
		} catch (NoSuchElementException e) {
			return true;
		}
	}

	/*
	 * Tests the remove method by attempting to remove a key and then verifying
	 * that the value returned is the correct value and that the hash table
	 * decreases in size. Also tests whether calling the get method on a key
	 * that has previously been removed correctly throws a
	 * NoSuchElementException
	 * 
	 * @return True when the remove method returns the correct value and
	 * decreases the size of the hash table and False when the remove method
	 * does not
	 */
	public static boolean testRemove() {
		HashTableMap<String, String> myMap = new HashTableMap<>(50);
		myMap.put("United States",
				"Geographic region: North America, Area: 9.83 million km^2, Capital: Washington D.C., "
						+ "GDP: $20.5 trillion, Population: 327.2 million, GDP per capita: $62,869");
		myMap.put("China",
				"Geographic region: Asia, Area: 9.6 million km^2, Capital: Beijing, GDP: $13.6 trillion, "
						+ "Population: 1.4 billion, GDP per capita: $18,116");
		myMap.put("United Kingdom",
				"Geographic region: Europe, Area: 243,610 km^2, Capital: London, GDP: $2.8 trillion, "
						+ "Population: 66.5 million, GDP per capita: $45,741");
		myMap.put("Russia",
				"Geographic region: Eurasia, Area: 17.1 million km^2, Capital: Moscow, GDP: $1.7 trillion, "
						+ "Population: 114.5 million, GDP per capita: $28,797");
		myMap.put("Germany",
				"Geographic region: Europe, Area: 357,022 km^2, Capital: Berlin, GDP: $4.0 trillion, "
						+ "Population: 82.9 million, GDP per capita: $52,386");
		if (myMap.remove(
				"United States") != "Geographic region: North America, Area: 9.83 million km^2, "
						+ "Capital: Washington D.C., GDP: $20.5 trillion, Population: 327.2 million, GDP per capita: $62,869"
				&& myMap.size() != 4) {
			return false;
		}
		try {
			myMap.get("United States");
			System.out.println(
					"Problem detected. Calling the get() method with an invalid key does"
							+ "not throw a NoSuchElementException.");
			return false;
		} catch (NoSuchElementException e) {
			return true;
		}
	}

	/*
	 * Tests that the HashTableMap correctly grows and rehashes when the load
	 * capacity reaches or exceeds 80%. This method adds key-value pairs to a
	 * hash table with a capacity of 10 and checks whether the new capacity of
	 * the hash table is 20 after adding the 9th key-value pair.
	 * 
	 * @return True when the hash table correctly doubles and rehashes, and
	 * False otherwise
	 */
	public static boolean testLoadFactor() {
		HashTableMap<String, String> myMap = new HashTableMap<>(5);
		myMap.put("United States",
				"Geographic region: North America, Area: 9.83 million km^2, Capital: Washington D.C., "
						+ "GDP: $20.5 trillion, Population: 327.2 million, GDP per capita: $62,869");
		myMap.put("China",
				"Geographic region: Asia, Area: 9.6 million km^2, Capital: Beijing, GDP: $13.6 trillion, "
						+ "Population: 1.4 billion, GDP per capita: $18,116");
		myMap.put("United Kingdom",
				"Geographic region: Europe, Area: 243,610 km^2, Capital: London, GDP: $2.8 trillion, "
						+ "Population: 66.5 million, GDP per capita: $45,741");
		myMap.put("Russia",
				"Geographic region: Eurasia, Area: 17.1 million km^2, Capital: Moscow, GDP: $1.7 trillion, "
						+ "Population: 114.5 million, GDP per capita: $28,797");
		myMap.put("Germany",
				"Geographic region: Europe, Area: 357,022 km^2, Capital: Berlin, GDP: $4.0 trillion, "
						+ "Population: 82.9 million, GDP per capita: $52,386");
		if (myMap.size() != 5) {
			return false;
		}
		if (myMap.capacity() != 10) {
			return false;
		}
		myMap.put("France",
				"Geographic region: Europe, Area: 643,801 km^2, Capital: Paris, GDP: $2.8 trillion, "
						+ "Population: 67.0 million, GDP per capita: $45,893");
		myMap.put("Japan",
				"Geographic region: Asia, Area: 377,915 km^2, Capital: Tokyo, GDP: $5.0 trillion, "
						+ "Population: 126.5 million, GDP per capita: $44,246");
		myMap.put("Italy",
				"Geographic region: Europe, Area: 301.340 km^2, Capital: Rome, GDP: $2.1 trillion, "
						+ "Population: 60.4 million, GDP per capita: $39,676");
		if (myMap.capacity() != 20) {
			return false;
		}
		return true;
	}

	/*
	 * Tests that the clear method correctly removes all key-value pairs from
	 * the hash table
	 * 
	 * @return True when the size of the hash table is 0, and False otherwise
	 */
	public static boolean testClear() {
		HashTableMap<String, String> myMap = new HashTableMap<>(10);
		myMap.put("France",
				"Geographic region: Europe, Area: 643,801 km^2, Capital: Paris, GDP: $2.8 trillion, "
						+ "Population: 67.0 million, GDP per capita: $45,893");
		myMap.put("Japan",
				"Geographic region: Asia, Area: 377,915 km^2, Capital: Tokyo, GDP: $5.0 trillion, "
						+ "Population: 126.5 million, GDP per capita: $44,246");
		myMap.put("Italy",
				"Geographic region: Europe, Area: 301.340 km^2, Capital: Rome, GDP: $2.1 trillion, "
						+ "Population: 60.4 million, GDP per capita: $39,676");
		myMap.clear();
		if (myMap.size() != 0) {
			return false;
		}
		return true;
	}

	/*
	 * Tests that the contains method correctly return true or false depending
	 * on whether the key passed into the method is in the hash table or not
	 * 
	 * @return True when the method correctly tests for a key, and False
	 * otherwise
	 */
	public static boolean testContains() {
		HashTableMap<String, String> myMap = new HashTableMap<>(10);
		myMap.put("France",
				"Geographic region: Europe, Area: 643,801 km^2, Capital: Paris, GDP: $2.8 trillion, "
						+ "Population: 67.0 million, GDP per capita: $45,893");
		myMap.put("Japan",
				"Geographic region: Asia, Area: 377,915 km^2, Capital: Tokyo, GDP: $5.0 trillion, "
						+ "Population: 126.5 million, GDP per capita: $44,246");
		myMap.put("Italy",
				"Geographic region: Europe, Area: 301.340 km^2, Capital: Rome, GDP: $2.1 trillion, "
						+ "Population: 60.4 million, GDP per capita: $39,676");
		if (myMap.containsKey("Italy") != true) {
			return false;
		}
		if (myMap.containsKey("United States") != false) {
			return false;
		}
		return true;
	}

	/*
	 * Tests the MapData class that inputs all the countries and their
	 * respective data into a map. Makes sure the countries and info are
	 * retrievable when called with the correct line number from the
	 * countries.csv file.
	 */
	public static boolean testData() {
		MapData map = new MapData();
		if (!map.getCountry(0).equals("United States")) {
			return false;
		}
		if (!map.getCountry(72).equals("Latvia")) {
			return false;
		}
		if (!map.getCountry(10).equals("South Korea")) {
			return false;
		}
		if (!map.getInfo(10).equals(
				"Geographic region: Asia\nArea: 99,720 km^2\nCapital: Seoul\nGDP: $1.6 trillion\n"
						+ "Population: 51.6 million\nGDP per capita: $43,290.00\nOther info: \n")) {
			return false;
		}
		if (!map.getInfo(0).equals(
				"Geographic region: North America\nArea: 9.83 million km^2\nCapital: Washington, D.C.\n"
						+ "GDP: $20.5 trillion\nPopulation: 327.2 million\nGDP per capita: $62,869.00\nOther info: \n")) {
			return false;
		}
		return true;
	}

	/**
	 * This method should test the methods defined within this class and display
	 * their outputs
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static void main(String[] args) {
		System.out.println("testPut(): " + testPut());
		System.out.println("testGet(): " + testGet());
		System.out.println("testRemove(): " + testRemove());
		System.out.println("testLoadFactor(): " + testLoadFactor());
		System.out.println("testClear(): " + testClear());
		System.out.println("testContains(): " + testContains());
		System.out.println("testData(): " + testData());
	}
}
