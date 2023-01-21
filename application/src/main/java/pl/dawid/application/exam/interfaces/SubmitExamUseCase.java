package pl.dawid.application.exam.interfaces;


import pl.dawid.application.exam.dto.SubmitStudentDto;

public interface SubmitExamUseCase {

  void submitExam(SubmitStudentDto dto);
}
