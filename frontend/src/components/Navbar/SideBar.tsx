import React from "react";
import { Link } from "react-router-dom";
import {
  Sidebar,
  Menu,
  MenuItem,
  SubMenu,
  useProSidebar,
  sidebarClasses,
} from "react-pro-sidebar";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEllipsisVertical, faUser } from "@fortawesome/free-solid-svg-icons";
import "font-awesome/css/font-awesome.min.css";
import "./sidebar.scss";
import Customers from "../Customer/list/Customers";
import CustomerGroups from "../customer-group/list/CustomerGroups";

const SideBar: React.FC = () => {
  const { collapseSidebar, collapsed } = useProSidebar();

  return (
    <Sidebar
      rootStyles={{
        [`.${sidebarClasses.container}`]: {
          backgroundColor: "#182537",
          color: "#F3F4F5",
        },
      }}
    >
      <Menu
        menuItemStyles={{
          button: {
            backgroundColor: "#182537",
            "&:hover": {
              backgroundColor: "#243041",
            },
          },
        }}
      >
        <div className="navbar_logo">
          {!collapsed && (
            <div className="w2rem">
              <img src="/assets/img/navbar/logo_sapo_white.svg" alt="img"></img>
            </div>
          )}
          <div>
            <FontAwesomeIcon
              icon={faEllipsisVertical}
              role="button"
              onClick={() => collapseSidebar()}
            />
          </div>
        </div>
        <SubMenu
          icon={<FontAwesomeIcon icon={faUser} role="button" />}
          label="Khách hàng"
        >
          <MenuItem component={<Link to="/customers" />}>
            Danh sách khách hàng
          </MenuItem>
          <MenuItem component={<Link to={"/customer-groups"} />}>
            Nhóm khách hàng
          </MenuItem>
        </SubMenu>
      </Menu>
    </Sidebar>
  );
};

export default SideBar;
