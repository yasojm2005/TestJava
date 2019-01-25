import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Yasiel on 24/01/2019.
 */
public class AuxHelper {

    static String splitSigns[] = {",", " ; "};


    private Set<String> citiSet = new HashSet<String>();
    private Set<String> idSet = new HashSet<String>();


    /**
     * @param format
     * @param line
     * @param operation
     * @param searchvalue
     */
    public void linesearch(char format, String line, String operation, String searchvalue) {
        try {
            if (format == '2') {
                //Delete the character '-' from the line
                StringBuilder lineStringBuilder = new StringBuilder(line);
                lineStringBuilder.deleteCharAt(lineStringBuilder.lastIndexOf("-"));
                line = lineStringBuilder.toString();

            }

            if (line.contains(searchvalue)) {
                int indexOfSplitString = Character.getNumericValue(format) - 1;
                String splitString = splitSigns[indexOfSplitString];
                String data[] = line.split(splitString);
                String name = data[0].substring(2);
                String city = data[1];
                String id = data[2];

                if (operation.equals("CITY")) {
                    if (city.equals(searchvalue)) {
                        citiSet.add(name + "," + id.toUpperCase());

                    }
                }

                if (operation.equals("ID")) {
                    if (id.equals(searchvalue)) {
                        idSet.add(city.toUpperCase());
                    }


                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("An error occurred while parsing, \n" +
                    "Check your file format and try again");
        } catch (Exception e) {
            System.out.println("File was not fully parsed as something went wrong");
            e.printStackTrace();
        }
    }

    /**
     * @param operation
     */
    public void search(String operation) {
        if (operation.equals("CITY")) {
            outputCity();
        } else if (operation.equals("ID")) {
            outputId();
        } else {
            System.out.println("Unknown operation \nEnter as 2nd argument'CITY' to search by city, 'ID' to search by ID");
        }
    }

    /**
     * Outputs a list of cities associated with the searchKey id
     */

    public void outputId() {
        Iterator<String> iterator = idSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());

        }
    }

    /**
     *  Outputs a list of people associated with the searchKey city
     */

    public void outputCity() {
        Iterator<String> iterator = citiSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());

        }

    }

    public Set<String> getCitiSet() {
        return citiSet;
    }

    public void setCitiSet(Set<String> citiSet) {
        this.citiSet = citiSet;
    }

    public Set<String> getIdSet() {
        return idSet;
    }

    public void setIdSet(Set<String> idSet) {
        this.idSet = idSet;
    }

}
