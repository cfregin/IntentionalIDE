// $ANTLR 3.2 Sep 23, 2009 12:02:23 /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g 2010-03-31 10:59:40

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class ES3Walker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "NULL", "TRUE", "FALSE", "BREAK", "CASE", "CATCH", "CONTINUE", "DEFAULT", "DELETE", "DO", "ELSE", "FINALLY", "FOR", "FUNCTION", "IF", "IN", "INSTANCEOF", "NEW", "RETURN", "SWITCH", "THIS", "THROW", "TRY", "TYPEOF", "VAR", "VOID", "WHILE", "WITH", "ABSTRACT", "BOOLEAN", "BYTE", "CHAR", "CLASS", "CONST", "DEBUGGER", "DOUBLE", "ENUM", "EXPORT", "EXTENDS", "FINAL", "FLOAT", "GOTO", "IMPLEMENTS", "IMPORT", "INT", "INTERFACE", "LONG", "NATIVE", "PACKAGE", "PRIVATE", "PROTECTED", "PUBLIC", "SHORT", "STATIC", "SUPER", "SYNCHRONIZED", "THROWS", "TRANSIENT", "VOLATILE", "LBRACE", "RBRACE", "LPAREN", "RPAREN", "LBRACK", "RBRACK", "DOT", "SEMIC", "COMMA", "LT", "GT", "LTE", "GTE", "EQ", "NEQ", "SAME", "NSAME", "ADD", "SUB", "MUL", "MOD", "INC", "DEC", "SHL", "SHR", "SHU", "AND", "OR", "XOR", "NOT", "INV", "LAND", "LOR", "QUE", "COLON", "ASSIGN", "ADDASS", "SUBASS", "MULASS", "MODASS", "SHLASS", "SHRASS", "SHUASS", "ANDASS", "ORASS", "XORASS", "DIV", "DIVASS", "ARGS", "ARRAY", "BLOCK", "BYFIELD", "BYINDEX", "CALL", "CEXPR", "EXPR", "FORITER", "FORSTEP", "ITEM", "LABELLED", "NAMEDVALUE", "NEG", "OBJECT", "PAREXPR", "PDEC", "PINC", "POS", "BSLASH", "DQUOTE", "SQUOTE", "TAB", "VT", "FF", "SP", "NBSP", "USP", "WhiteSpace", "LF", "CR", "LS", "PS", "LineTerminator", "EOL", "MultiLineComment", "SingleLineComment", "Identifier", "StringLiteral", "HexDigit", "IdentifierStartASCII", "DecimalDigit", "IdentifierPart", "IdentifierNameASCIIStart", "RegularExpressionLiteral", "OctalDigit", "ExponentPart", "DecimalIntegerLiteral", "DecimalLiteral", "OctalIntegerLiteral", "HexIntegerLiteral", "CharacterEscapeSequence", "ZeroToThree", "OctalEscapeSequence", "HexEscapeSequence", "UnicodeEscapeSequence", "EscapeSequence", "BackslashSequence", "RegularExpressionFirstChar", "RegularExpressionChar"
    };
    public static final int PACKAGE=52;
    public static final int FUNCTION=17;
    public static final int LOR=95;
    public static final int VT=134;
    public static final int SHR=87;
    public static final int RegularExpressionChar=170;
    public static final int LT=72;
    public static final int WHILE=30;
    public static final int MOD=83;
    public static final int SHL=86;
    public static final int CONST=37;
    public static final int BackslashSequence=168;
    public static final int LS=142;
    public static final int CASE=8;
    public static final int CHAR=35;
    public static final int NEW=21;
    public static final int DO=13;
    public static final int DQUOTE=131;
    public static final int NOT=92;
    public static final int DecimalDigit=152;
    public static final int BYFIELD=114;
    public static final int EOF=-1;
    public static final int BREAK=7;
    public static final int CEXPR=117;
    public static final int DIVASS=110;
    public static final int Identifier=148;
    public static final int BYINDEX=115;
    public static final int INC=84;
    public static final int RPAREN=66;
    public static final int FINAL=43;
    public static final int FORSTEP=120;
    public static final int IMPORT=47;
    public static final int EOL=145;
    public static final int POS=129;
    public static final int OctalDigit=156;
    public static final int RETURN=22;
    public static final int THIS=24;
    public static final int DOUBLE=39;
    public static final int ARGS=111;
    public static final int ExponentPart=157;
    public static final int WhiteSpace=139;
    public static final int VAR=28;
    public static final int EXPORT=41;
    public static final int VOID=29;
    public static final int LABELLED=122;
    public static final int SUPER=58;
    public static final int GOTO=45;
    public static final int EQ=76;
    public static final int XORASS=108;
    public static final int ADDASS=99;
    public static final int ARRAY=112;
    public static final int SHU=88;
    public static final int RBRACK=68;
    public static final int RBRACE=64;
    public static final int PRIVATE=53;
    public static final int STATIC=57;
    public static final int INV=93;
    public static final int SWITCH=23;
    public static final int NULL=4;
    public static final int ELSE=14;
    public static final int NATIVE=51;
    public static final int THROWS=60;
    public static final int INT=48;
    public static final int DELETE=12;
    public static final int MUL=82;
    public static final int IdentifierStartASCII=151;
    public static final int TRY=26;
    public static final int FF=135;
    public static final int SHLASS=103;
    public static final int OctalEscapeSequence=164;
    public static final int USP=138;
    public static final int RegularExpressionFirstChar=169;
    public static final int ANDASS=106;
    public static final int TYPEOF=27;
    public static final int IdentifierNameASCIIStart=154;
    public static final int QUE=96;
    public static final int OR=90;
    public static final int DEBUGGER=38;
    public static final int GT=73;
    public static final int PDEC=127;
    public static final int CALL=116;
    public static final int CharacterEscapeSequence=162;
    public static final int CATCH=9;
    public static final int FALSE=6;
    public static final int EscapeSequence=167;
    public static final int LAND=94;
    public static final int MULASS=101;
    public static final int THROW=25;
    public static final int PINC=128;
    public static final int DEC=85;
    public static final int PROTECTED=54;
    public static final int OctalIntegerLiteral=160;
    public static final int CLASS=36;
    public static final int LBRACK=67;
    public static final int ORASS=107;
    public static final int HexEscapeSequence=165;
    public static final int NAMEDVALUE=123;
    public static final int SingleLineComment=147;
    public static final int GTE=75;
    public static final int LBRACE=63;
    public static final int FOR=16;
    public static final int SUB=81;
    public static final int RegularExpressionLiteral=155;
    public static final int FLOAT=44;
    public static final int ABSTRACT=32;
    public static final int AND=89;
    public static final int DecimalIntegerLiteral=158;
    public static final int LTE=74;
    public static final int HexDigit=150;
    public static final int LPAREN=65;
    public static final int IF=18;
    public static final int SUBASS=100;
    public static final int SYNCHRONIZED=59;
    public static final int BOOLEAN=33;
    public static final int EXPR=118;
    public static final int IN=19;
    public static final int IMPLEMENTS=46;
    public static final int CONTINUE=10;
    public static final int OBJECT=125;
    public static final int COMMA=71;
    public static final int TRANSIENT=61;
    public static final int FORITER=119;
    public static final int MODASS=102;
    public static final int SHRASS=104;
    public static final int PS=143;
    public static final int DOT=69;
    public static final int MultiLineComment=146;
    public static final int IdentifierPart=153;
    public static final int WITH=31;
    public static final int ADD=80;
    public static final int BYTE=34;
    public static final int XOR=91;
    public static final int ZeroToThree=163;
    public static final int VOLATILE=62;
    public static final int ITEM=121;
    public static final int UnicodeEscapeSequence=166;
    public static final int NSAME=79;
    public static final int DEFAULT=11;
    public static final int SHUASS=105;
    public static final int TAB=133;
    public static final int SHORT=56;
    public static final int INSTANCEOF=20;
    public static final int SQUOTE=132;
    public static final int DecimalLiteral=159;
    public static final int TRUE=5;
    public static final int SAME=78;
    public static final int COLON=97;
    public static final int StringLiteral=149;
    public static final int NEQ=77;
    public static final int PAREXPR=126;
    public static final int ENUM=40;
    public static final int FINALLY=15;
    public static final int NBSP=137;
    public static final int HexIntegerLiteral=161;
    public static final int SP=136;
    public static final int BLOCK=113;
    public static final int NEG=124;
    public static final int LineTerminator=144;
    public static final int ASSIGN=98;
    public static final int INTERFACE=49;
    public static final int DIV=109;
    public static final int SEMIC=70;
    public static final int LONG=50;
    public static final int CR=141;
    public static final int PUBLIC=55;
    public static final int EXTENDS=42;
    public static final int BSLASH=130;
    public static final int LF=140;

    // delegates
    // delegators


        public ES3Walker(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public ES3Walker(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return ES3Walker.tokenNames; }
    public String getGrammarFileName() { return "/Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g"; }



    // $ANTLR start "program"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:20:1: program : ( statement )* ;
    public final void program() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:21:2: ( ( statement )* )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:21:4: ( statement )*
            {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:21:4: ( statement )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=NULL && LA1_0<=BREAK)||LA1_0==CONTINUE||(LA1_0>=DELETE && LA1_0<=DO)||(LA1_0>=FOR && LA1_0<=WITH)||(LA1_0>=LT && LA1_0<=QUE)||(LA1_0>=ASSIGN && LA1_0<=DIVASS)||(LA1_0>=ARRAY && LA1_0<=CEXPR)||LA1_0==LABELLED||(LA1_0>=NEG && LA1_0<=OBJECT)||(LA1_0>=PDEC && LA1_0<=POS)||(LA1_0>=Identifier && LA1_0<=StringLiteral)||LA1_0==RegularExpressionLiteral||(LA1_0>=DecimalLiteral && LA1_0<=HexIntegerLiteral)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:21:4: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_program43);
            	    statement();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "program"


    // $ANTLR start "statement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:24:1: statement : ( block | variableDeclaration | expression | ifStatement | doStatement | whileStatement | forStatement | continueStatement | breakStatement | returnStatement | withStatement | labelledStatement | switchStatement | throwStatement | tryStatement );
    public final void statement() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:25:2: ( block | variableDeclaration | expression | ifStatement | doStatement | whileStatement | forStatement | continueStatement | breakStatement | returnStatement | withStatement | labelledStatement | switchStatement | throwStatement | tryStatement )
            int alt2=15;
            switch ( input.LA(1) ) {
            case BLOCK:
                {
                alt2=1;
                }
                break;
            case VAR:
                {
                alt2=2;
                }
                break;
            case NULL:
            case TRUE:
            case FALSE:
            case DELETE:
            case FUNCTION:
            case IN:
            case INSTANCEOF:
            case NEW:
            case THIS:
            case TYPEOF:
            case VOID:
            case LT:
            case GT:
            case LTE:
            case GTE:
            case EQ:
            case NEQ:
            case SAME:
            case NSAME:
            case ADD:
            case SUB:
            case MUL:
            case MOD:
            case INC:
            case DEC:
            case SHL:
            case SHR:
            case SHU:
            case AND:
            case OR:
            case XOR:
            case NOT:
            case INV:
            case LAND:
            case LOR:
            case QUE:
            case ASSIGN:
            case ADDASS:
            case SUBASS:
            case MULASS:
            case MODASS:
            case SHLASS:
            case SHRASS:
            case SHUASS:
            case ANDASS:
            case ORASS:
            case XORASS:
            case DIV:
            case DIVASS:
            case ARRAY:
            case BYFIELD:
            case BYINDEX:
            case CALL:
            case CEXPR:
            case NEG:
            case OBJECT:
            case PDEC:
            case PINC:
            case POS:
            case Identifier:
            case StringLiteral:
            case RegularExpressionLiteral:
            case DecimalLiteral:
            case OctalIntegerLiteral:
            case HexIntegerLiteral:
                {
                alt2=3;
                }
                break;
            case IF:
                {
                alt2=4;
                }
                break;
            case DO:
                {
                alt2=5;
                }
                break;
            case WHILE:
                {
                alt2=6;
                }
                break;
            case FOR:
                {
                alt2=7;
                }
                break;
            case CONTINUE:
                {
                alt2=8;
                }
                break;
            case BREAK:
                {
                alt2=9;
                }
                break;
            case RETURN:
                {
                alt2=10;
                }
                break;
            case WITH:
                {
                alt2=11;
                }
                break;
            case LABELLED:
                {
                alt2=12;
                }
                break;
            case SWITCH:
                {
                alt2=13;
                }
                break;
            case THROW:
                {
                alt2=14;
                }
                break;
            case TRY:
                {
                alt2=15;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:25:4: block
                    {
                    pushFollow(FOLLOW_block_in_statement55);
                    block();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:26:4: variableDeclaration
                    {
                    pushFollow(FOLLOW_variableDeclaration_in_statement60);
                    variableDeclaration();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:27:4: expression
                    {
                    pushFollow(FOLLOW_expression_in_statement65);
                    expression();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:28:4: ifStatement
                    {
                    pushFollow(FOLLOW_ifStatement_in_statement70);
                    ifStatement();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:29:4: doStatement
                    {
                    pushFollow(FOLLOW_doStatement_in_statement75);
                    doStatement();

                    state._fsp--;


                    }
                    break;
                case 6 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:30:4: whileStatement
                    {
                    pushFollow(FOLLOW_whileStatement_in_statement80);
                    whileStatement();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:31:4: forStatement
                    {
                    pushFollow(FOLLOW_forStatement_in_statement85);
                    forStatement();

                    state._fsp--;


                    }
                    break;
                case 8 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:32:4: continueStatement
                    {
                    pushFollow(FOLLOW_continueStatement_in_statement90);
                    continueStatement();

                    state._fsp--;


                    }
                    break;
                case 9 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:33:4: breakStatement
                    {
                    pushFollow(FOLLOW_breakStatement_in_statement95);
                    breakStatement();

                    state._fsp--;


                    }
                    break;
                case 10 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:34:4: returnStatement
                    {
                    pushFollow(FOLLOW_returnStatement_in_statement100);
                    returnStatement();

                    state._fsp--;


                    }
                    break;
                case 11 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:35:4: withStatement
                    {
                    pushFollow(FOLLOW_withStatement_in_statement105);
                    withStatement();

                    state._fsp--;


                    }
                    break;
                case 12 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:36:4: labelledStatement
                    {
                    pushFollow(FOLLOW_labelledStatement_in_statement110);
                    labelledStatement();

                    state._fsp--;


                    }
                    break;
                case 13 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:37:4: switchStatement
                    {
                    pushFollow(FOLLOW_switchStatement_in_statement115);
                    switchStatement();

                    state._fsp--;


                    }
                    break;
                case 14 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:38:4: throwStatement
                    {
                    pushFollow(FOLLOW_throwStatement_in_statement120);
                    throwStatement();

                    state._fsp--;


                    }
                    break;
                case 15 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:39:4: tryStatement
                    {
                    pushFollow(FOLLOW_tryStatement_in_statement125);
                    tryStatement();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "statement"


    // $ANTLR start "block"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:42:1: block : ^( BLOCK ( statement )* ) ;
    public final void block() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:43:2: ( ^( BLOCK ( statement )* ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:43:4: ^( BLOCK ( statement )* )
            {
            match(input,BLOCK,FOLLOW_BLOCK_in_block138); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:43:13: ( statement )*
                loop3:
                do {
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( ((LA3_0>=NULL && LA3_0<=BREAK)||LA3_0==CONTINUE||(LA3_0>=DELETE && LA3_0<=DO)||(LA3_0>=FOR && LA3_0<=WITH)||(LA3_0>=LT && LA3_0<=QUE)||(LA3_0>=ASSIGN && LA3_0<=DIVASS)||(LA3_0>=ARRAY && LA3_0<=CEXPR)||LA3_0==LABELLED||(LA3_0>=NEG && LA3_0<=OBJECT)||(LA3_0>=PDEC && LA3_0<=POS)||(LA3_0>=Identifier && LA3_0<=StringLiteral)||LA3_0==RegularExpressionLiteral||(LA3_0>=DecimalLiteral && LA3_0<=HexIntegerLiteral)) ) {
                        alt3=1;
                    }


                    switch (alt3) {
                	case 1 :
                	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:43:13: statement
                	    {
                	    pushFollow(FOLLOW_statement_in_block140);
                	    statement();

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop3;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "block"


    // $ANTLR start "variableDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:46:1: variableDeclaration : ^( VAR ( Identifier | ^( ASSIGN Identifier expr ) )+ ) ;
    public final void variableDeclaration() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:47:2: ( ^( VAR ( Identifier | ^( ASSIGN Identifier expr ) )+ ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:47:4: ^( VAR ( Identifier | ^( ASSIGN Identifier expr ) )+ )
            {
            match(input,VAR,FOLLOW_VAR_in_variableDeclaration156); 

            match(input, Token.DOWN, null); 
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:47:11: ( Identifier | ^( ASSIGN Identifier expr ) )+
            int cnt4=0;
            loop4:
            do {
                int alt4=3;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==Identifier) ) {
                    alt4=1;
                }
                else if ( (LA4_0==ASSIGN) ) {
                    alt4=2;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:47:13: Identifier
            	    {
            	    match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration160); 

            	    }
            	    break;
            	case 2 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:47:26: ^( ASSIGN Identifier expr )
            	    {
            	    match(input,ASSIGN,FOLLOW_ASSIGN_in_variableDeclaration166); 

            	    match(input, Token.DOWN, null); 
            	    match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration168); 
            	    pushFollow(FOLLOW_expr_in_variableDeclaration170);
            	    expr();

            	    state._fsp--;


            	    match(input, Token.UP, null); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "variableDeclaration"


    // $ANTLR start "ifStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:50:1: ifStatement : ^( IF expression ( statement )+ ) ;
    public final void ifStatement() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:51:2: ( ^( IF expression ( statement )+ ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:51:4: ^( IF expression ( statement )+ )
            {
            match(input,IF,FOLLOW_IF_in_ifStatement190); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_ifStatement192);
            expression();

            state._fsp--;

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:51:21: ( statement )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>=NULL && LA5_0<=BREAK)||LA5_0==CONTINUE||(LA5_0>=DELETE && LA5_0<=DO)||(LA5_0>=FOR && LA5_0<=WITH)||(LA5_0>=LT && LA5_0<=QUE)||(LA5_0>=ASSIGN && LA5_0<=DIVASS)||(LA5_0>=ARRAY && LA5_0<=CEXPR)||LA5_0==LABELLED||(LA5_0>=NEG && LA5_0<=OBJECT)||(LA5_0>=PDEC && LA5_0<=POS)||(LA5_0>=Identifier && LA5_0<=StringLiteral)||LA5_0==RegularExpressionLiteral||(LA5_0>=DecimalLiteral && LA5_0<=HexIntegerLiteral)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:51:21: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_ifStatement194);
            	    statement();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "ifStatement"


    // $ANTLR start "doStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:54:1: doStatement : ^( DO statement expression ) ;
    public final void doStatement() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:55:2: ( ^( DO statement expression ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:55:4: ^( DO statement expression )
            {
            match(input,DO,FOLLOW_DO_in_doStatement210); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_statement_in_doStatement212);
            statement();

            state._fsp--;

            pushFollow(FOLLOW_expression_in_doStatement214);
            expression();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "doStatement"


    // $ANTLR start "whileStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:58:1: whileStatement : ^( WHILE expression statement ) ;
    public final void whileStatement() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:59:2: ( ^( WHILE expression statement ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:59:4: ^( WHILE expression statement )
            {
            match(input,WHILE,FOLLOW_WHILE_in_whileStatement229); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_whileStatement231);
            expression();

            state._fsp--;

            pushFollow(FOLLOW_statement_in_whileStatement233);
            statement();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "whileStatement"


    // $ANTLR start "forStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:62:1: forStatement : ^( FOR ( ^( FORSTEP ( exprOptClause | variableDeclaration ) exprOptClause exprOptClause ) | ^( FORITER ( exprClause | variableDeclaration ) exprClause ) ) statement ) ;
    public final void forStatement() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:63:2: ( ^( FOR ( ^( FORSTEP ( exprOptClause | variableDeclaration ) exprOptClause exprOptClause ) | ^( FORITER ( exprClause | variableDeclaration ) exprClause ) ) statement ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:63:4: ^( FOR ( ^( FORSTEP ( exprOptClause | variableDeclaration ) exprOptClause exprOptClause ) | ^( FORITER ( exprClause | variableDeclaration ) exprClause ) ) statement )
            {
            match(input,FOR,FOLLOW_FOR_in_forStatement249); 

            match(input, Token.DOWN, null); 
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:65:2: ( ^( FORSTEP ( exprOptClause | variableDeclaration ) exprOptClause exprOptClause ) | ^( FORITER ( exprClause | variableDeclaration ) exprClause ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==FORSTEP) ) {
                alt8=1;
            }
            else if ( (LA8_0==FORITER) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:66:3: ^( FORSTEP ( exprOptClause | variableDeclaration ) exprOptClause exprOptClause )
                    {
                    match(input,FORSTEP,FOLLOW_FORSTEP_in_forStatement259); 

                    match(input, Token.DOWN, null); 
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:66:14: ( exprOptClause | variableDeclaration )
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==EXPR) ) {
                        alt6=1;
                    }
                    else if ( (LA6_0==VAR) ) {
                        alt6=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 0, input);

                        throw nvae;
                    }
                    switch (alt6) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:66:16: exprOptClause
                            {
                            pushFollow(FOLLOW_exprOptClause_in_forStatement263);
                            exprOptClause();

                            state._fsp--;


                            }
                            break;
                        case 2 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:66:32: variableDeclaration
                            {
                            pushFollow(FOLLOW_variableDeclaration_in_forStatement267);
                            variableDeclaration();

                            state._fsp--;


                            }
                            break;

                    }

                    pushFollow(FOLLOW_exprOptClause_in_forStatement271);
                    exprOptClause();

                    state._fsp--;

                    pushFollow(FOLLOW_exprOptClause_in_forStatement273);
                    exprOptClause();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:67:5: ^( FORITER ( exprClause | variableDeclaration ) exprClause )
                    {
                    match(input,FORITER,FOLLOW_FORITER_in_forStatement283); 

                    match(input, Token.DOWN, null); 
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:67:16: ( exprClause | variableDeclaration )
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==EXPR) ) {
                        alt7=1;
                    }
                    else if ( (LA7_0==VAR) ) {
                        alt7=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 0, input);

                        throw nvae;
                    }
                    switch (alt7) {
                        case 1 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:67:18: exprClause
                            {
                            pushFollow(FOLLOW_exprClause_in_forStatement287);
                            exprClause();

                            state._fsp--;


                            }
                            break;
                        case 2 :
                            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:67:31: variableDeclaration
                            {
                            pushFollow(FOLLOW_variableDeclaration_in_forStatement291);
                            variableDeclaration();

                            state._fsp--;


                            }
                            break;

                    }

                    pushFollow(FOLLOW_exprClause_in_forStatement295);
                    exprClause();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;

            }

            pushFollow(FOLLOW_statement_in_forStatement303);
            statement();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "forStatement"


    // $ANTLR start "exprOptClause"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:72:1: exprOptClause : ^( EXPR ( expression )? ) ;
    public final void exprOptClause() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:73:2: ( ^( EXPR ( expression )? ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:73:4: ^( EXPR ( expression )? )
            {
            match(input,EXPR,FOLLOW_EXPR_in_exprOptClause317); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:73:12: ( expression )?
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=NULL && LA9_0<=FALSE)||LA9_0==DELETE||LA9_0==FUNCTION||(LA9_0>=IN && LA9_0<=NEW)||LA9_0==THIS||LA9_0==TYPEOF||LA9_0==VOID||(LA9_0>=LT && LA9_0<=QUE)||(LA9_0>=ASSIGN && LA9_0<=DIVASS)||LA9_0==ARRAY||(LA9_0>=BYFIELD && LA9_0<=CEXPR)||(LA9_0>=NEG && LA9_0<=OBJECT)||(LA9_0>=PDEC && LA9_0<=POS)||(LA9_0>=Identifier && LA9_0<=StringLiteral)||LA9_0==RegularExpressionLiteral||(LA9_0>=DecimalLiteral && LA9_0<=HexIntegerLiteral)) ) {
                    alt9=1;
                }
                switch (alt9) {
                    case 1 :
                        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:73:12: expression
                        {
                        pushFollow(FOLLOW_expression_in_exprOptClause319);
                        expression();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprOptClause"


    // $ANTLR start "exprClause"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:76:1: exprClause : ^( EXPR expression ) ;
    public final void exprClause() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:77:2: ( ^( EXPR expression ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:77:4: ^( EXPR expression )
            {
            match(input,EXPR,FOLLOW_EXPR_in_exprClause335); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_exprClause337);
            expression();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprClause"


    // $ANTLR start "continueStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:80:1: continueStatement : ^( CONTINUE ( Identifier )? ) ;
    public final void continueStatement() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:81:2: ( ^( CONTINUE ( Identifier )? ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:81:4: ^( CONTINUE ( Identifier )? )
            {
            match(input,CONTINUE,FOLLOW_CONTINUE_in_continueStatement352); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:81:16: ( Identifier )?
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==Identifier) ) {
                    alt10=1;
                }
                switch (alt10) {
                    case 1 :
                        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:81:16: Identifier
                        {
                        match(input,Identifier,FOLLOW_Identifier_in_continueStatement354); 

                        }
                        break;

                }


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "continueStatement"


    // $ANTLR start "breakStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:84:1: breakStatement : ^( BREAK ( Identifier )? ) ;
    public final void breakStatement() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:85:2: ( ^( BREAK ( Identifier )? ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:85:4: ^( BREAK ( Identifier )? )
            {
            match(input,BREAK,FOLLOW_BREAK_in_breakStatement370); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:85:13: ( Identifier )?
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==Identifier) ) {
                    alt11=1;
                }
                switch (alt11) {
                    case 1 :
                        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:85:13: Identifier
                        {
                        match(input,Identifier,FOLLOW_Identifier_in_breakStatement372); 

                        }
                        break;

                }


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "breakStatement"


    // $ANTLR start "returnStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:88:1: returnStatement : ^( RETURN ( expression )? ) ;
    public final void returnStatement() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:89:2: ( ^( RETURN ( expression )? ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:89:4: ^( RETURN ( expression )? )
            {
            match(input,RETURN,FOLLOW_RETURN_in_returnStatement388); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:89:14: ( expression )?
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>=NULL && LA12_0<=FALSE)||LA12_0==DELETE||LA12_0==FUNCTION||(LA12_0>=IN && LA12_0<=NEW)||LA12_0==THIS||LA12_0==TYPEOF||LA12_0==VOID||(LA12_0>=LT && LA12_0<=QUE)||(LA12_0>=ASSIGN && LA12_0<=DIVASS)||LA12_0==ARRAY||(LA12_0>=BYFIELD && LA12_0<=CEXPR)||(LA12_0>=NEG && LA12_0<=OBJECT)||(LA12_0>=PDEC && LA12_0<=POS)||(LA12_0>=Identifier && LA12_0<=StringLiteral)||LA12_0==RegularExpressionLiteral||(LA12_0>=DecimalLiteral && LA12_0<=HexIntegerLiteral)) ) {
                    alt12=1;
                }
                switch (alt12) {
                    case 1 :
                        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:89:14: expression
                        {
                        pushFollow(FOLLOW_expression_in_returnStatement390);
                        expression();

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "returnStatement"


    // $ANTLR start "withStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:92:1: withStatement : ^( WITH expression statement ) ;
    public final void withStatement() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:93:2: ( ^( WITH expression statement ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:93:4: ^( WITH expression statement )
            {
            match(input,WITH,FOLLOW_WITH_in_withStatement406); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_withStatement408);
            expression();

            state._fsp--;

            pushFollow(FOLLOW_statement_in_withStatement410);
            statement();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "withStatement"


    // $ANTLR start "labelledStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:96:1: labelledStatement : ^( LABELLED Identifier statement ) ;
    public final void labelledStatement() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:97:2: ( ^( LABELLED Identifier statement ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:97:4: ^( LABELLED Identifier statement )
            {
            match(input,LABELLED,FOLLOW_LABELLED_in_labelledStatement425); 

            match(input, Token.DOWN, null); 
            match(input,Identifier,FOLLOW_Identifier_in_labelledStatement427); 
            pushFollow(FOLLOW_statement_in_labelledStatement429);
            statement();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "labelledStatement"


    // $ANTLR start "switchStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:100:1: switchStatement : ^( SWITCH expression ( defaultClause )? ( caseClause )* ) ;
    public final void switchStatement() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:101:2: ( ^( SWITCH expression ( defaultClause )? ( caseClause )* ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:101:4: ^( SWITCH expression ( defaultClause )? ( caseClause )* )
            {
            match(input,SWITCH,FOLLOW_SWITCH_in_switchStatement444); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_switchStatement446);
            expression();

            state._fsp--;

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:101:25: ( defaultClause )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==DEFAULT) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:101:25: defaultClause
                    {
                    pushFollow(FOLLOW_defaultClause_in_switchStatement448);
                    defaultClause();

                    state._fsp--;


                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:101:40: ( caseClause )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==CASE) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:101:40: caseClause
            	    {
            	    pushFollow(FOLLOW_caseClause_in_switchStatement451);
            	    caseClause();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "switchStatement"


    // $ANTLR start "defaultClause"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:104:1: defaultClause : ^( DEFAULT ( statement )* ) ;
    public final void defaultClause() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:105:2: ( ^( DEFAULT ( statement )* ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:105:4: ^( DEFAULT ( statement )* )
            {
            match(input,DEFAULT,FOLLOW_DEFAULT_in_defaultClause467); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:105:15: ( statement )*
                loop15:
                do {
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( ((LA15_0>=NULL && LA15_0<=BREAK)||LA15_0==CONTINUE||(LA15_0>=DELETE && LA15_0<=DO)||(LA15_0>=FOR && LA15_0<=WITH)||(LA15_0>=LT && LA15_0<=QUE)||(LA15_0>=ASSIGN && LA15_0<=DIVASS)||(LA15_0>=ARRAY && LA15_0<=CEXPR)||LA15_0==LABELLED||(LA15_0>=NEG && LA15_0<=OBJECT)||(LA15_0>=PDEC && LA15_0<=POS)||(LA15_0>=Identifier && LA15_0<=StringLiteral)||LA15_0==RegularExpressionLiteral||(LA15_0>=DecimalLiteral && LA15_0<=HexIntegerLiteral)) ) {
                        alt15=1;
                    }


                    switch (alt15) {
                	case 1 :
                	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:105:15: statement
                	    {
                	    pushFollow(FOLLOW_statement_in_defaultClause469);
                	    statement();

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop15;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "defaultClause"


    // $ANTLR start "caseClause"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:108:1: caseClause : ^( CASE expression ( statement )* ) ;
    public final void caseClause() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:109:2: ( ^( CASE expression ( statement )* ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:109:4: ^( CASE expression ( statement )* )
            {
            match(input,CASE,FOLLOW_CASE_in_caseClause485); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_caseClause487);
            expression();

            state._fsp--;

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:109:23: ( statement )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>=NULL && LA16_0<=BREAK)||LA16_0==CONTINUE||(LA16_0>=DELETE && LA16_0<=DO)||(LA16_0>=FOR && LA16_0<=WITH)||(LA16_0>=LT && LA16_0<=QUE)||(LA16_0>=ASSIGN && LA16_0<=DIVASS)||(LA16_0>=ARRAY && LA16_0<=CEXPR)||LA16_0==LABELLED||(LA16_0>=NEG && LA16_0<=OBJECT)||(LA16_0>=PDEC && LA16_0<=POS)||(LA16_0>=Identifier && LA16_0<=StringLiteral)||LA16_0==RegularExpressionLiteral||(LA16_0>=DecimalLiteral && LA16_0<=HexIntegerLiteral)) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:109:23: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_caseClause489);
            	    statement();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "caseClause"


    // $ANTLR start "throwStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:112:1: throwStatement : ^( THROW expression ) ;
    public final void throwStatement() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:113:2: ( ^( THROW expression ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:113:4: ^( THROW expression )
            {
            match(input,THROW,FOLLOW_THROW_in_throwStatement505); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_throwStatement507);
            expression();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "throwStatement"


    // $ANTLR start "tryStatement"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:116:1: tryStatement : ^( TRY block ( catchClause )? ( finallyClause )? ) ;
    public final void tryStatement() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:117:2: ( ^( TRY block ( catchClause )? ( finallyClause )? ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:117:4: ^( TRY block ( catchClause )? ( finallyClause )? )
            {
            match(input,TRY,FOLLOW_TRY_in_tryStatement522); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_block_in_tryStatement524);
            block();

            state._fsp--;

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:117:17: ( catchClause )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==CATCH) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:117:17: catchClause
                    {
                    pushFollow(FOLLOW_catchClause_in_tryStatement526);
                    catchClause();

                    state._fsp--;


                    }
                    break;

            }

            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:117:30: ( finallyClause )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==FINALLY) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:117:30: finallyClause
                    {
                    pushFollow(FOLLOW_finallyClause_in_tryStatement529);
                    finallyClause();

                    state._fsp--;


                    }
                    break;

            }


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "tryStatement"


    // $ANTLR start "catchClause"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:120:1: catchClause : ^( CATCH Identifier block ) ;
    public final void catchClause() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:121:2: ( ^( CATCH Identifier block ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:121:4: ^( CATCH Identifier block )
            {
            match(input,CATCH,FOLLOW_CATCH_in_catchClause546); 

            match(input, Token.DOWN, null); 
            match(input,Identifier,FOLLOW_Identifier_in_catchClause548); 
            pushFollow(FOLLOW_block_in_catchClause550);
            block();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "catchClause"


    // $ANTLR start "finallyClause"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:124:1: finallyClause : ^( FINALLY block ) ;
    public final void finallyClause() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:125:2: ( ^( FINALLY block ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:125:4: ^( FINALLY block )
            {
            match(input,FINALLY,FOLLOW_FINALLY_in_finallyClause566); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_block_in_finallyClause568);
            block();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "finallyClause"


    // $ANTLR start "expression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:128:1: expression : ( expr | ^( CEXPR ( expr )+ ) );
    public final void expression() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:129:2: ( expr | ^( CEXPR ( expr )+ ) )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( ((LA20_0>=NULL && LA20_0<=FALSE)||LA20_0==DELETE||LA20_0==FUNCTION||(LA20_0>=IN && LA20_0<=NEW)||LA20_0==THIS||LA20_0==TYPEOF||LA20_0==VOID||(LA20_0>=LT && LA20_0<=QUE)||(LA20_0>=ASSIGN && LA20_0<=DIVASS)||LA20_0==ARRAY||(LA20_0>=BYFIELD && LA20_0<=CALL)||(LA20_0>=NEG && LA20_0<=OBJECT)||(LA20_0>=PDEC && LA20_0<=POS)||(LA20_0>=Identifier && LA20_0<=StringLiteral)||LA20_0==RegularExpressionLiteral||(LA20_0>=DecimalLiteral && LA20_0<=HexIntegerLiteral)) ) {
                alt20=1;
            }
            else if ( (LA20_0==CEXPR) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:129:4: expr
                    {
                    pushFollow(FOLLOW_expr_in_expression581);
                    expr();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:130:4: ^( CEXPR ( expr )+ )
                    {
                    match(input,CEXPR,FOLLOW_CEXPR_in_expression589); 

                    match(input, Token.DOWN, null); 
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:130:13: ( expr )+
                    int cnt19=0;
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( ((LA19_0>=NULL && LA19_0<=FALSE)||LA19_0==DELETE||LA19_0==FUNCTION||(LA19_0>=IN && LA19_0<=NEW)||LA19_0==THIS||LA19_0==TYPEOF||LA19_0==VOID||(LA19_0>=LT && LA19_0<=QUE)||(LA19_0>=ASSIGN && LA19_0<=DIVASS)||LA19_0==ARRAY||(LA19_0>=BYFIELD && LA19_0<=CALL)||(LA19_0>=NEG && LA19_0<=OBJECT)||(LA19_0>=PDEC && LA19_0<=POS)||(LA19_0>=Identifier && LA19_0<=StringLiteral)||LA19_0==RegularExpressionLiteral||(LA19_0>=DecimalLiteral && LA19_0<=HexIntegerLiteral)) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:130:13: expr
                    	    {
                    	    pushFollow(FOLLOW_expr_in_expression591);
                    	    expr();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt19 >= 1 ) break loop19;
                                EarlyExitException eee =
                                    new EarlyExitException(19, input);
                                throw eee;
                        }
                        cnt19++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "expression"


    // $ANTLR start "expr"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:133:1: expr : ( leftHandSideExpression | ^( ASSIGN expr expr ) | ^( MULASS expr expr ) | ^( DIVASS expr expr ) | ^( MODASS expr expr ) | ^( ADDASS expr expr ) | ^( SUBASS expr expr ) | ^( SHLASS expr expr ) | ^( SHRASS expr expr ) | ^( SHUASS expr expr ) | ^( ANDASS expr expr ) | ^( XORASS expr expr ) | ^( ORASS expr expr ) | ^( QUE expr expr expr ) | ^( LOR expr expr ) | ^( LAND expr expr ) | ^( AND expr expr ) | ^( OR expr expr ) | ^( XOR expr expr ) | ^( EQ expr expr ) | ^( NEQ expr expr ) | ^( SAME expr expr ) | ^( NSAME expr expr ) | ^( LT expr expr ) | ^( GT expr expr ) | ^( LTE expr expr ) | ^( GTE expr expr ) | ^( INSTANCEOF expr expr ) | ^( IN expr expr ) | ^( SHL expr expr ) | ^( SHR expr expr ) | ^( SHU expr expr ) | ^( ADD expr expr ) | ^( SUB expr expr ) | ^( MUL expr expr ) | ^( DIV expr expr ) | ^( MOD expr expr ) | ^( DELETE expr ) | ^( VOID expr ) | ^( TYPEOF expr ) | ^( INC expr ) | ^( DEC expr ) | ^( POS expr ) | ^( NEG expr ) | ^( INV expr ) | ^( NOT expr ) | ^( PINC expr ) | ^( PDEC expr ) );
    public final void expr() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:134:2: ( leftHandSideExpression | ^( ASSIGN expr expr ) | ^( MULASS expr expr ) | ^( DIVASS expr expr ) | ^( MODASS expr expr ) | ^( ADDASS expr expr ) | ^( SUBASS expr expr ) | ^( SHLASS expr expr ) | ^( SHRASS expr expr ) | ^( SHUASS expr expr ) | ^( ANDASS expr expr ) | ^( XORASS expr expr ) | ^( ORASS expr expr ) | ^( QUE expr expr expr ) | ^( LOR expr expr ) | ^( LAND expr expr ) | ^( AND expr expr ) | ^( OR expr expr ) | ^( XOR expr expr ) | ^( EQ expr expr ) | ^( NEQ expr expr ) | ^( SAME expr expr ) | ^( NSAME expr expr ) | ^( LT expr expr ) | ^( GT expr expr ) | ^( LTE expr expr ) | ^( GTE expr expr ) | ^( INSTANCEOF expr expr ) | ^( IN expr expr ) | ^( SHL expr expr ) | ^( SHR expr expr ) | ^( SHU expr expr ) | ^( ADD expr expr ) | ^( SUB expr expr ) | ^( MUL expr expr ) | ^( DIV expr expr ) | ^( MOD expr expr ) | ^( DELETE expr ) | ^( VOID expr ) | ^( TYPEOF expr ) | ^( INC expr ) | ^( DEC expr ) | ^( POS expr ) | ^( NEG expr ) | ^( INV expr ) | ^( NOT expr ) | ^( PINC expr ) | ^( PDEC expr ) )
            int alt21=48;
            switch ( input.LA(1) ) {
            case NULL:
            case TRUE:
            case FALSE:
            case FUNCTION:
            case NEW:
            case THIS:
            case ARRAY:
            case BYFIELD:
            case BYINDEX:
            case CALL:
            case OBJECT:
            case Identifier:
            case StringLiteral:
            case RegularExpressionLiteral:
            case DecimalLiteral:
            case OctalIntegerLiteral:
            case HexIntegerLiteral:
                {
                alt21=1;
                }
                break;
            case ASSIGN:
                {
                alt21=2;
                }
                break;
            case MULASS:
                {
                alt21=3;
                }
                break;
            case DIVASS:
                {
                alt21=4;
                }
                break;
            case MODASS:
                {
                alt21=5;
                }
                break;
            case ADDASS:
                {
                alt21=6;
                }
                break;
            case SUBASS:
                {
                alt21=7;
                }
                break;
            case SHLASS:
                {
                alt21=8;
                }
                break;
            case SHRASS:
                {
                alt21=9;
                }
                break;
            case SHUASS:
                {
                alt21=10;
                }
                break;
            case ANDASS:
                {
                alt21=11;
                }
                break;
            case XORASS:
                {
                alt21=12;
                }
                break;
            case ORASS:
                {
                alt21=13;
                }
                break;
            case QUE:
                {
                alt21=14;
                }
                break;
            case LOR:
                {
                alt21=15;
                }
                break;
            case LAND:
                {
                alt21=16;
                }
                break;
            case AND:
                {
                alt21=17;
                }
                break;
            case OR:
                {
                alt21=18;
                }
                break;
            case XOR:
                {
                alt21=19;
                }
                break;
            case EQ:
                {
                alt21=20;
                }
                break;
            case NEQ:
                {
                alt21=21;
                }
                break;
            case SAME:
                {
                alt21=22;
                }
                break;
            case NSAME:
                {
                alt21=23;
                }
                break;
            case LT:
                {
                alt21=24;
                }
                break;
            case GT:
                {
                alt21=25;
                }
                break;
            case LTE:
                {
                alt21=26;
                }
                break;
            case GTE:
                {
                alt21=27;
                }
                break;
            case INSTANCEOF:
                {
                alt21=28;
                }
                break;
            case IN:
                {
                alt21=29;
                }
                break;
            case SHL:
                {
                alt21=30;
                }
                break;
            case SHR:
                {
                alt21=31;
                }
                break;
            case SHU:
                {
                alt21=32;
                }
                break;
            case ADD:
                {
                alt21=33;
                }
                break;
            case SUB:
                {
                alt21=34;
                }
                break;
            case MUL:
                {
                alt21=35;
                }
                break;
            case DIV:
                {
                alt21=36;
                }
                break;
            case MOD:
                {
                alt21=37;
                }
                break;
            case DELETE:
                {
                alt21=38;
                }
                break;
            case VOID:
                {
                alt21=39;
                }
                break;
            case TYPEOF:
                {
                alt21=40;
                }
                break;
            case INC:
                {
                alt21=41;
                }
                break;
            case DEC:
                {
                alt21=42;
                }
                break;
            case POS:
                {
                alt21=43;
                }
                break;
            case NEG:
                {
                alt21=44;
                }
                break;
            case INV:
                {
                alt21=45;
                }
                break;
            case NOT:
                {
                alt21=46;
                }
                break;
            case PINC:
                {
                alt21=47;
                }
                break;
            case PDEC:
                {
                alt21=48;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:134:4: leftHandSideExpression
                    {
                    pushFollow(FOLLOW_leftHandSideExpression_in_expr605);
                    leftHandSideExpression();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:137:4: ^( ASSIGN expr expr )
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_expr616); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr618);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr620);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:138:4: ^( MULASS expr expr )
                    {
                    match(input,MULASS,FOLLOW_MULASS_in_expr629); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr631);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr633);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:139:4: ^( DIVASS expr expr )
                    {
                    match(input,DIVASS,FOLLOW_DIVASS_in_expr642); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr644);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr646);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 5 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:140:4: ^( MODASS expr expr )
                    {
                    match(input,MODASS,FOLLOW_MODASS_in_expr655); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr657);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr659);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 6 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:141:4: ^( ADDASS expr expr )
                    {
                    match(input,ADDASS,FOLLOW_ADDASS_in_expr668); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr670);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr672);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 7 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:142:4: ^( SUBASS expr expr )
                    {
                    match(input,SUBASS,FOLLOW_SUBASS_in_expr681); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr683);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr685);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 8 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:143:4: ^( SHLASS expr expr )
                    {
                    match(input,SHLASS,FOLLOW_SHLASS_in_expr694); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr696);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr698);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 9 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:144:4: ^( SHRASS expr expr )
                    {
                    match(input,SHRASS,FOLLOW_SHRASS_in_expr707); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr709);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr711);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 10 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:145:4: ^( SHUASS expr expr )
                    {
                    match(input,SHUASS,FOLLOW_SHUASS_in_expr720); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr722);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr724);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 11 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:146:4: ^( ANDASS expr expr )
                    {
                    match(input,ANDASS,FOLLOW_ANDASS_in_expr733); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr735);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr737);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 12 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:147:4: ^( XORASS expr expr )
                    {
                    match(input,XORASS,FOLLOW_XORASS_in_expr746); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr748);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr750);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 13 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:148:4: ^( ORASS expr expr )
                    {
                    match(input,ORASS,FOLLOW_ORASS_in_expr759); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr761);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr763);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 14 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:151:4: ^( QUE expr expr expr )
                    {
                    match(input,QUE,FOLLOW_QUE_in_expr776); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr778);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr780);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr782);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 15 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:154:4: ^( LOR expr expr )
                    {
                    match(input,LOR,FOLLOW_LOR_in_expr795); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr797);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr799);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 16 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:155:4: ^( LAND expr expr )
                    {
                    match(input,LAND,FOLLOW_LAND_in_expr808); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr810);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr812);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 17 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:158:4: ^( AND expr expr )
                    {
                    match(input,AND,FOLLOW_AND_in_expr825); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr827);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr829);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 18 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:159:4: ^( OR expr expr )
                    {
                    match(input,OR,FOLLOW_OR_in_expr838); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr840);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr842);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 19 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:160:4: ^( XOR expr expr )
                    {
                    match(input,XOR,FOLLOW_XOR_in_expr851); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr853);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr855);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 20 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:163:4: ^( EQ expr expr )
                    {
                    match(input,EQ,FOLLOW_EQ_in_expr868); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr870);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr872);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 21 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:164:4: ^( NEQ expr expr )
                    {
                    match(input,NEQ,FOLLOW_NEQ_in_expr881); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr883);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr885);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 22 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:165:4: ^( SAME expr expr )
                    {
                    match(input,SAME,FOLLOW_SAME_in_expr894); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr896);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr898);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 23 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:166:4: ^( NSAME expr expr )
                    {
                    match(input,NSAME,FOLLOW_NSAME_in_expr907); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr909);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr911);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 24 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:169:4: ^( LT expr expr )
                    {
                    match(input,LT,FOLLOW_LT_in_expr924); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr926);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr928);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 25 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:170:4: ^( GT expr expr )
                    {
                    match(input,GT,FOLLOW_GT_in_expr937); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr939);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr941);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 26 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:171:4: ^( LTE expr expr )
                    {
                    match(input,LTE,FOLLOW_LTE_in_expr950); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr952);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr954);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 27 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:172:4: ^( GTE expr expr )
                    {
                    match(input,GTE,FOLLOW_GTE_in_expr963); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr965);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr967);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 28 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:173:4: ^( INSTANCEOF expr expr )
                    {
                    match(input,INSTANCEOF,FOLLOW_INSTANCEOF_in_expr976); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr978);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr980);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 29 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:174:4: ^( IN expr expr )
                    {
                    match(input,IN,FOLLOW_IN_in_expr989); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr991);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr993);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 30 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:177:4: ^( SHL expr expr )
                    {
                    match(input,SHL,FOLLOW_SHL_in_expr1006); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1008);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr1010);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 31 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:178:4: ^( SHR expr expr )
                    {
                    match(input,SHR,FOLLOW_SHR_in_expr1019); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1021);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr1023);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 32 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:179:4: ^( SHU expr expr )
                    {
                    match(input,SHU,FOLLOW_SHU_in_expr1032); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1034);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr1036);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 33 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:182:4: ^( ADD expr expr )
                    {
                    match(input,ADD,FOLLOW_ADD_in_expr1049); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1051);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr1053);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 34 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:183:4: ^( SUB expr expr )
                    {
                    match(input,SUB,FOLLOW_SUB_in_expr1062); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1064);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr1066);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 35 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:186:4: ^( MUL expr expr )
                    {
                    match(input,MUL,FOLLOW_MUL_in_expr1079); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1081);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr1083);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 36 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:187:4: ^( DIV expr expr )
                    {
                    match(input,DIV,FOLLOW_DIV_in_expr1092); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1094);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr1096);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 37 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:188:4: ^( MOD expr expr )
                    {
                    match(input,MOD,FOLLOW_MOD_in_expr1105); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1107);
                    expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr1109);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 38 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:191:4: ^( DELETE expr )
                    {
                    match(input,DELETE,FOLLOW_DELETE_in_expr1122); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1124);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 39 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:192:4: ^( VOID expr )
                    {
                    match(input,VOID,FOLLOW_VOID_in_expr1133); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1135);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 40 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:193:4: ^( TYPEOF expr )
                    {
                    match(input,TYPEOF,FOLLOW_TYPEOF_in_expr1144); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1146);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 41 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:194:4: ^( INC expr )
                    {
                    match(input,INC,FOLLOW_INC_in_expr1155); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1157);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 42 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:195:4: ^( DEC expr )
                    {
                    match(input,DEC,FOLLOW_DEC_in_expr1166); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1168);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 43 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:196:4: ^( POS expr )
                    {
                    match(input,POS,FOLLOW_POS_in_expr1177); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1179);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 44 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:197:4: ^( NEG expr )
                    {
                    match(input,NEG,FOLLOW_NEG_in_expr1188); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1190);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 45 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:198:4: ^( INV expr )
                    {
                    match(input,INV,FOLLOW_INV_in_expr1199); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1201);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 46 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:199:4: ^( NOT expr )
                    {
                    match(input,NOT,FOLLOW_NOT_in_expr1210); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1212);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 47 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:202:4: ^( PINC expr )
                    {
                    match(input,PINC,FOLLOW_PINC_in_expr1225); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1227);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 48 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:203:4: ^( PDEC expr )
                    {
                    match(input,PDEC,FOLLOW_PDEC_in_expr1236); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1238);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "expr"


    // $ANTLR start "leftHandSideExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:206:1: leftHandSideExpression : ( primaryExpression | newExpression | functionDeclaration | callExpression | memberExpression );
    public final void leftHandSideExpression() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:207:2: ( primaryExpression | newExpression | functionDeclaration | callExpression | memberExpression )
            int alt22=5;
            switch ( input.LA(1) ) {
            case NULL:
            case TRUE:
            case FALSE:
            case THIS:
            case ARRAY:
            case OBJECT:
            case Identifier:
            case StringLiteral:
            case RegularExpressionLiteral:
            case DecimalLiteral:
            case OctalIntegerLiteral:
            case HexIntegerLiteral:
                {
                alt22=1;
                }
                break;
            case NEW:
                {
                alt22=2;
                }
                break;
            case FUNCTION:
                {
                alt22=3;
                }
                break;
            case CALL:
                {
                alt22=4;
                }
                break;
            case BYFIELD:
            case BYINDEX:
                {
                alt22=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }

            switch (alt22) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:207:4: primaryExpression
                    {
                    pushFollow(FOLLOW_primaryExpression_in_leftHandSideExpression1251);
                    primaryExpression();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:208:4: newExpression
                    {
                    pushFollow(FOLLOW_newExpression_in_leftHandSideExpression1256);
                    newExpression();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:209:4: functionDeclaration
                    {
                    pushFollow(FOLLOW_functionDeclaration_in_leftHandSideExpression1261);
                    functionDeclaration();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:210:4: callExpression
                    {
                    pushFollow(FOLLOW_callExpression_in_leftHandSideExpression1266);
                    callExpression();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:211:4: memberExpression
                    {
                    pushFollow(FOLLOW_memberExpression_in_leftHandSideExpression1271);
                    memberExpression();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "leftHandSideExpression"


    // $ANTLR start "newExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:214:1: newExpression : ^( NEW leftHandSideExpression ) ;
    public final void newExpression() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:215:2: ( ^( NEW leftHandSideExpression ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:215:4: ^( NEW leftHandSideExpression )
            {
            match(input,NEW,FOLLOW_NEW_in_newExpression1284); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_leftHandSideExpression_in_newExpression1286);
            leftHandSideExpression();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "newExpression"


    // $ANTLR start "functionDeclaration"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:218:1: functionDeclaration : ^( FUNCTION ( Identifier )? ^( ARGS ( Identifier )* ) block ) ;
    public final void functionDeclaration() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:219:2: ( ^( FUNCTION ( Identifier )? ^( ARGS ( Identifier )* ) block ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:219:4: ^( FUNCTION ( Identifier )? ^( ARGS ( Identifier )* ) block )
            {
            match(input,FUNCTION,FOLLOW_FUNCTION_in_functionDeclaration1301); 

            match(input, Token.DOWN, null); 
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:219:16: ( Identifier )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==Identifier) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:219:16: Identifier
                    {
                    match(input,Identifier,FOLLOW_Identifier_in_functionDeclaration1303); 

                    }
                    break;

            }

            match(input,ARGS,FOLLOW_ARGS_in_functionDeclaration1308); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:219:36: ( Identifier )*
                loop24:
                do {
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==Identifier) ) {
                        alt24=1;
                    }


                    switch (alt24) {
                	case 1 :
                	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:219:36: Identifier
                	    {
                	    match(input,Identifier,FOLLOW_Identifier_in_functionDeclaration1310); 

                	    }
                	    break;

                	default :
                	    break loop24;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }
            pushFollow(FOLLOW_block_in_functionDeclaration1315);
            block();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "functionDeclaration"


    // $ANTLR start "callExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:222:1: callExpression : ^( CALL leftHandSideExpression ^( ARGS ( expr )* ) ) ;
    public final void callExpression() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:223:2: ( ^( CALL leftHandSideExpression ^( ARGS ( expr )* ) ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:223:4: ^( CALL leftHandSideExpression ^( ARGS ( expr )* ) )
            {
            match(input,CALL,FOLLOW_CALL_in_callExpression1330); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_leftHandSideExpression_in_callExpression1332);
            leftHandSideExpression();

            state._fsp--;

            match(input,ARGS,FOLLOW_ARGS_in_callExpression1336); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:223:43: ( expr )*
                loop25:
                do {
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( ((LA25_0>=NULL && LA25_0<=FALSE)||LA25_0==DELETE||LA25_0==FUNCTION||(LA25_0>=IN && LA25_0<=NEW)||LA25_0==THIS||LA25_0==TYPEOF||LA25_0==VOID||(LA25_0>=LT && LA25_0<=QUE)||(LA25_0>=ASSIGN && LA25_0<=DIVASS)||LA25_0==ARRAY||(LA25_0>=BYFIELD && LA25_0<=CALL)||(LA25_0>=NEG && LA25_0<=OBJECT)||(LA25_0>=PDEC && LA25_0<=POS)||(LA25_0>=Identifier && LA25_0<=StringLiteral)||LA25_0==RegularExpressionLiteral||(LA25_0>=DecimalLiteral && LA25_0<=HexIntegerLiteral)) ) {
                        alt25=1;
                    }


                    switch (alt25) {
                	case 1 :
                	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:223:43: expr
                	    {
                	    pushFollow(FOLLOW_expr_in_callExpression1338);
                	    expr();

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop25;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "callExpression"


    // $ANTLR start "memberExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:226:1: memberExpression : ( ^( BYINDEX leftHandSideExpression expression ) | ^( BYFIELD leftHandSideExpression Identifier ) );
    public final void memberExpression() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:227:2: ( ^( BYINDEX leftHandSideExpression expression ) | ^( BYFIELD leftHandSideExpression Identifier ) )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==BYINDEX) ) {
                alt26=1;
            }
            else if ( (LA26_0==BYFIELD) ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:227:4: ^( BYINDEX leftHandSideExpression expression )
                    {
                    match(input,BYINDEX,FOLLOW_BYINDEX_in_memberExpression1357); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_leftHandSideExpression_in_memberExpression1359);
                    leftHandSideExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_memberExpression1361);
                    expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:228:4: ^( BYFIELD leftHandSideExpression Identifier )
                    {
                    match(input,BYFIELD,FOLLOW_BYFIELD_in_memberExpression1370); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_leftHandSideExpression_in_memberExpression1372);
                    leftHandSideExpression();

                    state._fsp--;

                    match(input,Identifier,FOLLOW_Identifier_in_memberExpression1374); 

                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "memberExpression"


    // $ANTLR start "primaryExpression"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:231:1: primaryExpression : ( Identifier | literal );
    public final void primaryExpression() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:232:2: ( Identifier | literal )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==Identifier) ) {
                alt27=1;
            }
            else if ( ((LA27_0>=NULL && LA27_0<=FALSE)||LA27_0==THIS||LA27_0==ARRAY||LA27_0==OBJECT||LA27_0==StringLiteral||LA27_0==RegularExpressionLiteral||(LA27_0>=DecimalLiteral && LA27_0<=HexIntegerLiteral)) ) {
                alt27=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:232:4: Identifier
                    {
                    match(input,Identifier,FOLLOW_Identifier_in_primaryExpression1387); 

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:233:4: literal
                    {
                    pushFollow(FOLLOW_literal_in_primaryExpression1392);
                    literal();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "primaryExpression"


    // $ANTLR start "literal"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:236:1: literal : ( THIS | NULL | booleanLiteral | numericLiteral | StringLiteral | RegularExpressionLiteral | arrayLiteral | objectLiteral );
    public final void literal() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:237:2: ( THIS | NULL | booleanLiteral | numericLiteral | StringLiteral | RegularExpressionLiteral | arrayLiteral | objectLiteral )
            int alt28=8;
            switch ( input.LA(1) ) {
            case THIS:
                {
                alt28=1;
                }
                break;
            case NULL:
                {
                alt28=2;
                }
                break;
            case TRUE:
            case FALSE:
                {
                alt28=3;
                }
                break;
            case DecimalLiteral:
            case OctalIntegerLiteral:
            case HexIntegerLiteral:
                {
                alt28=4;
                }
                break;
            case StringLiteral:
                {
                alt28=5;
                }
                break;
            case RegularExpressionLiteral:
                {
                alt28=6;
                }
                break;
            case ARRAY:
                {
                alt28=7;
                }
                break;
            case OBJECT:
                {
                alt28=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }

            switch (alt28) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:237:4: THIS
                    {
                    match(input,THIS,FOLLOW_THIS_in_literal1403); 

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:238:4: NULL
                    {
                    match(input,NULL,FOLLOW_NULL_in_literal1408); 

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:239:4: booleanLiteral
                    {
                    pushFollow(FOLLOW_booleanLiteral_in_literal1413);
                    booleanLiteral();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:240:4: numericLiteral
                    {
                    pushFollow(FOLLOW_numericLiteral_in_literal1418);
                    numericLiteral();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:241:4: StringLiteral
                    {
                    match(input,StringLiteral,FOLLOW_StringLiteral_in_literal1423); 

                    }
                    break;
                case 6 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:242:4: RegularExpressionLiteral
                    {
                    match(input,RegularExpressionLiteral,FOLLOW_RegularExpressionLiteral_in_literal1428); 

                    }
                    break;
                case 7 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:243:4: arrayLiteral
                    {
                    pushFollow(FOLLOW_arrayLiteral_in_literal1433);
                    arrayLiteral();

                    state._fsp--;


                    }
                    break;
                case 8 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:244:4: objectLiteral
                    {
                    pushFollow(FOLLOW_objectLiteral_in_literal1438);
                    objectLiteral();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "literal"


    // $ANTLR start "booleanLiteral"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:247:1: booleanLiteral : ( TRUE | FALSE );
    public final void booleanLiteral() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:248:2: ( TRUE | FALSE )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:
            {
            if ( (input.LA(1)>=TRUE && input.LA(1)<=FALSE) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "booleanLiteral"


    // $ANTLR start "numericLiteral"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:252:1: numericLiteral : ( DecimalLiteral | OctalIntegerLiteral | HexIntegerLiteral );
    public final void numericLiteral() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:253:2: ( DecimalLiteral | OctalIntegerLiteral | HexIntegerLiteral )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:
            {
            if ( (input.LA(1)>=DecimalLiteral && input.LA(1)<=HexIntegerLiteral) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "numericLiteral"


    // $ANTLR start "arrayLiteral"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:258:1: arrayLiteral : ^( ARRAY ( ^( ITEM ( expr )? ) )* ) ;
    public final void arrayLiteral() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:259:2: ( ^( ARRAY ( ^( ITEM ( expr )? ) )* ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:259:4: ^( ARRAY ( ^( ITEM ( expr )? ) )* )
            {
            match(input,ARRAY,FOLLOW_ARRAY_in_arrayLiteral1488); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:259:13: ( ^( ITEM ( expr )? ) )*
                loop30:
                do {
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( (LA30_0==ITEM) ) {
                        alt30=1;
                    }


                    switch (alt30) {
                	case 1 :
                	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:259:15: ^( ITEM ( expr )? )
                	    {
                	    match(input,ITEM,FOLLOW_ITEM_in_arrayLiteral1494); 

                	    if ( input.LA(1)==Token.DOWN ) {
                	        match(input, Token.DOWN, null); 
                	        // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:259:23: ( expr )?
                	        int alt29=2;
                	        int LA29_0 = input.LA(1);

                	        if ( ((LA29_0>=NULL && LA29_0<=FALSE)||LA29_0==DELETE||LA29_0==FUNCTION||(LA29_0>=IN && LA29_0<=NEW)||LA29_0==THIS||LA29_0==TYPEOF||LA29_0==VOID||(LA29_0>=LT && LA29_0<=QUE)||(LA29_0>=ASSIGN && LA29_0<=DIVASS)||LA29_0==ARRAY||(LA29_0>=BYFIELD && LA29_0<=CALL)||(LA29_0>=NEG && LA29_0<=OBJECT)||(LA29_0>=PDEC && LA29_0<=POS)||(LA29_0>=Identifier && LA29_0<=StringLiteral)||LA29_0==RegularExpressionLiteral||(LA29_0>=DecimalLiteral && LA29_0<=HexIntegerLiteral)) ) {
                	            alt29=1;
                	        }
                	        switch (alt29) {
                	            case 1 :
                	                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:259:23: expr
                	                {
                	                pushFollow(FOLLOW_expr_in_arrayLiteral1496);
                	                expr();

                	                state._fsp--;


                	                }
                	                break;

                	        }


                	        match(input, Token.UP, null); 
                	    }

                	    }
                	    break;

                	default :
                	    break loop30;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "arrayLiteral"


    // $ANTLR start "objectLiteral"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:262:1: objectLiteral : ^( OBJECT ( ^( NAMEDVALUE propertyName expr ) )* ) ;
    public final void objectLiteral() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:263:2: ( ^( OBJECT ( ^( NAMEDVALUE propertyName expr ) )* ) )
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:263:4: ^( OBJECT ( ^( NAMEDVALUE propertyName expr ) )* )
            {
            match(input,OBJECT,FOLLOW_OBJECT_in_objectLiteral1517); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:263:14: ( ^( NAMEDVALUE propertyName expr ) )*
                loop31:
                do {
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==NAMEDVALUE) ) {
                        alt31=1;
                    }


                    switch (alt31) {
                	case 1 :
                	    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:263:16: ^( NAMEDVALUE propertyName expr )
                	    {
                	    match(input,NAMEDVALUE,FOLLOW_NAMEDVALUE_in_objectLiteral1523); 

                	    match(input, Token.DOWN, null); 
                	    pushFollow(FOLLOW_propertyName_in_objectLiteral1525);
                	    propertyName();

                	    state._fsp--;

                	    pushFollow(FOLLOW_expr_in_objectLiteral1527);
                	    expr();

                	    state._fsp--;


                	    match(input, Token.UP, null); 

                	    }
                	    break;

                	default :
                	    break loop31;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "objectLiteral"


    // $ANTLR start "propertyName"
    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:266:1: propertyName : ( Identifier | StringLiteral | numericLiteral );
    public final void propertyName() throws RecognitionException {
        try {
            // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:267:2: ( Identifier | StringLiteral | numericLiteral )
            int alt32=3;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                alt32=1;
                }
                break;
            case StringLiteral:
                {
                alt32=2;
                }
                break;
            case DecimalLiteral:
            case OctalIntegerLiteral:
            case HexIntegerLiteral:
                {
                alt32=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }

            switch (alt32) {
                case 1 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:267:4: Identifier
                    {
                    match(input,Identifier,FOLLOW_Identifier_in_propertyName1545); 

                    }
                    break;
                case 2 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:268:4: StringLiteral
                    {
                    match(input,StringLiteral,FOLLOW_StringLiteral_in_propertyName1550); 

                    }
                    break;
                case 3 :
                    // /Users/cfregin/development/IntentionalIDE/eclipse_workspace/ParserCore/old grammars/ES3Walker.g:269:4: numericLiteral
                    {
                    pushFollow(FOLLOW_numericLiteral_in_propertyName1555);
                    numericLiteral();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "propertyName"

    // Delegated rules


 

    public static final BitSet FOLLOW_statement_in_program43 = new BitSet(new long[]{0x00000000FFFF34F2L,0xB43F7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_block_in_statement55 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclaration_in_statement60 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_statement65 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_statement70 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doStatement_in_statement75 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStatement_in_statement80 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStatement_in_statement85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continueStatement_in_statement90 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_breakStatement_in_statement95 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_returnStatement_in_statement100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_withStatement_in_statement105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_labelledStatement_in_statement110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_switchStatement_in_statement115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_throwStatement_in_statement120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tryStatement_in_statement125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BLOCK_in_block138 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_block140 = new BitSet(new long[]{0x00000000FFFF34F8L,0xB43F7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_VAR_in_variableDeclaration156 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration160 = new BitSet(new long[]{0x0000000000000008L,0x0000000400000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_ASSIGN_in_variableDeclaration166 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration168 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_variableDeclaration170 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_ifStatement190 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_ifStatement192 = new BitSet(new long[]{0x00000000FFFF34F8L,0xB43F7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_statement_in_ifStatement194 = new BitSet(new long[]{0x00000000FFFF34F8L,0xB43F7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_DO_in_doStatement210 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_doStatement212 = new BitSet(new long[]{0x00000000293A1070L,0xB03D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expression_in_doStatement214 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_WHILE_in_whileStatement229 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_whileStatement231 = new BitSet(new long[]{0x00000000FFFF34F8L,0xB43F7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_statement_in_whileStatement233 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FOR_in_forStatement249 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_FORSTEP_in_forStatement259 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprOptClause_in_forStatement263 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_variableDeclaration_in_forStatement267 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_exprOptClause_in_forStatement271 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_exprOptClause_in_forStatement273 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FORITER_in_forStatement283 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_exprClause_in_forStatement287 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_variableDeclaration_in_forStatement291 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_exprClause_in_forStatement295 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_statement_in_forStatement303 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXPR_in_exprOptClause317 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprOptClause319 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXPR_in_exprClause335 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_exprClause337 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CONTINUE_in_continueStatement352 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_continueStatement354 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BREAK_in_breakStatement370 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_breakStatement372 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RETURN_in_returnStatement388 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_returnStatement390 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_WITH_in_withStatement406 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_withStatement408 = new BitSet(new long[]{0x00000000FFFF34F8L,0xB43F7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_statement_in_withStatement410 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LABELLED_in_labelledStatement425 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_labelledStatement427 = new BitSet(new long[]{0x00000000FFFF34F8L,0xB43F7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_statement_in_labelledStatement429 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SWITCH_in_switchStatement444 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_switchStatement446 = new BitSet(new long[]{0x0000000000000908L});
    public static final BitSet FOLLOW_defaultClause_in_switchStatement448 = new BitSet(new long[]{0x0000000000000108L});
    public static final BitSet FOLLOW_caseClause_in_switchStatement451 = new BitSet(new long[]{0x0000000000000108L});
    public static final BitSet FOLLOW_DEFAULT_in_defaultClause467 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_defaultClause469 = new BitSet(new long[]{0x00000000FFFF34F8L,0xB43F7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_CASE_in_caseClause485 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_caseClause487 = new BitSet(new long[]{0x00000000FFFF34F8L,0xB43F7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_statement_in_caseClause489 = new BitSet(new long[]{0x00000000FFFF34F8L,0xB43F7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_THROW_in_throwStatement505 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_throwStatement507 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TRY_in_tryStatement522 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_tryStatement524 = new BitSet(new long[]{0x0000000000008208L});
    public static final BitSet FOLLOW_catchClause_in_tryStatement526 = new BitSet(new long[]{0x0000000000008008L});
    public static final BitSet FOLLOW_finallyClause_in_tryStatement529 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CATCH_in_catchClause546 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_catchClause548 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
    public static final BitSet FOLLOW_block_in_catchClause550 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FINALLY_in_finallyClause566 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_finallyClause568 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_expr_in_expression581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CEXPR_in_expression589 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expression591 = new BitSet(new long[]{0x00000000293A1078L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_leftHandSideExpression_in_expr605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_expr616 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr618 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr620 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MULASS_in_expr629 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr631 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr633 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DIVASS_in_expr642 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr644 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr646 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MODASS_in_expr655 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr657 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr659 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ADDASS_in_expr668 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr670 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr672 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SUBASS_in_expr681 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr683 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr685 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SHLASS_in_expr694 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr696 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr698 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SHRASS_in_expr707 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr709 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr711 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SHUASS_in_expr720 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr722 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr724 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ANDASS_in_expr733 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr735 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr737 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_XORASS_in_expr746 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr748 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr750 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ORASS_in_expr759 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr761 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr763 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUE_in_expr776 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr778 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr780 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr782 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOR_in_expr795 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr797 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr799 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LAND_in_expr808 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr810 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr812 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AND_in_expr825 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr827 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr829 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OR_in_expr838 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr840 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr842 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_XOR_in_expr851 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr853 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr855 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EQ_in_expr868 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr870 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr872 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEQ_in_expr881 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr883 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr885 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SAME_in_expr894 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr896 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr898 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NSAME_in_expr907 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr909 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr911 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_expr924 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr926 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr928 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GT_in_expr937 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr939 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr941 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LTE_in_expr950 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr952 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr954 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GTE_in_expr963 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr965 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr967 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INSTANCEOF_in_expr976 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr978 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr980 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IN_in_expr989 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr991 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr993 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SHL_in_expr1006 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1008 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr1010 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SHR_in_expr1019 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1021 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr1023 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SHU_in_expr1032 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1034 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr1036 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ADD_in_expr1049 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1051 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr1053 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SUB_in_expr1062 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1064 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr1066 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MUL_in_expr1079 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1081 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr1083 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DIV_in_expr1092 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1094 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr1096 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MOD_in_expr1105 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1107 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_expr1109 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DELETE_in_expr1122 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1124 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_VOID_in_expr1133 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1135 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TYPEOF_in_expr1144 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1146 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INC_in_expr1155 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1157 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DEC_in_expr1166 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1168 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_POS_in_expr1177 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1179 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEG_in_expr1188 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1190 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INV_in_expr1199 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1201 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOT_in_expr1210 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1212 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PINC_in_expr1225 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1227 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PDEC_in_expr1236 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1238 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_primaryExpression_in_leftHandSideExpression1251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_newExpression_in_leftHandSideExpression1256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionDeclaration_in_leftHandSideExpression1261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_callExpression_in_leftHandSideExpression1266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberExpression_in_leftHandSideExpression1271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_newExpression1284 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_leftHandSideExpression_in_newExpression1286 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNCTION_in_functionDeclaration1301 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_functionDeclaration1303 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
    public static final BitSet FOLLOW_ARGS_in_functionDeclaration1308 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Identifier_in_functionDeclaration1310 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_block_in_functionDeclaration1315 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CALL_in_callExpression1330 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_leftHandSideExpression_in_callExpression1332 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
    public static final BitSet FOLLOW_ARGS_in_callExpression1336 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_callExpression1338 = new BitSet(new long[]{0x00000000293A1078L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_BYINDEX_in_memberExpression1357 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_leftHandSideExpression_in_memberExpression1359 = new BitSet(new long[]{0x00000000293A1070L,0xB03D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expression_in_memberExpression1361 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BYFIELD_in_memberExpression1370 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_leftHandSideExpression_in_memberExpression1372 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_Identifier_in_memberExpression1374 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Identifier_in_primaryExpression1387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_primaryExpression1392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THIS_in_literal1403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_literal1408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanLiteral_in_literal1413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numericLiteral_in_literal1418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringLiteral_in_literal1423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RegularExpressionLiteral_in_literal1428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayLiteral_in_literal1433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_objectLiteral_in_literal1438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_booleanLiteral0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_numericLiteral0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ARRAY_in_arrayLiteral1488 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ITEM_in_arrayLiteral1494 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_arrayLiteral1496 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OBJECT_in_objectLiteral1517 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NAMEDVALUE_in_objectLiteral1523 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_propertyName_in_objectLiteral1525 = new BitSet(new long[]{0x00000000293A1070L,0xB01D7FFDFFFFFF00L,0x0000000388300003L});
    public static final BitSet FOLLOW_expr_in_objectLiteral1527 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Identifier_in_propertyName1545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringLiteral_in_propertyName1550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numericLiteral_in_propertyName1555 = new BitSet(new long[]{0x0000000000000002L});

}