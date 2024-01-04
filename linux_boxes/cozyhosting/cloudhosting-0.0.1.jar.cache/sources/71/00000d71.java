package lombok.eclipse;

import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.eclipse.jdt.internal.compiler.impl.ReferenceContext;
import org.eclipse.jdt.internal.compiler.problem.DefaultProblem;
import org.eclipse.jdt.internal.compiler.util.Util;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/eclipse/EclipseAstProblemView.SCL.lombok */
public class EclipseAstProblemView {
    public static void addProblemToCompilationResult(char[] fileNameArray, CompilationResult result, boolean isWarning, String message, int sourceStart, int sourceEnd) {
        int i;
        int i2;
        if (result == null) {
            return;
        }
        if (fileNameArray == null) {
            fileNameArray = "(unknown).java".toCharArray();
        }
        if (sourceStart >= 0) {
            int[] lineEnds = result.getLineSeparatorPositions();
            i = Util.getLineNumber(sourceStart, lineEnds, 0, lineEnds.length - 1);
        } else {
            i = 0;
        }
        int lineNumber = i;
        if (sourceStart >= 0) {
            i2 = Util.searchColumnNumber(result.getLineSeparatorPositions(), lineNumber, sourceStart);
        } else {
            i2 = 0;
        }
        int columnNumber = i2;
        result.record(new LombokProblem(fileNameArray, message, 0, new String[0], isWarning ? 0 : 1, sourceStart, sourceEnd, lineNumber, columnNumber), (ReferenceContext) null);
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/eclipse/EclipseAstProblemView$LombokProblem.SCL.lombok */
    private static class LombokProblem extends DefaultProblem {
        private static final String MARKER_ID = "org.eclipse.jdt.apt.pluggable.core.compileProblem";

        public LombokProblem(char[] originatingFileName, String message, int id, String[] stringArguments, int severity, int startPosition, int endPosition, int line, int column) {
            super(originatingFileName, message, id, stringArguments, severity, startPosition, endPosition, line, column);
        }

        public int getCategoryID() {
            return 0;
        }

        public String getMarkerType() {
            return MARKER_ID;
        }
    }
}