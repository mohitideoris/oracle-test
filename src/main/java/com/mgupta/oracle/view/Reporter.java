package com.mgupta.oracle.view;

import com.mgupta.oracle.entity.DataEntity;
import com.mgupta.oracle.store.ContractStore;
import com.mgupta.oracle.store.DataEntityStore;
import com.mgupta.oracle.store.GeoZoneStore;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Reporter {
    private static final Logger LOG = Logger.getLogger(Reporter.class.getName());

    private final DataEntityStore dataEntityStore;
    private final ContractStore contractStore;
    private final GeoZoneStore geoZoneStore;

    public Reporter(DataEntityStore dataEntityStore, ContractStore contractStore, GeoZoneStore geoZoneStore) {
        this.dataEntityStore = dataEntityStore;
        this.contractStore = contractStore;
        this.geoZoneStore = geoZoneStore;
    }

    public void reportUniqueCustomerId() {
        System.out.println("The number of unique customerId for each contractId");

        dataEntityStore.uniqueContracts().forEach(contractId -> {
            System.out.println(contractId + " -> " + this.contractStore.uniqueCustomer(contractId));
        });

        System.out.println("");
    }

    public void reportUniqueCustomersPerGeozone() {
        System.out.println("The number of unique customerId for each geozone");

        dataEntityStore.uniqueGeozone().forEach(geozone -> {
            System.out.println(geozone + " -> " + this.geoZoneStore.uniqueCustomer(geozone));
        });

        System.out.println("");
    }

    public void listUniqueCustomersPerGeozone() {
        System.out.println("The list of unique customerId for each geozone");

        dataEntityStore.uniqueGeozone().forEach(geozone -> {
            String uniqueCustomersAsString = this.geoZoneStore.uniqueCustomerList(geozone).stream().map(String::valueOf).collect(Collectors.joining(","));
            System.out.println(geozone + " -> " + uniqueCustomersAsString);
        });

        System.out.println("");
    }

    public void averageBuildDurationPerGeoZone() {
        System.out.println("The average build duration for each geozone");

        dataEntityStore.uniqueGeozone().forEach(geozone -> {
            Collection<DataEntity> dataEntities = this.geoZoneStore.dataEntities(geozone);

            int average = 0;
            for(DataEntity dataEntity : dataEntities) {
                try {
                    average += Integer.parseInt(dataEntity.getBuildDuration().substring(0, dataEntity.getBuildDuration().length() - 1));
                } catch(Exception ex) {
                    LOG.log(Level.SEVERE, "Failed to display average build duration. Invalid data entity duration");
                    return;
                }
            }

            average = average / dataEntities.size();

            System.out.println(geozone + " -> " + average);
        });

        System.out.println("");
    }
}
