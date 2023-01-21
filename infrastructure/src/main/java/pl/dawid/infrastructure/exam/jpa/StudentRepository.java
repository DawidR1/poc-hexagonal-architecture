package pl.dawid.infrastructure.exam.jpa;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {


  Set<StudentEntity> findAllByIdIn(Collection<UUID> ids);
}
