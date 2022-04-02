package com.mgupta.oracle.data;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogOnDataProcessingFailureStrategy implements DataProcessingFailureStrategy {
    private static Logger LOG = Logger.getLogger(LogOnDataProcessingFailureStrategy.class.getName());

    @Override
    public void onDataProcessingFailure(String dataLine) {
        LOG.log(Level.SEVERE, "[Data][Read][Failure]: Failed to read " + dataLine);
    }
}
