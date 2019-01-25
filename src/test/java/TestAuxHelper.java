

/**
 * Created by Yasiel on 24/01/2019.
 */
public class TestAuxHelper {

    @Test
    public void multiplicationOfZeroIntegersShouldReturnZero() {

        fileParser.parse('1', "D Lucy Mcgee,LONDON,51011156P", "CITY", "LONDON");


        assertEquals(1, fileParser.cityhashSet.contains("LONDON"));
        assertEquals(0, fileParser.cityhashSet.contains("D Lucy Mcgee,51011156P"));

    }

}
