package br.com.cooperativism.model;

import br.com.cooperativism.helper.ApiHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Session extends AbstractModel implements Serializable {

  private static final long serialVersionUID = 7211846874754010863L;

  @OneToOne(optional = false)
  @JoinColumn(name = "topic_id", referencedColumnName = "id", nullable = false)
  private Topic topic;

  @Column(name = "voting_start", nullable = false)
  private LocalDateTime votingStart = ApiHelper.getNow();

  @Column(name = "voting_end", nullable = false)
  private LocalDateTime votingEnd;

  public Session() {}

  public Session(final LocalDateTime votingStart, final LocalDateTime votingEnd, final Topic topic) {
    this.votingStart = votingStart;
    this.votingEnd = votingEnd;
    this.topic = topic;
  }

  public LocalDateTime getVotingStart() {
    return votingStart;
  }

  public void setVotingStart(LocalDateTime votingStart) {
    this.votingStart = votingStart;
  }

  public LocalDateTime getVotingEnd() {
    return votingEnd;
  }

  public void setVotingEnd(LocalDateTime votingEnd) {
    this.votingEnd = votingEnd;
  }

  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }

  public boolean isExpired() {
    return ApiHelper.isDateExpired(votingEnd);
  }

}
