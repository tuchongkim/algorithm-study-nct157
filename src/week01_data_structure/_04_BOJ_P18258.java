// 큐 2 / 실버4
// https://www.acmicpc.net/problem/18258

package week01_data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class _04_BOJ_P18258 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> myDeque = new ArrayDeque<>();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < N; i++) {
            String command = br.readLine();

            if (command.startsWith("push")) {
                int X = Integer.parseInt(command.substring(5).trim());
                myDeque.offerLast(X);
                continue;
            }

            switch (command) {
                case "pop":
                    if (myDeque.isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        sb.append(myDeque.pollFirst()).append("\n");
                    }
                    break;
                case "size":
                    sb.append(myDeque.size()).append("\n");
                    break;
                case "empty":
                    if (myDeque.isEmpty()) {
                        sb.append("1\n");
                    } else {
                        sb.append("0\n");
                    }
                    break;
                case "front":
                    if (myDeque.isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        sb.append(myDeque.peekFirst()).append("\n");
                    }
                    break;
                case "back":
                    if (myDeque.isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        sb.append(myDeque.peekLast()).append("\n");
                    }
                    break;
            }
        }

        System.out.println(sb);
    }
}
