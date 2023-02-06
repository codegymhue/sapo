package vn.sapo.address;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.address.dto.UpdateAddressParam;
import vn.sapo.entities.Address;
import vn.sapo.entities.customer.Customer;
import vn.sapo.shared.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressMapper addressMapper;


    @Override
    @Transactional(readOnly = true)
    public AddressResult findById(Integer id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("address not found"));
        Integer customerId = address.getCustomerId();
        return addressMapper.toDTO(address);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressResult> findByCustomerId(Integer customerId) {
        return addressRepository.findAllByCustomerId(customerId)
                .stream()
                .map(addressMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressResult> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AddressResult update(UpdateAddressParam updateAddressParam) {
        if (!addressRepository.existsById(updateAddressParam.getId()))
            throw new NotFoundException("address not found");
        Address address = addressMapper.toModel(updateAddressParam);
        address = addressRepository.save(address);
        return addressMapper.toDTO(address);
    }

    @Override
    @Transactional
    public AddressResult create(CreateAddressParam createAddressParam) {
        Address address = addressRepository.save(addressMapper.toModel(createAddressParam));
        return addressMapper.toDTO(address);
    }

    @Override
    @Transactional
    public void deleteByCustomerId(Integer customerId) {
        addressRepository.deleteByCustomerId(customerId);
    }
}


