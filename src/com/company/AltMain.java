package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Stack;

public class AltMain {
    public static void main(String[] args) throws IOException {
        String strFileContent = Files.readString(Path.of("../BracketsInspector/src/com/company/resources/text.txt"));
        System.out.println("strFileContent = " + strFileContent);
        boolean check = check(strFileContent);
        System.out.println("check = " + check);
    }

    static boolean check(String content) {
        Map<Character, Character> mapOfBrackets = Map.of(
                ')', '(',
                '}', '{',
                ']', '['
        );
        Stack<Character> brackets = new Stack<>();
        for (Character current : content.toCharArray()) {
            if (mapOfBrackets.containsValue(current)) {
                brackets.push(current);
            } else if (mapOfBrackets.containsKey(current) && (brackets.isEmpty() || !brackets.pop().equals(mapOfBrackets.get(current)))) {
                return false;
            }
        }
        return brackets.isEmpty();
    }
}
