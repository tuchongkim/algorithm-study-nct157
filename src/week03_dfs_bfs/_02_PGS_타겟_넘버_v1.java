// 타겟 넘버 / Lv.2
// https://school.programmers.co.kr/learn/courses/30/lessons/43165

package week03_dfs_bfs;

public class _02_PGS_타겟_넘버_v1 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int [] numbers1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        int [] numbers2 = {4, 1, 2, 1};
        int target2 = 4;

        System.out.println(solution.solution(numbers1, target1)); // 5 == 5
        System.out.println(solution.solution(numbers2, target2)); // 2 == 2
    }

    static class Solution {
        static int[] numbersArr;
        static int answer;
        static int targetNum;
        static int totalSum;

        public static int solution(int[] numbers, int target) {
            answer = 0;
            targetNum = target;
            numbersArr = numbers;

            totalSum = 0;
            for (int num: numbers) {
                totalSum += num;
            }

            for (int i = 0; i < numbers.length; i++) {
                dfs(i, totalSum);
            }
            return answer;
        }

        public static void dfs(int node, int curSum) {
            if (curSum - 2 * numbersArr[node] == targetNum) {
                answer++;
                return;
            } else if (curSum - 2 * numbersArr[node] < targetNum) {
                return;
            } else {
                for (int i = node + 1; i < numbersArr.length; i++) {
                    dfs(i, curSum - 2 * numbersArr[node]);
                }
            }
        }
    }
}
