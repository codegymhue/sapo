import { useState } from "react";
import { Routes, Route } from "react-router-dom";

import "./app.scss";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import SideBar from "./components/Navbar/SideBar";
import TopBar from "./components/Navbar/TopBar";
import Customers from "./components/Customer/list/Customers";
import CustomerGroups from "./components/customer-group/list/CustomerGroups";

function App() {
  return (
    <div className="app">
      <SideBar />
      <main className="content">
        <div className="d-flex">
          <TopBar />
        </div>
        <Routes>
          <Route path="/customers" element={<Customers />} />
          <Route path="/customer-groups" element={<CustomerGroups />} />
        </Routes>
      </main>
    </div>
  );
}

export default App;
