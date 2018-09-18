import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CodesTest {

    private Set<String> codes = new HashSet<>();

    @Before
    public void setUp() {
        codes.add("K1\\SK1");
        codes.add("K1\\SK2");
        codes.add("K1\\SK1\\SSK1");
        codes.add("K1\\SK1\\SSK2");

        codes.add("K2");
        codes.add("K2\\SK1\\SSK1");
        codes.add("K2\\SK1\\SSK2");
    }

    @Test
    public void sortUp_should_return_expected_list() {
        //given
        Codes codes = new Codes();
        List<String> expected = getUpList();

        //when
        List<String> actual = codes.sortToUp(this.codes);

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void sortDown_should_return_expected_list() {
        //given
        Codes codes = new Codes();
        List<String> expected = getDownList();

        //when
        List<String> actual = codes.sortToDown(this.codes);

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMissedUnitFor_should_return_expected_list() {
        //given
        Codes codes = new Codes();
        List<String> expected = expectedList();

        //when
        List<String> actual = codes.sortToUp(getString());

        //then
        Assert.assertEquals(expected, actual);
    }

    private List<String> getUpList() {
        List<String> result = new LinkedList<>();

        result.add("K1");
        result.add("K1\\SK1");
        result.add("K1\\SK1\\SSK1");
        result.add("K1\\SK1\\SSK2");
        result.add("K1\\SK2");

        result.add("K2");
        result.add("K2\\SK1");
        result.add("K2\\SK1\\SSK1");
        result.add("K2\\SK1\\SSK2");

        return result;
    }

    private List<String> getDownList() {
        List<String> result = new LinkedList<>();

        result.add("K2");
        result.add("K2\\SK1");
        result.add("K2\\SK1\\SSK2");
        result.add("K2\\SK1\\SSK1");

        result.add("K1");
        result.add("K1\\SK2");
        result.add("K1\\SK1");
        result.add("K1\\SK1\\SSK2");
        result.add("K1\\SK1\\SSK1");

        return result;
    }

    private Set<String> getString() {
        Set<String> result = new HashSet<>();
        result.add("k1\\sk1\\sk2\\sk3\\sk4");
        return result;
    }

    private List<String> expectedList() {
        List<String> expectedList = new ArrayList<>();

        expectedList.add("k1");
        expectedList.add("k1\\sk1");
        expectedList.add("k1\\sk1\\sk2");
        expectedList.add("k1\\sk1\\sk2\\sk3");
        expectedList.add("k1\\sk1\\sk2\\sk3\\sk4");

        return expectedList;
    }
}
