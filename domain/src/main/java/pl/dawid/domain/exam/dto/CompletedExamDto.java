package pl.dawid.domain.exam.dto;


import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import pl.dawid.common.valueobjects.AnswerNumber;
import pl.dawid.common.valueobjects.TaskNumber;
import pl.dawid.domain.student.StudentId;


@Builder
@Getter
public class CompletedExamDto {

  private Map<TaskNumber, AnswerNumber> answers;
  private StudentId studentId;
}
