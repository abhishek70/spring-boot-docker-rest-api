package com.abhishekd.restapi.services;

import com.abhishekd.restapi.api.v1.mapper.VendorMapper;
import com.abhishekd.restapi.api.v1.model.CustomerDTO;
import com.abhishekd.restapi.api.v1.model.VendorDTO;
import com.abhishekd.restapi.controllers.v1.VendorController;
import com.abhishekd.restapi.domain.Customer;
import com.abhishekd.restapi.domain.Vendor;
import com.abhishekd.restapi.exceptions.ResourceNotFoundException;
import com.abhishekd.restapi.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Vendor service implementation class
 */
@Service
public class VendorServiceImpl implements VendorService {

    /**
     * Injecting dependencies
     */
    private VendorRepository vendorRepository;
    private VendorMapper vendorMapper;

    /**
     * Constructor
     * @param vendorRepository
     * @param vendorMapper
     */
    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    /**
     * Method implementation for getting all vendors from the database
     * @return
     */
    @Override
    public List<VendorDTO> getVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getVendorId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    /**
     * Method implmentation for getting vendor by provided vendor Id
     * @param vendorId
     * @return
     */
    @Override
    public VendorDTO getVendorById(Long vendorId) {
        return  vendorRepository.findById(vendorId)
                .map(vendorMapper::vendorToVendorDTO)
                .map(vendorDTO -> {
                    vendorDTO.setVendorUrl(getVendorUrl(vendorId));
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    /**
     * Method implementation for creating a new vendor by provided vendor post data
     * @param vendorDTO
     * @return
     */
    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        return createOrUpdateVendorHelper(vendor);
    }

    /**
     * Helper method for create or update customer
     * @param vendor
     * @return VendorDTO
     */
    private VendorDTO createOrUpdateVendorHelper(Vendor vendor) {

        Vendor saveVendor = vendorRepository.save(vendor);

        VendorDTO saveVendorDTO = vendorMapper.vendorToVendorDTO(saveVendor);

        saveVendorDTO.setVendorUrl(getVendorUrl(saveVendor.getVendorId()));

        return saveVendorDTO;
    }

    /**
     * Method implementation for updating vendor by vendor Id
     * @param vendorId
     * @param vendorDTO
     * @return
     */
    @Override
    public VendorDTO updateVendor(Long vendorId, VendorDTO vendorDTO) {

        return vendorRepository.findById(vendorId).map(vendor -> {

            VendorDTO vendorDTO1 = null;

            if(vendor.getVendorId() != null) {
                Vendor saveVendor = vendorMapper.vendorDTOToVendor(vendorDTO);
                saveVendor.setVendorId(vendorId);

                vendorDTO1 = createOrUpdateVendorHelper(saveVendor);
            }

            return vendorDTO1;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    /**
     * Method implmentation for partial updating vendor by vendor Id
     * @param vendorId
     * @param vendorDTO
     * @return
     */
    @Override
    public VendorDTO patchVendor(Long vendorId, VendorDTO vendorDTO) {

        return vendorRepository.findById(vendorId).map(vendor -> {

            if(vendorDTO.getVendorName() != null) {
                vendor.setVendorName(vendorDTO.getVendorName());
            }

            VendorDTO saveVendorDTO = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
            saveVendorDTO.setVendorUrl(getVendorUrl(vendorId));
            return saveVendorDTO;

        }).orElseThrow(ResourceNotFoundException::new);

    }

    /**
     * Method implementation for deleting vendor by provided vendor Id
     * @param vendorId
     */
    @Override
    public void deleteVendorById(Long vendorId) {

        // Finding vendor with the provided vendor Id
        Optional<Vendor> vendor = vendorRepository.findById(vendorId);

        // If vendor found in database then do delete else
        // throw resource not found exception
        if(vendor.isPresent()) {

            vendorRepository.deleteById(vendorId);
        } else throw new ResourceNotFoundException();
    }

    /**
     * Helper method for generating vendor url
     * @param vendorId
     * @return
     */
    private String getVendorUrl(Long vendorId) {

        return VendorController.BASE_URL + "/" + vendorId;
    }
}
