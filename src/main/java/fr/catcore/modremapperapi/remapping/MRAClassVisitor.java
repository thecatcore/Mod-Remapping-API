package fr.catcore.modremapperapi.remapping;

import io.github.fabriccompatibiltylayers.modremappingapi.impl.VisitorInfosImpl;
import org.objectweb.asm.*;

public class MRAClassVisitor extends ClassVisitor {
    private final VisitorInfosImpl infos;
    private final String className;

    protected MRAClassVisitor(ClassVisitor classVisitor, VisitorInfosImpl infos, String className) {
        super(Opcodes.ASM9, classVisitor);
        this.infos = infos;
        this.className = className;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        String superType = superName;

        if (infos.SUPERS.containsKey(superName)) {
            superType = infos.SUPERS.get(superName);
        }

        super.visit(version, access, name, signature, superType, interfaces);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        String annotationType = descriptor;

        if (infos.ANNOTATION.containsKey(descriptor)) {
            annotationType = infos.ANNOTATION.get(descriptor);
        }

        return super.visitTypeAnnotation(typeRef, typePath, annotationType, visible);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor original = super.visitMethod(access, name, descriptor, signature, exceptions);
        original = new MRAMethodVisitor(original, infos, this.className);
        return original;
    }
}
