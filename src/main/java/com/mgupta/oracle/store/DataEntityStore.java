package com.mgupta.oracle.store;

import com.mgupta.oracle.entity.DataEntity;

import java.util.Collection;

public interface DataEntityStore extends DataEntityStoreBase {
    Collection<DataEntity> getAll();

    Collection<String> uniqueGeozone();

    Collection<Integer> uniqueContracts();
}
