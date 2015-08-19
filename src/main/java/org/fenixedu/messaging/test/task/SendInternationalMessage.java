package org.fenixedu.messaging.test.task;

import java.util.Locale;

import org.fenixedu.bennu.core.groups.DynamicGroup;
import org.fenixedu.bennu.core.i18n.BundleUtil;
import org.fenixedu.bennu.core.security.Authenticate;
import org.fenixedu.bennu.scheduler.annotation.Task;
import org.fenixedu.bennu.scheduler.custom.CustomTask;
import org.fenixedu.commons.i18n.LocalizedString;
import org.fenixedu.messaging.domain.MessagingSystem;
import org.fenixedu.messaging.domain.Sender;
import org.fenixedu.messaging.ui.MessageBean;

@Task(englishTitle = "Send a message with an unsupported locale", readOnly = false)
public class SendInternationalMessage extends CustomTask {
    @Override
    public void runTask() throws Exception {
        Authenticate.mock(DynamicGroup.get("managers").getMembers().iterator().next());
        Sender sysSender = MessagingSystem.getInstance().getSystemSender();
        MessageBean mb = new MessageBean();
        mb.setSender(sysSender);
        mb.setRecipientGroups(sysSender.getRecipients());
        mb.setReplyToObjects(sysSender.getReplyTos());
        mb.setBccs("italian@italia.it");
        mb.setExtraBccsLocale(Locale.ITALIAN);
        mb.setAutomaticFooter(true);
        LocalizedString subject =
                BundleUtil.getLocalizedString("resources.MessagingResources", "message.international.subject").with(
                        Locale.ITALIAN, "Buona Sera");
        LocalizedString body =
                BundleUtil.getLocalizedString("resources.MessagingResources", "message.international.body").with(Locale.ITALIAN,
                        "Come stai?");
        mb.setSubject(subject);
        mb.setBody(body);
        mb.send();
    }
}
