package com.company.CommandTest;

import com.company.command.QCommand;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

public class QCommandTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
    QCommand qCommandTest;

    @Before
    public void setUp() {
        qCommandTest = new QCommand();
    }

    @Test
    public void testExecuteMethod() {
        exit.expectSystemExitWithStatus(0);
        qCommandTest.execute();
    }

}
