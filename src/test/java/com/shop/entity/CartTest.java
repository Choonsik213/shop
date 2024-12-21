package com.shop.entity;

import com.shop.dto.MemberFormDto;
import com.shop.repository.CartRepository;
import com.shop.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @SpringBootTest : 프로젝트의 모든 설정(@Component, @Repository, @Service 같은 애너테이션)을 불러와서
 * 진짜 실행 환경처럼 테스트할 수 있음
 * @Transactional : 테스트가 끝나면 롤백시켜서 DB에 데이터가 남지 않게 함
 * @TestPropertySource : 테스트용 설정 파일(application-test.properties)을 사용하겠다는 의미
 * @Autowired : 내가 필요한 객체가 무엇인지 적고 @Autowired를 붙이면 스프링이 알아서 객체(빈)를 찾아서 넣어줌(주입)
 * 스프링이 자동으로 CartRepository를 찾아서 cartRepository 변수에 넣어줌
 * @PersistenceContext : 엔티티 매니저(JPA에서 DB와 직접 통신하는 객체)를 주입받음
 */

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class CartTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

    public Member createMember() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@enaver.com");
        memberFormDto.setName("테스터");
        memberFormDto.setPassword("1234");
        memberFormDto.setAddress("서울시 강남구"); // 주소가 필요한 로직이 있다면 추가
        return Member.createMember(memberFormDto, passwordEncoder);
        // 매개변수를 왜 저렇게 쓴걸까? (데이터를 담고있어서, 비밀번호를 암호화하기위함)
    }

    @Test
    @DisplayName("장바구니와 회원 매핑조회 테스트")
    public void findCartAndMemberTest() {

        Member member = createMember(); // 테스트용 Member객체 생성
        memberRepository.save(member);  // 생성한 객체를 DB에 저장

        Cart cart = new Cart();     // 테스트용 Cart객체 생성
        cart.setMember(member);     // 위에서 만든 member객체를 장바구니에 연결
        cartRepository.save(cart);  // Cart객체와 Member객체와 연관된 상태로 저장

        em.flush(); // Cart와 Member저장하고 DB에 반영하도록 요청
        em.clear(); // JPA가 관리하는 모든 엔티티 캐시를 지우기

        Cart savedCart = cartRepository.findById(cart.getId())  // DB에서 Cart객체 다시조회
                .orElseThrow(EntityNotFoundException::new); // Cart없으면 예외던져
        assertEquals(savedCart.getMember().getId(), member.getId());
        // (예상 값, 실제 값) 비교해서 같으면 성공

    }

}