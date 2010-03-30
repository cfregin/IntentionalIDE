import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

public class Main {
	public static void main(String[] args) throws Exception {
		
		CharStream input = new ANTLRFileStream(args[0]);
		JavaScriptLexer lex = new JavaScriptLexer(input);
		TokenStream tokens = new TokenRewriteStream(lex);
		//System.out.println("before, tokens="+tokens);
		JavaScriptParser parser = new JavaScriptParser(tokens);
		parser.program();
		System.out.println(tokens);
	}
}
