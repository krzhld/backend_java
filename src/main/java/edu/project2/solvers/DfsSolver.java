package edu.project2.solvers;

import edu.project2.structures.Cell;
import edu.project2.structures.Coordinate;
import edu.project2.structures.Maze;
import edu.project2.structures.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DfsSolver implements Solver {
    private final static Random RANDOMIZER = new Random();
    private final static int[] NEIGHBOUR_ROWS = {0, -1, 0, 1};
    private final static int[] NEIGHBOUR_COLS = {-1, 0, 1, 0};

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate startCoordinate, Coordinate endCoordinate) {
        List<Coordinate> answer = new ArrayList<>();
        Cell[][] grid = maze.grid();
        checkParams(startCoordinate, grid);
        checkParams(endCoordinate, grid);
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        depthFirstSearch(startCoordinate, endCoordinate, grid, visited, answer);
        if (!answer.contains(endCoordinate)) {
            throw new IllegalStateException("Maze cannot be solved!");
        }
        return answer;
    }

    private boolean depthFirstSearch(
        Coordinate currentCoordinate,
        Coordinate endCoordinate,
        Cell[][] grid,
        boolean[][] visited,
        List<Coordinate> answer) {
        if (currentCoordinate.equals(endCoordinate)) {
            answer.add(currentCoordinate);
            return true;
        }
        visited[currentCoordinate.row()][currentCoordinate.col()] = true;
        List<Coordinate> neighbours = getCellNeighbours(currentCoordinate, grid);
        while (!neighbours.isEmpty()) {
            int random = RANDOMIZER.nextInt(neighbours.size());
            Coordinate neighbour = neighbours.get(random);
            if (!visited[neighbour.row()][neighbour.col()]) {
                boolean isEnd = depthFirstSearch(neighbour, endCoordinate, grid, visited, answer);
                if (isEnd) {
                    answer.add(currentCoordinate);
                    return true;
                }
            }
            neighbours.remove(neighbour);
        }
        return false;
    }

    private List<Coordinate> getCellNeighbours(Coordinate coordinate, Cell[][] grid) {
        List<Coordinate> neighbours = new ArrayList<>(NEIGHBOUR_ROWS.length);
        int row = coordinate.row();
        int col = coordinate.col();
        for (int i = 0; i < NEIGHBOUR_ROWS.length; i++) {
            int rowCoordinate = row + NEIGHBOUR_ROWS[i];
            int colCoordinate = col + NEIGHBOUR_COLS[i];
            if (rowCoordinate >= grid.length
                || rowCoordinate < 0
                || colCoordinate >= grid[0].length
                || colCoordinate < 0
                || grid[rowCoordinate][colCoordinate].type().equals(Type.WALL)) {
                continue;
            }
            neighbours.add(grid[rowCoordinate][colCoordinate].coordinate());
        }
        return neighbours;
    }

    private void checkParams(Coordinate coordinate, Cell[][] grid) {
        if (coordinate.row() < 0
            || coordinate.row() >= grid.length
            || coordinate.col() < 0
            || coordinate.col() >= grid[0].length
            || grid[coordinate.row()][coordinate.col()].type().equals(Type.WALL)) {
            throw new IllegalArgumentException("Illegal parameter!");
        }
    }
}
