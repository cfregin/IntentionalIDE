package de.intentionalide.codegenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jruby.embed.ScriptingContainer;

import de.intentionalide.code.IntentionalClass;
import de.intentionalide.code.IntentionalProgram;

public class ObjectiveCGenerator {
	private IntentionalProgram program;
	public ObjectiveCGenerator(IntentionalProgram program){
		this.program = program;
	}
	public String getDemoString(){
		return "Hullu world";
	}
	public void generateClass(String className) throws IOException{
		ScriptingContainer container = new ScriptingContainer();
		List<String> loadPaths = new ArrayList<String>();
		loadPaths.add("jruby");
		container.getProvider().setLoadPaths(loadPaths);
		//String source = readFile("ErbRunner");
		
		container.runScriptlet("require 'ErbRunner'\n@myerb=ErbRunner.new\nputs @myerb.get_version\n");
		container.runScriptlet("puts 'start'");
		/*container.put("@myproxy", this);
		container.runScriptlet(source);
		container.runScriptlet("@myerb=ErbRunner.new");
		container.runScriptlet("puts @myerb.get_version");
		
		container.runScriptlet("puts @myproxy.getDemoString");*/
		IntentionalClass theClass = program.findClass(className);
		FileWriter headerFile = new FileWriter("output/"+ className+".h");
		FileWriter implementationFile = new FileWriter("output/"+ className+".m");
		StringBuffer header = new StringBuffer();
		StringBuffer implementation = new StringBuffer();
		header.append("@interface ");
		header.append(theClass);
		header.append(": NSObject {");
		header.append('\n');
		
		
		header.append("}");
		header.append("@end");
		header.append('\n');
		
		implementation.append("@implementation ");
		implementation.append(className);
		implementation.append('\n');
		implementation.append("@end");
		implementation.append('\n');
		
		headerFile.write(header.toString());
		implementationFile.write(implementation.toString());
		
		headerFile.close();
		implementationFile.close();
		
	}
	
	private String readFile(String className) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("jruby/" + className + ".rb"));
		String line=null;
		StringBuffer source = new StringBuffer();
		while((line=reader.readLine())!=null){
			source.append(line);
			source.append('\n');
		}
		return source.toString();
	}
}
