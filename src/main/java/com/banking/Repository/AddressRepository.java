package com.banking.Repository;

import com.banking.model.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address,Long> {


//    List<Address> findByConsumerId(Long consumerId);

//    List<Address> findByConsumer_id(Long id);



}
