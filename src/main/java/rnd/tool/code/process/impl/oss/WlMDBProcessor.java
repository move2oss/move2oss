package rnd.tool.code.process.impl.oss;

import java.util.Iterator;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.ArrayInitializerExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import rnd.tool.code.process.core.impl.CompilationUnitClassProcessor;
import rnd.tool.code.process.core.utils.MappingProvider;

public class WlMDBProcessor extends CompilationUnitClassProcessor {

	public WlMDBProcessor() {
		setMapping(MappingProvider.getMapping("weblogic/ejb"));
	}

	@Override
	protected void processClass(CompilationUnit sourceClass, ClassOrInterfaceDeclaration sourceTypeDec) {

		boolean found = removeImportAndExtension(sourceClass, sourceTypeDec, "weblogic.ejb.GenericMessageDrivenBean");
		if (!found) {
			return;
		}

		NodeList<AnnotationExpr> annotations = sourceTypeDec.getAnnotations();

		for (Iterator<AnnotationExpr> annotItr = annotations.iterator(); annotItr.hasNext();) {
			AnnotationExpr annot = (AnnotationExpr) annotItr.next();

			if (annot.getNameAsString().equals("MessageDriven")) {

				NormalAnnotationExpr messDrvnAnnot = (NormalAnnotationExpr) annot;

				NodeList<Expression> actCfgAnnots = new NodeList<>();

				messDrvnAnnot.accept(new VoidVisitorAdapter<Object>() {
					public void visit(MemberValuePair pair, Object arg) {

						String newPrpName = (String) getMapping().get("MessageDriven." + pair.getNameAsString());
						if (newPrpName != null) {

							NodeList<MemberValuePair> newPairs = new NodeList<MemberValuePair>();

							MemberValuePair prpNamePair = new MemberValuePair("propertyName",
									new StringLiteralExpr(newPrpName));
							newPairs.add(prpNamePair);

							MemberValuePair prpValPair = new MemberValuePair("propertyValue", pair.getValue());
							newPairs.add(prpValPair);

							NormalAnnotationExpr actCfgAnnot = new NormalAnnotationExpr(
									new Name("ActivationConfigProperty"), newPairs);

							actCfgAnnots.add(actCfgAnnot);
						}
					}
				}, null);

				MemberValuePair actCfgPair = new MemberValuePair();
				actCfgPair.setName("activationConfig");
				actCfgPair.setValue(new ArrayInitializerExpr(actCfgAnnots));
				messDrvnAnnot.setPairs(new NodeList<>(actCfgPair));

				break; // Break once detected

			}
		}
	}

}
