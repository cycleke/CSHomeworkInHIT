# -*- coding: utf-8 -*-
"""
The class Lexer can load DFA and parse text to tokens.
"""

import json
import sys
from typing import List
from lextoken import STR2TOKEN_KIND, TokenKind, Token, is_keyword


class Lexer:
    def __init__(self, dfa_table: dict) -> None:
        self._status = dfa_table.get("status")
        self._start = dfa_table.get("start")
        self._accept = dfa_table.get("accept")
        trans = dfa_table.get("trans")
        self._trans = {}
        for stat, table in trans.items():
            goto = {}
            for s, nxt in table.items():
                for ch in s:
                    goto[ch] = nxt
            self._trans[stat] = goto

    @staticmethod
    def load_lexer(path):
        with open(path, 'r') as dfa_file:
            dfa_table = json.load(dfa_file)
        return Lexer(dfa_table)

    def parse(self, context: str) -> List[Token]:
        tokens: List[Token] = []

        stat = self._start
        trans = self._get_status_trans(stat)
        last, curr, length = 0, 0, len(context)

        while curr < length:
            ch = context[curr]
            able_to_trans = ch in trans
            is_accept_stat = self._is_accept_stat(stat)

            if able_to_trans or is_accept_stat:
                if able_to_trans:
                    next_stat = trans[ch]
                else:
                    curr -= 1
                    next_stat = self._start

                if next_stat == self._start:
                    if str.isspace(context[last:curr + 1]):
                        curr += 1
                        last = curr
                        continue
                    token = self._parse_token(context, last, curr, stat)
                    tokens.append(token)
                    last = curr + 1
                stat = next_stat
                trans = self._get_status_trans(stat)
            else:
                error_token = Token(
                    TokenKind.ERROR, "Unexpected charactor {}".format(
                        repr(context[last:curr + 1])), (last, 0), (curr, 0))
                tokens.append(error_token)
                last = curr + 1
                stat = self._start
                trans = self._get_status_trans(stat)

            curr += 1

        if last != curr:
            if self._is_accept_stat(stat):
                token = self._parse_token(context, last, curr + 1, stat)
                tokens.append(token)
            else:
                error_token = Token(
                    TokenKind.ERROR, "Unexpected charactor {}".format(
                        repr(context[last:curr + 1])), (last, 0), (curr, 0))
                tokens.append(error_token)

        return Lexer._trans_position(context, tokens)

    @staticmethod
    def _trans_position(context: str, tokens: List[Token]) -> List[Token]:
        row, col = 0, 0
        i, j, n, m = 0, 0, len(context), len(tokens)

        while i < n and j < m:
            if i > 0 and context[i - 1] == '\n':
                row += 1
                col = 0

            if tokens[j].start[0] == i:
                tokens[j].start = (row + 1, col + 1)
            if tokens[j].end[0] == i:
                tokens[j].end = (row + 1, col + 1)
                j += 1

            i += 1
            col += 1

        return tokens

    def _get_status_trans(self, stat) -> dict:
        return self._trans.get(stat, {})

    def _is_accept_stat(self, stat) -> bool:
        return stat in self._accept

    def _parse_token(self, context: str, start, end, stat):
        raw = context[start:end + 1]
        start = (start, 0)
        end = (end, 0)
        if stat in self._status:
            kind_str = self._status[stat]
            if kind_str == "IDN":
                keyword_kind = is_keyword(raw)
                if keyword_kind:
                    return Token(keyword_kind, None, start, end)
                return Token(TokenKind.IDN, raw.strip(), start, end)

            kind = STR2TOKEN_KIND[kind_str]
            try:
                value = Lexer._PARSE_VALUE.get(kind, Lexer._parse_value)(raw)
                return Token(kind, value, start, end)
            except Exception as e:
                print(e)
                return Token(TokenKind.ERROR,
                             "Fail to parse {} to {}.".format(raw, kind.name),
                             start, end)
        else:
            return Token(TokenKind.ERROR, "Unknown token {}.".format(raw),
                         start, end)

    @staticmethod
    def _parse_int(raw: str) -> int:
        if raw.startswith(("0b", "0B")):
            return int(raw, 2)
        if raw.startswith(("0x", "0X")):
            return int(raw, 16)
        if (raw.startswith("0")):
            return int(raw, 8)
        return int(raw)

    @staticmethod
    def _parse_float(raw):
        return float(raw)

    @staticmethod
    def _parse_str(raw: str):
        return raw.encode().decode('unicode_escape')

    @staticmethod
    def _parse_char(raw):
        assert raw[0] == "'" and raw[-1] == "'"
        return raw[1:-1]

    @staticmethod
    def _parse_value(_: str):
        return None

    _PARSE_VALUE = {
        TokenKind.CONST_INTEGER: _parse_int.__func__,
        TokenKind.CONST_FLOAT: _parse_float.__func__,
        TokenKind.CONST_STRING: _parse_str.__func__,
        TokenKind.CONST_CHARACTER: _parse_char.__func__,
    }


if __name__ == "__main__":
    lexer = Lexer.load_lexer("src/lex_dfa.json")
    if len(sys.argv) == 1:
        file_name = "src/test.c"
    else:
        file_name = sys.argv[1]
    with open(file_name, "r") as f:
        tokens = lexer.parse(f.read())
        for token in tokens:
            if token.kind == TokenKind.ERROR:
                print("[ERROR] at {}:{} : {}".format(token.start[0],
                                                     token.start[1],
                                                     token.value))
            else:
                print("< {} >".format(token))
