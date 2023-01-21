package pl.dawid.domain.exam.dto;


import java.util.Set;
import lombok.Builder;
import lombok.Value;
import pl.dawid.common.valueobjects.Question;
import pl.dawid.common.valueobjects.TaskNumber;

@Value
@Builder
public class TaskDto {
   TaskNumber taskNumber;
   Question question;
   Set<AnswerDto> possibleAnswers;
   AnswerDto correctAnswer;
}
