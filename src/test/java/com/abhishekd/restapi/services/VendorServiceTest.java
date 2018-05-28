package com.abhishekd.restapi.services;

import com.abhishekd.restapi.api.v1.mapper.VendorMapper;
import com.abhishekd.restapi.api.v1.model.VendorDTO;
import com.abhishekd.restapi.domain.Vendor;
import com.abhishekd.restapi.exceptions.ResourceNotFoundException;
import com.abhishekd.restapi.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VendorServiceTest {

    private VendorService vendorService;

    @Mock
    private VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    public void getVendors() {

        List<Vendor> vendorList = new ArrayList<>();

        // Given
        Vendor vendorOne = new Vendor();
        Vendor vendorTwo = new Vendor();

        vendorList.add(vendorOne);
        vendorList.add(vendorTwo);

        // When
        when(vendorRepository.findAll()).thenReturn(vendorList);

        List<VendorDTO> vendorDTOList = vendorService.getVendors();

        // Then
        assertEquals(vendorDTOList.size(), 2);
    }

    @Test
    public void getVendorById() {

        // Given
        Vendor vendor = new Vendor();
        vendor.setVendorId(1L);
        vendor.setVendorName("Fruits");

        // When
        when(vendorRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        // Then
        assertEquals(vendorDTO.getVendorName(), vendor.getVendorName());
    }

    @Test
    public void createVendor() {

        // Given
        VendorDTO vendorDTO = new VendorDTO();

        vendorDTO.setVendorName("Shops");


        Vendor vendor = new Vendor();
        vendor.setVendorName(vendorDTO.getVendorName());
        vendor.setVendorId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        // When
        VendorDTO saveVendor = vendorService.createVendor(vendorDTO);

        // Then
        assertEquals(saveVendor.getVendorName(), vendorDTO.getVendorName());
        assertEquals("/api/v1/vendors/1", saveVendor.getVendorUrl());

    }

    @Test (expected = ResourceNotFoundException.class)
    public void updateVendor() {

        // Given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setVendorName("Fruits");


        Vendor vendor = new Vendor();
        vendor.setVendorName(vendorDTO.getVendorName());
        vendor.setVendorId(1L);


        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        // When
        VendorDTO returnVendorDto = vendorService.updateVendor(vendor.getVendorId(), vendorDTO);

        // Then
        assertEquals(returnVendorDto.getVendorName(), vendorDTO.getVendorName());
        assertEquals(returnVendorDto.getVendorUrl(), "/api/v1/vendors/1");
    }

    @Test
    public void deleteVendorById() {

        Long vendorId = 1L;

        vendorRepository.deleteById(vendorId);

        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}