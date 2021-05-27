package rnd.tool.code.mig.impl.oss;

import com.github.javaparser.ast.CompilationUnit;

import rnd.tool.code.gen.core.impl.CompilationUnitClassGenerator;
import rnd.tool.code.mig.core.AbstractMigrator;
import rnd.tool.code.parse.impl.oss.WlMDBParser;
import rnd.tool.code.process.impl.oss.WlMDBProcessor;

public class WlMDB2JeeMDB extends AbstractMigrator<CompilationUnit> {

	public WlMDB2JeeMDB() {
		this.classPar = new WlMDBParser();
		this.classPro = new WlMDBProcessor();
		this.classGen = new CompilationUnitClassGenerator();
	}

}
