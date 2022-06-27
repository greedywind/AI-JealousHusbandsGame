import java.util.ArrayList;
import java.util.List;

import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Class implementing the breadth first search algorithm.
 * 
 * @author greedywind / James Archer
 * 
 */
public class BreadthFirstSearch extends NodeExpander {

	public int PathCostToGoal;
	
    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    public BreadthFirstSearch() {
    }
    /**
     * 
     * @param n
     *   Node of type n
     * @param goalState
     *    The specified goalstate
     * @return
     *     
     */

    private static boolean isGoalStateOnExpansion(Node n,String goalState){
    	
    	return n.getState().equals(goalState);
    }
    
    /**
     *  This method checks if a given node is in the frontier by traversing through
     *  it.   
     * @param node
     *    A node of type Node2
     * @param Frontier
     *   A list of type Node2
     * @return
     * returns whether or not the given node is in the frontier 
     */
    private static boolean isInFrontier(Node node, Queue<Node>Frontier){
    	
    	for(Node fn:Frontier){
    		if(fn.getState().equals(node.getState())){
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    /**
     *  This method prints the solution to a search from the given node to a goal node.
     * @param node
     *     A node of type Node2
     * @param explored
     *     The set of explored states during the search.
     */
    private static void printSolution(Node node, List<String> explored){
    	
    	SimpleWriter out=new SimpleWriter1L();
    	out.println("The explored set is: ");
    	
    	for (String fn:explored){
    		out.print(fn+ "-->");
    	}
    	out.println();
    	out.println("The solution is: ");
    	for (Node fn:node.getPathFromRoot()){
    		
    		out.print(fn.getState()+"-->");
    	}
    	
    	out.println();
    	out.println("The pathcost is: "+ node.getPathCost());
    	out.close();
    	
    }
    /**
     * Main method.
     * 
     * @param args
     *            the command line arguments
     */
    public void BreadthFirstsearch(Node rootNode, String goalState ){
       
    	SimpleWriter out=new SimpleWriter1L();
    	Queue<Node> frontier=new Queue1L<>();
    	frontier.enqueue(rootNode);
    	List<String> explored=new ArrayList<>();
    	this.PathCostToGoal=0;                         // created for use later as an object parameter. not relevant to current problem.
       if (isGoalStateOnExpansion(rootNode,goalState)){
    	   printSolution(rootNode, explored);
    	   return;
       }
       
       Node current=frontier.front();
       while(frontier.length()>0){
    	   current=frontier.dequeue();
    	   
    	   List<Node> successorNodes=expandNode(current);
    	   
    	   out.println("Current Expanded Node: " + current.getState());
    	   
    	   explored.add(current.getState());
    	   
    	   if (isGoalStateOnExpansion(current,goalState)){
				   out.println("Solution Reached!!!");
				   printSolution(current,explored);
				   this.PathCostToGoal=current.getPathCost();
				   return;
    	   }
    	   
    	   
    	   for(Node fn:successorNodes){
    		   if (!(explored.contains(fn.getState()) || isInFrontier(fn,frontier))){
    				   frontier.enqueue(fn);
    		   }
    	   }
       }
       out.println("Solution could not be found");
       out.println("Search terminated at: " + current.getState());
       out.println("Current Path Cost: "+ current.getPathCost());
       
       out.close();
    }
    
       
      
}
     

    

