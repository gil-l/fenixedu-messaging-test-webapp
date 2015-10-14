package org.fenixedu.messaging.test.mock;

import org.fenixedu.messaging.core.dispatch.MessageDispatcher;
import org.fenixedu.messaging.core.domain.MessageDispatchReport;
import org.fenixedu.messaging.core.domain.Message;
import org.fenixedu.messaging.test.domain.MockEmailMessageDispatchReport;

import java.util.HashSet;
import java.util.Set;

public class MockDispatcher implements MessageDispatcher {

    @Override
    public MessageDispatchReport dispatch(Message message) {
        Set<String> mails = new HashSet<String>(message.getTos());
        mails.addAll(message.getCcs());
        mails.addAll(message.getBccs());
        MessageDispatchReport report = new MockEmailMessageDispatchReport(mails.size());
        return report;
    }
}
