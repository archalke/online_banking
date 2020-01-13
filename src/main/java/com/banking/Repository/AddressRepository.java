package com.banking.Repository;

import com.banking.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {




}
