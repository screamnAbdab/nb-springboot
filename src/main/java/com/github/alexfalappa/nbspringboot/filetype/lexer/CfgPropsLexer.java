/*
 * Copyright 2017 Alessandro Falappa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.alexfalappa.nbspringboot.filetype.lexer;

import org.netbeans.api.lexer.Token;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerInput;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 *
 * @author Alessandro Falappa
 */
public class CfgPropsLexer implements Lexer<CfgPropsTokenId> {

    private final LexerRestartInfo<CfgPropsTokenId> info;
    private int state = 0;

    CfgPropsLexer(LexerRestartInfo<CfgPropsTokenId> info) {
        this.info = info;
        if (info.state() != null) {
            state = (Integer) info.state();
        }
    }

    @Override
    public Token<CfgPropsTokenId> nextToken() {
        LexerInput input = info.input();
        if (state == 0) {
            int i = input.read();
            if (i == '#') {
                do {
                    i = input.read();
                } while (i != '\n'
                        && i != '\r'
                        && i != LexerInput.EOF);
                do {
                    i = input.read();
                } while (i == '\n'
                        || i == '\r');
                input.backup(1);
                state = 0;
                return info.tokenFactory().createToken(CfgPropsTokenId.COMMENT);
            }
            if (i == ':') {
                i = input.read();
            }
            while (i != '\n'
                    && i != '\r'
                    && i != ':'
                    && i != LexerInput.EOF) {
                i = input.read();
            }
            if (i == '\n' || i == '\r') {
                do {
                    i = input.read();
                } while (i == '\n'
                        || i == '\r');
            }
            if (i != LexerInput.EOF) {
                input.backup(1);
            }
            state = i == ':' ? 1 : 0;
            if (input.readLength() == 0) {
                return null;
            }
            return info.tokenFactory().createToken(CfgPropsTokenId.KEY);
        }
        if (state == 1) {
            input.read();
            state = 2;
            return info.tokenFactory().createToken(CfgPropsTokenId.EQUALS);
        }
        int i = 0;
        do {
            i = input.read();
            while (i != '\n'
                    && i != '\r'
                    && i != LexerInput.EOF) {
                i = input.read();
            }
            do {
                i = input.read();
            } while (i == '\n'
                    || i == '\r');
        } while (i == ' ');
        if (i != LexerInput.EOF) {
            input.backup(1);
        }
        state = 0;
        if (input.readLength() == 0) {
            return null;
        }
        return info.tokenFactory().createToken(CfgPropsTokenId.VALUE);
    }

    @Override
    public Object state() {
        return state;
    }

    @Override
    public void release() {
    }
}
