# -*- coding: utf-8 -*-
"""
Token and TokenKind class.
"""

from enum import Enum, auto as eauto
from typing import Optional, Tuple


class TokenKind(Enum):
    # specials
    IDN = eauto()
    COMMENT = eauto()
    EOF = eauto()
    ERROR = eauto()

    # const values
    CONST_INTEGER = eauto()
    CONST_FLOAT = eauto()
    CONST_STRING = eauto()
    CONST_CHARACTER = eauto()

    # punctuation
    PLUS = eauto()  # +
    MINUS = eauto()  # -
    STAR = eauto()  # *
    SLASH = eauto()  # /
    PERCENT = eauto()  # %
    INC = eauto()  # ++
    DEC = eauto()  # --

    GT = eauto()  # >
    GE = eauto()  # >=
    EQ = eauto()  # ==
    LE = eauto()  # <=
    LT = eauto()  # <
    NE = eauto()  # !=

    AND = eauto()  # &&
    OR = eauto()  # ||
    NOT = eauto()  # !

    AMPERSAND = eauto()  # &
    VERT_LINE = eauto()  # |
    TILDE = eauto()  # ~
    CARET = eauto()  # ^
    LSHIFT = eauto()  # <<
    RSHIFT = eauto()  # >>

    ASSIGN = eauto()  # =
    ADD_ASSIGN = eauto()  # +=
    SUB_ASSIGN = eauto()  # -=
    MUL_ASSIGN = eauto()  # *=
    DIV_ASSIGN = eauto()  # /=
    MOD_ASSIGN = eauto()  # %=
    OR_ASSIGN = eauto()  # |=
    AND_ASSIGN = eauto()  # &=
    XOR_ASSIGN = eauto()  # ^=
    LSHIFT_ASSIGN = eauto()  # <<=
    RSHIFT_ASSIGN = eauto()  # >>=

    SEMICOLON = eauto()  # ;
    COMMA = eauto()  # ,
    DOT = eauto()  # .
    ARROW = eauto()  # ->
    QUESTION = eauto()  # ?
    LEFT_BRACE = eauto()  # {
    LEFT_PAREN = eauto()  # (
    LEFT_SQUARE = eauto()  # [
    RIGHT_BRACE = eauto()  # }
    RIGHT_PAREN = eauto()  # )
    RIGHT_SQUARE = eauto()  # ]

    # keywords
    CHAR = eauto()
    SHORT = eauto()
    INT = eauto()
    LONG = eauto()
    FLOAT = eauto()
    DOUBLE = eauto()
    BOOL = eauto()
    VOID = eauto()
    SIGNED = eauto()
    UNSIGNED = eauto()
    IF = eauto()
    ELSE = eauto()
    SWITCH = eauto()
    CASE = eauto()
    DEFAULT = eauto()
    DO = eauto()
    WHILE = eauto()
    CONTINUE = eauto()
    BREAK = eauto()
    FOR = eauto()
    RETURN = eauto()
    STRUCT = eauto()
    ENUM = eauto()
    UNION = eauto()
    EXTERN = eauto()
    INLINE = eauto()
    GOTO = eauto()
    REGISTER = eauto()
    SIZEOF = eauto()
    TRUE = eauto()
    FALSE = eauto()
    FN = eauto()

    def __str__(self) -> str:
        return self.name


STR2TOKEN_KIND = dict(map(lambda kind: (kind.name, kind), TokenKind))
KEYWORDS_KINDS = {
    TokenKind.CHAR, TokenKind.SHORT, TokenKind.INT, TokenKind.LONG,
    TokenKind.FLOAT, TokenKind.DOUBLE, TokenKind.BOOL, TokenKind.VOID,
    TokenKind.SIGNED, TokenKind.UNSIGNED, TokenKind.IF, TokenKind.ELSE,
    TokenKind.SWITCH, TokenKind.CASE, TokenKind.DEFAULT, TokenKind.DO,
    TokenKind.WHILE, TokenKind.CONTINUE, TokenKind.BREAK, TokenKind.FOR,
    TokenKind.RETURN, TokenKind.STRUCT, TokenKind.ENUM, TokenKind.UNION,
    TokenKind.EXTERN, TokenKind.INLINE, TokenKind.GOTO, TokenKind.REGISTER,
    TokenKind.SIZEOF, TokenKind.TRUE, TokenKind.FALSE, TokenKind.FN
}


def is_keyword(word: str) -> Optional[TokenKind]:
    '''return the keyword type if word is a keyword'''
    kind = STR2TOKEN_KIND.get(word.upper())
    return kind if kind in KEYWORDS_KINDS else None


class Token:
    def __init__(self, kind: TokenKind, value, start: Tuple[int, int],
                 end: Tuple[int, int]) -> None:
        self.kind = kind
        self.value = value
        self.start = start
        self.end = end

    def __str__(self):
        return "{}, _".format(
            self.kind.name) if self.value is None else "{}, {}".format(
                self.kind.name, repr(self.value))
