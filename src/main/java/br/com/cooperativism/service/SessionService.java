package br.com.cooperativism.service;

import br.com.cooperativism.converter.SessionConverter;
import br.com.cooperativism.dto.SessionDto;
import br.com.cooperativism.exception.BusinessException;
import br.com.cooperativism.exception.NotFoundException;
import br.com.cooperativism.model.Session;
import br.com.cooperativism.model.Topic;
import br.com.cooperativism.repository.SessionRepository;
import br.com.cooperativism.request.SessionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

  @Autowired
  private SessionRepository sessionRepository;

  @Autowired
  private TopicService topicService;

  @Autowired
  private SessionConverter sessionConverter;

  public List<SessionDto> findAll() {
    final List<Session> sessions = sessionRepository.findAll();

    return sessionConverter.toDtoList(sessions);
  }

  public void create(final SessionRequest sessionRequest) {
    final Session session = sessionRepository.findFirstByTopicId(sessionRequest.getTopicId())
        .map(this::filterSession)
        .orElse(new Session());
    final Topic topic = topicService.findById(sessionRequest.getTopicId());
    session.setTopic(topic);
    session.setVotingEnd(session.getVotingStart().plusMinutes(sessionRequest.getDuration()));

    sessionRepository.save(session);
  }

  public Session findValidByTopicId(final Long topicId) {

    return sessionRepository.findFirstByTopicId(topicId)
        .map(this::filterSession)
        .orElseThrow(() -> throwNotFound(topicId));
  }

  public Session filterSession(final Session session) {
    if (session.isExpired()) {
      final String msg = String.format("%s '%s' às %s", "Sessão expirada para a Pauta",
          session.getTopic().getId(), session.getVotingEnd().toString());
      throw new BusinessException(msg);
    }

    return session;
  }

  public Session findByTopicId(final Long topicId) {

    return sessionRepository.findFirstByTopicId(topicId)
        .orElseThrow(() -> throwNotFound(topicId));
  }

  public SessionDto findByTopicIdToDto(final Long topicId) {

    return sessionConverter.toDto(findByTopicId(topicId));
  }

  private NotFoundException throwNotFound(final Long topicId) {
    throw new NotFoundException("Sessão não encontrada para essa Pauta: " + topicId);
  }

}
