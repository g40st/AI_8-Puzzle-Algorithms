package Puzzle;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import SearchAlgo.BFSAlgo;
import SearchAlgo.DFSAlgo;
import SearchAlgo.DFSIDAlgo;

public class Puzzle {
    int[] puzzleInput;
    State initialState;
    Queue<State> queue = new LinkedList<State>();
    State state;
    static State goalState;
    
    static int searchAlgo = 0;
    
    public Puzzle(int[] puzzleInput) {
        this.initialState = new State(puzzleInput);
        this.state = this.initialState;
        queue.clear();
        queue.add(this.initialState);  
    }
    
    public void solve() {
        if(searchAlgo == 1) {
            BFSAlgo bfsAlgo;
            try {
                bfsAlgo = new BFSAlgo();
                bfsAlgo.solvePuzzle(queue, goalState);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(searchAlgo == 2) {
        	try {
        		DFSAlgo dfsAlgo = new DFSAlgo();
        		dfsAlgo.solvePuzzle(queue.poll(), goalState);
        	} catch (IOException e) {
                e.printStackTrace();
            }
        } else if(searchAlgo == 3) {
        	try {
        		DFSIDAlgo dfsidAlgo = new DFSIDAlgo();
        		dfsidAlgo.solvePuzzle(queue.poll(), goalState);
        	} catch (IOException e) {
                e.printStackTrace();
            }
        	
        } else {
        	System.out.println("falsche Eingabe");	
        }
    }
    
    public static void main(String[] args) {
        // Ausgangsknoten  
    	//int[] input = {1,2,3,4,5,6,7,8,0};
    	
    	int[] input = {1,2,3,4,5,6,7,0,8};
    	/* 3 Schritte */
        //int[] input = {0,2,3,1,5,6,4,7,8}; 
    	/* 17 Schritte */
        //int[] input = {1,0,3,4,2,6,7,5,8}; 
    	/* 1231 Schritte */
        //int[] input = {4,1,2,5,8,3,7,0,6};	
    	/* 2204 Schritte */
        //int[] input = {0,5,2,1,8,3,4,7,6};
    	/* xxx Schritte */
    	//int[] input = {2,8,1,0,4,3,7,6,5};
    	/* xxx Schritte */
    	//int[] input = {2,8,1,4,6,3,0,7,5};
    	/* 59 Schritte */
    	//int[] input = {0,1,3,4,2,5,7,8,6};
    	
        /*DFS 4 Schritte 4-Ebene down/right*/
        //int[] input = {0,2,3,1,5,6,4,7,8}; 
        
        /*DFSID 12 Schritte 2-Ebene*/
        //int[] input = {1,2,3,4,5,6,0,7,8};
        
        /*DFSID 57 Schritte 4-Ebene*/
        //int[] input = {0,2,3,1,5,6,4,7,8}; 
        
    	/*BFS 261828 Schritte 12-Ebene | DFSID 403678 Schritte */
        //int[] input = {1,2,3,4,5,7,8,6,0}; 
         	
        
    	/* OutOfMemoryError: GC overhead limit exceeded */
        //int[] input = {7,2,3,4,6,5,1,8,0};
       
        // Zielknoten festlegen
        int[] arrGoalState = {1,2,3,4,5,6,7,8,0};
        goalState = new State(arrGoalState);

        Puzzle puzzle = new Puzzle(input);
        Scanner scan = new Scanner(System.in);
         
        System.out.println("for breadth-first search | type: 1");
        System.out.println("for depth-first search | type: 2");
        System.out.println("for depth-first search with iterative deepening | type: 3 \n");
        System.out.print("Auswahl: ");
        searchAlgo = scan.nextInt(); 
        scan.close();
        
        puzzle.solve();
    }
}
