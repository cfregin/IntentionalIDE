/** \file
 *  This C header file was generated by $ANTLR version 3.2 Sep 23, 2009 12:02:23
 *
 *     -  From the grammar source file : JavaScript.g
 *     -                            On : 2010-03-29 23:48:00
 *     -                 for the lexer : JavaScriptLexerLexer *
 * Editing it, at least manually, is not wise. 
 *
 * C language generator and runtime by Jim Idle, jimi|hereisanat|idle|dotgoeshere|ws.
 *
 *
 * The lexer JavaScriptLexer has the callable functions (rules) shown below,
 * which will invoke the code for the associated rule in the source grammar
 * assuming that the input stream is pointing to a token/text stream that could begin
 * this rule.
 * 
 * For instance if you call the first (topmost) rule in a parser grammar, you will
 * get the results of a full parse, but calling a rule half way through the grammar will
 * allow you to pass part of a full token stream to the parser, such as for syntax checking
 * in editors and so on.
 *
 * The parser entry points are called indirectly (by function pointer to function) via
 * a parser context typedef pJavaScriptLexer, which is returned from a call to JavaScriptLexerNew().
 *
 * As this is a generated lexer, it is unlikely you will call it 'manually'. However
 * the methods are provided anyway.
 * * The methods in pJavaScriptLexer are  as follows:
 *
 *  -  void      pJavaScriptLexer->T__31(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__32(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__33(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__34(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__35(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__36(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__37(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__38(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__39(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__40(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__41(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__42(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__43(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__44(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__45(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__46(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__47(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__48(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__49(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__50(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__51(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__52(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__53(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__54(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__55(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__56(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__57(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__58(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__59(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__60(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__61(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__62(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__63(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__64(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__65(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__66(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__67(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__68(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__69(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__70(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__71(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__72(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__73(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__74(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__75(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__76(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__77(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__78(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__79(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__80(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__81(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__82(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__83(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__84(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__85(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__86(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__87(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__88(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__89(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__90(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__91(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__92(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__93(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__94(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__95(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__96(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__97(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__98(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__99(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__100(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__101(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__102(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__103(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__104(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__105(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->T__106(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->StringLiteral(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->DoubleStringCharacter(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->SingleStringCharacter(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->EscapeSequence(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->CharacterEscapeSequence(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->NonEscapeCharacter(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->SingleEscapeCharacter(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->EscapeCharacter(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->HexEscapeSequence(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->UnicodeEscapeSequence(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->NumericLiteral(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->HexIntegerLiteral(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->HexDigit(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->DecimalLiteral(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->DecimalDigit(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->ExponentPart(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->Identifier(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->IdentifierStart(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->IdentifierPart(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->UnicodeLetter(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->UnicodeCombiningMark(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->UnicodeDigit(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->UnicodeConnectorPunctuation(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->Comment(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->LineComment(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->DLT(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->WhiteSpace(pJavaScriptLexer)
 *  -  void      pJavaScriptLexer->Tokens(pJavaScriptLexer)
 *
 * The return type for any particular rule is of course determined by the source
 * grammar file.
 */
// [The "BSD licence"]
// Copyright (c) 2005-2009 Jim Idle, Temporal Wave LLC
// http://www.temporal-wave.com
// http://www.linkedin.com/in/jimidle
//
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
// 1. Redistributions of source code must retain the above copyright
//    notice, this list of conditions and the following disclaimer.
// 2. Redistributions in binary form must reproduce the above copyright
//    notice, this list of conditions and the following disclaimer in the
//    documentation and/or other materials provided with the distribution.
// 3. The name of the author may not be used to endorse or promote products
//    derived from this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
// IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
// OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
// IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
// INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
// NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
// THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

#ifndef	_JavaScriptLexer_H
#define _JavaScriptLexer_H
/* =============================================================================
 * Standard antlr3 C runtime definitions
 */
#include    <antlr3.h>

/* End of standard antlr 3 runtime definitions
 * =============================================================================
 */
 
#ifdef __cplusplus
extern "C" {
#endif

// Forward declare the context typedef so that we can use it before it is
// properly defined. Delegators and delegates (from import statements) are
// interdependent and their context structures contain pointers to each other
// C only allows such things to be declared if you pre-declare the typedef.
//
typedef struct JavaScriptLexer_Ctx_struct JavaScriptLexer, * pJavaScriptLexer;



#ifdef	ANTLR3_WINDOWS
// Disable: Unreferenced parameter,							- Rules with parameters that are not used
//          constant conditional,							- ANTLR realizes that a prediction is always true (synpred usually)
//          initialized but unused variable					- tree rewrite variables declared but not needed
//          Unreferenced local variable						- lexer rule declares but does not always use _type
//          potentially unitialized variable used			- retval always returned from a rule 
//			unreferenced local function has been removed	- susually getTokenNames or freeScope, they can go without warnigns
//
// These are only really displayed at warning level /W4 but that is the code ideal I am aiming at
// and the codegen must generate some of these warnings by necessity, apart from 4100, which is
// usually generated when a parser rule is given a parameter that it does not use. Mostly though
// this is a matter of orthogonality hence I disable that one.
//
#pragma warning( disable : 4100 )
#pragma warning( disable : 4101 )
#pragma warning( disable : 4127 )
#pragma warning( disable : 4189 )
#pragma warning( disable : 4505 )
#pragma warning( disable : 4701 )
#endif

/* ========================
 * BACKTRACKING IS ENABLED
 * ========================
 */

/** Context tracking structure for JavaScriptLexer
 */
struct JavaScriptLexer_Ctx_struct
{
    /** Built in ANTLR3 context tracker contains all the generic elements
     *  required for context tracking.
     */
    pANTLR3_LEXER    pLexer;


     void (*mT__31)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__32)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__33)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__34)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__35)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__36)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__37)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__38)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__39)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__40)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__41)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__42)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__43)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__44)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__45)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__46)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__47)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__48)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__49)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__50)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__51)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__52)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__53)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__54)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__55)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__56)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__57)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__58)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__59)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__60)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__61)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__62)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__63)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__64)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__65)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__66)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__67)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__68)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__69)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__70)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__71)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__72)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__73)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__74)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__75)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__76)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__77)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__78)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__79)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__80)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__81)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__82)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__83)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__84)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__85)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__86)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__87)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__88)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__89)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__90)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__91)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__92)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__93)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__94)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__95)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__96)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__97)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__98)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__99)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__100)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__101)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__102)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__103)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__104)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__105)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mT__106)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mStringLiteral)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mDoubleStringCharacter)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mSingleStringCharacter)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mEscapeSequence)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mCharacterEscapeSequence)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mNonEscapeCharacter)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mSingleEscapeCharacter)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mEscapeCharacter)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mHexEscapeSequence)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mUnicodeEscapeSequence)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mNumericLiteral)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mHexIntegerLiteral)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mHexDigit)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mDecimalLiteral)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mDecimalDigit)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mExponentPart)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mIdentifier)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mIdentifierStart)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mIdentifierPart)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mUnicodeLetter)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mUnicodeCombiningMark)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mUnicodeDigit)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mUnicodeConnectorPunctuation)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mComment)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mLineComment)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mDLT)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mWhiteSpace)	(struct JavaScriptLexer_Ctx_struct * ctx);
     void (*mTokens)	(struct JavaScriptLexer_Ctx_struct * ctx);    const char * (*getGrammarFileName)();
    void	    (*free)   (struct JavaScriptLexer_Ctx_struct * ctx);
        
};

// Function protoypes for the constructor functions that external translation units
// such as delegators and delegates may wish to call.
//
ANTLR3_API pJavaScriptLexer JavaScriptLexerNew         (pANTLR3_INPUT_STREAM instream);
ANTLR3_API pJavaScriptLexer JavaScriptLexerNewSSD      (pANTLR3_INPUT_STREAM instream, pANTLR3_RECOGNIZER_SHARED_STATE state);

/** Symbolic definitions of all the tokens that the lexer will work with.
 * \{
 *
 * Antlr will define EOF, but we can't use that as it it is too common in
 * in C header files and that would be confusing. There is no way to filter this out at the moment
 * so we just undef it here for now. That isn't the value we get back from C recognizers
 * anyway. We are looking for ANTLR3_TOKEN_EOF.
 */
#ifdef	EOF
#undef	EOF
#endif
#ifdef	Tokens
#undef	Tokens
#endif 
#define DecimalDigit      17
#define EOF      -1
#define Identifier      5
#define SingleStringCharacter      9
#define T__93      93
#define T__94      94
#define T__91      91
#define T__92      92
#define T__90      90
#define Comment      28
#define SingleEscapeCharacter      14
#define UnicodeLetter      24
#define ExponentPart      21
#define WhiteSpace      30
#define T__99      99
#define T__98      98
#define T__97      97
#define T__96      96
#define T__95      95
#define UnicodeCombiningMark      27
#define DLT      4
#define UnicodeDigit      25
#define T__80      80
#define NumericLiteral      7
#define T__81      81
#define T__82      82
#define T__83      83
#define IdentifierStart      22
#define DoubleStringCharacter      8
#define T__85      85
#define T__84      84
#define T__87      87
#define T__86      86
#define T__89      89
#define T__88      88
#define T__71      71
#define T__72      72
#define T__70      70
#define CharacterEscapeSequence      11
#define T__76      76
#define T__75      75
#define T__74      74
#define T__73      73
#define EscapeSequence      10
#define T__79      79
#define UnicodeConnectorPunctuation      26
#define T__78      78
#define T__77      77
#define T__68      68
#define T__69      69
#define T__66      66
#define T__67      67
#define T__64      64
#define T__65      65
#define T__62      62
#define T__63      63
#define HexEscapeSequence      12
#define LineComment      29
#define T__61      61
#define T__60      60
#define HexDigit      18
#define T__55      55
#define T__56      56
#define T__57      57
#define T__58      58
#define T__51      51
#define T__52      52
#define T__53      53
#define T__54      54
#define T__59      59
#define T__103      103
#define T__104      104
#define T__105      105
#define T__106      106
#define EscapeCharacter      16
#define T__50      50
#define IdentifierPart      23
#define T__42      42
#define T__43      43
#define T__40      40
#define T__41      41
#define T__46      46
#define T__47      47
#define T__44      44
#define T__45      45
#define T__48      48
#define T__49      49
#define UnicodeEscapeSequence      13
#define T__102      102
#define T__101      101
#define T__100      100
#define DecimalLiteral      19
#define StringLiteral      6
#define T__31      31
#define T__32      32
#define T__33      33
#define T__34      34
#define T__35      35
#define T__36      36
#define T__37      37
#define T__38      38
#define T__39      39
#define HexIntegerLiteral      20
#define NonEscapeCharacter      15
#ifdef	EOF
#undef	EOF
#define	EOF	ANTLR3_TOKEN_EOF
#endif

#ifndef TOKENSOURCE
#define TOKENSOURCE(lxr) lxr->pLexer->rec->state->tokSource
#endif

/* End of token definitions for JavaScriptLexer
 * =============================================================================
 */
/** \} */

#ifdef __cplusplus
}
#endif

#endif

/* END - Note:Keep extra line feed to satisfy UNIX systems */
