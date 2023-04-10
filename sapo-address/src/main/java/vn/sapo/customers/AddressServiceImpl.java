package vn.sapo.customers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.customers.dto.*;
import vn.sapo.entities.Address;
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
    @Transactional
    public DeleteAddressResult deleteAddressesByListId(Integer id, List<Integer> ids) {
        Integer total = addressRepository.countAddressesByCustomerId(id);
        DeleteAddressResult result = new DeleteAddressResult();
        List<String> deletedNames;

        if (total == 1)
            result.setMessage(new HashMap<>() {{
                put("fail", "address.exception.lastAddress");
            }});

        if (total > ids.size()) {
            deletedNames = addressRepository.findLine1ByIds(ids);
            addressRepository.deleteByIdIn(ids);

            result.setNumberOfSuccess(ids.size())
                    .setMessage(new HashMap<>() {{
                        put("success", "Xóa thành công " + ids.size() + " địa chỉ");
                    }})
                    .setNamesDeleted(deletedNames)
                    .setIdsDeleted(ids);
        }

        if (ids.size() == total) {
            List<Integer> currentListAddressesId = addressRepository.findAllAddressIdByCustomerId(id);
            List<Integer> newListAddressesId = currentListAddressesId.subList(1, currentListAddressesId.size());
            deletedNames = addressRepository.findLine1ByIds(newListAddressesId);
            int deleteSize = newListAddressesId.size();
            addressRepository.deleteByIdIn(newListAddressesId);

            result.setNumberOfSuccess(newListAddressesId.size())
                    .setNumberOfFail(currentListAddressesId.size() - deleteSize)
                    .setAddressResult(findById(currentListAddressesId.get(0)))
                    .setMessage(new HashMap<>() {{
                        put("fail", "address.exception.lastAddress");
                    }})
                    .setNamesDeleted(deletedNames)
                    .setIdsDeleted(newListAddressesId);
        }

        return result;
    }

    @Override
    public Page<IAddressResult> findAllAddresses(Pageable pageable, Integer id) {
        return addressRepository.findAllByCustomerIdPageable(pageable, id);
    }

    @Override
    @Transactional
    public AddressResult createAddressWithCustomerId(CreateAddressParam createAddressParam, Integer id) {
        createAddressParam.setCustomerId(id);
        return create(createAddressParam);
    }

    @Override
    @Transactional(readOnly = true)
    public AddressResult findById(Integer id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("address.exception.notFound"));
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
        Integer id = updateAddressParam.getId();
//        updateAddressParam.setId(id);
//        Address address = addressMapper.toModel(updateAddressParam);
//        addressRepository.save(address);
//        return findById(id);
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new NotFoundException("address.exception.notFound")
        );

        address.setFullName(updateAddressParam.getFullName())
                .setPhoneNumber(updateAddressParam.getPhoneNumber())
                .setLine1(updateAddressParam.getLine1())
                .setEmail(updateAddressParam.getEmail())
                .setProvinceId(updateAddressParam.getProvinceId())
                .setProvinceName(updateAddressParam.getProvinceName())
                .setDistrictId(updateAddressParam.getDistrictId())
                .setDistrictName(updateAddressParam.getDistrictName())
                .setWardId(updateAddressParam.getWardId())
                .setWardName(updateAddressParam.getWardName())
                .setZipCode(updateAddressParam.getZipCode());

        addressRepository.save(address);

        return findById(id);
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
            if (address.isPresent()) {
                addressRepository.deleteById(address.get().getId());
            } else {
                throw new NotFoundException("Address not found");
            }
        }
    }

    @Override
    @Transactional
    public Map<String, Object> getAllAddressSupplierPage(Integer pageNo, Integer pageSize, Integer supplierId) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        Page<Address> addresses = addressRepository.findAllBySupplierId(pageable, supplierId);
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
        } else {
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
    public void create(List<CreateAddressParam> createAddressParamList) {
        List<Address> addresses = createAddressParamList.stream().map(addressMapper::toModel).collect(Collectors.toList());
        addressRepository.saveAll(addresses);
    }

    @Override
    @Transactional
    public void deleteByCustomerId(Integer customerId) {
        addressRepository.deleteByCustomerId(customerId);
    }

    @Override
    @Transactional
    public void deleteAllBySupplierId(Integer id) {
        addressRepository.deleteAllBySupplierId(id);
    }

}


