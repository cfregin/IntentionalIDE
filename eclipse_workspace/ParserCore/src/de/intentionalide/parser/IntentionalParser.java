package de.intentionalide.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.antlr.runtime.RecognitionException;

import de.intentionalide.code.IntentionalClass;
import de.intentionalide.code.IntentionalProgram;
import de.intentionalide.codegenerator.ObjectiveCGenerator;
public class IntentionalParser {
	public static void main(String[] args) throws IOException, RecognitionException {
		System.out.println(new File(".").getAbsolutePath());
		String javacode = IntentionalParser.readFile("src/demo/Demo.java");
		IntentionalProgram program = new IntentionalProgram();
		
		IntentionalJavaParser parser = new IntentionalJavaParser(program);
		parser.parseClassFile(javacode, "src/demo/Demo.java");
		for(IntentionalClass theClass: program.getClasses()){
			log("Found class:" + theClass.getClassName());
			log("With impoers:" + theClass.getImports());
			log("With methods:" + theClass.getMethods());
		}
		
		ObjectiveCGenerator generator = new ObjectiveCGenerator(program);
		generator.generateClass("Demo");
	}
	private static String readFile(String path) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
		String line = null;
		StringBuffer codeBuffer=new StringBuffer();
		while((line = reader.readLine())!=null){
			codeBuffer.append(line);
			codeBuffer.append('\n');
		}
		return codeBuffer.toString();
	}
	
	private static void log(Object message){
		System.out.println(message + "\n");
	}
}
