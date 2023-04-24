import axios from "axios";
import MUIDataTable, { MUIDataTableOptions } from "mui-datatables";
import { Button } from "react-bootstrap";
import "./customerGroups.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";

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

let data: (object | string[] | number[])[];

axios
  .post(
    "http://localhost:8080/api/customer_groups/pagination",
    {
      draw: 1,
      start: 0,
      length: 10,
      // order:
    },
    {
      headers: {
        "Content-Type": "application/json",
      },
    }
  )
  .then(
    (response) => {
      console.log(response);
    },
    (error) => {
      console.log("error");
      console.log(error);
    }
  );

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
            data={data}
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
