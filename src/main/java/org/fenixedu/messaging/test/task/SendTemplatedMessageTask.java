package org.fenixedu.messaging.test.task;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import org.fenixedu.bennu.core.domain.Bennu;
import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.core.groups.Group;
import org.fenixedu.bennu.core.groups.UserGroup;
import org.fenixedu.bennu.core.security.Authenticate;
import org.fenixedu.bennu.scheduler.annotation.Task;
import org.fenixedu.bennu.scheduler.custom.CustomTask;
import org.fenixedu.messaging.domain.MessagingSystem;
import org.fenixedu.messaging.domain.Sender;

@Task(englishTitle = "Send Templated Message", readOnly = false)
public class SendTemplatedMessageTask extends CustomTask {
    @Override
    public void runTask() throws Exception {
        Sender sys = MessagingSystem.systemSender();
        Set<User> users = Bennu.getInstance().getUserSet();
        Authenticate.mock(users.iterator().next());
        Group allUsers = UserGroup.of(users);
        MessagingSystem.getTemplateById("template2").send(new HashMap<String, Object>(), sys, Collections.singleton(allUsers),
                Collections.singleton("teste@messaging.gill.com"));
    }
}
