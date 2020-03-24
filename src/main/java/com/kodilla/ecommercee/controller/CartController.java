package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.exception.CartExceptionBadRequest;
import com.kodilla.ecommercee.exception.CartExceptionNotFound;
import com.kodilla.ecommercee.exception.NullArgumentException;
import com.kodilla.ecommercee.exception.UserException;

import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.ShopServiceFacade;
import io.github.jhipster.web.util.HeaderUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("api/v1/ecommercee/carts")
public class CartController {

    private final ShopServiceFacade shopServiceFacade;
    private final CartService cartService;

    private static final String ENTITY_NAME = "Cart";

    private final Logger log = LoggerFactory.getLogger(CartController.class);

    @Autowired
    public CartController(ShopServiceFacade shopServiceFacade, CartService cartService) {
        this.shopServiceFacade = shopServiceFacade;
        this.cartService = cartService;
    }

   @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newCart(@RequestBody CartDto cartDto) throws UserException, URISyntaxException, NullArgumentException, CartExceptionBadRequest {
        if(cartDto == null) {
            throw new IllegalArgumentException("Some passed arguments are equal null");
        }
        log.debug("REST request to add new Cart : {}", cartDto);
        CartDto cartSaved = shopServiceFacade.createNewCart(cartDto);
        return ResponseEntity.created(new URI("/api/v1/ecommercee/carts/" + cartSaved.getId()))
               .body(cartSaved);
    }

    @DeleteMapping(path ="/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable("id") Long cartId) throws CartExceptionNotFound{
        if(cartId == null) {
            throw new IllegalArgumentException("Passed arguments are equal null");
        }
        cartService.deleteCartById(cartId);
        log.debug("REST request to delete Cart : {}", cartId);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert("Ecommerce shop", false, ENTITY_NAME, cartId.toString())).build();
    }

    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductsFromCart(@PathVariable("id") Long cartId) throws CartExceptionNotFound {
        if(cartId == null) {
            throw new IllegalArgumentException("Passed arguments are equal null");
        }
        log.debug("REST request to get products from cart : {}", cartId);
        CartDto cartFetched = cartService.getCartById(cartId);
        return new ResponseEntity<>(cartFetched, HttpStatus.OK);
    }
//
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUpdateDeleteOrderItemsToCart(@PathVariable("id") Long cartId, @RequestBody CartDto cartDto) throws CartExceptionNotFound, UserException, NullArgumentException, CartExceptionBadRequest {
        if(cartDto == null ^ cartId == null) {
            throw new IllegalArgumentException("Passed arguments are equal null");
        }
        if(cartDto.getId() != cartId) {
            throw new CartExceptionBadRequest(CartExceptionBadRequest.ERR_CART_PATH);
        }
        if(!cartService.exists(cartId)) {
            throw new CartExceptionNotFound(CartExceptionNotFound.ERR_CART_NOT_FOUND);
        }
        cartDto.setId(cartId);
        CartDto cartUpdated = shopServiceFacade.addOrderItemsToExistingCart(cartDto);
        return new ResponseEntity<>(cartUpdated, HttpStatus.CREATED);
    }
}
