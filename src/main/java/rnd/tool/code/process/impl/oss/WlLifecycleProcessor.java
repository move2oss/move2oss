package rnd.tool.code.process.impl.oss;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.visitor.Visitable;

import rnd.tool.code.process.core.impl.CompilationUnitClassProcessor;
import rnd.tool.code.process.core.utils.MappingProvider;

public class WlLifecycleProcessor extends CompilationUnitClassProcessor {

	protected Logger logger = LogManager.getLogger(getClass().getName());

	public WlLifecycleProcessor() {
		super(MappingProvider.getMapping("weblogic/jee"));
	}

	@Override
	protected void processClass(CompilationUnit srcClass, ClassOrInterfaceDeclaration srcTypeDec) {

		boolean found = removeImportAndExtension(srcClass, srcTypeDec,
				"weblogic.application.ApplicationLifecycleListener");

		if (found) {

			// Imports
			srcClass.addImport("javax.servlet.annotation.WebListener");
			srcClass.addImport("javax.annotation.PreDestroy");
			srcClass.addImport("javax.annotation.PostConstruct");

			// Implements
			srcTypeDec.addImplementedType("ServletContextListener");

			// Annotations
			srcTypeDec.addMarkerAnnotation("WebListener");

			// Methods
			srcTypeDec.accept(new ModifierVisitor<Object>() {

				public Visitable visit(MethodDeclaration n, Object arg) {
					if (n.getNameAsString().startsWith("preStart")) {
						n.getAnnotations().add(new MarkerAnnotationExpr("PostConstruct"));
						n.getParameters().clear();
					} else if (n.getNameAsString().startsWith("preStop")) {
						n.getAnnotations().add(new MarkerAnnotationExpr("PreDestroy"));
						n.getParameters().clear();
					} else if (n.getNameAsString().startsWith("postStart")) {
						n.setName("contextInitialized");
					} else if (n.getNameAsString().startsWith("postStop")) {
						n.setName("contextDestroyed");
					}
					return super.visit(n, arg);
				}

			}, null);

		}

	}

}