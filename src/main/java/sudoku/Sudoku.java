package sudoku;


import java.util.ArrayList;
import java.util.List;

public class Sudoku {

    public static final int size = 9;

    private final Numbers[][] grid;

    public Sudoku(Numbers[][] grid) {
        this.grid = grid;
    }

    public List<Numbers> getRow(int r) {
        return List.of(grid[r]);
    }

    public List<Numbers> getColumn(int c) {
        var numbers = new ArrayList<Numbers>();
        for (int r = 0; r < size; r++) {
            numbers.add(grid[r][c]);
        }
        return numbers;
    }

    public List<Numbers> getBlock(int row, int col) {
        var numbers = new ArrayList<Numbers>();
        for (int r = row*3; r < row*3+3; r++) {
            for (int c = col*3; c < col*3+3; c++) {
                numbers.add(grid[r][c]);
            }
        }
        return numbers;
    }

}
