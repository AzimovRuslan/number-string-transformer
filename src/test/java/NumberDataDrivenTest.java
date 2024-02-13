import lombok.extern.slf4j.Slf4j;
import org.jazzteam.entity.Number;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.opentest4j.AssertionFailedError;

@Slf4j
class NumberDataDrivenTest {

    @ParameterizedTest
    @CsvFileSource(resources = "testData.csv")
    void numberToStringFormatTest(String expected, long number) {
        try {
            Assertions.assertEquals(expected, new Number(number).getStringFormat());
            log.info(number + "->" + expected + " - test passed");
        } catch (AssertionFailedError e) {
            log.error(e.getMessage());
            log.info(number + "->" + expected + " - test failed");
        }
    }
}
