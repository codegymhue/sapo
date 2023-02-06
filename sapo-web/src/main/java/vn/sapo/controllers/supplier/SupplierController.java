package vn.sapo.controllers.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.sapo.supplier.SupplierService;
import vn.sapo.supplier.dto.SupplierResult;

@Controller
@RequestMapping("/admin/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ModelAndView showSupplierListPage() {
        return new ModelAndView("/admin/suppliers/supplier_list");
    }

    @GetMapping("/export")
    public ModelAndView showSupplierListExport() {
        return new ModelAndView("/admin/suppliers/modals/export_file");
    }

    @GetMapping("/import")
    public ModelAndView showSupplierListImport() {
        return new ModelAndView("/admin/suppliers/modals/import_file");
    }


    @GetMapping("/{id}/detail")
    public ModelAndView showSupplierHistoryPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        try{
            SupplierResult supplier = supplierService.findById(id);
            modelAndView.addObject("supplier", supplier);
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
        }
        modelAndView.setViewName("/admin/suppliers/detail_suppliers");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showSupplierCreatePage() {
        return new ModelAndView("/admin/suppliers/supplier_create");
    }
    @GetMapping("/detail")
    public ModelAndView showSupplierDetailPage() {
        return new ModelAndView("/admin/suppliers/detail_suppliers");
    }

}