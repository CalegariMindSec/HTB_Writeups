package org.postgresql.gss;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/gss/GSSCallbackHandler.class */
class GSSCallbackHandler implements CallbackHandler {
    private final String user;
    private final char[] password;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GSSCallbackHandler(String user, char[] password) {
        this.user = user;
        this.password = password;
    }

    @Override // javax.security.auth.callback.CallbackHandler
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof TextOutputCallback) {
                TextOutputCallback toc = (TextOutputCallback) callback;
                switch (toc.getMessageType()) {
                    case 0:
                        System.out.println("INFO: " + toc.getMessage());
                        continue;
                    case 1:
                        System.out.println("WARNING: " + toc.getMessage());
                        continue;
                    case 2:
                        System.out.println("ERROR: " + toc.getMessage());
                        continue;
                    default:
                        throw new IOException("Unsupported message type: " + toc.getMessageType());
                }
            } else if (callback instanceof NameCallback) {
                NameCallback nc = (NameCallback) callback;
                nc.setName(this.user);
            } else if (callback instanceof PasswordCallback) {
                PasswordCallback pc = (PasswordCallback) callback;
                if (this.password == null) {
                    throw new IOException("No cached kerberos ticket found and no password supplied.");
                }
                pc.setPassword(this.password);
            } else {
                throw new UnsupportedCallbackException(callback, "Unrecognized Callback");
            }
        }
    }
}