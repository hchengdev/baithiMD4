package com.baithimd4.controller;

import com.baithimd4.model.Oder;
import com.baithimd4.model.Product;
import com.baithimd4.repository.IProductRepo;
import com.baithimd4.service.IOderService;
import com.baithimd4.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final IOderService service;
    private final IProductService productService;

    @GetMapping()
    public String getOrders(Model model, Pageable pageable) {
        Page<Oder> orderList = service.findAll(pageable);
        model.addAttribute("orderList", orderList);
        return "/list";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Oder> oder = service.findById(id);
        ModelAndView update = new ModelAndView("/update");
        if (oder.isPresent()) {
            update.addObject("oder", oder.get());
        } else {
            throw new RuntimeException("Order not found for ID: " + id);
        }
        return update;
    }

    @PostMapping("/update")
    public String update(Oder oder) {
        service.save(oder);
        return "redirect:/list";
    }
}
