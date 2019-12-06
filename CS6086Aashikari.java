import java.io.File;
import java.util.*;
//import java.lang.String;

public class CS6086Aashikari {
	
	private int top;
	private char[] operatorsArray;

	public CS6086Aashikari(int size) { 
		operatorsArray = new char[size]; top= -1;
	}

	public void push(char op) { 		
		top++; operatorsArray[top] = op;		
	}

	public char pop() {		
		return operatorsArray[top--];	
	}

	int topElement() {		
		return operatorsArray[top];
	}

	public boolean isEmpty() { 	
		return (top== -1);
	}
	
	public static char[] convStringToChar(String line, int lineNum) {
		
		System.out.println("The infix expression for line " + lineNum + " is: ");
		//System.out.println(line);
		
		//instantiate linked list and string array
		LinkedList<String> lList = new LinkedList<String>();
		String[] split = new String[10];
		
		//make string array equal to line without the spaces
	    split = line.split(" ");
	    
	    for (int i = 0; i < split.length; i++)
	    	System.out.print(split[i]);
	    
	    System.out.println("");
	    
	    //add each element from string array to linked list
	    for (int i = 0; i < split.length; i++)
	    {	
	    	lList.add(split[i]);
	    }
	    
	    //create another string array to store the elements from the linked
	    //list.
	    //there will be no extra cells in the array now
	    String[] afterList = new String[lList.size()];
	    lList.toArray(afterList);
	    
	    //char array to be returned to main
	    char[] infix = new char[afterList.length];
	    
	    //adds the values from new string array to char array
	    for(int i = 0; i < afterList.length; i++)
	   	    infix[i] = afterList[i].charAt(0);
	    
	    return infix;
		
	}

	public static void calcPostfix(char[] infix, int lineNum) {
	    
		System.out.println("The postfix expression for line " + lineNum + " is: ");
	    
	    CS6086Aashikari operatorsStack = new CS6086Aashikari(infix.length);
	    
	    for (int i = 0; i < infix.length; i++) 
	    {
	    	
	    	if (infix[i] == 'a' 
	    			|| infix[i] == 'b' 
	    			|| infix[i] == 'c' 
	    			|| infix[i] == 'd')
	    	{
	    			
	    		System.out.print(infix[i]);
	    			
	    	} else if(infix[i] == '^')
	    	{
	    		if(operatorsStack.isEmpty() == false) 
	    		{
	    			while(operatorsStack.isEmpty() == false 
	    					&& operatorsStack.topElement() == '^')
	    			{
	    				//currentChar = (char) operatorsStack.topElement();
	    				System.out.print((char) operatorsStack.topElement());
	    				operatorsStack.pop();
	    				if(operatorsStack.isEmpty() == true)
	    					break;
	    			}
	    		}
	    		operatorsStack.push(infix[i]);
	    			
	    	} else if (infix[i] == '*' 
	    			|| infix[i] == '/' )
	    	{
	    		if(operatorsStack.isEmpty() == false) 
	    		{
	    			while(operatorsStack.isEmpty() == false 
	    					&& operatorsStack.topElement() == '^'
	    					|| operatorsStack.topElement() == '*'
	    					|| operatorsStack.topElement() == '/')
	    			{
	    				System.out.print((char) operatorsStack.topElement());
	    				operatorsStack.pop();
	    				if(operatorsStack.isEmpty() == true)
	    					break;
	    			}
	    		}
	    		operatorsStack.push(infix[i]);
	    			
	    	} else if(infix[i] == '+' 
	    			|| infix[i] == '-')
	    	{
	    		if(operatorsStack.isEmpty() == false) 
	    		{
	    			while (operatorsStack.isEmpty() == false 
	    					&& operatorsStack.topElement() == '^'
	    					|| operatorsStack.topElement() == '*' 
	    					|| operatorsStack.topElement() == '/'
		    				|| operatorsStack.topElement() == '+' 
		    				|| operatorsStack.topElement() == '-')
	    			{
	    				System.out.print((char) operatorsStack.topElement());
	    				operatorsStack.pop();
	    				if(operatorsStack.isEmpty() == true)
	    					break;
	    			}
	    		}
	    		operatorsStack.push(infix[i]);
	    			
	    	}
	    	
	    }
	    
	    
		while (operatorsStack.isEmpty() == false 
				&& operatorsStack.topElement() != -1)
		{
			System.out.print((char) operatorsStack.topElement());
			operatorsStack.pop();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		Scanner in = new Scanner(new File("infixData6A.txt"));
	    String[] list = new String[5];
	    
	    for (int i = 0; i < list.length; i++)
	    {
	        list[i] = in.nextLine();
	    }
	
	    String line1 = list[0];
	    String line2 = list[1];
	    String line3 = list[2];
	    String line4 = list[3];
	    String line5 = list[4];
	    
	   
	    //char[] infix2 = convStringToChar(line2, 2);
	    //char[] infix3 = convStringToChar(line3, 3);
	    //char[] infix4 = convStringToChar(line4, 4);
	    //char[] infix5 = convStringToChar(line5, 5);
	    
	    //System.out.println("-----Beginning of operatorsStack-----");
	    
	    char[] infix1 = convStringToChar(line1, 1);
	    calcPostfix(infix1, 1);
	    System.out.println("");
	    char[] infix2 = convStringToChar(line2, 2);
	    calcPostfix(infix2, 2);
	    System.out.println("");
	    char[] infix3 = convStringToChar(line3, 3);
	    calcPostfix(infix3, 3);
	    System.out.println("");
	    char[] infix4 = convStringToChar(line4, 4);
	    calcPostfix(infix4, 4);
	    System.out.println("");
	    char[] infix5 = convStringToChar(line5, 5);
	    calcPostfix(infix5, 5);
	    
	    in.close();    
	}

}
