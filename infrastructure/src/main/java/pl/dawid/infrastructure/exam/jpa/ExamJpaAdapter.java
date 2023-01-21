package pl.dawid.infrastructure.exam.jpa;


import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import pl.dawid.application.exam.interfaces.ExamRepository;
import pl.dawid.common.exceptions.CustomException;
import pl.dawid.domain.exam.Answer;
import pl.dawid.domain.exam.Exam;
import pl.dawid.domain.exam.ExamId;
import pl.dawid.domain.exam.StudentGrade;
import pl.dawid.domain.student.StudentId;

@RequiredArgsConstructor
class ExamJpaAdapter implements ExamRepository {

  private final ExamEntityRepository examEntityRepository;
  private final StudentRepository studentRepository;

  @Override
  public Optional<Exam> findById(ExamId examId) {
    return examEntityRepository.findById(examId.getId())
        .map(this::map);
  }

  private Exam map(ExamEntity examEntity) {
    return examEntity.toExam();
  }

  // Without the implementation of the object update
  @Override
  public void save(Exam exam) {
    buildNewExam(exam);
  }


  private void buildNewExam(Exam exam) {
    ExamEntity examEntity = ExamEntity.builder()
        .allowedStudentToExam(mapStudent(exam.getAllowedStudentToExam()))
        .studentGrades(mapStudentGrade(exam.getStudentGrades()))
        .taskEntities(mapTasks(exam))
        .build();
    examEntityRepository.save(examEntity);
  }

  private <T extends Exam> Set<TaskEntity> mapTasks(T answer) {
    return answer.getContent()
        .getTasks()
        .stream()
        .map(task -> TaskEntity.builder()
            .correctAnswer(mapAnswer(task.getCorrectAnswer()))
            .possibleAnswers(mapAnswer(task.getPossibleAnswers()))
            .build())
        .collect(Collectors.toSet());
  }

  private Set<AnswerEntity> mapAnswer(Set<Answer> possibleAnswers) {
    return possibleAnswers.stream()
        .map(this::mapAnswer)
        .collect(Collectors.toSet());
  }

  private AnswerEntity mapAnswer(Answer correctAnswer) {
    return AnswerEntity.builder()
        .answer(correctAnswer.getAnswer())
        .answerNumber(correctAnswer.getAnswerNumber().getValue())
        .build();
  }

  private Set<StudentGradeEntity> mapStudentGrade(Set<StudentGrade> studentGrades) {
    return studentGrades
        .stream()
        .map(studentGrade -> {
          StudentEntity studentEntity = studentRepository.findById(studentGrade.getStudentId().getId())
              .orElseThrow(() -> new CustomException("Cannot find student with id: " + studentGrade.getStudentId().getId()));
          return StudentGradeEntity.builder()
              .student(studentEntity)
              .grade(studentGrade.getGrade().toString())
              .build();
        }).collect(Collectors.toSet());
  }

  private Set<StudentEntity> mapStudent(Set<StudentId> allowedStudentToExam) {
    Set<UUID> ids = allowedStudentToExam
        .stream()
        .map(StudentId::getId)
        .collect(Collectors.toSet());
    return studentRepository.findAllByIdIn(ids);
  }
}
