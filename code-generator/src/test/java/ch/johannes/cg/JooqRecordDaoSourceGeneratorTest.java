package ch.johannes.cg;

import ch.johannes.core.FileUtil;
import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;
import ch.johannes.plan.JooqRecordDaoSourcePlan;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static ch.johannes.descriptor.Descriptors.STRING_TYPE_DESCRIPTOR;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class JooqRecordDaoSourceGeneratorTest {

    @Test
    public void generateCode() throws Exception {
        String expectedJavaSourceText = FileUtil.readFileInPackage(this, JooqRecordDaoSourceGeneratorTest.class.getSimpleName() + ".generateCode.txt");

        JooqRecordDaoSourceGenerator cg = new JooqRecordDaoSourceGenerator();

        PackageDescriptor jooqTablePackage = PackageDescriptor.of("ch.johannes.examples.schema.tables");
        PackageDescriptor jooqRecordPackage = PackageDescriptor.of("ch.johannes.examples.schema.records");
        PackageDescriptor daoPackage = PackageDescriptor.of("ch.johannes.examples.mapper.oneone");

        ClassnameDescriptor daoClassname = ClassnameDescriptor.of("JooqPersonDao");

        TypeDescriptor jooqTableConstant = TypeDescriptor.of(jooqTablePackage, ClassnameDescriptor.of("Tables.PERSON"));

        TypeDescriptor jooqTableRecord = TypeDescriptor.of(jooqRecordPackage, ClassnameDescriptor.of("PersonRecord"));

        List<FieldDescriptor> jooqTableFields = new ArrayList<>();
        jooqTableFields.add(FieldDescriptor.of("firstname", STRING_TYPE_DESCRIPTOR));
        jooqTableFields.add(FieldDescriptor.of("lastname", STRING_TYPE_DESCRIPTOR));

        JooqRecordDaoSourcePlan plan = new JooqRecordDaoSourcePlan(daoPackage, daoClassname, jooqTableConstant, jooqTableRecord, jooqTableFields);

        String generatedCode = cg.generateCode(plan);
        assertThat(generatedCode, equalTo(expectedJavaSourceText));
    }
}