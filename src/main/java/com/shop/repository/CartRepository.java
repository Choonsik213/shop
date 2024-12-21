package com.shop.repository;

import com.shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * 1. 이 인터페이스 : CartRepository는 DB와 소통하기 위해 만든 다리야
     *
     * 우리가 직접 SQL 쿼리를 쓰지 않고,
     * JPA가 알아서 Cart 엔티티를 데이터베이스에 저장하거나 가져오는 일을 대신 해줘.
     * JpaRepository를 상속하면 기본적인 **CRUD 작업(저장, 조회, 수정, 삭제)**을 간단히 처리할 수 있어.


     * 2. CartRepository:
     *
     * Cart라는 엔티티를 다루는 "레포지토리(Repository)"야.
     * 즉, "Cart와 관련된 DB 작업을 처리하는 도구"라고 보면 돼.
     * extends JpaRepository<Cart, Long>:


     * 3. JpaRepository를 상속받아서 Cart 엔티티를 위한 DB 작업을 쉽게 할 수 있도록 기능을 물려받아.
     *
     * Cart: 어떤 테이블(엔티티)을 관리할지 알려줘.
     * Long: Cart 엔티티의 기본 키(ID)의 타입이 Long이라는 걸 알려줘.
     */

    // 현재 로그인한 회원의 Cart엔티티를 조회하는 메서드
    Optional<Cart> findByMemberId(Long memberId);

    // 장바구니에 들어갈 상품을 저장하거나 조회하기 위해 작성한 메서드
    Optional<Cart> findByMemberIdAndItemId(Long memberId, Long itemId);

}
