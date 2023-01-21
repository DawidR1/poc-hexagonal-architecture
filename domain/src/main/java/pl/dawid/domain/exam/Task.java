package pl.dawid.domain.exam;


import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import pl.dawid.common.valueobjects.AnswerNumber;
import pl.dawid.common.valueobjects.Question;
import pl.dawid.common.valueobjects.TaskNumber;

@Getter
@Builder
public class Task {

 private TaskId id;
 private TaskNumber taskNumber;
 private Question question;
 private Set<Answer> possibleAnswers;
 private Answer correctAnswer;


 boolean checkIfAnswerCorrect(AnswerNumber answer) {
  return correctAnswer.getAnswerNumber().equals(answer);
 }
}
