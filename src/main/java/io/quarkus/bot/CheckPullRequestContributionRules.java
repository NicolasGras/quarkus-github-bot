package io.quarkus.bot;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import javax.inject.Inject;

import org.kohsuke.github.GHCheckRunBuilder;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHEventPayload;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHPullRequestCommitDetail;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.PagedIterable;
import org.kohsuke.github.GHCheckRun.AnnotationLevel;
import org.kohsuke.github.GHCheckRun.Conclusion;
import org.kohsuke.github.GHCheckRun.Status;
import org.kohsuke.github.GHCheckRunBuilder.Annotation;
import org.kohsuke.github.GHCheckRunBuilder.Output;

import io.quarkiverse.githubapp.ConfigFile;
import io.quarkiverse.githubapp.event.PullRequest;
import io.quarkus.bot.config.Feature;
import io.quarkus.bot.config.QuarkusGitHubBotConfig;
import io.quarkus.bot.config.QuarkusGitHubBotConfigFile;

public class CheckPullRequestContributionRules {

    private static final Logger LOG = Logger.getLogger(CheckPullRequestContributionRules.class);

    @Inject
    QuarkusGitHubBotConfig quarkusBotConfig;

    void checkPullRequestContributionRules(
            @PullRequest.Opened @PullRequest.Reopened @PullRequest.Edited @PullRequest.Synchronize GHEventPayload.PullRequest pullRequestPayload,
            @ConfigFile("quarkus-github-bot.yml") QuarkusGitHubBotConfigFile quarkusBotConfigFile) throws IOException {

        if (!Feature.CHECK_CONTRIBUTION_RULES.isEnabled(quarkusBotConfigFile)) {
            return;
        }

        GHPullRequest pullRequest = pullRequestPayload.getPullRequest();
        PagedIterable<GHPullRequestCommitDetail> ghCOmmits = pullRequest.listCommits();

        if (!quarkusBotConfig.isDryRun()) {
            LOG.info("Check PR commits : " + ghCOmmits.toList());

        } else {
            LOG.info("Dry run execution");
        }
    }

}