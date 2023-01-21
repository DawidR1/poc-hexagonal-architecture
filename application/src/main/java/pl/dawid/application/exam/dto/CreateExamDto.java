package pl.dawid.application.exam.dto;


import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateExamDto {

  private Set<UUID> studentsAllowedToExam;
  private Set<TaskExternalDto> tasks;
}
