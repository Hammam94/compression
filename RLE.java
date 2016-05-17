package compersions;

public class RLE {
	public String encode(String value){
		String encodedString = "";
		for(int i = 0; i < value.length() ; i++){
			encodedString += value.substring(i,i+1);
			if(i + 3 < value.length() && value.charAt(i) == value.charAt(i + 1) && value.charAt(i) == value.charAt(i + 2) && value.charAt(i) == value.charAt(i + 3)){
				int counter = 4, j = i+3;
				while(j+1 < value.length() && value.charAt(j) == value.charAt(j+1)){
					counter++;
					++j;
				}
				encodedString += "!" + counter;
				i = j;
			}
		}
		return encodedString;
	}
	
	public String decode(String value){
		String decodedString = "";
		int index = 1;
		String[] numbeOfRepetitions = value.split("[a-zA-Z!]+");
		for(int i = 0; i< value.length(); i++){
			if(value.charAt(i) != '!') decodedString += String.valueOf(value.charAt(i));
			else {
				for(int j = 0; j < Integer.parseInt(numbeOfRepetitions[index]) - 1 ; j++){
					decodedString +=  String.valueOf(value.charAt(i-1));
				}
				
				i += numbeOfRepetitions[index].length();
				++index;
			}
		}
		return decodedString;
	}
}
