import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import edu.princeton.cs.algs4.StdOut;

public class LinkedStackOfStrings {

    private Node first = null;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(String item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }

    public static void main(String[] args) {
        LinkedStackOfStrings stack = new LinkedStackOfStrings();

        String projectRoot = System.getProperty("user.dir");
        String filePath = projectRoot + "/part1/Stack/tobe.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            // 逐行读取文件内容并将其推入或弹出堆栈
            while ((line = reader.readLine()) != null) {
                if (line.equals("-"))
                    StdOut.print(stack.pop());
                else {
                    stack.push(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
