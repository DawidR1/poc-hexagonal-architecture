package pl.dawid.application.exam.dto;


import java.util.Map;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitStudentDto {

  @NotNull
  private UUID studentId;
  @NotNull
  private UUID examId;
  private Map<Long, Long> answerByTaskNumber;
}
