package pl.dawid.domain.exam.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AnswerDto {
   Long number;
   String answerContent;

}
