package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name="item") //어떤 테이블과 매핑될지 지정
@Getter
@Setter
@ToString
public class Item {

    @Id // 기본키 되는 멤버변수
    @Column(name="item_id") // 테이블에 매핑될 컬럼 이름설정
    @GeneratedValue(strategy = GenerationType.AUTO) // 기본키 생성 전략을 AUTO로 지정
    private Long id;    // 상품코드

    @Column(nullable = false, length = 50)
    private String itemNm;  // 상품명

    @Column(name = "price", nullable = false)
    private int price;  // 가격

    @Column(nullable = false)
    private int stockNumber;    // 재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail;  // 상품상세설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;  // 상품판매상태

    private LocalDateTime regTime;  // 등록시간

    private LocalDateTime updateTime;   // 수정시간

}