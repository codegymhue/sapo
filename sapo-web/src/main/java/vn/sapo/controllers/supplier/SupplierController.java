package vn.sapo.controllers.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.sapo.employee.EmployeeService;
import vn.sapo.payment.method.PaymentMethodService;
import vn.sapo.supplier.SupplierExcelExporter;
import vn.sapo.supplier.SupplierExcelExporterInventory;
import vn.sapo.supplier.SupplierService;
import vn.sapo.supplier.dto.SupplierResult;
import vn.sapo.supplierGroup.SupplierGroupService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/suppliers")
public class SupplierController {

    @Autowired
    SupplierAPI supplierAPI;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    SupplierGroupService supplierGroupService;

    @Autowired
    PaymentMethodService paymentMethodService;

    @GetMapping
    public ModelAndView showSupplierListPage() {
        return new ModelAndView("/admin/suppliers/supplier_list_small");
    }
    @GetMapping("/small")
    public ModelAndView showSupplierListPageSmall() {
        return new ModelAndView("/admin/suppliers/supplier_list_small2");
    }

//    @GetMapping("SearchSupplierGroupV1")
//    public ResponseEntity<?> showSupplierListPage1() {
//        return new ResponseEntity<>("{\"supplierGroups\":[{\"id\":2663304,\"tenantId\":690985,\"type\":\"supplier\",\"name\":\"sĩ\",\"nameTranslate\":\"sĩ\",\"createdOn\":\"2023-02-08T07:39:48Z\",\"modifiedOn\":\"2023-02-08T07:39:48Z\",\"status\":\"active\",\"code\":\"STN00004\",\"note\":\"\",\"countSupplier\":0,\"isDefault\":false},{\"id\":2663303,\"tenantId\":690985,\"type\":\"supplier\",\"name\":\"bán buôn\",\"nameTranslate\":\"bán buôn\",\"createdOn\":\"2023-02-08T07:39:31Z\",\"modifiedOn\":\"2023-02-08T07:39:31Z\",\"status\":\"active\",\"code\":\"1\",\"note\":\"\",\"countSupplier\":0,\"isDefault\":false},{\"id\":2663254,\"tenantId\":690985,\"type\":\"supplier\",\"name\":\"bán sĩ\",\"nameTranslate\":\"bán sĩ\",\"createdOn\":\"2023-02-08T07:17:54Z\",\"modifiedOn\":\"2023-02-08T07:17:54Z\",\"status\":\"active\",\"code\":\"STN00003\",\"note\":\"\",\"countSupplier\":0,\"isDefault\":false},{\"id\":2660089,\"tenantId\":690985,\"type\":\"supplier\",\"name\":\"Buôn\",\"nameTranslate\":\"Buôn\",\"createdOn\":\"2023-02-06T01:31:28Z\",\"modifiedOn\":\"2023-02-06T01:31:28Z\",\"status\":\"active\",\"code\":\"STN00002\",\"note\":\"\",\"countSupplier\":0,\"isDefault\":false},{\"id\":2660079,\"tenantId\":690985,\"type\":\"supplier\",\"name\":\"DEFAULT\",\"nameTranslate\":\"Khác\",\"createdOn\":\"2023-02-06T01:13:14Z\",\"modifiedOn\":\"2023-02-06T01:13:14Z\",\"status\":\"active\",\"code\":\"MACDINH\",\"note\":null,\"countSupplier\":32,\"isDefault\":true}],\"totalCount\":5,\"totalPage\":1,\"page\":1}", HttpStatus.OK);
//    }


    @GetMapping("/{id}/histories")
    public ModelAndView showSupplierHistoryPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            SupplierResult supplier = supplierService.findById(id);
            modelAndView.addObject("supplier", supplier);
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
        }
//        modelAndView.setViewName("/admin/suppliers/detail_suppliers");
        modelAndView.setViewName("/admin/suppliers/supplier_detail");

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showSupplierCreatePage() {
        return new ModelAndView("/admin/suppliers/supplier_create");
    }
//    @GetMapping("/{id}")
//    public ModelAndView showSupplierInfoPage(@PathVariable Integer id) {
//        ModelAndView modelAndView = new ModelAndView();
//        try{
//            SupplierResult supplier = supplierService.findById(id);
//            supplierAPI.setData(supplier);
//            modelAndView.addObject("supplier", supplier);
//        }catch (Exception ex){
//            modelAndView.addObject("errors", ex.getMessage());
//        }
//        modelAndView.setViewName("/admin/suppliers/supplier_detail");
//        return modelAndView;
//    }


    @GetMapping("/{id}/edit")
    public ModelAndView showSupplierEdit(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            SupplierResult supplier = supplierService.findById(id);
            modelAndView.addObject("supplier", supplier);
            modelAndView.addObject("supplierGroup", supplierGroupService.finAll());
            modelAndView.addObject("employee", employeeService.findAll());
            modelAndView.addObject("paymentMethods", paymentMethodService.findAll());
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
        }
        modelAndView.setViewName("/admin/suppliers/supplier_update");
//        modelAndView.setViewName("/admin/suppliers/cap");
        return modelAndView;
    }

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Suppliers_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<SupplierResult> listSuppliers = supplierService.findAll();
        SupplierExcelExporter excelExporter = new SupplierExcelExporter(listSuppliers);
        excelExporter.export(response);
    }

    @GetMapping("/excel")
    public void exportItemToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Supplier_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<SupplierResult> listSuppliers = supplierService.findAll();
        SupplierExcelExporterInventory excelExporter = new SupplierExcelExporterInventory(listSuppliers);
        excelExporter.export(response);
    }

    @GetMapping("/{id}/addresses")
    public ModelAndView showSupplierAddress(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            SupplierResult supplier = supplierService.findById(id);
            modelAndView.addObject("supplier", supplier);
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
        }
        modelAndView.setViewName("/admin/suppliers/supplier_address");

        return modelAndView;
    }

}