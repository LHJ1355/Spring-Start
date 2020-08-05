package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.storeClear();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        repository.findById(member.getId())
                .ifPresent(m -> {
                    Assertions.assertThat(member).isEqualTo(m);
                });
        Assertions.assertThat(repository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring");
        repository.save(member1);
        
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        repository.findByName("spring1")
            .ifPresent(m->{
                Assertions.assertThat(member1).isEqualTo(m);
            });
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> results = repository.findAll();
        Assertions.assertThat(results.size()).isEqualTo(2);
    }

}
