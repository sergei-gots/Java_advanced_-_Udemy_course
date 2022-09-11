import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Lesson 15. Class Stack
 */

public class L15_Stack {

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();

        refill(stack);
        
        System.out.println("stack.peek() = " + stack.peek());
        System.out.println("stack.peek() = " + stack.peek());

        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());

        System.out.println("stack.peek() = " + stack.peek());

        System.out.println("stack.pop() = " + stack.pop());
        
        try {
            System.out.println("stack.pop() = " + stack.pop());
        }
        catch(EmptyStackException e) {
            e.printStackTrace();
        }
        
        refill(stack);

        System.out.println("stack.empty() = " + stack.empty());

        while(!stack.empty()){
            System.out.println("stack.pop() = " + stack.pop());
        }
        System.out.println("stack.empty() = " + stack.empty());
        
    }

    private static void refill(Stack<Integer> stack){
        stack.push(5);
        stack.push(3);
        stack.push(1);
    }
}
