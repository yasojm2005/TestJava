import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class application {
    private static application ourInstance = new application();


    public static application getInstance() {
        return ourInstance;
    }


    public static void main(String[] args) {
        String line = null;
        char formatType = '0';
        //path File name
        String phatfilename = args[0];
        //Operation
        String operationType = args[1];
        //key
        String searchKey = args[2];
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(phatfilename);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                 line = sc.nextLine();
                if(line.startsWith("F"))
                {
                    formatType = line.charAt(1);
                    continue;

                }
                FileParser.parse(formatType, line, operationType, searchKey);

            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
            FileParser.search(operationType,searchKey);
        }

    }
}
