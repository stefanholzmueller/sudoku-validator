package sudoku;

import io.vavr.Value;
import io.vavr.collection.*;
import io.vavr.control.Validation;


public class Validator {

    public static final String errorLessThan9Lines = "File does not have 9 lines";
    public static final String errorLineNot9Columns = "Line does not have the expected CSV format with 9 columns: ";
    public static final String errorInvalidNumber = "Not a valid number: ";
    public static final String errorInvalidRow = "Invalid set of numbers in row ";
    public static final String errorInvalidColumn = "Invalid set of numbers in column ";
    public static final String errorInvalidBlock = "Invalid set of numbers in block ";

    private static final Set<Numbers> fullSet = HashSet.of(Numbers.values());
    private static final List<Integer> list0to8 = Stream.from(0).take(Sudoku.size).toList();

    public static List<String> validate(List<String> csv) {
        if (csv.size() < Sudoku.size)
            return List.of(errorLessThan9Lines);
        else {
            return parseStructure(csv).fold(Value::toList, Validator::validateSudoku);
        }
    }

    static Validation<Seq<String>, Numbers[][]> parseStructure(List<String> lines) {
        return Validation.traverse(lines, Validator::parseLine).map(a -> a.toJavaArray(Numbers[][]::new));
    }

    private static Validation<Seq<String>, Numbers[]> parseLine(String line) {
        String[] cells = line.split(",");
        if (cells.length != Sudoku.size)
            return Validation.invalid(List.of(errorLineNot9Columns + line));
        else
            return Validation.traverse(List.of(cells), Validator::parseCell).map(a -> a.toJavaArray(Numbers[]::new));
    }

    private static Validation<Seq<String>, Numbers> parseCell(String cell) {
        return Numbers.fromString(cell).fold(
                () -> Validation.invalid(List.of(errorInvalidNumber + cell)),
                Validation::valid
        );
    }

    static List<String> validateSudoku(Numbers[][] grid) {
        Sudoku sudoku = new Sudoku(grid);
        var rowErrors = list0to8.flatMap(i -> validateRow(sudoku, i));
        var columnErrors = list0to8.flatMap(i -> validateColumn(sudoku, i));
        var blockErrors = list0to8.flatMap(i -> validateBlock(sudoku, i));
        return rowErrors.appendAll(columnErrors).appendAll(blockErrors);
    }

    private static List<String> validateRow(Sudoku sudoku, int row) {
        if (sudoku.getRow(row).toSet().equals(fullSet))
            return List.empty();
        else
            return List.of(errorInvalidRow + (row+1));
    }

    private static List<String> validateColumn(Sudoku sudoku, int col) {
        if (sudoku.getColumn(col).toSet().equals(fullSet))
            return List.empty();
        else
            return List.of(errorInvalidColumn + (col+1));
    }

    private static List<String> validateBlock(Sudoku sudoku, int block) {
        int row = block / 3;
        int col = block % 3;
        if (sudoku.getBlock(row, col).toSet().equals(fullSet))
            return List.empty();
        else
            return List.of(errorInvalidBlock + (block+1));
    }

}
