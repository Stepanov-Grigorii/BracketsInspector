package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class Main {

    static final Map<Integer, Character> B_MAP = Map.of(
            1, '(',
            2, ')',
            3, '{',
            4, '}',
            5, '[',
            6, ']'
    );

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
            if (B_MAP.containsValue((char) ch)) linkedList.add((char) ch);
        }
        System.out.println(bracketsInspector(linkedList));
    }

    static boolean bracketsInspector(LinkedList<Character> linkedList) {
        Stack<Character> stack = new Stack<>();
        while (!linkedList.isEmpty()) {
            if (linkedList.peek() == B_MAP.get(1) || linkedList.peek() == B_MAP.get(3)
                    || linkedList.peek() == B_MAP.get(5)) {
                stack.push(linkedList.pop());
            } else {
                if (stack.isEmpty()) return false;
                if (!checkBrackets(linkedList, stack)) return false;
            }
        }
        return stack.isEmpty();
    }

    static boolean checkBrackets(LinkedList<Character> queue, Stack<Character> stack) {
        int expresion = (int) queue.pop() - (int) stack.pop();
        return (expresion > 0 && expresion < 3);
    }
}