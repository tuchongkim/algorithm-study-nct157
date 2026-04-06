// 스택수열 / 실버2
// https://www.acmicpc.net/problem/1874

package week01_data_structure;

import java.util.Scanner;
import java.util.Stack;

public class _01_BOJ_P1874 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] givenNumbers = new int[n];

        for (int i = 0; i< n; i++) {
            givenNumbers[i] = sc.nextInt();
        }

        int curNum = 1; // 저장할 숫자를 트래킹
        boolean isValid = true;
        Stack<Integer> myStack = new Stack<>();
        StringBuffer sb = new StringBuffer();


        for (int i = 0; i < n; i++) {
            int givenNum = givenNumbers[i];

            if (givenNum >= curNum) {
                for (int j = curNum; j <= givenNum; j++) {
                    myStack.push(j);
                    sb.append("+\n");
                }
                myStack.pop();
                sb.append("-\n");
                curNum = givenNum + 1;
            } else {
                if (myStack.peek() < givenNum) {
                    isValid = false;
                    break;
                } else {
                    myStack.pop();
                    sb.append("-\n");
                }
            }
        }

        if (isValid) {
            System.out.println(sb);
        } else {
            System.out.println("NO");
        }
    }
}
