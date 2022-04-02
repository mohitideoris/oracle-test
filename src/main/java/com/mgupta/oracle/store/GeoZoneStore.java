package com.mgupta.oracle.store;

import com.mgupta.oracle.entity.DataEntity;

import java.util.Collection;

public interface GeoZoneStore extends DataEntityStoreBase {
    int uniqueCustomer(final String geozone);
    Collection<Integer> uniqueCustomerList(final String geozone);
    Collection<DataEntity> dataEntities(final String geozone);
}
