package main.java.shortestPath;

import java.util.LinkedList;
import java.util.Queue;

public class Itm {

    public enum KnightMove {
        UP_LEFT(-1, -2),
        UP_RIGHT(-1, 2),
        DOWN_LEFT(1, -2),
        DOWN_RIGHT(1, 2),
        LEFT_UP(-2, -1),
        LEFT_DOWN(-2, 1),
        RIGHT_UP(2, -1),
        RIGHT_DOWN(2, 1);
        private final int rowChange;
        private final int colChange;
        KnightMove(int rowChange, int colChange) {
            this.rowChange = rowChange;
            this.colChange = colChange;
        }
    }

    public int minKnightMoves(int N, int M, int R, int C, int S, int K){
        int[][] visit = new int[N][M];

        Queue<Position> q = new LinkedList<>();
        Position startPosition = new Position(R, C, 0);
        q.add(startPosition);
        visit[R][C] = 1;

        while(!q.isEmpty()){
            Position currentPosition = q.poll();
            if(currentPosition.row == S && currentPosition.column == K) {
                return currentPosition.count;
            }
            for(KnightMove dir: KnightMove.values()){
                int newRow = currentPosition.row + dir.rowChange;
                int newCol = currentPosition.column + dir.colChange;
                int newCount = currentPosition.count+1;
                if(
                        (newRow >= 0 && newRow < N)
                        && (newCol >= 0 && newCol < M)
                        && (visit[newRow][newCol] == 0)
                ){
                    visit[newRow][newCol] = 1;
                    q.add(new Position(newRow,newCol,newCount));
                }
            }
        }
        return -1;
    }

    public class Position{
        int row;
        int column;
        int count;
        public Position(int row, int column, int count){
            this.row = row;
            this.column = column;
            this.count = count;
        }
    }


}
