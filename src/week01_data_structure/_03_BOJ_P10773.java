// 제로 / 실버4
// https://www.acmicpc.net/problem/10773

package week01_data_structure;

import java.util.Scanner;
import java.util.Stack;

public class _03_BOJ_P10773 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        Stack<Integer> myStack = new Stack<>();

        for (int i = 0; i < K; i++) {
            int curNum = sc.nextInt();

            if (curNum == 0) {
                myStack.pop();
            } else {
                myStack.push(curNum);
            }
        }

        int answer = 0;

        while(!myStack.isEmpty()) {
            answer += myStack.pop();
        }
        System.out.println(answer);
    }
}
