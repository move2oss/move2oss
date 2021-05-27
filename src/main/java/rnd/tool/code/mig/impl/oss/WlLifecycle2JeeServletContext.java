package rnd.tool.code.mig.impl.oss;

import com.github.javaparser.ast.CompilationUnit;

import rnd.tool.code.gen.core.impl.CompilationUnitClassGenerator;
import rnd.tool.code.mig.core.AbstractMigrator;
import rnd.tool.code.parse.impl.oss.WlLifecycleParser;
import rnd.tool.code.process.impl.oss.WlLifecycleProcessor;

public class WlLifecycle2JeeServletContext extends AbstractMigrator<CompilationUnit> {

	public WlLifecycle2JeeServletContext() {
		this.classPar = new WlLifecycleParser();
		this.classPro = new WlLifecycleProcessor();
		this.classGen = new CompilationUnitClassGenerator();
	}

}
