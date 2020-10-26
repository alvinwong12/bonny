import java.util.Scanner;
import java.util.Stack;

public class Draft {

	// TDDO
	// should return boolean for style
	// stack is miss used in this function; either do resursively or iteratively with a stack
	// NOTE: resursive function calls (line 31) is already a stack in itself, where the stack consists of the sequences of function calls
	// Your stack is redundant in this function
    static String isValid(String input){
        Stack<Integer> stack = new Stack<>();

        for ( int i = input.length()-1; i >= 0; i--){
            if (input.charAt(i) == ')') {
                //store the indices of closed brackets in the stack
                stack.push(i);
                //System.out.println(stack);
            }
            if (input.charAt(i)=='('){
                //if open bracket is encountered, take string and evaluate
                int position = stack.pop(); //the index of that close bracket pair
                String game = input.substring(i + 1,position);//take string between the brackets
                String gameWithBrackets = new String("("+game+")");

                // TODO
                // error here, why not return?
                if (playGame(gameWithBrackets) == "f"){
                    System.out.println("INVALID");
                    //return "INVALID";
                } else {
                    String replacedInput = input.replace(gameWithBrackets,playGame(gameWithBrackets));
                    //System.out.println(replacedInput);
                    return isValid(replacedInput); //recursion with new game
                }
            }
        } return input;
    }

    static String playGame(String round){
        //accounts for all possibilities of rock, paper, scissors game and returns the winner
        switch (round){
            case "(R&R)":
                return "R";
            case "(P&P)":
                return "P";
            case "(S&S)":
                return "S";
            case "(R&S)":
                return "R";
            case "(S&R)":
                return "R";
            case "(P&S)":
                return "S";
            case "(S&P)":
                return "S";
            case "(R&P)":
                return "P";
            case "(P&R)":
                return "P";
            default:
                //if the game is not valid, return f
                return "f";
        }
    }

    //used as an initial check that the input is valid before proceeding to isValid game
    static boolean initialCheck(String input) {
        Stack<Character> stack = new Stack<>();

        if (input.length()>1 && input.charAt(0)!='('){
            return false;
        }

        for (int currentIndex = 0; currentIndex < input.length(); currentIndex++) {
            switch (input.charAt(currentIndex)) {
                case '(' :
                    stack.push('(');
                    break;
                case ')' :
                    if (stack.empty()) {
                        return false;
                    }
                    Character poppedCharacter = stack.pop();
                    if (poppedCharacter != '(') {
                        return false;
                    }
                    break;
                default:
                    //do nothing
            }
        }
        return (stack.empty());
    }


    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        // TODO -> uncomment when code above is finsished

        // String theirGame = keyboard.nextLine();
        // if (initialCheck(theirGame)){
        //     //String answer = new String(isValid(theirGame));
        //     //System.out.println(answer);
        //     isValid(theirGame);
        //     System.out.println("VALID");
        // } else {
        //     System.out.println("INVALID");
        // }

        // TODO Delete -> for testing only
        // once isValid function is changed to boolean return type, use bottom code to mass test
        // see testing instructions
        // while(keyboard.hasNextLine()){
        //     String theirGame = keyboard.nextLine();
        //     boolean valid = isValid(theirGame);
        //     if (valid){
        //         System.out.println("VALID");
        //     } else {
        //         System.out.println("INVALID");
        //     }
        // }
    }
}
