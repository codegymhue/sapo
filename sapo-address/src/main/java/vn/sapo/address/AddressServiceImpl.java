package vn.sapo.address;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.address.dto.UpdateAddressParam;
import vn.sapo.entities.Address;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.product.Product;
import vn.sapo.entities.supplier.Supplier;
import vn.sapo.entities.supplier.SupplierStatus;
import vn.sapo.shared.exceptions.NotFoundException;

import java.util.*;
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
    public List<AddressResult> findBySupplierId(Integer supplierId) {
        return addressRepository.findAllBySupplierId(supplierId)
                .stream()
                .map(addressMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteByAddressSupplierId(Integer idAddressSupplier) {
        addressRepository.deleteById(idAddressSupplier);
    }

    @Override
    public void deleteSoftSupplier(List<Integer> supplierAddressIds) {
        for (Integer supplierId : supplierAddressIds) {
            Optional<Address> address = addressRepository.findById(supplierId);
            if(address.isPresent()) {
                addressRepository.deleteById(address.get().getId());
            }else {
                throw new NotFoundException("Address not found");
            }
        }
    }

    @Override
    @Transactional
    public Map<String, Object> getAllAddressSupplierPage(Integer pageNo, Integer pageSize, Integer supplierId) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);

        Page<Address> addresses = addressRepository.findAllBySupplierId(pageable,supplierId );
        if (addresses.hasContent()) {
            List<Address> addressList = addresses.getContent();
            System.out.println("page: " + addressList);
            List<AddressResult> addressResultList = new ArrayList<>();

            for (Address address : addressList) {
                AddressResult addressResult = addressMapper.toDTO(address);
                addressResultList.add(addressResult);
            }

            Map<String, Object> response = new HashMap<>();

            response.put("addresses", addressResultList);
            response.put("totalItem", addresses.getTotalElements());
            response.put("totalPage", addresses.getTotalPages());

            return response;
        }
        else {
            return new HashMap<>();
        }
    }

    @Override
    @Transactional
    public AddressResult create(CreateAddressParam createAddressParam) {
        Address address = addressRepository.save(addressMapper.toModel(createAddressParam));
        return addressMapper.toDTO(address);
    }

    @Override
    @Transactional
    public void create(List<CreateAddressParam> createShippingAddressParams) {
        List<Address> addresses = createShippingAddressParams.stream().map(addressMapper::toModel).collect(Collectors.toList());
        addressRepository.saveAll(addresses);
    }

    @Override
    @Transactional
    public void deleteByCustomerId(Integer customerId) {
        addressRepository.deleteByCustomerId(customerId);
    }
    @Override
    @Transactional
   public void deleteAllBySupplierId(Integer id){
        addressRepository.deleteAllBySupplierId(id);
    };
}


