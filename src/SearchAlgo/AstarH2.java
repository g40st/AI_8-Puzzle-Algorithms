package SearchAlgo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

import Puzzle.Move;
import Puzzle.State;

public class AstarH2 {
    State actualState;
    FileWriter fw = new FileWriter("AstartH2.txt");
    BufferedWriter bw;
    final static int CAPACITY = 10000;
    /** The A * Search priority queue used to solve the puzzle. */
    StateSortH2 stateSortH2 = new StateSortH2();
    PriorityQueue<State> newQueue = new PriorityQueue<State>(CAPACITY, stateSortH2);
    
    public AstarH2() throws IOException {   
        bw = new BufferedWriter(fw);
        bw.write("Ausgangsknoten: ");       
    }
    
    private void addToQueue(State nextState, State goalState) throws IOException {
        if(nextState != null) {
            nextState.calulateH2(goalState);
            this.newQueue.add(nextState);
        }
    }
    
    public String solvePuzzle(State state, State goalState) throws IOException {    
       state.calulateH2(goalState);
        this.newQueue.add(state);
        while(true) {
            if(newQueue.isEmpty()) {
                System.out.println("keine Lösung gefunden");
                bw.write("keine Lösung gefunden");
                return "Keine Lösung gefunden";
            }
            actualState = newQueue.poll();
            actualState.incSteps();
            bw.write(actualState.toString());
            bw.write("\ng(s): " + actualState.getG() + " // h(s): " + actualState.getH()+ "\n");
            System.out.println(actualState);
            
            if(actualState.isSolved(goalState)) {
                System.out.println("\nLösung gefunden in Ebene " + actualState.getDepth() + " | Anzahl der Schritte: " + actualState.getSteps());
                bw.write("\n\nLösung gefunden in Ebene: " + actualState.getDepth() + " | Anzahl der Schritte: " + actualState.getSteps()); 
                bw.close();
                return "Loesung gefunden";  
            }
            this.addToQueue(Move.left(actualState),goalState);
            this.addToQueue(Move.right(actualState),goalState);
            this.addToQueue(Move.down(actualState),goalState);
            this.addToQueue(Move.up(actualState),goalState);            
        }     
    }
}

class StateSortH2 implements Comparator<State> {
    @Override
    public int compare(State o1, State o2) {
          return o1.getF() - o2.getF();
    }
}


