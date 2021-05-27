package rnd.tool.code.process.impl.oss;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import rnd.tool.code.process.core.impl.CompilationUnitClassProcessor;

public class WlSBProcessor extends CompilationUnitClassProcessor {

	@Override
	protected void processClass(CompilationUnit sourceClass, ClassOrInterfaceDeclaration sourceTypeDec) {
		removeImportAndExtension(sourceClass, sourceTypeDec, "weblogic.ejb.GenericSessionBean");
	}

}
