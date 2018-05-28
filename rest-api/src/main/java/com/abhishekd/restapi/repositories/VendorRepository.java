package com.abhishekd.restapi.repositories;

import com.abhishekd.restapi.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Vendor repository class for database interaction
 */
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
