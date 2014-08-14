import java.util.LinkedList;

public class Bits extends LinkedList<Boolean> {
	public String toString(){
		String result = "";
		for(boolean bit : this){
			if(bit){
				result += "1";
			} else {
				result += "0";
			}
		}
		return result;
	}
}
