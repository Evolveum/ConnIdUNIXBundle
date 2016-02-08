/**
 * Copyright (C) 2011 ConnId (connid-dev@googlegroups.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.connid.bundles.unix.methods;

import com.jcraft.jsch.JSchException;
import java.io.IOException;
import org.connid.bundles.unix.UnixConfiguration;
import org.connid.bundles.unix.UnixConnection;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.exceptions.ConnectorException;

public class UnixTest {

    private static final Log LOG = Log.getLog(UnixTest.class);

    private UnixConnection unixConnection = null;

    public UnixTest(final UnixConnection unixConnection) throws IOException, JSchException {
        this.unixConnection = unixConnection;
    }

    public final void test() {
        try {
            execute();
        } catch (Exception e) {
            LOG.error(e, "error during test connection");
            throw new ConnectorException(e);
        }
    }

    private void execute() throws Exception {
        unixConnection.testConnection();
    }
}
