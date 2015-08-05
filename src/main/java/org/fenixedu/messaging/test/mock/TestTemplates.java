package org.fenixedu.messaging.test.mock;

import org.fenixedu.messaging.annotation.MessageTemplate;

@MessageTemplate(id = "template1", name = "template.first.name", description = "template.first.description",
        subject = "template.example.subject", body = "template.example.text", html = "template.example.html",
        bundle = "resources.MessagingResources")
@MessageTemplate(id = "template2", name = "template.second.name", description = "template.second.description",
        subject = "template.example.subject", body = "template.example.text", bundle = "resources.MessagingResources")
@MessageTemplate(id = "template3", name = "template.third.name", description = "template.third.description",
        subject = "template.example.subject", bundle = "resources.MessagingResources")
@MessageTemplate(id = "empty-template", name = "Empty Template", description = "An empty template")
public class TestTemplates {

}