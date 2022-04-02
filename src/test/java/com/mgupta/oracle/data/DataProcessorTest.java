package com.mgupta.oracle.data;

import com.mgupta.oracle.entity.CsvToDataEntityConverter;
import com.mgupta.oracle.entity.DataEntity;
import com.mgupta.oracle.entity.DataEntityConverter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Collection;

public class DataProcessorTest {
    private DataProcessor processor;


    @Before
    public void beforeEachTest() {
        DataProcessingFailureStrategy strategy = new LogOnDataProcessingFailureStrategy();
        DataEntityConverter converter = new CsvToDataEntityConverter();
        processor  = new DataProcessor(strategy, converter);
    }

    @Test
    public void processValid() {
        ArrayList<String> data = new ArrayList<>();
        data.add("2343225,2345,us_east,RedTeam,ProjectApple,3445s");
        data.add("1223456,2345,us_west,BlueTeam,ProjectBanana,2211s");
        data.add("3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s");
        data.add("1233456,2345,us_west,BlueTeam,ProjectDate,2221s");
        data.add("3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s");


        Collection<DataEntity> entities = processor.process(data);
        assertEquals(5, entities.size());
    }

    @Test
    public void processInvalid() {
        ArrayList<String> data = new ArrayList<>();
        data.add("2343225,2345,us_east,RedTeam,");
        data.add("");
        data.add("2343225,2345,us_east,RedTeam,ProjectApple,3445s,122");

        Collection<DataEntity> entities = processor.process(data);
        assertEquals(0, entities.size());
    }
}
