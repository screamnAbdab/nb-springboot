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
package com.github.alexfalappa.nbspringboot.cfgprops.lexer;

import org.netbeans.api.lexer.Language;
import org.netbeans.modules.csl.spi.DefaultLanguageConfig;
import org.netbeans.modules.csl.spi.LanguageRegistration;
import org.openide.util.NbBundle;

/**
 * NetBeans language definition for Spring Boot configuration properties.
 *
 * @author Alessandro Falappa
 */
@LanguageRegistration(mimeType = CfgPropsLanguage.MIME_TYPE)
public class CfgPropsLanguage extends DefaultLanguageConfig {

    public static final String MIME_TYPE = "text/application+properties"; //NOI18N

    @Override
    public Language getLexerLanguage() {
        return CfgPropsTokenId.language();
    }

    @Override
    public String getDisplayName() {
        return NbBundle.getMessage(getClass(), MIME_TYPE);
    }

    @Override
    public String getLineCommentPrefix() {
        return "#"; //NOI18N
    }

}
