package me.vodarga.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum SecurityQuestionType {

  FAVORITE_PET("Name of your favorite pet?");

  private final String question;

}
