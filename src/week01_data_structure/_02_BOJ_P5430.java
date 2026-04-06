// AC / 골드5
// https://www.acmicpc.net/problem/5430

package week01_data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class _02_BOJ_P5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            char[] methods = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());
            Deque<Integer> numbers = new ArrayDeque<>();

            String numbersStr = br.readLine();
            StringTokenizer st = new StringTokenizer(numbersStr.substring(1, numbersStr.length()-1), ",");

            while (st.hasMoreTokens()) {
                numbers.offerLast(Integer.parseInt(st.nextToken()));
            }

            // 여기서부터 로직 시작
            int determinator = 0;
            boolean isError = false;

            for (char method: methods) {
                if (method == 'R') {
                    determinator = (determinator + 1) % 2;
                }
                else if (method == 'D') {
                    if (numbers.isEmpty()) {
                        isError = true;
                        break;
                    }
                    if (determinator == 0) {
                        numbers.pollFirst();
                    }
                    else if (determinator == 1) {
                        numbers.pollLast();
                    }
                }
            }

            // 출력
            if (isError) {
                System.out.println("error");
            } else {
                StringBuffer sb = new StringBuffer();
                sb.append("[");

                if (determinator == 0) {
                    while(!numbers.isEmpty()) {
                        sb.append(numbers.pollFirst());
                        if (!numbers.isEmpty()) {
                            sb.append(",");
                        }
                    }
                } else {
                    while(!numbers.isEmpty()) {
                        sb.append(numbers.pollLast());
                        if (!numbers.isEmpty()) {
                            sb.append(",");
                        }
                    }
                }

                sb.append("]");
                System.out.println(sb);
            }
        }
    }
}
