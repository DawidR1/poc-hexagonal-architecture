package pl.dawid.domain.exam;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import pl.dawid.domain.student.StudentId;

@Getter
@EqualsAndHashCode(of = "studentId")
@Builder
@AllArgsConstructor
public class StudentGrade {

  private static final Map<Predicate<Integer>, Grade> gradeByRange
      = Map.of(value -> value >= 90, Grade.A,
      value -> value >= 80 && value < 90, Grade.B,
      value -> value >= 70 && value < 80, Grade.C,
      value -> value >= 60 && value < 70, Grade.D,
      value -> value < 60, Grade.F);

  private StudentId studentId;
  private Grade grade;

  static StudentGrade giveGrade(StudentId studentId, long numberOfCorrectAnswers, long numberOfTasks) {
    int result = countPercentageOfCorrectAnswers(numberOfCorrectAnswers, numberOfTasks);
    Grade grade = getGrade(result);
    return new StudentGrade(studentId, grade);
  }

  private static Grade getGrade(int result) {
    return gradeByRange.entrySet()
        .stream()
        .filter(predicateGradeEntry -> predicateGradeEntry.getKey().test(result))
        .map(Entry::getValue)
        .findAny()
        .get();
  }

  private static int countPercentageOfCorrectAnswers(long numberOfCorrectAnswers, long numberOfTasks) {
    BigDecimal numberOfCorrectAnswersB = new BigDecimal(numberOfCorrectAnswers);
    BigDecimal numberOfTasksB = new BigDecimal(numberOfTasks);
    BigDecimal divide = numberOfCorrectAnswersB.divide(numberOfTasksB, 2, RoundingMode.HALF_UP);
    return divide.multiply(new BigDecimal(100)).intValue();
  }
}
