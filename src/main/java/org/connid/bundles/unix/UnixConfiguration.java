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
package org.connid.bundles.unix;

import org.connid.bundles.unix.utilities.Constants;
import org.connid.bundles.unix.utilities.DefaultProperties;
import org.identityconnectors.common.StringUtil;
import org.identityconnectors.common.security.GuardedString;
import org.identityconnectors.framework.common.exceptions.ConfigurationException;
import org.identityconnectors.framework.spi.AbstractConfiguration;
import org.identityconnectors.framework.spi.ConfigurationProperty;

public class UnixConfiguration extends AbstractConfiguration {

    private String hostname = "";

    private int port = 0;

    private String admin = "";

    private GuardedString password = null;

    private boolean createHomeDirectory = false;

    private boolean deleteHomeDirectory = false;

    private String shell = "";

    private boolean root = false;
    
    private boolean usePty = true;
    
    private String ptyType = "vt100";

    private GuardedString sudoPassword = null;

    private long readTimeout = 10000;
    
    private long timeToWait = 100;
    

    
    private int sshConnectionTimeout = 5000; 
    
    @ConfigurationProperty(displayMessageKey = "unix.admin.display",
    helpMessageKey = "unix.admin.help", order = 1)
    public final String getAdmin() {
        return admin;
    }

    public final void setAdmin(final String admin) {
        this.admin = admin;
    }

    @ConfigurationProperty(displayMessageKey = "unix.hostname.display",
    helpMessageKey = "unix.hostname.help", order = 3)
    public final String getHostname() {
        return hostname;
    }

    public final void setHostname(final String hostname) {
        this.hostname = hostname;
    }

    @ConfigurationProperty(displayMessageKey = "unix.password.display",
    helpMessageKey = "unix.password.help", order = 2)
    public final GuardedString getPassword() {
        return password;
    }

    public final void setPassword(final GuardedString password) {
        this.password = password;
    }

    @ConfigurationProperty(displayMessageKey = "unix.port.display",
    helpMessageKey = "unix.port.help", order = 4)
    public final int getPort() {
        return port;
    }

    public final void setPort(final int port) {
        this.port = port;
    }

    @ConfigurationProperty(displayMessageKey = "unix.createhomedir.display",
    helpMessageKey = "unix.createhomedir.help", order = 5)
    public final boolean isCreateHomeDirectory() {
        return createHomeDirectory;
    }
    
        public final void setCreateHomeDirectory(
            final boolean createHomeDirectory) {
        this.createHomeDirectory = createHomeDirectory;
    }

    @ConfigurationProperty(displayMessageKey = "unix.deletehomedir.display",
    helpMessageKey = "unix.deletehomedir.help", order = 6)
    public final boolean isDeleteHomeDirectory() {
        return deleteHomeDirectory;
    }

    public final void setDeleteHomeDirectory(
            final boolean deleteHomeDirectory) {
        this.deleteHomeDirectory = deleteHomeDirectory;
    }

//    @ConfigurationProperty(displayMessageKey = "unix.basehomedir.display",
//    helpMessageKey = "unix.basehomedir.help", order = 7)
//    public final String getBaseHomeDirectory() {
//        return baseHomeDirectory;
//    }
//
//    public final void setBaseHomeDirectory(final String baseHomeDirectory) {
//        this.baseHomeDirectory = baseHomeDirectory;
//    }

    @ConfigurationProperty(displayMessageKey = "unix.shell.display",
    helpMessageKey = "unix.shell.help", order = 8)
    public final String getShell() {
        return shell;
    }

    public final void setShell(final String shell) {
        this.shell = shell;
    }

    @ConfigurationProperty(displayMessageKey = "unix.isroot.display",
    helpMessageKey = "unix.isroot.help", order = 9)
    public boolean isRoot() {
        return root;
    }

    @ConfigurationProperty(displayMessageKey = "unix.usepty.display",
    	    helpMessageKey = "unix.usepty.help", order = 10)
    public boolean isUsePty() {
		return usePty;
	}
    
    public void setUsePty(boolean usePty) {
		this.usePty = usePty;
	}
    
    @ConfigurationProperty(displayMessageKey = "unix.ptytype.display",
    	    helpMessageKey = "unix.ptytype.help", order = 11)
    public String getPtyType() {
		return ptyType;
	}
    
    public void setPtyType(String ptyType) {
		this.ptyType = ptyType;
	}
    
    @ConfigurationProperty(displayMessageKey = "unix.ssh.connection.timeout.display",
    helpMessageKey = "unix.ssh.connection.timeout.help", order = 12)
    public int getSshConnectionTimeout() {
		return sshConnectionTimeout;
	}

    public void setSshConnectionTimeout(int sshConnectionTimeout) {
		this.sshConnectionTimeout = sshConnectionTimeout;
	}

//    @ConfigurationProperty(displayMessageKey = "unix.homedirattr.display",
//    helpMessageKey = "unix.homedirattr.help", order = 11)
//    public String getHomeDirectoryAttribute() {
//        return homeDirectoryAttribute;
//    }
//
//    public void setHomeDirectoryAttribute(String homeDirectoryAttribute) {
//        this.homeDirectoryAttribute = homeDirectoryAttribute;
//    }
//
//    @ConfigurationProperty(displayMessageKey = "unix.shellattr.display",
//    helpMessageKey = "unix.shellattr.help", order = 12)
//    public String getShellAttribute() {
//        return shellAttribute;
//    }
//
//    public void setShellAttribute(String shellAttribute) {
//        this.shellAttribute = shellAttribute;
//    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    @ConfigurationProperty(displayMessageKey = "unix.sudopwd.display",
    helpMessageKey = "unix.sudopwd.help", order = 13)
    public GuardedString getSudoPassword() {
        return sudoPassword;
    }

    public void setSudoPassword(GuardedString sudoPassword) {
        this.sudoPassword = sudoPassword;
    }
    
    @ConfigurationProperty(displayMessageKey = "unix.readtimeout.display",
    	    helpMessageKey = "unix.readtimeout.help", order = 14)
    public long getReadTimeout() {
		return readTimeout;
	}
    
    public void setReadTimeout(long readTimeout) {
		this.readTimeout = readTimeout;
	}
   
    @ConfigurationProperty(displayMessageKey = "unix.timetowait.display",
    	    helpMessageKey = "unix.timetowait.help", order = 14)
   public long getTimeToWait() {
	return timeToWait;
}
   
   public void setTimeToWait(long timeToWait) {
	this.timeToWait = timeToWait;
}

    @Override
    public final void validate() {
        if (StringUtil.isBlank(admin)) {
            throw new ConfigurationException("Unix admin username must not be blank!");
        }
        if (StringUtil.isBlank(hostname)) {
            throw new ConfigurationException("Unix hostname must not be blank!");
        }
        if (StringUtil.isBlank(password.toString())) {
            throw new ConfigurationException("Unix admin password must not be blank!");
        }
        if (StringUtil.isBlank(String.valueOf(port))) {
            port = Constants.SSH_DEFAULT_PORT;
        }
        if (port < 0 && port <= Constants.UNIX_LAST_PORT) {
            throw new ConfigurationException("Unix ssh port range: 0 - 65535");
        }
//        if (StringUtil.isBlank(baseHomeDirectory)) {
//            baseHomeDirectory = DefaultProperties.UNIX_USER_HOMEDIRECTORY;
//        }
        if (StringUtil.isBlank(
                Boolean.valueOf(createHomeDirectory).toString())) {
            createHomeDirectory = false;
        }
        if (StringUtil.isBlank(
                Boolean.valueOf(deleteHomeDirectory).toString())) {
            deleteHomeDirectory = false;
        }
        if (StringUtil.isBlank(shell)) {
            shell = DefaultProperties.UNIX_SHELL;
        }
//        if (StringUtil.isBlank(commentAttribute)) {
//            commentAttribute = DefaultProperties.COMMENT_ATTRIBUTE;
//
//        }
//        if (StringUtil.isBlank(shellAttribute)) {
//            shellAttribute = DefaultProperties.SHELL_ATTRIBUTE;
//
//        }
//        if (StringUtil.isBlank(homeDirectoryAttribute)) {
//            homeDirectoryAttribute = DefaultProperties.HOMEDIRECTORY_ATTRIBUTE;
//
//        }
        if ((!root) && (StringUtil.isBlank(sudoPassword.toString()))) {
            throw new ConfigurationException("Unix connector needs sudo password or root password");
        }
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (!(obj instanceof UnixConfiguration)){
    		return false;
    	}
    	
    	UnixConfiguration other = (UnixConfiguration) obj;
    	if (admin != null ? !admin.equals(other.admin) : other.admin != null) return false;
    	if (hostname != null ? !hostname.equals(other.hostname) : other.hostname != null) return false;
    	if (password != null ? !password.equals(other.password) : other.password != null) return false;
    	if (port != other.port ) return false;
    	
    	return true;
    }
    
    @Override
    public int hashCode() {
    	int result = super.hashCode();
        result = 31 * result + (admin != null ? admin.hashCode() : 0);
        result = 31 * result + (hostname != null ? hostname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (port);
        
        return result;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(admin).append("@").append(hostname).append(":").append(port);
    	return sb.toString();
    }
}
