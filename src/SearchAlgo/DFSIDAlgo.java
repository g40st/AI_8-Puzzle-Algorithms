package SearchAlgo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import Puzzle.Move;
import Puzzle.State;

public class DFSIDAlgo {
	State state;
    FileWriter fw = new FileWriter("DFSIDAlgo.txt");
    BufferedWriter bw;
    
    
    public DFSIDAlgo() throws IOException {
         bw = new BufferedWriter(fw);
         bw.write("Ausgangsknoten: ");    
    }
    
    private void addToQueue(State nextState, LinkedList<State> newQueue) {
        if(nextState != null) {
            newQueue.add(nextState);
        }
    }
    
    public void solvePuzzle(State state, State goalState) throws IOException {
        int limitDepth = 0;
        String solution;
        do{
        	bw.write("\n\nTiefenschranke: " + limitDepth + "\n");
        	solution = depthFirstSearch(state,goalState,0,limitDepth);
        	limitDepth++;
        }while(!solution.equals("Loesung gefunden")); 
    }
    
    private String depthFirstSearch(State state, State goalState, int depth, int limitDepth) throws IOException {
    	this.state = state;
    	
         // Ausgabe
         bw.write(this.state.toString());
         System.out.println(this.state);
         
         // Anzahl der Durchlaeufe
         this.state.incSteps();
         
         if(this.state.isSolved(goalState)) {
        	 System.out.println("\nLösung gefunden in Ebene " + this.state.getDepth() + " | Anzahl der Schritte: " + this.state.getSteps());
             bw.write("\n\nLösung gefunden in Ebene: " + this.state.getDepth() + " | Anzahl der Schritte: " + this.state.getSteps()); 
             bw.close();
             return "Loesung gefunden";
         }
                       
         // Nachfolger
         LinkedList<State> newQueue = new LinkedList<State>();	 
    	 this.addToQueue(Move.up(state), newQueue);
    	 this.addToQueue(Move.left(state), newQueue);
    	 this.addToQueue(Move.down(state), newQueue);
    	 this.addToQueue(Move.right(state), newQueue);   
         
         while(!newQueue.isEmpty() && depth < limitDepth) {
             String solution = depthFirstSearch(newQueue.poll(), goalState, (depth+1), limitDepth);
             if(solution.equals("Loesung gefunden")) {
                 return "Loesung gefunden";
             }
         }
         return "keine Lösung gefunden";	
    }  
}
