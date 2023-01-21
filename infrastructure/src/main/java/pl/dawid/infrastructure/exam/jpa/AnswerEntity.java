package pl.dawid.infrastructure.exam.jpa;


import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "answers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AnswerEntity {

  @Id
  private UUID id;
  @Column(nullable = false)
  private Long answerNumber;
  @Column(nullable = false)
  private String answer;
}
