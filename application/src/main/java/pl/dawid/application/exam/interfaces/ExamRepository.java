package pl.dawid.application.exam.interfaces;


import java.util.Optional;
import pl.dawid.domain.exam.Exam;
import pl.dawid.domain.exam.ExamId;

public interface ExamRepository {

  Optional<Exam> findById(ExamId examId);

  void save(Exam answer);
}
