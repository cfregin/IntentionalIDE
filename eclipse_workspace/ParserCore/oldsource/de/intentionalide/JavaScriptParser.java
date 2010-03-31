// $ANTLR 3.2 Sep 23, 2009 12:02:23 /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g 2010-03-31 10:59:42

package de.intentionalide;
import java.util.LinkedList;
import de.intentionalide.IntendClass;
  


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

public class JavaScriptParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "LT", "Identifier", "StringLiteral", "NumericLiteral", "DoubleStringCharacter", "SingleStringCharacter", "EscapeSequence", "CharacterEscapeSequence", "HexEscapeSequence", "UnicodeEscapeSequence", "SingleEscapeCharacter", "NonEscapeCharacter", "EscapeCharacter", "DecimalDigit", "HexDigit", "DecimalLiteral", "HexIntegerLiteral", "ExponentPart", "IdentifierStart", "IdentifierPart", "UnicodeLetter", "UnicodeDigit", "UnicodeConnectorPunctuation", "UnicodeCombiningMark", "Comment", "LineComment", "WhiteSpace", "'function'", "'('", "','", "')'", "'{'", "'}'", "'var'", "';'", "'='", "'if'", "'else'", "'do'", "'while'", "'for'", "'in'", "'continue'", "'break'", "'return'", "'with'", "':'", "'switch'", "'case'", "'default'", "'throw'", "'try'", "'catch'", "'finally'", "'new'", "'['", "']'", "'.'", "'*='", "'/='", "'%='", "'+='", "'-='", "'<<='", "'>>='", "'>>>='", "'&='", "'^='", "'|='", "'?'", "'||'", "'&&'", "'|'", "'^'", "'&'", "'=='", "'!='", "'==='", "'!=='", "'<'", "'>'", "'<='", "'>='", "'instanceof'", "'<<'", "'>>'", "'>>>'", "'+'", "'-'", "'*'", "'/'", "'%'", "'delete'", "'void'", "'typeof'", "'++'", "'--'", "'~'", "'!'", "'this'", "'null'", "'true'", "'false'"
    };
    public static final int LT=4;
    public static final int DecimalDigit=17;
    public static final int EOF=-1;
    public static final int Identifier=5;
    public static final int SingleStringCharacter=9;
    public static final int T__93=93;
    public static final int T__94=94;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__90=90;
    public static final int Comment=28;
    public static final int SingleEscapeCharacter=14;
    public static final int UnicodeLetter=24;
    public static final int ExponentPart=21;
    public static final int WhiteSpace=30;
    public static final int T__99=99;
    public static final int T__98=98;
    public static final int T__97=97;
    public static final int T__96=96;
    public static final int UnicodeCombiningMark=27;
    public static final int T__95=95;
    public static final int UnicodeDigit=25;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int NumericLiteral=7;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int IdentifierStart=22;
    public static final int DoubleStringCharacter=8;
    public static final int T__85=85;
    public static final int T__84=84;
    public static final int T__87=87;
    public static final int T__86=86;
    public static final int T__89=89;
    public static final int T__88=88;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__70=70;
    public static final int CharacterEscapeSequence=11;
    public static final int T__76=76;
    public static final int T__75=75;
    public static final int T__74=74;
    public static final int T__73=73;
    public static final int EscapeSequence=10;
    public static final int T__79=79;
    public static final int UnicodeConnectorPunctuation=26;
    public static final int T__78=78;
    public static final int T__77=77;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int HexEscapeSequence=12;
    public static final int LineComment=29;
    public static final int T__61=61;
    public static final int T__60=60;
    public static final int HexDigit=18;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__59=59;
    public static final int T__103=103;
    public static final int T__104=104;
    public static final int T__105=105;
    public static final int T__106=106;
    public static final int EscapeCharacter=16;
    public static final int T__50=50;
    public static final int IdentifierPart=23;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int UnicodeEscapeSequence=13;
    public static final int T__102=102;
    public static final int T__101=101;
    public static final int T__100=100;
    public static final int DecimalLiteral=19;
    public static final int StringLiteral=6;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int HexIntegerLiteral=20;
    public static final int NonEscapeCharacter=15;

    // delegates
    // delegators


        public JavaScriptParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public JavaScriptParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            this.state.ruleMemo = new HashMap[380+1];
             
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return JavaScriptParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g"; }


    IntendClass currentClass = new IntendClass();
    public LinkedList<String> methods = new LinkedList();
      



    public static class program_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "program"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:32:1: program : ( LT )* sourceElements ( LT )* EOF ;
    public final JavaScriptParser.program_return program() throws RecognitionException {
        JavaScriptParser.program_return retval = new JavaScriptParser.program_return();
        retval.start = input.LT(1);
        int program_StartIndex = input.index();
        Object root_0 = null;

        Token LT1=null;
        Token LT3=null;
        Token EOF4=null;
        JavaScriptParser.sourceElements_return sourceElements2 = null;


        Object LT1_tree=null;
        Object LT3_tree=null;
        Object EOF4_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:33:3: ( ( LT )* sourceElements ( LT )* EOF )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:33:5: ( LT )* sourceElements ( LT )* EOF
            {
            root_0 = (Object)adaptor.nil();

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:33:7: ( LT )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==LT) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT1=(Token)match(input,LT,FOLLOW_LT_in_program60); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            pushFollow(FOLLOW_sourceElements_in_program64);
            sourceElements2=sourceElements();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, sourceElements2.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:33:27: ( LT )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==LT) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT3=(Token)match(input,LT,FOLLOW_LT_in_program66); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            EOF4=(Token)match(input,EOF,FOLLOW_EOF_in_program70); if (state.failed) return retval;

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
            if ( state.backtracking>0 ) { memoize(input, 1, program_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "program"

    public static class sourceElements_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "sourceElements"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:36:1: sourceElements : sourceElement ( ( LT )* sourceElement )* ;
    public final JavaScriptParser.sourceElements_return sourceElements() throws RecognitionException {
        JavaScriptParser.sourceElements_return retval = new JavaScriptParser.sourceElements_return();
        retval.start = input.LT(1);
        int sourceElements_StartIndex = input.index();
        Object root_0 = null;

        Token LT6=null;
        JavaScriptParser.sourceElement_return sourceElement5 = null;

        JavaScriptParser.sourceElement_return sourceElement7 = null;


        Object LT6_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:37:3: ( sourceElement ( ( LT )* sourceElement )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:37:5: sourceElement ( ( LT )* sourceElement )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_sourceElement_in_sourceElements86);
            sourceElement5=sourceElement();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, sourceElement5.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:37:19: ( ( LT )* sourceElement )*
            loop4:
            do {
                int alt4=2;
                alt4 = dfa4.predict(input);
                switch (alt4) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:37:20: ( LT )* sourceElement
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:37:22: ( LT )*
            	    loop3:
            	    do {
            	        int alt3=2;
            	        int LA3_0 = input.LA(1);

            	        if ( (LA3_0==LT) ) {
            	            alt3=1;
            	        }


            	        switch (alt3) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT6=(Token)match(input,LT,FOLLOW_LT_in_sourceElements89); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop3;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_sourceElement_in_sourceElements93);
            	    sourceElement7=sourceElement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, sourceElement7.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 2, sourceElements_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "sourceElements"

    public static class sourceElement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "sourceElement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:40:1: sourceElement : ( functionDeclaration | statement );
    public final JavaScriptParser.sourceElement_return sourceElement() throws RecognitionException {
        JavaScriptParser.sourceElement_return retval = new JavaScriptParser.sourceElement_return();
        retval.start = input.LT(1);
        int sourceElement_StartIndex = input.index();
        Object root_0 = null;

        JavaScriptParser.functionDeclaration_return functionDeclaration8 = null;

        JavaScriptParser.statement_return statement9 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:41:3: ( functionDeclaration | statement )
            int alt5=2;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:41:5: functionDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_functionDeclaration_in_sourceElement110);
                    functionDeclaration8=functionDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, functionDeclaration8.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:42:5: statement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_statement_in_sourceElement116);
                    statement9=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement9.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 3, sourceElement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "sourceElement"

    public static class functionDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "functionDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:46:1: functionDeclaration : 'function' ( LT )* Identifier ( LT )* formalParameterList ( LT )* functionBody ;
    public final JavaScriptParser.functionDeclaration_return functionDeclaration() throws RecognitionException {
        JavaScriptParser.functionDeclaration_return retval = new JavaScriptParser.functionDeclaration_return();
        retval.start = input.LT(1);
        int functionDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal10=null;
        Token LT11=null;
        Token Identifier12=null;
        Token LT13=null;
        Token LT15=null;
        JavaScriptParser.formalParameterList_return formalParameterList14 = null;

        JavaScriptParser.functionBody_return functionBody16 = null;


        Object string_literal10_tree=null;
        Object LT11_tree=null;
        Object Identifier12_tree=null;
        Object LT13_tree=null;
        Object LT15_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:47:3: ( 'function' ( LT )* Identifier ( LT )* formalParameterList ( LT )* functionBody )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:47:5: 'function' ( LT )* Identifier ( LT )* formalParameterList ( LT )* functionBody
            {
            root_0 = (Object)adaptor.nil();

            string_literal10=(Token)match(input,31,FOLLOW_31_in_functionDeclaration132); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal10_tree = (Object)adaptor.create(string_literal10);
            adaptor.addChild(root_0, string_literal10_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:47:18: ( LT )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==LT) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT11=(Token)match(input,LT,FOLLOW_LT_in_functionDeclaration134); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            Identifier12=(Token)match(input,Identifier,FOLLOW_Identifier_in_functionDeclaration138); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier12_tree = (Object)adaptor.create(Identifier12);
            adaptor.addChild(root_0, Identifier12_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:47:34: ( LT )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==LT) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT13=(Token)match(input,LT,FOLLOW_LT_in_functionDeclaration140); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            pushFollow(FOLLOW_formalParameterList_in_functionDeclaration144);
            formalParameterList14=formalParameterList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, formalParameterList14.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:47:59: ( LT )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==LT) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT15=(Token)match(input,LT,FOLLOW_LT_in_functionDeclaration146); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            pushFollow(FOLLOW_functionBody_in_functionDeclaration150);
            functionBody16=functionBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, functionBody16.getTree());
            if ( state.backtracking==0 ) {
               methods.add("Got function functionDeclaration:" + (Identifier12!=null?Identifier12.getText():null) +", parameter:" + (formalParameterList14!=null?input.toString(formalParameterList14.start,formalParameterList14.stop):null) + ", With function content:" + (functionBody16!=null?input.toString(functionBody16.start,functionBody16.stop):null));
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
            if ( state.backtracking>0 ) { memoize(input, 4, functionDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "functionDeclaration"

    public static class functionExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "functionExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:51:1: functionExpression : 'function' ( LT )* ( Identifier )? ( LT )* formalParameterList ( LT )* functionBody ;
    public final JavaScriptParser.functionExpression_return functionExpression() throws RecognitionException {
        JavaScriptParser.functionExpression_return retval = new JavaScriptParser.functionExpression_return();
        retval.start = input.LT(1);
        int functionExpression_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal17=null;
        Token LT18=null;
        Token Identifier19=null;
        Token LT20=null;
        Token LT22=null;
        JavaScriptParser.formalParameterList_return formalParameterList21 = null;

        JavaScriptParser.functionBody_return functionBody23 = null;


        Object string_literal17_tree=null;
        Object LT18_tree=null;
        Object Identifier19_tree=null;
        Object LT20_tree=null;
        Object LT22_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:52:3: ( 'function' ( LT )* ( Identifier )? ( LT )* formalParameterList ( LT )* functionBody )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:52:5: 'function' ( LT )* ( Identifier )? ( LT )* formalParameterList ( LT )* functionBody
            {
            root_0 = (Object)adaptor.nil();

            string_literal17=(Token)match(input,31,FOLLOW_31_in_functionExpression169); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal17_tree = (Object)adaptor.create(string_literal17);
            adaptor.addChild(root_0, string_literal17_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:52:18: ( LT )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==LT) ) {
                    int LA9_2 = input.LA(2);

                    if ( (synpred9_JavaScript()) ) {
                        alt9=1;
                    }


                }


                switch (alt9) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT18=(Token)match(input,LT,FOLLOW_LT_in_functionExpression171); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:52:21: ( Identifier )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==Identifier) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: Identifier
                    {
                    Identifier19=(Token)match(input,Identifier,FOLLOW_Identifier_in_functionExpression175); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Identifier19_tree = (Object)adaptor.create(Identifier19);
                    adaptor.addChild(root_0, Identifier19_tree);
                    }

                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:52:35: ( LT )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==LT) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT20=(Token)match(input,LT,FOLLOW_LT_in_functionExpression178); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            pushFollow(FOLLOW_formalParameterList_in_functionExpression182);
            formalParameterList21=formalParameterList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, formalParameterList21.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:52:60: ( LT )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==LT) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT22=(Token)match(input,LT,FOLLOW_LT_in_functionExpression184); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            pushFollow(FOLLOW_functionBody_in_functionExpression188);
            functionBody23=functionBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, functionBody23.getTree());
            if ( state.backtracking==0 ) {
               methods.add("Got function expression");
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
            if ( state.backtracking>0 ) { memoize(input, 5, functionExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "functionExpression"

    public static class formalParameterList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "formalParameterList"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:56:1: formalParameterList : '(' ( ( LT )* t1= Identifier ( ( LT )* ',' ( LT )* t2= Identifier )* )? ( LT )* ')' ;
    public final JavaScriptParser.formalParameterList_return formalParameterList() throws RecognitionException {
        JavaScriptParser.formalParameterList_return retval = new JavaScriptParser.formalParameterList_return();
        retval.start = input.LT(1);
        int formalParameterList_StartIndex = input.index();
        Object root_0 = null;

        Token t1=null;
        Token t2=null;
        Token char_literal24=null;
        Token LT25=null;
        Token LT26=null;
        Token char_literal27=null;
        Token LT28=null;
        Token LT29=null;
        Token char_literal30=null;

        Object t1_tree=null;
        Object t2_tree=null;
        Object char_literal24_tree=null;
        Object LT25_tree=null;
        Object LT26_tree=null;
        Object char_literal27_tree=null;
        Object LT28_tree=null;
        Object LT29_tree=null;
        Object char_literal30_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:57:3: ( '(' ( ( LT )* t1= Identifier ( ( LT )* ',' ( LT )* t2= Identifier )* )? ( LT )* ')' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:57:5: '(' ( ( LT )* t1= Identifier ( ( LT )* ',' ( LT )* t2= Identifier )* )? ( LT )* ')'
            {
            root_0 = (Object)adaptor.nil();

            char_literal24=(Token)match(input,32,FOLLOW_32_in_formalParameterList207); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal24_tree = (Object)adaptor.create(char_literal24);
            adaptor.addChild(root_0, char_literal24_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:57:9: ( ( LT )* t1= Identifier ( ( LT )* ',' ( LT )* t2= Identifier )* )?
            int alt17=2;
            alt17 = dfa17.predict(input);
            switch (alt17) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:57:10: ( LT )* t1= Identifier ( ( LT )* ',' ( LT )* t2= Identifier )*
                    {
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:57:12: ( LT )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==LT) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT25=(Token)match(input,LT,FOLLOW_LT_in_formalParameterList210); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);

                    t1=(Token)match(input,Identifier,FOLLOW_Identifier_in_formalParameterList216); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t1_tree = (Object)adaptor.create(t1);
                    adaptor.addChild(root_0, t1_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:57:29: ( ( LT )* ',' ( LT )* t2= Identifier )*
                    loop16:
                    do {
                        int alt16=2;
                        alt16 = dfa16.predict(input);
                        switch (alt16) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:57:30: ( LT )* ',' ( LT )* t2= Identifier
                    	    {
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:57:32: ( LT )*
                    	    loop14:
                    	    do {
                    	        int alt14=2;
                    	        int LA14_0 = input.LA(1);

                    	        if ( (LA14_0==LT) ) {
                    	            alt14=1;
                    	        }


                    	        switch (alt14) {
                    	    	case 1 :
                    	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    	    {
                    	    	    LT26=(Token)match(input,LT,FOLLOW_LT_in_formalParameterList219); if (state.failed) return retval;

                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop14;
                    	        }
                    	    } while (true);

                    	    char_literal27=(Token)match(input,33,FOLLOW_33_in_formalParameterList223); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal27_tree = (Object)adaptor.create(char_literal27);
                    	    adaptor.addChild(root_0, char_literal27_tree);
                    	    }
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:57:41: ( LT )*
                    	    loop15:
                    	    do {
                    	        int alt15=2;
                    	        int LA15_0 = input.LA(1);

                    	        if ( (LA15_0==LT) ) {
                    	            alt15=1;
                    	        }


                    	        switch (alt15) {
                    	    	case 1 :
                    	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    	    {
                    	    	    LT28=(Token)match(input,LT,FOLLOW_LT_in_formalParameterList225); if (state.failed) return retval;

                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop15;
                    	        }
                    	    } while (true);

                    	    t2=(Token)match(input,Identifier,FOLLOW_Identifier_in_formalParameterList231); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    t2_tree = (Object)adaptor.create(t2);
                    	    adaptor.addChild(root_0, t2_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);


                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:57:64: ( LT )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==LT) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT29=(Token)match(input,LT,FOLLOW_LT_in_formalParameterList237); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            char_literal30=(Token)match(input,34,FOLLOW_34_in_formalParameterList241); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal30_tree = (Object)adaptor.create(char_literal30);
            adaptor.addChild(root_0, char_literal30_tree);
            }
            if ( state.backtracking==0 ) {
               methods.add("formalParameterList:" + (t1!=null?t1.getText():null) + ":and following:" + t2);
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
            if ( state.backtracking>0 ) { memoize(input, 6, formalParameterList_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "formalParameterList"

    public static class functionBody_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "functionBody"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:61:1: functionBody : '{' ( LT )* sourceElements ( LT )* '}' ;
    public final JavaScriptParser.functionBody_return functionBody() throws RecognitionException {
        JavaScriptParser.functionBody_return retval = new JavaScriptParser.functionBody_return();
        retval.start = input.LT(1);
        int functionBody_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal31=null;
        Token LT32=null;
        Token LT34=null;
        Token char_literal35=null;
        JavaScriptParser.sourceElements_return sourceElements33 = null;


        Object char_literal31_tree=null;
        Object LT32_tree=null;
        Object LT34_tree=null;
        Object char_literal35_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:62:3: ( '{' ( LT )* sourceElements ( LT )* '}' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:62:5: '{' ( LT )* sourceElements ( LT )* '}'
            {
            root_0 = (Object)adaptor.nil();

            char_literal31=(Token)match(input,35,FOLLOW_35_in_functionBody258); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal31_tree = (Object)adaptor.create(char_literal31);
            adaptor.addChild(root_0, char_literal31_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:62:11: ( LT )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==LT) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT32=(Token)match(input,LT,FOLLOW_LT_in_functionBody260); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            pushFollow(FOLLOW_sourceElements_in_functionBody264);
            sourceElements33=sourceElements();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, sourceElements33.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:62:31: ( LT )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==LT) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT34=(Token)match(input,LT,FOLLOW_LT_in_functionBody266); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            char_literal35=(Token)match(input,36,FOLLOW_36_in_functionBody270); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal35_tree = (Object)adaptor.create(char_literal35);
            adaptor.addChild(root_0, char_literal35_tree);
            }
            if ( state.backtracking==0 ) {
               methods.add("Got function Body");
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
            if ( state.backtracking>0 ) { memoize(input, 7, functionBody_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "functionBody"

    public static class statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:67:1: statement : ( statementBlock | variableStatement | emptyStatement | expressionStatement | ifStatement | iterationStatement | continueStatement | breakStatement | returnStatement | withStatement | labelledStatement | switchStatement | throwStatement | tryStatement );
    public final JavaScriptParser.statement_return statement() throws RecognitionException {
        JavaScriptParser.statement_return retval = new JavaScriptParser.statement_return();
        retval.start = input.LT(1);
        int statement_StartIndex = input.index();
        Object root_0 = null;

        JavaScriptParser.statementBlock_return statementBlock36 = null;

        JavaScriptParser.variableStatement_return variableStatement37 = null;

        JavaScriptParser.emptyStatement_return emptyStatement38 = null;

        JavaScriptParser.expressionStatement_return expressionStatement39 = null;

        JavaScriptParser.ifStatement_return ifStatement40 = null;

        JavaScriptParser.iterationStatement_return iterationStatement41 = null;

        JavaScriptParser.continueStatement_return continueStatement42 = null;

        JavaScriptParser.breakStatement_return breakStatement43 = null;

        JavaScriptParser.returnStatement_return returnStatement44 = null;

        JavaScriptParser.withStatement_return withStatement45 = null;

        JavaScriptParser.labelledStatement_return labelledStatement46 = null;

        JavaScriptParser.switchStatement_return switchStatement47 = null;

        JavaScriptParser.throwStatement_return throwStatement48 = null;

        JavaScriptParser.tryStatement_return tryStatement49 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:68:3: ( statementBlock | variableStatement | emptyStatement | expressionStatement | ifStatement | iterationStatement | continueStatement | breakStatement | returnStatement | withStatement | labelledStatement | switchStatement | throwStatement | tryStatement )
            int alt21=14;
            alt21 = dfa21.predict(input);
            switch (alt21) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:68:5: statementBlock
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_statementBlock_in_statement288);
                    statementBlock36=statementBlock();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statementBlock36.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:69:5: variableStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_variableStatement_in_statement294);
                    variableStatement37=variableStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, variableStatement37.getTree());

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:70:5: emptyStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_emptyStatement_in_statement300);
                    emptyStatement38=emptyStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, emptyStatement38.getTree());

                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:71:5: expressionStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_expressionStatement_in_statement306);
                    expressionStatement39=expressionStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expressionStatement39.getTree());

                    }
                    break;
                case 5 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:72:5: ifStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ifStatement_in_statement312);
                    ifStatement40=ifStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ifStatement40.getTree());

                    }
                    break;
                case 6 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:73:5: iterationStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_iterationStatement_in_statement318);
                    iterationStatement41=iterationStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, iterationStatement41.getTree());

                    }
                    break;
                case 7 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:74:5: continueStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_continueStatement_in_statement324);
                    continueStatement42=continueStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, continueStatement42.getTree());

                    }
                    break;
                case 8 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:75:5: breakStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_breakStatement_in_statement330);
                    breakStatement43=breakStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, breakStatement43.getTree());

                    }
                    break;
                case 9 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:76:5: returnStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_returnStatement_in_statement336);
                    returnStatement44=returnStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, returnStatement44.getTree());

                    }
                    break;
                case 10 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:77:5: withStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_withStatement_in_statement342);
                    withStatement45=withStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, withStatement45.getTree());

                    }
                    break;
                case 11 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:78:5: labelledStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_labelledStatement_in_statement348);
                    labelledStatement46=labelledStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, labelledStatement46.getTree());

                    }
                    break;
                case 12 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:79:5: switchStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_switchStatement_in_statement354);
                    switchStatement47=switchStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, switchStatement47.getTree());

                    }
                    break;
                case 13 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:80:5: throwStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_throwStatement_in_statement360);
                    throwStatement48=throwStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, throwStatement48.getTree());

                    }
                    break;
                case 14 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:81:5: tryStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_tryStatement_in_statement366);
                    tryStatement49=tryStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, tryStatement49.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 8, statement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "statement"

    public static class statementBlock_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statementBlock"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:84:1: statementBlock : '{' ( LT )* ( statementList )? ( LT )* '}' ;
    public final JavaScriptParser.statementBlock_return statementBlock() throws RecognitionException {
        JavaScriptParser.statementBlock_return retval = new JavaScriptParser.statementBlock_return();
        retval.start = input.LT(1);
        int statementBlock_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal50=null;
        Token LT51=null;
        Token LT53=null;
        Token char_literal54=null;
        JavaScriptParser.statementList_return statementList52 = null;


        Object char_literal50_tree=null;
        Object LT51_tree=null;
        Object LT53_tree=null;
        Object char_literal54_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:85:3: ( '{' ( LT )* ( statementList )? ( LT )* '}' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:85:5: '{' ( LT )* ( statementList )? ( LT )* '}'
            {
            root_0 = (Object)adaptor.nil();

            char_literal50=(Token)match(input,35,FOLLOW_35_in_statementBlock381); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal50_tree = (Object)adaptor.create(char_literal50);
            adaptor.addChild(root_0, char_literal50_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:85:11: ( LT )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==LT) ) {
                    int LA22_2 = input.LA(2);

                    if ( (synpred34_JavaScript()) ) {
                        alt22=1;
                    }


                }


                switch (alt22) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT51=(Token)match(input,LT,FOLLOW_LT_in_statementBlock383); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:85:14: ( statementList )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( ((LA23_0>=Identifier && LA23_0<=NumericLiteral)||(LA23_0>=31 && LA23_0<=32)||LA23_0==35||(LA23_0>=37 && LA23_0<=38)||LA23_0==40||(LA23_0>=42 && LA23_0<=44)||(LA23_0>=46 && LA23_0<=49)||LA23_0==51||(LA23_0>=54 && LA23_0<=55)||(LA23_0>=58 && LA23_0<=59)||(LA23_0>=91 && LA23_0<=92)||(LA23_0>=96 && LA23_0<=106)) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: statementList
                    {
                    pushFollow(FOLLOW_statementList_in_statementBlock387);
                    statementList52=statementList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statementList52.getTree());

                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:85:31: ( LT )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==LT) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT53=(Token)match(input,LT,FOLLOW_LT_in_statementBlock390); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            char_literal54=(Token)match(input,36,FOLLOW_36_in_statementBlock394); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal54_tree = (Object)adaptor.create(char_literal54);
            adaptor.addChild(root_0, char_literal54_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 9, statementBlock_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "statementBlock"

    public static class statementList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statementList"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:88:1: statementList : statement ( ( LT )* statement )* ;
    public final JavaScriptParser.statementList_return statementList() throws RecognitionException {
        JavaScriptParser.statementList_return retval = new JavaScriptParser.statementList_return();
        retval.start = input.LT(1);
        int statementList_StartIndex = input.index();
        Object root_0 = null;

        Token LT56=null;
        JavaScriptParser.statement_return statement55 = null;

        JavaScriptParser.statement_return statement57 = null;


        Object LT56_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:89:3: ( statement ( ( LT )* statement )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:89:5: statement ( ( LT )* statement )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_statement_in_statementList409);
            statement55=statement();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, statement55.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:89:15: ( ( LT )* statement )*
            loop26:
            do {
                int alt26=2;
                alt26 = dfa26.predict(input);
                switch (alt26) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:89:16: ( LT )* statement
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:89:18: ( LT )*
            	    loop25:
            	    do {
            	        int alt25=2;
            	        int LA25_0 = input.LA(1);

            	        if ( (LA25_0==LT) ) {
            	            alt25=1;
            	        }


            	        switch (alt25) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT56=(Token)match(input,LT,FOLLOW_LT_in_statementList412); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop25;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_statement_in_statementList416);
            	    statement57=statement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement57.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 10, statementList_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "statementList"

    public static class variableStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "variableStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:92:1: variableStatement : 'var' ( LT )* variableDeclarationList ( LT | ';' ) ;
    public final JavaScriptParser.variableStatement_return variableStatement() throws RecognitionException {
        JavaScriptParser.variableStatement_return retval = new JavaScriptParser.variableStatement_return();
        retval.start = input.LT(1);
        int variableStatement_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal58=null;
        Token LT59=null;
        Token set61=null;
        JavaScriptParser.variableDeclarationList_return variableDeclarationList60 = null;


        Object string_literal58_tree=null;
        Object LT59_tree=null;
        Object set61_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:93:3: ( 'var' ( LT )* variableDeclarationList ( LT | ';' ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:93:5: 'var' ( LT )* variableDeclarationList ( LT | ';' )
            {
            root_0 = (Object)adaptor.nil();

            string_literal58=(Token)match(input,37,FOLLOW_37_in_variableStatement433); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal58_tree = (Object)adaptor.create(string_literal58);
            adaptor.addChild(root_0, string_literal58_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:93:13: ( LT )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==LT) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT59=(Token)match(input,LT,FOLLOW_LT_in_variableStatement435); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

            pushFollow(FOLLOW_variableDeclarationList_in_variableStatement439);
            variableDeclarationList60=variableDeclarationList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclarationList60.getTree());
            set61=(Token)input.LT(1);
            if ( input.LA(1)==LT||input.LA(1)==38 ) {
                input.consume();
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
            if ( state.backtracking>0 ) { memoize(input, 11, variableStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "variableStatement"

    public static class variableDeclarationList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "variableDeclarationList"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:96:1: variableDeclarationList : variableDeclaration ( ( LT )* ',' ( LT )* variableDeclaration )* ;
    public final JavaScriptParser.variableDeclarationList_return variableDeclarationList() throws RecognitionException {
        JavaScriptParser.variableDeclarationList_return retval = new JavaScriptParser.variableDeclarationList_return();
        retval.start = input.LT(1);
        int variableDeclarationList_StartIndex = input.index();
        Object root_0 = null;

        Token LT63=null;
        Token char_literal64=null;
        Token LT65=null;
        JavaScriptParser.variableDeclaration_return variableDeclaration62 = null;

        JavaScriptParser.variableDeclaration_return variableDeclaration66 = null;


        Object LT63_tree=null;
        Object char_literal64_tree=null;
        Object LT65_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:97:3: ( variableDeclaration ( ( LT )* ',' ( LT )* variableDeclaration )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:97:5: variableDeclaration ( ( LT )* ',' ( LT )* variableDeclaration )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_variableDeclaration_in_variableDeclarationList463);
            variableDeclaration62=variableDeclaration();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclaration62.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:97:25: ( ( LT )* ',' ( LT )* variableDeclaration )*
            loop30:
            do {
                int alt30=2;
                alt30 = dfa30.predict(input);
                switch (alt30) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:97:26: ( LT )* ',' ( LT )* variableDeclaration
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:97:28: ( LT )*
            	    loop28:
            	    do {
            	        int alt28=2;
            	        int LA28_0 = input.LA(1);

            	        if ( (LA28_0==LT) ) {
            	            alt28=1;
            	        }


            	        switch (alt28) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT63=(Token)match(input,LT,FOLLOW_LT_in_variableDeclarationList466); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop28;
            	        }
            	    } while (true);

            	    char_literal64=(Token)match(input,33,FOLLOW_33_in_variableDeclarationList470); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal64_tree = (Object)adaptor.create(char_literal64);
            	    adaptor.addChild(root_0, char_literal64_tree);
            	    }
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:97:37: ( LT )*
            	    loop29:
            	    do {
            	        int alt29=2;
            	        int LA29_0 = input.LA(1);

            	        if ( (LA29_0==LT) ) {
            	            alt29=1;
            	        }


            	        switch (alt29) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT65=(Token)match(input,LT,FOLLOW_LT_in_variableDeclarationList472); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop29;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_variableDeclaration_in_variableDeclarationList476);
            	    variableDeclaration66=variableDeclaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclaration66.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 12, variableDeclarationList_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "variableDeclarationList"

    public static class variableDeclarationListNoIn_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "variableDeclarationListNoIn"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:100:1: variableDeclarationListNoIn : variableDeclarationNoIn ( ( LT )* ',' ( LT )* variableDeclarationNoIn )* ;
    public final JavaScriptParser.variableDeclarationListNoIn_return variableDeclarationListNoIn() throws RecognitionException {
        JavaScriptParser.variableDeclarationListNoIn_return retval = new JavaScriptParser.variableDeclarationListNoIn_return();
        retval.start = input.LT(1);
        int variableDeclarationListNoIn_StartIndex = input.index();
        Object root_0 = null;

        Token LT68=null;
        Token char_literal69=null;
        Token LT70=null;
        JavaScriptParser.variableDeclarationNoIn_return variableDeclarationNoIn67 = null;

        JavaScriptParser.variableDeclarationNoIn_return variableDeclarationNoIn71 = null;


        Object LT68_tree=null;
        Object char_literal69_tree=null;
        Object LT70_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:101:3: ( variableDeclarationNoIn ( ( LT )* ',' ( LT )* variableDeclarationNoIn )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:101:5: variableDeclarationNoIn ( ( LT )* ',' ( LT )* variableDeclarationNoIn )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_variableDeclarationNoIn_in_variableDeclarationListNoIn493);
            variableDeclarationNoIn67=variableDeclarationNoIn();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclarationNoIn67.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:101:29: ( ( LT )* ',' ( LT )* variableDeclarationNoIn )*
            loop33:
            do {
                int alt33=2;
                alt33 = dfa33.predict(input);
                switch (alt33) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:101:30: ( LT )* ',' ( LT )* variableDeclarationNoIn
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:101:32: ( LT )*
            	    loop31:
            	    do {
            	        int alt31=2;
            	        int LA31_0 = input.LA(1);

            	        if ( (LA31_0==LT) ) {
            	            alt31=1;
            	        }


            	        switch (alt31) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT68=(Token)match(input,LT,FOLLOW_LT_in_variableDeclarationListNoIn496); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop31;
            	        }
            	    } while (true);

            	    char_literal69=(Token)match(input,33,FOLLOW_33_in_variableDeclarationListNoIn500); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal69_tree = (Object)adaptor.create(char_literal69);
            	    adaptor.addChild(root_0, char_literal69_tree);
            	    }
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:101:41: ( LT )*
            	    loop32:
            	    do {
            	        int alt32=2;
            	        int LA32_0 = input.LA(1);

            	        if ( (LA32_0==LT) ) {
            	            alt32=1;
            	        }


            	        switch (alt32) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT70=(Token)match(input,LT,FOLLOW_LT_in_variableDeclarationListNoIn502); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop32;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_variableDeclarationNoIn_in_variableDeclarationListNoIn506);
            	    variableDeclarationNoIn71=variableDeclarationNoIn();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclarationNoIn71.getTree());

            	    }
            	    break;

            	default :
            	    break loop33;
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
            if ( state.backtracking>0 ) { memoize(input, 13, variableDeclarationListNoIn_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "variableDeclarationListNoIn"

    public static class variableDeclaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "variableDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:104:1: variableDeclaration : Identifier ( LT )* ( initialiser )? ;
    public final JavaScriptParser.variableDeclaration_return variableDeclaration() throws RecognitionException {
        JavaScriptParser.variableDeclaration_return retval = new JavaScriptParser.variableDeclaration_return();
        retval.start = input.LT(1);
        int variableDeclaration_StartIndex = input.index();
        Object root_0 = null;

        Token Identifier72=null;
        Token LT73=null;
        JavaScriptParser.initialiser_return initialiser74 = null;


        Object Identifier72_tree=null;
        Object LT73_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:105:3: ( Identifier ( LT )* ( initialiser )? )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:105:5: Identifier ( LT )* ( initialiser )?
            {
            root_0 = (Object)adaptor.nil();

            Identifier72=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration523); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier72_tree = (Object)adaptor.create(Identifier72);
            adaptor.addChild(root_0, Identifier72_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:105:18: ( LT )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==LT) ) {
                    int LA34_2 = input.LA(2);

                    if ( (synpred47_JavaScript()) ) {
                        alt34=1;
                    }


                }


                switch (alt34) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT73=(Token)match(input,LT,FOLLOW_LT_in_variableDeclaration525); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:105:21: ( initialiser )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==39) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: initialiser
                    {
                    pushFollow(FOLLOW_initialiser_in_variableDeclaration529);
                    initialiser74=initialiser();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, initialiser74.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 14, variableDeclaration_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "variableDeclaration"

    public static class variableDeclarationNoIn_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "variableDeclarationNoIn"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:108:1: variableDeclarationNoIn : Identifier ( LT )* ( initialiserNoIn )? ;
    public final JavaScriptParser.variableDeclarationNoIn_return variableDeclarationNoIn() throws RecognitionException {
        JavaScriptParser.variableDeclarationNoIn_return retval = new JavaScriptParser.variableDeclarationNoIn_return();
        retval.start = input.LT(1);
        int variableDeclarationNoIn_StartIndex = input.index();
        Object root_0 = null;

        Token Identifier75=null;
        Token LT76=null;
        JavaScriptParser.initialiserNoIn_return initialiserNoIn77 = null;


        Object Identifier75_tree=null;
        Object LT76_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:109:3: ( Identifier ( LT )* ( initialiserNoIn )? )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:109:5: Identifier ( LT )* ( initialiserNoIn )?
            {
            root_0 = (Object)adaptor.nil();

            Identifier75=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclarationNoIn545); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier75_tree = (Object)adaptor.create(Identifier75);
            adaptor.addChild(root_0, Identifier75_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:109:18: ( LT )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==LT) ) {
                    int LA36_2 = input.LA(2);

                    if ( (synpred49_JavaScript()) ) {
                        alt36=1;
                    }


                }


                switch (alt36) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT76=(Token)match(input,LT,FOLLOW_LT_in_variableDeclarationNoIn547); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:109:21: ( initialiserNoIn )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==39) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: initialiserNoIn
                    {
                    pushFollow(FOLLOW_initialiserNoIn_in_variableDeclarationNoIn551);
                    initialiserNoIn77=initialiserNoIn();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, initialiserNoIn77.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 15, variableDeclarationNoIn_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "variableDeclarationNoIn"

    public static class initialiser_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "initialiser"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:112:1: initialiser : '=' ( LT )* assignmentExpression ;
    public final JavaScriptParser.initialiser_return initialiser() throws RecognitionException {
        JavaScriptParser.initialiser_return retval = new JavaScriptParser.initialiser_return();
        retval.start = input.LT(1);
        int initialiser_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal78=null;
        Token LT79=null;
        JavaScriptParser.assignmentExpression_return assignmentExpression80 = null;


        Object char_literal78_tree=null;
        Object LT79_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:113:3: ( '=' ( LT )* assignmentExpression )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:113:5: '=' ( LT )* assignmentExpression
            {
            root_0 = (Object)adaptor.nil();

            char_literal78=(Token)match(input,39,FOLLOW_39_in_initialiser567); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal78_tree = (Object)adaptor.create(char_literal78);
            adaptor.addChild(root_0, char_literal78_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:113:11: ( LT )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==LT) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT79=(Token)match(input,LT,FOLLOW_LT_in_initialiser569); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);

            pushFollow(FOLLOW_assignmentExpression_in_initialiser573);
            assignmentExpression80=assignmentExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpression80.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 16, initialiser_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "initialiser"

    public static class initialiserNoIn_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "initialiserNoIn"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:116:1: initialiserNoIn : '=' ( LT )* assignmentExpressionNoIn ;
    public final JavaScriptParser.initialiserNoIn_return initialiserNoIn() throws RecognitionException {
        JavaScriptParser.initialiserNoIn_return retval = new JavaScriptParser.initialiserNoIn_return();
        retval.start = input.LT(1);
        int initialiserNoIn_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal81=null;
        Token LT82=null;
        JavaScriptParser.assignmentExpressionNoIn_return assignmentExpressionNoIn83 = null;


        Object char_literal81_tree=null;
        Object LT82_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:117:3: ( '=' ( LT )* assignmentExpressionNoIn )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:117:5: '=' ( LT )* assignmentExpressionNoIn
            {
            root_0 = (Object)adaptor.nil();

            char_literal81=(Token)match(input,39,FOLLOW_39_in_initialiserNoIn588); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal81_tree = (Object)adaptor.create(char_literal81);
            adaptor.addChild(root_0, char_literal81_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:117:11: ( LT )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==LT) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT82=(Token)match(input,LT,FOLLOW_LT_in_initialiserNoIn590); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);

            pushFollow(FOLLOW_assignmentExpressionNoIn_in_initialiserNoIn594);
            assignmentExpressionNoIn83=assignmentExpressionNoIn();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpressionNoIn83.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 17, initialiserNoIn_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "initialiserNoIn"

    public static class emptyStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "emptyStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:120:1: emptyStatement : ';' ;
    public final JavaScriptParser.emptyStatement_return emptyStatement() throws RecognitionException {
        JavaScriptParser.emptyStatement_return retval = new JavaScriptParser.emptyStatement_return();
        retval.start = input.LT(1);
        int emptyStatement_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal84=null;

        Object char_literal84_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:121:3: ( ';' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:121:5: ';'
            {
            root_0 = (Object)adaptor.nil();

            char_literal84=(Token)match(input,38,FOLLOW_38_in_emptyStatement609); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal84_tree = (Object)adaptor.create(char_literal84);
            adaptor.addChild(root_0, char_literal84_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 18, emptyStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "emptyStatement"

    public static class expressionStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expressionStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:124:1: expressionStatement : expression ( LT | ';' ) ;
    public final JavaScriptParser.expressionStatement_return expressionStatement() throws RecognitionException {
        JavaScriptParser.expressionStatement_return retval = new JavaScriptParser.expressionStatement_return();
        retval.start = input.LT(1);
        int expressionStatement_StartIndex = input.index();
        Object root_0 = null;

        Token set86=null;
        JavaScriptParser.expression_return expression85 = null;


        Object set86_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:125:3: ( expression ( LT | ';' ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:125:5: expression ( LT | ';' )
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_expression_in_expressionStatement624);
            expression85=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression85.getTree());
            set86=(Token)input.LT(1);
            if ( input.LA(1)==LT||input.LA(1)==38 ) {
                input.consume();
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
            if ( state.backtracking>0 ) { memoize(input, 19, expressionStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "expressionStatement"

    public static class ifStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ifStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:128:1: ifStatement : 'if' ( LT )* '(' ( LT )* expression ( LT )* ')' ( LT )* statement ( ( LT )* 'else' ( LT )* statement )? ;
    public final JavaScriptParser.ifStatement_return ifStatement() throws RecognitionException {
        JavaScriptParser.ifStatement_return retval = new JavaScriptParser.ifStatement_return();
        retval.start = input.LT(1);
        int ifStatement_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal87=null;
        Token LT88=null;
        Token char_literal89=null;
        Token LT90=null;
        Token LT92=null;
        Token char_literal93=null;
        Token LT94=null;
        Token LT96=null;
        Token string_literal97=null;
        Token LT98=null;
        JavaScriptParser.expression_return expression91 = null;

        JavaScriptParser.statement_return statement95 = null;

        JavaScriptParser.statement_return statement99 = null;


        Object string_literal87_tree=null;
        Object LT88_tree=null;
        Object char_literal89_tree=null;
        Object LT90_tree=null;
        Object LT92_tree=null;
        Object char_literal93_tree=null;
        Object LT94_tree=null;
        Object LT96_tree=null;
        Object string_literal97_tree=null;
        Object LT98_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:129:3: ( 'if' ( LT )* '(' ( LT )* expression ( LT )* ')' ( LT )* statement ( ( LT )* 'else' ( LT )* statement )? )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:129:5: 'if' ( LT )* '(' ( LT )* expression ( LT )* ')' ( LT )* statement ( ( LT )* 'else' ( LT )* statement )?
            {
            root_0 = (Object)adaptor.nil();

            string_literal87=(Token)match(input,40,FOLLOW_40_in_ifStatement648); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal87_tree = (Object)adaptor.create(string_literal87);
            adaptor.addChild(root_0, string_literal87_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:129:12: ( LT )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==LT) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT88=(Token)match(input,LT,FOLLOW_LT_in_ifStatement650); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);

            char_literal89=(Token)match(input,32,FOLLOW_32_in_ifStatement654); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal89_tree = (Object)adaptor.create(char_literal89);
            adaptor.addChild(root_0, char_literal89_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:129:21: ( LT )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==LT) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT90=(Token)match(input,LT,FOLLOW_LT_in_ifStatement656); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);

            pushFollow(FOLLOW_expression_in_ifStatement660);
            expression91=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression91.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:129:37: ( LT )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==LT) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT92=(Token)match(input,LT,FOLLOW_LT_in_ifStatement662); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);

            char_literal93=(Token)match(input,34,FOLLOW_34_in_ifStatement666); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal93_tree = (Object)adaptor.create(char_literal93);
            adaptor.addChild(root_0, char_literal93_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:129:46: ( LT )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==LT) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT94=(Token)match(input,LT,FOLLOW_LT_in_ifStatement668); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);

            pushFollow(FOLLOW_statement_in_ifStatement672);
            statement95=statement();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, statement95.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:129:59: ( ( LT )* 'else' ( LT )* statement )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==LT) ) {
                int LA46_1 = input.LA(2);

                if ( (synpred60_JavaScript()) ) {
                    alt46=1;
                }
            }
            else if ( (LA46_0==41) ) {
                int LA46_2 = input.LA(2);

                if ( (synpred60_JavaScript()) ) {
                    alt46=1;
                }
            }
            switch (alt46) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:129:60: ( LT )* 'else' ( LT )* statement
                    {
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:129:62: ( LT )*
                    loop44:
                    do {
                        int alt44=2;
                        int LA44_0 = input.LA(1);

                        if ( (LA44_0==LT) ) {
                            alt44=1;
                        }


                        switch (alt44) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT96=(Token)match(input,LT,FOLLOW_LT_in_ifStatement675); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop44;
                        }
                    } while (true);

                    string_literal97=(Token)match(input,41,FOLLOW_41_in_ifStatement679); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal97_tree = (Object)adaptor.create(string_literal97);
                    adaptor.addChild(root_0, string_literal97_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:129:74: ( LT )*
                    loop45:
                    do {
                        int alt45=2;
                        int LA45_0 = input.LA(1);

                        if ( (LA45_0==LT) ) {
                            alt45=1;
                        }


                        switch (alt45) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT98=(Token)match(input,LT,FOLLOW_LT_in_ifStatement681); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop45;
                        }
                    } while (true);

                    pushFollow(FOLLOW_statement_in_ifStatement685);
                    statement99=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement99.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 20, ifStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "ifStatement"

    public static class iterationStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "iterationStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:132:1: iterationStatement : ( doWhileStatement | whileStatement | forStatement | forInStatement );
    public final JavaScriptParser.iterationStatement_return iterationStatement() throws RecognitionException {
        JavaScriptParser.iterationStatement_return retval = new JavaScriptParser.iterationStatement_return();
        retval.start = input.LT(1);
        int iterationStatement_StartIndex = input.index();
        Object root_0 = null;

        JavaScriptParser.doWhileStatement_return doWhileStatement100 = null;

        JavaScriptParser.whileStatement_return whileStatement101 = null;

        JavaScriptParser.forStatement_return forStatement102 = null;

        JavaScriptParser.forInStatement_return forInStatement103 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:133:3: ( doWhileStatement | whileStatement | forStatement | forInStatement )
            int alt47=4;
            switch ( input.LA(1) ) {
            case 42:
                {
                alt47=1;
                }
                break;
            case 43:
                {
                alt47=2;
                }
                break;
            case 44:
                {
                int LA47_3 = input.LA(2);

                if ( (synpred63_JavaScript()) ) {
                    alt47=3;
                }
                else if ( (true) ) {
                    alt47=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 47, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;
            }

            switch (alt47) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:133:5: doWhileStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_doWhileStatement_in_iterationStatement702);
                    doWhileStatement100=doWhileStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, doWhileStatement100.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:134:5: whileStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_whileStatement_in_iterationStatement708);
                    whileStatement101=whileStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, whileStatement101.getTree());

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:135:5: forStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forStatement_in_iterationStatement714);
                    forStatement102=forStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, forStatement102.getTree());

                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:136:5: forInStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_forInStatement_in_iterationStatement720);
                    forInStatement103=forInStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, forInStatement103.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 21, iterationStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "iterationStatement"

    public static class doWhileStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "doWhileStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:139:1: doWhileStatement : 'do' ( LT )* statement ( LT )* 'while' ( LT )* '(' expression ')' ( LT | ';' ) ;
    public final JavaScriptParser.doWhileStatement_return doWhileStatement() throws RecognitionException {
        JavaScriptParser.doWhileStatement_return retval = new JavaScriptParser.doWhileStatement_return();
        retval.start = input.LT(1);
        int doWhileStatement_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal104=null;
        Token LT105=null;
        Token LT107=null;
        Token string_literal108=null;
        Token LT109=null;
        Token char_literal110=null;
        Token char_literal112=null;
        Token set113=null;
        JavaScriptParser.statement_return statement106 = null;

        JavaScriptParser.expression_return expression111 = null;


        Object string_literal104_tree=null;
        Object LT105_tree=null;
        Object LT107_tree=null;
        Object string_literal108_tree=null;
        Object LT109_tree=null;
        Object char_literal110_tree=null;
        Object char_literal112_tree=null;
        Object set113_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:140:3: ( 'do' ( LT )* statement ( LT )* 'while' ( LT )* '(' expression ')' ( LT | ';' ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:140:5: 'do' ( LT )* statement ( LT )* 'while' ( LT )* '(' expression ')' ( LT | ';' )
            {
            root_0 = (Object)adaptor.nil();

            string_literal104=(Token)match(input,42,FOLLOW_42_in_doWhileStatement735); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal104_tree = (Object)adaptor.create(string_literal104);
            adaptor.addChild(root_0, string_literal104_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:140:12: ( LT )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==LT) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT105=(Token)match(input,LT,FOLLOW_LT_in_doWhileStatement737); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);

            pushFollow(FOLLOW_statement_in_doWhileStatement741);
            statement106=statement();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, statement106.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:140:27: ( LT )*
            loop49:
            do {
                int alt49=2;
                int LA49_0 = input.LA(1);

                if ( (LA49_0==LT) ) {
                    alt49=1;
                }


                switch (alt49) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT107=(Token)match(input,LT,FOLLOW_LT_in_doWhileStatement743); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop49;
                }
            } while (true);

            string_literal108=(Token)match(input,43,FOLLOW_43_in_doWhileStatement747); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal108_tree = (Object)adaptor.create(string_literal108);
            adaptor.addChild(root_0, string_literal108_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:140:40: ( LT )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==LT) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT109=(Token)match(input,LT,FOLLOW_LT_in_doWhileStatement749); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop50;
                }
            } while (true);

            char_literal110=(Token)match(input,32,FOLLOW_32_in_doWhileStatement753); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal110_tree = (Object)adaptor.create(char_literal110);
            adaptor.addChild(root_0, char_literal110_tree);
            }
            pushFollow(FOLLOW_expression_in_doWhileStatement755);
            expression111=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression111.getTree());
            char_literal112=(Token)match(input,34,FOLLOW_34_in_doWhileStatement757); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal112_tree = (Object)adaptor.create(char_literal112);
            adaptor.addChild(root_0, char_literal112_tree);
            }
            set113=(Token)input.LT(1);
            if ( input.LA(1)==LT||input.LA(1)==38 ) {
                input.consume();
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
            if ( state.backtracking>0 ) { memoize(input, 22, doWhileStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "doWhileStatement"

    public static class whileStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "whileStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:143:1: whileStatement : 'while' ( LT )* '(' ( LT )* expression ( LT )* ')' ( LT )* statement ;
    public final JavaScriptParser.whileStatement_return whileStatement() throws RecognitionException {
        JavaScriptParser.whileStatement_return retval = new JavaScriptParser.whileStatement_return();
        retval.start = input.LT(1);
        int whileStatement_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal114=null;
        Token LT115=null;
        Token char_literal116=null;
        Token LT117=null;
        Token LT119=null;
        Token char_literal120=null;
        Token LT121=null;
        JavaScriptParser.expression_return expression118 = null;

        JavaScriptParser.statement_return statement122 = null;


        Object string_literal114_tree=null;
        Object LT115_tree=null;
        Object char_literal116_tree=null;
        Object LT117_tree=null;
        Object LT119_tree=null;
        Object char_literal120_tree=null;
        Object LT121_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:144:3: ( 'while' ( LT )* '(' ( LT )* expression ( LT )* ')' ( LT )* statement )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:144:5: 'while' ( LT )* '(' ( LT )* expression ( LT )* ')' ( LT )* statement
            {
            root_0 = (Object)adaptor.nil();

            string_literal114=(Token)match(input,43,FOLLOW_43_in_whileStatement781); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal114_tree = (Object)adaptor.create(string_literal114);
            adaptor.addChild(root_0, string_literal114_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:144:15: ( LT )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==LT) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT115=(Token)match(input,LT,FOLLOW_LT_in_whileStatement783); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop51;
                }
            } while (true);

            char_literal116=(Token)match(input,32,FOLLOW_32_in_whileStatement787); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal116_tree = (Object)adaptor.create(char_literal116);
            adaptor.addChild(root_0, char_literal116_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:144:24: ( LT )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==LT) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT117=(Token)match(input,LT,FOLLOW_LT_in_whileStatement789); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop52;
                }
            } while (true);

            pushFollow(FOLLOW_expression_in_whileStatement793);
            expression118=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression118.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:144:40: ( LT )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==LT) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT119=(Token)match(input,LT,FOLLOW_LT_in_whileStatement795); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop53;
                }
            } while (true);

            char_literal120=(Token)match(input,34,FOLLOW_34_in_whileStatement799); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal120_tree = (Object)adaptor.create(char_literal120);
            adaptor.addChild(root_0, char_literal120_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:144:49: ( LT )*
            loop54:
            do {
                int alt54=2;
                int LA54_0 = input.LA(1);

                if ( (LA54_0==LT) ) {
                    alt54=1;
                }


                switch (alt54) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT121=(Token)match(input,LT,FOLLOW_LT_in_whileStatement801); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop54;
                }
            } while (true);

            pushFollow(FOLLOW_statement_in_whileStatement805);
            statement122=statement();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, statement122.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 23, whileStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "whileStatement"

    public static class forStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "forStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:147:1: forStatement : 'for' ( LT )* '(' ( ( LT )* forStatementInitialiserPart )? ( LT )* ';' ( ( LT )* expression )? ( LT )* ';' ( ( LT )* expression )? ( LT )* ')' ( LT )* statement ;
    public final JavaScriptParser.forStatement_return forStatement() throws RecognitionException {
        JavaScriptParser.forStatement_return retval = new JavaScriptParser.forStatement_return();
        retval.start = input.LT(1);
        int forStatement_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal123=null;
        Token LT124=null;
        Token char_literal125=null;
        Token LT126=null;
        Token LT128=null;
        Token char_literal129=null;
        Token LT130=null;
        Token LT132=null;
        Token char_literal133=null;
        Token LT134=null;
        Token LT136=null;
        Token char_literal137=null;
        Token LT138=null;
        JavaScriptParser.forStatementInitialiserPart_return forStatementInitialiserPart127 = null;

        JavaScriptParser.expression_return expression131 = null;

        JavaScriptParser.expression_return expression135 = null;

        JavaScriptParser.statement_return statement139 = null;


        Object string_literal123_tree=null;
        Object LT124_tree=null;
        Object char_literal125_tree=null;
        Object LT126_tree=null;
        Object LT128_tree=null;
        Object char_literal129_tree=null;
        Object LT130_tree=null;
        Object LT132_tree=null;
        Object char_literal133_tree=null;
        Object LT134_tree=null;
        Object LT136_tree=null;
        Object char_literal137_tree=null;
        Object LT138_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:148:3: ( 'for' ( LT )* '(' ( ( LT )* forStatementInitialiserPart )? ( LT )* ';' ( ( LT )* expression )? ( LT )* ';' ( ( LT )* expression )? ( LT )* ')' ( LT )* statement )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:148:5: 'for' ( LT )* '(' ( ( LT )* forStatementInitialiserPart )? ( LT )* ';' ( ( LT )* expression )? ( LT )* ';' ( ( LT )* expression )? ( LT )* ')' ( LT )* statement
            {
            root_0 = (Object)adaptor.nil();

            string_literal123=(Token)match(input,44,FOLLOW_44_in_forStatement820); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal123_tree = (Object)adaptor.create(string_literal123);
            adaptor.addChild(root_0, string_literal123_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:148:13: ( LT )*
            loop55:
            do {
                int alt55=2;
                int LA55_0 = input.LA(1);

                if ( (LA55_0==LT) ) {
                    alt55=1;
                }


                switch (alt55) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT124=(Token)match(input,LT,FOLLOW_LT_in_forStatement822); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop55;
                }
            } while (true);

            char_literal125=(Token)match(input,32,FOLLOW_32_in_forStatement826); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal125_tree = (Object)adaptor.create(char_literal125);
            adaptor.addChild(root_0, char_literal125_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:148:20: ( ( LT )* forStatementInitialiserPart )?
            int alt57=2;
            alt57 = dfa57.predict(input);
            switch (alt57) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:148:21: ( LT )* forStatementInitialiserPart
                    {
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:148:23: ( LT )*
                    loop56:
                    do {
                        int alt56=2;
                        int LA56_0 = input.LA(1);

                        if ( (LA56_0==LT) ) {
                            alt56=1;
                        }


                        switch (alt56) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT126=(Token)match(input,LT,FOLLOW_LT_in_forStatement829); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop56;
                        }
                    } while (true);

                    pushFollow(FOLLOW_forStatementInitialiserPart_in_forStatement833);
                    forStatementInitialiserPart127=forStatementInitialiserPart();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, forStatementInitialiserPart127.getTree());

                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:148:58: ( LT )*
            loop58:
            do {
                int alt58=2;
                int LA58_0 = input.LA(1);

                if ( (LA58_0==LT) ) {
                    alt58=1;
                }


                switch (alt58) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT128=(Token)match(input,LT,FOLLOW_LT_in_forStatement837); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop58;
                }
            } while (true);

            char_literal129=(Token)match(input,38,FOLLOW_38_in_forStatement841); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal129_tree = (Object)adaptor.create(char_literal129);
            adaptor.addChild(root_0, char_literal129_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:148:65: ( ( LT )* expression )?
            int alt60=2;
            alt60 = dfa60.predict(input);
            switch (alt60) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:148:66: ( LT )* expression
                    {
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:148:68: ( LT )*
                    loop59:
                    do {
                        int alt59=2;
                        int LA59_0 = input.LA(1);

                        if ( (LA59_0==LT) ) {
                            alt59=1;
                        }


                        switch (alt59) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT130=(Token)match(input,LT,FOLLOW_LT_in_forStatement844); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop59;
                        }
                    } while (true);

                    pushFollow(FOLLOW_expression_in_forStatement848);
                    expression131=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression131.getTree());

                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:148:86: ( LT )*
            loop61:
            do {
                int alt61=2;
                int LA61_0 = input.LA(1);

                if ( (LA61_0==LT) ) {
                    alt61=1;
                }


                switch (alt61) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT132=(Token)match(input,LT,FOLLOW_LT_in_forStatement852); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop61;
                }
            } while (true);

            char_literal133=(Token)match(input,38,FOLLOW_38_in_forStatement856); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal133_tree = (Object)adaptor.create(char_literal133);
            adaptor.addChild(root_0, char_literal133_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:148:93: ( ( LT )* expression )?
            int alt63=2;
            alt63 = dfa63.predict(input);
            switch (alt63) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:148:94: ( LT )* expression
                    {
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:148:96: ( LT )*
                    loop62:
                    do {
                        int alt62=2;
                        int LA62_0 = input.LA(1);

                        if ( (LA62_0==LT) ) {
                            alt62=1;
                        }


                        switch (alt62) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT134=(Token)match(input,LT,FOLLOW_LT_in_forStatement859); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop62;
                        }
                    } while (true);

                    pushFollow(FOLLOW_expression_in_forStatement863);
                    expression135=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression135.getTree());

                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:148:114: ( LT )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==LT) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT136=(Token)match(input,LT,FOLLOW_LT_in_forStatement867); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop64;
                }
            } while (true);

            char_literal137=(Token)match(input,34,FOLLOW_34_in_forStatement871); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal137_tree = (Object)adaptor.create(char_literal137);
            adaptor.addChild(root_0, char_literal137_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:148:123: ( LT )*
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( (LA65_0==LT) ) {
                    alt65=1;
                }


                switch (alt65) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT138=(Token)match(input,LT,FOLLOW_LT_in_forStatement873); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop65;
                }
            } while (true);

            pushFollow(FOLLOW_statement_in_forStatement877);
            statement139=statement();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, statement139.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 24, forStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "forStatement"

    public static class forStatementInitialiserPart_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "forStatementInitialiserPart"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:151:1: forStatementInitialiserPart : ( expressionNoIn | 'var' ( LT )* variableDeclarationListNoIn );
    public final JavaScriptParser.forStatementInitialiserPart_return forStatementInitialiserPart() throws RecognitionException {
        JavaScriptParser.forStatementInitialiserPart_return retval = new JavaScriptParser.forStatementInitialiserPart_return();
        retval.start = input.LT(1);
        int forStatementInitialiserPart_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal141=null;
        Token LT142=null;
        JavaScriptParser.expressionNoIn_return expressionNoIn140 = null;

        JavaScriptParser.variableDeclarationListNoIn_return variableDeclarationListNoIn143 = null;


        Object string_literal141_tree=null;
        Object LT142_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:152:3: ( expressionNoIn | 'var' ( LT )* variableDeclarationListNoIn )
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( ((LA67_0>=Identifier && LA67_0<=NumericLiteral)||(LA67_0>=31 && LA67_0<=32)||LA67_0==35||(LA67_0>=58 && LA67_0<=59)||(LA67_0>=91 && LA67_0<=92)||(LA67_0>=96 && LA67_0<=106)) ) {
                alt67=1;
            }
            else if ( (LA67_0==37) ) {
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
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:152:5: expressionNoIn
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_expressionNoIn_in_forStatementInitialiserPart892);
                    expressionNoIn140=expressionNoIn();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expressionNoIn140.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:153:5: 'var' ( LT )* variableDeclarationListNoIn
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal141=(Token)match(input,37,FOLLOW_37_in_forStatementInitialiserPart898); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal141_tree = (Object)adaptor.create(string_literal141);
                    adaptor.addChild(root_0, string_literal141_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:153:13: ( LT )*
                    loop66:
                    do {
                        int alt66=2;
                        int LA66_0 = input.LA(1);

                        if ( (LA66_0==LT) ) {
                            alt66=1;
                        }


                        switch (alt66) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT142=(Token)match(input,LT,FOLLOW_LT_in_forStatementInitialiserPart900); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop66;
                        }
                    } while (true);

                    pushFollow(FOLLOW_variableDeclarationListNoIn_in_forStatementInitialiserPart904);
                    variableDeclarationListNoIn143=variableDeclarationListNoIn();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclarationListNoIn143.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 25, forStatementInitialiserPart_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "forStatementInitialiserPart"

    public static class forInStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "forInStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:156:1: forInStatement : 'for' ( LT )* '(' ( LT )* forInStatementInitialiserPart ( LT )* 'in' ( LT )* expression ( LT )* ')' ( LT )* statement ;
    public final JavaScriptParser.forInStatement_return forInStatement() throws RecognitionException {
        JavaScriptParser.forInStatement_return retval = new JavaScriptParser.forInStatement_return();
        retval.start = input.LT(1);
        int forInStatement_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal144=null;
        Token LT145=null;
        Token char_literal146=null;
        Token LT147=null;
        Token LT149=null;
        Token string_literal150=null;
        Token LT151=null;
        Token LT153=null;
        Token char_literal154=null;
        Token LT155=null;
        JavaScriptParser.forInStatementInitialiserPart_return forInStatementInitialiserPart148 = null;

        JavaScriptParser.expression_return expression152 = null;

        JavaScriptParser.statement_return statement156 = null;


        Object string_literal144_tree=null;
        Object LT145_tree=null;
        Object char_literal146_tree=null;
        Object LT147_tree=null;
        Object LT149_tree=null;
        Object string_literal150_tree=null;
        Object LT151_tree=null;
        Object LT153_tree=null;
        Object char_literal154_tree=null;
        Object LT155_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:157:3: ( 'for' ( LT )* '(' ( LT )* forInStatementInitialiserPart ( LT )* 'in' ( LT )* expression ( LT )* ')' ( LT )* statement )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:157:5: 'for' ( LT )* '(' ( LT )* forInStatementInitialiserPart ( LT )* 'in' ( LT )* expression ( LT )* ')' ( LT )* statement
            {
            root_0 = (Object)adaptor.nil();

            string_literal144=(Token)match(input,44,FOLLOW_44_in_forInStatement919); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal144_tree = (Object)adaptor.create(string_literal144);
            adaptor.addChild(root_0, string_literal144_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:157:13: ( LT )*
            loop68:
            do {
                int alt68=2;
                int LA68_0 = input.LA(1);

                if ( (LA68_0==LT) ) {
                    alt68=1;
                }


                switch (alt68) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT145=(Token)match(input,LT,FOLLOW_LT_in_forInStatement921); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop68;
                }
            } while (true);

            char_literal146=(Token)match(input,32,FOLLOW_32_in_forInStatement925); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal146_tree = (Object)adaptor.create(char_literal146);
            adaptor.addChild(root_0, char_literal146_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:157:22: ( LT )*
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( (LA69_0==LT) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT147=(Token)match(input,LT,FOLLOW_LT_in_forInStatement927); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop69;
                }
            } while (true);

            pushFollow(FOLLOW_forInStatementInitialiserPart_in_forInStatement931);
            forInStatementInitialiserPart148=forInStatementInitialiserPart();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, forInStatementInitialiserPart148.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:157:57: ( LT )*
            loop70:
            do {
                int alt70=2;
                int LA70_0 = input.LA(1);

                if ( (LA70_0==LT) ) {
                    alt70=1;
                }


                switch (alt70) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT149=(Token)match(input,LT,FOLLOW_LT_in_forInStatement933); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop70;
                }
            } while (true);

            string_literal150=(Token)match(input,45,FOLLOW_45_in_forInStatement937); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal150_tree = (Object)adaptor.create(string_literal150);
            adaptor.addChild(root_0, string_literal150_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:157:67: ( LT )*
            loop71:
            do {
                int alt71=2;
                int LA71_0 = input.LA(1);

                if ( (LA71_0==LT) ) {
                    alt71=1;
                }


                switch (alt71) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT151=(Token)match(input,LT,FOLLOW_LT_in_forInStatement939); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop71;
                }
            } while (true);

            pushFollow(FOLLOW_expression_in_forInStatement943);
            expression152=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression152.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:157:83: ( LT )*
            loop72:
            do {
                int alt72=2;
                int LA72_0 = input.LA(1);

                if ( (LA72_0==LT) ) {
                    alt72=1;
                }


                switch (alt72) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT153=(Token)match(input,LT,FOLLOW_LT_in_forInStatement945); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop72;
                }
            } while (true);

            char_literal154=(Token)match(input,34,FOLLOW_34_in_forInStatement949); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal154_tree = (Object)adaptor.create(char_literal154);
            adaptor.addChild(root_0, char_literal154_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:157:92: ( LT )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==LT) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT155=(Token)match(input,LT,FOLLOW_LT_in_forInStatement951); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop73;
                }
            } while (true);

            pushFollow(FOLLOW_statement_in_forInStatement955);
            statement156=statement();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, statement156.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 26, forInStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "forInStatement"

    public static class forInStatementInitialiserPart_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "forInStatementInitialiserPart"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:160:1: forInStatementInitialiserPart : ( leftHandSideExpression | 'var' ( LT )* variableDeclarationNoIn );
    public final JavaScriptParser.forInStatementInitialiserPart_return forInStatementInitialiserPart() throws RecognitionException {
        JavaScriptParser.forInStatementInitialiserPart_return retval = new JavaScriptParser.forInStatementInitialiserPart_return();
        retval.start = input.LT(1);
        int forInStatementInitialiserPart_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal158=null;
        Token LT159=null;
        JavaScriptParser.leftHandSideExpression_return leftHandSideExpression157 = null;

        JavaScriptParser.variableDeclarationNoIn_return variableDeclarationNoIn160 = null;


        Object string_literal158_tree=null;
        Object LT159_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:161:3: ( leftHandSideExpression | 'var' ( LT )* variableDeclarationNoIn )
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( ((LA75_0>=Identifier && LA75_0<=NumericLiteral)||(LA75_0>=31 && LA75_0<=32)||LA75_0==35||(LA75_0>=58 && LA75_0<=59)||(LA75_0>=103 && LA75_0<=106)) ) {
                alt75=1;
            }
            else if ( (LA75_0==37) ) {
                alt75=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 75, 0, input);

                throw nvae;
            }
            switch (alt75) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:161:5: leftHandSideExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_leftHandSideExpression_in_forInStatementInitialiserPart970);
                    leftHandSideExpression157=leftHandSideExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, leftHandSideExpression157.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:162:5: 'var' ( LT )* variableDeclarationNoIn
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal158=(Token)match(input,37,FOLLOW_37_in_forInStatementInitialiserPart976); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal158_tree = (Object)adaptor.create(string_literal158);
                    adaptor.addChild(root_0, string_literal158_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:162:13: ( LT )*
                    loop74:
                    do {
                        int alt74=2;
                        int LA74_0 = input.LA(1);

                        if ( (LA74_0==LT) ) {
                            alt74=1;
                        }


                        switch (alt74) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT159=(Token)match(input,LT,FOLLOW_LT_in_forInStatementInitialiserPart978); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop74;
                        }
                    } while (true);

                    pushFollow(FOLLOW_variableDeclarationNoIn_in_forInStatementInitialiserPart982);
                    variableDeclarationNoIn160=variableDeclarationNoIn();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclarationNoIn160.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 27, forInStatementInitialiserPart_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "forInStatementInitialiserPart"

    public static class continueStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "continueStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:165:1: continueStatement : 'continue' ( Identifier )? ( LT | ';' ) ;
    public final JavaScriptParser.continueStatement_return continueStatement() throws RecognitionException {
        JavaScriptParser.continueStatement_return retval = new JavaScriptParser.continueStatement_return();
        retval.start = input.LT(1);
        int continueStatement_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal161=null;
        Token Identifier162=null;
        Token set163=null;

        Object string_literal161_tree=null;
        Object Identifier162_tree=null;
        Object set163_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:166:3: ( 'continue' ( Identifier )? ( LT | ';' ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:166:5: 'continue' ( Identifier )? ( LT | ';' )
            {
            root_0 = (Object)adaptor.nil();

            string_literal161=(Token)match(input,46,FOLLOW_46_in_continueStatement995); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal161_tree = (Object)adaptor.create(string_literal161);
            adaptor.addChild(root_0, string_literal161_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:166:16: ( Identifier )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==Identifier) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: Identifier
                    {
                    Identifier162=(Token)match(input,Identifier,FOLLOW_Identifier_in_continueStatement997); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Identifier162_tree = (Object)adaptor.create(Identifier162);
                    adaptor.addChild(root_0, Identifier162_tree);
                    }

                    }
                    break;

            }

            set163=(Token)input.LT(1);
            if ( input.LA(1)==LT||input.LA(1)==38 ) {
                input.consume();
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
            if ( state.backtracking>0 ) { memoize(input, 28, continueStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "continueStatement"

    public static class breakStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "breakStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:169:1: breakStatement : 'break' ( Identifier )? ( LT | ';' ) ;
    public final JavaScriptParser.breakStatement_return breakStatement() throws RecognitionException {
        JavaScriptParser.breakStatement_return retval = new JavaScriptParser.breakStatement_return();
        retval.start = input.LT(1);
        int breakStatement_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal164=null;
        Token Identifier165=null;
        Token set166=null;

        Object string_literal164_tree=null;
        Object Identifier165_tree=null;
        Object set166_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:170:3: ( 'break' ( Identifier )? ( LT | ';' ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:170:5: 'break' ( Identifier )? ( LT | ';' )
            {
            root_0 = (Object)adaptor.nil();

            string_literal164=(Token)match(input,47,FOLLOW_47_in_breakStatement1020); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal164_tree = (Object)adaptor.create(string_literal164);
            adaptor.addChild(root_0, string_literal164_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:170:13: ( Identifier )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==Identifier) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: Identifier
                    {
                    Identifier165=(Token)match(input,Identifier,FOLLOW_Identifier_in_breakStatement1022); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Identifier165_tree = (Object)adaptor.create(Identifier165);
                    adaptor.addChild(root_0, Identifier165_tree);
                    }

                    }
                    break;

            }

            set166=(Token)input.LT(1);
            if ( input.LA(1)==LT||input.LA(1)==38 ) {
                input.consume();
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
            if ( state.backtracking>0 ) { memoize(input, 29, breakStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "breakStatement"

    public static class returnStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "returnStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:173:1: returnStatement : 'return' ( expression )? ( LT | ';' ) ;
    public final JavaScriptParser.returnStatement_return returnStatement() throws RecognitionException {
        JavaScriptParser.returnStatement_return retval = new JavaScriptParser.returnStatement_return();
        retval.start = input.LT(1);
        int returnStatement_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal167=null;
        Token set169=null;
        JavaScriptParser.expression_return expression168 = null;


        Object string_literal167_tree=null;
        Object set169_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:174:3: ( 'return' ( expression )? ( LT | ';' ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:174:5: 'return' ( expression )? ( LT | ';' )
            {
            root_0 = (Object)adaptor.nil();

            string_literal167=(Token)match(input,48,FOLLOW_48_in_returnStatement1045); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal167_tree = (Object)adaptor.create(string_literal167);
            adaptor.addChild(root_0, string_literal167_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:174:14: ( expression )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( ((LA78_0>=Identifier && LA78_0<=NumericLiteral)||(LA78_0>=31 && LA78_0<=32)||LA78_0==35||(LA78_0>=58 && LA78_0<=59)||(LA78_0>=91 && LA78_0<=92)||(LA78_0>=96 && LA78_0<=106)) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: expression
                    {
                    pushFollow(FOLLOW_expression_in_returnStatement1047);
                    expression168=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression168.getTree());

                    }
                    break;

            }

            set169=(Token)input.LT(1);
            if ( input.LA(1)==LT||input.LA(1)==38 ) {
                input.consume();
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
            if ( state.backtracking>0 ) { memoize(input, 30, returnStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "returnStatement"

    public static class withStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "withStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:177:1: withStatement : 'with' ( LT )* '(' ( LT )* expression ( LT )* ')' ( LT )* statement ;
    public final JavaScriptParser.withStatement_return withStatement() throws RecognitionException {
        JavaScriptParser.withStatement_return retval = new JavaScriptParser.withStatement_return();
        retval.start = input.LT(1);
        int withStatement_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal170=null;
        Token LT171=null;
        Token char_literal172=null;
        Token LT173=null;
        Token LT175=null;
        Token char_literal176=null;
        Token LT177=null;
        JavaScriptParser.expression_return expression174 = null;

        JavaScriptParser.statement_return statement178 = null;


        Object string_literal170_tree=null;
        Object LT171_tree=null;
        Object char_literal172_tree=null;
        Object LT173_tree=null;
        Object LT175_tree=null;
        Object char_literal176_tree=null;
        Object LT177_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:178:3: ( 'with' ( LT )* '(' ( LT )* expression ( LT )* ')' ( LT )* statement )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:178:5: 'with' ( LT )* '(' ( LT )* expression ( LT )* ')' ( LT )* statement
            {
            root_0 = (Object)adaptor.nil();

            string_literal170=(Token)match(input,49,FOLLOW_49_in_withStatement1072); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal170_tree = (Object)adaptor.create(string_literal170);
            adaptor.addChild(root_0, string_literal170_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:178:14: ( LT )*
            loop79:
            do {
                int alt79=2;
                int LA79_0 = input.LA(1);

                if ( (LA79_0==LT) ) {
                    alt79=1;
                }


                switch (alt79) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT171=(Token)match(input,LT,FOLLOW_LT_in_withStatement1074); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop79;
                }
            } while (true);

            char_literal172=(Token)match(input,32,FOLLOW_32_in_withStatement1078); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal172_tree = (Object)adaptor.create(char_literal172);
            adaptor.addChild(root_0, char_literal172_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:178:23: ( LT )*
            loop80:
            do {
                int alt80=2;
                int LA80_0 = input.LA(1);

                if ( (LA80_0==LT) ) {
                    alt80=1;
                }


                switch (alt80) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT173=(Token)match(input,LT,FOLLOW_LT_in_withStatement1080); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop80;
                }
            } while (true);

            pushFollow(FOLLOW_expression_in_withStatement1084);
            expression174=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression174.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:178:39: ( LT )*
            loop81:
            do {
                int alt81=2;
                int LA81_0 = input.LA(1);

                if ( (LA81_0==LT) ) {
                    alt81=1;
                }


                switch (alt81) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT175=(Token)match(input,LT,FOLLOW_LT_in_withStatement1086); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop81;
                }
            } while (true);

            char_literal176=(Token)match(input,34,FOLLOW_34_in_withStatement1090); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal176_tree = (Object)adaptor.create(char_literal176);
            adaptor.addChild(root_0, char_literal176_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:178:48: ( LT )*
            loop82:
            do {
                int alt82=2;
                int LA82_0 = input.LA(1);

                if ( (LA82_0==LT) ) {
                    alt82=1;
                }


                switch (alt82) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT177=(Token)match(input,LT,FOLLOW_LT_in_withStatement1092); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop82;
                }
            } while (true);

            pushFollow(FOLLOW_statement_in_withStatement1096);
            statement178=statement();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, statement178.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 31, withStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "withStatement"

    public static class labelledStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "labelledStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:181:1: labelledStatement : Identifier ( LT )* ':' ( LT )* statement ;
    public final JavaScriptParser.labelledStatement_return labelledStatement() throws RecognitionException {
        JavaScriptParser.labelledStatement_return retval = new JavaScriptParser.labelledStatement_return();
        retval.start = input.LT(1);
        int labelledStatement_StartIndex = input.index();
        Object root_0 = null;

        Token Identifier179=null;
        Token LT180=null;
        Token char_literal181=null;
        Token LT182=null;
        JavaScriptParser.statement_return statement183 = null;


        Object Identifier179_tree=null;
        Object LT180_tree=null;
        Object char_literal181_tree=null;
        Object LT182_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:182:3: ( Identifier ( LT )* ':' ( LT )* statement )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:182:5: Identifier ( LT )* ':' ( LT )* statement
            {
            root_0 = (Object)adaptor.nil();

            Identifier179=(Token)match(input,Identifier,FOLLOW_Identifier_in_labelledStatement1109); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier179_tree = (Object)adaptor.create(Identifier179);
            adaptor.addChild(root_0, Identifier179_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:182:18: ( LT )*
            loop83:
            do {
                int alt83=2;
                int LA83_0 = input.LA(1);

                if ( (LA83_0==LT) ) {
                    alt83=1;
                }


                switch (alt83) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT180=(Token)match(input,LT,FOLLOW_LT_in_labelledStatement1111); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop83;
                }
            } while (true);

            char_literal181=(Token)match(input,50,FOLLOW_50_in_labelledStatement1115); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal181_tree = (Object)adaptor.create(char_literal181);
            adaptor.addChild(root_0, char_literal181_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:182:27: ( LT )*
            loop84:
            do {
                int alt84=2;
                int LA84_0 = input.LA(1);

                if ( (LA84_0==LT) ) {
                    alt84=1;
                }


                switch (alt84) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT182=(Token)match(input,LT,FOLLOW_LT_in_labelledStatement1117); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop84;
                }
            } while (true);

            pushFollow(FOLLOW_statement_in_labelledStatement1121);
            statement183=statement();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, statement183.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 32, labelledStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "labelledStatement"

    public static class switchStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "switchStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:185:1: switchStatement : 'switch' ( LT )* '(' ( LT )* expression ( LT )* ')' ( LT )* caseBlock ;
    public final JavaScriptParser.switchStatement_return switchStatement() throws RecognitionException {
        JavaScriptParser.switchStatement_return retval = new JavaScriptParser.switchStatement_return();
        retval.start = input.LT(1);
        int switchStatement_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal184=null;
        Token LT185=null;
        Token char_literal186=null;
        Token LT187=null;
        Token LT189=null;
        Token char_literal190=null;
        Token LT191=null;
        JavaScriptParser.expression_return expression188 = null;

        JavaScriptParser.caseBlock_return caseBlock192 = null;


        Object string_literal184_tree=null;
        Object LT185_tree=null;
        Object char_literal186_tree=null;
        Object LT187_tree=null;
        Object LT189_tree=null;
        Object char_literal190_tree=null;
        Object LT191_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:186:3: ( 'switch' ( LT )* '(' ( LT )* expression ( LT )* ')' ( LT )* caseBlock )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:186:5: 'switch' ( LT )* '(' ( LT )* expression ( LT )* ')' ( LT )* caseBlock
            {
            root_0 = (Object)adaptor.nil();

            string_literal184=(Token)match(input,51,FOLLOW_51_in_switchStatement1136); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal184_tree = (Object)adaptor.create(string_literal184);
            adaptor.addChild(root_0, string_literal184_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:186:16: ( LT )*
            loop85:
            do {
                int alt85=2;
                int LA85_0 = input.LA(1);

                if ( (LA85_0==LT) ) {
                    alt85=1;
                }


                switch (alt85) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT185=(Token)match(input,LT,FOLLOW_LT_in_switchStatement1138); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop85;
                }
            } while (true);

            char_literal186=(Token)match(input,32,FOLLOW_32_in_switchStatement1142); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal186_tree = (Object)adaptor.create(char_literal186);
            adaptor.addChild(root_0, char_literal186_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:186:25: ( LT )*
            loop86:
            do {
                int alt86=2;
                int LA86_0 = input.LA(1);

                if ( (LA86_0==LT) ) {
                    alt86=1;
                }


                switch (alt86) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT187=(Token)match(input,LT,FOLLOW_LT_in_switchStatement1144); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop86;
                }
            } while (true);

            pushFollow(FOLLOW_expression_in_switchStatement1148);
            expression188=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression188.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:186:41: ( LT )*
            loop87:
            do {
                int alt87=2;
                int LA87_0 = input.LA(1);

                if ( (LA87_0==LT) ) {
                    alt87=1;
                }


                switch (alt87) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT189=(Token)match(input,LT,FOLLOW_LT_in_switchStatement1150); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop87;
                }
            } while (true);

            char_literal190=(Token)match(input,34,FOLLOW_34_in_switchStatement1154); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal190_tree = (Object)adaptor.create(char_literal190);
            adaptor.addChild(root_0, char_literal190_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:186:50: ( LT )*
            loop88:
            do {
                int alt88=2;
                int LA88_0 = input.LA(1);

                if ( (LA88_0==LT) ) {
                    alt88=1;
                }


                switch (alt88) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT191=(Token)match(input,LT,FOLLOW_LT_in_switchStatement1156); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop88;
                }
            } while (true);

            pushFollow(FOLLOW_caseBlock_in_switchStatement1160);
            caseBlock192=caseBlock();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, caseBlock192.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 33, switchStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "switchStatement"

    public static class caseBlock_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "caseBlock"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:189:1: caseBlock : '{' ( ( LT )* caseClause )* ( ( LT )* defaultClause ( ( LT )* caseClause )* )? ( LT )* '}' ;
    public final JavaScriptParser.caseBlock_return caseBlock() throws RecognitionException {
        JavaScriptParser.caseBlock_return retval = new JavaScriptParser.caseBlock_return();
        retval.start = input.LT(1);
        int caseBlock_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal193=null;
        Token LT194=null;
        Token LT196=null;
        Token LT198=null;
        Token LT200=null;
        Token char_literal201=null;
        JavaScriptParser.caseClause_return caseClause195 = null;

        JavaScriptParser.defaultClause_return defaultClause197 = null;

        JavaScriptParser.caseClause_return caseClause199 = null;


        Object char_literal193_tree=null;
        Object LT194_tree=null;
        Object LT196_tree=null;
        Object LT198_tree=null;
        Object LT200_tree=null;
        Object char_literal201_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:190:3: ( '{' ( ( LT )* caseClause )* ( ( LT )* defaultClause ( ( LT )* caseClause )* )? ( LT )* '}' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:190:5: '{' ( ( LT )* caseClause )* ( ( LT )* defaultClause ( ( LT )* caseClause )* )? ( LT )* '}'
            {
            root_0 = (Object)adaptor.nil();

            char_literal193=(Token)match(input,35,FOLLOW_35_in_caseBlock1175); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal193_tree = (Object)adaptor.create(char_literal193);
            adaptor.addChild(root_0, char_literal193_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:190:9: ( ( LT )* caseClause )*
            loop90:
            do {
                int alt90=2;
                alt90 = dfa90.predict(input);
                switch (alt90) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:190:10: ( LT )* caseClause
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:190:12: ( LT )*
            	    loop89:
            	    do {
            	        int alt89=2;
            	        int LA89_0 = input.LA(1);

            	        if ( (LA89_0==LT) ) {
            	            alt89=1;
            	        }


            	        switch (alt89) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT194=(Token)match(input,LT,FOLLOW_LT_in_caseBlock1178); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop89;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_caseClause_in_caseBlock1182);
            	    caseClause195=caseClause();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, caseClause195.getTree());

            	    }
            	    break;

            	default :
            	    break loop90;
                }
            } while (true);

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:190:28: ( ( LT )* defaultClause ( ( LT )* caseClause )* )?
            int alt94=2;
            alt94 = dfa94.predict(input);
            switch (alt94) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:190:29: ( LT )* defaultClause ( ( LT )* caseClause )*
                    {
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:190:31: ( LT )*
                    loop91:
                    do {
                        int alt91=2;
                        int LA91_0 = input.LA(1);

                        if ( (LA91_0==LT) ) {
                            alt91=1;
                        }


                        switch (alt91) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT196=(Token)match(input,LT,FOLLOW_LT_in_caseBlock1187); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop91;
                        }
                    } while (true);

                    pushFollow(FOLLOW_defaultClause_in_caseBlock1191);
                    defaultClause197=defaultClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, defaultClause197.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:190:48: ( ( LT )* caseClause )*
                    loop93:
                    do {
                        int alt93=2;
                        alt93 = dfa93.predict(input);
                        switch (alt93) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:190:49: ( LT )* caseClause
                    	    {
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:190:51: ( LT )*
                    	    loop92:
                    	    do {
                    	        int alt92=2;
                    	        int LA92_0 = input.LA(1);

                    	        if ( (LA92_0==LT) ) {
                    	            alt92=1;
                    	        }


                    	        switch (alt92) {
                    	    	case 1 :
                    	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    	    {
                    	    	    LT198=(Token)match(input,LT,FOLLOW_LT_in_caseBlock1194); if (state.failed) return retval;

                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop92;
                    	        }
                    	    } while (true);

                    	    pushFollow(FOLLOW_caseClause_in_caseBlock1198);
                    	    caseClause199=caseClause();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, caseClause199.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop93;
                        }
                    } while (true);


                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:190:71: ( LT )*
            loop95:
            do {
                int alt95=2;
                int LA95_0 = input.LA(1);

                if ( (LA95_0==LT) ) {
                    alt95=1;
                }


                switch (alt95) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT200=(Token)match(input,LT,FOLLOW_LT_in_caseBlock1204); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop95;
                }
            } while (true);

            char_literal201=(Token)match(input,36,FOLLOW_36_in_caseBlock1208); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal201_tree = (Object)adaptor.create(char_literal201);
            adaptor.addChild(root_0, char_literal201_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 34, caseBlock_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "caseBlock"

    public static class caseClause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "caseClause"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:193:1: caseClause : 'case' ( LT )* expression ( LT )* ':' ( LT )* ( statementList )? ;
    public final JavaScriptParser.caseClause_return caseClause() throws RecognitionException {
        JavaScriptParser.caseClause_return retval = new JavaScriptParser.caseClause_return();
        retval.start = input.LT(1);
        int caseClause_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal202=null;
        Token LT203=null;
        Token LT205=null;
        Token char_literal206=null;
        Token LT207=null;
        JavaScriptParser.expression_return expression204 = null;

        JavaScriptParser.statementList_return statementList208 = null;


        Object string_literal202_tree=null;
        Object LT203_tree=null;
        Object LT205_tree=null;
        Object char_literal206_tree=null;
        Object LT207_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:194:3: ( 'case' ( LT )* expression ( LT )* ':' ( LT )* ( statementList )? )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:194:5: 'case' ( LT )* expression ( LT )* ':' ( LT )* ( statementList )?
            {
            root_0 = (Object)adaptor.nil();

            string_literal202=(Token)match(input,52,FOLLOW_52_in_caseClause1221); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal202_tree = (Object)adaptor.create(string_literal202);
            adaptor.addChild(root_0, string_literal202_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:194:14: ( LT )*
            loop96:
            do {
                int alt96=2;
                int LA96_0 = input.LA(1);

                if ( (LA96_0==LT) ) {
                    alt96=1;
                }


                switch (alt96) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT203=(Token)match(input,LT,FOLLOW_LT_in_caseClause1223); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop96;
                }
            } while (true);

            pushFollow(FOLLOW_expression_in_caseClause1227);
            expression204=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression204.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:194:30: ( LT )*
            loop97:
            do {
                int alt97=2;
                int LA97_0 = input.LA(1);

                if ( (LA97_0==LT) ) {
                    alt97=1;
                }


                switch (alt97) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT205=(Token)match(input,LT,FOLLOW_LT_in_caseClause1229); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop97;
                }
            } while (true);

            char_literal206=(Token)match(input,50,FOLLOW_50_in_caseClause1233); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal206_tree = (Object)adaptor.create(char_literal206);
            adaptor.addChild(root_0, char_literal206_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:194:39: ( LT )*
            loop98:
            do {
                int alt98=2;
                int LA98_0 = input.LA(1);

                if ( (LA98_0==LT) ) {
                    int LA98_2 = input.LA(2);

                    if ( (synpred118_JavaScript()) ) {
                        alt98=1;
                    }


                }


                switch (alt98) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT207=(Token)match(input,LT,FOLLOW_LT_in_caseClause1235); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop98;
                }
            } while (true);

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:194:42: ( statementList )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( ((LA99_0>=Identifier && LA99_0<=NumericLiteral)||(LA99_0>=31 && LA99_0<=32)||LA99_0==35||(LA99_0>=37 && LA99_0<=38)||LA99_0==40||(LA99_0>=42 && LA99_0<=44)||(LA99_0>=46 && LA99_0<=49)||LA99_0==51||(LA99_0>=54 && LA99_0<=55)||(LA99_0>=58 && LA99_0<=59)||(LA99_0>=91 && LA99_0<=92)||(LA99_0>=96 && LA99_0<=106)) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: statementList
                    {
                    pushFollow(FOLLOW_statementList_in_caseClause1239);
                    statementList208=statementList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statementList208.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 35, caseClause_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "caseClause"

    public static class defaultClause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "defaultClause"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:197:1: defaultClause : 'default' ( LT )* ':' ( LT )* ( statementList )? ;
    public final JavaScriptParser.defaultClause_return defaultClause() throws RecognitionException {
        JavaScriptParser.defaultClause_return retval = new JavaScriptParser.defaultClause_return();
        retval.start = input.LT(1);
        int defaultClause_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal209=null;
        Token LT210=null;
        Token char_literal211=null;
        Token LT212=null;
        JavaScriptParser.statementList_return statementList213 = null;


        Object string_literal209_tree=null;
        Object LT210_tree=null;
        Object char_literal211_tree=null;
        Object LT212_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:198:3: ( 'default' ( LT )* ':' ( LT )* ( statementList )? )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:198:5: 'default' ( LT )* ':' ( LT )* ( statementList )?
            {
            root_0 = (Object)adaptor.nil();

            string_literal209=(Token)match(input,53,FOLLOW_53_in_defaultClause1255); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal209_tree = (Object)adaptor.create(string_literal209);
            adaptor.addChild(root_0, string_literal209_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:198:17: ( LT )*
            loop100:
            do {
                int alt100=2;
                int LA100_0 = input.LA(1);

                if ( (LA100_0==LT) ) {
                    alt100=1;
                }


                switch (alt100) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT210=(Token)match(input,LT,FOLLOW_LT_in_defaultClause1257); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop100;
                }
            } while (true);

            char_literal211=(Token)match(input,50,FOLLOW_50_in_defaultClause1261); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal211_tree = (Object)adaptor.create(char_literal211);
            adaptor.addChild(root_0, char_literal211_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:198:26: ( LT )*
            loop101:
            do {
                int alt101=2;
                int LA101_0 = input.LA(1);

                if ( (LA101_0==LT) ) {
                    int LA101_2 = input.LA(2);

                    if ( (synpred121_JavaScript()) ) {
                        alt101=1;
                    }


                }


                switch (alt101) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT212=(Token)match(input,LT,FOLLOW_LT_in_defaultClause1263); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop101;
                }
            } while (true);

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:198:29: ( statementList )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( ((LA102_0>=Identifier && LA102_0<=NumericLiteral)||(LA102_0>=31 && LA102_0<=32)||LA102_0==35||(LA102_0>=37 && LA102_0<=38)||LA102_0==40||(LA102_0>=42 && LA102_0<=44)||(LA102_0>=46 && LA102_0<=49)||LA102_0==51||(LA102_0>=54 && LA102_0<=55)||(LA102_0>=58 && LA102_0<=59)||(LA102_0>=91 && LA102_0<=92)||(LA102_0>=96 && LA102_0<=106)) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: statementList
                    {
                    pushFollow(FOLLOW_statementList_in_defaultClause1267);
                    statementList213=statementList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statementList213.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 36, defaultClause_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "defaultClause"

    public static class throwStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "throwStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:201:1: throwStatement : 'throw' expression ( LT | ';' ) ;
    public final JavaScriptParser.throwStatement_return throwStatement() throws RecognitionException {
        JavaScriptParser.throwStatement_return retval = new JavaScriptParser.throwStatement_return();
        retval.start = input.LT(1);
        int throwStatement_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal214=null;
        Token set216=null;
        JavaScriptParser.expression_return expression215 = null;


        Object string_literal214_tree=null;
        Object set216_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:202:3: ( 'throw' expression ( LT | ';' ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:202:5: 'throw' expression ( LT | ';' )
            {
            root_0 = (Object)adaptor.nil();

            string_literal214=(Token)match(input,54,FOLLOW_54_in_throwStatement1283); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal214_tree = (Object)adaptor.create(string_literal214);
            adaptor.addChild(root_0, string_literal214_tree);
            }
            pushFollow(FOLLOW_expression_in_throwStatement1285);
            expression215=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression215.getTree());
            set216=(Token)input.LT(1);
            if ( input.LA(1)==LT||input.LA(1)==38 ) {
                input.consume();
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
            if ( state.backtracking>0 ) { memoize(input, 37, throwStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "throwStatement"

    public static class tryStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "tryStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:205:1: tryStatement : 'try' ( LT )* statementBlock ( LT )* ( finallyClause | catchClause ( ( LT )* finallyClause )? ) ;
    public final JavaScriptParser.tryStatement_return tryStatement() throws RecognitionException {
        JavaScriptParser.tryStatement_return retval = new JavaScriptParser.tryStatement_return();
        retval.start = input.LT(1);
        int tryStatement_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal217=null;
        Token LT218=null;
        Token LT220=null;
        Token LT223=null;
        JavaScriptParser.statementBlock_return statementBlock219 = null;

        JavaScriptParser.finallyClause_return finallyClause221 = null;

        JavaScriptParser.catchClause_return catchClause222 = null;

        JavaScriptParser.finallyClause_return finallyClause224 = null;


        Object string_literal217_tree=null;
        Object LT218_tree=null;
        Object LT220_tree=null;
        Object LT223_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:206:3: ( 'try' ( LT )* statementBlock ( LT )* ( finallyClause | catchClause ( ( LT )* finallyClause )? ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:206:5: 'try' ( LT )* statementBlock ( LT )* ( finallyClause | catchClause ( ( LT )* finallyClause )? )
            {
            root_0 = (Object)adaptor.nil();

            string_literal217=(Token)match(input,55,FOLLOW_55_in_tryStatement1307); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal217_tree = (Object)adaptor.create(string_literal217);
            adaptor.addChild(root_0, string_literal217_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:206:13: ( LT )*
            loop103:
            do {
                int alt103=2;
                int LA103_0 = input.LA(1);

                if ( (LA103_0==LT) ) {
                    alt103=1;
                }


                switch (alt103) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT218=(Token)match(input,LT,FOLLOW_LT_in_tryStatement1309); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop103;
                }
            } while (true);

            pushFollow(FOLLOW_statementBlock_in_tryStatement1313);
            statementBlock219=statementBlock();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, statementBlock219.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:206:33: ( LT )*
            loop104:
            do {
                int alt104=2;
                int LA104_0 = input.LA(1);

                if ( (LA104_0==LT) ) {
                    alt104=1;
                }


                switch (alt104) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT220=(Token)match(input,LT,FOLLOW_LT_in_tryStatement1315); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop104;
                }
            } while (true);

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:206:36: ( finallyClause | catchClause ( ( LT )* finallyClause )? )
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==57) ) {
                alt107=1;
            }
            else if ( (LA107_0==56) ) {
                alt107=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 107, 0, input);

                throw nvae;
            }
            switch (alt107) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:206:37: finallyClause
                    {
                    pushFollow(FOLLOW_finallyClause_in_tryStatement1320);
                    finallyClause221=finallyClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, finallyClause221.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:206:53: catchClause ( ( LT )* finallyClause )?
                    {
                    pushFollow(FOLLOW_catchClause_in_tryStatement1324);
                    catchClause222=catchClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, catchClause222.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:206:65: ( ( LT )* finallyClause )?
                    int alt106=2;
                    alt106 = dfa106.predict(input);
                    switch (alt106) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:206:66: ( LT )* finallyClause
                            {
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:206:68: ( LT )*
                            loop105:
                            do {
                                int alt105=2;
                                int LA105_0 = input.LA(1);

                                if ( (LA105_0==LT) ) {
                                    alt105=1;
                                }


                                switch (alt105) {
                            	case 1 :
                            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                            	    {
                            	    LT223=(Token)match(input,LT,FOLLOW_LT_in_tryStatement1327); if (state.failed) return retval;

                            	    }
                            	    break;

                            	default :
                            	    break loop105;
                                }
                            } while (true);

                            pushFollow(FOLLOW_finallyClause_in_tryStatement1331);
                            finallyClause224=finallyClause();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, finallyClause224.getTree());

                            }
                            break;

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
            if ( state.backtracking>0 ) { memoize(input, 38, tryStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "tryStatement"

    public static class catchClause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "catchClause"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:209:1: catchClause : 'catch' ( LT )* '(' ( LT )* Identifier ( LT )* ')' ( LT )* statementBlock ;
    public final JavaScriptParser.catchClause_return catchClause() throws RecognitionException {
        JavaScriptParser.catchClause_return retval = new JavaScriptParser.catchClause_return();
        retval.start = input.LT(1);
        int catchClause_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal225=null;
        Token LT226=null;
        Token char_literal227=null;
        Token LT228=null;
        Token Identifier229=null;
        Token LT230=null;
        Token char_literal231=null;
        Token LT232=null;
        JavaScriptParser.statementBlock_return statementBlock233 = null;


        Object string_literal225_tree=null;
        Object LT226_tree=null;
        Object char_literal227_tree=null;
        Object LT228_tree=null;
        Object Identifier229_tree=null;
        Object LT230_tree=null;
        Object char_literal231_tree=null;
        Object LT232_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:210:3: ( 'catch' ( LT )* '(' ( LT )* Identifier ( LT )* ')' ( LT )* statementBlock )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:210:5: 'catch' ( LT )* '(' ( LT )* Identifier ( LT )* ')' ( LT )* statementBlock
            {
            root_0 = (Object)adaptor.nil();

            string_literal225=(Token)match(input,56,FOLLOW_56_in_catchClause1354); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal225_tree = (Object)adaptor.create(string_literal225);
            adaptor.addChild(root_0, string_literal225_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:210:15: ( LT )*
            loop108:
            do {
                int alt108=2;
                int LA108_0 = input.LA(1);

                if ( (LA108_0==LT) ) {
                    alt108=1;
                }


                switch (alt108) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT226=(Token)match(input,LT,FOLLOW_LT_in_catchClause1356); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop108;
                }
            } while (true);

            char_literal227=(Token)match(input,32,FOLLOW_32_in_catchClause1360); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal227_tree = (Object)adaptor.create(char_literal227);
            adaptor.addChild(root_0, char_literal227_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:210:24: ( LT )*
            loop109:
            do {
                int alt109=2;
                int LA109_0 = input.LA(1);

                if ( (LA109_0==LT) ) {
                    alt109=1;
                }


                switch (alt109) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT228=(Token)match(input,LT,FOLLOW_LT_in_catchClause1362); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop109;
                }
            } while (true);

            Identifier229=(Token)match(input,Identifier,FOLLOW_Identifier_in_catchClause1366); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier229_tree = (Object)adaptor.create(Identifier229);
            adaptor.addChild(root_0, Identifier229_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:210:40: ( LT )*
            loop110:
            do {
                int alt110=2;
                int LA110_0 = input.LA(1);

                if ( (LA110_0==LT) ) {
                    alt110=1;
                }


                switch (alt110) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT230=(Token)match(input,LT,FOLLOW_LT_in_catchClause1368); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop110;
                }
            } while (true);

            char_literal231=(Token)match(input,34,FOLLOW_34_in_catchClause1372); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal231_tree = (Object)adaptor.create(char_literal231);
            adaptor.addChild(root_0, char_literal231_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:210:49: ( LT )*
            loop111:
            do {
                int alt111=2;
                int LA111_0 = input.LA(1);

                if ( (LA111_0==LT) ) {
                    alt111=1;
                }


                switch (alt111) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT232=(Token)match(input,LT,FOLLOW_LT_in_catchClause1374); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop111;
                }
            } while (true);

            pushFollow(FOLLOW_statementBlock_in_catchClause1378);
            statementBlock233=statementBlock();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, statementBlock233.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 39, catchClause_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "catchClause"

    public static class finallyClause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "finallyClause"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:213:1: finallyClause : 'finally' ( LT )* statementBlock ;
    public final JavaScriptParser.finallyClause_return finallyClause() throws RecognitionException {
        JavaScriptParser.finallyClause_return retval = new JavaScriptParser.finallyClause_return();
        retval.start = input.LT(1);
        int finallyClause_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal234=null;
        Token LT235=null;
        JavaScriptParser.statementBlock_return statementBlock236 = null;


        Object string_literal234_tree=null;
        Object LT235_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:214:3: ( 'finally' ( LT )* statementBlock )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:214:5: 'finally' ( LT )* statementBlock
            {
            root_0 = (Object)adaptor.nil();

            string_literal234=(Token)match(input,57,FOLLOW_57_in_finallyClause1393); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal234_tree = (Object)adaptor.create(string_literal234);
            adaptor.addChild(root_0, string_literal234_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:214:17: ( LT )*
            loop112:
            do {
                int alt112=2;
                int LA112_0 = input.LA(1);

                if ( (LA112_0==LT) ) {
                    alt112=1;
                }


                switch (alt112) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT235=(Token)match(input,LT,FOLLOW_LT_in_finallyClause1395); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop112;
                }
            } while (true);

            pushFollow(FOLLOW_statementBlock_in_finallyClause1399);
            statementBlock236=statementBlock();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, statementBlock236.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 40, finallyClause_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "finallyClause"

    public static class expression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:218:1: expression : assignmentExpression ( ( LT )* ',' ( LT )* assignmentExpression )* ;
    public final JavaScriptParser.expression_return expression() throws RecognitionException {
        JavaScriptParser.expression_return retval = new JavaScriptParser.expression_return();
        retval.start = input.LT(1);
        int expression_StartIndex = input.index();
        Object root_0 = null;

        Token LT238=null;
        Token char_literal239=null;
        Token LT240=null;
        JavaScriptParser.assignmentExpression_return assignmentExpression237 = null;

        JavaScriptParser.assignmentExpression_return assignmentExpression241 = null;


        Object LT238_tree=null;
        Object char_literal239_tree=null;
        Object LT240_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:219:3: ( assignmentExpression ( ( LT )* ',' ( LT )* assignmentExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:219:5: assignmentExpression ( ( LT )* ',' ( LT )* assignmentExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_assignmentExpression_in_expression1413);
            assignmentExpression237=assignmentExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpression237.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:219:26: ( ( LT )* ',' ( LT )* assignmentExpression )*
            loop115:
            do {
                int alt115=2;
                alt115 = dfa115.predict(input);
                switch (alt115) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:219:27: ( LT )* ',' ( LT )* assignmentExpression
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:219:29: ( LT )*
            	    loop113:
            	    do {
            	        int alt113=2;
            	        int LA113_0 = input.LA(1);

            	        if ( (LA113_0==LT) ) {
            	            alt113=1;
            	        }


            	        switch (alt113) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT238=(Token)match(input,LT,FOLLOW_LT_in_expression1416); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop113;
            	        }
            	    } while (true);

            	    char_literal239=(Token)match(input,33,FOLLOW_33_in_expression1420); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal239_tree = (Object)adaptor.create(char_literal239);
            	    adaptor.addChild(root_0, char_literal239_tree);
            	    }
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:219:38: ( LT )*
            	    loop114:
            	    do {
            	        int alt114=2;
            	        int LA114_0 = input.LA(1);

            	        if ( (LA114_0==LT) ) {
            	            alt114=1;
            	        }


            	        switch (alt114) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT240=(Token)match(input,LT,FOLLOW_LT_in_expression1422); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop114;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_assignmentExpression_in_expression1426);
            	    assignmentExpression241=assignmentExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpression241.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 41, expression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "expression"

    public static class expressionNoIn_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expressionNoIn"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:222:1: expressionNoIn : assignmentExpressionNoIn ( ( LT )* ',' ( LT )* assignmentExpressionNoIn )* ;
    public final JavaScriptParser.expressionNoIn_return expressionNoIn() throws RecognitionException {
        JavaScriptParser.expressionNoIn_return retval = new JavaScriptParser.expressionNoIn_return();
        retval.start = input.LT(1);
        int expressionNoIn_StartIndex = input.index();
        Object root_0 = null;

        Token LT243=null;
        Token char_literal244=null;
        Token LT245=null;
        JavaScriptParser.assignmentExpressionNoIn_return assignmentExpressionNoIn242 = null;

        JavaScriptParser.assignmentExpressionNoIn_return assignmentExpressionNoIn246 = null;


        Object LT243_tree=null;
        Object char_literal244_tree=null;
        Object LT245_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:223:3: ( assignmentExpressionNoIn ( ( LT )* ',' ( LT )* assignmentExpressionNoIn )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:223:5: assignmentExpressionNoIn ( ( LT )* ',' ( LT )* assignmentExpressionNoIn )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_assignmentExpressionNoIn_in_expressionNoIn1443);
            assignmentExpressionNoIn242=assignmentExpressionNoIn();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpressionNoIn242.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:223:30: ( ( LT )* ',' ( LT )* assignmentExpressionNoIn )*
            loop118:
            do {
                int alt118=2;
                alt118 = dfa118.predict(input);
                switch (alt118) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:223:31: ( LT )* ',' ( LT )* assignmentExpressionNoIn
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:223:33: ( LT )*
            	    loop116:
            	    do {
            	        int alt116=2;
            	        int LA116_0 = input.LA(1);

            	        if ( (LA116_0==LT) ) {
            	            alt116=1;
            	        }


            	        switch (alt116) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT243=(Token)match(input,LT,FOLLOW_LT_in_expressionNoIn1446); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop116;
            	        }
            	    } while (true);

            	    char_literal244=(Token)match(input,33,FOLLOW_33_in_expressionNoIn1450); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal244_tree = (Object)adaptor.create(char_literal244);
            	    adaptor.addChild(root_0, char_literal244_tree);
            	    }
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:223:42: ( LT )*
            	    loop117:
            	    do {
            	        int alt117=2;
            	        int LA117_0 = input.LA(1);

            	        if ( (LA117_0==LT) ) {
            	            alt117=1;
            	        }


            	        switch (alt117) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT245=(Token)match(input,LT,FOLLOW_LT_in_expressionNoIn1452); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop117;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_assignmentExpressionNoIn_in_expressionNoIn1456);
            	    assignmentExpressionNoIn246=assignmentExpressionNoIn();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpressionNoIn246.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 42, expressionNoIn_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "expressionNoIn"

    public static class assignmentExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "assignmentExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:226:1: assignmentExpression : ( conditionalExpression | leftHandSideExpression ( LT )* assignmentOperator ( LT )* assignmentExpression );
    public final JavaScriptParser.assignmentExpression_return assignmentExpression() throws RecognitionException {
        JavaScriptParser.assignmentExpression_return retval = new JavaScriptParser.assignmentExpression_return();
        retval.start = input.LT(1);
        int assignmentExpression_StartIndex = input.index();
        Object root_0 = null;

        Token LT249=null;
        Token LT251=null;
        JavaScriptParser.conditionalExpression_return conditionalExpression247 = null;

        JavaScriptParser.leftHandSideExpression_return leftHandSideExpression248 = null;

        JavaScriptParser.assignmentOperator_return assignmentOperator250 = null;

        JavaScriptParser.assignmentExpression_return assignmentExpression252 = null;


        Object LT249_tree=null;
        Object LT251_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:227:3: ( conditionalExpression | leftHandSideExpression ( LT )* assignmentOperator ( LT )* assignmentExpression )
            int alt121=2;
            alt121 = dfa121.predict(input);
            switch (alt121) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:227:5: conditionalExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_conditionalExpression_in_assignmentExpression1473);
                    conditionalExpression247=conditionalExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalExpression247.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:228:5: leftHandSideExpression ( LT )* assignmentOperator ( LT )* assignmentExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_leftHandSideExpression_in_assignmentExpression1479);
                    leftHandSideExpression248=leftHandSideExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, leftHandSideExpression248.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:228:30: ( LT )*
                    loop119:
                    do {
                        int alt119=2;
                        int LA119_0 = input.LA(1);

                        if ( (LA119_0==LT) ) {
                            alt119=1;
                        }


                        switch (alt119) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT249=(Token)match(input,LT,FOLLOW_LT_in_assignmentExpression1481); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop119;
                        }
                    } while (true);

                    pushFollow(FOLLOW_assignmentOperator_in_assignmentExpression1485);
                    assignmentOperator250=assignmentOperator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentOperator250.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:228:54: ( LT )*
                    loop120:
                    do {
                        int alt120=2;
                        int LA120_0 = input.LA(1);

                        if ( (LA120_0==LT) ) {
                            alt120=1;
                        }


                        switch (alt120) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT251=(Token)match(input,LT,FOLLOW_LT_in_assignmentExpression1487); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop120;
                        }
                    } while (true);

                    pushFollow(FOLLOW_assignmentExpression_in_assignmentExpression1491);
                    assignmentExpression252=assignmentExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpression252.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 43, assignmentExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "assignmentExpression"

    public static class assignmentExpressionNoIn_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "assignmentExpressionNoIn"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:231:1: assignmentExpressionNoIn : ( conditionalExpressionNoIn | leftHandSideExpression ( LT )* assignmentOperator ( LT )* assignmentExpressionNoIn );
    public final JavaScriptParser.assignmentExpressionNoIn_return assignmentExpressionNoIn() throws RecognitionException {
        JavaScriptParser.assignmentExpressionNoIn_return retval = new JavaScriptParser.assignmentExpressionNoIn_return();
        retval.start = input.LT(1);
        int assignmentExpressionNoIn_StartIndex = input.index();
        Object root_0 = null;

        Token LT255=null;
        Token LT257=null;
        JavaScriptParser.conditionalExpressionNoIn_return conditionalExpressionNoIn253 = null;

        JavaScriptParser.leftHandSideExpression_return leftHandSideExpression254 = null;

        JavaScriptParser.assignmentOperator_return assignmentOperator256 = null;

        JavaScriptParser.assignmentExpressionNoIn_return assignmentExpressionNoIn258 = null;


        Object LT255_tree=null;
        Object LT257_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:232:3: ( conditionalExpressionNoIn | leftHandSideExpression ( LT )* assignmentOperator ( LT )* assignmentExpressionNoIn )
            int alt124=2;
            alt124 = dfa124.predict(input);
            switch (alt124) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:232:5: conditionalExpressionNoIn
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_conditionalExpressionNoIn_in_assignmentExpressionNoIn1506);
                    conditionalExpressionNoIn253=conditionalExpressionNoIn();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalExpressionNoIn253.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:233:5: leftHandSideExpression ( LT )* assignmentOperator ( LT )* assignmentExpressionNoIn
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_leftHandSideExpression_in_assignmentExpressionNoIn1512);
                    leftHandSideExpression254=leftHandSideExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, leftHandSideExpression254.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:233:30: ( LT )*
                    loop122:
                    do {
                        int alt122=2;
                        int LA122_0 = input.LA(1);

                        if ( (LA122_0==LT) ) {
                            alt122=1;
                        }


                        switch (alt122) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT255=(Token)match(input,LT,FOLLOW_LT_in_assignmentExpressionNoIn1514); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop122;
                        }
                    } while (true);

                    pushFollow(FOLLOW_assignmentOperator_in_assignmentExpressionNoIn1518);
                    assignmentOperator256=assignmentOperator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentOperator256.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:233:54: ( LT )*
                    loop123:
                    do {
                        int alt123=2;
                        int LA123_0 = input.LA(1);

                        if ( (LA123_0==LT) ) {
                            alt123=1;
                        }


                        switch (alt123) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT257=(Token)match(input,LT,FOLLOW_LT_in_assignmentExpressionNoIn1520); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop123;
                        }
                    } while (true);

                    pushFollow(FOLLOW_assignmentExpressionNoIn_in_assignmentExpressionNoIn1524);
                    assignmentExpressionNoIn258=assignmentExpressionNoIn();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpressionNoIn258.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 44, assignmentExpressionNoIn_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "assignmentExpressionNoIn"

    public static class leftHandSideExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "leftHandSideExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:236:1: leftHandSideExpression : ( callExpression | newExpression );
    public final JavaScriptParser.leftHandSideExpression_return leftHandSideExpression() throws RecognitionException {
        JavaScriptParser.leftHandSideExpression_return retval = new JavaScriptParser.leftHandSideExpression_return();
        retval.start = input.LT(1);
        int leftHandSideExpression_StartIndex = input.index();
        Object root_0 = null;

        JavaScriptParser.callExpression_return callExpression259 = null;

        JavaScriptParser.newExpression_return newExpression260 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:237:3: ( callExpression | newExpression )
            int alt125=2;
            alt125 = dfa125.predict(input);
            switch (alt125) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:237:5: callExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_callExpression_in_leftHandSideExpression1539);
                    callExpression259=callExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, callExpression259.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:238:5: newExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_newExpression_in_leftHandSideExpression1545);
                    newExpression260=newExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, newExpression260.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 45, leftHandSideExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "leftHandSideExpression"

    public static class newExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "newExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:241:1: newExpression : ( memberExpression | 'new' ( LT )* newExpression );
    public final JavaScriptParser.newExpression_return newExpression() throws RecognitionException {
        JavaScriptParser.newExpression_return retval = new JavaScriptParser.newExpression_return();
        retval.start = input.LT(1);
        int newExpression_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal262=null;
        Token LT263=null;
        JavaScriptParser.memberExpression_return memberExpression261 = null;

        JavaScriptParser.newExpression_return newExpression264 = null;


        Object string_literal262_tree=null;
        Object LT263_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 46) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:242:3: ( memberExpression | 'new' ( LT )* newExpression )
            int alt127=2;
            alt127 = dfa127.predict(input);
            switch (alt127) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:242:5: memberExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_memberExpression_in_newExpression1560);
                    memberExpression261=memberExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, memberExpression261.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:243:5: 'new' ( LT )* newExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal262=(Token)match(input,58,FOLLOW_58_in_newExpression1566); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal262_tree = (Object)adaptor.create(string_literal262);
                    adaptor.addChild(root_0, string_literal262_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:243:13: ( LT )*
                    loop126:
                    do {
                        int alt126=2;
                        int LA126_0 = input.LA(1);

                        if ( (LA126_0==LT) ) {
                            alt126=1;
                        }


                        switch (alt126) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT263=(Token)match(input,LT,FOLLOW_LT_in_newExpression1568); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop126;
                        }
                    } while (true);

                    pushFollow(FOLLOW_newExpression_in_newExpression1572);
                    newExpression264=newExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, newExpression264.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 46, newExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "newExpression"

    public static class memberExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "memberExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:246:1: memberExpression : ( primaryExpression | functionExpression | 'new' ( LT )* memberExpression ( LT )* arguments ) ( ( LT )* memberExpressionSuffix )* ;
    public final JavaScriptParser.memberExpression_return memberExpression() throws RecognitionException {
        JavaScriptParser.memberExpression_return retval = new JavaScriptParser.memberExpression_return();
        retval.start = input.LT(1);
        int memberExpression_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal267=null;
        Token LT268=null;
        Token LT270=null;
        Token LT272=null;
        JavaScriptParser.primaryExpression_return primaryExpression265 = null;

        JavaScriptParser.functionExpression_return functionExpression266 = null;

        JavaScriptParser.memberExpression_return memberExpression269 = null;

        JavaScriptParser.arguments_return arguments271 = null;

        JavaScriptParser.memberExpressionSuffix_return memberExpressionSuffix273 = null;


        Object string_literal267_tree=null;
        Object LT268_tree=null;
        Object LT270_tree=null;
        Object LT272_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 47) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:247:3: ( ( primaryExpression | functionExpression | 'new' ( LT )* memberExpression ( LT )* arguments ) ( ( LT )* memberExpressionSuffix )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:247:5: ( primaryExpression | functionExpression | 'new' ( LT )* memberExpression ( LT )* arguments ) ( ( LT )* memberExpressionSuffix )*
            {
            root_0 = (Object)adaptor.nil();

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:247:5: ( primaryExpression | functionExpression | 'new' ( LT )* memberExpression ( LT )* arguments )
            int alt130=3;
            switch ( input.LA(1) ) {
            case Identifier:
            case StringLiteral:
            case NumericLiteral:
            case 32:
            case 35:
            case 59:
            case 103:
            case 104:
            case 105:
            case 106:
                {
                alt130=1;
                }
                break;
            case 31:
                {
                alt130=2;
                }
                break;
            case 58:
                {
                alt130=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 130, 0, input);

                throw nvae;
            }

            switch (alt130) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:247:6: primaryExpression
                    {
                    pushFollow(FOLLOW_primaryExpression_in_memberExpression1588);
                    primaryExpression265=primaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, primaryExpression265.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:247:26: functionExpression
                    {
                    pushFollow(FOLLOW_functionExpression_in_memberExpression1592);
                    functionExpression266=functionExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, functionExpression266.getTree());

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:247:47: 'new' ( LT )* memberExpression ( LT )* arguments
                    {
                    string_literal267=(Token)match(input,58,FOLLOW_58_in_memberExpression1596); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal267_tree = (Object)adaptor.create(string_literal267);
                    adaptor.addChild(root_0, string_literal267_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:247:55: ( LT )*
                    loop128:
                    do {
                        int alt128=2;
                        int LA128_0 = input.LA(1);

                        if ( (LA128_0==LT) ) {
                            alt128=1;
                        }


                        switch (alt128) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT268=(Token)match(input,LT,FOLLOW_LT_in_memberExpression1598); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop128;
                        }
                    } while (true);

                    pushFollow(FOLLOW_memberExpression_in_memberExpression1602);
                    memberExpression269=memberExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, memberExpression269.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:247:77: ( LT )*
                    loop129:
                    do {
                        int alt129=2;
                        int LA129_0 = input.LA(1);

                        if ( (LA129_0==LT) ) {
                            alt129=1;
                        }


                        switch (alt129) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT270=(Token)match(input,LT,FOLLOW_LT_in_memberExpression1604); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop129;
                        }
                    } while (true);

                    pushFollow(FOLLOW_arguments_in_memberExpression1608);
                    arguments271=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments271.getTree());

                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:247:91: ( ( LT )* memberExpressionSuffix )*
            loop132:
            do {
                int alt132=2;
                alt132 = dfa132.predict(input);
                switch (alt132) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:247:92: ( LT )* memberExpressionSuffix
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:247:94: ( LT )*
            	    loop131:
            	    do {
            	        int alt131=2;
            	        int LA131_0 = input.LA(1);

            	        if ( (LA131_0==LT) ) {
            	            alt131=1;
            	        }


            	        switch (alt131) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT272=(Token)match(input,LT,FOLLOW_LT_in_memberExpression1612); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop131;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_memberExpressionSuffix_in_memberExpression1616);
            	    memberExpressionSuffix273=memberExpressionSuffix();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, memberExpressionSuffix273.getTree());

            	    }
            	    break;

            	default :
            	    break loop132;
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
            if ( state.backtracking>0 ) { memoize(input, 47, memberExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "memberExpression"

    public static class memberExpressionSuffix_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "memberExpressionSuffix"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:250:1: memberExpressionSuffix : ( indexSuffix | propertyReferenceSuffix );
    public final JavaScriptParser.memberExpressionSuffix_return memberExpressionSuffix() throws RecognitionException {
        JavaScriptParser.memberExpressionSuffix_return retval = new JavaScriptParser.memberExpressionSuffix_return();
        retval.start = input.LT(1);
        int memberExpressionSuffix_StartIndex = input.index();
        Object root_0 = null;

        JavaScriptParser.indexSuffix_return indexSuffix274 = null;

        JavaScriptParser.propertyReferenceSuffix_return propertyReferenceSuffix275 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 48) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:251:3: ( indexSuffix | propertyReferenceSuffix )
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==59) ) {
                alt133=1;
            }
            else if ( (LA133_0==61) ) {
                alt133=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 133, 0, input);

                throw nvae;
            }
            switch (alt133) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:251:5: indexSuffix
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_indexSuffix_in_memberExpressionSuffix1633);
                    indexSuffix274=indexSuffix();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, indexSuffix274.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:252:5: propertyReferenceSuffix
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_propertyReferenceSuffix_in_memberExpressionSuffix1639);
                    propertyReferenceSuffix275=propertyReferenceSuffix();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, propertyReferenceSuffix275.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 48, memberExpressionSuffix_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "memberExpressionSuffix"

    public static class callExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "callExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:255:1: callExpression : memberExpression ( LT )* arguments ( ( LT )* callExpressionSuffix )* ;
    public final JavaScriptParser.callExpression_return callExpression() throws RecognitionException {
        JavaScriptParser.callExpression_return retval = new JavaScriptParser.callExpression_return();
        retval.start = input.LT(1);
        int callExpression_StartIndex = input.index();
        Object root_0 = null;

        Token LT277=null;
        Token LT279=null;
        JavaScriptParser.memberExpression_return memberExpression276 = null;

        JavaScriptParser.arguments_return arguments278 = null;

        JavaScriptParser.callExpressionSuffix_return callExpressionSuffix280 = null;


        Object LT277_tree=null;
        Object LT279_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 49) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:256:3: ( memberExpression ( LT )* arguments ( ( LT )* callExpressionSuffix )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:256:5: memberExpression ( LT )* arguments ( ( LT )* callExpressionSuffix )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_memberExpression_in_callExpression1652);
            memberExpression276=memberExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, memberExpression276.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:256:24: ( LT )*
            loop134:
            do {
                int alt134=2;
                int LA134_0 = input.LA(1);

                if ( (LA134_0==LT) ) {
                    alt134=1;
                }


                switch (alt134) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT277=(Token)match(input,LT,FOLLOW_LT_in_callExpression1654); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop134;
                }
            } while (true);

            pushFollow(FOLLOW_arguments_in_callExpression1658);
            arguments278=arguments();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments278.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:256:37: ( ( LT )* callExpressionSuffix )*
            loop136:
            do {
                int alt136=2;
                alt136 = dfa136.predict(input);
                switch (alt136) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:256:38: ( LT )* callExpressionSuffix
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:256:40: ( LT )*
            	    loop135:
            	    do {
            	        int alt135=2;
            	        int LA135_0 = input.LA(1);

            	        if ( (LA135_0==LT) ) {
            	            alt135=1;
            	        }


            	        switch (alt135) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT279=(Token)match(input,LT,FOLLOW_LT_in_callExpression1661); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop135;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_callExpressionSuffix_in_callExpression1665);
            	    callExpressionSuffix280=callExpressionSuffix();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, callExpressionSuffix280.getTree());

            	    }
            	    break;

            	default :
            	    break loop136;
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
            if ( state.backtracking>0 ) { memoize(input, 49, callExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "callExpression"

    public static class callExpressionSuffix_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "callExpressionSuffix"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:259:1: callExpressionSuffix : ( arguments | indexSuffix | propertyReferenceSuffix );
    public final JavaScriptParser.callExpressionSuffix_return callExpressionSuffix() throws RecognitionException {
        JavaScriptParser.callExpressionSuffix_return retval = new JavaScriptParser.callExpressionSuffix_return();
        retval.start = input.LT(1);
        int callExpressionSuffix_StartIndex = input.index();
        Object root_0 = null;

        JavaScriptParser.arguments_return arguments281 = null;

        JavaScriptParser.indexSuffix_return indexSuffix282 = null;

        JavaScriptParser.propertyReferenceSuffix_return propertyReferenceSuffix283 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 50) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:260:3: ( arguments | indexSuffix | propertyReferenceSuffix )
            int alt137=3;
            switch ( input.LA(1) ) {
            case 32:
                {
                alt137=1;
                }
                break;
            case 59:
                {
                alt137=2;
                }
                break;
            case 61:
                {
                alt137=3;
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
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:260:5: arguments
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_arguments_in_callExpressionSuffix1682);
                    arguments281=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments281.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:261:5: indexSuffix
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_indexSuffix_in_callExpressionSuffix1688);
                    indexSuffix282=indexSuffix();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, indexSuffix282.getTree());

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:262:5: propertyReferenceSuffix
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_propertyReferenceSuffix_in_callExpressionSuffix1694);
                    propertyReferenceSuffix283=propertyReferenceSuffix();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, propertyReferenceSuffix283.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 50, callExpressionSuffix_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "callExpressionSuffix"

    public static class arguments_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arguments"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:265:1: arguments : '(' ( ( LT )* assignmentExpression ( ( LT )* ',' ( LT )* assignmentExpression )* )? ( LT )* ')' ;
    public final JavaScriptParser.arguments_return arguments() throws RecognitionException {
        JavaScriptParser.arguments_return retval = new JavaScriptParser.arguments_return();
        retval.start = input.LT(1);
        int arguments_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal284=null;
        Token LT285=null;
        Token LT287=null;
        Token char_literal288=null;
        Token LT289=null;
        Token LT291=null;
        Token char_literal292=null;
        JavaScriptParser.assignmentExpression_return assignmentExpression286 = null;

        JavaScriptParser.assignmentExpression_return assignmentExpression290 = null;


        Object char_literal284_tree=null;
        Object LT285_tree=null;
        Object LT287_tree=null;
        Object char_literal288_tree=null;
        Object LT289_tree=null;
        Object LT291_tree=null;
        Object char_literal292_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 51) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:266:3: ( '(' ( ( LT )* assignmentExpression ( ( LT )* ',' ( LT )* assignmentExpression )* )? ( LT )* ')' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:266:5: '(' ( ( LT )* assignmentExpression ( ( LT )* ',' ( LT )* assignmentExpression )* )? ( LT )* ')'
            {
            root_0 = (Object)adaptor.nil();

            char_literal284=(Token)match(input,32,FOLLOW_32_in_arguments1707); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal284_tree = (Object)adaptor.create(char_literal284);
            adaptor.addChild(root_0, char_literal284_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:266:9: ( ( LT )* assignmentExpression ( ( LT )* ',' ( LT )* assignmentExpression )* )?
            int alt142=2;
            alt142 = dfa142.predict(input);
            switch (alt142) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:266:10: ( LT )* assignmentExpression ( ( LT )* ',' ( LT )* assignmentExpression )*
                    {
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:266:12: ( LT )*
                    loop138:
                    do {
                        int alt138=2;
                        int LA138_0 = input.LA(1);

                        if ( (LA138_0==LT) ) {
                            alt138=1;
                        }


                        switch (alt138) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT285=(Token)match(input,LT,FOLLOW_LT_in_arguments1710); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop138;
                        }
                    } while (true);

                    pushFollow(FOLLOW_assignmentExpression_in_arguments1714);
                    assignmentExpression286=assignmentExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpression286.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:266:36: ( ( LT )* ',' ( LT )* assignmentExpression )*
                    loop141:
                    do {
                        int alt141=2;
                        alt141 = dfa141.predict(input);
                        switch (alt141) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:266:37: ( LT )* ',' ( LT )* assignmentExpression
                    	    {
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:266:39: ( LT )*
                    	    loop139:
                    	    do {
                    	        int alt139=2;
                    	        int LA139_0 = input.LA(1);

                    	        if ( (LA139_0==LT) ) {
                    	            alt139=1;
                    	        }


                    	        switch (alt139) {
                    	    	case 1 :
                    	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    	    {
                    	    	    LT287=(Token)match(input,LT,FOLLOW_LT_in_arguments1717); if (state.failed) return retval;

                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop139;
                    	        }
                    	    } while (true);

                    	    char_literal288=(Token)match(input,33,FOLLOW_33_in_arguments1721); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal288_tree = (Object)adaptor.create(char_literal288);
                    	    adaptor.addChild(root_0, char_literal288_tree);
                    	    }
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:266:48: ( LT )*
                    	    loop140:
                    	    do {
                    	        int alt140=2;
                    	        int LA140_0 = input.LA(1);

                    	        if ( (LA140_0==LT) ) {
                    	            alt140=1;
                    	        }


                    	        switch (alt140) {
                    	    	case 1 :
                    	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    	    {
                    	    	    LT289=(Token)match(input,LT,FOLLOW_LT_in_arguments1723); if (state.failed) return retval;

                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop140;
                    	        }
                    	    } while (true);

                    	    pushFollow(FOLLOW_assignmentExpression_in_arguments1727);
                    	    assignmentExpression290=assignmentExpression();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpression290.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop141;
                        }
                    } while (true);


                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:266:78: ( LT )*
            loop143:
            do {
                int alt143=2;
                int LA143_0 = input.LA(1);

                if ( (LA143_0==LT) ) {
                    alt143=1;
                }


                switch (alt143) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT291=(Token)match(input,LT,FOLLOW_LT_in_arguments1733); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop143;
                }
            } while (true);

            char_literal292=(Token)match(input,34,FOLLOW_34_in_arguments1737); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal292_tree = (Object)adaptor.create(char_literal292);
            adaptor.addChild(root_0, char_literal292_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 51, arguments_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "arguments"

    public static class indexSuffix_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "indexSuffix"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:269:1: indexSuffix : '[' ( LT )* expression ( LT )* ']' ;
    public final JavaScriptParser.indexSuffix_return indexSuffix() throws RecognitionException {
        JavaScriptParser.indexSuffix_return retval = new JavaScriptParser.indexSuffix_return();
        retval.start = input.LT(1);
        int indexSuffix_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal293=null;
        Token LT294=null;
        Token LT296=null;
        Token char_literal297=null;
        JavaScriptParser.expression_return expression295 = null;


        Object char_literal293_tree=null;
        Object LT294_tree=null;
        Object LT296_tree=null;
        Object char_literal297_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 52) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:270:3: ( '[' ( LT )* expression ( LT )* ']' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:270:5: '[' ( LT )* expression ( LT )* ']'
            {
            root_0 = (Object)adaptor.nil();

            char_literal293=(Token)match(input,59,FOLLOW_59_in_indexSuffix1752); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal293_tree = (Object)adaptor.create(char_literal293);
            adaptor.addChild(root_0, char_literal293_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:270:11: ( LT )*
            loop144:
            do {
                int alt144=2;
                int LA144_0 = input.LA(1);

                if ( (LA144_0==LT) ) {
                    alt144=1;
                }


                switch (alt144) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT294=(Token)match(input,LT,FOLLOW_LT_in_indexSuffix1754); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop144;
                }
            } while (true);

            pushFollow(FOLLOW_expression_in_indexSuffix1758);
            expression295=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression295.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:270:27: ( LT )*
            loop145:
            do {
                int alt145=2;
                int LA145_0 = input.LA(1);

                if ( (LA145_0==LT) ) {
                    alt145=1;
                }


                switch (alt145) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT296=(Token)match(input,LT,FOLLOW_LT_in_indexSuffix1760); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop145;
                }
            } while (true);

            char_literal297=(Token)match(input,60,FOLLOW_60_in_indexSuffix1764); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal297_tree = (Object)adaptor.create(char_literal297);
            adaptor.addChild(root_0, char_literal297_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 52, indexSuffix_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "indexSuffix"

    public static class propertyReferenceSuffix_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "propertyReferenceSuffix"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:273:1: propertyReferenceSuffix : '.' ( LT )* Identifier ;
    public final JavaScriptParser.propertyReferenceSuffix_return propertyReferenceSuffix() throws RecognitionException {
        JavaScriptParser.propertyReferenceSuffix_return retval = new JavaScriptParser.propertyReferenceSuffix_return();
        retval.start = input.LT(1);
        int propertyReferenceSuffix_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal298=null;
        Token LT299=null;
        Token Identifier300=null;

        Object char_literal298_tree=null;
        Object LT299_tree=null;
        Object Identifier300_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 53) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:274:3: ( '.' ( LT )* Identifier )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:274:5: '.' ( LT )* Identifier
            {
            root_0 = (Object)adaptor.nil();

            char_literal298=(Token)match(input,61,FOLLOW_61_in_propertyReferenceSuffix1780); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal298_tree = (Object)adaptor.create(char_literal298);
            adaptor.addChild(root_0, char_literal298_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:274:11: ( LT )*
            loop146:
            do {
                int alt146=2;
                int LA146_0 = input.LA(1);

                if ( (LA146_0==LT) ) {
                    alt146=1;
                }


                switch (alt146) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT299=(Token)match(input,LT,FOLLOW_LT_in_propertyReferenceSuffix1782); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop146;
                }
            } while (true);

            Identifier300=(Token)match(input,Identifier,FOLLOW_Identifier_in_propertyReferenceSuffix1786); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier300_tree = (Object)adaptor.create(Identifier300);
            adaptor.addChild(root_0, Identifier300_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 53, propertyReferenceSuffix_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "propertyReferenceSuffix"

    public static class assignmentOperator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "assignmentOperator"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:277:1: assignmentOperator : ( '=' | '*=' | '/=' | '%=' | '+=' | '-=' | '<<=' | '>>=' | '>>>=' | '&=' | '^=' | '|=' );
    public final JavaScriptParser.assignmentOperator_return assignmentOperator() throws RecognitionException {
        JavaScriptParser.assignmentOperator_return retval = new JavaScriptParser.assignmentOperator_return();
        retval.start = input.LT(1);
        int assignmentOperator_StartIndex = input.index();
        Object root_0 = null;

        Token set301=null;

        Object set301_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 54) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:278:3: ( '=' | '*=' | '/=' | '%=' | '+=' | '-=' | '<<=' | '>>=' | '>>>=' | '&=' | '^=' | '|=' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:
            {
            root_0 = (Object)adaptor.nil();

            set301=(Token)input.LT(1);
            if ( input.LA(1)==39||(input.LA(1)>=62 && input.LA(1)<=72) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set301));
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
            if ( state.backtracking>0 ) { memoize(input, 54, assignmentOperator_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "assignmentOperator"

    public static class conditionalExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "conditionalExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:281:1: conditionalExpression : logicalORExpression ( ( LT )* '?' ( LT )* assignmentExpression ( LT )* ':' ( LT )* assignmentExpression )? ;
    public final JavaScriptParser.conditionalExpression_return conditionalExpression() throws RecognitionException {
        JavaScriptParser.conditionalExpression_return retval = new JavaScriptParser.conditionalExpression_return();
        retval.start = input.LT(1);
        int conditionalExpression_StartIndex = input.index();
        Object root_0 = null;

        Token LT303=null;
        Token char_literal304=null;
        Token LT305=null;
        Token LT307=null;
        Token char_literal308=null;
        Token LT309=null;
        JavaScriptParser.logicalORExpression_return logicalORExpression302 = null;

        JavaScriptParser.assignmentExpression_return assignmentExpression306 = null;

        JavaScriptParser.assignmentExpression_return assignmentExpression310 = null;


        Object LT303_tree=null;
        Object char_literal304_tree=null;
        Object LT305_tree=null;
        Object LT307_tree=null;
        Object char_literal308_tree=null;
        Object LT309_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 55) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:282:3: ( logicalORExpression ( ( LT )* '?' ( LT )* assignmentExpression ( LT )* ':' ( LT )* assignmentExpression )? )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:282:5: logicalORExpression ( ( LT )* '?' ( LT )* assignmentExpression ( LT )* ':' ( LT )* assignmentExpression )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_logicalORExpression_in_conditionalExpression1858);
            logicalORExpression302=logicalORExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, logicalORExpression302.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:282:25: ( ( LT )* '?' ( LT )* assignmentExpression ( LT )* ':' ( LT )* assignmentExpression )?
            int alt151=2;
            alt151 = dfa151.predict(input);
            switch (alt151) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:282:26: ( LT )* '?' ( LT )* assignmentExpression ( LT )* ':' ( LT )* assignmentExpression
                    {
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:282:28: ( LT )*
                    loop147:
                    do {
                        int alt147=2;
                        int LA147_0 = input.LA(1);

                        if ( (LA147_0==LT) ) {
                            alt147=1;
                        }


                        switch (alt147) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT303=(Token)match(input,LT,FOLLOW_LT_in_conditionalExpression1861); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop147;
                        }
                    } while (true);

                    char_literal304=(Token)match(input,73,FOLLOW_73_in_conditionalExpression1865); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal304_tree = (Object)adaptor.create(char_literal304);
                    adaptor.addChild(root_0, char_literal304_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:282:37: ( LT )*
                    loop148:
                    do {
                        int alt148=2;
                        int LA148_0 = input.LA(1);

                        if ( (LA148_0==LT) ) {
                            alt148=1;
                        }


                        switch (alt148) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT305=(Token)match(input,LT,FOLLOW_LT_in_conditionalExpression1867); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop148;
                        }
                    } while (true);

                    pushFollow(FOLLOW_assignmentExpression_in_conditionalExpression1871);
                    assignmentExpression306=assignmentExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpression306.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:282:63: ( LT )*
                    loop149:
                    do {
                        int alt149=2;
                        int LA149_0 = input.LA(1);

                        if ( (LA149_0==LT) ) {
                            alt149=1;
                        }


                        switch (alt149) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT307=(Token)match(input,LT,FOLLOW_LT_in_conditionalExpression1873); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop149;
                        }
                    } while (true);

                    char_literal308=(Token)match(input,50,FOLLOW_50_in_conditionalExpression1877); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal308_tree = (Object)adaptor.create(char_literal308);
                    adaptor.addChild(root_0, char_literal308_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:282:72: ( LT )*
                    loop150:
                    do {
                        int alt150=2;
                        int LA150_0 = input.LA(1);

                        if ( (LA150_0==LT) ) {
                            alt150=1;
                        }


                        switch (alt150) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT309=(Token)match(input,LT,FOLLOW_LT_in_conditionalExpression1879); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop150;
                        }
                    } while (true);

                    pushFollow(FOLLOW_assignmentExpression_in_conditionalExpression1883);
                    assignmentExpression310=assignmentExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpression310.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 55, conditionalExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "conditionalExpression"

    public static class conditionalExpressionNoIn_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "conditionalExpressionNoIn"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:285:1: conditionalExpressionNoIn : logicalORExpressionNoIn ( ( LT )* '?' ( LT )* assignmentExpressionNoIn ( LT )* ':' ( LT )* assignmentExpressionNoIn )? ;
    public final JavaScriptParser.conditionalExpressionNoIn_return conditionalExpressionNoIn() throws RecognitionException {
        JavaScriptParser.conditionalExpressionNoIn_return retval = new JavaScriptParser.conditionalExpressionNoIn_return();
        retval.start = input.LT(1);
        int conditionalExpressionNoIn_StartIndex = input.index();
        Object root_0 = null;

        Token LT312=null;
        Token char_literal313=null;
        Token LT314=null;
        Token LT316=null;
        Token char_literal317=null;
        Token LT318=null;
        JavaScriptParser.logicalORExpressionNoIn_return logicalORExpressionNoIn311 = null;

        JavaScriptParser.assignmentExpressionNoIn_return assignmentExpressionNoIn315 = null;

        JavaScriptParser.assignmentExpressionNoIn_return assignmentExpressionNoIn319 = null;


        Object LT312_tree=null;
        Object char_literal313_tree=null;
        Object LT314_tree=null;
        Object LT316_tree=null;
        Object char_literal317_tree=null;
        Object LT318_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 56) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:286:3: ( logicalORExpressionNoIn ( ( LT )* '?' ( LT )* assignmentExpressionNoIn ( LT )* ':' ( LT )* assignmentExpressionNoIn )? )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:286:5: logicalORExpressionNoIn ( ( LT )* '?' ( LT )* assignmentExpressionNoIn ( LT )* ':' ( LT )* assignmentExpressionNoIn )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_logicalORExpressionNoIn_in_conditionalExpressionNoIn1898);
            logicalORExpressionNoIn311=logicalORExpressionNoIn();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, logicalORExpressionNoIn311.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:286:29: ( ( LT )* '?' ( LT )* assignmentExpressionNoIn ( LT )* ':' ( LT )* assignmentExpressionNoIn )?
            int alt156=2;
            alt156 = dfa156.predict(input);
            switch (alt156) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:286:30: ( LT )* '?' ( LT )* assignmentExpressionNoIn ( LT )* ':' ( LT )* assignmentExpressionNoIn
                    {
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:286:32: ( LT )*
                    loop152:
                    do {
                        int alt152=2;
                        int LA152_0 = input.LA(1);

                        if ( (LA152_0==LT) ) {
                            alt152=1;
                        }


                        switch (alt152) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT312=(Token)match(input,LT,FOLLOW_LT_in_conditionalExpressionNoIn1901); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop152;
                        }
                    } while (true);

                    char_literal313=(Token)match(input,73,FOLLOW_73_in_conditionalExpressionNoIn1905); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal313_tree = (Object)adaptor.create(char_literal313);
                    adaptor.addChild(root_0, char_literal313_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:286:41: ( LT )*
                    loop153:
                    do {
                        int alt153=2;
                        int LA153_0 = input.LA(1);

                        if ( (LA153_0==LT) ) {
                            alt153=1;
                        }


                        switch (alt153) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT314=(Token)match(input,LT,FOLLOW_LT_in_conditionalExpressionNoIn1907); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop153;
                        }
                    } while (true);

                    pushFollow(FOLLOW_assignmentExpressionNoIn_in_conditionalExpressionNoIn1911);
                    assignmentExpressionNoIn315=assignmentExpressionNoIn();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpressionNoIn315.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:286:71: ( LT )*
                    loop154:
                    do {
                        int alt154=2;
                        int LA154_0 = input.LA(1);

                        if ( (LA154_0==LT) ) {
                            alt154=1;
                        }


                        switch (alt154) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT316=(Token)match(input,LT,FOLLOW_LT_in_conditionalExpressionNoIn1913); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop154;
                        }
                    } while (true);

                    char_literal317=(Token)match(input,50,FOLLOW_50_in_conditionalExpressionNoIn1917); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal317_tree = (Object)adaptor.create(char_literal317);
                    adaptor.addChild(root_0, char_literal317_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:286:80: ( LT )*
                    loop155:
                    do {
                        int alt155=2;
                        int LA155_0 = input.LA(1);

                        if ( (LA155_0==LT) ) {
                            alt155=1;
                        }


                        switch (alt155) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT318=(Token)match(input,LT,FOLLOW_LT_in_conditionalExpressionNoIn1919); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop155;
                        }
                    } while (true);

                    pushFollow(FOLLOW_assignmentExpressionNoIn_in_conditionalExpressionNoIn1923);
                    assignmentExpressionNoIn319=assignmentExpressionNoIn();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpressionNoIn319.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 56, conditionalExpressionNoIn_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "conditionalExpressionNoIn"

    public static class logicalORExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "logicalORExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:289:1: logicalORExpression : logicalANDExpression ( ( LT )* '||' ( LT )* logicalANDExpression )* ;
    public final JavaScriptParser.logicalORExpression_return logicalORExpression() throws RecognitionException {
        JavaScriptParser.logicalORExpression_return retval = new JavaScriptParser.logicalORExpression_return();
        retval.start = input.LT(1);
        int logicalORExpression_StartIndex = input.index();
        Object root_0 = null;

        Token LT321=null;
        Token string_literal322=null;
        Token LT323=null;
        JavaScriptParser.logicalANDExpression_return logicalANDExpression320 = null;

        JavaScriptParser.logicalANDExpression_return logicalANDExpression324 = null;


        Object LT321_tree=null;
        Object string_literal322_tree=null;
        Object LT323_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 57) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:290:3: ( logicalANDExpression ( ( LT )* '||' ( LT )* logicalANDExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:290:5: logicalANDExpression ( ( LT )* '||' ( LT )* logicalANDExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_logicalANDExpression_in_logicalORExpression1938);
            logicalANDExpression320=logicalANDExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, logicalANDExpression320.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:290:26: ( ( LT )* '||' ( LT )* logicalANDExpression )*
            loop159:
            do {
                int alt159=2;
                alt159 = dfa159.predict(input);
                switch (alt159) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:290:27: ( LT )* '||' ( LT )* logicalANDExpression
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:290:29: ( LT )*
            	    loop157:
            	    do {
            	        int alt157=2;
            	        int LA157_0 = input.LA(1);

            	        if ( (LA157_0==LT) ) {
            	            alt157=1;
            	        }


            	        switch (alt157) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT321=(Token)match(input,LT,FOLLOW_LT_in_logicalORExpression1941); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop157;
            	        }
            	    } while (true);

            	    string_literal322=(Token)match(input,74,FOLLOW_74_in_logicalORExpression1945); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal322_tree = (Object)adaptor.create(string_literal322);
            	    adaptor.addChild(root_0, string_literal322_tree);
            	    }
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:290:39: ( LT )*
            	    loop158:
            	    do {
            	        int alt158=2;
            	        int LA158_0 = input.LA(1);

            	        if ( (LA158_0==LT) ) {
            	            alt158=1;
            	        }


            	        switch (alt158) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT323=(Token)match(input,LT,FOLLOW_LT_in_logicalORExpression1947); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop158;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_logicalANDExpression_in_logicalORExpression1951);
            	    logicalANDExpression324=logicalANDExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, logicalANDExpression324.getTree());

            	    }
            	    break;

            	default :
            	    break loop159;
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
            if ( state.backtracking>0 ) { memoize(input, 57, logicalORExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "logicalORExpression"

    public static class logicalORExpressionNoIn_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "logicalORExpressionNoIn"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:293:1: logicalORExpressionNoIn : logicalANDExpressionNoIn ( ( LT )* '||' ( LT )* logicalANDExpressionNoIn )* ;
    public final JavaScriptParser.logicalORExpressionNoIn_return logicalORExpressionNoIn() throws RecognitionException {
        JavaScriptParser.logicalORExpressionNoIn_return retval = new JavaScriptParser.logicalORExpressionNoIn_return();
        retval.start = input.LT(1);
        int logicalORExpressionNoIn_StartIndex = input.index();
        Object root_0 = null;

        Token LT326=null;
        Token string_literal327=null;
        Token LT328=null;
        JavaScriptParser.logicalANDExpressionNoIn_return logicalANDExpressionNoIn325 = null;

        JavaScriptParser.logicalANDExpressionNoIn_return logicalANDExpressionNoIn329 = null;


        Object LT326_tree=null;
        Object string_literal327_tree=null;
        Object LT328_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 58) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:294:3: ( logicalANDExpressionNoIn ( ( LT )* '||' ( LT )* logicalANDExpressionNoIn )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:294:5: logicalANDExpressionNoIn ( ( LT )* '||' ( LT )* logicalANDExpressionNoIn )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_logicalANDExpressionNoIn_in_logicalORExpressionNoIn1968);
            logicalANDExpressionNoIn325=logicalANDExpressionNoIn();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, logicalANDExpressionNoIn325.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:294:30: ( ( LT )* '||' ( LT )* logicalANDExpressionNoIn )*
            loop162:
            do {
                int alt162=2;
                alt162 = dfa162.predict(input);
                switch (alt162) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:294:31: ( LT )* '||' ( LT )* logicalANDExpressionNoIn
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:294:33: ( LT )*
            	    loop160:
            	    do {
            	        int alt160=2;
            	        int LA160_0 = input.LA(1);

            	        if ( (LA160_0==LT) ) {
            	            alt160=1;
            	        }


            	        switch (alt160) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT326=(Token)match(input,LT,FOLLOW_LT_in_logicalORExpressionNoIn1971); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop160;
            	        }
            	    } while (true);

            	    string_literal327=(Token)match(input,74,FOLLOW_74_in_logicalORExpressionNoIn1975); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal327_tree = (Object)adaptor.create(string_literal327);
            	    adaptor.addChild(root_0, string_literal327_tree);
            	    }
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:294:43: ( LT )*
            	    loop161:
            	    do {
            	        int alt161=2;
            	        int LA161_0 = input.LA(1);

            	        if ( (LA161_0==LT) ) {
            	            alt161=1;
            	        }


            	        switch (alt161) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT328=(Token)match(input,LT,FOLLOW_LT_in_logicalORExpressionNoIn1977); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop161;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_logicalANDExpressionNoIn_in_logicalORExpressionNoIn1981);
            	    logicalANDExpressionNoIn329=logicalANDExpressionNoIn();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, logicalANDExpressionNoIn329.getTree());

            	    }
            	    break;

            	default :
            	    break loop162;
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
            if ( state.backtracking>0 ) { memoize(input, 58, logicalORExpressionNoIn_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "logicalORExpressionNoIn"

    public static class logicalANDExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "logicalANDExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:297:1: logicalANDExpression : bitwiseORExpression ( ( LT )* '&&' ( LT )* bitwiseORExpression )* ;
    public final JavaScriptParser.logicalANDExpression_return logicalANDExpression() throws RecognitionException {
        JavaScriptParser.logicalANDExpression_return retval = new JavaScriptParser.logicalANDExpression_return();
        retval.start = input.LT(1);
        int logicalANDExpression_StartIndex = input.index();
        Object root_0 = null;

        Token LT331=null;
        Token string_literal332=null;
        Token LT333=null;
        JavaScriptParser.bitwiseORExpression_return bitwiseORExpression330 = null;

        JavaScriptParser.bitwiseORExpression_return bitwiseORExpression334 = null;


        Object LT331_tree=null;
        Object string_literal332_tree=null;
        Object LT333_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 59) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:298:3: ( bitwiseORExpression ( ( LT )* '&&' ( LT )* bitwiseORExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:298:5: bitwiseORExpression ( ( LT )* '&&' ( LT )* bitwiseORExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_bitwiseORExpression_in_logicalANDExpression1998);
            bitwiseORExpression330=bitwiseORExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bitwiseORExpression330.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:298:25: ( ( LT )* '&&' ( LT )* bitwiseORExpression )*
            loop165:
            do {
                int alt165=2;
                alt165 = dfa165.predict(input);
                switch (alt165) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:298:26: ( LT )* '&&' ( LT )* bitwiseORExpression
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:298:28: ( LT )*
            	    loop163:
            	    do {
            	        int alt163=2;
            	        int LA163_0 = input.LA(1);

            	        if ( (LA163_0==LT) ) {
            	            alt163=1;
            	        }


            	        switch (alt163) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT331=(Token)match(input,LT,FOLLOW_LT_in_logicalANDExpression2001); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop163;
            	        }
            	    } while (true);

            	    string_literal332=(Token)match(input,75,FOLLOW_75_in_logicalANDExpression2005); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal332_tree = (Object)adaptor.create(string_literal332);
            	    adaptor.addChild(root_0, string_literal332_tree);
            	    }
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:298:38: ( LT )*
            	    loop164:
            	    do {
            	        int alt164=2;
            	        int LA164_0 = input.LA(1);

            	        if ( (LA164_0==LT) ) {
            	            alt164=1;
            	        }


            	        switch (alt164) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT333=(Token)match(input,LT,FOLLOW_LT_in_logicalANDExpression2007); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop164;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_bitwiseORExpression_in_logicalANDExpression2011);
            	    bitwiseORExpression334=bitwiseORExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, bitwiseORExpression334.getTree());

            	    }
            	    break;

            	default :
            	    break loop165;
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
            if ( state.backtracking>0 ) { memoize(input, 59, logicalANDExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "logicalANDExpression"

    public static class logicalANDExpressionNoIn_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "logicalANDExpressionNoIn"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:301:1: logicalANDExpressionNoIn : bitwiseORExpressionNoIn ( ( LT )* '&&' ( LT )* bitwiseORExpressionNoIn )* ;
    public final JavaScriptParser.logicalANDExpressionNoIn_return logicalANDExpressionNoIn() throws RecognitionException {
        JavaScriptParser.logicalANDExpressionNoIn_return retval = new JavaScriptParser.logicalANDExpressionNoIn_return();
        retval.start = input.LT(1);
        int logicalANDExpressionNoIn_StartIndex = input.index();
        Object root_0 = null;

        Token LT336=null;
        Token string_literal337=null;
        Token LT338=null;
        JavaScriptParser.bitwiseORExpressionNoIn_return bitwiseORExpressionNoIn335 = null;

        JavaScriptParser.bitwiseORExpressionNoIn_return bitwiseORExpressionNoIn339 = null;


        Object LT336_tree=null;
        Object string_literal337_tree=null;
        Object LT338_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 60) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:302:3: ( bitwiseORExpressionNoIn ( ( LT )* '&&' ( LT )* bitwiseORExpressionNoIn )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:302:5: bitwiseORExpressionNoIn ( ( LT )* '&&' ( LT )* bitwiseORExpressionNoIn )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_bitwiseORExpressionNoIn_in_logicalANDExpressionNoIn2028);
            bitwiseORExpressionNoIn335=bitwiseORExpressionNoIn();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bitwiseORExpressionNoIn335.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:302:29: ( ( LT )* '&&' ( LT )* bitwiseORExpressionNoIn )*
            loop168:
            do {
                int alt168=2;
                alt168 = dfa168.predict(input);
                switch (alt168) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:302:30: ( LT )* '&&' ( LT )* bitwiseORExpressionNoIn
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:302:32: ( LT )*
            	    loop166:
            	    do {
            	        int alt166=2;
            	        int LA166_0 = input.LA(1);

            	        if ( (LA166_0==LT) ) {
            	            alt166=1;
            	        }


            	        switch (alt166) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT336=(Token)match(input,LT,FOLLOW_LT_in_logicalANDExpressionNoIn2031); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop166;
            	        }
            	    } while (true);

            	    string_literal337=(Token)match(input,75,FOLLOW_75_in_logicalANDExpressionNoIn2035); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal337_tree = (Object)adaptor.create(string_literal337);
            	    adaptor.addChild(root_0, string_literal337_tree);
            	    }
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:302:42: ( LT )*
            	    loop167:
            	    do {
            	        int alt167=2;
            	        int LA167_0 = input.LA(1);

            	        if ( (LA167_0==LT) ) {
            	            alt167=1;
            	        }


            	        switch (alt167) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT338=(Token)match(input,LT,FOLLOW_LT_in_logicalANDExpressionNoIn2037); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop167;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_bitwiseORExpressionNoIn_in_logicalANDExpressionNoIn2041);
            	    bitwiseORExpressionNoIn339=bitwiseORExpressionNoIn();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, bitwiseORExpressionNoIn339.getTree());

            	    }
            	    break;

            	default :
            	    break loop168;
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
            if ( state.backtracking>0 ) { memoize(input, 60, logicalANDExpressionNoIn_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "logicalANDExpressionNoIn"

    public static class bitwiseORExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "bitwiseORExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:305:1: bitwiseORExpression : bitwiseXORExpression ( ( LT )* '|' ( LT )* bitwiseXORExpression )* ;
    public final JavaScriptParser.bitwiseORExpression_return bitwiseORExpression() throws RecognitionException {
        JavaScriptParser.bitwiseORExpression_return retval = new JavaScriptParser.bitwiseORExpression_return();
        retval.start = input.LT(1);
        int bitwiseORExpression_StartIndex = input.index();
        Object root_0 = null;

        Token LT341=null;
        Token char_literal342=null;
        Token LT343=null;
        JavaScriptParser.bitwiseXORExpression_return bitwiseXORExpression340 = null;

        JavaScriptParser.bitwiseXORExpression_return bitwiseXORExpression344 = null;


        Object LT341_tree=null;
        Object char_literal342_tree=null;
        Object LT343_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 61) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:306:3: ( bitwiseXORExpression ( ( LT )* '|' ( LT )* bitwiseXORExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:306:5: bitwiseXORExpression ( ( LT )* '|' ( LT )* bitwiseXORExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_bitwiseXORExpression_in_bitwiseORExpression2058);
            bitwiseXORExpression340=bitwiseXORExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bitwiseXORExpression340.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:306:26: ( ( LT )* '|' ( LT )* bitwiseXORExpression )*
            loop171:
            do {
                int alt171=2;
                alt171 = dfa171.predict(input);
                switch (alt171) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:306:27: ( LT )* '|' ( LT )* bitwiseXORExpression
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:306:29: ( LT )*
            	    loop169:
            	    do {
            	        int alt169=2;
            	        int LA169_0 = input.LA(1);

            	        if ( (LA169_0==LT) ) {
            	            alt169=1;
            	        }


            	        switch (alt169) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT341=(Token)match(input,LT,FOLLOW_LT_in_bitwiseORExpression2061); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop169;
            	        }
            	    } while (true);

            	    char_literal342=(Token)match(input,76,FOLLOW_76_in_bitwiseORExpression2065); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal342_tree = (Object)adaptor.create(char_literal342);
            	    adaptor.addChild(root_0, char_literal342_tree);
            	    }
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:306:38: ( LT )*
            	    loop170:
            	    do {
            	        int alt170=2;
            	        int LA170_0 = input.LA(1);

            	        if ( (LA170_0==LT) ) {
            	            alt170=1;
            	        }


            	        switch (alt170) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT343=(Token)match(input,LT,FOLLOW_LT_in_bitwiseORExpression2067); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop170;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_bitwiseXORExpression_in_bitwiseORExpression2071);
            	    bitwiseXORExpression344=bitwiseXORExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, bitwiseXORExpression344.getTree());

            	    }
            	    break;

            	default :
            	    break loop171;
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
            if ( state.backtracking>0 ) { memoize(input, 61, bitwiseORExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "bitwiseORExpression"

    public static class bitwiseORExpressionNoIn_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "bitwiseORExpressionNoIn"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:309:1: bitwiseORExpressionNoIn : bitwiseXORExpressionNoIn ( ( LT )* '|' ( LT )* bitwiseXORExpressionNoIn )* ;
    public final JavaScriptParser.bitwiseORExpressionNoIn_return bitwiseORExpressionNoIn() throws RecognitionException {
        JavaScriptParser.bitwiseORExpressionNoIn_return retval = new JavaScriptParser.bitwiseORExpressionNoIn_return();
        retval.start = input.LT(1);
        int bitwiseORExpressionNoIn_StartIndex = input.index();
        Object root_0 = null;

        Token LT346=null;
        Token char_literal347=null;
        Token LT348=null;
        JavaScriptParser.bitwiseXORExpressionNoIn_return bitwiseXORExpressionNoIn345 = null;

        JavaScriptParser.bitwiseXORExpressionNoIn_return bitwiseXORExpressionNoIn349 = null;


        Object LT346_tree=null;
        Object char_literal347_tree=null;
        Object LT348_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 62) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:310:3: ( bitwiseXORExpressionNoIn ( ( LT )* '|' ( LT )* bitwiseXORExpressionNoIn )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:310:5: bitwiseXORExpressionNoIn ( ( LT )* '|' ( LT )* bitwiseXORExpressionNoIn )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_bitwiseXORExpressionNoIn_in_bitwiseORExpressionNoIn2088);
            bitwiseXORExpressionNoIn345=bitwiseXORExpressionNoIn();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bitwiseXORExpressionNoIn345.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:310:30: ( ( LT )* '|' ( LT )* bitwiseXORExpressionNoIn )*
            loop174:
            do {
                int alt174=2;
                alt174 = dfa174.predict(input);
                switch (alt174) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:310:31: ( LT )* '|' ( LT )* bitwiseXORExpressionNoIn
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:310:33: ( LT )*
            	    loop172:
            	    do {
            	        int alt172=2;
            	        int LA172_0 = input.LA(1);

            	        if ( (LA172_0==LT) ) {
            	            alt172=1;
            	        }


            	        switch (alt172) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT346=(Token)match(input,LT,FOLLOW_LT_in_bitwiseORExpressionNoIn2091); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop172;
            	        }
            	    } while (true);

            	    char_literal347=(Token)match(input,76,FOLLOW_76_in_bitwiseORExpressionNoIn2095); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal347_tree = (Object)adaptor.create(char_literal347);
            	    adaptor.addChild(root_0, char_literal347_tree);
            	    }
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:310:42: ( LT )*
            	    loop173:
            	    do {
            	        int alt173=2;
            	        int LA173_0 = input.LA(1);

            	        if ( (LA173_0==LT) ) {
            	            alt173=1;
            	        }


            	        switch (alt173) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT348=(Token)match(input,LT,FOLLOW_LT_in_bitwiseORExpressionNoIn2097); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop173;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_bitwiseXORExpressionNoIn_in_bitwiseORExpressionNoIn2101);
            	    bitwiseXORExpressionNoIn349=bitwiseXORExpressionNoIn();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, bitwiseXORExpressionNoIn349.getTree());

            	    }
            	    break;

            	default :
            	    break loop174;
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
            if ( state.backtracking>0 ) { memoize(input, 62, bitwiseORExpressionNoIn_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "bitwiseORExpressionNoIn"

    public static class bitwiseXORExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "bitwiseXORExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:313:1: bitwiseXORExpression : bitwiseANDExpression ( ( LT )* '^' ( LT )* bitwiseANDExpression )* ;
    public final JavaScriptParser.bitwiseXORExpression_return bitwiseXORExpression() throws RecognitionException {
        JavaScriptParser.bitwiseXORExpression_return retval = new JavaScriptParser.bitwiseXORExpression_return();
        retval.start = input.LT(1);
        int bitwiseXORExpression_StartIndex = input.index();
        Object root_0 = null;

        Token LT351=null;
        Token char_literal352=null;
        Token LT353=null;
        JavaScriptParser.bitwiseANDExpression_return bitwiseANDExpression350 = null;

        JavaScriptParser.bitwiseANDExpression_return bitwiseANDExpression354 = null;


        Object LT351_tree=null;
        Object char_literal352_tree=null;
        Object LT353_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 63) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:314:3: ( bitwiseANDExpression ( ( LT )* '^' ( LT )* bitwiseANDExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:314:5: bitwiseANDExpression ( ( LT )* '^' ( LT )* bitwiseANDExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_bitwiseANDExpression_in_bitwiseXORExpression2118);
            bitwiseANDExpression350=bitwiseANDExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bitwiseANDExpression350.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:314:26: ( ( LT )* '^' ( LT )* bitwiseANDExpression )*
            loop177:
            do {
                int alt177=2;
                alt177 = dfa177.predict(input);
                switch (alt177) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:314:27: ( LT )* '^' ( LT )* bitwiseANDExpression
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:314:29: ( LT )*
            	    loop175:
            	    do {
            	        int alt175=2;
            	        int LA175_0 = input.LA(1);

            	        if ( (LA175_0==LT) ) {
            	            alt175=1;
            	        }


            	        switch (alt175) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT351=(Token)match(input,LT,FOLLOW_LT_in_bitwiseXORExpression2121); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop175;
            	        }
            	    } while (true);

            	    char_literal352=(Token)match(input,77,FOLLOW_77_in_bitwiseXORExpression2125); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal352_tree = (Object)adaptor.create(char_literal352);
            	    adaptor.addChild(root_0, char_literal352_tree);
            	    }
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:314:38: ( LT )*
            	    loop176:
            	    do {
            	        int alt176=2;
            	        int LA176_0 = input.LA(1);

            	        if ( (LA176_0==LT) ) {
            	            alt176=1;
            	        }


            	        switch (alt176) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT353=(Token)match(input,LT,FOLLOW_LT_in_bitwiseXORExpression2127); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop176;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_bitwiseANDExpression_in_bitwiseXORExpression2131);
            	    bitwiseANDExpression354=bitwiseANDExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, bitwiseANDExpression354.getTree());

            	    }
            	    break;

            	default :
            	    break loop177;
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
            if ( state.backtracking>0 ) { memoize(input, 63, bitwiseXORExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "bitwiseXORExpression"

    public static class bitwiseXORExpressionNoIn_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "bitwiseXORExpressionNoIn"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:317:1: bitwiseXORExpressionNoIn : bitwiseANDExpressionNoIn ( ( LT )* '^' ( LT )* bitwiseANDExpressionNoIn )* ;
    public final JavaScriptParser.bitwiseXORExpressionNoIn_return bitwiseXORExpressionNoIn() throws RecognitionException {
        JavaScriptParser.bitwiseXORExpressionNoIn_return retval = new JavaScriptParser.bitwiseXORExpressionNoIn_return();
        retval.start = input.LT(1);
        int bitwiseXORExpressionNoIn_StartIndex = input.index();
        Object root_0 = null;

        Token LT356=null;
        Token char_literal357=null;
        Token LT358=null;
        JavaScriptParser.bitwiseANDExpressionNoIn_return bitwiseANDExpressionNoIn355 = null;

        JavaScriptParser.bitwiseANDExpressionNoIn_return bitwiseANDExpressionNoIn359 = null;


        Object LT356_tree=null;
        Object char_literal357_tree=null;
        Object LT358_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 64) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:318:3: ( bitwiseANDExpressionNoIn ( ( LT )* '^' ( LT )* bitwiseANDExpressionNoIn )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:318:5: bitwiseANDExpressionNoIn ( ( LT )* '^' ( LT )* bitwiseANDExpressionNoIn )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_bitwiseANDExpressionNoIn_in_bitwiseXORExpressionNoIn2148);
            bitwiseANDExpressionNoIn355=bitwiseANDExpressionNoIn();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bitwiseANDExpressionNoIn355.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:318:30: ( ( LT )* '^' ( LT )* bitwiseANDExpressionNoIn )*
            loop180:
            do {
                int alt180=2;
                alt180 = dfa180.predict(input);
                switch (alt180) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:318:31: ( LT )* '^' ( LT )* bitwiseANDExpressionNoIn
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:318:33: ( LT )*
            	    loop178:
            	    do {
            	        int alt178=2;
            	        int LA178_0 = input.LA(1);

            	        if ( (LA178_0==LT) ) {
            	            alt178=1;
            	        }


            	        switch (alt178) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT356=(Token)match(input,LT,FOLLOW_LT_in_bitwiseXORExpressionNoIn2151); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop178;
            	        }
            	    } while (true);

            	    char_literal357=(Token)match(input,77,FOLLOW_77_in_bitwiseXORExpressionNoIn2155); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal357_tree = (Object)adaptor.create(char_literal357);
            	    adaptor.addChild(root_0, char_literal357_tree);
            	    }
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:318:42: ( LT )*
            	    loop179:
            	    do {
            	        int alt179=2;
            	        int LA179_0 = input.LA(1);

            	        if ( (LA179_0==LT) ) {
            	            alt179=1;
            	        }


            	        switch (alt179) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT358=(Token)match(input,LT,FOLLOW_LT_in_bitwiseXORExpressionNoIn2157); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop179;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_bitwiseANDExpressionNoIn_in_bitwiseXORExpressionNoIn2161);
            	    bitwiseANDExpressionNoIn359=bitwiseANDExpressionNoIn();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, bitwiseANDExpressionNoIn359.getTree());

            	    }
            	    break;

            	default :
            	    break loop180;
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
            if ( state.backtracking>0 ) { memoize(input, 64, bitwiseXORExpressionNoIn_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "bitwiseXORExpressionNoIn"

    public static class bitwiseANDExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "bitwiseANDExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:321:1: bitwiseANDExpression : equalityExpression ( ( LT )* '&' ( LT )* equalityExpression )* ;
    public final JavaScriptParser.bitwiseANDExpression_return bitwiseANDExpression() throws RecognitionException {
        JavaScriptParser.bitwiseANDExpression_return retval = new JavaScriptParser.bitwiseANDExpression_return();
        retval.start = input.LT(1);
        int bitwiseANDExpression_StartIndex = input.index();
        Object root_0 = null;

        Token LT361=null;
        Token char_literal362=null;
        Token LT363=null;
        JavaScriptParser.equalityExpression_return equalityExpression360 = null;

        JavaScriptParser.equalityExpression_return equalityExpression364 = null;


        Object LT361_tree=null;
        Object char_literal362_tree=null;
        Object LT363_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 65) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:322:3: ( equalityExpression ( ( LT )* '&' ( LT )* equalityExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:322:5: equalityExpression ( ( LT )* '&' ( LT )* equalityExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_equalityExpression_in_bitwiseANDExpression2178);
            equalityExpression360=equalityExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, equalityExpression360.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:322:24: ( ( LT )* '&' ( LT )* equalityExpression )*
            loop183:
            do {
                int alt183=2;
                alt183 = dfa183.predict(input);
                switch (alt183) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:322:25: ( LT )* '&' ( LT )* equalityExpression
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:322:27: ( LT )*
            	    loop181:
            	    do {
            	        int alt181=2;
            	        int LA181_0 = input.LA(1);

            	        if ( (LA181_0==LT) ) {
            	            alt181=1;
            	        }


            	        switch (alt181) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT361=(Token)match(input,LT,FOLLOW_LT_in_bitwiseANDExpression2181); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop181;
            	        }
            	    } while (true);

            	    char_literal362=(Token)match(input,78,FOLLOW_78_in_bitwiseANDExpression2185); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal362_tree = (Object)adaptor.create(char_literal362);
            	    adaptor.addChild(root_0, char_literal362_tree);
            	    }
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:322:36: ( LT )*
            	    loop182:
            	    do {
            	        int alt182=2;
            	        int LA182_0 = input.LA(1);

            	        if ( (LA182_0==LT) ) {
            	            alt182=1;
            	        }


            	        switch (alt182) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT363=(Token)match(input,LT,FOLLOW_LT_in_bitwiseANDExpression2187); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop182;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_equalityExpression_in_bitwiseANDExpression2191);
            	    equalityExpression364=equalityExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, equalityExpression364.getTree());

            	    }
            	    break;

            	default :
            	    break loop183;
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
            if ( state.backtracking>0 ) { memoize(input, 65, bitwiseANDExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "bitwiseANDExpression"

    public static class bitwiseANDExpressionNoIn_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "bitwiseANDExpressionNoIn"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:325:1: bitwiseANDExpressionNoIn : equalityExpressionNoIn ( ( LT )* '&' ( LT )* equalityExpressionNoIn )* ;
    public final JavaScriptParser.bitwiseANDExpressionNoIn_return bitwiseANDExpressionNoIn() throws RecognitionException {
        JavaScriptParser.bitwiseANDExpressionNoIn_return retval = new JavaScriptParser.bitwiseANDExpressionNoIn_return();
        retval.start = input.LT(1);
        int bitwiseANDExpressionNoIn_StartIndex = input.index();
        Object root_0 = null;

        Token LT366=null;
        Token char_literal367=null;
        Token LT368=null;
        JavaScriptParser.equalityExpressionNoIn_return equalityExpressionNoIn365 = null;

        JavaScriptParser.equalityExpressionNoIn_return equalityExpressionNoIn369 = null;


        Object LT366_tree=null;
        Object char_literal367_tree=null;
        Object LT368_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 66) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:326:3: ( equalityExpressionNoIn ( ( LT )* '&' ( LT )* equalityExpressionNoIn )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:326:5: equalityExpressionNoIn ( ( LT )* '&' ( LT )* equalityExpressionNoIn )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_equalityExpressionNoIn_in_bitwiseANDExpressionNoIn2208);
            equalityExpressionNoIn365=equalityExpressionNoIn();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, equalityExpressionNoIn365.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:326:28: ( ( LT )* '&' ( LT )* equalityExpressionNoIn )*
            loop186:
            do {
                int alt186=2;
                alt186 = dfa186.predict(input);
                switch (alt186) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:326:29: ( LT )* '&' ( LT )* equalityExpressionNoIn
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:326:31: ( LT )*
            	    loop184:
            	    do {
            	        int alt184=2;
            	        int LA184_0 = input.LA(1);

            	        if ( (LA184_0==LT) ) {
            	            alt184=1;
            	        }


            	        switch (alt184) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT366=(Token)match(input,LT,FOLLOW_LT_in_bitwiseANDExpressionNoIn2211); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop184;
            	        }
            	    } while (true);

            	    char_literal367=(Token)match(input,78,FOLLOW_78_in_bitwiseANDExpressionNoIn2215); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal367_tree = (Object)adaptor.create(char_literal367);
            	    adaptor.addChild(root_0, char_literal367_tree);
            	    }
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:326:40: ( LT )*
            	    loop185:
            	    do {
            	        int alt185=2;
            	        int LA185_0 = input.LA(1);

            	        if ( (LA185_0==LT) ) {
            	            alt185=1;
            	        }


            	        switch (alt185) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT368=(Token)match(input,LT,FOLLOW_LT_in_bitwiseANDExpressionNoIn2217); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop185;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_equalityExpressionNoIn_in_bitwiseANDExpressionNoIn2221);
            	    equalityExpressionNoIn369=equalityExpressionNoIn();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, equalityExpressionNoIn369.getTree());

            	    }
            	    break;

            	default :
            	    break loop186;
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
            if ( state.backtracking>0 ) { memoize(input, 66, bitwiseANDExpressionNoIn_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "bitwiseANDExpressionNoIn"

    public static class equalityExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "equalityExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:329:1: equalityExpression : relationalExpression ( ( LT )* ( '==' | '!=' | '===' | '!==' ) ( LT )* relationalExpression )* ;
    public final JavaScriptParser.equalityExpression_return equalityExpression() throws RecognitionException {
        JavaScriptParser.equalityExpression_return retval = new JavaScriptParser.equalityExpression_return();
        retval.start = input.LT(1);
        int equalityExpression_StartIndex = input.index();
        Object root_0 = null;

        Token LT371=null;
        Token set372=null;
        Token LT373=null;
        JavaScriptParser.relationalExpression_return relationalExpression370 = null;

        JavaScriptParser.relationalExpression_return relationalExpression374 = null;


        Object LT371_tree=null;
        Object set372_tree=null;
        Object LT373_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 67) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:330:3: ( relationalExpression ( ( LT )* ( '==' | '!=' | '===' | '!==' ) ( LT )* relationalExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:330:5: relationalExpression ( ( LT )* ( '==' | '!=' | '===' | '!==' ) ( LT )* relationalExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_relationalExpression_in_equalityExpression2238);
            relationalExpression370=relationalExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, relationalExpression370.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:330:26: ( ( LT )* ( '==' | '!=' | '===' | '!==' ) ( LT )* relationalExpression )*
            loop189:
            do {
                int alt189=2;
                alt189 = dfa189.predict(input);
                switch (alt189) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:330:27: ( LT )* ( '==' | '!=' | '===' | '!==' ) ( LT )* relationalExpression
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:330:29: ( LT )*
            	    loop187:
            	    do {
            	        int alt187=2;
            	        int LA187_0 = input.LA(1);

            	        if ( (LA187_0==LT) ) {
            	            alt187=1;
            	        }


            	        switch (alt187) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT371=(Token)match(input,LT,FOLLOW_LT_in_equalityExpression2241); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop187;
            	        }
            	    } while (true);

            	    set372=(Token)input.LT(1);
            	    if ( (input.LA(1)>=79 && input.LA(1)<=82) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set372));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:330:64: ( LT )*
            	    loop188:
            	    do {
            	        int alt188=2;
            	        int LA188_0 = input.LA(1);

            	        if ( (LA188_0==LT) ) {
            	            alt188=1;
            	        }


            	        switch (alt188) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT373=(Token)match(input,LT,FOLLOW_LT_in_equalityExpression2261); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop188;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_relationalExpression_in_equalityExpression2265);
            	    relationalExpression374=relationalExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, relationalExpression374.getTree());

            	    }
            	    break;

            	default :
            	    break loop189;
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
            if ( state.backtracking>0 ) { memoize(input, 67, equalityExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "equalityExpression"

    public static class equalityExpressionNoIn_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "equalityExpressionNoIn"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:333:1: equalityExpressionNoIn : relationalExpressionNoIn ( ( LT )* ( '==' | '!=' | '===' | '!==' ) ( LT )* relationalExpressionNoIn )* ;
    public final JavaScriptParser.equalityExpressionNoIn_return equalityExpressionNoIn() throws RecognitionException {
        JavaScriptParser.equalityExpressionNoIn_return retval = new JavaScriptParser.equalityExpressionNoIn_return();
        retval.start = input.LT(1);
        int equalityExpressionNoIn_StartIndex = input.index();
        Object root_0 = null;

        Token LT376=null;
        Token set377=null;
        Token LT378=null;
        JavaScriptParser.relationalExpressionNoIn_return relationalExpressionNoIn375 = null;

        JavaScriptParser.relationalExpressionNoIn_return relationalExpressionNoIn379 = null;


        Object LT376_tree=null;
        Object set377_tree=null;
        Object LT378_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 68) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:334:3: ( relationalExpressionNoIn ( ( LT )* ( '==' | '!=' | '===' | '!==' ) ( LT )* relationalExpressionNoIn )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:334:5: relationalExpressionNoIn ( ( LT )* ( '==' | '!=' | '===' | '!==' ) ( LT )* relationalExpressionNoIn )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_relationalExpressionNoIn_in_equalityExpressionNoIn2280);
            relationalExpressionNoIn375=relationalExpressionNoIn();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, relationalExpressionNoIn375.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:334:30: ( ( LT )* ( '==' | '!=' | '===' | '!==' ) ( LT )* relationalExpressionNoIn )*
            loop192:
            do {
                int alt192=2;
                alt192 = dfa192.predict(input);
                switch (alt192) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:334:31: ( LT )* ( '==' | '!=' | '===' | '!==' ) ( LT )* relationalExpressionNoIn
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:334:33: ( LT )*
            	    loop190:
            	    do {
            	        int alt190=2;
            	        int LA190_0 = input.LA(1);

            	        if ( (LA190_0==LT) ) {
            	            alt190=1;
            	        }


            	        switch (alt190) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT376=(Token)match(input,LT,FOLLOW_LT_in_equalityExpressionNoIn2283); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop190;
            	        }
            	    } while (true);

            	    set377=(Token)input.LT(1);
            	    if ( (input.LA(1)>=79 && input.LA(1)<=82) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set377));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:334:68: ( LT )*
            	    loop191:
            	    do {
            	        int alt191=2;
            	        int LA191_0 = input.LA(1);

            	        if ( (LA191_0==LT) ) {
            	            alt191=1;
            	        }


            	        switch (alt191) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT378=(Token)match(input,LT,FOLLOW_LT_in_equalityExpressionNoIn2303); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop191;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_relationalExpressionNoIn_in_equalityExpressionNoIn2307);
            	    relationalExpressionNoIn379=relationalExpressionNoIn();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, relationalExpressionNoIn379.getTree());

            	    }
            	    break;

            	default :
            	    break loop192;
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
            if ( state.backtracking>0 ) { memoize(input, 68, equalityExpressionNoIn_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "equalityExpressionNoIn"

    public static class relationalExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "relationalExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:337:1: relationalExpression : shiftExpression ( ( LT )* ( '<' | '>' | '<=' | '>=' | 'instanceof' | 'in' ) ( LT )* shiftExpression )* ;
    public final JavaScriptParser.relationalExpression_return relationalExpression() throws RecognitionException {
        JavaScriptParser.relationalExpression_return retval = new JavaScriptParser.relationalExpression_return();
        retval.start = input.LT(1);
        int relationalExpression_StartIndex = input.index();
        Object root_0 = null;

        Token LT381=null;
        Token set382=null;
        Token LT383=null;
        JavaScriptParser.shiftExpression_return shiftExpression380 = null;

        JavaScriptParser.shiftExpression_return shiftExpression384 = null;


        Object LT381_tree=null;
        Object set382_tree=null;
        Object LT383_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 69) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:338:3: ( shiftExpression ( ( LT )* ( '<' | '>' | '<=' | '>=' | 'instanceof' | 'in' ) ( LT )* shiftExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:338:5: shiftExpression ( ( LT )* ( '<' | '>' | '<=' | '>=' | 'instanceof' | 'in' ) ( LT )* shiftExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_shiftExpression_in_relationalExpression2324);
            shiftExpression380=shiftExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, shiftExpression380.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:338:21: ( ( LT )* ( '<' | '>' | '<=' | '>=' | 'instanceof' | 'in' ) ( LT )* shiftExpression )*
            loop195:
            do {
                int alt195=2;
                alt195 = dfa195.predict(input);
                switch (alt195) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:338:22: ( LT )* ( '<' | '>' | '<=' | '>=' | 'instanceof' | 'in' ) ( LT )* shiftExpression
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:338:24: ( LT )*
            	    loop193:
            	    do {
            	        int alt193=2;
            	        int LA193_0 = input.LA(1);

            	        if ( (LA193_0==LT) ) {
            	            alt193=1;
            	        }


            	        switch (alt193) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT381=(Token)match(input,LT,FOLLOW_LT_in_relationalExpression2327); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop193;
            	        }
            	    } while (true);

            	    set382=(Token)input.LT(1);
            	    if ( input.LA(1)==45||(input.LA(1)>=83 && input.LA(1)<=87) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set382));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:338:77: ( LT )*
            	    loop194:
            	    do {
            	        int alt194=2;
            	        int LA194_0 = input.LA(1);

            	        if ( (LA194_0==LT) ) {
            	            alt194=1;
            	        }


            	        switch (alt194) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT383=(Token)match(input,LT,FOLLOW_LT_in_relationalExpression2355); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop194;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_shiftExpression_in_relationalExpression2359);
            	    shiftExpression384=shiftExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, shiftExpression384.getTree());

            	    }
            	    break;

            	default :
            	    break loop195;
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
            if ( state.backtracking>0 ) { memoize(input, 69, relationalExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "relationalExpression"

    public static class relationalExpressionNoIn_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "relationalExpressionNoIn"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:341:1: relationalExpressionNoIn : shiftExpression ( ( LT )* ( '<' | '>' | '<=' | '>=' | 'instanceof' ) ( LT )* shiftExpression )* ;
    public final JavaScriptParser.relationalExpressionNoIn_return relationalExpressionNoIn() throws RecognitionException {
        JavaScriptParser.relationalExpressionNoIn_return retval = new JavaScriptParser.relationalExpressionNoIn_return();
        retval.start = input.LT(1);
        int relationalExpressionNoIn_StartIndex = input.index();
        Object root_0 = null;

        Token LT386=null;
        Token set387=null;
        Token LT388=null;
        JavaScriptParser.shiftExpression_return shiftExpression385 = null;

        JavaScriptParser.shiftExpression_return shiftExpression389 = null;


        Object LT386_tree=null;
        Object set387_tree=null;
        Object LT388_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 70) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:342:3: ( shiftExpression ( ( LT )* ( '<' | '>' | '<=' | '>=' | 'instanceof' ) ( LT )* shiftExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:342:5: shiftExpression ( ( LT )* ( '<' | '>' | '<=' | '>=' | 'instanceof' ) ( LT )* shiftExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_shiftExpression_in_relationalExpressionNoIn2374);
            shiftExpression385=shiftExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, shiftExpression385.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:342:21: ( ( LT )* ( '<' | '>' | '<=' | '>=' | 'instanceof' ) ( LT )* shiftExpression )*
            loop198:
            do {
                int alt198=2;
                alt198 = dfa198.predict(input);
                switch (alt198) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:342:22: ( LT )* ( '<' | '>' | '<=' | '>=' | 'instanceof' ) ( LT )* shiftExpression
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:342:24: ( LT )*
            	    loop196:
            	    do {
            	        int alt196=2;
            	        int LA196_0 = input.LA(1);

            	        if ( (LA196_0==LT) ) {
            	            alt196=1;
            	        }


            	        switch (alt196) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT386=(Token)match(input,LT,FOLLOW_LT_in_relationalExpressionNoIn2377); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop196;
            	        }
            	    } while (true);

            	    set387=(Token)input.LT(1);
            	    if ( (input.LA(1)>=83 && input.LA(1)<=87) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set387));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:342:70: ( LT )*
            	    loop197:
            	    do {
            	        int alt197=2;
            	        int LA197_0 = input.LA(1);

            	        if ( (LA197_0==LT) ) {
            	            alt197=1;
            	        }


            	        switch (alt197) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT388=(Token)match(input,LT,FOLLOW_LT_in_relationalExpressionNoIn2401); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop197;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_shiftExpression_in_relationalExpressionNoIn2405);
            	    shiftExpression389=shiftExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, shiftExpression389.getTree());

            	    }
            	    break;

            	default :
            	    break loop198;
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
            if ( state.backtracking>0 ) { memoize(input, 70, relationalExpressionNoIn_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "relationalExpressionNoIn"

    public static class shiftExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "shiftExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:345:1: shiftExpression : additiveExpression ( ( LT )* ( '<<' | '>>' | '>>>' ) ( LT )* additiveExpression )* ;
    public final JavaScriptParser.shiftExpression_return shiftExpression() throws RecognitionException {
        JavaScriptParser.shiftExpression_return retval = new JavaScriptParser.shiftExpression_return();
        retval.start = input.LT(1);
        int shiftExpression_StartIndex = input.index();
        Object root_0 = null;

        Token LT391=null;
        Token set392=null;
        Token LT393=null;
        JavaScriptParser.additiveExpression_return additiveExpression390 = null;

        JavaScriptParser.additiveExpression_return additiveExpression394 = null;


        Object LT391_tree=null;
        Object set392_tree=null;
        Object LT393_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 71) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:346:3: ( additiveExpression ( ( LT )* ( '<<' | '>>' | '>>>' ) ( LT )* additiveExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:346:5: additiveExpression ( ( LT )* ( '<<' | '>>' | '>>>' ) ( LT )* additiveExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_additiveExpression_in_shiftExpression2420);
            additiveExpression390=additiveExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, additiveExpression390.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:346:24: ( ( LT )* ( '<<' | '>>' | '>>>' ) ( LT )* additiveExpression )*
            loop201:
            do {
                int alt201=2;
                alt201 = dfa201.predict(input);
                switch (alt201) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:346:25: ( LT )* ( '<<' | '>>' | '>>>' ) ( LT )* additiveExpression
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:346:27: ( LT )*
            	    loop199:
            	    do {
            	        int alt199=2;
            	        int LA199_0 = input.LA(1);

            	        if ( (LA199_0==LT) ) {
            	            alt199=1;
            	        }


            	        switch (alt199) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT391=(Token)match(input,LT,FOLLOW_LT_in_shiftExpression2423); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop199;
            	        }
            	    } while (true);

            	    set392=(Token)input.LT(1);
            	    if ( (input.LA(1)>=88 && input.LA(1)<=90) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set392));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:346:54: ( LT )*
            	    loop200:
            	    do {
            	        int alt200=2;
            	        int LA200_0 = input.LA(1);

            	        if ( (LA200_0==LT) ) {
            	            alt200=1;
            	        }


            	        switch (alt200) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT393=(Token)match(input,LT,FOLLOW_LT_in_shiftExpression2439); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop200;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_additiveExpression_in_shiftExpression2443);
            	    additiveExpression394=additiveExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, additiveExpression394.getTree());

            	    }
            	    break;

            	default :
            	    break loop201;
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
            if ( state.backtracking>0 ) { memoize(input, 71, shiftExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "shiftExpression"

    public static class additiveExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "additiveExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:349:1: additiveExpression : multiplicativeExpression ( ( LT )* ( '+' | '-' ) ( LT )* multiplicativeExpression )* ;
    public final JavaScriptParser.additiveExpression_return additiveExpression() throws RecognitionException {
        JavaScriptParser.additiveExpression_return retval = new JavaScriptParser.additiveExpression_return();
        retval.start = input.LT(1);
        int additiveExpression_StartIndex = input.index();
        Object root_0 = null;

        Token LT396=null;
        Token set397=null;
        Token LT398=null;
        JavaScriptParser.multiplicativeExpression_return multiplicativeExpression395 = null;

        JavaScriptParser.multiplicativeExpression_return multiplicativeExpression399 = null;


        Object LT396_tree=null;
        Object set397_tree=null;
        Object LT398_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 72) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:350:3: ( multiplicativeExpression ( ( LT )* ( '+' | '-' ) ( LT )* multiplicativeExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:350:5: multiplicativeExpression ( ( LT )* ( '+' | '-' ) ( LT )* multiplicativeExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression2458);
            multiplicativeExpression395=multiplicativeExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, multiplicativeExpression395.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:350:30: ( ( LT )* ( '+' | '-' ) ( LT )* multiplicativeExpression )*
            loop204:
            do {
                int alt204=2;
                alt204 = dfa204.predict(input);
                switch (alt204) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:350:31: ( LT )* ( '+' | '-' ) ( LT )* multiplicativeExpression
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:350:33: ( LT )*
            	    loop202:
            	    do {
            	        int alt202=2;
            	        int LA202_0 = input.LA(1);

            	        if ( (LA202_0==LT) ) {
            	            alt202=1;
            	        }


            	        switch (alt202) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT396=(Token)match(input,LT,FOLLOW_LT_in_additiveExpression2461); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop202;
            	        }
            	    } while (true);

            	    set397=(Token)input.LT(1);
            	    if ( (input.LA(1)>=91 && input.LA(1)<=92) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set397));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:350:50: ( LT )*
            	    loop203:
            	    do {
            	        int alt203=2;
            	        int LA203_0 = input.LA(1);

            	        if ( (LA203_0==LT) ) {
            	            alt203=1;
            	        }


            	        switch (alt203) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT398=(Token)match(input,LT,FOLLOW_LT_in_additiveExpression2473); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop203;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression2477);
            	    multiplicativeExpression399=multiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, multiplicativeExpression399.getTree());

            	    }
            	    break;

            	default :
            	    break loop204;
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
            if ( state.backtracking>0 ) { memoize(input, 72, additiveExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "additiveExpression"

    public static class multiplicativeExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "multiplicativeExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:353:1: multiplicativeExpression : unaryExpression ( ( LT )* ( '*' | '/' | '%' ) ( LT )* unaryExpression )* ;
    public final JavaScriptParser.multiplicativeExpression_return multiplicativeExpression() throws RecognitionException {
        JavaScriptParser.multiplicativeExpression_return retval = new JavaScriptParser.multiplicativeExpression_return();
        retval.start = input.LT(1);
        int multiplicativeExpression_StartIndex = input.index();
        Object root_0 = null;

        Token LT401=null;
        Token set402=null;
        Token LT403=null;
        JavaScriptParser.unaryExpression_return unaryExpression400 = null;

        JavaScriptParser.unaryExpression_return unaryExpression404 = null;


        Object LT401_tree=null;
        Object set402_tree=null;
        Object LT403_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 73) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:354:3: ( unaryExpression ( ( LT )* ( '*' | '/' | '%' ) ( LT )* unaryExpression )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:354:5: unaryExpression ( ( LT )* ( '*' | '/' | '%' ) ( LT )* unaryExpression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression2492);
            unaryExpression400=unaryExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression400.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:354:21: ( ( LT )* ( '*' | '/' | '%' ) ( LT )* unaryExpression )*
            loop207:
            do {
                int alt207=2;
                alt207 = dfa207.predict(input);
                switch (alt207) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:354:22: ( LT )* ( '*' | '/' | '%' ) ( LT )* unaryExpression
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:354:24: ( LT )*
            	    loop205:
            	    do {
            	        int alt205=2;
            	        int LA205_0 = input.LA(1);

            	        if ( (LA205_0==LT) ) {
            	            alt205=1;
            	        }


            	        switch (alt205) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT401=(Token)match(input,LT,FOLLOW_LT_in_multiplicativeExpression2495); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop205;
            	        }
            	    } while (true);

            	    set402=(Token)input.LT(1);
            	    if ( (input.LA(1)>=93 && input.LA(1)<=95) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set402));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:354:47: ( LT )*
            	    loop206:
            	    do {
            	        int alt206=2;
            	        int LA206_0 = input.LA(1);

            	        if ( (LA206_0==LT) ) {
            	            alt206=1;
            	        }


            	        switch (alt206) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT403=(Token)match(input,LT,FOLLOW_LT_in_multiplicativeExpression2511); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop206;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression2515);
            	    unaryExpression404=unaryExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression404.getTree());

            	    }
            	    break;

            	default :
            	    break loop207;
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
            if ( state.backtracking>0 ) { memoize(input, 73, multiplicativeExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "multiplicativeExpression"

    public static class unaryExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "unaryExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:357:1: unaryExpression : ( postfixExpression | ( 'delete' | 'void' | 'typeof' | '++' | '--' | '+' | '-' | '~' | '!' ) unaryExpression );
    public final JavaScriptParser.unaryExpression_return unaryExpression() throws RecognitionException {
        JavaScriptParser.unaryExpression_return retval = new JavaScriptParser.unaryExpression_return();
        retval.start = input.LT(1);
        int unaryExpression_StartIndex = input.index();
        Object root_0 = null;

        Token set406=null;
        JavaScriptParser.postfixExpression_return postfixExpression405 = null;

        JavaScriptParser.unaryExpression_return unaryExpression407 = null;


        Object set406_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 74) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:358:3: ( postfixExpression | ( 'delete' | 'void' | 'typeof' | '++' | '--' | '+' | '-' | '~' | '!' ) unaryExpression )
            int alt208=2;
            int LA208_0 = input.LA(1);

            if ( ((LA208_0>=Identifier && LA208_0<=NumericLiteral)||(LA208_0>=31 && LA208_0<=32)||LA208_0==35||(LA208_0>=58 && LA208_0<=59)||(LA208_0>=103 && LA208_0<=106)) ) {
                alt208=1;
            }
            else if ( ((LA208_0>=91 && LA208_0<=92)||(LA208_0>=96 && LA208_0<=102)) ) {
                alt208=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 208, 0, input);

                throw nvae;
            }
            switch (alt208) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:358:5: postfixExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_postfixExpression_in_unaryExpression2530);
                    postfixExpression405=postfixExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, postfixExpression405.getTree());

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:359:5: ( 'delete' | 'void' | 'typeof' | '++' | '--' | '+' | '-' | '~' | '!' ) unaryExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    set406=(Token)input.LT(1);
                    if ( (input.LA(1)>=91 && input.LA(1)<=92)||(input.LA(1)>=96 && input.LA(1)<=102) ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set406));
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression2572);
                    unaryExpression407=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression407.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 74, unaryExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "unaryExpression"

    public static class postfixExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "postfixExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:362:1: postfixExpression : leftHandSideExpression ( '++' | '--' )? ;
    public final JavaScriptParser.postfixExpression_return postfixExpression() throws RecognitionException {
        JavaScriptParser.postfixExpression_return retval = new JavaScriptParser.postfixExpression_return();
        retval.start = input.LT(1);
        int postfixExpression_StartIndex = input.index();
        Object root_0 = null;

        Token set409=null;
        JavaScriptParser.leftHandSideExpression_return leftHandSideExpression408 = null;


        Object set409_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 75) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:363:3: ( leftHandSideExpression ( '++' | '--' )? )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:363:5: leftHandSideExpression ( '++' | '--' )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_leftHandSideExpression_in_postfixExpression2587);
            leftHandSideExpression408=leftHandSideExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, leftHandSideExpression408.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:363:28: ( '++' | '--' )?
            int alt209=2;
            int LA209_0 = input.LA(1);

            if ( ((LA209_0>=99 && LA209_0<=100)) ) {
                alt209=1;
            }
            switch (alt209) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:
                    {
                    set409=(Token)input.LT(1);
                    if ( (input.LA(1)>=99 && input.LA(1)<=100) ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set409));
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
            if ( state.backtracking>0 ) { memoize(input, 75, postfixExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "postfixExpression"

    public static class primaryExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "primaryExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:366:1: primaryExpression : ( 'this' | Identifier | literal | arrayLiteral | objectLiteral | '(' ( LT )* expression ( LT )* ')' );
    public final JavaScriptParser.primaryExpression_return primaryExpression() throws RecognitionException {
        JavaScriptParser.primaryExpression_return retval = new JavaScriptParser.primaryExpression_return();
        retval.start = input.LT(1);
        int primaryExpression_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal410=null;
        Token Identifier411=null;
        Token char_literal415=null;
        Token LT416=null;
        Token LT418=null;
        Token char_literal419=null;
        JavaScriptParser.literal_return literal412 = null;

        JavaScriptParser.arrayLiteral_return arrayLiteral413 = null;

        JavaScriptParser.objectLiteral_return objectLiteral414 = null;

        JavaScriptParser.expression_return expression417 = null;


        Object string_literal410_tree=null;
        Object Identifier411_tree=null;
        Object char_literal415_tree=null;
        Object LT416_tree=null;
        Object LT418_tree=null;
        Object char_literal419_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 76) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:367:3: ( 'this' | Identifier | literal | arrayLiteral | objectLiteral | '(' ( LT )* expression ( LT )* ')' )
            int alt212=6;
            switch ( input.LA(1) ) {
            case 103:
                {
                alt212=1;
                }
                break;
            case Identifier:
                {
                alt212=2;
                }
                break;
            case StringLiteral:
            case NumericLiteral:
            case 104:
            case 105:
            case 106:
                {
                alt212=3;
                }
                break;
            case 59:
                {
                alt212=4;
                }
                break;
            case 35:
                {
                alt212=5;
                }
                break;
            case 32:
                {
                alt212=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 212, 0, input);

                throw nvae;
            }

            switch (alt212) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:367:5: 'this'
                    {
                    root_0 = (Object)adaptor.nil();

                    string_literal410=(Token)match(input,103,FOLLOW_103_in_primaryExpression2609); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal410_tree = (Object)adaptor.create(string_literal410);
                    adaptor.addChild(root_0, string_literal410_tree);
                    }

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:368:5: Identifier
                    {
                    root_0 = (Object)adaptor.nil();

                    Identifier411=(Token)match(input,Identifier,FOLLOW_Identifier_in_primaryExpression2615); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Identifier411_tree = (Object)adaptor.create(Identifier411);
                    adaptor.addChild(root_0, Identifier411_tree);
                    }

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:369:5: literal
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_literal_in_primaryExpression2621);
                    literal412=literal();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, literal412.getTree());

                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:370:5: arrayLiteral
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_arrayLiteral_in_primaryExpression2627);
                    arrayLiteral413=arrayLiteral();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arrayLiteral413.getTree());

                    }
                    break;
                case 5 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:371:5: objectLiteral
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_objectLiteral_in_primaryExpression2633);
                    objectLiteral414=objectLiteral();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, objectLiteral414.getTree());

                    }
                    break;
                case 6 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:372:5: '(' ( LT )* expression ( LT )* ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal415=(Token)match(input,32,FOLLOW_32_in_primaryExpression2639); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal415_tree = (Object)adaptor.create(char_literal415);
                    adaptor.addChild(root_0, char_literal415_tree);
                    }
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:372:11: ( LT )*
                    loop210:
                    do {
                        int alt210=2;
                        int LA210_0 = input.LA(1);

                        if ( (LA210_0==LT) ) {
                            alt210=1;
                        }


                        switch (alt210) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT416=(Token)match(input,LT,FOLLOW_LT_in_primaryExpression2641); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop210;
                        }
                    } while (true);

                    pushFollow(FOLLOW_expression_in_primaryExpression2645);
                    expression417=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression417.getTree());
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:372:27: ( LT )*
                    loop211:
                    do {
                        int alt211=2;
                        int LA211_0 = input.LA(1);

                        if ( (LA211_0==LT) ) {
                            alt211=1;
                        }


                        switch (alt211) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
                    	    {
                    	    LT418=(Token)match(input,LT,FOLLOW_LT_in_primaryExpression2647); if (state.failed) return retval;

                    	    }
                    	    break;

                    	default :
                    	    break loop211;
                        }
                    } while (true);

                    char_literal419=(Token)match(input,34,FOLLOW_34_in_primaryExpression2651); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal419_tree = (Object)adaptor.create(char_literal419);
                    adaptor.addChild(root_0, char_literal419_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 76, primaryExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "primaryExpression"

    public static class arrayLiteral_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arrayLiteral"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:376:1: arrayLiteral : '[' ( LT )* ( assignmentExpression )? ( ( LT )* ',' ( ( LT )* assignmentExpression )? )* ( LT )* ']' ;
    public final JavaScriptParser.arrayLiteral_return arrayLiteral() throws RecognitionException {
        JavaScriptParser.arrayLiteral_return retval = new JavaScriptParser.arrayLiteral_return();
        retval.start = input.LT(1);
        int arrayLiteral_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal420=null;
        Token LT421=null;
        Token LT423=null;
        Token char_literal424=null;
        Token LT425=null;
        Token LT427=null;
        Token char_literal428=null;
        JavaScriptParser.assignmentExpression_return assignmentExpression422 = null;

        JavaScriptParser.assignmentExpression_return assignmentExpression426 = null;


        Object char_literal420_tree=null;
        Object LT421_tree=null;
        Object LT423_tree=null;
        Object char_literal424_tree=null;
        Object LT425_tree=null;
        Object LT427_tree=null;
        Object char_literal428_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 77) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:377:3: ( '[' ( LT )* ( assignmentExpression )? ( ( LT )* ',' ( ( LT )* assignmentExpression )? )* ( LT )* ']' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:377:5: '[' ( LT )* ( assignmentExpression )? ( ( LT )* ',' ( ( LT )* assignmentExpression )? )* ( LT )* ']'
            {
            root_0 = (Object)adaptor.nil();

            char_literal420=(Token)match(input,59,FOLLOW_59_in_arrayLiteral2667); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal420_tree = (Object)adaptor.create(char_literal420);
            adaptor.addChild(root_0, char_literal420_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:377:11: ( LT )*
            loop213:
            do {
                int alt213=2;
                int LA213_0 = input.LA(1);

                if ( (LA213_0==LT) ) {
                    int LA213_2 = input.LA(2);

                    if ( (synpred280_JavaScript()) ) {
                        alt213=1;
                    }


                }


                switch (alt213) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT421=(Token)match(input,LT,FOLLOW_LT_in_arrayLiteral2669); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop213;
                }
            } while (true);

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:377:14: ( assignmentExpression )?
            int alt214=2;
            int LA214_0 = input.LA(1);

            if ( ((LA214_0>=Identifier && LA214_0<=NumericLiteral)||(LA214_0>=31 && LA214_0<=32)||LA214_0==35||(LA214_0>=58 && LA214_0<=59)||(LA214_0>=91 && LA214_0<=92)||(LA214_0>=96 && LA214_0<=106)) ) {
                alt214=1;
            }
            switch (alt214) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: assignmentExpression
                    {
                    pushFollow(FOLLOW_assignmentExpression_in_arrayLiteral2673);
                    assignmentExpression422=assignmentExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpression422.getTree());

                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:377:36: ( ( LT )* ',' ( ( LT )* assignmentExpression )? )*
            loop218:
            do {
                int alt218=2;
                alt218 = dfa218.predict(input);
                switch (alt218) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:377:37: ( LT )* ',' ( ( LT )* assignmentExpression )?
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:377:39: ( LT )*
            	    loop215:
            	    do {
            	        int alt215=2;
            	        int LA215_0 = input.LA(1);

            	        if ( (LA215_0==LT) ) {
            	            alt215=1;
            	        }


            	        switch (alt215) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT423=(Token)match(input,LT,FOLLOW_LT_in_arrayLiteral2677); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop215;
            	        }
            	    } while (true);

            	    char_literal424=(Token)match(input,33,FOLLOW_33_in_arrayLiteral2681); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal424_tree = (Object)adaptor.create(char_literal424);
            	    adaptor.addChild(root_0, char_literal424_tree);
            	    }
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:377:46: ( ( LT )* assignmentExpression )?
            	    int alt217=2;
            	    alt217 = dfa217.predict(input);
            	    switch (alt217) {
            	        case 1 :
            	            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:377:47: ( LT )* assignmentExpression
            	            {
            	            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:377:49: ( LT )*
            	            loop216:
            	            do {
            	                int alt216=2;
            	                int LA216_0 = input.LA(1);

            	                if ( (LA216_0==LT) ) {
            	                    alt216=1;
            	                }


            	                switch (alt216) {
            	            	case 1 :
            	            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	            	    {
            	            	    LT425=(Token)match(input,LT,FOLLOW_LT_in_arrayLiteral2684); if (state.failed) return retval;

            	            	    }
            	            	    break;

            	            	default :
            	            	    break loop216;
            	                }
            	            } while (true);

            	            pushFollow(FOLLOW_assignmentExpression_in_arrayLiteral2688);
            	            assignmentExpression426=assignmentExpression();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpression426.getTree());

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop218;
                }
            } while (true);

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:377:79: ( LT )*
            loop219:
            do {
                int alt219=2;
                int LA219_0 = input.LA(1);

                if ( (LA219_0==LT) ) {
                    alt219=1;
                }


                switch (alt219) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT427=(Token)match(input,LT,FOLLOW_LT_in_arrayLiteral2694); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop219;
                }
            } while (true);

            char_literal428=(Token)match(input,60,FOLLOW_60_in_arrayLiteral2698); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal428_tree = (Object)adaptor.create(char_literal428);
            adaptor.addChild(root_0, char_literal428_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 77, arrayLiteral_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "arrayLiteral"

    public static class objectLiteral_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "objectLiteral"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:381:1: objectLiteral : '{' ( LT )* propertyNameAndValue ( ( LT )* ',' ( LT )* propertyNameAndValue )* ( LT )* '}' ;
    public final JavaScriptParser.objectLiteral_return objectLiteral() throws RecognitionException {
        JavaScriptParser.objectLiteral_return retval = new JavaScriptParser.objectLiteral_return();
        retval.start = input.LT(1);
        int objectLiteral_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal429=null;
        Token LT430=null;
        Token LT432=null;
        Token char_literal433=null;
        Token LT434=null;
        Token LT436=null;
        Token char_literal437=null;
        JavaScriptParser.propertyNameAndValue_return propertyNameAndValue431 = null;

        JavaScriptParser.propertyNameAndValue_return propertyNameAndValue435 = null;


        Object char_literal429_tree=null;
        Object LT430_tree=null;
        Object LT432_tree=null;
        Object char_literal433_tree=null;
        Object LT434_tree=null;
        Object LT436_tree=null;
        Object char_literal437_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 78) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:382:3: ( '{' ( LT )* propertyNameAndValue ( ( LT )* ',' ( LT )* propertyNameAndValue )* ( LT )* '}' )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:382:5: '{' ( LT )* propertyNameAndValue ( ( LT )* ',' ( LT )* propertyNameAndValue )* ( LT )* '}'
            {
            root_0 = (Object)adaptor.nil();

            char_literal429=(Token)match(input,35,FOLLOW_35_in_objectLiteral2719); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal429_tree = (Object)adaptor.create(char_literal429);
            adaptor.addChild(root_0, char_literal429_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:382:11: ( LT )*
            loop220:
            do {
                int alt220=2;
                int LA220_0 = input.LA(1);

                if ( (LA220_0==LT) ) {
                    alt220=1;
                }


                switch (alt220) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT430=(Token)match(input,LT,FOLLOW_LT_in_objectLiteral2721); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop220;
                }
            } while (true);

            pushFollow(FOLLOW_propertyNameAndValue_in_objectLiteral2725);
            propertyNameAndValue431=propertyNameAndValue();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, propertyNameAndValue431.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:382:35: ( ( LT )* ',' ( LT )* propertyNameAndValue )*
            loop223:
            do {
                int alt223=2;
                alt223 = dfa223.predict(input);
                switch (alt223) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:382:36: ( LT )* ',' ( LT )* propertyNameAndValue
            	    {
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:382:38: ( LT )*
            	    loop221:
            	    do {
            	        int alt221=2;
            	        int LA221_0 = input.LA(1);

            	        if ( (LA221_0==LT) ) {
            	            alt221=1;
            	        }


            	        switch (alt221) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT432=(Token)match(input,LT,FOLLOW_LT_in_objectLiteral2728); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop221;
            	        }
            	    } while (true);

            	    char_literal433=(Token)match(input,33,FOLLOW_33_in_objectLiteral2732); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal433_tree = (Object)adaptor.create(char_literal433);
            	    adaptor.addChild(root_0, char_literal433_tree);
            	    }
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:382:47: ( LT )*
            	    loop222:
            	    do {
            	        int alt222=2;
            	        int LA222_0 = input.LA(1);

            	        if ( (LA222_0==LT) ) {
            	            alt222=1;
            	        }


            	        switch (alt222) {
            	    	case 1 :
            	    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    	    {
            	    	    LT434=(Token)match(input,LT,FOLLOW_LT_in_objectLiteral2734); if (state.failed) return retval;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop222;
            	        }
            	    } while (true);

            	    pushFollow(FOLLOW_propertyNameAndValue_in_objectLiteral2738);
            	    propertyNameAndValue435=propertyNameAndValue();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, propertyNameAndValue435.getTree());

            	    }
            	    break;

            	default :
            	    break loop223;
                }
            } while (true);

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:382:75: ( LT )*
            loop224:
            do {
                int alt224=2;
                int LA224_0 = input.LA(1);

                if ( (LA224_0==LT) ) {
                    alt224=1;
                }


                switch (alt224) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT436=(Token)match(input,LT,FOLLOW_LT_in_objectLiteral2742); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop224;
                }
            } while (true);

            char_literal437=(Token)match(input,36,FOLLOW_36_in_objectLiteral2746); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal437_tree = (Object)adaptor.create(char_literal437);
            adaptor.addChild(root_0, char_literal437_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 78, objectLiteral_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "objectLiteral"

    public static class propertyNameAndValue_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "propertyNameAndValue"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:385:1: propertyNameAndValue : propertyName ( LT )* ':' ( LT )* assignmentExpression ;
    public final JavaScriptParser.propertyNameAndValue_return propertyNameAndValue() throws RecognitionException {
        JavaScriptParser.propertyNameAndValue_return retval = new JavaScriptParser.propertyNameAndValue_return();
        retval.start = input.LT(1);
        int propertyNameAndValue_StartIndex = input.index();
        Object root_0 = null;

        Token LT439=null;
        Token char_literal440=null;
        Token LT441=null;
        JavaScriptParser.propertyName_return propertyName438 = null;

        JavaScriptParser.assignmentExpression_return assignmentExpression442 = null;


        Object LT439_tree=null;
        Object char_literal440_tree=null;
        Object LT441_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 79) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:386:3: ( propertyName ( LT )* ':' ( LT )* assignmentExpression )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:386:5: propertyName ( LT )* ':' ( LT )* assignmentExpression
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_propertyName_in_propertyNameAndValue2761);
            propertyName438=propertyName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, propertyName438.getTree());
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:386:20: ( LT )*
            loop225:
            do {
                int alt225=2;
                int LA225_0 = input.LA(1);

                if ( (LA225_0==LT) ) {
                    alt225=1;
                }


                switch (alt225) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT439=(Token)match(input,LT,FOLLOW_LT_in_propertyNameAndValue2763); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop225;
                }
            } while (true);

            char_literal440=(Token)match(input,50,FOLLOW_50_in_propertyNameAndValue2767); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal440_tree = (Object)adaptor.create(char_literal440);
            adaptor.addChild(root_0, char_literal440_tree);
            }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:386:29: ( LT )*
            loop226:
            do {
                int alt226=2;
                int LA226_0 = input.LA(1);

                if ( (LA226_0==LT) ) {
                    alt226=1;
                }


                switch (alt226) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
            	    {
            	    LT441=(Token)match(input,LT,FOLLOW_LT_in_propertyNameAndValue2769); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop226;
                }
            } while (true);

            pushFollow(FOLLOW_assignmentExpression_in_propertyNameAndValue2773);
            assignmentExpression442=assignmentExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpression442.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 79, propertyNameAndValue_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "propertyNameAndValue"

    public static class propertyName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "propertyName"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:389:1: propertyName : ( Identifier | StringLiteral | NumericLiteral );
    public final JavaScriptParser.propertyName_return propertyName() throws RecognitionException {
        JavaScriptParser.propertyName_return retval = new JavaScriptParser.propertyName_return();
        retval.start = input.LT(1);
        int propertyName_StartIndex = input.index();
        Object root_0 = null;

        Token set443=null;

        Object set443_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 80) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:390:3: ( Identifier | StringLiteral | NumericLiteral )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:
            {
            root_0 = (Object)adaptor.nil();

            set443=(Token)input.LT(1);
            if ( (input.LA(1)>=Identifier && input.LA(1)<=NumericLiteral) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set443));
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
            if ( state.backtracking>0 ) { memoize(input, 80, propertyName_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "propertyName"

    public static class literal_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "literal"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:396:1: literal : ( 'null' | 'true' | 'false' | StringLiteral | NumericLiteral );
    public final JavaScriptParser.literal_return literal() throws RecognitionException {
        JavaScriptParser.literal_return retval = new JavaScriptParser.literal_return();
        retval.start = input.LT(1);
        int literal_StartIndex = input.index();
        Object root_0 = null;

        Token set444=null;

        Object set444_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 81) ) { return retval; }
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:397:3: ( 'null' | 'true' | 'false' | StringLiteral | NumericLiteral )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:
            {
            root_0 = (Object)adaptor.nil();

            set444=(Token)input.LT(1);
            if ( (input.LA(1)>=StringLiteral && input.LA(1)<=NumericLiteral)||(input.LA(1)>=104 && input.LA(1)<=106) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set444));
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
            if ( state.backtracking>0 ) { memoize(input, 81, literal_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "literal"

    // $ANTLR start synpred5_JavaScript
    public final void synpred5_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:41:5: ( functionDeclaration )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:41:5: functionDeclaration
        {
        pushFollow(FOLLOW_functionDeclaration_in_synpred5_JavaScript110);
        functionDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred5_JavaScript

    // $ANTLR start synpred9_JavaScript
    public final void synpred9_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:52:16: ( LT )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:52:16: LT
        {
        match(input,LT,FOLLOW_LT_in_synpred9_JavaScript171); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred9_JavaScript

    // $ANTLR start synpred21_JavaScript
    public final void synpred21_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:68:5: ( statementBlock )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:68:5: statementBlock
        {
        pushFollow(FOLLOW_statementBlock_in_synpred21_JavaScript288);
        statementBlock();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred21_JavaScript

    // $ANTLR start synpred24_JavaScript
    public final void synpred24_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:71:5: ( expressionStatement )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:71:5: expressionStatement
        {
        pushFollow(FOLLOW_expressionStatement_in_synpred24_JavaScript306);
        expressionStatement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred24_JavaScript

    // $ANTLR start synpred31_JavaScript
    public final void synpred31_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:78:5: ( labelledStatement )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:78:5: labelledStatement
        {
        pushFollow(FOLLOW_labelledStatement_in_synpred31_JavaScript348);
        labelledStatement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred31_JavaScript

    // $ANTLR start synpred34_JavaScript
    public final void synpred34_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:85:9: ( LT )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:85:9: LT
        {
        match(input,LT,FOLLOW_LT_in_synpred34_JavaScript383); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred34_JavaScript

    // $ANTLR start synpred47_JavaScript
    public final void synpred47_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:105:16: ( LT )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:105:16: LT
        {
        match(input,LT,FOLLOW_LT_in_synpred47_JavaScript525); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred47_JavaScript

    // $ANTLR start synpred49_JavaScript
    public final void synpred49_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:109:16: ( LT )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:109:16: LT
        {
        match(input,LT,FOLLOW_LT_in_synpred49_JavaScript547); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred49_JavaScript

    // $ANTLR start synpred60_JavaScript
    public final void synpred60_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:129:60: ( ( LT )* 'else' ( LT )* statement )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:129:60: ( LT )* 'else' ( LT )* statement
        {
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:129:62: ( LT )*
        loop239:
        do {
            int alt239=2;
            int LA239_0 = input.LA(1);

            if ( (LA239_0==LT) ) {
                alt239=1;
            }


            switch (alt239) {
        	case 1 :
        	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
        	    {
        	    match(input,LT,FOLLOW_LT_in_synpred60_JavaScript675); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop239;
            }
        } while (true);

        match(input,41,FOLLOW_41_in_synpred60_JavaScript679); if (state.failed) return ;
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:129:74: ( LT )*
        loop240:
        do {
            int alt240=2;
            int LA240_0 = input.LA(1);

            if ( (LA240_0==LT) ) {
                alt240=1;
            }


            switch (alt240) {
        	case 1 :
        	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
        	    {
        	    match(input,LT,FOLLOW_LT_in_synpred60_JavaScript681); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop240;
            }
        } while (true);

        pushFollow(FOLLOW_statement_in_synpred60_JavaScript685);
        statement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred60_JavaScript

    // $ANTLR start synpred63_JavaScript
    public final void synpred63_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:135:5: ( forStatement )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:135:5: forStatement
        {
        pushFollow(FOLLOW_forStatement_in_synpred63_JavaScript714);
        forStatement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred63_JavaScript

    // $ANTLR start synpred118_JavaScript
    public final void synpred118_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:194:37: ( LT )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:194:37: LT
        {
        match(input,LT,FOLLOW_LT_in_synpred118_JavaScript1235); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred118_JavaScript

    // $ANTLR start synpred121_JavaScript
    public final void synpred121_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:198:24: ( LT )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:198:24: LT
        {
        match(input,LT,FOLLOW_LT_in_synpred121_JavaScript1263); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred121_JavaScript

    // $ANTLR start synpred140_JavaScript
    public final void synpred140_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:227:5: ( conditionalExpression )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:227:5: conditionalExpression
        {
        pushFollow(FOLLOW_conditionalExpression_in_synpred140_JavaScript1473);
        conditionalExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred140_JavaScript

    // $ANTLR start synpred143_JavaScript
    public final void synpred143_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:232:5: ( conditionalExpressionNoIn )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:232:5: conditionalExpressionNoIn
        {
        pushFollow(FOLLOW_conditionalExpressionNoIn_in_synpred143_JavaScript1506);
        conditionalExpressionNoIn();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred143_JavaScript

    // $ANTLR start synpred146_JavaScript
    public final void synpred146_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:237:5: ( callExpression )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:237:5: callExpression
        {
        pushFollow(FOLLOW_callExpression_in_synpred146_JavaScript1539);
        callExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred146_JavaScript

    // $ANTLR start synpred147_JavaScript
    public final void synpred147_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:242:5: ( memberExpression )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:242:5: memberExpression
        {
        pushFollow(FOLLOW_memberExpression_in_synpred147_JavaScript1560);
        memberExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred147_JavaScript

    // $ANTLR start synpred154_JavaScript
    public final void synpred154_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:247:92: ( ( LT )* memberExpressionSuffix )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:247:92: ( LT )* memberExpressionSuffix
        {
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:247:94: ( LT )*
        loop254:
        do {
            int alt254=2;
            int LA254_0 = input.LA(1);

            if ( (LA254_0==LT) ) {
                alt254=1;
            }


            switch (alt254) {
        	case 1 :
        	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
        	    {
        	    match(input,LT,FOLLOW_LT_in_synpred154_JavaScript1612); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop254;
            }
        } while (true);

        pushFollow(FOLLOW_memberExpressionSuffix_in_synpred154_JavaScript1616);
        memberExpressionSuffix();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred154_JavaScript

    // $ANTLR start synpred158_JavaScript
    public final void synpred158_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:256:38: ( ( LT )* callExpressionSuffix )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:256:38: ( LT )* callExpressionSuffix
        {
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:256:40: ( LT )*
        loop255:
        do {
            int alt255=2;
            int LA255_0 = input.LA(1);

            if ( (LA255_0==LT) ) {
                alt255=1;
            }


            switch (alt255) {
        	case 1 :
        	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
        	    {
        	    match(input,LT,FOLLOW_LT_in_synpred158_JavaScript1661); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop255;
            }
        } while (true);

        pushFollow(FOLLOW_callExpressionSuffix_in_synpred158_JavaScript1665);
        callExpressionSuffix();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred158_JavaScript

    // $ANTLR start synpred256_JavaScript
    public final void synpred256_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:350:31: ( ( LT )* ( '+' | '-' ) ( LT )* multiplicativeExpression )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:350:31: ( LT )* ( '+' | '-' ) ( LT )* multiplicativeExpression
        {
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:350:33: ( LT )*
        loop300:
        do {
            int alt300=2;
            int LA300_0 = input.LA(1);

            if ( (LA300_0==LT) ) {
                alt300=1;
            }


            switch (alt300) {
        	case 1 :
        	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
        	    {
        	    match(input,LT,FOLLOW_LT_in_synpred256_JavaScript2461); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop300;
            }
        } while (true);

        if ( (input.LA(1)>=91 && input.LA(1)<=92) ) {
            input.consume();
            state.errorRecovery=false;state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }

        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:350:50: ( LT )*
        loop301:
        do {
            int alt301=2;
            int LA301_0 = input.LA(1);

            if ( (LA301_0==LT) ) {
                alt301=1;
            }


            switch (alt301) {
        	case 1 :
        	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:0:0: LT
        	    {
        	    match(input,LT,FOLLOW_LT_in_synpred256_JavaScript2473); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop301;
            }
        } while (true);

        pushFollow(FOLLOW_multiplicativeExpression_in_synpred256_JavaScript2477);
        multiplicativeExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred256_JavaScript

    // $ANTLR start synpred280_JavaScript
    public final void synpred280_JavaScript_fragment() throws RecognitionException {   
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:377:9: ( LT )
        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/JavaScript.g:377:9: LT
        {
        match(input,LT,FOLLOW_LT_in_synpred280_JavaScript2669); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred280_JavaScript

    // Delegated rules

    public final boolean synpred60_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred60_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred121_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred121_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred146_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred146_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred154_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred154_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred34_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred34_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred147_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred147_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred63_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred63_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred47_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred47_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred256_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred256_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred280_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred280_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred118_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred118_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred158_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred158_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred9_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred21_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred21_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred31_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred31_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred49_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred49_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred24_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred24_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred143_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred143_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred140_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred140_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_JavaScript() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_JavaScript_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA4 dfa4 = new DFA4(this);
    protected DFA5 dfa5 = new DFA5(this);
    protected DFA17 dfa17 = new DFA17(this);
    protected DFA16 dfa16 = new DFA16(this);
    protected DFA21 dfa21 = new DFA21(this);
    protected DFA26 dfa26 = new DFA26(this);
    protected DFA30 dfa30 = new DFA30(this);
    protected DFA33 dfa33 = new DFA33(this);
    protected DFA57 dfa57 = new DFA57(this);
    protected DFA60 dfa60 = new DFA60(this);
    protected DFA63 dfa63 = new DFA63(this);
    protected DFA90 dfa90 = new DFA90(this);
    protected DFA94 dfa94 = new DFA94(this);
    protected DFA93 dfa93 = new DFA93(this);
    protected DFA106 dfa106 = new DFA106(this);
    protected DFA115 dfa115 = new DFA115(this);
    protected DFA118 dfa118 = new DFA118(this);
    protected DFA121 dfa121 = new DFA121(this);
    protected DFA124 dfa124 = new DFA124(this);
    protected DFA125 dfa125 = new DFA125(this);
    protected DFA127 dfa127 = new DFA127(this);
    protected DFA132 dfa132 = new DFA132(this);
    protected DFA136 dfa136 = new DFA136(this);
    protected DFA142 dfa142 = new DFA142(this);
    protected DFA141 dfa141 = new DFA141(this);
    protected DFA151 dfa151 = new DFA151(this);
    protected DFA156 dfa156 = new DFA156(this);
    protected DFA159 dfa159 = new DFA159(this);
    protected DFA162 dfa162 = new DFA162(this);
    protected DFA165 dfa165 = new DFA165(this);
    protected DFA168 dfa168 = new DFA168(this);
    protected DFA171 dfa171 = new DFA171(this);
    protected DFA174 dfa174 = new DFA174(this);
    protected DFA177 dfa177 = new DFA177(this);
    protected DFA180 dfa180 = new DFA180(this);
    protected DFA183 dfa183 = new DFA183(this);
    protected DFA186 dfa186 = new DFA186(this);
    protected DFA189 dfa189 = new DFA189(this);
    protected DFA192 dfa192 = new DFA192(this);
    protected DFA195 dfa195 = new DFA195(this);
    protected DFA198 dfa198 = new DFA198(this);
    protected DFA201 dfa201 = new DFA201(this);
    protected DFA204 dfa204 = new DFA204(this);
    protected DFA207 dfa207 = new DFA207(this);
    protected DFA218 dfa218 = new DFA218(this);
    protected DFA217 dfa217 = new DFA217(this);
    protected DFA223 dfa223 = new DFA223(this);
    static final String DFA4_eotS =
        "\4\uffff";
    static final String DFA4_eofS =
        "\2\2\2\uffff";
    static final String DFA4_minS =
        "\2\4\2\uffff";
    static final String DFA4_maxS =
        "\2\152\2\uffff";
    static final String DFA4_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA4_specialS =
        "\4\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\1\3\3\27\uffff\2\3\2\uffff\1\3\1\2\2\3\1\uffff\1\3\1\uffff"+
            "\3\3\1\uffff\4\3\1\uffff\1\3\2\uffff\2\3\2\uffff\2\3\37\uffff"+
            "\2\3\3\uffff\13\3",
            "\1\1\3\3\27\uffff\2\3\2\uffff\1\3\1\2\2\3\1\uffff\1\3\1\uffff"+
            "\3\3\1\uffff\4\3\1\uffff\1\3\2\uffff\2\3\2\uffff\2\3\37\uffff"+
            "\2\3\3\uffff\13\3",
            "",
            ""
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "()* loopback of 37:19: ( ( LT )* sourceElement )*";
        }
    }
    static final String DFA5_eotS =
        "\30\uffff";
    static final String DFA5_eofS =
        "\30\uffff";
    static final String DFA5_minS =
        "\1\5\1\0\26\uffff";
    static final String DFA5_maxS =
        "\1\152\1\0\26\uffff";
    static final String DFA5_acceptS =
        "\2\uffff\1\2\24\uffff\1\1";
    static final String DFA5_specialS =
        "\1\uffff\1\0\26\uffff}>";
    static final String[] DFA5_transitionS = {
            "\3\2\27\uffff\1\1\1\2\2\uffff\1\2\1\uffff\2\2\1\uffff\1\2\1"+
            "\uffff\3\2\1\uffff\4\2\1\uffff\1\2\2\uffff\2\2\2\uffff\2\2\37"+
            "\uffff\2\2\3\uffff\13\2",
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
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "40:1: sourceElement : ( functionDeclaration | statement );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA5_1 = input.LA(1);

                         
                        int index5_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_JavaScript()) ) {s = 23;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index5_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 5, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA17_eotS =
        "\4\uffff";
    static final String DFA17_eofS =
        "\4\uffff";
    static final String DFA17_minS =
        "\2\4\2\uffff";
    static final String DFA17_maxS =
        "\2\42\2\uffff";
    static final String DFA17_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA17_specialS =
        "\4\uffff}>";
    static final String[] DFA17_transitionS = {
            "\1\1\1\2\34\uffff\1\3",
            "\1\1\1\2\34\uffff\1\3",
            "",
            ""
    };

    static final short[] DFA17_eot = DFA.unpackEncodedString(DFA17_eotS);
    static final short[] DFA17_eof = DFA.unpackEncodedString(DFA17_eofS);
    static final char[] DFA17_min = DFA.unpackEncodedStringToUnsignedChars(DFA17_minS);
    static final char[] DFA17_max = DFA.unpackEncodedStringToUnsignedChars(DFA17_maxS);
    static final short[] DFA17_accept = DFA.unpackEncodedString(DFA17_acceptS);
    static final short[] DFA17_special = DFA.unpackEncodedString(DFA17_specialS);
    static final short[][] DFA17_transition;

    static {
        int numStates = DFA17_transitionS.length;
        DFA17_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA17_transition[i] = DFA.unpackEncodedString(DFA17_transitionS[i]);
        }
    }

    class DFA17 extends DFA {

        public DFA17(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 17;
            this.eot = DFA17_eot;
            this.eof = DFA17_eof;
            this.min = DFA17_min;
            this.max = DFA17_max;
            this.accept = DFA17_accept;
            this.special = DFA17_special;
            this.transition = DFA17_transition;
        }
        public String getDescription() {
            return "57:9: ( ( LT )* t1= Identifier ( ( LT )* ',' ( LT )* t2= Identifier )* )?";
        }
    }
    static final String DFA16_eotS =
        "\4\uffff";
    static final String DFA16_eofS =
        "\4\uffff";
    static final String DFA16_minS =
        "\2\4\2\uffff";
    static final String DFA16_maxS =
        "\2\42\2\uffff";
    static final String DFA16_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA16_specialS =
        "\4\uffff}>";
    static final String[] DFA16_transitionS = {
            "\1\1\34\uffff\1\3\1\2",
            "\1\1\34\uffff\1\3\1\2",
            "",
            ""
    };

    static final short[] DFA16_eot = DFA.unpackEncodedString(DFA16_eotS);
    static final short[] DFA16_eof = DFA.unpackEncodedString(DFA16_eofS);
    static final char[] DFA16_min = DFA.unpackEncodedStringToUnsignedChars(DFA16_minS);
    static final char[] DFA16_max = DFA.unpackEncodedStringToUnsignedChars(DFA16_maxS);
    static final short[] DFA16_accept = DFA.unpackEncodedString(DFA16_acceptS);
    static final short[] DFA16_special = DFA.unpackEncodedString(DFA16_specialS);
    static final short[][] DFA16_transition;

    static {
        int numStates = DFA16_transitionS.length;
        DFA16_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA16_transition[i] = DFA.unpackEncodedString(DFA16_transitionS[i]);
        }
    }

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = DFA16_eot;
            this.eof = DFA16_eof;
            this.min = DFA16_min;
            this.max = DFA16_max;
            this.accept = DFA16_accept;
            this.special = DFA16_special;
            this.transition = DFA16_transition;
        }
        public String getDescription() {
            return "()* loopback of 57:29: ( ( LT )* ',' ( LT )* t2= Identifier )*";
        }
    }
    static final String DFA21_eotS =
        "\31\uffff";
    static final String DFA21_eofS =
        "\31\uffff";
    static final String DFA21_minS =
        "\1\5\1\0\3\uffff\1\0\23\uffff";
    static final String DFA21_maxS =
        "\1\152\1\0\3\uffff\1\0\23\uffff";
    static final String DFA21_acceptS =
        "\2\uffff\1\2\1\3\1\4\7\uffff\1\5\1\6\2\uffff\1\7\1\10\1\11\1\12"+
        "\1\14\1\15\1\16\1\1\1\13";
    static final String DFA21_specialS =
        "\1\uffff\1\0\3\uffff\1\1\23\uffff}>";
    static final String[] DFA21_transitionS = {
            "\1\5\2\4\27\uffff\2\4\2\uffff\1\1\1\uffff\1\2\1\3\1\uffff\1"+
            "\14\1\uffff\3\15\1\uffff\1\20\1\21\1\22\1\23\1\uffff\1\24\2"+
            "\uffff\1\25\1\26\2\uffff\2\4\37\uffff\2\4\3\uffff\13\4",
            "\1\uffff",
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
            ""
    };

    static final short[] DFA21_eot = DFA.unpackEncodedString(DFA21_eotS);
    static final short[] DFA21_eof = DFA.unpackEncodedString(DFA21_eofS);
    static final char[] DFA21_min = DFA.unpackEncodedStringToUnsignedChars(DFA21_minS);
    static final char[] DFA21_max = DFA.unpackEncodedStringToUnsignedChars(DFA21_maxS);
    static final short[] DFA21_accept = DFA.unpackEncodedString(DFA21_acceptS);
    static final short[] DFA21_special = DFA.unpackEncodedString(DFA21_specialS);
    static final short[][] DFA21_transition;

    static {
        int numStates = DFA21_transitionS.length;
        DFA21_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA21_transition[i] = DFA.unpackEncodedString(DFA21_transitionS[i]);
        }
    }

    class DFA21 extends DFA {

        public DFA21(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 21;
            this.eot = DFA21_eot;
            this.eof = DFA21_eof;
            this.min = DFA21_min;
            this.max = DFA21_max;
            this.accept = DFA21_accept;
            this.special = DFA21_special;
            this.transition = DFA21_transition;
        }
        public String getDescription() {
            return "67:1: statement : ( statementBlock | variableStatement | emptyStatement | expressionStatement | ifStatement | iterationStatement | continueStatement | breakStatement | returnStatement | withStatement | labelledStatement | switchStatement | throwStatement | tryStatement );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA21_1 = input.LA(1);

                         
                        int index21_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred21_JavaScript()) ) {s = 23;}

                        else if ( (synpred24_JavaScript()) ) {s = 4;}

                         
                        input.seek(index21_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA21_5 = input.LA(1);

                         
                        int index21_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred24_JavaScript()) ) {s = 4;}

                        else if ( (synpred31_JavaScript()) ) {s = 24;}

                         
                        input.seek(index21_5);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 21, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA26_eotS =
        "\4\uffff";
    static final String DFA26_eofS =
        "\1\2\3\uffff";
    static final String DFA26_minS =
        "\2\4\2\uffff";
    static final String DFA26_maxS =
        "\2\152\2\uffff";
    static final String DFA26_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA26_specialS =
        "\4\uffff}>";
    static final String[] DFA26_transitionS = {
            "\1\1\3\3\27\uffff\2\3\2\uffff\1\3\1\2\2\3\1\uffff\1\3\1\uffff"+
            "\3\3\1\uffff\4\3\1\uffff\1\3\2\2\2\3\2\uffff\2\3\37\uffff\2"+
            "\3\3\uffff\13\3",
            "\1\1\3\3\27\uffff\2\3\2\uffff\1\3\1\2\2\3\1\uffff\1\3\1\uffff"+
            "\3\3\1\uffff\4\3\1\uffff\1\3\2\2\2\3\2\uffff\2\3\37\uffff\2"+
            "\3\3\uffff\13\3",
            "",
            ""
    };

    static final short[] DFA26_eot = DFA.unpackEncodedString(DFA26_eotS);
    static final short[] DFA26_eof = DFA.unpackEncodedString(DFA26_eofS);
    static final char[] DFA26_min = DFA.unpackEncodedStringToUnsignedChars(DFA26_minS);
    static final char[] DFA26_max = DFA.unpackEncodedStringToUnsignedChars(DFA26_maxS);
    static final short[] DFA26_accept = DFA.unpackEncodedString(DFA26_acceptS);
    static final short[] DFA26_special = DFA.unpackEncodedString(DFA26_specialS);
    static final short[][] DFA26_transition;

    static {
        int numStates = DFA26_transitionS.length;
        DFA26_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA26_transition[i] = DFA.unpackEncodedString(DFA26_transitionS[i]);
        }
    }

    class DFA26 extends DFA {

        public DFA26(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 26;
            this.eot = DFA26_eot;
            this.eof = DFA26_eof;
            this.min = DFA26_min;
            this.max = DFA26_max;
            this.accept = DFA26_accept;
            this.special = DFA26_special;
            this.transition = DFA26_transition;
        }
        public String getDescription() {
            return "()* loopback of 89:15: ( ( LT )* statement )*";
        }
    }
    static final String DFA30_eotS =
        "\5\uffff";
    static final String DFA30_eofS =
        "\1\uffff\1\2\2\uffff\1\2";
    static final String DFA30_minS =
        "\2\4\2\uffff\1\4";
    static final String DFA30_maxS =
        "\1\46\1\152\2\uffff\1\152";
    static final String DFA30_acceptS =
        "\2\uffff\1\2\1\1\1\uffff";
    static final String DFA30_specialS =
        "\5\uffff}>";
    static final String[] DFA30_transitionS = {
            "\1\1\34\uffff\1\3\4\uffff\1\2",
            "\1\4\3\2\27\uffff\2\2\1\3\1\uffff\4\2\1\uffff\5\2\1\uffff\4"+
            "\2\1\uffff\5\2\2\uffff\2\2\37\uffff\2\2\3\uffff\13\2",
            "",
            "",
            "\1\4\3\2\27\uffff\2\2\1\3\1\uffff\4\2\1\uffff\5\2\1\uffff\4"+
            "\2\1\uffff\5\2\2\uffff\2\2\37\uffff\2\2\3\uffff\13\2"
    };

    static final short[] DFA30_eot = DFA.unpackEncodedString(DFA30_eotS);
    static final short[] DFA30_eof = DFA.unpackEncodedString(DFA30_eofS);
    static final char[] DFA30_min = DFA.unpackEncodedStringToUnsignedChars(DFA30_minS);
    static final char[] DFA30_max = DFA.unpackEncodedStringToUnsignedChars(DFA30_maxS);
    static final short[] DFA30_accept = DFA.unpackEncodedString(DFA30_acceptS);
    static final short[] DFA30_special = DFA.unpackEncodedString(DFA30_specialS);
    static final short[][] DFA30_transition;

    static {
        int numStates = DFA30_transitionS.length;
        DFA30_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA30_transition[i] = DFA.unpackEncodedString(DFA30_transitionS[i]);
        }
    }

    class DFA30 extends DFA {

        public DFA30(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 30;
            this.eot = DFA30_eot;
            this.eof = DFA30_eof;
            this.min = DFA30_min;
            this.max = DFA30_max;
            this.accept = DFA30_accept;
            this.special = DFA30_special;
            this.transition = DFA30_transition;
        }
        public String getDescription() {
            return "()* loopback of 97:25: ( ( LT )* ',' ( LT )* variableDeclaration )*";
        }
    }
    static final String DFA33_eotS =
        "\4\uffff";
    static final String DFA33_eofS =
        "\1\2\3\uffff";
    static final String DFA33_minS =
        "\2\4\2\uffff";
    static final String DFA33_maxS =
        "\2\46\2\uffff";
    static final String DFA33_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA33_specialS =
        "\4\uffff}>";
    static final String[] DFA33_transitionS = {
            "\1\1\34\uffff\1\3\4\uffff\1\2",
            "\1\1\34\uffff\1\3\4\uffff\1\2",
            "",
            ""
    };

    static final short[] DFA33_eot = DFA.unpackEncodedString(DFA33_eotS);
    static final short[] DFA33_eof = DFA.unpackEncodedString(DFA33_eofS);
    static final char[] DFA33_min = DFA.unpackEncodedStringToUnsignedChars(DFA33_minS);
    static final char[] DFA33_max = DFA.unpackEncodedStringToUnsignedChars(DFA33_maxS);
    static final short[] DFA33_accept = DFA.unpackEncodedString(DFA33_acceptS);
    static final short[] DFA33_special = DFA.unpackEncodedString(DFA33_specialS);
    static final short[][] DFA33_transition;

    static {
        int numStates = DFA33_transitionS.length;
        DFA33_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA33_transition[i] = DFA.unpackEncodedString(DFA33_transitionS[i]);
        }
    }

    class DFA33 extends DFA {

        public DFA33(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 33;
            this.eot = DFA33_eot;
            this.eof = DFA33_eof;
            this.min = DFA33_min;
            this.max = DFA33_max;
            this.accept = DFA33_accept;
            this.special = DFA33_special;
            this.transition = DFA33_transition;
        }
        public String getDescription() {
            return "()* loopback of 101:29: ( ( LT )* ',' ( LT )* variableDeclarationNoIn )*";
        }
    }
    static final String DFA57_eotS =
        "\4\uffff";
    static final String DFA57_eofS =
        "\4\uffff";
    static final String DFA57_minS =
        "\2\4\2\uffff";
    static final String DFA57_maxS =
        "\2\152\2\uffff";
    static final String DFA57_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA57_specialS =
        "\4\uffff}>";
    static final String[] DFA57_transitionS = {
            "\1\1\3\2\27\uffff\2\2\2\uffff\1\2\1\uffff\1\2\1\3\23\uffff\2"+
            "\2\37\uffff\2\2\3\uffff\13\2",
            "\1\1\3\2\27\uffff\2\2\2\uffff\1\2\1\uffff\1\2\1\3\23\uffff"+
            "\2\2\37\uffff\2\2\3\uffff\13\2",
            "",
            ""
    };

    static final short[] DFA57_eot = DFA.unpackEncodedString(DFA57_eotS);
    static final short[] DFA57_eof = DFA.unpackEncodedString(DFA57_eofS);
    static final char[] DFA57_min = DFA.unpackEncodedStringToUnsignedChars(DFA57_minS);
    static final char[] DFA57_max = DFA.unpackEncodedStringToUnsignedChars(DFA57_maxS);
    static final short[] DFA57_accept = DFA.unpackEncodedString(DFA57_acceptS);
    static final short[] DFA57_special = DFA.unpackEncodedString(DFA57_specialS);
    static final short[][] DFA57_transition;

    static {
        int numStates = DFA57_transitionS.length;
        DFA57_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA57_transition[i] = DFA.unpackEncodedString(DFA57_transitionS[i]);
        }
    }

    class DFA57 extends DFA {

        public DFA57(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 57;
            this.eot = DFA57_eot;
            this.eof = DFA57_eof;
            this.min = DFA57_min;
            this.max = DFA57_max;
            this.accept = DFA57_accept;
            this.special = DFA57_special;
            this.transition = DFA57_transition;
        }
        public String getDescription() {
            return "148:20: ( ( LT )* forStatementInitialiserPart )?";
        }
    }
    static final String DFA60_eotS =
        "\4\uffff";
    static final String DFA60_eofS =
        "\4\uffff";
    static final String DFA60_minS =
        "\2\4\2\uffff";
    static final String DFA60_maxS =
        "\2\152\2\uffff";
    static final String DFA60_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA60_specialS =
        "\4\uffff}>";
    static final String[] DFA60_transitionS = {
            "\1\1\3\2\27\uffff\2\2\2\uffff\1\2\2\uffff\1\3\23\uffff\2\2\37"+
            "\uffff\2\2\3\uffff\13\2",
            "\1\1\3\2\27\uffff\2\2\2\uffff\1\2\2\uffff\1\3\23\uffff\2\2"+
            "\37\uffff\2\2\3\uffff\13\2",
            "",
            ""
    };

    static final short[] DFA60_eot = DFA.unpackEncodedString(DFA60_eotS);
    static final short[] DFA60_eof = DFA.unpackEncodedString(DFA60_eofS);
    static final char[] DFA60_min = DFA.unpackEncodedStringToUnsignedChars(DFA60_minS);
    static final char[] DFA60_max = DFA.unpackEncodedStringToUnsignedChars(DFA60_maxS);
    static final short[] DFA60_accept = DFA.unpackEncodedString(DFA60_acceptS);
    static final short[] DFA60_special = DFA.unpackEncodedString(DFA60_specialS);
    static final short[][] DFA60_transition;

    static {
        int numStates = DFA60_transitionS.length;
        DFA60_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA60_transition[i] = DFA.unpackEncodedString(DFA60_transitionS[i]);
        }
    }

    class DFA60 extends DFA {

        public DFA60(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 60;
            this.eot = DFA60_eot;
            this.eof = DFA60_eof;
            this.min = DFA60_min;
            this.max = DFA60_max;
            this.accept = DFA60_accept;
            this.special = DFA60_special;
            this.transition = DFA60_transition;
        }
        public String getDescription() {
            return "148:65: ( ( LT )* expression )?";
        }
    }
    static final String DFA63_eotS =
        "\4\uffff";
    static final String DFA63_eofS =
        "\4\uffff";
    static final String DFA63_minS =
        "\2\4\2\uffff";
    static final String DFA63_maxS =
        "\2\152\2\uffff";
    static final String DFA63_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA63_specialS =
        "\4\uffff}>";
    static final String[] DFA63_transitionS = {
            "\1\1\3\2\27\uffff\2\2\1\uffff\1\3\1\2\26\uffff\2\2\37\uffff"+
            "\2\2\3\uffff\13\2",
            "\1\1\3\2\27\uffff\2\2\1\uffff\1\3\1\2\26\uffff\2\2\37\uffff"+
            "\2\2\3\uffff\13\2",
            "",
            ""
    };

    static final short[] DFA63_eot = DFA.unpackEncodedString(DFA63_eotS);
    static final short[] DFA63_eof = DFA.unpackEncodedString(DFA63_eofS);
    static final char[] DFA63_min = DFA.unpackEncodedStringToUnsignedChars(DFA63_minS);
    static final char[] DFA63_max = DFA.unpackEncodedStringToUnsignedChars(DFA63_maxS);
    static final short[] DFA63_accept = DFA.unpackEncodedString(DFA63_acceptS);
    static final short[] DFA63_special = DFA.unpackEncodedString(DFA63_specialS);
    static final short[][] DFA63_transition;

    static {
        int numStates = DFA63_transitionS.length;
        DFA63_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA63_transition[i] = DFA.unpackEncodedString(DFA63_transitionS[i]);
        }
    }

    class DFA63 extends DFA {

        public DFA63(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 63;
            this.eot = DFA63_eot;
            this.eof = DFA63_eof;
            this.min = DFA63_min;
            this.max = DFA63_max;
            this.accept = DFA63_accept;
            this.special = DFA63_special;
            this.transition = DFA63_transition;
        }
        public String getDescription() {
            return "148:93: ( ( LT )* expression )?";
        }
    }
    static final String DFA90_eotS =
        "\4\uffff";
    static final String DFA90_eofS =
        "\4\uffff";
    static final String DFA90_minS =
        "\2\4\2\uffff";
    static final String DFA90_maxS =
        "\2\65\2\uffff";
    static final String DFA90_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA90_specialS =
        "\4\uffff}>";
    static final String[] DFA90_transitionS = {
            "\1\1\37\uffff\1\2\17\uffff\1\3\1\2",
            "\1\1\37\uffff\1\2\17\uffff\1\3\1\2",
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
            return "()* loopback of 190:9: ( ( LT )* caseClause )*";
        }
    }
    static final String DFA94_eotS =
        "\4\uffff";
    static final String DFA94_eofS =
        "\4\uffff";
    static final String DFA94_minS =
        "\2\4\2\uffff";
    static final String DFA94_maxS =
        "\2\65\2\uffff";
    static final String DFA94_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA94_specialS =
        "\4\uffff}>";
    static final String[] DFA94_transitionS = {
            "\1\1\37\uffff\1\3\20\uffff\1\2",
            "\1\1\37\uffff\1\3\20\uffff\1\2",
            "",
            ""
    };

    static final short[] DFA94_eot = DFA.unpackEncodedString(DFA94_eotS);
    static final short[] DFA94_eof = DFA.unpackEncodedString(DFA94_eofS);
    static final char[] DFA94_min = DFA.unpackEncodedStringToUnsignedChars(DFA94_minS);
    static final char[] DFA94_max = DFA.unpackEncodedStringToUnsignedChars(DFA94_maxS);
    static final short[] DFA94_accept = DFA.unpackEncodedString(DFA94_acceptS);
    static final short[] DFA94_special = DFA.unpackEncodedString(DFA94_specialS);
    static final short[][] DFA94_transition;

    static {
        int numStates = DFA94_transitionS.length;
        DFA94_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA94_transition[i] = DFA.unpackEncodedString(DFA94_transitionS[i]);
        }
    }

    class DFA94 extends DFA {

        public DFA94(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 94;
            this.eot = DFA94_eot;
            this.eof = DFA94_eof;
            this.min = DFA94_min;
            this.max = DFA94_max;
            this.accept = DFA94_accept;
            this.special = DFA94_special;
            this.transition = DFA94_transition;
        }
        public String getDescription() {
            return "190:28: ( ( LT )* defaultClause ( ( LT )* caseClause )* )?";
        }
    }
    static final String DFA93_eotS =
        "\4\uffff";
    static final String DFA93_eofS =
        "\4\uffff";
    static final String DFA93_minS =
        "\2\4\2\uffff";
    static final String DFA93_maxS =
        "\2\64\2\uffff";
    static final String DFA93_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA93_specialS =
        "\4\uffff}>";
    static final String[] DFA93_transitionS = {
            "\1\1\37\uffff\1\2\17\uffff\1\3",
            "\1\1\37\uffff\1\2\17\uffff\1\3",
            "",
            ""
    };

    static final short[] DFA93_eot = DFA.unpackEncodedString(DFA93_eotS);
    static final short[] DFA93_eof = DFA.unpackEncodedString(DFA93_eofS);
    static final char[] DFA93_min = DFA.unpackEncodedStringToUnsignedChars(DFA93_minS);
    static final char[] DFA93_max = DFA.unpackEncodedStringToUnsignedChars(DFA93_maxS);
    static final short[] DFA93_accept = DFA.unpackEncodedString(DFA93_acceptS);
    static final short[] DFA93_special = DFA.unpackEncodedString(DFA93_specialS);
    static final short[][] DFA93_transition;

    static {
        int numStates = DFA93_transitionS.length;
        DFA93_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA93_transition[i] = DFA.unpackEncodedString(DFA93_transitionS[i]);
        }
    }

    class DFA93 extends DFA {

        public DFA93(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 93;
            this.eot = DFA93_eot;
            this.eof = DFA93_eof;
            this.min = DFA93_min;
            this.max = DFA93_max;
            this.accept = DFA93_accept;
            this.special = DFA93_special;
            this.transition = DFA93_transition;
        }
        public String getDescription() {
            return "()* loopback of 190:48: ( ( LT )* caseClause )*";
        }
    }
    static final String DFA106_eotS =
        "\4\uffff";
    static final String DFA106_eofS =
        "\2\3\2\uffff";
    static final String DFA106_minS =
        "\2\4\2\uffff";
    static final String DFA106_maxS =
        "\2\152\2\uffff";
    static final String DFA106_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA106_specialS =
        "\4\uffff}>";
    static final String[] DFA106_transitionS = {
            "\1\1\3\3\27\uffff\2\3\2\uffff\4\3\1\uffff\5\3\1\uffff\4\3\1"+
            "\uffff\5\3\1\uffff\1\2\2\3\37\uffff\2\3\3\uffff\13\3",
            "\1\1\3\3\27\uffff\2\3\2\uffff\4\3\1\uffff\5\3\1\uffff\4\3\1"+
            "\uffff\5\3\1\uffff\1\2\2\3\37\uffff\2\3\3\uffff\13\3",
            "",
            ""
    };

    static final short[] DFA106_eot = DFA.unpackEncodedString(DFA106_eotS);
    static final short[] DFA106_eof = DFA.unpackEncodedString(DFA106_eofS);
    static final char[] DFA106_min = DFA.unpackEncodedStringToUnsignedChars(DFA106_minS);
    static final char[] DFA106_max = DFA.unpackEncodedStringToUnsignedChars(DFA106_maxS);
    static final short[] DFA106_accept = DFA.unpackEncodedString(DFA106_acceptS);
    static final short[] DFA106_special = DFA.unpackEncodedString(DFA106_specialS);
    static final short[][] DFA106_transition;

    static {
        int numStates = DFA106_transitionS.length;
        DFA106_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA106_transition[i] = DFA.unpackEncodedString(DFA106_transitionS[i]);
        }
    }

    class DFA106 extends DFA {

        public DFA106(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 106;
            this.eot = DFA106_eot;
            this.eof = DFA106_eof;
            this.min = DFA106_min;
            this.max = DFA106_max;
            this.accept = DFA106_accept;
            this.special = DFA106_special;
            this.transition = DFA106_transition;
        }
        public String getDescription() {
            return "206:65: ( ( LT )* finallyClause )?";
        }
    }
    static final String DFA115_eotS =
        "\5\uffff";
    static final String DFA115_eofS =
        "\2\2\2\uffff\1\2";
    static final String DFA115_minS =
        "\2\4\2\uffff\1\4";
    static final String DFA115_maxS =
        "\1\74\1\152\2\uffff\1\152";
    static final String DFA115_acceptS =
        "\2\uffff\1\2\1\1\1\uffff";
    static final String DFA115_specialS =
        "\5\uffff}>";
    static final String[] DFA115_transitionS = {
            "\1\1\34\uffff\1\3\1\2\3\uffff\1\2\13\uffff\1\2\11\uffff\1\2",
            "\1\4\3\2\27\uffff\2\2\1\3\5\2\1\uffff\5\2\1\uffff\12\2\2\uffff"+
            "\3\2\36\uffff\2\2\3\uffff\13\2",
            "",
            "",
            "\1\4\3\2\27\uffff\2\2\1\3\5\2\1\uffff\5\2\1\uffff\12\2\2\uffff"+
            "\3\2\36\uffff\2\2\3\uffff\13\2"
    };

    static final short[] DFA115_eot = DFA.unpackEncodedString(DFA115_eotS);
    static final short[] DFA115_eof = DFA.unpackEncodedString(DFA115_eofS);
    static final char[] DFA115_min = DFA.unpackEncodedStringToUnsignedChars(DFA115_minS);
    static final char[] DFA115_max = DFA.unpackEncodedStringToUnsignedChars(DFA115_maxS);
    static final short[] DFA115_accept = DFA.unpackEncodedString(DFA115_acceptS);
    static final short[] DFA115_special = DFA.unpackEncodedString(DFA115_specialS);
    static final short[][] DFA115_transition;

    static {
        int numStates = DFA115_transitionS.length;
        DFA115_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA115_transition[i] = DFA.unpackEncodedString(DFA115_transitionS[i]);
        }
    }

    class DFA115 extends DFA {

        public DFA115(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 115;
            this.eot = DFA115_eot;
            this.eof = DFA115_eof;
            this.min = DFA115_min;
            this.max = DFA115_max;
            this.accept = DFA115_accept;
            this.special = DFA115_special;
            this.transition = DFA115_transition;
        }
        public String getDescription() {
            return "()* loopback of 219:26: ( ( LT )* ',' ( LT )* assignmentExpression )*";
        }
    }
    static final String DFA118_eotS =
        "\4\uffff";
    static final String DFA118_eofS =
        "\1\2\3\uffff";
    static final String DFA118_minS =
        "\2\4\2\uffff";
    static final String DFA118_maxS =
        "\2\46\2\uffff";
    static final String DFA118_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA118_specialS =
        "\4\uffff}>";
    static final String[] DFA118_transitionS = {
            "\1\1\34\uffff\1\3\4\uffff\1\2",
            "\1\1\34\uffff\1\3\4\uffff\1\2",
            "",
            ""
    };

    static final short[] DFA118_eot = DFA.unpackEncodedString(DFA118_eotS);
    static final short[] DFA118_eof = DFA.unpackEncodedString(DFA118_eofS);
    static final char[] DFA118_min = DFA.unpackEncodedStringToUnsignedChars(DFA118_minS);
    static final char[] DFA118_max = DFA.unpackEncodedStringToUnsignedChars(DFA118_maxS);
    static final short[] DFA118_accept = DFA.unpackEncodedString(DFA118_acceptS);
    static final short[] DFA118_special = DFA.unpackEncodedString(DFA118_specialS);
    static final short[][] DFA118_transition;

    static {
        int numStates = DFA118_transitionS.length;
        DFA118_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA118_transition[i] = DFA.unpackEncodedString(DFA118_transitionS[i]);
        }
    }

    class DFA118 extends DFA {

        public DFA118(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 118;
            this.eot = DFA118_eot;
            this.eof = DFA118_eof;
            this.min = DFA118_min;
            this.max = DFA118_max;
            this.accept = DFA118_accept;
            this.special = DFA118_special;
            this.transition = DFA118_transition;
        }
        public String getDescription() {
            return "()* loopback of 223:30: ( ( LT )* ',' ( LT )* assignmentExpressionNoIn )*";
        }
    }
    static final String DFA121_eotS =
        "\13\uffff";
    static final String DFA121_eofS =
        "\13\uffff";
    static final String DFA121_minS =
        "\1\5\10\0\2\uffff";
    static final String DFA121_maxS =
        "\1\152\10\0\2\uffff";
    static final String DFA121_acceptS =
        "\11\uffff\1\1\1\2";
    static final String DFA121_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\2\uffff}>";
    static final String[] DFA121_transitionS = {
            "\1\2\2\3\27\uffff\1\7\1\6\2\uffff\1\5\26\uffff\1\10\1\4\37\uffff"+
            "\2\11\3\uffff\7\11\1\1\3\3",
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

    static final short[] DFA121_eot = DFA.unpackEncodedString(DFA121_eotS);
    static final short[] DFA121_eof = DFA.unpackEncodedString(DFA121_eofS);
    static final char[] DFA121_min = DFA.unpackEncodedStringToUnsignedChars(DFA121_minS);
    static final char[] DFA121_max = DFA.unpackEncodedStringToUnsignedChars(DFA121_maxS);
    static final short[] DFA121_accept = DFA.unpackEncodedString(DFA121_acceptS);
    static final short[] DFA121_special = DFA.unpackEncodedString(DFA121_specialS);
    static final short[][] DFA121_transition;

    static {
        int numStates = DFA121_transitionS.length;
        DFA121_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA121_transition[i] = DFA.unpackEncodedString(DFA121_transitionS[i]);
        }
    }

    class DFA121 extends DFA {

        public DFA121(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 121;
            this.eot = DFA121_eot;
            this.eof = DFA121_eof;
            this.min = DFA121_min;
            this.max = DFA121_max;
            this.accept = DFA121_accept;
            this.special = DFA121_special;
            this.transition = DFA121_transition;
        }
        public String getDescription() {
            return "226:1: assignmentExpression : ( conditionalExpression | leftHandSideExpression ( LT )* assignmentOperator ( LT )* assignmentExpression );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA121_1 = input.LA(1);

                         
                        int index121_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred140_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index121_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA121_2 = input.LA(1);

                         
                        int index121_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred140_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index121_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA121_3 = input.LA(1);

                         
                        int index121_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred140_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index121_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA121_4 = input.LA(1);

                         
                        int index121_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred140_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index121_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA121_5 = input.LA(1);

                         
                        int index121_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred140_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index121_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA121_6 = input.LA(1);

                         
                        int index121_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred140_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index121_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA121_7 = input.LA(1);

                         
                        int index121_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred140_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index121_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA121_8 = input.LA(1);

                         
                        int index121_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred140_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index121_8);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 121, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA124_eotS =
        "\13\uffff";
    static final String DFA124_eofS =
        "\13\uffff";
    static final String DFA124_minS =
        "\1\5\10\0\2\uffff";
    static final String DFA124_maxS =
        "\1\152\10\0\2\uffff";
    static final String DFA124_acceptS =
        "\11\uffff\1\1\1\2";
    static final String DFA124_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\2\uffff}>";
    static final String[] DFA124_transitionS = {
            "\1\2\2\3\27\uffff\1\7\1\6\2\uffff\1\5\26\uffff\1\10\1\4\37\uffff"+
            "\2\11\3\uffff\7\11\1\1\3\3",
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

    static final short[] DFA124_eot = DFA.unpackEncodedString(DFA124_eotS);
    static final short[] DFA124_eof = DFA.unpackEncodedString(DFA124_eofS);
    static final char[] DFA124_min = DFA.unpackEncodedStringToUnsignedChars(DFA124_minS);
    static final char[] DFA124_max = DFA.unpackEncodedStringToUnsignedChars(DFA124_maxS);
    static final short[] DFA124_accept = DFA.unpackEncodedString(DFA124_acceptS);
    static final short[] DFA124_special = DFA.unpackEncodedString(DFA124_specialS);
    static final short[][] DFA124_transition;

    static {
        int numStates = DFA124_transitionS.length;
        DFA124_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA124_transition[i] = DFA.unpackEncodedString(DFA124_transitionS[i]);
        }
    }

    class DFA124 extends DFA {

        public DFA124(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 124;
            this.eot = DFA124_eot;
            this.eof = DFA124_eof;
            this.min = DFA124_min;
            this.max = DFA124_max;
            this.accept = DFA124_accept;
            this.special = DFA124_special;
            this.transition = DFA124_transition;
        }
        public String getDescription() {
            return "231:1: assignmentExpressionNoIn : ( conditionalExpressionNoIn | leftHandSideExpression ( LT )* assignmentOperator ( LT )* assignmentExpressionNoIn );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA124_1 = input.LA(1);

                         
                        int index124_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred143_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index124_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA124_2 = input.LA(1);

                         
                        int index124_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred143_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index124_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA124_3 = input.LA(1);

                         
                        int index124_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred143_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index124_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA124_4 = input.LA(1);

                         
                        int index124_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred143_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index124_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA124_5 = input.LA(1);

                         
                        int index124_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred143_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index124_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA124_6 = input.LA(1);

                         
                        int index124_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred143_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index124_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA124_7 = input.LA(1);

                         
                        int index124_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred143_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index124_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA124_8 = input.LA(1);

                         
                        int index124_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred143_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index124_8);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 124, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA125_eotS =
        "\13\uffff";
    static final String DFA125_eofS =
        "\13\uffff";
    static final String DFA125_minS =
        "\1\5\10\0\2\uffff";
    static final String DFA125_maxS =
        "\1\152\10\0\2\uffff";
    static final String DFA125_acceptS =
        "\11\uffff\1\1\1\2";
    static final String DFA125_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\2\uffff}>";
    static final String[] DFA125_transitionS = {
            "\1\2\2\3\27\uffff\1\7\1\6\2\uffff\1\5\26\uffff\1\10\1\4\53\uffff"+
            "\1\1\3\3",
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

    static final short[] DFA125_eot = DFA.unpackEncodedString(DFA125_eotS);
    static final short[] DFA125_eof = DFA.unpackEncodedString(DFA125_eofS);
    static final char[] DFA125_min = DFA.unpackEncodedStringToUnsignedChars(DFA125_minS);
    static final char[] DFA125_max = DFA.unpackEncodedStringToUnsignedChars(DFA125_maxS);
    static final short[] DFA125_accept = DFA.unpackEncodedString(DFA125_acceptS);
    static final short[] DFA125_special = DFA.unpackEncodedString(DFA125_specialS);
    static final short[][] DFA125_transition;

    static {
        int numStates = DFA125_transitionS.length;
        DFA125_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA125_transition[i] = DFA.unpackEncodedString(DFA125_transitionS[i]);
        }
    }

    class DFA125 extends DFA {

        public DFA125(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 125;
            this.eot = DFA125_eot;
            this.eof = DFA125_eof;
            this.min = DFA125_min;
            this.max = DFA125_max;
            this.accept = DFA125_accept;
            this.special = DFA125_special;
            this.transition = DFA125_transition;
        }
        public String getDescription() {
            return "236:1: leftHandSideExpression : ( callExpression | newExpression );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA125_1 = input.LA(1);

                         
                        int index125_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index125_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA125_2 = input.LA(1);

                         
                        int index125_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index125_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA125_3 = input.LA(1);

                         
                        int index125_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index125_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA125_4 = input.LA(1);

                         
                        int index125_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index125_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA125_5 = input.LA(1);

                         
                        int index125_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index125_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA125_6 = input.LA(1);

                         
                        int index125_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index125_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA125_7 = input.LA(1);

                         
                        int index125_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index125_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA125_8 = input.LA(1);

                         
                        int index125_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_JavaScript()) ) {s = 9;}

                        else if ( (true) ) {s = 10;}

                         
                        input.seek(index125_8);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 125, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA127_eotS =
        "\12\uffff";
    static final String DFA127_eofS =
        "\12\uffff";
    static final String DFA127_minS =
        "\1\5\7\uffff\1\0\1\uffff";
    static final String DFA127_maxS =
        "\1\152\7\uffff\1\0\1\uffff";
    static final String DFA127_acceptS =
        "\1\uffff\1\1\7\uffff\1\2";
    static final String DFA127_specialS =
        "\10\uffff\1\0\1\uffff}>";
    static final String[] DFA127_transitionS = {
            "\3\1\27\uffff\2\1\2\uffff\1\1\26\uffff\1\10\1\1\53\uffff\4\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            ""
    };

    static final short[] DFA127_eot = DFA.unpackEncodedString(DFA127_eotS);
    static final short[] DFA127_eof = DFA.unpackEncodedString(DFA127_eofS);
    static final char[] DFA127_min = DFA.unpackEncodedStringToUnsignedChars(DFA127_minS);
    static final char[] DFA127_max = DFA.unpackEncodedStringToUnsignedChars(DFA127_maxS);
    static final short[] DFA127_accept = DFA.unpackEncodedString(DFA127_acceptS);
    static final short[] DFA127_special = DFA.unpackEncodedString(DFA127_specialS);
    static final short[][] DFA127_transition;

    static {
        int numStates = DFA127_transitionS.length;
        DFA127_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA127_transition[i] = DFA.unpackEncodedString(DFA127_transitionS[i]);
        }
    }

    class DFA127 extends DFA {

        public DFA127(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 127;
            this.eot = DFA127_eot;
            this.eof = DFA127_eof;
            this.min = DFA127_min;
            this.max = DFA127_max;
            this.accept = DFA127_accept;
            this.special = DFA127_special;
            this.transition = DFA127_transition;
        }
        public String getDescription() {
            return "241:1: newExpression : ( memberExpression | 'new' ( LT )* newExpression );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA127_8 = input.LA(1);

                         
                        int index127_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred147_JavaScript()) ) {s = 1;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index127_8);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 127, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA132_eotS =
        "\32\uffff";
    static final String DFA132_eofS =
        "\1\2\31\uffff";
    static final String DFA132_minS =
        "\1\4\1\0\30\uffff";
    static final String DFA132_maxS =
        "\1\144\1\0\30\uffff";
    static final String DFA132_acceptS =
        "\2\uffff\1\2\25\uffff\1\1\1\uffff";
    static final String DFA132_specialS =
        "\1\uffff\1\0\30\uffff}>";
    static final String[] DFA132_transitionS = {
            "\1\1\33\uffff\3\2\1\uffff\1\2\1\uffff\2\2\5\uffff\1\2\4\uffff"+
            "\1\2\10\uffff\1\30\1\2\1\30\42\2\3\uffff\2\2",
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
            ""
    };

    static final short[] DFA132_eot = DFA.unpackEncodedString(DFA132_eotS);
    static final short[] DFA132_eof = DFA.unpackEncodedString(DFA132_eofS);
    static final char[] DFA132_min = DFA.unpackEncodedStringToUnsignedChars(DFA132_minS);
    static final char[] DFA132_max = DFA.unpackEncodedStringToUnsignedChars(DFA132_maxS);
    static final short[] DFA132_accept = DFA.unpackEncodedString(DFA132_acceptS);
    static final short[] DFA132_special = DFA.unpackEncodedString(DFA132_specialS);
    static final short[][] DFA132_transition;

    static {
        int numStates = DFA132_transitionS.length;
        DFA132_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA132_transition[i] = DFA.unpackEncodedString(DFA132_transitionS[i]);
        }
    }

    class DFA132 extends DFA {

        public DFA132(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 132;
            this.eot = DFA132_eot;
            this.eof = DFA132_eof;
            this.min = DFA132_min;
            this.max = DFA132_max;
            this.accept = DFA132_accept;
            this.special = DFA132_special;
            this.transition = DFA132_transition;
        }
        public String getDescription() {
            return "()* loopback of 247:91: ( ( LT )* memberExpressionSuffix )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA132_1 = input.LA(1);

                         
                        int index132_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred154_JavaScript()) ) {s = 24;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index132_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 132, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA136_eotS =
        "\32\uffff";
    static final String DFA136_eofS =
        "\1\2\31\uffff";
    static final String DFA136_minS =
        "\1\4\1\0\30\uffff";
    static final String DFA136_maxS =
        "\1\144\1\0\30\uffff";
    static final String DFA136_acceptS =
        "\2\uffff\1\2\24\uffff\1\1\2\uffff";
    static final String DFA136_specialS =
        "\1\uffff\1\0\30\uffff}>";
    static final String[] DFA136_transitionS = {
            "\1\1\33\uffff\1\27\2\2\1\uffff\1\2\1\uffff\2\2\5\uffff\1\2\4"+
            "\uffff\1\2\10\uffff\1\27\1\2\1\27\42\2\3\uffff\2\2",
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
            ""
    };

    static final short[] DFA136_eot = DFA.unpackEncodedString(DFA136_eotS);
    static final short[] DFA136_eof = DFA.unpackEncodedString(DFA136_eofS);
    static final char[] DFA136_min = DFA.unpackEncodedStringToUnsignedChars(DFA136_minS);
    static final char[] DFA136_max = DFA.unpackEncodedStringToUnsignedChars(DFA136_maxS);
    static final short[] DFA136_accept = DFA.unpackEncodedString(DFA136_acceptS);
    static final short[] DFA136_special = DFA.unpackEncodedString(DFA136_specialS);
    static final short[][] DFA136_transition;

    static {
        int numStates = DFA136_transitionS.length;
        DFA136_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA136_transition[i] = DFA.unpackEncodedString(DFA136_transitionS[i]);
        }
    }

    class DFA136 extends DFA {

        public DFA136(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 136;
            this.eot = DFA136_eot;
            this.eof = DFA136_eof;
            this.min = DFA136_min;
            this.max = DFA136_max;
            this.accept = DFA136_accept;
            this.special = DFA136_special;
            this.transition = DFA136_transition;
        }
        public String getDescription() {
            return "()* loopback of 256:37: ( ( LT )* callExpressionSuffix )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA136_1 = input.LA(1);

                         
                        int index136_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred158_JavaScript()) ) {s = 23;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index136_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 136, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA142_eotS =
        "\4\uffff";
    static final String DFA142_eofS =
        "\4\uffff";
    static final String DFA142_minS =
        "\2\4\2\uffff";
    static final String DFA142_maxS =
        "\2\152\2\uffff";
    static final String DFA142_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA142_specialS =
        "\4\uffff}>";
    static final String[] DFA142_transitionS = {
            "\1\1\3\2\27\uffff\2\2\1\uffff\1\3\1\2\26\uffff\2\2\37\uffff"+
            "\2\2\3\uffff\13\2",
            "\1\1\3\2\27\uffff\2\2\1\uffff\1\3\1\2\26\uffff\2\2\37\uffff"+
            "\2\2\3\uffff\13\2",
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
            return "266:9: ( ( LT )* assignmentExpression ( ( LT )* ',' ( LT )* assignmentExpression )* )?";
        }
    }
    static final String DFA141_eotS =
        "\4\uffff";
    static final String DFA141_eofS =
        "\4\uffff";
    static final String DFA141_minS =
        "\2\4\2\uffff";
    static final String DFA141_maxS =
        "\2\42\2\uffff";
    static final String DFA141_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA141_specialS =
        "\4\uffff}>";
    static final String[] DFA141_transitionS = {
            "\1\1\34\uffff\1\3\1\2",
            "\1\1\34\uffff\1\3\1\2",
            "",
            ""
    };

    static final short[] DFA141_eot = DFA.unpackEncodedString(DFA141_eotS);
    static final short[] DFA141_eof = DFA.unpackEncodedString(DFA141_eofS);
    static final char[] DFA141_min = DFA.unpackEncodedStringToUnsignedChars(DFA141_minS);
    static final char[] DFA141_max = DFA.unpackEncodedStringToUnsignedChars(DFA141_maxS);
    static final short[] DFA141_accept = DFA.unpackEncodedString(DFA141_acceptS);
    static final short[] DFA141_special = DFA.unpackEncodedString(DFA141_specialS);
    static final short[][] DFA141_transition;

    static {
        int numStates = DFA141_transitionS.length;
        DFA141_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA141_transition[i] = DFA.unpackEncodedString(DFA141_transitionS[i]);
        }
    }

    class DFA141 extends DFA {

        public DFA141(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 141;
            this.eot = DFA141_eot;
            this.eof = DFA141_eof;
            this.min = DFA141_min;
            this.max = DFA141_max;
            this.accept = DFA141_accept;
            this.special = DFA141_special;
            this.transition = DFA141_transition;
        }
        public String getDescription() {
            return "()* loopback of 266:36: ( ( LT )* ',' ( LT )* assignmentExpression )*";
        }
    }
    static final String DFA151_eotS =
        "\5\uffff";
    static final String DFA151_eofS =
        "\2\3\2\uffff\1\3";
    static final String DFA151_minS =
        "\2\4\2\uffff\1\4";
    static final String DFA151_maxS =
        "\1\111\1\152\2\uffff\1\152";
    static final String DFA151_acceptS =
        "\2\uffff\1\1\1\2\1\uffff";
    static final String DFA151_specialS =
        "\5\uffff}>";
    static final String[] DFA151_transitionS = {
            "\1\1\34\uffff\2\3\1\uffff\1\3\1\uffff\1\3\13\uffff\1\3\11\uffff"+
            "\1\3\14\uffff\1\2",
            "\1\4\3\3\27\uffff\10\3\1\uffff\5\3\1\uffff\12\3\2\uffff\3\3"+
            "\14\uffff\1\2\21\uffff\2\3\3\uffff\13\3",
            "",
            "",
            "\1\4\3\3\27\uffff\10\3\1\uffff\5\3\1\uffff\12\3\2\uffff\3\3"+
            "\14\uffff\1\2\21\uffff\2\3\3\uffff\13\3"
    };

    static final short[] DFA151_eot = DFA.unpackEncodedString(DFA151_eotS);
    static final short[] DFA151_eof = DFA.unpackEncodedString(DFA151_eofS);
    static final char[] DFA151_min = DFA.unpackEncodedStringToUnsignedChars(DFA151_minS);
    static final char[] DFA151_max = DFA.unpackEncodedStringToUnsignedChars(DFA151_maxS);
    static final short[] DFA151_accept = DFA.unpackEncodedString(DFA151_acceptS);
    static final short[] DFA151_special = DFA.unpackEncodedString(DFA151_specialS);
    static final short[][] DFA151_transition;

    static {
        int numStates = DFA151_transitionS.length;
        DFA151_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA151_transition[i] = DFA.unpackEncodedString(DFA151_transitionS[i]);
        }
    }

    class DFA151 extends DFA {

        public DFA151(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 151;
            this.eot = DFA151_eot;
            this.eof = DFA151_eof;
            this.min = DFA151_min;
            this.max = DFA151_max;
            this.accept = DFA151_accept;
            this.special = DFA151_special;
            this.transition = DFA151_transition;
        }
        public String getDescription() {
            return "282:25: ( ( LT )* '?' ( LT )* assignmentExpression ( LT )* ':' ( LT )* assignmentExpression )?";
        }
    }
    static final String DFA156_eotS =
        "\4\uffff";
    static final String DFA156_eofS =
        "\1\3\3\uffff";
    static final String DFA156_minS =
        "\2\4\2\uffff";
    static final String DFA156_maxS =
        "\2\111\2\uffff";
    static final String DFA156_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA156_specialS =
        "\4\uffff}>";
    static final String[] DFA156_transitionS = {
            "\1\1\34\uffff\1\3\4\uffff\1\3\6\uffff\1\3\4\uffff\1\3\26\uffff"+
            "\1\2",
            "\1\1\34\uffff\1\3\4\uffff\1\3\6\uffff\1\3\4\uffff\1\3\26\uffff"+
            "\1\2",
            "",
            ""
    };

    static final short[] DFA156_eot = DFA.unpackEncodedString(DFA156_eotS);
    static final short[] DFA156_eof = DFA.unpackEncodedString(DFA156_eofS);
    static final char[] DFA156_min = DFA.unpackEncodedStringToUnsignedChars(DFA156_minS);
    static final char[] DFA156_max = DFA.unpackEncodedStringToUnsignedChars(DFA156_maxS);
    static final short[] DFA156_accept = DFA.unpackEncodedString(DFA156_acceptS);
    static final short[] DFA156_special = DFA.unpackEncodedString(DFA156_specialS);
    static final short[][] DFA156_transition;

    static {
        int numStates = DFA156_transitionS.length;
        DFA156_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA156_transition[i] = DFA.unpackEncodedString(DFA156_transitionS[i]);
        }
    }

    class DFA156 extends DFA {

        public DFA156(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 156;
            this.eot = DFA156_eot;
            this.eof = DFA156_eof;
            this.min = DFA156_min;
            this.max = DFA156_max;
            this.accept = DFA156_accept;
            this.special = DFA156_special;
            this.transition = DFA156_transition;
        }
        public String getDescription() {
            return "286:29: ( ( LT )* '?' ( LT )* assignmentExpressionNoIn ( LT )* ':' ( LT )* assignmentExpressionNoIn )?";
        }
    }
    static final String DFA159_eotS =
        "\5\uffff";
    static final String DFA159_eofS =
        "\2\2\2\uffff\1\2";
    static final String DFA159_minS =
        "\2\4\2\uffff\1\4";
    static final String DFA159_maxS =
        "\1\112\1\152\2\uffff\1\152";
    static final String DFA159_acceptS =
        "\2\uffff\1\2\1\1\1\uffff";
    static final String DFA159_specialS =
        "\5\uffff}>";
    static final String[] DFA159_transitionS = {
            "\1\1\34\uffff\2\2\1\uffff\1\2\1\uffff\1\2\13\uffff\1\2\11\uffff"+
            "\1\2\14\uffff\1\2\1\3",
            "\1\4\3\2\27\uffff\10\2\1\uffff\5\2\1\uffff\12\2\2\uffff\3\2"+
            "\14\uffff\1\2\1\3\20\uffff\2\2\3\uffff\13\2",
            "",
            "",
            "\1\4\3\2\27\uffff\10\2\1\uffff\5\2\1\uffff\12\2\2\uffff\3\2"+
            "\14\uffff\1\2\1\3\20\uffff\2\2\3\uffff\13\2"
    };

    static final short[] DFA159_eot = DFA.unpackEncodedString(DFA159_eotS);
    static final short[] DFA159_eof = DFA.unpackEncodedString(DFA159_eofS);
    static final char[] DFA159_min = DFA.unpackEncodedStringToUnsignedChars(DFA159_minS);
    static final char[] DFA159_max = DFA.unpackEncodedStringToUnsignedChars(DFA159_maxS);
    static final short[] DFA159_accept = DFA.unpackEncodedString(DFA159_acceptS);
    static final short[] DFA159_special = DFA.unpackEncodedString(DFA159_specialS);
    static final short[][] DFA159_transition;

    static {
        int numStates = DFA159_transitionS.length;
        DFA159_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA159_transition[i] = DFA.unpackEncodedString(DFA159_transitionS[i]);
        }
    }

    class DFA159 extends DFA {

        public DFA159(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 159;
            this.eot = DFA159_eot;
            this.eof = DFA159_eof;
            this.min = DFA159_min;
            this.max = DFA159_max;
            this.accept = DFA159_accept;
            this.special = DFA159_special;
            this.transition = DFA159_transition;
        }
        public String getDescription() {
            return "()* loopback of 290:26: ( ( LT )* '||' ( LT )* logicalANDExpression )*";
        }
    }
    static final String DFA162_eotS =
        "\4\uffff";
    static final String DFA162_eofS =
        "\1\2\3\uffff";
    static final String DFA162_minS =
        "\2\4\2\uffff";
    static final String DFA162_maxS =
        "\2\112\2\uffff";
    static final String DFA162_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA162_specialS =
        "\4\uffff}>";
    static final String[] DFA162_transitionS = {
            "\1\1\34\uffff\1\2\4\uffff\1\2\6\uffff\1\2\4\uffff\1\2\26\uffff"+
            "\1\2\1\3",
            "\1\1\34\uffff\1\2\4\uffff\1\2\6\uffff\1\2\4\uffff\1\2\26\uffff"+
            "\1\2\1\3",
            "",
            ""
    };

    static final short[] DFA162_eot = DFA.unpackEncodedString(DFA162_eotS);
    static final short[] DFA162_eof = DFA.unpackEncodedString(DFA162_eofS);
    static final char[] DFA162_min = DFA.unpackEncodedStringToUnsignedChars(DFA162_minS);
    static final char[] DFA162_max = DFA.unpackEncodedStringToUnsignedChars(DFA162_maxS);
    static final short[] DFA162_accept = DFA.unpackEncodedString(DFA162_acceptS);
    static final short[] DFA162_special = DFA.unpackEncodedString(DFA162_specialS);
    static final short[][] DFA162_transition;

    static {
        int numStates = DFA162_transitionS.length;
        DFA162_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA162_transition[i] = DFA.unpackEncodedString(DFA162_transitionS[i]);
        }
    }

    class DFA162 extends DFA {

        public DFA162(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 162;
            this.eot = DFA162_eot;
            this.eof = DFA162_eof;
            this.min = DFA162_min;
            this.max = DFA162_max;
            this.accept = DFA162_accept;
            this.special = DFA162_special;
            this.transition = DFA162_transition;
        }
        public String getDescription() {
            return "()* loopback of 294:30: ( ( LT )* '||' ( LT )* logicalANDExpressionNoIn )*";
        }
    }
    static final String DFA165_eotS =
        "\5\uffff";
    static final String DFA165_eofS =
        "\2\2\2\uffff\1\2";
    static final String DFA165_minS =
        "\2\4\2\uffff\1\4";
    static final String DFA165_maxS =
        "\1\113\1\152\2\uffff\1\152";
    static final String DFA165_acceptS =
        "\2\uffff\1\2\1\1\1\uffff";
    static final String DFA165_specialS =
        "\5\uffff}>";
    static final String[] DFA165_transitionS = {
            "\1\1\34\uffff\2\2\1\uffff\1\2\1\uffff\1\2\13\uffff\1\2\11\uffff"+
            "\1\2\14\uffff\2\2\1\3",
            "\1\4\3\2\27\uffff\10\2\1\uffff\5\2\1\uffff\12\2\2\uffff\3\2"+
            "\14\uffff\2\2\1\3\17\uffff\2\2\3\uffff\13\2",
            "",
            "",
            "\1\4\3\2\27\uffff\10\2\1\uffff\5\2\1\uffff\12\2\2\uffff\3\2"+
            "\14\uffff\2\2\1\3\17\uffff\2\2\3\uffff\13\2"
    };

    static final short[] DFA165_eot = DFA.unpackEncodedString(DFA165_eotS);
    static final short[] DFA165_eof = DFA.unpackEncodedString(DFA165_eofS);
    static final char[] DFA165_min = DFA.unpackEncodedStringToUnsignedChars(DFA165_minS);
    static final char[] DFA165_max = DFA.unpackEncodedStringToUnsignedChars(DFA165_maxS);
    static final short[] DFA165_accept = DFA.unpackEncodedString(DFA165_acceptS);
    static final short[] DFA165_special = DFA.unpackEncodedString(DFA165_specialS);
    static final short[][] DFA165_transition;

    static {
        int numStates = DFA165_transitionS.length;
        DFA165_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA165_transition[i] = DFA.unpackEncodedString(DFA165_transitionS[i]);
        }
    }

    class DFA165 extends DFA {

        public DFA165(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 165;
            this.eot = DFA165_eot;
            this.eof = DFA165_eof;
            this.min = DFA165_min;
            this.max = DFA165_max;
            this.accept = DFA165_accept;
            this.special = DFA165_special;
            this.transition = DFA165_transition;
        }
        public String getDescription() {
            return "()* loopback of 298:25: ( ( LT )* '&&' ( LT )* bitwiseORExpression )*";
        }
    }
    static final String DFA168_eotS =
        "\4\uffff";
    static final String DFA168_eofS =
        "\1\2\3\uffff";
    static final String DFA168_minS =
        "\2\4\2\uffff";
    static final String DFA168_maxS =
        "\2\113\2\uffff";
    static final String DFA168_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA168_specialS =
        "\4\uffff}>";
    static final String[] DFA168_transitionS = {
            "\1\1\34\uffff\1\2\4\uffff\1\2\6\uffff\1\2\4\uffff\1\2\26\uffff"+
            "\2\2\1\3",
            "\1\1\34\uffff\1\2\4\uffff\1\2\6\uffff\1\2\4\uffff\1\2\26\uffff"+
            "\2\2\1\3",
            "",
            ""
    };

    static final short[] DFA168_eot = DFA.unpackEncodedString(DFA168_eotS);
    static final short[] DFA168_eof = DFA.unpackEncodedString(DFA168_eofS);
    static final char[] DFA168_min = DFA.unpackEncodedStringToUnsignedChars(DFA168_minS);
    static final char[] DFA168_max = DFA.unpackEncodedStringToUnsignedChars(DFA168_maxS);
    static final short[] DFA168_accept = DFA.unpackEncodedString(DFA168_acceptS);
    static final short[] DFA168_special = DFA.unpackEncodedString(DFA168_specialS);
    static final short[][] DFA168_transition;

    static {
        int numStates = DFA168_transitionS.length;
        DFA168_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA168_transition[i] = DFA.unpackEncodedString(DFA168_transitionS[i]);
        }
    }

    class DFA168 extends DFA {

        public DFA168(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 168;
            this.eot = DFA168_eot;
            this.eof = DFA168_eof;
            this.min = DFA168_min;
            this.max = DFA168_max;
            this.accept = DFA168_accept;
            this.special = DFA168_special;
            this.transition = DFA168_transition;
        }
        public String getDescription() {
            return "()* loopback of 302:29: ( ( LT )* '&&' ( LT )* bitwiseORExpressionNoIn )*";
        }
    }
    static final String DFA171_eotS =
        "\5\uffff";
    static final String DFA171_eofS =
        "\2\2\2\uffff\1\2";
    static final String DFA171_minS =
        "\2\4\2\uffff\1\4";
    static final String DFA171_maxS =
        "\1\114\1\152\2\uffff\1\152";
    static final String DFA171_acceptS =
        "\2\uffff\1\2\1\1\1\uffff";
    static final String DFA171_specialS =
        "\5\uffff}>";
    static final String[] DFA171_transitionS = {
            "\1\1\34\uffff\2\2\1\uffff\1\2\1\uffff\1\2\13\uffff\1\2\11\uffff"+
            "\1\2\14\uffff\3\2\1\3",
            "\1\4\3\2\27\uffff\10\2\1\uffff\5\2\1\uffff\12\2\2\uffff\3\2"+
            "\14\uffff\3\2\1\3\16\uffff\2\2\3\uffff\13\2",
            "",
            "",
            "\1\4\3\2\27\uffff\10\2\1\uffff\5\2\1\uffff\12\2\2\uffff\3\2"+
            "\14\uffff\3\2\1\3\16\uffff\2\2\3\uffff\13\2"
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
            return "()* loopback of 306:26: ( ( LT )* '|' ( LT )* bitwiseXORExpression )*";
        }
    }
    static final String DFA174_eotS =
        "\4\uffff";
    static final String DFA174_eofS =
        "\1\2\3\uffff";
    static final String DFA174_minS =
        "\2\4\2\uffff";
    static final String DFA174_maxS =
        "\2\114\2\uffff";
    static final String DFA174_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA174_specialS =
        "\4\uffff}>";
    static final String[] DFA174_transitionS = {
            "\1\1\34\uffff\1\2\4\uffff\1\2\6\uffff\1\2\4\uffff\1\2\26\uffff"+
            "\3\2\1\3",
            "\1\1\34\uffff\1\2\4\uffff\1\2\6\uffff\1\2\4\uffff\1\2\26\uffff"+
            "\3\2\1\3",
            "",
            ""
    };

    static final short[] DFA174_eot = DFA.unpackEncodedString(DFA174_eotS);
    static final short[] DFA174_eof = DFA.unpackEncodedString(DFA174_eofS);
    static final char[] DFA174_min = DFA.unpackEncodedStringToUnsignedChars(DFA174_minS);
    static final char[] DFA174_max = DFA.unpackEncodedStringToUnsignedChars(DFA174_maxS);
    static final short[] DFA174_accept = DFA.unpackEncodedString(DFA174_acceptS);
    static final short[] DFA174_special = DFA.unpackEncodedString(DFA174_specialS);
    static final short[][] DFA174_transition;

    static {
        int numStates = DFA174_transitionS.length;
        DFA174_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA174_transition[i] = DFA.unpackEncodedString(DFA174_transitionS[i]);
        }
    }

    class DFA174 extends DFA {

        public DFA174(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 174;
            this.eot = DFA174_eot;
            this.eof = DFA174_eof;
            this.min = DFA174_min;
            this.max = DFA174_max;
            this.accept = DFA174_accept;
            this.special = DFA174_special;
            this.transition = DFA174_transition;
        }
        public String getDescription() {
            return "()* loopback of 310:30: ( ( LT )* '|' ( LT )* bitwiseXORExpressionNoIn )*";
        }
    }
    static final String DFA177_eotS =
        "\5\uffff";
    static final String DFA177_eofS =
        "\2\2\2\uffff\1\2";
    static final String DFA177_minS =
        "\2\4\2\uffff\1\4";
    static final String DFA177_maxS =
        "\1\115\1\152\2\uffff\1\152";
    static final String DFA177_acceptS =
        "\2\uffff\1\2\1\1\1\uffff";
    static final String DFA177_specialS =
        "\5\uffff}>";
    static final String[] DFA177_transitionS = {
            "\1\1\34\uffff\2\2\1\uffff\1\2\1\uffff\1\2\13\uffff\1\2\11\uffff"+
            "\1\2\14\uffff\4\2\1\3",
            "\1\4\3\2\27\uffff\10\2\1\uffff\5\2\1\uffff\12\2\2\uffff\3\2"+
            "\14\uffff\4\2\1\3\15\uffff\2\2\3\uffff\13\2",
            "",
            "",
            "\1\4\3\2\27\uffff\10\2\1\uffff\5\2\1\uffff\12\2\2\uffff\3\2"+
            "\14\uffff\4\2\1\3\15\uffff\2\2\3\uffff\13\2"
    };

    static final short[] DFA177_eot = DFA.unpackEncodedString(DFA177_eotS);
    static final short[] DFA177_eof = DFA.unpackEncodedString(DFA177_eofS);
    static final char[] DFA177_min = DFA.unpackEncodedStringToUnsignedChars(DFA177_minS);
    static final char[] DFA177_max = DFA.unpackEncodedStringToUnsignedChars(DFA177_maxS);
    static final short[] DFA177_accept = DFA.unpackEncodedString(DFA177_acceptS);
    static final short[] DFA177_special = DFA.unpackEncodedString(DFA177_specialS);
    static final short[][] DFA177_transition;

    static {
        int numStates = DFA177_transitionS.length;
        DFA177_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA177_transition[i] = DFA.unpackEncodedString(DFA177_transitionS[i]);
        }
    }

    class DFA177 extends DFA {

        public DFA177(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 177;
            this.eot = DFA177_eot;
            this.eof = DFA177_eof;
            this.min = DFA177_min;
            this.max = DFA177_max;
            this.accept = DFA177_accept;
            this.special = DFA177_special;
            this.transition = DFA177_transition;
        }
        public String getDescription() {
            return "()* loopback of 314:26: ( ( LT )* '^' ( LT )* bitwiseANDExpression )*";
        }
    }
    static final String DFA180_eotS =
        "\4\uffff";
    static final String DFA180_eofS =
        "\1\2\3\uffff";
    static final String DFA180_minS =
        "\2\4\2\uffff";
    static final String DFA180_maxS =
        "\2\115\2\uffff";
    static final String DFA180_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA180_specialS =
        "\4\uffff}>";
    static final String[] DFA180_transitionS = {
            "\1\1\34\uffff\1\2\4\uffff\1\2\6\uffff\1\2\4\uffff\1\2\26\uffff"+
            "\4\2\1\3",
            "\1\1\34\uffff\1\2\4\uffff\1\2\6\uffff\1\2\4\uffff\1\2\26\uffff"+
            "\4\2\1\3",
            "",
            ""
    };

    static final short[] DFA180_eot = DFA.unpackEncodedString(DFA180_eotS);
    static final short[] DFA180_eof = DFA.unpackEncodedString(DFA180_eofS);
    static final char[] DFA180_min = DFA.unpackEncodedStringToUnsignedChars(DFA180_minS);
    static final char[] DFA180_max = DFA.unpackEncodedStringToUnsignedChars(DFA180_maxS);
    static final short[] DFA180_accept = DFA.unpackEncodedString(DFA180_acceptS);
    static final short[] DFA180_special = DFA.unpackEncodedString(DFA180_specialS);
    static final short[][] DFA180_transition;

    static {
        int numStates = DFA180_transitionS.length;
        DFA180_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA180_transition[i] = DFA.unpackEncodedString(DFA180_transitionS[i]);
        }
    }

    class DFA180 extends DFA {

        public DFA180(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 180;
            this.eot = DFA180_eot;
            this.eof = DFA180_eof;
            this.min = DFA180_min;
            this.max = DFA180_max;
            this.accept = DFA180_accept;
            this.special = DFA180_special;
            this.transition = DFA180_transition;
        }
        public String getDescription() {
            return "()* loopback of 318:30: ( ( LT )* '^' ( LT )* bitwiseANDExpressionNoIn )*";
        }
    }
    static final String DFA183_eotS =
        "\5\uffff";
    static final String DFA183_eofS =
        "\2\2\2\uffff\1\2";
    static final String DFA183_minS =
        "\2\4\2\uffff\1\4";
    static final String DFA183_maxS =
        "\1\116\1\152\2\uffff\1\152";
    static final String DFA183_acceptS =
        "\2\uffff\1\2\1\1\1\uffff";
    static final String DFA183_specialS =
        "\5\uffff}>";
    static final String[] DFA183_transitionS = {
            "\1\1\34\uffff\2\2\1\uffff\1\2\1\uffff\1\2\13\uffff\1\2\11\uffff"+
            "\1\2\14\uffff\5\2\1\3",
            "\1\4\3\2\27\uffff\10\2\1\uffff\5\2\1\uffff\12\2\2\uffff\3\2"+
            "\14\uffff\5\2\1\3\14\uffff\2\2\3\uffff\13\2",
            "",
            "",
            "\1\4\3\2\27\uffff\10\2\1\uffff\5\2\1\uffff\12\2\2\uffff\3\2"+
            "\14\uffff\5\2\1\3\14\uffff\2\2\3\uffff\13\2"
    };

    static final short[] DFA183_eot = DFA.unpackEncodedString(DFA183_eotS);
    static final short[] DFA183_eof = DFA.unpackEncodedString(DFA183_eofS);
    static final char[] DFA183_min = DFA.unpackEncodedStringToUnsignedChars(DFA183_minS);
    static final char[] DFA183_max = DFA.unpackEncodedStringToUnsignedChars(DFA183_maxS);
    static final short[] DFA183_accept = DFA.unpackEncodedString(DFA183_acceptS);
    static final short[] DFA183_special = DFA.unpackEncodedString(DFA183_specialS);
    static final short[][] DFA183_transition;

    static {
        int numStates = DFA183_transitionS.length;
        DFA183_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA183_transition[i] = DFA.unpackEncodedString(DFA183_transitionS[i]);
        }
    }

    class DFA183 extends DFA {

        public DFA183(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 183;
            this.eot = DFA183_eot;
            this.eof = DFA183_eof;
            this.min = DFA183_min;
            this.max = DFA183_max;
            this.accept = DFA183_accept;
            this.special = DFA183_special;
            this.transition = DFA183_transition;
        }
        public String getDescription() {
            return "()* loopback of 322:24: ( ( LT )* '&' ( LT )* equalityExpression )*";
        }
    }
    static final String DFA186_eotS =
        "\4\uffff";
    static final String DFA186_eofS =
        "\1\2\3\uffff";
    static final String DFA186_minS =
        "\2\4\2\uffff";
    static final String DFA186_maxS =
        "\2\116\2\uffff";
    static final String DFA186_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA186_specialS =
        "\4\uffff}>";
    static final String[] DFA186_transitionS = {
            "\1\1\34\uffff\1\2\4\uffff\1\2\6\uffff\1\2\4\uffff\1\2\26\uffff"+
            "\5\2\1\3",
            "\1\1\34\uffff\1\2\4\uffff\1\2\6\uffff\1\2\4\uffff\1\2\26\uffff"+
            "\5\2\1\3",
            "",
            ""
    };

    static final short[] DFA186_eot = DFA.unpackEncodedString(DFA186_eotS);
    static final short[] DFA186_eof = DFA.unpackEncodedString(DFA186_eofS);
    static final char[] DFA186_min = DFA.unpackEncodedStringToUnsignedChars(DFA186_minS);
    static final char[] DFA186_max = DFA.unpackEncodedStringToUnsignedChars(DFA186_maxS);
    static final short[] DFA186_accept = DFA.unpackEncodedString(DFA186_acceptS);
    static final short[] DFA186_special = DFA.unpackEncodedString(DFA186_specialS);
    static final short[][] DFA186_transition;

    static {
        int numStates = DFA186_transitionS.length;
        DFA186_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA186_transition[i] = DFA.unpackEncodedString(DFA186_transitionS[i]);
        }
    }

    class DFA186 extends DFA {

        public DFA186(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 186;
            this.eot = DFA186_eot;
            this.eof = DFA186_eof;
            this.min = DFA186_min;
            this.max = DFA186_max;
            this.accept = DFA186_accept;
            this.special = DFA186_special;
            this.transition = DFA186_transition;
        }
        public String getDescription() {
            return "()* loopback of 326:28: ( ( LT )* '&' ( LT )* equalityExpressionNoIn )*";
        }
    }
    static final String DFA189_eotS =
        "\5\uffff";
    static final String DFA189_eofS =
        "\2\2\2\uffff\1\2";
    static final String DFA189_minS =
        "\2\4\2\uffff\1\4";
    static final String DFA189_maxS =
        "\1\122\1\152\2\uffff\1\152";
    static final String DFA189_acceptS =
        "\2\uffff\1\2\1\1\1\uffff";
    static final String DFA189_specialS =
        "\5\uffff}>";
    static final String[] DFA189_transitionS = {
            "\1\1\34\uffff\2\2\1\uffff\1\2\1\uffff\1\2\13\uffff\1\2\11\uffff"+
            "\1\2\14\uffff\6\2\4\3",
            "\1\4\3\2\27\uffff\10\2\1\uffff\5\2\1\uffff\12\2\2\uffff\3\2"+
            "\14\uffff\6\2\4\3\10\uffff\2\2\3\uffff\13\2",
            "",
            "",
            "\1\4\3\2\27\uffff\10\2\1\uffff\5\2\1\uffff\12\2\2\uffff\3\2"+
            "\14\uffff\6\2\4\3\10\uffff\2\2\3\uffff\13\2"
    };

    static final short[] DFA189_eot = DFA.unpackEncodedString(DFA189_eotS);
    static final short[] DFA189_eof = DFA.unpackEncodedString(DFA189_eofS);
    static final char[] DFA189_min = DFA.unpackEncodedStringToUnsignedChars(DFA189_minS);
    static final char[] DFA189_max = DFA.unpackEncodedStringToUnsignedChars(DFA189_maxS);
    static final short[] DFA189_accept = DFA.unpackEncodedString(DFA189_acceptS);
    static final short[] DFA189_special = DFA.unpackEncodedString(DFA189_specialS);
    static final short[][] DFA189_transition;

    static {
        int numStates = DFA189_transitionS.length;
        DFA189_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA189_transition[i] = DFA.unpackEncodedString(DFA189_transitionS[i]);
        }
    }

    class DFA189 extends DFA {

        public DFA189(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 189;
            this.eot = DFA189_eot;
            this.eof = DFA189_eof;
            this.min = DFA189_min;
            this.max = DFA189_max;
            this.accept = DFA189_accept;
            this.special = DFA189_special;
            this.transition = DFA189_transition;
        }
        public String getDescription() {
            return "()* loopback of 330:26: ( ( LT )* ( '==' | '!=' | '===' | '!==' ) ( LT )* relationalExpression )*";
        }
    }
    static final String DFA192_eotS =
        "\4\uffff";
    static final String DFA192_eofS =
        "\1\2\3\uffff";
    static final String DFA192_minS =
        "\2\4\2\uffff";
    static final String DFA192_maxS =
        "\2\122\2\uffff";
    static final String DFA192_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA192_specialS =
        "\4\uffff}>";
    static final String[] DFA192_transitionS = {
            "\1\1\34\uffff\1\2\4\uffff\1\2\6\uffff\1\2\4\uffff\1\2\26\uffff"+
            "\6\2\4\3",
            "\1\1\34\uffff\1\2\4\uffff\1\2\6\uffff\1\2\4\uffff\1\2\26\uffff"+
            "\6\2\4\3",
            "",
            ""
    };

    static final short[] DFA192_eot = DFA.unpackEncodedString(DFA192_eotS);
    static final short[] DFA192_eof = DFA.unpackEncodedString(DFA192_eofS);
    static final char[] DFA192_min = DFA.unpackEncodedStringToUnsignedChars(DFA192_minS);
    static final char[] DFA192_max = DFA.unpackEncodedStringToUnsignedChars(DFA192_maxS);
    static final short[] DFA192_accept = DFA.unpackEncodedString(DFA192_acceptS);
    static final short[] DFA192_special = DFA.unpackEncodedString(DFA192_specialS);
    static final short[][] DFA192_transition;

    static {
        int numStates = DFA192_transitionS.length;
        DFA192_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA192_transition[i] = DFA.unpackEncodedString(DFA192_transitionS[i]);
        }
    }

    class DFA192 extends DFA {

        public DFA192(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 192;
            this.eot = DFA192_eot;
            this.eof = DFA192_eof;
            this.min = DFA192_min;
            this.max = DFA192_max;
            this.accept = DFA192_accept;
            this.special = DFA192_special;
            this.transition = DFA192_transition;
        }
        public String getDescription() {
            return "()* loopback of 334:30: ( ( LT )* ( '==' | '!=' | '===' | '!==' ) ( LT )* relationalExpressionNoIn )*";
        }
    }
    static final String DFA195_eotS =
        "\5\uffff";
    static final String DFA195_eofS =
        "\2\2\2\uffff\1\2";
    static final String DFA195_minS =
        "\2\4\2\uffff\1\4";
    static final String DFA195_maxS =
        "\1\127\1\152\2\uffff\1\152";
    static final String DFA195_acceptS =
        "\2\uffff\1\2\1\1\1\uffff";
    static final String DFA195_specialS =
        "\5\uffff}>";
    static final String[] DFA195_transitionS = {
            "\1\1\34\uffff\2\2\1\uffff\1\2\1\uffff\1\2\6\uffff\1\3\4\uffff"+
            "\1\2\11\uffff\1\2\14\uffff\12\2\5\3",
            "\1\4\3\2\27\uffff\10\2\1\uffff\5\2\1\3\12\2\2\uffff\3\2\14"+
            "\uffff\12\2\5\3\3\uffff\2\2\3\uffff\13\2",
            "",
            "",
            "\1\4\3\2\27\uffff\10\2\1\uffff\5\2\1\3\12\2\2\uffff\3\2\14"+
            "\uffff\12\2\5\3\3\uffff\2\2\3\uffff\13\2"
    };

    static final short[] DFA195_eot = DFA.unpackEncodedString(DFA195_eotS);
    static final short[] DFA195_eof = DFA.unpackEncodedString(DFA195_eofS);
    static final char[] DFA195_min = DFA.unpackEncodedStringToUnsignedChars(DFA195_minS);
    static final char[] DFA195_max = DFA.unpackEncodedStringToUnsignedChars(DFA195_maxS);
    static final short[] DFA195_accept = DFA.unpackEncodedString(DFA195_acceptS);
    static final short[] DFA195_special = DFA.unpackEncodedString(DFA195_specialS);
    static final short[][] DFA195_transition;

    static {
        int numStates = DFA195_transitionS.length;
        DFA195_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA195_transition[i] = DFA.unpackEncodedString(DFA195_transitionS[i]);
        }
    }

    class DFA195 extends DFA {

        public DFA195(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 195;
            this.eot = DFA195_eot;
            this.eof = DFA195_eof;
            this.min = DFA195_min;
            this.max = DFA195_max;
            this.accept = DFA195_accept;
            this.special = DFA195_special;
            this.transition = DFA195_transition;
        }
        public String getDescription() {
            return "()* loopback of 338:21: ( ( LT )* ( '<' | '>' | '<=' | '>=' | 'instanceof' | 'in' ) ( LT )* shiftExpression )*";
        }
    }
    static final String DFA198_eotS =
        "\4\uffff";
    static final String DFA198_eofS =
        "\1\2\3\uffff";
    static final String DFA198_minS =
        "\2\4\2\uffff";
    static final String DFA198_maxS =
        "\2\127\2\uffff";
    static final String DFA198_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA198_specialS =
        "\4\uffff}>";
    static final String[] DFA198_transitionS = {
            "\1\1\34\uffff\1\2\4\uffff\1\2\6\uffff\1\2\4\uffff\1\2\26\uffff"+
            "\12\2\5\3",
            "\1\1\34\uffff\1\2\4\uffff\1\2\6\uffff\1\2\4\uffff\1\2\26\uffff"+
            "\12\2\5\3",
            "",
            ""
    };

    static final short[] DFA198_eot = DFA.unpackEncodedString(DFA198_eotS);
    static final short[] DFA198_eof = DFA.unpackEncodedString(DFA198_eofS);
    static final char[] DFA198_min = DFA.unpackEncodedStringToUnsignedChars(DFA198_minS);
    static final char[] DFA198_max = DFA.unpackEncodedStringToUnsignedChars(DFA198_maxS);
    static final short[] DFA198_accept = DFA.unpackEncodedString(DFA198_acceptS);
    static final short[] DFA198_special = DFA.unpackEncodedString(DFA198_specialS);
    static final short[][] DFA198_transition;

    static {
        int numStates = DFA198_transitionS.length;
        DFA198_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA198_transition[i] = DFA.unpackEncodedString(DFA198_transitionS[i]);
        }
    }

    class DFA198 extends DFA {

        public DFA198(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 198;
            this.eot = DFA198_eot;
            this.eof = DFA198_eof;
            this.min = DFA198_min;
            this.max = DFA198_max;
            this.accept = DFA198_accept;
            this.special = DFA198_special;
            this.transition = DFA198_transition;
        }
        public String getDescription() {
            return "()* loopback of 342:21: ( ( LT )* ( '<' | '>' | '<=' | '>=' | 'instanceof' ) ( LT )* shiftExpression )*";
        }
    }
    static final String DFA201_eotS =
        "\5\uffff";
    static final String DFA201_eofS =
        "\2\2\2\uffff\1\2";
    static final String DFA201_minS =
        "\2\4\2\uffff\1\4";
    static final String DFA201_maxS =
        "\1\132\1\152\2\uffff\1\152";
    static final String DFA201_acceptS =
        "\2\uffff\1\2\1\1\1\uffff";
    static final String DFA201_specialS =
        "\5\uffff}>";
    static final String[] DFA201_transitionS = {
            "\1\1\34\uffff\2\2\1\uffff\1\2\1\uffff\1\2\6\uffff\1\2\4\uffff"+
            "\1\2\11\uffff\1\2\14\uffff\17\2\3\3",
            "\1\4\3\2\27\uffff\10\2\1\uffff\20\2\2\uffff\3\2\14\uffff\17"+
            "\2\3\3\2\2\3\uffff\13\2",
            "",
            "",
            "\1\4\3\2\27\uffff\10\2\1\uffff\20\2\2\uffff\3\2\14\uffff\17"+
            "\2\3\3\2\2\3\uffff\13\2"
    };

    static final short[] DFA201_eot = DFA.unpackEncodedString(DFA201_eotS);
    static final short[] DFA201_eof = DFA.unpackEncodedString(DFA201_eofS);
    static final char[] DFA201_min = DFA.unpackEncodedStringToUnsignedChars(DFA201_minS);
    static final char[] DFA201_max = DFA.unpackEncodedStringToUnsignedChars(DFA201_maxS);
    static final short[] DFA201_accept = DFA.unpackEncodedString(DFA201_acceptS);
    static final short[] DFA201_special = DFA.unpackEncodedString(DFA201_specialS);
    static final short[][] DFA201_transition;

    static {
        int numStates = DFA201_transitionS.length;
        DFA201_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA201_transition[i] = DFA.unpackEncodedString(DFA201_transitionS[i]);
        }
    }

    class DFA201 extends DFA {

        public DFA201(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 201;
            this.eot = DFA201_eot;
            this.eof = DFA201_eof;
            this.min = DFA201_min;
            this.max = DFA201_max;
            this.accept = DFA201_accept;
            this.special = DFA201_special;
            this.transition = DFA201_transition;
        }
        public String getDescription() {
            return "()* loopback of 346:24: ( ( LT )* ( '<<' | '>>' | '>>>' ) ( LT )* additiveExpression )*";
        }
    }
    static final String DFA204_eotS =
        "\24\uffff";
    static final String DFA204_eofS =
        "\1\2\23\uffff";
    static final String DFA204_minS =
        "\1\4\1\0\22\uffff";
    static final String DFA204_maxS =
        "\1\134\1\0\22\uffff";
    static final String DFA204_acceptS =
        "\2\uffff\1\2\20\uffff\1\1";
    static final String DFA204_specialS =
        "\1\uffff\1\0\22\uffff}>";
    static final String[] DFA204_transitionS = {
            "\1\1\34\uffff\2\2\1\uffff\1\2\1\uffff\1\2\6\uffff\1\2\4\uffff"+
            "\1\2\11\uffff\1\2\14\uffff\22\2\2\23",
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

    static final short[] DFA204_eot = DFA.unpackEncodedString(DFA204_eotS);
    static final short[] DFA204_eof = DFA.unpackEncodedString(DFA204_eofS);
    static final char[] DFA204_min = DFA.unpackEncodedStringToUnsignedChars(DFA204_minS);
    static final char[] DFA204_max = DFA.unpackEncodedStringToUnsignedChars(DFA204_maxS);
    static final short[] DFA204_accept = DFA.unpackEncodedString(DFA204_acceptS);
    static final short[] DFA204_special = DFA.unpackEncodedString(DFA204_specialS);
    static final short[][] DFA204_transition;

    static {
        int numStates = DFA204_transitionS.length;
        DFA204_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA204_transition[i] = DFA.unpackEncodedString(DFA204_transitionS[i]);
        }
    }

    class DFA204 extends DFA {

        public DFA204(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 204;
            this.eot = DFA204_eot;
            this.eof = DFA204_eof;
            this.min = DFA204_min;
            this.max = DFA204_max;
            this.accept = DFA204_accept;
            this.special = DFA204_special;
            this.transition = DFA204_transition;
        }
        public String getDescription() {
            return "()* loopback of 350:30: ( ( LT )* ( '+' | '-' ) ( LT )* multiplicativeExpression )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA204_1 = input.LA(1);

                         
                        int index204_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred256_JavaScript()) ) {s = 19;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index204_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 204, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA207_eotS =
        "\5\uffff";
    static final String DFA207_eofS =
        "\2\2\2\uffff\1\2";
    static final String DFA207_minS =
        "\2\4\2\uffff\1\4";
    static final String DFA207_maxS =
        "\1\137\1\152\2\uffff\1\152";
    static final String DFA207_acceptS =
        "\2\uffff\1\2\1\1\1\uffff";
    static final String DFA207_specialS =
        "\5\uffff}>";
    static final String[] DFA207_transitionS = {
            "\1\1\34\uffff\2\2\1\uffff\1\2\1\uffff\1\2\6\uffff\1\2\4\uffff"+
            "\1\2\11\uffff\1\2\14\uffff\24\2\3\3",
            "\1\4\3\2\27\uffff\10\2\1\uffff\20\2\2\uffff\3\2\14\uffff\24"+
            "\2\3\3\13\2",
            "",
            "",
            "\1\4\3\2\27\uffff\10\2\1\uffff\20\2\2\uffff\3\2\14\uffff\24"+
            "\2\3\3\13\2"
    };

    static final short[] DFA207_eot = DFA.unpackEncodedString(DFA207_eotS);
    static final short[] DFA207_eof = DFA.unpackEncodedString(DFA207_eofS);
    static final char[] DFA207_min = DFA.unpackEncodedStringToUnsignedChars(DFA207_minS);
    static final char[] DFA207_max = DFA.unpackEncodedStringToUnsignedChars(DFA207_maxS);
    static final short[] DFA207_accept = DFA.unpackEncodedString(DFA207_acceptS);
    static final short[] DFA207_special = DFA.unpackEncodedString(DFA207_specialS);
    static final short[][] DFA207_transition;

    static {
        int numStates = DFA207_transitionS.length;
        DFA207_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA207_transition[i] = DFA.unpackEncodedString(DFA207_transitionS[i]);
        }
    }

    class DFA207 extends DFA {

        public DFA207(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 207;
            this.eot = DFA207_eot;
            this.eof = DFA207_eof;
            this.min = DFA207_min;
            this.max = DFA207_max;
            this.accept = DFA207_accept;
            this.special = DFA207_special;
            this.transition = DFA207_transition;
        }
        public String getDescription() {
            return "()* loopback of 354:21: ( ( LT )* ( '*' | '/' | '%' ) ( LT )* unaryExpression )*";
        }
    }
    static final String DFA218_eotS =
        "\4\uffff";
    static final String DFA218_eofS =
        "\4\uffff";
    static final String DFA218_minS =
        "\2\4\2\uffff";
    static final String DFA218_maxS =
        "\2\74\2\uffff";
    static final String DFA218_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA218_specialS =
        "\4\uffff}>";
    static final String[] DFA218_transitionS = {
            "\1\1\34\uffff\1\3\32\uffff\1\2",
            "\1\1\34\uffff\1\3\32\uffff\1\2",
            "",
            ""
    };

    static final short[] DFA218_eot = DFA.unpackEncodedString(DFA218_eotS);
    static final short[] DFA218_eof = DFA.unpackEncodedString(DFA218_eofS);
    static final char[] DFA218_min = DFA.unpackEncodedStringToUnsignedChars(DFA218_minS);
    static final char[] DFA218_max = DFA.unpackEncodedStringToUnsignedChars(DFA218_maxS);
    static final short[] DFA218_accept = DFA.unpackEncodedString(DFA218_acceptS);
    static final short[] DFA218_special = DFA.unpackEncodedString(DFA218_specialS);
    static final short[][] DFA218_transition;

    static {
        int numStates = DFA218_transitionS.length;
        DFA218_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA218_transition[i] = DFA.unpackEncodedString(DFA218_transitionS[i]);
        }
    }

    class DFA218 extends DFA {

        public DFA218(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 218;
            this.eot = DFA218_eot;
            this.eof = DFA218_eof;
            this.min = DFA218_min;
            this.max = DFA218_max;
            this.accept = DFA218_accept;
            this.special = DFA218_special;
            this.transition = DFA218_transition;
        }
        public String getDescription() {
            return "()* loopback of 377:36: ( ( LT )* ',' ( ( LT )* assignmentExpression )? )*";
        }
    }
    static final String DFA217_eotS =
        "\4\uffff";
    static final String DFA217_eofS =
        "\4\uffff";
    static final String DFA217_minS =
        "\2\4\2\uffff";
    static final String DFA217_maxS =
        "\2\152\2\uffff";
    static final String DFA217_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA217_specialS =
        "\4\uffff}>";
    static final String[] DFA217_transitionS = {
            "\1\1\3\2\27\uffff\2\2\1\3\1\uffff\1\2\26\uffff\2\2\1\3\36\uffff"+
            "\2\2\3\uffff\13\2",
            "\1\1\3\2\27\uffff\2\2\1\3\1\uffff\1\2\26\uffff\2\2\1\3\36\uffff"+
            "\2\2\3\uffff\13\2",
            "",
            ""
    };

    static final short[] DFA217_eot = DFA.unpackEncodedString(DFA217_eotS);
    static final short[] DFA217_eof = DFA.unpackEncodedString(DFA217_eofS);
    static final char[] DFA217_min = DFA.unpackEncodedStringToUnsignedChars(DFA217_minS);
    static final char[] DFA217_max = DFA.unpackEncodedStringToUnsignedChars(DFA217_maxS);
    static final short[] DFA217_accept = DFA.unpackEncodedString(DFA217_acceptS);
    static final short[] DFA217_special = DFA.unpackEncodedString(DFA217_specialS);
    static final short[][] DFA217_transition;

    static {
        int numStates = DFA217_transitionS.length;
        DFA217_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA217_transition[i] = DFA.unpackEncodedString(DFA217_transitionS[i]);
        }
    }

    class DFA217 extends DFA {

        public DFA217(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 217;
            this.eot = DFA217_eot;
            this.eof = DFA217_eof;
            this.min = DFA217_min;
            this.max = DFA217_max;
            this.accept = DFA217_accept;
            this.special = DFA217_special;
            this.transition = DFA217_transition;
        }
        public String getDescription() {
            return "377:46: ( ( LT )* assignmentExpression )?";
        }
    }
    static final String DFA223_eotS =
        "\4\uffff";
    static final String DFA223_eofS =
        "\4\uffff";
    static final String DFA223_minS =
        "\2\4\2\uffff";
    static final String DFA223_maxS =
        "\2\44\2\uffff";
    static final String DFA223_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA223_specialS =
        "\4\uffff}>";
    static final String[] DFA223_transitionS = {
            "\1\1\34\uffff\1\3\2\uffff\1\2",
            "\1\1\34\uffff\1\3\2\uffff\1\2",
            "",
            ""
    };

    static final short[] DFA223_eot = DFA.unpackEncodedString(DFA223_eotS);
    static final short[] DFA223_eof = DFA.unpackEncodedString(DFA223_eofS);
    static final char[] DFA223_min = DFA.unpackEncodedStringToUnsignedChars(DFA223_minS);
    static final char[] DFA223_max = DFA.unpackEncodedStringToUnsignedChars(DFA223_maxS);
    static final short[] DFA223_accept = DFA.unpackEncodedString(DFA223_acceptS);
    static final short[] DFA223_special = DFA.unpackEncodedString(DFA223_specialS);
    static final short[][] DFA223_transition;

    static {
        int numStates = DFA223_transitionS.length;
        DFA223_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA223_transition[i] = DFA.unpackEncodedString(DFA223_transitionS[i]);
        }
    }

    class DFA223 extends DFA {

        public DFA223(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 223;
            this.eot = DFA223_eot;
            this.eof = DFA223_eof;
            this.min = DFA223_min;
            this.max = DFA223_max;
            this.accept = DFA223_accept;
            this.special = DFA223_special;
            this.transition = DFA223_transition;
        }
        public String getDescription() {
            return "()* loopback of 382:35: ( ( LT )* ',' ( LT )* propertyNameAndValue )*";
        }
    }
 

    public static final BitSet FOLLOW_LT_in_program60 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_sourceElements_in_program64 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_LT_in_program66 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_EOF_in_program70 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sourceElement_in_sourceElements86 = new BitSet(new long[]{0x0CCBDD69800000F2L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_sourceElements89 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_sourceElement_in_sourceElements93 = new BitSet(new long[]{0x0CCBDD69800000F2L,0x000007FF18000000L});
    public static final BitSet FOLLOW_functionDeclaration_in_sourceElement110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_sourceElement116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_functionDeclaration132 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_LT_in_functionDeclaration134 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_Identifier_in_functionDeclaration138 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_LT_in_functionDeclaration140 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_formalParameterList_in_functionDeclaration144 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_LT_in_functionDeclaration146 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_functionBody_in_functionDeclaration150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_functionExpression169 = new BitSet(new long[]{0x0000000100000030L});
    public static final BitSet FOLLOW_LT_in_functionExpression171 = new BitSet(new long[]{0x0000000100000030L});
    public static final BitSet FOLLOW_Identifier_in_functionExpression175 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_LT_in_functionExpression178 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_formalParameterList_in_functionExpression182 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_LT_in_functionExpression184 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_functionBody_in_functionExpression188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_formalParameterList207 = new BitSet(new long[]{0x0000000400000030L});
    public static final BitSet FOLLOW_LT_in_formalParameterList210 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_Identifier_in_formalParameterList216 = new BitSet(new long[]{0x0000000600000010L});
    public static final BitSet FOLLOW_LT_in_formalParameterList219 = new BitSet(new long[]{0x0000000200000010L});
    public static final BitSet FOLLOW_33_in_formalParameterList223 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_LT_in_formalParameterList225 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_Identifier_in_formalParameterList231 = new BitSet(new long[]{0x0000000600000010L});
    public static final BitSet FOLLOW_LT_in_formalParameterList237 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_34_in_formalParameterList241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_functionBody258 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_functionBody260 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_sourceElements_in_functionBody264 = new BitSet(new long[]{0x0000001000000010L});
    public static final BitSet FOLLOW_LT_in_functionBody266 = new BitSet(new long[]{0x0000001000000010L});
    public static final BitSet FOLLOW_36_in_functionBody270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statementBlock_in_statement288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableStatement_in_statement294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_emptyStatement_in_statement300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expressionStatement_in_statement306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_statement312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_iterationStatement_in_statement318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continueStatement_in_statement324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_breakStatement_in_statement330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_returnStatement_in_statement336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_withStatement_in_statement342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_labelledStatement_in_statement348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_switchStatement_in_statement354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_throwStatement_in_statement360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tryStatement_in_statement366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_statementBlock381 = new BitSet(new long[]{0x0CCBDD79800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_statementBlock383 = new BitSet(new long[]{0x0CCBDD79800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_statementList_in_statementBlock387 = new BitSet(new long[]{0x0000001000000010L});
    public static final BitSet FOLLOW_LT_in_statementBlock390 = new BitSet(new long[]{0x0000001000000010L});
    public static final BitSet FOLLOW_36_in_statementBlock394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statementList409 = new BitSet(new long[]{0x0CCBDD69800000F2L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_statementList412 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_statement_in_statementList416 = new BitSet(new long[]{0x0CCBDD69800000F2L,0x000007FF18000000L});
    public static final BitSet FOLLOW_37_in_variableStatement433 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_LT_in_variableStatement435 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_variableDeclarationList_in_variableStatement439 = new BitSet(new long[]{0x0000004000000010L});
    public static final BitSet FOLLOW_set_in_variableStatement441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclaration_in_variableDeclarationList463 = new BitSet(new long[]{0x0000000200000012L});
    public static final BitSet FOLLOW_LT_in_variableDeclarationList466 = new BitSet(new long[]{0x0000000200000010L});
    public static final BitSet FOLLOW_33_in_variableDeclarationList470 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_LT_in_variableDeclarationList472 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_variableDeclaration_in_variableDeclarationList476 = new BitSet(new long[]{0x0000000200000012L});
    public static final BitSet FOLLOW_variableDeclarationNoIn_in_variableDeclarationListNoIn493 = new BitSet(new long[]{0x0000000200000012L});
    public static final BitSet FOLLOW_LT_in_variableDeclarationListNoIn496 = new BitSet(new long[]{0x0000000200000010L});
    public static final BitSet FOLLOW_33_in_variableDeclarationListNoIn500 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_LT_in_variableDeclarationListNoIn502 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_variableDeclarationNoIn_in_variableDeclarationListNoIn506 = new BitSet(new long[]{0x0000000200000012L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration523 = new BitSet(new long[]{0x0000008000000012L});
    public static final BitSet FOLLOW_LT_in_variableDeclaration525 = new BitSet(new long[]{0x0000008000000012L});
    public static final BitSet FOLLOW_initialiser_in_variableDeclaration529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclarationNoIn545 = new BitSet(new long[]{0x0000008000000012L});
    public static final BitSet FOLLOW_LT_in_variableDeclarationNoIn547 = new BitSet(new long[]{0x0000008000000012L});
    public static final BitSet FOLLOW_initialiserNoIn_in_variableDeclarationNoIn551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_initialiser567 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_initialiser569 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_assignmentExpression_in_initialiser573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_initialiserNoIn588 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_initialiserNoIn590 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_assignmentExpressionNoIn_in_initialiserNoIn594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_emptyStatement609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionStatement624 = new BitSet(new long[]{0x0000004000000010L});
    public static final BitSet FOLLOW_set_in_expressionStatement626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ifStatement648 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_LT_in_ifStatement650 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_32_in_ifStatement654 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_ifStatement656 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_expression_in_ifStatement660 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_LT_in_ifStatement662 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_34_in_ifStatement666 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_ifStatement668 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_statement_in_ifStatement672 = new BitSet(new long[]{0x0000020000000012L});
    public static final BitSet FOLLOW_LT_in_ifStatement675 = new BitSet(new long[]{0x0000020000000010L});
    public static final BitSet FOLLOW_41_in_ifStatement679 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_ifStatement681 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_statement_in_ifStatement685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doWhileStatement_in_iterationStatement702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStatement_in_iterationStatement708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStatement_in_iterationStatement714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forInStatement_in_iterationStatement720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_doWhileStatement735 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_doWhileStatement737 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_statement_in_doWhileStatement741 = new BitSet(new long[]{0x0000080000000010L});
    public static final BitSet FOLLOW_LT_in_doWhileStatement743 = new BitSet(new long[]{0x0000080000000010L});
    public static final BitSet FOLLOW_43_in_doWhileStatement747 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_LT_in_doWhileStatement749 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_32_in_doWhileStatement753 = new BitSet(new long[]{0x0C000009800000E0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_expression_in_doWhileStatement755 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_doWhileStatement757 = new BitSet(new long[]{0x0000004000000010L});
    public static final BitSet FOLLOW_set_in_doWhileStatement759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_whileStatement781 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_LT_in_whileStatement783 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_32_in_whileStatement787 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_whileStatement789 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_expression_in_whileStatement793 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_LT_in_whileStatement795 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_34_in_whileStatement799 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_whileStatement801 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_statement_in_whileStatement805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_forStatement820 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_LT_in_forStatement822 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_32_in_forStatement826 = new BitSet(new long[]{0x0C000069800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_forStatement829 = new BitSet(new long[]{0x0C000029800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_forStatementInitialiserPart_in_forStatement833 = new BitSet(new long[]{0x0000004000000010L});
    public static final BitSet FOLLOW_LT_in_forStatement837 = new BitSet(new long[]{0x0000004000000010L});
    public static final BitSet FOLLOW_38_in_forStatement841 = new BitSet(new long[]{0x0C000049800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_forStatement844 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_expression_in_forStatement848 = new BitSet(new long[]{0x0000004000000010L});
    public static final BitSet FOLLOW_LT_in_forStatement852 = new BitSet(new long[]{0x0000004000000010L});
    public static final BitSet FOLLOW_38_in_forStatement856 = new BitSet(new long[]{0x0C00000D800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_forStatement859 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_expression_in_forStatement863 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_LT_in_forStatement867 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_34_in_forStatement871 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_forStatement873 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_statement_in_forStatement877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expressionNoIn_in_forStatementInitialiserPart892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_forStatementInitialiserPart898 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_LT_in_forStatementInitialiserPart900 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_variableDeclarationListNoIn_in_forStatementInitialiserPart904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_forInStatement919 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_LT_in_forInStatement921 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_32_in_forInStatement925 = new BitSet(new long[]{0x0C000029800000F0L,0x0000078000000000L});
    public static final BitSet FOLLOW_LT_in_forInStatement927 = new BitSet(new long[]{0x0C000029800000F0L,0x0000078000000000L});
    public static final BitSet FOLLOW_forInStatementInitialiserPart_in_forInStatement931 = new BitSet(new long[]{0x0000200000000010L});
    public static final BitSet FOLLOW_LT_in_forInStatement933 = new BitSet(new long[]{0x0000200000000010L});
    public static final BitSet FOLLOW_45_in_forInStatement937 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_forInStatement939 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_expression_in_forInStatement943 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_LT_in_forInStatement945 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_34_in_forInStatement949 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_forInStatement951 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_statement_in_forInStatement955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_leftHandSideExpression_in_forInStatementInitialiserPart970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_forInStatementInitialiserPart976 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_LT_in_forInStatementInitialiserPart978 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_variableDeclarationNoIn_in_forInStatementInitialiserPart982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_continueStatement995 = new BitSet(new long[]{0x0000004000000030L});
    public static final BitSet FOLLOW_Identifier_in_continueStatement997 = new BitSet(new long[]{0x0000004000000010L});
    public static final BitSet FOLLOW_set_in_continueStatement1000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_breakStatement1020 = new BitSet(new long[]{0x0000004000000030L});
    public static final BitSet FOLLOW_Identifier_in_breakStatement1022 = new BitSet(new long[]{0x0000004000000010L});
    public static final BitSet FOLLOW_set_in_breakStatement1025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_returnStatement1045 = new BitSet(new long[]{0x0C000049800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_expression_in_returnStatement1047 = new BitSet(new long[]{0x0000004000000010L});
    public static final BitSet FOLLOW_set_in_returnStatement1050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_withStatement1072 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_LT_in_withStatement1074 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_32_in_withStatement1078 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_withStatement1080 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_expression_in_withStatement1084 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_LT_in_withStatement1086 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_34_in_withStatement1090 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_withStatement1092 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_statement_in_withStatement1096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_labelledStatement1109 = new BitSet(new long[]{0x0004000000000010L});
    public static final BitSet FOLLOW_LT_in_labelledStatement1111 = new BitSet(new long[]{0x0004000000000010L});
    public static final BitSet FOLLOW_50_in_labelledStatement1115 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_labelledStatement1117 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_statement_in_labelledStatement1121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_switchStatement1136 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_LT_in_switchStatement1138 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_32_in_switchStatement1142 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_switchStatement1144 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_expression_in_switchStatement1148 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_LT_in_switchStatement1150 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_34_in_switchStatement1154 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_LT_in_switchStatement1156 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_caseBlock_in_switchStatement1160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_caseBlock1175 = new BitSet(new long[]{0x0030001000000010L});
    public static final BitSet FOLLOW_LT_in_caseBlock1178 = new BitSet(new long[]{0x0010000000000010L});
    public static final BitSet FOLLOW_caseClause_in_caseBlock1182 = new BitSet(new long[]{0x0030001000000010L});
    public static final BitSet FOLLOW_LT_in_caseBlock1187 = new BitSet(new long[]{0x0020000000000010L});
    public static final BitSet FOLLOW_defaultClause_in_caseBlock1191 = new BitSet(new long[]{0x0010001000000010L});
    public static final BitSet FOLLOW_LT_in_caseBlock1194 = new BitSet(new long[]{0x0010000000000010L});
    public static final BitSet FOLLOW_caseClause_in_caseBlock1198 = new BitSet(new long[]{0x0010001000000010L});
    public static final BitSet FOLLOW_LT_in_caseBlock1204 = new BitSet(new long[]{0x0000001000000010L});
    public static final BitSet FOLLOW_36_in_caseBlock1208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_caseClause1221 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_caseClause1223 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_expression_in_caseClause1227 = new BitSet(new long[]{0x0004000000000010L});
    public static final BitSet FOLLOW_LT_in_caseClause1229 = new BitSet(new long[]{0x0004000000000010L});
    public static final BitSet FOLLOW_50_in_caseClause1233 = new BitSet(new long[]{0x0CCBDD69800000F2L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_caseClause1235 = new BitSet(new long[]{0x0CCBDD69800000F2L,0x000007FF18000000L});
    public static final BitSet FOLLOW_statementList_in_caseClause1239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_defaultClause1255 = new BitSet(new long[]{0x0004000000000010L});
    public static final BitSet FOLLOW_LT_in_defaultClause1257 = new BitSet(new long[]{0x0004000000000010L});
    public static final BitSet FOLLOW_50_in_defaultClause1261 = new BitSet(new long[]{0x0CCBDD69800000F2L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_defaultClause1263 = new BitSet(new long[]{0x0CCBDD69800000F2L,0x000007FF18000000L});
    public static final BitSet FOLLOW_statementList_in_defaultClause1267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_throwStatement1283 = new BitSet(new long[]{0x0C000009800000E0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_expression_in_throwStatement1285 = new BitSet(new long[]{0x0000004000000010L});
    public static final BitSet FOLLOW_set_in_throwStatement1287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_tryStatement1307 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_LT_in_tryStatement1309 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_statementBlock_in_tryStatement1313 = new BitSet(new long[]{0x0300000000000010L});
    public static final BitSet FOLLOW_LT_in_tryStatement1315 = new BitSet(new long[]{0x0300000000000010L});
    public static final BitSet FOLLOW_finallyClause_in_tryStatement1320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_catchClause_in_tryStatement1324 = new BitSet(new long[]{0x0200000000000012L});
    public static final BitSet FOLLOW_LT_in_tryStatement1327 = new BitSet(new long[]{0x0200000000000010L});
    public static final BitSet FOLLOW_finallyClause_in_tryStatement1331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_catchClause1354 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_LT_in_catchClause1356 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_32_in_catchClause1360 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_LT_in_catchClause1362 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_Identifier_in_catchClause1366 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_LT_in_catchClause1368 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_34_in_catchClause1372 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_LT_in_catchClause1374 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_statementBlock_in_catchClause1378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_finallyClause1393 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_LT_in_finallyClause1395 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_statementBlock_in_finallyClause1399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignmentExpression_in_expression1413 = new BitSet(new long[]{0x0000000200000012L});
    public static final BitSet FOLLOW_LT_in_expression1416 = new BitSet(new long[]{0x0000000200000010L});
    public static final BitSet FOLLOW_33_in_expression1420 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_expression1422 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_assignmentExpression_in_expression1426 = new BitSet(new long[]{0x0000000200000012L});
    public static final BitSet FOLLOW_assignmentExpressionNoIn_in_expressionNoIn1443 = new BitSet(new long[]{0x0000000200000012L});
    public static final BitSet FOLLOW_LT_in_expressionNoIn1446 = new BitSet(new long[]{0x0000000200000010L});
    public static final BitSet FOLLOW_33_in_expressionNoIn1450 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_expressionNoIn1452 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_assignmentExpressionNoIn_in_expressionNoIn1456 = new BitSet(new long[]{0x0000000200000012L});
    public static final BitSet FOLLOW_conditionalExpression_in_assignmentExpression1473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_leftHandSideExpression_in_assignmentExpression1479 = new BitSet(new long[]{0xC000008000000010L,0x00000000000001FFL});
    public static final BitSet FOLLOW_LT_in_assignmentExpression1481 = new BitSet(new long[]{0xC000008000000010L,0x00000000000001FFL});
    public static final BitSet FOLLOW_assignmentOperator_in_assignmentExpression1485 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_assignmentExpression1487 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_assignmentExpression_in_assignmentExpression1491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalExpressionNoIn_in_assignmentExpressionNoIn1506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_leftHandSideExpression_in_assignmentExpressionNoIn1512 = new BitSet(new long[]{0xC000008000000010L,0x00000000000001FFL});
    public static final BitSet FOLLOW_LT_in_assignmentExpressionNoIn1514 = new BitSet(new long[]{0xC000008000000010L,0x00000000000001FFL});
    public static final BitSet FOLLOW_assignmentOperator_in_assignmentExpressionNoIn1518 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_assignmentExpressionNoIn1520 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_assignmentExpressionNoIn_in_assignmentExpressionNoIn1524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_callExpression_in_leftHandSideExpression1539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_newExpression_in_leftHandSideExpression1545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpression_in_newExpression1560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_newExpression1566 = new BitSet(new long[]{0x0C000009800000F0L,0x0000078000000000L});
    public static final BitSet FOLLOW_LT_in_newExpression1568 = new BitSet(new long[]{0x0C000009800000F0L,0x0000078000000000L});
    public static final BitSet FOLLOW_newExpression_in_newExpression1572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primaryExpression_in_memberExpression1588 = new BitSet(new long[]{0x2800000000000012L});
    public static final BitSet FOLLOW_functionExpression_in_memberExpression1592 = new BitSet(new long[]{0x2800000000000012L});
    public static final BitSet FOLLOW_58_in_memberExpression1596 = new BitSet(new long[]{0x0C000009800000F0L,0x0000078000000000L});
    public static final BitSet FOLLOW_LT_in_memberExpression1598 = new BitSet(new long[]{0x0C000009800000F0L,0x0000078000000000L});
    public static final BitSet FOLLOW_memberExpression_in_memberExpression1602 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_LT_in_memberExpression1604 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_arguments_in_memberExpression1608 = new BitSet(new long[]{0x2800000000000012L});
    public static final BitSet FOLLOW_LT_in_memberExpression1612 = new BitSet(new long[]{0x2800000000000010L});
    public static final BitSet FOLLOW_memberExpressionSuffix_in_memberExpression1616 = new BitSet(new long[]{0x2800000000000012L});
    public static final BitSet FOLLOW_indexSuffix_in_memberExpressionSuffix1633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_propertyReferenceSuffix_in_memberExpressionSuffix1639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpression_in_callExpression1652 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_LT_in_callExpression1654 = new BitSet(new long[]{0x0000000100000010L});
    public static final BitSet FOLLOW_arguments_in_callExpression1658 = new BitSet(new long[]{0x2800000100000012L});
    public static final BitSet FOLLOW_LT_in_callExpression1661 = new BitSet(new long[]{0x2800000100000010L});
    public static final BitSet FOLLOW_callExpressionSuffix_in_callExpression1665 = new BitSet(new long[]{0x2800000100000012L});
    public static final BitSet FOLLOW_arguments_in_callExpressionSuffix1682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_indexSuffix_in_callExpressionSuffix1688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_propertyReferenceSuffix_in_callExpressionSuffix1694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_arguments1707 = new BitSet(new long[]{0x0C00000D800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_arguments1710 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_assignmentExpression_in_arguments1714 = new BitSet(new long[]{0x0000000600000010L});
    public static final BitSet FOLLOW_LT_in_arguments1717 = new BitSet(new long[]{0x0000000200000010L});
    public static final BitSet FOLLOW_33_in_arguments1721 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_arguments1723 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_assignmentExpression_in_arguments1727 = new BitSet(new long[]{0x0000000600000010L});
    public static final BitSet FOLLOW_LT_in_arguments1733 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_34_in_arguments1737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_indexSuffix1752 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_indexSuffix1754 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_expression_in_indexSuffix1758 = new BitSet(new long[]{0x1000000000000010L});
    public static final BitSet FOLLOW_LT_in_indexSuffix1760 = new BitSet(new long[]{0x1000000000000010L});
    public static final BitSet FOLLOW_60_in_indexSuffix1764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_propertyReferenceSuffix1780 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_LT_in_propertyReferenceSuffix1782 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_Identifier_in_propertyReferenceSuffix1786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_assignmentOperator0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_logicalORExpression_in_conditionalExpression1858 = new BitSet(new long[]{0x0000000000000012L,0x0000000000000200L});
    public static final BitSet FOLLOW_LT_in_conditionalExpression1861 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_conditionalExpression1865 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_conditionalExpression1867 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_assignmentExpression_in_conditionalExpression1871 = new BitSet(new long[]{0x0004000000000010L});
    public static final BitSet FOLLOW_LT_in_conditionalExpression1873 = new BitSet(new long[]{0x0004000000000010L});
    public static final BitSet FOLLOW_50_in_conditionalExpression1877 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_conditionalExpression1879 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_assignmentExpression_in_conditionalExpression1883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_logicalORExpressionNoIn_in_conditionalExpressionNoIn1898 = new BitSet(new long[]{0x0000000000000012L,0x0000000000000200L});
    public static final BitSet FOLLOW_LT_in_conditionalExpressionNoIn1901 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_conditionalExpressionNoIn1905 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_conditionalExpressionNoIn1907 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_assignmentExpressionNoIn_in_conditionalExpressionNoIn1911 = new BitSet(new long[]{0x0004000000000010L});
    public static final BitSet FOLLOW_LT_in_conditionalExpressionNoIn1913 = new BitSet(new long[]{0x0004000000000010L});
    public static final BitSet FOLLOW_50_in_conditionalExpressionNoIn1917 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_conditionalExpressionNoIn1919 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_assignmentExpressionNoIn_in_conditionalExpressionNoIn1923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_logicalANDExpression_in_logicalORExpression1938 = new BitSet(new long[]{0x0000000000000012L,0x0000000000000400L});
    public static final BitSet FOLLOW_LT_in_logicalORExpression1941 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_logicalORExpression1945 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_logicalORExpression1947 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_logicalANDExpression_in_logicalORExpression1951 = new BitSet(new long[]{0x0000000000000012L,0x0000000000000400L});
    public static final BitSet FOLLOW_logicalANDExpressionNoIn_in_logicalORExpressionNoIn1968 = new BitSet(new long[]{0x0000000000000012L,0x0000000000000400L});
    public static final BitSet FOLLOW_LT_in_logicalORExpressionNoIn1971 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_logicalORExpressionNoIn1975 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_logicalORExpressionNoIn1977 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_logicalANDExpressionNoIn_in_logicalORExpressionNoIn1981 = new BitSet(new long[]{0x0000000000000012L,0x0000000000000400L});
    public static final BitSet FOLLOW_bitwiseORExpression_in_logicalANDExpression1998 = new BitSet(new long[]{0x0000000000000012L,0x0000000000000800L});
    public static final BitSet FOLLOW_LT_in_logicalANDExpression2001 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_logicalANDExpression2005 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_logicalANDExpression2007 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_bitwiseORExpression_in_logicalANDExpression2011 = new BitSet(new long[]{0x0000000000000012L,0x0000000000000800L});
    public static final BitSet FOLLOW_bitwiseORExpressionNoIn_in_logicalANDExpressionNoIn2028 = new BitSet(new long[]{0x0000000000000012L,0x0000000000000800L});
    public static final BitSet FOLLOW_LT_in_logicalANDExpressionNoIn2031 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_logicalANDExpressionNoIn2035 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_logicalANDExpressionNoIn2037 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_bitwiseORExpressionNoIn_in_logicalANDExpressionNoIn2041 = new BitSet(new long[]{0x0000000000000012L,0x0000000000000800L});
    public static final BitSet FOLLOW_bitwiseXORExpression_in_bitwiseORExpression2058 = new BitSet(new long[]{0x0000000000000012L,0x0000000000001000L});
    public static final BitSet FOLLOW_LT_in_bitwiseORExpression2061 = new BitSet(new long[]{0x0000000000000010L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_bitwiseORExpression2065 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_bitwiseORExpression2067 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_bitwiseXORExpression_in_bitwiseORExpression2071 = new BitSet(new long[]{0x0000000000000012L,0x0000000000001000L});
    public static final BitSet FOLLOW_bitwiseXORExpressionNoIn_in_bitwiseORExpressionNoIn2088 = new BitSet(new long[]{0x0000000000000012L,0x0000000000001000L});
    public static final BitSet FOLLOW_LT_in_bitwiseORExpressionNoIn2091 = new BitSet(new long[]{0x0000000000000010L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_bitwiseORExpressionNoIn2095 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_bitwiseORExpressionNoIn2097 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_bitwiseXORExpressionNoIn_in_bitwiseORExpressionNoIn2101 = new BitSet(new long[]{0x0000000000000012L,0x0000000000001000L});
    public static final BitSet FOLLOW_bitwiseANDExpression_in_bitwiseXORExpression2118 = new BitSet(new long[]{0x0000000000000012L,0x0000000000002000L});
    public static final BitSet FOLLOW_LT_in_bitwiseXORExpression2121 = new BitSet(new long[]{0x0000000000000010L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_bitwiseXORExpression2125 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_bitwiseXORExpression2127 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_bitwiseANDExpression_in_bitwiseXORExpression2131 = new BitSet(new long[]{0x0000000000000012L,0x0000000000002000L});
    public static final BitSet FOLLOW_bitwiseANDExpressionNoIn_in_bitwiseXORExpressionNoIn2148 = new BitSet(new long[]{0x0000000000000012L,0x0000000000002000L});
    public static final BitSet FOLLOW_LT_in_bitwiseXORExpressionNoIn2151 = new BitSet(new long[]{0x0000000000000010L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_bitwiseXORExpressionNoIn2155 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_bitwiseXORExpressionNoIn2157 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_bitwiseANDExpressionNoIn_in_bitwiseXORExpressionNoIn2161 = new BitSet(new long[]{0x0000000000000012L,0x0000000000002000L});
    public static final BitSet FOLLOW_equalityExpression_in_bitwiseANDExpression2178 = new BitSet(new long[]{0x0000000000000012L,0x0000000000004000L});
    public static final BitSet FOLLOW_LT_in_bitwiseANDExpression2181 = new BitSet(new long[]{0x0000000000000010L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_bitwiseANDExpression2185 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_bitwiseANDExpression2187 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_equalityExpression_in_bitwiseANDExpression2191 = new BitSet(new long[]{0x0000000000000012L,0x0000000000004000L});
    public static final BitSet FOLLOW_equalityExpressionNoIn_in_bitwiseANDExpressionNoIn2208 = new BitSet(new long[]{0x0000000000000012L,0x0000000000004000L});
    public static final BitSet FOLLOW_LT_in_bitwiseANDExpressionNoIn2211 = new BitSet(new long[]{0x0000000000000010L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_bitwiseANDExpressionNoIn2215 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_bitwiseANDExpressionNoIn2217 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_equalityExpressionNoIn_in_bitwiseANDExpressionNoIn2221 = new BitSet(new long[]{0x0000000000000012L,0x0000000000004000L});
    public static final BitSet FOLLOW_relationalExpression_in_equalityExpression2238 = new BitSet(new long[]{0x0000000000000012L,0x0000000000078000L});
    public static final BitSet FOLLOW_LT_in_equalityExpression2241 = new BitSet(new long[]{0x0000000000000010L,0x0000000000078000L});
    public static final BitSet FOLLOW_set_in_equalityExpression2245 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_equalityExpression2261 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_relationalExpression_in_equalityExpression2265 = new BitSet(new long[]{0x0000000000000012L,0x0000000000078000L});
    public static final BitSet FOLLOW_relationalExpressionNoIn_in_equalityExpressionNoIn2280 = new BitSet(new long[]{0x0000000000000012L,0x0000000000078000L});
    public static final BitSet FOLLOW_LT_in_equalityExpressionNoIn2283 = new BitSet(new long[]{0x0000000000000010L,0x0000000000078000L});
    public static final BitSet FOLLOW_set_in_equalityExpressionNoIn2287 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_equalityExpressionNoIn2303 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_relationalExpressionNoIn_in_equalityExpressionNoIn2307 = new BitSet(new long[]{0x0000000000000012L,0x0000000000078000L});
    public static final BitSet FOLLOW_shiftExpression_in_relationalExpression2324 = new BitSet(new long[]{0x0000200000000012L,0x0000000000F80000L});
    public static final BitSet FOLLOW_LT_in_relationalExpression2327 = new BitSet(new long[]{0x0000200000000010L,0x0000000000F80000L});
    public static final BitSet FOLLOW_set_in_relationalExpression2331 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_relationalExpression2355 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_shiftExpression_in_relationalExpression2359 = new BitSet(new long[]{0x0000200000000012L,0x0000000000F80000L});
    public static final BitSet FOLLOW_shiftExpression_in_relationalExpressionNoIn2374 = new BitSet(new long[]{0x0000000000000012L,0x0000000000F80000L});
    public static final BitSet FOLLOW_LT_in_relationalExpressionNoIn2377 = new BitSet(new long[]{0x0000000000000010L,0x0000000000F80000L});
    public static final BitSet FOLLOW_set_in_relationalExpressionNoIn2381 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_relationalExpressionNoIn2401 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_shiftExpression_in_relationalExpressionNoIn2405 = new BitSet(new long[]{0x0000000000000012L,0x0000000000F80000L});
    public static final BitSet FOLLOW_additiveExpression_in_shiftExpression2420 = new BitSet(new long[]{0x0000000000000012L,0x0000000007000000L});
    public static final BitSet FOLLOW_LT_in_shiftExpression2423 = new BitSet(new long[]{0x0000000000000010L,0x0000000007000000L});
    public static final BitSet FOLLOW_set_in_shiftExpression2427 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_shiftExpression2439 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_additiveExpression_in_shiftExpression2443 = new BitSet(new long[]{0x0000000000000012L,0x0000000007000000L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression2458 = new BitSet(new long[]{0x0000000000000012L,0x0000000018000000L});
    public static final BitSet FOLLOW_LT_in_additiveExpression2461 = new BitSet(new long[]{0x0000000000000010L,0x0000000018000000L});
    public static final BitSet FOLLOW_set_in_additiveExpression2465 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_additiveExpression2473 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression2477 = new BitSet(new long[]{0x0000000000000012L,0x0000000018000000L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression2492 = new BitSet(new long[]{0x0000000000000012L,0x00000000E0000000L});
    public static final BitSet FOLLOW_LT_in_multiplicativeExpression2495 = new BitSet(new long[]{0x0000000000000010L,0x00000000E0000000L});
    public static final BitSet FOLLOW_set_in_multiplicativeExpression2499 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_multiplicativeExpression2511 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression2515 = new BitSet(new long[]{0x0000000000000012L,0x00000000E0000000L});
    public static final BitSet FOLLOW_postfixExpression_in_unaryExpression2530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_unaryExpression2536 = new BitSet(new long[]{0x0C000009800000E0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression2572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_leftHandSideExpression_in_postfixExpression2587 = new BitSet(new long[]{0x0000000000000002L,0x0000001800000000L});
    public static final BitSet FOLLOW_set_in_postfixExpression2589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_103_in_primaryExpression2609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_primaryExpression2615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_primaryExpression2621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayLiteral_in_primaryExpression2627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_objectLiteral_in_primaryExpression2633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_primaryExpression2639 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_primaryExpression2641 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_expression_in_primaryExpression2645 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_LT_in_primaryExpression2647 = new BitSet(new long[]{0x0000000400000010L});
    public static final BitSet FOLLOW_34_in_primaryExpression2651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_arrayLiteral2667 = new BitSet(new long[]{0x1C00000B800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_arrayLiteral2669 = new BitSet(new long[]{0x1C00000B800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_assignmentExpression_in_arrayLiteral2673 = new BitSet(new long[]{0x1000000200000010L});
    public static final BitSet FOLLOW_LT_in_arrayLiteral2677 = new BitSet(new long[]{0x0000000200000010L});
    public static final BitSet FOLLOW_33_in_arrayLiteral2681 = new BitSet(new long[]{0x1C00000B800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_arrayLiteral2684 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_assignmentExpression_in_arrayLiteral2688 = new BitSet(new long[]{0x1000000200000010L});
    public static final BitSet FOLLOW_LT_in_arrayLiteral2694 = new BitSet(new long[]{0x1000000000000010L});
    public static final BitSet FOLLOW_60_in_arrayLiteral2698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_objectLiteral2719 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_LT_in_objectLiteral2721 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_propertyNameAndValue_in_objectLiteral2725 = new BitSet(new long[]{0x0000001200000010L});
    public static final BitSet FOLLOW_LT_in_objectLiteral2728 = new BitSet(new long[]{0x0000000200000010L});
    public static final BitSet FOLLOW_33_in_objectLiteral2732 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_LT_in_objectLiteral2734 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_propertyNameAndValue_in_objectLiteral2738 = new BitSet(new long[]{0x0000001200000010L});
    public static final BitSet FOLLOW_LT_in_objectLiteral2742 = new BitSet(new long[]{0x0000001000000010L});
    public static final BitSet FOLLOW_36_in_objectLiteral2746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_propertyName_in_propertyNameAndValue2761 = new BitSet(new long[]{0x0004000000000010L});
    public static final BitSet FOLLOW_LT_in_propertyNameAndValue2763 = new BitSet(new long[]{0x0004000000000010L});
    public static final BitSet FOLLOW_50_in_propertyNameAndValue2767 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_propertyNameAndValue2769 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_assignmentExpression_in_propertyNameAndValue2773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_propertyName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_literal0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionDeclaration_in_synpred5_JavaScript110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_synpred9_JavaScript171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statementBlock_in_synpred21_JavaScript288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expressionStatement_in_synpred24_JavaScript306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_labelledStatement_in_synpred31_JavaScript348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_synpred34_JavaScript383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_synpred47_JavaScript525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_synpred49_JavaScript547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_synpred60_JavaScript675 = new BitSet(new long[]{0x0000020000000010L});
    public static final BitSet FOLLOW_41_in_synpred60_JavaScript679 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_synpred60_JavaScript681 = new BitSet(new long[]{0x0CCBDD69800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_statement_in_synpred60_JavaScript685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStatement_in_synpred63_JavaScript714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_synpred118_JavaScript1235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_synpred121_JavaScript1263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalExpression_in_synpred140_JavaScript1473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalExpressionNoIn_in_synpred143_JavaScript1506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_callExpression_in_synpred146_JavaScript1539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpression_in_synpred147_JavaScript1560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_synpred154_JavaScript1612 = new BitSet(new long[]{0x2800000000000010L});
    public static final BitSet FOLLOW_memberExpressionSuffix_in_synpred154_JavaScript1616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_synpred158_JavaScript1661 = new BitSet(new long[]{0x2800000100000010L});
    public static final BitSet FOLLOW_callExpressionSuffix_in_synpred158_JavaScript1665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_synpred256_JavaScript2461 = new BitSet(new long[]{0x0000000000000010L,0x0000000018000000L});
    public static final BitSet FOLLOW_set_in_synpred256_JavaScript2465 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_LT_in_synpred256_JavaScript2473 = new BitSet(new long[]{0x0C000009800000F0L,0x000007FF18000000L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_synpred256_JavaScript2477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_synpred280_JavaScript2669 = new BitSet(new long[]{0x0000000000000002L});

}