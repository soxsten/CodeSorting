import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
    public void sortUp() {
        //given
        Codes codes = new Codes();
        codes.addCodes(this.codes);
        List<String> expected = getUpList();

        //when
        List<String> actual = codes.sortToUp();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void sortDown() {
        //given
        Codes codes = new Codes();
        codes.addCodes(this.codes);
        List<String> expected = getDownList();

        //when
        List<String> actual = codes.sortToDown();

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
}
