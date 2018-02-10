import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JesusValera
 */
public class Main {

    private static final String FILE = "example.txt";
    private List<String> lines;
    private File file;

    public static void main(String[] args) {
        new Main(usingArgs(args));
    }

    /**
     * @param args check if there is an argument set.
     * @return the path file
     */
    private static String usingArgs(String[] args) {
        if (args.length > 0) {
            return args[0];
        } else {
            return FILE;
        }
    }

    public Main(String path) {
        lines = new ArrayList<>();
        file = new File(path);

        if (!file.exists()) {
            System.err.println("File does not exists.");
            System.exit(0);
        }

        try {
            readLines(file);
            generateRandomParagraph();

        } catch (IOException e) {
            System.err.println("There was a problem reading the file " + file.getName() + "\n-> " + e.getMessage());
        }

    }

    /**
     * The program counts by lines however does not count empty lines.
     * E.g:
     * <p>
     * --- document.txt ---
     * 1| - Hello
     * 2| - mi name is
     * 3| -
     * 4| -
     * 5| - Jesus
     * 6| -
     * --------------------
     * Result:
     * 1 -> Hello
     * 2 -> mi name is
     * 3 -> Jesus
     * --------------------
     * --------------------
     *
     * @param file the path of the file
     * @throws IOException it can occurs when the program tries to read the document or at closing.
     */
    private void readLines(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.equals("")) {
                lines.add(line);
            }
        }

        br.close();
        fr.close();
    }

    private void generateRandomParagraph() {
        int randomNumber = (int) (Math.random() * lines.size());

        System.out.println("File name: " + file.getName());
        System.out.println("Number of lines: " + lines.size());
        System.out.println("Random line selected: " + (randomNumber + 1));

        if (lines.size() > 0) {
            System.out.println(lines.get(randomNumber));
        } else {
            System.out.println("Empty document.");
        }
    }
}
