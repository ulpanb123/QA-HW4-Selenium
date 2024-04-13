package part3;

import org.testng.annotations.Factory;

public class TestFactory {

    @Factory
    public Object[] createInstances() {
        return new Object[]{
                new TestWithTestNG("Random param"),
                new TestWithTestNG(""),
                new TestWithTestNG("Also non-zero param")
        };
    }
}

