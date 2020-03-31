package br.com.cooperativism.service;

import br.com.cooperativism.client.UserClient;
import br.com.cooperativism.converter.MemberConverter;
import br.com.cooperativism.dto.MemberDto;
import br.com.cooperativism.model.Member;
import br.com.cooperativism.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private MemberConverter memberConverter;

  @Autowired
  private UserClient userClient;

  public List<MemberDto> findAll() {
    final List<Member> members = memberRepository.findAll();

    return memberConverter.toDtoList(members);
  }

  public Member getOrSave(final String cpf) {
    userClient.isCpfValid(cpf).block();

    final Member member = memberRepository.findFirstByCpf(cpf)
        .orElse(new Member(cpf));

    return memberRepository.save(member);
  }

  public Boolean isCpfValid(String cpf) {

    return userClient.isCpfValid(cpf).block();
  }

  public Optional<Member> findByCpf(final String cpf) {

    return memberRepository.findFirstByCpf(cpf);
  }

}
