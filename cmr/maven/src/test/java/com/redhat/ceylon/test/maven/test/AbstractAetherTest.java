/*
 * Copyright 2014 Red Hat inc. and third party contributors as noted
 * by the author tags.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.redhat.ceylon.test.maven.test;

import java.io.File;
import java.net.URL;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.redhat.ceylon.cmr.api.CmrRepository;
import com.redhat.ceylon.cmr.impl.CMRJULLogger;
import com.redhat.ceylon.cmr.maven.AetherRepository;

/**
 * Abstract Aether tests.
 *
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public abstract class AbstractAetherTest {
    protected static final com.redhat.ceylon.common.log.Logger log = new CMRJULLogger();

    AbstractAetherTest() {
        // Force logger to log dammit!
        initLogger(Logger.getLogger("com.redhat.ceylon.log.cmr"), true);
    }

    protected CmrRepository createAetherRepository() throws Exception {
        URL settingsURL = getClass().getClassLoader().getResource("maven-settings/settings.xml");
        String settingsXml = new File(settingsURL.toURI()).getPath();
        return AetherRepository.createRepository(log, settingsXml, false, 60000);
    }
    
    protected static void initLogger(Logger logger, boolean verbose) {
        boolean handlersExists = false;
        for (Handler handler : logger.getHandlers()) {
            handlersExists = true;
            if (handler instanceof ConsoleHandler) {
                if (verbose) {
                    handler.setLevel(Level.ALL);
                }
            }
        }
        if (verbose) {
            logger.setLevel(Level.ALL);
            if (handlersExists == false) {
                ConsoleHandler handler = new ConsoleHandler();
                handler.setLevel(Level.ALL);
                logger.addHandler(handler);
            }
        }
    }
}
