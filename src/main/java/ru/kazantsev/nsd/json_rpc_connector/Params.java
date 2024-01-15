package ru.kazantsev.nsd.json_rpc_connector;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;

public abstract class Params {
    public static class Create extends Params {
        String fqn;
        Attrs attrs;
        List<String> view = null;

        public Create(String fqn, Attrs attrs) {
            this.fqn = fqn;
            this.attrs = attrs;
        }

        public Create(String fqn, Attrs attrs, List<String> returnAttrs) {
            this(fqn, attrs);
            this.view = returnAttrs;
        }

        public String getFqn() {
            return fqn;
        }

        public Attrs getAttrs() {
            return attrs;
        }

        public List<String> getView() {
            return view;
        }
    }

    public static class Get extends Params {
        String fqn;
        Query query;
        List<String> view = null;

        public Get(String fqn, Query query) {
            this.fqn = fqn;
            this.query = query;
        }

        public Get(String fqn, Query query, List<String> returnAttrs) {
            this(fqn, query);
            this.view = returnAttrs;
        }

        public String getFqn() {
            return fqn;
        }

        public Query getQuery() {
            return query;
        }

        public List<String> getView() {
            return view;
        }
    }

    public static class Edit extends Params {
        String uuid;
        Query query;
        Attrs attrs;
        List<String> view = null;

        public Edit(String uuid, Attrs attrs) {
            this.uuid = uuid;
            this.attrs = attrs;
        }

        public Edit(String uuid, Attrs attrs, List<String> returnAttrs) {
            this(uuid, attrs);
            this.view = returnAttrs;
        }

        public Edit(Query query, Attrs attrs) {
            this.query = query;
            this.attrs = attrs;
        }

        public Edit(Query query, Attrs attrs, List<String> returnAttrs) {
            this(query, attrs);
            this.view = returnAttrs;
        }

        public String getUuid() {
            return uuid;
        }

        public Query getQuery() {
            return query;
        }

        public Attrs getAttrs() {
            return attrs;
        }

        public List<String> getView() {
            return view;
        }
    }

    static class Find extends Params {
        String fqn;
        Query query;
        List<String> view = null;
        Long limit;
        Long offset;

        Find(String fqn, Query query) {
            this.fqn = fqn;
            this.query = query;
        }

        Find(String fqn, Query query, List<String> returnAttrs) {
            this(fqn, query);
            this.view = returnAttrs;
        }

        Find(String fqn, Query query, List<String> returnAttrs, Long limit, Long offset) {
            this(fqn, query, returnAttrs);
            this.limit = limit;
            this.offset = offset;
        }

        public String getFqn() {
            return fqn;
        }

        public Query getQuery() {
            return query;
        }

        public List<String> getView() {
            return view;
        }

        public Long getLimit() {
            return limit;
        }

        public Long getOffset() {
            return offset;
        }
    }
}
