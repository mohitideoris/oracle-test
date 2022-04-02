package com.mgupta.oracle.app;

import com.mgupta.oracle.data.*;
import com.mgupta.oracle.entity.CsvToDataEntityConverter;
import com.mgupta.oracle.entity.DataEntity;
import com.mgupta.oracle.entity.DataEntityConverter;
import com.mgupta.oracle.events.ContractDataEntityEventHandler;
import com.mgupta.oracle.events.EventHandlerProvider;
import com.mgupta.oracle.events.GeoZoneDataEntityEventHandler;
import com.mgupta.oracle.store.*;
import com.mgupta.oracle.view.Reporter;

import java.util.Collection;
import java.util.logging.Logger;

public class AppRunner {
    public static Logger log = Logger.getLogger(AppRunner.class.getName());

    private final GeoZoneStore geoZoneStore;
    private final ContractStore contractStore;
    private final DataProvider dataProvider;

    public AppRunner() {
        geoZoneStore = new GeoZoneStoreImpl();
        contractStore = new ContractStoreImpl();
        dataProvider = new DataProviderImpl();

        // Can add more classes (Openess)
        EventHandlerProvider.register(new GeoZoneDataEntityEventHandler(geoZoneStore));
        EventHandlerProvider.register(new ContractDataEntityEventHandler(contractStore));
    }

    public void run() throws IllegalAccessException, InstantiationException {
        System.out.println("Running application");

        // Run with default context
        run(AppRunnerContext.defaultContext());
    }

    public void run(AppRunnerContext context) throws InstantiationException, IllegalAccessException {
        System.out.println("Running application");

        // Process each line and get Data entityr
        DataProcessor dataProcessor = new DataProcessor(context.getFailureStrategy(), context.getConverter());
        Collection<DataEntity> dataEntity = dataProcessor.process(dataProvider.get());

        // Store all data entities.
        DataEntityStore store = new DataEntityStoreImpl(EventHandlerProvider.handlers());
        dataEntity.stream().forEach(store::add);

        // Time to report
        Reporter reporter = new Reporter(store, contractStore, geoZoneStore);
        reporter.reportUniqueCustomerId();
        reporter.reportUniqueCustomersPerGeozone();
        reporter.listUniqueCustomersPerGeozone();
        reporter.averageBuildDurationPerGeoZone();
    }

    private static class AppRunnerContext {
        private DataProcessingFailureStrategy strategy;
        private DataEntityConverter converter;

        public static AppRunnerContext defaultContext() {
            AppRunnerContext context = new AppRunnerContext();
            context.strategy = new LogOnDataProcessingFailureStrategy();
            context.converter = new CsvToDataEntityConverter();

            return context;
        }

        public DataProcessingFailureStrategy getFailureStrategy() {
            return strategy;
        }

        public DataEntityConverter getConverter() {
            return converter;
        }
    }
}
