package com.mgupta.oracle.data;

import com.mgupta.oracle.app.AppRunner;
import com.mgupta.oracle.entity.DataEntity;
import com.mgupta.oracle.entity.DataEntityConversionException;
import com.mgupta.oracle.entity.DataEntityConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataProcessor {
    public static Logger LOG = Logger.getLogger(DataProcessor.class.getName());

    private final DataProcessingFailureStrategy failureStrategy;
    private final DataEntityConverter converter;

    public DataProcessor(DataProcessingFailureStrategy strategy, DataEntityConverter converter) {
        this.failureStrategy = strategy;
        this.converter = converter;
    }

    public Collection<DataEntity> process(Collection<String> data) {
        ArrayList<DataEntity> dataEntities = new ArrayList<>();
        ArrayList<String> failures = new ArrayList<>();

        for(String csvLine : data) {
            try {
                LOG.info("[DataProcessor][CsvContent]: " + csvLine);
                dataEntities.add(this.converter.convert(csvLine));
            } catch (DataEntityConversionException ex) {
                LOG.log(Level.WARNING, "[DataProcessor][CsvContent][Failure]: " + csvLine);
                this.failureStrategy.onDataProcessingFailure(csvLine);
                failures.add(csvLine);
            }
        }

        // TO do something with failures.
        // May be pass to failure strategy ... Business Logic.
        // Or could return result containing (a) dataEntities  (b) failures

        return dataEntities;
    }
}
