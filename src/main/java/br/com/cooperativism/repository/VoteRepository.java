package br.com.cooperativism.repository;

import br.com.cooperativism.enums.VoteStatusEnum;
import br.com.cooperativism.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

  Boolean existsBySessionTopicIdAndMemberCpfAndStatus(final Long topicId, final String memberCpf,
      final VoteStatusEnum status);

  Optional<Vote> findFirstByMemberCpf(final String memberCpf);

  List<Vote> findBySessionTopicId(final Long topicId);

  Optional<Vote> findFirstBySessionId(final Long sessionId);

  List<Vote> findBySessionId(final Long sessionId);

  Optional<Vote> findBySessionTopicIdAndMemberCpf(final Long topicId, String memberCpf);

}
