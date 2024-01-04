package org.hibernate.boot.jaxb.hbm.transform;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/transform/TargetColumnAdapter.class */
interface TargetColumnAdapter {
    void setName(String str);

    void setTable(String str);

    void setNullable(Boolean bool);

    void setUnique(Boolean bool);

    void setColumnDefinition(String str);

    void setLength(Integer num);

    void setPrecision(Integer num);

    void setScale(Integer num);

    void setDefault(String str);

    void setCheck(String str);

    void setComment(String str);

    void setRead(String str);

    void setWrite(String str);

    void setInsertable(Boolean bool);

    void setUpdatable(Boolean bool);
}