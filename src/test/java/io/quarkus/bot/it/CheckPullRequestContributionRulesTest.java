package io.quarkus.bot.it;

import static io.quarkiverse.githubapp.testing.GitHubAppTesting.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kohsuke.github.GHEvent;
import org.kohsuke.github.GHPullRequest;
import org.mockito.junit.jupiter.MockitoExtension;

import io.quarkiverse.githubapp.testing.GitHubAppTest;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@GitHubAppTest
@ExtendWith(MockitoExtension.class)
public class CheckPullRequestContributionRulesTest {

    private GHPullRequest mockPR;

    /**
     * Check of PR's commits is not processed
     * if features's list in 'quarkus-github-bot.yml' file does not contain
     * CHECK_CONTRIBUTION_RULES
     */
    @Test
    void checkNotProcessedIfNotInConfig() throws IOException {

        given().github(mocks -> {
            mocks.configFile("quarkus-github-bot.yml").fromString("features: [ ]\n");
            mockPR = mocks.pullRequest(samplePullRequestId);
        })
                .when().payloadFromString(getSamplePullRequestPayload())
                .event(GHEvent.PULL_REQUEST)
                .then().github(mocks -> {

                    verify(mockPR, times(0)).listCommits();

                    verifyNoMoreInteractions(mocks.ghObjects());
                });
    }

    private static long samplePullRequestId = 1091703530;

    private static String getSamplePullRequestPayload() {
        return """
                {
                    "action": "opened",
                    "number": 26,
                    "pull_request": {
                      "url": "https://api.github.com/repos/TestUser/test-playground-repo/pulls/26",
                      "id": 1091703530,
                      "number": 26,
                      "state": "open",
                      "title": "Test Pull Request Title",
                      "user": {
                        "login": "TestUser",
                        "type": "User"
                      },
                      "body": null,
                      "created_at": "2022-10-19T04:06:36Z",
                      "updated_at": "2022-10-19T04:06:36Z",
                      "assignee": null,
                      "base": {
                        "label": "TestUser:main",
                        "ref": "main",
                        "sha": "585d5655021b58b521d76f2fb40b77d76fb4eea5"
                      }
                    },
                    "repository": {
                      "id": 528667691,
                      "name": "test-playground-repo",
                      "full_name": "TestUser/test-playground-repo"
                    },
                    "sender": {
                      "login": "TestUser",
                      "id": 8484741
                    }
                  }
                    """;
    }

}