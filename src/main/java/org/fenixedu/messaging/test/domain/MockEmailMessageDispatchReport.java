package org.fenixedu.messaging.test.domain;

import static pt.ist.fenixframework.FenixFramework.atomic;

import org.fenixedu.messaging.domain.MessageDispatchReport;
import org.joda.time.DateTime;

public class MockEmailMessageDispatchReport extends MockEmailMessageDispatchReport_Base {

    public class SendingThread extends Thread {
        public int nMails;
        public MessageDispatchReport report;

        public SendingThread(int nMails, MessageDispatchReport report) {
            this.nMails = nMails;
            this.report = report;
        }

        @Override
        public void run() {
            int times = nMails;
            while (times > 0) {
                int rand = (int) Math.floor(Math.random() * 3);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                switch (rand) {
                case 0:
                    atomic(() -> {
                        report.setDeliveredCount(report.getDeliveredCount() + 1);
                    });
                    break;
                case 1:
                    atomic(() -> {
                        report.setFailedCount(report.getFailedCount() + 1);
                    });
                    break;
                case 2:
                    atomic(() -> {
                        report.setInvalidCount(report.getInvalidCount() + 1);
                    });
                    break;
                }
                times--;
            }
            atomic(() -> {
                report.setFinishedDelivery(DateTime.now());
            });
        };
    }

    public MockEmailMessageDispatchReport(int nMails) {
        super();
        setDeliveredCount(0);
        setInvalidCount(0);
        setFailedCount(0);
        setTotalCount(nMails);
        new SendingThread(nMails, this).start();
    }
}
