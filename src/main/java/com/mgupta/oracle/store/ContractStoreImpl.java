package com.mgupta.oracle.store;

import com.mgupta.oracle.entity.DataEntity;

import java.util.HashMap;
import java.util.HashSet;

public class ContractStoreImpl implements ContractStore {
    private HashMap<Integer, HashSet<Integer>> contractInfo;

    public ContractStoreImpl() {
        contractInfo = new HashMap<>();
    }

    @Override
    public int uniqueCustomer(Integer contractId) {
        return !contractInfo.containsKey(contractId) ? 0 : contractInfo.getOrDefault(contractId, new HashSet<>()).size();
    }

    @Override
    public void add(DataEntity entity) {
        if ( !contractInfo.containsKey(entity.getContractId())) {
            contractInfo.put(entity.getContractId(), new HashSet<>());
        }

        HashSet<Integer> customers = contractInfo.get(entity.getContractId());
        customers.add(entity.getCustomerId());
    }
}
