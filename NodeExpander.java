

import java.util.ArrayList;
import java.util.List;

import components.set.Set;



/**
 * @author greedywind / James Archer
 * 
 */
public class NodeExpander extends TransitionModel {
	
	

	/**
	 * Returns the children obtained from expanding the specified node in the
	 * specified problem.
	 * 
	 * @param node
	 *            the node to expand
	 * @param problem
	 *            the problem the specified node is within.
	 * 
	 * @return the children obtained from expanding the specified node in the
	 *         specified problem.
	 */
	public final List<Node> expandNode(Node node) {
		List<Node> childNodes = new ArrayList<Node>();

		Set<String> states=SuccessorStates(node.getState(),ActionSet());
	

		for (String successorState : states) {

		    int stepCost =1;
			childNodes.add(new Node(successorState, node, stepCost));
		}
		

		return childNodes;
	}

}