package com.mgupta.oracle.store;

public interface ContractStore extends DataEntityStoreBase {
    int uniqueCustomer(final Integer contractId);
}
