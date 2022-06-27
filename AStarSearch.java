import java.util.ArrayList;
import java.util.Arrays;
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
 * A class defining the Astar search algorithm to the jealous husbands problem.
 * 
 * @author greedywind / James Archer
 * 
 */
public class AStarSearch extends NodeExpander {


    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    public AStarSearch() {
    }

    /**
     *  This method checks if a the state at the given node n is the specified goal state.
     * @param n
     *   The node to be checked
     * @param goalState
     * 
     * @requires
     *   n be of type Node2 as specified in project Lab1.
     * 
     *       
     * @return the value of the expression is n===goalstate;
     */
    private static boolean isGoalStateOnExpansion(Node2 n,String goalState){
    	
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
 private static boolean isInFrontier(Node2 node, List<Node2>Frontier) {
    	
    	for (Node fn:Frontier) {
    		if (fn.getState().equals(node.getState())) {
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
  *     The set of explored states during the search. A set is used so as to not require
  *     an element be checked before being added to the explored set.
  */
    
    private static void printSolution (Node2 node, Set<String> explored) {
    	
    	SimpleWriter out = new SimpleWriter1L();
    	out.println("The explored set is: ");
    	
    	for (String fn:explored) {
    		out.print(fn + "-->");
    	}
    	
    	
    	out.println();
    	out.println("The solution is: ");
    	for (Node2 fn:node.getPathFromRoot2()) {
    		
    		out.print(fn.getState() + "-->");
    	}
    	
    	out.println();
    	out.println("The pathcost is: " + node.current.getPathCost());
    	out.close();
    	
    }
    
    /**
     * 
     * @param frontier
     *  The list of unexplored nodes.
     * @return
     * the node with the lowest evaluation value
     */
    private static Node2 minNodeEval(List<Node2> frontier){
   
    	int min=frontier.get(0).evaluationValue;
    	int posstore=0;
    	for (int i=1; i<frontier.size(); i++){
    		int temp=frontier.get(i).evaluationValue;
    		
    		if(min>temp){
    			min=temp;
    			posstore=i;
    		}
    	}
    	
    	return frontier.get(posstore);
    }
    	
    /**
     * Main method.
     * 
     * @param args
     *            the command line arguments
     */
    public void aStarSearch(Node2 rootNode, String goalState ){
       
    	SimpleWriter out=new SimpleWriter1L();
    	List<Node2> frontier=new ArrayList<>();
    	
    	rootNode.computeHeuristic();
    	rootNode.computeEvaluation();
    	frontier.add(rootNode);
    	Set<String> explored=new Set1L<>();

    	
       if (isGoalStateOnExpansion(rootNode, goalState)){
    	   printSolution(rootNode, explored);
    	   return;
       }
       
       Node2 currentNode=rootNode.newInstance();
       while(frontier.size()>0){
    	   
    	   currentNode=minNodeEval(frontier);
    	   frontier.remove(currentNode);
    	   
    	   List<Node> successorNodes=expandNode(currentNode.current);
    	   explored.add(currentNode.getState());
    	   out.println("Current Expanded Node: "+ currentNode.getState()+" , " + "Node Evaluation Value: "+ currentNode.evaluationValue);
    	   
    	   if (isGoalStateOnExpansion(currentNode,goalState)){
    		   out.println();
			   out.println("Solution Reached!!!");
			   printSolution(currentNode,explored);
			   return;
    	   }
			   
			   List<Node2> successorNodes2=new ArrayList<>();
	    	   
	    	   for (Node fn:successorNodes){
	    		   successorNodes2.add(new Node2(fn,goalState));
	    	   }
	    	   
	    	   for (Node2 fn:successorNodes2){
	    		   fn.computeHeuristic();
	    		   fn.computeEvaluation();
	    		   
	    			   
	    			  
	    			   if(!(isInFrontier(fn,frontier) || explored.contains(fn.getState()))){
	    				    frontier.add(fn);
	    			   }else{
	    				    for(int i=0; i<frontier.size(); i++){
	    				    	if(fn.getState().equals(frontier.get(i).getState())){
	    				    		if(fn.evaluationValue<frontier.get(i).evaluationValue){
		    				    		frontier.set(i,fn);
	    				    		}
	    				    	}
	    				    }
	    			     }
	    	   }
       }
    	   
    	  
       
       out.println("Solution could not be found");
       out.println("Search terminated at: " + currentNode.getState());
       out.println("Current Path Cost: "+ currentNode.current.getPathCost());
       
       out.close();
    }

   }


