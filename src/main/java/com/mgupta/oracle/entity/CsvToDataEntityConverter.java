package com.mgupta.oracle.entity;

public class CsvToDataEntityConverter implements DataEntityConverter {
    public static final int TOTAL_ELEMENTS = 6;

    public static final int IDX_CUSTOMER_ID = 0;
    public static final int IDX_CONTRACT_ID = 1;
    public static final int IDX_GEOZONE = 2;
    public static final int IDX_TEAM_CODE = 3;
    public static final int IDX_PROJECT_CODE = 4;
    public static final int IDX_BUILD_DURATION = 5;

    @Override
    public DataEntity convert(String line) throws DataEntityConversionException {
        String[] split = line.split(",");

        if (split.length != TOTAL_ELEMENTS) {
            throw new DataEntityConversionException("Data conversion failed. Wrong csv elements. Expected " + TOTAL_ELEMENTS);
        }

        try {
            DataEntity.DataEntityBuilder builder = new DataEntity.DataEntityBuilder();

            // Another approach is to pass String and validation for all fields is then handled by Builder.
            // Thereby reducing load on the client such as in this case.
            //
            // Point of discussion
            builder.setCustomerId(Integer.parseInt(split[IDX_CUSTOMER_ID]));
            builder.setContractId(Integer.parseInt(split[IDX_CONTRACT_ID]));

            // NOTE: TODO: Validation for GeoZone, TeamCode etc.
            builder.setGeozone(split[IDX_GEOZONE]);
            builder.setTeamCode(split[IDX_TEAM_CODE]);
            builder.setProjectCode(split[IDX_PROJECT_CODE]);
            builder.setBuildDuration(split[IDX_BUILD_DURATION]);

            return builder.createDataEntity();
        } catch(Exception ex) {
            throw new DataEntityConversionException("Data conversion failed", ex);
        }
    }
}
