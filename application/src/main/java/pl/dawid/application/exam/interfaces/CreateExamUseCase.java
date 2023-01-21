package pl.dawid.application.exam.interfaces;

import pl.dawid.application.exam.dto.CreateExamDto;

public interface CreateExamUseCase {

  void createExam(CreateExamDto createExamDto);
}