package com.mgupta.oracle.data;

public interface DataProcessingFailureStrategy {
    void onDataProcessingFailure(String dataLine);
}
