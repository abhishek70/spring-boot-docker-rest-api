package com.abhishekd.restapi.services;


import com.abhishekd.restapi.api.v1.model.VendorDTO;

import java.util.List;

/**
 * Vendor Interface
 */
public interface VendorService {

    // Get all vendors
    List<VendorDTO> getVendors();

    // Get vendor by id
    VendorDTO getVendorById(Long vendorId);

    // Create new vendor
    VendorDTO createVendor(VendorDTO vendorDTO);

    // Put vendor
    VendorDTO updateVendor(Long vendorId, VendorDTO vendorDTO);

    // Patch customer
    VendorDTO patchVendor(Long vendorId, VendorDTO vendorDTO);

    // Delete vendor
    void deleteVendorById(Long vendorId);
}
