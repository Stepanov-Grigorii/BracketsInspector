package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReader in = null;
        try {
            in = new FileReader(new File("../BracketsInspector/src/com/company/resources/text.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        LinkedList<Character> linkedList = new LinkedList<>();
        int ch;
        while (true) {
            assert in != null;
            if ((ch = in.read()) == -1) break;
            if (ch == (int) '(' | ch == (int) ')' | ch == (int) '{'
                    | ch == (int) '}' | ch == (int) '[' | ch == (int) ']') {
                linkedList.add((char) ch);
            }
        }
        System.out.println(bracketsInspector(linkedList));
    }

    static boolean bracketsInspector(LinkedList<Character> linkedList) {
        Stack<Character> stack = new Stack<>();
        LinkedList<Character> queue = new LinkedList<>();

        while (!linkedList.isEmpty()) {

            if (linkedList.peek() == '(' | linkedList.peek() == '{' | linkedList.peek() == '[') {
                if (!queue.isEmpty()) {
                    checkBrackets(queue, stack);
                }
                stack.push(linkedList.pop());
            } else {
                if (stack.size() == queue.size()) return false;
                queue.offer(linkedList.pop());
            }
        }
        if (queue.size() != stack.size()) return false;
        return checkBrackets(queue, stack);
    }

    static boolean checkBrackets(LinkedList<Character> queue, Stack<Character> stack) {
        while (!queue.isEmpty()) {
            int expression = (int) queue.pop() - (int) stack.pop();
            if (expression < 0 | expression > 2) return false;
        }
        return true;
    }
}

