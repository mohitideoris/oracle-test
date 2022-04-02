package com.mgupta.oracle.events;

import com.mgupta.oracle.entity.DataEntity;
import com.mgupta.oracle.store.GeoZoneStore;

public class GeoZoneDataEntityEventHandler implements DataEntityEventHandler{
    private final GeoZoneStore store;

    public GeoZoneDataEntityEventHandler(final GeoZoneStore store) {
        this.store = store;
    }

    @Override
    public void onDataEntityAdd(DataEntity entity) {
        this.store.add(entity);
    }
}
