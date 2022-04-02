package com.mgupta.oracle.entity;

public interface DataEntityConverter {
    DataEntity convert(String line) throws DataEntityConversionException;
}
