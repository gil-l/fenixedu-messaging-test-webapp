package org.fenixedu.messaging.test.task;

import java.util.Collections;
import java.util.Set;

import org.fenixedu.bennu.core.domain.Bennu;
import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.core.domain.UserProfile;
import org.fenixedu.bennu.core.groups.Group;
import org.fenixedu.bennu.core.groups.UserGroup;
import org.fenixedu.bennu.scheduler.annotation.Task;
import org.fenixedu.bennu.scheduler.custom.CustomTask;
import org.fenixedu.messaging.domain.Message.MessageBuilder;
import org.fenixedu.messaging.domain.MessageDeletionPolicy;
import org.fenixedu.messaging.domain.ReplyTo;
import org.fenixedu.messaging.domain.Sender;

@Task(englishTitle = "Create Sender", readOnly = false)
public class CreateSenderTask extends CustomTask {
    @Override
    public void runTask() throws Exception {
        Set<User> users = Bennu.getInstance().getUserSet();
        boolean htmlSender = true;
        Group allUsers = UserGroup.of(users);
        for (User user : users) {
            UserProfile profile = user.getProfile();
            Group userGroup = UserGroup.of(Collections.singleton(user));
            Sender sender =
                    new Sender(profile.getFullName(), profile.getEmail(), userGroup,
                            MessageDeletionPolicy.keepAmountOfMessages(10));
            sender.setHtmlSender(htmlSender);
            htmlSender = !htmlSender;
            sender.addReplyTo(user);
            sender.addReplyTo("noreply@test.com");
            sender.addRecipient(userGroup);
            sender.addRecipient(allUsers);
            MessageBuilder b =
                    new MessageBuilder(sender, "An Example Message", "My name is Commander " + profile.getFamilyNames()
                            + " and this is my favourite application in Fenix.");
            b.bcc("one@test.com");
            b.bcc("two@test.com");
            b.cc(allUsers);
            b.to(userGroup);
            b.replyTo(ReplyTo.user(user));
        }
    }
}
