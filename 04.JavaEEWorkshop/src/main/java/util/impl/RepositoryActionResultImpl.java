package util.impl;

import util.api.RepositoryActionResult;

public class RepositoryActionResultImpl implements RepositoryActionResult {

    private Object result;

    @Override
    public Object getResult() {
        return this.result;
    }

    @Override
    public void setResult(Object result) {
        this.result = result;
    }
}
