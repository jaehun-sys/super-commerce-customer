package com.bestcommerce.util.converter;

import com.bestcommerce.address.entity.Address;
import com.bestcommerce.address.dto.AddressDto;
import com.bestcommerce.cart.entity.Cart;
import com.bestcommerce.cart.dto.CartDto;
import com.bestcommerce.product.entity.Product;
import com.bestcommerce.product.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoConverter {

    public ProductDto toProductDto(Product product){
        return new ProductDto(product.getProductId(), product.getProductName(), product.getProductCost(), product.getInfo(), product.getThumbPath(), product.getDeliveryCost());
    }

    public List<ProductDto> toProductDtoList(List<Product> productList){
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Product product : productList){
            productDtoList.add(toProductDto(product));
        }
        return productDtoList;
    }

    public AddressDto toAddressDto(Address address){
        return new AddressDto(address.getAddrId(), address.getCustomer().getCuId(), address.getAddr(), address.getRepYn(), address.getZipCode());
    }

    public List<AddressDto> toAddressDtoList(List<Address> addressList){
        List<AddressDto> addressDtoList = new ArrayList<>();
        for(Address address : addressList){
            addressDtoList.add(toAddressDto(address));
        }
        return addressDtoList;
    }

    public CartDto toCartDto(Cart cart){
        return new CartDto(cart.getProductCount(), cart.getSize().getSizeId(), cart.getCartKey().getCustomerId(), cart.getProduct().getProductId());
    }
}
