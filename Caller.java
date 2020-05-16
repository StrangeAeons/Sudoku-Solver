interface Caller {

    int[][] getArray( Cell[] cells);
}

class GetRegions implements Caller {


    public int[][] getArray( Cell[] cells) {
	int[][] arr = new int[9][10];
	for( int i = 0; i < 81; i++) {
	    int regionID = cells[i].regionID();
	    for(int j = 1; j < 10; j++) {
		if(cells[i].contains( j) ) {
		    arr[regionID][j]++;
		}
	    }
	}
	return arr;	
    }
}

class GetRows implements Caller {

    
    public int[][] getArray( Cell[] cells) {
	int[][] arr = new int[9][10];
	for( int i = 0; i < 81; i++) {
	    for( int j = 1; j < 10; j++) {
		if( cells[i].contains( j) )
		    arr[i / 9][j]++;
	    }
	}
	return arr;	
    }
}

class GetColumns implements Caller {

    
    public int[][] getArray( Cell[] cells) {
	int[][] arr = new int[9][10];
	for( int i = 0; i < 81; i++) {
	    for( int j = 1; j < 10; j++) {
		if( cells[i].contains( j) )
		    arr[i % 9][j]++;
	    }
	}
	return arr;	
    }
}
