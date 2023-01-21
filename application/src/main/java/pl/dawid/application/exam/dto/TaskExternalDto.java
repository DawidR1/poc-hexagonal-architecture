package pl.dawid.application.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskExternalDto {

  private Long taskNumber;
  private String question;
  private String possibleAnswers;
  private String correctAnswer;
}
