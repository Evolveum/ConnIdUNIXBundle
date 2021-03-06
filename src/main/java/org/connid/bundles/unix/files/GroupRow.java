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
package org.connid.bundles.unix.files;

public class GroupRow {

    private String groupname = "";
    private String passwordValidator = "";
    private String groupIdentifier = "";
    
    public String getGroupIdentifier() {
        return groupIdentifier;
    }

    public void setGroupIdentifier(String groupIdentifier) {
        this.groupIdentifier = groupIdentifier;
    }

    public String getPasswordValidator() {
        return passwordValidator;
    }

    public void setPasswordValidator(String passwordValidator) {
        this.passwordValidator = passwordValidator;
    }

    public String getGroupname() {
		return groupname;
	}
    
    public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
}
