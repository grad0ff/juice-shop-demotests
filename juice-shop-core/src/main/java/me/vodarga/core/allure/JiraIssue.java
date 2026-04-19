package me.vodarga.core.allure;

import static io.qameta.allure.util.ResultsUtils.ISSUE_LINK_TYPE;

import io.qameta.allure.LabelAnnotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@LabelAnnotation(name = ISSUE_LINK_TYPE)
public @interface JiraIssue {

  String value();

}
