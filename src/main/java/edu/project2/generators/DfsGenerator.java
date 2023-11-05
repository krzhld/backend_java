package edu.project2.generators;

import edu.project2.structures.Cell;
import edu.project2.structures.Coordinate;
import edu.project2.structures.Maze;
import edu.project2.structures.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DfsGenerator implements Generator {
    private final static Random RANDOMIZER = new Random();
    private final static int[] NEIGHBOUR_ROWS = {0, -2, 0, 2};
    private final static int[] NEIGHBOUR_COLS = {-2, 0, 2, 0};

    public DfsGenerator() {

    }

    @SuppressWarnings("MagicNumber")
    @Override
    public Maze generate(int height, int width) {
        checkParam(height);
        checkParam(width);
        Cell[][] grid = generateStartGrid(height, width);
        generateMazeGrid(grid);
        return new Maze(height, width, grid);
    }

    private void generateMazeGrid(Cell[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Cell startCell = grid[1][1];
        depthFirstSearch(grid, startCell, visited);
    }

    private Cell[][] generateStartGrid(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i % 2 != 0) && (j % 2 != 0) && (i < height - 1) && (j < width - 1)) {
                    grid[i][j] = new Cell(new Coordinate(i, j), Type.PASSAGE);
                } else {
                    grid[i][j] = new Cell(new Coordinate(i, j), Type.WALL);
                }
            }
        }
        return grid;
    }

    private void depthFirstSearch(Cell[][] grid, Cell currentCell, boolean[][] visited) {
        Coordinate coordinate = currentCell.coordinate();
        visited[coordinate.row()][coordinate.col()] = true;
        List<Cell> neighbourCells = getCellNeighbours(coordinate, grid);

        while (!neighbourCells.isEmpty()) {
            int choice = RANDOMIZER.nextInt(neighbourCells.size());
            Cell neighbourCell = neighbourCells.get(choice);
            Coordinate neighbourCoordinate = neighbourCell.coordinate();

            if (!visited[neighbourCell.coordinate().row()][neighbourCell.coordinate().col()]) {
                Coordinate wallCoordinate = removeWall(coordinate, neighbourCell.coordinate(), grid);
                visited[wallCoordinate.row()][wallCoordinate.col()] = true;

                depthFirstSearch(grid, neighbourCell, visited);
            }
            neighbourCells.remove(neighbourCoordinate);
        }
    }

    private List<Cell> getCellNeighbours(Coordinate coordinate, Cell[][] grid) {
        List<Cell> neighbourCells = new ArrayList<>(NEIGHBOUR_ROWS.length);
        int row = coordinate.row();
        int col = coordinate.col();
        for (int i = 0; i < NEIGHBOUR_ROWS.length; i++) {
            int rowCoordinate = row + NEIGHBOUR_ROWS[i];
            int colCoordinate = col + NEIGHBOUR_COLS[i];
            if (rowCoordinate >= grid.length
                || rowCoordinate < 0
                || colCoordinate >= grid[0].length
                || colCoordinate < 0) {
                continue;
            }
            neighbourCells.add(grid[rowCoordinate][colCoordinate]);
        }
        return neighbourCells;
    }

    private Coordinate removeWall(Coordinate currentCoordinate, Coordinate neighbourCoordinate, Cell[][] grid) {
        Cell cell;
        if (currentCoordinate.row() == neighbourCoordinate.row()) {
            Coordinate tempCoordinate = grid[currentCoordinate.row()][(currentCoordinate.col() + neighbourCoordinate.col()) / 2].coordinate();
            cell = new Cell(tempCoordinate, Type.PASSAGE);
            grid[currentCoordinate.row()][(currentCoordinate.col() + neighbourCoordinate.col()) / 2] = cell;
        } else {
            Coordinate tempCoordinate = grid[(currentCoordinate.row() + neighbourCoordinate.row()) / 2][currentCoordinate.col()].coordinate();
            cell = new Cell(tempCoordinate, Type.PASSAGE);
            grid[(currentCoordinate.row() + neighbourCoordinate.row()) / 2][currentCoordinate.col()] = cell;
        }
        return cell.coordinate();
    }

    private void checkParam(int param) {
        if (param < 0 || param % 2 == 0) {
            throw new IllegalArgumentException("Illegal parameter!");
        }
    }
}
