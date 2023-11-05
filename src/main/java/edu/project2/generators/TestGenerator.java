package edu.project2.generators;

import edu.project2.structures.Cell;
import edu.project2.structures.Coordinate;
import edu.project2.structures.Maze;
import edu.project2.structures.Type;

public class TestGenerator implements Generator {
    @Override
    public Maze generate(int height, int width) {
        checkParam(height);
        checkParam(width);
        Cell[][] grid = new Cell[height][width];
        for (int i = 1; i < height - 1; ++i) {
            for (int j = 1; j < width - 1; ++j) {
                grid[i][j] = new Cell(new Coordinate(i, j), Type.PASSAGE);
            }
        }
        for (int i = 0; i < height; i++) {
            grid[i][0] = new Cell(new Coordinate(i, 0), Type.WALL);
            grid[i][width - 1] = new Cell(new Coordinate(i, width - 1), Type.WALL);
        }
        for (int j = 0; j < width; j++) {
            grid[0][j] = new Cell(new Coordinate(0, j), Type.WALL);
            grid[height - 1][j] = new Cell(new Coordinate(height - 1, j), Type.WALL);
        }
        return new Maze(height, width, grid);
    }

    private void checkParam(int param) {
        if (param < 0 || param % 2 == 0) {
            throw new IllegalArgumentException("Illegal parameter!");
        }
    }
}
