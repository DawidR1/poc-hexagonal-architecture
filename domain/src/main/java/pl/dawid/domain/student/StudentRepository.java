package pl.dawid.domain.student;


import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface StudentRepository {

  Optional<Student> findById(StudentId studentId);
  Set<Student> findByIdIn(Collection<StudentId> studentIds);

}
