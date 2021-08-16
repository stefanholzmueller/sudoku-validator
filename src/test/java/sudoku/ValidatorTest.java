package sudoku;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidatorTest {

    private void assertErrors(List<String> lines, List<String> expectedErrors) {
        Validator validator = new Validator();
        List<String> errors = validator.validate(lines);
        assertEquals(expectedErrors, errors);
    }

    @Test
    void noLines() {
        assertErrors(
                List.of(),
                List.of(Validator.errorLessThan9Lines)
        );
    }

    @Test
    void oneLine() {
        assertErrors(
                List.of(
                        "1,2,3,4,5,6,7,8,9"
                ),
                List.of(Validator.errorLessThan9Lines)
        );
    }

    @Test
    void eightLines() {
        assertErrors(
                List.of(
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9"
                ),
                List.of(Validator.errorLessThan9Lines)
        );
    }

    @Test
    void validStructureButAllBlocksInvalid() {
        assertErrors(
                List.of(
                        "1,2,3,4,5,6,7,8,9",
                        "2,3,4,5,6,7,8,9,1",
                        "3,4,5,6,7,8,9,1,2",
                        "4,5,6,7,8,9,1,2,3",
                        "5,6,7,8,9,1,2,3,4",
                        "6,7,8,9,1,2,3,4,5",
                        "7,8,9,1,2,3,4,5,6",
                        "8,9,1,2,3,4,5,6,7",
                        "9,1,2,3,4,5,6,7,8"
                ),
                List.of(
                        Validator.errorInvalidBlock + "1",
                        Validator.errorInvalidBlock + "2",
                        Validator.errorInvalidBlock + "3",
                        Validator.errorInvalidBlock + "4",
                        Validator.errorInvalidBlock + "5",
                        Validator.errorInvalidBlock + "6",
                        Validator.errorInvalidBlock + "7",
                        Validator.errorInvalidBlock + "8",
                        Validator.errorInvalidBlock + "9"
                )
        );
    }

    @Test
    void lessThan9Columns() {
        assertErrors(
                List.of(
                        "1,2,3,4,5,6,7,8",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9"
                ),
                List.of(Validator.errorLineNot9Columns + "1,2,3,4,5,6,7,8")
        );
    }

    @Test
    void moreThan9Columns() {
        assertErrors(
                List.of(
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9,10"
                ),
                List.of(Validator.errorLineNot9Columns + "1,2,3,4,5,6,7,8,9,10")
        );
    }

    @Test
    void inconsistentColumns() {
        assertErrors(
                List.of(
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9,10,11,12"
                ),
                List.of(
                        Validator.errorLineNot9Columns + "1,2,3,4,5,6,7",
                        Validator.errorLineNot9Columns + "1,2,3,4,5,6,7,8,9,10,11,12")
        );
    }

    @Test
    void emptyCell() {
        assertErrors(
                List.of(
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9"
                ),
                List.of(Validator.errorInvalidNumber)
        );
    }

    @Test
    void twoEmptyCells() {
        assertErrors(
                List.of(
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9"
                ),
                List.of(Validator.errorInvalidNumber, Validator.errorInvalidNumber)
        );
    }

    @Test
    void invalidCell() {
        assertErrors(
                List.of(
                        "1,2,3,4,5,6,7,8,10",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9"
                ),
                List.of(Validator.errorInvalidNumber + "10")
        );
    }

    @Test
    void multipleProblemsWithStructure() {
        assertErrors(
                List.of(
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "11,2,3,4,5,6,7,8,9",
                        "1,2,3,4,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "",
                        "1,2,3,4,5,6,7,8,9,10",
                        "1,2,3,4,5,6,7,8,9"
                ),
                List.of(
                        Validator.errorInvalidNumber + "11",
                        Validator.errorLineNot9Columns + "1,2,3,4,6,7,8,9",
                        Validator.errorLineNot9Columns,
                        Validator.errorLineNot9Columns + "1,2,3,4,5,6,7,8,9,10"
                )
        );
    }

    @Test
    void allRowsValidButAllColumnsAndBlocksInvalid() {
        assertErrors(
                List.of(
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9",
                        "1,2,3,4,5,6,7,8,9"
                ),
                List.of(
                        Validator.errorInvalidColumn + "1",
                        Validator.errorInvalidColumn + "2",
                        Validator.errorInvalidColumn + "3",
                        Validator.errorInvalidColumn + "4",
                        Validator.errorInvalidColumn + "5",
                        Validator.errorInvalidColumn + "6",
                        Validator.errorInvalidColumn + "7",
                        Validator.errorInvalidColumn + "8",
                        Validator.errorInvalidColumn + "9",
                        Validator.errorInvalidBlock + "1",
                        Validator.errorInvalidBlock + "2",
                        Validator.errorInvalidBlock + "3",
                        Validator.errorInvalidBlock + "4",
                        Validator.errorInvalidBlock + "5",
                        Validator.errorInvalidBlock + "6",
                        Validator.errorInvalidBlock + "7",
                        Validator.errorInvalidBlock + "8",
                        Validator.errorInvalidBlock + "9"
                )
        );
    }

    @Test
    void invalidSecondRowAndLastColumn() {
        assertErrors(
                List.of(
                        "1,2,3,4,5,6,7,8,9",
                        "4,5,6,7,8,9,1,2,9",
                        "7,8,9,1,2,3,4,5,6",
                        "5,6,7,8,9,1,2,3,4",
                        "8,9,1,2,3,4,5,6,7",
                        "2,3,4,5,6,7,8,9,1",
                        "9,1,2,3,4,5,6,7,8",
                        "3,4,5,6,7,8,9,1,2",
                        "6,7,8,9,1,2,3,4,5"
                ),
                List.of(
                        Validator.errorInvalidRow + "2",
                        Validator.errorInvalidColumn + "9",
                        Validator.errorInvalidBlock + "3"
                )
        );
    }

    @Test
    void validSudoku() {
        assertErrors(
                List.of(
                        "1,2,3,4,5,6,7,8,9",
                        "4,5,6,7,8,9,1,2,3",
                        "7,8,9,1,2,3,4,5,6",
                        "5,6,7,8,9,1,2,3,4",
                        "8,9,1,2,3,4,5,6,7",
                        "2,3,4,5,6,7,8,9,1",
                        "9,1,2,3,4,5,6,7,8",
                        "3,4,5,6,7,8,9,1,2",
                        "6,7,8,9,1,2,3,4,5"
                ),
                List.of()
        );
    }

}
