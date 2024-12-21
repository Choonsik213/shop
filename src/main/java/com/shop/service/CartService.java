package com.shop.service;

import com.shop.dto.CartDto;
import com.shop.entity.Cart;
import com.shop.entity.Item;
import com.shop.entity.Member;
import com.shop.repository.CartRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartService cartService;
    private final CartRepository cartRepository;

    public Long addCart(CartDto cartDto, String email) {
        // 1. 장바구니에 담을 상품 엔티티 조회
        Item item = itemRepository.findById(cartDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        // 2. 현재 로그인 한 회원 엔티티 조회
        Member member = memberRepository.findByEmail(email);

        // 3. 회원의 장바구니 엔티티 조회
        public Member getLoggedInMember(String email) {
            return memberRepository.findByEmail(email);
        }

    }
}
