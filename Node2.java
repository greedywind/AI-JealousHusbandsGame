import java.util.ArrayList;
import java.util.List;


/**
 * A class defining the Node2 data structure. It differs from the Node structure by 
 * adding two object parameters heuristicValue and the evaluationValue of the given node.
 * 
 * @author greedywind / James Archer
 * 
 */


public class Node2 extends Node {
	
	public Node current;
	private String goalState;
	
	public int heuristicValue;
	public int evaluationValue;
	
	public Node2 (Node current, String goalState){
		super(current.getState());
		this.current= current;
		this.goalState=goalState;
	}
	
	
	
	/**
	 * Computes the heuristic by counting the number of people or (ones) in a state and subtracts by 2 if
	 * the boat is present.
	 */
	public final void computeHeuristic() {
		
		
		String temp=this.current.getState();
		int count = temp.length() - temp.replace("1", "").length();
		
		if(temp.endsWith("0")){
			this.heuristicValue=count;
		}else{
			this.heuristicValue=count-1;
		}
		
	}
	/**
	 * Computes the evaluation function by adding the heuristic value and the path cost to the node.
	 */
	
	public final void computeEvaluation(){
	   
		
		if (this.heuristicValue!=0){
	    this.evaluationValue= this.heuristicValue + this.current.getPathCost();
	   }
	}
	
	public final Node2 newInstance(){
		Node2 n= new Node2(this, this.goalState);
		
		return n;
	}
	/**
	 * Returns the cost of the path from the initial state to this node as
	 * indicated by the parent pointers.
	 * 
	 * @return the cost of the path from the initial state to this node as
	 *         indicated by the parent pointers.
	 */
	public List<Node2> getPathFromRoot2() {
		List<Node2> path = new ArrayList<>();
		Node2 currentNode = this;
		while (!currentNode.current.isRootNode()) {
			path.add(0, currentNode);
			currentNode = new Node2(currentNode.current.getParent(),currentNode.goalState);
		}
		// ensure the root node is added
		path.add(0, currentNode);
		return path;
	}
	
	
	
}