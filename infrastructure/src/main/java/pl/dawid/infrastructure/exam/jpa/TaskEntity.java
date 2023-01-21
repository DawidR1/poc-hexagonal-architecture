package pl.dawid.infrastructure.exam.jpa;


import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
class TaskEntity {

  @Id
  private UUID id;
  @Column(nullable = false)
  private Long taskNumber;
  @Column(nullable = false)
  private String question;

  @OneToMany
  private Set<AnswerEntity> possibleAnswers;

  @ManyToOne
  @JoinColumn(name = "correct_answer_id")
  private AnswerEntity correctAnswer;
}