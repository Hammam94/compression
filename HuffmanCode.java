package compersions;

import java.util.*;

public class HuffmanCode {
	PriorityQueue<Node> pqueue;
	HashMap<String, String> table = new HashMap<String, String>();
	public HuffmanCode(String[] values, int[] frequency){
	    pqueue = new PriorityQueue<>(values.length, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if (o1.freq < o2.freq )
			        return -1;
			    else if (o1.freq > o2.freq)
			        return 1;
			    return 0;
			}
		});
	    
	    for(int i = 0; i< values.length ; ++i){
	    	Node node = new Node();
	    	node.freq = frequency[i];
	    	node.c = values[i];
	    	pqueue.add(node);
	    }
	}
	
	public String encode(String value){
		String encodedString = "";
		treeBuild();
		tableBuild(pqueue.poll(), "");	
		for(int i = 0; i < value.length(); ++i){
			encodedString += (String) table.get(String.valueOf(value.charAt(i)));
		}		
		return encodedString;
	}
	private void treeBuild(){
		if(pqueue.size() == 1) return;
		Node first = pqueue.poll(), second = pqueue.poll();
		Node third = new Node();
		third.c = "*";
		third.freq = first.freq + second.freq;
		if(first.freq > second.freq){
			third.left = second;
			third.right = first;
		} else {
			third.left = first;
			third.right = second;
		}
		pqueue.add(third);
		treeBuild();
	}
	private void tableBuild(Node parent, String value){
		if(parent.left == null && parent.right == null){
			table.put(parent.c, value);
			return;
		}
		tableBuild(parent.left, value + "0");
		tableBuild(parent.right, value + "1");
	}
	
	public String decode(String value){
		String decodedString = "";
		String subString = "", key ="";
		for(int i = 0; i < value.length(); ++i){
			subString += value.substring(i, i+1);
			key = getKey(subString);
			if(key.equals(""))continue;
			else{
				decodedString += key;
				subString = "";
			}
		}
		return decodedString;
	}
	
	private String getKey(String value){
		String key = "";
        for(Map.Entry<String, String> entry : table.entrySet()) {
            if((value == null && entry.getValue() == null) || (value != null && value.equals(entry.getValue()))) {
                key = (String)entry.getKey();
                break;
            }
        }
        return key;
	}
	private class Node{
		int freq;
		String c;
		Node left;
		Node right;
	}
}
