package Amazon;

/**
 * Psuedo code :
 *
 * without ()
 *  Stack s;
 *  String result;
 *  for i : 1 to exp.length {
 *    if(exp[i] isOperand) {
 *
 *    } else {
 *      while(!s.isEmpty() && hasHigherPrecedence(s.top() , exp[i])) {
 *        result += s.pop();
 *      }
 *      s.push(exp[i]);
 *    }
 *  }
 *
 *  while(!s.isEmpty())
 *    result += s.pop();
 *
 * with ()
 *
 *
 *
 *  Stack s;
 *  String result;
 *  for i : 1 to exp.length {
 *    if(exp[i] isOperand) {
 *
 *    }
 *    else if(exp[i] == '(') {
 *      s.push(exp[i]);
 *    }
 *    else if(exp[i] == ')') {
 *      while(!s.isEmpty() &&  s.top() != '(') {
 *          result += s.pop();
 *      }
 *      s.pop();
 *    }
 *    else {
 *      while(!s.isEmpty() && s.top() != '(' && hasHigherPrecedence(s.top() , exp[i])) {
 *        // we need to keep checking until we hit a '(' because each expression is bounded by '('
 *        result += s.pop();
 *      }
 *      s.push(exp[i]);
 *    }
 *  }
 *
 *  while(!s.isEmpty())
 *    result += s.pop();
 *
 *
 *
 *
 * for infix to prefix :
 *  reverse the infix string/expression and replace ( by ) and ) by (.
 *  calculate postfix on that expression
 *  reverse it again.
 *
 *
 *
 *  prefix to infix
 *  for i : exp.length-1 to 0
 *  Read the Prefix expression in reverse order (from right to left)
 *  If the symbol is an operand, then push it onto the Stack
 *  If the symbol is an operator, then pop two operands from the Stack , combine the operand and operator and push to stack.
 *  (op1 + operator + op2)
 *
 *
 *
 *  postfix to infix
 *  for i : 0 to exp.length-1
 *  Read the Prefix expression
 *  If the symbol is an operand, then push it onto the Stack
 *  If the symbol is an operator, then pop two operands from the Stack , combine the operand and operator and push to stack.
 *  (op1 + operator + op2)
 *
 *
 *  prefix to postfix
 *  for i : exp.length-1 to 0
 *  Read the Prefix expression in reverse order (from right to left)
 *  If the symbol is an operand, then push it onto the Stack
 *  If the symbol is an operator, then pop two operands from the Stack , combine the operand and operator and push
 *  to stack.
 *  op1 +  op2 + operator <= this is the stirng concatenation
 *
 *
 *  postfix to prefix
 *  for i : 0 to exp.length-1
 *  Read the Prefix expression (from left to right)
 *  If the symbol is an operand, then push it onto the Stack
 *  If the symbol is an operator, then pop two operands from the Stack , combine the operand and operator and push
 *  to stack.
 *   operator + op2 +  op1 <= this is the stirng concatenation
 *
 *
 */
public class InfixToPostfix {

  public static void main(String[] args) {

    System.out.println(hasHigherPrecedence('-', '+'));

  }




  public static boolean hasHigherPrecedence(char op1, char op2){
    int op1Weight = GetOperatorWeight(op1);
    int op2Weight = GetOperatorWeight(op2);
    if (op1Weight == op2Weight){
      return true;
    }
    return op1Weight > op2Weight;
  }

  public static int GetOperatorWeight(char op){
    int weight = -1;
    switch (op)
    {
      case '+':
      case '-':
        weight = 1;
        break;
      case '*':
      case '/':
        weight = 2;
        break;
      case '^':
        weight = 3;
        break;
    }
    return weight;
  }
}
