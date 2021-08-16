import io.vavr.collection.List;
import sudoku.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 1)
            terminateWithError("Command-line argument must be a file name");
        Path path = Paths.get(args[0]);
        if (!Files.isReadable(path))
            terminateWithError("File '" + path + "' is not readable");

        List<String> lines = List.ofAll(Files.readAllLines(path));

        List<String> errors = Validator.validate(lines);

        if (errors.isEmpty())
            System.exit(0);
        else
            terminateWithError(String.join(System.lineSeparator(), errors));
    }

    private static void terminateWithError(String errorMessage) {
        System.out.println(errorMessage);
        System.exit(1);
    }

}
