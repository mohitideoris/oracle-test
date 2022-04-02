package com.mgupta.oracle.store;

import com.mgupta.oracle.entity.DataEntity;
import com.mgupta.oracle.events.DataEntityEventHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class DataEntityStoreImpl implements DataEntityStore {
    private ArrayList<DataEntity> dataEntities = new ArrayList<>();
    private HashSet<String> uniqueGeozones = new HashSet<>();
    private HashSet<Integer> uniqueContracts = new HashSet<>();

    private final Collection<DataEntityEventHandler> handlers;

    public DataEntityStoreImpl(Collection<DataEntityEventHandler> handlers) {
        this.handlers = handlers;
    }
    @Override
    public void add(DataEntity entity) {
        dataEntities.add(entity);

        uniqueGeozones.add(entity.getGeozone());
        uniqueContracts.add(entity.getContractId());

        // Notify each handler
        this.handlers.stream().forEach(handler -> handler.onDataEntityAdd(entity));
    }

    @Override
    public Collection<DataEntity> getAll() {
        return dataEntities;
    }

    @Override
    public Collection<String> uniqueGeozone() {
        return Collections.unmodifiableCollection(uniqueGeozones);
    }

    @Override
    public Collection<Integer> uniqueContracts() {
        return Collections.unmodifiableCollection(uniqueContracts);
    }
}
