package com.mgupta.oracle.events;

import com.mgupta.oracle.entity.DataEntity;

public interface DataEntityEventHandler {
    void onDataEntityAdd(DataEntity entity);
}
