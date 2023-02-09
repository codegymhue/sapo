package vn.sapo.controllers.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.sapo.employee.EmployeeService;
import vn.sapo.payment.method.PaymentMethodService;
import vn.sapo.supplier.SupplierService;
import vn.sapo.supplier.dto.SupplierResult;

@Controller
@RequestMapping("/admin/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PaymentMethodService paymentMethodService;

    @GetMapping
    public ModelAndView showSupplierListPage() {
        return new ModelAndView("/admin/suppliers/supplier_list");
    }

//    @GetMapping("SearchSupplierGroupV1")
//    public ResponseEntity<?> showSupplierListPage1() {
//        return new ResponseEntity<>("{\"supplierGroups\":[{\"id\":2663304,\"tenantId\":690985,\"type\":\"supplier\",\"name\":\"sĩ\",\"nameTranslate\":\"sĩ\",\"createdOn\":\"2023-02-08T07:39:48Z\",\"modifiedOn\":\"2023-02-08T07:39:48Z\",\"status\":\"active\",\"code\":\"STN00004\",\"note\":\"\",\"countSupplier\":0,\"isDefault\":false},{\"id\":2663303,\"tenantId\":690985,\"type\":\"supplier\",\"name\":\"bán buôn\",\"nameTranslate\":\"bán buôn\",\"createdOn\":\"2023-02-08T07:39:31Z\",\"modifiedOn\":\"2023-02-08T07:39:31Z\",\"status\":\"active\",\"code\":\"1\",\"note\":\"\",\"countSupplier\":0,\"isDefault\":false},{\"id\":2663254,\"tenantId\":690985,\"type\":\"supplier\",\"name\":\"bán sĩ\",\"nameTranslate\":\"bán sĩ\",\"createdOn\":\"2023-02-08T07:17:54Z\",\"modifiedOn\":\"2023-02-08T07:17:54Z\",\"status\":\"active\",\"code\":\"STN00003\",\"note\":\"\",\"countSupplier\":0,\"isDefault\":false},{\"id\":2660089,\"tenantId\":690985,\"type\":\"supplier\",\"name\":\"Buôn\",\"nameTranslate\":\"Buôn\",\"createdOn\":\"2023-02-06T01:31:28Z\",\"modifiedOn\":\"2023-02-06T01:31:28Z\",\"status\":\"active\",\"code\":\"STN00002\",\"note\":\"\",\"countSupplier\":0,\"isDefault\":false},{\"id\":2660079,\"tenantId\":690985,\"type\":\"supplier\",\"name\":\"DEFAULT\",\"nameTranslate\":\"Khác\",\"createdOn\":\"2023-02-06T01:13:14Z\",\"modifiedOn\":\"2023-02-06T01:13:14Z\",\"status\":\"active\",\"code\":\"MACDINH\",\"note\":null,\"countSupplier\":32,\"isDefault\":true}],\"totalCount\":5,\"totalPage\":1,\"page\":1}", HttpStatus.OK);
//    }

    @GetMapping("/export")
    public ModelAndView showSupplierListExport() {
        return new ModelAndView("/admin/suppliers/modals/export_file");
    }

    @GetMapping("/import")
    public ModelAndView showSupplierListImport() {
        return new ModelAndView("/admin/suppliers/modals/import_file");
    }


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

    @GetMapping("/{id}/edit")
    public ModelAndView showSupplierEdit(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            SupplierResult supplier = supplierService.findById(id);
            modelAndView.addObject("supplier", supplier);
            modelAndView.addObject("employee", employeeService.findAll());
            modelAndView.addObject("paymentMethod", paymentMethodService.findAll());
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
        }
        modelAndView.setViewName("/admin/suppliers/update_supplier");
//        modelAndView.setViewName("/admin/suppliers/cap");
        return modelAndView;
    }

}