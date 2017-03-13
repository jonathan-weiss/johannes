package ch.johannes.plan;

import ch.johannes.descriptor.ClassnameDescriptor;
import ch.johannes.descriptor.FieldDescriptor;
import ch.johannes.descriptor.PackageDescriptor;
import ch.johannes.descriptor.TypeDescriptor;

import java.util.List;

public class JooqRecordDaoSourcePlan {

    private final PackageDescriptor daoPackage;

    private final ClassnameDescriptor daoClassname;

    private final TypeDescriptor jooqTableConstant;

    private final TypeDescriptor jooqTableRecord;

    private final List<FieldDescriptor> jooqTableFields;

    public JooqRecordDaoSourcePlan(PackageDescriptor daoPackage, ClassnameDescriptor daoClassname, TypeDescriptor jooqTableConstant, TypeDescriptor jooqTableRecord, List<FieldDescriptor> jooqTableFields) {
        this.daoPackage = daoPackage;
        this.daoClassname = daoClassname;
        this.jooqTableConstant = jooqTableConstant;
        this.jooqTableRecord = jooqTableRecord;
        this.jooqTableFields = jooqTableFields;
    }

    public PackageDescriptor getDaoPackage() {
        return daoPackage;
    }

    public ClassnameDescriptor getDaoClassname() {
        return daoClassname;
    }

    public TypeDescriptor getJooqTableConstant() {
        return jooqTableConstant;
    }

    public TypeDescriptor getJooqTableRecord() {
        return jooqTableRecord;
    }

    public List<FieldDescriptor> getJooqTableFields() {
        return jooqTableFields;
    }
}
