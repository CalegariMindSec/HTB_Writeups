package org.hibernate.boot.model.relational;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/model/relational/InitCommand.class */
public class InitCommand {
    private final String[] initCommands;

    public InitCommand(String... initCommands) {
        this.initCommands = initCommands;
    }

    public String[] getInitCommands() {
        return this.initCommands;
    }
}