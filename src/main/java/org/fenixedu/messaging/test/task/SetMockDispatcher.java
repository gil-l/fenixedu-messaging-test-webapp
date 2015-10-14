package org.fenixedu.messaging.test.task;

import org.fenixedu.bennu.scheduler.annotation.Task;
import org.fenixedu.bennu.scheduler.custom.CustomTask;
import org.fenixedu.messaging.core.domain.MessagingSystem;
import org.fenixedu.messaging.test.mock.MockDispatcher;

@Task(englishTitle = "Set Mock Dispatcher", readOnly = false)
public class SetMockDispatcher extends CustomTask {
    @Override
    public void runTask() throws Exception {
        MessagingSystem.setMessageDispatcher(new MockDispatcher());
    }
}
