package Helper;

public class IgnoreHelper {
	private static String[] listOfIgnore;
	public void init(String[] list){
		listOfIgnore = list;
	}
	public static boolean ifIgnore(String str){
		
		for (String element: listOfIgnore){
			if (element.toLowerCase().equals(str.toLowerCase())){
				return true;
			}
		}
		return false;
	}
}
