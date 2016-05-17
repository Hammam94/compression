package compersions;

public class DE_RLE {
	int rows, columns;
	public DE_RLE(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
	}
	
	public String encode(int[][] values){
		String encodedString = "";
		for(int i = 0; i < rows; ++i)
			for(int j = 0; j < columns ; ++j){
				if(j+1 < columns && values[i][j] == values[i][j+1] && values[i][j] == values[i][j+2] && values[i][j] == values[i][j+3]){
					encodedString += String.valueOf(values[i][j]);
					int counter = 4, x = j+3;
					while(x+1 < columns && values[i][x] == values[i][x+1]){
						counter++;
						++x;
					}
					j = x;
					encodedString += "!" + counter +( i + 1 == rows && j+1 == columns ? "" :",");					
				}else{
					if (j != 0)encodedString += String.valueOf(values[i][j] - values[i][j-1]) + (i + 1 == rows && j+1 == columns ? "" :",");
					else encodedString += String.valueOf(values[i][j]) + (i + 1 == rows && j+1 == columns ? "" :",");
				}
			}
		return encodedString;
	}
	
	public int[][] decode(String value){
		int[][] decodedArray = new int[rows][columns] ;
		String[] list = value.split(",");
		int index = 0;
		for(int i = 0; i < rows; ++i){
			for(int j = 0; j < columns ; ++j){
				if(list[index].contains("!")){
					String[] container = list[index].split("!");
					int x = 0;
					for(x = 0; x < Integer.parseInt(container[1]) ; ++x){
						decodedArray[i][x] = Integer.parseInt(container[0]);
					}
					j+= x;
					index++;
				}else{
					if(j != 0)decodedArray[i][j] = decodedArray[i][j-1] + Integer.parseInt(list[index]);
					else decodedArray[i][j] = Integer.parseInt(list[index]);
					++index;
				}
			}
		}
		return decodedArray;
	}
	
}
