package cn.service;

import cn.bean.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();
    public void save(Product product);
    public Product findById(Integer id);
}
