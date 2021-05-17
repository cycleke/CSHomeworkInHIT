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
