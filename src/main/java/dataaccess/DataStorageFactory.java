package dataaccess;

public class DataStorageFactory {
	
	public static DataStorage getStorage () {
		return new DataStorageDBconnect();
	}

}
