package compersions;

import java.util.*;
import java.util.Map.Entry;

public class Tunstall {
	HashMap<String, Double> probabilityOfCharacters;
	Set set;
	Iterator i;
	HashMap<String, String> table = new HashMap<String, String>();
	
	int n, m, code = 0;
	Node root;
	public Tunstall(HashMap<String, Double> probabilityOfCharacters){
		this.probabilityOfCharacters = probabilityOfCharacters;
		set = probabilityOfCharacters.entrySet();
		n = probabilityOfCharacters.size();
		root = new Node();
		root.propability = 1.0;
		root.n = n;
	}
	public String encode(String value){
		String encodedString = "";
		treeBuilder(root, n);
		String stringCode = "";
		tableBuilder(root, n, stringCode);
		
		return encodedString;
	}
	private void treeBuilder(Node parent, int level){
		if(level == 0)return;
		i = set.iterator();
		while(i.hasNext()){
			Map.Entry me = (Map.Entry)i.next();
			Node child = new Node();
			child.value = (String) me.getKey();
			child.propability = (Double) me.getValue() * parent.propability;
			child.n = 0;
			parent.childern.add(child);
			
		}
		Node maxNode = parent.childern.get(0);
		for(int j = 1 ; j < parent.childern.size(); ++j){
			if(maxNode.propability < parent.childern.get(j).propability) maxNode = parent.childern.get(j);
		}
		maxNode.n = n;
		treeBuilder(maxNode, level - 1);
	}
	private void tableBuilder(Node parent, int level, String stringCode){
		if(level == 0 || parent.n == 0) {
			table.put(stringCode, String.valueOf(code));
			code++;
			return;
		}
		for(int j = 0; j < parent.childern.size() ; ++j ){
			stringCode += parent.childern.get(j).value;
			if(parent.n == n)tableBuilder(parent.childern.get(j), level - 1, stringCode);
			stringCode = stringCode.substring(0,stringCode.length() - 1 );
		}
	}
	
	public String decode(String value){
		String decodedString = "";
		
		return decodedString;
	}
	
	private class Node{
		Double propability;
		String value;
		int n;
		ArrayList<Node> childern = new ArrayList<Node>();
	}
}
