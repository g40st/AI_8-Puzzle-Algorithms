package SearchAlgo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import Puzzle.Move;
import Puzzle.State;

public class DFSAlgo {
    State state;
    FileWriter fw = new FileWriter("DFSAlgo.txt");
    BufferedWriter bw;
    
    
    public DFSAlgo() throws IOException {
         bw = new BufferedWriter(fw);
         bw.write("Ausgangsknoten: ");    
    }
    
    private void addToQueue(State nextState, LinkedList<State> newQueue) {
        if(nextState != null) {
            newQueue.add(nextState);
        }
    }
    
    public String solvePuzzle(State state, State goalState) throws IOException {
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
                
        //Nachfolger
        LinkedList<State> newQueue = new LinkedList<State>();
        this.addToQueue(Move.down(state),newQueue);
        this.addToQueue(Move.up(state),newQueue);
        this.addToQueue(Move.right(state),newQueue);

        this.addToQueue(Move.left(state),newQueue);
                
        while(newQueue.size() > 0) {
            String solution = solvePuzzle(newQueue.poll(), goalState);
            if(solution.equals("Loesung gefunden")) {
                return "Loesung gefunden";
            }
        }
        System.out.println("Keine Lösung gefunden");
        bw.write("keine Lösung gefunden");
        bw.close();
        return "keine Lösung gefunden";
    }
}
