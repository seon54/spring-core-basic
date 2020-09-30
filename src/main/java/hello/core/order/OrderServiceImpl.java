package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    /**
     * RateDiscountPolicy를 적용시키기 위해 OrderServiceImpl를 수정해야 하므로 DIP 위반
     * 기능을 확장해서 코드를 변경하게 되면 클라이언트 코드에 영향을 주게 되므로 OCP 위반
     *
     * DIP를 위반하지 않도록 인터페이스에만 의존하도록 의존관계 변경 필요
     * OrderServiceImpl에 DiscountPolicy 구현 객체를 생성하고 주입해주어야 함
     * 주입될 구현 객체는 외부(AppConfig)에서 결정
     * OrderServiceImpl은 의존 관계에 대한 고민을 할 필요없이 실행에만 집중할 수 있음
     */

    private final MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
