package com.mgupta.oracle.entity;

/**
 * This handles information about customer.
 *
 * Note: I haven't commented on each getter/setter.
 */
public class DataEntity {
    private final int customerId;
    private final int contractId;
    private final String geozone;
    private final String teamCode;
    private final String projectCode;
    private final String buildDuration;

    /**
     * Prevent creation of this instance but using Builder pattern.
     */
    private DataEntity(int customerId, int contractId, String geozone, String teamCode, String projectCode, String buildDuration) {
        this.customerId = customerId;
        this.contractId = contractId;
        this.geozone = geozone;
        this.teamCode = teamCode;
        this.projectCode = projectCode;
        this.buildDuration = buildDuration;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getContractId() {
        return contractId;
    }

    public String getGeozone() {
        return geozone;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public String getBuildDuration() {
        return buildDuration;
    }

    /**
     * Show casing builder patter.
     */
    public static class DataEntityBuilder {
        private int customerId;
        private int contractId;
        private String geozone;
        private String teamCode;
        private String projectCode;
        private String buildDuration;

        public DataEntityBuilder setCustomerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public DataEntityBuilder setContractId(int contractId) {
            this.contractId = contractId;
            return this;
        }

        public DataEntityBuilder setGeozone(String geozone) {
            this.geozone = geozone;
            return this;
        }

        public DataEntityBuilder setTeamCode(String teamCode) {
            this.teamCode = teamCode;
            return this;
        }

        public DataEntityBuilder setProjectCode(String projectCode) {
            this.projectCode = projectCode;
            return this;
        }

        public DataEntityBuilder setBuildDuration(String buildDuration) {
            this.buildDuration = buildDuration;
            return this;
        }

        public DataEntity createDataEntity() {
            return new DataEntity(customerId, contractId, geozone, teamCode, projectCode, buildDuration);
        }
    }
}
