package io.quarkus.bot.workflow;

public class WorkflowConstants {

    @Deprecated
    public static final String SUREFIRE_REPORTS_ARTIFACT_PREFIX = "surefire-reports-";
    public static final String BUILD_REPORTS_ARTIFACT_PREFIX = "build-reports-";
    public static final String MESSAGE_ID_ACTIVE = "<!-- Quarkus-GitHub-Bot/msg-id:workflow-run-status-active -->";
    public static final String MESSAGE_ID_HIDDEN = "<!-- Quarkus-GitHub-Bot/msg-id:workflow-run-status-hidden -->";
    public static final String QUARKUS_CI_WORKFLOW_NAME = "Quarkus CI";
    public static final String QUARKUS_DOCUMENTATION_CI_WORKFLOW_NAME = "Quarkus Documentation CI";
    public static final String PULL_REQUEST_NUMBER_PREFIX = "pull-request-number-";
    public static final String JVM_TESTS_PREFIX = "JVM Tests - ";
    public static final String WINDOWS = "Windows";
}
