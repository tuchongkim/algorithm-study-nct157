// 타겟 넘버 / Lv.2
// https://school.programmers.co.kr/learn/courses/30/lessons/43165

package week03_dfs_bfs;

public class _01_PGS_타겟_넘버_v2 {
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

        public int solution(int[] numbers, int target) {
            // 완전탐색으로 풀 수 있는가? -> 2^20 = 대략 100만 (가능)
            // 모든 숫자를 사용해야 함 -> 종료 조건을 길이로

            answer = 0;
            targetNum = target;
            numbersArr = numbers;

            dfs(0, 0);

            return answer;
        }

        public static void dfs(int node, int curSum) {
            if (node == numbersArr.length) {
                if (curSum == targetNum) answer++;
                return;
            }
            dfs(node + 1, curSum - numbersArr[node]);
            dfs(node + 1, curSum + numbersArr[node]);
        }
    }
}