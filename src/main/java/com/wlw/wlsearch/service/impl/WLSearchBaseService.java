package com.wlw.wlsearch.service.impl;

import com.wlw.wlsearch.config.WLSearchConfig;
import com.wlw.wlsearch.dto.SearchParam;
import com.wlw.wlsearch.entity.SearchResult;
import com.wlw.wlsearch.service.WLSearchService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.ExistsQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptService;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author fuxg
 * @create 2017-01-23 15:29
 */
public abstract class WLSearchBaseService implements WLSearchService {

    protected SearchParam searchParam;
    protected ElasticsearchTemplate elasticsearchTemplate;
    protected QueryBuilder queryBuilder;
    protected SortBuilder sortBuilder;
    private FunctionScoreQueryBuilder functionScoreQueryBuilder;
    private String scoreScript;

    @Override
    public Page<SearchResult> search(SearchParam searchParam) {
        this.searchParam = searchParam;
        this.elasticsearchTemplate = elasticsearchTemplate();
        this.queryBuilder = queryBuilder();
        this.sortBuilder = sortBuilder();
        this.scoreScript = scoreScript();

        functionScoreQueryBuilder = new FunctionScoreQueryBuilder(queryBuilder);
        setScoreScript();
        SearchResponse response = execute();

        return null;
    }

    private SearchResponse execute() {
        SearchResponse response = new SearchResponse();
        try {
            SearchRequestBuilder searchRequestBuilder = elasticsearchTemplate.getClient()
                    .prepareSearch(WLSearchConfig.INDEX_NAME_WL)
                    .setTypes(types())
                    .setQuery(functionScoreQueryBuilder)
                    .setFrom(searchParam.getPage())
                    .setSize(searchParam.getPageSize());
            if (sortBuilder != null) {
                searchRequestBuilder.addSort(sortBuilder);
            }
            response = searchRequestBuilder.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void setScoreScript() {
        if (StringUtils.isNotBlank(scoreScript)) {
            Script script = new Script(scoreScript, ScriptService.ScriptType.INLINE, "groovy", Collections.emptyMap());
            ScoreFunctionBuilder scoreBuilder = ScoreFunctionBuilders.scriptFunction(script);
            functionScoreQueryBuilder.add(scoreBuilder).boostMode("replace");
        }
    }

    protected abstract String[] types();

    protected abstract ElasticsearchTemplate elasticsearchTemplate();

    protected abstract QueryBuilder queryBuilder();

    protected String scoreScript() {
        return "";
    }

    protected SortBuilder sortBuilder() {
        return null;
    }

    protected QueryBuilder areaQueryBuilder() {
        Long areaid = searchParam.getAreaid();
        Long cityid = searchParam.getCityid();
        Long provid = searchParam.getProvid();
        if ((areaid != null && areaid > 0) || (cityid != null && cityid > 0) || (provid != null && provid > 0)) {
            String searchField;
            Long searchValue;
            if (areaid != null && areaid > 0) {
                searchField = "areaid";
                searchValue = areaid;
            } else if (cityid != null && cityid > 0) {
                searchField = "cityid";
                searchValue = cityid;
            } else {
                searchField = "provid";
                searchValue = provid;
            }
            BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
            boolQueryBuilder.mustNot(new ExistsQueryBuilder(searchField));
            boolQueryBuilder.should(new TermQueryBuilder(searchField, searchValue));
            return boolQueryBuilder;
        } else {
            return null;
        }
    }

}
