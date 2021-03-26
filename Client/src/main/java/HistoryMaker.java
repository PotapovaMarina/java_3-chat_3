import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class HistoryMaker {
    private static final int LAST_LINES = 100;
    public static void sendHistory (String login, String message){
        try {
            Files.write(getPath(login),message.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Path getPath(String login) {
        Path path = Paths.get("history//history" + login + ".txt");
        if (Files.notExists(path.getParent())) {
            createDirPath(path);
        }
        return path;
    }

    public static void createDirPath(Path path){
        try {
            Files.createDirectories(path.getParent());
            } catch (IOException e) {
             e.printStackTrace();
            }
    }
    public static String lastHistory(String login){
        Path path=getPath(login);
        if (Files.notExists(path)){
            return "";      }
        try {
            List<String> lines=Files.readAllLines(path, StandardCharsets.UTF_8);
            return getLast(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String getLast(List<String> lines){
        StringBuilder text=new StringBuilder();
        int i0=0;
        if (lines.size()>LAST_LINES){
            i0=lines.size()-LAST_LINES;
            }
        for (int i = i0; i < lines.size(); i++) {
            text.append(lines.get(i)).append(System.lineSeparator());
        }
        return text.toString();
        }
    }
