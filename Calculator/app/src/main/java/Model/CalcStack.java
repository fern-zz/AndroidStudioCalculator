package Model;

import java.util.LinkedList;

public class CalcStack extends LinkedList<String> {

    public String pop() {
        return super.pop();
    }

    public String peek() {
        return super.peek();
    }

    public void clear() {
        super.clear();
    }

    public int size() {
        return super.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < size(); i++) {
            sb.append(get(i)).append("\n");
        }
        return sb.toString();
    }
    public void push(String s) {
        super.push(s);
    }
    public void push(char s) {
        super.push(String.valueOf(s));
    }
    public void push(Long s) {
        super.push(String.valueOf(s));
    }
}

