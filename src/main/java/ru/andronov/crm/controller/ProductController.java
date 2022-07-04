package ru.andronov.crm.controller;

import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.andronov.crm.domain.Product;
import ru.andronov.crm.service.IProductService;
import ru.andronov.crm.utils.ProductPDFExporter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final IProductService productService;

    @GetMapping(path = "product/all/lead/{id}")
    public List<Product> getAllProductsByLeadId(@PathVariable("id") int leadId) {
        log.info("Getting all products by lead id {}", leadId);
        return productService.getProductsByLeadId(leadId);
    }

    @GetMapping(path = "service/all/lead/{id}")
    public List<Product> getAllServicesByLeadId(@PathVariable("id") int leadId) {
        log.info("Getting all services by lead id {}", leadId);
        return productService.getServicesByLeadId(leadId);
    }

    @PostMapping(path = "product/create")
    public Product createProduct(@RequestBody Product product) {
        log.info("Creating new {}", product);
        return productService.create(product);
    }

    @PutMapping(path = "product/edit")
    public Product updateProduct(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping(path = "product/{id}")
    public void updateProduct(@PathVariable("id") int id) {
        productService.deleteById(id);
    }

    @GetMapping("product/export/pdf/{lead_id}")
    public void exportToPDF(@PathVariable("lead_id") int leadId,
                            @RequestParam(name = "discount", defaultValue = "0") int discount,
                            HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=products_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Product> products = productService.getProductsByLeadId(leadId);

        var exporter = new ProductPDFExporter(products, discount);
        exporter.export(response);

    }
}
