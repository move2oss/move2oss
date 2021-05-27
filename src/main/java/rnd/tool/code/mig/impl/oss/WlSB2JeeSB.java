package rnd.tool.code.mig.impl.oss;

import com.github.javaparser.ast.CompilationUnit;

import rnd.tool.code.gen.core.impl.CompilationUnitClassGenerator;
import rnd.tool.code.mig.core.AbstractMigrator;
import rnd.tool.code.parse.impl.oss.WlSBParser;
import rnd.tool.code.process.impl.oss.WlSBProcessor;

public class WlSB2JeeSB extends AbstractMigrator<CompilationUnit> {

	public WlSB2JeeSB() {
		this.classPar = new WlSBParser();
		this.classPro = new WlSBProcessor();
		this.classGen = new CompilationUnitClassGenerator();
	}

}