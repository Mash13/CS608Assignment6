import java.io.File;
import java.util.LinkedList;
import java.util.*;
import java.lang.Double;
//import java.lang.String;

public class CS6086Bashikari {
	
	private int top;
	private int top2;
	private char[] operatorsArray;
	private double[] operandsArray;
	
	//static double[] numList = new double[4];

	public CS6086Bashikari(int size) { 
		
		operatorsArray = new char[size]; top= -1;

	}
	
	public CS6086Bashikari(int size, int num) { 
		
		operandsArray = new double[size]; top2= -1;

	}

	public void push(char op) { 
		
		top++; operatorsArray[top] = op;
		
	}
	
	public void push(double op) { 
		
		top2++; operandsArray[top2] = op;
		
	}

	public char pop() {
		
		return operatorsArray[top--];
	
	}
	
	public double pop2() {
		
		return operandsArray[top2--];
	
	}

	int topElement() {
		
		return operatorsArray[top];
	
	}
	
	double topElement2() {
		
		return operandsArray[top2];
	
	}

	public boolean isEmpty() { 
		
		return (top== -1);
		
	}
	
	public boolean isEmpty2() { 
		
		return (top2== -1);
		
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
	    
	    CS6086Bashikari operatorsStack = new CS6086Bashikari(10);
	    
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
	
	public static void calcInfix(String[] inputList,int lineNum) {
		
		String infixInput = inputList[lineNum];
	    char[] infix = infixInput.toCharArray();
	    String[] dubList = inputList[lineNum+1].split(" ");
		
		double numA = Double.parseDouble(dubList[0]);
		double numB = Double.parseDouble(dubList[1]);
		double numC = Double.parseDouble(dubList[2]);
		double numD = Double.parseDouble(dubList[3]);
		
		int outputLineNum = lineNum + 1;
		System.out.print("The infix expression on line " + outputLineNum +
				" is: ");
		System.out.println(infixInput);
		
		char outputChar = 'a';
		for(int i = 0; i < dubList.length; i++)
		{
			System.out.print(outputChar + " = " + dubList[i] + " ");
			outputChar++;
		}
		System.out.println("");
	    
		CS6086Bashikari operandsStack = new CS6086Bashikari(infix.length, 1);
	    CS6086Bashikari operatorsStack = new CS6086Bashikari(infix.length);
	    
	    for (int i = 0; i < infix.length; i++) 
	    {
	    	
	    	if (infix[i] == 'a')
	    	{
	    			
	    		//System.out.print(infix[i]);
	    		operandsStack.push(numA);
	    		
	    	} else if (infix[i] == 'b')
		    {
		    			
		    	//System.out.print(infix[i]);
		    	operandsStack.push(numB);
		    			
		    } else if (infix[i] == 'c')
		    {
		    			
		    	//System.out.print(infix[i]);
		    	operandsStack.push(numC);
		    			
		    } else if (infix[i] == 'd')
		    {
		    			
		    	//System.out.print(infix[i]);
		    	operandsStack.push(numD);
		    			
	    	} else if(infix[i] == '^')
	    	{
	    		if(operatorsStack.isEmpty() == false) 
	    		{
	    			while(operatorsStack.isEmpty() == false 
	    					&& operatorsStack.topElement() == '^')
	    			{
	    				//currentChar = (char) operatorsStack.topElement();
	    				//System.out.print((char) operatorsStack.topElement());
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
	    				double opd2 = operandsStack.topElement2();
		    			operandsStack.pop2();
		    			double opd1 = operandsStack.topElement2();
		    			operandsStack.pop2();
		    			char oprtr = (char) operatorsStack.topElement();
		    			operatorsStack.pop();
		    		
		    			double result = calcResult(opd1, oprtr, opd2);
		    			operandsStack.push(result);
	    				//System.out.print((char) operatorsStack.topElement());
	    				//operatorsStack.pop();
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
	    				double opd2 = operandsStack.topElement2();
		    			operandsStack.pop2();
		    			double opd1 = operandsStack.topElement2();
		    			operandsStack.pop2();
		    			char oprtr = (char) operatorsStack.topElement();
		    			operatorsStack.pop();
		    		
		    			double result = calcResult(opd1, oprtr, opd2);
		    			operandsStack.push(result);
	    				//System.out.print((char) operatorsStack.topElement());
	    				//operatorsStack.pop();
	    				if(operatorsStack.isEmpty() == true)
	    					break;
	    			}
	    		}
	    		operatorsStack.push(infix[i]);
	    			
	    	} else if(infix[i] == '(')
	    	{
	    		operatorsStack.push(infix[i]);
	    	}else if(infix[i] == ')')
	    	{
	    		while (operatorsStack.topElement() != '(')
	    		{
	    			double opd2 = operandsStack.topElement2();
	    			operandsStack.pop2();
	    			double opd1 = operandsStack.topElement2();
	    			operandsStack.pop2();
	    			char oprtr = (char) operatorsStack.topElement();
	    			operatorsStack.pop();
	    		
	    			double result = calcResult(opd1, oprtr, opd2);
	    			operandsStack.push(result);
	    		}
	    		
	    		//should operatorsStack.topElement() be printed here?
	    		operatorsStack.pop();	
	    		
	    		
	    	}else
	    	{
	    		
	    		double opd2 = operandsStack.topElement2();
    			operandsStack.pop2();
    			double opd1 = operandsStack.topElement2();
    			operandsStack.pop2();
    			char oprtr = (char) operatorsStack.topElement();
    			operatorsStack.pop();
	    		
	    		double result = calcResult(opd1, oprtr, opd2);
    			operandsStack.push(result);
	    	}
	    	
	    }
	    
	    
		while (operatorsStack.isEmpty() == false 
				&& operatorsStack.topElement() != -1)
		{
			double opd2 = operandsStack.topElement2();
			operandsStack.pop2();
			double opd1 = operandsStack.topElement2();
			operandsStack.pop2();
			char oprtr = (char) operatorsStack.topElement();
			operatorsStack.pop();
    		
    		double result = calcResult(opd1, oprtr, opd2);
			operandsStack.push(result);
		}
		
		System.out.println("The value of the infix expression for line " 
				+ outputLineNum + " is: " + operandsStack.topElement2());
		
	}

	public static double calcResult(double opd1, char oprtr, double opd2) {
		
		double result = 0;
		
		if (oprtr == '+')
		{
			
			result = opd1 + opd2;
			
		}else if (oprtr == '-')
		{

			result = opd1 - opd2;
			
		}else if (oprtr == '*')
		{

			result = opd1 * opd2;
			
		}else if (oprtr == '/')
		{
			
			result = opd1 / opd2;
			
		}
		
		return result;
	}
	
	public static void main(String[] args) throws Exception {
	  
		Scanner in = new Scanner(new File("infixData6B.txt"));
		String[] inputList = new String[10];
		
	    for (int i = 0; i < inputList.length; i++)
	    {
	    	inputList[i] = in.nextLine();
	    }
	    
	    calcInfix(inputList, 0);
	    calcInfix(inputList, 2);
	    calcInfix(inputList, 4);
	    calcInfix(inputList, 6);
	    calcInfix(inputList, 8);
	    
	    in.close();
	}

}