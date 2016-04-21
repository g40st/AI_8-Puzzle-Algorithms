package SearchAlgo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import Puzzle.Move;
import Puzzle.State;

public class BFSAlgo {
    State state;
    FileWriter fw = new FileWriter("BFSAlgo.txt");
    BufferedWriter bw;
    Queue<State> newQueue = new LinkedList<State>();
    
    public BFSAlgo() throws IOException {   
        bw = new BufferedWriter(fw);     
    }
    
    private void addToQueue(State nextState) {
        if(nextState != null) {
            this.newQueue.add(nextState);
        }
    }
 
    public void solvePuzzle(Queue<State> queue, State goalState) throws IOException {
        newQueue = new LinkedList<State>();
        while(!queue.isEmpty()) {
            this.state = queue.poll();
//            bw.write(this.state.toString());
//            System.out.println(this.state);
            
            // Anzahl der Durchlaeufe
            this.state.incSteps();
            
            if(this.state.isSolved(goalState)) {
            	State tmp = state.getParentState();
             	bw.write("Zielkonoten: ");   
             	bw.write(state.toString());
                while(tmp != null) {
                    bw.write(tmp.toString());
                    System.out.println(tmp);
                    tmp = tmp.getParentState();
                }
                System.out.println("\nLösung gefunden in Ebene " + this.state.getDepth() + " | Anzahl der Schritte: " + this.state.getSteps());
                bw.write("\n\nLösung gefunden in Ebene: " + this.state.getDepth() + " | Anzahl der Schritte: " + this.state.getSteps());
                bw.close();
                return;
            }
            this.addToQueue(Move.up(state));
            this.addToQueue(Move.down(state));
            this.addToQueue(Move.left(state));
            this.addToQueue(Move.right(state));
            
        }
        if(newQueue.size() > 0) {
            solvePuzzle(newQueue, goalState);
        } else {
            bw.write("keine Lösung!");
            System.out.println("keine Lösung!");
            bw.close();
        }   
    }
}
