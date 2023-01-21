package pl.dawid.infrastructure.exam.endpoint;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dawid.application.exam.dto.CreateExamDto;
import pl.dawid.application.exam.dto.SubmitStudentDto;
import pl.dawid.application.exam.interfaces.CreateExamUseCase;
import pl.dawid.application.exam.interfaces.SubmitExamUseCase;


@RestController
@RequiredArgsConstructor
@RequestMapping("exam")
class ExamController {

  private final SubmitExamUseCase submitExamUseCase;
  private final CreateExamUseCase createExamUseCase;

  @PostMapping("submit")
  public ResponseEntity<Void> submit(@RequestBody @Valid SubmitStudentDto submitStudentDto) {
    submitExamUseCase.submitExam(submitStudentDto);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .build();
  }


  @PostMapping()
  public ResponseEntity<Void> createExam(@RequestBody @Valid CreateExamDto createExamDto) {
    createExamUseCase.createExam(createExamDto);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .build();
  }
}