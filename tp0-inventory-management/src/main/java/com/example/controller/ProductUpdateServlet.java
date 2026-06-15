package com.example.controller;

import com.example.dao.ProductDAO;
import com.example.model.Product;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

public class ProductUpdateServlet extends HttpServlet {
    private ProductDAO productDAO;
    public void init() { productDAO = new ProductDAO(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Optional<Product> optionalProduct = productDAO.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(request.getParameter("name"));
            product.setDescription(request.getParameter("description"));
            product.setPrice(new BigDecimal(request.getParameter("price")));
            product.setStockQuantity(Integer.parseInt(request.getParameter("stockQuantity")));
            product.setSku(request.getParameter("sku"));
            productDAO.update(product);
        }
        response.sendRedirect("products");
    }
}
