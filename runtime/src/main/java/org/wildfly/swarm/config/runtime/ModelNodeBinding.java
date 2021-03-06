package org.wildfly.swarm.config.runtime;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Heiko Braun
 * @date 4/19/11
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ModelNodeBinding {

    /**
     * The name of the DMR attribute
     *
     * @return
     */
    String detypedName();
}