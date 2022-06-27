import java.util.ArrayList;
import java.util.List;


/**
 * Put a short phrase describing the program here.
 * 
 * @author greedywind / James Archer
 * 
 */
public class Node {

	// n.STATE: the state in the state space to which the node corresponds;
	private String state;

	// n.PARENT: the node in the search tree that generated this node;
	private Node parent;

	// n.PATH-COST: the cost, traditionally denoted by g(n), of the path from
	// the initial state to the node, as indicated by the parent pointers.
	private int pathCost;

	/**
	 * Constructs a node with the specified state.
	 * 
	 * @param state
	 *            the state in the state space to which the node corresponds.
	 */
	public Node(String state) {
		this.state = state;
		this.pathCost = 0;
	}

	/**
	 * Constructs a node with the specified state, parent and step
	 * cost.
	 * 
	 * @param state
	 *            the state in the state space to which the node corresponds.
	 * @param parent
	 *            the node in the search tree that generated the node.
	 *
	 * @param stepCost
	 *            the cost from the parent node to this node. Used to calculate
	 *            the full pathCost from the root node to here, based on the
	 *            parent's path cost.
	 */
	public Node(String state, Node parent,  int stepCost) {
		this.state=(state);
		this.parent = parent;
		this.pathCost = parent.pathCost + stepCost;
	}

	/**
	 * Returns the state in the state space to which the node corresponds.
	 * 
	 * @return the state in the state space to which the node corresponds.
	 */
	public String getState() {
		return state;
	}

	/**
	 * Returns this node's parent node, from which this node was generated.
	 * 
	 * @return the node's parent node, from which this node was generated.
	 */
	public Node getParent() {
		return parent;
	}

	

	/**
	 * Returns the cost of the path from the initial state to this node as
	 * indicated by the parent pointers.
	 * 
	 * @return the cost of the path from the initial state to this node as
	 *         indicated by the parent pointers.
	 */
	public int getPathCost() {
		return pathCost;
	}

	/**
	 * Returns <code>true</code> if the node has no parent.
	 * 
	 * @return <code>true</code> if the node has no parent.
	 */
	public boolean isRootNode() {
		return parent == null;
	}

	/**
	 * Returns the path from the root node to this node.
	 * 
	 * @return the path from the root node to this node.
	 */
	public List<Node> getPathFromRoot() {
		List<Node> path = new ArrayList<Node>();
		Node current = this;
		while (!current.isRootNode()) {
			path.add(0, current);
			current = current.getParent();
		}
		// ensure the root node is added
		path.add(0, current);
		return path;
	}
	

	
}


