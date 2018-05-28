package com.abhishekd.restapi.api.v1.mapper;

import com.abhishekd.restapi.api.v1.model.CustomerDTO;
import com.abhishekd.restapi.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Data transfer object mapper using MapStruct
 */
@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
