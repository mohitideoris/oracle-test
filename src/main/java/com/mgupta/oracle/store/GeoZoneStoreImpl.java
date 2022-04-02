package com.mgupta.oracle.store;

import com.mgupta.oracle.entity.DataEntity;
import com.mgupta.oracle.events.DataEntityEventHandler;

import java.util.*;

public class GeoZoneStoreImpl implements GeoZoneStore {
    private final HashMap<String, HashSet<Integer>> geoZoneInfo;
    private final HashMap<String, List<DataEntity>> geoZoneToDataEntity;

    public GeoZoneStoreImpl() {
        geoZoneInfo = new HashMap<>();
        geoZoneToDataEntity = new HashMap<>();
    }

    @Override
    public int uniqueCustomer(String geozone) {
        return !geoZoneInfo.containsKey(geozone) ? 0 : geoZoneInfo.getOrDefault(geozone, new HashSet<>()).size();
    }

    @Override
    public Collection<Integer> uniqueCustomerList(String geozone) {
        HashSet<Integer> customers = geoZoneInfo.getOrDefault(geozone, new HashSet<>());
        return Collections.unmodifiableCollection(customers);
    }

    @Override
    public Collection<DataEntity> dataEntities(String geozone) {
        return Collections.unmodifiableCollection(geoZoneToDataEntity.getOrDefault(geozone, new ArrayList()));
    }

    @Override
    public void add(DataEntity entity) {
        if ( !geoZoneInfo.containsKey(entity.getGeozone())) {
            geoZoneInfo.put(entity.getGeozone(), new HashSet<>());
            geoZoneToDataEntity.put(entity.getGeozone(), new ArrayList<>());
        }

        // GeoZone to unique customer id
        HashSet<Integer> customers = geoZoneInfo.get(entity.getGeozone());
        customers.add(entity.getCustomerId());

        // GeoZone to data entities
        geoZoneToDataEntity.get(entity.getGeozone()).add(entity);
    }
}
