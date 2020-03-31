package br.com.cooperativism.service;

import br.com.cooperativism.converter.VoteConverter;
import br.com.cooperativism.dto.VoteDto;
import br.com.cooperativism.dto.VoteResult;
import br.com.cooperativism.enums.VoteEnum;
import br.com.cooperativism.enums.VoteStatusEnum;
import br.com.cooperativism.exception.BusinessException;
import br.com.cooperativism.exception.NotFoundException;
import br.com.cooperativism.messaging.VoteRunner;
import br.com.cooperativism.model.Vote;
import br.com.cooperativism.repository.VoteRepository;
import br.com.cooperativism.request.VoteRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoteService {

  private Logger logger = LoggerFactory.getLogger(VoteService.class);

  @Autowired
  private VoteRepository voteRepository;

  @Autowired
  private VoteConverter voteConverter;

  @Autowired
  private VoteRunner voteRunner;

  @Autowired
  private MemberService memberService;

  public List<Vote> findAll() {
    return voteRepository.findAll();
  }

  public void sendVote(final VoteRequest voteRequest) {
    Vote vote = voteConverter.fromRequest(voteRequest);
    vote = findByTopicIdAndMemberCpf(vote);
    validateVote(voteRequest, vote);
    voteRunner.sendVote(vote);
    voteRepository.save(vote);
  }

  public void vote(Vote vote) {
    vote = findByTopicIdAndMemberCpf(vote);
    if (memberService.isCpfValid(vote.getMember().getCpf())) {
      vote.setStatus(VoteStatusEnum.VALID);
    } else {
      final String msg = "CPF inválido para Votar- ".concat(vote.getMember().getCpf());
      logger.error(msg);
      vote.setErrorMsg(msg);
      vote.setStatus(VoteStatusEnum.INVALID);
    }
    voteRepository.save(vote);
  }

  public VoteDto findByMemberCpfToDto(final String memberCpf) {
    Vote vote = voteRepository.findFirstByMemberCpf(memberCpf)
        .orElseThrow(() -> new NotFoundException("Voto não encontrado para o CPF: ".concat(memberCpf)));

    return voteConverter.toDto(vote);
  }

  private void validateVote(final VoteRequest voteRequest, final Vote vote) {
    if (VoteEnum.find(voteRequest.getVote()).isEmpty()) {
      throw new BusinessException("Voto inválido - exemplo: Sim/Não");
    }

    if (voteRepository.existsBySessionTopicIdAndMemberCpfAndStatus(voteRequest.getTopicId(),
        voteRequest.getMemberCpf(), VoteStatusEnum.VALID)) {
      throw new BusinessException("Associado já votou na Pauta");
    }
  }

  public List<Vote> findBySessionId(final Long sessionId) {

    return voteRepository.findBySessionId(sessionId);
  }

  public VoteResult sumVotesByTopicId(final Long topicId) {
    final List<Vote> votes = voteRepository.findBySessionTopicId(topicId).stream()
        .filter(v -> VoteStatusEnum.VALID.equals(v.getStatus()))
        .collect(Collectors.toList());

    final Integer votesNo = Math.toIntExact(votes.stream()
        .filter(v -> v.getVote().equals(VoteEnum.NO))
        .count());
    final Integer votesYes = Math.toIntExact(votes.stream()
        .filter(v -> v.getVote().equals(VoteEnum.YES))
        .count());

    return new VoteResult(votesYes, votesNo);
  }

  public VoteDto findById(final Long voteId) {
    final Vote vote = voteRepository.findById(voteId)
        .orElseThrow(() -> new NotFoundException("Voto não encontrado para o id - " + voteId));

    return voteConverter.toDto(vote);
  }

  public Vote findByTopicIdAndMemberCpf(final Vote vote) {

    return voteRepository.findBySessionTopicIdAndMemberCpf(vote.getSession().getTopic().getId(),
        vote.getMember().getCpf())
        .orElse(vote);
  }
}
