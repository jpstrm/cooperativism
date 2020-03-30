package br.com.cooperativism.service;

import br.com.cooperativism.converter.MemberConverter;
import br.com.cooperativism.dto.MemberDto;
import br.com.cooperativism.exception.BusinessException;
import br.com.cooperativism.model.Member;
import br.com.cooperativism.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private MemberConverter memberConverter;

  public List<MemberDto> findAll() {
    final List<Member> members = memberRepository.findAll();
    return memberConverter.toDtoList(members);
  }

  public Member getOrSave(final String cpf) {
    if (cpf.length() > 11) {
      throw new BusinessException("Cpf inválido");
    }

    final Member member = memberRepository.findFirstByCpf(cpf)
        .orElse(new Member(cpf));

    return memberRepository.save(member);
  }

}
