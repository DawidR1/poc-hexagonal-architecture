package pl.dawid.infrastructure.exam.jpa;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.dawid.common.valueobjects.AnswerNumber;
import pl.dawid.common.valueobjects.Question;
import pl.dawid.common.valueobjects.TaskNumber;
import pl.dawid.domain.exam.Answer;
import pl.dawid.domain.exam.AnswerId;
import pl.dawid.domain.exam.Content;
import pl.dawid.domain.exam.Exam;
import pl.dawid.domain.exam.Grade;
import pl.dawid.domain.exam.StudentGrade;
import pl.dawid.domain.exam.Task;
import pl.dawid.domain.exam.TaskId;
import pl.dawid.domain.student.StudentId;

@Entity
@Table(name = "exams")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  @OneToMany
  private Set<StudentEntity> allowedStudentToExam = new HashSet<>();
  @OneToMany
  private Set<StudentGradeEntity> studentGrades = new HashSet<>();

  @OneToMany
  private Set<TaskEntity> taskEntities = new HashSet<>();


  public Exam toExam() {
    return Exam.builder()
        .allowedStudentToExam(mapStudent())
        .content(mapContent())
        .studentGrades(mapStudentGrades())
        .build();
  }

  private Set<StudentGrade> mapStudentGrades() {
    return studentGrades.stream()
        .map(studentGradeEntity -> StudentGrade.builder()
            .studentId(StudentId.of(studentGradeEntity.getStudent().getId()))
            .grade(Grade.valueOf(studentGradeEntity.getGrade()))
            .build())
        .collect(Collectors.toSet());
  }

  private Content mapContent() {
    return Content.of(mapTasks());
  }

  private Set<Task> mapTasks() {
    return taskEntities.stream().map(taskEntity -> Task.builder()
        .taskNumber(TaskNumber.of(taskEntity.getTaskNumber()))
        .id(TaskId.of(taskEntity.getId()))
        .possibleAnswers(mapAnswer(taskEntity.getPossibleAnswers()))
        .correctAnswer(mapAnswer(taskEntity.getCorrectAnswer()))
        .question(Question.of(taskEntity.getQuestion()))
        .id(TaskId.of(taskEntity.getId()))
        .build()).collect(Collectors.toSet());
  }

  private Answer mapAnswer(AnswerEntity answerEntity) {
    return Answer.builder()
        .answer(answerEntity.getAnswer())
        .answerNumber(AnswerNumber.of(answerEntity.getAnswerNumber()))
        .id(AnswerId.of(answerEntity.getId()))
        .build();
  }

  private Set<Answer> mapAnswer(Set<AnswerEntity> possibleAnswers) {
    return possibleAnswers.stream()
        .map(this::mapAnswer)
        .collect(Collectors.toSet());
  }


  private Set<StudentId> mapStudent() {
    return allowedStudentToExam.stream()
        .map(StudentEntity::getId)
        .map(StudentId::of)
        .collect(Collectors.toSet());
  }
}
