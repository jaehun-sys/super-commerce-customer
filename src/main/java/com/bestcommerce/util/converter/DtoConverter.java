package com.bestcommerce.util.converter;

import com.bestcommerce.address.entity.Address;
import com.bestcommerce.address.dto.AddressDto;
import com.bestcommerce.cart.entity.Cart;
import com.bestcommerce.cart.dto.CartDto;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.entity.Customer;
import com.bestcommerce.product.entity.Product;
import com.bestcommerce.product.dto.ProductDto;
import com.bestcommerce.size.dto.SizeDto;
import com.bestcommerce.size.entity.Size;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoConverter {

    public ProductDto toProductDto(Product product){
        return new ProductDto(product.getProductId(), product.getBrand().getId(), "", product.getProductName(), product.getProductCost(), product.getInfo(), product.getThumbPath(), product.getDeliveryCost());
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

    public SizeDto toSizeDto(Size size){
        return new SizeDto(size.getSizeId(), size.getProduct().getProductId(), size.getMeasureId(), size.getMeasureName(), size.getContentId(), size.getContentName(), size.getSizeValue(), size.getSizeRemainQuantity());
    }

    public List<SizeDto> toSizeDtoList(List<Size> sizeList){
        List<SizeDto> sizeDtoList = new ArrayList<>();
        for(Size size : sizeList){
            sizeDtoList.add(toSizeDto(size));
        }
        return sizeDtoList;
    }

    public CustomerDto toCustomerDto(Customer customer){
        return new CustomerDto(customer.getCuId(), customer.getCuName(), customer.getMember().getMemberEmail(), "", customer.getCuTelNumber(), customer.getBirthdate(), customer.getRegisterDate(), customer.getModifyDate());
    }
}
