package pl.dawid.common.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class AnswerNumber {

  private Long value;

}
