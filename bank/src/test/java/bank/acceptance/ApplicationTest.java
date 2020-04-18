package bank.acceptance;

import cucumber.api.java.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApplicationTest {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationTest.class);

    @Before
    public void setUp() {
        LOG.info("Spring context initialized for executing cucumber tests");
    }

    @Test
    public void applicationContextLoads() {

    }
}