import java.util.Scanner;

public class Main {

    public static void main( String[] args) {
	Scanner in = new Scanner(System.in);

	System.out.println("Enter the sudoku play grid row by row : ");
	System.out.println("(Enter 0 for empty cell)");

	int[] arr = new int[81];
	for( int i = 0; i < arr.length; i++) {
	    arr[i] = in.nextInt();
	}

	Grid playGrid = new Grid();
	
	for( int i = 0; i < arr.length; i++) {
	    int solution = arr[i];
	    if( solution > 0) {
		playGrid.getCell(i).setSolution( solution, playGrid);
	    }
	}
	int changes = 0;
	do {
	    changes = Cell.getNumberSolved();
	    playGrid.checkRegionsRowsColumns();	
	    System.out.println("BC CALLED");
	    Grid.BinaryCandidates BC = playGrid.new BinaryCandidates();
	    PointingNumbers[] P = {BC.new PointingRows(), BC.new PointingColumns()};
	    BC.printRegions();
	    Printer.printCandidates( playGrid);
	    Printer.printGrid( playGrid);
	}
	while( changes != Cell.getNumberSolved());
	System.out.println("NUMBER SOLVED : " + Cell.getNumberSolved() );
	PauseTest.main( new String[0]);
    }
}

