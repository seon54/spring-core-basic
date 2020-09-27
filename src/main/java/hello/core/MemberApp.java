package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        /*
        *  문제점
        *  1. 다른 저장소로 변경할 때 OCP(개방-폐쇄 원칙)을 지킬 수 없다.
        *  2. DIP(의존 역전 원칙) - 의존 관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있다.
        * */
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());


    }
}
