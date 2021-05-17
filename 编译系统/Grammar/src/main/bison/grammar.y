%language "Java"
%define api.parser.class {Grammar}
%define api.parser.public
%define api.package {pers.cycleke.compiler}
%define parse.error verbose

%code imports {
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
}

%code {
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

}
%token CONST_INTEGER CONST_FLOAT CONST_STRING CONST_CHARACTER IDN STRUCT
%token LEFT_BRACE LEFT_PAREN LEFT_SQUARE RIGHT_BRACE RIGHT_PAREN RIGHT_SQUARE
%token CHAR SHORT INT LONG SIGNED UNSIGNED FLOAT DOUBLE VOID
%token IF ELSE WHILE
%token RETURN
%token COMMA DOT SEMICOLON // , . ;

%right ASSIGN             // =
%left OR                  // ||
%left AND                 // &&
%left VERT_LINE           // |
%left CARET               // ^
%left AMPERSAND           // &
%left EQ NE               // == !=
%left LE GE LT GT         // <= >= < >
%left PLUS MINUS          // + -
%left STAR SLASH PERCENT  // * / %
%precedence GET_ADDR      // &
%precedence POS NEG       // + -
%nonassoc ELSE

%%

PROGRAM
  : TOP_STATEMENTS {lexer.addNonTerminalNode(SymbolKind.S_PROGRAM, 1);}
  ;

TOP_STATEMENTS
  : STATEMENT_VAR_DEF TOP_STATEMENTS {lexer.addNonTerminalNode(SymbolKind.S_TOP_STATEMENTS, 2);}
  | STATEMENT_FUNC_DEF TOP_STATEMENTS {lexer.addNonTerminalNode(SymbolKind.S_TOP_STATEMENTS, 2);}
  | STATEMENT_STRUCT_DEF TOP_STATEMENTS {lexer.addNonTerminalNode(SymbolKind.S_TOP_STATEMENTS, 2);}
  | {lexer.addNonTerminalNode(SymbolKind.S_TOP_STATEMENTS, 0);}
  ;

STATEMENTS_BLOCK
  : LEFT_BRACE STATEMENTS RIGHT_BRACE {lexer.addNonTerminalNode(SymbolKind.S_STATEMENTS_BLOCK, 3);}
  | LEFT_BRACE RIGHT_BRACE {lexer.addNonTerminalNode(SymbolKind.S_STATEMENTS_BLOCK, 2);}
  ;

STATEMENTS
  : STATEMENT STATEMENTS {lexer.addNonTerminalNode(SymbolKind.S_STATEMENTS, 2);}
  | STATEMENT {lexer.addNonTerminalNode(SymbolKind.S_STATEMENTS, 1);}
  ;

LEFT_EXPRESSION
  : IDN LEFT_EXPRESSION_EXPAND {lexer.addNonTerminalNode(SymbolKind.S_LEFT_EXPRESSION, 2);}
  ;

LEFT_EXPRESSION_EXPAND
  : LEFT_SQUARE RIGHT_EXPRESSION RIGHT_SQUARE LEFT_EXPRESSION_EXPAND {lexer.addNonTerminalNode(SymbolKind.S_LEFT_EXPRESSION_EXPAND, 4);}
  | DOT IDN LEFT_EXPRESSION_EXPAND {lexer.addNonTerminalNode(SymbolKind.S_LEFT_EXPRESSION_EXPAND, 3);}
  | {lexer.addNonTerminalNode(SymbolKind.S_LEFT_EXPRESSION_EXPAND, 0);}
  ;

NUMBER
  : CONST_FLOAT {lexer.addNonTerminalNode(SymbolKind.S_NUMBER, 1);}
  | CONST_INTEGER {lexer.addNonTerminalNode(SymbolKind.S_NUMBER, 1);}
  | CONST_CHARACTER {lexer.addNonTerminalNode(SymbolKind.S_NUMBER, 1);}
  ;

RIGHT_EXPRESSION
  : RIGHT_EXPRESSION LT RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);}
  | RIGHT_EXPRESSION LE RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);}
  | RIGHT_EXPRESSION GT RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);}
  | RIGHT_EXPRESSION GE RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);}
  | RIGHT_EXPRESSION EQ RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);}
  | RIGHT_EXPRESSION NE RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);}
  | RIGHT_EXPRESSION PLUS RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);}
  | RIGHT_EXPRESSION MINUS RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);}
  | RIGHT_EXPRESSION STAR RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);}
  | RIGHT_EXPRESSION SLASH RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);}
  | RIGHT_EXPRESSION PERCENT RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);}
  | RIGHT_EXPRESSION OR RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);}
  | RIGHT_EXPRESSION AND RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);}
  | RIGHT_EXPRESSION AMPERSAND RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);}
  | RIGHT_EXPRESSION VERT_LINE RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);}
  | RIGHT_EXPRESSION CARET RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);}
  | PLUS RIGHT_EXPRESSION %prec POS {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 2);}
  | MINUS RIGHT_EXPRESSION %prec NEG {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 2);}
  | LEFT_PAREN RIGHT_EXPRESSION RIGHT_PAREN {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 3);}
  | LEFT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 1);}
  | AMPERSAND LEFT_EXPRESSION %prec GET_ADDR {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 2);}
  | NUMBER {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 1);}
  | CONST_STRING {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 1);}
  | FUNC_CALL {lexer.addNonTerminalNode(SymbolKind.S_RIGHT_EXPRESSION, 1);}
  ;

STATEMENT
  : STATEMENT_ASSIGN {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT, 1);}
  | STATEMENT_IF {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT, 1);}
  | STATEMENT_WHILE {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT, 1);}
  | STATEMENT_VAR_DEF {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT, 1);}
  | STATEMENT_STRUCT_DEF {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT, 1);}
  | STATEMENT_RETURN {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT, 1);}
  | FUNC_CALL SEMICOLON {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT, 2);}
  | SEMICOLON {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT, 1);}
  ;

STATEMENT_ASSIGN
	: LEFT_EXPRESSION ASSIGN RIGHT_EXPRESSION SEMICOLON {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_ASSIGN, 4);}
	;

STATEMENT_IF
  : IF LEFT_PAREN RIGHT_EXPRESSION RIGHT_PAREN STATEMENTS_BLOCK STATEMENT_ELSE {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_IF, 6);}
  ;

STATEMENT_ELSE
  : ELSE STATEMENTS_BLOCK {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_ELSE, 2);}
  | ELSE STATEMENT_IF {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_ELSE, 2);}
  | {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_ELSE, 0);}
  ;

STATEMENT_WHILE
  : WHILE LEFT_PAREN RIGHT_EXPRESSION RIGHT_PAREN STATEMENTS_BLOCK {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_WHILE, 5);}
  ;

STATEMENT_RETURN
  : RETURN SEMICOLON {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_RETURN, 2);}
  | RETURN RIGHT_EXPRESSION SEMICOLON {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_RETURN, 3);}
  ;

STRUCT_TYPE
  : STRUCT IDN {lexer.addNonTerminalNode(SymbolKind.S_STRUCT_TYPE, 2);}
  ;

VAR_TYPE
  : CHAR {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 1);}
  | SHORT {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 1);}
  | INT {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 1);}
  | LONG {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 1);}
  | FLOAT {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 1);}
  | DOUBLE {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 1);}
  | VOID {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 1);}
  | LONG LONG {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 2);}
  | SIGNED SHORT {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 2);}
  | SIGNED INT {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 2);}
  | SIGNED LONG {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 2);}
  | SIGNED LONG LONG {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 3);}
  | UNSIGNED SHORT {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 2);}
  | UNSIGNED INT {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 2);}
  | UNSIGNED LONG {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 2);}
  | UNSIGNED LONG LONG {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 3);}
  | STRUCT_TYPE {lexer.addNonTerminalNode(SymbolKind.S_VAR_TYPE, 1);}
  ;

STATEMENT_VAR_DEF
  : VAR_TYPE INITIALIZE_DEC STATEMENT_VAR_DEF_EXPAND {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_VAR_DEF, 3);}
  | VAR_TYPE NON_INITIALIZE_DEC STATEMENT_VAR_DEF_EXPAND {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_VAR_DEF, 3);}
  ;

STATEMENT_VAR_DEF_EXPAND
  : COMMA INITIALIZE_DEC STATEMENT_VAR_DEF_EXPAND {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_VAR_DEF_EXPAND, 3);}
  | COMMA NON_INITIALIZE_DEC STATEMENT_VAR_DEF_EXPAND {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_VAR_DEF_EXPAND, 3);}
  | SEMICOLON {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_VAR_DEF_EXPAND, 1);}
  ;

INITIALIZE_DEC
  : IDN ASSIGN RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_INITIALIZE_DEC, 3);}
  | STAR IDN ASSIGN RIGHT_EXPRESSION {lexer.addNonTerminalNode(SymbolKind.S_INITIALIZE_DEC, 4);}
  ;

ARRAY_SUFFIX
  : LEFT_SQUARE RIGHT_SQUARE {lexer.addNonTerminalNode(SymbolKind.S_ARRAY_SUFFIX, 2);}
  | LEFT_SQUARE RIGHT_EXPRESSION RIGHT_SQUARE {lexer.addNonTerminalNode(SymbolKind.S_ARRAY_SUFFIX, 3);}
  | LEFT_SQUARE RIGHT_EXPRESSION RIGHT_SQUARE ARRAY_SUFFIX {lexer.addNonTerminalNode(SymbolKind.S_ARRAY_SUFFIX, 4);}
  ;

NON_INITIALIZE_DEC
  : IDN {lexer.addNonTerminalNode(SymbolKind.S_NON_INITIALIZE_DEC, 1);}
  | STAR IDN {lexer.addNonTerminalNode(SymbolKind.S_NON_INITIALIZE_DEC, 2);}
  | IDN ARRAY_SUFFIX {lexer.addNonTerminalNode(SymbolKind.S_NON_INITIALIZE_DEC, 2);}
  | STAR IDN ARRAY_SUFFIX {lexer.addNonTerminalNode(SymbolKind.S_NON_INITIALIZE_DEC, 3);}
  ;

STATEMENT_STRUCT_DEF
  : STRUCT IDN LEFT_BRACE STRUCT_VAR_DEF RIGHT_BRACE SEMICOLON {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_STRUCT_DEF, 6);}
  ;

STRUCT_VAR_DEF
  : VAR_TYPE NON_INITIALIZE_DEC NON_INITIALIZE_DEC_EXPAND SEMICOLON STRUCT_VAR_DEF {lexer.addNonTerminalNode(SymbolKind.S_STRUCT_VAR_DEF, 5);}
  | {lexer.addNonTerminalNode(SymbolKind.S_STRUCT_VAR_DEF, 0);}
  ;

NON_INITIALIZE_DEC_EXPAND
  : COMMA NON_INITIALIZE_DEC NON_INITIALIZE_DEC_EXPAND {lexer.addNonTerminalNode(SymbolKind.S_NON_INITIALIZE_DEC_EXPAND, 3);}
  | {lexer.addNonTerminalNode(SymbolKind.S_NON_INITIALIZE_DEC_EXPAND, 0);}
  ;

FUNC_CALL
  : IDN LEFT_PAREN RIGHT_EXPRESSION SEND_ARGS_EXPAND RIGHT_PAREN {lexer.addNonTerminalNode(SymbolKind.S_FUNC_CALL, 5);}
  | IDN LEFT_PAREN RIGHT_PAREN {lexer.addNonTerminalNode(SymbolKind.S_FUNC_CALL, 3);}
  ;

SEND_ARGS_EXPAND
  : COMMA RIGHT_EXPRESSION SEND_ARGS_EXPAND {lexer.addNonTerminalNode(SymbolKind.S_SEND_ARGS_EXPAND, 3);}
  | {lexer.addNonTerminalNode(SymbolKind.S_SEND_ARGS_EXPAND, 0);}
  ;

STATEMENT_FUNC_DEF
  : VAR_TYPE IDN LEFT_PAREN RIGHT_PAREN STATEMENTS_BLOCK {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_FUNC_DEF, 5);}
  | VAR_TYPE IDN LEFT_PAREN RECV_ARGS RECV_ARGS_EXPAND RIGHT_PAREN STATEMENTS_BLOCK {lexer.addNonTerminalNode(SymbolKind.S_STATEMENT_FUNC_DEF, 7);}
  ;

RECV_ARGS
  : VAR_TYPE IDN {lexer.addNonTerminalNode(SymbolKind.S_RECV_ARGS, 2);}
  | VAR_TYPE STAR IDN {lexer.addNonTerminalNode(SymbolKind.S_RECV_ARGS, 3);}
  | VAR_TYPE IDN ARRAY_SUFFIX {lexer.addNonTerminalNode(SymbolKind.S_RECV_ARGS, 3);}
  ;

RECV_ARGS_EXPAND
  : COMMA RECV_ARGS RECV_ARGS_EXPAND {lexer.addNonTerminalNode(SymbolKind.S_RECV_ARGS_EXPAND, 3);}
  | {lexer.addNonTerminalNode(SymbolKind.S_RECV_ARGS_EXPAND, 0);}
  ;
