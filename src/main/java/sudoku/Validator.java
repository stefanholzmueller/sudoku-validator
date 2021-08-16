package sudoku;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Validator {

    public static final String errorLessThan9Lines = "File does not have 9 lines";
    public static final String errorLineNot9Columns = "Line does not have the expected CSV format with 9 columns: ";
    public static final String errorInvalidNumber = "Not a valid number: ";
    public static final String errorInvalidRow = "Invalid set of numbers in row ";
    public static final String errorInvalidColumn = "Invalid set of numbers in column ";
    public static final String errorInvalidBlock = "Invalid set of numbers in block ";

    private static final Set<Numbers> fullSet = new HashSet<>(List.of(Numbers.values()));
    private static final List<Integer> list0to8 = IntStream.range(0, Sudoku.size).boxed().collect(Collectors.toList());

    private final List<String> errors = new ArrayList<>();

    public List<String> validate(List<String> csv) {
        if (csv.size() < Sudoku.size) {
            errors.add(errorLessThan9Lines);
        } else {
            parseStructure(csv).ifPresent(this::validateSudoku);
        }
        return errors;
    }

    Optional<Numbers[][]> parseStructure(List<String> lines) {
        List<Numbers[]> parsedRows = new ArrayList<>();
        for (String line : lines) {
            parseLine(line).ifPresent(parsedRows::add);
        }

        if (parsedRows.size() == Sudoku.size)
            return Optional.of(parsedRows.toArray(Numbers[][]::new));
        else
            return Optional.empty();
    }

    private Optional<Numbers[]> parseLine(String line) {
        String[] cells = line.split(",");
        if (cells.length != Sudoku.size) {
            errors.add(errorLineNot9Columns + line);
            return Optional.empty();
        } else {
           return parseCells(cells);
        }
    }

    private Optional<Numbers[]> parseCells(String[] cells) {
        List<Numbers> parsedNumbers = new ArrayList<>();
        for (String cell : cells) {
            Numbers.fromString(cell).ifPresentOrElse(parsedNumbers::add, () -> errors.add(errorInvalidNumber + cell));
        }

        if (parsedNumbers.size() == Sudoku.size)
            return Optional.of(parsedNumbers.toArray(Numbers[]::new));
        else
            return Optional.empty();
    }

    private void validateSudoku(Numbers[][] grid) {
        Sudoku sudoku = new Sudoku(grid);

        list0to8.forEach(i -> validateRow(sudoku, i));
        list0to8.forEach(i -> validateColumn(sudoku, i));
        list0to8.forEach(i -> validateBlock(sudoku, i));
    }

    private void validateRow(Sudoku sudoku, int row) {
        Set<Numbers> numbers = new HashSet<>(sudoku.getRow(row));

        if (!numbers.equals(fullSet))
            errors.add(errorInvalidRow + (row+1));
    }

    private void validateColumn(Sudoku sudoku, int col) {
        Set<Numbers> numbers = new HashSet<>(sudoku.getColumn(col));

        if (!numbers.equals(fullSet))
            errors.add(errorInvalidColumn + (col+1));
    }

    private void validateBlock(Sudoku sudoku, int block) {
        int row = block / 3;
        int col = block % 3;
        Set<Numbers> numbers = new HashSet<>(sudoku.getBlock(row, col));

        if (!numbers.equals(fullSet))
            errors.add(errorInvalidBlock + (block+1));
    }

}
