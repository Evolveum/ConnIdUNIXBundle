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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.connid.bundles.unix.schema.SchemaAccountAttribute;
import org.connid.bundles.unix.utilities.EvaluateCommandsResultOutput;
import org.identityconnectors.framework.common.objects.Uid;

public class PasswdFile {

    private List<PasswdRow> passwdRows = new ArrayList<PasswdRow>();

    public PasswdFile(final List<String> passwdFile) {
        setPasswdRows(passwdFile);
    }
    
    public List<PasswdRow> getPasswdRows() {
		return passwdRows;
	}

    private void setPasswdRows(final List<String> passwdFile) {
        for (Iterator<String> it = passwdFile.iterator(); it.hasNext();) {
            passwdRows.add(EvaluateCommandsResultOutput.toPasswdRow(it.next()));
        }
    }

    public final List<PasswdRow> searchRowByAttribute(final String attributeName, final String attributeValue, final boolean not) {
        List<PasswdRow> userRow = new ArrayList<PasswdRow>();
        for (Iterator<PasswdRow> it = passwdRows.iterator(); it.hasNext();) {
            PasswdRow passwdRow = it.next();
			if (attributeName.equals(SchemaAccountAttribute.NAME.getName()) || attributeName.equals(Uid.NAME)) {
				if (attributeValue.equalsIgnoreCase(passwdRow.getUsername())) {
					userRow.add(passwdRow);
				}
			}
			if (attributeName.equals(SchemaAccountAttribute.SHEL.getName())) {
				if (attributeValue.equalsIgnoreCase(passwdRow.getShell())) {
					userRow.add(passwdRow);
				}
			}
			if (attributeName.equals(SchemaAccountAttribute.COMMENT.getName())) {

				if (attributeValue.equalsIgnoreCase(passwdRow.getComment())) {
					userRow.add(passwdRow);
				}
			}
			if (attributeName.equals(SchemaAccountAttribute.HOME.getName())) {

				if (attributeValue.equalsIgnoreCase(passwdRow.getHomeDirectory())) {
					userRow.add(passwdRow);
				}
			}
        }
        return userRow;
    }

    public List<PasswdRow> searchRowByStartsWithValue(final String attributeName,
            final String startWithValue) {
        List<PasswdRow> userRow = new ArrayList<PasswdRow>();
        for (Iterator<PasswdRow> it = passwdRows.iterator(); it.hasNext();) {
            PasswdRow passwdRow = it.next();
            if (attributeName.equals(SchemaAccountAttribute.NAME.getName()) || attributeName.equals(Uid.NAME)) {
            	 if (passwdRow.getUsername().startsWith(startWithValue)) {
                     userRow.add(passwdRow);
                 }
			}
			if (attributeName.equals(SchemaAccountAttribute.SHEL.getName())) {
				if (passwdRow.getShell().startsWith(startWithValue)) {
	                userRow.add(passwdRow);
	            }
			}
			if (attributeName.equals(SchemaAccountAttribute.COMMENT.getName())) {

				 if (passwdRow.getComment().startsWith(startWithValue)) {
		                userRow.add(passwdRow);
		            }
			}
			if (attributeName.equals(SchemaAccountAttribute.HOME.getName())) {

				 if (passwdRow.getHomeDirectory().startsWith(startWithValue)) {
		                userRow.add(passwdRow);
		            }
			}
           
        }
        return userRow;
    }

    public List<PasswdRow> searchRowByEndsWithValue(final String attributeName, 
            final String endsWithValue) {
        List<PasswdRow> userRow = new ArrayList<PasswdRow>();
        for (Iterator<PasswdRow> it = passwdRows.iterator(); it.hasNext();) {
            PasswdRow passwdRow = it.next();
            if (attributeName.equals(SchemaAccountAttribute.NAME.getName()) || attributeName.equals(Uid.NAME)) {
            	 if (passwdRow.getUsername().endsWith(endsWithValue)) {
                     userRow.add(passwdRow);
                 }
			}
			if (attributeName.equals(SchemaAccountAttribute.SHEL.getName())) {
				if (passwdRow.getShell().endsWith(endsWithValue)) {
	                userRow.add(passwdRow);
	            }
			}
			if (attributeName.equals(SchemaAccountAttribute.COMMENT.getName())) {

				if (passwdRow.getComment().endsWith(endsWithValue)) {
	                userRow.add(passwdRow);
	            }
			}
			if (attributeName.equals(SchemaAccountAttribute.HOME.getName())) {
				if (passwdRow.getHomeDirectory().endsWith(endsWithValue)) {
	                userRow.add(passwdRow);
	            }
			}
            
        }
        return userRow;
    }

    public List<PasswdRow> searchRowByContainsValue(String attributeName, String containsValue) {
        List<PasswdRow> userRow = new ArrayList<PasswdRow>();
        for (Iterator<PasswdRow> it = passwdRows.iterator(); it.hasNext();) {
            PasswdRow passwdRow = it.next();
            if (attributeName.equals(SchemaAccountAttribute.NAME.getName()) || attributeName.equals(Uid.NAME)) {
            	if (passwdRow.getUsername().contains(containsValue)) {
                    userRow.add(passwdRow);
                }
			}
			if (attributeName.equals(SchemaAccountAttribute.SHEL.getName())) {
				 if (passwdRow.getShell().contains(containsValue)) {
		                userRow.add(passwdRow);
		            }
			}
			if (attributeName.equals(SchemaAccountAttribute.COMMENT.getName())) {
				 if (passwdRow.getComment().contains(containsValue)) {
		                userRow.add(passwdRow);
		            }
			}
			if (attributeName.equals(SchemaAccountAttribute.HOME.getName())) {
				 if (passwdRow.getHomeDirectory().contains(containsValue)) {
		                userRow.add(passwdRow);
		            }
			}
           
           
        }
        return userRow;
    }
}
