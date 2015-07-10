package org.fenixedu.messaging.test.mock;

import java.util.HashSet;
import java.util.Set;

import org.fenixedu.messaging.domain.Message;
import org.fenixedu.messaging.domain.MessageDispatchReport;
import org.fenixedu.messaging.domain.MessageDispatcher;
import org.fenixedu.messaging.test.domain.MockEmailMessageDispatchReport;

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
