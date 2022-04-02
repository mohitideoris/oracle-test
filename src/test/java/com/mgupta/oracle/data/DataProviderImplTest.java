package com.mgupta.oracle.data;

import org.junit.Test;
import static org.junit.Assert.*;

public class DataProviderImplTest {

    @Test
    public void checkData() {
        DataProviderImpl dataProvider = new DataProviderImpl();

        assertEquals(5, dataProvider.get().size());
    }
}
