package com.baithimd4.controller;

import com.baithimd4.model.Category;
import com.baithimd4.model.Oder;
import com.baithimd4.model.Product;
import com.baithimd4.repository.IProductRepo;
import com.baithimd4.service.ICategoryService;
import com.baithimd4.service.IOderService;
import com.baithimd4.service.IProductService;
import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    IOderService service;
    @Autowired
    IProductService productService;
    @Autowired
    ICategoryService categoryService;

    @GetMapping()
    public String getOrders(Model model,@PageableDefault(value = 5) Pageable pageable) {
        Page<Oder> orderList = service.findAll(pageable);
        model.addAttribute("orderList", orderList);
        return "/list";
    }

    @GetMapping("/update/{id}")
    public String editOrder(@PathVariable("id") Long id, Model model) {
        Optional<Oder> order = service.findById(id);
        if (order == null) {
            // Handle order not found
            return "redirect:/list";
        }
        Iterable<Product> products = productService.findAll();
        List<Category> productTypes = (List<Category>) categoryService.findAll();
        model.addAttribute("order", order.get());
        model.addAttribute("products", products);
        model.addAttribute("productTypes", productTypes);
        return "/update";
    }

    @PostMapping("/update/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute("order") Oder editedOrder, RedirectAttributes redirectAttributes) {
        if(service.findById(id).isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Order not found");
            return "redirect:/orders";
        }
        service.save(editedOrder);
        return "redirect:/list";
    }


    @GetMapping("/search")
    public String searchOrders(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PageableDefault(value = 5) Pageable pageable,
            Model model) {

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        Page<Oder> orders = service.findByNgayMuaBetween(startDateTime, endDateTime, pageable);
        model.addAttribute("orderList", orders);
        return "list";
    }
}
