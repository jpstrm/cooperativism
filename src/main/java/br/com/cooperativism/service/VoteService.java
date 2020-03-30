package br.com.cooperativism.service;

import br.com.cooperativism.converter.VoteConverter;
import br.com.cooperativism.dto.VoteDto;
import br.com.cooperativism.enums.VoteEnum;
import br.com.cooperativism.exception.BusinessException;
import br.com.cooperativism.exception.NotFoundException;
import br.com.cooperativism.model.Vote;
import br.com.cooperativism.repository.VoteRepository;
import br.com.cooperativism.request.VoteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

  @Autowired
  private VoteRepository voteRepository;

  @Autowired
  private VoteConverter voteConverter;

  public List<Vote> findAll() {
    return voteRepository.findAll();
  }

  public void vote(final VoteRequest voteRequest) {
    validateRequest(voteRequest);
    final Vote vote = voteConverter.fromRequest(voteRequest);
    voteRepository.save(vote);
  }

  public VoteDto findByMemberCpfToDto(final String memberCpf) {
    Vote vote = voteRepository.findFirstByMemberCpf(memberCpf)
        .orElseThrow(() -> new NotFoundException("Voto não encontrado para o CPF: ".concat(memberCpf)));

    return voteConverter.toDto(vote);
  }

  private void validateRequest(final VoteRequest voteRequest) {
    if (VoteEnum.find(voteRequest.getVote()).isEmpty()) {
      throw new BusinessException("Voto inválido - exemplo: Sim/Não");
    }

    if (voteRepository.existsByMemberCpf(voteRequest.getMemberCpf())) {
      throw new BusinessException("Associado já votou na Pauta");
    }
  }

  public List<Vote> findBySessionId(final Long sessionId) {

    return voteRepository.findBySessionId(sessionId);
  }

}
