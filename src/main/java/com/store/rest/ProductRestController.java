package com.store.rest;

import com.store.entity.Product;
import com.store.exception.ResourceNotFoundException;
import com.store.repository.ProductRepository;
import com.store.temporary.TopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/product")
public class ProductRestController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> list = productRepository.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        Product findId = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));
        return ResponseEntity.ok(findId);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> update(@PathVariable("id") int id, @RequestBody Product product) {
        Product findId = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));
        productRepository.save(product);
        return ResponseEntity.ok(findId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        Product findId = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));
        productRepository.delete(findId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("getTopProduct/{date}")
    public ResponseEntity<List<TopProduct>> getTopProduct(@PathVariable String date) {
        List<TopProduct> listProduct = productRepository.getTopProduct(date);
        if (listProduct.isEmpty()) {
            return new ResponseEntity<List<TopProduct>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<TopProduct>>(listProduct, HttpStatus.OK);
    }

//    @GetMapping("search/{name}{cate}")
//    public List<Product> search(@PathVariable("name") Optional<String> name, @PathVariable("cate") Optional<String> cate){
//        if (name.isPresent()){
//            if(cate.isPresent()) {
//                return productRepository.findByNameLikeAndCateNameLike(name.orElse(" "), cate.orElse(" "));
//            }else{
//                return productRepository.findByNameLike(name.orElse(" "));
//            }
//        }
//        return productRepository.findAll();
//    }

    @GetMapping("search")
    public List<Product> searchAll(){
        return productRepository.findAll();
    }

    @GetMapping("search/{name}")
    public List<Product> searchProdName(@PathVariable("name") Optional<String> name){
        if (name.isPresent()) {
            return productRepository.findByNameLike(name.orElse(""));
        }
        return productRepository.findAll();
    }
}
