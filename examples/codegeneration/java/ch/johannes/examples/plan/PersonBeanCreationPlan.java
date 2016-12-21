package ch.johannes.examples.plan;

import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.examples.metadata.PersonMetadata;
import ch.johannes.plan.AbstractBeanCreationPlan;

public class PersonBeanCreationPlan extends AbstractBeanCreationPlan {

    @Override
    public void describePlan() {
        //TODO withAllFieldOf
        //TODO excluding
        //TODO andGetter
        //TODO andSetter
        //TODO immutable

        inPackage(PersonMetadata.PACKAGE_DESCRIPTOR);
        withClassname("SimplePerson");
        withField(PersonMetadata.FIRSTNAME);
        withField(PersonMetadata.LASTNAME);
    }

}
