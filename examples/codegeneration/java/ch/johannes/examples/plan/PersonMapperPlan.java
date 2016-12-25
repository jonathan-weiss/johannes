package ch.johannes.examples.plan;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.examples.metadata.PersonMetadata;
import ch.johannes.examples.metadata.PersonTOMetadata;
import ch.johannes.plan.AbstractBeanMapperPlan;
import ch.johannes.plan.SourceClassPath;
import ch.johannes.plan.SourceFieldPath;
import ch.johannes.plan.TargetClassPath;
import ch.johannes.plan.TargetFieldPath;

/*
 * plan must give the following possibilities:
 * - Map all fields
 * - ignore some fields, when using map all fields
 * - map certain fields.
 */
public class PersonMapperPlan extends AbstractBeanMapperPlan {

    @Override
    public void describePlan() {
        mapClass(from(PersonMetadata.CLASS_DESCRIPTOR), to(PersonTOMetadata.CLASS_DESCRIPTOR));
        mapFields(from(PersonMetadata.FIRSTNAME, PersonMetadata.LASTNAME), to(PersonTOMetadata.LASTNAME));

    }

    private void mapClass(SourceClassPath sourceClassPath, TargetClassPath targetClassPath) {

    }

    private static SourceClassPath from(ClassDescriptor sourceClassDescriptor) {
        return null;
    }

    private static TargetClassPath to(ClassDescriptor targetClassDescriptor) {
        return null;
    }

    private void mapFields(SourceFieldPath sourceFieldPath, TargetFieldPath targetFieldPath) {

    }

    private static SourceFieldPath from(FieldDescriptor... sourceFields) {
        return null;
    }

    private static TargetFieldPath to(FieldDescriptor... targetFields) {
        return null;
    }
}
