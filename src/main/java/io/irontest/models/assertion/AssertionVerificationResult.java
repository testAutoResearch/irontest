package io.irontest.models.assertion;

/**
 * Output of assertion verifier.
 * Created by Zheng on 5/08/2015.
 */
public class AssertionVerificationResult {
    private long assertionId;         //  id of the assertion in question
    private Boolean passed;          //  true if assertion verification passed, false otherwise, null if not verified
    private String error;            //  message of error occurred during verification

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public long getAssertionId() {
        return assertionId;
    }

    public void setAssertionId(long assertionId) {
        this.assertionId = assertionId;
    }
}
