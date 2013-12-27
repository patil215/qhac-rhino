import java.io.IOException;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.shell.Global;
import org.mozilla.javascript.tools.shell.Main;

public class Runner {
	public static void main(String args[]) throws IOException {

		Global global = Main.getGlobal();
		Context context = ContextFactory.getGlobal().enterContext();
		context.setOptimizationLevel(-1);
		context.setLanguageVersion(Context.VERSION_1_8);
		global.init(context);
		Main.processFile(context, global, "env.js");
		Main.processFile(context, global, "sha1.js");
		Main.processFile(context, global, "qhac.js");
		

		Object district = global.get("Districts.austin", global);
		if (district == Scriptable.NOT_FOUND) {
			System.out.println("NOT FOUND");
		} else {
			System.out.println(Context.toString(district));
		}
	}

}
