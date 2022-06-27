import java.util.Arrays;

import components.set.Set1L;
import components.set.Set;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Transition model for the jealous husbands search problem. Model
 * defines how successor states are generated, if a state is valid,
 * the set of all actions and
 * 
 * @author greedywind / James Archer
 * 
 */
public class TransitionModel {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    public TransitionModel() {
    }

    /**
     * This method checks if a given state is valid. It does so by checking if a wife
     * is present at any side then her husband must be present or if he isn't, no other
     * husbands must be present.
     * 
     */
    private static boolean isValidState(String state) {
    	
    	boolean checker = true;
    	
    	for (int i=0; i<3; i++){
    			if (state.charAt(i)!=state.charAt(i+3)){   //Checks if the husband isn't present.
    				if (i==2){
    					if(state.charAt(i)==state.charAt(3)|| state.charAt(i)==state.charAt(4)){ // He isn't, checks if any of the other husbands are present.
    		                return false;
    		    		 }
    				}
    					else if (state.charAt(i) == state.charAt(4-i) || state.charAt(i) == state.charAt(5)){
    		                return false;
    		    		 }
    				
    	   }
    	}
    	
    	if((state.equals("0000001") || state.equals("1111110")) && checker==true){   // if all the people at either bank, then the boat must be there as well.
    		checker = false;
    	}
    	
      return checker;
    }
    /**
     * Returns the set of all actions available for the given problem
     */
    public static Set<String> ActionSet(){
    	Set1L<String> ActionSet=new Set1L<>();
    	
    	ActionSet.add("1100001");
    	ActionSet.add("1010001");
    	ActionSet.add("1001001");
    	ActionSet.add("1000101");
    	ActionSet.add("1000011");
    	ActionSet.add("0110001");
    	ActionSet.add("0101001");
    	ActionSet.add("0100101");
    	ActionSet.add("0100011");
    	ActionSet.add("0011001");
    	ActionSet.add("0010101");
    	ActionSet.add("0010011");
    	ActionSet.add("0001101");
    	ActionSet.add("0001011");
    	ActionSet.add("0000111");
    	ActionSet.add("1000001");
    	ActionSet.add("0100001");
    	ActionSet.add("0010001");
    	ActionSet.add("0001001");
    	ActionSet.add("0000101");
    	ActionSet.add("0000011");
    	
    	return ActionSet;
    	
    }
    /**
     * 
     * @param variable1
     * @param variable2
     * @return
     *  the result of adding element by element between two strings of integers.
     */
    public static String AddByElement(String variable1, String variable2){
    
    	int var1=Integer.parseInt(variable1);
    	int var2=Integer.parseInt(variable2);
    	
    	String padder = String.format("%0" + 7  + 'd', 0);
    	String s = Integer.toString(var1+var2);
    	s=padder.substring(s.length()) + s;
    	return s;
    }
    /**
     * 
     * @param var1
     *     variable of type string
     * @param var2
     *     variable of type string
     * @requires <pre>
     *     every element in var1 is greater than or equal to every element in var2.
     *     <pre>
     * @return
     * the result of subtracting element by element between two strings of integers.
     */   
    public static String SubtractByElement(String var1, String var2){
        
    	
    		int l=Integer.parseInt(var1);
    		int s=Integer.parseInt(var2);
    		
    		String padder = String.format("%0" + 7  + 'd', 0);
        	String t= Integer.toString(l-s);
        	t=padder.substring(t.length()) + t;
    	
    	
    	return t;
    }

    /**
     * 
     * @param State
     *   the state from which the successors will be generated
     * @param ActionSet
     *      the set of all valid actions using the initial state as a template.
     * 
     * @return
     */
    public static Set<String> SuccessorStates(String State, Set<String> ActionSet){
    	
    	Set<String> successors=new Set1L<>();
    	boolean checker=true;
    	
    		if (State.endsWith("0")){                        //If a state ends in a zero, then the boat isn't present.
    			for (String fn:ActionSet){
    				String temp=AddByElement(fn,State);      // The only valid actions for direct successors are to add people to the state.
        			if(!temp.contains("2")){                 // This statement checks if a person who was already present was added, which is not possible.
        				successors.add(temp);
        			}
    			}
    			
    		}
    		else{
    			for(String fn:ActionSet){                  //else, the boat is present.
    				for (int i=0; i<fn.length(); i++){
        				if (Integer.parseInt(State.charAt(i)+"")<Integer.parseInt(fn.charAt(i)+"")){   // We check that it satisfies the method preconditions
        					checker=false;
        					break;
        				}
    				}
    				if(checker){
        				String temp=SubtractByElement(State,fn);
    					successors.add(temp);
        			}
    				checker=true;
    			}
    		
    			
    	  }
    	Set<String> validSuccessors=successors.newInstance();   
    	for(String fn: successors){               //makes sure all the successors are valid according to the problem definition.
    		if(isValidState(fn)){
    			validSuccessors.add(fn);
    		}
    	}
    	return validSuccessors;
    	
    }
    
    
    

    /**
     * Prints all the allowed states and their allowed transitions excluding the goal state. Since we wouldn't
     * want to move from it.
     * 
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        
        String filename="data/store.txt";
        SimpleWriter out = new SimpleWriter1L(filename);
        /*
         * 
         */
        
       
        Set<String> ActionSet=ActionSet();
        int count=0;
        for(int i=1; i < 128; i++){
        	String padder = String.format("%0" + 7  + 'd', 0);
        	String s = Integer.toBinaryString(i);
        	s=padder.substring(s.length()) + s;                //makes sure all states are the same length.
        	  if(isValidState(s)){
        	   Set<String> successors = SuccessorStates(s, ActionSet);
        	   out.println( s+ " --->");
        	   out.println("Possible Transitions");
        	   out.println("----------------------------------------------");
        	   while(successors.size()>0){
        		out.println(successors.removeAny()); 
        	   }
        	   
        	   
        	   
        	  count++;
        	  successors.clear();
        	  }
        	}
        
         
        
        out.println("Total number of valid states= " + count);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
