package compersions;

import java.util.*;

public class LZW {
	ArrayList<String> encodeTable = new ArrayList<String>();
	ArrayList<String> decodeTable = new ArrayList<String>();
	ArrayList<Integer> comperised = new ArrayList<Integer>(); 
	int index = 0;
	
	public String encode(String value){
		String encodedString = "";
	
		for(int i = 0 ; i < value.length() ; ++i){
			String subString = value.substring(i,i+1);
			if(!encodeTable.contains(subString)){
				encodeTable.add(subString);
				decodeTable.add(subString);
			}
		}
		
		String subString = "";
		for(int i = 0 ; i < value.length() ; ++i){
			subString += value.substring(i,i+1);
			if(!encodeTable.contains(subString)){
				encodeTable.add(subString);
				comperised.add(encodeTable.indexOf(subString.substring(0,subString.length() - 1)));
				encodedString += encodeTable.indexOf(subString.substring(0,subString.length() - 1));
				subString = "";
				--i;
			}
			if(i+1 == value.length()){
				comperised.add(encodeTable.indexOf(subString.substring(0,subString.length())));
				encodedString += encodeTable.indexOf(subString.substring(0,subString.length()));
			}
		}
		return encodedString;
	}
	
	public String decode(String value){
		String decodedString = "";
		String lastPatternUsed = "";
		for(int i = 0 ; i < comperised.size() ; ++i){
			int curInteger = comperised.get(i);
			if(curInteger >= decodeTable.size()){
				encodeFDecode(decodedString, curInteger, lastPatternUsed);
				lastPatternUsed = decodeTable.get(curInteger);
				decodedString += decodeTable.get(curInteger);
			} else {
				lastPatternUsed = decodeTable.get(curInteger);
				decodedString += decodeTable.get(curInteger);
			}
		}
//		for(int i = 0; i < decodeTable.size(); ++i){
//			System.out.println(decodeTable.get(i) + " : " + i + " // \\ " + encodeTable.get(i));
//		}
		return decodedString;
	}
	
	private void encodeFDecode(String value, int encodedNumber, String lastPatternUsed){
		String subString ="";
		int startIndex = index;
		for(int i = startIndex ; i < value.length() ; ++i){
			subString += value.substring(i,i+1);
			if(!decodeTable.contains(subString)){
				decodeTable.add(subString);
				subString = "";
				index = i;
				--i;
			} else if(i + 1 == value.length() && decodeTable.size() <= encodedNumber){
				decodeTable.add(subString + lastPatternUsed.substring(0,1));
				index = i + 1;
			}
		}
	}

}
