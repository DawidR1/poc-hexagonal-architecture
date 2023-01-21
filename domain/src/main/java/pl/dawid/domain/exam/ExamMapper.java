package pl.dawid.domain.exam;


import java.util.Set;
import java.util.stream.Collectors;
import pl.dawid.common.valueobjects.AnswerNumber;
import pl.dawid.domain.exam.dto.AnswerDto;
import pl.dawid.domain.exam.dto.ExamContentDto;

class ExamMapper {

  public Content toContent(ExamContentDto examContentDto) {
    Set<Task> tasks = examContentDto.getTasks()
        .stream()
        .map(taskDto -> Task.builder()
            .question(taskDto.getQuestion())
            .correctAnswer(map(taskDto.getCorrectAnswer()))
            .possibleAnswers(map(taskDto.getPossibleAnswers()))
            .taskNumber(taskDto.getTaskNumber())
            .build())
        .collect(Collectors.toSet());
    return Content.of(tasks);
  }

  private Set<Answer> map(Set<AnswerDto> possibleAnswers) {
    return possibleAnswers.stream()
        .map(this::map)
        .collect(Collectors.toSet());
  }

  private Answer map(AnswerDto correctAnswer) {
    return Answer.builder()
        .answerNumber(AnswerNumber.of(correctAnswer.getNumber()))
        .answer(correctAnswer.getAnswerContent())
        .build();
  }
}
