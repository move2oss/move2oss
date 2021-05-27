package rnd.tool.code.mig.impl.oss;

import com.github.javaparser.ast.CompilationUnit;

import rnd.tool.code.gen.core.impl.CompilationUnitClassGenerator;
import rnd.tool.code.mig.core.AbstractMigrator;
import rnd.tool.code.parse.core.impl.CompilationUnitClassParser;
import rnd.tool.code.process.core.impl.CompilationUnitClassProcessor;
import rnd.tool.code.process.core.utils.MappingProvider;

public class WsJms2JeeJms extends AbstractMigrator<CompilationUnit> {

	public WsJms2JeeJms() {
		this.classPar = new CompilationUnitClassParser();
		this.classPro = new CompilationUnitClassProcessor(MappingProvider.getMapping("websphere/jms"));
		this.classGen = new CompilationUnitClassGenerator();
	}

}
