package sudoku;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sudoku.Numbers.*;


class SudokuTest {

    private final List<String> testCsv = List.of(
            "1,2,3,4,5,6,7,8,9",
            "2,3,4,5,6,7,8,9,1",
            "3,4,5,6,7,8,9,1,2",
            "4,5,6,7,8,9,1,2,3",
            "5,6,7,8,9,1,2,3,4",
            "6,7,8,9,1,2,3,4,5",
            "7,7,7,7,7,7,7,7,7",
            "8,9,1,2,3,4,5,6,7",
            "9,1,2,3,4,5,6,7,8"
    );
    private final Validator validator = new Validator();
    private final Sudoku testSudoku = new Sudoku(validator.parseStructure(testCsv).get());

    @Test
    void getFirstRow() {
        assertEquals(
                List.of(_1, _2, _3, _4, _5, _6, _7, _8, _9),
                testSudoku.getRow(0)
        );
    }

    @Test
    void getSecondRow() {
        assertEquals(
                List.of(_2, _3, _4, _5, _6, _7, _8, _9, _1),
                testSudoku.getRow(1)
        );
    }

    @Test
    void getSeventhRow() {
        assertEquals(
                List.of(_7, _7, _7, _7, _7, _7, _7, _7, _7),
                testSudoku.getRow(6)
        );
    }

    @Test
    void getNinthRow() {
        assertEquals(
                List.of(_9, _1, _2, _3, _4, _5, _6, _7, _8),
                testSudoku.getRow(8)
        );
    }

    @Test
    void getFirstColumn() {
        assertEquals(
                List.of(_1, _2, _3, _4, _5, _6, _7, _8, _9),
                testSudoku.getColumn(0)
        );
    }

    @Test
    void getSeventhColumn() {
        assertEquals(
                List.of(_7, _8, _9, _1, _2, _3, _7, _5, _6),
                testSudoku.getColumn(6)
        );
    }

    @Test
    void getFirstBlock() {
        assertEquals(
                List.of(_1, _2, _3, _2, _3, _4, _3, _4, _5),
                testSudoku.getBlock(0, 0)
        );
    }

    @Test
    void getSeventhBlock() {
        assertEquals(
                List.of(_7, _7, _7, _8, _9, _1, _9, _1, _2),
                testSudoku.getBlock(2, 0)
        );
    }

    @Test
    void getMiddleBlock() {
        assertEquals(
                List.of(_7, _8, _9, _8, _9, _1, _9, _1, _2),
                testSudoku.getBlock(1, 1)
        );
    }

}
