package pl.dawid.domain.exam;


import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.dawid.common.exceptions.CustomException;
import pl.dawid.common.valueobjects.AnswerNumber;
import pl.dawid.common.valueobjects.TaskNumber;

@Getter
@AllArgsConstructor(staticName = "of")
public class Content {

  private Set<Task> tasks;


  boolean checkIfTaskCorrect(TaskNumber taskToCheck, AnswerNumber answer) {
    Task task = tasks.stream()
        .filter(task1 -> task1.getTaskNumber().equals(taskToCheck))
        .findAny()
        .orElseThrow(() -> new CustomException("Task was not found"));
    return task.checkIfAnswerCorrect(answer);
  }
}