import axios from "axios";
import { CUSTOMER_GROUP_API } from "./customerGroupApi";
import { DataTableInput } from "../../interfaces/share/ShareInterface";

class CustomerGroupsService {
  static getCustomerGroupsPagination(dataTableInput: DataTableInput) {
    return axios.post(CUSTOMER_GROUP_API, dataTableInput, {
      headers: {
        "Content-Type": "application/json",
      },
    });
  }
}

export default CustomerGroupsService;
