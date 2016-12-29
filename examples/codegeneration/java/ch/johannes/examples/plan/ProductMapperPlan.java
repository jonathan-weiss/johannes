package ch.johannes.examples.plan;

import ch.johannes.descriptor.ClassDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.TypeDescriptor;
import ch.johannes.examples.metadata.ProductMetadata;
import ch.johannes.plan.AbstractBeanMapperPlan;
import ch.johannes.plan.FieldMappingOption;
import ch.johannes.plan.SourceFieldPath;
import ch.johannes.plan.TargetFieldPath;

/*
 * 1. nur Angabe der Source-Klasse und des Target-Types. Arbeiten mit all-field und exclude
 * 2. Angabe der Felder inkl. Type mit Verschachtelung
 * 3. Angabe der Felder ohne Type mit Verschachtelung
 */
public class ProductMapperPlan extends AbstractBeanMapperPlan {

    @Override
    public void describePlan() {
        sourceClass(ProductMetadata.PRODUCT_DESCRIPTOR);
        targetClass(TypeDescriptor.of("ch.johannes.examples.generated.mapper.product", "ProductTO"));

        mapField(
                from(ProductMetadata.PRODUCT_IDENTIFIER),
                to(FieldDescriptor.of("id", ProductMetadata.PRODUCT_IDENTIFIER.getFieldType())),
                withOption("")
        );
        mapField(
                from(ProductMetadata.PRODUCT_NAME),
                to(FieldDescriptor.of("name", ProductMetadata.PRODUCT_NAME.getFieldType()))
        );
    }

    private void sourceClass(ClassDescriptor sourceClass) {

    }

    private void targetClass(TypeDescriptor targetType) {

    }

    private void mapField(SourceFieldPath sourceFieldPath, TargetFieldPath targetFieldPath) {

    }

    private void mapField(SourceFieldPath sourceFieldPath, TargetFieldPath targetFieldPath, FieldMappingOption option) {

    }

    private void excludeFields(FieldDescriptor... sourceFields) {

    }

    private void includeFields(FieldDescriptor... sourceFields) {

    }

    private static FieldMappingOption withOption(Object object) {
        return new FieldMappingOption() {
            @Override
            public Object useDefaultValueForNull() {
                return object;
            }
        };
    }

    private static SourceFieldPath from(FieldDescriptor... sourceFields) {
        return null;
    }

    private static TargetFieldPath to(FieldDescriptor... targetFields) {
        return null;
    }
}
