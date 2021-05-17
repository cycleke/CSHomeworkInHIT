/* A Bison parser, made by GNU Bison 3.7.6.  */

/* Skeleton implementation for Bison LALR(1) parsers in Java

   Copyright (C) 2007-2015, 2018-2021 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <https://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* DO NOT RELY ON FEATURES THAT ARE NOT DOCUMENTED in the manual,
   especially those whose name start with YY_ or yy_.  They are
   private implementation details that can be changed or removed.  */

package pers.cycleke.compiler;



import java.text.MessageFormat;
/* "%code imports" blocks.  */
/* "grammar.y":7  */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* "grammar.java":50  */

/**
 * A Bison parser, automatically generated from <tt>grammar.y</tt>.
 *
 * @author LALR (1) parser skeleton written by Paolo Bonzini.
 */
public class Grammar
{
  /** Version number for the Bison executable that generated this parser.  */
  public static final String bisonVersion = "3.7.6";

  /** Name of the skeleton that generated this parser.  */
  public static final String bisonSkeleton = "lalr1.java";



  /**
   * True if verbose error messages are enabled.
   */
  private boolean yyErrorVerbose = true;

  /**
   * Whether verbose error messages are enabled.
   */
  public final boolean getErrorVerbose() { return yyErrorVerbose; }

  /**
   * Set the verbosity of error messages.
   * @param verbose True to request verbose error messages.
   */
  public final void setErrorVerbose(boolean verbose)
  { yyErrorVerbose = verbose; }




  public enum SymbolKind
  {
    S_YYEOF(0),                    /* "end of file"  */
    S_YYerror(1),                  /* error  */
    S_YYUNDEF(2),                  /* "invalid token"  */
    S_CONST_INTEGER(3),            /* CONST_INTEGER  */
    S_CONST_FLOAT(4),              /* CONST_FLOAT  */
    S_CONST_STRING(5),             /* CONST_STRING  */
    S_CONST_CHARACTER(6),          /* CONST_CHARACTER  */
    S_IDN(7),                      /* IDN  */
    S_STRUCT(8),                   /* STRUCT  */
    S_LEFT_BRACE(9),               /* LEFT_BRACE  */
    S_LEFT_PAREN(10),              /* LEFT_PAREN  */
    S_LEFT_SQUARE(11),             /* LEFT_SQUARE  */
    S_RIGHT_BRACE(12),             /* RIGHT_BRACE  */
    S_RIGHT_PAREN(13),             /* RIGHT_PAREN  */
    S_RIGHT_SQUARE(14),            /* RIGHT_SQUARE  */
    S_CHAR(15),                    /* CHAR  */
    S_SHORT(16),                   /* SHORT  */
    S_INT(17),                     /* INT  */
    S_LONG(18),                    /* LONG  */
    S_SIGNED(19),                  /* SIGNED  */
    S_UNSIGNED(20),                /* UNSIGNED  */
    S_FLOAT(21),                   /* FLOAT  */
    S_DOUBLE(22),                  /* DOUBLE  */
    S_VOID(23),                    /* VOID  */
    S_IF(24),                      /* IF  */
    S_ELSE(25),                    /* ELSE  */
    S_WHILE(26),                   /* WHILE  */
    S_RETURN(27),                  /* RETURN  */
    S_COMMA(28),                   /* COMMA  */
    S_DOT(29),                     /* DOT  */
    S_SEMICOLON(30),               /* SEMICOLON  */
    S_ASSIGN(31),                  /* ASSIGN  */
    S_OR(32),                      /* OR  */
    S_AND(33),                     /* AND  */
    S_VERT_LINE(34),               /* VERT_LINE  */
    S_CARET(35),                   /* CARET  */
    S_AMPERSAND(36),               /* AMPERSAND  */
    S_EQ(37),                      /* EQ  */
    S_NE(38),                      /* NE  */
    S_LE(39),                      /* LE  */
    S_GE(40),                      /* GE  */
    S_LT(41),                      /* LT  */
    S_GT(42),                      /* GT  */
    S_PLUS(43),                    /* PLUS  */
    S_MINUS(44),                   /* MINUS  */
    S_STAR(45),                    /* STAR  */
    S_SLASH(46),                   /* SLASH  */
    S_PERCENT(47),                 /* PERCENT  */
    S_GET_ADDR(48),                /* GET_ADDR  */
    S_POS(49),                     /* POS  */
    S_NEG(50),                     /* NEG  */
    S_YYACCEPT(51),                /* $accept  */
    S_PROGRAM(52),                 /* PROGRAM  */
    S_TOP_STATEMENTS(53),          /* TOP_STATEMENTS  */
    S_STATEMENTS_BLOCK(54),        /* STATEMENTS_BLOCK  */
    S_STATEMENTS(55),              /* STATEMENTS  */
    S_LEFT_EXPRESSION(56),         /* LEFT_EXPRESSION  */
    S_LEFT_EXPRESSION_EXPAND(57),  /* LEFT_EXPRESSION_EXPAND  */
    S_NUMBER(58),                  /* NUMBER  */
    S_RIGHT_EXPRESSION(59),        /* RIGHT_EXPRESSION  */
    S_STATEMENT(60),               /* STATEMENT  */
    S_STATEMENT_ASSIGN(61),        /* STATEMENT_ASSIGN  */
    S_STATEMENT_IF(62),            /* STATEMENT_IF  */
    S_STATEMENT_ELSE(63),          /* STATEMENT_ELSE  */
    S_STATEMENT_WHILE(64),         /* STATEMENT_WHILE  */
    S_STATEMENT_RETURN(65),        /* STATEMENT_RETURN  */
    S_STRUCT_TYPE(66),             /* STRUCT_TYPE  */
    S_VAR_TYPE(67),                /* VAR_TYPE  */
    S_STATEMENT_VAR_DEF(68),       /* STATEMENT_VAR_DEF  */
    S_STATEMENT_VAR_DEF_EXPAND(69), /* STATEMENT_VAR_DEF_EXPAND  */
    S_INITIALIZE_DEC(70),          /* INITIALIZE_DEC  */
    S_ARRAY_SUFFIX(71),            /* ARRAY_SUFFIX  */
    S_NON_INITIALIZE_DEC(72),      /* NON_INITIALIZE_DEC  */
    S_STATEMENT_STRUCT_DEF(73),    /* STATEMENT_STRUCT_DEF  */
    S_STRUCT_VAR_DEF(74),          /* STRUCT_VAR_DEF  */
    S_NON_INITIALIZE_DEC_EXPAND(75), /* NON_INITIALIZE_DEC_EXPAND  */
    S_FUNC_CALL(76),               /* FUNC_CALL  */
    S_SEND_ARGS_EXPAND(77),        /* SEND_ARGS_EXPAND  */
    S_STATEMENT_FUNC_DEF(78),      /* STATEMENT_FUNC_DEF  */
    S_RECV_ARGS(79),               /* RECV_ARGS  */
    S_RECV_ARGS_EXPAND(80);        /* RECV_ARGS_EXPAND  */


    private final int yycode_;

    SymbolKind (int n) {
      this.yycode_ = n;
    }

    private static final SymbolKind[] values_ = {
      SymbolKind.S_YYEOF,
      SymbolKind.S_YYerror,
      SymbolKind.S_YYUNDEF,
      SymbolKind.S_CONST_INTEGER,
      SymbolKind.S_CONST_FLOAT,
      SymbolKind.S_CONST_STRING,
      SymbolKind.S_CONST_CHARACTER,
      SymbolKind.S_IDN,
      SymbolKind.S_STRUCT,
      SymbolKind.S_LEFT_BRACE,
      SymbolKind.S_LEFT_PAREN,
      SymbolKind.S_LEFT_SQUARE,
      SymbolKind.S_RIGHT_BRACE,
      SymbolKind.S_RIGHT_PAREN,
      SymbolKind.S_RIGHT_SQUARE,
      SymbolKind.S_CHAR,
      SymbolKind.S_SHORT,
      SymbolKind.S_INT,
      SymbolKind.S_LONG,
      SymbolKind.S_SIGNED,
      SymbolKind.S_UNSIGNED,
      SymbolKind.S_FLOAT,
      SymbolKind.S_DOUBLE,
      SymbolKind.S_VOID,
      SymbolKind.S_IF,
      SymbolKind.S_ELSE,
      SymbolKind.S_WHILE,
      SymbolKind.S_RETURN,
      SymbolKind.S_COMMA,
      SymbolKind.S_DOT,
      SymbolKind.S_SEMICOLON,
      SymbolKind.S_ASSIGN,
      SymbolKind.S_OR,
      SymbolKind.S_AND,
      SymbolKind.S_VERT_LINE,
      SymbolKind.S_CARET,
      SymbolKind.S_AMPERSAND,
      SymbolKind.S_EQ,
      SymbolKind.S_NE,
      SymbolKind.S_LE,
      SymbolKind.S_GE,
      SymbolKind.S_LT,
      SymbolKind.S_GT,
      SymbolKind.S_PLUS,
      SymbolKind.S_MINUS,
      SymbolKind.S_STAR,
      SymbolKind.S_SLASH,
      SymbolKind.S_PERCENT,
      SymbolKind.S_GET_ADDR,
      SymbolKind.S_POS,
      SymbolKind.S_NEG,
      SymbolKind.S_YYACCEPT,
      SymbolKind.S_PROGRAM,
      SymbolKind.S_TOP_STATEMENTS,
      SymbolKind.S_STATEMENTS_BLOCK,
      SymbolKind.S_STATEMENTS,
      SymbolKind.S_LEFT_EXPRESSION,
      SymbolKind.S_LEFT_EXPRESSION_EXPAND,
      SymbolKind.S_NUMBER,
      SymbolKind.S_RIGHT_EXPRESSION,
      SymbolKind.S_STATEMENT,
      SymbolKind.S_STATEMENT_ASSIGN,
      SymbolKind.S_STATEMENT_IF,
      SymbolKind.S_STATEMENT_ELSE,
      SymbolKind.S_STATEMENT_WHILE,
      SymbolKind.S_STATEMENT_RETURN,
      SymbolKind.S_STRUCT_TYPE,
      SymbolKind.S_VAR_TYPE,
      SymbolKind.S_STATEMENT_VAR_DEF,
      SymbolKind.S_STATEMENT_VAR_DEF_EXPAND,
      SymbolKind.S_INITIALIZE_DEC,
      SymbolKind.S_ARRAY_SUFFIX,
      SymbolKind.S_NON_INITIALIZE_DEC,
      SymbolKind.S_STATEMENT_STRUCT_DEF,
      SymbolKind.S_STRUCT_VAR_DEF,
      SymbolKind.S_NON_INITIALIZE_DEC_EXPAND,
      SymbolKind.S_FUNC_CALL,
      SymbolKind.S_SEND_ARGS_EXPAND,
      SymbolKind.S_STATEMENT_FUNC_DEF,
      SymbolKind.S_RECV_ARGS,
      SymbolKind.S_RECV_ARGS_EXPAND
    };

    static final SymbolKind get(int code) {
      return values_[code];
    }

    public final int getCode() {
      return this.yycode_;
    }

    /* Return YYSTR after stripping away unnecessary quotes and
       backslashes, so that it's suitable for yyerror.  The heuristic is
       that double-quoting is unnecessary unless the string contains an
       apostrophe, a comma, or backslash (other than backslash-backslash).
       YYSTR is taken from yytname.  */
    private static String yytnamerr_(String yystr)
    {
      if (yystr.charAt (0) == '"')
        {
          StringBuffer yyr = new StringBuffer();
          strip_quotes: for (int i = 1; i < yystr.length(); i++)
            switch (yystr.charAt(i))
              {
              case '\'':
              case ',':
                break strip_quotes;

              case '\\':
                if (yystr.charAt(++i) != '\\')
                  break strip_quotes;
                /* Fall through.  */
              default:
                yyr.append(yystr.charAt(i));
                break;

              case '"':
                return yyr.toString();
              }
        }
      return yystr;
    }

    /* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
       First, the terminals, then, starting at \a YYNTOKENS_, nonterminals.  */
    private static final String[] yytname_ = yytname_init();
  private static final String[] yytname_init()
  {
    return new String[]
    {
  "\"end of file\"", "error", "\"invalid token\"", "CONST_INTEGER",
  "CONST_FLOAT", "CONST_STRING", "CONST_CHARACTER", "IDN", "STRUCT",
  "LEFT_BRACE", "LEFT_PAREN", "LEFT_SQUARE", "RIGHT_BRACE", "RIGHT_PAREN",
  "RIGHT_SQUARE", "CHAR", "SHORT", "INT", "LONG", "SIGNED", "UNSIGNED",
  "FLOAT", "DOUBLE", "VOID", "IF", "ELSE", "WHILE", "RETURN", "COMMA",
  "DOT", "SEMICOLON", "ASSIGN", "OR", "AND", "VERT_LINE", "CARET",
  "AMPERSAND", "EQ", "NE", "LE", "GE", "LT", "GT", "PLUS", "MINUS", "STAR",
  "SLASH", "PERCENT", "GET_ADDR", "POS", "NEG", "$accept", "PROGRAM",
  "TOP_STATEMENTS", "STATEMENTS_BLOCK", "STATEMENTS", "LEFT_EXPRESSION",
  "LEFT_EXPRESSION_EXPAND", "NUMBER", "RIGHT_EXPRESSION", "STATEMENT",
  "STATEMENT_ASSIGN", "STATEMENT_IF", "STATEMENT_ELSE", "STATEMENT_WHILE",
  "STATEMENT_RETURN", "STRUCT_TYPE", "VAR_TYPE", "STATEMENT_VAR_DEF",
  "STATEMENT_VAR_DEF_EXPAND", "INITIALIZE_DEC", "ARRAY_SUFFIX",
  "NON_INITIALIZE_DEC", "STATEMENT_STRUCT_DEF", "STRUCT_VAR_DEF",
  "NON_INITIALIZE_DEC_EXPAND", "FUNC_CALL", "SEND_ARGS_EXPAND",
  "STATEMENT_FUNC_DEF", "RECV_ARGS", "RECV_ARGS_EXPAND", null
    };
  }

    /* The user-facing name of this symbol.  */
    public final String getName() {
      return yytnamerr_(yytname_[yycode_]);
    }

  };


  /**
   * Communication interface between the scanner and the Bison-generated
   * parser <tt>Grammar</tt>.
   */
  public interface Lexer {
    /* Token kinds.  */
    /** Token "end of file", to be returned by the scanner.  */
    static final int YYEOF = 0;
    /** Token error, to be returned by the scanner.  */
    static final int YYerror = 256;
    /** Token "invalid token", to be returned by the scanner.  */
    static final int YYUNDEF = 257;
    /** Token CONST_INTEGER, to be returned by the scanner.  */
    static final int CONST_INTEGER = 258;
    /** Token CONST_FLOAT, to be returned by the scanner.  */
    static final int CONST_FLOAT = 259;
    /** Token CONST_STRING, to be returned by the scanner.  */
    static final int CONST_STRING = 260;
    /** Token CONST_CHARACTER, to be returned by the scanner.  */
    static final int CONST_CHARACTER = 261;
    /** Token IDN, to be returned by the scanner.  */
    static final int IDN = 262;
    /** Token STRUCT, to be returned by the scanner.  */
    static final int STRUCT = 263;
    /** Token LEFT_BRACE, to be returned by the scanner.  */
    static final int LEFT_BRACE = 264;
    /** Token LEFT_PAREN, to be returned by the scanner.  */
    static final int LEFT_PAREN = 265;
    /** Token LEFT_SQUARE, to be returned by the scanner.  */
    static final int LEFT_SQUARE = 266;
    /** Token RIGHT_BRACE, to be returned by the scanner.  */
    static final int RIGHT_BRACE = 267;
    /** Token RIGHT_PAREN, to be returned by the scanner.  */
    static final int RIGHT_PAREN = 268;
    /** Token RIGHT_SQUARE, to be returned by the scanner.  */
    static final int RIGHT_SQUARE = 269;
    /** Token CHAR, to be returned by the scanner.  */
    static final int CHAR = 270;
    /** Token SHORT, to be returned by the scanner.  */
    static final int SHORT = 271;
    /** Token INT, to be returned by the scanner.  */
    static final int INT = 272;
    /** Token LONG, to be returned by the scanner.  */
    static final int LONG = 273;
    /** Token SIGNED, to be returned by the scanner.  */
    static final int SIGNED = 274;
    /** Token UNSIGNED, to be returned by the scanner.  */
    static final int UNSIGNED = 275;
    /** Token FLOAT, to be returned by the scanner.  */
    static final int FLOAT = 276;
    /** Token DOUBLE, to be returned by the scanner.  */
    static final int DOUBLE = 277;
    /** Token VOID, to be returned by the scanner.  */
    static final int VOID = 278;
    /** Token IF, to be returned by the scanner.  */
    static final int IF = 279;
    /** Token ELSE, to be returned by the scanner.  */
    static final int ELSE = 280;
    /** Token WHILE, to be returned by the scanner.  */
    static final int WHILE = 281;
    /** Token RETURN, to be returned by the scanner.  */
    static final int RETURN = 282;
    /** Token COMMA, to be returned by the scanner.  */
    static final int COMMA = 283;
    /** Token DOT, to be returned by the scanner.  */
    static final int DOT = 284;
    /** Token SEMICOLON, to be returned by the scanner.  */
    static final int SEMICOLON = 285;
    /** Token ASSIGN, to be returned by the scanner.  */
    static final int ASSIGN = 286;
    /** Token OR, to be returned by the scanner.  */
    static final int OR = 287;
    /** Token AND, to be returned by the scanner.  */
    static final int AND = 288;
    /** Token VERT_LINE, to be returned by the scanner.  */
    static final int VERT_LINE = 289;
    /** Token CARET, to be returned by the scanner.  */
    static final int CARET = 290;
    /** Token AMPERSAND, to be returned by the scanner.  */
    static final int AMPERSAND = 291;
    /** Token EQ, to be returned by the scanner.  */
    static final int EQ = 292;
    /** Token NE, to be returned by the scanner.  */
    static final int NE = 293;
    /** Token LE, to be returned by the scanner.  */
    static final int LE = 294;
    /** Token GE, to be returned by the scanner.  */
    static final int GE = 295;
    /** Token LT, to be returned by the scanner.  */
    static final int LT = 296;
    /** Token GT, to be returned by the scanner.  */
    static final int GT = 297;
    /** Token PLUS, to be returned by the scanner.  */
    static final int PLUS = 298;
    /** Token MINUS, to be returned by the scanner.  */
    static final int MINUS = 299;
    /** Token STAR, to be returned by the scanner.  */
    static final int STAR = 300;
    /** Token SLASH, to be returned by the scanner.  */
    static final int SLASH = 301;
    /** Token PERCENT, to be returned by the scanner.  */
    static final int PERCENT = 302;
    /** Token GET_ADDR, to be returned by the scanner.  */
    static final int GET_ADDR = 303;
    /** Token POS, to be returned by the scanner.  */
    static final int POS = 304;
    /** Token NEG, to be returned by the scanner.  */
    static final int NEG = 305;

    /** Deprecated, use YYEOF instead.  */
    public static final int EOF = YYEOF;


    /**
     * Method to retrieve the semantic value of the last scanned token.
     * @return the semantic value of the last scanned token.
     */
    Object getLVal();

    /**
     * Entry point for the scanner.  Returns the token identifier corresponding
     * to the next token and prepares to return the semantic value
     * of the token.
     * @return the token identifier corresponding to the next token.
     */
    int yylex() throws java.io.IOException;

    /**
     * Emit an errorin a user-defined way.
     *
     *
     * @param msg The string for the error message.
     */
     void yyerror(String msg);


  }


  /**
   * The object doing lexical analysis for us.
   */
  private Lexer yylexer;





  /**
   * Instantiates the Bison-generated parser.
   * @param yylexer The scanner that will supply tokens to the parser.
   */
  public Grammar (Lexer yylexer)
  {

    this.yylexer = yylexer;

  }



  private int yynerrs = 0;

  /**
   * The number of syntax errors so far.
   */
  public final int getNumberOfErrors () { return yynerrs; }

  /**
   * Print an error message via the lexer.
   *
   * @param msg The error message.
   */
  public final void yyerror(String msg) {
      yylexer.yyerror(msg);
  }



  private final class YYStack {
    private int[] stateStack = new int[16];
    private Object[] valueStack = new Object[16];

    public int size = 16;
    public int height = -1;

    public final void push (int state, Object value) {
      height++;
      if (size == height)
        {
          int[] newStateStack = new int[size * 2];
          System.arraycopy (stateStack, 0, newStateStack, 0, height);
          stateStack = newStateStack;

          Object[] newValueStack = new Object[size * 2];
          System.arraycopy (valueStack, 0, newValueStack, 0, height);
          valueStack = newValueStack;

          size *= 2;
        }

      stateStack[height] = state;
      valueStack[height] = value;
    }

    public final void pop () {
      pop (1);
    }

    public final void pop (int num) {
      // Avoid memory leaks... garbage collection is a white lie!
      if (0 < num) {
        java.util.Arrays.fill (valueStack, height - num + 1, height + 1, null);
      }
      height -= num;
    }

    public final int stateAt (int i) {
      return stateStack[height - i];
    }

    public final Object valueAt (int i) {
      return valueStack[height - i];
    }

    // Print the state stack on the debug stream.
    public void print (java.io.PrintStream out) {
      out.print ("Stack now");

      for (int i = 0; i <= height; i++)
        {
          out.print (' ');
          out.print (stateStack[i]);
        }
      out.println ();
    }
  }

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return success (<tt>true</tt>).
   */
  public static final int YYACCEPT = 0;

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return failure (<tt>false</tt>).
   */
  public static final int YYABORT = 1;



  /**
   * Returned by a Bison action in order to start error recovery without
   * printing an error message.
   */
  public static final int YYERROR = 2;

  /**
   * Internal return codes that are not supported for user semantic
   * actions.
   */
  private static final int YYERRLAB = 3;
  private static final int YYNEWSTATE = 4;
  private static final int YYDEFAULT = 5;
  private static final int YYREDUCE = 6;
  private static final int YYERRLAB1 = 7;
  private static final int YYRETURN = 8;


  private int yyerrstatus_ = 0;


  /**
   * Whether error recovery is being done.  In this state, the parser
   * reads token until it reaches a known state, and then restarts normal
   * operation.
   */
  public final boolean recovering ()
  {
    return yyerrstatus_ == 0;
  }

  /** Compute post-reduction state.
   * @param yystate   the current state
   * @param yysym     the nonterminal to push on the stack
   */
  private int yyLRGotoState (int yystate, int yysym)
  {
    int yyr = yypgoto_[yysym - YYNTOKENS_] + yystate;
    if (0 <= yyr && yyr <= YYLAST_ && yycheck_[yyr] == yystate)
      return yytable_[yyr];
    else
      return yydefgoto_[yysym - YYNTOKENS_];
  }

  private int yyaction(int yyn, YYStack yystack, int yylen)
  {
    /* If YYLEN is nonzero, implement the default value of the action:
       '$$ = $1'.  Otherwise, use the top of the stack.

       Otherwise, the following line sets YYVAL to garbage.
       This behavior is undocumented and Bison
       users should not rely upon it.  */
    Object yyval = (0 < yylen) ? yystack.valueAt(yylen - 1) : yystack.valueAt(0);

    switch (yyn)
      {
          case 2: /* PROGRAM: TOP_STATEMENTS  */
  if (yyn == 2)
    /* "grammar.y":271  */
                   {lexer.addNonTerminalNode(SymbolKind.S_PROGRAM, 1);};
  break;


  case 3: /* TOP_STATEMENTS: STATEMENT_VAR_DEF TOP_STATEMENTS  */
  if (yyn == 3)
    /* "grammar.y":275  */
                                     {lexer.addNonTerminalNode(SymbolKind.S_TOP_STATEMENTS, 2);};
  break;


  case 4: /* TOP_STATEMENTS: STATEMENT_FUNC_DEF TOP_STATEMENTS  */
  if (yyn == 4)
    /* "grammar.y":276  */
                                      {lexer.addNonTerminalNode(SymbolKind.S_TOP_STATEMENTS, 2);};
  break;


  case 5: /* TOP_STATEMENTS: STATEMENT_STRUCT_DEF TOP_STATEMENTS  */
  if (yyn == 5)
    /* "grammar.y":277  */
                                        {lexer.addNonTerminalNode(SymbolKind.S_TOP_STATEMENTS, 2);};
  break;


  case 6: /* TOP_STATEMENTS: %empty  */
  if (yyn == 6)
    /* "grammar.y":278  */
    {lexer.addNonTerminalNode(SymbolKind.S_TOP_STATEMENTS, 0);};
  break;


  case 7: /* STATEMENTS_BLOCK: LEFT_BRACE STATEMENTS RIGHT_BRACE  */
  if (yyn == 7)
    /* "grammar.y":282  */
                                      {lexer.addNonTerminalNode(SymbolKind.S_STATEMENTS_BLOCK, 3);};
  break;


  case 8: /* STATEMENTS_BLOCK: LEFT_BRACE RIGHT_BRACE  */
  if (yyn == 8)
    /* "grammar.y":283  */
                           {lexer.addNonTerminalNode(SymbolKind.S_STATEMENTS_BLOCK, 2);};
  break;


  case 9: /* STATEMENTS: STATEMENT STATEMENTS  */
  if (yyn == 9)
    /* "grammar.y":287  */
                         {lexer.addNonTerminalNode(SymbolKind.S_STATEMENTS, 2);};
  break;


  case 10: /* STATEMENTS: STATEMENT  */
  if (yyn == 10)
    /* "grammar.y":288  */
              {lexer.addNonTerminalNode(SymbolKind.S_STATEMENTS, 1);};
  break;


  case 11: /* LEFT_EXPRESSION: IDN LEFT_EXPRESSION_EXPAND  */
  if (yyn == 11)
    /* "grammar.y":292  */
                               {lexer.addNonTerminalNode(SymbolKind.S_LEFT_EXPRESSION, 2);};
  break;


  case 12: /* LEFT_EXPRESSION_EXPAND: LEFT_SQUARE RIGHT_EXPRESSION RIGHT_SQUARE LEFT_EXPRESSION_EXPAND  */
  if (yyn == 12)
    /* "grammar.y":296  */
                                                                     {lexer.addNonTerminalNode(SymbolKind.S_LEFT_EXPRESSION_EXPAND, 4);};
  break;


  case 13: /* LEFT_EXPRESSION_EXPAND: DOT IDN LEFT_EXPRESSION_EXPAND  */
  if (yyn == 13)
    /* "grammar.y":297  */
                                   {lexer.addNonTerminalNode(SymbolKind.S_LEFT_EXPRESSION_EXPAND, 3);};
  break;


  case 14: /* LEFT_EXPRESSION_EXPAND: %empty  */
  if (yyn == 14)
    /* "grammar.y":298  */
    {lexer.addNonTerminalNode(SymbolKind.S_LEFT_EXPRESSION_EXPAND, 0);};
  break;


  case 15: /* NUMBER: CONST_FLOAT  */
  if (yyn == 15)
    /* "grammar.y":302  */
                {lexer.addNonTerminalNode(SymbolKind.S_NUMBER, 1);};
  break;


  case 16: /* NUMBER: CONST_INTEGER  */
  if (yyn == 16)
    /* "grammar.y":303  */
                  {lexer.addNonTerminalNode(SymbolKind.S_NUMBER, 1);};
  break;


  case 17: /* NUMBER: CONST_CHARACTER  */
  if (yyn == 17)
    /* "grammar.y":304  */
                    {lexer.addNonTerminalNode(SymbolKind.S_NUMBER, 1);};
  break;


  case 18: /* RIGHT_EXPRESSION: RIGHT_EXPRESSION LT RIGHT_EXPRESSION  */
  if (yyn == 18)
    /* "grammar.y":308  */
                                         {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);};
  break;


  case 19: /* RIGHT_EXPRESSION: RIGHT_EXPRESSION LE RIGHT_EXPRESSION  */
  if (yyn == 19)
    /* "grammar.y":309  */
                                         {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);};
  break;


  case 20: /* RIGHT_EXPRESSION: RIGHT_EXPRESSION GT RIGHT_EXPRESSION  */
  if (yyn == 20)
    /* "grammar.y":310  */
                                         {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);};
  break;


  case 21: /* RIGHT_EXPRESSION: RIGHT_EXPRESSION GE RIGHT_EXPRESSION  */
  if (yyn == 21)
    /* "grammar.y":311  */
                                         {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);};
  break;


  case 22: /* RIGHT_EXPRESSION: RIGHT_EXPRESSION EQ RIGHT_EXPRESSION  */
  if (yyn == 22)
    /* "grammar.y":312  */
                                         {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);};
  break;


  case 23: /* RIGHT_EXPRESSION: RIGHT_EXPRESSION NE RIGHT_EXPRESSION  */
  if (yyn == 23)
    /* "grammar.y":313  */
                                         {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);};
  break;


  case 24: /* RIGHT_EXPRESSION: RIGHT_EXPRESSION PLUS RIGHT_EXPRESSION  */
  if (yyn == 24)
    /* "grammar.y":314  */
                                           {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);};
  break;


  case 25: /* RIGHT_EXPRESSION: RIGHT_EXPRESSION MINUS RIGHT_EXPRESSION  */
  if (yyn == 25)
    /* "grammar.y":315  */
                                            {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);};
  break;


  case 26: /* RIGHT_EXPRESSION: RIGHT_EXPRESSION STAR RIGHT_EXPRESSION  */
  if (yyn == 26)
    /* "grammar.y":316  */
                                           {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);};
  break;


  case 27: /* RIGHT_EXPRESSION: RIGHT_EXPRESSION SLASH RIGHT_EXPRESSION  */
  if (yyn == 27)
    /* "grammar.y":317  */
                                            {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);};
  break;


  case 28: /* RIGHT_EXPRESSION: RIGHT_EXPRESSION PERCENT RIGHT_EXPRESSION  */
  if (yyn == 28)
    /* "grammar.y":318  */
                                              {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);};
  break;


  case 29: /* RIGHT_EXPRESSION: RIGHT_EXPRESSION OR RIGHT_EXPRESSION  */
  if (yyn == 29)
    /* "grammar.y":319  */
                                         {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);};
  break;


  case 30: /* RIGHT_EXPRESSION: RIGHT_EXPRESSION AND RIGHT_EXPRESSION  */
  if (yyn == 30)
    /* "grammar.y":320  */
                                          {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);};
  break;


  case 31: /* RIGHT_EXPRESSION: RIGHT_EXPRESSION AMPERSAND RIGHT_EXPRESSION  */
  if (yyn == 31)
    /* "grammar.y":321  */
                                                {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);};
  break;


  case 32: /* RIGHT_EXPRESSION: RIGHT_EXPRESSION VERT_LINE RIGHT_EXPRESSION  */
  if (yyn == 32)
    /* "grammar.y":322  */
                                                {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);};
  break;


  case 33: /* RIGHT_EXPRESSION: RIGHT_EXPRESSION CARET RIGHT_EXPRESSION  */
  if (yyn == 33)
    /* "grammar.y":323  */
                                            {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);};
  break;


  case 34: /* RIGHT_EXPRESSION: PLUS RIGHT_EXPRESSION  */
  if (yyn == 34)
    /* "grammar.y":324  */
                                    {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 2);};
  break;


  case 35: /* RIGHT_EXPRESSION: MINUS RIGHT_EXPRESSION  */
  if (yyn == 35)
    /* "grammar.y":325  */
                                     {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 2);};
  break;


  case 36: /* RIGHT_EXPRESSION: LEFT_PAREN RIGHT_EXPRESSION RIGHT_PAREN  */
  if (yyn == 36)
    /* "grammar.y":326  */
                                            {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);};
  break;


  case 37: /* RIGHT_EXPRESSION: LEFT_EXPRESSION  */
  if (yyn == 37)
    /* "grammar.y":327  */
                    {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 1);};
  break;


  case 38: /* RIGHT_EXPRESSION: AMPERSAND LEFT_EXPRESSION  */
  if (yyn == 38)
    /* "grammar.y":328  */
                                             {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 2);};
  break;


  case 39: /* RIGHT_EXPRESSION: NUMBER  */
  if (yyn == 39)
    /* "grammar.y":329  */
           {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 1);};
  break;


  case 40: /* RIGHT_EXPRESSION: CONST_STRING  */
  if (yyn == 40)
    /* "grammar.y":330  */
                 {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 1);};
  break;


  case 41: /* RIGHT_EXPRESSION: FUNC_CALL  */
  if (yyn == 41)
    /* "grammar.y":331  */
              {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 1);};
  break;


  case 42: /* STATEMENT: STATEMENT_ASSIGN  */
  if (yyn == 42)
    /* "grammar.y":335  */
                     {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT, 1);};
  break;


  case 43: /* STATEMENT: STATEMENT_IF  */
  if (yyn == 43)
    /* "grammar.y":336  */
                 {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT, 1);};
  break;


  case 44: /* STATEMENT: STATEMENT_WHILE  */
  if (yyn == 44)
    /* "grammar.y":337  */
                    {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT, 1);};
  break;


  case 45: /* STATEMENT: STATEMENT_VAR_DEF  */
  if (yyn == 45)
    /* "grammar.y":338  */
                      {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT, 1);};
  break;


  case 46: /* STATEMENT: STATEMENT_STRUCT_DEF  */
  if (yyn == 46)
    /* "grammar.y":339  */
                         {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT, 1);};
  break;


  case 47: /* STATEMENT: STATEMENT_RETURN  */
  if (yyn == 47)
    /* "grammar.y":340  */
                     {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT, 1);};
  break;


  case 48: /* STATEMENT: FUNC_CALL SEMICOLON  */
  if (yyn == 48)
    /* "grammar.y":341  */
                        {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT, 2);};
  break;


  case 49: /* STATEMENT: SEMICOLON  */
  if (yyn == 49)
    /* "grammar.y":342  */
              {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT, 1);};
  break;


  case 50: /* STATEMENT_ASSIGN: LEFT_EXPRESSION ASSIGN RIGHT_EXPRESSION SEMICOLON  */
  if (yyn == 50)
    /* "grammar.y":346  */
                                                            {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_ASSIGN, 4);};
  break;


  case 51: /* STATEMENT_IF: IF LEFT_PAREN RIGHT_EXPRESSION RIGHT_PAREN STATEMENTS_BLOCK STATEMENT_ELSE  */
  if (yyn == 51)
    /* "grammar.y":350  */
                                                                               {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_IF, 6);};
  break;


  case 52: /* STATEMENT_ELSE: ELSE STATEMENTS_BLOCK  */
  if (yyn == 52)
    /* "grammar.y":354  */
                          {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_ELSE, 2);};
  break;


  case 53: /* STATEMENT_ELSE: ELSE STATEMENT_IF  */
  if (yyn == 53)
    /* "grammar.y":355  */
                      {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_ELSE, 2);};
  break;


  case 54: /* STATEMENT_ELSE: %empty  */
  if (yyn == 54)
    /* "grammar.y":356  */
    {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_ELSE, 0);};
  break;


  case 55: /* STATEMENT_WHILE: WHILE LEFT_PAREN RIGHT_EXPRESSION RIGHT_PAREN STATEMENTS_BLOCK  */
  if (yyn == 55)
    /* "grammar.y":360  */
                                                                   {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_WHILE, 5);};
  break;


  case 56: /* STATEMENT_RETURN: RETURN SEMICOLON  */
  if (yyn == 56)
    /* "grammar.y":364  */
                     {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_RETURN, 2);};
  break;


  case 57: /* STATEMENT_RETURN: RETURN RIGHT_EXPRESSION SEMICOLON  */
  if (yyn == 57)
    /* "grammar.y":365  */
                                      {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_RETURN, 3);};
  break;


  case 58: /* STRUCT_TYPE: STRUCT IDN  */
  if (yyn == 58)
    /* "grammar.y":369  */
               {lexer.addNonTerminalNode(SymbolKind.S_STRUCT_TYPE, 2);};
  break;


  case 59: /* VAR_TYPE: CHAR  */
  if (yyn == 59)
    /* "grammar.y":373  */
         {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 1);};
  break;


  case 60: /* VAR_TYPE: SHORT  */
  if (yyn == 60)
    /* "grammar.y":374  */
          {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 1);};
  break;


  case 61: /* VAR_TYPE: INT  */
  if (yyn == 61)
    /* "grammar.y":375  */
        {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 1);};
  break;


  case 62: /* VAR_TYPE: LONG  */
  if (yyn == 62)
    /* "grammar.y":376  */
         {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 1);};
  break;


  case 63: /* VAR_TYPE: FLOAT  */
  if (yyn == 63)
    /* "grammar.y":377  */
          {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 1);};
  break;


  case 64: /* VAR_TYPE: DOUBLE  */
  if (yyn == 64)
    /* "grammar.y":378  */
           {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 1);};
  break;


  case 65: /* VAR_TYPE: VOID  */
  if (yyn == 65)
    /* "grammar.y":379  */
         {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 1);};
  break;


  case 66: /* VAR_TYPE: LONG LONG  */
  if (yyn == 66)
    /* "grammar.y":380  */
              {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 2);};
  break;


  case 67: /* VAR_TYPE: SIGNED SHORT  */
  if (yyn == 67)
    /* "grammar.y":381  */
                 {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 2);};
  break;


  case 68: /* VAR_TYPE: SIGNED INT  */
  if (yyn == 68)
    /* "grammar.y":382  */
               {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 2);};
  break;


  case 69: /* VAR_TYPE: SIGNED LONG  */
  if (yyn == 69)
    /* "grammar.y":383  */
                {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 2);};
  break;


  case 70: /* VAR_TYPE: SIGNED LONG LONG  */
  if (yyn == 70)
    /* "grammar.y":384  */
                     {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 3);};
  break;


  case 71: /* VAR_TYPE: UNSIGNED SHORT  */
  if (yyn == 71)
    /* "grammar.y":385  */
                   {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 2);};
  break;


  case 72: /* VAR_TYPE: UNSIGNED INT  */
  if (yyn == 72)
    /* "grammar.y":386  */
                 {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 2);};
  break;


  case 73: /* VAR_TYPE: UNSIGNED LONG  */
  if (yyn == 73)
    /* "grammar.y":387  */
                  {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 2);};
  break;


  case 74: /* VAR_TYPE: UNSIGNED LONG LONG  */
  if (yyn == 74)
    /* "grammar.y":388  */
                       {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 3);};
  break;


  case 75: /* VAR_TYPE: STRUCT_TYPE  */
  if (yyn == 75)
    /* "grammar.y":389  */
                {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 1);};
  break;


  case 76: /* STATEMENT_VAR_DEF: VAR_TYPE INITIALIZE_DEC STATEMENT_VAR_DEF_EXPAND  */
  if (yyn == 76)
    /* "grammar.y":393  */
                                                     {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_VAR_DEF, 3);};
  break;


  case 77: /* STATEMENT_VAR_DEF: VAR_TYPE NON_INITIALIZE_DEC STATEMENT_VAR_DEF_EXPAND  */
  if (yyn == 77)
    /* "grammar.y":394  */
                                                         {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_VAR_DEF, 3);};
  break;


  case 78: /* STATEMENT_VAR_DEF_EXPAND: COMMA INITIALIZE_DEC STATEMENT_VAR_DEF_EXPAND  */
  if (yyn == 78)
    /* "grammar.y":398  */
                                                  {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_VAR_DEF_EXPAND, 3);};
  break;


  case 79: /* STATEMENT_VAR_DEF_EXPAND: COMMA NON_INITIALIZE_DEC STATEMENT_VAR_DEF_EXPAND  */
  if (yyn == 79)
    /* "grammar.y":399  */
                                                      {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_VAR_DEF_EXPAND, 3);};
  break;


  case 80: /* STATEMENT_VAR_DEF_EXPAND: SEMICOLON  */
  if (yyn == 80)
    /* "grammar.y":400  */
              {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_VAR_DEF_EXPAND, 1);};
  break;


  case 81: /* INITIALIZE_DEC: IDN ASSIGN RIGHT_EXPRESSION  */
  if (yyn == 81)
    /* "grammar.y":404  */
                                {lexer.addNonTerminalNode(SymbolKind.S_INITIALIZE_DEC, 3);};
  break;


  case 82: /* INITIALIZE_DEC: STAR IDN ASSIGN RIGHT_EXPRESSION  */
  if (yyn == 82)
    /* "grammar.y":405  */
                                     {lexer.addNonTerminalNode(SymbolKind.S_INITIALIZE_DEC, 4);};
  break;


  case 83: /* ARRAY_SUFFIX: LEFT_SQUARE RIGHT_SQUARE  */
  if (yyn == 83)
    /* "grammar.y":409  */
                             {lexer.addNonTerminalNode(SymbolKind.S_ARRAY_SUFFIX, 2);};
  break;


  case 84: /* ARRAY_SUFFIX: LEFT_SQUARE RIGHT_EXPRESSION RIGHT_SQUARE  */
  if (yyn == 84)
    /* "grammar.y":410  */
                                              {lexer.addNonTerminalNode(SymbolKind.S_ARRAY_SUFFIX, 3);};
  break;


  case 85: /* ARRAY_SUFFIX: LEFT_SQUARE RIGHT_EXPRESSION RIGHT_SQUARE ARRAY_SUFFIX  */
  if (yyn == 85)
    /* "grammar.y":411  */
                                                           {lexer.addNonTerminalNode(SymbolKind.S_ARRAY_SUFFIX, 4);};
  break;


  case 86: /* NON_INITIALIZE_DEC: IDN  */
  if (yyn == 86)
    /* "grammar.y":415  */
        {lexer.addNonTerminalNode(SymbolKind.S_NON_INITIALIZE_DEC, 1);};
  break;


  case 87: /* NON_INITIALIZE_DEC: STAR IDN  */
  if (yyn == 87)
    /* "grammar.y":416  */
             {lexer.addNonTerminalNode(SymbolKind.S_NON_INITIALIZE_DEC, 2);};
  break;


  case 88: /* NON_INITIALIZE_DEC: IDN ARRAY_SUFFIX  */
  if (yyn == 88)
    /* "grammar.y":417  */
                     {lexer.addNonTerminalNode(SymbolKind.S_NON_INITIALIZE_DEC, 2);};
  break;


  case 89: /* NON_INITIALIZE_DEC: STAR IDN ARRAY_SUFFIX  */
  if (yyn == 89)
    /* "grammar.y":418  */
                          {lexer.addNonTerminalNode(SymbolKind.S_NON_INITIALIZE_DEC, 3);};
  break;


  case 90: /* STATEMENT_STRUCT_DEF: STRUCT IDN LEFT_BRACE STRUCT_VAR_DEF RIGHT_BRACE SEMICOLON  */
  if (yyn == 90)
    /* "grammar.y":422  */
                                                               {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_STRUCT_DEF, 6);};
  break;


  case 91: /* STRUCT_VAR_DEF: VAR_TYPE NON_INITIALIZE_DEC NON_INITIALIZE_DEC_EXPAND SEMICOLON STRUCT_VAR_DEF  */
  if (yyn == 91)
    /* "grammar.y":426  */
                                                                                   {lexer.addNonTerminalNode(SymbolKind.S_STRUCT_VAR_DEF, 5);};
  break;


  case 92: /* STRUCT_VAR_DEF: %empty  */
  if (yyn == 92)
    /* "grammar.y":427  */
    {lexer.addNonTerminalNode(SymbolKind.S_STRUCT_VAR_DEF, 0);};
  break;


  case 93: /* NON_INITIALIZE_DEC_EXPAND: COMMA NON_INITIALIZE_DEC NON_INITIALIZE_DEC_EXPAND  */
  if (yyn == 93)
    /* "grammar.y":431  */
                                                       {lexer.addNonTerminalNode(SymbolKind.S_NON_INITIALIZE_DEC_EXPAND, 3);};
  break;


  case 94: /* NON_INITIALIZE_DEC_EXPAND: %empty  */
  if (yyn == 94)
    /* "grammar.y":432  */
    {lexer.addNonTerminalNode(SymbolKind.S_NON_INITIALIZE_DEC_EXPAND, 0);};
  break;


  case 95: /* FUNC_CALL: IDN LEFT_PAREN RIGHT_EXPRESSION SEND_ARGS_EXPAND RIGHT_PAREN  */
  if (yyn == 95)
    /* "grammar.y":436  */
                                                                 {lexer.addNonTerminalNode(SymbolKind.S_FUNC_CALL, 5);};
  break;


  case 96: /* FUNC_CALL: IDN LEFT_PAREN RIGHT_PAREN  */
  if (yyn == 96)
    /* "grammar.y":437  */
                               {lexer.addNonTerminalNode(SymbolKind.S_FUNC_CALL, 3);};
  break;


  case 97: /* SEND_ARGS_EXPAND: COMMA RIGHT_EXPRESSION SEND_ARGS_EXPAND  */
  if (yyn == 97)
    /* "grammar.y":441  */
                                            {lexer.addNonTerminalNode(SymbolKind.S_SEND_ARGS_EXPAND, 3);};
  break;


  case 98: /* SEND_ARGS_EXPAND: %empty  */
  if (yyn == 98)
    /* "grammar.y":442  */
    {lexer.addNonTerminalNode(SymbolKind.S_SEND_ARGS_EXPAND, 0);};
  break;


  case 99: /* STATEMENT_FUNC_DEF: VAR_TYPE IDN LEFT_PAREN RIGHT_PAREN STATEMENTS_BLOCK  */
  if (yyn == 99)
    /* "grammar.y":446  */
                                                         {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_FUNC_DEF, 5);};
  break;


  case 100: /* STATEMENT_FUNC_DEF: VAR_TYPE IDN LEFT_PAREN RECV_ARGS RECV_ARGS_EXPAND RIGHT_PAREN STATEMENTS_BLOCK  */
  if (yyn == 100)
    /* "grammar.y":447  */
                                                                                    {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_FUNC_DEF, 7);};
  break;


  case 101: /* RECV_ARGS: VAR_TYPE IDN  */
  if (yyn == 101)
    /* "grammar.y":451  */
                 {lexer.addNonTerminalNode(SymbolKind.S_RECV_ARGS, 2);};
  break;


  case 102: /* RECV_ARGS: VAR_TYPE STAR IDN  */
  if (yyn == 102)
    /* "grammar.y":452  */
                      {lexer.addNonTerminalNode(SymbolKind.S_RECV_ARGS, 3);};
  break;


  case 103: /* RECV_ARGS: VAR_TYPE IDN ARRAY_SUFFIX  */
  if (yyn == 103)
    /* "grammar.y":453  */
                              {lexer.addNonTerminalNode(SymbolKind.S_RECV_ARGS, 3);};
  break;


  case 104: /* RECV_ARGS_EXPAND: COMMA RECV_ARGS RECV_ARGS_EXPAND  */
  if (yyn == 104)
    /* "grammar.y":457  */
                                     {lexer.addNonTerminalNode(SymbolKind.S_RECV_ARGS_EXPAND, 3);};
  break;


  case 105: /* RECV_ARGS_EXPAND: %empty  */
  if (yyn == 105)
    /* "grammar.y":458  */
    {lexer.addNonTerminalNode(SymbolKind.S_RECV_ARGS_EXPAND, 0);};
  break;



/* "grammar.java":1373  */

        default: break;
      }

    yystack.pop(yylen);
    yylen = 0;
    /* Shift the result of the reduction.  */
    int yystate = yyLRGotoState(yystack.stateAt(0), yyr1_[yyn]);
    yystack.push(yystate, yyval);
    return YYNEWSTATE;
  }




  /**
   * Parse input from the scanner that was specified at object construction
   * time.  Return whether the end of the input was reached successfully.
   *
   * @return <tt>true</tt> if the parsing succeeds.  Note that this does not
   *          imply that there were no syntax errors.
   */
  public boolean parse() throws java.io.IOException

  {


    /* Lookahead token kind.  */
    int yychar = YYEMPTY_;
    /* Lookahead symbol kind.  */
    SymbolKind yytoken = null;

    /* State.  */
    int yyn = 0;
    int yylen = 0;
    int yystate = 0;
    YYStack yystack = new YYStack ();
    int label = YYNEWSTATE;



    /* Semantic value of the lookahead.  */
    Object yylval = null;

    yyerrstatus_ = 0;
    yynerrs = 0;

    /* Initialize the stack.  */
    yystack.push (yystate, yylval);



    for (;;)
      switch (label)
      {
        /* New state.  Unlike in the C/C++ skeletons, the state is already
           pushed when we come here.  */
      case YYNEWSTATE:

        /* Accept?  */
        if (yystate == YYFINAL_)
          return true;

        /* Take a decision.  First try without lookahead.  */
        yyn = yypact_[yystate];
        if (yyPactValueIsDefault (yyn))
          {
            label = YYDEFAULT;
            break;
          }

        /* Read a lookahead token.  */
        if (yychar == YYEMPTY_)
          {

            yychar = yylexer.yylex ();
            yylval = yylexer.getLVal();

          }

        /* Convert token to internal form.  */
        yytoken = yytranslate_ (yychar);

        if (yytoken == SymbolKind.S_YYerror)
          {
            // The scanner already issued an error message, process directly
            // to error recovery.  But do not keep the error token as
            // lookahead, it is too special and may lead us to an endless
            // loop in error recovery. */
            yychar = Lexer.YYUNDEF;
            yytoken = SymbolKind.S_YYUNDEF;
            label = YYERRLAB1;
          }
        else
          {
            /* If the proper action on seeing token YYTOKEN is to reduce or to
               detect an error, take that action.  */
            yyn += yytoken.getCode();
            if (yyn < 0 || YYLAST_ < yyn || yycheck_[yyn] != yytoken.getCode())
              label = YYDEFAULT;

            /* <= 0 means reduce or error.  */
            else if ((yyn = yytable_[yyn]) <= 0)
              {
                if (yyTableValueIsError (yyn))
                  label = YYERRLAB;
                else
                  {
                    yyn = -yyn;
                    label = YYREDUCE;
                  }
              }

            else
              {
                /* Shift the lookahead token.  */
                /* Discard the token being shifted.  */
                yychar = YYEMPTY_;

                /* Count tokens shifted since error; after three, turn off error
                   status.  */
                if (yyerrstatus_ > 0)
                  --yyerrstatus_;

                yystate = yyn;
                yystack.push (yystate, yylval);
                label = YYNEWSTATE;
              }
          }
        break;

      /*-----------------------------------------------------------.
      | yydefault -- do the default action for the current state.  |
      `-----------------------------------------------------------*/
      case YYDEFAULT:
        yyn = yydefact_[yystate];
        if (yyn == 0)
          label = YYERRLAB;
        else
          label = YYREDUCE;
        break;

      /*-----------------------------.
      | yyreduce -- Do a reduction.  |
      `-----------------------------*/
      case YYREDUCE:
        yylen = yyr2_[yyn];
        label = yyaction(yyn, yystack, yylen);
        yystate = yystack.stateAt (0);
        break;

      /*------------------------------------.
      | yyerrlab -- here on detecting error |
      `------------------------------------*/
      case YYERRLAB:
        /* If not already recovering from an error, report this error.  */
        if (yyerrstatus_ == 0)
          {
            ++yynerrs;
            if (yychar == YYEMPTY_)
              yytoken = null;
            yyreportSyntaxError (new Context (yystack, yytoken));
          }

        if (yyerrstatus_ == 3)
          {
            /* If just tried and failed to reuse lookahead token after an
               error, discard it.  */

            if (yychar <= Lexer.YYEOF)
              {
                /* Return failure if at end of input.  */
                if (yychar == Lexer.YYEOF)
                  return false;
              }
            else
              yychar = YYEMPTY_;
          }

        /* Else will try to reuse lookahead token after shifting the error
           token.  */
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------.
      | errorlab -- error raised explicitly by YYERROR.  |
      `-------------------------------------------------*/
      case YYERROR:
        /* Do not reclaim the symbols of the rule which action triggered
           this YYERROR.  */
        yystack.pop (yylen);
        yylen = 0;
        yystate = yystack.stateAt (0);
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------------------.
      | yyerrlab1 -- common code for both syntax error and YYERROR.  |
      `-------------------------------------------------------------*/
      case YYERRLAB1:
        yyerrstatus_ = 3;       /* Each real token shifted decrements this.  */

        // Pop stack until we find a state that shifts the error token.
        for (;;)
          {
            yyn = yypact_[yystate];
            if (!yyPactValueIsDefault (yyn))
              {
                yyn += SymbolKind.S_YYerror.getCode();
                if (0 <= yyn && yyn <= YYLAST_
                    && yycheck_[yyn] == SymbolKind.S_YYerror.getCode())
                  {
                    yyn = yytable_[yyn];
                    if (0 < yyn)
                      break;
                  }
              }

            /* Pop the current state because it cannot handle the
             * error token.  */
            if (yystack.height == 0)
              return false;


            yystack.pop ();
            yystate = yystack.stateAt (0);
          }

        if (label == YYABORT)
          /* Leave the switch.  */
          break;



        /* Shift the error token.  */

        yystate = yyn;
        yystack.push (yyn, yylval);
        label = YYNEWSTATE;
        break;

        /* Accept.  */
      case YYACCEPT:
        return true;

        /* Abort.  */
      case YYABORT:
        return false;
      }
}




  /**
   * Information needed to get the list of expected tokens and to forge
   * a syntax error diagnostic.
   */
  public static final class Context
  {
    Context (YYStack stack, SymbolKind token)
    {
      yystack = stack;
      yytoken = token;
    }

    private YYStack yystack;


    /**
     * The symbol kind of the lookahead token.
     */
    public final SymbolKind getToken ()
    {
      return yytoken;
    }

    private SymbolKind yytoken;
    static final int NTOKENS = Grammar.YYNTOKENS_;

    /**
     * Put in YYARG at most YYARGN of the expected tokens given the
     * current YYCTX, and return the number of tokens stored in YYARG.  If
     * YYARG is null, return the number of expected tokens (guaranteed to
     * be less than YYNTOKENS).
     */
    int getExpectedTokens (SymbolKind yyarg[], int yyargn)
    {
      return getExpectedTokens (yyarg, 0, yyargn);
    }

    int getExpectedTokens (SymbolKind yyarg[], int yyoffset, int yyargn)
    {
      int yycount = yyoffset;
      int yyn = yypact_[this.yystack.stateAt (0)];
      if (!yyPactValueIsDefault (yyn))
        {
          /* Start YYX at -YYN if negative to avoid negative
             indexes in YYCHECK.  In other words, skip the first
             -YYN actions for this state because they are default
             actions.  */
          int yyxbegin = yyn < 0 ? -yyn : 0;
          /* Stay within bounds of both yycheck and yytname.  */
          int yychecklim = YYLAST_ - yyn + 1;
          int yyxend = yychecklim < NTOKENS ? yychecklim : NTOKENS;
          for (int yyx = yyxbegin; yyx < yyxend; ++yyx)
            if (yycheck_[yyx + yyn] == yyx && yyx != SymbolKind.S_YYerror.getCode()
                && !yyTableValueIsError(yytable_[yyx + yyn]))
              {
                if (yyarg == null)
                  yycount += 1;
                else if (yycount == yyargn)
                  return 0; // FIXME: this is incorrect.
                else
                  yyarg[yycount++] = SymbolKind.get(yyx);
              }
        }
      if (yyarg != null && yycount == yyoffset && yyoffset < yyargn)
        yyarg[yycount] = null;
      return yycount - yyoffset;
    }
  }


  private int yysyntaxErrorArguments (Context yyctx, SymbolKind[] yyarg, int yyargn)
  {
    /* There are many possibilities here to consider:
       - If this state is a consistent state with a default action,
         then the only way this function was invoked is if the
         default action is an error action.  In that case, don't
         check for expected tokens because there are none.
       - The only way there can be no lookahead present (in tok) is
         if this state is a consistent state with a default action.
         Thus, detecting the absence of a lookahead is sufficient to
         determine that there is no unexpected or expected token to
         report.  In that case, just report a simple "syntax error".
       - Don't assume there isn't a lookahead just because this
         state is a consistent state with a default action.  There
         might have been a previous inconsistent state, consistent
         state with a non-default action, or user semantic action
         that manipulated yychar.  (However, yychar is currently out
         of scope during semantic actions.)
       - Of course, the expected token list depends on states to
         have correct lookahead information, and it depends on the
         parser not to perform extra reductions after fetching a
         lookahead from the scanner and before detecting a syntax
         error.  Thus, state merging (from LALR or IELR) and default
         reductions corrupt the expected token list.  However, the
         list is correct for canonical LR with one exception: it
         will still contain any token that will not be accepted due
         to an error action in a later state.
    */
    int yycount = 0;
    if (yyctx.getToken() != null)
      {
        if (yyarg != null)
          yyarg[yycount] = yyctx.getToken();
        yycount += 1;
        yycount += yyctx.getExpectedTokens(yyarg, 1, yyargn);
      }
    return yycount;
  }


  /**
   * Build and emit a "syntax error" message in a user-defined way.
   *
   * @param ctx  The context of the error.
   */
  private void yyreportSyntaxError(Context yyctx) {
      if (yyErrorVerbose) {
          final int argmax = 5;
          SymbolKind[] yyarg = new SymbolKind[argmax];
          int yycount = yysyntaxErrorArguments(yyctx, yyarg, argmax);
          String[] yystr = new String[yycount];
          for (int yyi = 0; yyi < yycount; ++yyi) {
              yystr[yyi] = yyarg[yyi].getName();
          }
          String yyformat;
          switch (yycount) {
              default:
              case 0: yyformat = "syntax error"; break;
              case 1: yyformat = "syntax error, unexpected {0}"; break;
              case 2: yyformat = "syntax error, unexpected {0}, expecting {1}"; break;
              case 3: yyformat = "syntax error, unexpected {0}, expecting {1} or {2}"; break;
              case 4: yyformat = "syntax error, unexpected {0}, expecting {1} or {2} or {3}"; break;
              case 5: yyformat = "syntax error, unexpected {0}, expecting {1} or {2} or {3} or {4}"; break;
          }
          yyerror(new MessageFormat(yyformat).format(yystr));
      } else {
          yyerror("syntax error");
      }
  }

  /**
   * Whether the given <code>yypact_</code> value indicates a defaulted state.
   * @param yyvalue   the value to check
   */
  private static boolean yyPactValueIsDefault (int yyvalue)
  {
    return yyvalue == yypact_ninf_;
  }

  /**
   * Whether the given <code>yytable_</code>
   * value indicates a syntax error.
   * @param yyvalue the value to check
   */
  private static boolean yyTableValueIsError (int yyvalue)
  {
    return yyvalue == yytable_ninf_;
  }

  private static final short yypact_ninf_ = -130;
  private static final short yytable_ninf_ = -1;

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short[] yypact_ = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     376,     7,  -130,  -130,  -130,    -5,   102,   160,  -130,  -130,
    -130,    23,  -130,  -130,     4,   376,   376,   376,    18,  -130,
    -130,  -130,    12,  -130,  -130,    13,  -130,     6,    28,    74,
      74,  -130,  -130,  -130,   392,  -130,  -130,   360,    81,   139,
    -130,     1,     8,  -130,  -130,  -130,    29,    31,    30,    38,
      34,    53,  -130,  -130,  -130,  -130,    83,   139,  -130,    75,
     139,   139,  -130,  -130,   188,  -130,   320,   139,  -130,     9,
      74,    74,  -130,    78,    94,    62,    85,   246,  -130,    78,
      98,   392,   100,    93,   139,   109,  -130,   119,    -3,  -130,
    -130,  -130,    78,   139,   139,   139,   139,   139,   139,   139,
     139,   139,   139,   139,   139,   139,   139,   139,   139,   320,
    -130,  -130,    78,    31,    97,  -130,  -130,   113,   120,   104,
    -130,   121,   107,   267,  -130,  -130,  -130,  -130,     8,  -130,
    -130,   105,  -130,  -130,    53,    38,  -130,   268,   205,    -3,
    -130,  -130,   383,   397,   410,   422,   433,   442,   442,   125,
     125,   125,   125,   134,   134,  -130,  -130,  -130,    62,   392,
     139,   139,  -130,   286,  -130,   139,  -130,  -130,  -130,  -130,
     139,   126,    -3,  -130,  -130,  -130,   154,   171,  -130,   304,
     268,  -130,  -130,    38,    38,  -130,  -130,   116,  -130,    10,
    -130,  -130,  -130
    };
  }

/* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
   Performed when YYTABLE does not specify something else to do.  Zero
   means the default is an error.  */
  private static final byte[] yydefact_ = yydefact_init();
  private static final byte[] yydefact_init()
  {
    return new byte[]
    {
       6,     0,    59,    60,    61,    62,     0,     0,    63,    64,
      65,     0,     2,    75,     0,     6,     6,     6,    58,    66,
      67,    68,    69,    71,    72,    73,     1,    86,     0,     0,
       0,     3,     5,     4,    92,    70,    74,     0,     0,     0,
      88,    87,     0,    80,    76,    77,     0,     0,     0,     0,
       0,   105,    16,    15,    40,    17,    14,     0,    83,     0,
       0,     0,    37,    39,     0,    41,    81,     0,    89,    86,
       0,     0,    58,    86,     0,    94,     0,     0,    99,   101,
       0,     0,     0,     0,     0,     0,    11,     0,    14,    38,
      34,    35,    84,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,    82,
      78,    79,    87,     0,     0,    90,     8,     0,     0,     0,
      49,     0,     0,    10,    42,    43,    44,    47,     0,    45,
      46,     0,   103,   102,   105,     0,    96,    98,     0,    14,
      36,    85,    29,    30,    32,    33,    31,    22,    23,    19,
      21,    18,    20,    24,    25,    26,    27,    28,    94,    92,
       0,     0,    56,     0,     7,     0,     9,    48,   104,   100,
       0,     0,    14,    13,    93,    91,     0,     0,    57,     0,
      98,    95,    12,     0,     0,    50,    97,    54,    55,     0,
      51,    52,    53
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short[] yypgoto_ = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -130,  -130,   240,  -106,    27,   -52,  -129,  -130,   -39,  -130,
    -130,   -16,  -130,  -130,  -130,  -130,   -31,   -75,     3,   132,
     -40,   -38,   -72,    26,   101,   -53,    56,  -130,   179,   137
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short[] yydefgoto_ = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
       0,    11,    12,    78,   121,    62,    86,    63,    64,   123,
     124,   125,   190,   126,   127,    13,    14,    15,    44,    29,
      40,    30,    16,    48,   114,    65,   171,    17,    51,    82
    };
  }

/* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule whose
   number is the opposite.  If YYTABLE_NINF, syntax error.  */
  private static final short[] yytable_ = yytable_init();
  private static final short[] yytable_init()
  {
    return new short[]
    {
      66,    68,   129,    47,    71,   130,    50,    89,    84,    75,
     173,    27,    38,    19,    18,    69,    37,    38,    87,    77,
      38,    90,    91,    26,   131,   122,    85,    34,   109,   169,
      35,    36,    67,    45,   117,    41,    72,    39,    73,   132,
      39,    79,    76,   182,   137,   138,   128,    77,   129,    28,
      50,   130,   141,    28,   142,   143,   144,   145,   146,   147,
     148,   149,   150,   151,   152,   153,   154,   155,   156,   157,
     131,   122,    68,   110,   111,   158,    74,   187,   188,    80,
     163,    81,    88,   191,    52,    53,    54,    55,    56,    38,
     113,    57,   128,    83,    84,    58,    52,    53,    54,    55,
      56,   112,    42,    57,    43,   133,   136,    52,    53,    54,
      55,    56,    85,   135,    57,   115,   139,    59,    20,    21,
      22,   176,   177,   160,    60,    61,   179,   159,    47,    59,
     161,   180,   140,   164,   162,   167,    60,    61,   165,   181,
      59,   189,    52,    53,    54,    55,    56,    60,    61,    57,
     166,    93,    94,    95,    96,    97,    98,    99,   100,   101,
     102,   103,   104,   105,   106,   107,   108,   183,   104,   105,
     106,   107,   108,   192,    70,    59,    23,    24,    25,   106,
     107,   108,    60,    61,   184,   175,    93,    94,    95,    96,
      97,    98,    99,   100,   101,   102,   103,   104,   105,   106,
     107,   108,    92,    93,    94,    95,    96,    97,    98,    99,
     100,   101,   102,   103,   104,   105,   106,   107,   108,   172,
      93,    94,    95,    96,    97,    98,    99,   100,   101,   102,
     103,   104,   105,   106,   107,   108,   186,    93,    94,    95,
      96,    97,    98,    99,   100,   101,   102,   103,   104,   105,
     106,   107,   108,    56,     1,    31,    32,    33,   116,   174,
     134,     2,     3,     4,     5,     6,     7,     8,     9,    10,
     117,   168,   118,   119,    56,     1,   120,     0,     0,     0,
       0,     0,     2,     3,     4,     5,     6,     7,     8,     9,
      10,   117,     0,   118,   119,     0,   170,   120,     0,     0,
      93,    94,    95,    96,    97,    98,    99,   100,   101,   102,
     103,   104,   105,   106,   107,   108,   178,     0,    93,    94,
      95,    96,    97,    98,    99,   100,   101,   102,   103,   104,
     105,   106,   107,   108,   185,     0,    93,    94,    95,    96,
      97,    98,    99,   100,   101,   102,   103,   104,   105,   106,
     107,   108,    93,    94,    95,    96,    97,    98,    99,   100,
     101,   102,   103,   104,   105,   106,   107,   108,    46,     0,
       0,     0,     0,    49,     0,     2,     3,     4,     5,     6,
       7,     8,     9,    10,     1,     0,     0,     0,     0,     0,
       0,     2,     3,     4,     5,     6,     7,     8,     9,    10,
      46,     0,     0,     0,     0,     0,     0,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    94,    95,    96,    97,
      98,    99,   100,   101,   102,   103,   104,   105,   106,   107,
     108,    95,    96,    97,    98,    99,   100,   101,   102,   103,
     104,   105,   106,   107,   108,    96,    97,    98,    99,   100,
     101,   102,   103,   104,   105,   106,   107,   108,    97,    98,
      99,   100,   101,   102,   103,   104,   105,   106,   107,   108,
      98,    99,   100,   101,   102,   103,   104,   105,   106,   107,
     108,   100,   101,   102,   103,   104,   105,   106,   107,   108
    };
  }

private static final short[] yycheck_ = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
      39,    41,    77,    34,    42,    77,    37,    59,    11,    47,
     139,     7,    11,    18,     7,     7,    10,    11,    57,     9,
      11,    60,    61,     0,    77,    77,    29,     9,    67,   135,
      18,    18,    31,    30,    24,     7,     7,    31,     7,    79,
      31,     7,    12,   172,    83,    84,    77,     9,   123,    45,
      81,   123,    92,    45,    93,    94,    95,    96,    97,    98,
      99,   100,   101,   102,   103,   104,   105,   106,   107,   108,
     123,   123,   112,    70,    71,   113,    45,   183,   184,    45,
     119,    28,     7,   189,     3,     4,     5,     6,     7,    11,
      28,    10,   123,    10,    11,    14,     3,     4,     5,     6,
       7,     7,    28,    10,    30,     7,    13,     3,     4,     5,
       6,     7,    29,    13,    10,    30,     7,    36,    16,    17,
      18,   160,   161,    10,    43,    44,   165,    30,   159,    36,
      10,   170,    13,    12,    30,    30,    43,    44,    31,    13,
      36,    25,     3,     4,     5,     6,     7,    43,    44,    10,
     123,    32,    33,    34,    35,    36,    37,    38,    39,    40,
      41,    42,    43,    44,    45,    46,    47,    13,    43,    44,
      45,    46,    47,   189,    42,    36,    16,    17,    18,    45,
      46,    47,    43,    44,    13,   159,    32,    33,    34,    35,
      36,    37,    38,    39,    40,    41,    42,    43,    44,    45,
      46,    47,    14,    32,    33,    34,    35,    36,    37,    38,
      39,    40,    41,    42,    43,    44,    45,    46,    47,    14,
      32,    33,    34,    35,    36,    37,    38,    39,    40,    41,
      42,    43,    44,    45,    46,    47,   180,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42,    43,    44,
      45,    46,    47,     7,     8,    15,    16,    17,    12,   158,
      81,    15,    16,    17,    18,    19,    20,    21,    22,    23,
      24,   134,    26,    27,     7,     8,    30,    -1,    -1,    -1,
      -1,    -1,    15,    16,    17,    18,    19,    20,    21,    22,
      23,    24,    -1,    26,    27,    -1,    28,    30,    -1,    -1,
      32,    33,    34,    35,    36,    37,    38,    39,    40,    41,
      42,    43,    44,    45,    46,    47,    30,    -1,    32,    33,
      34,    35,    36,    37,    38,    39,    40,    41,    42,    43,
      44,    45,    46,    47,    30,    -1,    32,    33,    34,    35,
      36,    37,    38,    39,    40,    41,    42,    43,    44,    45,
      46,    47,    32,    33,    34,    35,    36,    37,    38,    39,
      40,    41,    42,    43,    44,    45,    46,    47,     8,    -1,
      -1,    -1,    -1,    13,    -1,    15,    16,    17,    18,    19,
      20,    21,    22,    23,     8,    -1,    -1,    -1,    -1,    -1,
      -1,    15,    16,    17,    18,    19,    20,    21,    22,    23,
       8,    -1,    -1,    -1,    -1,    -1,    -1,    15,    16,    17,
      18,    19,    20,    21,    22,    23,    33,    34,    35,    36,
      37,    38,    39,    40,    41,    42,    43,    44,    45,    46,
      47,    34,    35,    36,    37,    38,    39,    40,    41,    42,
      43,    44,    45,    46,    47,    35,    36,    37,    38,    39,
      40,    41,    42,    43,    44,    45,    46,    47,    36,    37,
      38,    39,    40,    41,    42,    43,    44,    45,    46,    47,
      37,    38,    39,    40,    41,    42,    43,    44,    45,    46,
      47,    39,    40,    41,    42,    43,    44,    45,    46,    47
    };
  }

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
  private static final byte[] yystos_ = yystos_init();
  private static final byte[] yystos_init()
  {
    return new byte[]
    {
       0,     8,    15,    16,    17,    18,    19,    20,    21,    22,
      23,    52,    53,    66,    67,    68,    73,    78,     7,    18,
      16,    17,    18,    16,    17,    18,     0,     7,    45,    70,
      72,    53,    53,    53,     9,    18,    18,    10,    11,    31,
      71,     7,    28,    30,    69,    69,     8,    67,    74,    13,
      67,    79,     3,     4,     5,     6,     7,    10,    14,    36,
      43,    44,    56,    58,    59,    76,    59,    31,    71,     7,
      70,    72,     7,     7,    45,    72,    12,     9,    54,     7,
      45,    28,    80,    10,    11,    29,    57,    59,     7,    56,
      59,    59,    14,    32,    33,    34,    35,    36,    37,    38,
      39,    40,    41,    42,    43,    44,    45,    46,    47,    59,
      69,    69,     7,    28,    75,    30,    12,    24,    26,    27,
      30,    55,    56,    60,    61,    62,    64,    65,    67,    68,
      73,    76,    71,     7,    79,    13,    13,    59,    59,     7,
      13,    71,    59,    59,    59,    59,    59,    59,    59,    59,
      59,    59,    59,    59,    59,    59,    59,    59,    72,    30,
      10,    10,    30,    59,    12,    31,    55,    30,    80,    54,
      28,    77,    14,    57,    75,    74,    59,    59,    30,    59,
      59,    13,    57,    13,    13,    30,    77,    54,    54,    25,
      63,    54,    62
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final byte[] yyr1_ = yyr1_init();
  private static final byte[] yyr1_init()
  {
    return new byte[]
    {
       0,    51,    52,    53,    53,    53,    53,    54,    54,    55,
      55,    56,    57,    57,    57,    58,    58,    58,    59,    59,
      59,    59,    59,    59,    59,    59,    59,    59,    59,    59,
      59,    59,    59,    59,    59,    59,    59,    59,    59,    59,
      59,    59,    60,    60,    60,    60,    60,    60,    60,    60,
      61,    62,    63,    63,    63,    64,    65,    65,    66,    67,
      67,    67,    67,    67,    67,    67,    67,    67,    67,    67,
      67,    67,    67,    67,    67,    67,    68,    68,    69,    69,
      69,    70,    70,    71,    71,    71,    72,    72,    72,    72,
      73,    74,    74,    75,    75,    76,    76,    77,    77,    78,
      78,    79,    79,    79,    80,    80
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte[] yyr2_ = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     1,     2,     2,     2,     0,     3,     2,     2,
       1,     2,     4,     3,     0,     1,     1,     1,     3,     3,
       3,     3,     3,     3,     3,     3,     3,     3,     3,     3,
       3,     3,     3,     3,     2,     2,     3,     1,     2,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     2,     1,
       4,     6,     2,     2,     0,     5,     2,     3,     2,     1,
       1,     1,     1,     1,     1,     1,     2,     2,     2,     2,
       3,     2,     2,     2,     3,     1,     3,     3,     3,     3,
       1,     3,     4,     2,     3,     4,     1,     2,     2,     3,
       6,     5,     0,     3,     0,     5,     3,     3,     0,     5,
       7,     2,     3,     3,     3,     0
    };
  }




  /* YYTRANSLATE_(TOKEN-NUM) -- Symbol number corresponding to TOKEN-NUM
     as returned by yylex, with out-of-bounds checking.  */
  private static final SymbolKind yytranslate_(int t)
  {
    // Last valid token kind.
    int code_max = 305;
    if (t <= 0)
      return SymbolKind.S_YYEOF;
    else if (t <= code_max)
      return SymbolKind.get(yytranslate_table_[t]);
    else
      return SymbolKind.S_YYUNDEF;
  }
  private static final byte[] yytranslate_table_ = yytranslate_table_init();
  private static final byte[] yytranslate_table_init()
  {
    return new byte[]
    {
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42,    43,    44,
      45,    46,    47,    48,    49,    50
    };
  }


  private static final int YYLAST_ = 489;
  private static final int YYEMPTY_ = -2;
  private static final int YYFINAL_ = 26;
  private static final int YYNTOKENS_ = 51;

/* Unqualified %code blocks.  */
/* "grammar.y":13  */

/**
     * Parse input from the scanner that was specified at object construction
     * time.  Return whether the end of the input was reached successfully.
     *
     * @return <tt>true</tt> if the parsing succeeds.  Note that this does not
     * imply that there were no syntax errors.
     */
    public boolean parseMe() throws java.io.IOException {


        /* Lookahead token kind.  */
        int yychar = YYEMPTY_;
        /* Lookahead symbol kind.  */
        SymbolKind yytoken = null;

        /* State.  */
        int yyn = 0;
        int yylen = 0;
        int yystate = 0;
        Position yylpos = null;
        YYStack yystack = new YYStack();
        int label = YYNEWSTATE;



        /* Semantic value of the lookahead.  */
        Object yylval = null;

        yyerrstatus_ = 0;
        yynerrs = 0;

        /* Initialize the stack.  */
        yystack.push(yystate, yylval);


        for (; ; )
            switch (label) {
          /* New state.  Unlike in the C/C++ skeletons, the state is already
             pushed when we come here.  */
                case YYNEWSTATE:

                    /* Accept?  */
                    if (yystate == YYFINAL_)
                        return true;

                    /* Take a decision.  First try without lookahead.  */
                    yyn = yypact_[yystate];
                    if (yyPactValueIsDefault(yyn)) {
                        label = YYDEFAULT;
                        break;
                    }

                    /* Read a lookahead token.  */
                    if (yychar == YYEMPTY_) {

                        yychar = yylexer.yylex();
                        yylval = yylexer.getLVal();
                        yylpos = lexer.getLPosition();

                    }

                    /* Convert token to internal form.  */
                    yytoken = yytranslate_(yychar);

                    if (yytoken == SymbolKind.S_YYerror) {
                        // The scanner already issued an error message, process directly
                        // to error recovery.  But do not keep the error token as
                        // lookahead, it is too special and may lead us to an endless
                        // loop in error recovery. */
                        yychar = Lexer.YYUNDEF;
                        yytoken = SymbolKind.S_YYUNDEF;
                        label = YYERRLAB1;
                    } else {
              /* If the proper action on seeing token YYTOKEN is to reduce or to
                 detect an error, take that action.  */
                        yyn += yytoken.getCode();
                        if (yyn < 0 || YYLAST_ < yyn || yycheck_[yyn] != yytoken.getCode())
                            label = YYDEFAULT;

                            /* <= 0 means reduce or error.  */
                        else if ((yyn = yytable_[yyn]) <= 0) {
                            if (yyTableValueIsError(yyn))
                                label = YYERRLAB;
                            else {
                                yyn = -yyn;
                                label = YYREDUCE;
                            }
                        } else {
                            /* Shift the lookahead token.  */
                            /* Discard the token being shifted.  */
                            yychar = YYEMPTY_;

                  /* Count tokens shifted since error; after three, turn off error
                     status.  */
                            if (yyerrstatus_ > 0)
                                --yyerrstatus_;

                            yystate = yyn;
                            yystack.push(yystate, yylval);
                            lexer.addTerminalNode(yytoken, yylpos, yylval);
                            label = YYNEWSTATE;
                        }
                    }
                    break;

        /*-----------------------------------------------------------.
        | yydefault -- do the default action for the current state.  |
        `-----------------------------------------------------------*/
                case YYDEFAULT:
                    yyn = yydefact_[yystate];
                    if (yyn == 0)
                        label = YYERRLAB;
                    else
                        label = YYREDUCE;
                    break;

        /*-----------------------------.
        | yyreduce -- Do a reduction.  |
        `-----------------------------*/
                case YYREDUCE:
                    yylen = yyr2_[yyn];
                    label = yyaction(yyn, yystack, yylen);
                    yystate = yystack.stateAt(0);
                    break;

        /*------------------------------------.
        | yyerrlab -- here on detecting error |
        `------------------------------------*/
                case YYERRLAB:
                    /* If not already recovering from an error, report this error.  */
                    if (yyerrstatus_ == 0) {
                        ++yynerrs;
                        if (yychar == YYEMPTY_)
                            yytoken = null;
                        yyreportSyntaxError(new Context(yystack, yytoken));
                    }

                    if (yyerrstatus_ == 3) {
              /* If just tried and failed to reuse lookahead token after an
                 error, discard it.  */

                        if (yychar <= Lexer.YYEOF) {
                            /* Return failure if at end of input.  */
                            if (yychar == Lexer.YYEOF)
                                return false;
                        } else
                            yychar = YYEMPTY_;
                    }

          /* Else will try to reuse lookahead token after shifting the error
             token.  */
                    label = YYERRLAB1;
                    break;

        /*-------------------------------------------------.
        | errorlab -- error raised explicitly by YYERROR.  |
        `-------------------------------------------------*/
                case YYERROR:
          /* Do not reclaim the symbols of the rule which action triggered
             this YYERROR.  */
                    yystack.pop(yylen);
                    yylen = 0;
                    yystate = yystack.stateAt(0);
                    label = YYERRLAB1;
                    break;

        /*-------------------------------------------------------------.
        | yyerrlab1 -- common code for both syntax error and YYERROR.  |
        `-------------------------------------------------------------*/
                case YYERRLAB1:
                    yyerrstatus_ = 3;       /* Each real token shifted decrements this.  */

                    // Pop stack until we find a state that shifts the error token.
                    for (; ; ) {
                        yyn = yypact_[yystate];
                        if (!yyPactValueIsDefault(yyn)) {
                            yyn += SymbolKind.S_YYerror.getCode();
                            if (0 <= yyn && yyn <= YYLAST_
                                    && yycheck_[yyn] == SymbolKind.S_YYerror.getCode()) {
                                yyn = yytable_[yyn];
                                if (0 < yyn)
                                    break;
                            }
                        }

                        /* Pop the current state because it cannot handle the
                         * error token.  */
                        if (yystack.height == 0)
                            return false;


                        yystack.pop();
                        yystate = yystack.stateAt(0);
                    }

                    if (label == YYABORT)
                        /* Leave the switch.  */
                        break;



                    /* Shift the error token.  */

                    yystate = yyn;
                    yystack.push(yyn, yylval);
                    lexer.addTerminalNode(yytoken, yylpos, yylval);
                    label = YYNEWSTATE;
                    break;

                /* Accept.  */
                case YYACCEPT:
                    return true;

                /* Abort.  */
                case YYABORT:
                    return false;
            }
    }

  private static pers.cycleke.compiler.Lexer lexer;

  public static void main(String[] args) throws IOException {
    var inputFile = new File(args.length == 1 ? args[0] :
                             "src/main/resources/test.lex");
    lexer = new pers.cycleke.compiler.Lexer(new FileInputStream(inputFile));
    var p = new Grammar(lexer);
    if (!p.parseMe()) {
      System.exit(1);
    }
    lexer.printASTree();
  }


/* "grammar.java":2359  */

}
