package com.wlw.wlsearch.service.impl;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptService;
import org.elasticsearch.search.sort.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author fuxg
 * @create 2017-01-23 16:30
 */
@Service("wl_store_search")
public class StoreSearchServiceImpl extends WLSearchBaseService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    protected ElasticsearchTemplate elasticsearchTemplate() {
        return elasticsearchTemplate;
    }

    @Override
    protected QueryBuilder queryBuilder() {
        String searchText = searchParam.getContent();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.should(new WildcardQueryBuilder("title", "*" + searchText + "*"));
        boolQueryBuilder.should(new WildcardQueryBuilder("shorttitle", "*" + searchText + "*"));
        boolQueryBuilder.should(new TermQueryBuilder("content", searchText));
        QueryBuilder areaQueryBuilder = areaQueryBuilder();
        if (areaQueryBuilder != null) {
            boolQueryBuilder.must(areaQueryBuilder);
        }
        return boolQueryBuilder;
    }

    @Override
    protected String scoreScript() {
        Long areaid = searchParam.getAreaid();
        Long cityid = searchParam.getCityid();
        String scoreScript = "";
        if ((areaid != null && areaid > 0) || (cityid != null && cityid > 0)) { //搜索范围在市以内
            scoreScript = "doc['agent_type'].value == 1 ? 5 : 1";
        } else {  //搜索范围在省或全国
            Long locationCityid = searchParam.getLocationCityid();
            if (locationCityid != null && locationCityid > 0) {
                scoreScript = "(" + cityid + " == doc['cityid'].value && doc['agent_type'].value ==1) ? 5: 1";
            }
        }
        return scoreScript;
    }

    @Override
    protected SortBuilder sortBuilder() {
        if (searchParam.getLatitude() != null && searchParam.getLongitude() != null) {
            GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location");
            sort.unit(DistanceUnit.KILOMETERS);
            sort.order(SortOrder.ASC);
            sort.point(searchParam.getLatitude(), searchParam.getLongitude());
            return sort;
        }
        return null;
    }
}
