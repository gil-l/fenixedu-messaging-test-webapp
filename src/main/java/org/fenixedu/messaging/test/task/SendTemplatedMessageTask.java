package org.fenixedu.messaging.test.task;

import org.fenixedu.bennu.core.domain.Bennu;
import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.core.groups.Group;
import org.fenixedu.bennu.core.security.Authenticate;
import org.fenixedu.bennu.scheduler.annotation.Task;
import org.fenixedu.bennu.scheduler.custom.CustomTask;
import org.fenixedu.messaging.core.domain.Message;

import java.util.Set;

@Task(englishTitle = "Send Templated Message", readOnly = false)
public class SendTemplatedMessageTask extends CustomTask {
    @Override
    public void runTask() throws Exception {
        Set<User> users = Bennu.getInstance().getUserSet();
        Authenticate.mock(users.iterator().next());
        Group allUsers = Group.users(users.stream());
        Message.fromSystem().subject("Example Message").bcc(allUsers).singleBcc("teste@messaging.gill.com").template("template2")
                .and().send();
    }
}
