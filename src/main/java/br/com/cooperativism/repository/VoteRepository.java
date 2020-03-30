package br.com.cooperativism.repository;

import br.com.cooperativism.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

  Boolean existsByMemberCpf(final String memberCpf);

  Optional<Vote> findFirstByMemberCpf(final String memberCpf);

  List<Vote> findBySessionTopicId(final Long topicId);

}
