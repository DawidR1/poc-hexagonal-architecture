package pl.dawid.application.exam;


import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.dawid.application.exam.dto.CreateExamDto;
import pl.dawid.application.exam.dto.TaskExternalDto;
import pl.dawid.application.exam.interfaces.CreateExamUseCase;
import pl.dawid.common.exceptions.CustomException;
import pl.dawid.domain.exam.Exam;
import pl.dawid.domain.exam.dto.ExamContentDto;
import pl.dawid.application.exam.interfaces.ExamRepository;
import pl.dawid.domain.exam.dto.TaskDto;
import pl.dawid.domain.student.Student;
import pl.dawid.domain.student.StudentId;
import pl.dawid.domain.student.StudentRepository;

@RequiredArgsConstructor
class CreateExamService implements CreateExamUseCase {

  private final StudentRepository studentRepository;
  private final ExamRepository examRepository;

  @Override
  @Transactional
  public void createExam(CreateExamDto createExamDto) {
    Set<StudentId> studentIds = createExamDto.getStudentsAllowedToExam()
        .stream()
        .map(StudentId::of)
        .collect(Collectors.toSet());
    validateStudents(studentIds);

    ExamContentDto examContentDto = new ExamContentDto(map(createExamDto.getTasks()));

    Exam exam = Exam.createExam(studentIds, examContentDto);
    examRepository.save(exam);
  }

  private void validateStudents(Set<StudentId> studentIds) {
    Set<Student> students = studentRepository.findByIdIn(studentIds);
    if (students.size() != studentIds.size()) {
      throw new CustomException("incorrect data has been entered");
    }
  }

  private Set<TaskDto> map(Set<TaskExternalDto> tasks) {
    //map TaskExternalDto -> TaskDto
    return null;
  }

}
