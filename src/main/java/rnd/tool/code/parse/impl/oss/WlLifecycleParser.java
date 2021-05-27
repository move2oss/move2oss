package rnd.tool.code.parse.impl.oss;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import rnd.tool.code.parse.core.impl.CompilationUnitClassParser;

public class WlLifecycleParser extends CompilationUnitClassParser {

	@Override
	protected boolean checkClass(CompilationUnit sourceClass, ClassOrInterfaceDeclaration sourceTypeDec) {
		return checkExtension(sourceTypeDec, "ApplicationLifecycleListener");
	}

}
