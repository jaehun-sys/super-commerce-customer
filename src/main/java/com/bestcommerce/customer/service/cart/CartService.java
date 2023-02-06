package com.bestcommerce.customer.service.cart;

import com.bestcommerce.customer.domain.Cart;
import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.domain.Product;
import com.bestcommerce.customer.repository.domain.CartRepository;
import com.bestcommerce.customer.repository.domain.CustomerRepository;
import com.bestcommerce.customer.repository.domain.ProductRepository;
import com.bestcommerce.customer.service.account.AccountService;
import com.bestcommerce.customer.service.product.ProductSelectService;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, CustomerRepository customerRepository, ProductRepository productRepository){
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public void putProductToCart(Long cu_id, Long product_id, Long size_id, int product_cnt){
        AccountService accountService = new AccountService(customerRepository);
        ProductSelectService productSelectService = new ProductSelectService(productRepository);
        Customer customer = accountService.getOneCustomerInfo(cu_id);
        Product product = productSelectService.getOnlyOneProduct(product_id);
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setProduct(product);
        cart.setSizeId(size_id);
        cart.setProductCount(product_cnt);
        cartRepository.save(cart);
    }
}
