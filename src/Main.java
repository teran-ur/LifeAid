import dao.DatabaseInitializer;
import view.MainMenu;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.createTables();
        new MainMenu();
    }
}
