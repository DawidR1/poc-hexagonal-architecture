package pl.dawid.domain.exam;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import pl.dawid.common.valueobjects.AnswerNumber;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class Answer {

  private AnswerNumber answerNumber;
  private AnswerId id;
  private String answer;


}