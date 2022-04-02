package com.mgupta.oracle.entity;

import org.junit.Test;
import static org.junit.Assert.*;

public class CsvToDataEntityConverterTest {
    @Test
    public void valid() throws DataEntityConversionException {
        CsvToDataEntityConverter converter = new CsvToDataEntityConverter();

        DataEntity dataEntity = converter.convert("2343225,2345,us_east,RedTeam,ProjectApple,3445s");

        assertNotNull(dataEntity);
        assertEquals(2343225, dataEntity.getCustomerId());
        assertEquals(2345, dataEntity.getContractId());
        assertEquals("us_east", dataEntity.getGeozone());
        assertEquals("RedTeam", dataEntity.getTeamCode());
        assertEquals("ProjectApple", dataEntity.getProjectCode());
        assertEquals("3445s", dataEntity.getBuildDuration());
    }

    @Test
    public void invalid() {
        CsvToDataEntityConverter converter = new CsvToDataEntityConverter();

        try {
            converter.convert("2343225,2345,us_east,RedTeam,ProjectApple");
            fail("Expected to fail");
        }
        catch(Exception ex) {
        }

        try {
            converter.convert("");
            fail("Expected to fail");
        }
        catch(Exception ex) {
        }

    }
}
