package org.hibernate.boot.jaxb.internal.stax;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/internal/stax/XMLStreamConstantsUtils.class */
public final class XMLStreamConstantsUtils {
    private XMLStreamConstantsUtils() {
    }

    public static String getEventName(int eventId) {
        switch (eventId) {
            case 1:
                return "StartElementEvent";
            case 2:
                return "EndElementEvent";
            case 3:
                return "ProcessingInstructionEvent";
            case 4:
                return "CharacterEvent";
            case 5:
                return "CommentEvent";
            case 6:
            default:
                return "UNKNOWN_EVENT_TYPE";
            case 7:
                return "StartDocumentEvent";
            case 8:
                return "EndDocumentEvent";
            case 9:
                return "EntityReferenceEvent";
            case 10:
                return "AttributeBase";
            case 11:
                return "DTDEvent";
            case 12:
                return "CDATA";
        }
    }
}