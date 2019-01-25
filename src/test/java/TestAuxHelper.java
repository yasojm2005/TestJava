import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

/**
 * Created by Yasiel on 24/01/2019.
 */
public class TestAuxHelper {
    AuxHelper auxHelper;

    @Before
    public void setup() {
        auxHelper = mock(AuxHelper.class);
    }

    /**
     *
     */
    @Test
    public void Test() {

        auxHelper.linesearch('1', "D Lucy Mcgee,LONDON,51011156P", "CITY", "LONDON");



    }

}
