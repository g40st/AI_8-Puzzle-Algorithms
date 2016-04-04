package Puzzle;

import java.util.Arrays;


public class State {
    public int[] array = new int[9];
    public int blankIndex;
    private int depth;
    // Anfangsknoten wird incrementiert deshalb -1
    private static int steps = -1;
    private State previous;
    
    
    public State(int[] input) {
        this.array = input;
        this.blankIndex = getIndex(input, 0);
        this.previous = null;
        this.depth = 0;
    }
    
    public State(State previous, int blankIndex) {
        this.array = Arrays.copyOf(previous.array, previous.array.length);
        this.array[previous.blankIndex] = this.array[blankIndex];
        this.array[blankIndex] = 0;
        this.blankIndex = blankIndex;
        this.depth = previous.depth + 1;
        this.previous = previous;
    }
    
    public static int getIndex(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) { 
                return i; 
            }  
        }
        return -1;
    }
    
    public boolean isSolved(State goalState) {
        for(int i = 0; i < goalState.array.length; i++) {
            if(goalState.array[i] != this.array[i]) { 
                return false;
            }
        }
        return true;
    }
    
    public String toString() {
        int[] state = this.array;
        String s = "\n\n";
        for(int i = 0; i < state.length; i++) {
            if(i % 3 == 0 && i != 0) {
                    s += "\n";
            }
            s += (state[i] != 0) ? String.format("%d ", state[i]) : "  ";
        }
        return s;
    }
    
    public int getDepth() {
        return depth;
    }

    public int getSteps() {
        return steps;
    }
    
    public void incSteps() {
    	steps++;
    }
}
