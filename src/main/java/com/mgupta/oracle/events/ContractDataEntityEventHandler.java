package com.mgupta.oracle.events;

import com.mgupta.oracle.entity.DataEntity;
import com.mgupta.oracle.store.ContractStore;

public class ContractDataEntityEventHandler implements DataEntityEventHandler{
    private final ContractStore store;

    public ContractDataEntityEventHandler(final ContractStore store) {
        this.store = store;
    }

    @Override
    public void onDataEntityAdd(DataEntity entity) {
        // System.out.println("[Notify][ContractDataEntity] " + entity.getCustomerId());
        this.store.add(entity);
    }
}
