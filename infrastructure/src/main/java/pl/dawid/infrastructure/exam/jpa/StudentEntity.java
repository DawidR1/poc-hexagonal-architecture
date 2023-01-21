package pl.dawid.infrastructure.exam.jpa;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "students")
@Getter
public class StudentEntity {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  private Boolean isActive;
}