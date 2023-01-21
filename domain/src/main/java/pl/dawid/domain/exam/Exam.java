package pl.dawid.domain.exam;


import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.dawid.common.exceptions.CustomException;
import pl.dawid.common.valueobjects.AnswerNumber;
import pl.dawid.common.valueobjects.TaskNumber;
import pl.dawid.domain.exam.dto.ExamContentDto;
import pl.dawid.domain.exam.dto.CompletedExamDto;
import pl.dawid.domain.student.StudentId;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Exam {

  private ExamId id;
  private Content content;
  private Set<StudentId> allowedStudentToExam = new HashSet<>();
  private Set<StudentGrade> studentGrades = new HashSet<>();

  private Exam(Content content, Set<StudentId> allowedStudentToExam) {
    this.content = content;
    this.allowedStudentToExam = allowedStudentToExam;
  }

  public static Exam createExam(Set<StudentId> studentIds, ExamContentDto contentDto) {
    Content content = new ExamMapper().toContent(contentDto);
    return new Exam(content, studentIds);
  }

  public void submit(CompletedExamDto dto) {
    StudentId studentToSave = dto.getStudentId();
    checkIfStudentCanTakeTheTest(studentToSave);
    checkIfStudentNotCompletedTest(studentToSave);
    long correctAnswers = countCorrectAnswers(dto);
    StudentGrade studentGrade = StudentGrade.giveGrade(studentToSave, correctAnswers, content.getTasks().size());
    studentGrades.add(studentGrade);
  }

  private long countCorrectAnswers(CompletedExamDto dto) {
    return dto.getAnswers()
        .entrySet()
        .stream()
        .filter(this::isAnswerCorrect)
        .count();
  }

  private void checkIfStudentNotCompletedTest(StudentId studentToSave) {
    studentGrades.stream()
        .map(StudentGrade::getStudentId)
        .filter(studentToSave::equals)
        .findAny()
        .ifPresent(studentGrade -> {
          throw new CustomException("Student has already complete the exam");
        });
  }

  private void checkIfStudentCanTakeTheTest(StudentId studentToSave) {
    allowedStudentToExam
        .stream()
        .filter(studentToSave::equals)
        .findAny()
        .orElseThrow(() -> new CustomException("Student was not allowed to take the exam"));
  }

  private boolean isAnswerCorrect(Entry<TaskNumber, AnswerNumber> questionNumberUUIDEntry) {
    return content.checkIfTaskCorrect(questionNumberUUIDEntry.getKey(), questionNumberUUIDEntry.getValue());
  }
}