package com.hkb.shoppingcart.user;

import com.hkb.shoppingcart.model.CartUser;
import com.hkb.shoppingcart.repo.CartUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private CartUserRepository cartUserRepository;

    public UserDetailsServiceImpl(CartUserRepository cartUserRepository) {
        this.cartUserRepository = cartUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CartUser cartUser = cartUserRepository.findByUserName(username);
        if (cartUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(cartUser.getUserName(), cartUser.getPassword(), emptyList());
    }
}
