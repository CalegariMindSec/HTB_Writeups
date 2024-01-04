package org.aspectj.util;

import org.springframework.beans.PropertyAccessor;
import org.springframework.jdbc.datasource.init.ScriptUtils;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/util/GenericSignature.class */
public class GenericSignature {

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/util/GenericSignature$ClassSignature.class */
    public static class ClassSignature {
        public ClassTypeSignature superclassSignature;
        public FormalTypeParameter[] formalTypeParameters = FormalTypeParameter.NONE;
        public ClassTypeSignature[] superInterfaceSignatures = ClassTypeSignature.NONE;

        public String toString() {
            ClassTypeSignature[] classTypeSignatureArr;
            StringBuilder ret = new StringBuilder();
            ret.append(this.formalTypeParameters.toString());
            ret.append(this.superclassSignature.toString());
            for (ClassTypeSignature superInterfaceSignature : this.superInterfaceSignatures) {
                ret.append(superInterfaceSignature.toString());
            }
            return ret.toString();
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/util/GenericSignature$MethodTypeSignature.class */
    public static class MethodTypeSignature {
        public FormalTypeParameter[] formalTypeParameters;
        public TypeSignature[] parameters;
        public TypeSignature returnType;
        public FieldTypeSignature[] throwsSignatures;

        public MethodTypeSignature(FormalTypeParameter[] aFormalParameterList, TypeSignature[] aParameterList, TypeSignature aReturnType, FieldTypeSignature[] aThrowsSignatureList) {
            this.formalTypeParameters = FormalTypeParameter.NONE;
            this.parameters = new TypeSignature[0];
            this.throwsSignatures = new FieldTypeSignature[0];
            this.formalTypeParameters = aFormalParameterList;
            this.parameters = aParameterList;
            this.returnType = aReturnType;
            this.throwsSignatures = aThrowsSignatureList;
        }

        public String toString() {
            TypeSignature[] typeSignatureArr;
            FieldTypeSignature[] fieldTypeSignatureArr;
            FormalTypeParameter[] formalTypeParameterArr;
            StringBuilder sb = new StringBuilder();
            if (this.formalTypeParameters.length > 0) {
                sb.append("<");
                for (FormalTypeParameter formalTypeParameter : this.formalTypeParameters) {
                    sb.append(formalTypeParameter.toString());
                }
                sb.append(">");
            }
            sb.append("(");
            for (TypeSignature parameter : this.parameters) {
                sb.append(parameter.toString());
            }
            sb.append(")");
            sb.append(this.returnType.toString());
            for (FieldTypeSignature throwsSignature : this.throwsSignatures) {
                sb.append("^");
                sb.append(throwsSignature.toString());
            }
            return sb.toString();
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/util/GenericSignature$FormalTypeParameter.class */
    public static class FormalTypeParameter {
        public static final FormalTypeParameter[] NONE = new FormalTypeParameter[0];
        public String identifier;
        public FieldTypeSignature classBound;
        public FieldTypeSignature[] interfaceBounds;

        public String toString() {
            FieldTypeSignature[] fieldTypeSignatureArr;
            StringBuilder ret = new StringBuilder();
            ret.append("T");
            ret.append(this.identifier);
            ret.append(":");
            ret.append(this.classBound.toString());
            for (FieldTypeSignature interfaceBound : this.interfaceBounds) {
                ret.append(":");
                ret.append(interfaceBound.toString());
            }
            return ret.toString();
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/util/GenericSignature$TypeSignature.class */
    public static abstract class TypeSignature {
        public boolean isBaseType() {
            return false;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/util/GenericSignature$BaseTypeSignature.class */
    public static class BaseTypeSignature extends TypeSignature {
        private final String sig;

        public BaseTypeSignature(String aPrimitiveType) {
            this.sig = aPrimitiveType;
        }

        @Override // org.aspectj.util.GenericSignature.TypeSignature
        public boolean isBaseType() {
            return true;
        }

        public String toString() {
            return this.sig;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/util/GenericSignature$FieldTypeSignature.class */
    public static abstract class FieldTypeSignature extends TypeSignature {
        public boolean isClassTypeSignature() {
            return false;
        }

        public boolean isTypeVariableSignature() {
            return false;
        }

        public boolean isArrayTypeSignature() {
            return false;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/util/GenericSignature$ClassTypeSignature.class */
    public static class ClassTypeSignature extends FieldTypeSignature {
        public static final ClassTypeSignature[] NONE = new ClassTypeSignature[0];
        public String classSignature;
        public SimpleClassTypeSignature outerType;
        public SimpleClassTypeSignature[] nestedTypes;

        public ClassTypeSignature(String sig, String identifier) {
            this.classSignature = sig;
            this.outerType = new SimpleClassTypeSignature(identifier);
            this.nestedTypes = new SimpleClassTypeSignature[0];
        }

        public ClassTypeSignature(String sig, SimpleClassTypeSignature outer, SimpleClassTypeSignature[] inners) {
            this.classSignature = sig;
            this.outerType = outer;
            this.nestedTypes = inners;
        }

        @Override // org.aspectj.util.GenericSignature.FieldTypeSignature
        public boolean isClassTypeSignature() {
            return true;
        }

        public String toString() {
            return this.classSignature;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/util/GenericSignature$TypeVariableSignature.class */
    public static class TypeVariableSignature extends FieldTypeSignature {
        public String typeVariableName;

        public TypeVariableSignature(String typeVarToken) {
            this.typeVariableName = typeVarToken.substring(1);
        }

        @Override // org.aspectj.util.GenericSignature.FieldTypeSignature
        public boolean isTypeVariableSignature() {
            return true;
        }

        public String toString() {
            return "T" + this.typeVariableName + ScriptUtils.DEFAULT_STATEMENT_SEPARATOR;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/util/GenericSignature$ArrayTypeSignature.class */
    public static class ArrayTypeSignature extends FieldTypeSignature {
        public TypeSignature typeSig;

        public ArrayTypeSignature(TypeSignature aTypeSig) {
            this.typeSig = aTypeSig;
        }

        @Override // org.aspectj.util.GenericSignature.FieldTypeSignature
        public boolean isArrayTypeSignature() {
            return true;
        }

        public String toString() {
            return PropertyAccessor.PROPERTY_KEY_PREFIX + this.typeSig.toString();
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/util/GenericSignature$SimpleClassTypeSignature.class */
    public static class SimpleClassTypeSignature {
        public String identifier;
        public TypeArgument[] typeArguments;

        public SimpleClassTypeSignature(String identifier) {
            this.identifier = identifier;
            this.typeArguments = new TypeArgument[0];
        }

        public SimpleClassTypeSignature(String identifier, TypeArgument[] args) {
            this.identifier = identifier;
            this.typeArguments = args;
        }

        public String toString() {
            TypeArgument[] typeArgumentArr;
            StringBuilder sb = new StringBuilder();
            sb.append(this.identifier);
            if (this.typeArguments.length > 0) {
                sb.append("<");
                for (TypeArgument typeArgument : this.typeArguments) {
                    sb.append(typeArgument.toString());
                }
                sb.append(">");
            }
            return sb.toString();
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/util/GenericSignature$TypeArgument.class */
    public static class TypeArgument {
        public boolean isWildcard;
        public boolean isPlus;
        public boolean isMinus;
        public FieldTypeSignature signature;

        public TypeArgument() {
            this.isWildcard = false;
            this.isPlus = false;
            this.isMinus = false;
            this.isWildcard = true;
        }

        public TypeArgument(boolean plus, boolean minus, FieldTypeSignature aSig) {
            this.isWildcard = false;
            this.isPlus = false;
            this.isMinus = false;
            this.isPlus = plus;
            this.isMinus = minus;
            this.signature = aSig;
        }

        public String toString() {
            if (this.isWildcard) {
                return "*";
            }
            StringBuilder sb = new StringBuilder();
            if (this.isPlus) {
                sb.append("+");
            }
            if (this.isMinus) {
                sb.append("-");
            }
            sb.append(this.signature.toString());
            return sb.toString();
        }
    }
}