package pl.dawid.application.exam;


import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.dawid.application.exam.dto.SubmitStudentDto;
import pl.dawid.application.exam.interfaces.SubmitExamUseCase;
import pl.dawid.common.exceptions.CustomException;
import pl.dawid.common.valueobjects.AnswerNumber;
import pl.dawid.common.valueobjects.TaskNumber;
import pl.dawid.domain.exam.Exam;
import pl.dawid.domain.exam.ExamId;
import pl.dawid.application.exam.interfaces.ExamRepository;
import pl.dawid.domain.exam.dto.CompletedExamDto;
import pl.dawid.domain.student.Student;
import pl.dawid.domain.student.StudentId;
import pl.dawid.domain.student.StudentRepository;

@RequiredArgsConstructor
@Component
class SubmitExamService implements SubmitExamUseCase {

  private final StudentRepository studentRepository;
  private final ExamRepository examRepository;

  @Override
  @Transactional
  public void submitExam(SubmitStudentDto dto) {
    Student student = studentRepository.findById(StudentId.of(dto.getStudentId()))
        .orElseThrow(() -> new CustomException("Student not found, id: " + dto.getStudentId()));

    if (!student.isActive()) {
      throw new CustomException("Student is not active");
    }

    Exam exam = examRepository.findById(ExamId.of(dto.getExamId()))
        .orElseThrow(() -> new CustomException("Exam not found, id: " + dto.getExamId()));

    CompletedExamDto completedExamDto = buildCompletedExam(dto);
    exam.submit(completedExamDto);
    examRepository.save(exam);
  }

  private CompletedExamDto buildCompletedExam(SubmitStudentDto dto) {
    Map<TaskNumber, AnswerNumber> answers = dto.getAnswerByTaskNumber()
        .entrySet()
        .stream()
        .collect(Collectors.toMap(
            entry -> TaskNumber.of(entry.getKey()),
            entry -> AnswerNumber.of(entry.getValue())));

    return CompletedExamDto.builder()
        .answers(answers)
        .studentId(StudentId.of(dto.getStudentId()))
        .build();
  }
}