package com.abhishekd.restapi.api.v1.mapper;

import com.abhishekd.restapi.api.v1.model.VendorDTO;
import com.abhishekd.restapi.domain.Vendor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendorMapperTest {

    public static final String FRUITS = "Fruits";
    public static final String SHOPS = "Shops";
    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void vendorToVendorDTO() {

        // Given
        Vendor vendor = new Vendor();
        vendor.setVendorName(FRUITS);
        vendor.setVendorId(1L);

        // When
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        // Then
        assertEquals(vendorDTO.getVendorName(), FRUITS);
    }

    @Test
    public void vendorDTOToVendor() {

        // Given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setVendorName(SHOPS);

        // When
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        // Then
        assertEquals(vendor.getVendorName(), SHOPS);

    }
}