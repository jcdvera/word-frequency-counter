import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    static String file = "src/resources/fileExample.txt";
    public static String readFile() throws IOException {
        DataInputStream reader = new DataInputStream(new FileInputStream(file));
        int nBytesToRead = reader.available();

        byte[] bytes = new byte[0];
        if (nBytesToRead > 0) {
            bytes = new byte[nBytesToRead];
            reader.read(bytes);
        }
        return new String(bytes);
    }

    public static void main(String[] args) throws IOException {

        String fileText = readFile();
        System.out.println(fileText);
    }
}