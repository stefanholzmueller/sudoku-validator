package sudoku;

import io.vavr.collection.List;
import io.vavr.control.Option;

public enum Numbers {

    _1("1"),
    _2("2"),
    _3("3"),
    _4("4"),
    _5("5"),
    _6("6"),
    _7("7"),
    _8("8"),
    _9("9");

    private final String string;

    Numbers(String string) {
        this.string = string;
    }

    String getString() {
        return this.string;
    }

    public static Option<Numbers> fromString(String str) {
        return List.of(values()).find(n -> n.getString().equals(str));
    }

}
