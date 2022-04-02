package com.mgupta.oracle.app;

import com.mgupta.oracle.data.DataProvider;
import com.mgupta.oracle.data.DataProviderImpl;
import com.mgupta.oracle.events.ContractDataEntityEventHandler;
import com.mgupta.oracle.events.EventHandlerProvider;
import com.mgupta.oracle.events.GeoZoneDataEntityEventHandler;
import com.mgupta.oracle.store.ContractStore;
import com.mgupta.oracle.store.ContractStoreImpl;
import com.mgupta.oracle.store.GeoZoneStore;
import com.mgupta.oracle.store.GeoZoneStoreImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class AppRunnerTest {
    private static GeoZoneStore geoZoneStore;
    private static ContractStore contractStore;
    private static DataProvider dataProvider;

    @BeforeClass
    public static void beforeClass() {
        geoZoneStore = new GeoZoneStoreImpl();
        contractStore = new ContractStoreImpl();
        dataProvider = new DataProvider() {
            @Override
            public Collection<String> get() {
                ArrayList data = new ArrayList();
                data.add("1,100,zone1,RedTeam,ProjectApple,100s");
                data.add("2,100,zone1,BlueTeam,ProjectBanana,200s");
                data.add("1,200,zone2,BlueTeam,ProjectBanana,200s");
                return data;
            }
        };


        EventHandlerProvider.register(new GeoZoneDataEntityEventHandler(geoZoneStore));
        EventHandlerProvider.register(new ContractDataEntityEventHandler(contractStore));
    }

    @Test
    public void run() {
        AppRunner runner = new AppRunner(geoZoneStore, contractStore, dataProvider);
        runner.run();

        // Unknown geozone
        assertEquals(0, geoZoneStore.uniqueCustomer("us_east"));

        // unique customers
        assertEquals(2, geoZoneStore.uniqueCustomer("zone1"));
        assertEquals(1, geoZoneStore.uniqueCustomer("zone2"));

        // unique customer list
        Collection<Integer> customers = geoZoneStore.uniqueCustomerList("zone1");
        assertEquals(2, customers.size());
        Integer[] customerIds = customers.toArray(new Integer[0]);
        assertEquals(1, customerIds[0].intValue());
        assertEquals(2, customerIds[1].intValue());

        // Note:
        // Can add more test....LEFT
    }

}
