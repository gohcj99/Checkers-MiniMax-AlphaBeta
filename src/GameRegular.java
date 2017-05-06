
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class GameRegular {

	Scanner input = new Scanner(System.in);
	
	  private final static int SIZE = 8;
	    public char[][] board; // Stores the checker locations, with chars 'a','b','_'
	    public int[][] ScoringBoard = new int[SIZE][SIZE]; // For Tie breaker in MiniMax.
	    //public int[][] EvaluationMinMax = new int [700][5];
	    
	    public int[][] AB = new int [700][3];
	    
	    int ABcounter=0;
	    
	    int MiniMaxSteps=0;
	    int AlphaBetaSteps=0;
	    
	    int Alpha = -1000;
	    int Alpha2 = -1000;
	    
	    int Beta1=-1000;
	    int Beta2=-1000;
	    
	    int BetaOwner;
	    int BetaFlag=0;
	        
	    private int ACheckers; // Number of red checkers on the board
	    private int BCheckers; // Number of black checkers on the board
	    private char whosemove; // Either 'A' or 'B', for who's move it currently is.
	    
	    int CounterGlobal=0;
	    
	    // Constructs default Checkers object, initializing board to starting location
	    
	    public GameRegular() {
		
			board = new char[SIZE][SIZE];
			ACheckers = 12;
			BCheckers = 12;
			whosemove = 'a';
			// Initialize board with all the red and black checkers in starting
			// positions.
			int i,j;
			for (i=0;i<SIZE;i++)
			    for (j=0;j<SIZE;j++)
				board[i][j] = '_';
	
			for (i=1;i<SIZE;i+=2) {
			    board[i][1] = 'a';
			    board[i][5] = 'b';
			    board[i][7] = 'b';
			}
			for (i=0;i<SIZE;i+=2) {
			    board[i][0] = 'a';
			    board[i][2] = 'a';
			    board[i][6] = 'b';
			}
				
			ScoringBoard[0][0] = 4;
			ScoringBoard[2][0] = 4;
			ScoringBoard[4][0] = 4;
			ScoringBoard[6][0] = 4;
			ScoringBoard[7][1] = 4;
			ScoringBoard[7][3] = 4;
			ScoringBoard[7][5] = 4;
			ScoringBoard[7][7] = 4;
			ScoringBoard[5][7] = 4;
			ScoringBoard[3][7] = 4;
			ScoringBoard[1][7] = 4;
			ScoringBoard[0][6] = 4;
			ScoringBoard[0][4] = 4;
			ScoringBoard[0][2] = 4;
			
			ScoringBoard[1][1]=3;
			ScoringBoard[3][1]=3;
			ScoringBoard[5][1]=3;
			ScoringBoard[6][2]=3;
			ScoringBoard[6][4]=3;
			ScoringBoard[6][6]=3;
			ScoringBoard[4][6]=3;
			ScoringBoard[2][6]=3;
			ScoringBoard[1][5]=3;
			ScoringBoard[1][3]=3;
			
			ScoringBoard[2][2]=2;
			ScoringBoard[4][2]=2;
			ScoringBoard[5][3]=2;
			ScoringBoard[5][5]=2;
			ScoringBoard[3][5]=2;
			ScoringBoard[2][4]=2;
			
			ScoringBoard[3][3]=1;
			ScoringBoard[4][4]=1;
			
			
	    }
	    
	    public void PrintBoard() {
	    	
	    	int i,j;
	    	
	    	System.out.println("  0 1 2 3 4 5 6 7     [X-Axis]");
	    	
	    	for (i=0;i<SIZE;i++) {
	    	    System.out.print((i) + " ");
	    	    for (j=0;j<SIZE;j++) {
	    		System.out.print(board[j][i] + " ");
	    	    }
	    	    System.out.println();
	    	}
	    	System.out.println("\n[Y-Axis]");
	    	
	    	System.out.println("");
	    	
	        }
	    
	    public boolean gameOver() {
	    	return (ACheckers == 0 || BCheckers == 0);
	     }
	    
	    public void PlayerInput(GameRegular game){
	    	

	    	
	    	int[] PlayerChoice = new int[2];
	    	
	    	while (gameOver()==false){
	    	
	    	int[][] Evaluation = new int[50][4];
	    	
	    		 		
	    	if (whosemove=='a'){
	    		
	    	    System.out.println("It is your turn, Team A.");
	    	    
	    	}
	    	else {
	    	    System.out.println("It is your turn, Team B.");
	    	}
	    	
	    	System.out.println("");
	    	
	    	game.PrintBoard();
 	
	    	System.out.println("");

		    ListPossibleMove(board,whosemove,0);
		    	    
		    ABcounter=0;
		      
		    CounterGlobal=0;
		       
//			for( int i = 0; i < EvaluationMinMax .length; i++ )
//				   Arrays.fill( EvaluationMinMax [i], 0 );
			
		    Alpha = -1000;
		    Alpha2 = -1000;
		    
		    Beta1 = -1000;
		    Beta2 = -1000;
		    BetaFlag =0;
		    
			for( int i = 0; i < AB .length; i++ )
				   Arrays.fill( AB[i], 0 );
	    	
	    	System.out.println("_____________________________________________________________________________________");
	    	System.out.println("");
	    	
	    	if (whosemove=='a'){
	    		
	    		whosemove='b';
	    	}
	    	
	    	else {
	    	    whosemove='a';
	    	}
	    	
	    	}
	    	
	    	if (ACheckers==0){
	    		
	    		System.out.println("The Winner is TEAM B !\n");
	    		game.PrintBoard();
	    	}
	    	
	    	else {
	    		
	    		System.out.println("The Winner is TEAM A !\n");
	    		game.PrintBoard();
	    	}
	    
	    }
	    
	    public void ListPossibleMove(char[][] board,char team,int minimax){
	    	
	    	int[][] Level1Node = new int[50][4];
	    	
	    	int Level1NodeCounter=0;
	    	
	    	int[][] MiniMaxEvaluate = new int[30][12];
	    	
	    	int i,j;
	    	
	    	char kingTeam='X';
	    	
	    	if (team=='a'){
	    		kingTeam='A';
	    	}
	    	if (team=='b'){
	    		kingTeam='B';
	    	}
	    	
	    	int[] Coordinate = new int[2];
	    	int[][] ValidMoves = new int[40][12];
	    	
	    	int NumberOfMoves=0;
	    	
	    	int ValidMovesCounter = 0;
	    	
	    	System.out.println("All possible move for Team ["+team+"] are: ");
	    	System.out.println();
	    	
	    	int MaxKill=0;
	    	
	    	
	    	for (i=0;i<SIZE;i++) {
    			
    			for (j=0;j<SIZE;j++) {
    				
    				if(board[j][i]==team || board[j][i]==kingTeam){
    					
    					Coordinate[0]=j;
    					Coordinate[1]=i;
    					
    					char checkteam;
    					
    					if(board[j][i]==kingTeam ){

    						checkteam=kingTeam;
    					}
    					else{
    						checkteam=team;
    					}
    					
    					int[][] result = validMove(Coordinate,board,checkteam,1);
    					
    					int counter = 0;
    					
    			    	while (counter<30 && result[counter][0]==1 ){
    			    		
    			    		if(result[counter][5]>MaxKill){
    			    			
    			    			MaxKill=result[counter][5];
    			    		}
    			    		
    			    		counter++;
    			    	}
	    	
    				}
    			}
	    	}
	    		
	    	for (i=0;i<SIZE;i++) {
	    				    			
	    			for (j=0;j<SIZE;j++) {
	    				
	    				if(board[j][i]==team || board[j][i]==kingTeam){
	    					
	    					Coordinate[0]=j;
	    					Coordinate[1]=i;
	    					
	    					char checkteam;
	    					
	    					if(board[j][i]==kingTeam ){

	    						checkteam=kingTeam;
	    					}
	    					else{
	    						checkteam=team;
	    					}
	    					
	    					int[][] result = validMove(Coordinate,board,checkteam,1);
	    					
	    					int counter = 0;
	    					
	    			    	while (counter<30 && result[counter][0]==1){
	    			    		
		    		    		
	    			    		if (result[counter][5]==MaxKill){
	    			    		
	    			    		System.out.println(
	    			    				ValidMovesCounter+1+") "+"Move From: ["+(result[counter][1])+(result[counter][2])+"] "+
	    			    				"Move To: ["+(result[counter][3])+(result[counter][4])+"] "+
	    			    				"Will Jump (Kill) Enemy: ["+(result[counter][5])+"]" 
	    			    				
	    			    						);
	    			    		
	    			    		
	    			    		ValidMoves[ValidMovesCounter][0]=result[counter][1];
	    			    		ValidMoves[ValidMovesCounter][1]=result[counter][2];
	    			    		ValidMoves[ValidMovesCounter][2]=result[counter][3];
	    			    		ValidMoves[ValidMovesCounter][3]=result[counter][4];
	    			    		ValidMoves[ValidMovesCounter][4]=result[counter][5];
	    			    		ValidMoves[ValidMovesCounter][5]=result[counter][6];
	    			    		ValidMoves[ValidMovesCounter][6]=result[counter][7];
	    			    		ValidMoves[ValidMovesCounter][7]=result[counter][8];
	    			    		ValidMoves[ValidMovesCounter][8]=result[counter][9];
	    			    		ValidMoves[ValidMovesCounter][9]=result[counter][10];
	    			    		ValidMoves[ValidMovesCounter][10]=result[counter][11];
	    			    		
	    			    		int[] PotentialMoves = new int[11];
	    			    		
	    		    			PotentialMoves[0]=ValidMoves[ValidMovesCounter][0];
	    		    			PotentialMoves[1]=ValidMoves[ValidMovesCounter][1];
	    		    			PotentialMoves[2]=ValidMoves[ValidMovesCounter][2];
	    		    			PotentialMoves[3]=ValidMoves[ValidMovesCounter][3];
	    		    			PotentialMoves[4]=ValidMoves[ValidMovesCounter][4];
	    		    			PotentialMoves[5]=ValidMoves[ValidMovesCounter][5];
	    		    			PotentialMoves[6]=ValidMoves[ValidMovesCounter][6];
	    		    			PotentialMoves[7]=ValidMoves[ValidMovesCounter][7];
	    		    			PotentialMoves[8]=ValidMoves[ValidMovesCounter][8];
	    		    			PotentialMoves[9]=ValidMoves[ValidMovesCounter][9];
	    		    			PotentialMoves[10]=ValidMoves[ValidMovesCounter][10];
	    			    		
	    			    		
	    		    			int[] Result = new int[2];
	    		    			
	    		    			int MoveNumber = ValidMovesCounter+1;
	    		    			
	    			    		Result=Minimax(PotentialMoves,board,MoveNumber);
	    			    		
	    			    		MiniMaxEvaluate[ValidMovesCounter][0]=Result[0];
	    			    		MiniMaxEvaluate[ValidMovesCounter][1]=Result[1];
	    			    		
//	    			    		System.out.println("Final(1): "+MiniMaxEvaluate[ValidMovesCounter][0]);
//	    			    		System.out.println("Tie(1): "+MiniMaxEvaluate[ValidMovesCounter][1]);
//	    			    		System.out.println("");
	    			    		
	    			    		//MiniMaxEvaluation Level1
	    			    		
	    			    		Level1Node[Level1NodeCounter][0]=1;
	    			    		Level1Node[Level1NodeCounter][1]=Result[0];
	    			    		Level1Node[Level1NodeCounter][2]=Result[1];
	    			    		Level1Node[Level1NodeCounter][3]=(ValidMovesCounter+1);	
	    			    		
	    			    		Level1NodeCounter++;
	    			    		
	    			    		//MiniMaxEvaluation Level1 Ends
	    			    		
	    			    		NumberOfMoves++;
	    			    		ValidMovesCounter++;
	    			    		
	    			    		}
	    			    		counter++;
	    			    		
	    			    	}
	    			    		
	    				}
	    			}
	    			

	    	}
	    	
	    	//System.out.println();
	    	
	    	int MoveSelected;
	    	boolean flag = false;
	    	
	    	int[] SelectedMove = new int[11];
	    	
	    	System.out.println("Number of Possible Moves: "+NumberOfMoves);
	    	
		    int x=0;
		    int y=0;
		    	    
		    System.out.println();
		    
		    //Evaluate Level3
	    	
	    	int Max1=-1000;
	    	int Max2=-1000;
	    	int Ideal=0;
	    	
	    	int z = 0;
	    	
	    	while(Level1Node[z][0]!=0){
	    		
	    		MiniMaxSteps++;
	    		
//	    		System.out.println("("+MiniMaxSteps+") Evaluating Level 1: "+(z+1)+" ("+Level1Node[z][1]+","+Level1Node[z][2]+")");
	    		
	    		
	    		if(Level1Node[z][1]>Max1){
	    			
	    			Max1=Level1Node[z][1];
	    			Max2=Level1Node[z][2];
	    			Ideal=Level1Node[z][3];
	    			
	    		}
	    		
	    		if(Level1Node[z][1]==Max1 && Level1Node[z][2]>Max2){
	    			
	    			Max1=Level1Node[z][1];
	    			Max2=Level1Node[z][2];
	    			Ideal=Level1Node[z][3];
	    			
	    		}
	    		
	    		if(Level1Node[z][1]==Max1 && Level1Node[z][2]==Max2){
	    			
	    				
		    			Max1=Level1Node[z][1];
		    			Max2=Level1Node[z][2];
		    			Ideal=Level1Node[z][3];
		    			
	    				
	    			//}
	    			
	    			
	    		}
	    		
	    		
	    		z++;
	    	}
	    	

	    	//System.out.println("Evaluating Level 3 "+MoveNumber+"."+MoveNumber2+"."+(x+1)+" "+Level3Node[x][1]+" "+Level3Node[x][2]);
		    
		    //Ends
	    	
	    	x=0;
	    	System.out.println();
	    	
	    	//System.out.println("Alpha Beta Result:");
	    	
//	    	while(AB[x][0]!=0){
//	    		
//	    		System.out.print(AB[x][0]);
//	    		System.out.print(AB[x][1]);
//	    		System.out.println(AB[x][2]);
//	    		
//	    		x++;
//	    		
//	    	}
	    	

	    	System.out.println();
	    	System.out.println("Suggested Move (MiniMax) is move: "+Ideal+" ("+Max1+" "+Max2+") Steps Required: "+MiniMaxSteps);
	    	System.out.println("Suggested Move (AlphaBeta) is move: "+Ideal+" ("+Beta1+" "+Beta2+") Steps Required: "+AlphaBetaSteps);
	    	System.out.println("Improvement of AlphaBeta implmentation (Step Reduction): "+(MiniMaxSteps-AlphaBetaSteps)+ " steps.");
	    	System.out.println("");
	    	
	    	AlphaBetaSteps=0;
	    	MiniMaxSteps=0;
	    	Alpha = -1000;
	    		
	    	while (flag==false){
	    		
    			if(NumberOfMoves==0){
    				
    				System.out.println("Game Ended, Thanks for Playing !");
    			}
    			
    			else{
	    		
	    		
    				System.out.println("Select the move you want to make: ");
    				
    			}
    			
	    		 while (true)
	    	            try {
	    	            	MoveSelected = Integer.parseInt(input.nextLine());
	    	                break;
	    	            } catch (NumberFormatException nfe) {
	    	            	
	    	    			if(NumberOfMoves==0){
	    	    				
	    	    				System.out.println("Game Ended, Thanks for Playing !");
	    	    			}
	    	    			
	    	    			else{
	    	    				System.out.print("Please input number: ");
	    	    			}
	    	            }
	    		
	    		System.out.println("\n\n");
	    		
	    		if (MoveSelected<=0 || MoveSelected>NumberOfMoves){
	    			
	    			if(NumberOfMoves==0){
	    				
	    				System.out.println("Game Ended, Thanks for Playing !");
	    			}
	    			
	    			else{
	    			
	    				System.out.println("Error! Invalid Selection: "+MoveSelected);
	    			
	    			}
	    		}
	    		
	    		else {
	    			
	    			
	    			
	    			flag=true;
	    			
	    			SelectedMove[0]=ValidMoves[MoveSelected-1][0];
	    			SelectedMove[1]=ValidMoves[MoveSelected-1][1];
	    			SelectedMove[2]=ValidMoves[MoveSelected-1][2];
	    			SelectedMove[3]=ValidMoves[MoveSelected-1][3];
	    			SelectedMove[4]=ValidMoves[MoveSelected-1][4];
	    			SelectedMove[5]=ValidMoves[MoveSelected-1][5];
	    			SelectedMove[6]=ValidMoves[MoveSelected-1][6];
	    			SelectedMove[7]=ValidMoves[MoveSelected-1][7];
	    			SelectedMove[8]=ValidMoves[MoveSelected-1][8];
	    			SelectedMove[9]=ValidMoves[MoveSelected-1][9];
	    			SelectedMove[10]=ValidMoves[MoveSelected-1][10];
	    					
	    			PerformMove(SelectedMove,whosemove,board);
	    				
	    		}
	    		
	    	}
	    		
	    }
	    
	    public boolean CoordinateCheck(int[] coordinate,char turn){
	    	
	    	int x;
	    	int y;
	    	
	    	x=coordinate[0]+1;
	    	y=coordinate[1]+1;
	    	
	    	
	    	if (turn == 'b'){
	    			
	    		if (x==0 || x==9 || y==0 || y==9 ){
	    			
	    			return false;
	    		}
	    		
		    	else {
		    		
		    		return true;
		    	}

	    	}
	    	
	    	else {
	    		
	    		return true;
	    	}
	    	
	    }
	    
	    public int[] MoveLeft(int[] origin,char team){
	    	
	    	int[] Final = new int[2];
	    	
	    	int xOrigin = origin[0];
	    	int yOrigin = origin[1];
	    	
	    	int xFinal;
	    	int yFinal;
	    	
	    	if (team=='b' || team=='B'){
	    	
	    	xFinal = xOrigin-1;
	    	yFinal = yOrigin-1;
	    	
	    	Final[0] = xFinal;
	    	Final[1] = yFinal;
	    	
	    	return Final;
	    	
	    	}
	    	
	    	else{
	    			
	    	    	xFinal = xOrigin-1;
	    	    	yFinal = yOrigin+1;
	    	    	
	    	    	Final[0] = xFinal;
	    	    	Final[1] = yFinal;
	    	    	
	    	    	return Final;
	    			
	    	}
	    }
	    
	    public int[] MoveRight(int[] origin,char team){
	    	
	    	if (team=='b'|| team=='B'){
	    	
	    	int[] Final = new int[2];
	    	
	    	int xOrigin = origin[0];
	    	int yOrigin = origin[1];
	    	
	    	int xFinal;
	    	int yFinal;
	    	
	    	xFinal = xOrigin+1;
	    	yFinal = yOrigin-1;
	    	
	    	Final[0] = xFinal;
	    	Final[1] = yFinal;
	    	
	    	return Final;
	    	}
	    	
	    	else {
	    		
		    	int[] Final = new int[2];
		    	
		    	int xOrigin = origin[0];
		    	int yOrigin = origin[1];
		    	
		    	int xFinal;
		    	int yFinal;
		    	
		    	xFinal = xOrigin+1;
		    	yFinal = yOrigin+1;
		    	
		    	Final[0] = xFinal;
		    	Final[1] = yFinal;
		    	
		    	return Final;
		    }
	    	
	    }
	    
	    public char CheckersPresent (int[] Coordinate,char[][] board){
	    		
	    	int x = Coordinate[0];
	    	int y = Coordinate[1];
	    	
	    	if(CoordinateCheck(Coordinate,'b')==true && x>=0 && y>=0 && x<=8 && y<=8){
	    		
	    		return board[x][y];	  
	    	}
	    	
	    	
	    	
	    	else {
	    		
	    		return 'X';
	    	}
	    	
	    }
	    
	    public boolean VerifyTeam (int[] Origin,char[][] board,char Team){
	    	
	    	int x = Origin[0];
	    	int y = Origin[1];
	    	
	    	if (Team == board[x][y]){
	    		return true;
	    	}
	    	
	    	else{
	    		return false;
	    	}
	    	
	    }
	    
	    public int[][] validMove(int[] movefrom,char[][] board,char team,int kingloop) {
	    	
	    	// Identify Friend and Foe starts
	    	
	    	char friend = team;
	    	
	    	char enemy='X';
	    	
	    	char enemy2 = 'X';
	    	
	    	if (team=='b' || team=='B'){
	    		
	    		enemy='a';
	    		enemy2='A';
	    		
	    	}
	    	
	    	if (team=='a' || team=='A'){
	    		
	    		enemy='b';
	    		enemy2='B';
	    		
	    	}
	    	
	    	//Identify King and King related variables.
	    	
	    	int king;
	    	
	    	if (team=='B' || team=='A'){
	    		
	    		king=1;
	    	}
	    	
	    	else {
	    		
	    		king=0;
	    	}
	    	
	    	
	    	if (kingloop==0){
	    		
		    	if (team=='b' || team=='B'){
		    		
		    		
		    		team='A';
		    		enemy='a';
		    		enemy2='A';
		    		
		    	}
		    	
		    	else{
		    		
		    		team='B';
		    		enemy='b';
		    		enemy2='B';
		    		
		    	}
	    			
	    	}
	    	
	    	
	    	//Identify King and King related variables ends.
	    	
	    	// Identify Friends and Foe ends.
	    	
	    	int KillCounter = 0; // Tracks the number of kills (jumps) made.
	    	
	    	int[][] ValidMoves = new int[30][12]; // Array to keep track of Valid Moves.
	    	
	    	// Array Position: Indicator 2D. [][x]
	    	//[0]=Indicate Valid Move [1]=originX [2]=originY [3]=destinationX [4]=destinationY [5]=killCounter 
	    	//[6]=killoneX [7]=killoneY [8]=killtwoX [9]=killtwoY [10]=killthreeX [11]=killthreeY
	    	
	    	int SaveCounter=0; // The next available pointer to ValidMoves array.
	    	
	    	int xOrigin = movefrom[0]; // Initial X- Axis of piece which wanted to be moved.
	    	
	    	int yOrigin = movefrom[1]; // Initial Y- Axis of piece which wanted to be moved.
	    	
			String xValid;
			
			String yValid;
	    	
			int[] TempLocation1L = new int[2];
			int[] TempLocation1LE = new int[2];
						
			int[] TempLocation2L = new int[2];
			int[] TempLocation2R = new int[2];
			int[] TempLocation2LE = new int[2];
			int[] TempLocation2RE = new int[2];
			
			int[] TempLocation3L = new int[2];
			int[] TempLocation3R = new int[2];
			int[] TempLocation3LE = new int[2];
			int[] TempLocation3RE = new int[2];
			
	    	// Move Left 1 Attempt
			
			TempLocation1L=MoveLeft(movefrom,team);
			TempLocation1LE=MoveLeft(TempLocation1L,team);
			
			
			if (CoordinateCheck(TempLocation1L,'B')==true && CheckersPresent(TempLocation1L,board)=='_' && VerifyTeam(movefrom,board,friend)){
						
    			ValidMoves[SaveCounter][0]=1;
    			ValidMoves[SaveCounter][1]=movefrom[0];
    			ValidMoves[SaveCounter][2]=movefrom[1];
    			ValidMoves[SaveCounter][3]=TempLocation1L[0];
    			ValidMoves[SaveCounter][4]=TempLocation1L[1];
    			ValidMoves[SaveCounter][5]=KillCounter;
    			
    			//King Variables starts.
    			
    			boolean flag = true;
    			
    			int[] KingTemp = new int[2];
    			KingTemp[0]=ValidMoves[SaveCounter][3];
    			KingTemp[1]=ValidMoves[SaveCounter][4];
    			
    			//King Variables ends.
    			
    			SaveCounter++;
    			
    			//King Movement All Empty Left.
    			
    			if(king==1){
    				
    				//While there are empty left steps.
    				while (flag==true){
    					
    					if (CoordinateCheck(MoveLeft(KingTemp,team),'B')==true && CheckersPresent(MoveLeft(KingTemp,team),board)=='_' ){
    						
	    					
	    	    			xValid=Integer.toString(MoveLeft(KingTemp,team)[0]);
	    	    			yValid=Integer.toString(MoveLeft(KingTemp,team)[1]);
	    	    			
	    	    			ValidMoves[SaveCounter][0]=1;
	    	    			ValidMoves[SaveCounter][1]=movefrom[0];
	    	    			ValidMoves[SaveCounter][2]=movefrom[1];
	    	    			ValidMoves[SaveCounter][3]=MoveLeft(KingTemp,team)[0];
	    	    			ValidMoves[SaveCounter][4]=MoveLeft(KingTemp,team)[1];
	    	    			ValidMoves[SaveCounter][5]=KillCounter;
	    	    				
	    	    			KingTemp[0]=ValidMoves[SaveCounter][3];
	    	    			KingTemp[1]=ValidMoves[SaveCounter][4];
	    	    			
	    	    			SaveCounter++;
	    	    			
	    				}
    					
    					else{
    						
    						flag=false;
    					}
    						
    				}
    									
    			}
    			
    			else{
    				
    				//DO NOTHING PEASENT !
    			}
    			
    			
    			//King Movement All Empty Left ends.
    			    			
			 }
			
			// Move Left 1 Attempt Failed Due to presence of a piece
			else{
				
				//Determine if the piece if an enemy and can be attacked
				if (CoordinateCheck(TempLocation1L,'B')==true && (CheckersPresent(TempLocation1L,board)==enemy || CheckersPresent(TempLocation1L,board)==enemy2) && CheckersPresent(TempLocation1LE,board)=='_'){
					
						int xDestination = TempLocation1LE[0] ; // Destination of First Left Attack
		    			int yDestination = TempLocation1LE[1];
		    			
		    			int Kill1x = TempLocation1L[0]; // Coordinates of 1st killed enemy.
		    			int Kill1y = TempLocation1L[1];
		    				
		    			TempLocation2L[0]=MoveLeft(TempLocation1LE,team)[0]; // Position of next Left probe (see if presence of enemy)
		    			TempLocation2L[1]=MoveLeft(TempLocation1LE,team)[1];
		    			
		    			TempLocation2LE[0]=MoveLeft(TempLocation2L,team)[0]; // Position of next Left empty space.
		    			TempLocation2LE[1]=MoveLeft(TempLocation2L,team)[1];
		    			
		    			TempLocation2R[0]=MoveRight(TempLocation1LE,team)[0]; // Position of next Right probe (see if presence of enemy)
		    			TempLocation2R[1]=MoveRight(TempLocation1LE,team)[1];
		    			
		    			TempLocation2RE[0]=MoveRight(TempLocation2R,team)[0]; // Position of next Right empty space.
		    			TempLocation2RE[1]=MoveRight(TempLocation2R,team)[1];
		    			
		    			KillCounter++;
		    			
    	    			ValidMoves[SaveCounter][0]=1;
    	    			ValidMoves[SaveCounter][1]=movefrom[0];
    	    			ValidMoves[SaveCounter][2]=movefrom[1];
    	    			ValidMoves[SaveCounter][3]=xDestination;
    	    			ValidMoves[SaveCounter][4]=yDestination;
    	    			ValidMoves[SaveCounter][5]=KillCounter;
    	    			ValidMoves[SaveCounter][6]=Kill1x;
    	    			ValidMoves[SaveCounter][7]=Kill1y;
    	    			
    	    			//Determine 2nd attack through left.
    	    			
    	    			if ((CheckersPresent(TempLocation2L,board)==enemy || CheckersPresent(TempLocation2L,board)==enemy2) && CheckersPresent(TempLocation2LE,board) =='_' && CoordinateCheck(TempLocation2LE,'B')==true){
    	    				
    						xDestination = TempLocation2LE[0] ; // Destination of Second Left Left Attack
    		    			yDestination = TempLocation2LE[1];
    		    				
        	    			int Kill2x = TempLocation2L[0]; // Coordinates of 2nd killed enemy.
        	    			int Kill2y = TempLocation2L[1];
        	    			    	    			
    		    			TempLocation3L[0]=MoveLeft(TempLocation2LE,team)[0]; // Position of next Left probe (see if presence of enemy)
    		    			TempLocation3L[1]=MoveLeft(TempLocation2LE,team)[1];
    		    			
    		    			TempLocation3LE[0]=MoveLeft(TempLocation3L,team)[0]; // Position of next Left empty space.
    		    			TempLocation3LE[1]=MoveLeft(TempLocation3L,team)[1];
    		    			
    		    			TempLocation3R[0]=MoveRight(TempLocation2LE,team)[0]; // Position of next Right probe (see if presence of enemy)
    		    			TempLocation3R[1]=MoveRight(TempLocation2LE,team)[1];
    		    			
    		    			TempLocation3RE[0]=MoveRight(TempLocation3R,team)[0]; // Position of next Right empty space.
    		    			TempLocation3RE[1]=MoveRight(TempLocation3R,team)[1];
        	    			
        	    			KillCounter++;
        	    			
        	    			ValidMoves[SaveCounter][0]=1;
        	    			ValidMoves[SaveCounter][1]=movefrom[0];
        	    			ValidMoves[SaveCounter][2]=movefrom[1];
        	    			ValidMoves[SaveCounter][3]=xDestination;
        	    			ValidMoves[SaveCounter][4]=yDestination;
        	    			ValidMoves[SaveCounter][5]=KillCounter;
        	    			ValidMoves[SaveCounter][6]=Kill1x;
        	    			ValidMoves[SaveCounter][7]=Kill1y;
        	    			ValidMoves[SaveCounter][8]=Kill2x;
        	    			ValidMoves[SaveCounter][9]=Kill2y;
        	    			
        	    			
        	    			//Determine 3rd attack through left.
        	    			
        	    			if ((CheckersPresent(TempLocation3L,board)==enemy || CheckersPresent(TempLocation3L,board)==enemy2) && CheckersPresent(TempLocation3LE,board) =='_' && CoordinateCheck(TempLocation3LE,'B')==true){
        	    				
        						xDestination = TempLocation3LE[0] ; // Destination of Second Left Left Attack
        		    			yDestination = TempLocation3LE[1];
            	    			
            	    			int Kill3x = TempLocation3L[0]; // Coordinates of 3rd killed enemy.
            	    			int Kill3y = TempLocation3L[1];
            	    			    	    			   			
            	    			KillCounter++;
            	    			
            	    			ValidMoves[SaveCounter][0]=1;
            	    			ValidMoves[SaveCounter][1]=movefrom[0];
            	    			ValidMoves[SaveCounter][2]=movefrom[1];
            	    			ValidMoves[SaveCounter][3]=xDestination;
            	    			ValidMoves[SaveCounter][4]=yDestination;
            	    			ValidMoves[SaveCounter][5]=KillCounter;
            	    			ValidMoves[SaveCounter][6]=Kill1x;
            	    			ValidMoves[SaveCounter][7]=Kill1y;
            	    			ValidMoves[SaveCounter][8]=Kill2x;
            	    			ValidMoves[SaveCounter][9]=Kill2y;
            	    			ValidMoves[SaveCounter][10]=Kill3x;
            	    			ValidMoves[SaveCounter][11]=Kill3y;
            	    			
            	    			SaveCounter++;
            	    			
        	    			}
        	    			
        	    			//3rd left attack fail.
        	    			else{
        	    				
        	    				//Determine 3rd attack through right
        	    				if ((CheckersPresent(TempLocation3R,board)==enemy || CheckersPresent(TempLocation3R,board)==enemy2) && CheckersPresent(TempLocation3RE,board) =='_' && CoordinateCheck(TempLocation3RE,'B')==true){
        	    				
            						xDestination = TempLocation3RE[0] ; // Destination of Second Left Right Attack
            		    			yDestination = TempLocation3LE[1];
                	    			
                	    			int Kill3x = TempLocation3R[0]; // Coordinates of 3rd killed enemy.
                	    			int Kill3y = TempLocation3R[1];
                	    			    	    			   			
                	    			KillCounter++;
                	    			
                	    			ValidMoves[SaveCounter][0]=1;
                	    			ValidMoves[SaveCounter][1]=movefrom[0];
                	    			ValidMoves[SaveCounter][2]=movefrom[1];
                	    			ValidMoves[SaveCounter][3]=xDestination;
                	    			ValidMoves[SaveCounter][4]=yDestination;
                	    			ValidMoves[SaveCounter][5]=KillCounter;
                	    			ValidMoves[SaveCounter][6]=Kill1x;
                	    			ValidMoves[SaveCounter][7]=Kill1y;
                	    			ValidMoves[SaveCounter][8]=Kill2x;
                	    			ValidMoves[SaveCounter][9]=Kill2y;
                	    			ValidMoves[SaveCounter][10]=Kill3x;
                	    			ValidMoves[SaveCounter][11]=Kill3y;
                	    			
                	    			SaveCounter++;
        	    						
        	    				}
        	    				
        	    				//3rd right attack fail.
        	    				else{
        	    					
                	    			SaveCounter++;
        	    					//DO NOTHING AND TRY TO MOVE TO RIGHT.
        	    				}
        	    				
        	    				
        	    			}

        	    			
    	    			}  	    			
    	    			//2nd left attack fail.
    	    			
    	    			else {
    	    				
    	    				//Determine 2nd attack through right.
    	    				if ((CheckersPresent(TempLocation2R,board)==enemy || CheckersPresent(TempLocation2R,board)==enemy2) && CheckersPresent(TempLocation2RE,board) =='_' && CoordinateCheck(TempLocation2LE,'B')==true){
        	    				
        						xDestination = TempLocation2RE[0] ; // Destination of Second Left Left Attack
        		    			yDestination = TempLocation2RE[1];
        		    				
            	    			int Kill2x = TempLocation2R[0]; // Coordinates of 2nd killed enemy.
            	    			int Kill2y = TempLocation2R[1];
            	    			    	    			
        		    			TempLocation3L[0]=MoveLeft(TempLocation2RE,team)[0]; // Position of next Left probe (see if presence of enemy)
        		    			TempLocation3L[1]=MoveLeft(TempLocation2RE,team)[1];
        		    			
        		    			TempLocation3LE[0]=MoveLeft(TempLocation3L,team)[0]; // Position of next Left empty space.
        		    			TempLocation3LE[1]=MoveLeft(TempLocation3L,team)[1];
        		    			
        		    			TempLocation3R[0]=MoveRight(TempLocation2RE,team)[0]; // Position of next Right probe (see if presence of enemy)
        		    			TempLocation3R[1]=MoveRight(TempLocation2RE,team)[1];
        		    			
        		    			TempLocation3RE[0]=MoveRight(TempLocation3R,team)[0]; // Position of next Right empty space.
        		    			TempLocation3RE[1]=MoveRight(TempLocation3R,team)[1];
            	    			
            	    			KillCounter++;
            	    			
            	    			ValidMoves[SaveCounter][0]=1;
            	    			ValidMoves[SaveCounter][1]=movefrom[0];
            	    			ValidMoves[SaveCounter][2]=movefrom[1];
            	    			ValidMoves[SaveCounter][3]=xDestination;
            	    			ValidMoves[SaveCounter][4]=yDestination;
            	    			ValidMoves[SaveCounter][5]=KillCounter;
            	    			ValidMoves[SaveCounter][6]=Kill1x;
            	    			ValidMoves[SaveCounter][7]=Kill1y;
            	    			ValidMoves[SaveCounter][8]=Kill2x;
            	    			ValidMoves[SaveCounter][9]=Kill2y;
            	    			
            	    			//Detect 3rd Attack through left
            	    			if ((CheckersPresent(TempLocation3L,board)==enemy || CheckersPresent(TempLocation3L,board)==enemy2) && CheckersPresent(TempLocation3LE,board) =='_' && CoordinateCheck(TempLocation3LE,'B')==true){
            	    				
            						xDestination = TempLocation3LE[0] ; // Destination of Second Left Left Attack
            		    			yDestination = TempLocation3LE[1];
                	    			
                	    			int Kill3x = TempLocation3L[0]; // Coordinates of 3rd killed enemy.
                	    			int Kill3y = TempLocation3L[1];
                	    			    	    			   			
                	    			KillCounter++;
                	    			
                	    			ValidMoves[SaveCounter][0]=1;
                	    			ValidMoves[SaveCounter][1]=movefrom[0];
                	    			ValidMoves[SaveCounter][2]=movefrom[1];
                	    			ValidMoves[SaveCounter][3]=xDestination;
                	    			ValidMoves[SaveCounter][4]=yDestination;
                	    			ValidMoves[SaveCounter][5]=KillCounter;
                	    			ValidMoves[SaveCounter][6]=Kill1x;
                	    			ValidMoves[SaveCounter][7]=Kill1y;
                	    			ValidMoves[SaveCounter][8]=Kill2x;
                	    			ValidMoves[SaveCounter][9]=Kill2y;
                	    			ValidMoves[SaveCounter][10]=Kill3x;
                	    			ValidMoves[SaveCounter][11]=Kill3y;
                	    			
                	    			SaveCounter++;
                	    			
            	    			}
            	    			
            	    			//3rd Attack through left failed.
            	    			else{
            	    				
            	    				//Determine 3rd attack through right
            	    				if ((CheckersPresent(TempLocation3R,board)==enemy || CheckersPresent(TempLocation3R,board)==enemy2) && CheckersPresent(TempLocation3RE,board) =='_' && CoordinateCheck(TempLocation3RE,'B')==true){
            	    				
                						xDestination = TempLocation3RE[0] ; // Destination of Second Left Right Attack
                		    			yDestination = TempLocation3LE[1];
                    	    			
                    	    			int Kill3x = TempLocation3R[0]; // Coordinates of 3rd killed enemy.
                    	    			int Kill3y = TempLocation3R[1];
                    	    			    	    			   			
                    	    			KillCounter++;
                    	    			
                    	    			ValidMoves[SaveCounter][0]=1;
                    	    			ValidMoves[SaveCounter][1]=movefrom[0];
                    	    			ValidMoves[SaveCounter][2]=movefrom[1];
                    	    			ValidMoves[SaveCounter][3]=xDestination;
                    	    			ValidMoves[SaveCounter][4]=yDestination;
                    	    			ValidMoves[SaveCounter][5]=KillCounter;
                    	    			ValidMoves[SaveCounter][6]=Kill1x;
                    	    			ValidMoves[SaveCounter][7]=Kill1y;
                    	    			ValidMoves[SaveCounter][8]=Kill2x;
                    	    			ValidMoves[SaveCounter][9]=Kill2y;
                    	    			ValidMoves[SaveCounter][10]=Kill3x;
                    	    			ValidMoves[SaveCounter][11]=Kill3y;
                    	    			
                    	    			SaveCounter++;
            	    						
            	    				}
            	    				
            	    				else{
            	    					
            	    	    			SaveCounter++;
            	    					//DO NOTHING AND TRY TO MOVE TO RIGHT.
            	    				}
            	    				
            	    			}

    	    				}
    	    				
    	    				//2nd attack though right failed
    	    				else{
    	    					
    	    	    			SaveCounter++;
    	    					//DO NOTHING AND TRY TO MOVE TO RIGHT.
    	    				}
    	    			}
				}
				
				
				//1st Left piece cannot be attacked because it is friendly or blocked.
				else {
	    			//SaveCounter++;
					//DO NOTHING AND TRY TO MOVE TO RIGHT.
					
				}
				
			}
			
			// Move Left 1 Attempt ends
			
			KillCounter=0;
			
			// Move Right 1 Attempt
			
			int[] TempLocation1R = new int[2];
			int[] TempLocation1RE = new int[2];
						
			TempLocation1R=MoveRight(movefrom,team);
			TempLocation1RE=MoveRight(TempLocation1R,team);
			
			if (CoordinateCheck(TempLocation1R,'B')==true && CheckersPresent(TempLocation1R,board)=='_' && VerifyTeam(movefrom,board,friend)){

    			ValidMoves[SaveCounter][0]=1;
    			ValidMoves[SaveCounter][1]=movefrom[0];
    			ValidMoves[SaveCounter][2]=movefrom[1];
    			ValidMoves[SaveCounter][3]=MoveRight(movefrom,team)[0];
    			ValidMoves[SaveCounter][4]=MoveRight(movefrom,team)[1];
    			ValidMoves[SaveCounter][5]=KillCounter;
    			
    			//King variable starts.
    			
    			boolean flag = true;
    			
    			int[] KingTemp = new int[2];
    			KingTemp[0]=ValidMoves[SaveCounter][3];
    			KingTemp[1]=ValidMoves[SaveCounter][4];
    				
    			//King variable ends.
    			
    			SaveCounter++;
    			
    			//King all available Right Movement.
    			
    			if (king==1){
    				
    				while (flag==true){
    					
	    				if (CoordinateCheck(MoveRight(KingTemp,team),'B')==true && CheckersPresent(MoveRight(KingTemp,team),board)=='_' ){
	    					
		
	    	    			ValidMoves[SaveCounter][0]=1;
	    	    			ValidMoves[SaveCounter][1]=movefrom[0];
	    	    			ValidMoves[SaveCounter][2]=movefrom[1];
	    	    			ValidMoves[SaveCounter][3]=MoveRight(KingTemp,team)[0];
	    	    			ValidMoves[SaveCounter][4]=MoveRight(KingTemp,team)[1];
	    	    			ValidMoves[SaveCounter][5]=KillCounter;
	    	    				
	    	    			KingTemp[0]=ValidMoves[SaveCounter][3];
	    	    			KingTemp[1]=ValidMoves[SaveCounter][4];
	    	    			
	    	    			SaveCounter++;
    	    			
	    				}
    				
	    				else {
    	    				
	    					flag=false;
	    				}
    					
    				}
    				
    			}
    			
    			else {
    				
    				//DO NOTHING PEASENT !
    			}
    			
    			//King all available Right Movement ends.   			
			 }
			// Move Right 1 Attempt Failed Due to presence of a piece
			else{
				
				//Determine if the piece if an enemy and can be attacked
				if (CoordinateCheck(TempLocation1R,'B')==true && (CheckersPresent(TempLocation1R,board)==enemy || CheckersPresent(TempLocation1R,board)==enemy2) && CheckersPresent(TempLocation1RE,board)=='_'){
					
						int xDestination = TempLocation1RE[0] ; // Destination of First Left Attack
		    			int yDestination = TempLocation1RE[1];
		    			
		    			int Kill1x = TempLocation1R[0]; // Coordinates of 1st killed enemy.
		    			int Kill1y = TempLocation1R[1];
		    				
		    			TempLocation2L[0]=MoveLeft(TempLocation1RE,team)[0]; // Position of next Left probe (see if presence of enemy)
		    			TempLocation2L[1]=MoveLeft(TempLocation1RE,team)[1];
		    			
		    			TempLocation2LE[0]=MoveLeft(TempLocation2L,team)[0]; // Position of next Left empty space.
		    			TempLocation2LE[1]=MoveLeft(TempLocation2L,team)[1];
		    			
		    			TempLocation2R[0]=MoveRight(TempLocation1RE,team)[0]; // Position of next Right probe (see if presence of enemy)
		    			TempLocation2R[1]=MoveRight(TempLocation1RE,team)[1];
		    			
		    			TempLocation2RE[0]=MoveRight(TempLocation2R,team)[0]; // Position of next Right empty space.
		    			TempLocation2RE[1]=MoveRight(TempLocation2R,team)[1];
		    			
		    			KillCounter++;
		    			
    	    			ValidMoves[SaveCounter][0]=1;
    	    			ValidMoves[SaveCounter][1]=movefrom[0];
    	    			ValidMoves[SaveCounter][2]=movefrom[1];
    	    			ValidMoves[SaveCounter][3]=xDestination;
    	    			ValidMoves[SaveCounter][4]=yDestination;
    	    			ValidMoves[SaveCounter][5]=KillCounter;
    	    			ValidMoves[SaveCounter][6]=Kill1x;
    	    			ValidMoves[SaveCounter][7]=Kill1y;
    	    			
    	    			//Determine 2nd attack through left.
    	    			
    	    			if ((CheckersPresent(TempLocation2L,board)==enemy || CheckersPresent(TempLocation2L,board)==enemy2) && CheckersPresent(TempLocation2LE,board) =='_' && CoordinateCheck(TempLocation2LE,'B')==true){
    	    				
    						xDestination = TempLocation2LE[0] ; // Destination of Second Left Left Attack
    		    			yDestination = TempLocation2LE[1];
    		    				
        	    			int Kill2x = TempLocation2L[0]; // Coordinates of 2nd killed enemy.
        	    			int Kill2y = TempLocation2L[1];
        	    			    	    			
    		    			TempLocation3L[0]=MoveLeft(TempLocation2LE,team)[0]; // Position of next Left probe (see if presence of enemy)
    		    			TempLocation3L[1]=MoveLeft(TempLocation2LE,team)[1];
    		    			
    		    			TempLocation3LE[0]=MoveLeft(TempLocation3L,team)[0]; // Position of next Left empty space.
    		    			TempLocation3LE[1]=MoveLeft(TempLocation3L,team)[1];
    		    			
    		    			TempLocation3R[0]=MoveRight(TempLocation2LE,team)[0]; // Position of next Right probe (see if presence of enemy)
    		    			TempLocation3R[1]=MoveRight(TempLocation2LE,team)[1];
    		    			
    		    			TempLocation3RE[0]=MoveRight(TempLocation3R,team)[0]; // Position of next Right empty space.
    		    			TempLocation3RE[1]=MoveRight(TempLocation3R,team)[1];
        	    			
        	    			KillCounter++;
        	    			
        	    			ValidMoves[SaveCounter][0]=1;
        	    			ValidMoves[SaveCounter][1]=movefrom[0];
        	    			ValidMoves[SaveCounter][2]=movefrom[1];
        	    			ValidMoves[SaveCounter][3]=xDestination;
        	    			ValidMoves[SaveCounter][4]=yDestination;
        	    			ValidMoves[SaveCounter][5]=KillCounter;
        	    			ValidMoves[SaveCounter][6]=Kill1x;
        	    			ValidMoves[SaveCounter][7]=Kill1y;
        	    			ValidMoves[SaveCounter][8]=Kill2x;
        	    			ValidMoves[SaveCounter][9]=Kill2y;
        	    			
        	    			
        	    			//Determine 3rd attack through left.
        	    			
        	    			if ((CheckersPresent(TempLocation3L,board)==enemy || CheckersPresent(TempLocation3L,board)==enemy2) && CheckersPresent(TempLocation3LE,board) =='_' && CoordinateCheck(TempLocation3LE,'B')==true){
        	    				
        						xDestination = TempLocation3LE[0] ; // Destination of Second Left Left Attack
        		    			yDestination = TempLocation3LE[1];
            	    			
            	    			int Kill3x = TempLocation3L[0]; // Coordinates of 3rd killed enemy.
            	    			int Kill3y = TempLocation3L[1];
            	    			    	    			   			
            	    			KillCounter++;
            	    			
            	    			ValidMoves[SaveCounter][0]=1;
            	    			ValidMoves[SaveCounter][1]=movefrom[0];
            	    			ValidMoves[SaveCounter][2]=movefrom[1];
            	    			ValidMoves[SaveCounter][3]=xDestination;
            	    			ValidMoves[SaveCounter][4]=yDestination;
            	    			ValidMoves[SaveCounter][5]=KillCounter;
            	    			ValidMoves[SaveCounter][6]=Kill1x;
            	    			ValidMoves[SaveCounter][7]=Kill1y;
            	    			ValidMoves[SaveCounter][8]=Kill2x;
            	    			ValidMoves[SaveCounter][9]=Kill2y;
            	    			ValidMoves[SaveCounter][10]=Kill3x;
            	    			ValidMoves[SaveCounter][11]=Kill3y;
            	    			
            	    			SaveCounter++;
            	    			
        	    			}
        	    			
        	    			//3rd left attack fail.
        	    			else{
        	    				
        	    				//Determine 3rd attack through right
        	    				if ((CheckersPresent(TempLocation3R,board)==enemy || CheckersPresent(TempLocation3R,board)==enemy2) && CheckersPresent(TempLocation3RE,board) =='_' && CoordinateCheck(TempLocation3RE,'B')==true){
        	    				
            						xDestination = TempLocation3RE[0] ; // Destination of Second Left Right Attack
            		    			yDestination = TempLocation3LE[1];
                	    			
                	    			int Kill3x = TempLocation3R[0]; // Coordinates of 3rd killed enemy.
                	    			int Kill3y = TempLocation3R[1];
                	    			    	    			   			
                	    			KillCounter++;
                	    			
                	    			ValidMoves[SaveCounter][0]=1;
                	    			ValidMoves[SaveCounter][1]=movefrom[0];
                	    			ValidMoves[SaveCounter][2]=movefrom[1];
                	    			ValidMoves[SaveCounter][3]=xDestination;
                	    			ValidMoves[SaveCounter][4]=yDestination;
                	    			ValidMoves[SaveCounter][5]=KillCounter;
                	    			ValidMoves[SaveCounter][6]=Kill1x;
                	    			ValidMoves[SaveCounter][7]=Kill1y;
                	    			ValidMoves[SaveCounter][8]=Kill2x;
                	    			ValidMoves[SaveCounter][9]=Kill2y;
                	    			ValidMoves[SaveCounter][10]=Kill3x;
                	    			ValidMoves[SaveCounter][11]=Kill3y;
                	    			
                	    			SaveCounter++;
        	    						
        	    				}
        	    				
        	    				//3rd right attack fail.
        	    				else{
        	    					
                	    			SaveCounter++;
        	    					//DO NOTHING AND TRY TO MOVE TO RIGHT.
        	    				}	
        	    				
        	    			}
        	    			
    	    			}  	    			
    	    			//2nd left attack fail.
    	    			
    	    			else {
    	    				
    	    				//Determine 2nd attack through right.
    	    				if ((CheckersPresent(TempLocation2R,board)==enemy || CheckersPresent(TempLocation2R,board)==enemy2) && CheckersPresent(TempLocation2RE,board) =='_' && CoordinateCheck(TempLocation2LE,'B')==true){
        	    				
        						xDestination = TempLocation2RE[0] ; // Destination of Second Left Left Attack
        		    			yDestination = TempLocation2RE[1];
        		    				
            	    			int Kill2x = TempLocation2R[0]; // Coordinates of 2nd killed enemy.
            	    			int Kill2y = TempLocation2R[1];
            	    			    	    			
        		    			TempLocation3L[0]=MoveLeft(TempLocation2RE,team)[0]; // Position of next Left probe (see if presence of enemy)
        		    			TempLocation3L[1]=MoveLeft(TempLocation2RE,team)[1];
        		    			
        		    			TempLocation3LE[0]=MoveLeft(TempLocation3L,team)[0]; // Position of next Left empty space.
        		    			TempLocation3LE[1]=MoveLeft(TempLocation3L,team)[1];
        		    			
        		    			TempLocation3R[0]=MoveRight(TempLocation2RE,team)[0]; // Position of next Right probe (see if presence of enemy)
        		    			TempLocation3R[1]=MoveRight(TempLocation2RE,team)[1];
        		    			
        		    			TempLocation3RE[0]=MoveRight(TempLocation3R,team)[0]; // Position of next Right empty space.
        		    			TempLocation3RE[1]=MoveRight(TempLocation3R,team)[1];
            	    			
            	    			KillCounter++;
            	    			
            	    			ValidMoves[SaveCounter][0]=1;
            	    			ValidMoves[SaveCounter][1]=movefrom[0];
            	    			ValidMoves[SaveCounter][2]=movefrom[1];
            	    			ValidMoves[SaveCounter][3]=xDestination;
            	    			ValidMoves[SaveCounter][4]=yDestination;
            	    			ValidMoves[SaveCounter][5]=KillCounter;
            	    			ValidMoves[SaveCounter][6]=Kill1x;
            	    			ValidMoves[SaveCounter][7]=Kill1y;
            	    			ValidMoves[SaveCounter][8]=Kill2x;
            	    			ValidMoves[SaveCounter][9]=Kill2y;
            	    			
            	    			//Detect 3rd Attack through left
            	    			if ((CheckersPresent(TempLocation3L,board)==enemy || CheckersPresent(TempLocation3L,board)==enemy2) && CheckersPresent(TempLocation3LE,board) =='_' && CoordinateCheck(TempLocation3LE,'B')==true){
            	    				
            						xDestination = TempLocation3LE[0] ; // Destination of Second Left Left Attack
            		    			yDestination = TempLocation3LE[1];
                	    			
                	    			int Kill3x = TempLocation3L[0]; // Coordinates of 3rd killed enemy.
                	    			int Kill3y = TempLocation3L[1];
                	    			    	    			   			
                	    			KillCounter++;
                	    			
                	    			ValidMoves[SaveCounter][0]=1;
                	    			ValidMoves[SaveCounter][1]=movefrom[0];
                	    			ValidMoves[SaveCounter][2]=movefrom[1];
                	    			ValidMoves[SaveCounter][3]=xDestination;
                	    			ValidMoves[SaveCounter][4]=yDestination;
                	    			ValidMoves[SaveCounter][5]=KillCounter;
                	    			ValidMoves[SaveCounter][6]=Kill1x;
                	    			ValidMoves[SaveCounter][7]=Kill1y;
                	    			ValidMoves[SaveCounter][8]=Kill2x;
                	    			ValidMoves[SaveCounter][9]=Kill2y;
                	    			ValidMoves[SaveCounter][10]=Kill3x;
                	    			ValidMoves[SaveCounter][11]=Kill3y;
                	    			
                	    			SaveCounter++;
                	    			
            	    			}
            	    			
            	    			//3rd Attack through left failed.
            	    			else{
            	    				
            	    				//Determine 3rd attack through right
            	    				if ((CheckersPresent(TempLocation3R,board)==enemy || CheckersPresent(TempLocation3R,board)==enemy2) && CheckersPresent(TempLocation3RE,board) =='_' && CoordinateCheck(TempLocation3RE,'B')==true){
            	    				
                						xDestination = TempLocation3RE[0] ; // Destination of Second Left Right Attack
                		    			yDestination = TempLocation3LE[1];
                    	    			
                    	    			int Kill3x = TempLocation3R[0]; // Coordinates of 3rd killed enemy.
                    	    			int Kill3y = TempLocation3R[1];
                    	    			    	    			   			
                    	    			KillCounter++;
                    	    			
                    	    			ValidMoves[SaveCounter][0]=1;
                    	    			ValidMoves[SaveCounter][1]=movefrom[0];
                    	    			ValidMoves[SaveCounter][2]=movefrom[1];
                    	    			ValidMoves[SaveCounter][3]=xDestination;
                    	    			ValidMoves[SaveCounter][4]=yDestination;
                    	    			ValidMoves[SaveCounter][5]=KillCounter;
                    	    			ValidMoves[SaveCounter][6]=Kill1x;
                    	    			ValidMoves[SaveCounter][7]=Kill1y;
                    	    			ValidMoves[SaveCounter][8]=Kill2x;
                    	    			ValidMoves[SaveCounter][9]=Kill2y;
                    	    			ValidMoves[SaveCounter][10]=Kill3x;
                    	    			ValidMoves[SaveCounter][11]=Kill3y;
                    	    			
                    	    			SaveCounter++;
            	    						
            	    				}
            	    				
            	    				else{
            	    					
            	    	    			SaveCounter++;
            	    					//DO NOTHING AND TRY TO MOVE TO RIGHT.
            	    				}
		
            	    			}
            	    						
    	    				}
    	    					
    	    				//2nd attack though right failed
    	    				else{
    	    					
    	    	    			SaveCounter++;
    	    					//DO NOTHING AND TRY TO MOVE TO RIGHT.
    	    					
    	    				}
    	    				
    	    			}
    	    				
				}
				
				//1st Right piece cannot be attacked because it is friendly or blocked.
				else{
					
					//DO NOTHING AND END
				}
				
			}
			// Move Right 1 Attempts ends
	    	
			//King Reverse Mode Starts.

			if (king==1 && kingloop==1){
				
				int[][] result = new int[20][13];
				
				result = validMove(movefrom,board,team,0);
				
				int counter=0;
				int flag = 0;
				
				
				while (flag==0){
					
					if (result[counter][0]==1){
							
						
    	    			ValidMoves[SaveCounter][0]=result[counter][0];
    	    			ValidMoves[SaveCounter][1]=result[counter][1];
    	    			ValidMoves[SaveCounter][2]=result[counter][2];
    	    			ValidMoves[SaveCounter][3]=result[counter][3];
    	    			ValidMoves[SaveCounter][4]=result[counter][4];
    	    			ValidMoves[SaveCounter][5]=result[counter][5];
    	    			ValidMoves[SaveCounter][6]=result[counter][6];
    	    			ValidMoves[SaveCounter][7]=result[counter][7];
    	    			ValidMoves[SaveCounter][8]=result[counter][8];
    	    			ValidMoves[SaveCounter][9]=result[counter][9];
    	    			ValidMoves[SaveCounter][10]=result[counter][10];
    	    			ValidMoves[SaveCounter][11]=result[counter][11];
						
    	    			SaveCounter++;
    	    			counter++;

					}
					
					else{
						
						flag=1;
						
					}

				}
	
			}
				
			//King Reverse Mode Ends

			return ValidMoves;
	    }
	    
	    public void PerformMove(int[] MoveData,char team,char[][] board){
	    	
	    	int [] SelectedMove = new int [11];
	    	
	    	SelectedMove = MoveData;

		    char OriginalPiece = board[SelectedMove[0]][SelectedMove[1]];
		     
		    board[SelectedMove[0]][SelectedMove[1]]='_';
		    board[SelectedMove[2]][SelectedMove[3]]=OriginalPiece;
	       
		    if (SelectedMove[4]==1){
	    	   
		    	   board[SelectedMove[5]][SelectedMove[6]]='_';
		    	   
		    	   if (OriginalPiece=='a' || OriginalPiece=='A'){
		    		   
		    		   //BCheckers--;
		    	   }
		    	   
		    	   if (OriginalPiece=='b' || OriginalPiece=='B'){
		    		   //ACheckers--;
		    	   }

		    }
	       
		    if (SelectedMove[4]==2){
	    	   
		    	   board[SelectedMove[5]][SelectedMove[6]]='_';
		    	   board[SelectedMove[7]][SelectedMove[8]]='_';
		    	   
		    	   if (OriginalPiece=='a' || OriginalPiece=='A'){
		    		   
		    		   //BCheckers--;
		    		   //BCheckers--;
		    	   }
		    	   
		    	   if (OriginalPiece=='b' || OriginalPiece=='B'){
		    		   //ACheckers--;
		    		   //ACheckers--;
		    	   }
	    	   
		    }
	    	
	       
		    if (SelectedMove[4]==3){
	    	   
		    	   board[SelectedMove[5]][SelectedMove[6]]='_';
		    	   board[SelectedMove[7]][SelectedMove[8]]='_';
		    	   board[SelectedMove[9]][SelectedMove[10]]='_';
		    	   
		    	   if (OriginalPiece=='a' || OriginalPiece=='A'){
		    		   
		    		   //BCheckers--;
		    		   //BCheckers--;
		    		   //BCheckers--;
		    	   }
		    	   
		    	   if (OriginalPiece=='b' || OriginalPiece=='B'){
		    		   //ACheckers--;
		    		  // ACheckers--;
		    		   //ACheckers--;
		    	   }
	    	   
		    }
	       
	       //King Promotion.
	       
	       if (board[0][0]=='b'){
	    	   board[0][0]='B';
	       }
	       
	       if (board[2][0]=='b'){
	    	   board[2][0]='B';
	       }
	       
	       if (board[4][0]=='b'){
	    	   board[4][0]='B';
	       }
	       
	       if (board[6][0]=='b'){
	    	   board[6][0]='B';
	       }
	       
	       if(board[1][7]=='a'){
	    	   board[1][7]='A';
	       }
	       
	       if(board[3][7]=='a'){
	    	   board[3][7]='A';
	       }
	       
	       if(board[5][7]=='a'){
	    	   board[5][7]='A';
	       }
	       
	       if(board[7][7]=='a'){
	    	   board[7][7]='A';
	       }
	       
	       //King Promotion Ends.

	    }

	    public int[] Minimax(int[] MoveData,char[][] OriginalBoard,int MoveNumber){
	    	
	    	char [][] myInt = new char[OriginalBoard.length][];
	    	for(int i = 0; i < OriginalBoard.length; i++)
	    	    myInt[i] = OriginalBoard[i].clone();
			
//	    	System.out.println("");
	    	
	    	char FriendlyMove=whosemove;
	    	char EnemyMove;
	    	
		 	int NumberOfFriendly=0;
		 	int NumberOfEnemy=0;
		 	
		 	int TieBreakerScore=0;
		 	
		 	char Friend1;
		 	char Friend2;
		 	char Enemy1='x';
		 	char Enemy2='x';
		 	
		 	
		 	if (FriendlyMove=='a'){
		 		
		 		EnemyMove='b';
		 		Friend1='a';
		 		Friend2='A';
		 		
		 		Enemy1='b';
		 		Enemy2='B';
		 		
		 	}
		 	
		 	else{
		 		
		 		EnemyMove='a';
		 		Friend1='b';
		 		Friend2='B';
		 		
		 		Enemy1='a';
		 		Enemy2='A';
		 		
		 	}
	    	
	    	PerformMove(MoveData,whosemove,myInt);
	    	
		 	int i,j;
		 	
		 	// Calculate Normal Score: 3 for regular, 5 for King.

		 	int y = 0;
		 	
		 	while (y!=8){
		 		
		 		int x = 0;
		 		
		 		while(x!=8){
		 			
		 			if(myInt[x][y]==Friend1 || myInt[x][y]==Friend2 ){
		 				
		 				if(myInt[x][y]==Friend1){
		 					
		 					NumberOfFriendly=NumberOfFriendly+3;
		 				}
		 				
		 				
		 				if(myInt[x][y]==Friend2){
		 					
		 					NumberOfFriendly=NumberOfFriendly+5;
		 				}

		 				
		 			}
		 			
		 			
		 			if(myInt[x][y]!=Friend1 && myInt[x][y]!=Friend2 &&  myInt[x][y]!='_'){
		 				
		 				if(myInt[x][y]==Enemy1){
		 					
		 					NumberOfEnemy=NumberOfEnemy+3;
		 				}
		 				
		 				if(myInt[x][y]==Enemy2){
		 					
		 					NumberOfEnemy=NumberOfEnemy+5;
		 				}
		 				
		 			}
		 			
		 			x++;
		 		}
		 		
		 		y++;
		 		
		 	}
		 	
			
			//Add Tie Breaker
		 	
		 	TieBreakerScore=ScoringBoard[MoveData[2]][MoveData[3]];
		 	
		 	//Final Piece Score
		 	
		 	int FinalScore= NumberOfFriendly-NumberOfEnemy;

//	    	System.out.println("     0 1 2 3 4 5 6 7     [MiniMax]  ["+FinalScore+"] Scores("+whosemove+") = "+NumberOfFriendly+"(Friend)  "+NumberOfEnemy+"(Enemy) Tie BreakerScore: "+ScoringBoard[MoveData[2]][MoveData[3]]);
//	    	
//	    	for (i=0;i<SIZE;i++) {
//	    		
//	    	    System.out.print("   "+(i) + " ");
//	    	    for (j=0;j<SIZE;j++) {
//	    		System.out.print(myInt[j][i] + " ");
//	    	    }
//	    	    System.out.println();
//	    	}
//	    	
//	    	System.out.println("   \n[Y-Axis]");
//	    	
//	    	System.out.println("");
	    	
	    	char [][] TemporaryBoard2 = new char[myInt.length][];
	    	for(i = 0; i < myInt.length; i++)
	    		TemporaryBoard2[i] = myInt[i].clone();    	
	    	
	    	int Temporary[] = new int[3];
	    	
	    	Temporary = ListPossibleMoveMiniMax(TemporaryBoard2,EnemyMove,MoveNumber,TieBreakerScore);
	    	
		 	int[] Result = new int[3];
		 	

		 	Result[0]=Temporary[0];
		 	Result[1]=Temporary[1];
		 	
		 	return Result;
	    		
	    	
	    }
	    
	    public int[] ListPossibleMoveMiniMax(char[][] board,char team,int MoveNumber,int TieBreakerScore){
	    	
		 	int[][] Level2Node = new int [50][4];
		 	
		 	int Level2NodeCounter=0;
	    	
	    	int[][] MiniMaxEvaluate = new int[30][12];
	    	
	    	int i,j;
	    	
	    	int LBeta1=-1000;
	    	int LBeta2=-1000;
	    	int LBetaOwner=0;
	    	
	    	char kingTeam='X';
	    	
	    	if (team=='a'){
	    		kingTeam='A';
	    	}
	    	if (team=='b'){
	    		kingTeam='B';
	    	}
	    	
	    	int[] Coordinate = new int[2];
	    	int[][] ValidMoves = new int[40][12];
	    	
	    	int NumberOfMoves=0;
	    	
	    	int ValidMovesCounter = 0;
	    	
//	    	System.out.println("All possible move for Team ["+team+"] are: ");
//	    	System.out.println();
	    	
	    	int MaxKill=0;
	    	
	    	
	    	for (i=0;i<SIZE;i++) {
    			
    			for (j=0;j<SIZE;j++) {
    				
    				if(board[j][i]==team || board[j][i]==kingTeam){
    					
    					Coordinate[0]=j;
    					Coordinate[1]=i;
    					
    					char checkteam;
    					
    					if(board[j][i]==kingTeam ){

    						checkteam=kingTeam;
    					}
    					else{
    						checkteam=team;
    					}
    					
    					int[][] result = validMove(Coordinate,board,checkteam,1);
    					
    					int counter = 0;
    					
    			    	while (counter<30 && result[counter][0]==1 ){
    			    		
    			    		if(result[counter][5]>MaxKill){
    			    			
    			    			MaxKill=result[counter][5];
    			    		}
    			    		
    			    		counter++;
    			    	}
	    	
    				}
    			}
	    	}
	    		    	
	    	for (i=0;i<SIZE;i++) {
	    				    			
	    			for (j=0;j<SIZE;j++) {
	    				
	    				if(board[j][i]==team || board[j][i]==kingTeam){
	    					
	    					Coordinate[0]=j;
	    					Coordinate[1]=i;
	    					
	    					char checkteam;
	    					
	    					if(board[j][i]==kingTeam ){

	    						checkteam=kingTeam;
	    					}
	    					
	    					else{
	    						checkteam=team;
	    					}
	    					
	    					int[][] result = validMove(Coordinate,board,checkteam,1);
	    					
	    					int counter = 0;
	    					
	    			    	while (counter<30 && result[counter][0]==1){
	    			    		
	    			    		if (result[counter][5]==MaxKill){
	    			  	
//	    			    			
//	    			    		System.out.println(
//	    			    				"                        "+MoveNumber+"."+(ValidMovesCounter+1)+") "+"Move From: ["+(result[counter][1])+(result[counter][2])+"] "+
//	    			    				"Move Too: ["+(result[counter][3])+(result[counter][4])+"] "+
//	    			    				"Will Jump (Kill) Enemy: ["+(result[counter][5])+"] "+(CounterGlobal+1) 
//	    			    					
//	    			    						);
	    			    		
	    			    		
	    			    		ValidMoves[ValidMovesCounter][0]=result[counter][1];
	    			    		ValidMoves[ValidMovesCounter][1]=result[counter][2];
	    			    		ValidMoves[ValidMovesCounter][2]=result[counter][3];
	    			    		ValidMoves[ValidMovesCounter][3]=result[counter][4];
	    			    		ValidMoves[ValidMovesCounter][4]=result[counter][5];
	    			    		ValidMoves[ValidMovesCounter][5]=result[counter][6];
	    			    		ValidMoves[ValidMovesCounter][6]=result[counter][7];
	    			    		ValidMoves[ValidMovesCounter][7]=result[counter][8];
	    			    		ValidMoves[ValidMovesCounter][8]=result[counter][9];
	    			    		ValidMoves[ValidMovesCounter][9]=result[counter][10];
	    			    		ValidMoves[ValidMovesCounter][10]=result[counter][11];
	    			    		
	    			    		int[] PotentialMoves = new int[11];
	    			    		
	    		    			PotentialMoves[0]=ValidMoves[ValidMovesCounter][0];
	    		    			PotentialMoves[1]=ValidMoves[ValidMovesCounter][1];
	    		    			PotentialMoves[2]=ValidMoves[ValidMovesCounter][2];
	    		    			PotentialMoves[3]=ValidMoves[ValidMovesCounter][3];
	    		    			PotentialMoves[4]=ValidMoves[ValidMovesCounter][4];
	    		    			PotentialMoves[5]=ValidMoves[ValidMovesCounter][5];
	    		    			PotentialMoves[6]=ValidMoves[ValidMovesCounter][6];
	    		    			PotentialMoves[7]=ValidMoves[ValidMovesCounter][7];
	    		    			PotentialMoves[8]=ValidMoves[ValidMovesCounter][8];
	    		    			PotentialMoves[9]=ValidMoves[ValidMovesCounter][9];
	    		    			PotentialMoves[10]=ValidMoves[ValidMovesCounter][10];
	    			    		
	    		    			int[] Result = new int[2];
	    		    			
	    		    			int MoveNumber2 = ValidMovesCounter+1;
	    		    			
	    			    		Result=Minimax2(PotentialMoves,board,MoveNumber,MoveNumber2);
	    			    		
	    			    		LBeta1=Result[5];
	    			    		LBeta2=Result[6];
	    			    		LBetaOwner=Result[4];
	    			    		   			    		
//	    			    		System.out.println("                        "+"Final(2): "+MiniMaxEvaluate[ValidMovesCounter][0]);
//	    			    		System.out.println("                        "+"Tie(2): "+MiniMaxEvaluate[ValidMovesCounter][1]);
//	    			    		System.out.println("");
	    			    		
	    			    		
	    			    		//MiniMaxEvaluation Level3
	    			    		
	    			    		Level2Node[Level2NodeCounter][0]=1;
	    			    		Level2Node[Level2NodeCounter][1]=Result[2];
	    			    		Level2Node[Level2NodeCounter][2]=Result[3];
	    			    		
	    			    		Level2NodeCounter++;
	    			    		
	    			    		//MiniMaxEvaluation Level3 Ends
	    			    			
	    			    		NumberOfMoves++;
	    			    		ValidMovesCounter++;

	    			    		}
	    			    		counter++;
	    			    		
	    			    	}
	    			    		
	    				}
	    				
	    			}
	    			
	    	}
	    	
	    	//Evaluate Level2
	    	
	    	int Min1=1000;
	    	int Min2=1000;
	    	
	    	int x = 0;
	    	
	    	while(Level2Node[x][0]!=0){
	    		
	    		MiniMaxSteps++;
	    		
//	    		System.out.println("("+MiniMaxSteps+") Evaluating Level 2 "+MoveNumber+"."+(x+1) +" "+Level2Node[x][1]+" "+Level2Node[x][2] );
	    		
	    		
	    		if(Level2Node[x][1]<Min1){
	    			
	    			Min1=Level2Node[x][1];
	    			Min2=Level2Node[x][2];
	    		}
	    		
	    		if(Level2Node[x][1]==Min1 && Level2Node[x][2]<Min2){
	    			
	    			Min1=Level2Node[x][1];
	    			Min2=Level2Node[x][2];
	    		}
	    		
	    		if(Level2Node[x][1]==Min1 && Level2Node[x][2]==Min2){
	    				
	    				Min1=Level2Node[x][1];
		    			Min2=Level2Node[x][2];
	    				
	    		}
	    		
	    		
	    		x++;
	    	}
	    	
//	    	System.out.println("Level 2 Minimum: ("+Min1+","+Min2+")");
	    	
	    	int[] BestMove = new int[2];
	    	BestMove[0] = Min1;
	    	BestMove[1] = Min2;
	    	
//	    	System.out.println();
	    	
//	    	System.out.println("Beta for Node: "+LBetaOwner+" || "+LBeta1+"."+LBeta2);
	    	
	    	if (LBeta1>Beta1 || (LBeta1==Beta1 && LBeta2>Beta2) || (LBeta1==Beta1 && LBeta2==Beta2) ){
		    	
		    	Beta1=LBeta1;
		    	Beta2=LBeta2;
		    	BetaOwner=LBetaOwner;
	    	}
	    	
//	    	System.out.println("Active Beta: "+BetaOwner+" || "+Beta1+"."+Beta2);
	    	
//	    	System.out.println();
//	    	
//	    	System.out.println();
	    		    	
//	    	System.out.println("                        "+MoveNumber+" Number of Possible Moves: "+NumberOfMoves+" Selected Move(Min)= "+BestMove[0]+ " "+BestMove[1]);
//	    			
//	    	System.out.println("                        __________________________________________________________________");	
	    	
	    	return BestMove;
	
	    }

	    public int[] Minimax2(int[] MoveData,char[][] OriginalBoard,int MoveNumber,int MoveNumber2){
	 	
	 	char [][] myInt2 = new char[OriginalBoard.length][];
	 	for(int i = 0; i < OriginalBoard.length; i++)
	 	    myInt2[i] = OriginalBoard[i].clone();
			
//	 	System.out.println("");
	 		
	 	char FriendlyMove=whosemove;
	 	char EnemyMove;
	 	
	 	int NumberOfFriendly=0;
	 	int NumberOfEnemy=0;
	 	
	 	int TieBreakerScore=0;
	 	
	 	char Friend1;
	 	char Friend2;
	 	char Enemy1='x';
	 	char Enemy2='x';
	 	
	 	
	 	if (FriendlyMove=='a'){
	 		
	 		EnemyMove='a';
	 		Friend1='a';
	 		Friend2='A';
	 		
	 		Enemy1='b';
	 		Enemy2='B';
	 		
	 	}
	 	
	 	else{
	 		
	 		EnemyMove='b';
	 		Friend1='b';
	 		Friend2='B';
	 		
	 		Enemy1='a';
	 		Enemy2='A';
	 	}
	 	

	 	PerformMove(MoveData,whosemove,myInt2);
	 	int i,j;
	 	
	 	// Add

	 	int y = 0;
	 	
	 	while (y!=8){
	 		
	 		int x = 0;
	 		
	 		while(x!=8){
	 			
	 			if(myInt2[x][y]==Friend1 || myInt2[x][y]==Friend2 ){
	 				
	 				if(myInt2[x][y]==Friend1){
	 					
	 					NumberOfFriendly=NumberOfFriendly+3;
	 				}
	 				
	 				
	 				if(myInt2[x][y]==Friend2){
	 					
	 					NumberOfFriendly=NumberOfFriendly+5;
	 				}

	 				
	 			}
	 			
	 			
	 			if(myInt2[x][y]!=Friend1 && myInt2[x][y]!=Friend2 &&  myInt2[x][y]!='_'){
	 				
	 				if(myInt2[x][y]==Enemy1){
	 					
	 					NumberOfEnemy=NumberOfEnemy+3;
	 				}
	 				
	 				if(myInt2[x][y]==Enemy2){
	 					
	 					NumberOfEnemy=NumberOfEnemy+5;
	 				}
	 				
	 			}
	 			
	 			x++;
	 		}
	 		
	 		y++;
	 		
	 	}
	 	
	 	//Final Piece Score
	 	
	 	int FinalScore= NumberOfFriendly-NumberOfEnemy;
		
		//Add Tie Breaker
	 	
	 	TieBreakerScore=ScoringBoard[MoveData[2]][MoveData[3]];
	 	
	 	//Add Tie Breaker

//	 	System.out.println("                           0 1 2 3 4 5 6 7     [MiniMax2] ["+FinalScore+"] Scores("+whosemove+") = "+NumberOfFriendly+"(Friend)  "+NumberOfEnemy+"(Enemy) Tie BreakerScore: "+ScoringBoard[MoveData[2]][MoveData[3]]);
//	 	
//
//	 	for (i=0;i<SIZE;i++) {
//	 		
//	 	    System.out.print("                         "+(i) + " ");
//	 	    for (j=0;j<SIZE;j++) {
//	 		System.out.print(myInt2[j][i] + " ");
//	 	    }
//	 	    System.out.println();
//	 	    
//	 	}
	 	
//	 	System.out.println("");
	 	
	 	char [][] TemporaryBoard3 = new char[myInt2.length][];
	 	for(i = 0; i < myInt2.length; i++)
	 		TemporaryBoard3[i] = myInt2[i].clone();    	
	 	
	 	int Temporary[] = new int[5];
	 	
	 	Temporary = ListPossibleMoveMiniMax2(TemporaryBoard3,EnemyMove,MoveNumber,MoveNumber2,TieBreakerScore);
	 	
	// 	System.out.println("Beta for Node: "+Temporary[2]+" || "+Temporary[3]+","+Temporary[4]);
	 	
//    	BestMove[2] = MoveNumber;
//    	BestMove[3] = Alpha;
//    	BestMove[4] = Alpha2;
	
//	 	System.out.println("                        MiniMax2");
	 	
	 	int[] Result = new int[7];
	 	
	 	Result[0]=FinalScore;
	 	Result[1]=TieBreakerScore;
	 	Result[2]=Temporary[0];
	 	Result[3]=Temporary[1];
	 	Result[4]=Temporary[2];
	 	Result[5]=Temporary[3];
	 	Result[6]=Temporary[4];
	 	
	 	return Result;
	 	
	 }
	    
	    public int[] ListPossibleMoveMiniMax2(char[][] board,char team,int MoveNumber,int MoveNumber2,int TieBreakerScore){
	    	
	    	int[][] Level3Node = new int[50][3];
	    	
	    	int Level3NodeCounter=0;
	    	
	    	int[][] MiniMaxEvaluate = new int[30][12];
	    	
	    	int i,j;
	    	
	    	char kingTeam='X';
	    	
	    	if (team=='a'){
	    		kingTeam='A';
	    	}
	    	if (team=='b'){
	    		kingTeam='B';
	    	}
	    	
	    	int[] Coordinate = new int[2];
	    	int[][] ValidMoves = new int[40][12];
	    	
	    	int NumberOfMoves=0;
	    	
	    	int ValidMovesCounter = 0;
	    	
//	    	System.out.println("                                                    "+"All possible move for Team ["+team+"] 2nd Turn are: ");
//	    	System.out.println();
	    	
	    	int MaxKill=0;
	    	
	    	
	    	for (i=0;i<SIZE;i++) {
    			
    			for (j=0;j<SIZE;j++) {
    				
    				if(board[j][i]==team || board[j][i]==kingTeam){
    					
    					Coordinate[0]=j;
    					Coordinate[1]=i;
    					
    					char checkteam;
    					
    					if(board[j][i]==kingTeam ){

    						checkteam=kingTeam;
    					}
    					else{
    						checkteam=team;
    					}
    					
    					int[][] result = validMove(Coordinate,board,checkteam,1);
    					
    					int counter = 0;
    					
    			    	while (counter<30 && result[counter][0]==1 ){
    			    		
    			    		if(result[counter][5]>MaxKill){
    			    			
    			    			MaxKill=result[counter][5];
    			    		}
    			    		
    			    		counter++;
    			    	}
	    	
    				}
    			}
	    	}
	    		    	
	    	for (i=0;i<SIZE;i++) {
	    				    			
	    			for (j=0;j<SIZE;j++) {
	    				
	    				if(board[j][i]==team || board[j][i]==kingTeam){
	    					
	    					Coordinate[0]=j;
	    					Coordinate[1]=i;
	    					
	    					char checkteam;
	    					
	    					if(board[j][i]==kingTeam ){

	    						checkteam=kingTeam;
	    					}
	    					else{
	    						checkteam=team;
	    					}
	    					
	    					int[][] result = validMove(Coordinate,board,checkteam,1);
	    					
	    					int counter = 0;
	    					
	    			    	while (counter<30 && result[counter][0]==1){
	    			    		 		    		
	    			    		if (result[counter][5]==MaxKill){
	
//	    			    		System.out.println(
//	    			    				"                                                    "+MoveNumber+"."+MoveNumber2+"."+(ValidMovesCounter+1)+") "+"Move From: ["+(result[counter][1])+(result[counter][2])+"] "+
//	    			    				"Move Too: ["+(result[counter][3])+(result[counter][4])+"] "+
//	    			    				"Will Jump (Kill) Enemy: ["+(result[counter][5])+"] "+(CounterGlobal+1) 
//	    			    					
//	    			    						);

	    			    		ValidMoves[ValidMovesCounter][0]=result[counter][1];
	    			    		ValidMoves[ValidMovesCounter][1]=result[counter][2];
	    			    		ValidMoves[ValidMovesCounter][2]=result[counter][3];
	    			    		ValidMoves[ValidMovesCounter][3]=result[counter][4];
	    			    		ValidMoves[ValidMovesCounter][4]=result[counter][5];
	    			    		ValidMoves[ValidMovesCounter][5]=result[counter][6];
	    			    		ValidMoves[ValidMovesCounter][6]=result[counter][7];
	    			    		ValidMoves[ValidMovesCounter][7]=result[counter][8];
	    			    		ValidMoves[ValidMovesCounter][8]=result[counter][9];
	    			    		ValidMoves[ValidMovesCounter][9]=result[counter][10];
	    			    		ValidMoves[ValidMovesCounter][10]=result[counter][11];
	    			    		
	    			    		int[] PotentialMoves = new int[11];
	    			    		
	    		    			PotentialMoves[0]=ValidMoves[ValidMovesCounter][0];
	    		    			PotentialMoves[1]=ValidMoves[ValidMovesCounter][1];
	    		    			PotentialMoves[2]=ValidMoves[ValidMovesCounter][2];
	    		    			PotentialMoves[3]=ValidMoves[ValidMovesCounter][3];
	    		    			PotentialMoves[4]=ValidMoves[ValidMovesCounter][4];
	    		    			PotentialMoves[5]=ValidMoves[ValidMovesCounter][5];
	    		    			PotentialMoves[6]=ValidMoves[ValidMovesCounter][6];
	    		    			PotentialMoves[7]=ValidMoves[ValidMovesCounter][7];
	    		    			PotentialMoves[8]=ValidMoves[ValidMovesCounter][8];
	    		    			PotentialMoves[9]=ValidMoves[ValidMovesCounter][9];
	    		    			PotentialMoves[10]=ValidMoves[ValidMovesCounter][10];
	    			    		
	    		    			int[] Result = new int[2];
	    		    			
	    			    		Result=Minimax3(PotentialMoves,board);
	    			    		
	    			    		//Tie Breaker.
	    			    		
	    			    		
	    			    		//Tie Breaker
	    			    		
	    			    		MiniMaxEvaluate[ValidMovesCounter][0]=Result[0];
	    			    		MiniMaxEvaluate[ValidMovesCounter][1]=Result[1];
	    			    		
//	    			    		System.out.println("                                                    "+"Final(2): "+MiniMaxEvaluate[ValidMovesCounter][0]);
//	    			    		System.out.println("                                                    "+"Tie(2): "+MiniMaxEvaluate[ValidMovesCounter][1]);
//	    			    		System.out.println("");
	    			    					    		
//	    			    		EvaluationMinMax[CounterGlobal][0]= MoveNumber;
//	    			    		EvaluationMinMax[CounterGlobal][1]= MoveNumber2;
//	    			    		EvaluationMinMax[CounterGlobal][2]= ValidMovesCounter+1;
//	    			    		EvaluationMinMax[CounterGlobal][3]= MiniMaxEvaluate[ValidMovesCounter][0];
//	    			    		EvaluationMinMax[CounterGlobal][4]= Result[1];
	    			    		
	    			    		//MiniMaxEvaluation Level3
	    			    		
	    			    		Level3Node[Level3NodeCounter][0]=1;
	    			    		Level3Node[Level3NodeCounter][1]=Result[0];
	    			    		Level3Node[Level3NodeCounter][2]=Result[1];
	    			    		
	    			    		
	    			    		Level3NodeCounter++;
	    			    		
	    			    		//MiniMaxEvaluation Level3 Ends
	    			    		
	    			    		CounterGlobal++;
	    			    		NumberOfMoves++;
	    			    		ValidMovesCounter++;

	    			    		}
	    			    		counter++;
	    			    		
	    			    	}
	    			    		
	    				}
	    			}
	    			

	    	}
	    	
	    	//Evaluate Level3 MiniMax
	    	
	    	int Max1=-1000;
	    	int Max2=-1000;
	    	
	    	int x = 0;
	    	
	    	while(Level3Node[x][0]!=0){
	    		
	    		MiniMaxSteps++;
	    		
//	    		System.out.println("("+MiniMaxSteps+") Evaluating Level 3 "+MoveNumber+"."+MoveNumber2+"."+(x+1)+" "+Level3Node[x][1]+" "+Level3Node[x][2]);
	    		
	    		if(Level3Node[x][1]>Max1){
	    			
	    			//System.out.println("1");
	    			
	    			Max1=Level3Node[x][1];
	    			Max2=Level3Node[x][2];
	    		}
	    		
	    		else {
	    			
		    		if(Level3Node[x][1]==Max1 && Level3Node[x][2]>Max2){
		    			
		    			//System.out.println("2");
		    			
		    			Max1=Level3Node[x][1];
		    			Max2=Level3Node[x][2];
		    		}
	    			
		    		if(Level3Node[x][1]==Max1 && Level3Node[x][2]==Max2){
		    			
		    			//if(getRandomBoolean()==true){
		    				
		    				//System.out.println("3");
		    				
			    			Max1=Level3Node[x][1];
			    			Max2=Level3Node[x][2];
		    				
		    			//}
		    			
		    		}
	    			
	    		}
	    		
	    		x++;
	    	}
	    	
//	    	System.out.println("Level 3 Maximum: ("+Max1+","+Max2+")");
	    	
	    	//Evaluate Level3 MiniMax Ends
	    	
	    	//Evaluate Level3 AlphaBeta
	    	
	    	x=0;
	    	
	    	int flag =0;
	    	
	    	int LocalA1=-1000;
	    	int LocalA2=-1000;
	    	
	    	int[] LocalMax = new int[2];
	    	    	
	    	int[][] Compare = new int[50][3];
	    	
	    	int CCounter=0;
	    	
//	    	System.out.println(" ");
	    		
	    	while(Level3Node[x][0]!=0 && flag==0){
	    		
	    		if(MoveNumber2==1 && (x+1)==1){
	    			
//	    			System.out.println("Alpha Value Reset (1st Child of Node)");
//	    			System.out.println(" ");
	    			
	    		    Alpha = -1000;
	    		    Alpha2 = -1000;
	    		    BetaFlag=0;
	    			
	    		}
	    		
	    		
	    		if(MoveNumber2==1  ){
	    			
	    			
	    			AlphaBetaSteps++;
	    			
//	    			System.out.print("("+AlphaBetaSteps+") Alpha Beta First Child Node "+MoveNumber+"."+MoveNumber2+"."+(x+1)+" "+Alpha+" "+Alpha2);	
//	    			System.out.println(" ("+Level3Node[x][1]+"."+Level3Node[x][2]+")");
	    			
//	    	    	System.out.println("Test A1: "+Alpha);
//	    	    	System.out.println("Test A2: "+Alpha2);
//	    	    	System.out.println("AB Probing"+(x+1)+" "+Level3Node[x][1]+" / "+Level3Node[x][2]);	
//	    			
//	    			System.out.println("AB ANALYZE");
	    			
		    		if(Level3Node[x][1]>Alpha){
		    			
		    			Alpha=Level3Node[x][1];
		    			Alpha2=Level3Node[x][2];
		    			
		    			
		    			//AlphaBetaSteps++;
		    		}
		    		
		    		if(Level3Node[x][1]==Alpha && Level3Node[x][2]>Alpha2){
		    			
		    			Alpha=Level3Node[x][1];
		    			Alpha2=Level3Node[x][2];
		    			
		    			
		    			//AlphaBetaSteps++;
		    		}
		    		
		    		if(Level3Node[x][1]==Alpha && Level3Node[x][2]==Alpha2){
		    			
		    			//if(getRandomBoolean()==true){
		    				
			    			Alpha=Level3Node[x][1];
			    			Alpha2=Level3Node[x][2];
;
			    			

		    				
		    			//}
		    			
		    		}
	    			
		    		
		    		
	    			}
	    		
	    		
	    		else{
	    			
	    			
	    			
	    			LocalA1=Alpha;
	    			LocalA2=Alpha2;
	    			
	    			
	    			LocalMax[0]=Level3Node[x][1];
	    			LocalMax[1]=Level3Node[x][2];
	    			
	    			
	    			if (BetaFlag==0){
	    			
	    			if ((Level3Node[x][1]>Alpha && Level3Node[x][2]>Alpha2) || Level3Node[x][1]>Alpha || (Level3Node[x][1]==Alpha && Level3Node[x][2]>Alpha2 ) || (Level3Node[x][1]==Alpha && Level3Node[x][2]==Alpha2 )){
	    					
	
	    				AlphaBetaSteps++;
//	    				System.out.print("("+AlphaBetaSteps+") Alpha Beta Probing Rejected "+MoveNumber+"."+MoveNumber2+"."+(x+1)+" -- Will not resume probing.");	
//	    				System.out.println(" ("+Level3Node[x][1]+"."+Level3Node[x][2]+")");
	    				flag=1;
	    				
	    				
	    				
	    			}
	    			
	    			else {
	    				
	    				AlphaBetaSteps++;
//	    				System.out.print("("+AlphaBetaSteps+") Alpha Beta Probing Node "+MoveNumber+"."+MoveNumber2+"."+(x+1)+" ");
	    				
	    				if (Level3Node[x][1]<LocalMax[0] || (Level3Node[x][1]==LocalMax[0] && Level3Node[x][2]==LocalMax[1])){
	    					
//	    					System.out.println("Valid Value and will search next: ("+Level3Node[x][1]+"."+Level3Node[x][2]+")");		
	    	    			LocalMax[0]=Level3Node[x][1];
	    	    			LocalMax[1]=Level3Node[x][2];
	    	    			
	    	    			Compare[CCounter][0]=Level3Node[x][1];
	    	    			Compare[CCounter][1]=Level3Node[x][2];
	    	    			Compare[CCounter][2]=1;
	    	    			
	    	    			CCounter++;
	    	    			
	    					
	    				}
	    				
	    				
	    				
	    				if(Level3Node[x][1]<LocalA1 || (Level3Node[x][1]==LocalA1 && Level3Node[x][2]>LocalA2)){
	    					
//	    					System.out.println("Valid Value and will search next: ("+Level3Node[x][1]+"."+Level3Node[x][2]+")");
	    					
	    	    			Compare[CCounter][0]=Level3Node[x][1];
	    	    			Compare[CCounter][1]=Level3Node[x][2];
	    	    			Compare[CCounter][2]=1;
	    	    			
	    	    			CCounter++;
    				
	    				}
	    					
	    			}
	    			
	    		}
	    			
	    	}
	    		
	    		x++;
	    }

	    	
	    	if(flag==0 && MoveNumber2!=1 && BetaFlag==0){
	    		
	    		int result[] = GetMax(Compare);
	    			
		    		Alpha=result[0];
		    		Alpha2=result[1];
		    		
	    		
	    		for( i = 0; i < Compare.length; i++ )
					   Arrays.fill( Compare[i], 0 );
	    		

    		
    	}
	    	
//	    	System.out.println("Alpha Final: "+Alpha);
//	    	System.out.println("Alpha2 Final: "+Alpha2);
	    	
	    	if (((Alpha<Beta1 || (Alpha==Beta1 && Alpha2<Beta2) || (Alpha==Beta1 && Alpha2==Beta2 ))&& MoveNumber!=1)){
	    		

//	    		System.out.println("[Alert!]: Next Child will not be probed ! ["+Alpha+"."+Alpha2+"]");
	    		BetaFlag=1;
	    		
	    	}
	    	
	
    		LocalA1=-1000;
	    	LocalA2=-1000;
	    	

	    	//EvaluateLevel3 AlphaBeta ends
	    	
	    	
//	    	System.out.println("MoveNumber: "+MoveNumber);
	    	
	    	if((ABcounter+1)==MoveNumber){
	    		
	    		//System.out.println("ADD");
	    		
	    		if (Alpha2!=0){
	    		
	    		AB[ABcounter][0]=MoveNumber;
	    		AB[ABcounter][1]=Alpha;		
	    		AB[ABcounter][2]=Alpha2;
	    		
	    		}
	    	}
	    	
	    	else {
	    		
	    		//System.out.println("NO ADD");
	    		
	    		ABcounter++;
	    		
	    		AB[ABcounter][0]=MoveNumber;
	    		AB[ABcounter][1]=Alpha;		
	    		AB[ABcounter][2]=Alpha2;
	    		
	    		
	    	}
	    	
	    	
	    	
	    	
	    	int[] BestMove = new int[5];
	    	BestMove[0] = Max1;
	    	BestMove[1] = Max2;
	    	BestMove[2] = MoveNumber;
	    	BestMove[3] = Alpha;
	    	BestMove[4] = Alpha2;
	    	
//	    	System.out.println();
//	    		    	
//	    	System.out.println("                                                   Number of Possible Moves: "+NumberOfMoves+"  Best Move is: "+BestMove[0]+" "+BestMove[1]);
//	    			
//	    	System.out.println("                                                   __________________________________________________________________");	
	    	
	    	return BestMove;
	    		
	    }

	    public int[] Minimax3(int[] MoveData,char[][] OriginalBoard){
		 	
		 	char [][] myInt = new char[OriginalBoard.length][];
		 	for(int i = 0; i < OriginalBoard.length; i++)
		 	    myInt[i] = OriginalBoard[i].clone();
				
//		 	System.out.println("");
		 	
		 	char FriendlyMove=whosemove;
		 	char EnemyMove;
		 	
		 	int NumberOfFriendly=0;
		 	int NumberOfEnemy=0;
		 	
		 	int TieBreakerScore=0;
		 	
		 	char Friend1;
		 	char Friend2;
		 	char Enemy1='x';
		 	char Enemy2='x';
		 	
		 	
		 	if (FriendlyMove=='a'){
		 		
		 		EnemyMove='b';
		 		Friend1='a';
		 		Friend2='A';
		 		
		 		Enemy1='b';
		 		Enemy2='B';
		 		
		 	}
		 	
		 	else{
		 		
		 		EnemyMove='a';
		 		Friend1='b';
		 		Friend2='B';
		 		
		 		Enemy1='a';
		 		Enemy2='A';
		 	}
		 	
		 	
		 	PerformMove(MoveData,whosemove,myInt);
		 	int i,j;
		 	
		 	// Add
			
		 	int y = 0;
		 	
		 	while (y!=8){
		 		
		 		int x = 0;
		 		
		 		while(x!=8){
		 			
		 			if(myInt[x][y]==Friend1 || myInt[x][y]==Friend2 ){
		 				
		 				
		 				if(myInt[x][y]==Friend1){
		 				
		 				NumberOfFriendly=NumberOfFriendly+3;
		 				
		 				}
		 				
		 				if(myInt[x][y]==Friend2){
			 				
		 				NumberOfFriendly=NumberOfFriendly+5;
		 				
		 				}
		 				
		 			}
		 			
		 			
		 			if(myInt[x][y]!=Friend1 && myInt[x][y]!=Friend2 &&  myInt[x][y]!='_'){
		 				
		 				if(myInt[x][y]==Enemy1){
			 				
		 					NumberOfEnemy=NumberOfEnemy+3;
		 				
		 				}
		 				
		 				if(myInt[x][y]==Enemy2){
			 				
		 					NumberOfEnemy=NumberOfEnemy+5;
		 				
		 				}
		 				
		 			}
		 			
		 			x++;
		 		}
		 		
		 		y++;
		 		
		 	}
		 	
		 	//Final Piece Score
		 	
		 	int FinalScore= NumberOfFriendly-NumberOfEnemy;
			
			//Add Tie Breaker
		 	
		 	TieBreakerScore=ScoringBoard[MoveData[2]][MoveData[3]];
		 	
		 	//Add Tie Breaker
		 	

//		 	System.out.println("                                                      0 1 2 3 4 5 6 7     [MiniMax3] ["+FinalScore+"] Scores("+whosemove+") = "+NumberOfFriendly+"(Friend)  "+NumberOfEnemy+"(Enemy) Tie BreakerScore: "+ScoringBoard[MoveData[2]][MoveData[3]]);
//		 	
//
//		 	for (i=0;i<SIZE;i++) {
//		 	    System.out.print("                                                    "+(i) + " ");
//		 	    for (j=0;j<SIZE;j++) {
//		 		System.out.print(myInt[j][i] + " ");
//		 	    }
//		 	    System.out.println();
//		 	}
		 	
		 	
//		 	System.out.println("");
		 	
		 	char [][] TemporaryBoard2 = new char[myInt.length][];
		 	for(i = 0; i < myInt.length; i++)
		 		TemporaryBoard2[i] = myInt[i].clone();    	
		 	
//		 	System.out.println("                                                    MiniMax3");
		 	
		 	int[] Result = new int[2];
		 	
		 	Result[0]=FinalScore;
		 	Result[1]=TieBreakerScore;
		 	
		 	return Result;
		 	
		 }
	 
	    public  int[] GetMax(int[][] array){
			
			int[][] compare = array;
			int[] result = new int[2];
			
			int counter = array.length;
			
			int x=0;
			
			int Max1=compare[0][0];
			int Max2=compare[0][1];
			
			while(x<counter && compare[x][2]==1){
				
				if (compare[x][0]>Max1 || (compare[x][0]==Max1 && compare[x][1]>Max2)){
					
					Max1=compare[x][0];
					Max2=compare[x][1];
					
				}
				
				x++;
				
			}
			
			
			result[0]=Max1;
			result[1]=Max2;
			
			return result;
			
			
		}
	    

}
	




	 
	    
	    
	    
	   
 
	    

	


	    

