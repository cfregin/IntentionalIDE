// $ANTLR 3.2 Sep 23, 2009 12:02:23 /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g 2010-03-31 21:59:58

package de.intentionalide.parser.java;
import de.intentionalide.code.IntentionalClass;
import de.intentionalide.code.IntentionalImport;
import de.intentionalide.code.IntentionalMethod;
import de.intentionalide.code.IntentionalProgram;
import java.util.Stack;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

/** A Java 1.5 grammar for ANTLR v3 derived from the spec
 *
 *  This is a very close representation of the spec; the changes
 *  are comestic (remove left recursion) and also fixes (the spec
 *  isn't exactly perfect).  I have run this on the 1.4.2 source
 *  and some nasty looking enums from 1.5, but have not really
 *  tested for 1.5 compatibility.
 *
 *  I built this with: java -Xmx100M org.antlr.Tool java.g
 *  and got two errors that are ok (for now):
 *  java.g:691:9: Decision can match input such as
 *    "'0'..'9'{'E', 'e'}{'+', '-'}'0'..'9'{'D', 'F', 'd', 'f'}"
 *    using multiple alternatives: 3, 4
 *  As a result, alternative(s) 4 were disabled for that input
 *  java.g:734:35: Decision can match input such as "{'$', 'A'..'Z',
 *    '_', 'a'..'z', '\u00C0'..'\u00D6', '\u00D8'..'\u00F6',
 *    '\u00F8'..'\u1FFF', '\u3040'..'\u318F', '\u3300'..'\u337F',
 *    '\u3400'..'\u3D2D', '\u4E00'..'\u9FFF', '\uF900'..'\uFAFF'}"
 *    using multiple alternatives: 1, 2
 *  As a result, alternative(s) 2 were disabled for that input
 *
 *  You can turn enum on/off as a keyword :)
 *
 *  Version 1.0 -- initial release July 5, 2006 (requires 3.0b2 or higher)
 *
 *  Primary author: Terence Parr, July 2006
 *
 *  Version 1.0.1 -- corrections by Koen Vanderkimpen & Marko van Dooren,
 *      October 25, 2006;
 *      fixed normalInterfaceDeclaration: now uses typeParameters instead
 *          of typeParameter (according to JLS, 3rd edition)
 *      fixed castExpression: no longer allows expression next to type
 *          (according to semantics in JLS, in contrast with syntax in JLS)
 *
 *  Version 1.0.2 -- Terence Parr, Nov 27, 2006
 *      java spec I built this from had some bizarre for-loop control.
 *          Looked weird and so I looked elsewhere...Yep, it's messed up.
 *          simplified.
 *
 *  Version 1.0.3 -- Chris Hogue, Feb 26, 2007
 *      Factored out an annotationName rule and used it in the annotation rule.
 *          Not sure why, but typeName wasn't recognizing references to inner
 *          annotations (e.g. @InterfaceName.InnerAnnotation())
 *      Factored out the elementValue section of an annotation reference.  Created
 *          elementValuePair and elementValuePairs rules, then used them in the
 *          annotation rule.  Allows it to recognize annotation references with
 *          multiple, comma separated attributes.
 *      Updated elementValueArrayInitializer so that it allows multiple elements.
 *          (It was only allowing 0 or 1 element).
 *      Updated localVariableDeclaration to allow annotations.  Interestingly the JLS
 *          doesn't appear to indicate this is legal, but it does work as of at least
 *          JDK 1.5.0_06.
 *      Moved the Identifier portion of annotationTypeElementRest to annotationMethodRest.
 *          Because annotationConstantRest already references variableDeclarator which
 *          has the Identifier portion in it, the parser would fail on constants in
 *          annotation definitions because it expected two identifiers.
 *      Added optional trailing ';' to the alternatives in annotationTypeElementRest.
 *          Wouldn't handle an inner interface that has a trailing ';'.
 *      Swapped the expression and type rule reference order in castExpression to
 *          make it check for genericized casts first.  It was failing to recognize a
 *          statement like  "Class<Byte> TYPE = (Class<Byte>)...;" because it was seeing
 *          'Class<Byte' in the cast expression as a less than expression, then failing
 *          on the '>'.
 *      Changed createdName to use typeArguments instead of nonWildcardTypeArguments.
 *         
 *      Changed the 'this' alternative in primary to allow 'identifierSuffix' rather than
 *          just 'arguments'.  The case it couldn't handle was a call to an explicit
 *          generic method invocation (e.g. this.<E>doSomething()).  Using identifierSuffix
 *          may be overly aggressive--perhaps should create a more constrained thisSuffix rule?
 *
 *  Version 1.0.4 -- Hiroaki Nakamura, May 3, 2007
 *
 *  Fixed formalParameterDecls, localVariableDeclaration, forInit,
 *  and forVarControl to use variableModifier* not 'final'? (annotation)?
 *
 *  Version 1.0.5 -- Terence, June 21, 2007
 *  --a[i].foo didn't work. Fixed unaryExpression
 *
 *  Version 1.0.6 -- John Ridgway, March 17, 2008
 *      Made "assert" a switchable keyword like "enum".
 *      Fixed compilationUnit to disallow "annotation importDeclaration ...".
 *      Changed "Identifier ('.' Identifier)*" to "qualifiedName" in more
 *          places.
 *      Changed modifier* and/or variableModifier* to classOrInterfaceModifiers,
 *          modifiers or variableModifiers, as appropriate.
 *      Renamed "bound" to "typeBound" to better match language in the JLS.
 *      Added "memberDeclaration" which rewrites to methodDeclaration or
 *      fieldDeclaration and pulled type into memberDeclaration.  So we parse
 *          type and then move on to decide whether we're dealing with a field
 *          or a method.
 *      Modified "constructorDeclaration" to use "constructorBody" instead of
 *          "methodBody".  constructorBody starts with explicitConstructorInvocation,
 *          then goes on to blockStatement*.  Pulling explicitConstructorInvocation
 *          out of expressions allowed me to simplify "primary".
 *      Changed variableDeclarator to simplify it.
 *      Changed type to use classOrInterfaceType, thus simplifying it; of course
 *          I then had to add classOrInterfaceType, but it is used in several
 *          places.
 *      Fixed annotations, old version allowed "@X(y,z)", which is illegal.
 *      Added optional comma to end of "elementValueArrayInitializer"; as per JLS.
 *      Changed annotationTypeElementRest to use normalClassDeclaration and
 *          normalInterfaceDeclaration rather than classDeclaration and
 *          interfaceDeclaration, thus getting rid of a couple of grammar ambiguities.
 *      Split localVariableDeclaration into localVariableDeclarationStatement
 *          (includes the terminating semi-colon) and localVariableDeclaration.
 *          This allowed me to use localVariableDeclaration in "forInit" clauses,
 *           simplifying them.
 *      Changed switchBlockStatementGroup to use multiple labels.  This adds an
 *          ambiguity, but if one uses appropriately greedy parsing it yields the
 *           parse that is closest to the meaning of the switch statement.
 *      Renamed "forVarControl" to "enhancedForControl" -- JLS language.
 *      Added semantic predicates to test for shift operations rather than other
 *          things.  Thus, for instance, the string "< <" will never be treated
 *          as a left-shift operator.
 *      In "creator" we rule out "nonWildcardTypeArguments" on arrayCreation,
 *          which are illegal.
 *      Moved "nonWildcardTypeArguments into innerCreator.
 *      Removed 'super' superSuffix from explicitGenericInvocation, since that
 *          is only used in explicitConstructorInvocation at the beginning of a
 *           constructorBody.  (This is part of the simplification of expressions
 *           mentioned earlier.)
 *      Simplified primary (got rid of those things that are only used in
 *          explicitConstructorInvocation).
 *      Lexer -- removed "Exponent?" from FloatingPointLiteral choice 4, since it
 *          led to an ambiguity.
 *
 *      This grammar successfully parses every .java file in the JDK 1.5 source
 *          tree (excluding those whose file names include '-', which are not
 *          valid Java compilation units).
 *
 *  Known remaining problems:
 *      "Letter" and "JavaIDDigit" are wrong.  The actual specification of
 *      "Letter" should be "a character for which the method
 *      Character.isJavaIdentifierStart(int) returns true."  A "Java
 *      letter-or-digit is a character for which the method
 *      Character.isJavaIdentifierPart(int) returns true."
 */
public class JavaParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENTIFIER", "INTLITERAL", "LONGLITERAL", "FLOATLITERAL", "DOUBLELITERAL", "CHARLITERAL", "STRINGLITERAL", "TRUE", "FALSE", "NULL", "IntegerNumber", "LongSuffix", "HexPrefix", "HexDigit", "Exponent", "NonIntegerNumber", "FloatSuffix", "DoubleSuffix", "EscapeSequence", "WS", "COMMENT", "LINE_COMMENT", "ABSTRACT", "ASSERT", "BOOLEAN", "BREAK", "BYTE", "CASE", "CATCH", "CHAR", "CLASS", "CONST", "CONTINUE", "DEFAULT", "DO", "DOUBLE", "ELSE", "ENUM", "EXTENDS", "FINAL", "FINALLY", "FLOAT", "FOR", "GOTO", "IF", "IMPLEMENTS", "IMPORT", "INSTANCEOF", "INT", "INTERFACE", "LONG", "NATIVE", "NEW", "PACKAGE", "PRIVATE", "PROTECTED", "PUBLIC", "RETURN", "SHORT", "STATIC", "STRICTFP", "SUPER", "SWITCH", "SYNCHRONIZED", "THIS", "THROW", "THROWS", "TRANSIENT", "TRY", "VOID", "VOLATILE", "WHILE", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACKET", "RBRACKET", "SEMI", "COMMA", "DOT", "ELLIPSIS", "EQ", "BANG", "TILDE", "QUES", "COLON", "EQEQ", "AMPAMP", "BARBAR", "PLUSPLUS", "SUBSUB", "PLUS", "SUB", "STAR", "SLASH", "AMP", "BAR", "CARET", "PERCENT", "PLUSEQ", "SUBEQ", "STAREQ", "SLASHEQ", "AMPEQ", "BAREQ", "CARETEQ", "PERCENTEQ", "MONKEYS_AT", "BANGEQ", "GT", "LT", "IdentifierStart", "IdentifierPart", "SurrogateIdentifer"
    };
    public static final int PACKAGE=57;
    public static final int LT=115;
    public static final int STAR=98;
    public static final int WHILE=75;
    public static final int CONST=35;
    public static final int CASE=31;
    public static final int CHAR=33;
    public static final int NEW=56;
    public static final int DO=38;
    public static final int EOF=-1;
    public static final int BREAK=29;
    public static final int LBRACKET=80;
    public static final int FINAL=43;
    public static final int RPAREN=77;
    public static final int IMPORT=50;
    public static final int SUBSUB=95;
    public static final int STAREQ=106;
    public static final int FloatSuffix=20;
    public static final int NonIntegerNumber=19;
    public static final int CARET=102;
    public static final int RETURN=61;
    public static final int THIS=68;
    public static final int DOUBLE=39;
    public static final int MONKEYS_AT=112;
    public static final int BARBAR=93;
    public static final int VOID=73;
    public static final int SUPER=65;
    public static final int GOTO=47;
    public static final int EQ=86;
    public static final int AMPAMP=92;
    public static final int COMMENT=24;
    public static final int QUES=89;
    public static final int EQEQ=91;
    public static final int HexPrefix=16;
    public static final int RBRACE=79;
    public static final int LINE_COMMENT=25;
    public static final int PRIVATE=58;
    public static final int STATIC=63;
    public static final int SWITCH=66;
    public static final int NULL=13;
    public static final int ELSE=40;
    public static final int STRICTFP=64;
    public static final int DOUBLELITERAL=8;
    public static final int IdentifierStart=116;
    public static final int NATIVE=55;
    public static final int ELLIPSIS=85;
    public static final int THROWS=70;
    public static final int INT=52;
    public static final int SLASHEQ=107;
    public static final int INTLITERAL=5;
    public static final int ASSERT=27;
    public static final int TRY=72;
    public static final int LONGLITERAL=6;
    public static final int LongSuffix=15;
    public static final int WS=23;
    public static final int SurrogateIdentifer=118;
    public static final int CHARLITERAL=9;
    public static final int GT=114;
    public static final int CATCH=32;
    public static final int FALSE=12;
    public static final int EscapeSequence=22;
    public static final int THROW=69;
    public static final int PROTECTED=59;
    public static final int CLASS=34;
    public static final int BAREQ=109;
    public static final int IntegerNumber=14;
    public static final int AMP=100;
    public static final int PLUSPLUS=94;
    public static final int LBRACE=78;
    public static final int SUBEQ=105;
    public static final int Exponent=18;
    public static final int FOR=46;
    public static final int SUB=97;
    public static final int FLOAT=45;
    public static final int ABSTRACT=26;
    public static final int HexDigit=17;
    public static final int PLUSEQ=104;
    public static final int LPAREN=76;
    public static final int IF=48;
    public static final int SLASH=99;
    public static final int BOOLEAN=28;
    public static final int SYNCHRONIZED=67;
    public static final int IMPLEMENTS=49;
    public static final int CONTINUE=36;
    public static final int COMMA=83;
    public static final int AMPEQ=108;
    public static final int IDENTIFIER=4;
    public static final int TRANSIENT=71;
    public static final int TILDE=88;
    public static final int BANGEQ=113;
    public static final int PLUS=96;
    public static final int RBRACKET=81;
    public static final int DOT=84;
    public static final int IdentifierPart=117;
    public static final int BYTE=30;
    public static final int PERCENT=103;
    public static final int VOLATILE=74;
    public static final int DEFAULT=37;
    public static final int SHORT=62;
    public static final int BANG=87;
    public static final int INSTANCEOF=51;
    public static final int TRUE=11;
    public static final int SEMI=82;
    public static final int COLON=90;
    public static final int ENUM=41;
    public static final int PERCENTEQ=111;
    public static final int DoubleSuffix=21;
    public static final int FINALLY=44;
    public static final int STRINGLITERAL=10;
    public static final int CARETEQ=110;
    public static final int INTERFACE=53;
    public static final int LONG=54;
    public static final int EXTENDS=42;
    public static final int FLOATLITERAL=7;
    public static final int PUBLIC=60;
    public static final int BAR=101;

    // delegates
    // delegators


        public JavaParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public JavaParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            this.state.ruleMemo = new HashMap[381+1];
             
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return JavaParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g"; }



    Stack<IntentionalClass> classStack = new Stack<IntentionalClass>();
    private IntentionalProgram program=null;

    public void setIntentionalProgram(IntentionalProgram program){
      this.program = program;
    }
    public IntentionalProgram getIntentionalProgram(){
      return program;
    }


    public static class compilationUnit_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "compilationUnit"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:318:1: compilationUnit : ( ( annotations )? packageDeclaration )? ( importDeclaration )* ( typeDeclaration )* ;
    public final JavaParser.compilationUnit_return compilationUnit() throws RecognitionException {
        JavaParser.compilationUnit_return retval = new JavaParser.compilationUnit_return();
        retval.start = input.LT(1);
        int compilationUnit_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.annotations_return annotations1 = null;

        JavaParser.packageDeclaration_return packageDeclaration2 = null;

        JavaParser.importDeclaration_return importDeclaration3 = null;

        JavaParser.typeDeclaration_return typeDeclaration4 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:323:5: ( ( ( annotations )? packageDeclaration )? ( importDeclaration )* ( typeDeclaration )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:323:9: ( ( annotations )? packageDeclaration )? ( importDeclaration )* ( typeDeclaration )*
            {
            root_0 = (Object)adaptor.nil();

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:323:9: ( ( annotations )? packageDeclaration )?
            int alt2=2;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:323:13: ( annotations )? packageDeclaration
                    {
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:323:13: ( annotations )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==MONKEYS_AT) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:323:14: annotations
                            {
                            pushFollow(FOLLOW_annotations_in_compilationUnit115);
                            annotations1=annotations();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, annotations1.getTree());

                            }
                            break;

                    }

                    pushFollow(FOLLOW_packageDeclaration_in_compilationUnit144);
                    packageDeclaration2=packageDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, packageDeclaration2.getTree());

                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:327:9: ( importDeclaration )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==IMPORT) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:327:10: importDeclaration
            	    {
            	    pushFollow(FOLLOW_importDeclaration_in_compilationUnit166);
            	    importDeclaration3=importDeclaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, importDeclaration3.getTree());

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:329:9: ( typeDeclaration )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==IDENTIFIER||LA4_0==ABSTRACT||LA4_0==BOOLEAN||LA4_0==BYTE||(LA4_0>=CHAR && LA4_0<=CLASS)||LA4_0==DOUBLE||LA4_0==ENUM||LA4_0==FINAL||LA4_0==FLOAT||(LA4_0>=INT && LA4_0<=NATIVE)||(LA4_0>=PRIVATE && LA4_0<=PUBLIC)||(LA4_0>=SHORT && LA4_0<=STRICTFP)||LA4_0==SYNCHRONIZED||LA4_0==TRANSIENT||(LA4_0>=VOID && LA4_0<=VOLATILE)||LA4_0==SEMI||LA4_0==MONKEYS_AT||LA4_0==LT) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:329:10: typeDeclaration
            	    {
            	    pushFollow(FOLLOW_typeDeclaration_in_compilationUnit188);
            	    typeDeclaration4=typeDeclaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeDeclaration4.getTree());

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 1, compilationUnit_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "compilationUnit"

    public static class packageDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "packageDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:333:1: packageDeclaration : 'package' qualifiedName ';' ;
    public final JavaParser.packageDeclaration_return packageDeclaration() throws RecognitionException {
        JavaParser.packageDeclaration_return retval = new JavaParser.packageDeclaration_return();
        retval.start = input.LT(1);
        int packageDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal5=null;
        Token char_literal7=null;
        JavaParser.qualifiedName_return qualifiedName6 = null;


        Object string_literal5_tree=null;
        Object char_literal7_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:334:5: ( 'package' qualifiedName ';' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:334:9: 'package' qualifiedName ';'
            {
            root_0 = (Object)adaptor.nil();

            string_literal5=(Token)match(input,PACKAGE,FOLLOW_PACKAGE_in_packageDeclaration219); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal5_tree = (Object)adaptor.create(string_literal5);
            adaptor.addChild(root_0, string_literal5_tree);
            }
            pushFollow(FOLLOW_qualifiedName_in_packageDeclaration221);
            qualifiedName6=qualifiedName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedName6.getTree());
            char_literal7=(Token)match(input,SEMI,FOLLOW_SEMI_in_packageDeclaration231); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal7_tree = (Object)adaptor.create(char_literal7);
            adaptor.addChild(root_0, char_literal7_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 2, packageDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "packageDeclaration"

    public static class importDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "importDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:338:1: importDeclaration : ( 'import' ( 'static' )? IDENTIFIER '.' '*' ';' | 'import' ( 'static' )? IDENTIFIER ( '.' IDENTIFIER )+ ( '.' '*' )? ';' );
    public final JavaParser.importDeclaration_return importDeclaration() throws RecognitionException {
        JavaParser.importDeclaration_return retval = new JavaParser.importDeclaration_return();
        retval.start = input.LT(1);
        int importDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal8=null;
        Token string_literal9=null;
        Token IDENTIFIER10=null;
        Token char_literal11=null;
        Token char_literal12=null;
        Token char_literal13=null;
        Token string_literal14=null;
        Token string_literal15=null;
        Token IDENTIFIER16=null;
        Token char_literal17=null;
        Token IDENTIFIER18=null;
        Token char_literal19=null;
        Token char_literal20=null;
        Token char_literal21=null;

        Object string_literal8_tree=null;
        Object string_literal9_tree=null;
        Object IDENTIFIER10_tree=null;
        Object char_literal11_tree=null;
        Object char_literal12_tree=null;
        Object char_literal13_tree=null;
        Object string_literal14_tree=null;
        Object string_literal15_tree=null;
        Object IDENTIFIER16_tree=null;
        Object char_literal17_tree=null;
        Object IDENTIFIER18_tree=null;
        Object char_literal19_tree=null;
        Object char_literal20_tree=null;
        Object char_literal21_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:339:5: ( 'import' ( 'static' )? IDENTIFIER '.' '*' ';' | 'import' ( 'static' )? IDENTIFIER ( '.' IDENTIFIER )+ ( '.' '*' )? ';' )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==IMPORT) ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==STATIC) ) {
                    int LA9_2 = input.LA(3);

                    if ( (LA9_2==IDENTIFIER) ) {
                        int LA9_3 = input.LA(4);

                        if ( (LA9_3==DOT) ) {
                            int LA9_4 = input.LA(5);

                            if ( (LA9_4==STAR) ) {
                                alt9=1;
                            }
                            else if ( (LA9_4==IDENTIFIER) ) {
                                alt9=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 9, 4, input);

                                throw nvae;
                            }
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 9, 3, input);

                            throw nvae;
                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 9, 2, input);

                        throw nvae;
                    }
                }
                else if ( (LA9_1==IDENTIFIER) ) {
                    int LA9_3 = input.LA(3);

                    if ( (LA9_3==DOT) ) {
                        int LA9_4 = input.LA(4);

                        if ( (LA9_4==STAR) ) {
                            alt9=1;
                        }
                        else if ( (LA9_4==IDENTIFIER) ) {
                            alt9=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 9, 4, input);

                            throw nvae;
                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 9, 3, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:339:9: 'import' ( 'static' )? IDENTIFIER '.' '*' ';'
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal8=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_importDeclaration252); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal8_tree = (Object)adaptor.create(string_literal8);
                    adaptor.addChild(root_0, string_literal8_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:340:9: ( 'static' )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==STATIC) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:340:10: 'static'
                            {
                            string_literal9=(Token)match(input,STATIC,FOLLOW_STATIC_in_importDeclaration264); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            string_literal9_tree = (Object)adaptor.create(string_literal9);
                            adaptor.addChild(root_0, string_literal9_tree);
                            }

                            }
                            break;

                    }

                    IDENTIFIER10=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_importDeclaration285); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER10_tree = (Object)adaptor.create(IDENTIFIER10);
                    adaptor.addChild(root_0, IDENTIFIER10_tree);
                    }
                    char_literal11=(Token)match(input,DOT,FOLLOW_DOT_in_importDeclaration287); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal11_tree = (Object)adaptor.create(char_literal11);
                    adaptor.addChild(root_0, char_literal11_tree);
                    }
                    char_literal12=(Token)match(input,STAR,FOLLOW_STAR_in_importDeclaration289); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal12_tree = (Object)adaptor.create(char_literal12);
                    adaptor.addChild(root_0, char_literal12_tree);
                    }
                    char_literal13=(Token)match(input,SEMI,FOLLOW_SEMI_in_importDeclaration299); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal13_tree = (Object)adaptor.create(char_literal13);
                    adaptor.addChild(root_0, char_literal13_tree);
                    }

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:344:9: 'import' ( 'static' )? IDENTIFIER ( '.' IDENTIFIER )+ ( '.' '*' )? ';'
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal14=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_importDeclaration316); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal14_tree = (Object)adaptor.create(string_literal14);
                    adaptor.addChild(root_0, string_literal14_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:345:9: ( 'static' )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==STATIC) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:345:10: 'static'
                            {
                            string_literal15=(Token)match(input,STATIC,FOLLOW_STATIC_in_importDeclaration328); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            string_literal15_tree = (Object)adaptor.create(string_literal15);
                            adaptor.addChild(root_0, string_literal15_tree);
                            }

                            }
                            break;

                    }

                    IDENTIFIER16=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_importDeclaration349); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER16_tree = (Object)adaptor.create(IDENTIFIER16);
                    adaptor.addChild(root_0, IDENTIFIER16_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:348:9: ( '.' IDENTIFIER )+
                    int cnt7=0;
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==DOT) ) {
                            int LA7_1 = input.LA(2);

                            if ( (LA7_1==IDENTIFIER) ) {
                                alt7=1;
                            }


                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:348:10: '.' IDENTIFIER
                    	    {
                    	    char_literal17=(Token)match(input,DOT,FOLLOW_DOT_in_importDeclaration360); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal17_tree = (Object)adaptor.create(char_literal17);
                    	    adaptor.addChild(root_0, char_literal17_tree);
                    	    }
                    	    IDENTIFIER18=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_importDeclaration362); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    IDENTIFIER18_tree = (Object)adaptor.create(IDENTIFIER18);
                    	    adaptor.addChild(root_0, IDENTIFIER18_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt7 >= 1 ) break loop7;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(7, input);
                                throw eee;
                        }
                        cnt7++;
                    } while (true);

                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:350:9: ( '.' '*' )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==DOT) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:350:10: '.' '*'
                            {
                            char_literal19=(Token)match(input,DOT,FOLLOW_DOT_in_importDeclaration384); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal19_tree = (Object)adaptor.create(char_literal19);
                            adaptor.addChild(root_0, char_literal19_tree);
                            }
                            char_literal20=(Token)match(input,STAR,FOLLOW_STAR_in_importDeclaration386); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal20_tree = (Object)adaptor.create(char_literal20);
                            adaptor.addChild(root_0, char_literal20_tree);
                            }

                            }
                            break;

                    }

                    char_literal21=(Token)match(input,SEMI,FOLLOW_SEMI_in_importDeclaration407); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal21_tree = (Object)adaptor.create(char_literal21);
                    adaptor.addChild(root_0, char_literal21_tree);
                    }
                    if ( state.backtracking==0 ) {

                      IntentionalImport newImport = new IntentionalImport();
                      newImport.setPath("new Import");  
                      classStack.peek().addImport(newImport);
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 3, importDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "importDeclaration"

    public static class qualifiedImportName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "qualifiedImportName"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:360:1: qualifiedImportName : IDENTIFIER ( '.' IDENTIFIER )* ;
    public final JavaParser.qualifiedImportName_return qualifiedImportName() throws RecognitionException {
        JavaParser.qualifiedImportName_return retval = new JavaParser.qualifiedImportName_return();
        retval.start = input.LT(1);
        int qualifiedImportName_StartIndex = input.index();
        Object root_0 = null;

        Token IDENTIFIER22=null;
        Token char_literal23=null;
        Token IDENTIFIER24=null;

        Object IDENTIFIER22_tree=null;
        Object char_literal23_tree=null;
        Object IDENTIFIER24_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:361:5: ( IDENTIFIER ( '.' IDENTIFIER )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:361:9: IDENTIFIER ( '.' IDENTIFIER )*
            {
            root_0 = (Object)adaptor.nil();

            IDENTIFIER22=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_qualifiedImportName437); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER22_tree = (Object)adaptor.create(IDENTIFIER22);
            adaptor.addChild(root_0, IDENTIFIER22_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:362:9: ( '.' IDENTIFIER )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==DOT) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:362:10: '.' IDENTIFIER
            	    {
            	    char_literal23=(Token)match(input,DOT,FOLLOW_DOT_in_qualifiedImportName448); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal23_tree = (Object)adaptor.create(char_literal23);
            	    adaptor.addChild(root_0, char_literal23_tree);
            	    }
            	    IDENTIFIER24=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_qualifiedImportName450); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    IDENTIFIER24_tree = (Object)adaptor.create(IDENTIFIER24);
            	    adaptor.addChild(root_0, IDENTIFIER24_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 4, qualifiedImportName_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "qualifiedImportName"

    public static class typeDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typeDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:366:1: typeDeclaration : ( classOrInterfaceDeclaration | ';' );
    public final JavaParser.typeDeclaration_return typeDeclaration() throws RecognitionException {
        JavaParser.typeDeclaration_return retval = new JavaParser.typeDeclaration_return();
        retval.start = input.LT(1);
        int typeDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal26=null;
        JavaParser.classOrInterfaceDeclaration_return classOrInterfaceDeclaration25 = null;


        Object char_literal26_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:367:5: ( classOrInterfaceDeclaration | ';' )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==IDENTIFIER||LA11_0==ABSTRACT||LA11_0==BOOLEAN||LA11_0==BYTE||(LA11_0>=CHAR && LA11_0<=CLASS)||LA11_0==DOUBLE||LA11_0==ENUM||LA11_0==FINAL||LA11_0==FLOAT||(LA11_0>=INT && LA11_0<=NATIVE)||(LA11_0>=PRIVATE && LA11_0<=PUBLIC)||(LA11_0>=SHORT && LA11_0<=STRICTFP)||LA11_0==SYNCHRONIZED||LA11_0==TRANSIENT||(LA11_0>=VOID && LA11_0<=VOLATILE)||LA11_0==MONKEYS_AT||LA11_0==LT) ) {
                alt11=1;
            }
            else if ( (LA11_0==SEMI) ) {
                alt11=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:367:9: classOrInterfaceDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_classOrInterfaceDeclaration_in_typeDeclaration481);
                    classOrInterfaceDeclaration25=classOrInterfaceDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classOrInterfaceDeclaration25.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:368:9: ';'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal26=(Token)match(input,SEMI,FOLLOW_SEMI_in_typeDeclaration491); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal26_tree = (Object)adaptor.create(char_literal26);
                    adaptor.addChild(root_0, char_literal26_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 5, typeDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "typeDeclaration"

    public static class classOrInterfaceDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "classOrInterfaceDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:371:1: classOrInterfaceDeclaration : ( classDeclaration | interfaceDeclaration );
    public final JavaParser.classOrInterfaceDeclaration_return classOrInterfaceDeclaration() throws RecognitionException {
        JavaParser.classOrInterfaceDeclaration_return retval = new JavaParser.classOrInterfaceDeclaration_return();
        retval.start = input.LT(1);
        int classOrInterfaceDeclaration_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.classDeclaration_return classDeclaration27 = null;

        JavaParser.interfaceDeclaration_return interfaceDeclaration28 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:372:5: ( classDeclaration | interfaceDeclaration )
            int alt12=2;
            alt12 = dfa12.predict(input);
            switch (alt12) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:372:10: classDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_classDeclaration_in_classOrInterfaceDeclaration512);
                    classDeclaration27=classDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classDeclaration27.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:373:9: interfaceDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_interfaceDeclaration_in_classOrInterfaceDeclaration522);
                    interfaceDeclaration28=interfaceDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceDeclaration28.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 6, classOrInterfaceDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "classOrInterfaceDeclaration"

    public static class modifiers_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "modifiers"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:377:1: modifiers : ( annotation | 'public' | 'protected' | 'private' | 'static' | 'abstract' | 'final' | 'native' | 'synchronized' | 'transient' | 'volatile' | 'strictfp' )* ;
    public final JavaParser.modifiers_return modifiers() throws RecognitionException {
        JavaParser.modifiers_return retval = new JavaParser.modifiers_return();
        retval.start = input.LT(1);
        int modifiers_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal30=null;
        Token string_literal31=null;
        Token string_literal32=null;
        Token string_literal33=null;
        Token string_literal34=null;
        Token string_literal35=null;
        Token string_literal36=null;
        Token string_literal37=null;
        Token string_literal38=null;
        Token string_literal39=null;
        Token string_literal40=null;
        JavaParser.annotation_return annotation29 = null;


        Object string_literal30_tree=null;
        Object string_literal31_tree=null;
        Object string_literal32_tree=null;
        Object string_literal33_tree=null;
        Object string_literal34_tree=null;
        Object string_literal35_tree=null;
        Object string_literal36_tree=null;
        Object string_literal37_tree=null;
        Object string_literal38_tree=null;
        Object string_literal39_tree=null;
        Object string_literal40_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:378:5: ( ( annotation | 'public' | 'protected' | 'private' | 'static' | 'abstract' | 'final' | 'native' | 'synchronized' | 'transient' | 'volatile' | 'strictfp' )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:379:5: ( annotation | 'public' | 'protected' | 'private' | 'static' | 'abstract' | 'final' | 'native' | 'synchronized' | 'transient' | 'volatile' | 'strictfp' )*
            {
            root_0 = (Object)adaptor.nil();

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:379:5: ( annotation | 'public' | 'protected' | 'private' | 'static' | 'abstract' | 'final' | 'native' | 'synchronized' | 'transient' | 'volatile' | 'strictfp' )*
            loop13:
            do {
                int alt13=13;
                alt13 = dfa13.predict(input);
                switch (alt13) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:379:10: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_modifiers557);
            	    annotation29=annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotation29.getTree());

            	    }
            	    break;
            	case 2 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:380:9: 'public'
            	    {
            	    string_literal30=(Token)match(input,PUBLIC,FOLLOW_PUBLIC_in_modifiers567); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal30_tree = (Object)adaptor.create(string_literal30);
            	    adaptor.addChild(root_0, string_literal30_tree);
            	    }

            	    }
            	    break;
            	case 3 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:381:9: 'protected'
            	    {
            	    string_literal31=(Token)match(input,PROTECTED,FOLLOW_PROTECTED_in_modifiers577); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal31_tree = (Object)adaptor.create(string_literal31);
            	    adaptor.addChild(root_0, string_literal31_tree);
            	    }

            	    }
            	    break;
            	case 4 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:382:9: 'private'
            	    {
            	    string_literal32=(Token)match(input,PRIVATE,FOLLOW_PRIVATE_in_modifiers587); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal32_tree = (Object)adaptor.create(string_literal32);
            	    adaptor.addChild(root_0, string_literal32_tree);
            	    }

            	    }
            	    break;
            	case 5 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:383:9: 'static'
            	    {
            	    string_literal33=(Token)match(input,STATIC,FOLLOW_STATIC_in_modifiers597); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal33_tree = (Object)adaptor.create(string_literal33);
            	    adaptor.addChild(root_0, string_literal33_tree);
            	    }

            	    }
            	    break;
            	case 6 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:384:9: 'abstract'
            	    {
            	    string_literal34=(Token)match(input,ABSTRACT,FOLLOW_ABSTRACT_in_modifiers607); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal34_tree = (Object)adaptor.create(string_literal34);
            	    adaptor.addChild(root_0, string_literal34_tree);
            	    }

            	    }
            	    break;
            	case 7 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:385:9: 'final'
            	    {
            	    string_literal35=(Token)match(input,FINAL,FOLLOW_FINAL_in_modifiers617); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal35_tree = (Object)adaptor.create(string_literal35);
            	    adaptor.addChild(root_0, string_literal35_tree);
            	    }

            	    }
            	    break;
            	case 8 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:386:9: 'native'
            	    {
            	    string_literal36=(Token)match(input,NATIVE,FOLLOW_NATIVE_in_modifiers627); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal36_tree = (Object)adaptor.create(string_literal36);
            	    adaptor.addChild(root_0, string_literal36_tree);
            	    }

            	    }
            	    break;
            	case 9 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:387:9: 'synchronized'
            	    {
            	    string_literal37=(Token)match(input,SYNCHRONIZED,FOLLOW_SYNCHRONIZED_in_modifiers637); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal37_tree = (Object)adaptor.create(string_literal37);
            	    adaptor.addChild(root_0, string_literal37_tree);
            	    }

            	    }
            	    break;
            	case 10 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:388:9: 'transient'
            	    {
            	    string_literal38=(Token)match(input,TRANSIENT,FOLLOW_TRANSIENT_in_modifiers647); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal38_tree = (Object)adaptor.create(string_literal38);
            	    adaptor.addChild(root_0, string_literal38_tree);
            	    }

            	    }
            	    break;
            	case 11 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:389:9: 'volatile'
            	    {
            	    string_literal39=(Token)match(input,VOLATILE,FOLLOW_VOLATILE_in_modifiers657); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal39_tree = (Object)adaptor.create(string_literal39);
            	    adaptor.addChild(root_0, string_literal39_tree);
            	    }

            	    }
            	    break;
            	case 12 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:390:9: 'strictfp'
            	    {
            	    string_literal40=(Token)match(input,STRICTFP,FOLLOW_STRICTFP_in_modifiers667); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal40_tree = (Object)adaptor.create(string_literal40);
            	    adaptor.addChild(root_0, string_literal40_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 7, modifiers_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "modifiers"

    public static class variableModifiers_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "variableModifiers"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:395:1: variableModifiers : ( 'final' | annotation )* ;
    public final JavaParser.variableModifiers_return variableModifiers() throws RecognitionException {
        JavaParser.variableModifiers_return retval = new JavaParser.variableModifiers_return();
        retval.start = input.LT(1);
        int variableModifiers_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal41=null;
        JavaParser.annotation_return annotation42 = null;


        Object string_literal41_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:396:5: ( ( 'final' | annotation )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:396:9: ( 'final' | annotation )*
            {
            root_0 = (Object)adaptor.nil();

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:396:9: ( 'final' | annotation )*
            loop14:
            do {
                int alt14=3;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==FINAL) ) {
                    alt14=1;
                }
                else if ( (LA14_0==MONKEYS_AT) ) {
                    alt14=2;
                }


                switch (alt14) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:396:13: 'final'
            	    {
            	    string_literal41=(Token)match(input,FINAL,FOLLOW_FINAL_in_variableModifiers699); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal41_tree = (Object)adaptor.create(string_literal41);
            	    adaptor.addChild(root_0, string_literal41_tree);
            	    }

            	    }
            	    break;
            	case 2 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:397:13: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_variableModifiers713);
            	    annotation42=annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotation42.getTree());

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 8, variableModifiers_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "variableModifiers"

    public static class classDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "classDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:402:1: classDeclaration : ( normalClassDeclaration | enumDeclaration );
    public final JavaParser.classDeclaration_return classDeclaration() throws RecognitionException {
        JavaParser.classDeclaration_return retval = new JavaParser.classDeclaration_return();
        retval.start = input.LT(1);
        int classDeclaration_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.normalClassDeclaration_return normalClassDeclaration43 = null;

        JavaParser.enumDeclaration_return enumDeclaration44 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:403:5: ( normalClassDeclaration | enumDeclaration )
            int alt15=2;
            alt15 = dfa15.predict(input);
            switch (alt15) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:403:9: normalClassDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_normalClassDeclaration_in_classDeclaration749);
                    normalClassDeclaration43=normalClassDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, normalClassDeclaration43.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:404:9: enumDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_enumDeclaration_in_classDeclaration759);
                    enumDeclaration44=enumDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, enumDeclaration44.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 9, classDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "classDeclaration"

    public static class normalClassDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "normalClassDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:407:1: normalClassDeclaration : modifiers 'class' IDENTIFIER ( typeParameters )? ( 'extends' type )? ( 'implements' typeList )? classBody ;
    public final JavaParser.normalClassDeclaration_return normalClassDeclaration() throws RecognitionException {
        JavaParser.normalClassDeclaration_return retval = new JavaParser.normalClassDeclaration_return();
        retval.start = input.LT(1);
        int normalClassDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal46=null;
        Token IDENTIFIER47=null;
        Token string_literal49=null;
        Token string_literal51=null;
        JavaParser.modifiers_return modifiers45 = null;

        JavaParser.typeParameters_return typeParameters48 = null;

        JavaParser.type_return type50 = null;

        JavaParser.typeList_return typeList52 = null;

        JavaParser.classBody_return classBody53 = null;


        Object string_literal46_tree=null;
        Object IDENTIFIER47_tree=null;
        Object string_literal49_tree=null;
        Object string_literal51_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:408:5: ( modifiers 'class' IDENTIFIER ( typeParameters )? ( 'extends' type )? ( 'implements' typeList )? classBody )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:408:9: modifiers 'class' IDENTIFIER ( typeParameters )? ( 'extends' type )? ( 'implements' typeList )? classBody
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_modifiers_in_normalClassDeclaration779);
            modifiers45=modifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers45.getTree());
            string_literal46=(Token)match(input,CLASS,FOLLOW_CLASS_in_normalClassDeclaration782); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal46_tree = (Object)adaptor.create(string_literal46);
            adaptor.addChild(root_0, string_literal46_tree);
            }
            IDENTIFIER47=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_normalClassDeclaration784); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER47_tree = (Object)adaptor.create(IDENTIFIER47);
            adaptor.addChild(root_0, IDENTIFIER47_tree);
            }
            if ( state.backtracking==0 ) {

                        IntentionalClass newClass = new IntentionalClass((IDENTIFIER47!=null?IDENTIFIER47.getText():null));
                        if(classStack.empty()==false){
                          classStack.peek().addPrivateClass(newClass);
                        }else{
                          program.addClass(newClass);
                        }
                        classStack.push(newClass);
                       
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:418:9: ( typeParameters )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==LT) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:418:10: typeParameters
                    {
                    pushFollow(FOLLOW_typeParameters_in_normalClassDeclaration805);
                    typeParameters48=typeParameters();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeParameters48.getTree());

                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:420:9: ( 'extends' type )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==EXTENDS) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:420:10: 'extends' type
                    {
                    string_literal49=(Token)match(input,EXTENDS,FOLLOW_EXTENDS_in_normalClassDeclaration827); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal49_tree = (Object)adaptor.create(string_literal49);
                    adaptor.addChild(root_0, string_literal49_tree);
                    }
                    pushFollow(FOLLOW_type_in_normalClassDeclaration829);
                    type50=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, type50.getTree());

                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:422:9: ( 'implements' typeList )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==IMPLEMENTS) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:422:10: 'implements' typeList
                    {
                    string_literal51=(Token)match(input,IMPLEMENTS,FOLLOW_IMPLEMENTS_in_normalClassDeclaration851); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal51_tree = (Object)adaptor.create(string_literal51);
                    adaptor.addChild(root_0, string_literal51_tree);
                    }
                    pushFollow(FOLLOW_typeList_in_normalClassDeclaration853);
                    typeList52=typeList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeList52.getTree());

                    }
                    break;

            }

            pushFollow(FOLLOW_classBody_in_normalClassDeclaration886);
            classBody53=classBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, classBody53.getTree());
            if ( state.backtracking==0 ) {

                      classStack.pop();
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 10, normalClassDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "normalClassDeclaration"

    public static class typeParameters_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typeParameters"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:431:1: typeParameters : '<' typeParameter ( ',' typeParameter )* '>' ;
    public final JavaParser.typeParameters_return typeParameters() throws RecognitionException {
        JavaParser.typeParameters_return retval = new JavaParser.typeParameters_return();
        retval.start = input.LT(1);
        int typeParameters_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal54=null;
        Token char_literal56=null;
        Token char_literal58=null;
        JavaParser.typeParameter_return typeParameter55 = null;

        JavaParser.typeParameter_return typeParameter57 = null;


        Object char_literal54_tree=null;
        Object char_literal56_tree=null;
        Object char_literal58_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:432:5: ( '<' typeParameter ( ',' typeParameter )* '>' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:432:9: '<' typeParameter ( ',' typeParameter )* '>'
            {
            root_0 = (Object)adaptor.nil();

            char_literal54=(Token)match(input,LT,FOLLOW_LT_in_typeParameters917); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal54_tree = (Object)adaptor.create(char_literal54);
            adaptor.addChild(root_0, char_literal54_tree);
            }
            pushFollow(FOLLOW_typeParameter_in_typeParameters931);
            typeParameter55=typeParameter();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeParameter55.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:434:13: ( ',' typeParameter )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==COMMA) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:434:14: ',' typeParameter
            	    {
            	    char_literal56=(Token)match(input,COMMA,FOLLOW_COMMA_in_typeParameters946); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal56_tree = (Object)adaptor.create(char_literal56);
            	    adaptor.addChild(root_0, char_literal56_tree);
            	    }
            	    pushFollow(FOLLOW_typeParameter_in_typeParameters948);
            	    typeParameter57=typeParameter();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeParameter57.getTree());

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            char_literal58=(Token)match(input,GT,FOLLOW_GT_in_typeParameters973); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal58_tree = (Object)adaptor.create(char_literal58);
            adaptor.addChild(root_0, char_literal58_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 11, typeParameters_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "typeParameters"

    public static class typeParameter_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typeParameter"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:439:1: typeParameter : IDENTIFIER ( 'extends' typeBound )? ;
    public final JavaParser.typeParameter_return typeParameter() throws RecognitionException {
        JavaParser.typeParameter_return retval = new JavaParser.typeParameter_return();
        retval.start = input.LT(1);
        int typeParameter_StartIndex = input.index();
        Object root_0 = null;

        Token IDENTIFIER59=null;
        Token string_literal60=null;
        JavaParser.typeBound_return typeBound61 = null;


        Object IDENTIFIER59_tree=null;
        Object string_literal60_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:440:5: ( IDENTIFIER ( 'extends' typeBound )? )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:440:9: IDENTIFIER ( 'extends' typeBound )?
            {
            root_0 = (Object)adaptor.nil();

            IDENTIFIER59=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_typeParameter993); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER59_tree = (Object)adaptor.create(IDENTIFIER59);
            adaptor.addChild(root_0, IDENTIFIER59_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:441:9: ( 'extends' typeBound )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==EXTENDS) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:441:10: 'extends' typeBound
                    {
                    string_literal60=(Token)match(input,EXTENDS,FOLLOW_EXTENDS_in_typeParameter1004); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal60_tree = (Object)adaptor.create(string_literal60);
                    adaptor.addChild(root_0, string_literal60_tree);
                    }
                    pushFollow(FOLLOW_typeBound_in_typeParameter1006);
                    typeBound61=typeBound();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeBound61.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 12, typeParameter_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "typeParameter"

    public static class typeBound_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typeBound"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:446:1: typeBound : type ( '&' type )* ;
    public final JavaParser.typeBound_return typeBound() throws RecognitionException {
        JavaParser.typeBound_return retval = new JavaParser.typeBound_return();
        retval.start = input.LT(1);
        int typeBound_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal63=null;
        JavaParser.type_return type62 = null;

        JavaParser.type_return type64 = null;


        Object char_literal63_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:447:5: ( type ( '&' type )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:447:9: type ( '&' type )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_type_in_typeBound1038);
            type62=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, type62.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:448:9: ( '&' type )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==AMP) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:448:10: '&' type
            	    {
            	    char_literal63=(Token)match(input,AMP,FOLLOW_AMP_in_typeBound1049); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal63_tree = (Object)adaptor.create(char_literal63);
            	    adaptor.addChild(root_0, char_literal63_tree);
            	    }
            	    pushFollow(FOLLOW_type_in_typeBound1051);
            	    type64=type();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, type64.getTree());

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 13, typeBound_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "typeBound"

    public static class enumDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "enumDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:453:1: enumDeclaration : modifiers ( 'enum' ) IDENTIFIER ( 'implements' typeList )? enumBody ;
    public final JavaParser.enumDeclaration_return enumDeclaration() throws RecognitionException {
        JavaParser.enumDeclaration_return retval = new JavaParser.enumDeclaration_return();
        retval.start = input.LT(1);
        int enumDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal66=null;
        Token IDENTIFIER67=null;
        Token string_literal68=null;
        JavaParser.modifiers_return modifiers65 = null;

        JavaParser.typeList_return typeList69 = null;

        JavaParser.enumBody_return enumBody70 = null;


        Object string_literal66_tree=null;
        Object IDENTIFIER67_tree=null;
        Object string_literal68_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:454:5: ( modifiers ( 'enum' ) IDENTIFIER ( 'implements' typeList )? enumBody )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:454:9: modifiers ( 'enum' ) IDENTIFIER ( 'implements' typeList )? enumBody
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_modifiers_in_enumDeclaration1083);
            modifiers65=modifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers65.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:455:9: ( 'enum' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:455:10: 'enum'
            {
            string_literal66=(Token)match(input,ENUM,FOLLOW_ENUM_in_enumDeclaration1095); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal66_tree = (Object)adaptor.create(string_literal66);
            adaptor.addChild(root_0, string_literal66_tree);
            }

            }

            IDENTIFIER67=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_enumDeclaration1116); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER67_tree = (Object)adaptor.create(IDENTIFIER67);
            adaptor.addChild(root_0, IDENTIFIER67_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:458:9: ( 'implements' typeList )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==IMPLEMENTS) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:458:10: 'implements' typeList
                    {
                    string_literal68=(Token)match(input,IMPLEMENTS,FOLLOW_IMPLEMENTS_in_enumDeclaration1127); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal68_tree = (Object)adaptor.create(string_literal68);
                    adaptor.addChild(root_0, string_literal68_tree);
                    }
                    pushFollow(FOLLOW_typeList_in_enumDeclaration1129);
                    typeList69=typeList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeList69.getTree());

                    }
                    break;

            }

            pushFollow(FOLLOW_enumBody_in_enumDeclaration1150);
            enumBody70=enumBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, enumBody70.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 14, enumDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "enumDeclaration"

    public static class enumBody_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "enumBody"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:464:1: enumBody : '{' ( enumConstants )? ( ',' )? ( enumBodyDeclarations )? '}' ;
    public final JavaParser.enumBody_return enumBody() throws RecognitionException {
        JavaParser.enumBody_return retval = new JavaParser.enumBody_return();
        retval.start = input.LT(1);
        int enumBody_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal71=null;
        Token char_literal73=null;
        Token char_literal75=null;
        JavaParser.enumConstants_return enumConstants72 = null;

        JavaParser.enumBodyDeclarations_return enumBodyDeclarations74 = null;


        Object char_literal71_tree=null;
        Object char_literal73_tree=null;
        Object char_literal75_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:465:5: ( '{' ( enumConstants )? ( ',' )? ( enumBodyDeclarations )? '}' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:465:9: '{' ( enumConstants )? ( ',' )? ( enumBodyDeclarations )? '}'
            {
            root_0 = (Object)adaptor.nil();

            char_literal71=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_enumBody1175); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal71_tree = (Object)adaptor.create(char_literal71);
            adaptor.addChild(root_0, char_literal71_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:466:9: ( enumConstants )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==IDENTIFIER||LA23_0==MONKEYS_AT) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:466:10: enumConstants
                    {
                    pushFollow(FOLLOW_enumConstants_in_enumBody1186);
                    enumConstants72=enumConstants();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, enumConstants72.getTree());

                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:468:9: ( ',' )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==COMMA) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:0:0: ','
                    {
                    char_literal73=(Token)match(input,COMMA,FOLLOW_COMMA_in_enumBody1208); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal73_tree = (Object)adaptor.create(char_literal73);
                    adaptor.addChild(root_0, char_literal73_tree);
                    }

                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:469:9: ( enumBodyDeclarations )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==SEMI) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:469:10: enumBodyDeclarations
                    {
                    pushFollow(FOLLOW_enumBodyDeclarations_in_enumBody1221);
                    enumBodyDeclarations74=enumBodyDeclarations();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, enumBodyDeclarations74.getTree());

                    }
                    break;

            }

            char_literal75=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_enumBody1243); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal75_tree = (Object)adaptor.create(char_literal75);
            adaptor.addChild(root_0, char_literal75_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 15, enumBody_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "enumBody"

    public static class enumConstants_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "enumConstants"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:474:1: enumConstants : enumConstant ( ',' enumConstant )* ;
    public final JavaParser.enumConstants_return enumConstants() throws RecognitionException {
        JavaParser.enumConstants_return retval = new JavaParser.enumConstants_return();
        retval.start = input.LT(1);
        int enumConstants_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal77=null;
        JavaParser.enumConstant_return enumConstant76 = null;

        JavaParser.enumConstant_return enumConstant78 = null;


        Object char_literal77_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:475:5: ( enumConstant ( ',' enumConstant )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:475:9: enumConstant ( ',' enumConstant )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_enumConstant_in_enumConstants1263);
            enumConstant76=enumConstant();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, enumConstant76.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:476:9: ( ',' enumConstant )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==COMMA) ) {
                    int LA26_1 = input.LA(2);

                    if ( (LA26_1==IDENTIFIER||LA26_1==MONKEYS_AT) ) {
                        alt26=1;
                    }


                }


                switch (alt26) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:476:10: ',' enumConstant
            	    {
            	    char_literal77=(Token)match(input,COMMA,FOLLOW_COMMA_in_enumConstants1274); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal77_tree = (Object)adaptor.create(char_literal77);
            	    adaptor.addChild(root_0, char_literal77_tree);
            	    }
            	    pushFollow(FOLLOW_enumConstant_in_enumConstants1276);
            	    enumConstant78=enumConstant();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, enumConstant78.getTree());

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 16, enumConstants_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "enumConstants"

    public static class enumConstant_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "enumConstant"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:480:1: enumConstant : ( annotations )? IDENTIFIER ( arguments )? ( classBody )? ;
    public final JavaParser.enumConstant_return enumConstant() throws RecognitionException {
        JavaParser.enumConstant_return retval = new JavaParser.enumConstant_return();
        retval.start = input.LT(1);
        int enumConstant_StartIndex = input.index();
        Object root_0 = null;

        Token IDENTIFIER80=null;
        JavaParser.annotations_return annotations79 = null;

        JavaParser.arguments_return arguments81 = null;

        JavaParser.classBody_return classBody82 = null;


        Object IDENTIFIER80_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:485:5: ( ( annotations )? IDENTIFIER ( arguments )? ( classBody )? )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:485:9: ( annotations )? IDENTIFIER ( arguments )? ( classBody )?
            {
            root_0 = (Object)adaptor.nil();

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:485:9: ( annotations )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==MONKEYS_AT) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:485:10: annotations
                    {
                    pushFollow(FOLLOW_annotations_in_enumConstant1310);
                    annotations79=annotations();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotations79.getTree());

                    }
                    break;

            }

            IDENTIFIER80=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_enumConstant1331); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER80_tree = (Object)adaptor.create(IDENTIFIER80);
            adaptor.addChild(root_0, IDENTIFIER80_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:488:9: ( arguments )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==LPAREN) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:488:10: arguments
                    {
                    pushFollow(FOLLOW_arguments_in_enumConstant1342);
                    arguments81=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments81.getTree());

                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:490:9: ( classBody )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==LBRACE) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:490:10: classBody
                    {
                    pushFollow(FOLLOW_classBody_in_enumConstant1364);
                    classBody82=classBody();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classBody82.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 17, enumConstant_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "enumConstant"

    public static class enumBodyDeclarations_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "enumBodyDeclarations"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:496:1: enumBodyDeclarations : ';' ( classBodyDeclaration )* ;
    public final JavaParser.enumBodyDeclarations_return enumBodyDeclarations() throws RecognitionException {
        JavaParser.enumBodyDeclarations_return retval = new JavaParser.enumBodyDeclarations_return();
        retval.start = input.LT(1);
        int enumBodyDeclarations_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal83=null;
        JavaParser.classBodyDeclaration_return classBodyDeclaration84 = null;


        Object char_literal83_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:497:5: ( ';' ( classBodyDeclaration )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:497:9: ';' ( classBodyDeclaration )*
            {
            root_0 = (Object)adaptor.nil();

            char_literal83=(Token)match(input,SEMI,FOLLOW_SEMI_in_enumBodyDeclarations1405); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal83_tree = (Object)adaptor.create(char_literal83);
            adaptor.addChild(root_0, char_literal83_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:498:9: ( classBodyDeclaration )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==IDENTIFIER||LA30_0==ABSTRACT||LA30_0==BOOLEAN||LA30_0==BYTE||(LA30_0>=CHAR && LA30_0<=CLASS)||LA30_0==DOUBLE||LA30_0==ENUM||LA30_0==FINAL||LA30_0==FLOAT||(LA30_0>=INT && LA30_0<=NATIVE)||(LA30_0>=PRIVATE && LA30_0<=PUBLIC)||(LA30_0>=SHORT && LA30_0<=STRICTFP)||LA30_0==SYNCHRONIZED||LA30_0==TRANSIENT||(LA30_0>=VOID && LA30_0<=VOLATILE)||LA30_0==LBRACE||LA30_0==SEMI||LA30_0==MONKEYS_AT||LA30_0==LT) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:498:10: classBodyDeclaration
            	    {
            	    pushFollow(FOLLOW_classBodyDeclaration_in_enumBodyDeclarations1417);
            	    classBodyDeclaration84=classBodyDeclaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, classBodyDeclaration84.getTree());

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 18, enumBodyDeclarations_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "enumBodyDeclarations"

    public static class interfaceDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "interfaceDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:502:1: interfaceDeclaration : ( normalInterfaceDeclaration | annotationTypeDeclaration );
    public final JavaParser.interfaceDeclaration_return interfaceDeclaration() throws RecognitionException {
        JavaParser.interfaceDeclaration_return retval = new JavaParser.interfaceDeclaration_return();
        retval.start = input.LT(1);
        int interfaceDeclaration_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.normalInterfaceDeclaration_return normalInterfaceDeclaration85 = null;

        JavaParser.annotationTypeDeclaration_return annotationTypeDeclaration86 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:503:5: ( normalInterfaceDeclaration | annotationTypeDeclaration )
            int alt31=2;
            alt31 = dfa31.predict(input);
            switch (alt31) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:503:9: normalInterfaceDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_normalInterfaceDeclaration_in_interfaceDeclaration1448);
                    normalInterfaceDeclaration85=normalInterfaceDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, normalInterfaceDeclaration85.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:504:9: annotationTypeDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_annotationTypeDeclaration_in_interfaceDeclaration1458);
                    annotationTypeDeclaration86=annotationTypeDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotationTypeDeclaration86.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 19, interfaceDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "interfaceDeclaration"

    public static class normalInterfaceDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "normalInterfaceDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:507:1: normalInterfaceDeclaration : modifiers 'interface' IDENTIFIER ( typeParameters )? ( 'extends' typeList )? interfaceBody ;
    public final JavaParser.normalInterfaceDeclaration_return normalInterfaceDeclaration() throws RecognitionException {
        JavaParser.normalInterfaceDeclaration_return retval = new JavaParser.normalInterfaceDeclaration_return();
        retval.start = input.LT(1);
        int normalInterfaceDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal88=null;
        Token IDENTIFIER89=null;
        Token string_literal91=null;
        JavaParser.modifiers_return modifiers87 = null;

        JavaParser.typeParameters_return typeParameters90 = null;

        JavaParser.typeList_return typeList92 = null;

        JavaParser.interfaceBody_return interfaceBody93 = null;


        Object string_literal88_tree=null;
        Object IDENTIFIER89_tree=null;
        Object string_literal91_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:508:5: ( modifiers 'interface' IDENTIFIER ( typeParameters )? ( 'extends' typeList )? interfaceBody )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:508:9: modifiers 'interface' IDENTIFIER ( typeParameters )? ( 'extends' typeList )? interfaceBody
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_modifiers_in_normalInterfaceDeclaration1482);
            modifiers87=modifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers87.getTree());
            string_literal88=(Token)match(input,INTERFACE,FOLLOW_INTERFACE_in_normalInterfaceDeclaration1484); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal88_tree = (Object)adaptor.create(string_literal88);
            adaptor.addChild(root_0, string_literal88_tree);
            }
            IDENTIFIER89=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_normalInterfaceDeclaration1486); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER89_tree = (Object)adaptor.create(IDENTIFIER89);
            adaptor.addChild(root_0, IDENTIFIER89_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:509:9: ( typeParameters )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==LT) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:509:10: typeParameters
                    {
                    pushFollow(FOLLOW_typeParameters_in_normalInterfaceDeclaration1497);
                    typeParameters90=typeParameters();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeParameters90.getTree());

                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:511:9: ( 'extends' typeList )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==EXTENDS) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:511:10: 'extends' typeList
                    {
                    string_literal91=(Token)match(input,EXTENDS,FOLLOW_EXTENDS_in_normalInterfaceDeclaration1519); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal91_tree = (Object)adaptor.create(string_literal91);
                    adaptor.addChild(root_0, string_literal91_tree);
                    }
                    pushFollow(FOLLOW_typeList_in_normalInterfaceDeclaration1521);
                    typeList92=typeList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeList92.getTree());

                    }
                    break;

            }

            pushFollow(FOLLOW_interfaceBody_in_normalInterfaceDeclaration1542);
            interfaceBody93=interfaceBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceBody93.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 20, normalInterfaceDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "normalInterfaceDeclaration"

    public static class typeList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typeList"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:516:1: typeList : type ( ',' type )* ;
    public final JavaParser.typeList_return typeList() throws RecognitionException {
        JavaParser.typeList_return retval = new JavaParser.typeList_return();
        retval.start = input.LT(1);
        int typeList_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal95=null;
        JavaParser.type_return type94 = null;

        JavaParser.type_return type96 = null;


        Object char_literal95_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:517:5: ( type ( ',' type )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:517:9: type ( ',' type )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_type_in_typeList1562);
            type94=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, type94.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:518:9: ( ',' type )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==COMMA) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:518:10: ',' type
            	    {
            	    char_literal95=(Token)match(input,COMMA,FOLLOW_COMMA_in_typeList1573); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal95_tree = (Object)adaptor.create(char_literal95);
            	    adaptor.addChild(root_0, char_literal95_tree);
            	    }
            	    pushFollow(FOLLOW_type_in_typeList1575);
            	    type96=type();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, type96.getTree());

            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 21, typeList_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "typeList"

    public static class classBody_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "classBody"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:522:1: classBody : '{' ( classBodyDeclaration )* '}' ;
    public final JavaParser.classBody_return classBody() throws RecognitionException {
        JavaParser.classBody_return retval = new JavaParser.classBody_return();
        retval.start = input.LT(1);
        int classBody_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal97=null;
        Token char_literal99=null;
        JavaParser.classBodyDeclaration_return classBodyDeclaration98 = null;


        Object char_literal97_tree=null;
        Object char_literal99_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:523:5: ( '{' ( classBodyDeclaration )* '}' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:523:9: '{' ( classBodyDeclaration )* '}'
            {
            root_0 = (Object)adaptor.nil();

            char_literal97=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_classBody1606); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal97_tree = (Object)adaptor.create(char_literal97);
            adaptor.addChild(root_0, char_literal97_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:524:9: ( classBodyDeclaration )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==IDENTIFIER||LA35_0==ABSTRACT||LA35_0==BOOLEAN||LA35_0==BYTE||(LA35_0>=CHAR && LA35_0<=CLASS)||LA35_0==DOUBLE||LA35_0==ENUM||LA35_0==FINAL||LA35_0==FLOAT||(LA35_0>=INT && LA35_0<=NATIVE)||(LA35_0>=PRIVATE && LA35_0<=PUBLIC)||(LA35_0>=SHORT && LA35_0<=STRICTFP)||LA35_0==SYNCHRONIZED||LA35_0==TRANSIENT||(LA35_0>=VOID && LA35_0<=VOLATILE)||LA35_0==LBRACE||LA35_0==SEMI||LA35_0==MONKEYS_AT||LA35_0==LT) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:524:10: classBodyDeclaration
            	    {
            	    pushFollow(FOLLOW_classBodyDeclaration_in_classBody1618);
            	    classBodyDeclaration98=classBodyDeclaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, classBodyDeclaration98.getTree());

            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);

            char_literal99=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_classBody1640); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal99_tree = (Object)adaptor.create(char_literal99);
            adaptor.addChild(root_0, char_literal99_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 22, classBody_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "classBody"

    public static class interfaceBody_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "interfaceBody"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:529:1: interfaceBody : '{' ( interfaceBodyDeclaration )* '}' ;
    public final JavaParser.interfaceBody_return interfaceBody() throws RecognitionException {
        JavaParser.interfaceBody_return retval = new JavaParser.interfaceBody_return();
        retval.start = input.LT(1);
        int interfaceBody_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal100=null;
        Token char_literal102=null;
        JavaParser.interfaceBodyDeclaration_return interfaceBodyDeclaration101 = null;


        Object char_literal100_tree=null;
        Object char_literal102_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:530:5: ( '{' ( interfaceBodyDeclaration )* '}' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:530:9: '{' ( interfaceBodyDeclaration )* '}'
            {
            root_0 = (Object)adaptor.nil();

            char_literal100=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_interfaceBody1660); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal100_tree = (Object)adaptor.create(char_literal100);
            adaptor.addChild(root_0, char_literal100_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:531:9: ( interfaceBodyDeclaration )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==IDENTIFIER||LA36_0==ABSTRACT||LA36_0==BOOLEAN||LA36_0==BYTE||(LA36_0>=CHAR && LA36_0<=CLASS)||LA36_0==DOUBLE||LA36_0==ENUM||LA36_0==FINAL||LA36_0==FLOAT||(LA36_0>=INT && LA36_0<=NATIVE)||(LA36_0>=PRIVATE && LA36_0<=PUBLIC)||(LA36_0>=SHORT && LA36_0<=STRICTFP)||LA36_0==SYNCHRONIZED||LA36_0==TRANSIENT||(LA36_0>=VOID && LA36_0<=VOLATILE)||LA36_0==SEMI||LA36_0==MONKEYS_AT||LA36_0==LT) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:531:10: interfaceBodyDeclaration
            	    {
            	    pushFollow(FOLLOW_interfaceBodyDeclaration_in_interfaceBody1672);
            	    interfaceBodyDeclaration101=interfaceBodyDeclaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceBodyDeclaration101.getTree());

            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);

            char_literal102=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_interfaceBody1694); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal102_tree = (Object)adaptor.create(char_literal102);
            adaptor.addChild(root_0, char_literal102_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 23, interfaceBody_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "interfaceBody"

    public static class classBodyDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "classBodyDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:536:1: classBodyDeclaration : ( ';' | ( 'static' )? block | memberDecl );
    public final JavaParser.classBodyDeclaration_return classBodyDeclaration() throws RecognitionException {
        JavaParser.classBodyDeclaration_return retval = new JavaParser.classBodyDeclaration_return();
        retval.start = input.LT(1);
        int classBodyDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal103=null;
        Token string_literal104=null;
        JavaParser.block_return block105 = null;

        JavaParser.memberDecl_return memberDecl106 = null;


        Object char_literal103_tree=null;
        Object string_literal104_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:537:5: ( ';' | ( 'static' )? block | memberDecl )
            int alt38=3;
            switch ( input.LA(1) ) {
            case SEMI:
                {
                alt38=1;
                }
                break;
            case STATIC:
                {
                int LA38_2 = input.LA(2);

                if ( (LA38_2==IDENTIFIER||LA38_2==ABSTRACT||LA38_2==BOOLEAN||LA38_2==BYTE||(LA38_2>=CHAR && LA38_2<=CLASS)||LA38_2==DOUBLE||LA38_2==ENUM||LA38_2==FINAL||LA38_2==FLOAT||(LA38_2>=INT && LA38_2<=NATIVE)||(LA38_2>=PRIVATE && LA38_2<=PUBLIC)||(LA38_2>=SHORT && LA38_2<=STRICTFP)||LA38_2==SYNCHRONIZED||LA38_2==TRANSIENT||(LA38_2>=VOID && LA38_2<=VOLATILE)||LA38_2==MONKEYS_AT||LA38_2==LT) ) {
                    alt38=3;
                }
                else if ( (LA38_2==LBRACE) ) {
                    alt38=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 38, 2, input);

                    throw nvae;
                }
                }
                break;
            case LBRACE:
                {
                alt38=2;
                }
                break;
            case IDENTIFIER:
            case ABSTRACT:
            case BOOLEAN:
            case BYTE:
            case CHAR:
            case CLASS:
            case DOUBLE:
            case ENUM:
            case FINAL:
            case FLOAT:
            case INT:
            case INTERFACE:
            case LONG:
            case NATIVE:
            case PRIVATE:
            case PROTECTED:
            case PUBLIC:
            case SHORT:
            case STRICTFP:
            case SYNCHRONIZED:
            case TRANSIENT:
            case VOID:
            case VOLATILE:
            case MONKEYS_AT:
            case LT:
                {
                alt38=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }

            switch (alt38) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:537:9: ';'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal103=(Token)match(input,SEMI,FOLLOW_SEMI_in_classBodyDeclaration1714); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal103_tree = (Object)adaptor.create(char_literal103);
                    adaptor.addChild(root_0, char_literal103_tree);
                    }

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:538:9: ( 'static' )? block
                    {
                    root_0 = (Object)adaptor.nil();

                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:538:9: ( 'static' )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==STATIC) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:538:10: 'static'
                            {
                            string_literal104=(Token)match(input,STATIC,FOLLOW_STATIC_in_classBodyDeclaration1725); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            string_literal104_tree = (Object)adaptor.create(string_literal104);
                            adaptor.addChild(root_0, string_literal104_tree);
                            }

                            }
                            break;

                    }

                    pushFollow(FOLLOW_block_in_classBodyDeclaration1747);
                    block105=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, block105.getTree());

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:541:9: memberDecl
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_memberDecl_in_classBodyDeclaration1757);
                    memberDecl106=memberDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, memberDecl106.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 24, classBodyDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "classBodyDeclaration"

    public static class memberDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "memberDecl"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:544:1: memberDecl : ( fieldDeclaration | methodDeclaration | classDeclaration | interfaceDeclaration );
    public final JavaParser.memberDecl_return memberDecl() throws RecognitionException {
        JavaParser.memberDecl_return retval = new JavaParser.memberDecl_return();
        retval.start = input.LT(1);
        int memberDecl_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.fieldDeclaration_return fieldDeclaration107 = null;

        JavaParser.methodDeclaration_return methodDeclaration108 = null;

        JavaParser.classDeclaration_return classDeclaration109 = null;

        JavaParser.interfaceDeclaration_return interfaceDeclaration110 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:545:5: ( fieldDeclaration | methodDeclaration | classDeclaration | interfaceDeclaration )
            int alt39=4;
            alt39 = dfa39.predict(input);
            switch (alt39) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:545:10: fieldDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_fieldDeclaration_in_memberDecl1778);
                    fieldDeclaration107=fieldDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fieldDeclaration107.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:546:10: methodDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_methodDeclaration_in_memberDecl1789);
                    methodDeclaration108=methodDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, methodDeclaration108.getTree());

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:547:10: classDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_classDeclaration_in_memberDecl1800);
                    classDeclaration109=classDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classDeclaration109.getTree());

                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:548:10: interfaceDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_interfaceDeclaration_in_memberDecl1811);
                    interfaceDeclaration110=interfaceDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceDeclaration110.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 25, memberDecl_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "memberDecl"

    public static class methodDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "methodDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:552:1: methodDeclaration : ( modifiers ( typeParameters )? IDENTIFIER formalParameters ( 'throws' qualifiedNameList )? '{' ( explicitConstructorInvocation )? ( blockStatement )* '}' | modifiers ( typeParameters )? ( type | 'void' ) IDENTIFIER formalParameters ( '[' ']' )* ( 'throws' qualifiedNameList )? ( block | ';' ) );
    public final JavaParser.methodDeclaration_return methodDeclaration() throws RecognitionException {
        JavaParser.methodDeclaration_return retval = new JavaParser.methodDeclaration_return();
        retval.start = input.LT(1);
        int methodDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token IDENTIFIER113=null;
        Token string_literal115=null;
        Token char_literal117=null;
        Token char_literal120=null;
        Token string_literal124=null;
        Token IDENTIFIER125=null;
        Token char_literal127=null;
        Token char_literal128=null;
        Token string_literal129=null;
        Token char_literal132=null;
        JavaParser.modifiers_return modifiers111 = null;

        JavaParser.typeParameters_return typeParameters112 = null;

        JavaParser.formalParameters_return formalParameters114 = null;

        JavaParser.qualifiedNameList_return qualifiedNameList116 = null;

        JavaParser.explicitConstructorInvocation_return explicitConstructorInvocation118 = null;

        JavaParser.blockStatement_return blockStatement119 = null;

        JavaParser.modifiers_return modifiers121 = null;

        JavaParser.typeParameters_return typeParameters122 = null;

        JavaParser.type_return type123 = null;

        JavaParser.formalParameters_return formalParameters126 = null;

        JavaParser.qualifiedNameList_return qualifiedNameList130 = null;

        JavaParser.block_return block131 = null;


        Object IDENTIFIER113_tree=null;
        Object string_literal115_tree=null;
        Object char_literal117_tree=null;
        Object char_literal120_tree=null;
        Object string_literal124_tree=null;
        Object IDENTIFIER125_tree=null;
        Object char_literal127_tree=null;
        Object char_literal128_tree=null;
        Object string_literal129_tree=null;
        Object char_literal132_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:553:5: ( modifiers ( typeParameters )? IDENTIFIER formalParameters ( 'throws' qualifiedNameList )? '{' ( explicitConstructorInvocation )? ( blockStatement )* '}' | modifiers ( typeParameters )? ( type | 'void' ) IDENTIFIER formalParameters ( '[' ']' )* ( 'throws' qualifiedNameList )? ( block | ';' ) )
            int alt49=2;
            alt49 = dfa49.predict(input);
            switch (alt49) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:555:10: modifiers ( typeParameters )? IDENTIFIER formalParameters ( 'throws' qualifiedNameList )? '{' ( explicitConstructorInvocation )? ( blockStatement )* '}'
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_modifiers_in_methodDeclaration1849);
                    modifiers111=modifiers();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers111.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:556:9: ( typeParameters )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==LT) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:556:10: typeParameters
                            {
                            pushFollow(FOLLOW_typeParameters_in_methodDeclaration1860);
                            typeParameters112=typeParameters();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeParameters112.getTree());

                            }
                            break;

                    }

                    IDENTIFIER113=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_methodDeclaration1881); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER113_tree = (Object)adaptor.create(IDENTIFIER113);
                    adaptor.addChild(root_0, IDENTIFIER113_tree);
                    }
                    pushFollow(FOLLOW_formalParameters_in_methodDeclaration1891);
                    formalParameters114=formalParameters();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, formalParameters114.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:560:9: ( 'throws' qualifiedNameList )?
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==THROWS) ) {
                        alt41=1;
                    }
                    switch (alt41) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:560:10: 'throws' qualifiedNameList
                            {
                            string_literal115=(Token)match(input,THROWS,FOLLOW_THROWS_in_methodDeclaration1902); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            string_literal115_tree = (Object)adaptor.create(string_literal115);
                            adaptor.addChild(root_0, string_literal115_tree);
                            }
                            pushFollow(FOLLOW_qualifiedNameList_in_methodDeclaration1904);
                            qualifiedNameList116=qualifiedNameList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedNameList116.getTree());

                            }
                            break;

                    }

                    char_literal117=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_methodDeclaration1925); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal117_tree = (Object)adaptor.create(char_literal117);
                    adaptor.addChild(root_0, char_literal117_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:563:9: ( explicitConstructorInvocation )?
                    int alt42=2;
                    alt42 = dfa42.predict(input);
                    switch (alt42) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:563:10: explicitConstructorInvocation
                            {
                            pushFollow(FOLLOW_explicitConstructorInvocation_in_methodDeclaration1937);
                            explicitConstructorInvocation118=explicitConstructorInvocation();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, explicitConstructorInvocation118.getTree());

                            }
                            break;

                    }

                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:565:9: ( blockStatement )*
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( ((LA43_0>=IDENTIFIER && LA43_0<=NULL)||(LA43_0>=ABSTRACT && LA43_0<=BYTE)||(LA43_0>=CHAR && LA43_0<=CLASS)||LA43_0==CONTINUE||(LA43_0>=DO && LA43_0<=DOUBLE)||LA43_0==ENUM||LA43_0==FINAL||(LA43_0>=FLOAT && LA43_0<=FOR)||LA43_0==IF||(LA43_0>=INT && LA43_0<=NEW)||(LA43_0>=PRIVATE && LA43_0<=THROW)||(LA43_0>=TRANSIENT && LA43_0<=LPAREN)||LA43_0==LBRACE||LA43_0==SEMI||(LA43_0>=BANG && LA43_0<=TILDE)||(LA43_0>=PLUSPLUS && LA43_0<=SUB)||LA43_0==MONKEYS_AT||LA43_0==LT) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:565:10: blockStatement
                    	    {
                    	    pushFollow(FOLLOW_blockStatement_in_methodDeclaration1959);
                    	    blockStatement119=blockStatement();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, blockStatement119.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop43;
                        }
                    } while (true);

                    char_literal120=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_methodDeclaration1980); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal120_tree = (Object)adaptor.create(char_literal120);
                    adaptor.addChild(root_0, char_literal120_tree);
                    }

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:568:9: modifiers ( typeParameters )? ( type | 'void' ) IDENTIFIER formalParameters ( '[' ']' )* ( 'throws' qualifiedNameList )? ( block | ';' )
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_modifiers_in_methodDeclaration1990);
                    modifiers121=modifiers();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers121.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:569:9: ( typeParameters )?
                    int alt44=2;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==LT) ) {
                        alt44=1;
                    }
                    switch (alt44) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:569:10: typeParameters
                            {
                            pushFollow(FOLLOW_typeParameters_in_methodDeclaration2001);
                            typeParameters122=typeParameters();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeParameters122.getTree());

                            }
                            break;

                    }

                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:571:9: ( type | 'void' )
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( (LA45_0==IDENTIFIER||LA45_0==BOOLEAN||LA45_0==BYTE||LA45_0==CHAR||LA45_0==DOUBLE||LA45_0==FLOAT||LA45_0==INT||LA45_0==LONG||LA45_0==SHORT) ) {
                        alt45=1;
                    }
                    else if ( (LA45_0==VOID) ) {
                        alt45=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 45, 0, input);

                        throw nvae;
                    }
                    switch (alt45) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:571:10: type
                            {
                            pushFollow(FOLLOW_type_in_methodDeclaration2023);
                            type123=type();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, type123.getTree());

                            }
                            break;
                        case 2 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:572:13: 'void'
                            {
                            string_literal124=(Token)match(input,VOID,FOLLOW_VOID_in_methodDeclaration2037); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            string_literal124_tree = (Object)adaptor.create(string_literal124);
                            adaptor.addChild(root_0, string_literal124_tree);
                            }

                            }
                            break;

                    }

                    IDENTIFIER125=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_methodDeclaration2057); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER125_tree = (Object)adaptor.create(IDENTIFIER125);
                    adaptor.addChild(root_0, IDENTIFIER125_tree);
                    }
                    pushFollow(FOLLOW_formalParameters_in_methodDeclaration2067);
                    formalParameters126=formalParameters();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, formalParameters126.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:576:9: ( '[' ']' )*
                    loop46:
                    do {
                        int alt46=2;
                        int LA46_0 = input.LA(1);

                        if ( (LA46_0==LBRACKET) ) {
                            alt46=1;
                        }


                        switch (alt46) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:576:10: '[' ']'
                    	    {
                    	    char_literal127=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_methodDeclaration2078); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal127_tree = (Object)adaptor.create(char_literal127);
                    	    adaptor.addChild(root_0, char_literal127_tree);
                    	    }
                    	    char_literal128=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_methodDeclaration2080); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal128_tree = (Object)adaptor.create(char_literal128);
                    	    adaptor.addChild(root_0, char_literal128_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop46;
                        }
                    } while (true);

                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:578:9: ( 'throws' qualifiedNameList )?
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( (LA47_0==THROWS) ) {
                        alt47=1;
                    }
                    switch (alt47) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:578:10: 'throws' qualifiedNameList
                            {
                            string_literal129=(Token)match(input,THROWS,FOLLOW_THROWS_in_methodDeclaration2102); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            string_literal129_tree = (Object)adaptor.create(string_literal129);
                            adaptor.addChild(root_0, string_literal129_tree);
                            }
                            pushFollow(FOLLOW_qualifiedNameList_in_methodDeclaration2104);
                            qualifiedNameList130=qualifiedNameList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedNameList130.getTree());

                            }
                            break;

                    }

                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:580:9: ( block | ';' )
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( (LA48_0==LBRACE) ) {
                        alt48=1;
                    }
                    else if ( (LA48_0==SEMI) ) {
                        alt48=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 48, 0, input);

                        throw nvae;
                    }
                    switch (alt48) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:581:13: block
                            {
                            pushFollow(FOLLOW_block_in_methodDeclaration2159);
                            block131=block();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, block131.getTree());

                            }
                            break;
                        case 2 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:582:13: ';'
                            {
                            char_literal132=(Token)match(input,SEMI,FOLLOW_SEMI_in_methodDeclaration2173); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal132_tree = (Object)adaptor.create(char_literal132);
                            adaptor.addChild(root_0, char_literal132_tree);
                            }

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                              //todo-- method stack here. push new method
                              IntentionalMethod currentMethod = new IntentionalMethod();
                              currentMethod.setMethodName((IDENTIFIER125!=null?IDENTIFIER125.getText():null));
                              classStack.peek().addMethod(currentMethod);
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 26, methodDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "methodDeclaration"

    public static class fieldDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "fieldDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:593:1: fieldDeclaration : modifiers type variableDeclarator ( ',' variableDeclarator )* ';' ;
    public final JavaParser.fieldDeclaration_return fieldDeclaration() throws RecognitionException {
        JavaParser.fieldDeclaration_return retval = new JavaParser.fieldDeclaration_return();
        retval.start = input.LT(1);
        int fieldDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal136=null;
        Token char_literal138=null;
        JavaParser.modifiers_return modifiers133 = null;

        JavaParser.type_return type134 = null;

        JavaParser.variableDeclarator_return variableDeclarator135 = null;

        JavaParser.variableDeclarator_return variableDeclarator137 = null;


        Object char_literal136_tree=null;
        Object char_literal138_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:594:5: ( modifiers type variableDeclarator ( ',' variableDeclarator )* ';' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:594:9: modifiers type variableDeclarator ( ',' variableDeclarator )* ';'
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_modifiers_in_fieldDeclaration2215);
            modifiers133=modifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers133.getTree());
            pushFollow(FOLLOW_type_in_fieldDeclaration2225);
            type134=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, type134.getTree());
            pushFollow(FOLLOW_variableDeclarator_in_fieldDeclaration2235);
            variableDeclarator135=variableDeclarator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclarator135.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:597:9: ( ',' variableDeclarator )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==COMMA) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:597:10: ',' variableDeclarator
            	    {
            	    char_literal136=(Token)match(input,COMMA,FOLLOW_COMMA_in_fieldDeclaration2246); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal136_tree = (Object)adaptor.create(char_literal136);
            	    adaptor.addChild(root_0, char_literal136_tree);
            	    }
            	    pushFollow(FOLLOW_variableDeclarator_in_fieldDeclaration2248);
            	    variableDeclarator137=variableDeclarator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclarator137.getTree());

            	    }
            	    break;

            	default :
            	    break loop50;
                }
            } while (true);

            char_literal138=(Token)match(input,SEMI,FOLLOW_SEMI_in_fieldDeclaration2269); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal138_tree = (Object)adaptor.create(char_literal138);
            adaptor.addChild(root_0, char_literal138_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 27, fieldDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "fieldDeclaration"

    public static class variableDeclarator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "variableDeclarator"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:602:1: variableDeclarator : IDENTIFIER ( '[' ']' )* ( '=' variableInitializer )? ;
    public final JavaParser.variableDeclarator_return variableDeclarator() throws RecognitionException {
        JavaParser.variableDeclarator_return retval = new JavaParser.variableDeclarator_return();
        retval.start = input.LT(1);
        int variableDeclarator_StartIndex = input.index();
        Object root_0 = null;

        Token IDENTIFIER139=null;
        Token char_literal140=null;
        Token char_literal141=null;
        Token char_literal142=null;
        JavaParser.variableInitializer_return variableInitializer143 = null;


        Object IDENTIFIER139_tree=null;
        Object char_literal140_tree=null;
        Object char_literal141_tree=null;
        Object char_literal142_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:603:5: ( IDENTIFIER ( '[' ']' )* ( '=' variableInitializer )? )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:603:9: IDENTIFIER ( '[' ']' )* ( '=' variableInitializer )?
            {
            root_0 = (Object)adaptor.nil();

            IDENTIFIER139=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_variableDeclarator2289); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER139_tree = (Object)adaptor.create(IDENTIFIER139);
            adaptor.addChild(root_0, IDENTIFIER139_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:604:9: ( '[' ']' )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==LBRACKET) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:604:10: '[' ']'
            	    {
            	    char_literal140=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_variableDeclarator2300); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal140_tree = (Object)adaptor.create(char_literal140);
            	    adaptor.addChild(root_0, char_literal140_tree);
            	    }
            	    char_literal141=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_variableDeclarator2302); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal141_tree = (Object)adaptor.create(char_literal141);
            	    adaptor.addChild(root_0, char_literal141_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop51;
                }
            } while (true);

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:606:9: ( '=' variableInitializer )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==EQ) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:606:10: '=' variableInitializer
                    {
                    char_literal142=(Token)match(input,EQ,FOLLOW_EQ_in_variableDeclarator2324); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal142_tree = (Object)adaptor.create(char_literal142);
                    adaptor.addChild(root_0, char_literal142_tree);
                    }
                    pushFollow(FOLLOW_variableInitializer_in_variableDeclarator2326);
                    variableInitializer143=variableInitializer();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, variableInitializer143.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 28, variableDeclarator_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "variableDeclarator"

    public static class interfaceBodyDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "interfaceBodyDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:610:1: interfaceBodyDeclaration : ( interfaceFieldDeclaration | interfaceMethodDeclaration | interfaceDeclaration | classDeclaration | ';' );
    public final JavaParser.interfaceBodyDeclaration_return interfaceBodyDeclaration() throws RecognitionException {
        JavaParser.interfaceBodyDeclaration_return retval = new JavaParser.interfaceBodyDeclaration_return();
        retval.start = input.LT(1);
        int interfaceBodyDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal148=null;
        JavaParser.interfaceFieldDeclaration_return interfaceFieldDeclaration144 = null;

        JavaParser.interfaceMethodDeclaration_return interfaceMethodDeclaration145 = null;

        JavaParser.interfaceDeclaration_return interfaceDeclaration146 = null;

        JavaParser.classDeclaration_return classDeclaration147 = null;


        Object char_literal148_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:614:5: ( interfaceFieldDeclaration | interfaceMethodDeclaration | interfaceDeclaration | classDeclaration | ';' )
            int alt53=5;
            alt53 = dfa53.predict(input);
            switch (alt53) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:615:9: interfaceFieldDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_interfaceFieldDeclaration_in_interfaceBodyDeclaration2365);
                    interfaceFieldDeclaration144=interfaceFieldDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceFieldDeclaration144.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:616:9: interfaceMethodDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_interfaceMethodDeclaration_in_interfaceBodyDeclaration2375);
                    interfaceMethodDeclaration145=interfaceMethodDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceMethodDeclaration145.getTree());

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:617:9: interfaceDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_interfaceDeclaration_in_interfaceBodyDeclaration2385);
                    interfaceDeclaration146=interfaceDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceDeclaration146.getTree());

                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:618:9: classDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_classDeclaration_in_interfaceBodyDeclaration2395);
                    classDeclaration147=classDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classDeclaration147.getTree());

                    }
                    break;
                case 5 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:619:9: ';'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal148=(Token)match(input,SEMI,FOLLOW_SEMI_in_interfaceBodyDeclaration2405); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal148_tree = (Object)adaptor.create(char_literal148);
                    adaptor.addChild(root_0, char_literal148_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 29, interfaceBodyDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "interfaceBodyDeclaration"

    public static class interfaceMethodDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "interfaceMethodDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:622:1: interfaceMethodDeclaration : modifiers ( typeParameters )? ( type | 'void' ) IDENTIFIER formalParameters ( '[' ']' )* ( 'throws' qualifiedNameList )? ';' ;
    public final JavaParser.interfaceMethodDeclaration_return interfaceMethodDeclaration() throws RecognitionException {
        JavaParser.interfaceMethodDeclaration_return retval = new JavaParser.interfaceMethodDeclaration_return();
        retval.start = input.LT(1);
        int interfaceMethodDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal152=null;
        Token IDENTIFIER153=null;
        Token char_literal155=null;
        Token char_literal156=null;
        Token string_literal157=null;
        Token char_literal159=null;
        JavaParser.modifiers_return modifiers149 = null;

        JavaParser.typeParameters_return typeParameters150 = null;

        JavaParser.type_return type151 = null;

        JavaParser.formalParameters_return formalParameters154 = null;

        JavaParser.qualifiedNameList_return qualifiedNameList158 = null;


        Object string_literal152_tree=null;
        Object IDENTIFIER153_tree=null;
        Object char_literal155_tree=null;
        Object char_literal156_tree=null;
        Object string_literal157_tree=null;
        Object char_literal159_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:623:5: ( modifiers ( typeParameters )? ( type | 'void' ) IDENTIFIER formalParameters ( '[' ']' )* ( 'throws' qualifiedNameList )? ';' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:623:9: modifiers ( typeParameters )? ( type | 'void' ) IDENTIFIER formalParameters ( '[' ']' )* ( 'throws' qualifiedNameList )? ';'
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_modifiers_in_interfaceMethodDeclaration2425);
            modifiers149=modifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers149.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:624:9: ( typeParameters )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==LT) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:624:10: typeParameters
                    {
                    pushFollow(FOLLOW_typeParameters_in_interfaceMethodDeclaration2436);
                    typeParameters150=typeParameters();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeParameters150.getTree());

                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:626:9: ( type | 'void' )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==IDENTIFIER||LA55_0==BOOLEAN||LA55_0==BYTE||LA55_0==CHAR||LA55_0==DOUBLE||LA55_0==FLOAT||LA55_0==INT||LA55_0==LONG||LA55_0==SHORT) ) {
                alt55=1;
            }
            else if ( (LA55_0==VOID) ) {
                alt55=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }
            switch (alt55) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:626:10: type
                    {
                    pushFollow(FOLLOW_type_in_interfaceMethodDeclaration2458);
                    type151=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, type151.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:627:10: 'void'
                    {
                    string_literal152=(Token)match(input,VOID,FOLLOW_VOID_in_interfaceMethodDeclaration2469); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal152_tree = (Object)adaptor.create(string_literal152);
                    adaptor.addChild(root_0, string_literal152_tree);
                    }

                    }
                    break;

            }

            IDENTIFIER153=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_interfaceMethodDeclaration2489); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER153_tree = (Object)adaptor.create(IDENTIFIER153);
            adaptor.addChild(root_0, IDENTIFIER153_tree);
            }
            pushFollow(FOLLOW_formalParameters_in_interfaceMethodDeclaration2499);
            formalParameters154=formalParameters();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, formalParameters154.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:631:9: ( '[' ']' )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( (LA56_0==LBRACKET) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:631:10: '[' ']'
            	    {
            	    char_literal155=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_interfaceMethodDeclaration2510); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal155_tree = (Object)adaptor.create(char_literal155);
            	    adaptor.addChild(root_0, char_literal155_tree);
            	    }
            	    char_literal156=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_interfaceMethodDeclaration2512); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal156_tree = (Object)adaptor.create(char_literal156);
            	    adaptor.addChild(root_0, char_literal156_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop56;
                }
            } while (true);

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:633:9: ( 'throws' qualifiedNameList )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==THROWS) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:633:10: 'throws' qualifiedNameList
                    {
                    string_literal157=(Token)match(input,THROWS,FOLLOW_THROWS_in_interfaceMethodDeclaration2534); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal157_tree = (Object)adaptor.create(string_literal157);
                    adaptor.addChild(root_0, string_literal157_tree);
                    }
                    pushFollow(FOLLOW_qualifiedNameList_in_interfaceMethodDeclaration2536);
                    qualifiedNameList158=qualifiedNameList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedNameList158.getTree());

                    }
                    break;

            }

            char_literal159=(Token)match(input,SEMI,FOLLOW_SEMI_in_interfaceMethodDeclaration2549); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal159_tree = (Object)adaptor.create(char_literal159);
            adaptor.addChild(root_0, char_literal159_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 30, interfaceMethodDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "interfaceMethodDeclaration"

    public static class interfaceFieldDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "interfaceFieldDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:637:1: interfaceFieldDeclaration : modifiers type variableDeclarator ( ',' variableDeclarator )* ';' ;
    public final JavaParser.interfaceFieldDeclaration_return interfaceFieldDeclaration() throws RecognitionException {
        JavaParser.interfaceFieldDeclaration_return retval = new JavaParser.interfaceFieldDeclaration_return();
        retval.start = input.LT(1);
        int interfaceFieldDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal163=null;
        Token char_literal165=null;
        JavaParser.modifiers_return modifiers160 = null;

        JavaParser.type_return type161 = null;

        JavaParser.variableDeclarator_return variableDeclarator162 = null;

        JavaParser.variableDeclarator_return variableDeclarator164 = null;


        Object char_literal163_tree=null;
        Object char_literal165_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:643:5: ( modifiers type variableDeclarator ( ',' variableDeclarator )* ';' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:643:9: modifiers type variableDeclarator ( ',' variableDeclarator )* ';'
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_modifiers_in_interfaceFieldDeclaration2571);
            modifiers160=modifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers160.getTree());
            pushFollow(FOLLOW_type_in_interfaceFieldDeclaration2573);
            type161=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, type161.getTree());
            pushFollow(FOLLOW_variableDeclarator_in_interfaceFieldDeclaration2575);
            variableDeclarator162=variableDeclarator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclarator162.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:644:9: ( ',' variableDeclarator )*
            loop58:
            do {
                int alt58=2;
                int LA58_0 = input.LA(1);

                if ( (LA58_0==COMMA) ) {
                    alt58=1;
                }


                switch (alt58) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:644:10: ',' variableDeclarator
            	    {
            	    char_literal163=(Token)match(input,COMMA,FOLLOW_COMMA_in_interfaceFieldDeclaration2586); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal163_tree = (Object)adaptor.create(char_literal163);
            	    adaptor.addChild(root_0, char_literal163_tree);
            	    }
            	    pushFollow(FOLLOW_variableDeclarator_in_interfaceFieldDeclaration2588);
            	    variableDeclarator164=variableDeclarator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclarator164.getTree());

            	    }
            	    break;

            	default :
            	    break loop58;
                }
            } while (true);

            char_literal165=(Token)match(input,SEMI,FOLLOW_SEMI_in_interfaceFieldDeclaration2609); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal165_tree = (Object)adaptor.create(char_literal165);
            adaptor.addChild(root_0, char_literal165_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 31, interfaceFieldDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "interfaceFieldDeclaration"

    public static class type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "type"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:650:1: type : ( classOrInterfaceType ( '[' ']' )* | primitiveType ( '[' ']' )* );
    public final JavaParser.type_return type() throws RecognitionException {
        JavaParser.type_return retval = new JavaParser.type_return();
        retval.start = input.LT(1);
        int type_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal167=null;
        Token char_literal168=null;
        Token char_literal170=null;
        Token char_literal171=null;
        JavaParser.classOrInterfaceType_return classOrInterfaceType166 = null;

        JavaParser.primitiveType_return primitiveType169 = null;


        Object char_literal167_tree=null;
        Object char_literal168_tree=null;
        Object char_literal170_tree=null;
        Object char_literal171_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:651:5: ( classOrInterfaceType ( '[' ']' )* | primitiveType ( '[' ']' )* )
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==IDENTIFIER) ) {
                alt61=1;
            }
            else if ( (LA61_0==BOOLEAN||LA61_0==BYTE||LA61_0==CHAR||LA61_0==DOUBLE||LA61_0==FLOAT||LA61_0==INT||LA61_0==LONG||LA61_0==SHORT) ) {
                alt61=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }
            switch (alt61) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:651:9: classOrInterfaceType ( '[' ']' )*
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_classOrInterfaceType_in_type2630);
                    classOrInterfaceType166=classOrInterfaceType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classOrInterfaceType166.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:652:9: ( '[' ']' )*
                    loop59:
                    do {
                        int alt59=2;
                        int LA59_0 = input.LA(1);

                        if ( (LA59_0==LBRACKET) ) {
                            alt59=1;
                        }


                        switch (alt59) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:652:10: '[' ']'
                    	    {
                    	    char_literal167=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_type2641); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal167_tree = (Object)adaptor.create(char_literal167);
                    	    adaptor.addChild(root_0, char_literal167_tree);
                    	    }
                    	    char_literal168=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_type2643); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal168_tree = (Object)adaptor.create(char_literal168);
                    	    adaptor.addChild(root_0, char_literal168_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop59;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:654:9: primitiveType ( '[' ']' )*
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_primitiveType_in_type2664);
                    primitiveType169=primitiveType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, primitiveType169.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:655:9: ( '[' ']' )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==LBRACKET) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:655:10: '[' ']'
                    	    {
                    	    char_literal170=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_type2675); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal170_tree = (Object)adaptor.create(char_literal170);
                    	    adaptor.addChild(root_0, char_literal170_tree);
                    	    }
                    	    char_literal171=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_type2677); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal171_tree = (Object)adaptor.create(char_literal171);
                    	    adaptor.addChild(root_0, char_literal171_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop60;
                        }
                    } while (true);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 32, type_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "type"

    public static class classOrInterfaceType_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "classOrInterfaceType"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:660:1: classOrInterfaceType : IDENTIFIER ( typeArguments )? ( '.' IDENTIFIER ( typeArguments )? )* ;
    public final JavaParser.classOrInterfaceType_return classOrInterfaceType() throws RecognitionException {
        JavaParser.classOrInterfaceType_return retval = new JavaParser.classOrInterfaceType_return();
        retval.start = input.LT(1);
        int classOrInterfaceType_StartIndex = input.index();
        Object root_0 = null;

        Token IDENTIFIER172=null;
        Token char_literal174=null;
        Token IDENTIFIER175=null;
        JavaParser.typeArguments_return typeArguments173 = null;

        JavaParser.typeArguments_return typeArguments176 = null;


        Object IDENTIFIER172_tree=null;
        Object char_literal174_tree=null;
        Object IDENTIFIER175_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:661:5: ( IDENTIFIER ( typeArguments )? ( '.' IDENTIFIER ( typeArguments )? )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:661:9: IDENTIFIER ( typeArguments )? ( '.' IDENTIFIER ( typeArguments )? )*
            {
            root_0 = (Object)adaptor.nil();

            IDENTIFIER172=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_classOrInterfaceType2709); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER172_tree = (Object)adaptor.create(IDENTIFIER172);
            adaptor.addChild(root_0, IDENTIFIER172_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:662:9: ( typeArguments )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==LT) ) {
                int LA62_1 = input.LA(2);

                if ( (LA62_1==IDENTIFIER||LA62_1==BOOLEAN||LA62_1==BYTE||LA62_1==CHAR||LA62_1==DOUBLE||LA62_1==FLOAT||LA62_1==INT||LA62_1==LONG||LA62_1==SHORT||LA62_1==QUES) ) {
                    alt62=1;
                }
            }
            switch (alt62) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:662:10: typeArguments
                    {
                    pushFollow(FOLLOW_typeArguments_in_classOrInterfaceType2720);
                    typeArguments173=typeArguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeArguments173.getTree());

                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:664:9: ( '.' IDENTIFIER ( typeArguments )? )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==DOT) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:664:10: '.' IDENTIFIER ( typeArguments )?
            	    {
            	    char_literal174=(Token)match(input,DOT,FOLLOW_DOT_in_classOrInterfaceType2742); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal174_tree = (Object)adaptor.create(char_literal174);
            	    adaptor.addChild(root_0, char_literal174_tree);
            	    }
            	    IDENTIFIER175=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_classOrInterfaceType2744); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    IDENTIFIER175_tree = (Object)adaptor.create(IDENTIFIER175);
            	    adaptor.addChild(root_0, IDENTIFIER175_tree);
            	    }
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:665:13: ( typeArguments )?
            	    int alt63=2;
            	    int LA63_0 = input.LA(1);

            	    if ( (LA63_0==LT) ) {
            	        int LA63_1 = input.LA(2);

            	        if ( (LA63_1==IDENTIFIER||LA63_1==BOOLEAN||LA63_1==BYTE||LA63_1==CHAR||LA63_1==DOUBLE||LA63_1==FLOAT||LA63_1==INT||LA63_1==LONG||LA63_1==SHORT||LA63_1==QUES) ) {
            	            alt63=1;
            	        }
            	    }
            	    switch (alt63) {
            	        case 1 :
            	            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:665:14: typeArguments
            	            {
            	            pushFollow(FOLLOW_typeArguments_in_classOrInterfaceType2759);
            	            typeArguments176=typeArguments();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeArguments176.getTree());

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop64;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 33, classOrInterfaceType_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "classOrInterfaceType"

    public static class primitiveType_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "primitiveType"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:670:1: primitiveType : ( 'boolean' | 'char' | 'byte' | 'short' | 'int' | 'long' | 'float' | 'double' );
    public final JavaParser.primitiveType_return primitiveType() throws RecognitionException {
        JavaParser.primitiveType_return retval = new JavaParser.primitiveType_return();
        retval.start = input.LT(1);
        int primitiveType_StartIndex = input.index();
        Object root_0 = null;

        Token set177=null;

        Object set177_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:671:5: ( 'boolean' | 'char' | 'byte' | 'short' | 'int' | 'long' | 'float' | 'double' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:
            {
            root_0 = (Object)adaptor.nil();

            set177=(Token)input.LT(1);
            if ( input.LA(1)==BOOLEAN||input.LA(1)==BYTE||input.LA(1)==CHAR||input.LA(1)==DOUBLE||input.LA(1)==FLOAT||input.LA(1)==INT||input.LA(1)==LONG||input.LA(1)==SHORT ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set177));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 34, primitiveType_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "primitiveType"

    public static class typeArguments_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typeArguments"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:681:1: typeArguments : '<' typeArgument ( ',' typeArgument )* '>' ;
    public final JavaParser.typeArguments_return typeArguments() throws RecognitionException {
        JavaParser.typeArguments_return retval = new JavaParser.typeArguments_return();
        retval.start = input.LT(1);
        int typeArguments_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal178=null;
        Token char_literal180=null;
        Token char_literal182=null;
        JavaParser.typeArgument_return typeArgument179 = null;

        JavaParser.typeArgument_return typeArgument181 = null;


        Object char_literal178_tree=null;
        Object char_literal180_tree=null;
        Object char_literal182_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:682:5: ( '<' typeArgument ( ',' typeArgument )* '>' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:682:9: '<' typeArgument ( ',' typeArgument )* '>'
            {
            root_0 = (Object)adaptor.nil();

            char_literal178=(Token)match(input,LT,FOLLOW_LT_in_typeArguments2896); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal178_tree = (Object)adaptor.create(char_literal178);
            adaptor.addChild(root_0, char_literal178_tree);
            }
            pushFollow(FOLLOW_typeArgument_in_typeArguments2898);
            typeArgument179=typeArgument();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeArgument179.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:683:9: ( ',' typeArgument )*
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( (LA65_0==COMMA) ) {
                    alt65=1;
                }


                switch (alt65) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:683:10: ',' typeArgument
            	    {
            	    char_literal180=(Token)match(input,COMMA,FOLLOW_COMMA_in_typeArguments2909); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal180_tree = (Object)adaptor.create(char_literal180);
            	    adaptor.addChild(root_0, char_literal180_tree);
            	    }
            	    pushFollow(FOLLOW_typeArgument_in_typeArguments2911);
            	    typeArgument181=typeArgument();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeArgument181.getTree());

            	    }
            	    break;

            	default :
            	    break loop65;
                }
            } while (true);

            char_literal182=(Token)match(input,GT,FOLLOW_GT_in_typeArguments2933); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal182_tree = (Object)adaptor.create(char_literal182);
            adaptor.addChild(root_0, char_literal182_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 35, typeArguments_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "typeArguments"

    public static class typeArgument_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typeArgument"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:688:1: typeArgument : ( type | '?' ( ( 'extends' | 'super' ) type )? );
    public final JavaParser.typeArgument_return typeArgument() throws RecognitionException {
        JavaParser.typeArgument_return retval = new JavaParser.typeArgument_return();
        retval.start = input.LT(1);
        int typeArgument_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal184=null;
        Token set185=null;
        JavaParser.type_return type183 = null;

        JavaParser.type_return type186 = null;


        Object char_literal184_tree=null;
        Object set185_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:689:5: ( type | '?' ( ( 'extends' | 'super' ) type )? )
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==IDENTIFIER||LA67_0==BOOLEAN||LA67_0==BYTE||LA67_0==CHAR||LA67_0==DOUBLE||LA67_0==FLOAT||LA67_0==INT||LA67_0==LONG||LA67_0==SHORT) ) {
                alt67=1;
            }
            else if ( (LA67_0==QUES) ) {
                alt67=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 67, 0, input);

                throw nvae;
            }
            switch (alt67) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:689:9: type
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_type_in_typeArgument2953);
                    type183=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, type183.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:690:9: '?' ( ( 'extends' | 'super' ) type )?
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal184=(Token)match(input,QUES,FOLLOW_QUES_in_typeArgument2963); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal184_tree = (Object)adaptor.create(char_literal184);
                    adaptor.addChild(root_0, char_literal184_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:691:9: ( ( 'extends' | 'super' ) type )?
                    int alt66=2;
                    int LA66_0 = input.LA(1);

                    if ( (LA66_0==EXTENDS||LA66_0==SUPER) ) {
                        alt66=1;
                    }
                    switch (alt66) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:692:13: ( 'extends' | 'super' ) type
                            {
                            set185=(Token)input.LT(1);
                            if ( input.LA(1)==EXTENDS||input.LA(1)==SUPER ) {
                                input.consume();
                                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set185));
                                state.errorRecovery=false;state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }

                            pushFollow(FOLLOW_type_in_typeArgument3031);
                            type186=type();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, type186.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 36, typeArgument_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "typeArgument"

    public static class qualifiedNameList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "qualifiedNameList"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:699:1: qualifiedNameList : qualifiedName ( ',' qualifiedName )* ;
    public final JavaParser.qualifiedNameList_return qualifiedNameList() throws RecognitionException {
        JavaParser.qualifiedNameList_return retval = new JavaParser.qualifiedNameList_return();
        retval.start = input.LT(1);
        int qualifiedNameList_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal188=null;
        JavaParser.qualifiedName_return qualifiedName187 = null;

        JavaParser.qualifiedName_return qualifiedName189 = null;


        Object char_literal188_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:700:5: ( qualifiedName ( ',' qualifiedName )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:700:9: qualifiedName ( ',' qualifiedName )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_qualifiedName_in_qualifiedNameList3062);
            qualifiedName187=qualifiedName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedName187.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:701:9: ( ',' qualifiedName )*
            loop68:
            do {
                int alt68=2;
                int LA68_0 = input.LA(1);

                if ( (LA68_0==COMMA) ) {
                    alt68=1;
                }


                switch (alt68) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:701:10: ',' qualifiedName
            	    {
            	    char_literal188=(Token)match(input,COMMA,FOLLOW_COMMA_in_qualifiedNameList3073); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal188_tree = (Object)adaptor.create(char_literal188);
            	    adaptor.addChild(root_0, char_literal188_tree);
            	    }
            	    pushFollow(FOLLOW_qualifiedName_in_qualifiedNameList3075);
            	    qualifiedName189=qualifiedName();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedName189.getTree());

            	    }
            	    break;

            	default :
            	    break loop68;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 37, qualifiedNameList_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "qualifiedNameList"

    public static class formalParameters_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "formalParameters"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:705:1: formalParameters : '(' ( formalParameterDecls )? ')' ;
    public final JavaParser.formalParameters_return formalParameters() throws RecognitionException {
        JavaParser.formalParameters_return retval = new JavaParser.formalParameters_return();
        retval.start = input.LT(1);
        int formalParameters_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal190=null;
        Token char_literal192=null;
        JavaParser.formalParameterDecls_return formalParameterDecls191 = null;


        Object char_literal190_tree=null;
        Object char_literal192_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:706:5: ( '(' ( formalParameterDecls )? ')' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:706:9: '(' ( formalParameterDecls )? ')'
            {
            root_0 = (Object)adaptor.nil();

            char_literal190=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_formalParameters3106); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal190_tree = (Object)adaptor.create(char_literal190);
            adaptor.addChild(root_0, char_literal190_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:707:9: ( formalParameterDecls )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==IDENTIFIER||LA69_0==BOOLEAN||LA69_0==BYTE||LA69_0==CHAR||LA69_0==DOUBLE||LA69_0==FINAL||LA69_0==FLOAT||LA69_0==INT||LA69_0==LONG||LA69_0==SHORT||LA69_0==MONKEYS_AT) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:707:10: formalParameterDecls
                    {
                    pushFollow(FOLLOW_formalParameterDecls_in_formalParameters3117);
                    formalParameterDecls191=formalParameterDecls();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, formalParameterDecls191.getTree());

                    }
                    break;

            }

            char_literal192=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_formalParameters3139); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal192_tree = (Object)adaptor.create(char_literal192);
            adaptor.addChild(root_0, char_literal192_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 38, formalParameters_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "formalParameters"

    public static class formalParameterDecls_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "formalParameterDecls"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:712:1: formalParameterDecls : ( ellipsisParameterDecl | normalParameterDecl ( ',' normalParameterDecl )* | ( normalParameterDecl ',' )+ ellipsisParameterDecl );
    public final JavaParser.formalParameterDecls_return formalParameterDecls() throws RecognitionException {
        JavaParser.formalParameterDecls_return retval = new JavaParser.formalParameterDecls_return();
        retval.start = input.LT(1);
        int formalParameterDecls_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal195=null;
        Token char_literal198=null;
        JavaParser.ellipsisParameterDecl_return ellipsisParameterDecl193 = null;

        JavaParser.normalParameterDecl_return normalParameterDecl194 = null;

        JavaParser.normalParameterDecl_return normalParameterDecl196 = null;

        JavaParser.normalParameterDecl_return normalParameterDecl197 = null;

        JavaParser.ellipsisParameterDecl_return ellipsisParameterDecl199 = null;


        Object char_literal195_tree=null;
        Object char_literal198_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:713:5: ( ellipsisParameterDecl | normalParameterDecl ( ',' normalParameterDecl )* | ( normalParameterDecl ',' )+ ellipsisParameterDecl )
            int alt72=3;
            switch ( input.LA(1) ) {
            case FINAL:
                {
                int LA72_1 = input.LA(2);

                if ( (synpred96_Java()) ) {
                    alt72=1;
                }
                else if ( (synpred98_Java()) ) {
                    alt72=2;
                }
                else if ( (true) ) {
                    alt72=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 72, 1, input);

                    throw nvae;
                }
                }
                break;
            case MONKEYS_AT:
                {
                int LA72_2 = input.LA(2);

                if ( (synpred96_Java()) ) {
                    alt72=1;
                }
                else if ( (synpred98_Java()) ) {
                    alt72=2;
                }
                else if ( (true) ) {
                    alt72=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 72, 2, input);

                    throw nvae;
                }
                }
                break;
            case IDENTIFIER:
                {
                int LA72_3 = input.LA(2);

                if ( (synpred96_Java()) ) {
                    alt72=1;
                }
                else if ( (synpred98_Java()) ) {
                    alt72=2;
                }
                else if ( (true) ) {
                    alt72=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 72, 3, input);

                    throw nvae;
                }
                }
                break;
            case BOOLEAN:
            case BYTE:
            case CHAR:
            case DOUBLE:
            case FLOAT:
            case INT:
            case LONG:
            case SHORT:
                {
                int LA72_4 = input.LA(2);

                if ( (synpred96_Java()) ) {
                    alt72=1;
                }
                else if ( (synpred98_Java()) ) {
                    alt72=2;
                }
                else if ( (true) ) {
                    alt72=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 72, 4, input);

                    throw nvae;
                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;
            }

            switch (alt72) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:713:9: ellipsisParameterDecl
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ellipsisParameterDecl_in_formalParameterDecls3159);
                    ellipsisParameterDecl193=ellipsisParameterDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ellipsisParameterDecl193.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:714:9: normalParameterDecl ( ',' normalParameterDecl )*
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_normalParameterDecl_in_formalParameterDecls3169);
                    normalParameterDecl194=normalParameterDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, normalParameterDecl194.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:715:9: ( ',' normalParameterDecl )*
                    loop70:
                    do {
                        int alt70=2;
                        int LA70_0 = input.LA(1);

                        if ( (LA70_0==COMMA) ) {
                            alt70=1;
                        }


                        switch (alt70) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:715:10: ',' normalParameterDecl
                    	    {
                    	    char_literal195=(Token)match(input,COMMA,FOLLOW_COMMA_in_formalParameterDecls3180); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal195_tree = (Object)adaptor.create(char_literal195);
                    	    adaptor.addChild(root_0, char_literal195_tree);
                    	    }
                    	    pushFollow(FOLLOW_normalParameterDecl_in_formalParameterDecls3182);
                    	    normalParameterDecl196=normalParameterDecl();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, normalParameterDecl196.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop70;
                        }
                    } while (true);


                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:717:9: ( normalParameterDecl ',' )+ ellipsisParameterDecl
                    {
                    root_0 = (Object)adaptor.nil();

                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:717:9: ( normalParameterDecl ',' )+
                    int cnt71=0;
                    loop71:
                    do {
                        int alt71=2;
                        switch ( input.LA(1) ) {
                        case FINAL:
                            {
                            int LA71_1 = input.LA(2);

                            if ( (synpred99_Java()) ) {
                                alt71=1;
                            }


                            }
                            break;
                        case MONKEYS_AT:
                            {
                            int LA71_2 = input.LA(2);

                            if ( (synpred99_Java()) ) {
                                alt71=1;
                            }


                            }
                            break;
                        case IDENTIFIER:
                            {
                            int LA71_3 = input.LA(2);

                            if ( (synpred99_Java()) ) {
                                alt71=1;
                            }


                            }
                            break;
                        case BOOLEAN:
                        case BYTE:
                        case CHAR:
                        case DOUBLE:
                        case FLOAT:
                        case INT:
                        case LONG:
                        case SHORT:
                            {
                            int LA71_4 = input.LA(2);

                            if ( (synpred99_Java()) ) {
                                alt71=1;
                            }


                            }
                            break;

                        }

                        switch (alt71) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:717:10: normalParameterDecl ','
                    	    {
                    	    pushFollow(FOLLOW_normalParameterDecl_in_formalParameterDecls3204);
                    	    normalParameterDecl197=normalParameterDecl();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, normalParameterDecl197.getTree());
                    	    char_literal198=(Token)match(input,COMMA,FOLLOW_COMMA_in_formalParameterDecls3214); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal198_tree = (Object)adaptor.create(char_literal198);
                    	    adaptor.addChild(root_0, char_literal198_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt71 >= 1 ) break loop71;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(71, input);
                                throw eee;
                        }
                        cnt71++;
                    } while (true);

                    pushFollow(FOLLOW_ellipsisParameterDecl_in_formalParameterDecls3236);
                    ellipsisParameterDecl199=ellipsisParameterDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ellipsisParameterDecl199.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 39, formalParameterDecls_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "formalParameterDecls"

    public static class normalParameterDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "normalParameterDecl"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:723:1: normalParameterDecl : variableModifiers type IDENTIFIER ( '[' ']' )* ;
    public final JavaParser.normalParameterDecl_return normalParameterDecl() throws RecognitionException {
        JavaParser.normalParameterDecl_return retval = new JavaParser.normalParameterDecl_return();
        retval.start = input.LT(1);
        int normalParameterDecl_StartIndex = input.index();
        Object root_0 = null;

        Token IDENTIFIER202=null;
        Token char_literal203=null;
        Token char_literal204=null;
        JavaParser.variableModifiers_return variableModifiers200 = null;

        JavaParser.type_return type201 = null;


        Object IDENTIFIER202_tree=null;
        Object char_literal203_tree=null;
        Object char_literal204_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:724:5: ( variableModifiers type IDENTIFIER ( '[' ']' )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:724:9: variableModifiers type IDENTIFIER ( '[' ']' )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_variableModifiers_in_normalParameterDecl3256);
            variableModifiers200=variableModifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variableModifiers200.getTree());
            pushFollow(FOLLOW_type_in_normalParameterDecl3258);
            type201=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, type201.getTree());
            IDENTIFIER202=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_normalParameterDecl3260); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER202_tree = (Object)adaptor.create(IDENTIFIER202);
            adaptor.addChild(root_0, IDENTIFIER202_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:725:9: ( '[' ']' )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==LBRACKET) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:725:10: '[' ']'
            	    {
            	    char_literal203=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_normalParameterDecl3271); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal203_tree = (Object)adaptor.create(char_literal203);
            	    adaptor.addChild(root_0, char_literal203_tree);
            	    }
            	    char_literal204=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_normalParameterDecl3273); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal204_tree = (Object)adaptor.create(char_literal204);
            	    adaptor.addChild(root_0, char_literal204_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop73;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 40, normalParameterDecl_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "normalParameterDecl"

    public static class ellipsisParameterDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ellipsisParameterDecl"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:729:1: ellipsisParameterDecl : variableModifiers type '...' IDENTIFIER ;
    public final JavaParser.ellipsisParameterDecl_return ellipsisParameterDecl() throws RecognitionException {
        JavaParser.ellipsisParameterDecl_return retval = new JavaParser.ellipsisParameterDecl_return();
        retval.start = input.LT(1);
        int ellipsisParameterDecl_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal207=null;
        Token IDENTIFIER208=null;
        JavaParser.variableModifiers_return variableModifiers205 = null;

        JavaParser.type_return type206 = null;


        Object string_literal207_tree=null;
        Object IDENTIFIER208_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:730:5: ( variableModifiers type '...' IDENTIFIER )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:730:9: variableModifiers type '...' IDENTIFIER
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_variableModifiers_in_ellipsisParameterDecl3304);
            variableModifiers205=variableModifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variableModifiers205.getTree());
            pushFollow(FOLLOW_type_in_ellipsisParameterDecl3314);
            type206=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, type206.getTree());
            string_literal207=(Token)match(input,ELLIPSIS,FOLLOW_ELLIPSIS_in_ellipsisParameterDecl3317); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal207_tree = (Object)adaptor.create(string_literal207);
            adaptor.addChild(root_0, string_literal207_tree);
            }
            IDENTIFIER208=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_ellipsisParameterDecl3327); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER208_tree = (Object)adaptor.create(IDENTIFIER208);
            adaptor.addChild(root_0, IDENTIFIER208_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 41, ellipsisParameterDecl_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "ellipsisParameterDecl"

    public static class explicitConstructorInvocation_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "explicitConstructorInvocation"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:736:1: explicitConstructorInvocation : ( ( nonWildcardTypeArguments )? ( 'this' | 'super' ) arguments ';' | primary '.' ( nonWildcardTypeArguments )? 'super' arguments ';' );
    public final JavaParser.explicitConstructorInvocation_return explicitConstructorInvocation() throws RecognitionException {
        JavaParser.explicitConstructorInvocation_return retval = new JavaParser.explicitConstructorInvocation_return();
        retval.start = input.LT(1);
        int explicitConstructorInvocation_StartIndex = input.index();
        Object root_0 = null;

        Token set210=null;
        Token char_literal212=null;
        Token char_literal214=null;
        Token string_literal216=null;
        Token char_literal218=null;
        JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments209 = null;

        JavaParser.arguments_return arguments211 = null;

        JavaParser.primary_return primary213 = null;

        JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments215 = null;

        JavaParser.arguments_return arguments217 = null;


        Object set210_tree=null;
        Object char_literal212_tree=null;
        Object char_literal214_tree=null;
        Object string_literal216_tree=null;
        Object char_literal218_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:737:5: ( ( nonWildcardTypeArguments )? ( 'this' | 'super' ) arguments ';' | primary '.' ( nonWildcardTypeArguments )? 'super' arguments ';' )
            int alt76=2;
            alt76 = dfa76.predict(input);
            switch (alt76) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:737:9: ( nonWildcardTypeArguments )? ( 'this' | 'super' ) arguments ';'
                    {
                    root_0 = (Object)adaptor.nil();

                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:737:9: ( nonWildcardTypeArguments )?
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( (LA74_0==LT) ) {
                        alt74=1;
                    }
                    switch (alt74) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:737:10: nonWildcardTypeArguments
                            {
                            pushFollow(FOLLOW_nonWildcardTypeArguments_in_explicitConstructorInvocation3349);
                            nonWildcardTypeArguments209=nonWildcardTypeArguments();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, nonWildcardTypeArguments209.getTree());

                            }
                            break;

                    }

                    set210=(Token)input.LT(1);
                    if ( input.LA(1)==SUPER||input.LA(1)==THIS ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set210));
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    pushFollow(FOLLOW_arguments_in_explicitConstructorInvocation3407);
                    arguments211=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments211.getTree());
                    char_literal212=(Token)match(input,SEMI,FOLLOW_SEMI_in_explicitConstructorInvocation3409); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal212_tree = (Object)adaptor.create(char_literal212);
                    adaptor.addChild(root_0, char_literal212_tree);
                    }

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:744:9: primary '.' ( nonWildcardTypeArguments )? 'super' arguments ';'
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_primary_in_explicitConstructorInvocation3420);
                    primary213=primary();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, primary213.getTree());
                    char_literal214=(Token)match(input,DOT,FOLLOW_DOT_in_explicitConstructorInvocation3430); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal214_tree = (Object)adaptor.create(char_literal214);
                    adaptor.addChild(root_0, char_literal214_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:746:9: ( nonWildcardTypeArguments )?
                    int alt75=2;
                    int LA75_0 = input.LA(1);

                    if ( (LA75_0==LT) ) {
                        alt75=1;
                    }
                    switch (alt75) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:746:10: nonWildcardTypeArguments
                            {
                            pushFollow(FOLLOW_nonWildcardTypeArguments_in_explicitConstructorInvocation3441);
                            nonWildcardTypeArguments215=nonWildcardTypeArguments();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, nonWildcardTypeArguments215.getTree());

                            }
                            break;

                    }

                    string_literal216=(Token)match(input,SUPER,FOLLOW_SUPER_in_explicitConstructorInvocation3462); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal216_tree = (Object)adaptor.create(string_literal216);
                    adaptor.addChild(root_0, string_literal216_tree);
                    }
                    pushFollow(FOLLOW_arguments_in_explicitConstructorInvocation3472);
                    arguments217=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments217.getTree());
                    char_literal218=(Token)match(input,SEMI,FOLLOW_SEMI_in_explicitConstructorInvocation3474); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal218_tree = (Object)adaptor.create(char_literal218);
                    adaptor.addChild(root_0, char_literal218_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 42, explicitConstructorInvocation_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "explicitConstructorInvocation"

    public static class qualifiedName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "qualifiedName"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:752:1: qualifiedName : IDENTIFIER ( '.' IDENTIFIER )* ;
    public final JavaParser.qualifiedName_return qualifiedName() throws RecognitionException {
        JavaParser.qualifiedName_return retval = new JavaParser.qualifiedName_return();
        retval.start = input.LT(1);
        int qualifiedName_StartIndex = input.index();
        Object root_0 = null;

        Token IDENTIFIER219=null;
        Token char_literal220=null;
        Token IDENTIFIER221=null;

        Object IDENTIFIER219_tree=null;
        Object char_literal220_tree=null;
        Object IDENTIFIER221_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:753:5: ( IDENTIFIER ( '.' IDENTIFIER )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:753:9: IDENTIFIER ( '.' IDENTIFIER )*
            {
            root_0 = (Object)adaptor.nil();

            IDENTIFIER219=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_qualifiedName3494); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER219_tree = (Object)adaptor.create(IDENTIFIER219);
            adaptor.addChild(root_0, IDENTIFIER219_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:754:9: ( '.' IDENTIFIER )*
            loop77:
            do {
                int alt77=2;
                int LA77_0 = input.LA(1);

                if ( (LA77_0==DOT) ) {
                    alt77=1;
                }


                switch (alt77) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:754:10: '.' IDENTIFIER
            	    {
            	    char_literal220=(Token)match(input,DOT,FOLLOW_DOT_in_qualifiedName3505); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal220_tree = (Object)adaptor.create(char_literal220);
            	    adaptor.addChild(root_0, char_literal220_tree);
            	    }
            	    IDENTIFIER221=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_qualifiedName3507); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    IDENTIFIER221_tree = (Object)adaptor.create(IDENTIFIER221);
            	    adaptor.addChild(root_0, IDENTIFIER221_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop77;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 43, qualifiedName_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "qualifiedName"

    public static class annotations_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "annotations"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:758:1: annotations : ( annotation )+ ;
    public final JavaParser.annotations_return annotations() throws RecognitionException {
        JavaParser.annotations_return retval = new JavaParser.annotations_return();
        retval.start = input.LT(1);
        int annotations_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.annotation_return annotation222 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:759:5: ( ( annotation )+ )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:759:9: ( annotation )+
            {
            root_0 = (Object)adaptor.nil();

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:759:9: ( annotation )+
            int cnt78=0;
            loop78:
            do {
                int alt78=2;
                int LA78_0 = input.LA(1);

                if ( (LA78_0==MONKEYS_AT) ) {
                    alt78=1;
                }


                switch (alt78) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:759:10: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_annotations3539);
            	    annotation222=annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotation222.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt78 >= 1 ) break loop78;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(78, input);
                        throw eee;
                }
                cnt78++;
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 44, annotations_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "annotations"

    public static class annotation_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "annotation"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:763:1: annotation : '@' qualifiedName ( '(' ( elementValuePairs | elementValue )? ')' )? ;
    public final JavaParser.annotation_return annotation() throws RecognitionException {
        JavaParser.annotation_return retval = new JavaParser.annotation_return();
        retval.start = input.LT(1);
        int annotation_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal223=null;
        Token char_literal225=null;
        Token char_literal228=null;
        JavaParser.qualifiedName_return qualifiedName224 = null;

        JavaParser.elementValuePairs_return elementValuePairs226 = null;

        JavaParser.elementValue_return elementValue227 = null;


        Object char_literal223_tree=null;
        Object char_literal225_tree=null;
        Object char_literal228_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:768:5: ( '@' qualifiedName ( '(' ( elementValuePairs | elementValue )? ')' )? )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:768:9: '@' qualifiedName ( '(' ( elementValuePairs | elementValue )? ')' )?
            {
            root_0 = (Object)adaptor.nil();

            char_literal223=(Token)match(input,MONKEYS_AT,FOLLOW_MONKEYS_AT_in_annotation3572); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal223_tree = (Object)adaptor.create(char_literal223);
            adaptor.addChild(root_0, char_literal223_tree);
            }
            pushFollow(FOLLOW_qualifiedName_in_annotation3574);
            qualifiedName224=qualifiedName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedName224.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:769:9: ( '(' ( elementValuePairs | elementValue )? ')' )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==LPAREN) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:769:13: '(' ( elementValuePairs | elementValue )? ')'
                    {
                    char_literal225=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_annotation3588); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal225_tree = (Object)adaptor.create(char_literal225);
                    adaptor.addChild(root_0, char_literal225_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:770:19: ( elementValuePairs | elementValue )?
                    int alt79=3;
                    int LA79_0 = input.LA(1);

                    if ( (LA79_0==IDENTIFIER) ) {
                        int LA79_1 = input.LA(2);

                        if ( (LA79_1==EQ) ) {
                            alt79=1;
                        }
                        else if ( (LA79_1==INSTANCEOF||(LA79_1>=LPAREN && LA79_1<=RPAREN)||LA79_1==LBRACKET||LA79_1==DOT||LA79_1==QUES||(LA79_1>=EQEQ && LA79_1<=PERCENT)||(LA79_1>=BANGEQ && LA79_1<=LT)) ) {
                            alt79=2;
                        }
                    }
                    else if ( ((LA79_0>=INTLITERAL && LA79_0<=NULL)||LA79_0==BOOLEAN||LA79_0==BYTE||LA79_0==CHAR||LA79_0==DOUBLE||LA79_0==FLOAT||LA79_0==INT||LA79_0==LONG||LA79_0==NEW||LA79_0==SHORT||LA79_0==SUPER||LA79_0==THIS||LA79_0==VOID||LA79_0==LPAREN||LA79_0==LBRACE||(LA79_0>=BANG && LA79_0<=TILDE)||(LA79_0>=PLUSPLUS && LA79_0<=SUB)||LA79_0==MONKEYS_AT) ) {
                        alt79=2;
                    }
                    switch (alt79) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:770:23: elementValuePairs
                            {
                            pushFollow(FOLLOW_elementValuePairs_in_annotation3615);
                            elementValuePairs226=elementValuePairs();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, elementValuePairs226.getTree());

                            }
                            break;
                        case 2 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:771:23: elementValue
                            {
                            pushFollow(FOLLOW_elementValue_in_annotation3639);
                            elementValue227=elementValue();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, elementValue227.getTree());

                            }
                            break;

                    }

                    char_literal228=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_annotation3675); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal228_tree = (Object)adaptor.create(char_literal228);
                    adaptor.addChild(root_0, char_literal228_tree);
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 45, annotation_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "annotation"

    public static class elementValuePairs_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elementValuePairs"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:777:1: elementValuePairs : elementValuePair ( ',' elementValuePair )* ;
    public final JavaParser.elementValuePairs_return elementValuePairs() throws RecognitionException {
        JavaParser.elementValuePairs_return retval = new JavaParser.elementValuePairs_return();
        retval.start = input.LT(1);
        int elementValuePairs_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal230=null;
        JavaParser.elementValuePair_return elementValuePair229 = null;

        JavaParser.elementValuePair_return elementValuePair231 = null;


        Object char_literal230_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 46) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:778:5: ( elementValuePair ( ',' elementValuePair )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:778:9: elementValuePair ( ',' elementValuePair )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_elementValuePair_in_elementValuePairs3707);
            elementValuePair229=elementValuePair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, elementValuePair229.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:779:9: ( ',' elementValuePair )*
            loop81:
            do {
                int alt81=2;
                int LA81_0 = input.LA(1);

                if ( (LA81_0==COMMA) ) {
                    alt81=1;
                }


                switch (alt81) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:779:10: ',' elementValuePair
            	    {
            	    char_literal230=(Token)match(input,COMMA,FOLLOW_COMMA_in_elementValuePairs3718); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal230_tree = (Object)adaptor.create(char_literal230);
            	    adaptor.addChild(root_0, char_literal230_tree);
            	    }
            	    pushFollow(FOLLOW_elementValuePair_in_elementValuePairs3720);
            	    elementValuePair231=elementValuePair();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, elementValuePair231.getTree());

            	    }
            	    break;

            	default :
            	    break loop81;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 46, elementValuePairs_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "elementValuePairs"

    public static class elementValuePair_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elementValuePair"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:783:1: elementValuePair : IDENTIFIER '=' elementValue ;
    public final JavaParser.elementValuePair_return elementValuePair() throws RecognitionException {
        JavaParser.elementValuePair_return retval = new JavaParser.elementValuePair_return();
        retval.start = input.LT(1);
        int elementValuePair_StartIndex = input.index();
        Object root_0 = null;

        Token IDENTIFIER232=null;
        Token char_literal233=null;
        JavaParser.elementValue_return elementValue234 = null;


        Object IDENTIFIER232_tree=null;
        Object char_literal233_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 47) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:784:5: ( IDENTIFIER '=' elementValue )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:784:9: IDENTIFIER '=' elementValue
            {
            root_0 = (Object)adaptor.nil();

            IDENTIFIER232=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_elementValuePair3751); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER232_tree = (Object)adaptor.create(IDENTIFIER232);
            adaptor.addChild(root_0, IDENTIFIER232_tree);
            }
            char_literal233=(Token)match(input,EQ,FOLLOW_EQ_in_elementValuePair3753); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal233_tree = (Object)adaptor.create(char_literal233);
            adaptor.addChild(root_0, char_literal233_tree);
            }
            pushFollow(FOLLOW_elementValue_in_elementValuePair3755);
            elementValue234=elementValue();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, elementValue234.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 47, elementValuePair_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "elementValuePair"

    public static class elementValue_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elementValue"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:787:1: elementValue : ( conditionalExpression | annotation | elementValueArrayInitializer );
    public final JavaParser.elementValue_return elementValue() throws RecognitionException {
        JavaParser.elementValue_return retval = new JavaParser.elementValue_return();
        retval.start = input.LT(1);
        int elementValue_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.conditionalExpression_return conditionalExpression235 = null;

        JavaParser.annotation_return annotation236 = null;

        JavaParser.elementValueArrayInitializer_return elementValueArrayInitializer237 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 48) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:788:5: ( conditionalExpression | annotation | elementValueArrayInitializer )
            int alt82=3;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
            case INTLITERAL:
            case LONGLITERAL:
            case FLOATLITERAL:
            case DOUBLELITERAL:
            case CHARLITERAL:
            case STRINGLITERAL:
            case TRUE:
            case FALSE:
            case NULL:
            case BOOLEAN:
            case BYTE:
            case CHAR:
            case DOUBLE:
            case FLOAT:
            case INT:
            case LONG:
            case NEW:
            case SHORT:
            case SUPER:
            case THIS:
            case VOID:
            case LPAREN:
            case BANG:
            case TILDE:
            case PLUSPLUS:
            case SUBSUB:
            case PLUS:
            case SUB:
                {
                alt82=1;
                }
                break;
            case MONKEYS_AT:
                {
                alt82=2;
                }
                break;
            case LBRACE:
                {
                alt82=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 82, 0, input);

                throw nvae;
            }

            switch (alt82) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:788:9: conditionalExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_conditionalExpression_in_elementValue3775);
                    conditionalExpression235=conditionalExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalExpression235.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:789:9: annotation
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_annotation_in_elementValue3785);
                    annotation236=annotation();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotation236.getTree());

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:790:9: elementValueArrayInitializer
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_elementValueArrayInitializer_in_elementValue3795);
                    elementValueArrayInitializer237=elementValueArrayInitializer();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, elementValueArrayInitializer237.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 48, elementValue_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "elementValue"

    public static class elementValueArrayInitializer_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elementValueArrayInitializer"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:793:1: elementValueArrayInitializer : '{' ( elementValue ( ',' elementValue )* )? ( ',' )? '}' ;
    public final JavaParser.elementValueArrayInitializer_return elementValueArrayInitializer() throws RecognitionException {
        JavaParser.elementValueArrayInitializer_return retval = new JavaParser.elementValueArrayInitializer_return();
        retval.start = input.LT(1);
        int elementValueArrayInitializer_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal238=null;
        Token char_literal240=null;
        Token char_literal242=null;
        Token char_literal243=null;
        JavaParser.elementValue_return elementValue239 = null;

        JavaParser.elementValue_return elementValue241 = null;


        Object char_literal238_tree=null;
        Object char_literal240_tree=null;
        Object char_literal242_tree=null;
        Object char_literal243_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 49) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:794:5: ( '{' ( elementValue ( ',' elementValue )* )? ( ',' )? '}' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:794:9: '{' ( elementValue ( ',' elementValue )* )? ( ',' )? '}'
            {
            root_0 = (Object)adaptor.nil();

            char_literal238=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_elementValueArrayInitializer3815); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal238_tree = (Object)adaptor.create(char_literal238);
            adaptor.addChild(root_0, char_literal238_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:795:9: ( elementValue ( ',' elementValue )* )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( ((LA84_0>=IDENTIFIER && LA84_0<=NULL)||LA84_0==BOOLEAN||LA84_0==BYTE||LA84_0==CHAR||LA84_0==DOUBLE||LA84_0==FLOAT||LA84_0==INT||LA84_0==LONG||LA84_0==NEW||LA84_0==SHORT||LA84_0==SUPER||LA84_0==THIS||LA84_0==VOID||LA84_0==LPAREN||LA84_0==LBRACE||(LA84_0>=BANG && LA84_0<=TILDE)||(LA84_0>=PLUSPLUS && LA84_0<=SUB)||LA84_0==MONKEYS_AT) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:795:10: elementValue ( ',' elementValue )*
                    {
                    pushFollow(FOLLOW_elementValue_in_elementValueArrayInitializer3826);
                    elementValue239=elementValue();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, elementValue239.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:796:13: ( ',' elementValue )*
                    loop83:
                    do {
                        int alt83=2;
                        int LA83_0 = input.LA(1);

                        if ( (LA83_0==COMMA) ) {
                            int LA83_1 = input.LA(2);

                            if ( ((LA83_1>=IDENTIFIER && LA83_1<=NULL)||LA83_1==BOOLEAN||LA83_1==BYTE||LA83_1==CHAR||LA83_1==DOUBLE||LA83_1==FLOAT||LA83_1==INT||LA83_1==LONG||LA83_1==NEW||LA83_1==SHORT||LA83_1==SUPER||LA83_1==THIS||LA83_1==VOID||LA83_1==LPAREN||LA83_1==LBRACE||(LA83_1>=BANG && LA83_1<=TILDE)||(LA83_1>=PLUSPLUS && LA83_1<=SUB)||LA83_1==MONKEYS_AT) ) {
                                alt83=1;
                            }


                        }


                        switch (alt83) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:796:14: ',' elementValue
                    	    {
                    	    char_literal240=(Token)match(input,COMMA,FOLLOW_COMMA_in_elementValueArrayInitializer3841); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal240_tree = (Object)adaptor.create(char_literal240);
                    	    adaptor.addChild(root_0, char_literal240_tree);
                    	    }
                    	    pushFollow(FOLLOW_elementValue_in_elementValueArrayInitializer3843);
                    	    elementValue241=elementValue();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, elementValue241.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop83;
                        }
                    } while (true);


                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:798:12: ( ',' )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==COMMA) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:798:13: ','
                    {
                    char_literal242=(Token)match(input,COMMA,FOLLOW_COMMA_in_elementValueArrayInitializer3872); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal242_tree = (Object)adaptor.create(char_literal242);
                    adaptor.addChild(root_0, char_literal242_tree);
                    }

                    }
                    break;

            }

            char_literal243=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_elementValueArrayInitializer3876); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal243_tree = (Object)adaptor.create(char_literal243);
            adaptor.addChild(root_0, char_literal243_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 49, elementValueArrayInitializer_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "elementValueArrayInitializer"

    public static class annotationTypeDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "annotationTypeDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:802:1: annotationTypeDeclaration : modifiers '@' 'interface' IDENTIFIER annotationTypeBody ;
    public final JavaParser.annotationTypeDeclaration_return annotationTypeDeclaration() throws RecognitionException {
        JavaParser.annotationTypeDeclaration_return retval = new JavaParser.annotationTypeDeclaration_return();
        retval.start = input.LT(1);
        int annotationTypeDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal245=null;
        Token string_literal246=null;
        Token IDENTIFIER247=null;
        JavaParser.modifiers_return modifiers244 = null;

        JavaParser.annotationTypeBody_return annotationTypeBody248 = null;


        Object char_literal245_tree=null;
        Object string_literal246_tree=null;
        Object IDENTIFIER247_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 50) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:806:5: ( modifiers '@' 'interface' IDENTIFIER annotationTypeBody )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:806:9: modifiers '@' 'interface' IDENTIFIER annotationTypeBody
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_modifiers_in_annotationTypeDeclaration3899);
            modifiers244=modifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers244.getTree());
            char_literal245=(Token)match(input,MONKEYS_AT,FOLLOW_MONKEYS_AT_in_annotationTypeDeclaration3901); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal245_tree = (Object)adaptor.create(char_literal245);
            adaptor.addChild(root_0, char_literal245_tree);
            }
            string_literal246=(Token)match(input,INTERFACE,FOLLOW_INTERFACE_in_annotationTypeDeclaration3911); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal246_tree = (Object)adaptor.create(string_literal246);
            adaptor.addChild(root_0, string_literal246_tree);
            }
            IDENTIFIER247=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_annotationTypeDeclaration3921); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER247_tree = (Object)adaptor.create(IDENTIFIER247);
            adaptor.addChild(root_0, IDENTIFIER247_tree);
            }
            pushFollow(FOLLOW_annotationTypeBody_in_annotationTypeDeclaration3931);
            annotationTypeBody248=annotationTypeBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, annotationTypeBody248.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 50, annotationTypeDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "annotationTypeDeclaration"

    public static class annotationTypeBody_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "annotationTypeBody"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:813:1: annotationTypeBody : '{' ( annotationTypeElementDeclaration )* '}' ;
    public final JavaParser.annotationTypeBody_return annotationTypeBody() throws RecognitionException {
        JavaParser.annotationTypeBody_return retval = new JavaParser.annotationTypeBody_return();
        retval.start = input.LT(1);
        int annotationTypeBody_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal249=null;
        Token char_literal251=null;
        JavaParser.annotationTypeElementDeclaration_return annotationTypeElementDeclaration250 = null;


        Object char_literal249_tree=null;
        Object char_literal251_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 51) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:814:5: ( '{' ( annotationTypeElementDeclaration )* '}' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:814:9: '{' ( annotationTypeElementDeclaration )* '}'
            {
            root_0 = (Object)adaptor.nil();

            char_literal249=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_annotationTypeBody3952); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal249_tree = (Object)adaptor.create(char_literal249);
            adaptor.addChild(root_0, char_literal249_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:815:9: ( annotationTypeElementDeclaration )*
            loop86:
            do {
                int alt86=2;
                int LA86_0 = input.LA(1);

                if ( (LA86_0==IDENTIFIER||LA86_0==ABSTRACT||LA86_0==BOOLEAN||LA86_0==BYTE||(LA86_0>=CHAR && LA86_0<=CLASS)||LA86_0==DOUBLE||LA86_0==ENUM||LA86_0==FINAL||LA86_0==FLOAT||(LA86_0>=INT && LA86_0<=NATIVE)||(LA86_0>=PRIVATE && LA86_0<=PUBLIC)||(LA86_0>=SHORT && LA86_0<=STRICTFP)||LA86_0==SYNCHRONIZED||LA86_0==TRANSIENT||(LA86_0>=VOID && LA86_0<=VOLATILE)||LA86_0==SEMI||LA86_0==MONKEYS_AT||LA86_0==LT) ) {
                    alt86=1;
                }


                switch (alt86) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:815:10: annotationTypeElementDeclaration
            	    {
            	    pushFollow(FOLLOW_annotationTypeElementDeclaration_in_annotationTypeBody3964);
            	    annotationTypeElementDeclaration250=annotationTypeElementDeclaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotationTypeElementDeclaration250.getTree());

            	    }
            	    break;

            	default :
            	    break loop86;
                }
            } while (true);

            char_literal251=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_annotationTypeBody3986); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal251_tree = (Object)adaptor.create(char_literal251);
            adaptor.addChild(root_0, char_literal251_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 51, annotationTypeBody_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "annotationTypeBody"

    public static class annotationTypeElementDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "annotationTypeElementDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:820:1: annotationTypeElementDeclaration : ( annotationMethodDeclaration | interfaceFieldDeclaration | normalClassDeclaration | normalInterfaceDeclaration | enumDeclaration | annotationTypeDeclaration | ';' );
    public final JavaParser.annotationTypeElementDeclaration_return annotationTypeElementDeclaration() throws RecognitionException {
        JavaParser.annotationTypeElementDeclaration_return retval = new JavaParser.annotationTypeElementDeclaration_return();
        retval.start = input.LT(1);
        int annotationTypeElementDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal258=null;
        JavaParser.annotationMethodDeclaration_return annotationMethodDeclaration252 = null;

        JavaParser.interfaceFieldDeclaration_return interfaceFieldDeclaration253 = null;

        JavaParser.normalClassDeclaration_return normalClassDeclaration254 = null;

        JavaParser.normalInterfaceDeclaration_return normalInterfaceDeclaration255 = null;

        JavaParser.enumDeclaration_return enumDeclaration256 = null;

        JavaParser.annotationTypeDeclaration_return annotationTypeDeclaration257 = null;


        Object char_literal258_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 52) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:824:5: ( annotationMethodDeclaration | interfaceFieldDeclaration | normalClassDeclaration | normalInterfaceDeclaration | enumDeclaration | annotationTypeDeclaration | ';' )
            int alt87=7;
            alt87 = dfa87.predict(input);
            switch (alt87) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:824:9: annotationMethodDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_annotationMethodDeclaration_in_annotationTypeElementDeclaration4008);
                    annotationMethodDeclaration252=annotationMethodDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotationMethodDeclaration252.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:825:9: interfaceFieldDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_interfaceFieldDeclaration_in_annotationTypeElementDeclaration4018);
                    interfaceFieldDeclaration253=interfaceFieldDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceFieldDeclaration253.getTree());

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:826:9: normalClassDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_normalClassDeclaration_in_annotationTypeElementDeclaration4028);
                    normalClassDeclaration254=normalClassDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, normalClassDeclaration254.getTree());

                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:827:9: normalInterfaceDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_normalInterfaceDeclaration_in_annotationTypeElementDeclaration4038);
                    normalInterfaceDeclaration255=normalInterfaceDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, normalInterfaceDeclaration255.getTree());

                    }
                    break;
                case 5 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:828:9: enumDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_enumDeclaration_in_annotationTypeElementDeclaration4048);
                    enumDeclaration256=enumDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, enumDeclaration256.getTree());

                    }
                    break;
                case 6 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:829:9: annotationTypeDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_annotationTypeDeclaration_in_annotationTypeElementDeclaration4058);
                    annotationTypeDeclaration257=annotationTypeDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotationTypeDeclaration257.getTree());

                    }
                    break;
                case 7 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:830:9: ';'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal258=(Token)match(input,SEMI,FOLLOW_SEMI_in_annotationTypeElementDeclaration4068); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal258_tree = (Object)adaptor.create(char_literal258);
                    adaptor.addChild(root_0, char_literal258_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 52, annotationTypeElementDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "annotationTypeElementDeclaration"

    public static class annotationMethodDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "annotationMethodDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:833:1: annotationMethodDeclaration : modifiers type IDENTIFIER '(' ')' ( 'default' elementValue )? ';' ;
    public final JavaParser.annotationMethodDeclaration_return annotationMethodDeclaration() throws RecognitionException {
        JavaParser.annotationMethodDeclaration_return retval = new JavaParser.annotationMethodDeclaration_return();
        retval.start = input.LT(1);
        int annotationMethodDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token IDENTIFIER261=null;
        Token char_literal262=null;
        Token char_literal263=null;
        Token string_literal264=null;
        Token char_literal266=null;
        JavaParser.modifiers_return modifiers259 = null;

        JavaParser.type_return type260 = null;

        JavaParser.elementValue_return elementValue265 = null;


        Object IDENTIFIER261_tree=null;
        Object char_literal262_tree=null;
        Object char_literal263_tree=null;
        Object string_literal264_tree=null;
        Object char_literal266_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 53) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:834:5: ( modifiers type IDENTIFIER '(' ')' ( 'default' elementValue )? ';' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:834:9: modifiers type IDENTIFIER '(' ')' ( 'default' elementValue )? ';'
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_modifiers_in_annotationMethodDeclaration4088);
            modifiers259=modifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers259.getTree());
            pushFollow(FOLLOW_type_in_annotationMethodDeclaration4090);
            type260=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, type260.getTree());
            IDENTIFIER261=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_annotationMethodDeclaration4092); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER261_tree = (Object)adaptor.create(IDENTIFIER261);
            adaptor.addChild(root_0, IDENTIFIER261_tree);
            }
            char_literal262=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_annotationMethodDeclaration4102); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal262_tree = (Object)adaptor.create(char_literal262);
            adaptor.addChild(root_0, char_literal262_tree);
            }
            char_literal263=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_annotationMethodDeclaration4104); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal263_tree = (Object)adaptor.create(char_literal263);
            adaptor.addChild(root_0, char_literal263_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:835:17: ( 'default' elementValue )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==DEFAULT) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:835:18: 'default' elementValue
                    {
                    string_literal264=(Token)match(input,DEFAULT,FOLLOW_DEFAULT_in_annotationMethodDeclaration4107); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal264_tree = (Object)adaptor.create(string_literal264);
                    adaptor.addChild(root_0, string_literal264_tree);
                    }
                    pushFollow(FOLLOW_elementValue_in_annotationMethodDeclaration4109);
                    elementValue265=elementValue();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, elementValue265.getTree());

                    }
                    break;

            }

            char_literal266=(Token)match(input,SEMI,FOLLOW_SEMI_in_annotationMethodDeclaration4138); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal266_tree = (Object)adaptor.create(char_literal266);
            adaptor.addChild(root_0, char_literal266_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 53, annotationMethodDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "annotationMethodDeclaration"

    public static class block_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "block"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:840:1: block : '{' ( blockStatement )* '}' ;
    public final JavaParser.block_return block() throws RecognitionException {
        JavaParser.block_return retval = new JavaParser.block_return();
        retval.start = input.LT(1);
        int block_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal267=null;
        Token char_literal269=null;
        JavaParser.blockStatement_return blockStatement268 = null;


        Object char_literal267_tree=null;
        Object char_literal269_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 54) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:841:5: ( '{' ( blockStatement )* '}' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:841:9: '{' ( blockStatement )* '}'
            {
            root_0 = (Object)adaptor.nil();

            char_literal267=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_block4162); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal267_tree = (Object)adaptor.create(char_literal267);
            adaptor.addChild(root_0, char_literal267_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:842:9: ( blockStatement )*
            loop89:
            do {
                int alt89=2;
                int LA89_0 = input.LA(1);

                if ( ((LA89_0>=IDENTIFIER && LA89_0<=NULL)||(LA89_0>=ABSTRACT && LA89_0<=BYTE)||(LA89_0>=CHAR && LA89_0<=CLASS)||LA89_0==CONTINUE||(LA89_0>=DO && LA89_0<=DOUBLE)||LA89_0==ENUM||LA89_0==FINAL||(LA89_0>=FLOAT && LA89_0<=FOR)||LA89_0==IF||(LA89_0>=INT && LA89_0<=NEW)||(LA89_0>=PRIVATE && LA89_0<=THROW)||(LA89_0>=TRANSIENT && LA89_0<=LPAREN)||LA89_0==LBRACE||LA89_0==SEMI||(LA89_0>=BANG && LA89_0<=TILDE)||(LA89_0>=PLUSPLUS && LA89_0<=SUB)||LA89_0==MONKEYS_AT||LA89_0==LT) ) {
                    alt89=1;
                }


                switch (alt89) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:842:10: blockStatement
            	    {
            	    pushFollow(FOLLOW_blockStatement_in_block4173);
            	    blockStatement268=blockStatement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, blockStatement268.getTree());

            	    }
            	    break;

            	default :
            	    break loop89;
                }
            } while (true);

            char_literal269=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_block4194); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal269_tree = (Object)adaptor.create(char_literal269);
            adaptor.addChild(root_0, char_literal269_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 54, block_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "block"

    public static class blockStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "blockStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:871:1: blockStatement : ( localVariableDeclarationStatement | classOrInterfaceDeclaration | statement );
    public final JavaParser.blockStatement_return blockStatement() throws RecognitionException {
        JavaParser.blockStatement_return retval = new JavaParser.blockStatement_return();
        retval.start = input.LT(1);
        int blockStatement_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.localVariableDeclarationStatement_return localVariableDeclarationStatement270 = null;

        JavaParser.classOrInterfaceDeclaration_return classOrInterfaceDeclaration271 = null;

        JavaParser.statement_return statement272 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 55) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:872:5: ( localVariableDeclarationStatement | classOrInterfaceDeclaration | statement )
            int alt90=3;
            alt90 = dfa90.predict(input);
            switch (alt90) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:872:9: localVariableDeclarationStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_localVariableDeclarationStatement_in_blockStatement4216);
                    localVariableDeclarationStatement270=localVariableDeclarationStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, localVariableDeclarationStatement270.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:873:9: classOrInterfaceDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_classOrInterfaceDeclaration_in_blockStatement4226);
                    classOrInterfaceDeclaration271=classOrInterfaceDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classOrInterfaceDeclaration271.getTree());

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:874:9: statement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_statement_in_blockStatement4236);
                    statement272=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement272.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 55, blockStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "blockStatement"

    public static class localVariableDeclarationStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "localVariableDeclarationStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:878:1: localVariableDeclarationStatement : localVariableDeclaration ';' ;
    public final JavaParser.localVariableDeclarationStatement_return localVariableDeclarationStatement() throws RecognitionException {
        JavaParser.localVariableDeclarationStatement_return retval = new JavaParser.localVariableDeclarationStatement_return();
        retval.start = input.LT(1);
        int localVariableDeclarationStatement_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal274=null;
        JavaParser.localVariableDeclaration_return localVariableDeclaration273 = null;


        Object char_literal274_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 56) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:879:5: ( localVariableDeclaration ';' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:879:9: localVariableDeclaration ';'
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_localVariableDeclaration_in_localVariableDeclarationStatement4257);
            localVariableDeclaration273=localVariableDeclaration();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, localVariableDeclaration273.getTree());
            char_literal274=(Token)match(input,SEMI,FOLLOW_SEMI_in_localVariableDeclarationStatement4267); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal274_tree = (Object)adaptor.create(char_literal274);
            adaptor.addChild(root_0, char_literal274_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 56, localVariableDeclarationStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "localVariableDeclarationStatement"

    public static class localVariableDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "localVariableDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:883:1: localVariableDeclaration : variableModifiers type variableDeclarator ( ',' variableDeclarator )* ;
    public final JavaParser.localVariableDeclaration_return localVariableDeclaration() throws RecognitionException {
        JavaParser.localVariableDeclaration_return retval = new JavaParser.localVariableDeclaration_return();
        retval.start = input.LT(1);
        int localVariableDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal278=null;
        JavaParser.variableModifiers_return variableModifiers275 = null;

        JavaParser.type_return type276 = null;

        JavaParser.variableDeclarator_return variableDeclarator277 = null;

        JavaParser.variableDeclarator_return variableDeclarator279 = null;


        Object char_literal278_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 57) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:884:5: ( variableModifiers type variableDeclarator ( ',' variableDeclarator )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:884:9: variableModifiers type variableDeclarator ( ',' variableDeclarator )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_variableModifiers_in_localVariableDeclaration4287);
            variableModifiers275=variableModifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variableModifiers275.getTree());
            pushFollow(FOLLOW_type_in_localVariableDeclaration4289);
            type276=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, type276.getTree());
            pushFollow(FOLLOW_variableDeclarator_in_localVariableDeclaration4299);
            variableDeclarator277=variableDeclarator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclarator277.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:886:9: ( ',' variableDeclarator )*
            loop91:
            do {
                int alt91=2;
                int LA91_0 = input.LA(1);

                if ( (LA91_0==COMMA) ) {
                    alt91=1;
                }


                switch (alt91) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:886:10: ',' variableDeclarator
            	    {
            	    char_literal278=(Token)match(input,COMMA,FOLLOW_COMMA_in_localVariableDeclaration4310); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal278_tree = (Object)adaptor.create(char_literal278);
            	    adaptor.addChild(root_0, char_literal278_tree);
            	    }
            	    pushFollow(FOLLOW_variableDeclarator_in_localVariableDeclaration4312);
            	    variableDeclarator279=variableDeclarator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclarator279.getTree());

            	    }
            	    break;

            	default :
            	    break loop91;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 57, localVariableDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "localVariableDeclaration"

    public static class statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:890:1: statement : ( block | ( 'assert' ) expression ( ':' expression )? ';' | 'assert' expression ( ':' expression )? ';' | 'if' parExpression statement ( 'else' statement )? | forstatement | 'while' parExpression statement | 'do' statement 'while' parExpression ';' | trystatement | 'switch' parExpression '{' switchBlockStatementGroups '}' | 'synchronized' parExpression block | 'return' ( expression )? ';' | 'throw' expression ';' | 'break' ( IDENTIFIER )? ';' | 'continue' ( IDENTIFIER )? ';' | expression ';' | IDENTIFIER ':' statement | ';' );
    public final JavaParser.statement_return statement() throws RecognitionException {
        JavaParser.statement_return retval = new JavaParser.statement_return();
        retval.start = input.LT(1);
        int statement_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal281=null;
        Token char_literal283=null;
        Token char_literal285=null;
        Token string_literal286=null;
        Token char_literal288=null;
        Token char_literal290=null;
        Token string_literal291=null;
        Token string_literal294=null;
        Token string_literal297=null;
        Token string_literal300=null;
        Token string_literal302=null;
        Token char_literal304=null;
        Token string_literal306=null;
        Token char_literal308=null;
        Token char_literal310=null;
        Token string_literal311=null;
        Token string_literal314=null;
        Token char_literal316=null;
        Token string_literal317=null;
        Token char_literal319=null;
        Token string_literal320=null;
        Token IDENTIFIER321=null;
        Token char_literal322=null;
        Token string_literal323=null;
        Token IDENTIFIER324=null;
        Token char_literal325=null;
        Token char_literal327=null;
        Token IDENTIFIER328=null;
        Token char_literal329=null;
        Token char_literal331=null;
        JavaParser.block_return block280 = null;

        JavaParser.expression_return expression282 = null;

        JavaParser.expression_return expression284 = null;

        JavaParser.expression_return expression287 = null;

        JavaParser.expression_return expression289 = null;

        JavaParser.parExpression_return parExpression292 = null;

        JavaParser.statement_return statement293 = null;

        JavaParser.statement_return statement295 = null;

        JavaParser.forstatement_return forstatement296 = null;

        JavaParser.parExpression_return parExpression298 = null;

        JavaParser.statement_return statement299 = null;

        JavaParser.statement_return statement301 = null;

        JavaParser.parExpression_return parExpression303 = null;

        JavaParser.trystatement_return trystatement305 = null;

        JavaParser.parExpression_return parExpression307 = null;

        JavaParser.switchBlockStatementGroups_return switchBlockStatementGroups309 = null;

        JavaParser.parExpression_return parExpression312 = null;

        JavaParser.block_return block313 = null;

        JavaParser.expression_return expression315 = null;

        JavaParser.expression_return expression318 = null;

        JavaParser.expression_return expression326 = null;

        JavaParser.statement_return statement330 = null;


        Object string_literal281_tree=null;
        Object char_literal283_tree=null;
        Object char_literal285_tree=null;
        Object string_literal286_tree=null;
        Object char_literal288_tree=null;
        Object char_literal290_tree=null;
        Object string_literal291_tree=null;
        Object string_literal294_tree=null;
        Object string_literal297_tree=null;
        Object string_literal300_tree=null;
        Object string_literal302_tree=null;
        Object char_literal304_tree=null;
        Object string_literal306_tree=null;
        Object char_literal308_tree=null;
        Object char_literal310_tree=null;
        Object string_literal311_tree=null;
        Object string_literal314_tree=null;
        Object char_literal316_tree=null;
        Object string_literal317_tree=null;
        Object char_literal319_tree=null;
        Object string_literal320_tree=null;
        Object IDENTIFIER321_tree=null;
        Object char_literal322_tree=null;
        Object string_literal323_tree=null;
        Object IDENTIFIER324_tree=null;
        Object char_literal325_tree=null;
        Object char_literal327_tree=null;
        Object IDENTIFIER328_tree=null;
        Object char_literal329_tree=null;
        Object char_literal331_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 58) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:891:5: ( block | ( 'assert' ) expression ( ':' expression )? ';' | 'assert' expression ( ':' expression )? ';' | 'if' parExpression statement ( 'else' statement )? | forstatement | 'while' parExpression statement | 'do' statement 'while' parExpression ';' | trystatement | 'switch' parExpression '{' switchBlockStatementGroups '}' | 'synchronized' parExpression block | 'return' ( expression )? ';' | 'throw' expression ';' | 'break' ( IDENTIFIER )? ';' | 'continue' ( IDENTIFIER )? ';' | expression ';' | IDENTIFIER ':' statement | ';' )
            int alt98=17;
            alt98 = dfa98.predict(input);
            switch (alt98) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:891:9: block
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_block_in_statement4343);
                    block280=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, block280.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:893:9: ( 'assert' ) expression ( ':' expression )? ';'
                    {
                    root_0 = (Object)adaptor.nil();

                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:893:9: ( 'assert' )
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:893:10: 'assert'
                    {
                    string_literal281=(Token)match(input,ASSERT,FOLLOW_ASSERT_in_statement4367); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal281_tree = (Object)adaptor.create(string_literal281);
                    adaptor.addChild(root_0, string_literal281_tree);
                    }

                    }

                    pushFollow(FOLLOW_expression_in_statement4387);
                    expression282=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression282.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:895:20: ( ':' expression )?
                    int alt92=2;
                    int LA92_0 = input.LA(1);

                    if ( (LA92_0==COLON) ) {
                        alt92=1;
                    }
                    switch (alt92) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:895:21: ':' expression
                            {
                            char_literal283=(Token)match(input,COLON,FOLLOW_COLON_in_statement4390); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal283_tree = (Object)adaptor.create(char_literal283);
                            adaptor.addChild(root_0, char_literal283_tree);
                            }
                            pushFollow(FOLLOW_expression_in_statement4392);
                            expression284=expression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression284.getTree());

                            }
                            break;

                    }

                    char_literal285=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4396); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal285_tree = (Object)adaptor.create(char_literal285);
                    adaptor.addChild(root_0, char_literal285_tree);
                    }

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:896:9: 'assert' expression ( ':' expression )? ';'
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal286=(Token)match(input,ASSERT,FOLLOW_ASSERT_in_statement4406); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal286_tree = (Object)adaptor.create(string_literal286);
                    adaptor.addChild(root_0, string_literal286_tree);
                    }
                    pushFollow(FOLLOW_expression_in_statement4409);
                    expression287=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression287.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:896:30: ( ':' expression )?
                    int alt93=2;
                    int LA93_0 = input.LA(1);

                    if ( (LA93_0==COLON) ) {
                        alt93=1;
                    }
                    switch (alt93) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:896:31: ':' expression
                            {
                            char_literal288=(Token)match(input,COLON,FOLLOW_COLON_in_statement4412); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal288_tree = (Object)adaptor.create(char_literal288);
                            adaptor.addChild(root_0, char_literal288_tree);
                            }
                            pushFollow(FOLLOW_expression_in_statement4414);
                            expression289=expression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression289.getTree());

                            }
                            break;

                    }

                    char_literal290=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4418); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal290_tree = (Object)adaptor.create(char_literal290);
                    adaptor.addChild(root_0, char_literal290_tree);
                    }

                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:897:9: 'if' parExpression statement ( 'else' statement )?
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal291=(Token)match(input,IF,FOLLOW_IF_in_statement4440); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal291_tree = (Object)adaptor.create(string_literal291);
                    adaptor.addChild(root_0, string_literal291_tree);
                    }
                    pushFollow(FOLLOW_parExpression_in_statement4442);
                    parExpression292=parExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parExpression292.getTree());
                    pushFollow(FOLLOW_statement_in_statement4444);
                    statement293=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement293.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:897:38: ( 'else' statement )?
                    int alt94=2;
                    int LA94_0 = input.LA(1);

                    if ( (LA94_0==ELSE) ) {
                        int LA94_1 = input.LA(2);

                        if ( (synpred133_Java()) ) {
                            alt94=1;
                        }
                    }
                    switch (alt94) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:897:39: 'else' statement
                            {
                            string_literal294=(Token)match(input,ELSE,FOLLOW_ELSE_in_statement4447); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            string_literal294_tree = (Object)adaptor.create(string_literal294);
                            adaptor.addChild(root_0, string_literal294_tree);
                            }
                            pushFollow(FOLLOW_statement_in_statement4449);
                            statement295=statement();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, statement295.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 5 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:898:9: forstatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forstatement_in_statement4471);
                    forstatement296=forstatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, forstatement296.getTree());

                    }
                    break;
                case 6 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:899:9: 'while' parExpression statement
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal297=(Token)match(input,WHILE,FOLLOW_WHILE_in_statement4481); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal297_tree = (Object)adaptor.create(string_literal297);
                    adaptor.addChild(root_0, string_literal297_tree);
                    }
                    pushFollow(FOLLOW_parExpression_in_statement4483);
                    parExpression298=parExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parExpression298.getTree());
                    pushFollow(FOLLOW_statement_in_statement4485);
                    statement299=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement299.getTree());

                    }
                    break;
                case 7 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:900:9: 'do' statement 'while' parExpression ';'
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal300=(Token)match(input,DO,FOLLOW_DO_in_statement4495); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal300_tree = (Object)adaptor.create(string_literal300);
                    adaptor.addChild(root_0, string_literal300_tree);
                    }
                    pushFollow(FOLLOW_statement_in_statement4497);
                    statement301=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement301.getTree());
                    string_literal302=(Token)match(input,WHILE,FOLLOW_WHILE_in_statement4499); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal302_tree = (Object)adaptor.create(string_literal302);
                    adaptor.addChild(root_0, string_literal302_tree);
                    }
                    pushFollow(FOLLOW_parExpression_in_statement4501);
                    parExpression303=parExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parExpression303.getTree());
                    char_literal304=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4503); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal304_tree = (Object)adaptor.create(char_literal304);
                    adaptor.addChild(root_0, char_literal304_tree);
                    }

                    }
                    break;
                case 8 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:901:9: trystatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_trystatement_in_statement4513);
                    trystatement305=trystatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, trystatement305.getTree());

                    }
                    break;
                case 9 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:902:9: 'switch' parExpression '{' switchBlockStatementGroups '}'
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal306=(Token)match(input,SWITCH,FOLLOW_SWITCH_in_statement4523); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal306_tree = (Object)adaptor.create(string_literal306);
                    adaptor.addChild(root_0, string_literal306_tree);
                    }
                    pushFollow(FOLLOW_parExpression_in_statement4525);
                    parExpression307=parExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parExpression307.getTree());
                    char_literal308=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_statement4527); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal308_tree = (Object)adaptor.create(char_literal308);
                    adaptor.addChild(root_0, char_literal308_tree);
                    }
                    pushFollow(FOLLOW_switchBlockStatementGroups_in_statement4529);
                    switchBlockStatementGroups309=switchBlockStatementGroups();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, switchBlockStatementGroups309.getTree());
                    char_literal310=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_statement4531); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal310_tree = (Object)adaptor.create(char_literal310);
                    adaptor.addChild(root_0, char_literal310_tree);
                    }

                    }
                    break;
                case 10 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:903:9: 'synchronized' parExpression block
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal311=(Token)match(input,SYNCHRONIZED,FOLLOW_SYNCHRONIZED_in_statement4541); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal311_tree = (Object)adaptor.create(string_literal311);
                    adaptor.addChild(root_0, string_literal311_tree);
                    }
                    pushFollow(FOLLOW_parExpression_in_statement4543);
                    parExpression312=parExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parExpression312.getTree());
                    pushFollow(FOLLOW_block_in_statement4545);
                    block313=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, block313.getTree());

                    }
                    break;
                case 11 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:904:9: 'return' ( expression )? ';'
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal314=(Token)match(input,RETURN,FOLLOW_RETURN_in_statement4555); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal314_tree = (Object)adaptor.create(string_literal314);
                    adaptor.addChild(root_0, string_literal314_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:904:18: ( expression )?
                    int alt95=2;
                    int LA95_0 = input.LA(1);

                    if ( ((LA95_0>=IDENTIFIER && LA95_0<=NULL)||LA95_0==BOOLEAN||LA95_0==BYTE||LA95_0==CHAR||LA95_0==DOUBLE||LA95_0==FLOAT||LA95_0==INT||LA95_0==LONG||LA95_0==NEW||LA95_0==SHORT||LA95_0==SUPER||LA95_0==THIS||LA95_0==VOID||LA95_0==LPAREN||(LA95_0>=BANG && LA95_0<=TILDE)||(LA95_0>=PLUSPLUS && LA95_0<=SUB)) ) {
                        alt95=1;
                    }
                    switch (alt95) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:904:19: expression
                            {
                            pushFollow(FOLLOW_expression_in_statement4558);
                            expression315=expression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression315.getTree());

                            }
                            break;

                    }

                    char_literal316=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4563); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal316_tree = (Object)adaptor.create(char_literal316);
                    adaptor.addChild(root_0, char_literal316_tree);
                    }

                    }
                    break;
                case 12 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:905:9: 'throw' expression ';'
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal317=(Token)match(input,THROW,FOLLOW_THROW_in_statement4573); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal317_tree = (Object)adaptor.create(string_literal317);
                    adaptor.addChild(root_0, string_literal317_tree);
                    }
                    pushFollow(FOLLOW_expression_in_statement4575);
                    expression318=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression318.getTree());
                    char_literal319=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4577); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal319_tree = (Object)adaptor.create(char_literal319);
                    adaptor.addChild(root_0, char_literal319_tree);
                    }

                    }
                    break;
                case 13 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:906:9: 'break' ( IDENTIFIER )? ';'
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal320=(Token)match(input,BREAK,FOLLOW_BREAK_in_statement4587); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal320_tree = (Object)adaptor.create(string_literal320);
                    adaptor.addChild(root_0, string_literal320_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:907:13: ( IDENTIFIER )?
                    int alt96=2;
                    int LA96_0 = input.LA(1);

                    if ( (LA96_0==IDENTIFIER) ) {
                        alt96=1;
                    }
                    switch (alt96) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:907:14: IDENTIFIER
                            {
                            IDENTIFIER321=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_statement4602); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            IDENTIFIER321_tree = (Object)adaptor.create(IDENTIFIER321);
                            adaptor.addChild(root_0, IDENTIFIER321_tree);
                            }

                            }
                            break;

                    }

                    char_literal322=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4619); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal322_tree = (Object)adaptor.create(char_literal322);
                    adaptor.addChild(root_0, char_literal322_tree);
                    }

                    }
                    break;
                case 14 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:909:9: 'continue' ( IDENTIFIER )? ';'
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal323=(Token)match(input,CONTINUE,FOLLOW_CONTINUE_in_statement4629); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal323_tree = (Object)adaptor.create(string_literal323);
                    adaptor.addChild(root_0, string_literal323_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:910:13: ( IDENTIFIER )?
                    int alt97=2;
                    int LA97_0 = input.LA(1);

                    if ( (LA97_0==IDENTIFIER) ) {
                        alt97=1;
                    }
                    switch (alt97) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:910:14: IDENTIFIER
                            {
                            IDENTIFIER324=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_statement4644); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            IDENTIFIER324_tree = (Object)adaptor.create(IDENTIFIER324);
                            adaptor.addChild(root_0, IDENTIFIER324_tree);
                            }

                            }
                            break;

                    }

                    char_literal325=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4661); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal325_tree = (Object)adaptor.create(char_literal325);
                    adaptor.addChild(root_0, char_literal325_tree);
                    }

                    }
                    break;
                case 15 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:912:9: expression ';'
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_expression_in_statement4671);
                    expression326=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression326.getTree());
                    char_literal327=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4674); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal327_tree = (Object)adaptor.create(char_literal327);
                    adaptor.addChild(root_0, char_literal327_tree);
                    }

                    }
                    break;
                case 16 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:913:9: IDENTIFIER ':' statement
                    {
                    root_0 = (Object)adaptor.nil();

                    IDENTIFIER328=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_statement4689); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER328_tree = (Object)adaptor.create(IDENTIFIER328);
                    adaptor.addChild(root_0, IDENTIFIER328_tree);
                    }
                    char_literal329=(Token)match(input,COLON,FOLLOW_COLON_in_statement4691); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal329_tree = (Object)adaptor.create(char_literal329);
                    adaptor.addChild(root_0, char_literal329_tree);
                    }
                    pushFollow(FOLLOW_statement_in_statement4693);
                    statement330=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement330.getTree());

                    }
                    break;
                case 17 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:914:9: ';'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal331=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4703); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal331_tree = (Object)adaptor.create(char_literal331);
                    adaptor.addChild(root_0, char_literal331_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 58, statement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "statement"

    public static class switchBlockStatementGroups_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "switchBlockStatementGroups"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:918:1: switchBlockStatementGroups : ( switchBlockStatementGroup )* ;
    public final JavaParser.switchBlockStatementGroups_return switchBlockStatementGroups() throws RecognitionException {
        JavaParser.switchBlockStatementGroups_return retval = new JavaParser.switchBlockStatementGroups_return();
        retval.start = input.LT(1);
        int switchBlockStatementGroups_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.switchBlockStatementGroup_return switchBlockStatementGroup332 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 59) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:919:5: ( ( switchBlockStatementGroup )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:919:9: ( switchBlockStatementGroup )*
            {
            root_0 = (Object)adaptor.nil();

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:919:9: ( switchBlockStatementGroup )*
            loop99:
            do {
                int alt99=2;
                int LA99_0 = input.LA(1);

                if ( (LA99_0==CASE||LA99_0==DEFAULT) ) {
                    alt99=1;
                }


                switch (alt99) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:919:10: switchBlockStatementGroup
            	    {
            	    pushFollow(FOLLOW_switchBlockStatementGroup_in_switchBlockStatementGroups4725);
            	    switchBlockStatementGroup332=switchBlockStatementGroup();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, switchBlockStatementGroup332.getTree());

            	    }
            	    break;

            	default :
            	    break loop99;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 59, switchBlockStatementGroups_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "switchBlockStatementGroups"

    public static class switchBlockStatementGroup_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "switchBlockStatementGroup"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:922:1: switchBlockStatementGroup : switchLabel ( blockStatement )* ;
    public final JavaParser.switchBlockStatementGroup_return switchBlockStatementGroup() throws RecognitionException {
        JavaParser.switchBlockStatementGroup_return retval = new JavaParser.switchBlockStatementGroup_return();
        retval.start = input.LT(1);
        int switchBlockStatementGroup_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.switchLabel_return switchLabel333 = null;

        JavaParser.blockStatement_return blockStatement334 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 60) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:923:5: ( switchLabel ( blockStatement )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:924:9: switchLabel ( blockStatement )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_switchLabel_in_switchBlockStatementGroup4754);
            switchLabel333=switchLabel();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, switchLabel333.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:925:9: ( blockStatement )*
            loop100:
            do {
                int alt100=2;
                int LA100_0 = input.LA(1);

                if ( ((LA100_0>=IDENTIFIER && LA100_0<=NULL)||(LA100_0>=ABSTRACT && LA100_0<=BYTE)||(LA100_0>=CHAR && LA100_0<=CLASS)||LA100_0==CONTINUE||(LA100_0>=DO && LA100_0<=DOUBLE)||LA100_0==ENUM||LA100_0==FINAL||(LA100_0>=FLOAT && LA100_0<=FOR)||LA100_0==IF||(LA100_0>=INT && LA100_0<=NEW)||(LA100_0>=PRIVATE && LA100_0<=THROW)||(LA100_0>=TRANSIENT && LA100_0<=LPAREN)||LA100_0==LBRACE||LA100_0==SEMI||(LA100_0>=BANG && LA100_0<=TILDE)||(LA100_0>=PLUSPLUS && LA100_0<=SUB)||LA100_0==MONKEYS_AT||LA100_0==LT) ) {
                    alt100=1;
                }


                switch (alt100) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:925:10: blockStatement
            	    {
            	    pushFollow(FOLLOW_blockStatement_in_switchBlockStatementGroup4765);
            	    blockStatement334=blockStatement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, blockStatement334.getTree());

            	    }
            	    break;

            	default :
            	    break loop100;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 60, switchBlockStatementGroup_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "switchBlockStatementGroup"

    public static class switchLabel_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "switchLabel"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:929:1: switchLabel : ( 'case' expression ':' | 'default' ':' );
    public final JavaParser.switchLabel_return switchLabel() throws RecognitionException {
        JavaParser.switchLabel_return retval = new JavaParser.switchLabel_return();
        retval.start = input.LT(1);
        int switchLabel_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal335=null;
        Token char_literal337=null;
        Token string_literal338=null;
        Token char_literal339=null;
        JavaParser.expression_return expression336 = null;


        Object string_literal335_tree=null;
        Object char_literal337_tree=null;
        Object string_literal338_tree=null;
        Object char_literal339_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 61) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:930:5: ( 'case' expression ':' | 'default' ':' )
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==CASE) ) {
                alt101=1;
            }
            else if ( (LA101_0==DEFAULT) ) {
                alt101=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 101, 0, input);

                throw nvae;
            }
            switch (alt101) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:930:9: 'case' expression ':'
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal335=(Token)match(input,CASE,FOLLOW_CASE_in_switchLabel4796); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal335_tree = (Object)adaptor.create(string_literal335);
                    adaptor.addChild(root_0, string_literal335_tree);
                    }
                    pushFollow(FOLLOW_expression_in_switchLabel4798);
                    expression336=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression336.getTree());
                    char_literal337=(Token)match(input,COLON,FOLLOW_COLON_in_switchLabel4800); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal337_tree = (Object)adaptor.create(char_literal337);
                    adaptor.addChild(root_0, char_literal337_tree);
                    }

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:931:9: 'default' ':'
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal338=(Token)match(input,DEFAULT,FOLLOW_DEFAULT_in_switchLabel4810); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal338_tree = (Object)adaptor.create(string_literal338);
                    adaptor.addChild(root_0, string_literal338_tree);
                    }
                    char_literal339=(Token)match(input,COLON,FOLLOW_COLON_in_switchLabel4812); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal339_tree = (Object)adaptor.create(char_literal339);
                    adaptor.addChild(root_0, char_literal339_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 61, switchLabel_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "switchLabel"

    public static class trystatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "trystatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:935:1: trystatement : 'try' block ( catches 'finally' block | catches | 'finally' block ) ;
    public final JavaParser.trystatement_return trystatement() throws RecognitionException {
        JavaParser.trystatement_return retval = new JavaParser.trystatement_return();
        retval.start = input.LT(1);
        int trystatement_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal340=null;
        Token string_literal343=null;
        Token string_literal346=null;
        JavaParser.block_return block341 = null;

        JavaParser.catches_return catches342 = null;

        JavaParser.block_return block344 = null;

        JavaParser.catches_return catches345 = null;

        JavaParser.block_return block347 = null;


        Object string_literal340_tree=null;
        Object string_literal343_tree=null;
        Object string_literal346_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 62) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:936:5: ( 'try' block ( catches 'finally' block | catches | 'finally' block ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:936:9: 'try' block ( catches 'finally' block | catches | 'finally' block )
            {
            root_0 = (Object)adaptor.nil();

            string_literal340=(Token)match(input,TRY,FOLLOW_TRY_in_trystatement4833); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal340_tree = (Object)adaptor.create(string_literal340);
            adaptor.addChild(root_0, string_literal340_tree);
            }
            pushFollow(FOLLOW_block_in_trystatement4835);
            block341=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, block341.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:937:9: ( catches 'finally' block | catches | 'finally' block )
            int alt102=3;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==CATCH) ) {
                int LA102_1 = input.LA(2);

                if ( (synpred153_Java()) ) {
                    alt102=1;
                }
                else if ( (synpred154_Java()) ) {
                    alt102=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 102, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA102_0==FINALLY) ) {
                alt102=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 102, 0, input);

                throw nvae;
            }
            switch (alt102) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:937:13: catches 'finally' block
                    {
                    pushFollow(FOLLOW_catches_in_trystatement4849);
                    catches342=catches();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, catches342.getTree());
                    string_literal343=(Token)match(input,FINALLY,FOLLOW_FINALLY_in_trystatement4851); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal343_tree = (Object)adaptor.create(string_literal343);
                    adaptor.addChild(root_0, string_literal343_tree);
                    }
                    pushFollow(FOLLOW_block_in_trystatement4853);
                    block344=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, block344.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:938:13: catches
                    {
                    pushFollow(FOLLOW_catches_in_trystatement4867);
                    catches345=catches();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, catches345.getTree());

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:939:13: 'finally' block
                    {
                    string_literal346=(Token)match(input,FINALLY,FOLLOW_FINALLY_in_trystatement4881); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal346_tree = (Object)adaptor.create(string_literal346);
                    adaptor.addChild(root_0, string_literal346_tree);
                    }
                    pushFollow(FOLLOW_block_in_trystatement4883);
                    block347=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, block347.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 62, trystatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "trystatement"

    public static class catches_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "catches"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:943:1: catches : catchClause ( catchClause )* ;
    public final JavaParser.catches_return catches() throws RecognitionException {
        JavaParser.catches_return retval = new JavaParser.catches_return();
        retval.start = input.LT(1);
        int catches_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.catchClause_return catchClause348 = null;

        JavaParser.catchClause_return catchClause349 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 63) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:944:5: ( catchClause ( catchClause )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:944:9: catchClause ( catchClause )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_catchClause_in_catches4914);
            catchClause348=catchClause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, catchClause348.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:945:9: ( catchClause )*
            loop103:
            do {
                int alt103=2;
                int LA103_0 = input.LA(1);

                if ( (LA103_0==CATCH) ) {
                    alt103=1;
                }


                switch (alt103) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:945:10: catchClause
            	    {
            	    pushFollow(FOLLOW_catchClause_in_catches4925);
            	    catchClause349=catchClause();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, catchClause349.getTree());

            	    }
            	    break;

            	default :
            	    break loop103;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 63, catches_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "catches"

    public static class catchClause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "catchClause"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:949:1: catchClause : 'catch' '(' formalParameter ')' block ;
    public final JavaParser.catchClause_return catchClause() throws RecognitionException {
        JavaParser.catchClause_return retval = new JavaParser.catchClause_return();
        retval.start = input.LT(1);
        int catchClause_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal350=null;
        Token char_literal351=null;
        Token char_literal353=null;
        JavaParser.formalParameter_return formalParameter352 = null;

        JavaParser.block_return block354 = null;


        Object string_literal350_tree=null;
        Object char_literal351_tree=null;
        Object char_literal353_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 64) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:950:5: ( 'catch' '(' formalParameter ')' block )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:950:9: 'catch' '(' formalParameter ')' block
            {
            root_0 = (Object)adaptor.nil();

            string_literal350=(Token)match(input,CATCH,FOLLOW_CATCH_in_catchClause4956); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal350_tree = (Object)adaptor.create(string_literal350);
            adaptor.addChild(root_0, string_literal350_tree);
            }
            char_literal351=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_catchClause4958); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal351_tree = (Object)adaptor.create(char_literal351);
            adaptor.addChild(root_0, char_literal351_tree);
            }
            pushFollow(FOLLOW_formalParameter_in_catchClause4960);
            formalParameter352=formalParameter();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, formalParameter352.getTree());
            char_literal353=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_catchClause4970); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal353_tree = (Object)adaptor.create(char_literal353);
            adaptor.addChild(root_0, char_literal353_tree);
            }
            pushFollow(FOLLOW_block_in_catchClause4972);
            block354=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, block354.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 64, catchClause_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "catchClause"

    public static class formalParameter_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "formalParameter"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:954:1: formalParameter : variableModifiers type IDENTIFIER ( '[' ']' )* ;
    public final JavaParser.formalParameter_return formalParameter() throws RecognitionException {
        JavaParser.formalParameter_return retval = new JavaParser.formalParameter_return();
        retval.start = input.LT(1);
        int formalParameter_StartIndex = input.index();
        Object root_0 = null;

        Token IDENTIFIER357=null;
        Token char_literal358=null;
        Token char_literal359=null;
        JavaParser.variableModifiers_return variableModifiers355 = null;

        JavaParser.type_return type356 = null;


        Object IDENTIFIER357_tree=null;
        Object char_literal358_tree=null;
        Object char_literal359_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 65) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:955:5: ( variableModifiers type IDENTIFIER ( '[' ']' )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:955:9: variableModifiers type IDENTIFIER ( '[' ']' )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_variableModifiers_in_formalParameter4993);
            variableModifiers355=variableModifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variableModifiers355.getTree());
            pushFollow(FOLLOW_type_in_formalParameter4995);
            type356=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, type356.getTree());
            IDENTIFIER357=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_formalParameter4997); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER357_tree = (Object)adaptor.create(IDENTIFIER357);
            adaptor.addChild(root_0, IDENTIFIER357_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:956:9: ( '[' ']' )*
            loop104:
            do {
                int alt104=2;
                int LA104_0 = input.LA(1);

                if ( (LA104_0==LBRACKET) ) {
                    alt104=1;
                }


                switch (alt104) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:956:10: '[' ']'
            	    {
            	    char_literal358=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_formalParameter5008); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal358_tree = (Object)adaptor.create(char_literal358);
            	    adaptor.addChild(root_0, char_literal358_tree);
            	    }
            	    char_literal359=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_formalParameter5010); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal359_tree = (Object)adaptor.create(char_literal359);
            	    adaptor.addChild(root_0, char_literal359_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop104;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 65, formalParameter_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "formalParameter"

    public static class forstatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "forstatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:960:1: forstatement : ( 'for' '(' variableModifiers type IDENTIFIER ':' expression ')' statement | 'for' '(' ( forInit )? ';' ( expression )? ';' ( expressionList )? ')' statement );
    public final JavaParser.forstatement_return forstatement() throws RecognitionException {
        JavaParser.forstatement_return retval = new JavaParser.forstatement_return();
        retval.start = input.LT(1);
        int forstatement_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal360=null;
        Token char_literal361=null;
        Token IDENTIFIER364=null;
        Token char_literal365=null;
        Token char_literal367=null;
        Token string_literal369=null;
        Token char_literal370=null;
        Token char_literal372=null;
        Token char_literal374=null;
        Token char_literal376=null;
        JavaParser.variableModifiers_return variableModifiers362 = null;

        JavaParser.type_return type363 = null;

        JavaParser.expression_return expression366 = null;

        JavaParser.statement_return statement368 = null;

        JavaParser.forInit_return forInit371 = null;

        JavaParser.expression_return expression373 = null;

        JavaParser.expressionList_return expressionList375 = null;

        JavaParser.statement_return statement377 = null;


        Object string_literal360_tree=null;
        Object char_literal361_tree=null;
        Object IDENTIFIER364_tree=null;
        Object char_literal365_tree=null;
        Object char_literal367_tree=null;
        Object string_literal369_tree=null;
        Object char_literal370_tree=null;
        Object char_literal372_tree=null;
        Object char_literal374_tree=null;
        Object char_literal376_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 66) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:961:5: ( 'for' '(' variableModifiers type IDENTIFIER ':' expression ')' statement | 'for' '(' ( forInit )? ';' ( expression )? ';' ( expressionList )? ')' statement )
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==FOR) ) {
                int LA108_1 = input.LA(2);

                if ( (synpred157_Java()) ) {
                    alt108=1;
                }
                else if ( (true) ) {
                    alt108=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 108, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 108, 0, input);

                throw nvae;
            }
            switch (alt108) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:963:9: 'for' '(' variableModifiers type IDENTIFIER ':' expression ')' statement
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal360=(Token)match(input,FOR,FOLLOW_FOR_in_forstatement5059); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal360_tree = (Object)adaptor.create(string_literal360);
                    adaptor.addChild(root_0, string_literal360_tree);
                    }
                    char_literal361=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_forstatement5061); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal361_tree = (Object)adaptor.create(char_literal361);
                    adaptor.addChild(root_0, char_literal361_tree);
                    }
                    pushFollow(FOLLOW_variableModifiers_in_forstatement5063);
                    variableModifiers362=variableModifiers();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, variableModifiers362.getTree());
                    pushFollow(FOLLOW_type_in_forstatement5065);
                    type363=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, type363.getTree());
                    IDENTIFIER364=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_forstatement5067); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER364_tree = (Object)adaptor.create(IDENTIFIER364);
                    adaptor.addChild(root_0, IDENTIFIER364_tree);
                    }
                    char_literal365=(Token)match(input,COLON,FOLLOW_COLON_in_forstatement5069); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal365_tree = (Object)adaptor.create(char_literal365);
                    adaptor.addChild(root_0, char_literal365_tree);
                    }
                    pushFollow(FOLLOW_expression_in_forstatement5080);
                    expression366=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression366.getTree());
                    char_literal367=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_forstatement5082); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal367_tree = (Object)adaptor.create(char_literal367);
                    adaptor.addChild(root_0, char_literal367_tree);
                    }
                    pushFollow(FOLLOW_statement_in_forstatement5084);
                    statement368=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement368.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:967:9: 'for' '(' ( forInit )? ';' ( expression )? ';' ( expressionList )? ')' statement
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal369=(Token)match(input,FOR,FOLLOW_FOR_in_forstatement5116); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal369_tree = (Object)adaptor.create(string_literal369);
                    adaptor.addChild(root_0, string_literal369_tree);
                    }
                    char_literal370=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_forstatement5118); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal370_tree = (Object)adaptor.create(char_literal370);
                    adaptor.addChild(root_0, char_literal370_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:968:17: ( forInit )?
                    int alt105=2;
                    int LA105_0 = input.LA(1);

                    if ( ((LA105_0>=IDENTIFIER && LA105_0<=NULL)||LA105_0==BOOLEAN||LA105_0==BYTE||LA105_0==CHAR||LA105_0==DOUBLE||LA105_0==FINAL||LA105_0==FLOAT||LA105_0==INT||LA105_0==LONG||LA105_0==NEW||LA105_0==SHORT||LA105_0==SUPER||LA105_0==THIS||LA105_0==VOID||LA105_0==LPAREN||(LA105_0>=BANG && LA105_0<=TILDE)||(LA105_0>=PLUSPLUS && LA105_0<=SUB)||LA105_0==MONKEYS_AT) ) {
                        alt105=1;
                    }
                    switch (alt105) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:968:18: forInit
                            {
                            pushFollow(FOLLOW_forInit_in_forstatement5138);
                            forInit371=forInit();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, forInit371.getTree());

                            }
                            break;

                    }

                    char_literal372=(Token)match(input,SEMI,FOLLOW_SEMI_in_forstatement5159); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal372_tree = (Object)adaptor.create(char_literal372);
                    adaptor.addChild(root_0, char_literal372_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:970:17: ( expression )?
                    int alt106=2;
                    int LA106_0 = input.LA(1);

                    if ( ((LA106_0>=IDENTIFIER && LA106_0<=NULL)||LA106_0==BOOLEAN||LA106_0==BYTE||LA106_0==CHAR||LA106_0==DOUBLE||LA106_0==FLOAT||LA106_0==INT||LA106_0==LONG||LA106_0==NEW||LA106_0==SHORT||LA106_0==SUPER||LA106_0==THIS||LA106_0==VOID||LA106_0==LPAREN||(LA106_0>=BANG && LA106_0<=TILDE)||(LA106_0>=PLUSPLUS && LA106_0<=SUB)) ) {
                        alt106=1;
                    }
                    switch (alt106) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:970:18: expression
                            {
                            pushFollow(FOLLOW_expression_in_forstatement5179);
                            expression373=expression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression373.getTree());

                            }
                            break;

                    }

                    char_literal374=(Token)match(input,SEMI,FOLLOW_SEMI_in_forstatement5200); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal374_tree = (Object)adaptor.create(char_literal374);
                    adaptor.addChild(root_0, char_literal374_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:972:17: ( expressionList )?
                    int alt107=2;
                    int LA107_0 = input.LA(1);

                    if ( ((LA107_0>=IDENTIFIER && LA107_0<=NULL)||LA107_0==BOOLEAN||LA107_0==BYTE||LA107_0==CHAR||LA107_0==DOUBLE||LA107_0==FLOAT||LA107_0==INT||LA107_0==LONG||LA107_0==NEW||LA107_0==SHORT||LA107_0==SUPER||LA107_0==THIS||LA107_0==VOID||LA107_0==LPAREN||(LA107_0>=BANG && LA107_0<=TILDE)||(LA107_0>=PLUSPLUS && LA107_0<=SUB)) ) {
                        alt107=1;
                    }
                    switch (alt107) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:972:18: expressionList
                            {
                            pushFollow(FOLLOW_expressionList_in_forstatement5220);
                            expressionList375=expressionList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, expressionList375.getTree());

                            }
                            break;

                    }

                    char_literal376=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_forstatement5241); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal376_tree = (Object)adaptor.create(char_literal376);
                    adaptor.addChild(root_0, char_literal376_tree);
                    }
                    pushFollow(FOLLOW_statement_in_forstatement5243);
                    statement377=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement377.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 66, forstatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "forstatement"

    public static class forInit_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "forInit"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:976:1: forInit : ( localVariableDeclaration | expressionList );
    public final JavaParser.forInit_return forInit() throws RecognitionException {
        JavaParser.forInit_return retval = new JavaParser.forInit_return();
        retval.start = input.LT(1);
        int forInit_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.localVariableDeclaration_return localVariableDeclaration378 = null;

        JavaParser.expressionList_return expressionList379 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 67) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:977:5: ( localVariableDeclaration | expressionList )
            int alt109=2;
            alt109 = dfa109.predict(input);
            switch (alt109) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:977:9: localVariableDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_localVariableDeclaration_in_forInit5263);
                    localVariableDeclaration378=localVariableDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, localVariableDeclaration378.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:978:9: expressionList
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_expressionList_in_forInit5273);
                    expressionList379=expressionList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expressionList379.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 67, forInit_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "forInit"

    public static class parExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:981:1: parExpression : '(' expression ')' ;
    public final JavaParser.parExpression_return parExpression() throws RecognitionException {
        JavaParser.parExpression_return retval = new JavaParser.parExpression_return();
        retval.start = input.LT(1);
        int parExpression_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal380=null;
        Token char_literal382=null;
        JavaParser.expression_return expression381 = null;


        Object char_literal380_tree=null;
        Object char_literal382_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 68) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:982:5: ( '(' expression ')' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:982:9: '(' expression ')'
            {
            root_0 = (Object)adaptor.nil();

            char_literal380=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_parExpression5293); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal380_tree = (Object)adaptor.create(char_literal380);
            adaptor.addChild(root_0, char_literal380_tree);
            }
            pushFollow(FOLLOW_expression_in_parExpression5295);
            expression381=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression381.getTree());
            char_literal382=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_parExpression5297); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal382_tree = (Object)adaptor.create(char_literal382);
            adaptor.addChild(root_0, char_literal382_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 68, parExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "parExpression"

    public static class expressionList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expressionList"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:985:1: expressionList : expression ( ',' expression )* ;
    public final JavaParser.expressionList_return expressionList() throws RecognitionException {
        JavaParser.expressionList_return retval = new JavaParser.expressionList_return();
        retval.start = input.LT(1);
        int expressionList_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal384=null;
        JavaParser.expression_return expression383 = null;

        JavaParser.expression_return expression385 = null;


        Object char_literal384_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 69) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:986:5: ( expression ( ',' expression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:986:9: expression ( ',' expression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_expression_in_expressionList5317);
            expression383=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression383.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:987:9: ( ',' expression )*
            loop110:
            do {
                int alt110=2;
                int LA110_0 = input.LA(1);

                if ( (LA110_0==COMMA) ) {
                    alt110=1;
                }


                switch (alt110) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:987:10: ',' expression
            	    {
            	    char_literal384=(Token)match(input,COMMA,FOLLOW_COMMA_in_expressionList5328); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal384_tree = (Object)adaptor.create(char_literal384);
            	    adaptor.addChild(root_0, char_literal384_tree);
            	    }
            	    pushFollow(FOLLOW_expression_in_expressionList5330);
            	    expression385=expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression385.getTree());

            	    }
            	    break;

            	default :
            	    break loop110;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 69, expressionList_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "expressionList"

    public static class expression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:992:1: expression : conditionalExpression ( assignmentOperator expression )? ;
    public final JavaParser.expression_return expression() throws RecognitionException {
        JavaParser.expression_return retval = new JavaParser.expression_return();
        retval.start = input.LT(1);
        int expression_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.conditionalExpression_return conditionalExpression386 = null;

        JavaParser.assignmentOperator_return assignmentOperator387 = null;

        JavaParser.expression_return expression388 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 70) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:993:5: ( conditionalExpression ( assignmentOperator expression )? )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:993:9: conditionalExpression ( assignmentOperator expression )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_conditionalExpression_in_expression5362);
            conditionalExpression386=conditionalExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalExpression386.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:994:9: ( assignmentOperator expression )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==EQ||(LA111_0>=PLUSEQ && LA111_0<=PERCENTEQ)||(LA111_0>=GT && LA111_0<=LT)) ) {
                alt111=1;
            }
            switch (alt111) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:994:10: assignmentOperator expression
                    {
                    pushFollow(FOLLOW_assignmentOperator_in_expression5373);
                    assignmentOperator387=assignmentOperator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentOperator387.getTree());
                    pushFollow(FOLLOW_expression_in_expression5375);
                    expression388=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression388.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 70, expression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "expression"

    public static class assignmentOperator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "assignmentOperator"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:999:1: assignmentOperator : ( '=' | '+=' | '-=' | '*=' | '/=' | '&=' | '|=' | '^=' | '%=' | '<' '<' '=' | '>' '>' '>' '=' | '>' '>' '=' );
    public final JavaParser.assignmentOperator_return assignmentOperator() throws RecognitionException {
        JavaParser.assignmentOperator_return retval = new JavaParser.assignmentOperator_return();
        retval.start = input.LT(1);
        int assignmentOperator_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal389=null;
        Token string_literal390=null;
        Token string_literal391=null;
        Token string_literal392=null;
        Token string_literal393=null;
        Token string_literal394=null;
        Token string_literal395=null;
        Token string_literal396=null;
        Token string_literal397=null;
        Token char_literal398=null;
        Token char_literal399=null;
        Token char_literal400=null;
        Token char_literal401=null;
        Token char_literal402=null;
        Token char_literal403=null;
        Token char_literal404=null;
        Token char_literal405=null;
        Token char_literal406=null;
        Token char_literal407=null;

        Object char_literal389_tree=null;
        Object string_literal390_tree=null;
        Object string_literal391_tree=null;
        Object string_literal392_tree=null;
        Object string_literal393_tree=null;
        Object string_literal394_tree=null;
        Object string_literal395_tree=null;
        Object string_literal396_tree=null;
        Object string_literal397_tree=null;
        Object char_literal398_tree=null;
        Object char_literal399_tree=null;
        Object char_literal400_tree=null;
        Object char_literal401_tree=null;
        Object char_literal402_tree=null;
        Object char_literal403_tree=null;
        Object char_literal404_tree=null;
        Object char_literal405_tree=null;
        Object char_literal406_tree=null;
        Object char_literal407_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 71) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1000:5: ( '=' | '+=' | '-=' | '*=' | '/=' | '&=' | '|=' | '^=' | '%=' | '<' '<' '=' | '>' '>' '>' '=' | '>' '>' '=' )
            int alt112=12;
            alt112 = dfa112.predict(input);
            switch (alt112) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1000:9: '='
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal389=(Token)match(input,EQ,FOLLOW_EQ_in_assignmentOperator5407); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal389_tree = (Object)adaptor.create(char_literal389);
                    adaptor.addChild(root_0, char_literal389_tree);
                    }

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1001:9: '+='
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal390=(Token)match(input,PLUSEQ,FOLLOW_PLUSEQ_in_assignmentOperator5417); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal390_tree = (Object)adaptor.create(string_literal390);
                    adaptor.addChild(root_0, string_literal390_tree);
                    }

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1002:9: '-='
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal391=(Token)match(input,SUBEQ,FOLLOW_SUBEQ_in_assignmentOperator5427); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal391_tree = (Object)adaptor.create(string_literal391);
                    adaptor.addChild(root_0, string_literal391_tree);
                    }

                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1003:9: '*='
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal392=(Token)match(input,STAREQ,FOLLOW_STAREQ_in_assignmentOperator5437); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal392_tree = (Object)adaptor.create(string_literal392);
                    adaptor.addChild(root_0, string_literal392_tree);
                    }

                    }
                    break;
                case 5 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1004:9: '/='
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal393=(Token)match(input,SLASHEQ,FOLLOW_SLASHEQ_in_assignmentOperator5447); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal393_tree = (Object)adaptor.create(string_literal393);
                    adaptor.addChild(root_0, string_literal393_tree);
                    }

                    }
                    break;
                case 6 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1005:9: '&='
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal394=(Token)match(input,AMPEQ,FOLLOW_AMPEQ_in_assignmentOperator5457); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal394_tree = (Object)adaptor.create(string_literal394);
                    adaptor.addChild(root_0, string_literal394_tree);
                    }

                    }
                    break;
                case 7 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1006:9: '|='
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal395=(Token)match(input,BAREQ,FOLLOW_BAREQ_in_assignmentOperator5467); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal395_tree = (Object)adaptor.create(string_literal395);
                    adaptor.addChild(root_0, string_literal395_tree);
                    }

                    }
                    break;
                case 8 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1007:9: '^='
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal396=(Token)match(input,CARETEQ,FOLLOW_CARETEQ_in_assignmentOperator5477); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal396_tree = (Object)adaptor.create(string_literal396);
                    adaptor.addChild(root_0, string_literal396_tree);
                    }

                    }
                    break;
                case 9 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1008:9: '%='
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal397=(Token)match(input,PERCENTEQ,FOLLOW_PERCENTEQ_in_assignmentOperator5487); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal397_tree = (Object)adaptor.create(string_literal397);
                    adaptor.addChild(root_0, string_literal397_tree);
                    }

                    }
                    break;
                case 10 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1009:10: '<' '<' '='
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal398=(Token)match(input,LT,FOLLOW_LT_in_assignmentOperator5498); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal398_tree = (Object)adaptor.create(char_literal398);
                    adaptor.addChild(root_0, char_literal398_tree);
                    }
                    char_literal399=(Token)match(input,LT,FOLLOW_LT_in_assignmentOperator5500); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal399_tree = (Object)adaptor.create(char_literal399);
                    adaptor.addChild(root_0, char_literal399_tree);
                    }
                    char_literal400=(Token)match(input,EQ,FOLLOW_EQ_in_assignmentOperator5502); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal400_tree = (Object)adaptor.create(char_literal400);
                    adaptor.addChild(root_0, char_literal400_tree);
                    }

                    }
                    break;
                case 11 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1010:10: '>' '>' '>' '='
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal401=(Token)match(input,GT,FOLLOW_GT_in_assignmentOperator5513); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal401_tree = (Object)adaptor.create(char_literal401);
                    adaptor.addChild(root_0, char_literal401_tree);
                    }
                    char_literal402=(Token)match(input,GT,FOLLOW_GT_in_assignmentOperator5515); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal402_tree = (Object)adaptor.create(char_literal402);
                    adaptor.addChild(root_0, char_literal402_tree);
                    }
                    char_literal403=(Token)match(input,GT,FOLLOW_GT_in_assignmentOperator5517); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal403_tree = (Object)adaptor.create(char_literal403);
                    adaptor.addChild(root_0, char_literal403_tree);
                    }
                    char_literal404=(Token)match(input,EQ,FOLLOW_EQ_in_assignmentOperator5519); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal404_tree = (Object)adaptor.create(char_literal404);
                    adaptor.addChild(root_0, char_literal404_tree);
                    }

                    }
                    break;
                case 12 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1011:10: '>' '>' '='
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal405=(Token)match(input,GT,FOLLOW_GT_in_assignmentOperator5530); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal405_tree = (Object)adaptor.create(char_literal405);
                    adaptor.addChild(root_0, char_literal405_tree);
                    }
                    char_literal406=(Token)match(input,GT,FOLLOW_GT_in_assignmentOperator5532); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal406_tree = (Object)adaptor.create(char_literal406);
                    adaptor.addChild(root_0, char_literal406_tree);
                    }
                    char_literal407=(Token)match(input,EQ,FOLLOW_EQ_in_assignmentOperator5534); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal407_tree = (Object)adaptor.create(char_literal407);
                    adaptor.addChild(root_0, char_literal407_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 71, assignmentOperator_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "assignmentOperator"

    public static class conditionalExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "conditionalExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1015:1: conditionalExpression : conditionalOrExpression ( '?' expression ':' conditionalExpression )? ;
    public final JavaParser.conditionalExpression_return conditionalExpression() throws RecognitionException {
        JavaParser.conditionalExpression_return retval = new JavaParser.conditionalExpression_return();
        retval.start = input.LT(1);
        int conditionalExpression_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal409=null;
        Token char_literal411=null;
        JavaParser.conditionalOrExpression_return conditionalOrExpression408 = null;

        JavaParser.expression_return expression410 = null;

        JavaParser.conditionalExpression_return conditionalExpression412 = null;


        Object char_literal409_tree=null;
        Object char_literal411_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 72) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1016:5: ( conditionalOrExpression ( '?' expression ':' conditionalExpression )? )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1016:9: conditionalOrExpression ( '?' expression ':' conditionalExpression )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_conditionalOrExpression_in_conditionalExpression5555);
            conditionalOrExpression408=conditionalOrExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalOrExpression408.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1017:9: ( '?' expression ':' conditionalExpression )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( (LA113_0==QUES) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1017:10: '?' expression ':' conditionalExpression
                    {
                    char_literal409=(Token)match(input,QUES,FOLLOW_QUES_in_conditionalExpression5566); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal409_tree = (Object)adaptor.create(char_literal409);
                    adaptor.addChild(root_0, char_literal409_tree);
                    }
                    pushFollow(FOLLOW_expression_in_conditionalExpression5568);
                    expression410=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression410.getTree());
                    char_literal411=(Token)match(input,COLON,FOLLOW_COLON_in_conditionalExpression5570); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal411_tree = (Object)adaptor.create(char_literal411);
                    adaptor.addChild(root_0, char_literal411_tree);
                    }
                    pushFollow(FOLLOW_conditionalExpression_in_conditionalExpression5572);
                    conditionalExpression412=conditionalExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalExpression412.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 72, conditionalExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "conditionalExpression"

    public static class conditionalOrExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "conditionalOrExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1021:1: conditionalOrExpression : conditionalAndExpression ( '||' conditionalAndExpression )* ;
    public final JavaParser.conditionalOrExpression_return conditionalOrExpression() throws RecognitionException {
        JavaParser.conditionalOrExpression_return retval = new JavaParser.conditionalOrExpression_return();
        retval.start = input.LT(1);
        int conditionalOrExpression_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal414=null;
        JavaParser.conditionalAndExpression_return conditionalAndExpression413 = null;

        JavaParser.conditionalAndExpression_return conditionalAndExpression415 = null;


        Object string_literal414_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 73) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1022:5: ( conditionalAndExpression ( '||' conditionalAndExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1022:9: conditionalAndExpression ( '||' conditionalAndExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_conditionalAndExpression_in_conditionalOrExpression5603);
            conditionalAndExpression413=conditionalAndExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalAndExpression413.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1023:9: ( '||' conditionalAndExpression )*
            loop114:
            do {
                int alt114=2;
                int LA114_0 = input.LA(1);

                if ( (LA114_0==BARBAR) ) {
                    alt114=1;
                }


                switch (alt114) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1023:10: '||' conditionalAndExpression
            	    {
            	    string_literal414=(Token)match(input,BARBAR,FOLLOW_BARBAR_in_conditionalOrExpression5614); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal414_tree = (Object)adaptor.create(string_literal414);
            	    adaptor.addChild(root_0, string_literal414_tree);
            	    }
            	    pushFollow(FOLLOW_conditionalAndExpression_in_conditionalOrExpression5616);
            	    conditionalAndExpression415=conditionalAndExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalAndExpression415.getTree());

            	    }
            	    break;

            	default :
            	    break loop114;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 73, conditionalOrExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "conditionalOrExpression"

    public static class conditionalAndExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "conditionalAndExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1027:1: conditionalAndExpression : inclusiveOrExpression ( '&&' inclusiveOrExpression )* ;
    public final JavaParser.conditionalAndExpression_return conditionalAndExpression() throws RecognitionException {
        JavaParser.conditionalAndExpression_return retval = new JavaParser.conditionalAndExpression_return();
        retval.start = input.LT(1);
        int conditionalAndExpression_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal417=null;
        JavaParser.inclusiveOrExpression_return inclusiveOrExpression416 = null;

        JavaParser.inclusiveOrExpression_return inclusiveOrExpression418 = null;


        Object string_literal417_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 74) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1028:5: ( inclusiveOrExpression ( '&&' inclusiveOrExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1028:9: inclusiveOrExpression ( '&&' inclusiveOrExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_inclusiveOrExpression_in_conditionalAndExpression5647);
            inclusiveOrExpression416=inclusiveOrExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, inclusiveOrExpression416.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1029:9: ( '&&' inclusiveOrExpression )*
            loop115:
            do {
                int alt115=2;
                int LA115_0 = input.LA(1);

                if ( (LA115_0==AMPAMP) ) {
                    alt115=1;
                }


                switch (alt115) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1029:10: '&&' inclusiveOrExpression
            	    {
            	    string_literal417=(Token)match(input,AMPAMP,FOLLOW_AMPAMP_in_conditionalAndExpression5658); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal417_tree = (Object)adaptor.create(string_literal417);
            	    adaptor.addChild(root_0, string_literal417_tree);
            	    }
            	    pushFollow(FOLLOW_inclusiveOrExpression_in_conditionalAndExpression5660);
            	    inclusiveOrExpression418=inclusiveOrExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, inclusiveOrExpression418.getTree());

            	    }
            	    break;

            	default :
            	    break loop115;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 74, conditionalAndExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "conditionalAndExpression"

    public static class inclusiveOrExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "inclusiveOrExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1033:1: inclusiveOrExpression : exclusiveOrExpression ( '|' exclusiveOrExpression )* ;
    public final JavaParser.inclusiveOrExpression_return inclusiveOrExpression() throws RecognitionException {
        JavaParser.inclusiveOrExpression_return retval = new JavaParser.inclusiveOrExpression_return();
        retval.start = input.LT(1);
        int inclusiveOrExpression_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal420=null;
        JavaParser.exclusiveOrExpression_return exclusiveOrExpression419 = null;

        JavaParser.exclusiveOrExpression_return exclusiveOrExpression421 = null;


        Object char_literal420_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 75) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1034:5: ( exclusiveOrExpression ( '|' exclusiveOrExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1034:9: exclusiveOrExpression ( '|' exclusiveOrExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression5691);
            exclusiveOrExpression419=exclusiveOrExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exclusiveOrExpression419.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1035:9: ( '|' exclusiveOrExpression )*
            loop116:
            do {
                int alt116=2;
                int LA116_0 = input.LA(1);

                if ( (LA116_0==BAR) ) {
                    alt116=1;
                }


                switch (alt116) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1035:10: '|' exclusiveOrExpression
            	    {
            	    char_literal420=(Token)match(input,BAR,FOLLOW_BAR_in_inclusiveOrExpression5702); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal420_tree = (Object)adaptor.create(char_literal420);
            	    adaptor.addChild(root_0, char_literal420_tree);
            	    }
            	    pushFollow(FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression5704);
            	    exclusiveOrExpression421=exclusiveOrExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, exclusiveOrExpression421.getTree());

            	    }
            	    break;

            	default :
            	    break loop116;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 75, inclusiveOrExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "inclusiveOrExpression"

    public static class exclusiveOrExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exclusiveOrExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1039:1: exclusiveOrExpression : andExpression ( '^' andExpression )* ;
    public final JavaParser.exclusiveOrExpression_return exclusiveOrExpression() throws RecognitionException {
        JavaParser.exclusiveOrExpression_return retval = new JavaParser.exclusiveOrExpression_return();
        retval.start = input.LT(1);
        int exclusiveOrExpression_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal423=null;
        JavaParser.andExpression_return andExpression422 = null;

        JavaParser.andExpression_return andExpression424 = null;


        Object char_literal423_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 76) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1040:5: ( andExpression ( '^' andExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1040:9: andExpression ( '^' andExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_andExpression_in_exclusiveOrExpression5735);
            andExpression422=andExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpression422.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1041:9: ( '^' andExpression )*
            loop117:
            do {
                int alt117=2;
                int LA117_0 = input.LA(1);

                if ( (LA117_0==CARET) ) {
                    alt117=1;
                }


                switch (alt117) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1041:10: '^' andExpression
            	    {
            	    char_literal423=(Token)match(input,CARET,FOLLOW_CARET_in_exclusiveOrExpression5746); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal423_tree = (Object)adaptor.create(char_literal423);
            	    adaptor.addChild(root_0, char_literal423_tree);
            	    }
            	    pushFollow(FOLLOW_andExpression_in_exclusiveOrExpression5748);
            	    andExpression424=andExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpression424.getTree());

            	    }
            	    break;

            	default :
            	    break loop117;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 76, exclusiveOrExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "exclusiveOrExpression"

    public static class andExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "andExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1045:1: andExpression : equalityExpression ( '&' equalityExpression )* ;
    public final JavaParser.andExpression_return andExpression() throws RecognitionException {
        JavaParser.andExpression_return retval = new JavaParser.andExpression_return();
        retval.start = input.LT(1);
        int andExpression_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal426=null;
        JavaParser.equalityExpression_return equalityExpression425 = null;

        JavaParser.equalityExpression_return equalityExpression427 = null;


        Object char_literal426_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 77) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1046:5: ( equalityExpression ( '&' equalityExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1046:9: equalityExpression ( '&' equalityExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_equalityExpression_in_andExpression5779);
            equalityExpression425=equalityExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, equalityExpression425.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1047:9: ( '&' equalityExpression )*
            loop118:
            do {
                int alt118=2;
                int LA118_0 = input.LA(1);

                if ( (LA118_0==AMP) ) {
                    alt118=1;
                }


                switch (alt118) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1047:10: '&' equalityExpression
            	    {
            	    char_literal426=(Token)match(input,AMP,FOLLOW_AMP_in_andExpression5790); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal426_tree = (Object)adaptor.create(char_literal426);
            	    adaptor.addChild(root_0, char_literal426_tree);
            	    }
            	    pushFollow(FOLLOW_equalityExpression_in_andExpression5792);
            	    equalityExpression427=equalityExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, equalityExpression427.getTree());

            	    }
            	    break;

            	default :
            	    break loop118;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 77, andExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "andExpression"

    public static class equalityExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "equalityExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1051:1: equalityExpression : instanceOfExpression ( ( '==' | '!=' ) instanceOfExpression )* ;
    public final JavaParser.equalityExpression_return equalityExpression() throws RecognitionException {
        JavaParser.equalityExpression_return retval = new JavaParser.equalityExpression_return();
        retval.start = input.LT(1);
        int equalityExpression_StartIndex = input.index();
        Object root_0 = null;

        Token set429=null;
        JavaParser.instanceOfExpression_return instanceOfExpression428 = null;

        JavaParser.instanceOfExpression_return instanceOfExpression430 = null;


        Object set429_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 78) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1052:5: ( instanceOfExpression ( ( '==' | '!=' ) instanceOfExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1052:9: instanceOfExpression ( ( '==' | '!=' ) instanceOfExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_instanceOfExpression_in_equalityExpression5823);
            instanceOfExpression428=instanceOfExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, instanceOfExpression428.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1053:9: ( ( '==' | '!=' ) instanceOfExpression )*
            loop119:
            do {
                int alt119=2;
                int LA119_0 = input.LA(1);

                if ( (LA119_0==EQEQ||LA119_0==BANGEQ) ) {
                    alt119=1;
                }


                switch (alt119) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1054:13: ( '==' | '!=' ) instanceOfExpression
            	    {
            	    set429=(Token)input.LT(1);
            	    if ( input.LA(1)==EQEQ||input.LA(1)==BANGEQ ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set429));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_instanceOfExpression_in_equalityExpression5900);
            	    instanceOfExpression430=instanceOfExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, instanceOfExpression430.getTree());

            	    }
            	    break;

            	default :
            	    break loop119;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 78, equalityExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "equalityExpression"

    public static class instanceOfExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "instanceOfExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1061:1: instanceOfExpression : relationalExpression ( 'instanceof' type )? ;
    public final JavaParser.instanceOfExpression_return instanceOfExpression() throws RecognitionException {
        JavaParser.instanceOfExpression_return retval = new JavaParser.instanceOfExpression_return();
        retval.start = input.LT(1);
        int instanceOfExpression_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal432=null;
        JavaParser.relationalExpression_return relationalExpression431 = null;

        JavaParser.type_return type433 = null;


        Object string_literal432_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 79) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1062:5: ( relationalExpression ( 'instanceof' type )? )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1062:9: relationalExpression ( 'instanceof' type )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_relationalExpression_in_instanceOfExpression5931);
            relationalExpression431=relationalExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, relationalExpression431.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1063:9: ( 'instanceof' type )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==INSTANCEOF) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1063:10: 'instanceof' type
                    {
                    string_literal432=(Token)match(input,INSTANCEOF,FOLLOW_INSTANCEOF_in_instanceOfExpression5942); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal432_tree = (Object)adaptor.create(string_literal432);
                    adaptor.addChild(root_0, string_literal432_tree);
                    }
                    pushFollow(FOLLOW_type_in_instanceOfExpression5944);
                    type433=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, type433.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 79, instanceOfExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "instanceOfExpression"

    public static class relationalExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "relationalExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1067:1: relationalExpression : shiftExpression ( relationalOp shiftExpression )* ;
    public final JavaParser.relationalExpression_return relationalExpression() throws RecognitionException {
        JavaParser.relationalExpression_return retval = new JavaParser.relationalExpression_return();
        retval.start = input.LT(1);
        int relationalExpression_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.shiftExpression_return shiftExpression434 = null;

        JavaParser.relationalOp_return relationalOp435 = null;

        JavaParser.shiftExpression_return shiftExpression436 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 80) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1068:5: ( shiftExpression ( relationalOp shiftExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1068:9: shiftExpression ( relationalOp shiftExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_shiftExpression_in_relationalExpression5975);
            shiftExpression434=shiftExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, shiftExpression434.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1069:9: ( relationalOp shiftExpression )*
            loop121:
            do {
                int alt121=2;
                int LA121_0 = input.LA(1);

                if ( (LA121_0==LT) ) {
                    int LA121_2 = input.LA(2);

                    if ( ((LA121_2>=IDENTIFIER && LA121_2<=NULL)||LA121_2==BOOLEAN||LA121_2==BYTE||LA121_2==CHAR||LA121_2==DOUBLE||LA121_2==FLOAT||LA121_2==INT||LA121_2==LONG||LA121_2==NEW||LA121_2==SHORT||LA121_2==SUPER||LA121_2==THIS||LA121_2==VOID||LA121_2==LPAREN||(LA121_2>=EQ && LA121_2<=TILDE)||(LA121_2>=PLUSPLUS && LA121_2<=SUB)) ) {
                        alt121=1;
                    }


                }
                else if ( (LA121_0==GT) ) {
                    int LA121_3 = input.LA(2);

                    if ( ((LA121_3>=IDENTIFIER && LA121_3<=NULL)||LA121_3==BOOLEAN||LA121_3==BYTE||LA121_3==CHAR||LA121_3==DOUBLE||LA121_3==FLOAT||LA121_3==INT||LA121_3==LONG||LA121_3==NEW||LA121_3==SHORT||LA121_3==SUPER||LA121_3==THIS||LA121_3==VOID||LA121_3==LPAREN||(LA121_3>=EQ && LA121_3<=TILDE)||(LA121_3>=PLUSPLUS && LA121_3<=SUB)) ) {
                        alt121=1;
                    }


                }


                switch (alt121) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1069:10: relationalOp shiftExpression
            	    {
            	    pushFollow(FOLLOW_relationalOp_in_relationalExpression5986);
            	    relationalOp435=relationalOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, relationalOp435.getTree());
            	    pushFollow(FOLLOW_shiftExpression_in_relationalExpression5988);
            	    shiftExpression436=shiftExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, shiftExpression436.getTree());

            	    }
            	    break;

            	default :
            	    break loop121;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 80, relationalExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "relationalExpression"

    public static class relationalOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "relationalOp"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1073:1: relationalOp : ( '<' '=' | '>' '=' | '<' | '>' );
    public final JavaParser.relationalOp_return relationalOp() throws RecognitionException {
        JavaParser.relationalOp_return retval = new JavaParser.relationalOp_return();
        retval.start = input.LT(1);
        int relationalOp_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal437=null;
        Token char_literal438=null;
        Token char_literal439=null;
        Token char_literal440=null;
        Token char_literal441=null;
        Token char_literal442=null;

        Object char_literal437_tree=null;
        Object char_literal438_tree=null;
        Object char_literal439_tree=null;
        Object char_literal440_tree=null;
        Object char_literal441_tree=null;
        Object char_literal442_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 81) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1074:5: ( '<' '=' | '>' '=' | '<' | '>' )
            int alt122=4;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==LT) ) {
                int LA122_1 = input.LA(2);

                if ( (LA122_1==EQ) ) {
                    alt122=1;
                }
                else if ( ((LA122_1>=IDENTIFIER && LA122_1<=NULL)||LA122_1==BOOLEAN||LA122_1==BYTE||LA122_1==CHAR||LA122_1==DOUBLE||LA122_1==FLOAT||LA122_1==INT||LA122_1==LONG||LA122_1==NEW||LA122_1==SHORT||LA122_1==SUPER||LA122_1==THIS||LA122_1==VOID||LA122_1==LPAREN||(LA122_1>=BANG && LA122_1<=TILDE)||(LA122_1>=PLUSPLUS && LA122_1<=SUB)) ) {
                    alt122=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 122, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA122_0==GT) ) {
                int LA122_2 = input.LA(2);

                if ( (LA122_2==EQ) ) {
                    alt122=2;
                }
                else if ( ((LA122_2>=IDENTIFIER && LA122_2<=NULL)||LA122_2==BOOLEAN||LA122_2==BYTE||LA122_2==CHAR||LA122_2==DOUBLE||LA122_2==FLOAT||LA122_2==INT||LA122_2==LONG||LA122_2==NEW||LA122_2==SHORT||LA122_2==SUPER||LA122_2==THIS||LA122_2==VOID||LA122_2==LPAREN||(LA122_2>=BANG && LA122_2<=TILDE)||(LA122_2>=PLUSPLUS && LA122_2<=SUB)) ) {
                    alt122=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 122, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 122, 0, input);

                throw nvae;
            }
            switch (alt122) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1074:10: '<' '='
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal437=(Token)match(input,LT,FOLLOW_LT_in_relationalOp6020); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal437_tree = (Object)adaptor.create(char_literal437);
                    adaptor.addChild(root_0, char_literal437_tree);
                    }
                    char_literal438=(Token)match(input,EQ,FOLLOW_EQ_in_relationalOp6022); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal438_tree = (Object)adaptor.create(char_literal438);
                    adaptor.addChild(root_0, char_literal438_tree);
                    }

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1075:10: '>' '='
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal439=(Token)match(input,GT,FOLLOW_GT_in_relationalOp6033); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal439_tree = (Object)adaptor.create(char_literal439);
                    adaptor.addChild(root_0, char_literal439_tree);
                    }
                    char_literal440=(Token)match(input,EQ,FOLLOW_EQ_in_relationalOp6035); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal440_tree = (Object)adaptor.create(char_literal440);
                    adaptor.addChild(root_0, char_literal440_tree);
                    }

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1076:9: '<'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal441=(Token)match(input,LT,FOLLOW_LT_in_relationalOp6045); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal441_tree = (Object)adaptor.create(char_literal441);
                    adaptor.addChild(root_0, char_literal441_tree);
                    }

                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1077:9: '>'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal442=(Token)match(input,GT,FOLLOW_GT_in_relationalOp6055); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal442_tree = (Object)adaptor.create(char_literal442);
                    adaptor.addChild(root_0, char_literal442_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 81, relationalOp_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "relationalOp"

    public static class shiftExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "shiftExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1080:1: shiftExpression : additiveExpression ( shiftOp additiveExpression )* ;
    public final JavaParser.shiftExpression_return shiftExpression() throws RecognitionException {
        JavaParser.shiftExpression_return retval = new JavaParser.shiftExpression_return();
        retval.start = input.LT(1);
        int shiftExpression_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.additiveExpression_return additiveExpression443 = null;

        JavaParser.shiftOp_return shiftOp444 = null;

        JavaParser.additiveExpression_return additiveExpression445 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 82) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1081:5: ( additiveExpression ( shiftOp additiveExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1081:9: additiveExpression ( shiftOp additiveExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_additiveExpression_in_shiftExpression6075);
            additiveExpression443=additiveExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, additiveExpression443.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1082:9: ( shiftOp additiveExpression )*
            loop123:
            do {
                int alt123=2;
                int LA123_0 = input.LA(1);

                if ( (LA123_0==LT) ) {
                    int LA123_1 = input.LA(2);

                    if ( (LA123_1==LT) ) {
                        int LA123_4 = input.LA(3);

                        if ( ((LA123_4>=IDENTIFIER && LA123_4<=NULL)||LA123_4==BOOLEAN||LA123_4==BYTE||LA123_4==CHAR||LA123_4==DOUBLE||LA123_4==FLOAT||LA123_4==INT||LA123_4==LONG||LA123_4==NEW||LA123_4==SHORT||LA123_4==SUPER||LA123_4==THIS||LA123_4==VOID||LA123_4==LPAREN||(LA123_4>=BANG && LA123_4<=TILDE)||(LA123_4>=PLUSPLUS && LA123_4<=SUB)) ) {
                            alt123=1;
                        }


                    }


                }
                else if ( (LA123_0==GT) ) {
                    int LA123_2 = input.LA(2);

                    if ( (LA123_2==GT) ) {
                        int LA123_5 = input.LA(3);

                        if ( (LA123_5==GT) ) {
                            int LA123_7 = input.LA(4);

                            if ( ((LA123_7>=IDENTIFIER && LA123_7<=NULL)||LA123_7==BOOLEAN||LA123_7==BYTE||LA123_7==CHAR||LA123_7==DOUBLE||LA123_7==FLOAT||LA123_7==INT||LA123_7==LONG||LA123_7==NEW||LA123_7==SHORT||LA123_7==SUPER||LA123_7==THIS||LA123_7==VOID||LA123_7==LPAREN||(LA123_7>=BANG && LA123_7<=TILDE)||(LA123_7>=PLUSPLUS && LA123_7<=SUB)) ) {
                                alt123=1;
                            }


                        }
                        else if ( ((LA123_5>=IDENTIFIER && LA123_5<=NULL)||LA123_5==BOOLEAN||LA123_5==BYTE||LA123_5==CHAR||LA123_5==DOUBLE||LA123_5==FLOAT||LA123_5==INT||LA123_5==LONG||LA123_5==NEW||LA123_5==SHORT||LA123_5==SUPER||LA123_5==THIS||LA123_5==VOID||LA123_5==LPAREN||(LA123_5>=BANG && LA123_5<=TILDE)||(LA123_5>=PLUSPLUS && LA123_5<=SUB)) ) {
                            alt123=1;
                        }


                    }


                }


                switch (alt123) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1082:10: shiftOp additiveExpression
            	    {
            	    pushFollow(FOLLOW_shiftOp_in_shiftExpression6086);
            	    shiftOp444=shiftOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, shiftOp444.getTree());
            	    pushFollow(FOLLOW_additiveExpression_in_shiftExpression6088);
            	    additiveExpression445=additiveExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, additiveExpression445.getTree());

            	    }
            	    break;

            	default :
            	    break loop123;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 82, shiftExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "shiftExpression"

    public static class shiftOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "shiftOp"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1087:1: shiftOp : ( '<' '<' | '>' '>' '>' | '>' '>' );
    public final JavaParser.shiftOp_return shiftOp() throws RecognitionException {
        JavaParser.shiftOp_return retval = new JavaParser.shiftOp_return();
        retval.start = input.LT(1);
        int shiftOp_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal446=null;
        Token char_literal447=null;
        Token char_literal448=null;
        Token char_literal449=null;
        Token char_literal450=null;
        Token char_literal451=null;
        Token char_literal452=null;

        Object char_literal446_tree=null;
        Object char_literal447_tree=null;
        Object char_literal448_tree=null;
        Object char_literal449_tree=null;
        Object char_literal450_tree=null;
        Object char_literal451_tree=null;
        Object char_literal452_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 83) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1088:5: ( '<' '<' | '>' '>' '>' | '>' '>' )
            int alt124=3;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==LT) ) {
                alt124=1;
            }
            else if ( (LA124_0==GT) ) {
                int LA124_2 = input.LA(2);

                if ( (LA124_2==GT) ) {
                    int LA124_3 = input.LA(3);

                    if ( (LA124_3==GT) ) {
                        alt124=2;
                    }
                    else if ( ((LA124_3>=IDENTIFIER && LA124_3<=NULL)||LA124_3==BOOLEAN||LA124_3==BYTE||LA124_3==CHAR||LA124_3==DOUBLE||LA124_3==FLOAT||LA124_3==INT||LA124_3==LONG||LA124_3==NEW||LA124_3==SHORT||LA124_3==SUPER||LA124_3==THIS||LA124_3==VOID||LA124_3==LPAREN||(LA124_3>=BANG && LA124_3<=TILDE)||(LA124_3>=PLUSPLUS && LA124_3<=SUB)) ) {
                        alt124=3;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 124, 3, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 124, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 124, 0, input);

                throw nvae;
            }
            switch (alt124) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1088:10: '<' '<'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal446=(Token)match(input,LT,FOLLOW_LT_in_shiftOp6121); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal446_tree = (Object)adaptor.create(char_literal446);
                    adaptor.addChild(root_0, char_literal446_tree);
                    }
                    char_literal447=(Token)match(input,LT,FOLLOW_LT_in_shiftOp6123); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal447_tree = (Object)adaptor.create(char_literal447);
                    adaptor.addChild(root_0, char_literal447_tree);
                    }

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1089:10: '>' '>' '>'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal448=(Token)match(input,GT,FOLLOW_GT_in_shiftOp6134); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal448_tree = (Object)adaptor.create(char_literal448);
                    adaptor.addChild(root_0, char_literal448_tree);
                    }
                    char_literal449=(Token)match(input,GT,FOLLOW_GT_in_shiftOp6136); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal449_tree = (Object)adaptor.create(char_literal449);
                    adaptor.addChild(root_0, char_literal449_tree);
                    }
                    char_literal450=(Token)match(input,GT,FOLLOW_GT_in_shiftOp6138); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal450_tree = (Object)adaptor.create(char_literal450);
                    adaptor.addChild(root_0, char_literal450_tree);
                    }

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1090:10: '>' '>'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal451=(Token)match(input,GT,FOLLOW_GT_in_shiftOp6149); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal451_tree = (Object)adaptor.create(char_literal451);
                    adaptor.addChild(root_0, char_literal451_tree);
                    }
                    char_literal452=(Token)match(input,GT,FOLLOW_GT_in_shiftOp6151); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal452_tree = (Object)adaptor.create(char_literal452);
                    adaptor.addChild(root_0, char_literal452_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 83, shiftOp_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "shiftOp"

    public static class additiveExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "additiveExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1094:1: additiveExpression : multiplicativeExpression ( ( '+' | '-' ) multiplicativeExpression )* ;
    public final JavaParser.additiveExpression_return additiveExpression() throws RecognitionException {
        JavaParser.additiveExpression_return retval = new JavaParser.additiveExpression_return();
        retval.start = input.LT(1);
        int additiveExpression_StartIndex = input.index();
        Object root_0 = null;

        Token set454=null;
        JavaParser.multiplicativeExpression_return multiplicativeExpression453 = null;

        JavaParser.multiplicativeExpression_return multiplicativeExpression455 = null;


        Object set454_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 84) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1095:5: ( multiplicativeExpression ( ( '+' | '-' ) multiplicativeExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1095:9: multiplicativeExpression ( ( '+' | '-' ) multiplicativeExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression6172);
            multiplicativeExpression453=multiplicativeExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, multiplicativeExpression453.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1096:9: ( ( '+' | '-' ) multiplicativeExpression )*
            loop125:
            do {
                int alt125=2;
                int LA125_0 = input.LA(1);

                if ( ((LA125_0>=PLUS && LA125_0<=SUB)) ) {
                    alt125=1;
                }


                switch (alt125) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1097:13: ( '+' | '-' ) multiplicativeExpression
            	    {
            	    set454=(Token)input.LT(1);
            	    if ( (input.LA(1)>=PLUS && input.LA(1)<=SUB) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set454));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression6249);
            	    multiplicativeExpression455=multiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, multiplicativeExpression455.getTree());

            	    }
            	    break;

            	default :
            	    break loop125;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 84, additiveExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "additiveExpression"

    public static class multiplicativeExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "multiplicativeExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1104:1: multiplicativeExpression : unaryExpression ( ( '*' | '/' | '%' ) unaryExpression )* ;
    public final JavaParser.multiplicativeExpression_return multiplicativeExpression() throws RecognitionException {
        JavaParser.multiplicativeExpression_return retval = new JavaParser.multiplicativeExpression_return();
        retval.start = input.LT(1);
        int multiplicativeExpression_StartIndex = input.index();
        Object root_0 = null;

        Token set457=null;
        JavaParser.unaryExpression_return unaryExpression456 = null;

        JavaParser.unaryExpression_return unaryExpression458 = null;


        Object set457_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 85) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1105:5: ( unaryExpression ( ( '*' | '/' | '%' ) unaryExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1106:9: unaryExpression ( ( '*' | '/' | '%' ) unaryExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression6287);
            unaryExpression456=unaryExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression456.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1107:9: ( ( '*' | '/' | '%' ) unaryExpression )*
            loop126:
            do {
                int alt126=2;
                int LA126_0 = input.LA(1);

                if ( ((LA126_0>=STAR && LA126_0<=SLASH)||LA126_0==PERCENT) ) {
                    alt126=1;
                }


                switch (alt126) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1108:13: ( '*' | '/' | '%' ) unaryExpression
            	    {
            	    set457=(Token)input.LT(1);
            	    if ( (input.LA(1)>=STAR && input.LA(1)<=SLASH)||input.LA(1)==PERCENT ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set457));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression6382);
            	    unaryExpression458=unaryExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression458.getTree());

            	    }
            	    break;

            	default :
            	    break loop126;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 85, multiplicativeExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "multiplicativeExpression"

    public static class unaryExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "unaryExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1116:1: unaryExpression : ( '+' unaryExpression | '-' unaryExpression | '++' unaryExpression | '--' unaryExpression | unaryExpressionNotPlusMinus );
    public final JavaParser.unaryExpression_return unaryExpression() throws RecognitionException {
        JavaParser.unaryExpression_return retval = new JavaParser.unaryExpression_return();
        retval.start = input.LT(1);
        int unaryExpression_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal459=null;
        Token char_literal461=null;
        Token string_literal463=null;
        Token string_literal465=null;
        JavaParser.unaryExpression_return unaryExpression460 = null;

        JavaParser.unaryExpression_return unaryExpression462 = null;

        JavaParser.unaryExpression_return unaryExpression464 = null;

        JavaParser.unaryExpression_return unaryExpression466 = null;

        JavaParser.unaryExpressionNotPlusMinus_return unaryExpressionNotPlusMinus467 = null;


        Object char_literal459_tree=null;
        Object char_literal461_tree=null;
        Object string_literal463_tree=null;
        Object string_literal465_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 86) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1121:5: ( '+' unaryExpression | '-' unaryExpression | '++' unaryExpression | '--' unaryExpression | unaryExpressionNotPlusMinus )
            int alt127=5;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt127=1;
                }
                break;
            case SUB:
                {
                alt127=2;
                }
                break;
            case PLUSPLUS:
                {
                alt127=3;
                }
                break;
            case SUBSUB:
                {
                alt127=4;
                }
                break;
            case IDENTIFIER:
            case INTLITERAL:
            case LONGLITERAL:
            case FLOATLITERAL:
            case DOUBLELITERAL:
            case CHARLITERAL:
            case STRINGLITERAL:
            case TRUE:
            case FALSE:
            case NULL:
            case BOOLEAN:
            case BYTE:
            case CHAR:
            case DOUBLE:
            case FLOAT:
            case INT:
            case LONG:
            case NEW:
            case SHORT:
            case SUPER:
            case THIS:
            case VOID:
            case LPAREN:
            case BANG:
            case TILDE:
                {
                alt127=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 127, 0, input);

                throw nvae;
            }

            switch (alt127) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1121:9: '+' unaryExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal459=(Token)match(input,PLUS,FOLLOW_PLUS_in_unaryExpression6415); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal459_tree = (Object)adaptor.create(char_literal459);
                    adaptor.addChild(root_0, char_literal459_tree);
                    }
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression6418);
                    unaryExpression460=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression460.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1122:9: '-' unaryExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal461=(Token)match(input,SUB,FOLLOW_SUB_in_unaryExpression6428); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal461_tree = (Object)adaptor.create(char_literal461);
                    adaptor.addChild(root_0, char_literal461_tree);
                    }
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression6430);
                    unaryExpression462=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression462.getTree());

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1123:9: '++' unaryExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal463=(Token)match(input,PLUSPLUS,FOLLOW_PLUSPLUS_in_unaryExpression6440); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal463_tree = (Object)adaptor.create(string_literal463);
                    adaptor.addChild(root_0, string_literal463_tree);
                    }
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression6442);
                    unaryExpression464=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression464.getTree());

                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1124:9: '--' unaryExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal465=(Token)match(input,SUBSUB,FOLLOW_SUBSUB_in_unaryExpression6452); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal465_tree = (Object)adaptor.create(string_literal465);
                    adaptor.addChild(root_0, string_literal465_tree);
                    }
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression6454);
                    unaryExpression466=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression466.getTree());

                    }
                    break;
                case 5 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1125:9: unaryExpressionNotPlusMinus
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_unaryExpressionNotPlusMinus_in_unaryExpression6464);
                    unaryExpressionNotPlusMinus467=unaryExpressionNotPlusMinus();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpressionNotPlusMinus467.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 86, unaryExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "unaryExpression"

    public static class unaryExpressionNotPlusMinus_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "unaryExpressionNotPlusMinus"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1128:1: unaryExpressionNotPlusMinus : ( '~' unaryExpression | '!' unaryExpression | castExpression | primary ( selector )* ( '++' | '--' )? );
    public final JavaParser.unaryExpressionNotPlusMinus_return unaryExpressionNotPlusMinus() throws RecognitionException {
        JavaParser.unaryExpressionNotPlusMinus_return retval = new JavaParser.unaryExpressionNotPlusMinus_return();
        retval.start = input.LT(1);
        int unaryExpressionNotPlusMinus_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal468=null;
        Token char_literal470=null;
        Token set475=null;
        JavaParser.unaryExpression_return unaryExpression469 = null;

        JavaParser.unaryExpression_return unaryExpression471 = null;

        JavaParser.castExpression_return castExpression472 = null;

        JavaParser.primary_return primary473 = null;

        JavaParser.selector_return selector474 = null;


        Object char_literal468_tree=null;
        Object char_literal470_tree=null;
        Object set475_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 87) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1129:5: ( '~' unaryExpression | '!' unaryExpression | castExpression | primary ( selector )* ( '++' | '--' )? )
            int alt130=4;
            alt130 = dfa130.predict(input);
            switch (alt130) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1129:9: '~' unaryExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal468=(Token)match(input,TILDE,FOLLOW_TILDE_in_unaryExpressionNotPlusMinus6484); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal468_tree = (Object)adaptor.create(char_literal468);
                    adaptor.addChild(root_0, char_literal468_tree);
                    }
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus6486);
                    unaryExpression469=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression469.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1130:9: '!' unaryExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal470=(Token)match(input,BANG,FOLLOW_BANG_in_unaryExpressionNotPlusMinus6496); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal470_tree = (Object)adaptor.create(char_literal470);
                    adaptor.addChild(root_0, char_literal470_tree);
                    }
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus6498);
                    unaryExpression471=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression471.getTree());

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1131:9: castExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_castExpression_in_unaryExpressionNotPlusMinus6508);
                    castExpression472=castExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, castExpression472.getTree());

                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1132:9: primary ( selector )* ( '++' | '--' )?
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_primary_in_unaryExpressionNotPlusMinus6518);
                    primary473=primary();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, primary473.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1133:9: ( selector )*
                    loop128:
                    do {
                        int alt128=2;
                        int LA128_0 = input.LA(1);

                        if ( (LA128_0==LBRACKET||LA128_0==DOT) ) {
                            alt128=1;
                        }


                        switch (alt128) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1133:10: selector
                    	    {
                    	    pushFollow(FOLLOW_selector_in_unaryExpressionNotPlusMinus6529);
                    	    selector474=selector();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, selector474.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop128;
                        }
                    } while (true);

                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1135:9: ( '++' | '--' )?
                    int alt129=2;
                    int LA129_0 = input.LA(1);

                    if ( ((LA129_0>=PLUSPLUS && LA129_0<=SUBSUB)) ) {
                        alt129=1;
                    }
                    switch (alt129) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:
                            {
                            set475=(Token)input.LT(1);
                            if ( (input.LA(1)>=PLUSPLUS && input.LA(1)<=SUBSUB) ) {
                                input.consume();
                                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set475));
                                state.errorRecovery=false;state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 87, unaryExpressionNotPlusMinus_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "unaryExpressionNotPlusMinus"

    public static class castExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "castExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1140:1: castExpression : ( '(' primitiveType ')' unaryExpression | '(' type ')' unaryExpressionNotPlusMinus );
    public final JavaParser.castExpression_return castExpression() throws RecognitionException {
        JavaParser.castExpression_return retval = new JavaParser.castExpression_return();
        retval.start = input.LT(1);
        int castExpression_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal476=null;
        Token char_literal478=null;
        Token char_literal480=null;
        Token char_literal482=null;
        JavaParser.primitiveType_return primitiveType477 = null;

        JavaParser.unaryExpression_return unaryExpression479 = null;

        JavaParser.type_return type481 = null;

        JavaParser.unaryExpressionNotPlusMinus_return unaryExpressionNotPlusMinus483 = null;


        Object char_literal476_tree=null;
        Object char_literal478_tree=null;
        Object char_literal480_tree=null;
        Object char_literal482_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 88) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1141:5: ( '(' primitiveType ')' unaryExpression | '(' type ')' unaryExpressionNotPlusMinus )
            int alt131=2;
            int LA131_0 = input.LA(1);

            if ( (LA131_0==LPAREN) ) {
                int LA131_1 = input.LA(2);

                if ( (synpred206_Java()) ) {
                    alt131=1;
                }
                else if ( (true) ) {
                    alt131=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 131, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 131, 0, input);

                throw nvae;
            }
            switch (alt131) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1141:9: '(' primitiveType ')' unaryExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal476=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_castExpression6599); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal476_tree = (Object)adaptor.create(char_literal476);
                    adaptor.addChild(root_0, char_literal476_tree);
                    }
                    pushFollow(FOLLOW_primitiveType_in_castExpression6601);
                    primitiveType477=primitiveType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, primitiveType477.getTree());
                    char_literal478=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_castExpression6603); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal478_tree = (Object)adaptor.create(char_literal478);
                    adaptor.addChild(root_0, char_literal478_tree);
                    }
                    pushFollow(FOLLOW_unaryExpression_in_castExpression6605);
                    unaryExpression479=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression479.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1142:9: '(' type ')' unaryExpressionNotPlusMinus
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal480=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_castExpression6615); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal480_tree = (Object)adaptor.create(char_literal480);
                    adaptor.addChild(root_0, char_literal480_tree);
                    }
                    pushFollow(FOLLOW_type_in_castExpression6617);
                    type481=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, type481.getTree());
                    char_literal482=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_castExpression6619); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal482_tree = (Object)adaptor.create(char_literal482);
                    adaptor.addChild(root_0, char_literal482_tree);
                    }
                    pushFollow(FOLLOW_unaryExpressionNotPlusMinus_in_castExpression6621);
                    unaryExpressionNotPlusMinus483=unaryExpressionNotPlusMinus();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpressionNotPlusMinus483.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 88, castExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "castExpression"

    public static class primary_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "primary"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1145:1: primary : ( parExpression | 'this' ( '.' IDENTIFIER )* ( identifierSuffix )? | IDENTIFIER ( '.' IDENTIFIER )* ( identifierSuffix )? | 'super' superSuffix | literal | creator | primitiveType ( '[' ']' )* '.' 'class' | 'void' '.' 'class' );
    public final JavaParser.primary_return primary() throws RecognitionException {
        JavaParser.primary_return retval = new JavaParser.primary_return();
        retval.start = input.LT(1);
        int primary_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal485=null;
        Token char_literal486=null;
        Token IDENTIFIER487=null;
        Token IDENTIFIER489=null;
        Token char_literal490=null;
        Token IDENTIFIER491=null;
        Token string_literal493=null;
        Token char_literal498=null;
        Token char_literal499=null;
        Token char_literal500=null;
        Token string_literal501=null;
        Token string_literal502=null;
        Token char_literal503=null;
        Token string_literal504=null;
        JavaParser.parExpression_return parExpression484 = null;

        JavaParser.identifierSuffix_return identifierSuffix488 = null;

        JavaParser.identifierSuffix_return identifierSuffix492 = null;

        JavaParser.superSuffix_return superSuffix494 = null;

        JavaParser.literal_return literal495 = null;

        JavaParser.creator_return creator496 = null;

        JavaParser.primitiveType_return primitiveType497 = null;


        Object string_literal485_tree=null;
        Object char_literal486_tree=null;
        Object IDENTIFIER487_tree=null;
        Object IDENTIFIER489_tree=null;
        Object char_literal490_tree=null;
        Object IDENTIFIER491_tree=null;
        Object string_literal493_tree=null;
        Object char_literal498_tree=null;
        Object char_literal499_tree=null;
        Object char_literal500_tree=null;
        Object string_literal501_tree=null;
        Object string_literal502_tree=null;
        Object char_literal503_tree=null;
        Object string_literal504_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 89) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1149:5: ( parExpression | 'this' ( '.' IDENTIFIER )* ( identifierSuffix )? | IDENTIFIER ( '.' IDENTIFIER )* ( identifierSuffix )? | 'super' superSuffix | literal | creator | primitiveType ( '[' ']' )* '.' 'class' | 'void' '.' 'class' )
            int alt137=8;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                alt137=1;
                }
                break;
            case THIS:
                {
                alt137=2;
                }
                break;
            case IDENTIFIER:
                {
                alt137=3;
                }
                break;
            case SUPER:
                {
                alt137=4;
                }
                break;
            case INTLITERAL:
            case LONGLITERAL:
            case FLOATLITERAL:
            case DOUBLELITERAL:
            case CHARLITERAL:
            case STRINGLITERAL:
            case TRUE:
            case FALSE:
            case NULL:
                {
                alt137=5;
                }
                break;
            case NEW:
                {
                alt137=6;
                }
                break;
            case BOOLEAN:
            case BYTE:
            case CHAR:
            case DOUBLE:
            case FLOAT:
            case INT:
            case LONG:
            case SHORT:
                {
                alt137=7;
                }
                break;
            case VOID:
                {
                alt137=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 137, 0, input);

                throw nvae;
            }

            switch (alt137) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1149:9: parExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_parExpression_in_primary6643);
                    parExpression484=parExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parExpression484.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1150:9: 'this' ( '.' IDENTIFIER )* ( identifierSuffix )?
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal485=(Token)match(input,THIS,FOLLOW_THIS_in_primary6665); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal485_tree = (Object)adaptor.create(string_literal485);
                    adaptor.addChild(root_0, string_literal485_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1151:9: ( '.' IDENTIFIER )*
                    loop132:
                    do {
                        int alt132=2;
                        int LA132_0 = input.LA(1);

                        if ( (LA132_0==DOT) ) {
                            int LA132_2 = input.LA(2);

                            if ( (LA132_2==IDENTIFIER) ) {
                                int LA132_3 = input.LA(3);

                                if ( (synpred208_Java()) ) {
                                    alt132=1;
                                }


                            }


                        }


                        switch (alt132) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1151:10: '.' IDENTIFIER
                    	    {
                    	    char_literal486=(Token)match(input,DOT,FOLLOW_DOT_in_primary6676); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal486_tree = (Object)adaptor.create(char_literal486);
                    	    adaptor.addChild(root_0, char_literal486_tree);
                    	    }
                    	    IDENTIFIER487=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_primary6678); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    IDENTIFIER487_tree = (Object)adaptor.create(IDENTIFIER487);
                    	    adaptor.addChild(root_0, IDENTIFIER487_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop132;
                        }
                    } while (true);

                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1153:9: ( identifierSuffix )?
                    int alt133=2;
                    alt133 = dfa133.predict(input);
                    switch (alt133) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1153:10: identifierSuffix
                            {
                            pushFollow(FOLLOW_identifierSuffix_in_primary6700);
                            identifierSuffix488=identifierSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, identifierSuffix488.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1155:9: IDENTIFIER ( '.' IDENTIFIER )* ( identifierSuffix )?
                    {
                    root_0 = (Object)adaptor.nil();

                    IDENTIFIER489=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_primary6721); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER489_tree = (Object)adaptor.create(IDENTIFIER489);
                    adaptor.addChild(root_0, IDENTIFIER489_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1156:9: ( '.' IDENTIFIER )*
                    loop134:
                    do {
                        int alt134=2;
                        int LA134_0 = input.LA(1);

                        if ( (LA134_0==DOT) ) {
                            int LA134_2 = input.LA(2);

                            if ( (LA134_2==IDENTIFIER) ) {
                                int LA134_3 = input.LA(3);

                                if ( (synpred211_Java()) ) {
                                    alt134=1;
                                }


                            }


                        }


                        switch (alt134) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1156:10: '.' IDENTIFIER
                    	    {
                    	    char_literal490=(Token)match(input,DOT,FOLLOW_DOT_in_primary6732); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal490_tree = (Object)adaptor.create(char_literal490);
                    	    adaptor.addChild(root_0, char_literal490_tree);
                    	    }
                    	    IDENTIFIER491=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_primary6734); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    IDENTIFIER491_tree = (Object)adaptor.create(IDENTIFIER491);
                    	    adaptor.addChild(root_0, IDENTIFIER491_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop134;
                        }
                    } while (true);

                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1158:9: ( identifierSuffix )?
                    int alt135=2;
                    alt135 = dfa135.predict(input);
                    switch (alt135) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1158:10: identifierSuffix
                            {
                            pushFollow(FOLLOW_identifierSuffix_in_primary6756);
                            identifierSuffix492=identifierSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, identifierSuffix492.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1160:9: 'super' superSuffix
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal493=(Token)match(input,SUPER,FOLLOW_SUPER_in_primary6777); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal493_tree = (Object)adaptor.create(string_literal493);
                    adaptor.addChild(root_0, string_literal493_tree);
                    }
                    pushFollow(FOLLOW_superSuffix_in_primary6787);
                    superSuffix494=superSuffix();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, superSuffix494.getTree());

                    }
                    break;
                case 5 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1162:9: literal
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_literal_in_primary6797);
                    literal495=literal();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, literal495.getTree());

                    }
                    break;
                case 6 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1163:9: creator
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_creator_in_primary6807);
                    creator496=creator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, creator496.getTree());

                    }
                    break;
                case 7 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1164:9: primitiveType ( '[' ']' )* '.' 'class'
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_primitiveType_in_primary6817);
                    primitiveType497=primitiveType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, primitiveType497.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1165:9: ( '[' ']' )*
                    loop136:
                    do {
                        int alt136=2;
                        int LA136_0 = input.LA(1);

                        if ( (LA136_0==LBRACKET) ) {
                            alt136=1;
                        }


                        switch (alt136) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1165:10: '[' ']'
                    	    {
                    	    char_literal498=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_primary6828); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal498_tree = (Object)adaptor.create(char_literal498);
                    	    adaptor.addChild(root_0, char_literal498_tree);
                    	    }
                    	    char_literal499=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_primary6830); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal499_tree = (Object)adaptor.create(char_literal499);
                    	    adaptor.addChild(root_0, char_literal499_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop136;
                        }
                    } while (true);

                    char_literal500=(Token)match(input,DOT,FOLLOW_DOT_in_primary6851); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal500_tree = (Object)adaptor.create(char_literal500);
                    adaptor.addChild(root_0, char_literal500_tree);
                    }
                    string_literal501=(Token)match(input,CLASS,FOLLOW_CLASS_in_primary6853); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal501_tree = (Object)adaptor.create(string_literal501);
                    adaptor.addChild(root_0, string_literal501_tree);
                    }

                    }
                    break;
                case 8 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1168:9: 'void' '.' 'class'
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal502=(Token)match(input,VOID,FOLLOW_VOID_in_primary6863); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal502_tree = (Object)adaptor.create(string_literal502);
                    adaptor.addChild(root_0, string_literal502_tree);
                    }
                    char_literal503=(Token)match(input,DOT,FOLLOW_DOT_in_primary6865); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal503_tree = (Object)adaptor.create(char_literal503);
                    adaptor.addChild(root_0, char_literal503_tree);
                    }
                    string_literal504=(Token)match(input,CLASS,FOLLOW_CLASS_in_primary6867); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal504_tree = (Object)adaptor.create(string_literal504);
                    adaptor.addChild(root_0, string_literal504_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 89, primary_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "primary"

    public static class superSuffix_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "superSuffix"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1172:1: superSuffix : ( arguments | '.' ( typeArguments )? IDENTIFIER ( arguments )? );
    public final JavaParser.superSuffix_return superSuffix() throws RecognitionException {
        JavaParser.superSuffix_return retval = new JavaParser.superSuffix_return();
        retval.start = input.LT(1);
        int superSuffix_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal506=null;
        Token IDENTIFIER508=null;
        JavaParser.arguments_return arguments505 = null;

        JavaParser.typeArguments_return typeArguments507 = null;

        JavaParser.arguments_return arguments509 = null;


        Object char_literal506_tree=null;
        Object IDENTIFIER508_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 90) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1173:5: ( arguments | '.' ( typeArguments )? IDENTIFIER ( arguments )? )
            int alt140=2;
            int LA140_0 = input.LA(1);

            if ( (LA140_0==LPAREN) ) {
                alt140=1;
            }
            else if ( (LA140_0==DOT) ) {
                alt140=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 140, 0, input);

                throw nvae;
            }
            switch (alt140) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1173:9: arguments
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_arguments_in_superSuffix6893);
                    arguments505=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments505.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1174:9: '.' ( typeArguments )? IDENTIFIER ( arguments )?
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal506=(Token)match(input,DOT,FOLLOW_DOT_in_superSuffix6903); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal506_tree = (Object)adaptor.create(char_literal506);
                    adaptor.addChild(root_0, char_literal506_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1174:13: ( typeArguments )?
                    int alt138=2;
                    int LA138_0 = input.LA(1);

                    if ( (LA138_0==LT) ) {
                        alt138=1;
                    }
                    switch (alt138) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1174:14: typeArguments
                            {
                            pushFollow(FOLLOW_typeArguments_in_superSuffix6906);
                            typeArguments507=typeArguments();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeArguments507.getTree());

                            }
                            break;

                    }

                    IDENTIFIER508=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_superSuffix6927); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER508_tree = (Object)adaptor.create(IDENTIFIER508);
                    adaptor.addChild(root_0, IDENTIFIER508_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1177:9: ( arguments )?
                    int alt139=2;
                    int LA139_0 = input.LA(1);

                    if ( (LA139_0==LPAREN) ) {
                        alt139=1;
                    }
                    switch (alt139) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1177:10: arguments
                            {
                            pushFollow(FOLLOW_arguments_in_superSuffix6938);
                            arguments509=arguments();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments509.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 90, superSuffix_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "superSuffix"

    public static class identifierSuffix_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "identifierSuffix"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1182:1: identifierSuffix : ( ( '[' ']' )+ '.' 'class' | ( '[' expression ']' )+ | arguments | '.' 'class' | '.' nonWildcardTypeArguments IDENTIFIER arguments | '.' 'this' | '.' 'super' arguments | innerCreator );
    public final JavaParser.identifierSuffix_return identifierSuffix() throws RecognitionException {
        JavaParser.identifierSuffix_return retval = new JavaParser.identifierSuffix_return();
        retval.start = input.LT(1);
        int identifierSuffix_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal510=null;
        Token char_literal511=null;
        Token char_literal512=null;
        Token string_literal513=null;
        Token char_literal514=null;
        Token char_literal516=null;
        Token char_literal518=null;
        Token string_literal519=null;
        Token char_literal520=null;
        Token IDENTIFIER522=null;
        Token char_literal524=null;
        Token string_literal525=null;
        Token char_literal526=null;
        Token string_literal527=null;
        JavaParser.expression_return expression515 = null;

        JavaParser.arguments_return arguments517 = null;

        JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments521 = null;

        JavaParser.arguments_return arguments523 = null;

        JavaParser.arguments_return arguments528 = null;

        JavaParser.innerCreator_return innerCreator529 = null;


        Object char_literal510_tree=null;
        Object char_literal511_tree=null;
        Object char_literal512_tree=null;
        Object string_literal513_tree=null;
        Object char_literal514_tree=null;
        Object char_literal516_tree=null;
        Object char_literal518_tree=null;
        Object string_literal519_tree=null;
        Object char_literal520_tree=null;
        Object IDENTIFIER522_tree=null;
        Object char_literal524_tree=null;
        Object string_literal525_tree=null;
        Object char_literal526_tree=null;
        Object string_literal527_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 91) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1183:5: ( ( '[' ']' )+ '.' 'class' | ( '[' expression ']' )+ | arguments | '.' 'class' | '.' nonWildcardTypeArguments IDENTIFIER arguments | '.' 'this' | '.' 'super' arguments | innerCreator )
            int alt143=8;
            alt143 = dfa143.predict(input);
            switch (alt143) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1183:9: ( '[' ']' )+ '.' 'class'
                    {
                    root_0 = (Object)adaptor.nil();

                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1183:9: ( '[' ']' )+
                    int cnt141=0;
                    loop141:
                    do {
                        int alt141=2;
                        int LA141_0 = input.LA(1);

                        if ( (LA141_0==LBRACKET) ) {
                            alt141=1;
                        }


                        switch (alt141) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1183:10: '[' ']'
                    	    {
                    	    char_literal510=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_identifierSuffix6971); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal510_tree = (Object)adaptor.create(char_literal510);
                    	    adaptor.addChild(root_0, char_literal510_tree);
                    	    }
                    	    char_literal511=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_identifierSuffix6973); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal511_tree = (Object)adaptor.create(char_literal511);
                    	    adaptor.addChild(root_0, char_literal511_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt141 >= 1 ) break loop141;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(141, input);
                                throw eee;
                        }
                        cnt141++;
                    } while (true);

                    char_literal512=(Token)match(input,DOT,FOLLOW_DOT_in_identifierSuffix6994); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal512_tree = (Object)adaptor.create(char_literal512);
                    adaptor.addChild(root_0, char_literal512_tree);
                    }
                    string_literal513=(Token)match(input,CLASS,FOLLOW_CLASS_in_identifierSuffix6996); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal513_tree = (Object)adaptor.create(string_literal513);
                    adaptor.addChild(root_0, string_literal513_tree);
                    }

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1186:9: ( '[' expression ']' )+
                    {
                    root_0 = (Object)adaptor.nil();

                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1186:9: ( '[' expression ']' )+
                    int cnt142=0;
                    loop142:
                    do {
                        int alt142=2;
                        alt142 = dfa142.predict(input);
                        switch (alt142) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1186:10: '[' expression ']'
                    	    {
                    	    char_literal514=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_identifierSuffix7007); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal514_tree = (Object)adaptor.create(char_literal514);
                    	    adaptor.addChild(root_0, char_literal514_tree);
                    	    }
                    	    pushFollow(FOLLOW_expression_in_identifierSuffix7009);
                    	    expression515=expression();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression515.getTree());
                    	    char_literal516=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_identifierSuffix7011); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal516_tree = (Object)adaptor.create(char_literal516);
                    	    adaptor.addChild(root_0, char_literal516_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt142 >= 1 ) break loop142;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(142, input);
                                throw eee;
                        }
                        cnt142++;
                    } while (true);


                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1188:9: arguments
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_arguments_in_identifierSuffix7032);
                    arguments517=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments517.getTree());

                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1189:9: '.' 'class'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal518=(Token)match(input,DOT,FOLLOW_DOT_in_identifierSuffix7042); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal518_tree = (Object)adaptor.create(char_literal518);
                    adaptor.addChild(root_0, char_literal518_tree);
                    }
                    string_literal519=(Token)match(input,CLASS,FOLLOW_CLASS_in_identifierSuffix7044); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal519_tree = (Object)adaptor.create(string_literal519);
                    adaptor.addChild(root_0, string_literal519_tree);
                    }

                    }
                    break;
                case 5 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1190:9: '.' nonWildcardTypeArguments IDENTIFIER arguments
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal520=(Token)match(input,DOT,FOLLOW_DOT_in_identifierSuffix7054); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal520_tree = (Object)adaptor.create(char_literal520);
                    adaptor.addChild(root_0, char_literal520_tree);
                    }
                    pushFollow(FOLLOW_nonWildcardTypeArguments_in_identifierSuffix7056);
                    nonWildcardTypeArguments521=nonWildcardTypeArguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, nonWildcardTypeArguments521.getTree());
                    IDENTIFIER522=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierSuffix7058); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER522_tree = (Object)adaptor.create(IDENTIFIER522);
                    adaptor.addChild(root_0, IDENTIFIER522_tree);
                    }
                    pushFollow(FOLLOW_arguments_in_identifierSuffix7060);
                    arguments523=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments523.getTree());

                    }
                    break;
                case 6 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1191:9: '.' 'this'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal524=(Token)match(input,DOT,FOLLOW_DOT_in_identifierSuffix7070); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal524_tree = (Object)adaptor.create(char_literal524);
                    adaptor.addChild(root_0, char_literal524_tree);
                    }
                    string_literal525=(Token)match(input,THIS,FOLLOW_THIS_in_identifierSuffix7072); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal525_tree = (Object)adaptor.create(string_literal525);
                    adaptor.addChild(root_0, string_literal525_tree);
                    }

                    }
                    break;
                case 7 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1192:9: '.' 'super' arguments
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal526=(Token)match(input,DOT,FOLLOW_DOT_in_identifierSuffix7082); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal526_tree = (Object)adaptor.create(char_literal526);
                    adaptor.addChild(root_0, char_literal526_tree);
                    }
                    string_literal527=(Token)match(input,SUPER,FOLLOW_SUPER_in_identifierSuffix7084); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal527_tree = (Object)adaptor.create(string_literal527);
                    adaptor.addChild(root_0, string_literal527_tree);
                    }
                    pushFollow(FOLLOW_arguments_in_identifierSuffix7086);
                    arguments528=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments528.getTree());

                    }
                    break;
                case 8 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1193:9: innerCreator
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_innerCreator_in_identifierSuffix7096);
                    innerCreator529=innerCreator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, innerCreator529.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 91, identifierSuffix_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "identifierSuffix"

    public static class selector_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "selector"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1197:1: selector : ( '.' IDENTIFIER ( arguments )? | '.' 'this' | '.' 'super' superSuffix | innerCreator | '[' expression ']' );
    public final JavaParser.selector_return selector() throws RecognitionException {
        JavaParser.selector_return retval = new JavaParser.selector_return();
        retval.start = input.LT(1);
        int selector_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal530=null;
        Token IDENTIFIER531=null;
        Token char_literal533=null;
        Token string_literal534=null;
        Token char_literal535=null;
        Token string_literal536=null;
        Token char_literal539=null;
        Token char_literal541=null;
        JavaParser.arguments_return arguments532 = null;

        JavaParser.superSuffix_return superSuffix537 = null;

        JavaParser.innerCreator_return innerCreator538 = null;

        JavaParser.expression_return expression540 = null;


        Object char_literal530_tree=null;
        Object IDENTIFIER531_tree=null;
        Object char_literal533_tree=null;
        Object string_literal534_tree=null;
        Object char_literal535_tree=null;
        Object string_literal536_tree=null;
        Object char_literal539_tree=null;
        Object char_literal541_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 92) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1198:5: ( '.' IDENTIFIER ( arguments )? | '.' 'this' | '.' 'super' superSuffix | innerCreator | '[' expression ']' )
            int alt145=5;
            int LA145_0 = input.LA(1);

            if ( (LA145_0==DOT) ) {
                switch ( input.LA(2) ) {
                case IDENTIFIER:
                    {
                    alt145=1;
                    }
                    break;
                case THIS:
                    {
                    alt145=2;
                    }
                    break;
                case SUPER:
                    {
                    alt145=3;
                    }
                    break;
                case NEW:
                    {
                    alt145=4;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 145, 1, input);

                    throw nvae;
                }

            }
            else if ( (LA145_0==LBRACKET) ) {
                alt145=5;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 145, 0, input);

                throw nvae;
            }
            switch (alt145) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1198:9: '.' IDENTIFIER ( arguments )?
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal530=(Token)match(input,DOT,FOLLOW_DOT_in_selector7118); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal530_tree = (Object)adaptor.create(char_literal530);
                    adaptor.addChild(root_0, char_literal530_tree);
                    }
                    IDENTIFIER531=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_selector7120); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER531_tree = (Object)adaptor.create(IDENTIFIER531);
                    adaptor.addChild(root_0, IDENTIFIER531_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1199:9: ( arguments )?
                    int alt144=2;
                    int LA144_0 = input.LA(1);

                    if ( (LA144_0==LPAREN) ) {
                        alt144=1;
                    }
                    switch (alt144) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1199:10: arguments
                            {
                            pushFollow(FOLLOW_arguments_in_selector7131);
                            arguments532=arguments();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments532.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1201:9: '.' 'this'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal533=(Token)match(input,DOT,FOLLOW_DOT_in_selector7152); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal533_tree = (Object)adaptor.create(char_literal533);
                    adaptor.addChild(root_0, char_literal533_tree);
                    }
                    string_literal534=(Token)match(input,THIS,FOLLOW_THIS_in_selector7154); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal534_tree = (Object)adaptor.create(string_literal534);
                    adaptor.addChild(root_0, string_literal534_tree);
                    }

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1202:9: '.' 'super' superSuffix
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal535=(Token)match(input,DOT,FOLLOW_DOT_in_selector7164); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal535_tree = (Object)adaptor.create(char_literal535);
                    adaptor.addChild(root_0, char_literal535_tree);
                    }
                    string_literal536=(Token)match(input,SUPER,FOLLOW_SUPER_in_selector7166); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal536_tree = (Object)adaptor.create(string_literal536);
                    adaptor.addChild(root_0, string_literal536_tree);
                    }
                    pushFollow(FOLLOW_superSuffix_in_selector7176);
                    superSuffix537=superSuffix();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, superSuffix537.getTree());

                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1204:9: innerCreator
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_innerCreator_in_selector7186);
                    innerCreator538=innerCreator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, innerCreator538.getTree());

                    }
                    break;
                case 5 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1205:9: '[' expression ']'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal539=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_selector7196); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal539_tree = (Object)adaptor.create(char_literal539);
                    adaptor.addChild(root_0, char_literal539_tree);
                    }
                    pushFollow(FOLLOW_expression_in_selector7198);
                    expression540=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression540.getTree());
                    char_literal541=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_selector7200); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal541_tree = (Object)adaptor.create(char_literal541);
                    adaptor.addChild(root_0, char_literal541_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 92, selector_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "selector"

    public static class creator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "creator"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1208:1: creator : ( 'new' nonWildcardTypeArguments classOrInterfaceType classCreatorRest | 'new' classOrInterfaceType classCreatorRest | arrayCreator );
    public final JavaParser.creator_return creator() throws RecognitionException {
        JavaParser.creator_return retval = new JavaParser.creator_return();
        retval.start = input.LT(1);
        int creator_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal542=null;
        Token string_literal546=null;
        JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments543 = null;

        JavaParser.classOrInterfaceType_return classOrInterfaceType544 = null;

        JavaParser.classCreatorRest_return classCreatorRest545 = null;

        JavaParser.classOrInterfaceType_return classOrInterfaceType547 = null;

        JavaParser.classCreatorRest_return classCreatorRest548 = null;

        JavaParser.arrayCreator_return arrayCreator549 = null;


        Object string_literal542_tree=null;
        Object string_literal546_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 93) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1209:5: ( 'new' nonWildcardTypeArguments classOrInterfaceType classCreatorRest | 'new' classOrInterfaceType classCreatorRest | arrayCreator )
            int alt146=3;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==NEW) ) {
                int LA146_1 = input.LA(2);

                if ( (synpred236_Java()) ) {
                    alt146=1;
                }
                else if ( (synpred237_Java()) ) {
                    alt146=2;
                }
                else if ( (true) ) {
                    alt146=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 146, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 146, 0, input);

                throw nvae;
            }
            switch (alt146) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1209:9: 'new' nonWildcardTypeArguments classOrInterfaceType classCreatorRest
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal542=(Token)match(input,NEW,FOLLOW_NEW_in_creator7220); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal542_tree = (Object)adaptor.create(string_literal542);
                    adaptor.addChild(root_0, string_literal542_tree);
                    }
                    pushFollow(FOLLOW_nonWildcardTypeArguments_in_creator7222);
                    nonWildcardTypeArguments543=nonWildcardTypeArguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, nonWildcardTypeArguments543.getTree());
                    pushFollow(FOLLOW_classOrInterfaceType_in_creator7224);
                    classOrInterfaceType544=classOrInterfaceType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classOrInterfaceType544.getTree());
                    pushFollow(FOLLOW_classCreatorRest_in_creator7226);
                    classCreatorRest545=classCreatorRest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classCreatorRest545.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1210:9: 'new' classOrInterfaceType classCreatorRest
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal546=(Token)match(input,NEW,FOLLOW_NEW_in_creator7236); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal546_tree = (Object)adaptor.create(string_literal546);
                    adaptor.addChild(root_0, string_literal546_tree);
                    }
                    pushFollow(FOLLOW_classOrInterfaceType_in_creator7238);
                    classOrInterfaceType547=classOrInterfaceType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classOrInterfaceType547.getTree());
                    pushFollow(FOLLOW_classCreatorRest_in_creator7240);
                    classCreatorRest548=classCreatorRest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classCreatorRest548.getTree());

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1211:9: arrayCreator
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_arrayCreator_in_creator7250);
                    arrayCreator549=arrayCreator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arrayCreator549.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 93, creator_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "creator"

    public static class arrayCreator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arrayCreator"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1214:1: arrayCreator : ( 'new' createdName '[' ']' ( '[' ']' )* arrayInitializer | 'new' createdName '[' expression ']' ( '[' expression ']' )* ( '[' ']' )* );
    public final JavaParser.arrayCreator_return arrayCreator() throws RecognitionException {
        JavaParser.arrayCreator_return retval = new JavaParser.arrayCreator_return();
        retval.start = input.LT(1);
        int arrayCreator_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal550=null;
        Token char_literal552=null;
        Token char_literal553=null;
        Token char_literal554=null;
        Token char_literal555=null;
        Token string_literal557=null;
        Token char_literal559=null;
        Token char_literal561=null;
        Token char_literal562=null;
        Token char_literal564=null;
        Token char_literal565=null;
        Token char_literal566=null;
        JavaParser.createdName_return createdName551 = null;

        JavaParser.arrayInitializer_return arrayInitializer556 = null;

        JavaParser.createdName_return createdName558 = null;

        JavaParser.expression_return expression560 = null;

        JavaParser.expression_return expression563 = null;


        Object string_literal550_tree=null;
        Object char_literal552_tree=null;
        Object char_literal553_tree=null;
        Object char_literal554_tree=null;
        Object char_literal555_tree=null;
        Object string_literal557_tree=null;
        Object char_literal559_tree=null;
        Object char_literal561_tree=null;
        Object char_literal562_tree=null;
        Object char_literal564_tree=null;
        Object char_literal565_tree=null;
        Object char_literal566_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 94) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1215:5: ( 'new' createdName '[' ']' ( '[' ']' )* arrayInitializer | 'new' createdName '[' expression ']' ( '[' expression ']' )* ( '[' ']' )* )
            int alt150=2;
            int LA150_0 = input.LA(1);

            if ( (LA150_0==NEW) ) {
                int LA150_1 = input.LA(2);

                if ( (synpred239_Java()) ) {
                    alt150=1;
                }
                else if ( (true) ) {
                    alt150=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 150, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 150, 0, input);

                throw nvae;
            }
            switch (alt150) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1215:9: 'new' createdName '[' ']' ( '[' ']' )* arrayInitializer
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal550=(Token)match(input,NEW,FOLLOW_NEW_in_arrayCreator7270); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal550_tree = (Object)adaptor.create(string_literal550);
                    adaptor.addChild(root_0, string_literal550_tree);
                    }
                    pushFollow(FOLLOW_createdName_in_arrayCreator7272);
                    createdName551=createdName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, createdName551.getTree());
                    char_literal552=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_arrayCreator7282); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal552_tree = (Object)adaptor.create(char_literal552);
                    adaptor.addChild(root_0, char_literal552_tree);
                    }
                    char_literal553=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_arrayCreator7284); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal553_tree = (Object)adaptor.create(char_literal553);
                    adaptor.addChild(root_0, char_literal553_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1217:9: ( '[' ']' )*
                    loop147:
                    do {
                        int alt147=2;
                        int LA147_0 = input.LA(1);

                        if ( (LA147_0==LBRACKET) ) {
                            alt147=1;
                        }


                        switch (alt147) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1217:10: '[' ']'
                    	    {
                    	    char_literal554=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_arrayCreator7295); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal554_tree = (Object)adaptor.create(char_literal554);
                    	    adaptor.addChild(root_0, char_literal554_tree);
                    	    }
                    	    char_literal555=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_arrayCreator7297); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal555_tree = (Object)adaptor.create(char_literal555);
                    	    adaptor.addChild(root_0, char_literal555_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop147;
                        }
                    } while (true);

                    pushFollow(FOLLOW_arrayInitializer_in_arrayCreator7318);
                    arrayInitializer556=arrayInitializer();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arrayInitializer556.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1221:9: 'new' createdName '[' expression ']' ( '[' expression ']' )* ( '[' ']' )*
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal557=(Token)match(input,NEW,FOLLOW_NEW_in_arrayCreator7329); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal557_tree = (Object)adaptor.create(string_literal557);
                    adaptor.addChild(root_0, string_literal557_tree);
                    }
                    pushFollow(FOLLOW_createdName_in_arrayCreator7331);
                    createdName558=createdName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, createdName558.getTree());
                    char_literal559=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_arrayCreator7341); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal559_tree = (Object)adaptor.create(char_literal559);
                    adaptor.addChild(root_0, char_literal559_tree);
                    }
                    pushFollow(FOLLOW_expression_in_arrayCreator7343);
                    expression560=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression560.getTree());
                    char_literal561=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_arrayCreator7353); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal561_tree = (Object)adaptor.create(char_literal561);
                    adaptor.addChild(root_0, char_literal561_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1224:9: ( '[' expression ']' )*
                    loop148:
                    do {
                        int alt148=2;
                        alt148 = dfa148.predict(input);
                        switch (alt148) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1224:13: '[' expression ']'
                    	    {
                    	    char_literal562=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_arrayCreator7367); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal562_tree = (Object)adaptor.create(char_literal562);
                    	    adaptor.addChild(root_0, char_literal562_tree);
                    	    }
                    	    pushFollow(FOLLOW_expression_in_arrayCreator7369);
                    	    expression563=expression();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression563.getTree());
                    	    char_literal564=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_arrayCreator7383); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal564_tree = (Object)adaptor.create(char_literal564);
                    	    adaptor.addChild(root_0, char_literal564_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop148;
                        }
                    } while (true);

                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1227:9: ( '[' ']' )*
                    loop149:
                    do {
                        int alt149=2;
                        int LA149_0 = input.LA(1);

                        if ( (LA149_0==LBRACKET) ) {
                            int LA149_2 = input.LA(2);

                            if ( (LA149_2==RBRACKET) ) {
                                alt149=1;
                            }


                        }


                        switch (alt149) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1227:10: '[' ']'
                    	    {
                    	    char_literal565=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_arrayCreator7405); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal565_tree = (Object)adaptor.create(char_literal565);
                    	    adaptor.addChild(root_0, char_literal565_tree);
                    	    }
                    	    char_literal566=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_arrayCreator7407); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal566_tree = (Object)adaptor.create(char_literal566);
                    	    adaptor.addChild(root_0, char_literal566_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop149;
                        }
                    } while (true);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 94, arrayCreator_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "arrayCreator"

    public static class variableInitializer_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "variableInitializer"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1231:1: variableInitializer : ( arrayInitializer | expression );
    public final JavaParser.variableInitializer_return variableInitializer() throws RecognitionException {
        JavaParser.variableInitializer_return retval = new JavaParser.variableInitializer_return();
        retval.start = input.LT(1);
        int variableInitializer_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.arrayInitializer_return arrayInitializer567 = null;

        JavaParser.expression_return expression568 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 95) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1232:5: ( arrayInitializer | expression )
            int alt151=2;
            int LA151_0 = input.LA(1);

            if ( (LA151_0==LBRACE) ) {
                alt151=1;
            }
            else if ( ((LA151_0>=IDENTIFIER && LA151_0<=NULL)||LA151_0==BOOLEAN||LA151_0==BYTE||LA151_0==CHAR||LA151_0==DOUBLE||LA151_0==FLOAT||LA151_0==INT||LA151_0==LONG||LA151_0==NEW||LA151_0==SHORT||LA151_0==SUPER||LA151_0==THIS||LA151_0==VOID||LA151_0==LPAREN||(LA151_0>=BANG && LA151_0<=TILDE)||(LA151_0>=PLUSPLUS && LA151_0<=SUB)) ) {
                alt151=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 151, 0, input);

                throw nvae;
            }
            switch (alt151) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1232:9: arrayInitializer
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_arrayInitializer_in_variableInitializer7438);
                    arrayInitializer567=arrayInitializer();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arrayInitializer567.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1233:9: expression
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_expression_in_variableInitializer7448);
                    expression568=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression568.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 95, variableInitializer_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "variableInitializer"

    public static class arrayInitializer_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arrayInitializer"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1236:1: arrayInitializer : '{' ( variableInitializer ( ',' variableInitializer )* )? ( ',' )? '}' ;
    public final JavaParser.arrayInitializer_return arrayInitializer() throws RecognitionException {
        JavaParser.arrayInitializer_return retval = new JavaParser.arrayInitializer_return();
        retval.start = input.LT(1);
        int arrayInitializer_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal569=null;
        Token char_literal571=null;
        Token char_literal573=null;
        Token char_literal574=null;
        JavaParser.variableInitializer_return variableInitializer570 = null;

        JavaParser.variableInitializer_return variableInitializer572 = null;


        Object char_literal569_tree=null;
        Object char_literal571_tree=null;
        Object char_literal573_tree=null;
        Object char_literal574_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 96) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1237:5: ( '{' ( variableInitializer ( ',' variableInitializer )* )? ( ',' )? '}' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1237:9: '{' ( variableInitializer ( ',' variableInitializer )* )? ( ',' )? '}'
            {
            root_0 = (Object)adaptor.nil();

            char_literal569=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_arrayInitializer7468); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal569_tree = (Object)adaptor.create(char_literal569);
            adaptor.addChild(root_0, char_literal569_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1238:13: ( variableInitializer ( ',' variableInitializer )* )?
            int alt153=2;
            int LA153_0 = input.LA(1);

            if ( ((LA153_0>=IDENTIFIER && LA153_0<=NULL)||LA153_0==BOOLEAN||LA153_0==BYTE||LA153_0==CHAR||LA153_0==DOUBLE||LA153_0==FLOAT||LA153_0==INT||LA153_0==LONG||LA153_0==NEW||LA153_0==SHORT||LA153_0==SUPER||LA153_0==THIS||LA153_0==VOID||LA153_0==LPAREN||LA153_0==LBRACE||(LA153_0>=BANG && LA153_0<=TILDE)||(LA153_0>=PLUSPLUS && LA153_0<=SUB)) ) {
                alt153=1;
            }
            switch (alt153) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1238:14: variableInitializer ( ',' variableInitializer )*
                    {
                    pushFollow(FOLLOW_variableInitializer_in_arrayInitializer7484);
                    variableInitializer570=variableInitializer();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, variableInitializer570.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1239:17: ( ',' variableInitializer )*
                    loop152:
                    do {
                        int alt152=2;
                        int LA152_0 = input.LA(1);

                        if ( (LA152_0==COMMA) ) {
                            int LA152_1 = input.LA(2);

                            if ( ((LA152_1>=IDENTIFIER && LA152_1<=NULL)||LA152_1==BOOLEAN||LA152_1==BYTE||LA152_1==CHAR||LA152_1==DOUBLE||LA152_1==FLOAT||LA152_1==INT||LA152_1==LONG||LA152_1==NEW||LA152_1==SHORT||LA152_1==SUPER||LA152_1==THIS||LA152_1==VOID||LA152_1==LPAREN||LA152_1==LBRACE||(LA152_1>=BANG && LA152_1<=TILDE)||(LA152_1>=PLUSPLUS && LA152_1<=SUB)) ) {
                                alt152=1;
                            }


                        }


                        switch (alt152) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1239:18: ',' variableInitializer
                    	    {
                    	    char_literal571=(Token)match(input,COMMA,FOLLOW_COMMA_in_arrayInitializer7503); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal571_tree = (Object)adaptor.create(char_literal571);
                    	    adaptor.addChild(root_0, char_literal571_tree);
                    	    }
                    	    pushFollow(FOLLOW_variableInitializer_in_arrayInitializer7505);
                    	    variableInitializer572=variableInitializer();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, variableInitializer572.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop152;
                        }
                    } while (true);


                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1242:13: ( ',' )?
            int alt154=2;
            int LA154_0 = input.LA(1);

            if ( (LA154_0==COMMA) ) {
                alt154=1;
            }
            switch (alt154) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1242:14: ','
                    {
                    char_literal573=(Token)match(input,COMMA,FOLLOW_COMMA_in_arrayInitializer7555); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal573_tree = (Object)adaptor.create(char_literal573);
                    adaptor.addChild(root_0, char_literal573_tree);
                    }

                    }
                    break;

            }

            char_literal574=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_arrayInitializer7568); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal574_tree = (Object)adaptor.create(char_literal574);
            adaptor.addChild(root_0, char_literal574_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 96, arrayInitializer_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "arrayInitializer"

    public static class createdName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "createdName"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1247:1: createdName : ( classOrInterfaceType | primitiveType );
    public final JavaParser.createdName_return createdName() throws RecognitionException {
        JavaParser.createdName_return retval = new JavaParser.createdName_return();
        retval.start = input.LT(1);
        int createdName_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.classOrInterfaceType_return classOrInterfaceType575 = null;

        JavaParser.primitiveType_return primitiveType576 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 97) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1248:5: ( classOrInterfaceType | primitiveType )
            int alt155=2;
            int LA155_0 = input.LA(1);

            if ( (LA155_0==IDENTIFIER) ) {
                alt155=1;
            }
            else if ( (LA155_0==BOOLEAN||LA155_0==BYTE||LA155_0==CHAR||LA155_0==DOUBLE||LA155_0==FLOAT||LA155_0==INT||LA155_0==LONG||LA155_0==SHORT) ) {
                alt155=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 155, 0, input);

                throw nvae;
            }
            switch (alt155) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1248:9: classOrInterfaceType
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_classOrInterfaceType_in_createdName7602);
                    classOrInterfaceType575=classOrInterfaceType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classOrInterfaceType575.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1249:9: primitiveType
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_primitiveType_in_createdName7612);
                    primitiveType576=primitiveType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, primitiveType576.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 97, createdName_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "createdName"

    public static class innerCreator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "innerCreator"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1252:1: innerCreator : '.' 'new' ( nonWildcardTypeArguments )? IDENTIFIER ( typeArguments )? classCreatorRest ;
    public final JavaParser.innerCreator_return innerCreator() throws RecognitionException {
        JavaParser.innerCreator_return retval = new JavaParser.innerCreator_return();
        retval.start = input.LT(1);
        int innerCreator_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal577=null;
        Token string_literal578=null;
        Token IDENTIFIER580=null;
        JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments579 = null;

        JavaParser.typeArguments_return typeArguments581 = null;

        JavaParser.classCreatorRest_return classCreatorRest582 = null;


        Object char_literal577_tree=null;
        Object string_literal578_tree=null;
        Object IDENTIFIER580_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 98) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1253:5: ( '.' 'new' ( nonWildcardTypeArguments )? IDENTIFIER ( typeArguments )? classCreatorRest )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1253:9: '.' 'new' ( nonWildcardTypeArguments )? IDENTIFIER ( typeArguments )? classCreatorRest
            {
            root_0 = (Object)adaptor.nil();

            char_literal577=(Token)match(input,DOT,FOLLOW_DOT_in_innerCreator7633); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal577_tree = (Object)adaptor.create(char_literal577);
            adaptor.addChild(root_0, char_literal577_tree);
            }
            string_literal578=(Token)match(input,NEW,FOLLOW_NEW_in_innerCreator7635); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal578_tree = (Object)adaptor.create(string_literal578);
            adaptor.addChild(root_0, string_literal578_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1254:9: ( nonWildcardTypeArguments )?
            int alt156=2;
            int LA156_0 = input.LA(1);

            if ( (LA156_0==LT) ) {
                alt156=1;
            }
            switch (alt156) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1254:10: nonWildcardTypeArguments
                    {
                    pushFollow(FOLLOW_nonWildcardTypeArguments_in_innerCreator7646);
                    nonWildcardTypeArguments579=nonWildcardTypeArguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, nonWildcardTypeArguments579.getTree());

                    }
                    break;

            }

            IDENTIFIER580=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_innerCreator7667); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER580_tree = (Object)adaptor.create(IDENTIFIER580);
            adaptor.addChild(root_0, IDENTIFIER580_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1257:9: ( typeArguments )?
            int alt157=2;
            int LA157_0 = input.LA(1);

            if ( (LA157_0==LT) ) {
                alt157=1;
            }
            switch (alt157) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1257:10: typeArguments
                    {
                    pushFollow(FOLLOW_typeArguments_in_innerCreator7678);
                    typeArguments581=typeArguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeArguments581.getTree());

                    }
                    break;

            }

            pushFollow(FOLLOW_classCreatorRest_in_innerCreator7699);
            classCreatorRest582=classCreatorRest();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, classCreatorRest582.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 98, innerCreator_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "innerCreator"

    public static class classCreatorRest_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "classCreatorRest"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1263:1: classCreatorRest : arguments ( classBody )? ;
    public final JavaParser.classCreatorRest_return classCreatorRest() throws RecognitionException {
        JavaParser.classCreatorRest_return retval = new JavaParser.classCreatorRest_return();
        retval.start = input.LT(1);
        int classCreatorRest_StartIndex = input.index();
        Object root_0 = null;

        JavaParser.arguments_return arguments583 = null;

        JavaParser.classBody_return classBody584 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 99) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1264:5: ( arguments ( classBody )? )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1264:9: arguments ( classBody )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_arguments_in_classCreatorRest7720);
            arguments583=arguments();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments583.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1265:9: ( classBody )?
            int alt158=2;
            int LA158_0 = input.LA(1);

            if ( (LA158_0==LBRACE) ) {
                alt158=1;
            }
            switch (alt158) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1265:10: classBody
                    {
                    pushFollow(FOLLOW_classBody_in_classCreatorRest7731);
                    classBody584=classBody();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classBody584.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 99, classCreatorRest_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "classCreatorRest"

    public static class nonWildcardTypeArguments_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "nonWildcardTypeArguments"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1270:1: nonWildcardTypeArguments : '<' typeList '>' ;
    public final JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments() throws RecognitionException {
        JavaParser.nonWildcardTypeArguments_return retval = new JavaParser.nonWildcardTypeArguments_return();
        retval.start = input.LT(1);
        int nonWildcardTypeArguments_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal585=null;
        Token char_literal587=null;
        JavaParser.typeList_return typeList586 = null;


        Object char_literal585_tree=null;
        Object char_literal587_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 100) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1271:5: ( '<' typeList '>' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1271:9: '<' typeList '>'
            {
            root_0 = (Object)adaptor.nil();

            char_literal585=(Token)match(input,LT,FOLLOW_LT_in_nonWildcardTypeArguments7763); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal585_tree = (Object)adaptor.create(char_literal585);
            adaptor.addChild(root_0, char_literal585_tree);
            }
            pushFollow(FOLLOW_typeList_in_nonWildcardTypeArguments7765);
            typeList586=typeList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeList586.getTree());
            char_literal587=(Token)match(input,GT,FOLLOW_GT_in_nonWildcardTypeArguments7775); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal587_tree = (Object)adaptor.create(char_literal587);
            adaptor.addChild(root_0, char_literal587_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 100, nonWildcardTypeArguments_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "nonWildcardTypeArguments"

    public static class arguments_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arguments"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1275:1: arguments : '(' ( expressionList )? ')' ;
    public final JavaParser.arguments_return arguments() throws RecognitionException {
        JavaParser.arguments_return retval = new JavaParser.arguments_return();
        retval.start = input.LT(1);
        int arguments_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal588=null;
        Token char_literal590=null;
        JavaParser.expressionList_return expressionList589 = null;


        Object char_literal588_tree=null;
        Object char_literal590_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 101) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1276:5: ( '(' ( expressionList )? ')' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1276:9: '(' ( expressionList )? ')'
            {
            root_0 = (Object)adaptor.nil();

            char_literal588=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_arguments7795); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal588_tree = (Object)adaptor.create(char_literal588);
            adaptor.addChild(root_0, char_literal588_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1276:13: ( expressionList )?
            int alt159=2;
            int LA159_0 = input.LA(1);

            if ( ((LA159_0>=IDENTIFIER && LA159_0<=NULL)||LA159_0==BOOLEAN||LA159_0==BYTE||LA159_0==CHAR||LA159_0==DOUBLE||LA159_0==FLOAT||LA159_0==INT||LA159_0==LONG||LA159_0==NEW||LA159_0==SHORT||LA159_0==SUPER||LA159_0==THIS||LA159_0==VOID||LA159_0==LPAREN||(LA159_0>=BANG && LA159_0<=TILDE)||(LA159_0>=PLUSPLUS && LA159_0<=SUB)) ) {
                alt159=1;
            }
            switch (alt159) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1276:14: expressionList
                    {
                    pushFollow(FOLLOW_expressionList_in_arguments7798);
                    expressionList589=expressionList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expressionList589.getTree());

                    }
                    break;

            }

            char_literal590=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_arguments7811); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal590_tree = (Object)adaptor.create(char_literal590);
            adaptor.addChild(root_0, char_literal590_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 101, arguments_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "arguments"

    public static class literal_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "literal"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1280:1: literal : ( INTLITERAL | LONGLITERAL | FLOATLITERAL | DOUBLELITERAL | CHARLITERAL | STRINGLITERAL | TRUE | FALSE | NULL );
    public final JavaParser.literal_return literal() throws RecognitionException {
        JavaParser.literal_return retval = new JavaParser.literal_return();
        retval.start = input.LT(1);
        int literal_StartIndex = input.index();
        Object root_0 = null;

        Token set591=null;

        Object set591_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 102) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1281:5: ( INTLITERAL | LONGLITERAL | FLOATLITERAL | DOUBLELITERAL | CHARLITERAL | STRINGLITERAL | TRUE | FALSE | NULL )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:
            {
            root_0 = (Object)adaptor.nil();

            set591=(Token)input.LT(1);
            if ( (input.LA(1)>=INTLITERAL && input.LA(1)<=NULL) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set591));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 102, literal_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "literal"

    public static class classHeader_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "classHeader"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1292:1: classHeader : modifiers 'class' IDENTIFIER ;
    public final JavaParser.classHeader_return classHeader() throws RecognitionException {
        JavaParser.classHeader_return retval = new JavaParser.classHeader_return();
        retval.start = input.LT(1);
        int classHeader_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal593=null;
        Token IDENTIFIER594=null;
        JavaParser.modifiers_return modifiers592 = null;


        Object string_literal593_tree=null;
        Object IDENTIFIER594_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 103) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1297:5: ( modifiers 'class' IDENTIFIER )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1297:9: modifiers 'class' IDENTIFIER
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_modifiers_in_classHeader7935);
            modifiers592=modifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers592.getTree());
            string_literal593=(Token)match(input,CLASS,FOLLOW_CLASS_in_classHeader7937); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal593_tree = (Object)adaptor.create(string_literal593);
            adaptor.addChild(root_0, string_literal593_tree);
            }
            IDENTIFIER594=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_classHeader7939); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER594_tree = (Object)adaptor.create(IDENTIFIER594);
            adaptor.addChild(root_0, IDENTIFIER594_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 103, classHeader_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "classHeader"

    public static class enumHeader_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "enumHeader"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1300:1: enumHeader : modifiers ( 'enum' | IDENTIFIER ) IDENTIFIER ;
    public final JavaParser.enumHeader_return enumHeader() throws RecognitionException {
        JavaParser.enumHeader_return retval = new JavaParser.enumHeader_return();
        retval.start = input.LT(1);
        int enumHeader_StartIndex = input.index();
        Object root_0 = null;

        Token set596=null;
        Token IDENTIFIER597=null;
        JavaParser.modifiers_return modifiers595 = null;


        Object set596_tree=null;
        Object IDENTIFIER597_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 104) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1301:5: ( modifiers ( 'enum' | IDENTIFIER ) IDENTIFIER )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1301:9: modifiers ( 'enum' | IDENTIFIER ) IDENTIFIER
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_modifiers_in_enumHeader7959);
            modifiers595=modifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers595.getTree());
            set596=(Token)input.LT(1);
            if ( input.LA(1)==IDENTIFIER||input.LA(1)==ENUM ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set596));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            IDENTIFIER597=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_enumHeader7967); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER597_tree = (Object)adaptor.create(IDENTIFIER597);
            adaptor.addChild(root_0, IDENTIFIER597_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 104, enumHeader_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "enumHeader"

    public static class interfaceHeader_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "interfaceHeader"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1304:1: interfaceHeader : modifiers 'interface' IDENTIFIER ;
    public final JavaParser.interfaceHeader_return interfaceHeader() throws RecognitionException {
        JavaParser.interfaceHeader_return retval = new JavaParser.interfaceHeader_return();
        retval.start = input.LT(1);
        int interfaceHeader_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal599=null;
        Token IDENTIFIER600=null;
        JavaParser.modifiers_return modifiers598 = null;


        Object string_literal599_tree=null;
        Object IDENTIFIER600_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 105) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1305:5: ( modifiers 'interface' IDENTIFIER )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1305:9: modifiers 'interface' IDENTIFIER
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_modifiers_in_interfaceHeader7987);
            modifiers598=modifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers598.getTree());
            string_literal599=(Token)match(input,INTERFACE,FOLLOW_INTERFACE_in_interfaceHeader7989); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal599_tree = (Object)adaptor.create(string_literal599);
            adaptor.addChild(root_0, string_literal599_tree);
            }
            IDENTIFIER600=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_interfaceHeader7991); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER600_tree = (Object)adaptor.create(IDENTIFIER600);
            adaptor.addChild(root_0, IDENTIFIER600_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 105, interfaceHeader_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "interfaceHeader"

    public static class annotationHeader_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "annotationHeader"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1308:1: annotationHeader : modifiers '@' 'interface' IDENTIFIER ;
    public final JavaParser.annotationHeader_return annotationHeader() throws RecognitionException {
        JavaParser.annotationHeader_return retval = new JavaParser.annotationHeader_return();
        retval.start = input.LT(1);
        int annotationHeader_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal602=null;
        Token string_literal603=null;
        Token IDENTIFIER604=null;
        JavaParser.modifiers_return modifiers601 = null;


        Object char_literal602_tree=null;
        Object string_literal603_tree=null;
        Object IDENTIFIER604_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 106) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1309:5: ( modifiers '@' 'interface' IDENTIFIER )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1309:9: modifiers '@' 'interface' IDENTIFIER
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_modifiers_in_annotationHeader8011);
            modifiers601=modifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers601.getTree());
            char_literal602=(Token)match(input,MONKEYS_AT,FOLLOW_MONKEYS_AT_in_annotationHeader8013); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal602_tree = (Object)adaptor.create(char_literal602);
            adaptor.addChild(root_0, char_literal602_tree);
            }
            string_literal603=(Token)match(input,INTERFACE,FOLLOW_INTERFACE_in_annotationHeader8015); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal603_tree = (Object)adaptor.create(string_literal603);
            adaptor.addChild(root_0, string_literal603_tree);
            }
            IDENTIFIER604=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_annotationHeader8017); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER604_tree = (Object)adaptor.create(IDENTIFIER604);
            adaptor.addChild(root_0, IDENTIFIER604_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 106, annotationHeader_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "annotationHeader"

    public static class typeHeader_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typeHeader"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1312:1: typeHeader : modifiers ( 'class' | 'enum' | ( ( '@' )? 'interface' ) ) IDENTIFIER ;
    public final JavaParser.typeHeader_return typeHeader() throws RecognitionException {
        JavaParser.typeHeader_return retval = new JavaParser.typeHeader_return();
        retval.start = input.LT(1);
        int typeHeader_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal606=null;
        Token string_literal607=null;
        Token char_literal608=null;
        Token string_literal609=null;
        Token IDENTIFIER610=null;
        JavaParser.modifiers_return modifiers605 = null;


        Object string_literal606_tree=null;
        Object string_literal607_tree=null;
        Object char_literal608_tree=null;
        Object string_literal609_tree=null;
        Object IDENTIFIER610_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 107) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1313:5: ( modifiers ( 'class' | 'enum' | ( ( '@' )? 'interface' ) ) IDENTIFIER )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1313:9: modifiers ( 'class' | 'enum' | ( ( '@' )? 'interface' ) ) IDENTIFIER
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_modifiers_in_typeHeader8037);
            modifiers605=modifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers605.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1313:19: ( 'class' | 'enum' | ( ( '@' )? 'interface' ) )
            int alt161=3;
            switch ( input.LA(1) ) {
            case CLASS:
                {
                alt161=1;
                }
                break;
            case ENUM:
                {
                alt161=2;
                }
                break;
            case INTERFACE:
            case MONKEYS_AT:
                {
                alt161=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 161, 0, input);

                throw nvae;
            }

            switch (alt161) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1313:20: 'class'
                    {
                    string_literal606=(Token)match(input,CLASS,FOLLOW_CLASS_in_typeHeader8040); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal606_tree = (Object)adaptor.create(string_literal606);
                    adaptor.addChild(root_0, string_literal606_tree);
                    }

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1313:28: 'enum'
                    {
                    string_literal607=(Token)match(input,ENUM,FOLLOW_ENUM_in_typeHeader8042); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal607_tree = (Object)adaptor.create(string_literal607);
                    adaptor.addChild(root_0, string_literal607_tree);
                    }

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1313:35: ( ( '@' )? 'interface' )
                    {
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1313:35: ( ( '@' )? 'interface' )
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1313:36: ( '@' )? 'interface'
                    {
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1313:36: ( '@' )?
                    int alt160=2;
                    int LA160_0 = input.LA(1);

                    if ( (LA160_0==MONKEYS_AT) ) {
                        alt160=1;
                    }
                    switch (alt160) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:0:0: '@'
                            {
                            char_literal608=(Token)match(input,MONKEYS_AT,FOLLOW_MONKEYS_AT_in_typeHeader8045); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal608_tree = (Object)adaptor.create(char_literal608);
                            adaptor.addChild(root_0, char_literal608_tree);
                            }

                            }
                            break;

                    }

                    string_literal609=(Token)match(input,INTERFACE,FOLLOW_INTERFACE_in_typeHeader8049); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal609_tree = (Object)adaptor.create(string_literal609);
                    adaptor.addChild(root_0, string_literal609_tree);
                    }

                    }


                    }
                    break;

            }

            IDENTIFIER610=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_typeHeader8053); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER610_tree = (Object)adaptor.create(IDENTIFIER610);
            adaptor.addChild(root_0, IDENTIFIER610_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 107, typeHeader_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "typeHeader"

    public static class methodHeader_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "methodHeader"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1316:1: methodHeader : modifiers ( typeParameters )? ( type | 'void' )? IDENTIFIER '(' ;
    public final JavaParser.methodHeader_return methodHeader() throws RecognitionException {
        JavaParser.methodHeader_return retval = new JavaParser.methodHeader_return();
        retval.start = input.LT(1);
        int methodHeader_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal614=null;
        Token IDENTIFIER615=null;
        Token char_literal616=null;
        JavaParser.modifiers_return modifiers611 = null;

        JavaParser.typeParameters_return typeParameters612 = null;

        JavaParser.type_return type613 = null;


        Object string_literal614_tree=null;
        Object IDENTIFIER615_tree=null;
        Object char_literal616_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 108) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1317:5: ( modifiers ( typeParameters )? ( type | 'void' )? IDENTIFIER '(' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1317:9: modifiers ( typeParameters )? ( type | 'void' )? IDENTIFIER '('
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_modifiers_in_methodHeader8073);
            modifiers611=modifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers611.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1317:19: ( typeParameters )?
            int alt162=2;
            int LA162_0 = input.LA(1);

            if ( (LA162_0==LT) ) {
                alt162=1;
            }
            switch (alt162) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:0:0: typeParameters
                    {
                    pushFollow(FOLLOW_typeParameters_in_methodHeader8075);
                    typeParameters612=typeParameters();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeParameters612.getTree());

                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1317:35: ( type | 'void' )?
            int alt163=3;
            switch ( input.LA(1) ) {
                case IDENTIFIER:
                    {
                    int LA163_1 = input.LA(2);

                    if ( (LA163_1==IDENTIFIER||LA163_1==LBRACKET||LA163_1==DOT||LA163_1==LT) ) {
                        alt163=1;
                    }
                    }
                    break;
                case BOOLEAN:
                case BYTE:
                case CHAR:
                case DOUBLE:
                case FLOAT:
                case INT:
                case LONG:
                case SHORT:
                    {
                    alt163=1;
                    }
                    break;
                case VOID:
                    {
                    alt163=2;
                    }
                    break;
            }

            switch (alt163) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1317:36: type
                    {
                    pushFollow(FOLLOW_type_in_methodHeader8079);
                    type613=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, type613.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1317:41: 'void'
                    {
                    string_literal614=(Token)match(input,VOID,FOLLOW_VOID_in_methodHeader8081); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal614_tree = (Object)adaptor.create(string_literal614);
                    adaptor.addChild(root_0, string_literal614_tree);
                    }

                    }
                    break;

            }

            IDENTIFIER615=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_methodHeader8085); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER615_tree = (Object)adaptor.create(IDENTIFIER615);
            adaptor.addChild(root_0, IDENTIFIER615_tree);
            }
            char_literal616=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_methodHeader8087); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal616_tree = (Object)adaptor.create(char_literal616);
            adaptor.addChild(root_0, char_literal616_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 108, methodHeader_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "methodHeader"

    public static class fieldHeader_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "fieldHeader"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1320:1: fieldHeader : modifiers type IDENTIFIER ( '[' ']' )* ( '=' | ',' | ';' ) ;
    public final JavaParser.fieldHeader_return fieldHeader() throws RecognitionException {
        JavaParser.fieldHeader_return retval = new JavaParser.fieldHeader_return();
        retval.start = input.LT(1);
        int fieldHeader_StartIndex = input.index();
        Object root_0 = null;

        Token IDENTIFIER619=null;
        Token char_literal620=null;
        Token char_literal621=null;
        Token set622=null;
        JavaParser.modifiers_return modifiers617 = null;

        JavaParser.type_return type618 = null;


        Object IDENTIFIER619_tree=null;
        Object char_literal620_tree=null;
        Object char_literal621_tree=null;
        Object set622_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 109) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1321:5: ( modifiers type IDENTIFIER ( '[' ']' )* ( '=' | ',' | ';' ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1321:9: modifiers type IDENTIFIER ( '[' ']' )* ( '=' | ',' | ';' )
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_modifiers_in_fieldHeader8107);
            modifiers617=modifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers617.getTree());
            pushFollow(FOLLOW_type_in_fieldHeader8109);
            type618=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, type618.getTree());
            IDENTIFIER619=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_fieldHeader8111); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER619_tree = (Object)adaptor.create(IDENTIFIER619);
            adaptor.addChild(root_0, IDENTIFIER619_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1321:35: ( '[' ']' )*
            loop164:
            do {
                int alt164=2;
                int LA164_0 = input.LA(1);

                if ( (LA164_0==LBRACKET) ) {
                    alt164=1;
                }


                switch (alt164) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1321:36: '[' ']'
            	    {
            	    char_literal620=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_fieldHeader8114); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal620_tree = (Object)adaptor.create(char_literal620);
            	    adaptor.addChild(root_0, char_literal620_tree);
            	    }
            	    char_literal621=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_fieldHeader8115); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal621_tree = (Object)adaptor.create(char_literal621);
            	    adaptor.addChild(root_0, char_literal621_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop164;
                }
            } while (true);

            set622=(Token)input.LT(1);
            if ( (input.LA(1)>=SEMI && input.LA(1)<=COMMA)||input.LA(1)==EQ ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set622));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 109, fieldHeader_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "fieldHeader"

    public static class localVariableHeader_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "localVariableHeader"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1324:1: localVariableHeader : variableModifiers type IDENTIFIER ( '[' ']' )* ( '=' | ',' | ';' ) ;
    public final JavaParser.localVariableHeader_return localVariableHeader() throws RecognitionException {
        JavaParser.localVariableHeader_return retval = new JavaParser.localVariableHeader_return();
        retval.start = input.LT(1);
        int localVariableHeader_StartIndex = input.index();
        Object root_0 = null;

        Token IDENTIFIER625=null;
        Token char_literal626=null;
        Token char_literal627=null;
        Token set628=null;
        JavaParser.variableModifiers_return variableModifiers623 = null;

        JavaParser.type_return type624 = null;


        Object IDENTIFIER625_tree=null;
        Object char_literal626_tree=null;
        Object char_literal627_tree=null;
        Object set628_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 110) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1325:5: ( variableModifiers type IDENTIFIER ( '[' ']' )* ( '=' | ',' | ';' ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1325:9: variableModifiers type IDENTIFIER ( '[' ']' )* ( '=' | ',' | ';' )
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_variableModifiers_in_localVariableHeader8145);
            variableModifiers623=variableModifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variableModifiers623.getTree());
            pushFollow(FOLLOW_type_in_localVariableHeader8147);
            type624=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, type624.getTree());
            IDENTIFIER625=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_localVariableHeader8149); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER625_tree = (Object)adaptor.create(IDENTIFIER625);
            adaptor.addChild(root_0, IDENTIFIER625_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1325:43: ( '[' ']' )*
            loop165:
            do {
                int alt165=2;
                int LA165_0 = input.LA(1);

                if ( (LA165_0==LBRACKET) ) {
                    alt165=1;
                }


                switch (alt165) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1325:44: '[' ']'
            	    {
            	    char_literal626=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_localVariableHeader8152); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal626_tree = (Object)adaptor.create(char_literal626);
            	    adaptor.addChild(root_0, char_literal626_tree);
            	    }
            	    char_literal627=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_localVariableHeader8153); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal627_tree = (Object)adaptor.create(char_literal627);
            	    adaptor.addChild(root_0, char_literal627_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop165;
                }
            } while (true);

            set628=(Token)input.LT(1);
            if ( (input.LA(1)>=SEMI && input.LA(1)<=COMMA)||input.LA(1)==EQ ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set628));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 110, localVariableHeader_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "localVariableHeader"

    // $ANTLR start synpred2_Java
    public final void synpred2_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:323:13: ( ( annotations )? packageDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:323:13: ( annotations )? packageDeclaration
        {
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:323:13: ( annotations )?
        int alt166=2;
        int LA166_0 = input.LA(1);

        if ( (LA166_0==MONKEYS_AT) ) {
            alt166=1;
        }
        switch (alt166) {
            case 1 :
                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:323:14: annotations
                {
                pushFollow(FOLLOW_annotations_in_synpred2_Java115);
                annotations();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_packageDeclaration_in_synpred2_Java144);
        packageDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_Java

    // $ANTLR start synpred12_Java
    public final void synpred12_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:372:10: ( classDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:372:10: classDeclaration
        {
        pushFollow(FOLLOW_classDeclaration_in_synpred12_Java512);
        classDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred12_Java

    // $ANTLR start synpred27_Java
    public final void synpred27_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:403:9: ( normalClassDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:403:9: normalClassDeclaration
        {
        pushFollow(FOLLOW_normalClassDeclaration_in_synpred27_Java749);
        normalClassDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred27_Java

    // $ANTLR start synpred43_Java
    public final void synpred43_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:503:9: ( normalInterfaceDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:503:9: normalInterfaceDeclaration
        {
        pushFollow(FOLLOW_normalInterfaceDeclaration_in_synpred43_Java1448);
        normalInterfaceDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred43_Java

    // $ANTLR start synpred52_Java
    public final void synpred52_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:545:10: ( fieldDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:545:10: fieldDeclaration
        {
        pushFollow(FOLLOW_fieldDeclaration_in_synpred52_Java1778);
        fieldDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred52_Java

    // $ANTLR start synpred53_Java
    public final void synpred53_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:546:10: ( methodDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:546:10: methodDeclaration
        {
        pushFollow(FOLLOW_methodDeclaration_in_synpred53_Java1789);
        methodDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred53_Java

    // $ANTLR start synpred54_Java
    public final void synpred54_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:547:10: ( classDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:547:10: classDeclaration
        {
        pushFollow(FOLLOW_classDeclaration_in_synpred54_Java1800);
        classDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred54_Java

    // $ANTLR start synpred57_Java
    public final void synpred57_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:563:10: ( explicitConstructorInvocation )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:563:10: explicitConstructorInvocation
        {
        pushFollow(FOLLOW_explicitConstructorInvocation_in_synpred57_Java1937);
        explicitConstructorInvocation();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred57_Java

    // $ANTLR start synpred59_Java
    public final void synpred59_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:555:10: ( modifiers ( typeParameters )? IDENTIFIER formalParameters ( 'throws' qualifiedNameList )? '{' ( explicitConstructorInvocation )? ( blockStatement )* '}' )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:555:10: modifiers ( typeParameters )? IDENTIFIER formalParameters ( 'throws' qualifiedNameList )? '{' ( explicitConstructorInvocation )? ( blockStatement )* '}'
        {
        pushFollow(FOLLOW_modifiers_in_synpred59_Java1849);
        modifiers();

        state._fsp--;
        if (state.failed) return ;
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:556:9: ( typeParameters )?
        int alt169=2;
        int LA169_0 = input.LA(1);

        if ( (LA169_0==LT) ) {
            alt169=1;
        }
        switch (alt169) {
            case 1 :
                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:556:10: typeParameters
                {
                pushFollow(FOLLOW_typeParameters_in_synpred59_Java1860);
                typeParameters();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_synpred59_Java1881); if (state.failed) return ;
        pushFollow(FOLLOW_formalParameters_in_synpred59_Java1891);
        formalParameters();

        state._fsp--;
        if (state.failed) return ;
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:560:9: ( 'throws' qualifiedNameList )?
        int alt170=2;
        int LA170_0 = input.LA(1);

        if ( (LA170_0==THROWS) ) {
            alt170=1;
        }
        switch (alt170) {
            case 1 :
                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:560:10: 'throws' qualifiedNameList
                {
                match(input,THROWS,FOLLOW_THROWS_in_synpred59_Java1902); if (state.failed) return ;
                pushFollow(FOLLOW_qualifiedNameList_in_synpred59_Java1904);
                qualifiedNameList();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,LBRACE,FOLLOW_LBRACE_in_synpred59_Java1925); if (state.failed) return ;
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:563:9: ( explicitConstructorInvocation )?
        int alt171=2;
        alt171 = dfa171.predict(input);
        switch (alt171) {
            case 1 :
                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:563:10: explicitConstructorInvocation
                {
                pushFollow(FOLLOW_explicitConstructorInvocation_in_synpred59_Java1937);
                explicitConstructorInvocation();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:565:9: ( blockStatement )*
        loop172:
        do {
            int alt172=2;
            int LA172_0 = input.LA(1);

            if ( ((LA172_0>=IDENTIFIER && LA172_0<=NULL)||(LA172_0>=ABSTRACT && LA172_0<=BYTE)||(LA172_0>=CHAR && LA172_0<=CLASS)||LA172_0==CONTINUE||(LA172_0>=DO && LA172_0<=DOUBLE)||LA172_0==ENUM||LA172_0==FINAL||(LA172_0>=FLOAT && LA172_0<=FOR)||LA172_0==IF||(LA172_0>=INT && LA172_0<=NEW)||(LA172_0>=PRIVATE && LA172_0<=THROW)||(LA172_0>=TRANSIENT && LA172_0<=LPAREN)||LA172_0==LBRACE||LA172_0==SEMI||(LA172_0>=BANG && LA172_0<=TILDE)||(LA172_0>=PLUSPLUS && LA172_0<=SUB)||LA172_0==MONKEYS_AT||LA172_0==LT) ) {
                alt172=1;
            }


            switch (alt172) {
        	case 1 :
        	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:565:10: blockStatement
        	    {
        	    pushFollow(FOLLOW_blockStatement_in_synpred59_Java1959);
        	    blockStatement();

        	    state._fsp--;
        	    if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop172;
            }
        } while (true);

        match(input,RBRACE,FOLLOW_RBRACE_in_synpred59_Java1980); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred59_Java

    // $ANTLR start synpred68_Java
    public final void synpred68_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:615:9: ( interfaceFieldDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:615:9: interfaceFieldDeclaration
        {
        pushFollow(FOLLOW_interfaceFieldDeclaration_in_synpred68_Java2365);
        interfaceFieldDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred68_Java

    // $ANTLR start synpred69_Java
    public final void synpred69_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:616:9: ( interfaceMethodDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:616:9: interfaceMethodDeclaration
        {
        pushFollow(FOLLOW_interfaceMethodDeclaration_in_synpred69_Java2375);
        interfaceMethodDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred69_Java

    // $ANTLR start synpred70_Java
    public final void synpred70_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:617:9: ( interfaceDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:617:9: interfaceDeclaration
        {
        pushFollow(FOLLOW_interfaceDeclaration_in_synpred70_Java2385);
        interfaceDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred70_Java

    // $ANTLR start synpred71_Java
    public final void synpred71_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:618:9: ( classDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:618:9: classDeclaration
        {
        pushFollow(FOLLOW_classDeclaration_in_synpred71_Java2395);
        classDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred71_Java

    // $ANTLR start synpred96_Java
    public final void synpred96_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:713:9: ( ellipsisParameterDecl )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:713:9: ellipsisParameterDecl
        {
        pushFollow(FOLLOW_ellipsisParameterDecl_in_synpred96_Java3159);
        ellipsisParameterDecl();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred96_Java

    // $ANTLR start synpred98_Java
    public final void synpred98_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:714:9: ( normalParameterDecl ( ',' normalParameterDecl )* )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:714:9: normalParameterDecl ( ',' normalParameterDecl )*
        {
        pushFollow(FOLLOW_normalParameterDecl_in_synpred98_Java3169);
        normalParameterDecl();

        state._fsp--;
        if (state.failed) return ;
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:715:9: ( ',' normalParameterDecl )*
        loop175:
        do {
            int alt175=2;
            int LA175_0 = input.LA(1);

            if ( (LA175_0==COMMA) ) {
                alt175=1;
            }


            switch (alt175) {
        	case 1 :
        	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:715:10: ',' normalParameterDecl
        	    {
        	    match(input,COMMA,FOLLOW_COMMA_in_synpred98_Java3180); if (state.failed) return ;
        	    pushFollow(FOLLOW_normalParameterDecl_in_synpred98_Java3182);
        	    normalParameterDecl();

        	    state._fsp--;
        	    if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop175;
            }
        } while (true);


        }
    }
    // $ANTLR end synpred98_Java

    // $ANTLR start synpred99_Java
    public final void synpred99_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:717:10: ( normalParameterDecl ',' )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:717:10: normalParameterDecl ','
        {
        pushFollow(FOLLOW_normalParameterDecl_in_synpred99_Java3204);
        normalParameterDecl();

        state._fsp--;
        if (state.failed) return ;
        match(input,COMMA,FOLLOW_COMMA_in_synpred99_Java3214); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred99_Java

    // $ANTLR start synpred103_Java
    public final void synpred103_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:737:9: ( ( nonWildcardTypeArguments )? ( 'this' | 'super' ) arguments ';' )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:737:9: ( nonWildcardTypeArguments )? ( 'this' | 'super' ) arguments ';'
        {
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:737:9: ( nonWildcardTypeArguments )?
        int alt176=2;
        int LA176_0 = input.LA(1);

        if ( (LA176_0==LT) ) {
            alt176=1;
        }
        switch (alt176) {
            case 1 :
                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:737:10: nonWildcardTypeArguments
                {
                pushFollow(FOLLOW_nonWildcardTypeArguments_in_synpred103_Java3349);
                nonWildcardTypeArguments();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        if ( input.LA(1)==SUPER||input.LA(1)==THIS ) {
            input.consume();
            state.errorRecovery=false;state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }

        pushFollow(FOLLOW_arguments_in_synpred103_Java3407);
        arguments();

        state._fsp--;
        if (state.failed) return ;
        match(input,SEMI,FOLLOW_SEMI_in_synpred103_Java3409); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred103_Java

    // $ANTLR start synpred117_Java
    public final void synpred117_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:824:9: ( annotationMethodDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:824:9: annotationMethodDeclaration
        {
        pushFollow(FOLLOW_annotationMethodDeclaration_in_synpred117_Java4008);
        annotationMethodDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred117_Java

    // $ANTLR start synpred118_Java
    public final void synpred118_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:825:9: ( interfaceFieldDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:825:9: interfaceFieldDeclaration
        {
        pushFollow(FOLLOW_interfaceFieldDeclaration_in_synpred118_Java4018);
        interfaceFieldDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred118_Java

    // $ANTLR start synpred119_Java
    public final void synpred119_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:826:9: ( normalClassDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:826:9: normalClassDeclaration
        {
        pushFollow(FOLLOW_normalClassDeclaration_in_synpred119_Java4028);
        normalClassDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred119_Java

    // $ANTLR start synpred120_Java
    public final void synpred120_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:827:9: ( normalInterfaceDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:827:9: normalInterfaceDeclaration
        {
        pushFollow(FOLLOW_normalInterfaceDeclaration_in_synpred120_Java4038);
        normalInterfaceDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred120_Java

    // $ANTLR start synpred121_Java
    public final void synpred121_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:828:9: ( enumDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:828:9: enumDeclaration
        {
        pushFollow(FOLLOW_enumDeclaration_in_synpred121_Java4048);
        enumDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred121_Java

    // $ANTLR start synpred122_Java
    public final void synpred122_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:829:9: ( annotationTypeDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:829:9: annotationTypeDeclaration
        {
        pushFollow(FOLLOW_annotationTypeDeclaration_in_synpred122_Java4058);
        annotationTypeDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred122_Java

    // $ANTLR start synpred125_Java
    public final void synpred125_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:872:9: ( localVariableDeclarationStatement )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:872:9: localVariableDeclarationStatement
        {
        pushFollow(FOLLOW_localVariableDeclarationStatement_in_synpred125_Java4216);
        localVariableDeclarationStatement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred125_Java

    // $ANTLR start synpred126_Java
    public final void synpred126_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:873:9: ( classOrInterfaceDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:873:9: classOrInterfaceDeclaration
        {
        pushFollow(FOLLOW_classOrInterfaceDeclaration_in_synpred126_Java4226);
        classOrInterfaceDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred126_Java

    // $ANTLR start synpred130_Java
    public final void synpred130_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:893:9: ( ( 'assert' ) expression ( ':' expression )? ';' )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:893:9: ( 'assert' ) expression ( ':' expression )? ';'
        {
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:893:9: ( 'assert' )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:893:10: 'assert'
        {
        match(input,ASSERT,FOLLOW_ASSERT_in_synpred130_Java4367); if (state.failed) return ;

        }

        pushFollow(FOLLOW_expression_in_synpred130_Java4387);
        expression();

        state._fsp--;
        if (state.failed) return ;
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:895:20: ( ':' expression )?
        int alt179=2;
        int LA179_0 = input.LA(1);

        if ( (LA179_0==COLON) ) {
            alt179=1;
        }
        switch (alt179) {
            case 1 :
                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:895:21: ':' expression
                {
                match(input,COLON,FOLLOW_COLON_in_synpred130_Java4390); if (state.failed) return ;
                pushFollow(FOLLOW_expression_in_synpred130_Java4392);
                expression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,SEMI,FOLLOW_SEMI_in_synpred130_Java4396); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred130_Java

    // $ANTLR start synpred132_Java
    public final void synpred132_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:896:9: ( 'assert' expression ( ':' expression )? ';' )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:896:9: 'assert' expression ( ':' expression )? ';'
        {
        match(input,ASSERT,FOLLOW_ASSERT_in_synpred132_Java4406); if (state.failed) return ;
        pushFollow(FOLLOW_expression_in_synpred132_Java4409);
        expression();

        state._fsp--;
        if (state.failed) return ;
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:896:30: ( ':' expression )?
        int alt180=2;
        int LA180_0 = input.LA(1);

        if ( (LA180_0==COLON) ) {
            alt180=1;
        }
        switch (alt180) {
            case 1 :
                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:896:31: ':' expression
                {
                match(input,COLON,FOLLOW_COLON_in_synpred132_Java4412); if (state.failed) return ;
                pushFollow(FOLLOW_expression_in_synpred132_Java4414);
                expression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,SEMI,FOLLOW_SEMI_in_synpred132_Java4418); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred132_Java

    // $ANTLR start synpred133_Java
    public final void synpred133_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:897:39: ( 'else' statement )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:897:39: 'else' statement
        {
        match(input,ELSE,FOLLOW_ELSE_in_synpred133_Java4447); if (state.failed) return ;
        pushFollow(FOLLOW_statement_in_synpred133_Java4449);
        statement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred133_Java

    // $ANTLR start synpred148_Java
    public final void synpred148_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:912:9: ( expression ';' )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:912:9: expression ';'
        {
        pushFollow(FOLLOW_expression_in_synpred148_Java4671);
        expression();

        state._fsp--;
        if (state.failed) return ;
        match(input,SEMI,FOLLOW_SEMI_in_synpred148_Java4674); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred148_Java

    // $ANTLR start synpred149_Java
    public final void synpred149_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:913:9: ( IDENTIFIER ':' statement )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:913:9: IDENTIFIER ':' statement
        {
        match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_synpred149_Java4689); if (state.failed) return ;
        match(input,COLON,FOLLOW_COLON_in_synpred149_Java4691); if (state.failed) return ;
        pushFollow(FOLLOW_statement_in_synpred149_Java4693);
        statement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred149_Java

    // $ANTLR start synpred153_Java
    public final void synpred153_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:937:13: ( catches 'finally' block )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:937:13: catches 'finally' block
        {
        pushFollow(FOLLOW_catches_in_synpred153_Java4849);
        catches();

        state._fsp--;
        if (state.failed) return ;
        match(input,FINALLY,FOLLOW_FINALLY_in_synpred153_Java4851); if (state.failed) return ;
        pushFollow(FOLLOW_block_in_synpred153_Java4853);
        block();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred153_Java

    // $ANTLR start synpred154_Java
    public final void synpred154_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:938:13: ( catches )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:938:13: catches
        {
        pushFollow(FOLLOW_catches_in_synpred154_Java4867);
        catches();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred154_Java

    // $ANTLR start synpred157_Java
    public final void synpred157_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:963:9: ( 'for' '(' variableModifiers type IDENTIFIER ':' expression ')' statement )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:963:9: 'for' '(' variableModifiers type IDENTIFIER ':' expression ')' statement
        {
        match(input,FOR,FOLLOW_FOR_in_synpred157_Java5059); if (state.failed) return ;
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred157_Java5061); if (state.failed) return ;
        pushFollow(FOLLOW_variableModifiers_in_synpred157_Java5063);
        variableModifiers();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_type_in_synpred157_Java5065);
        type();

        state._fsp--;
        if (state.failed) return ;
        match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_synpred157_Java5067); if (state.failed) return ;
        match(input,COLON,FOLLOW_COLON_in_synpred157_Java5069); if (state.failed) return ;
        pushFollow(FOLLOW_expression_in_synpred157_Java5080);
        expression();

        state._fsp--;
        if (state.failed) return ;
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred157_Java5082); if (state.failed) return ;
        pushFollow(FOLLOW_statement_in_synpred157_Java5084);
        statement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred157_Java

    // $ANTLR start synpred161_Java
    public final void synpred161_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:977:9: ( localVariableDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:977:9: localVariableDeclaration
        {
        pushFollow(FOLLOW_localVariableDeclaration_in_synpred161_Java5263);
        localVariableDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred161_Java

    // $ANTLR start synpred202_Java
    public final void synpred202_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1131:9: ( castExpression )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1131:9: castExpression
        {
        pushFollow(FOLLOW_castExpression_in_synpred202_Java6508);
        castExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred202_Java

    // $ANTLR start synpred206_Java
    public final void synpred206_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1141:9: ( '(' primitiveType ')' unaryExpression )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1141:9: '(' primitiveType ')' unaryExpression
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred206_Java6599); if (state.failed) return ;
        pushFollow(FOLLOW_primitiveType_in_synpred206_Java6601);
        primitiveType();

        state._fsp--;
        if (state.failed) return ;
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred206_Java6603); if (state.failed) return ;
        pushFollow(FOLLOW_unaryExpression_in_synpred206_Java6605);
        unaryExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred206_Java

    // $ANTLR start synpred208_Java
    public final void synpred208_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1151:10: ( '.' IDENTIFIER )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1151:10: '.' IDENTIFIER
        {
        match(input,DOT,FOLLOW_DOT_in_synpred208_Java6676); if (state.failed) return ;
        match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_synpred208_Java6678); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred208_Java

    // $ANTLR start synpred209_Java
    public final void synpred209_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1153:10: ( identifierSuffix )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1153:10: identifierSuffix
        {
        pushFollow(FOLLOW_identifierSuffix_in_synpred209_Java6700);
        identifierSuffix();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred209_Java

    // $ANTLR start synpred211_Java
    public final void synpred211_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1156:10: ( '.' IDENTIFIER )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1156:10: '.' IDENTIFIER
        {
        match(input,DOT,FOLLOW_DOT_in_synpred211_Java6732); if (state.failed) return ;
        match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_synpred211_Java6734); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred211_Java

    // $ANTLR start synpred212_Java
    public final void synpred212_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1158:10: ( identifierSuffix )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1158:10: identifierSuffix
        {
        pushFollow(FOLLOW_identifierSuffix_in_synpred212_Java6756);
        identifierSuffix();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred212_Java

    // $ANTLR start synpred224_Java
    public final void synpred224_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1186:10: ( '[' expression ']' )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1186:10: '[' expression ']'
        {
        match(input,LBRACKET,FOLLOW_LBRACKET_in_synpred224_Java7007); if (state.failed) return ;
        pushFollow(FOLLOW_expression_in_synpred224_Java7009);
        expression();

        state._fsp--;
        if (state.failed) return ;
        match(input,RBRACKET,FOLLOW_RBRACKET_in_synpred224_Java7011); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred224_Java

    // $ANTLR start synpred236_Java
    public final void synpred236_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1209:9: ( 'new' nonWildcardTypeArguments classOrInterfaceType classCreatorRest )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1209:9: 'new' nonWildcardTypeArguments classOrInterfaceType classCreatorRest
        {
        match(input,NEW,FOLLOW_NEW_in_synpred236_Java7220); if (state.failed) return ;
        pushFollow(FOLLOW_nonWildcardTypeArguments_in_synpred236_Java7222);
        nonWildcardTypeArguments();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_classOrInterfaceType_in_synpred236_Java7224);
        classOrInterfaceType();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_classCreatorRest_in_synpred236_Java7226);
        classCreatorRest();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred236_Java

    // $ANTLR start synpred237_Java
    public final void synpred237_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1210:9: ( 'new' classOrInterfaceType classCreatorRest )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1210:9: 'new' classOrInterfaceType classCreatorRest
        {
        match(input,NEW,FOLLOW_NEW_in_synpred237_Java7236); if (state.failed) return ;
        pushFollow(FOLLOW_classOrInterfaceType_in_synpred237_Java7238);
        classOrInterfaceType();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_classCreatorRest_in_synpred237_Java7240);
        classCreatorRest();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred237_Java

    // $ANTLR start synpred239_Java
    public final void synpred239_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1215:9: ( 'new' createdName '[' ']' ( '[' ']' )* arrayInitializer )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1215:9: 'new' createdName '[' ']' ( '[' ']' )* arrayInitializer
        {
        match(input,NEW,FOLLOW_NEW_in_synpred239_Java7270); if (state.failed) return ;
        pushFollow(FOLLOW_createdName_in_synpred239_Java7272);
        createdName();

        state._fsp--;
        if (state.failed) return ;
        match(input,LBRACKET,FOLLOW_LBRACKET_in_synpred239_Java7282); if (state.failed) return ;
        match(input,RBRACKET,FOLLOW_RBRACKET_in_synpred239_Java7284); if (state.failed) return ;
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1217:9: ( '[' ']' )*
        loop193:
        do {
            int alt193=2;
            int LA193_0 = input.LA(1);

            if ( (LA193_0==LBRACKET) ) {
                alt193=1;
            }


            switch (alt193) {
        	case 1 :
        	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1217:10: '[' ']'
        	    {
        	    match(input,LBRACKET,FOLLOW_LBRACKET_in_synpred239_Java7295); if (state.failed) return ;
        	    match(input,RBRACKET,FOLLOW_RBRACKET_in_synpred239_Java7297); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop193;
            }
        } while (true);

        pushFollow(FOLLOW_arrayInitializer_in_synpred239_Java7318);
        arrayInitializer();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred239_Java

    // $ANTLR start synpred240_Java
    public final void synpred240_Java_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1224:13: ( '[' expression ']' )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/grammars/Java.g:1224:13: '[' expression ']'
        {
        match(input,LBRACKET,FOLLOW_LBRACKET_in_synpred240_Java7367); if (state.failed) return ;
        pushFollow(FOLLOW_expression_in_synpred240_Java7369);
        expression();

        state._fsp--;
        if (state.failed) return ;
        match(input,RBRACKET,FOLLOW_RBRACKET_in_synpred240_Java7383); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred240_Java

    // Delegated rules

    public final boolean synpred43_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred43_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred98_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred98_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred157_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred157_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred224_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred224_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred211_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred211_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred121_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred121_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred239_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred239_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred69_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred69_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred202_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred202_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred154_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred154_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred71_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred71_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred133_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred133_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred125_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred125_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred132_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred132_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred119_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred119_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred54_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred54_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred148_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred148_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred117_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred117_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred130_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred130_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred126_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred126_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred59_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred59_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred212_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred212_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred161_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred161_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred57_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred57_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred209_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred209_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred68_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred68_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred53_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred53_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred52_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred52_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred236_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred236_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred12_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred12_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred149_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred149_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred120_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred120_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred122_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred122_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred240_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred240_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred206_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred206_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred70_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred70_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred27_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred27_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred96_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred96_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred153_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred153_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred99_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred99_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred103_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred103_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred237_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred237_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred118_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred118_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred208_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred208_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA2 dfa2 = new DFA2(this);
    protected DFA12 dfa12 = new DFA12(this);
    protected DFA13 dfa13 = new DFA13(this);
    protected DFA15 dfa15 = new DFA15(this);
    protected DFA31 dfa31 = new DFA31(this);
    protected DFA39 dfa39 = new DFA39(this);
    protected DFA49 dfa49 = new DFA49(this);
    protected DFA42 dfa42 = new DFA42(this);
    protected DFA53 dfa53 = new DFA53(this);
    protected DFA76 dfa76 = new DFA76(this);
    protected DFA87 dfa87 = new DFA87(this);
    protected DFA90 dfa90 = new DFA90(this);
    protected DFA98 dfa98 = new DFA98(this);
    protected DFA109 dfa109 = new DFA109(this);
    protected DFA112 dfa112 = new DFA112(this);
    protected DFA130 dfa130 = new DFA130(this);
    protected DFA133 dfa133 = new DFA133(this);
    protected DFA135 dfa135 = new DFA135(this);
    protected DFA143 dfa143 = new DFA143(this);
    protected DFA142 dfa142 = new DFA142(this);
    protected DFA148 dfa148 = new DFA148(this);
    protected DFA171 dfa171 = new DFA171(this);
    static final String DFA2_eotS =
        "\24\uffff";
    static final String DFA2_eofS =
        "\1\3\23\uffff";
    static final String DFA2_minS =
        "\1\32\1\0\22\uffff";
    static final String DFA2_maxS =
        "\1\160\1\0\22\uffff";
    static final String DFA2_acceptS =
        "\2\uffff\1\1\1\2\20\uffff";
    static final String DFA2_specialS =
        "\1\uffff\1\0\22\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\3\7\uffff\1\3\6\uffff\1\3\1\uffff\1\3\6\uffff\1\3\2\uffff"+
            "\1\3\1\uffff\1\3\1\uffff\1\2\3\3\2\uffff\2\3\2\uffff\1\3\3\uffff"+
            "\1\3\2\uffff\1\3\7\uffff\1\3\35\uffff\1\1",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "323:9: ( ( annotations )? packageDeclaration )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA2_1 = input.LA(1);

                         
                        int index2_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Java()) ) {s = 2;}

                        else if ( (true) ) {s = 3;}

                         
                        input.seek(index2_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 2, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA12_eotS =
        "\20\uffff";
    static final String DFA12_eofS =
        "\20\uffff";
    static final String DFA12_minS =
        "\1\32\14\0\3\uffff";
    static final String DFA12_maxS =
        "\1\160\14\0\3\uffff";
    static final String DFA12_acceptS =
        "\15\uffff\1\1\1\uffff\1\2";
    static final String DFA12_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\3\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\6\7\uffff\1\15\6\uffff\1\15\1\uffff\1\7\11\uffff\1\17\1\uffff"+
            "\1\10\2\uffff\1\4\1\3\1\2\2\uffff\1\5\1\14\2\uffff\1\11\3\uffff"+
            "\1\12\2\uffff\1\13\45\uffff\1\1",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            ""
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "371:1: classOrInterfaceDeclaration : ( classDeclaration | interfaceDeclaration );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA12_1 = input.LA(1);

                         
                        int index12_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index12_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA12_2 = input.LA(1);

                         
                        int index12_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index12_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA12_3 = input.LA(1);

                         
                        int index12_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index12_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA12_4 = input.LA(1);

                         
                        int index12_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index12_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA12_5 = input.LA(1);

                         
                        int index12_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index12_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA12_6 = input.LA(1);

                         
                        int index12_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index12_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA12_7 = input.LA(1);

                         
                        int index12_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index12_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA12_8 = input.LA(1);

                         
                        int index12_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index12_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA12_9 = input.LA(1);

                         
                        int index12_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index12_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA12_10 = input.LA(1);

                         
                        int index12_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index12_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA12_11 = input.LA(1);

                         
                        int index12_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index12_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA12_12 = input.LA(1);

                         
                        int index12_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index12_12);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 12, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA13_eotS =
        "\17\uffff";
    static final String DFA13_eofS =
        "\17\uffff";
    static final String DFA13_minS =
        "\1\4\1\uffff\1\4\14\uffff";
    static final String DFA13_maxS =
        "\1\163\1\uffff\1\65\14\uffff";
    static final String DFA13_acceptS =
        "\1\uffff\1\15\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13"+
        "\1\14\1\1";
    static final String DFA13_specialS =
        "\17\uffff}>";
    static final String[] DFA13_transitionS = {
            "\1\1\25\uffff\1\7\1\uffff\1\1\1\uffff\1\1\2\uffff\2\1\4\uffff"+
            "\1\1\1\uffff\1\1\1\uffff\1\10\1\uffff\1\1\6\uffff\3\1\1\11\2"+
            "\uffff\1\5\1\4\1\3\1\uffff\1\1\1\6\1\15\2\uffff\1\12\3\uffff"+
            "\1\13\1\uffff\1\1\1\14\45\uffff\1\2\2\uffff\1\1",
            "",
            "\1\16\60\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "()* loopback of 379:5: ( annotation | 'public' | 'protected' | 'private' | 'static' | 'abstract' | 'final' | 'native' | 'synchronized' | 'transient' | 'volatile' | 'strictfp' )*";
        }
    }
    static final String DFA15_eotS =
        "\17\uffff";
    static final String DFA15_eofS =
        "\17\uffff";
    static final String DFA15_minS =
        "\1\32\14\0\2\uffff";
    static final String DFA15_maxS =
        "\1\160\14\0\2\uffff";
    static final String DFA15_acceptS =
        "\15\uffff\1\1\1\2";
    static final String DFA15_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\2\uffff}>";
    static final String[] DFA15_transitionS = {
            "\1\6\7\uffff\1\15\6\uffff\1\16\1\uffff\1\7\13\uffff\1\10\2\uffff"+
            "\1\4\1\3\1\2\2\uffff\1\5\1\14\2\uffff\1\11\3\uffff\1\12\2\uffff"+
            "\1\13\45\uffff\1\1",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA15_eot = DFA.unpackEncodedString(DFA15_eotS);
    static final short[] DFA15_eof = DFA.unpackEncodedString(DFA15_eofS);
    static final char[] DFA15_min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS);
    static final char[] DFA15_max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS);
    static final short[] DFA15_accept = DFA.unpackEncodedString(DFA15_acceptS);
    static final short[] DFA15_special = DFA.unpackEncodedString(DFA15_specialS);
    static final short[][] DFA15_transition;

    static {
        int numStates = DFA15_transitionS.length;
        DFA15_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA15_transition[i] = DFA.unpackEncodedString(DFA15_transitionS[i]);
        }
    }

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = DFA15_eot;
            this.eof = DFA15_eof;
            this.min = DFA15_min;
            this.max = DFA15_max;
            this.accept = DFA15_accept;
            this.special = DFA15_special;
            this.transition = DFA15_transition;
        }
        public String getDescription() {
            return "402:1: classDeclaration : ( normalClassDeclaration | enumDeclaration );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA15_1 = input.LA(1);

                         
                        int index15_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred27_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index15_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA15_2 = input.LA(1);

                         
                        int index15_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred27_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index15_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA15_3 = input.LA(1);

                         
                        int index15_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred27_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index15_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA15_4 = input.LA(1);

                         
                        int index15_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred27_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index15_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA15_5 = input.LA(1);

                         
                        int index15_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred27_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index15_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA15_6 = input.LA(1);

                         
                        int index15_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred27_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index15_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA15_7 = input.LA(1);

                         
                        int index15_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred27_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index15_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA15_8 = input.LA(1);

                         
                        int index15_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred27_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index15_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA15_9 = input.LA(1);

                         
                        int index15_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred27_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index15_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA15_10 = input.LA(1);

                         
                        int index15_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred27_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index15_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA15_11 = input.LA(1);

                         
                        int index15_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred27_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index15_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA15_12 = input.LA(1);

                         
                        int index15_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred27_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index15_12);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 15, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA31_eotS =
        "\17\uffff";
    static final String DFA31_eofS =
        "\17\uffff";
    static final String DFA31_minS =
        "\1\32\14\0\2\uffff";
    static final String DFA31_maxS =
        "\1\160\14\0\2\uffff";
    static final String DFA31_acceptS =
        "\15\uffff\1\1\1\2";
    static final String DFA31_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\2\uffff}>";
    static final String[] DFA31_transitionS = {
            "\1\6\20\uffff\1\7\11\uffff\1\15\1\uffff\1\10\2\uffff\1\4\1\3"+
            "\1\2\2\uffff\1\5\1\14\2\uffff\1\11\3\uffff\1\12\2\uffff\1\13"+
            "\45\uffff\1\1",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA31_eot = DFA.unpackEncodedString(DFA31_eotS);
    static final short[] DFA31_eof = DFA.unpackEncodedString(DFA31_eofS);
    static final char[] DFA31_min = DFA.unpackEncodedStringToUnsignedChars(DFA31_minS);
    static final char[] DFA31_max = DFA.unpackEncodedStringToUnsignedChars(DFA31_maxS);
    static final short[] DFA31_accept = DFA.unpackEncodedString(DFA31_acceptS);
    static final short[] DFA31_special = DFA.unpackEncodedString(DFA31_specialS);
    static final short[][] DFA31_transition;

    static {
        int numStates = DFA31_transitionS.length;
        DFA31_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA31_transition[i] = DFA.unpackEncodedString(DFA31_transitionS[i]);
        }
    }

    class DFA31 extends DFA {

        public DFA31(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 31;
            this.eot = DFA31_eot;
            this.eof = DFA31_eof;
            this.min = DFA31_min;
            this.max = DFA31_max;
            this.accept = DFA31_accept;
            this.special = DFA31_special;
            this.transition = DFA31_transition;
        }
        public String getDescription() {
            return "502:1: interfaceDeclaration : ( normalInterfaceDeclaration | annotationTypeDeclaration );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA31_1 = input.LA(1);

                         
                        int index31_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred43_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index31_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA31_2 = input.LA(1);

                         
                        int index31_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred43_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index31_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA31_3 = input.LA(1);

                         
                        int index31_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred43_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index31_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA31_4 = input.LA(1);

                         
                        int index31_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred43_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index31_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA31_5 = input.LA(1);

                         
                        int index31_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred43_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index31_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA31_6 = input.LA(1);

                         
                        int index31_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred43_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index31_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA31_7 = input.LA(1);

                         
                        int index31_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred43_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index31_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA31_8 = input.LA(1);

                         
                        int index31_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred43_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index31_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA31_9 = input.LA(1);

                         
                        int index31_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred43_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index31_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA31_10 = input.LA(1);

                         
                        int index31_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred43_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index31_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA31_11 = input.LA(1);

                         
                        int index31_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred43_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index31_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA31_12 = input.LA(1);

                         
                        int index31_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred43_Java()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index31_12);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 31, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA39_eotS =
        "\25\uffff";
    static final String DFA39_eofS =
        "\25\uffff";
    static final String DFA39_minS =
        "\1\4\16\0\6\uffff";
    static final String DFA39_maxS =
        "\1\163\16\0\6\uffff";
    static final String DFA39_acceptS =
        "\17\uffff\1\2\1\uffff\1\3\1\uffff\1\4\1\1";
    static final String DFA39_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\1\15\6\uffff}>";
    static final String[] DFA39_transitionS = {
            "\1\15\25\uffff\1\6\1\uffff\1\16\1\uffff\1\16\2\uffff\1\16\1"+
            "\21\4\uffff\1\16\1\uffff\1\21\1\uffff\1\7\1\uffff\1\16\6\uffff"+
            "\1\16\1\23\1\16\1\10\2\uffff\1\4\1\3\1\2\1\uffff\1\16\1\5\1"+
            "\14\2\uffff\1\11\3\uffff\1\12\1\uffff\1\17\1\13\45\uffff\1\1"+
            "\2\uffff\1\17",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA39_eot = DFA.unpackEncodedString(DFA39_eotS);
    static final short[] DFA39_eof = DFA.unpackEncodedString(DFA39_eofS);
    static final char[] DFA39_min = DFA.unpackEncodedStringToUnsignedChars(DFA39_minS);
    static final char[] DFA39_max = DFA.unpackEncodedStringToUnsignedChars(DFA39_maxS);
    static final short[] DFA39_accept = DFA.unpackEncodedString(DFA39_acceptS);
    static final short[] DFA39_special = DFA.unpackEncodedString(DFA39_specialS);
    static final short[][] DFA39_transition;

    static {
        int numStates = DFA39_transitionS.length;
        DFA39_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA39_transition[i] = DFA.unpackEncodedString(DFA39_transitionS[i]);
        }
    }

    class DFA39 extends DFA {

        public DFA39(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 39;
            this.eot = DFA39_eot;
            this.eof = DFA39_eof;
            this.min = DFA39_min;
            this.max = DFA39_max;
            this.accept = DFA39_accept;
            this.special = DFA39_special;
            this.transition = DFA39_transition;
        }
        public String getDescription() {
            return "544:1: memberDecl : ( fieldDeclaration | methodDeclaration | classDeclaration | interfaceDeclaration );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA39_1 = input.LA(1);

                         
                        int index39_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred52_Java()) ) {s = 20;}

                        else if ( (synpred53_Java()) ) {s = 15;}

                        else if ( (synpred54_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index39_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA39_2 = input.LA(1);

                         
                        int index39_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred52_Java()) ) {s = 20;}

                        else if ( (synpred53_Java()) ) {s = 15;}

                        else if ( (synpred54_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index39_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA39_3 = input.LA(1);

                         
                        int index39_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred52_Java()) ) {s = 20;}

                        else if ( (synpred53_Java()) ) {s = 15;}

                        else if ( (synpred54_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index39_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA39_4 = input.LA(1);

                         
                        int index39_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred52_Java()) ) {s = 20;}

                        else if ( (synpred53_Java()) ) {s = 15;}

                        else if ( (synpred54_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index39_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA39_5 = input.LA(1);

                         
                        int index39_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred52_Java()) ) {s = 20;}

                        else if ( (synpred53_Java()) ) {s = 15;}

                        else if ( (synpred54_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index39_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA39_6 = input.LA(1);

                         
                        int index39_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred52_Java()) ) {s = 20;}

                        else if ( (synpred53_Java()) ) {s = 15;}

                        else if ( (synpred54_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index39_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA39_7 = input.LA(1);

                         
                        int index39_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred52_Java()) ) {s = 20;}

                        else if ( (synpred53_Java()) ) {s = 15;}

                        else if ( (synpred54_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index39_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA39_8 = input.LA(1);

                         
                        int index39_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred52_Java()) ) {s = 20;}

                        else if ( (synpred53_Java()) ) {s = 15;}

                        else if ( (synpred54_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index39_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA39_9 = input.LA(1);

                         
                        int index39_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred52_Java()) ) {s = 20;}

                        else if ( (synpred53_Java()) ) {s = 15;}

                        else if ( (synpred54_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index39_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA39_10 = input.LA(1);

                         
                        int index39_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred52_Java()) ) {s = 20;}

                        else if ( (synpred53_Java()) ) {s = 15;}

                        else if ( (synpred54_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index39_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA39_11 = input.LA(1);

                         
                        int index39_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred52_Java()) ) {s = 20;}

                        else if ( (synpred53_Java()) ) {s = 15;}

                        else if ( (synpred54_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index39_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA39_12 = input.LA(1);

                         
                        int index39_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred52_Java()) ) {s = 20;}

                        else if ( (synpred53_Java()) ) {s = 15;}

                        else if ( (synpred54_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index39_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA39_13 = input.LA(1);

                         
                        int index39_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred52_Java()) ) {s = 20;}

                        else if ( (synpred53_Java()) ) {s = 15;}

                         
                        input.seek(index39_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA39_14 = input.LA(1);

                         
                        int index39_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred52_Java()) ) {s = 20;}

                        else if ( (synpred53_Java()) ) {s = 15;}

                         
                        input.seek(index39_14);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 39, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA49_eotS =
        "\22\uffff";
    static final String DFA49_eofS =
        "\22\uffff";
    static final String DFA49_minS =
        "\1\4\16\0\3\uffff";
    static final String DFA49_maxS =
        "\1\163\16\0\3\uffff";
    static final String DFA49_acceptS =
        "\17\uffff\1\2\1\uffff\1\1";
    static final String DFA49_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\1\15\3\uffff}>";
    static final String[] DFA49_transitionS = {
            "\1\16\25\uffff\1\6\1\uffff\1\17\1\uffff\1\17\2\uffff\1\17\5"+
            "\uffff\1\17\3\uffff\1\7\1\uffff\1\17\6\uffff\1\17\1\uffff\1"+
            "\17\1\10\2\uffff\1\4\1\3\1\2\1\uffff\1\17\1\5\1\14\2\uffff\1"+
            "\11\3\uffff\1\12\1\uffff\1\17\1\13\45\uffff\1\1\2\uffff\1\15",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            ""
    };

    static final short[] DFA49_eot = DFA.unpackEncodedString(DFA49_eotS);
    static final short[] DFA49_eof = DFA.unpackEncodedString(DFA49_eofS);
    static final char[] DFA49_min = DFA.unpackEncodedStringToUnsignedChars(DFA49_minS);
    static final char[] DFA49_max = DFA.unpackEncodedStringToUnsignedChars(DFA49_maxS);
    static final short[] DFA49_accept = DFA.unpackEncodedString(DFA49_acceptS);
    static final short[] DFA49_special = DFA.unpackEncodedString(DFA49_specialS);
    static final short[][] DFA49_transition;

    static {
        int numStates = DFA49_transitionS.length;
        DFA49_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA49_transition[i] = DFA.unpackEncodedString(DFA49_transitionS[i]);
        }
    }

    class DFA49 extends DFA {

        public DFA49(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 49;
            this.eot = DFA49_eot;
            this.eof = DFA49_eof;
            this.min = DFA49_min;
            this.max = DFA49_max;
            this.accept = DFA49_accept;
            this.special = DFA49_special;
            this.transition = DFA49_transition;
        }
        public String getDescription() {
            return "552:1: methodDeclaration : ( modifiers ( typeParameters )? IDENTIFIER formalParameters ( 'throws' qualifiedNameList )? '{' ( explicitConstructorInvocation )? ( blockStatement )* '}' | modifiers ( typeParameters )? ( type | 'void' ) IDENTIFIER formalParameters ( '[' ']' )* ( 'throws' qualifiedNameList )? ( block | ';' ) );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA49_1 = input.LA(1);

                         
                        int index49_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred59_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index49_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA49_2 = input.LA(1);

                         
                        int index49_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred59_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index49_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA49_3 = input.LA(1);

                         
                        int index49_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred59_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index49_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA49_4 = input.LA(1);

                         
                        int index49_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred59_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index49_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA49_5 = input.LA(1);

                         
                        int index49_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred59_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index49_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA49_6 = input.LA(1);

                         
                        int index49_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred59_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index49_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA49_7 = input.LA(1);

                         
                        int index49_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred59_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index49_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA49_8 = input.LA(1);

                         
                        int index49_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred59_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index49_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA49_9 = input.LA(1);

                         
                        int index49_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred59_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index49_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA49_10 = input.LA(1);

                         
                        int index49_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred59_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index49_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA49_11 = input.LA(1);

                         
                        int index49_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred59_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index49_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA49_12 = input.LA(1);

                         
                        int index49_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred59_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index49_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA49_13 = input.LA(1);

                         
                        int index49_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred59_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index49_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA49_14 = input.LA(1);

                         
                        int index49_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred59_Java()) ) {s = 17;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index49_14);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 49, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA42_eotS =
        "\55\uffff";
    static final String DFA42_eofS =
        "\55\uffff";
    static final String DFA42_minS =
        "\1\4\1\uffff\10\0\43\uffff";
    static final String DFA42_maxS =
        "\1\163\1\uffff\10\0\43\uffff";
    static final String DFA42_acceptS =
        "\1\uffff\1\1\10\uffff\1\2\42\uffff";
    static final String DFA42_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\43\uffff}>";
    static final String[] DFA42_transitionS = {
            "\1\5\11\6\14\uffff\2\12\1\10\1\12\1\10\2\uffff\1\10\1\12\1\uffff"+
            "\1\12\1\uffff\1\12\1\10\1\uffff\1\12\1\uffff\1\12\1\uffff\1"+
            "\10\1\12\1\uffff\1\12\3\uffff\1\10\1\12\1\10\1\12\1\7\1\uffff"+
            "\4\12\1\10\2\12\1\4\2\12\1\2\1\12\1\uffff\2\12\1\11\2\12\1\3"+
            "\1\uffff\2\12\2\uffff\1\12\4\uffff\2\12\5\uffff\4\12\16\uffff"+
            "\1\12\2\uffff\1\1",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA42_eot = DFA.unpackEncodedString(DFA42_eotS);
    static final short[] DFA42_eof = DFA.unpackEncodedString(DFA42_eofS);
    static final char[] DFA42_min = DFA.unpackEncodedStringToUnsignedChars(DFA42_minS);
    static final char[] DFA42_max = DFA.unpackEncodedStringToUnsignedChars(DFA42_maxS);
    static final short[] DFA42_accept = DFA.unpackEncodedString(DFA42_acceptS);
    static final short[] DFA42_special = DFA.unpackEncodedString(DFA42_specialS);
    static final short[][] DFA42_transition;

    static {
        int numStates = DFA42_transitionS.length;
        DFA42_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA42_transition[i] = DFA.unpackEncodedString(DFA42_transitionS[i]);
        }
    }

    class DFA42 extends DFA {

        public DFA42(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 42;
            this.eot = DFA42_eot;
            this.eof = DFA42_eof;
            this.min = DFA42_min;
            this.max = DFA42_max;
            this.accept = DFA42_accept;
            this.special = DFA42_special;
            this.transition = DFA42_transition;
        }
        public String getDescription() {
            return "563:9: ( explicitConstructorInvocation )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA42_2 = input.LA(1);

                         
                        int index42_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred57_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index42_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA42_3 = input.LA(1);

                         
                        int index42_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred57_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index42_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA42_4 = input.LA(1);

                         
                        int index42_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred57_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index42_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA42_5 = input.LA(1);

                         
                        int index42_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred57_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index42_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA42_6 = input.LA(1);

                         
                        int index42_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred57_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index42_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA42_7 = input.LA(1);

                         
                        int index42_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred57_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index42_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA42_8 = input.LA(1);

                         
                        int index42_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred57_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index42_8);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA42_9 = input.LA(1);

                         
                        int index42_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred57_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index42_9);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 42, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA53_eotS =
        "\26\uffff";
    static final String DFA53_eofS =
        "\26\uffff";
    static final String DFA53_minS =
        "\1\4\16\0\7\uffff";
    static final String DFA53_maxS =
        "\1\163\16\0\7\uffff";
    static final String DFA53_acceptS =
        "\17\uffff\1\2\1\uffff\1\3\1\4\1\uffff\1\5\1\1";
    static final String DFA53_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\1\15\7\uffff}>";
    static final String[] DFA53_transitionS = {
            "\1\15\25\uffff\1\6\1\uffff\1\16\1\uffff\1\16\2\uffff\1\16\1"+
            "\22\4\uffff\1\16\1\uffff\1\22\1\uffff\1\7\1\uffff\1\16\6\uffff"+
            "\1\16\1\21\1\16\1\10\2\uffff\1\4\1\3\1\2\1\uffff\1\16\1\5\1"+
            "\14\2\uffff\1\11\3\uffff\1\12\1\uffff\1\17\1\13\7\uffff\1\24"+
            "\35\uffff\1\1\2\uffff\1\17",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA53_eot = DFA.unpackEncodedString(DFA53_eotS);
    static final short[] DFA53_eof = DFA.unpackEncodedString(DFA53_eofS);
    static final char[] DFA53_min = DFA.unpackEncodedStringToUnsignedChars(DFA53_minS);
    static final char[] DFA53_max = DFA.unpackEncodedStringToUnsignedChars(DFA53_maxS);
    static final short[] DFA53_accept = DFA.unpackEncodedString(DFA53_acceptS);
    static final short[] DFA53_special = DFA.unpackEncodedString(DFA53_specialS);
    static final short[][] DFA53_transition;

    static {
        int numStates = DFA53_transitionS.length;
        DFA53_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA53_transition[i] = DFA.unpackEncodedString(DFA53_transitionS[i]);
        }
    }

    class DFA53 extends DFA {

        public DFA53(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 53;
            this.eot = DFA53_eot;
            this.eof = DFA53_eof;
            this.min = DFA53_min;
            this.max = DFA53_max;
            this.accept = DFA53_accept;
            this.special = DFA53_special;
            this.transition = DFA53_transition;
        }
        public String getDescription() {
            return "610:1: interfaceBodyDeclaration : ( interfaceFieldDeclaration | interfaceMethodDeclaration | interfaceDeclaration | classDeclaration | ';' );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA53_1 = input.LA(1);

                         
                        int index53_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred68_Java()) ) {s = 21;}

                        else if ( (synpred69_Java()) ) {s = 15;}

                        else if ( (synpred70_Java()) ) {s = 17;}

                        else if ( (synpred71_Java()) ) {s = 18;}

                         
                        input.seek(index53_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA53_2 = input.LA(1);

                         
                        int index53_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred68_Java()) ) {s = 21;}

                        else if ( (synpred69_Java()) ) {s = 15;}

                        else if ( (synpred70_Java()) ) {s = 17;}

                        else if ( (synpred71_Java()) ) {s = 18;}

                         
                        input.seek(index53_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA53_3 = input.LA(1);

                         
                        int index53_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred68_Java()) ) {s = 21;}

                        else if ( (synpred69_Java()) ) {s = 15;}

                        else if ( (synpred70_Java()) ) {s = 17;}

                        else if ( (synpred71_Java()) ) {s = 18;}

                         
                        input.seek(index53_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA53_4 = input.LA(1);

                         
                        int index53_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred68_Java()) ) {s = 21;}

                        else if ( (synpred69_Java()) ) {s = 15;}

                        else if ( (synpred70_Java()) ) {s = 17;}

                        else if ( (synpred71_Java()) ) {s = 18;}

                         
                        input.seek(index53_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA53_5 = input.LA(1);

                         
                        int index53_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred68_Java()) ) {s = 21;}

                        else if ( (synpred69_Java()) ) {s = 15;}

                        else if ( (synpred70_Java()) ) {s = 17;}

                        else if ( (synpred71_Java()) ) {s = 18;}

                         
                        input.seek(index53_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA53_6 = input.LA(1);

                         
                        int index53_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred68_Java()) ) {s = 21;}

                        else if ( (synpred69_Java()) ) {s = 15;}

                        else if ( (synpred70_Java()) ) {s = 17;}

                        else if ( (synpred71_Java()) ) {s = 18;}

                         
                        input.seek(index53_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA53_7 = input.LA(1);

                         
                        int index53_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred68_Java()) ) {s = 21;}

                        else if ( (synpred69_Java()) ) {s = 15;}

                        else if ( (synpred70_Java()) ) {s = 17;}

                        else if ( (synpred71_Java()) ) {s = 18;}

                         
                        input.seek(index53_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA53_8 = input.LA(1);

                         
                        int index53_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred68_Java()) ) {s = 21;}

                        else if ( (synpred69_Java()) ) {s = 15;}

                        else if ( (synpred70_Java()) ) {s = 17;}

                        else if ( (synpred71_Java()) ) {s = 18;}

                         
                        input.seek(index53_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA53_9 = input.LA(1);

                         
                        int index53_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred68_Java()) ) {s = 21;}

                        else if ( (synpred69_Java()) ) {s = 15;}

                        else if ( (synpred70_Java()) ) {s = 17;}

                        else if ( (synpred71_Java()) ) {s = 18;}

                         
                        input.seek(index53_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA53_10 = input.LA(1);

                         
                        int index53_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred68_Java()) ) {s = 21;}

                        else if ( (synpred69_Java()) ) {s = 15;}

                        else if ( (synpred70_Java()) ) {s = 17;}

                        else if ( (synpred71_Java()) ) {s = 18;}

                         
                        input.seek(index53_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA53_11 = input.LA(1);

                         
                        int index53_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred68_Java()) ) {s = 21;}

                        else if ( (synpred69_Java()) ) {s = 15;}

                        else if ( (synpred70_Java()) ) {s = 17;}

                        else if ( (synpred71_Java()) ) {s = 18;}

                         
                        input.seek(index53_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA53_12 = input.LA(1);

                         
                        int index53_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred68_Java()) ) {s = 21;}

                        else if ( (synpred69_Java()) ) {s = 15;}

                        else if ( (synpred70_Java()) ) {s = 17;}

                        else if ( (synpred71_Java()) ) {s = 18;}

                         
                        input.seek(index53_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA53_13 = input.LA(1);

                         
                        int index53_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred68_Java()) ) {s = 21;}

                        else if ( (synpred69_Java()) ) {s = 15;}

                         
                        input.seek(index53_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA53_14 = input.LA(1);

                         
                        int index53_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred68_Java()) ) {s = 21;}

                        else if ( (synpred69_Java()) ) {s = 15;}

                         
                        input.seek(index53_14);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 53, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA76_eotS =
        "\12\uffff";
    static final String DFA76_eofS =
        "\12\uffff";
    static final String DFA76_minS =
        "\1\4\1\uffff\1\0\1\uffff\1\0\5\uffff";
    static final String DFA76_maxS =
        "\1\163\1\uffff\1\0\1\uffff\1\0\5\uffff";
    static final String DFA76_acceptS =
        "\1\uffff\1\1\1\uffff\1\2\6\uffff";
    static final String DFA76_specialS =
        "\2\uffff\1\0\1\uffff\1\1\5\uffff}>";
    static final String[] DFA76_transitionS = {
            "\12\3\16\uffff\1\3\1\uffff\1\3\2\uffff\1\3\5\uffff\1\3\5\uffff"+
            "\1\3\6\uffff\1\3\1\uffff\1\3\1\uffff\1\3\5\uffff\1\3\2\uffff"+
            "\1\4\2\uffff\1\2\4\uffff\1\3\2\uffff\1\3\46\uffff\1\1",
            "",
            "\1\uffff",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA76_eot = DFA.unpackEncodedString(DFA76_eotS);
    static final short[] DFA76_eof = DFA.unpackEncodedString(DFA76_eofS);
    static final char[] DFA76_min = DFA.unpackEncodedStringToUnsignedChars(DFA76_minS);
    static final char[] DFA76_max = DFA.unpackEncodedStringToUnsignedChars(DFA76_maxS);
    static final short[] DFA76_accept = DFA.unpackEncodedString(DFA76_acceptS);
    static final short[] DFA76_special = DFA.unpackEncodedString(DFA76_specialS);
    static final short[][] DFA76_transition;

    static {
        int numStates = DFA76_transitionS.length;
        DFA76_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA76_transition[i] = DFA.unpackEncodedString(DFA76_transitionS[i]);
        }
    }

    class DFA76 extends DFA {

        public DFA76(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 76;
            this.eot = DFA76_eot;
            this.eof = DFA76_eof;
            this.min = DFA76_min;
            this.max = DFA76_max;
            this.accept = DFA76_accept;
            this.special = DFA76_special;
            this.transition = DFA76_transition;
        }
        public String getDescription() {
            return "736:1: explicitConstructorInvocation : ( ( nonWildcardTypeArguments )? ( 'this' | 'super' ) arguments ';' | primary '.' ( nonWildcardTypeArguments )? 'super' arguments ';' );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA76_2 = input.LA(1);

                         
                        int index76_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 3;}

                         
                        input.seek(index76_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA76_4 = input.LA(1);

                         
                        int index76_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 3;}

                         
                        input.seek(index76_4);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 76, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA87_eotS =
        "\26\uffff";
    static final String DFA87_eofS =
        "\26\uffff";
    static final String DFA87_minS =
        "\1\4\16\0\7\uffff";
    static final String DFA87_maxS =
        "\1\160\16\0\7\uffff";
    static final String DFA87_acceptS =
        "\17\uffff\1\3\1\4\1\5\1\7\1\1\1\2\1\6";
    static final String DFA87_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\1\15\7\uffff}>";
    static final String[] DFA87_transitionS = {
            "\1\15\25\uffff\1\6\1\uffff\1\16\1\uffff\1\16\2\uffff\1\16\1"+
            "\17\4\uffff\1\16\1\uffff\1\21\1\uffff\1\7\1\uffff\1\16\6\uffff"+
            "\1\16\1\20\1\16\1\10\2\uffff\1\4\1\3\1\2\1\uffff\1\16\1\5\1"+
            "\14\2\uffff\1\11\3\uffff\1\12\2\uffff\1\13\7\uffff\1\22\35\uffff"+
            "\1\1",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA87_eot = DFA.unpackEncodedString(DFA87_eotS);
    static final short[] DFA87_eof = DFA.unpackEncodedString(DFA87_eofS);
    static final char[] DFA87_min = DFA.unpackEncodedStringToUnsignedChars(DFA87_minS);
    static final char[] DFA87_max = DFA.unpackEncodedStringToUnsignedChars(DFA87_maxS);
    static final short[] DFA87_accept = DFA.unpackEncodedString(DFA87_acceptS);
    static final short[] DFA87_special = DFA.unpackEncodedString(DFA87_specialS);
    static final short[][] DFA87_transition;

    static {
        int numStates = DFA87_transitionS.length;
        DFA87_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA87_transition[i] = DFA.unpackEncodedString(DFA87_transitionS[i]);
        }
    }

    class DFA87 extends DFA {

        public DFA87(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 87;
            this.eot = DFA87_eot;
            this.eof = DFA87_eof;
            this.min = DFA87_min;
            this.max = DFA87_max;
            this.accept = DFA87_accept;
            this.special = DFA87_special;
            this.transition = DFA87_transition;
        }
        public String getDescription() {
            return "820:1: annotationTypeElementDeclaration : ( annotationMethodDeclaration | interfaceFieldDeclaration | normalClassDeclaration | normalInterfaceDeclaration | enumDeclaration | annotationTypeDeclaration | ';' );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA87_1 = input.LA(1);

                         
                        int index87_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred117_Java()) ) {s = 19;}

                        else if ( (synpred118_Java()) ) {s = 20;}

                        else if ( (synpred119_Java()) ) {s = 15;}

                        else if ( (synpred120_Java()) ) {s = 16;}

                        else if ( (synpred121_Java()) ) {s = 17;}

                        else if ( (synpred122_Java()) ) {s = 21;}

                         
                        input.seek(index87_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA87_2 = input.LA(1);

                         
                        int index87_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred117_Java()) ) {s = 19;}

                        else if ( (synpred118_Java()) ) {s = 20;}

                        else if ( (synpred119_Java()) ) {s = 15;}

                        else if ( (synpred120_Java()) ) {s = 16;}

                        else if ( (synpred121_Java()) ) {s = 17;}

                        else if ( (synpred122_Java()) ) {s = 21;}

                         
                        input.seek(index87_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA87_3 = input.LA(1);

                         
                        int index87_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred117_Java()) ) {s = 19;}

                        else if ( (synpred118_Java()) ) {s = 20;}

                        else if ( (synpred119_Java()) ) {s = 15;}

                        else if ( (synpred120_Java()) ) {s = 16;}

                        else if ( (synpred121_Java()) ) {s = 17;}

                        else if ( (synpred122_Java()) ) {s = 21;}

                         
                        input.seek(index87_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA87_4 = input.LA(1);

                         
                        int index87_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred117_Java()) ) {s = 19;}

                        else if ( (synpred118_Java()) ) {s = 20;}

                        else if ( (synpred119_Java()) ) {s = 15;}

                        else if ( (synpred120_Java()) ) {s = 16;}

                        else if ( (synpred121_Java()) ) {s = 17;}

                        else if ( (synpred122_Java()) ) {s = 21;}

                         
                        input.seek(index87_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA87_5 = input.LA(1);

                         
                        int index87_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred117_Java()) ) {s = 19;}

                        else if ( (synpred118_Java()) ) {s = 20;}

                        else if ( (synpred119_Java()) ) {s = 15;}

                        else if ( (synpred120_Java()) ) {s = 16;}

                        else if ( (synpred121_Java()) ) {s = 17;}

                        else if ( (synpred122_Java()) ) {s = 21;}

                         
                        input.seek(index87_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA87_6 = input.LA(1);

                         
                        int index87_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred117_Java()) ) {s = 19;}

                        else if ( (synpred118_Java()) ) {s = 20;}

                        else if ( (synpred119_Java()) ) {s = 15;}

                        else if ( (synpred120_Java()) ) {s = 16;}

                        else if ( (synpred121_Java()) ) {s = 17;}

                        else if ( (synpred122_Java()) ) {s = 21;}

                         
                        input.seek(index87_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA87_7 = input.LA(1);

                         
                        int index87_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred117_Java()) ) {s = 19;}

                        else if ( (synpred118_Java()) ) {s = 20;}

                        else if ( (synpred119_Java()) ) {s = 15;}

                        else if ( (synpred120_Java()) ) {s = 16;}

                        else if ( (synpred121_Java()) ) {s = 17;}

                        else if ( (synpred122_Java()) ) {s = 21;}

                         
                        input.seek(index87_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA87_8 = input.LA(1);

                         
                        int index87_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred117_Java()) ) {s = 19;}

                        else if ( (synpred118_Java()) ) {s = 20;}

                        else if ( (synpred119_Java()) ) {s = 15;}

                        else if ( (synpred120_Java()) ) {s = 16;}

                        else if ( (synpred121_Java()) ) {s = 17;}

                        else if ( (synpred122_Java()) ) {s = 21;}

                         
                        input.seek(index87_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA87_9 = input.LA(1);

                         
                        int index87_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred117_Java()) ) {s = 19;}

                        else if ( (synpred118_Java()) ) {s = 20;}

                        else if ( (synpred119_Java()) ) {s = 15;}

                        else if ( (synpred120_Java()) ) {s = 16;}

                        else if ( (synpred121_Java()) ) {s = 17;}

                        else if ( (synpred122_Java()) ) {s = 21;}

                         
                        input.seek(index87_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA87_10 = input.LA(1);

                         
                        int index87_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred117_Java()) ) {s = 19;}

                        else if ( (synpred118_Java()) ) {s = 20;}

                        else if ( (synpred119_Java()) ) {s = 15;}

                        else if ( (synpred120_Java()) ) {s = 16;}

                        else if ( (synpred121_Java()) ) {s = 17;}

                        else if ( (synpred122_Java()) ) {s = 21;}

                         
                        input.seek(index87_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA87_11 = input.LA(1);

                         
                        int index87_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred117_Java()) ) {s = 19;}

                        else if ( (synpred118_Java()) ) {s = 20;}

                        else if ( (synpred119_Java()) ) {s = 15;}

                        else if ( (synpred120_Java()) ) {s = 16;}

                        else if ( (synpred121_Java()) ) {s = 17;}

                        else if ( (synpred122_Java()) ) {s = 21;}

                         
                        input.seek(index87_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA87_12 = input.LA(1);

                         
                        int index87_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred117_Java()) ) {s = 19;}

                        else if ( (synpred118_Java()) ) {s = 20;}

                        else if ( (synpred119_Java()) ) {s = 15;}

                        else if ( (synpred120_Java()) ) {s = 16;}

                        else if ( (synpred121_Java()) ) {s = 17;}

                        else if ( (synpred122_Java()) ) {s = 21;}

                         
                        input.seek(index87_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA87_13 = input.LA(1);

                         
                        int index87_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred117_Java()) ) {s = 19;}

                        else if ( (synpred118_Java()) ) {s = 20;}

                         
                        input.seek(index87_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA87_14 = input.LA(1);

                         
                        int index87_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred117_Java()) ) {s = 19;}

                        else if ( (synpred118_Java()) ) {s = 20;}

                         
                        input.seek(index87_14);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 87, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA90_eotS =
        "\54\uffff";
    static final String DFA90_eofS =
        "\54\uffff";
    static final String DFA90_minS =
        "\1\4\4\0\6\uffff\1\0\40\uffff";
    static final String DFA90_maxS =
        "\1\160\4\0\6\uffff\1\0\40\uffff";
    static final String DFA90_acceptS =
        "\5\uffff\1\2\14\uffff\1\3\30\uffff\1\1";
    static final String DFA90_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\6\uffff\1\4\40\uffff}>";
    static final String[] DFA90_transitionS = {
            "\1\3\11\22\14\uffff\1\5\1\22\1\4\1\22\1\4\2\uffff\1\4\1\5\1"+
            "\uffff\1\22\1\uffff\1\22\1\4\1\uffff\1\5\1\uffff\1\1\1\uffff"+
            "\1\4\1\22\1\uffff\1\22\3\uffff\1\4\1\5\1\4\1\5\1\22\1\uffff"+
            "\3\5\1\22\1\4\2\5\2\22\1\13\2\22\1\uffff\1\5\2\22\1\5\2\22\1"+
            "\uffff\1\22\3\uffff\1\22\4\uffff\2\22\5\uffff\4\22\16\uffff"+
            "\1\2",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA90_eot = DFA.unpackEncodedString(DFA90_eotS);
    static final short[] DFA90_eof = DFA.unpackEncodedString(DFA90_eofS);
    static final char[] DFA90_min = DFA.unpackEncodedStringToUnsignedChars(DFA90_minS);
    static final char[] DFA90_max = DFA.unpackEncodedStringToUnsignedChars(DFA90_maxS);
    static final short[] DFA90_accept = DFA.unpackEncodedString(DFA90_acceptS);
    static final short[] DFA90_special = DFA.unpackEncodedString(DFA90_specialS);
    static final short[][] DFA90_transition;

    static {
        int numStates = DFA90_transitionS.length;
        DFA90_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA90_transition[i] = DFA.unpackEncodedString(DFA90_transitionS[i]);
        }
    }

    class DFA90 extends DFA {

        public DFA90(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 90;
            this.eot = DFA90_eot;
            this.eof = DFA90_eof;
            this.min = DFA90_min;
            this.max = DFA90_max;
            this.accept = DFA90_accept;
            this.special = DFA90_special;
            this.transition = DFA90_transition;
        }
        public String getDescription() {
            return "871:1: blockStatement : ( localVariableDeclarationStatement | classOrInterfaceDeclaration | statement );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA90_1 = input.LA(1);

                         
                        int index90_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred125_Java()) ) {s = 43;}

                        else if ( (synpred126_Java()) ) {s = 5;}

                         
                        input.seek(index90_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA90_2 = input.LA(1);

                         
                        int index90_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred125_Java()) ) {s = 43;}

                        else if ( (synpred126_Java()) ) {s = 5;}

                         
                        input.seek(index90_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA90_3 = input.LA(1);

                         
                        int index90_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred125_Java()) ) {s = 43;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index90_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA90_4 = input.LA(1);

                         
                        int index90_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred125_Java()) ) {s = 43;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index90_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA90_11 = input.LA(1);

                         
                        int index90_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred126_Java()) ) {s = 5;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index90_11);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 90, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA98_eotS =
        "\40\uffff";
    static final String DFA98_eofS =
        "\40\uffff";
    static final String DFA98_minS =
        "\1\4\1\uffff\1\0\23\uffff\1\0\11\uffff";
    static final String DFA98_maxS =
        "\1\141\1\uffff\1\0\23\uffff\1\0\11\uffff";
    static final String DFA98_acceptS =
        "\1\uffff\1\1\1\uffff\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\15\uffff\1\21\1\2\1\3\1\20";
    static final String DFA98_specialS =
        "\2\uffff\1\0\23\uffff\1\1\11\uffff}>";
    static final String[] DFA98_transitionS = {
            "\1\26\11\16\15\uffff\1\2\1\16\1\14\1\16\2\uffff\1\16\2\uffff"+
            "\1\15\1\uffff\1\6\1\16\5\uffff\1\16\1\4\1\uffff\1\3\3\uffff"+
            "\1\16\1\uffff\1\16\1\uffff\1\16\4\uffff\1\12\1\16\2\uffff\1"+
            "\16\1\10\1\11\1\16\1\13\2\uffff\1\7\1\16\1\uffff\1\5\1\16\1"+
            "\uffff\1\1\3\uffff\1\34\4\uffff\2\16\5\uffff\4\16",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA98_eot = DFA.unpackEncodedString(DFA98_eotS);
    static final short[] DFA98_eof = DFA.unpackEncodedString(DFA98_eofS);
    static final char[] DFA98_min = DFA.unpackEncodedStringToUnsignedChars(DFA98_minS);
    static final char[] DFA98_max = DFA.unpackEncodedStringToUnsignedChars(DFA98_maxS);
    static final short[] DFA98_accept = DFA.unpackEncodedString(DFA98_acceptS);
    static final short[] DFA98_special = DFA.unpackEncodedString(DFA98_specialS);
    static final short[][] DFA98_transition;

    static {
        int numStates = DFA98_transitionS.length;
        DFA98_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA98_transition[i] = DFA.unpackEncodedString(DFA98_transitionS[i]);
        }
    }

    class DFA98 extends DFA {

        public DFA98(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 98;
            this.eot = DFA98_eot;
            this.eof = DFA98_eof;
            this.min = DFA98_min;
            this.max = DFA98_max;
            this.accept = DFA98_accept;
            this.special = DFA98_special;
            this.transition = DFA98_transition;
        }
        public String getDescription() {
            return "890:1: statement : ( block | ( 'assert' ) expression ( ':' expression )? ';' | 'assert' expression ( ':' expression )? ';' | 'if' parExpression statement ( 'else' statement )? | forstatement | 'while' parExpression statement | 'do' statement 'while' parExpression ';' | trystatement | 'switch' parExpression '{' switchBlockStatementGroups '}' | 'synchronized' parExpression block | 'return' ( expression )? ';' | 'throw' expression ';' | 'break' ( IDENTIFIER )? ';' | 'continue' ( IDENTIFIER )? ';' | expression ';' | IDENTIFIER ':' statement | ';' );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA98_2 = input.LA(1);

                         
                        int index98_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred130_Java()) ) {s = 29;}

                        else if ( (synpred132_Java()) ) {s = 30;}

                         
                        input.seek(index98_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA98_22 = input.LA(1);

                         
                        int index98_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred148_Java()) ) {s = 14;}

                        else if ( (synpred149_Java()) ) {s = 31;}

                         
                        input.seek(index98_22);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 98, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA109_eotS =
        "\21\uffff";
    static final String DFA109_eofS =
        "\21\uffff";
    static final String DFA109_minS =
        "\1\4\2\uffff\2\0\14\uffff";
    static final String DFA109_maxS =
        "\1\160\2\uffff\2\0\14\uffff";
    static final String DFA109_acceptS =
        "\1\uffff\1\1\3\uffff\1\2\13\uffff";
    static final String DFA109_specialS =
        "\3\uffff\1\0\1\1\14\uffff}>";
    static final String[] DFA109_transitionS = {
            "\1\3\11\5\16\uffff\1\4\1\uffff\1\4\2\uffff\1\4\5\uffff\1\4\3"+
            "\uffff\1\1\1\uffff\1\4\6\uffff\1\4\1\uffff\1\4\1\uffff\1\5\5"+
            "\uffff\1\4\2\uffff\1\5\2\uffff\1\5\4\uffff\1\5\2\uffff\1\5\12"+
            "\uffff\2\5\5\uffff\4\5\16\uffff\1\1",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA109_eot = DFA.unpackEncodedString(DFA109_eotS);
    static final short[] DFA109_eof = DFA.unpackEncodedString(DFA109_eofS);
    static final char[] DFA109_min = DFA.unpackEncodedStringToUnsignedChars(DFA109_minS);
    static final char[] DFA109_max = DFA.unpackEncodedStringToUnsignedChars(DFA109_maxS);
    static final short[] DFA109_accept = DFA.unpackEncodedString(DFA109_acceptS);
    static final short[] DFA109_special = DFA.unpackEncodedString(DFA109_specialS);
    static final short[][] DFA109_transition;

    static {
        int numStates = DFA109_transitionS.length;
        DFA109_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA109_transition[i] = DFA.unpackEncodedString(DFA109_transitionS[i]);
        }
    }

    class DFA109 extends DFA {

        public DFA109(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 109;
            this.eot = DFA109_eot;
            this.eof = DFA109_eof;
            this.min = DFA109_min;
            this.max = DFA109_max;
            this.accept = DFA109_accept;
            this.special = DFA109_special;
            this.transition = DFA109_transition;
        }
        public String getDescription() {
            return "976:1: forInit : ( localVariableDeclaration | expressionList );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA109_3 = input.LA(1);

                         
                        int index109_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred161_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index109_3);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA109_4 = input.LA(1);

                         
                        int index109_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred161_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index109_4);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 109, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA112_eotS =
        "\17\uffff";
    static final String DFA112_eofS =
        "\17\uffff";
    static final String DFA112_minS =
        "\1\126\12\uffff\1\162\1\126\2\uffff";
    static final String DFA112_maxS =
        "\1\163\12\uffff\2\162\2\uffff";
    static final String DFA112_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\2\uffff\1\13"+
        "\1\14";
    static final String DFA112_specialS =
        "\17\uffff}>";
    static final String[] DFA112_transitionS = {
            "\1\1\21\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\2\uffff\1\13"+
            "\1\12",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\14",
            "\1\16\33\uffff\1\15",
            "",
            ""
    };

    static final short[] DFA112_eot = DFA.unpackEncodedString(DFA112_eotS);
    static final short[] DFA112_eof = DFA.unpackEncodedString(DFA112_eofS);
    static final char[] DFA112_min = DFA.unpackEncodedStringToUnsignedChars(DFA112_minS);
    static final char[] DFA112_max = DFA.unpackEncodedStringToUnsignedChars(DFA112_maxS);
    static final short[] DFA112_accept = DFA.unpackEncodedString(DFA112_acceptS);
    static final short[] DFA112_special = DFA.unpackEncodedString(DFA112_specialS);
    static final short[][] DFA112_transition;

    static {
        int numStates = DFA112_transitionS.length;
        DFA112_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA112_transition[i] = DFA.unpackEncodedString(DFA112_transitionS[i]);
        }
    }

    class DFA112 extends DFA {

        public DFA112(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 112;
            this.eot = DFA112_eot;
            this.eof = DFA112_eof;
            this.min = DFA112_min;
            this.max = DFA112_max;
            this.accept = DFA112_accept;
            this.special = DFA112_special;
            this.transition = DFA112_transition;
        }
        public String getDescription() {
            return "999:1: assignmentOperator : ( '=' | '+=' | '-=' | '*=' | '/=' | '&=' | '|=' | '^=' | '%=' | '<' '<' '=' | '>' '>' '>' '=' | '>' '>' '=' );";
        }
    }
    static final String DFA130_eotS =
        "\14\uffff";
    static final String DFA130_eofS =
        "\14\uffff";
    static final String DFA130_minS =
        "\1\4\2\uffff\1\0\10\uffff";
    static final String DFA130_maxS =
        "\1\130\2\uffff\1\0\10\uffff";
    static final String DFA130_acceptS =
        "\1\uffff\1\1\1\2\1\uffff\1\4\6\uffff\1\3";
    static final String DFA130_specialS =
        "\3\uffff\1\0\10\uffff}>";
    static final String[] DFA130_transitionS = {
            "\12\4\16\uffff\1\4\1\uffff\1\4\2\uffff\1\4\5\uffff\1\4\5\uffff"+
            "\1\4\6\uffff\1\4\1\uffff\1\4\1\uffff\1\4\5\uffff\1\4\2\uffff"+
            "\1\4\2\uffff\1\4\4\uffff\1\4\2\uffff\1\3\12\uffff\1\2\1\1",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA130_eot = DFA.unpackEncodedString(DFA130_eotS);
    static final short[] DFA130_eof = DFA.unpackEncodedString(DFA130_eofS);
    static final char[] DFA130_min = DFA.unpackEncodedStringToUnsignedChars(DFA130_minS);
    static final char[] DFA130_max = DFA.unpackEncodedStringToUnsignedChars(DFA130_maxS);
    static final short[] DFA130_accept = DFA.unpackEncodedString(DFA130_acceptS);
    static final short[] DFA130_special = DFA.unpackEncodedString(DFA130_specialS);
    static final short[][] DFA130_transition;

    static {
        int numStates = DFA130_transitionS.length;
        DFA130_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA130_transition[i] = DFA.unpackEncodedString(DFA130_transitionS[i]);
        }
    }

    class DFA130 extends DFA {

        public DFA130(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 130;
            this.eot = DFA130_eot;
            this.eof = DFA130_eof;
            this.min = DFA130_min;
            this.max = DFA130_max;
            this.accept = DFA130_accept;
            this.special = DFA130_special;
            this.transition = DFA130_transition;
        }
        public String getDescription() {
            return "1128:1: unaryExpressionNotPlusMinus : ( '~' unaryExpression | '!' unaryExpression | castExpression | primary ( selector )* ( '++' | '--' )? );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA130_3 = input.LA(1);

                         
                        int index130_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred202_Java()) ) {s = 11;}

                        else if ( (true) ) {s = 4;}

                         
                        input.seek(index130_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 130, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA133_eotS =
        "\41\uffff";
    static final String DFA133_eofS =
        "\1\4\40\uffff";
    static final String DFA133_minS =
        "\1\63\1\0\1\uffff\1\0\35\uffff";
    static final String DFA133_maxS =
        "\1\163\1\0\1\uffff\1\0\35\uffff";
    static final String DFA133_acceptS =
        "\2\uffff\1\1\1\uffff\1\2\34\uffff";
    static final String DFA133_specialS =
        "\1\uffff\1\0\1\uffff\1\1\35\uffff}>";
    static final String[] DFA133_transitionS = {
            "\1\4\30\uffff\1\2\1\4\1\uffff\1\4\1\1\3\4\1\3\1\uffff\1\4\2"+
            "\uffff\27\4\1\uffff\3\4",
            "\1\uffff",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA133_eot = DFA.unpackEncodedString(DFA133_eotS);
    static final short[] DFA133_eof = DFA.unpackEncodedString(DFA133_eofS);
    static final char[] DFA133_min = DFA.unpackEncodedStringToUnsignedChars(DFA133_minS);
    static final char[] DFA133_max = DFA.unpackEncodedStringToUnsignedChars(DFA133_maxS);
    static final short[] DFA133_accept = DFA.unpackEncodedString(DFA133_acceptS);
    static final short[] DFA133_special = DFA.unpackEncodedString(DFA133_specialS);
    static final short[][] DFA133_transition;

    static {
        int numStates = DFA133_transitionS.length;
        DFA133_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA133_transition[i] = DFA.unpackEncodedString(DFA133_transitionS[i]);
        }
    }

    class DFA133 extends DFA {

        public DFA133(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 133;
            this.eot = DFA133_eot;
            this.eof = DFA133_eof;
            this.min = DFA133_min;
            this.max = DFA133_max;
            this.accept = DFA133_accept;
            this.special = DFA133_special;
            this.transition = DFA133_transition;
        }
        public String getDescription() {
            return "1153:9: ( identifierSuffix )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA133_1 = input.LA(1);

                         
                        int index133_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred209_Java()) ) {s = 2;}

                        else if ( (true) ) {s = 4;}

                         
                        input.seek(index133_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA133_3 = input.LA(1);

                         
                        int index133_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred209_Java()) ) {s = 2;}

                        else if ( (true) ) {s = 4;}

                         
                        input.seek(index133_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 133, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA135_eotS =
        "\41\uffff";
    static final String DFA135_eofS =
        "\1\4\40\uffff";
    static final String DFA135_minS =
        "\1\63\1\0\1\uffff\1\0\35\uffff";
    static final String DFA135_maxS =
        "\1\163\1\0\1\uffff\1\0\35\uffff";
    static final String DFA135_acceptS =
        "\2\uffff\1\1\1\uffff\1\2\34\uffff";
    static final String DFA135_specialS =
        "\1\uffff\1\0\1\uffff\1\1\35\uffff}>";
    static final String[] DFA135_transitionS = {
            "\1\4\30\uffff\1\2\1\4\1\uffff\1\4\1\1\3\4\1\3\1\uffff\1\4\2"+
            "\uffff\27\4\1\uffff\3\4",
            "\1\uffff",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA135_eot = DFA.unpackEncodedString(DFA135_eotS);
    static final short[] DFA135_eof = DFA.unpackEncodedString(DFA135_eofS);
    static final char[] DFA135_min = DFA.unpackEncodedStringToUnsignedChars(DFA135_minS);
    static final char[] DFA135_max = DFA.unpackEncodedStringToUnsignedChars(DFA135_maxS);
    static final short[] DFA135_accept = DFA.unpackEncodedString(DFA135_acceptS);
    static final short[] DFA135_special = DFA.unpackEncodedString(DFA135_specialS);
    static final short[][] DFA135_transition;

    static {
        int numStates = DFA135_transitionS.length;
        DFA135_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA135_transition[i] = DFA.unpackEncodedString(DFA135_transitionS[i]);
        }
    }

    class DFA135 extends DFA {

        public DFA135(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 135;
            this.eot = DFA135_eot;
            this.eof = DFA135_eof;
            this.min = DFA135_min;
            this.max = DFA135_max;
            this.accept = DFA135_accept;
            this.special = DFA135_special;
            this.transition = DFA135_transition;
        }
        public String getDescription() {
            return "1158:9: ( identifierSuffix )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA135_1 = input.LA(1);

                         
                        int index135_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred212_Java()) ) {s = 2;}

                        else if ( (true) ) {s = 4;}

                         
                        input.seek(index135_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA135_3 = input.LA(1);

                         
                        int index135_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred212_Java()) ) {s = 2;}

                        else if ( (true) ) {s = 4;}

                         
                        input.seek(index135_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 135, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA143_eotS =
        "\13\uffff";
    static final String DFA143_eofS =
        "\13\uffff";
    static final String DFA143_minS =
        "\1\114\1\4\1\uffff\1\42\7\uffff";
    static final String DFA143_maxS =
        "\1\124\1\141\1\uffff\1\163\7\uffff";
    static final String DFA143_acceptS =
        "\2\uffff\1\3\1\uffff\1\1\1\2\1\4\1\6\1\7\1\10\1\5";
    static final String DFA143_specialS =
        "\13\uffff}>";
    static final String[] DFA143_transitionS = {
            "\1\2\3\uffff\1\1\3\uffff\1\3",
            "\12\5\16\uffff\1\5\1\uffff\1\5\2\uffff\1\5\5\uffff\1\5\5\uffff"+
            "\1\5\6\uffff\1\5\1\uffff\1\5\1\uffff\1\5\5\uffff\1\5\2\uffff"+
            "\1\5\2\uffff\1\5\4\uffff\1\5\2\uffff\1\5\4\uffff\1\4\5\uffff"+
            "\2\5\5\uffff\4\5",
            "",
            "\1\6\25\uffff\1\11\10\uffff\1\10\2\uffff\1\7\56\uffff\1\12",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA143_eot = DFA.unpackEncodedString(DFA143_eotS);
    static final short[] DFA143_eof = DFA.unpackEncodedString(DFA143_eofS);
    static final char[] DFA143_min = DFA.unpackEncodedStringToUnsignedChars(DFA143_minS);
    static final char[] DFA143_max = DFA.unpackEncodedStringToUnsignedChars(DFA143_maxS);
    static final short[] DFA143_accept = DFA.unpackEncodedString(DFA143_acceptS);
    static final short[] DFA143_special = DFA.unpackEncodedString(DFA143_specialS);
    static final short[][] DFA143_transition;

    static {
        int numStates = DFA143_transitionS.length;
        DFA143_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA143_transition[i] = DFA.unpackEncodedString(DFA143_transitionS[i]);
        }
    }

    class DFA143 extends DFA {

        public DFA143(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 143;
            this.eot = DFA143_eot;
            this.eof = DFA143_eof;
            this.min = DFA143_min;
            this.max = DFA143_max;
            this.accept = DFA143_accept;
            this.special = DFA143_special;
            this.transition = DFA143_transition;
        }
        public String getDescription() {
            return "1182:1: identifierSuffix : ( ( '[' ']' )+ '.' 'class' | ( '[' expression ']' )+ | arguments | '.' 'class' | '.' nonWildcardTypeArguments IDENTIFIER arguments | '.' 'this' | '.' 'super' arguments | innerCreator );";
        }
    }
    static final String DFA142_eotS =
        "\41\uffff";
    static final String DFA142_eofS =
        "\1\1\40\uffff";
    static final String DFA142_minS =
        "\1\63\1\uffff\1\0\36\uffff";
    static final String DFA142_maxS =
        "\1\163\1\uffff\1\0\36\uffff";
    static final String DFA142_acceptS =
        "\1\uffff\1\2\36\uffff\1\1";
    static final String DFA142_specialS =
        "\2\uffff\1\0\36\uffff}>";
    static final String[] DFA142_transitionS = {
            "\1\1\31\uffff\1\1\1\uffff\1\1\1\2\4\1\1\uffff\1\1\2\uffff\27"+
            "\1\1\uffff\3\1",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA142_eot = DFA.unpackEncodedString(DFA142_eotS);
    static final short[] DFA142_eof = DFA.unpackEncodedString(DFA142_eofS);
    static final char[] DFA142_min = DFA.unpackEncodedStringToUnsignedChars(DFA142_minS);
    static final char[] DFA142_max = DFA.unpackEncodedStringToUnsignedChars(DFA142_maxS);
    static final short[] DFA142_accept = DFA.unpackEncodedString(DFA142_acceptS);
    static final short[] DFA142_special = DFA.unpackEncodedString(DFA142_specialS);
    static final short[][] DFA142_transition;

    static {
        int numStates = DFA142_transitionS.length;
        DFA142_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA142_transition[i] = DFA.unpackEncodedString(DFA142_transitionS[i]);
        }
    }

    class DFA142 extends DFA {

        public DFA142(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 142;
            this.eot = DFA142_eot;
            this.eof = DFA142_eof;
            this.min = DFA142_min;
            this.max = DFA142_max;
            this.accept = DFA142_accept;
            this.special = DFA142_special;
            this.transition = DFA142_transition;
        }
        public String getDescription() {
            return "()+ loopback of 1186:9: ( '[' expression ']' )+";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA142_2 = input.LA(1);

                         
                        int index142_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred224_Java()) ) {s = 32;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index142_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 142, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA148_eotS =
        "\41\uffff";
    static final String DFA148_eofS =
        "\1\2\40\uffff";
    static final String DFA148_minS =
        "\1\63\1\0\37\uffff";
    static final String DFA148_maxS =
        "\1\163\1\0\37\uffff";
    static final String DFA148_acceptS =
        "\2\uffff\1\2\35\uffff\1\1";
    static final String DFA148_specialS =
        "\1\uffff\1\0\37\uffff}>";
    static final String[] DFA148_transitionS = {
            "\1\2\31\uffff\1\2\1\uffff\1\2\1\1\4\2\1\uffff\1\2\2\uffff\27"+
            "\2\1\uffff\3\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA148_eot = DFA.unpackEncodedString(DFA148_eotS);
    static final short[] DFA148_eof = DFA.unpackEncodedString(DFA148_eofS);
    static final char[] DFA148_min = DFA.unpackEncodedStringToUnsignedChars(DFA148_minS);
    static final char[] DFA148_max = DFA.unpackEncodedStringToUnsignedChars(DFA148_maxS);
    static final short[] DFA148_accept = DFA.unpackEncodedString(DFA148_acceptS);
    static final short[] DFA148_special = DFA.unpackEncodedString(DFA148_specialS);
    static final short[][] DFA148_transition;

    static {
        int numStates = DFA148_transitionS.length;
        DFA148_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA148_transition[i] = DFA.unpackEncodedString(DFA148_transitionS[i]);
        }
    }

    class DFA148 extends DFA {

        public DFA148(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 148;
            this.eot = DFA148_eot;
            this.eof = DFA148_eof;
            this.min = DFA148_min;
            this.max = DFA148_max;
            this.accept = DFA148_accept;
            this.special = DFA148_special;
            this.transition = DFA148_transition;
        }
        public String getDescription() {
            return "()* loopback of 1224:9: ( '[' expression ']' )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA148_1 = input.LA(1);

                         
                        int index148_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred240_Java()) ) {s = 32;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index148_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 148, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA171_eotS =
        "\55\uffff";
    static final String DFA171_eofS =
        "\55\uffff";
    static final String DFA171_minS =
        "\1\4\1\uffff\10\0\43\uffff";
    static final String DFA171_maxS =
        "\1\163\1\uffff\10\0\43\uffff";
    static final String DFA171_acceptS =
        "\1\uffff\1\1\10\uffff\1\2\42\uffff";
    static final String DFA171_specialS =
        "\2\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\43\uffff}>";
    static final String[] DFA171_transitionS = {
            "\1\5\11\6\14\uffff\2\12\1\10\1\12\1\10\2\uffff\1\10\1\12\1\uffff"+
            "\1\12\1\uffff\1\12\1\10\1\uffff\1\12\1\uffff\1\12\1\uffff\1"+
            "\10\1\12\1\uffff\1\12\3\uffff\1\10\1\12\1\10\1\12\1\7\1\uffff"+
            "\4\12\1\10\2\12\1\4\2\12\1\2\1\12\1\uffff\2\12\1\11\2\12\1\3"+
            "\1\uffff\2\12\2\uffff\1\12\4\uffff\2\12\5\uffff\4\12\16\uffff"+
            "\1\12\2\uffff\1\1",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA171_eot = DFA.unpackEncodedString(DFA171_eotS);
    static final short[] DFA171_eof = DFA.unpackEncodedString(DFA171_eofS);
    static final char[] DFA171_min = DFA.unpackEncodedStringToUnsignedChars(DFA171_minS);
    static final char[] DFA171_max = DFA.unpackEncodedStringToUnsignedChars(DFA171_maxS);
    static final short[] DFA171_accept = DFA.unpackEncodedString(DFA171_acceptS);
    static final short[] DFA171_special = DFA.unpackEncodedString(DFA171_specialS);
    static final short[][] DFA171_transition;

    static {
        int numStates = DFA171_transitionS.length;
        DFA171_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA171_transition[i] = DFA.unpackEncodedString(DFA171_transitionS[i]);
        }
    }

    class DFA171 extends DFA {

        public DFA171(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 171;
            this.eot = DFA171_eot;
            this.eof = DFA171_eof;
            this.min = DFA171_min;
            this.max = DFA171_max;
            this.accept = DFA171_accept;
            this.special = DFA171_special;
            this.transition = DFA171_transition;
        }
        public String getDescription() {
            return "563:9: ( explicitConstructorInvocation )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA171_2 = input.LA(1);

                         
                        int index171_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred57_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index171_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA171_3 = input.LA(1);

                         
                        int index171_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred57_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index171_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA171_4 = input.LA(1);

                         
                        int index171_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred57_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index171_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA171_5 = input.LA(1);

                         
                        int index171_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred57_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index171_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA171_6 = input.LA(1);

                         
                        int index171_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred57_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index171_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA171_7 = input.LA(1);

                         
                        int index171_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred57_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index171_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA171_8 = input.LA(1);

                         
                        int index171_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred57_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index171_8);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA171_9 = input.LA(1);

                         
                        int index171_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred57_Java()) ) {s = 1;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index171_9);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 171, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_annotations_in_compilationUnit115 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_packageDeclaration_in_compilationUnit144 = new BitSet(new long[]{0x9CA40A0404000002L,0x0001000000040489L});
    public static final BitSet FOLLOW_importDeclaration_in_compilationUnit166 = new BitSet(new long[]{0x9CA40A0404000002L,0x0001000000040489L});
    public static final BitSet FOLLOW_typeDeclaration_in_compilationUnit188 = new BitSet(new long[]{0x9CA00A0404000002L,0x0001000000040489L});
    public static final BitSet FOLLOW_PACKAGE_in_packageDeclaration219 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_qualifiedName_in_packageDeclaration221 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_packageDeclaration231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_importDeclaration252 = new BitSet(new long[]{0x8000000000000010L});
    public static final BitSet FOLLOW_STATIC_in_importDeclaration264 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_importDeclaration285 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_DOT_in_importDeclaration287 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_STAR_in_importDeclaration289 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_importDeclaration299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_importDeclaration316 = new BitSet(new long[]{0x8000000000000010L});
    public static final BitSet FOLLOW_STATIC_in_importDeclaration328 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_importDeclaration349 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_DOT_in_importDeclaration360 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_importDeclaration362 = new BitSet(new long[]{0x0000000000000000L,0x0000000000140000L});
    public static final BitSet FOLLOW_DOT_in_importDeclaration384 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_STAR_in_importDeclaration386 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_importDeclaration407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_qualifiedImportName437 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_DOT_in_qualifiedImportName448 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_qualifiedImportName450 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_typeDeclaration481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMI_in_typeDeclaration491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classDeclaration_in_classOrInterfaceDeclaration512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interfaceDeclaration_in_classOrInterfaceDeclaration522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_modifiers557 = new BitSet(new long[]{0x9C80080004000002L,0x0001000000000489L});
    public static final BitSet FOLLOW_PUBLIC_in_modifiers567 = new BitSet(new long[]{0x9C80080004000002L,0x0001000000000489L});
    public static final BitSet FOLLOW_PROTECTED_in_modifiers577 = new BitSet(new long[]{0x9C80080004000002L,0x0001000000000489L});
    public static final BitSet FOLLOW_PRIVATE_in_modifiers587 = new BitSet(new long[]{0x9C80080004000002L,0x0001000000000489L});
    public static final BitSet FOLLOW_STATIC_in_modifiers597 = new BitSet(new long[]{0x9C80080004000002L,0x0001000000000489L});
    public static final BitSet FOLLOW_ABSTRACT_in_modifiers607 = new BitSet(new long[]{0x9C80080004000002L,0x0001000000000489L});
    public static final BitSet FOLLOW_FINAL_in_modifiers617 = new BitSet(new long[]{0x9C80080004000002L,0x0001000000000489L});
    public static final BitSet FOLLOW_NATIVE_in_modifiers627 = new BitSet(new long[]{0x9C80080004000002L,0x0001000000000489L});
    public static final BitSet FOLLOW_SYNCHRONIZED_in_modifiers637 = new BitSet(new long[]{0x9C80080004000002L,0x0001000000000489L});
    public static final BitSet FOLLOW_TRANSIENT_in_modifiers647 = new BitSet(new long[]{0x9C80080004000002L,0x0001000000000489L});
    public static final BitSet FOLLOW_VOLATILE_in_modifiers657 = new BitSet(new long[]{0x9C80080004000002L,0x0001000000000489L});
    public static final BitSet FOLLOW_STRICTFP_in_modifiers667 = new BitSet(new long[]{0x9C80080004000002L,0x0001000000000489L});
    public static final BitSet FOLLOW_FINAL_in_variableModifiers699 = new BitSet(new long[]{0x0000080000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_annotation_in_variableModifiers713 = new BitSet(new long[]{0x0000080000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_normalClassDeclaration_in_classDeclaration749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumDeclaration_in_classDeclaration759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_normalClassDeclaration779 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_CLASS_in_normalClassDeclaration782 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_normalClassDeclaration784 = new BitSet(new long[]{0x0002040000000000L,0x0008000000004000L});
    public static final BitSet FOLLOW_typeParameters_in_normalClassDeclaration805 = new BitSet(new long[]{0x0002040000000000L,0x0008000000004000L});
    public static final BitSet FOLLOW_EXTENDS_in_normalClassDeclaration827 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_type_in_normalClassDeclaration829 = new BitSet(new long[]{0x0002040000000000L,0x0008000000004000L});
    public static final BitSet FOLLOW_IMPLEMENTS_in_normalClassDeclaration851 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_typeList_in_normalClassDeclaration853 = new BitSet(new long[]{0x0002040000000000L,0x0008000000004000L});
    public static final BitSet FOLLOW_classBody_in_normalClassDeclaration886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_typeParameters917 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_typeParameter_in_typeParameters931 = new BitSet(new long[]{0x0000000000000000L,0x0004000000080000L});
    public static final BitSet FOLLOW_COMMA_in_typeParameters946 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_typeParameter_in_typeParameters948 = new BitSet(new long[]{0x0000000000000000L,0x0004000000080000L});
    public static final BitSet FOLLOW_GT_in_typeParameters973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_typeParameter993 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_EXTENDS_in_typeParameter1004 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_typeBound_in_typeParameter1006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_typeBound1038 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});
    public static final BitSet FOLLOW_AMP_in_typeBound1049 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_type_in_typeBound1051 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});
    public static final BitSet FOLLOW_modifiers_in_enumDeclaration1083 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ENUM_in_enumDeclaration1095 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_enumDeclaration1116 = new BitSet(new long[]{0x0002000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_IMPLEMENTS_in_enumDeclaration1127 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_typeList_in_enumDeclaration1129 = new BitSet(new long[]{0x0002000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_enumBody_in_enumDeclaration1150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_enumBody1175 = new BitSet(new long[]{0x0000000000000010L,0x00010000000C8000L});
    public static final BitSet FOLLOW_enumConstants_in_enumBody1186 = new BitSet(new long[]{0x0000000000000000L,0x00000000000C8000L});
    public static final BitSet FOLLOW_COMMA_in_enumBody1208 = new BitSet(new long[]{0x0000000000000000L,0x0000000000048000L});
    public static final BitSet FOLLOW_enumBodyDeclarations_in_enumBody1221 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RBRACE_in_enumBody1243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumConstant_in_enumConstants1263 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_COMMA_in_enumConstants1274 = new BitSet(new long[]{0x0000000000000010L,0x0001000000000000L});
    public static final BitSet FOLLOW_enumConstant_in_enumConstants1276 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_annotations_in_enumConstant1310 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_enumConstant1331 = new BitSet(new long[]{0x0002040000000002L,0x0008000000005000L});
    public static final BitSet FOLLOW_arguments_in_enumConstant1342 = new BitSet(new long[]{0x0002040000000002L,0x0008000000004000L});
    public static final BitSet FOLLOW_classBody_in_enumConstant1364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMI_in_enumBodyDeclarations1405 = new BitSet(new long[]{0xDCF02A8654000012L,0x0009000000044689L});
    public static final BitSet FOLLOW_classBodyDeclaration_in_enumBodyDeclarations1417 = new BitSet(new long[]{0xDCF02A8654000012L,0x0009000000044689L});
    public static final BitSet FOLLOW_normalInterfaceDeclaration_in_interfaceDeclaration1448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationTypeDeclaration_in_interfaceDeclaration1458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_normalInterfaceDeclaration1482 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_INTERFACE_in_normalInterfaceDeclaration1484 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_normalInterfaceDeclaration1486 = new BitSet(new long[]{0x0000040000000000L,0x0008000000004000L});
    public static final BitSet FOLLOW_typeParameters_in_normalInterfaceDeclaration1497 = new BitSet(new long[]{0x0000040000000000L,0x0008000000004000L});
    public static final BitSet FOLLOW_EXTENDS_in_normalInterfaceDeclaration1519 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_typeList_in_normalInterfaceDeclaration1521 = new BitSet(new long[]{0x0000040000000000L,0x0008000000004000L});
    public static final BitSet FOLLOW_interfaceBody_in_normalInterfaceDeclaration1542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_typeList1562 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_COMMA_in_typeList1573 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_type_in_typeList1575 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_LBRACE_in_classBody1606 = new BitSet(new long[]{0xDCF02A8654000010L,0x000900000004C689L});
    public static final BitSet FOLLOW_classBodyDeclaration_in_classBody1618 = new BitSet(new long[]{0xDCF02A8654000010L,0x000900000004C689L});
    public static final BitSet FOLLOW_RBRACE_in_classBody1640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_interfaceBody1660 = new BitSet(new long[]{0xDCF02A8654000010L,0x0009000000048689L});
    public static final BitSet FOLLOW_interfaceBodyDeclaration_in_interfaceBody1672 = new BitSet(new long[]{0xDCF02A8654000010L,0x0009000000048689L});
    public static final BitSet FOLLOW_RBRACE_in_interfaceBody1694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMI_in_classBodyDeclaration1714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATIC_in_classBodyDeclaration1725 = new BitSet(new long[]{0x8000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_classBodyDeclaration1747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberDecl_in_classBodyDeclaration1757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fieldDeclaration_in_memberDecl1778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodDeclaration_in_memberDecl1789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classDeclaration_in_memberDecl1800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interfaceDeclaration_in_memberDecl1811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_methodDeclaration1849 = new BitSet(new long[]{0x0000000000000010L,0x0008000000000000L});
    public static final BitSet FOLLOW_typeParameters_in_methodDeclaration1860 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_methodDeclaration1881 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_formalParameters_in_methodDeclaration1891 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004040L});
    public static final BitSet FOLLOW_THROWS_in_methodDeclaration1902 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_qualifiedNameList_in_methodDeclaration1904 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_LBRACE_in_methodDeclaration1925 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C184DFBFL});
    public static final BitSet FOLLOW_explicitConstructorInvocation_in_methodDeclaration1937 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C184DFBFL});
    public static final BitSet FOLLOW_blockStatement_in_methodDeclaration1959 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C184DFBFL});
    public static final BitSet FOLLOW_RBRACE_in_methodDeclaration1980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_methodDeclaration1990 = new BitSet(new long[]{0x4050208250000010L,0x0008000000000200L});
    public static final BitSet FOLLOW_typeParameters_in_methodDeclaration2001 = new BitSet(new long[]{0x4050208250000010L,0x0000000000000200L});
    public static final BitSet FOLLOW_type_in_methodDeclaration2023 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_VOID_in_methodDeclaration2037 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_methodDeclaration2057 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_formalParameters_in_methodDeclaration2067 = new BitSet(new long[]{0x8000000000000000L,0x0000000000054040L});
    public static final BitSet FOLLOW_LBRACKET_in_methodDeclaration2078 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_methodDeclaration2080 = new BitSet(new long[]{0x8000000000000000L,0x0000000000054040L});
    public static final BitSet FOLLOW_THROWS_in_methodDeclaration2102 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_qualifiedNameList_in_methodDeclaration2104 = new BitSet(new long[]{0x8000000000000000L,0x0000000000044000L});
    public static final BitSet FOLLOW_block_in_methodDeclaration2159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMI_in_methodDeclaration2173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_fieldDeclaration2215 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_type_in_fieldDeclaration2225 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_variableDeclarator_in_fieldDeclaration2235 = new BitSet(new long[]{0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_COMMA_in_fieldDeclaration2246 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_variableDeclarator_in_fieldDeclaration2248 = new BitSet(new long[]{0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_SEMI_in_fieldDeclaration2269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_variableDeclarator2289 = new BitSet(new long[]{0x0000000000000002L,0x0000000000410000L});
    public static final BitSet FOLLOW_LBRACKET_in_variableDeclarator2300 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_variableDeclarator2302 = new BitSet(new long[]{0x0000000000000002L,0x0000000000410000L});
    public static final BitSet FOLLOW_EQ_in_variableDeclarator2324 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1805212L});
    public static final BitSet FOLLOW_variableInitializer_in_variableDeclarator2326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interfaceFieldDeclaration_in_interfaceBodyDeclaration2365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interfaceMethodDeclaration_in_interfaceBodyDeclaration2375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interfaceDeclaration_in_interfaceBodyDeclaration2385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classDeclaration_in_interfaceBodyDeclaration2395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMI_in_interfaceBodyDeclaration2405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_interfaceMethodDeclaration2425 = new BitSet(new long[]{0x4050208250000010L,0x0008000000000200L});
    public static final BitSet FOLLOW_typeParameters_in_interfaceMethodDeclaration2436 = new BitSet(new long[]{0x4050208250000010L,0x0000000000000200L});
    public static final BitSet FOLLOW_type_in_interfaceMethodDeclaration2458 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_VOID_in_interfaceMethodDeclaration2469 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_interfaceMethodDeclaration2489 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_formalParameters_in_interfaceMethodDeclaration2499 = new BitSet(new long[]{0x0000000000000000L,0x0000000000050040L});
    public static final BitSet FOLLOW_LBRACKET_in_interfaceMethodDeclaration2510 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_interfaceMethodDeclaration2512 = new BitSet(new long[]{0x0000000000000000L,0x0000000000050040L});
    public static final BitSet FOLLOW_THROWS_in_interfaceMethodDeclaration2534 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_qualifiedNameList_in_interfaceMethodDeclaration2536 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_interfaceMethodDeclaration2549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_interfaceFieldDeclaration2571 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_type_in_interfaceFieldDeclaration2573 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_variableDeclarator_in_interfaceFieldDeclaration2575 = new BitSet(new long[]{0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_COMMA_in_interfaceFieldDeclaration2586 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_variableDeclarator_in_interfaceFieldDeclaration2588 = new BitSet(new long[]{0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_SEMI_in_interfaceFieldDeclaration2609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classOrInterfaceType_in_type2630 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_LBRACKET_in_type2641 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_type2643 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_primitiveType_in_type2664 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_LBRACKET_in_type2675 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_type2677 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_classOrInterfaceType2709 = new BitSet(new long[]{0x0000000000000002L,0x0008000000100000L});
    public static final BitSet FOLLOW_typeArguments_in_classOrInterfaceType2720 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_DOT_in_classOrInterfaceType2742 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_classOrInterfaceType2744 = new BitSet(new long[]{0x0000000000000002L,0x0008000000100000L});
    public static final BitSet FOLLOW_typeArguments_in_classOrInterfaceType2759 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_set_in_primitiveType0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_typeArguments2896 = new BitSet(new long[]{0x4050208250000010L,0x0000000002000000L});
    public static final BitSet FOLLOW_typeArgument_in_typeArguments2898 = new BitSet(new long[]{0x0000000000000000L,0x0004000000080000L});
    public static final BitSet FOLLOW_COMMA_in_typeArguments2909 = new BitSet(new long[]{0x4050208250000010L,0x0000000002000000L});
    public static final BitSet FOLLOW_typeArgument_in_typeArguments2911 = new BitSet(new long[]{0x0000000000000000L,0x0004000000080000L});
    public static final BitSet FOLLOW_GT_in_typeArguments2933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_typeArgument2953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUES_in_typeArgument2963 = new BitSet(new long[]{0x0000040000000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_typeArgument2987 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_type_in_typeArgument3031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualifiedName_in_qualifiedNameList3062 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_COMMA_in_qualifiedNameList3073 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_qualifiedName_in_qualifiedNameList3075 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_formalParameters3106 = new BitSet(new long[]{0x4050288250000010L,0x0001000000002000L});
    public static final BitSet FOLLOW_formalParameterDecls_in_formalParameters3117 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_formalParameters3139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ellipsisParameterDecl_in_formalParameterDecls3159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_normalParameterDecl_in_formalParameterDecls3169 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_COMMA_in_formalParameterDecls3180 = new BitSet(new long[]{0x4050288250000010L,0x0001000000000000L});
    public static final BitSet FOLLOW_normalParameterDecl_in_formalParameterDecls3182 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_normalParameterDecl_in_formalParameterDecls3204 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_COMMA_in_formalParameterDecls3214 = new BitSet(new long[]{0x4050288250000010L,0x0001000000000000L});
    public static final BitSet FOLLOW_ellipsisParameterDecl_in_formalParameterDecls3236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableModifiers_in_normalParameterDecl3256 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_type_in_normalParameterDecl3258 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_normalParameterDecl3260 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_LBRACKET_in_normalParameterDecl3271 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_normalParameterDecl3273 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_variableModifiers_in_ellipsisParameterDecl3304 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_type_in_ellipsisParameterDecl3314 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_ELLIPSIS_in_ellipsisParameterDecl3317 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_ellipsisParameterDecl3327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_explicitConstructorInvocation3349 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000012L});
    public static final BitSet FOLLOW_set_in_explicitConstructorInvocation3375 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_arguments_in_explicitConstructorInvocation3407 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_explicitConstructorInvocation3409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_in_explicitConstructorInvocation3420 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_DOT_in_explicitConstructorInvocation3430 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000002L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_explicitConstructorInvocation3441 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_SUPER_in_explicitConstructorInvocation3462 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_arguments_in_explicitConstructorInvocation3472 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_explicitConstructorInvocation3474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_qualifiedName3494 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_DOT_in_qualifiedName3505 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_qualifiedName3507 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_annotation_in_annotations3539 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_MONKEYS_AT_in_annotation3572 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_qualifiedName_in_annotation3574 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_LPAREN_in_annotation3588 = new BitSet(new long[]{0x4150208250003FF0L,0x00090003C1807212L});
    public static final BitSet FOLLOW_elementValuePairs_in_annotation3615 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_elementValue_in_annotation3639 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_annotation3675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elementValuePair_in_elementValuePairs3707 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_COMMA_in_elementValuePairs3718 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_elementValuePair_in_elementValuePairs3720 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_elementValuePair3751 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_EQ_in_elementValuePair3753 = new BitSet(new long[]{0x4150208250003FF0L,0x00090003C1805212L});
    public static final BitSet FOLLOW_elementValue_in_elementValuePair3755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalExpression_in_elementValue3775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_elementValue3785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elementValueArrayInitializer_in_elementValue3795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_elementValueArrayInitializer3815 = new BitSet(new long[]{0x4150208250003FF0L,0x00090003C188D212L});
    public static final BitSet FOLLOW_elementValue_in_elementValueArrayInitializer3826 = new BitSet(new long[]{0x0000000000000000L,0x0000000000088000L});
    public static final BitSet FOLLOW_COMMA_in_elementValueArrayInitializer3841 = new BitSet(new long[]{0x4150208250003FF0L,0x00090003C1805212L});
    public static final BitSet FOLLOW_elementValue_in_elementValueArrayInitializer3843 = new BitSet(new long[]{0x0000000000000000L,0x0000000000088000L});
    public static final BitSet FOLLOW_COMMA_in_elementValueArrayInitializer3872 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RBRACE_in_elementValueArrayInitializer3876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_annotationTypeDeclaration3899 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_MONKEYS_AT_in_annotationTypeDeclaration3901 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_INTERFACE_in_annotationTypeDeclaration3911 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_annotationTypeDeclaration3921 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_annotationTypeBody_in_annotationTypeDeclaration3931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_annotationTypeBody3952 = new BitSet(new long[]{0xDCF02A8654000010L,0x0001000000048489L});
    public static final BitSet FOLLOW_annotationTypeElementDeclaration_in_annotationTypeBody3964 = new BitSet(new long[]{0xDCF02A8654000010L,0x0001000000048489L});
    public static final BitSet FOLLOW_RBRACE_in_annotationTypeBody3986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationMethodDeclaration_in_annotationTypeElementDeclaration4008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interfaceFieldDeclaration_in_annotationTypeElementDeclaration4018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_normalClassDeclaration_in_annotationTypeElementDeclaration4028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_normalInterfaceDeclaration_in_annotationTypeElementDeclaration4038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumDeclaration_in_annotationTypeElementDeclaration4048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationTypeDeclaration_in_annotationTypeElementDeclaration4058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMI_in_annotationTypeElementDeclaration4068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_annotationMethodDeclaration4088 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_type_in_annotationMethodDeclaration4090 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_annotationMethodDeclaration4092 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_LPAREN_in_annotationMethodDeclaration4102 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_annotationMethodDeclaration4104 = new BitSet(new long[]{0x0000002000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_DEFAULT_in_annotationMethodDeclaration4107 = new BitSet(new long[]{0x4150208250003FF0L,0x00090003C1805212L});
    public static final BitSet FOLLOW_elementValue_in_annotationMethodDeclaration4109 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_annotationMethodDeclaration4138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_block4162 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C184DFBFL});
    public static final BitSet FOLLOW_blockStatement_in_block4173 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C184DFBFL});
    public static final BitSet FOLLOW_RBRACE_in_block4194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_localVariableDeclarationStatement_in_blockStatement4216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_blockStatement4226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_blockStatement4236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_localVariableDeclaration_in_localVariableDeclarationStatement4257 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_localVariableDeclarationStatement4267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableModifiers_in_localVariableDeclaration4287 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_type_in_localVariableDeclaration4289 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_variableDeclarator_in_localVariableDeclaration4299 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_COMMA_in_localVariableDeclaration4310 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_variableDeclarator_in_localVariableDeclaration4312 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_block_in_statement4343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSERT_in_statement4367 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_statement4387 = new BitSet(new long[]{0x0000000000000000L,0x0000000004040000L});
    public static final BitSet FOLLOW_COLON_in_statement4390 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_statement4392 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_statement4396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSERT_in_statement4406 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_statement4409 = new BitSet(new long[]{0x0000000000000000L,0x0000000004040000L});
    public static final BitSet FOLLOW_COLON_in_statement4412 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_statement4414 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_statement4418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_statement4440 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_parExpression_in_statement4442 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C1845FBFL});
    public static final BitSet FOLLOW_statement_in_statement4444 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_ELSE_in_statement4447 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C1845FBFL});
    public static final BitSet FOLLOW_statement_in_statement4449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forstatement_in_statement4471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_statement4481 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_parExpression_in_statement4483 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C1845FBFL});
    public static final BitSet FOLLOW_statement_in_statement4485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DO_in_statement4495 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C1845FBFL});
    public static final BitSet FOLLOW_statement_in_statement4497 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_WHILE_in_statement4499 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_parExpression_in_statement4501 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_statement4503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trystatement_in_statement4513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SWITCH_in_statement4523 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_parExpression_in_statement4525 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_LBRACE_in_statement4527 = new BitSet(new long[]{0x0000002080000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_switchBlockStatementGroups_in_statement4529 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RBRACE_in_statement4531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SYNCHRONIZED_in_statement4541 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_parExpression_in_statement4543 = new BitSet(new long[]{0x8000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_statement4545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_statement4555 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1841212L});
    public static final BitSet FOLLOW_expression_in_statement4558 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_statement4563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THROW_in_statement4573 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_statement4575 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_statement4577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BREAK_in_statement4587 = new BitSet(new long[]{0x0000000000000010L,0x0000000000040000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_statement4602 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_statement4619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTINUE_in_statement4629 = new BitSet(new long[]{0x0000000000000010L,0x0000000000040000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_statement4644 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_statement4661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_statement4671 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_statement4674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_statement4689 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_COLON_in_statement4691 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C1845FBFL});
    public static final BitSet FOLLOW_statement_in_statement4693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMI_in_statement4703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_switchBlockStatementGroup_in_switchBlockStatementGroups4725 = new BitSet(new long[]{0x0000002080000002L});
    public static final BitSet FOLLOW_switchLabel_in_switchBlockStatementGroup4754 = new BitSet(new long[]{0xFDF16AD67C003FF2L,0x00090003C1845FBFL});
    public static final BitSet FOLLOW_blockStatement_in_switchBlockStatementGroup4765 = new BitSet(new long[]{0xFDF16AD67C003FF2L,0x00090003C1845FBFL});
    public static final BitSet FOLLOW_CASE_in_switchLabel4796 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_switchLabel4798 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_COLON_in_switchLabel4800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFAULT_in_switchLabel4810 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_COLON_in_switchLabel4812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRY_in_trystatement4833 = new BitSet(new long[]{0x8000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_trystatement4835 = new BitSet(new long[]{0x0000100100000000L});
    public static final BitSet FOLLOW_catches_in_trystatement4849 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_FINALLY_in_trystatement4851 = new BitSet(new long[]{0x8000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_trystatement4853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_catches_in_trystatement4867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINALLY_in_trystatement4881 = new BitSet(new long[]{0x8000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_trystatement4883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_catchClause_in_catches4914 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_catchClause_in_catches4925 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_CATCH_in_catchClause4956 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_LPAREN_in_catchClause4958 = new BitSet(new long[]{0x4050288250000010L,0x0001000000000000L});
    public static final BitSet FOLLOW_formalParameter_in_catchClause4960 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_catchClause4970 = new BitSet(new long[]{0x8000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_catchClause4972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableModifiers_in_formalParameter4993 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_type_in_formalParameter4995 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_formalParameter4997 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_LBRACKET_in_formalParameter5008 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_formalParameter5010 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_FOR_in_forstatement5059 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_LPAREN_in_forstatement5061 = new BitSet(new long[]{0x4050288250000010L,0x0001000000000000L});
    public static final BitSet FOLLOW_variableModifiers_in_forstatement5063 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_type_in_forstatement5065 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_forstatement5067 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_COLON_in_forstatement5069 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_forstatement5080 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_forstatement5082 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C1845FBFL});
    public static final BitSet FOLLOW_statement_in_forstatement5084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_forstatement5116 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_LPAREN_in_forstatement5118 = new BitSet(new long[]{0x4150288250003FF0L,0x00090003C1841212L});
    public static final BitSet FOLLOW_forInit_in_forstatement5138 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_forstatement5159 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1841212L});
    public static final BitSet FOLLOW_expression_in_forstatement5179 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_forstatement5200 = new BitSet(new long[]{0x4150288250003FF0L,0x00090003C1803212L});
    public static final BitSet FOLLOW_expressionList_in_forstatement5220 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_forstatement5241 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C1845FBFL});
    public static final BitSet FOLLOW_statement_in_forstatement5243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_localVariableDeclaration_in_forInit5263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expressionList_in_forInit5273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parExpression5293 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_parExpression5295 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_parExpression5297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionList5317 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_COMMA_in_expressionList5328 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_expressionList5330 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_conditionalExpression_in_expression5362 = new BitSet(new long[]{0x0000000000000002L,0x000CFF0000400000L});
    public static final BitSet FOLLOW_assignmentOperator_in_expression5373 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_expression5375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_assignmentOperator5407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUSEQ_in_assignmentOperator5417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUBEQ_in_assignmentOperator5427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAREQ_in_assignmentOperator5437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASHEQ_in_assignmentOperator5447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AMPEQ_in_assignmentOperator5457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BAREQ_in_assignmentOperator5467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CARETEQ_in_assignmentOperator5477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENTEQ_in_assignmentOperator5487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_assignmentOperator5498 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_LT_in_assignmentOperator5500 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_EQ_in_assignmentOperator5502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_assignmentOperator5513 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_GT_in_assignmentOperator5515 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_GT_in_assignmentOperator5517 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_EQ_in_assignmentOperator5519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_assignmentOperator5530 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_GT_in_assignmentOperator5532 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_EQ_in_assignmentOperator5534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalOrExpression_in_conditionalExpression5555 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_QUES_in_conditionalExpression5566 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_conditionalExpression5568 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_COLON_in_conditionalExpression5570 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_conditionalExpression_in_conditionalExpression5572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalAndExpression_in_conditionalOrExpression5603 = new BitSet(new long[]{0x0000000000000002L,0x0000000020000000L});
    public static final BitSet FOLLOW_BARBAR_in_conditionalOrExpression5614 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_conditionalAndExpression_in_conditionalOrExpression5616 = new BitSet(new long[]{0x0000000000000002L,0x0000000020000000L});
    public static final BitSet FOLLOW_inclusiveOrExpression_in_conditionalAndExpression5647 = new BitSet(new long[]{0x0000000000000002L,0x0000000010000000L});
    public static final BitSet FOLLOW_AMPAMP_in_conditionalAndExpression5658 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_inclusiveOrExpression_in_conditionalAndExpression5660 = new BitSet(new long[]{0x0000000000000002L,0x0000000010000000L});
    public static final BitSet FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression5691 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
    public static final BitSet FOLLOW_BAR_in_inclusiveOrExpression5702 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression5704 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
    public static final BitSet FOLLOW_andExpression_in_exclusiveOrExpression5735 = new BitSet(new long[]{0x0000000000000002L,0x0000004000000000L});
    public static final BitSet FOLLOW_CARET_in_exclusiveOrExpression5746 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_andExpression_in_exclusiveOrExpression5748 = new BitSet(new long[]{0x0000000000000002L,0x0000004000000000L});
    public static final BitSet FOLLOW_equalityExpression_in_andExpression5779 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});
    public static final BitSet FOLLOW_AMP_in_andExpression5790 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_equalityExpression_in_andExpression5792 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});
    public static final BitSet FOLLOW_instanceOfExpression_in_equalityExpression5823 = new BitSet(new long[]{0x0000000000000002L,0x0002000008000000L});
    public static final BitSet FOLLOW_set_in_equalityExpression5850 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_instanceOfExpression_in_equalityExpression5900 = new BitSet(new long[]{0x0000000000000002L,0x0002000008000000L});
    public static final BitSet FOLLOW_relationalExpression_in_instanceOfExpression5931 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_INSTANCEOF_in_instanceOfExpression5942 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_type_in_instanceOfExpression5944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_shiftExpression_in_relationalExpression5975 = new BitSet(new long[]{0x0000000000000002L,0x000C000000000000L});
    public static final BitSet FOLLOW_relationalOp_in_relationalExpression5986 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_shiftExpression_in_relationalExpression5988 = new BitSet(new long[]{0x0000000000000002L,0x000C000000000000L});
    public static final BitSet FOLLOW_LT_in_relationalOp6020 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_EQ_in_relationalOp6022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_relationalOp6033 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_EQ_in_relationalOp6035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_relationalOp6045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_relationalOp6055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_additiveExpression_in_shiftExpression6075 = new BitSet(new long[]{0x0000000000000002L,0x000C000000000000L});
    public static final BitSet FOLLOW_shiftOp_in_shiftExpression6086 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_additiveExpression_in_shiftExpression6088 = new BitSet(new long[]{0x0000000000000002L,0x000C000000000000L});
    public static final BitSet FOLLOW_LT_in_shiftOp6121 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_LT_in_shiftOp6123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_shiftOp6134 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_GT_in_shiftOp6136 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_GT_in_shiftOp6138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_shiftOp6149 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_GT_in_shiftOp6151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression6172 = new BitSet(new long[]{0x0000000000000002L,0x0000000300000000L});
    public static final BitSet FOLLOW_set_in_additiveExpression6199 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression6249 = new BitSet(new long[]{0x0000000000000002L,0x0000000300000000L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression6287 = new BitSet(new long[]{0x0000000000000002L,0x0000008C00000000L});
    public static final BitSet FOLLOW_set_in_multiplicativeExpression6314 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression6382 = new BitSet(new long[]{0x0000000000000002L,0x0000008C00000000L});
    public static final BitSet FOLLOW_PLUS_in_unaryExpression6415 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression6418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUB_in_unaryExpression6428 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression6430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUSPLUS_in_unaryExpression6440 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression6442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUBSUB_in_unaryExpression6452 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression6454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpressionNotPlusMinus_in_unaryExpression6464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TILDE_in_unaryExpressionNotPlusMinus6484 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus6486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BANG_in_unaryExpressionNotPlusMinus6496 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus6498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_castExpression_in_unaryExpressionNotPlusMinus6508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_in_unaryExpressionNotPlusMinus6518 = new BitSet(new long[]{0x0000000000000002L,0x00000000C0110000L});
    public static final BitSet FOLLOW_selector_in_unaryExpressionNotPlusMinus6529 = new BitSet(new long[]{0x0000000000000002L,0x00000000C0110000L});
    public static final BitSet FOLLOW_set_in_unaryExpressionNotPlusMinus6550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_castExpression6599 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_primitiveType_in_castExpression6601 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_castExpression6603 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_unaryExpression_in_castExpression6605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_castExpression6615 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_type_in_castExpression6617 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_castExpression6619 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_unaryExpressionNotPlusMinus_in_castExpression6621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parExpression_in_primary6643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THIS_in_primary6665 = new BitSet(new long[]{0x0000000000000002L,0x0000000000111000L});
    public static final BitSet FOLLOW_DOT_in_primary6676 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_primary6678 = new BitSet(new long[]{0x0000000000000002L,0x0000000000111000L});
    public static final BitSet FOLLOW_identifierSuffix_in_primary6700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_primary6721 = new BitSet(new long[]{0x0000000000000002L,0x0000000000111000L});
    public static final BitSet FOLLOW_DOT_in_primary6732 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_primary6734 = new BitSet(new long[]{0x0000000000000002L,0x0000000000111000L});
    public static final BitSet FOLLOW_identifierSuffix_in_primary6756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUPER_in_primary6777 = new BitSet(new long[]{0x0000000000000000L,0x0000000000101000L});
    public static final BitSet FOLLOW_superSuffix_in_primary6787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_primary6797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_creator_in_primary6807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primitiveType_in_primary6817 = new BitSet(new long[]{0x0000000000000000L,0x0000000000110000L});
    public static final BitSet FOLLOW_LBRACKET_in_primary6828 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_primary6830 = new BitSet(new long[]{0x0000000000000000L,0x0000000000110000L});
    public static final BitSet FOLLOW_DOT_in_primary6851 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_CLASS_in_primary6853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_primary6863 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_DOT_in_primary6865 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_CLASS_in_primary6867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arguments_in_superSuffix6893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_superSuffix6903 = new BitSet(new long[]{0x0000000000000010L,0x0008000000000000L});
    public static final BitSet FOLLOW_typeArguments_in_superSuffix6906 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_superSuffix6927 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_arguments_in_superSuffix6938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_identifierSuffix6971 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_identifierSuffix6973 = new BitSet(new long[]{0x0000000000000000L,0x0000000000110000L});
    public static final BitSet FOLLOW_DOT_in_identifierSuffix6994 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_CLASS_in_identifierSuffix6996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_identifierSuffix7007 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_identifierSuffix7009 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_identifierSuffix7011 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_arguments_in_identifierSuffix7032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_identifierSuffix7042 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_CLASS_in_identifierSuffix7044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_identifierSuffix7054 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_identifierSuffix7056 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierSuffix7058 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_arguments_in_identifierSuffix7060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_identifierSuffix7070 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_THIS_in_identifierSuffix7072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_identifierSuffix7082 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_SUPER_in_identifierSuffix7084 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_arguments_in_identifierSuffix7086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_innerCreator_in_identifierSuffix7096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_selector7118 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_selector7120 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_arguments_in_selector7131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_selector7152 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_THIS_in_selector7154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_selector7164 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_SUPER_in_selector7166 = new BitSet(new long[]{0x0000000000000000L,0x0000000000101000L});
    public static final BitSet FOLLOW_superSuffix_in_selector7176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_innerCreator_in_selector7186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_selector7196 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_selector7198 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_selector7200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_creator7220 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_creator7222 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_classOrInterfaceType_in_creator7224 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_classCreatorRest_in_creator7226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_creator7236 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_classOrInterfaceType_in_creator7238 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_classCreatorRest_in_creator7240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayCreator_in_creator7250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_arrayCreator7270 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_createdName_in_arrayCreator7272 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_LBRACKET_in_arrayCreator7282 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_arrayCreator7284 = new BitSet(new long[]{0x0000000000000000L,0x0000000000014000L});
    public static final BitSet FOLLOW_LBRACKET_in_arrayCreator7295 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_arrayCreator7297 = new BitSet(new long[]{0x0000000000000000L,0x0000000000014000L});
    public static final BitSet FOLLOW_arrayInitializer_in_arrayCreator7318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_arrayCreator7329 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_createdName_in_arrayCreator7331 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_LBRACKET_in_arrayCreator7341 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_arrayCreator7343 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_arrayCreator7353 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_LBRACKET_in_arrayCreator7367 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_arrayCreator7369 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_arrayCreator7383 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_LBRACKET_in_arrayCreator7405 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_arrayCreator7407 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_arrayInitializer_in_variableInitializer7438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_variableInitializer7448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_arrayInitializer7468 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C188D212L});
    public static final BitSet FOLLOW_variableInitializer_in_arrayInitializer7484 = new BitSet(new long[]{0x0000000000000000L,0x0000000000088000L});
    public static final BitSet FOLLOW_COMMA_in_arrayInitializer7503 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1805212L});
    public static final BitSet FOLLOW_variableInitializer_in_arrayInitializer7505 = new BitSet(new long[]{0x0000000000000000L,0x0000000000088000L});
    public static final BitSet FOLLOW_COMMA_in_arrayInitializer7555 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RBRACE_in_arrayInitializer7568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classOrInterfaceType_in_createdName7602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primitiveType_in_createdName7612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_innerCreator7633 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_NEW_in_innerCreator7635 = new BitSet(new long[]{0x0000000000000010L,0x0008000000000000L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_innerCreator7646 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_innerCreator7667 = new BitSet(new long[]{0x0000000000000000L,0x0008000000001000L});
    public static final BitSet FOLLOW_typeArguments_in_innerCreator7678 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_classCreatorRest_in_innerCreator7699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arguments_in_classCreatorRest7720 = new BitSet(new long[]{0x0002040000000002L,0x0008000000004000L});
    public static final BitSet FOLLOW_classBody_in_classCreatorRest7731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_nonWildcardTypeArguments7763 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_typeList_in_nonWildcardTypeArguments7765 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_GT_in_nonWildcardTypeArguments7775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_arguments7795 = new BitSet(new long[]{0x4150288250003FF0L,0x00090003C1803212L});
    public static final BitSet FOLLOW_expressionList_in_arguments7798 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_arguments7811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_literal0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_classHeader7935 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_CLASS_in_classHeader7937 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_classHeader7939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_enumHeader7959 = new BitSet(new long[]{0x0000020000000010L});
    public static final BitSet FOLLOW_set_in_enumHeader7961 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_enumHeader7967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_interfaceHeader7987 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_INTERFACE_in_interfaceHeader7989 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_interfaceHeader7991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_annotationHeader8011 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_MONKEYS_AT_in_annotationHeader8013 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_INTERFACE_in_annotationHeader8015 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_annotationHeader8017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_typeHeader8037 = new BitSet(new long[]{0x0020020400000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_CLASS_in_typeHeader8040 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ENUM_in_typeHeader8042 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_MONKEYS_AT_in_typeHeader8045 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_INTERFACE_in_typeHeader8049 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_typeHeader8053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_methodHeader8073 = new BitSet(new long[]{0x4050208250000010L,0x0008000000000200L});
    public static final BitSet FOLLOW_typeParameters_in_methodHeader8075 = new BitSet(new long[]{0x4050208250000010L,0x0000000000000200L});
    public static final BitSet FOLLOW_type_in_methodHeader8079 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_VOID_in_methodHeader8081 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_methodHeader8085 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_LPAREN_in_methodHeader8087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_fieldHeader8107 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_type_in_fieldHeader8109 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_fieldHeader8111 = new BitSet(new long[]{0x0000000000000000L,0x00000000004D0000L});
    public static final BitSet FOLLOW_LBRACKET_in_fieldHeader8114 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_fieldHeader8115 = new BitSet(new long[]{0x0000000000000000L,0x00000000004D0000L});
    public static final BitSet FOLLOW_set_in_fieldHeader8119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableModifiers_in_localVariableHeader8145 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_type_in_localVariableHeader8147 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_localVariableHeader8149 = new BitSet(new long[]{0x0000000000000000L,0x00000000004D0000L});
    public static final BitSet FOLLOW_LBRACKET_in_localVariableHeader8152 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_localVariableHeader8153 = new BitSet(new long[]{0x0000000000000000L,0x00000000004D0000L});
    public static final BitSet FOLLOW_set_in_localVariableHeader8157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotations_in_synpred2_Java115 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_packageDeclaration_in_synpred2_Java144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classDeclaration_in_synpred12_Java512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_normalClassDeclaration_in_synpred27_Java749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_normalInterfaceDeclaration_in_synpred43_Java1448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fieldDeclaration_in_synpred52_Java1778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodDeclaration_in_synpred53_Java1789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classDeclaration_in_synpred54_Java1800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_explicitConstructorInvocation_in_synpred57_Java1937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_synpred59_Java1849 = new BitSet(new long[]{0x0000000000000010L,0x0008000000000000L});
    public static final BitSet FOLLOW_typeParameters_in_synpred59_Java1860 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_synpred59_Java1881 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_formalParameters_in_synpred59_Java1891 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004040L});
    public static final BitSet FOLLOW_THROWS_in_synpred59_Java1902 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_qualifiedNameList_in_synpred59_Java1904 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_LBRACE_in_synpred59_Java1925 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C184DFBFL});
    public static final BitSet FOLLOW_explicitConstructorInvocation_in_synpred59_Java1937 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C184DFBFL});
    public static final BitSet FOLLOW_blockStatement_in_synpred59_Java1959 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C184DFBFL});
    public static final BitSet FOLLOW_RBRACE_in_synpred59_Java1980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interfaceFieldDeclaration_in_synpred68_Java2365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interfaceMethodDeclaration_in_synpred69_Java2375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interfaceDeclaration_in_synpred70_Java2385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classDeclaration_in_synpred71_Java2395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ellipsisParameterDecl_in_synpred96_Java3159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_normalParameterDecl_in_synpred98_Java3169 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_COMMA_in_synpred98_Java3180 = new BitSet(new long[]{0x4050288250000010L,0x0001000000000000L});
    public static final BitSet FOLLOW_normalParameterDecl_in_synpred98_Java3182 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_normalParameterDecl_in_synpred99_Java3204 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_COMMA_in_synpred99_Java3214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_synpred103_Java3349 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000012L});
    public static final BitSet FOLLOW_set_in_synpred103_Java3375 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_arguments_in_synpred103_Java3407 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_synpred103_Java3409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationMethodDeclaration_in_synpred117_Java4008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interfaceFieldDeclaration_in_synpred118_Java4018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_normalClassDeclaration_in_synpred119_Java4028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_normalInterfaceDeclaration_in_synpred120_Java4038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumDeclaration_in_synpred121_Java4048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationTypeDeclaration_in_synpred122_Java4058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_localVariableDeclarationStatement_in_synpred125_Java4216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_synpred126_Java4226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSERT_in_synpred130_Java4367 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_synpred130_Java4387 = new BitSet(new long[]{0x0000000000000000L,0x0000000004040000L});
    public static final BitSet FOLLOW_COLON_in_synpred130_Java4390 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_synpred130_Java4392 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_synpred130_Java4396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSERT_in_synpred132_Java4406 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_synpred132_Java4409 = new BitSet(new long[]{0x0000000000000000L,0x0000000004040000L});
    public static final BitSet FOLLOW_COLON_in_synpred132_Java4412 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_synpred132_Java4414 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_synpred132_Java4418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELSE_in_synpred133_Java4447 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C1845FBFL});
    public static final BitSet FOLLOW_statement_in_synpred133_Java4449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_synpred148_Java4671 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_synpred148_Java4674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_synpred149_Java4689 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_COLON_in_synpred149_Java4691 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C1845FBFL});
    public static final BitSet FOLLOW_statement_in_synpred149_Java4693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_catches_in_synpred153_Java4849 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_FINALLY_in_synpred153_Java4851 = new BitSet(new long[]{0x8000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_synpred153_Java4853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_catches_in_synpred154_Java4867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_synpred157_Java5059 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_LPAREN_in_synpred157_Java5061 = new BitSet(new long[]{0x4050288250000010L,0x0001000000000000L});
    public static final BitSet FOLLOW_variableModifiers_in_synpred157_Java5063 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_type_in_synpred157_Java5065 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_synpred157_Java5067 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_COLON_in_synpred157_Java5069 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_synpred157_Java5080 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred157_Java5082 = new BitSet(new long[]{0xFDF16AD67C003FF0L,0x00090003C1845FBFL});
    public static final BitSet FOLLOW_statement_in_synpred157_Java5084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_localVariableDeclaration_in_synpred161_Java5263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_castExpression_in_synpred202_Java6508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred206_Java6599 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_primitiveType_in_synpred206_Java6601 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred206_Java6603 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_unaryExpression_in_synpred206_Java6605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_synpred208_Java6676 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_synpred208_Java6678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifierSuffix_in_synpred209_Java6700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_synpred211_Java6732 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_synpred211_Java6734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifierSuffix_in_synpred212_Java6756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_synpred224_Java7007 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_synpred224_Java7009 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_synpred224_Java7011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_synpred236_Java7220 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_synpred236_Java7222 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_classOrInterfaceType_in_synpred236_Java7224 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_classCreatorRest_in_synpred236_Java7226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_synpred237_Java7236 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_classOrInterfaceType_in_synpred237_Java7238 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_classCreatorRest_in_synpred237_Java7240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_synpred239_Java7270 = new BitSet(new long[]{0x4050208250000010L});
    public static final BitSet FOLLOW_createdName_in_synpred239_Java7272 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_LBRACKET_in_synpred239_Java7282 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_synpred239_Java7284 = new BitSet(new long[]{0x0000000000000000L,0x0000000000014000L});
    public static final BitSet FOLLOW_LBRACKET_in_synpred239_Java7295 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_synpred239_Java7297 = new BitSet(new long[]{0x0000000000000000L,0x0000000000014000L});
    public static final BitSet FOLLOW_arrayInitializer_in_synpred239_Java7318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_synpred240_Java7367 = new BitSet(new long[]{0x4150208250003FF0L,0x00080003C1801212L});
    public static final BitSet FOLLOW_expression_in_synpred240_Java7369 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_RBRACKET_in_synpred240_Java7383 = new BitSet(new long[]{0x0000000000000002L});

}