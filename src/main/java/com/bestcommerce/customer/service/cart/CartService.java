package com.bestcommerce.customer.service.cart;

import com.bestcommerce.customer.domain.Cart;
import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.domain.Product;
import com.bestcommerce.customer.dto.CartDto;
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

    public void putProductToCart(CartDto cartDto){
        AccountService accountService = new AccountService(customerRepository);
        ProductSelectService productSelectService = new ProductSelectService(productRepository);
        Customer customer = accountService.getOneCustomerInfo(cartDto.getCustomerId());
        Product product = productSelectService.getOnlyOneProduct(cartDto.getProductId());
        Cart cart = new Cart(cartDto.getProductCount(), cartDto.getSizeId(), customer, product);
        cartRepository.save(cart);
    }
}
