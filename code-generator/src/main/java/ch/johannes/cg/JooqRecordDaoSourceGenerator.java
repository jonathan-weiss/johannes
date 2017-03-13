package ch.johannes.cg;

import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.plan.JooqRecordDaoSourcePlan;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.List;

public class JooqRecordDaoSourceGenerator {

    public String generateCode(JooqRecordDaoSourcePlan jooqRecordDaoSourcePlan) {

        //private final JooqExecutor jooqExecutor;
        ClassName classOfJooqExecutor = ClassName.get("ch.johannes.data", "JooqExecutor"); //TODO make configurable
        FieldSpec jooqExecutorField = FieldSpec.builder(classOfJooqExecutor, "jooqExecutor")
                .addModifiers(Modifier.PRIVATE)
                .addModifiers(Modifier.FINAL)
                .build();

        //@Inject
        //public JooqPersonDao(JooqExecutor jooqExecutor) {
        //    this.jooqExecutor = jooqExecutor;
        //}
        ClassName injectClassName = ClassName.get("javax.inject", "Inject");
        final MethodSpec constructorMethodSpec = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(classOfJooqExecutor, "jooqExecutor")
                .addStatement("this.$L = $L", "jooqExecutor", "jooqExecutor")
                .addAnnotation(injectClassName)
                .build();

        String recordEntityName = jooqRecordDaoSourcePlan.getJooqTableRecord().getClassName().getClassName();
        //@Override
        //public List<PersonRecord> getAllPersonRecord() {
        //    final Result<PersonRecord> records = jooqExecutor.execute(dslContext -> dslContext.selectFrom(PERSON).fetch());
        //    return records;
        //}
        ClassName recordClassName = ClassName.get(jooqRecordDaoSourcePlan.getJooqTableRecord().getClassPackage().getPackageName(),
                jooqRecordDaoSourcePlan.getJooqTableRecord().getClassName().getClassName());

        final ParameterizedTypeName listOfRecords = ParameterizedTypeName.get(ClassName.get(List.class), recordClassName);

        ClassName staticImportedTable = ClassName.get(
                jooqRecordDaoSourcePlan.getJooqTableConstant().getClassPackage().getPackageName(),
                jooqRecordDaoSourcePlan.getJooqTableConstant().getClassName().getClassName());


        MethodSpec fetchAllMethodSpec = MethodSpec.methodBuilder(BeanUtil.prefixName(recordEntityName, "fetchAll"))
                .addModifiers(Modifier.PUBLIC)
                .returns(listOfRecords)
                .addStatement("final Result<$T> records = jooqExecutor.execute(dslContext -> dslContext.selectFrom($T).fetch()", recordClassName, staticImportedTable)
                .addStatement("return records")
                .build();


        ClassName applicationScopedClassName = ClassName.get("javax.inject", "ApplicationScoped");
        TypeSpec targetType = TypeSpec.classBuilder(jooqRecordDaoSourcePlan.getDaoClassname().getClassName())
                .addModifiers(Modifier.PUBLIC)
                .addField(jooqExecutorField)
                .addMethod(constructorMethodSpec)
                .addMethod(fetchAllMethodSpec)
                .addAnnotation(applicationScopedClassName)
                .build();

        JavaFile javaFile = JavaFile.builder(jooqRecordDaoSourcePlan.getDaoPackage().getPackageName(), targetType)
                .build();

        return javaFile.toString();
    }

}
