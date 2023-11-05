package edu.project2;

import edu.project2.generators.DfsGenerator;
import edu.project2.generators.Generator;
import edu.project2.generators.TestGenerator;
import edu.project2.renderers.ConsoleRenderer;
import edu.project2.renderers.Renderer;
import edu.project2.solvers.DfsSolver;
import java.util.List;
import edu.project2.structures.Cell;
import edu.project2.structures.Coordinate;
import edu.project2.structures.Maze;
import edu.project2.structures.Type;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class MazeSolverTest {

    @Test
    void testRendererWithKnownMaze() {
        Generator generator = new TestGenerator();
        Maze maze = generator.generate(7, 7);
        Cell[][] grid = maze.grid();
        grid[2][2] = new Cell(new Coordinate(2, 2), Type.WALL);
        grid[4][4] = new Cell(new Coordinate(4, 4), Type.WALL);

        Renderer renderer = new ConsoleRenderer();
        String result = renderer.render(maze);

        String expectedResult = """
            ███████
            █     █
            █ █   █
            █     █
            █   █ █
            █     █
            ███████
            """;
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDfsSolverWithTestGeneratorMaze() {
        int height = 5;
        int width = 5;
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(3, 3);

        var generator = new TestGenerator();
        var solver = new DfsSolver();
        Maze maze = generator.generate(height, width);
        List<Coordinate> result = solver.solve(maze, start, end);

        assertThat(result).contains(start, end);
    }

    @Test
    void testTestGeneratorWithIllegalParams() {
        int height = -1;
        int width = 0;

        var generator = new TestGenerator();
        assertThatIllegalArgumentException().isThrownBy(() -> generator.generate(height, width));
    }

    @Test
    void testDfsGeneratorWithIllegalParams() {
        int height = -1;
        int width = 0;

        var generator = new DfsGenerator();
        assertThatIllegalArgumentException().isThrownBy(() -> generator.generate(height, width));
    }

    @Test
    void testDfsSolverWhenExistSolution() {
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(3, 5);
        Generator testGenerator = new TestGenerator();
        Maze maze = testGenerator.generate(5, 7);
        Cell[][] grid = maze.grid();
        grid[2][1] = new Cell(new Coordinate(2, 1), Type.WALL);
        grid[2][2] = new Cell(new Coordinate(2, 2), Type.WALL);
        grid[2][3] = new Cell(new Coordinate(2, 3), Type.WALL);
        grid[2][4] = new Cell(new Coordinate(2, 4), Type.WALL);

        DfsSolver solver = new DfsSolver();
        List<Coordinate> path = solver.solve(maze, start, end);
        assertThatCode(() -> solver.solve(maze, start, end)).doesNotThrowAnyException();

        Renderer renderer = new ConsoleRenderer();
        String result = renderer.render(maze, path);

        String expectedResult = """
            ███████
            █xxxxx█
            █████x█
            █    x█
            ███████
            """;
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDfsSolverWhenNotExistSolution() {
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(3, 5);
        Generator testGenerator = new TestGenerator();
        Maze maze = testGenerator.generate(5, 7);
        Cell[][] grid = maze.grid();
        grid[2][1] = new Cell(new Coordinate(2, 1), Type.WALL);
        grid[2][2] = new Cell(new Coordinate(2, 2), Type.WALL);
        grid[2][3] = new Cell(new Coordinate(2, 3), Type.WALL);
        grid[2][4] = new Cell(new Coordinate(2, 4), Type.WALL);
        grid[2][5] = new Cell(new Coordinate(2, 5), Type.WALL);

        DfsSolver solver = new DfsSolver();
        assertThatIllegalStateException().isThrownBy(() -> solver.solve(maze, start, end));

        //    ███████
        //    █x    █
        //    ███████
        //    █    x█
        //    ███████

    }

    @Test
    void testDfsSolverWithIllegalParams() {
        int height = 15;
        int width = 15;
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(20, 20);
        Generator generator = new TestGenerator();

        Maze maze = generator.generate(height, width);

        var solver = new DfsSolver();
        assertThatIllegalArgumentException().isThrownBy(() -> solver.solve(maze, start, end));
    }
}
