package rnd.tool.code.mig.impl.oss;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import rnd.tool.code.mig.core.utils.MigrationHelper;

public class WebsphereTest {

	@Test
	public void testJms() throws IOException {

		Path inPath = Paths.get("../samples/websphere/tests/data");
		Path outPath = Paths.get("../samples/websphere/gen/data");

		String className = "WebSphereJMSExample";

		WsJms2JeeJms mig = new WsJms2JeeJms();

		MigrationHelper.migrate(mig, inPath, outPath, className);
	}
}
