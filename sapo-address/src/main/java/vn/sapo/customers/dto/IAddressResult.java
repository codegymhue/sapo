package vn.sapo.customers.dto;

public interface IAddressResult {

    Integer getId();

    String getPhoneNumber();

    String getLine1();

    Integer getDistrictId();

    String getDistrictName();

    Integer getProvinceId();

    String getProvinceName();

    Integer getWardId();

    String getWardName();

    String getZipCode();

    String getEmail();
}
