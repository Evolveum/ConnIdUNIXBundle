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
package org.connid.bundles.unix.commands;

import java.util.Set;

import org.connid.bundles.unix.UnixConfiguration;
import org.connid.bundles.unix.commands.OptionBuilder.Operation;
import org.identityconnectors.framework.common.objects.Attribute;

public class UserAdd {

    private UnixConfiguration unixConfiguration = null;

    /**
     * useradd - create a new user or update default new user information.
     */
    private static final String USERADD_COMMAND = "useradd";

    /**
     * Create the user's home directory if it does not exist. The files and directories contained in the skeleton
     * directory (which can be defined with the -k option) will be copied to the home directory. By default, no home
     * directories are created.
     */
    private static final String CREATE_HOME_DIR_OPTION = "-m";

    /**
     * The default base directory for the system if -d HOME_DIR is not specified. BASE_DIR is concatenated with the
     * account name to define the home directory. If the -m option is not used, BASE_DIR must exist. If this option is
     * not specified, useradd will use the base directory specified by the HOME variable in /etc/default/useradd, or
     * /home by default.
     */
//    private static final String BASE_HOME_DIR_OPTION = "-b";

    /**
     * The name of the user's login shell. The default is to leave this field blank, which causes the system to select
     * the default login shell specified by the SHELL variable in /etc/default/useradd, or an empty string by default.
     */
//    private static final String SHELL_OPTION = "-s";

    /**
     * Any text string. It is generally a short description of the login, and is currently used as the field for the
     * user's full name.
     *
     */
    /**
     * The encrypted password, as returned by crypt(3). The default is to disable the password. Note: This option is not
     * recommended because the password (or encrypted password) will be visible by users listing the processes. You
     * should make sure the password respects the system's password policy.
     *
     */
    private String username = "";
    
    private Set<Attribute> attributes = null;
 
    public UserAdd(final UnixConfiguration configuration, String username, Set<Attribute> attributes) {
        unixConfiguration = configuration;
        this.username = username;
        this.attributes = attributes;
    }

    private String createUserAddCommand() {
        StringBuilder useraddCommand = new StringBuilder(USERADD_COMMAND);
        useraddCommand.append(" ").append(OptionBuilder.buildUserCommandOptions(attributes, Operation.CREATE));
        if (unixConfiguration.isCreateHomeDirectory()) {
            useraddCommand.append(CREATE_HOME_DIR_OPTION).append(" ");
        }
        useraddCommand.append(username);
        return useraddCommand.toString();
    }

   
	public String useradd() {
        return createUserAddCommand();
    }
    
 
}
