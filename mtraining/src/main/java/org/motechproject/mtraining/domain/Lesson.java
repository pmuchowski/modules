package org.motechproject.mtraining.domain;

import org.motechproject.mds.annotations.Entity;

/**
 * Lesson domain object forms the leaf node in the course structure hierarchy.
 */
@Entity
public class Lesson extends CourseUnitMetadata {

    public Lesson() {
        super();
    }

    public Lesson(String name, CourseUnitState state, String content) {

        super(name, state, content);
    }

}
