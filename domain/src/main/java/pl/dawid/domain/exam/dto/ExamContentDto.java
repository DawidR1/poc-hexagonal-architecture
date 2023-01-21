package pl.dawid.domain.exam.dto;


import java.util.Set;
import lombok.Value;

@Value
public class ExamContentDto {

  Set<TaskDto> tasks;
}