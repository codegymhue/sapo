class LocationRegionCreate {
    constructor(address, wardId, wardName, districtId, districtName, provinceId, provinceName) {
        this.address = address;
        this.wardId = wardId;
        this.wardName = wardName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
    }
}

class Supplier {
    constructor(id, name, phone, email, locationRegionCreate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.locationRegionCreate = locationRegionCreate;
    }

}
