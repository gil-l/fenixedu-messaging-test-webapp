package org.fenixedu.messaging.test.task;

import jersey.repackaged.com.google.common.collect.Sets;
import org.fenixedu.bennu.core.groups.DynamicGroup;
import org.fenixedu.bennu.core.groups.Group;
import org.fenixedu.bennu.core.i18n.BundleUtil;
import org.fenixedu.bennu.core.security.Authenticate;
import org.fenixedu.bennu.scheduler.annotation.Task;
import org.fenixedu.bennu.scheduler.custom.CustomTask;
import org.fenixedu.commons.i18n.I18N;
import org.fenixedu.messaging.core.domain.Message;

import java.util.Locale;

@Task(englishTitle = "Send a message with an unsupported locale", readOnly = false)
public class SendInternationalMessage extends CustomTask {
    @Override
    public void runTask() throws Exception {
        Authenticate.mock(DynamicGroup.get("managers").getMembers().iterator().next());
        Message.fromSystem()
                .subject(
                        BundleUtil.getLocalizedString("resources.MessagingResources", "message.international.subject").with(
                                Locale.ITALIAN, "Buona Sera"))
                .textBody(
                        BundleUtil.getLocalizedString("resources.MessagingResources", "message.international.body").with(
                                Locale.ITALIAN, "Come stai?")).to(Sets.newHashSet(Group.parse("U(bennu0,bennu1,bennu2)")))
                .cc(Sets.newHashSet(Group.parse("U(bennu3)"))).replyToSender().singleBcc("italian@italia.it")
                .preferredLocale(I18N.getLocale()).send();
    }
}
