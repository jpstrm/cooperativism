package br.com.cooperativism.service;

import br.com.cooperativism.converter.VoteConverter;
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

  @Autowired

  public List<Vote> findAll() {
    return voteRepository.findAll();
  }

  public void vote(final VoteRequest voteRequest) {
    validateRequest(voteRequest);
    final Vote vote = voteConverter.fromRequest(voteRequest);

    voteRepository.save(vote);
  }

  private void validateRequest(final VoteRequest voteRequest) {
    final List<Vote> votes = voteRepository.findByTopicName(voteRequest.getTopicName());
    if (votes.isEmpty()) {
      throw new NotFoundException("Pauta não encontrada para votação");
    }
    if (voteRepository.existsByMemberCpf(voteRequest.getMemberCpf())) {
      throw new BusinessException("Associado já votou na Pauta");
    }
  }

}
