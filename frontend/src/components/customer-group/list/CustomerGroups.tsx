import { useState, useEffect } from "react";
import CustomerGroupsService from "../../../services/customerGroupService/CustomerGroupService";
import MUIDataTable, { MUIDataTableOptions } from "mui-datatables";
import { Button } from "react-bootstrap";
import "./customerGroups.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { DataTableInput } from "../../../interfaces/share/ShareInterface";
import { CustomerGroup } from "../../../interfaces/customerGroup/customerGroupInterface";

const columns = [
  {
    name: "title",
    label: "Tên nhóm",
    options: {
      filter: false,
      sort: true,
    },
  },
  {
    name: "cusCode",
    label: "Mã nhóm",
    options: {
      filter: false,
      sort: false,
    },
  },
  {
    name: "type",
    label: "Loại nhóm",
    options: {
      filter: false,
      sort: false,
    },
  },
  {
    name: "note",
    label: "Mô tả",
    options: {
      filter: false,
      sort: false,
    },
  },
  {
    name: "countCus",
    label: "Số lượng khách hàng",
    options: {
      filter: false,
      sort: false,
    },
  },
  {
    name: "createAt",
    label: "Ngày tạo",
    options: {
      filter: false,
      sort: false,
    },
  },
];

const dataTableInput: DataTableInput = {
  draw: 1,
  start: 0,
  length: 10,
  order: [{ dir: "DESC" }],
};

const options: MUIDataTableOptions = {
  serverSide: true,
  filter: false,
  search: false,
  print: false,
  download: false,
  viewColumns: false,
  customToolbar: undefined,
  responsive: "vertical",
};

function CustomerGroups() {
  const [customerGroups, setCustomerGroups] = useState<any>([]);

  useEffect(() => {
    try {
      async function getCustomerGroupsPagination() {
        const customerGroupRes =
          await CustomerGroupsService.getCustomerGroupsPagination(
            dataTableInput
          );
        setCustomerGroups(customerGroupRes.data.data);
      }
      getCustomerGroupsPagination();
    } catch (error) {
      console.log(error);
    }
  }, []);

  return (
    <>
      <div className="customer-groups">
        <div className="d-flex justify-content-end">
          <Button
            variant="primary"
            type="button"
            className="add-customer-group my-3"
          >
            <span className="mx-2">
              <FontAwesomeIcon icon={faPlus} />
            </span>
            <span>Thêm nhóm khách hàng</span>
          </Button>
        </div>
        <div className="customer-group-table">
          <MUIDataTable
            title={""}
            data={customerGroups}
            columns={columns}
            options={options}
            components={{
              Checkbox: () => null,
            }}
          />
        </div>
      </div>
    </>
  );
}

export default CustomerGroups;
