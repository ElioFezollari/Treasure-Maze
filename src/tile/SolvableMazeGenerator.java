package tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SolvableMazeGenerator {
    private int width;
    private int height;
    private int[][] maze;
    private Random random = new Random();
    private List<Coord> frontier = new ArrayList<>();

    public SolvableMazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new int[height][width];
    }

    public void generateSolvableMaze() {

        for (int i = 1; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = 1;
            }
        }


        int exitSide = random.nextInt(4);
        int startX, startY;

        switch (exitSide) {
            case 0:
                startX = random.nextInt(width);
                startY = 0;
                break;
            case 1:
                startX = width - 1;
                startY = random.nextInt(height);
                break;
            case 2:
                startX = random.nextInt(width);
                startY = height - 1;
                break;
            case 3:
                startX = 0;
                startY = random.nextInt(height);
                break;
            default:
                throw new RuntimeException("Invalid exit side");
        }

        maze[startY][startX] = 0;
        frontier.add(new Coord(startX, startY));


        while (!frontier.isEmpty()) {

            connectToAllNeighbors();
        }
        for (int j = 0; j < width; j++) {
            maze[0][j] = 1;
            maze[height - 1][j] = 1;
        }

        for (int i = 0; i < height; i++) {
            maze[i][0] = 1;
            maze[i][width - 1] = 1;
        }
        addRandomPath(2);
        for (int i = 3; i < 10; i++) {
            addRandomPath(i);
        }
        addRandomTiles(8,11);
        maze[25][15] = 0;
        maze[24][14] = 0;
        maze[24][16] = 0;
        maze[25][14] = 0;
        maze[26][15] = 0;
    }
    private void addRandomPath(int value) {
        int randomX, randomY;
        do {
            randomX = random.nextInt(width);
            randomY = random.nextInt(height);
        } while (maze[randomY][randomX] == 1);
    
        maze[randomY][randomX] = value;
    }
    
    private void addRandomTiles(int count, int value) {
        for (int i = 0; i < count; i++) {
            int randomX, randomY;
            do {
                randomX = random.nextInt(width);
                randomY = random.nextInt(height);
            } while (maze[randomY][randomX] != 1);

            maze[randomY][randomX] = value;
        }
    }

    private void connectToAllNeighbors() {
        int randomIndex = random.nextInt(frontier.size());
        Coord current = frontier.remove(randomIndex);

        List<Coord> neighbors = getUnvisitedNeighbors(current.x, current.y);

        for (Coord neighbor : neighbors) {
            int wallX = (current.x + neighbor.x) / 2;
            int wallY = (current.y + neighbor.y) / 2;
            maze[wallY][wallX] = 0;

            maze[neighbor.y][neighbor.x] = 0;

            frontier.add(neighbor);
        }
    }

    private List<Coord> getUnvisitedNeighbors(int x, int y) {
        List<Coord> neighbors = new ArrayList<>();

        if (y > 1 && maze[y - 2][x] == 1) neighbors.add(new Coord(x, y - 2));
        if (y < height - 2 && maze[y + 2][x] == 1) neighbors.add(new Coord(x, y + 2));
        if (x > 1 && maze[y][x - 2] == 1) neighbors.add(new Coord(x - 2, y));
        if (x < width - 2 && maze[y][x + 2] == 1) neighbors.add(new Coord(x + 2, y));

        return neighbors;
    }




    private static class Coord {
        int x, y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int getMazeValue(int x, int y) {
        return maze[y][x];
    }
}