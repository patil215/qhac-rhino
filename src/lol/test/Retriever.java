package lol.test;

import java.io.IOException;
import java.util.Scanner;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.shell.Global;
import org.mozilla.javascript.tools.shell.Main;


public class Retriever {
	
	final Global global;
	final Context context;
	final Scriptable scope;
	
	final String district;
	final String username;
	final String password;
	final String studentId;

	public Retriever() {

		context = ContextFactory.getGlobal().enterContext();
		context.setOptimizationLevel(-1);
		context.setLanguageVersion(Context.VERSION_1_7);
		global = new Global(context);
		scope = global;
		
		// initialize objects
		try {
			Main.processFile(context, global, "env.js");
			Main.processFile(context, global, "sizzle.js");
			Main.processFile(context, global, "sha1.js");
			Main.processFile(context, global, "qhac.js");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// check that Sizzle is loaded
		final Object sizzle = global.get("Sizzle", global);
		if (sizzle == Scriptable.NOT_FOUND) {
			System.err.println("Sizzle is not in scope.");
		} else {
			System.out.println("Sizzle loaded.");
		}
		
		// process credentials
		final Scanner s = new Scanner(System.in);
		System.out.print("District (roundrock/austin): ");
		district = sanitize(s.nextLine());
		System.out.print("Username: ");
		username = sanitize(s.nextLine());
		System.out.print("Password: ");
		password = sanitize(s.nextLine());
		System.out.print("ID: ");
		studentId = sanitize(s.nextLine());
	}
	
	public void login() {
		String loginCommand = "GradeRetriever.login("
				+ "Districts." + district + ","
				+ "'" + username + "',"
				+ "'" + password + "',"
				+ "'" + studentId + "',"
				+ "function() { GradeRetriever.getAverages("
					+ "Districts." + district + ","
					+ "function(doc) { console.log(JSON.stringify(GradeParser.parseAverages("
					+ "Districts." + district + ","
					+ "doc"
				+ ")))})});";
		System.out.println("Evaluating: " + loginCommand);
		context.evaluateString(scope, loginCommand, "<cmd>", 0, null);
	}
	
	/** Sanitizes input so that it is (relatively) safe to pass to JavaScript. */
	static String sanitize(String str) {
		return str.replace("'", "\'").replace("\n", "");
	}
	
	public static abstract class SimpleFunction implements Function {

		@Override
		public void delete(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delete(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object get(String arg0, Scriptable arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object get(int arg0, Scriptable arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getClassName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getDefaultValue(Class<?> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object[] getIds() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Scriptable getParentScope() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Scriptable getPrototype() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean has(String arg0, Scriptable arg1) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean has(int arg0, Scriptable arg1) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean hasInstance(Scriptable arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void put(String arg0, Scriptable arg1, Object arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void put(int arg0, Scriptable arg1, Object arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setParentScope(Scriptable arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setPrototype(Scriptable arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Scriptable construct(Context arg0, Scriptable arg1, Object[] arg2) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
