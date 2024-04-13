package part3;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestWithTestNG {
    private String param;

    public TestWithTestNG(String param) {
        this.param = param;
    }

    @Test
    public void test() {
        System.out.println("Test with parameter: " + param);
        int length = param.length();
        Assert.assertTrue(length > 0, "Parameter length should be greater than 0");
    }
}
