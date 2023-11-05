package edu.project2.renderers;

import edu.project2.structures.Cell;
import edu.project2.structures.Coordinate;
import edu.project2.structures.Maze;
import edu.project2.structures.Type;
import java.util.List;

public class ConsoleRenderer implements Renderer {

    private final static char WALL_SYMBOL = '█';
    private final static char PASSAGE_SYMBOL = ' ';
    private final static char PATH_SYMBOL = 'x';

    @Override
    public String render(Maze maze) {
        StringBuilder gridStrBuilder = new StringBuilder();
        Cell[][] grid = maze.grid();
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                if (grid[i][j].type().equals(Type.WALL)) {
                    gridStrBuilder.append(WALL_SYMBOL);
                } else {
                    gridStrBuilder.append(PASSAGE_SYMBOL);
                }
            }
            gridStrBuilder.append("\n");
        }
        return gridStrBuilder.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder gridStrBuilder = new StringBuilder();
        Cell[][] grid = maze.grid();
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                if (path.contains(new Coordinate(i, j))) {
                    gridStrBuilder.append(PATH_SYMBOL);
                } else if (grid[i][j].type().equals(Type.WALL)) {
                    gridStrBuilder.append(WALL_SYMBOL);
                } else {
                    gridStrBuilder.append(PASSAGE_SYMBOL);
                }
            }
            gridStrBuilder.append("\n");
        }
        return gridStrBuilder.toString();
    }
}
