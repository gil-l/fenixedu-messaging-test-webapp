package org.fenixedu.messaging.test.task;

import org.fenixedu.bennu.core.domain.Bennu;
import org.fenixedu.bennu.scheduler.annotation.Task;
import org.fenixedu.bennu.scheduler.custom.CustomTask;

@Task(englishTitle = "Create Sender", readOnly = false)
public class CreateSenderTask extends CustomTask {
    @Override
    public void runTask() throws Exception {
        Bennu.getInstance().setMessagingSystem(null);
    }
}
