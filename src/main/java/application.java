import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class application {

    static String splitSigns[] = {",", " ; "};
    static HashMap<String, HashSet<String>> cityhash = new HashMap();
    static HashMap<String, HashSet<String>> idhash = new HashMap();


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
               linesearch(formatType, line, operationType, searchKey);

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
           search(operationType,searchKey);
        }

    }


    static void linesearch(char formatType, String line, String operationType, String searchKey) {
        try {
            if (formatType == '2') {
                //Delete the character '-' from the line
                StringBuilder lineStringBuilder = new StringBuilder(line);
                lineStringBuilder.deleteCharAt(lineStringBuilder.lastIndexOf("-"));
                line = lineStringBuilder.toString();
            }

            if (line.contains(searchKey)) {
                int indexOfSplitString = Character.getNumericValue(formatType) - 1;
                String splitString = splitSigns[indexOfSplitString];
                String data[] = line.split(splitString);
                String name = data[0].substring(2);
                String city = data[1];
                String id = data[2];

                if (operationType.equals("CITY")) {
                    if (city.equals(searchKey)) {
                        if (cityhash.containsKey(searchKey)) {
                            cityhash.get(searchKey).add(name + "" +  id.toUpperCase());
                        } else {
                            HashSet<String> set = new HashSet();
                            set.add( name + "" +  id.toUpperCase() );
                            cityhash.put(searchKey.toUpperCase(), set);
                        }
                    }
                }

                if (operationType.equals("ID")) {
                    if (id.equals(searchKey)) {
                        if (idhash.containsKey(searchKey)) {
                            idhash.get(searchKey).add(city.toUpperCase());
                        } else {
                            HashSet<String> set = new HashSet();
                            set.add(city.toUpperCase());
                            idhash.put(searchKey.toUpperCase(), set);
                        }
                    }
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("An error occurred while parsing, \n" +
                    "Check your file format and try again");
        } catch (Exception e) {
            System.out.println("File was not fully parsed as something went wrong");
            e.printStackTrace();
        }
    }

    static void search(String operationType, String searchKey) {
        if (operationType.equals("CITY")) {
            outputCity(searchKey);
        } else if (operationType.equals("ID")) {
            outputId(searchKey);
        } else {
            System.out.println("Unknown operation \nEnter as 2nd argument'CITY' to search by city, 'ID' to search by ID");
        }
    }

    /*
    Outputs a list of cities associated with the searchKey id
     */
    static void outputId(String searchKey) {
        try {
            for (String city : idhash.get(searchKey)) {
                System.out.println(city);
            }
        } catch (NullPointerException e) {
            System.out.printf("ID %s not found", searchKey);
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    /*
    Outputs a list of people associated with the searchKey city
     */
    static void outputCity(String searchKey) {
        try {
            for (String  data : cityhash.get((searchKey))) {
                System.out.println(data);
            }
        } catch (NullPointerException e) {
            System.out.printf("City %s not found", searchKey);
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }
}
