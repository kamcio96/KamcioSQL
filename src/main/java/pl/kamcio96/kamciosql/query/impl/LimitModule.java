package pl.kamcio96.kamciosql.query.impl;

class LimitModule {

    private int limit = -1;
    private int offset = -1;

    void limit(int limit) {
        this.limit = limit;
    }

    void offset(int offset) {
        this.offset = offset;
    }

    String getQueryPart() {
        StringBuilder builder = new StringBuilder();
        if(limit > 0) {
            builder.append(" LIMIT ").append(limit);
            if(offset > 0) {
                builder.append(" OFFSET ").append(offset);
            }
            builder.append(" ");
        }
        return builder.toString();
    }
}
