import java.util.Arrays;

import components.set.Set1L;
import components.set.Set;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Finds the solution to the jealous husbands problem using both A* search and
 * Breadth First Search algorithms
 * 
 * @author Akinlawon Solomon
 * 
 */
public class SolutionLab1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SolutionLab1() {
    }

   public static void main(String[]args){
	   
	   SimpleWriter out=new SimpleWriter1L();
	   String init_state="1111111";
	   String goalState="0000000";
	   Node nBFS=new Node(init_state);
	   BreadthFirstSearch search=new BreadthFirstSearch();
	   search.BreadthFirstsearch(nBFS,goalState);
	   
	   Node2 nAstar=new Node2(nBFS,goalState);
	   
	   AStarSearch search2=new AStarSearch();
	   
	   out.println();
	   search2.aStarSearch(nAstar, goalState);
	   
   }
}
