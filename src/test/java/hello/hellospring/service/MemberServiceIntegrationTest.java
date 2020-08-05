package hello.hellospring.service;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    private MemberService memberService;

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        memberService.findOne(saveId).ifPresent(m -> {
            org.assertj.core.api.Assertions.assertThat(m).isEqualTo(member);
        });
    }

    @Test
    void duplicateValidation(){
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        System.out.println(e.getMessage());
    }

}