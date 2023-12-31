package edu.project2.solvers;

import edu.project2.pojo.Coordinate;
import edu.project2.pojo.Maze;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate startCoordinate, Coordinate endCoordinate);
}
