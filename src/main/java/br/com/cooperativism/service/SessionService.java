package br.com.cooperativism.service;

import br.com.cooperativism.converter.SessionConverter;
import br.com.cooperativism.dto.SessionDto;
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

  public void create(SessionRequest sessionRequest) {
    final Topic topic = topicService.findById(sessionRequest.getTopicId());
    final Session session = sessionRepository.findFirstByTopicId(sessionRequest.getTopicId())
        .orElse(new Session());
    session.setTopic(topic);
    session.setVotingEnd(session.getVotingStart().plusMinutes(sessionRequest.getDuration()));

    sessionRepository.save(session);
  }

}
