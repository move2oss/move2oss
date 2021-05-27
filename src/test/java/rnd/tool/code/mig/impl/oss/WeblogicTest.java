package rnd.tool.code.mig.impl.oss;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import rnd.tool.code.mig.core.utils.MigrationHelper;

public class WeblogicTest {

	@Test
	public void testJms() throws IOException {

		Path inPath = Paths.get("../samples/weblogic/src/jms");
		Path outPath = Paths.get("../samples/weblogic/gen/jms");

		WlJms2JeeJms mig = new WlJms2JeeJms();

		MigrationHelper.migrate(mig, inPath, outPath, "WebLogicJMSTests");

	}

	@Test
	public void testServlet() throws IOException {

		Path inPath = Paths.get("../samples/weblogic/src/webapp");
		Path outPath = Paths.get("../samples/weblogic/gen/webapp");

		WlServlet2JeeServlet mig = new WlServlet2JeeServlet();

		MigrationHelper.migrate(mig, inPath, outPath, "SampleWebLogicServlet");
		MigrationHelper.migrate(mig, inPath, outPath, "SampleWebLogicFilter");

	}

	@Test
	public void testLifecycle() throws IOException {

		Path inPath = Paths.get("../samples/weblogic/src/webapp");
		Path outPath = Paths.get("../samples/weblogic/gen/webapp");

		WlLifecycle2JeeServletContext mig = new WlLifecycle2JeeServletContext();

		MigrationHelper.migrate(mig, inPath, outPath, "SampleWebLogicApplicationLifecycleListener");

	}

	@Test
	public void testMDB() throws IOException {

		Path inPath = Paths.get("../samples/weblogic/src/ejb/mdb/");
		Path outPath = Paths.get("../samples/weblogic/gen/ejb/mdb");

		WlMDB2JeeMDB mig = new WlMDB2JeeMDB();

		MigrationHelper.migrate(mig, inPath, outPath, "LogEventSubscriber");

	}

	@Test
	public void testSB() throws IOException {

		Path inPath = Paths.get("../samples/weblogic/src/ejb/sb");
		Path outPath = Paths.get("../samples/weblogic/gen/ejb/sb");

		WlSB2JeeSB mig = new WlSB2JeeSB();

		MigrationHelper.migrate(mig, inPath, outPath, "ItemLookupBean");

	}

}
