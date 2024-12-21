package com.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name="cart")
@Getter
@Setter
@ToString
public class Cart extends BaseEntity{ // 4조 ERD연습
    /**
     * 장바구니 안에 장바구니상품도 들어있음

     * cart_id(pk)
     * member_id(fk)
     * item_id(fk, 우리는 product)
     * count(우리는 quantity)
     * is_selected
     * created_at
     *
     * @Id : 기본키
     * @Column : 컬럼명
     * @GeneratedValue : 고유번호 자동 증가
     * @OneToOne(strategy = FetchType.LAZY) : 1:1 관계, 지연로딩
     */

    @Id
    @Column(name="cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    private int quantity;

    private boolean is_selected;

    private LocalDateTime created_at;


    // Cart클래스에 Member엔티티와 를 파라미터로 받아서 장바구니 엔티티 생성하는 로직
    public static Cart createCart(Member member, Item item) {
        Cart cart = new Cart();
        cart.setMember(member);
        cart.setItem(item);
        return cart;
    }

    // 장바구니에 담을 수량을 증가시켜주는 메서드 로직
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }




}
