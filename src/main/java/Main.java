import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Navigation nav = new Navigation();
        nav.buildNavigation().forEach(it -> it.print(1));
    }
}
