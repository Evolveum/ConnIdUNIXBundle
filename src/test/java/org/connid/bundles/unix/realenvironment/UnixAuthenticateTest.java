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
package org.connid.bundles.unix.realenvironment;

import java.util.HashSet;
import java.util.Set;

import org.connid.bundles.unix.UnixConnector;
import org.connid.bundles.unix.search.Operand;
import org.connid.bundles.unix.search.Operator;
import org.connid.bundles.unix.utilities.AttributesTestValue;
import org.connid.bundles.unix.utilities.SharedTestMethods;
import org.identityconnectors.common.security.GuardedString;
import org.identityconnectors.framework.common.exceptions.ConnectorException;
import org.identityconnectors.framework.common.objects.ConnectorObject;
import org.identityconnectors.framework.common.objects.Name;
import org.identityconnectors.framework.common.objects.ObjectClass;
import org.identityconnectors.framework.common.objects.ResultsHandler;
import org.identityconnectors.framework.common.objects.Uid;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UnixAuthenticateTest extends SharedTestMethods {

    private UnixConnector connector = null;

    private Name name = null;

    private Uid newAccount = null;

    private AttributesTestValue attrs = null;

    @Before
    public final void initTest() {
        attrs = new AttributesTestValue();
        connector = new UnixConnector();
        connector.init(createConfiguration());
        name = new Name(attrs.getUsername());
    }

    @Test
    public final void authenticateTest() {
        newAccount = connector.create(ObjectClass.ACCOUNT,
                createSetOfAttributes(name, attrs.getPassword(), true), null);
        Assert.assertEquals(name.getNameValue(), newAccount.getUidValue());
        final Uid authUid = connector.authenticate(ObjectClass.ACCOUNT, newAccount.getUidValue(),
                attrs.getGuardedPassword(), null);
        Assert.assertEquals(newAccount.getUidValue(), authUid.getUidValue());
        connector.delete(ObjectClass.ACCOUNT, newAccount, null);
    }
    
    @Test
    public final void authenticateTechUserTest() {
    	
    	final Uid authUid = connector.authenticate(ObjectClass.ACCOUNT, "midpoint",
                new GuardedString("secret".toCharArray()), null);
        Assert.assertEquals("midpoint", authUid.getUidValue());
//        connector.delete(ObjectClass.ACCOUNT, newAccount, null);
    }

    @Test(expected = ConnectorException.class)
    public final void authFailedTest() {
        connector.authenticate(ObjectClass.ACCOUNT,
                attrs.getWrongUsername(),
                attrs.getWrongGuardedPassword(), null);
    }

    @Test(expected = ConnectorException.class)
    public final void authTestWithWrongPassword() {
        connector.authenticate(ObjectClass.ACCOUNT, name.getNameValue(),
                attrs.getWrongGuardedPassword(), null);
    }

    @Test(expected = ConnectorException.class)
    public final void authTestWithWrongUsername() {
        connector.authenticate(ObjectClass.ACCOUNT,
                attrs.getWrongUsername(), attrs.getGuardedPassword(), null);
    }

    @Test(expected = ConnectorException.class)
    public void authTestWithWrongObjectClass() {
        connector.authenticate(attrs.getWrongObjectClass(),
                attrs.getUsername(), attrs.getGuardedPassword(), null);
    }

    @Test(expected = ConnectorException.class)
    public final void authTestWithNullPassword() {
        connector.authenticate(ObjectClass.ACCOUNT, name.getNameValue(),
                null, null);
    }

    @Test(expected = ConnectorException.class)
    public final void authTestWithNullUsername() {
        connector.authenticate(ObjectClass.ACCOUNT, null,
                attrs.getWrongGuardedPassword(), null);
    }

    @Test(expected = ConnectorException.class)
    public final void authTestWithAllNull() {
        connector.authenticate(null, null, null, null);
    }

    @After
    public final void close() {
        connector.dispose();
    }
}
