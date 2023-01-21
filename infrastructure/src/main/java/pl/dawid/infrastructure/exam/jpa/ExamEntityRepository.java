package pl.dawid.infrastructure.exam.jpa;


import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface ExamEntityRepository extends JpaRepository<ExamEntity, UUID> {

}
