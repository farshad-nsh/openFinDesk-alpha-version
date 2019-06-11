package com.farshad.openFinDesk.infrastructure.annotations;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Scope(value = "prototype")
@Component
public @interface CommandHandler {
    //Class<? extends Message>[] ExtMessageClasses() default "";
    //EventType EVENT_TYPE() default EventType.NEWASSETARRIVED;
    String version() default "";
    // String[] calculatorNames() default {"nav","anotherVariable"};
}
