package com.example.calculator;
import java.util.Stack;

public class Calculator {
    /***
     * Method with stack to evaluate a compound expression
     *
     * @Expression Expression this is our string with values wich we will change
     *
     * @return We will return answer for this string value
     *
     * @IllegalArgumentException This is information about the error so that it is clear why this or that problem occurs
     * */
    public static int evaluate(String expression) {
        char[] arr = expression.replaceAll(" ", "").toCharArray();
        Stack<Integer> value = new Stack<>();
        Stack<Character> chr = new Stack<>();
        for(int i=0;i<arr.length;i++){
            if(arr[i]>='0' && arr[i]<='9' || (arr[i]=='-' && (i==0 || arr[i-1] =='(' || isSign(arr[i-1]))))
            {
                StringBuilder sb = new StringBuilder();
                if(arr[i]=='-'){
                    sb.append(arr[i++]);
                }

                while (i<arr.length && arr[i]>='0' && arr[i]<='9')
                {
                    sb.append(arr[i++]);
                }
                i--;
                value.push(Integer.parseInt(sb.toString()));
            }
            else if(arr[i]=='(')
            {
                chr.push(arr[i]);
            }
            else if(arr[i] == ')')
            {
                while (!chr.isEmpty() && chr.peek() != '(')
                {
                    value.push(calc(chr.pop(), value.pop(), value.pop()));
                }
                if(!chr.isEmpty() && chr.peek() =='(')
                {
                    chr.pop();
                }
                else {
                    throw new IllegalArgumentException("The parentheses are not placed correctly!");
                }
            }
            else if(isSign(arr[i]))
            {
                while (!chr.isEmpty() && signPriority(arr[i], chr.peek()))
                {
                    value.push(calc(chr.pop(), value.pop(), value.pop()));
                }
                chr.push(arr[i]);
            }
            else
            {
                throw new IllegalArgumentException("Unknown symbol!");
            }
        }
        while (!chr.isEmpty() && chr.peek() !='(')
        {
            value.push(calc(chr.pop(), value.pop(), value.pop()));
        }
        if(!chr.isEmpty() && value.size()!=1) throw new IllegalArgumentException("Argument incorrect!");
        return value.pop();
    }

    /**
     * Method for calculation our values
     *
     * @Sign Character which has our sign
     *
     * @a The number by which we will divide, multiply, etc.
     *
     * @b The number by which we will divide, multiply, etc.
     * **/
    // first parameter is b, because when we take information we take firstly value in stack, so for correct arifmetic calculation
    // we ought to put b after a, when we divide, multiply, etc.
    // for example: "b/a"
    private static int calc(char sign, int b, int a)
    {
        switch (sign){
            case '+':  return a+b;
            case '-':  return a-b;
            case '*':  return a*b;
            case '/':
                if(b==0) throw new IllegalArgumentException("divide for 0");
                return a/b;
        }
        return 0;
    }

    /**
     * Boolean which helps us understand that we have our multiplication, division, etc. signs in our stack of symbols.
     *
     * @sign Character for our symbol
     * **/
    private  static boolean isSign(char sign)
    {
        return sign == '+' || sign == '-' || sign == '*' || sign == '/';
    }

    /**
     * Boolean variable that helps us understand which sign to use according to the rules of addition in mathematics
     *
     * @chr1 Character for our symbol in massive
     * @chr2 Character for our symbol in stack
     * **/
    private static boolean signPriority(char chr1, char chr2)
    {
        if(chr2 == '(' || chr2 == ')') return false;

        return (chr1 != '*' && chr1!= '/') || (chr2 != '+' && chr2!='-');
    }
}
