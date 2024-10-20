/* 1106. Parsing A Boolean Expression

Intuition
Fundamentally, we want to use stack, but instead of adding every char to stack, we focus on the elements that matter (operators, boolean values, and parantheses) while ignoring commas (since they don't affect the outcome).

We still push operators and boolean values onto the stack as we process the expression, but when we encounter a closing parenthesis, we immediately evaluate the subexpression by popping values from the stack.

The key improvement is that we can stop early if the result becomes clear. For example, with the & operator, if we find an f while popping values, we know the subexpression result is f and can stop without checking further. Similarly, for the | operator, finding a t allows us to stop early.

Example:
&(t,|(f,t))
We push & and t, then |, followed by f and t.
When popping values for the | subexpression, we know the result is t as soon as we find one t.
We push t back onto the stack and continue with the & operator, which evaluates to t since both values are true.

Approach
Initialize stack for operators & boolean values
Traverse through expression:
If char is comma, or an open parenthesis, skip
If char is bool, or an operator, push to stack
If char is a closing parenthesis:
Initialize two boolean flags to track presence of true & false within the parentheses
Process values in parentheses:
While the top of stack is not an operator:
Pop from stack and check:
If t: hasTrue
If f: hasFalse
Pop operator from stack
Evaluate subexpression based on the operator:
If !, push f if hasTrue. Otherwise, push t.
If &, push f if hasFalse. Otherwise, push t.
If |, push t if hasTrue is true. Otherwise, push f.
The final result will be at the top of the stack
Complexity
Time complexity: O(N)
Space complexity: O(N) */

public class Solution {

    public boolean parseBoolExpr(String expression) {
        Stack<Character> st = new Stack<>();

        for (char currChar : expression.toCharArray()) {
            if (currChar == ',' || currChar == '(') continue; 
            if (
                currChar == 't' ||
                currChar == 'f' ||
                currChar == '!' ||
                currChar == '&' ||
                currChar == '|'
            ) {
                st.push(currChar);
            }
            else if (currChar == ')') {
                boolean hasTrue = false, hasFalse = false;

                while (
                    st.peek() != '!' && st.peek() != '&' && st.peek() != '|'
                ) {
                    char topValue = st.pop();
                    if (topValue == 't') hasTrue = true;
                    if (topValue == 'f') hasFalse = true;
                }

                char op = st.pop();
                if (op == '!') {
                    st.push(hasTrue ? 'f' : 't');
                } else if (op == '&') {
                    st.push(hasFalse ? 'f' : 't');
                } else {
                    st.push(hasTrue ? 't' : 'f');
                }
            }
        }
        return st.peek() == 't';
    }
}

// Most Efficient solution Time:1ms

class Solution {
    private int idx = 0;

    public boolean parseBoolExpr(final String expression) {
        this.idx = 0;

        if(expression.length() == 1)
            return expression.charAt(0) == 't';
        
        return this.helper(expression);
    }

    private boolean helper(final String s) {
        final char operator = s.charAt(this.idx);

        this.idx += 2;
        
        char c = s.charAt(this.idx);

        boolean result = false;

        if(c == 't') {
            result = true;
            this.idx++;
        } else if(c == 'f') {
            result = false;
            this.idx++;
        } else {
            result = this.helper(s);
        }

        c = s.charAt(this.idx);

        while(c != ')') {
            if(c == ',') {
                c = s.charAt(++this.idx);
                continue;
            }

            boolean curr = false;

            if(c == 't') {
                curr = true;
                this.idx++;
            } else if(c == 'f') {
                curr = false;
                this.idx++;
            } else {
                curr = helper(s);
            }

            if(operator == '&')
                result &= curr;
            else if(operator == '|')
                result |= curr;

            c = s.charAt(this.idx);
        }

        this.idx++;

        return operator == '!' ? !result : result;
    }
}
