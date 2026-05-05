// 아이템 줍기 / Lv.3
// https://school.programmers.co.kr/learn/courses/30/lessons/87694

package week04_dfs_bfs;

import java.util.*;

public class _03_PGS_아이템줍기_1차시도 {
    public static void main(String[] args) {
        int[][] rect1 = {
                {1, 1, 7, 4},
                {3, 2, 5, 5},
                {4, 3, 6, 9},
                {2, 6, 8, 8}
        };

        int[][] rect2 = {
                {1, 1, 8, 4},
                {2, 2, 4, 9},
                {3, 6, 9, 8},
                {6, 3, 7, 7}
        };

        int[][] rect3 = {
                {1, 1, 5, 7}
        };

        int[][] rect4 = {
                {2, 1, 7, 5},
                {6, 4, 10, 10}
        };

        int[][] rect5 = {
                {2, 2, 5, 5},
                {1, 3, 6, 4},
                {3, 1, 4, 6}
        };

        Solution sol = new Solution();

        System.out.println(sol.solution(rect1, 1, 3, 7, 8)); // 0 != 17(expected)
        System.out.println(sol.solution(rect2, 9, 7, 6, 1)); // 0 != 11(expected)
        System.out.println(sol.solution(rect3, 1, 1, 4, 7)); // 9 == 9(expected)
        System.out.println(sol.solution(rect4, 3, 1, 7, 10)); // 0 != 15(expected)
        System.out.println(sol.solution(rect5, 1, 4, 6, 3)); // 10 == 10(expected)
    }

    static class Solution {
        static int[][][] board;
        static int[][] rectangle;
        static boolean[][] visited;
        static int answer;

        public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
            // 최단거리 -> BFS
            /*
            접근 1. 각 직사각형의 둘레에 존재하는 점들을 ArrayList에 넣고, 새로운 직사각형이 들어올 때마다 업데이트
                -> 이 경우 나중에 최단거리를 찾는데 어려움이 있을 것으로 예상
            접근 2. 좌표값의 상한선이 50이므로, 전체 board를 선언하고 유효한 값들을 true로 설정
                -> 추후에 BFS로 탐색하기 용이
            */

            // 1. 초기화
            Solution.rectangle = rectangle;
            board = new int[51][51][5];
            visited = new boolean[51][51];
            answer = 0;

            // 2. 각 직사각형의 테두리에 있는 모든 점들을 순회하면서 유효한 위치와 방향 저장
            // board[x][y][] 에서 마지막 배열에 {위치 유효여부, 오른쪽, 아래쪽, 왼쪽, 위쪽} 순서대로 유효성 저장
            // 유효한 경우 = 1, 유효하지 않은 경우 = 0
            for (int i = 0; i < rectangle.length; i++) {
                setValidCoordinates(rectangle[i], i);
            }

            // 출력 확인용 코드
//            for (int y = 10; y >= 0; y--) {
//                for (int x = 0; x <= 10; x++) {
//                    System.out.print(board[x][y][0]);
//                }
//                System.out.println();
//            }

            // 3. bfs를 이용해서 board를 순회
            bfs(characterX, characterY, itemX, itemY);

            return answer;
        }

        public static void bfs(int startX, int startY, int targetX, int targetY) {
            Queue<int[]> myQueue = new ArrayDeque<>();
            visited[startX][startY] = true;
            myQueue.offer(new int[]{startX, startY, 0}); //마지막 숫자는 depth

            while (!myQueue.isEmpty()) {
                int[] curPos = myQueue.poll();
                int curX = curPos[0];
                int curY = curPos[1];
                int depth = curPos[2];

                // 4. 종료조건: target 위치에 도달한 경우
                if (curX == targetX && curY == targetY) {
                    answer = depth;
                    break;
                }

                int[] validDirections = board[curX][curY];

                // 우, 하, 좌, 상 위치 중에서 유효한 방향이 있다면 해당 위치 탐색
                for (int i = 1; i <= 4; i++) {
                    int newX = curX;
                    int newY = curY;

                    if (validDirections[i] == 1) {
                        switch (i) {
                            case 1:
                                newX = curX + 1;
                                break;
                            case 3:
                                newX = curX - 1;
                                break;
                            case 2:
                                newY = curY - 1;
                                break;
                            case 4:
                                newY = curY + 1;
                                break;
                        }

                        if (!visited[newX][newY]) {
                            visited[newX][newY] = true;
                            myQueue.offer(new int[]{newX, newY, depth + 1});
                        }
                    }
                }
            }
        }

        public static void setValidCoordinates(int[] rectPos, int curIndex) {
            int x1 = rectPos[0];
            int y1 = rectPos[1];
            int x2 = rectPos[2];
            int y2 = rectPos[3];
            boolean[] checkedDirections;

            checkedDirections = checkOutline(x1, y1, curIndex);
            if (checkedDirections[0]) {
                board[x1][y1][0] = 1;
                board[x1][y1][1] = 1;
                board[x1][y1][4] = 1;
            }

            checkedDirections = checkOutline(x2, y1, curIndex);
            if (checkedDirections[0]) {
                board[x2][y1][0] = 1;
                board[x2][y1][3] = 1;
                board[x2][y1][4] = 1;
            }

            checkedDirections = checkOutline(x1, y2, curIndex);
            if (checkedDirections[0]) {
                board[x1][y2][0] = 1;
                board[x1][y2][1] = 1;
                board[x1][y2][2] = 1;
            }

            checkedDirections = checkOutline(x2, y2, curIndex);
            if (checkedDirections[0]) {
                board[x2][y2][0] = 1;
                board[x2][y2][3] = 1;
                board[x2][y2][2] = 1;
            }


            if (x1 + 1 != x2) {
                for (int i = x1 + 1; i < x2; i++) {
                    checkedDirections = checkOutline(i, y1, curIndex);
                    if (checkedDirections[0]) {
                        board[i][y1][0] = 1;
                        board[i][y1][1] = 1;
                        board[i][y1][3] = 1;
                        for (int j = 0; j < 5; j++) {
                            if (checkedDirections[j] == false) {
                                board[i][y1][j] = 0;
                            }
                        }
                    }

                    checkedDirections = checkOutline(i, y2, curIndex);
                    if (checkedDirections[0]) {
                        board[i][y2][0] = 1;
                        board[i][y2][1] = 1;
                        board[i][y2][3] = 1;
                        for (int j = 0; j < 5; j++) {
                            if (checkedDirections[j] == false) {
                                board[i][y2][j] = 0;
                            }
                        }
                    }
                }
            }

            if (y1 + 1 != y2) {
                for (int i = y1 + 1; i < y2; i++) {
                    checkedDirections = checkOutline(x1, i, curIndex);
                    if (checkedDirections[0]) {
                        board[x1][i][0] = 1;
                        board[x1][i][2] = 1;
                        board[x1][i][4] = 1;
                        for (int j = 0; j < 5; j++) {
                            if (checkedDirections[j] == false) {
                                board[x1][i][j] = 0;
                            }
                        }
                    }
                    checkedDirections = checkOutline(x2, i, curIndex);
                    if (checkedDirections[0]) {
                        board[x2][i][0] = 1;
                        board[x2][i][2] = 1;
                        board[x2][i][4] = 1;
                        for (int j = 0; j < 5; j++) {
                            if (checkedDirections[j] == false) {
                                board[x2][i][j] = 0;
                            }
                        }
                    }
                }
            }
        }

        public static boolean[] checkOutline(int x, int y, int curIndex) {
            boolean[] checkedDirections = new boolean[5];
            Arrays.fill(checkedDirections, true);

            for (int i = 0; i < rectangle.length; i++) {
                if (i == curIndex) continue;
                if (x > rectangle[i][0] && x < rectangle[i][2]) {
                    if (y > rectangle[i][1] && y < rectangle[i][3]) {
                        //다른 직사각형 내부에 현재 점이 존재
                        Arrays.fill(checkedDirections, false);
                        return checkedDirections;
                    }
                }

                if (x == rectangle[i][0]) {
                    checkedDirections[1] = false;
                }
                if (x == rectangle[i][2]) {
                    checkedDirections[3] = false;
                }
                if (y == rectangle[i][1]) {
                    checkedDirections[4] = false;
                }
                if (y == rectangle[i][3]) {
                    checkedDirections[2] = false;
                }
            }
            return checkedDirections;
        }
    }
}