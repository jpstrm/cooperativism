package br.com.cooperativism.repository;

import br.com.cooperativism.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

  Optional<Session> findFirstByTopicId(final Long topicId);

  Optional<Session> findFirstByTopicId(final String topicName);

}
