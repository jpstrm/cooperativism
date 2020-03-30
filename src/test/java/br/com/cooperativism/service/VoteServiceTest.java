package br.com.cooperativism.service;

import br.com.cooperativism.base.AbstractUnitTest;
import br.com.cooperativism.converter.VoteConverter;
import br.com.cooperativism.enums.VoteEnum;
import br.com.cooperativism.exception.BusinessException;
import br.com.cooperativism.exception.NotFoundException;
import br.com.cooperativism.mock.vote.VoteMock;
import br.com.cooperativism.mock.vote.VoteRequestMock;
import br.com.cooperativism.model.Vote;
import br.com.cooperativism.repository.VoteRepository;
import br.com.cooperativism.request.VoteRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VoteServiceTest extends AbstractUnitTest {

  @InjectMocks
  private VoteService voteService;

  @Mock
  private VoteRepository voteRepository;

  @Mock
  private VoteConverter voteConverter;

  private VoteRequest voteRequest;
  private String memberCpf = "12345678912";

  @BeforeEach
  public void beforeEach() {
    voteRequest = VoteRequestMock.getRandom("sim");

  }

  @Test
  public void shouldVote() {
    Vote vote = VoteMock.getRandom("Test", VoteEnum.YES);
    VoteRequest voteRequest = VoteRequestMock.getRandom("SIM");

    when(voteConverter.fromRequest(isA(VoteRequest.class)))
        .thenReturn(vote);
    when(voteRepository.existsByMemberCpf(anyString()))
        .thenReturn(false);

    voteService.vote(voteRequest);

    ArgumentCaptor<Vote> captor = ArgumentCaptor.forClass(Vote.class);
    verify(voteRepository, times(1)).save(captor.capture());

    Vote result = captor.getValue();

    assertNotNull(result);
    assertEquals(VoteEnum.YES, result.getVote());
    assertEquals(vote.getMember().getCpf(), result.getMember().getCpf());
  }

  @Test
  public void throwErrorIfMemberNotFoundWhenFindByMemberCpfToDto() {
    when(voteRepository.findFirstByMemberCpf(anyString()))
        .thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> {
      voteService.findByMemberCpfToDto("12345678912");
    });
  }

  @Test
  public void throwErrorIfMemberAlreadyVoted() {
    when(voteRepository.existsByMemberCpf(memberCpf))
        .thenReturn(true);
    assertThrows(BusinessException.class, () -> voteService.vote(voteRequest));
  }

  @Test
  public void throwErrorIfVoteIsInvalid() {
    voteRequest.setVote("naoo");

    assertThrows(BusinessException.class, () -> voteService.vote(voteRequest));
  }

}
