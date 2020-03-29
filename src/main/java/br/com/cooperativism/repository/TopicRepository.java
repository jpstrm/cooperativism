package br.com.cooperativism.repository;

import br.com.cooperativism.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

  Optional<Topic> findFirstByNameIgnoreCase(final String name);

}
